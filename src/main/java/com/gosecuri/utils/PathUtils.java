package com.gosecuri.utils;

public final class PathUtils {

    public static final String DIR_PATH = System.getProperty("user.dir");
    public static final String BASE_PATH = DIR_PATH + "/src/main/java/com/gosecuri";

    public static final String AGENT_TEMPLATE_PATH = BASE_PATH + "/templates/agent.html";
    public static final String INDEX_TEMPLATE_PATH = BASE_PATH + "/templates/index.html";

    public static final String GENERATED_FOLDER_PATH = BASE_PATH + "/generated/";
    public static final String IDENTITY_CARDS_PATH = BASE_PATH + "/identitycards/";
    public static final String AGENT_TEXT_FILES_PATH = BASE_PATH + "/src/main/java/com/gosecuri/textfiles/agentfiles/";
    public static final String STAFF_FILE_PATH = BASE_PATH + "/textfiles/staff.txt";

    private PathUtils() { }
}
