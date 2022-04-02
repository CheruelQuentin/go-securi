package com.gosecuri.htmlgeneration;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.gosecuri.utils.PathUtils.*;

public class IndexPageGenerator extends PageGenerator {

    //Paths of all the HTML pages of agents
    private final List<String> allAgentPagePaths;

    public IndexPageGenerator(final String _outputPath) {
        super(_outputPath);
        allAgentPagePaths = getAllAgentPagePaths();
    }

    private List<String> getAllAgentPagePaths() {
        //Retrieve paths of generated HTML files
        File f = new File(outputPath);
        return List.of(f.list());
    }

    private Boolean agentHasPage(final String agentFileName) {
        for(String path : allAgentPagePaths) {
            if(path.contains(agentFileName)) return true;
        }
        return false;
    }

    private String getAgentPagePath(final String agentName) {
        for(String agentPagePath : allAgentPagePaths) {
            if(agentPagePath.contains(agentName)) return agentPagePath;
        }
        throw new RuntimeException("Could not find agent page");
    }

    private void addAgentLinks() throws IOException {
        //Load staff.txt to retrieve all the agents
        List<String> agents = Files.readAllLines(Path.of(STAFF_FILE_PATH));
        for(String agent : agents) {
            Element agentAnchor = doc.select(String.format("#%s", agent)).first();
            //Add agent link to index.html if the agent has a page
            if(agentHasPage(agent)) {
                agentAnchor.attr("href", getAgentPagePath(agent));
            }
            else {
                agentAnchor.addClass("disabled");
            }
        }
    }

    @Override
    public void generateHTML() throws IOException {
        LoadHTMLTemplateToDocument(INDEX_TEMPLATE_PATH);
        addAgentLinks();
        File newHtmlFile = new File(outputPath + "index.html");
        FileUtils.writeStringToFile(newHtmlFile, doc.toString(), (String) null);
    }
}
