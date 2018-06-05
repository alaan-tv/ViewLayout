package media.dee.dcms.layout.model;

import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class ScriptDependency implements Dependency {
    private String code;
    private InjectionPoint injectionPoint;

    public ScriptDependency(String code, String injectionPoint){
        this.code = code;
        this.injectionPoint = new InjectionPoint(injectionPoint);
    }

    @Override
    public String asEmbedded() {
        return String.format("<style>%s</style>", this.code);
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
        return new Element("script")
                .attr("type", "text/javascript")
                .appendChild(new DataNode(code, ""));
    }

    @Override
    public InjectionPoint injectSelector() {
        return injectionPoint;
    }

    @Override
    public boolean equals(Object obj) {
        if( obj instanceof ScriptDependency){
            ScriptDependency other = (ScriptDependency) obj;
            return code.equals(other.code);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.code.hashCode();
    }


}
