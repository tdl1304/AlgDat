package com.stddev.algdat7.decompressor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = "graphs/compressedFile.txt";
        Huffman hm = new Huffman(input);

    }
}
