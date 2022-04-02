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

import static com.gosecuri.utils.PathUtils.*;

public class AgentPageGenerator extends PageGenerator {

    private final List<String> agentData;
    private final String agentFileName;

    public AgentPageGenerator(final String outputPath, final List<String> _agentData) {
        super(outputPath);
        agentData = _agentData;
        agentFileName = generateFileName();
    }

    public String generateFileName() {
        //Last name is index 0, first name is index 1
        return agentData.get(1).toLowerCase(Locale.ROOT).charAt(0) + agentData.get(0).toLowerCase(Locale.ROOT);
    }

    private void addEquipment() {
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

        String identity = agentData.get(1) + " " + agentData.get(0);
        doc.getElementById("agent-name").text(identity);
    }

    private String getIdentityCardPath() {
        return (IDENTITY_CARDS_FOLDER_PATH + agentFileName + ".png").replaceAll("\\\\", "/");
    }

    private void addIdentityCard() {
        Element img = doc.select("#identity").first();
        img.attr("src", getIdentityCardPath());
    }

    @Override
    public void generateHTML() throws IOException {
        LoadHTMLTemplateToDocument(AGENT_TEMPLATE_PATH);
        addEquipment();
        addIdentityCard();
        File newHtmlFile = new File(outputPath + agentFileName + ".html");
        FileUtils.writeStringToFile(newHtmlFile, doc.toString(), (String) null);
    }
}