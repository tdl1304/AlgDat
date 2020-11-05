package algdat7.compressor;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman {
    private TextFileHandler textFileHandler;
    private String input;
    private String output;
    //store all chars in
    private ArrayList<Character> data;
    //index is equal to unicode value, and its data is equal to frequency
    private int[] freqTable;
    private Queue<Node> pq;
    private Node root;
    private String dict;
    private StringBuilder encodedMessage;
    private String[] dictTable;
    private final int size = 65534;
    private String splitter;

    public Huffman(String input, String output) throws Exception {
        splitter = "-7";
        this.output = output;
        this.input = input;
        textFileHandler = new TextFileHandler(this.input);
        data = textFileHandler.getCharacters();
        if (data.size() == 0 || data == null) throw new NullPointerException("File is empty");
        freqTable = new int[size];
        pq = new PriorityQueue<>();
        dict = "";
        dictTable = new String[size];
    }

    public void encode() throws IOException {
        encodedMessage = new StringBuilder();
        dict = "";
        createFreqTable();
        createHuffmanTree();
        createHMDict(root, dict);
        fillDictTable();
        writeToFile();
    }


    private void writeToFile() throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
        out.writeUTF(dict);

        for (char c : data
        ) {
            encodedMessage.append(dictTable[c]);
        }

        while(encodedMessage.length() > 31) {
            out.writeInt(Integer.parseInt("1"+encodedMessage.substring(0,30), 2));
            encodedMessage.delete(0,30);
        }
        out.writeInt(Integer.parseInt("1"+encodedMessage.toString(), 2)); //Write out last part that is excess
        out.close();
    }




    private void fillDictTable() {
        String fail = "";
        try {
            String[] temp = dict.split(splitter);
            for (String s : temp
            ) {
                if(s.equals("")) continue;
                dictTable[s.charAt(s.length() - 1)] = s.substring(0, s.length() - 1);
            }
        } catch (Exception e) {
            System.out.println(fail);
            System.out.println(e.getLocalizedMessage());
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
            char c = root.c;
            dict += s + root.c + splitter;
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
