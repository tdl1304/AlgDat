package com.stddev.algdat7.decompressor;


import java.io.*;
import java.util.ArrayList;

public class Huffman {
    private String input = "";
    private String output = "";
    private Node root;
    private String[] dict;
    private ArrayList<Integer> encodedMessage;
    private String decompressedData = "";
    private String binaryString = "";
    private String splitter;

    public Huffman(String input, String output) throws IOException {
        splitter = "-7";
        this.input = input;
        this.output = output;
        encodedMessage = new ArrayList<>();

    }

    //Public wrapper for decompressing
    public void decompress() throws IOException {
        resetVariables();
        extractData();
        createHuffmanTree();
        formatBinaryString();
        decompressToString();
        writeToFile(output);
    }

    private void resetVariables() {
        root = null;
        dict = null;
        encodedMessage.clear();
        decompressedData = "";
        binaryString = "";
    }

    private void writeToFile(String output) throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
        out.writeChars(decompressedData);
        out.close();
    }

    private void extractData() throws IOException {
        DataInputStream in = null;
        int data;
        try {
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(input)));
            dict = in.readUTF().split(splitter);
            while (true) {
                data = in.readInt();
                encodedMessage.add(data);
            }
        } catch (Exception e) {
        } finally {
            in.close();
        }
    }

    //fjerner  1'er som ble lagt til foran strengen i komprimmering
    private void formatBinaryString() {
        for (int i : encodedMessage
        ) {
            binaryString += Integer.toBinaryString(i).substring(1);
        }
    }

    //returns false if there are no more reads from binaryString, else return excess binaryString and true
    private boolean findAllChars(Node root, String binaryString) {
        if (this.binaryString.length() == 0) {
            return false;
        }
        if (root.left == null && root.right == null) {
            decompressedData +=root.c;
            this.binaryString = binaryString;
            return true;
        }
        if (binaryString.charAt(0) == '0') {
            findAllChars(root.left, binaryString.substring(1));
        } else {
            findAllChars(root.right, binaryString.substring(1));
        }
        return true;
    }


    //wrapper for findAllChars
    private void decompressToString() {
        while(findAllChars(root, binaryString)) {
        }
    }

    private void createHuffmanTree() {
        for (String s : dict
        ) {
            root = addRecursive(root, s);
        }
    }

    //Recurvesively adds characters to a binary tree with the branch path equal to its codename
    private Node addRecursive(Node current, String dictEntry) {
        if (current == null && dictEntry.length() > 1) {
            current = new Node();
        } else if (current == null) {
            current = new Node(dictEntry.charAt(0));
            return current;
        }

        if (dictEntry.charAt(0) == '0') {
            current.left = addRecursive(current.left, dictEntry.substring(1));
        } else if (dictEntry.charAt(0) == '1') {
            current.right = addRecursive(current.right, dictEntry.substring(1));
        }

        return current;
    }


}
