package com.gosecuri.htmlgeneration;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

public class AgentPageGenerator {

    public static final String AGENT_TEMPLATE_PATH = "/src/main/java/com/gosecuri/templates/agent.html";
    public static final String GENERATED_AGENT_PAGE_FOLDER = "/src/main/java/com/gosecuri/generated/";

    private final String dirPath;
    private Document doc;
    private final List<String> agentData;

    public AgentPageGenerator(List<String> _agentData) {
        dirPath = System.getProperty("user.dir");
        agentData = _agentData;
    }

    private void LoadHTMLTemplateToDocument() throws IOException {
        File htmlTemplateFile = new File(dirPath + AGENT_TEMPLATE_PATH);
        String htmlString = FileUtils.readFileToString(htmlTemplateFile, StandardCharsets.UTF_8);
        doc = Jsoup.parse(htmlString);
    }

    private void injectDataFromFile() {
        //Check equipment
        boolean isEquipment = false;
        for(String line : agentData) {
            if(isEquipment) {
                for(Element input : doc.select("input")) {
                    if(input.id().equals(line)) {
                        input.attr("checked", true);
                    }
                }
            }
            if(line.equals("")) {
                isEquipment = true;
            }
        }

        //Inject identity
        String identity = agentData.get(1) + " " + agentData.get(0);
        doc.getElementById("identification").text(identity);
    }

    public String generateFileName() {
        //Last name is index 0
        //First name is index 1
        return agentData.get(1).toLowerCase(Locale.ROOT).charAt(0) + agentData.get(0).toLowerCase(Locale.ROOT) + ".html";
    }

    public void generateHTML() throws IOException {
        LoadHTMLTemplateToDocument();
        injectDataFromFile();
        File newHtmlFile = new File(dirPath + GENERATED_AGENT_PAGE_FOLDER + generateFileName());
        FileUtils.writeStringToFile(newHtmlFile, doc.toString(), (String) null);
    }
}
