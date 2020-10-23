package com.stddev.algdat7.decompressor;

//TO FIX BINARY STRING
import java.io.*;
import java.util.ArrayList;

public class Huffman {
    String input = "";
    Node root;
    String[] dict;
    ArrayList<Integer> encodedMessage;
    String decompressedData = "";
    String binaryString = "";

    public Huffman(String input) throws IOException {
        this.input = input;
        encodedMessage = new ArrayList<>();
        extractData();
        createHuffmanTree();
        formatBinaryString();
        decompress();
        System.out.println(decompressedData);
    }

    private void extractData() throws IOException {
        DataInputStream in = null;
        int data;
        try {
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(input)));
            dict = in.readUTF().split("\t");
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
            decompressedData += "" + root.c;
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
    private void decompress() {
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
