package com.gosecuri;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.gosecuri.htmlgeneration.AgentPageGenerator;
import com.gosecuri.htmlgeneration.IndexPageGenerator;

import static com.gosecuri.utils.PathUtils.AGENT_TEXT_FILES_FOLDER_PATH;
import static com.gosecuri.utils.PathUtils.GENERATED_FOLDER_PATH;

public class Main {

    public static void main(String[] args) throws IOException {
        String outputPath = GENERATED_FOLDER_PATH;
        if(args.length > 0)
            outputPath = args[0];

        System.out.println(outputPath);

        File f = new File(AGENT_TEXT_FILES_FOLDER_PATH);

        String[] files = f.list();
        if(files != null && files.length > 0) {
            for (String file : files) {
                List<String> agentData = Files.readAllLines(Path.of(AGENT_TEXT_FILES_FOLDER_PATH + file));
                AgentPageGenerator apg = new AgentPageGenerator(outputPath, agentData);
                apg.generateHTML();
            }
        }

        IndexPageGenerator idp = new IndexPageGenerator(outputPath);
        idp.generateHTML();
    }
}