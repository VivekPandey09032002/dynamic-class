package com.nucleus.generator.config;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DisplayTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemplateConfig {

    @Bean
    public Template velocityTemplate(){
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        return velocityEngine.getTemplate("src/main/resources/template/class.vm");
    }

    @Bean
    public VelocityContext velocityContext(){
        VelocityContext context = new VelocityContext();
        context.put("display", new DisplayTool());
        return context;
    }
}
