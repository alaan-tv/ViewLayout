/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package media.dee.dcms.layout.render;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import media.dee.dcms.core.GraphNode;
import media.dee.dcms.core.components.UUID;
import media.dee.dcms.core.components.WebComponent;
import media.dee.dcms.core.db.GraphDatabaseService;
import media.dee.dcms.core.db.NoSuchRecordException;
import media.dee.dcms.core.db.Record;
import media.dee.dcms.core.layout.RenderException;
import media.dee.dcms.core.layout.Template;
import media.dee.dcms.core.services.RenderService;
import media.dee.dcms.core.services.TemplateService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.*;
import java.util.Collections;


@Component
@UUID("renderer")
@WebComponent.Command.For(value="getData", component = HTML5Renderer.class)
public class HTML5Renderer implements RenderService,WebComponent.Command, WebComponent {

    private GraphDatabaseService<?> graphDatabaseService;
    private TemplateService templateService;

    @Reference
    void setGraphDatabaseService(GraphDatabaseService<?> graphDatabaseService){
        this.graphDatabaseService = graphDatabaseService;
    }

    @Reference
    void setTemplateService(TemplateService templateService){
        this.templateService = templateService;
    }

    @Override @SuppressWarnings("unchecked")
    public JsonNode execute(JsonNode... arguments) {
        long id = arguments[0].asLong();
        String query = "MATCH (v:Viewable{id: {id} })RETURN v";
        try {
            Record r = graphDatabaseService.fetchOne(query, Collections.singletonMap("id", id ));

            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            render(out, r.get("v"));
            return new ObjectMapper().createObjectNode()
                    .put("html", out.toString("UTF-8") );
        } catch (NoSuchRecordException | UnsupportedEncodingException | RenderException e) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            e.printStackTrace(new PrintWriter(out));
            return new ObjectMapper().createObjectNode()
                    .put("error", "not-fount")
                    .put("error", out.toString() );
        }
    }

    @Override
    public void render(OutputStream outputStream, GraphNode model) throws RenderException {
        String query = "MATCH (m:Viewable)-[:TEMPLATE]->(t:Template) WHERE ID(m) = {id} RETURN t.template as template";
        try {
            Record r = graphDatabaseService.fetchOne(query, Collections.singletonMap("id", model.getRawId() ));
            StringBuffer outbuff = templateService.render(r.get("template"), model);
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.print(outbuff.toString());
            printWriter.close();

        } catch (NoSuchRecordException e) {
            e.printStackTrace(new PrintStream(outputStream));
        }
    }

    @Override
    public void render(OutputStream outputStream, Template template, GraphNode model) throws RenderException{

        StringBuffer outbuff = templateService.render(template.getTemplate(), model);
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.print(outbuff.toString());
        printWriter.close();

    }
}
