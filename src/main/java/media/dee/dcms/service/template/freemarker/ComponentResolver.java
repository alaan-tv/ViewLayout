package media.dee.dcms.service.template.freemarker;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import media.dee.dcms.Writer.HtmlWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class ComponentResolver implements TemplateMethodModelEx {
    HtmlWriter tree ;
    public ComponentResolver(HtmlWriter tree){
        this.tree = tree;
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        Document component = Jsoup.parse("<script>test</script><div> Test %s </div>\n");
        tree.addHeader(component.getElementsByTag("script").outerHtml());
        tree.addBody(String.format(component.getElementsByTag("div").outerHtml(),list.get(0)));
        return "";
    }
}
