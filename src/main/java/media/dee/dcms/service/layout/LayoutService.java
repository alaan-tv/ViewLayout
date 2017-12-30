package media.dee.dcms.service.layout;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

public interface LayoutService {
    StringBuffer resolve(String layoutID, Map dataModel) throws IOException, TemplateException;
}
