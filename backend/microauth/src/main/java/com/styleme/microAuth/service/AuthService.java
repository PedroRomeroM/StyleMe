
package com.styleme.microAuth.service;


import com.styleme.microAuth.DTOs.AuthDTO;
import com.styleme.microAuth.DTOs.AuthTotalDTO;
import com.styleme.microAuth.DTOs.AuthUpdateDTO;
import com.styleme.microAuth.exception.AuthInsertException;
import com.styleme.microAuth.exception.AuthLoginException;
import com.styleme.microAuth.exception.AuthUpdateException;
import com.styleme.microAuth.exception.EncryptionException;
import com.styleme.microAuth.model.Auth;
import com.styleme.microAuth.model.CodeMailValidate;
import com.styleme.microAuth.repository.AuthCodeMailValidateRepository;
import com.styleme.microAuth.repository.AuthRepository;
import com.styleme.microAuth.utils.Encrypt;
import com.styleme.microAuth.utils.PasswordRecoverEmailTemplate;
import com.styleme.microAuth.utils.SendEmail;
import com.styleme.microAuth.utils.ValidateEmailEmailTemplate;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    
    private static String VALIDAR_EMAIL_STR = "AP"; //AG - validar ou AP - aprovado
    
    @Autowired
    private AuthRepository rep;
    
    @Autowired
    private AuthCodeMailValidateRepository codeRep;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private SendEmail sendEmail;
    
    
    public AuthDTO getAuthById(Integer id) throws AuthLoginException{
        Optional<Auth> conta = rep.findById(id);
        if(conta.isPresent()){
            AuthDTO dto = mapper.map(conta.get(), AuthDTO.class);
            return dto;
        }
        throw new AuthLoginException("Essa Conta Não Existe!");
    }
    
    public boolean emailExists(String email){
        Optional<Auth> op = rep.findByEmail(email);
        if(op.isPresent()){
            return true;
        }
        return false;
    }
    
    public AuthDTO getLogin(String email, String senha) throws AuthLoginException, EncryptionException {
        Optional<Auth> conta = rep.findByEmail(email);
        if (conta.isPresent()) {
            if (conta.get().getTipoUser().equals("AG")) {
                throw new AuthLoginException("Esse Email Ainda Não Foi Validado!");
            }
            
            senha = senha + conta.get().getSalt();
            boolean verify = Encrypt.verifyPasswordIsRight(senha, conta.get().getSenha());
            if (verify) {
                AuthDTO dto = mapper.map(conta.get(), AuthDTO.class);
                return dto;
            }
            throw new AuthLoginException("A Senha Está Errada!");
        }
        throw new AuthLoginException("Essa Conta Não Existe!");
    }
    
    
    public AuthDTO insertAuth(AuthDTO dto) throws AuthInsertException, EncryptionException {
        Optional<Auth> conta = rep.findByEmail(dto.getEmail());
        if (conta.isPresent()) {
            throw new AuthInsertException("Uma Conta Com Esse Email '" + dto.getEmail() + "' Já Existe!");
        }

        String salt = Encrypt.gerarSalt(Encrypt.SALT_SIZE);
        String senha = Encrypt.encryptPassword(dto.getSenha(), salt);
        int sequence = dto.getId();
        
        String codValidate = Encrypt.gerarSalt(64);

        Auth reg = new Auth(sequence, dto.getEmail(), senha, salt, VALIDAR_EMAIL_STR);
        
        CodeMailValidate codMail = new CodeMailValidate(codValidate, sequence);
        codeRep.deleteById(codValidate);
        codeRep.save(codMail);
        
        try {
            reg = rep.save(reg);
        } catch (Exception e) {
            throw new AuthInsertException("A seguinte exception foi lançada: " + e.getMessage());
        }

        if("AG".equals(VALIDAR_EMAIL_STR)){
            ValidateEmailEmailTemplate emailTemplate = new ValidateEmailEmailTemplate(dto.getEmail(), codValidate);
            sendEmail.sendEmailWithHtmlTemplate(emailTemplate);
            //sendEmail.sendEmail(emailTemplate);
        }
        dto = mapper.map(reg, AuthDTO.class);
        return dto;
    }
    
    public boolean validateEmail(String cod){
        Optional<CodeMailValidate> model = codeRep.findById(cod);
        if(model.isPresent()){
            Optional<Auth> auth = rep.findById(model.get().getIdLogin());
            if(auth.isPresent()){
                Auth auth2 = auth.get();
                auth2.setTipoUser("AP");
                rep.save(auth2);
                codeRep.deleteById(cod);
                return true;
            }
        }
        return false;
    }
    
    public AuthTotalDTO updateAuth(AuthUpdateDTO dto) throws AuthUpdateException, EncryptionException{
        Optional<Auth> oldConta = rep.findByEmail(dto.getEmail());

        if(!oldConta.isPresent()) throw new AuthUpdateException("Essa Conta Não Existe!");
        
        Auth conta = oldConta.get();
        AuthTotalDTO dto2 = mapper.map(conta, AuthTotalDTO.class);
        
        String senha = dto.getSenha();
        String senha2 = dto.getNewsenha();
        
        if(!senha2.isEmpty()){
            if(Encrypt.verifyPasswordIsRight((senha+conta.getSalt()),conta.getSenha())){
                senha2 = Encrypt.encryptPassword(senha2, oldConta.get().getSalt());
                conta.setSenha(senha2);
            }else{
                throw new AuthUpdateException("A Senha Atual Está Incorreta!");
            }
        }
        
        rep.save(conta);
        
        return dto2;
    }
    
    
    public void recoverPassword(String email) throws AuthUpdateException, EncryptionException{
        Optional<Auth> oldConta = rep.findByEmail(email);

        if(!oldConta.isPresent()) throw new AuthUpdateException("Essa Conta Não Existe!");
        
        Auth conta = oldConta.get();
        //Gera uma senha random com 8 caracteres
        String senhaC = Encrypt.gerarSalt(8);
        
        String senha = Encrypt.encryptPassword(senhaC, oldConta.get().getSalt());
        conta.setSenha(senha);
        
        rep.save(conta);
        PasswordRecoverEmailTemplate temp = new PasswordRecoverEmailTemplate(conta.getEmail(), senhaC);
        sendEmail.sendEmailWithHtmlTemplate(temp);
        //sendEmail(temp);
    }
    
    public void delete(Integer id){
        rep.deleteById(id);
    }
    
    
}
