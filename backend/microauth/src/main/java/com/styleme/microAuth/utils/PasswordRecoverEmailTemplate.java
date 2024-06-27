
package com.styleme.microAuth.utils;

import org.thymeleaf.context.Context;

public class PasswordRecoverEmailTemplate extends TemplateEmail{
    
    private static final String TEMPLATE_NAME = "recuperar_senha";
    
    public PasswordRecoverEmailTemplate(String to, String senha) {
        super(to);
        //String body = "A Sua nova senha é: '"+ senha +"'";
        setBody(senha);
        setSubject("Recuperação de Senha");
        setTemplateName(TEMPLATE_NAME);
        Context ctx = new Context();
        ctx.setVariable("senha", senha);
        setContext(ctx);
    }
    
    
    
}
