package algdat7.compressor;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = "graphs/textcompressed.txt";
        String output = "graphs/compressedFile.txt";
        Huffman hm = new Huffman(input, output);
        hm.encode();

        File fileIn = new File(input);
        File fileOut = new File(output);
        System.out.println("Compressed file saved to: " + fileOut.getAbsolutePath());
        System.out.println("Before compression: " + (double)fileIn.length()/1000 + "kB | After compression: " + (double)fileOut.length()/1000 +"kB");
    }
}
