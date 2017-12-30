package media.dee.dcms.service.layout.impl;


import com.sun.javafx.tools.ant.DeployFXTask;
import freemarker.template.TemplateException;
import media.dee.dcms.repository.LayoutRepository;
import media.dee.dcms.service.layout.LayoutService;
import media.dee.dcms.service.template.TemplateService;

import java.io.IOException;
import java.util.Map;

public class DefaultLayoutService implements LayoutService {

    TemplateService templateService;

    LayoutRepository layoutRepository;
    @Override
    public StringBuffer resolve(String layoutID, Map dataModel) throws IOException, TemplateException {

        Map layout = layoutRepository.findOne(layoutID);
        templateService.render(layout,dataModel);
        return null;
    }
}
