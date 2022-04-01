package com.gosecuri.htmlgeneration;

import java.io.IOException;

public interface HTMLGenerator {

    public void generateHTML() throws IOException;

    public void LoadHTMLTemplateToDocument() throws IOException;

}
