package com.gosecuri;

import com.gosecuri.agent.Agent;
import com.gosecuri.htmlgeneration.AgentPageGenerator;
import com.gosecuri.htmlgeneration.IndexPageGenerator;
import com.gosecuri.security.HtpasswdGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static com.gosecuri.utils.PathUtils.*;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        //Set output path of generated files
        String outputPath = GENERATED_FOLDER_PATH;
        if(args.length > 0) {
            String argPath = args[0];
            if(!argPath.endsWith("/")) outputPath = argPath + "/";
            else outputPath = argPath;
        }

        //Create list of Agent
        List<Agent> agents = new ArrayList<>();
        File agentFilesFolder = new File(AGENT_TEXT_FILES_FOLDER_PATH);
        String[] files = agentFilesFolder.list();
        if(files != null && files.length > 0) {
            for (String file : files) {
                List<String> agentData = Files.readAllLines(Path.of(AGENT_TEXT_FILES_FOLDER_PATH + file));
                agents.add(new Agent(agentData));
            }
        }

        //Generate agents html pages
        for(Agent agent : agents) {
            AgentPageGenerator apg = new AgentPageGenerator(outputPath, agent);
            apg.generateHTML();
        }

        //Generate index.html
        IndexPageGenerator idp = new IndexPageGenerator(outputPath, agents);
        idp.generateHTML();

        //Generate .htpasswd
        HtpasswdGenerator htpasswdGenerator = new HtpasswdGenerator(outputPath, agents);
        htpasswdGenerator.generateHtpasswd();
    }
}