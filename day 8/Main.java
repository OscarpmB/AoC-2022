import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public void print(int[][] m){
        for(int r = 0; r < m.length ; r++ ){
            for( int c = 0; c <m[r].length ; c++){
                System.out.print(m[r][c]);
            }
            System.out.println();
        }
    }
    public boolean isOuterTree(int c, int[][] trees){
        return (c == 0) || (c == trees[0].length-1) ? true : false;
    }

    public int findTreesDumb(int[][] trees){
        int visable = 0;
        return 0;
    }

    public int findTrees(int[][] trees){
        int visable = 0;
        int[] maxCol = Arrays.copyOf(trees[0], trees[0].length);
        int maxRow = 0;
        for(int r = 0; r < trees.length ; r++){
            if(r == 0 || r == trees.length-1){
                visable += trees.length;
            }else {
                for(int c = 0; c < trees[r].length; c++){
                    int t = trees[r][c];
                    if(isOuterTree(c, trees)){
                        visable += 1;
                        maxRow = t;
                    }else if(t > maxRow){ // visable from left
                        visable += 1;
                        maxCol[c] = t > maxCol[c] ? t : maxCol[c]; // check if tree is viable from top
                        maxRow = t;
                        
                    }else if(t > maxCol[c]){ // check if visable from top
                        visable += 1;
                        maxCol[c] = t;
                    }else{
                        // Check if visable from right
                        boolean right = true;
                        for( int i = c+1; i < trees[r].length; i++){
                            if(t <= trees[r][i]){
                                right = false;
                                break;
                            }
                        }
                        // Check visability from bottom
                        boolean bottom = true;
                        for(int i = r+1; i < trees.length; i++){
                            if(t <= trees[i][c]){
                                bottom = false;
                                break;
                            }
                        }
                        if(bottom || right){
                            visable += 1;
                        }
                    }
                }
            }
            
        }
        return visable;
    }

    public Main() throws Exception{
        String file = "input.txt";
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
            r++;
        }
        // print(trees);
        System.out.println(findTrees(trees));

    }
    public static void main(String[] args) throws Exception {
        Main m = new Main();
    }
}
