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

public class IndexPageGenerator implements HTMLGenerator{

    private Document doc;
    //Paths of all the HTML pages of agents
    private List<String> allAgentPagePaths;

    public IndexPageGenerator() {
        allAgentPagePaths = getAllAgentPagePaths();
    }

    private List<String> getAllAgentPagePaths() {
        File f = new File(GENERATED_FOLDER_PATH);
        return List.of(f.list());
    }

    private Boolean agentHasPage(String agentFileName) {
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
    public void LoadHTMLTemplateToDocument() throws IOException {
        File htmlTemplateFile = new File(INDEX_TEMPLATE_PATH);
        String htmlString = FileUtils.readFileToString(htmlTemplateFile, StandardCharsets.UTF_8);
        doc = Jsoup.parse(htmlString);
    }

    @Override
    public void generateHTML() throws IOException {
        LoadHTMLTemplateToDocument();
        addAgentLinks();
        File newHtmlFile = new File(GENERATED_FOLDER_PATH + "index.html");
        FileUtils.writeStringToFile(newHtmlFile, doc.toString(), (String) null);
    }
}
