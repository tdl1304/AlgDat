package com.stddev.algdat7.compressor;

import java.io.*;
import java.util.ArrayList;

public class TextFileHandler {
    private String path;
    private ArrayList<Character> characters;
    private char offset = 'a';

    /**
     * Constructor method
     *
     * @param path path to text file
     */
    public TextFileHandler(String path) throws IOException {
        this.path = path;
        characters = new ArrayList<>();
        this.getRawData(path);

    }

    private void getRawData(String compressedFile) {
        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(compressedFile)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    short offset = dataInputStream.readShort();
                    byte stringLen = dataInputStream.readByte();
                    String nextChar = dataInputStream.readUTF();
                    characters.add((char)offset);
                    characters.add((char)stringLen);
                    characters.add(nextChar.charAt(0));
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }
}
