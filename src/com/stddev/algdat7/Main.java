package com.stddev.algdat7;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "graphs/diverse.txt";
        String output = "graphs/compressedFile.txt";
        Huffman hm = new Huffman(path, output);
        hm.encode();
        System.out.println(Integer.MAX_VALUE);
    }

    public static void test() throws IOException {
        TextFileHandler fl = new TextFileHandler("graphs/diverse.txt");
        char[] ch = fl.getCharacters();
        int max = 0;
        for (char c: ch
        ) {
            if(c>max) max = c;
        }
        System.out.println(max);
        System.out.println((char)max);
    }


}
