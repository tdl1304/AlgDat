package algdat8;

import java.io.*;

public class TextSplitter1000 {
    static String mappe = "graphs/oblig8/";
    static String file1 = "graphs/oblig8/djikstra.txt";
    static String file2 = "graphs/oblig8/astar.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        BufferedReader br2 = new BufferedReader(new FileReader(file2));
        BufferedWriter bw = null;
        int j = 0;
        int i = 1;
        String data = "";
        String line;
        //djik
        while((line=br1.readLine())!= null) {
            data += line+"\n";
            if (i % 999 == 0) {
                bw = new BufferedWriter(new FileWriter(mappe+"djik"+String.valueOf(j)+".csv"));
                bw.write(data);
                j++;
                bw.close();
                data = "";
            }
            i++;
        }
        bw = new BufferedWriter(new FileWriter(mappe+"djik"+String.valueOf(j)+".csv"));
        bw.write(data);
        j++;
        bw.close();
        data = "";

        j = 0;
        i = 1;
        //a star
        while((line=br2.readLine())!= null) {
            data+=line+"\n";
            if (i % 999 == 0) {
                bw = new BufferedWriter(new FileWriter(mappe+"astar"+String.valueOf(j)+".csv"));
                bw.write(data);
                j++;
                bw.close();
                data = "";
            }
            i++;
        }
        bw = new BufferedWriter(new FileWriter(mappe+"astar"+String.valueOf(j)+".csv"));
        bw.write(data);
        j++;
        bw.close();
        data = "";
        br1.close();
        br2.close();


    }

}
