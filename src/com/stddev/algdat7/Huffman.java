package com.stddev.algdat7;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman {
    private TextFileHandler textFileHandler;
    private String input;
    private String output;
    //store all chars in
    private char[] data;
    //index is equal to unicode value, and its data is equal to frequency
    private int[] freqTable;
    private Queue<Node> pq;
    private Node root;
    private String dict;
    private String encodedMessage;
    private String[] dictTable;
    private final int size = 65534;

    public Huffman(String input, String output) throws Exception {
        this.output = output;
        this.input = input;
        textFileHandler = new TextFileHandler(this.input);
        data = textFileHandler.getCharacters();
        if (data.length == 0 || data == null) throw new NullPointerException("File is empty");
        freqTable = new int[size];
        pq = new PriorityQueue<>();
        dict = "";
        encodedMessage = "";
        dictTable = new String[size];
        dictTable[0] = "s";
    }

    public String getDict() {
        return dict;
    }

    private void writeToFile() throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
        out.writeUTF(dict);
        String sTemp = null;
        String subTemp = null;
        for (char c : data
        ) {
            encodedMessage += dictTable[c];
        }
        sTemp = encodedMessage;
        while(sTemp.length() > 31) {
            subTemp = "1"+sTemp.substring(0,31);
            sTemp = sTemp.substring(32);
            out.writeInt(stringToInt(subTemp));
        }
        out.writeInt(stringToInt(subTemp)); //Write out last part that is excess
        out.close();
    }


    public int stringToInt(String s) {
        char[] chars = s.toCharArray();
        int ret = 1;
        for (int i = 0; i < chars.length; i++) {
            ret <<= 1;
            if(chars[i]=='1') {
                ret +=1;
            }
        }
        return ret;
    }

    public String getEncodedMessage() {
        return encodedMessage;
    }

    public void encode() throws IOException {
        encodedMessage = "";
        dict = "";
        createFreqTable();
        createHuffmanTree();
        createHMDict(root, dict);
        fillDictTable();
        writeToFile();
    }

    private void fillDictTable() {
        String[] temp = dict.split("\t");
        for (String s : temp
        ) {
            dictTable[s.charAt(s.length()-1)] = s.substring(0, s.length() - 1);
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
            dict += s + root.c + "\t";
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
