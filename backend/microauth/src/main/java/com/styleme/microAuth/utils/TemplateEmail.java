
package com.styleme.microAuth.utils;


import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.context.Context;

@Getter
@Setter
public class TemplateEmail {
    
    private String to;
    private String subject;
    private String body;
    private String templateName;
    private Context context;

    public TemplateEmail(String to) {
        this.to = to;
    }
    
    
    
}
