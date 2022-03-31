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

    public static final String BASE_PATH = "/src/main/java/com/gosecuri";
    public static final String AGENT_TEMPLATE_PATH = BASE_PATH + "/templates/agent.html";
    public static final String GENERATED_AGENT_PAGE_FOLDER = BASE_PATH + "/generated/";
    public static final String IDENTITY_CARDS_PATH = BASE_PATH + "/identitycards/";

    private final String dirPath;
    private Document doc;
    private final List<String> agentData;
    private final String agentFileName;

    public AgentPageGenerator(List<String> _agentData) {
        dirPath = System.getProperty("user.dir");
        agentData = _agentData;
        agentFileName = generateFileName();
    }

    public String generateFileName() {
        //Last name is index 0
        //First name is index 1
        return agentData.get(1).toLowerCase(Locale.ROOT).charAt(0) + agentData.get(0).toLowerCase(Locale.ROOT);
    }

    private void LoadHTMLTemplateToDocument() throws IOException {
        File htmlTemplateFile = new File(dirPath + AGENT_TEMPLATE_PATH);
        String htmlString = FileUtils.readFileToString(htmlTemplateFile, StandardCharsets.UTF_8);
        doc = Jsoup.parse(htmlString);
    }

    private void toggleEquipment() {
        //Check equipment
        boolean isEquipment = false;
        for(String line : agentData) {
            if(isEquipment) {
                for(Element checkbox : doc.select(".checkbox")) {
                    if(checkbox.id().equals(line)) {
                        checkbox.addClass("checked");
                    }
                }
            }
            if(line.equals("")) {
                isEquipment = true;
            }
        }

        //Inject identity
        String identity = agentData.get(1) + " " + agentData.get(0);
        doc.getElementById("agent-name").text(identity);
    }

    private String getIdentityCardPath() {
        return dirPath + IDENTITY_CARDS_PATH + agentFileName + ".png";
    }

    private void addIdentityCard() {
        Element img = doc.select("#identity").first();
        img.attr("src", getIdentityCardPath());
    }

    public void generateHTML() throws IOException {
        LoadHTMLTemplateToDocument();
        toggleEquipment();
        addIdentityCard();
        File newHtmlFile = new File(dirPath + GENERATED_AGENT_PAGE_FOLDER + agentFileName + ".html");
        FileUtils.writeStringToFile(newHtmlFile, doc.toString(), (String) null);
    }
}
