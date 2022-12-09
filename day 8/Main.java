import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public Main() throws Exception{
        String file = "test.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        

        int rows = 0;
        String line;
        int cols=0;
        while ( reader.ready()){
            line = reader.readLine();
            cols = line.length();
            rows++;
        }
        reader.close();
        BufferedReader fillReader = new BufferedReader(new FileReader(file));
        int[][] trees = new int[rows][cols];
        int r = 0;
        while(fillReader.ready()){
            line = fillReader.readLine();
            for (int c = 0; c < trees.length; c++) {
                trees[r][c] = line.charAt(c) - 48; // compensate for ascii
            }
        }
        

    }
    public static void main(String[] args) throws Exception {
        Main m = new Main();
    }
}
