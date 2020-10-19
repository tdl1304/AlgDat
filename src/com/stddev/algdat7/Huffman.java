package com.stddev.algdat7;

import java.io.*;
import java.util.BitSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman {
    private TextFileHandler textFileHandler;
    String path;
    //store all chars in
    private char[] data;
    //index is equal to unicode value, and its data is equal to frequency
    private int[] freqTable;
    private Queue<Node> pq;
    private Node root;
    private String dict;
    private String encodedMessage;
    private String[] dictTable;
    private final int size = 65533;

    public Huffman(String path) throws Exception {
        this.path = path;
        textFileHandler = new TextFileHandler(path);
        data = textFileHandler.getCharacters();
        if (data.length == 0 || data == null) throw new NullPointerException("File is empty");
        freqTable = new int[size];
        pq = new PriorityQueue<>();
        dict = "";
        encodedMessage = "";
        dictTable = new String[size];
    }

    public String getDict() {
        return dict;
    }

    private void writeToFile() throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream("graphs/compressedFile.txt"));
        out.writeUTF(dict);
        String temp;
        for (char c : data
        ) {
            temp = dictTable[c];
            while (true) {
                if (temp.length() > 10) {
                    out.writeByte(Integer.parseInt(temp.substring(0, 9))/8);
                    temp = temp.substring(9);
                } else {
                    out.writeByte(Integer.parseInt(temp)/8);
                    break;
                }
            }

        }

        out.close();
    }

    public String getEncodedMessage() {
        return encodedMessage;
    }

    public void encode() throws IOException {
        createFreqTable();
        createHuffmanTree();
        createHMDict(root, dict);
        fillDictTable();
        writeToFile();
    }

    private void fillDictTable() {
        String[] temp = dict.split("\n");
        for (String s : temp
        ) {
            dictTable[s.charAt(s.length() - 1)] = s.substring(0, s.length() - 1);
        }
    }

    private void createFreqTable() {
        for (int c : data
        ) {
            freqTable[c]++;
        }
    }

    //assigns a dictionary to string: dict
    private void createHMDict(Node root, String s) {
        if (root.left == null && root.right == null) {
            dict += s + root.c + "\n";
            return;
        }
        createHMDict(root.left, s + "0");
        createHMDict(root.right, s + "1");
    }

    //set root to huffman tree root node
    private void createHuffmanTree() {
        //Fill priority queue
        for (int i = 0; i < freqTable.length; i++) {
            if (freqTable[i] != 0) pq.add(new Node(freqTable[i], (char) i));
        }
        //Huffman tree creation
        while (pq.size() > 1) {
            Node x = pq.poll();
            Node y = pq.poll();
            Node newNode = new Node(x.num + y.num);
            newNode.left = x;
            newNode.right = y;
            root = newNode;
            pq.add(newNode);
        }
    }
}
