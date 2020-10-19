package com.stddev.algdat7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Huffman {
    byte[] x;
    FileInputStream y;

    public Huffman(String path) throws IOException {
        y = new FileInputStream(path);
        x = y.readAllBytes();
    }

    public FileInputStream getY() {
        return y;
    }

    public byte[] getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Huffman{" +
                "x=" + Arrays.toString(x) +
                '}';
    }
}
