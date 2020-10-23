package com.stddev.algdat7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextFileHandler {
    private File file;
    private char[] characters;

    /**
     * Constructor method
     *
     * @param path path to text file
     */
    public TextFileHandler(String path) throws IOException {
        file = new File(path);
        this.fill();
    }

    private void fill() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String buffer = "";
        String line;
        while ((line = br.readLine()) != null) {
            buffer += line+'\n';
        }
        buffer = buffer.substring(0, buffer.length()-1);
        characters = buffer.toCharArray();
        br.close();
    }

    public char[] getCharacters() {
        return characters;
    }
}
