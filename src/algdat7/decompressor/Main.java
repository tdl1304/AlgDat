package algdat7.decompressor;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = "graphs/compressedFile.txt";
        String output = "graphs/decompressedFile.txt";
        Huffman hm = new Huffman(input, output);
        hm.decompress();

        File fileIn = new File(input);
        File fileOut = new File(output);
        System.out.println("Decompressed file saved to: " + fileOut.getAbsolutePath());
        System.out.println("Before decompression: " + (double)fileIn.length()/1000 + "kB | After decompression: " + (double)fileOut.length()/1000 +"kB");

    }
}
