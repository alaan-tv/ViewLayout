package media.dee.dcms.layout.model;

import org.jsoup.nodes.Node;

import java.net.URL;

public interface Dependency {
    String asEmbedded();
    URL asExternal();
    Node asNode();
    InjectionPoint injectSelector();
}
