package media.dee.dcms.Writer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.Writer;

public class HtmlWriter extends Writer{
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

    }

    @Override
    public void flush() throws IOException {

    }

    private Document tree;

    public HtmlWriter(){
        tree = Jsoup.parse("");
    }

    public void write(String tag) {
        tree.body().append(tag);
    }

    public void addHeader(String tag){
        tree.head().append(tag);
    }

    public void addBody(String tag){
        tree.body().append(tag);
    }

    public StringBuffer getBuffer(){
        return new StringBuffer(tree.outerHtml());
    }
    @Override
    public void close() throws IOException {

    }

    @Override
    public String toString() {
        return tree.outerHtml();
    }
}
