package uaic.info.catalog.commands;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import uaic.info.catalog.Catalog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements CatalogCommand{

    public static void report(Catalog catalog,String path) throws IOException, TemplateException
    {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File(path));

        Template template = cfg.getTemplate("template.ftl");

        Map<String, Object> data = new HashMap<>();
        data.put("catalog", catalog);

        Writer out = new FileWriter(new File("report.html"));
        template.process(data, out);
        out.flush();
        out.close();
    }
}
