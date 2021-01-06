package ru.itis.javalab.queue.chain.services.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.itis.javalab.queue.chain.services.interfaces.TemplateService;

import java.io.IOException;

@Service
@AllArgsConstructor
class TemplateServiceFreemarkerImpl implements TemplateService {

    private final Configuration configuration;

    @Override
    public String process(String templateName, Object model) {
        try {
            Template template = configuration.getTemplate(templateName + ".ftlh");
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
