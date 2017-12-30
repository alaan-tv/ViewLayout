package media.dee.dcms.service.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import media.dee.dcms.Writer.HtmlWriter;
import media.dee.dcms.service.template.TemplateService;
import media.dee.dcms.service.template.freemarker.ComponentResolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerTemplateService implements TemplateService {
    private static Configuration cfg = null;

    static{
        cfg = new Configuration(new Version("2.3.0"));
    }

    public StringBuffer render(String name, String html, Map dataModel) throws IOException, TemplateException {
        HtmlWriter out = new HtmlWriter();
        if(dataModel == null)
            dataModel = new HashMap();
        dataModel.put(ComponentResolver.class.getName(),new ComponentResolver(out));
        Template template = new Template(name, html, cfg);
        template.process(dataModel,out);

        return out.getBuffer();
    }

    @Override
    public StringBuffer render(Map<String,String> template, Map dataModel) throws IOException, TemplateException {

        return render(template.get("name"), template.get("html"),dataModel);
    }
}
