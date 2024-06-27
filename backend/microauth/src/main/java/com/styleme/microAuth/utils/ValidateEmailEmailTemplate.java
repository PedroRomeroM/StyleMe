
package com.styleme.microAuth.utils;

import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.context.Context;

@Getter
@Setter
public class ValidateEmailEmailTemplate extends TemplateEmail{

    private static final String TEMPLATE_NAME = "confirmar_cadastro";
    
    public ValidateEmailEmailTemplate(String to, String cod) {
        super(to);
        this.setSubject("Validação de Email - Style.Me");
        this.setTemplateName(TEMPLATE_NAME);
        String link = "http://localhost:8082/api/validate?cod="+cod;
        Context ctx = new Context();
        ctx.setVariable("link", link);
        setContext(ctx);
    }
    
    
}
