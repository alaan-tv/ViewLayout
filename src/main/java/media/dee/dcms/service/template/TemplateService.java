package media.dee.dcms.service.template;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

public interface TemplateService {
    StringBuffer render(Map<String,String> template, Map dataModel) throws IOException, TemplateException;
}
