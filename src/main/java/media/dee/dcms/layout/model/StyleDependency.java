package media.dee.dcms.layout.model;

import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class StyleDependency implements Dependency {
    private String style;

    public StyleDependency(String style){
        this.style = style;
    }

    @Override
    public String asEmbedded() {
        return String.format("<style>%s</style>", this.style);
    }

    @Override
    public URL asExternal() {
        try {
            return new URL("http","localhost", 80,UUID.randomUUID().toString());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    public Node asNode() {

        return new Element("style")
                .appendChild(new DataNode(style, ""));
    }

    @Override
    public InjectionPoint injectSelector() {
        return new InjectionPoint(InjectionOperation.APPEND, "head");
    }

    @Override
    public boolean equals(Object obj) {
        if( obj instanceof StyleDependency){
            StyleDependency other = (StyleDependency) obj;
            return style.equals(other.style);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.style.hashCode();
    }
    
}
