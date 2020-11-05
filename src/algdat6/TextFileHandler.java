package algdat6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class TextFileHandler {
    private File file;
    private ArrayList<int[]> intArray;

    /**
     * Constructor method
     *
     * @param path path to text file
     */
    public TextFileHandler(String path) throws IOException {
        file = new File(path);
        intArray = new ArrayList<>();
        this.fill();
    }

    public ArrayList<int[]> getIntArray() {
        return intArray;
    }

    /*
    public ArrayList<int[]> getIntArray() {
        ArrayList<int[]> temp = new ArrayList<>();
        String[] first = stringArray.get(0);
        String[] s;
        temp.add(new int[]{Integer.parseInt(first[0]), Integer.parseInt(first[1])});
        for (int i = 1; i < stringArray.size(); i++) {
            s = stringArray.get(i);
            temp.add(new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])});
        }
        return temp;
    }*/

    private void fill() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        int[] temp;
        String[] strings;
        String line;
        while ((line = br.readLine()) != null) {
            strings = line.trim().split("\\s+\\s+\\s|\\s+\\s|\\s|\t");
            int length = strings.length;
            temp = new int[length];
            for (int i = 0; i < length; i++) {
                temp[i] = Integer.parseInt(strings[i]);
            }
            intArray.add(temp);
        }
        br.close();
    }
}

