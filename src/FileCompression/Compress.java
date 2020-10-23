package FileCompression;

public class Compress {
    public static void main(String[] args) {
        String inPath, outPath;
        inPath = "C:\\Users\\pRIVAT\\IdeaProjects\\AlgDat\\graphs\\diverse.txt";
        outPath = "C:\\Users\\pRIVAT\\IdeaProjects\\AlgDat\\graphs\\compressedFile.ttt";
        byte[] indata = FileScanner.loadFile(inPath), outData = Huffman.encode(LempelZ.compress(indata));
        System.out.println("Compressed (" + Math.round(((double) outData.length/indata.length) * 100) + "%) from: " + indata.length + "B to " + outData.length + "B.");
        FileScanner.writeFile(outPath, outData);
    }
}
