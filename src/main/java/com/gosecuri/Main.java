package com.gosecuri;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.gosecuri.htmlgeneration.AgentPageGenerator;

public class Main {

    public static void main(String[] args) throws IOException {
        String dirPath = System.getProperty("user.dir");
        File f = new File(dirPath + "/src/main/java/com/gosecuri/textfiles/agentfiles");

        String[] files = f.list();
        if(files != null || files.length > 0) {
            for (String file : files) {
                List<String> agentData = Files.readAllLines(Path.of(dirPath + "/src/main/java/com/gosecuri/textfiles/agentfiles/" + file));
                AgentPageGenerator apg = new AgentPageGenerator(agentData);
                apg.generateHTML();
            }
        }
    }
}