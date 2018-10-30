package com.db2.templates;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

// todo: used as common
public class CommonGenerator {
    private static final String HTML_DIR = "resources/templates";

    private static CommonGenerator pageGenerator;
    private final Configuration cfg;

    public static CommonGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new CommonGenerator();
        return pageGenerator;
    }
    // todo: remove
    public String makePage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    private CommonGenerator() {
        cfg = new Configuration();
    }
}
