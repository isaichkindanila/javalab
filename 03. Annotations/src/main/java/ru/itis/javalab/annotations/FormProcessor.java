package ru.itis.javalab.annotations;

import com.google.auto.service.AutoService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Getter;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.tools.StandardLocation.CLASS_OUTPUT;

@AutoService(Processor.class)
@SupportedAnnotationTypes("ru.itis.javalab.annotations.HtmlForm")
public class FormProcessor extends AbstractProcessor {

    private final Template template;

    public FormProcessor() {
        var cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassForTemplateLoading(FormProcessor.class, "/");

        try {
            template = cfg.getTemplate("form.ftl");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void createForm(String name, HtmlForm form, List<FormFiled> fields) throws Exception {
        var file = processingEnv.getFiler().createResource(CLASS_OUTPUT, "", name + ".html");
        var writer = file.openWriter();

        template.process(new FormData(form, fields), writer);
        writer.close();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (var element : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
            var name = element.getSimpleName().toString();
            var form = element.getAnnotation(HtmlForm.class);
            var fields = element.getEnclosedElements()
                    .stream()
                    .map(e -> e.getAnnotation(FormFiled.class))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            try {
                createForm(name, form, fields);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        return true;
    }

    @Getter
    public static class FieldData {
        private final String name;
        private final String type;
        private final String placeholder;

        private FieldData(FormFiled filed) {
            name = filed.name();
            type = filed.type();
            placeholder = filed.placeholder();
        }
    }

    @Getter
    public static class FormData {
        private final String method;
        private final String action;
        private final List<FieldData> fields;

        private FormData(HtmlForm form, List<FormFiled> fieldList) {
            method = form.method();
            action = form.action();
            fields = fieldList.stream().map(FieldData::new).collect(Collectors.toList());
        }
    }
}
