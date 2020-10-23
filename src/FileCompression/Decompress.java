package FileCompression;

public class Decompress {
    public static void main(String[] args) {
        String inPath, outPath;
        inPath = "C:\\Users\\pRIVAT\\IdeaProjects\\AlgDat\\graphs\\compressedFile.ttt";
        outPath = "C:\\Users\\pRIVAT\\IdeaProjects\\AlgDat\\graphs\\decompressedFile.txt";

        byte[] indata = FileScanner.loadFile(inPath), outData = LempelZ.decompress(Huffman.decode(indata));

        System.out.println("Decompressed from: " + indata.length + "B to " + outData.length + "B.");

        FileScanner.writeFile(outPath, outData);
    }
}
