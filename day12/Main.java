import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{
    private int rowLength;

    public void printMap(char[][] map){
        for (int row = 0; row < map.length; row++) {
            for (int c = 0; c < map[row].length; c++) {
                System.out.print(map[row][c]);
            }
            System.out.print("\n");
        }
    }

    /*
     * @return array which symbolises edges, element is node number of -1 if no edge
     */
    private int[] findEdges(int r, int c, char[][] map, int node){
        int[] edges = {-1,-1,-1,-1};
        // Check left right and down. Take care of edge cases
        if(r==0){
            int down = Math.abs(map[r+1][c] - map[r][c]);
            if(down == 1 || down == 0){
                edges[0] = node + map[r].length;
            }
            if(c == 0){// check right
                int right = Math.abs(map[r][c+1] - map[r][c]);
                if(right == 1 || right == 0){
                    edges[1] = node + 1;
                }
            }else if(c == map[r].length-1){ // and left
                int left = Math.abs(map[r][c-1] - map[r][c]);
                if(left == 1 || left == 0){
                    edges[1] = node - 1;
                }
            }else{ // check left and right
                int right = Math.abs(map[r][c+1] - map[r][c]);
                if(right == 1 || right == 0){
                    edges[1] = node + 1;
                }
                int left = Math.abs(map[r][c-1] - map[r][c]);
                if(left == 1 || left == 0){
                    edges[2] = node -1;
                }
            }
        }else if(r == map[r].length-1){ // Check left up and right
            int up = Math.abs(map[r-1][c] - map[r][c]);
            if(up == 1 || up == 0){
                edges[0] = node - map[r].length;
            }
            if(c == 0){// check right
                int right = Math.abs(map[r][c+1] - map[r][c]);
                if(right == 1 || right == 0){
                    edges[1] = node +1;
                }
            }else if(c == map[r].length-1){ // and left
                int left = Math.abs(map[r][c-1] - map[r][c]);
                if(left == 1 || left == 0){
                    edges[1] = node - 1;
                }
            }else{ // check left and right
                int right = Math.abs(map[r][c+1] - map[r][c]);
                if(right == 1 || right == 0){
                    edges[1] = node + 1;
                }
                int left = Math.abs(map[r][c-1] - map[r][c]);
                if(left == 1 || left == 0){
                    edges[2] = node - 1;
                }
            }
        }else{ 
            // Add up and down but validate öeft and right seperate
            int up = Math.abs(map[r-1][c] - map[r][c]);
            if(up == 1 || up == 0){
                edges[0] = node - map[r].length;
            }
            int down = Math.abs(map[r+1][c] - map[r][c]);
            if(down == 1 || down == 0){
                edges[1] = node + map[r].length;
            }
            // Check if left or right needs to be handled seperate
            if(c==0 || c == map[r].length-1){
                int x = c == 0 ? -1 : 1;
                switch(x){
                    case -1:
                    int right = Math.abs(map[r][c+1] - map[r][c]);
                    if(right == 1 || right == 0){
                        edges[3] = node+1;
                    }
                    break;
                    default:
                    int left = Math.abs(map[r][c-1] - map[r][c]);
                    if(left == 1 || left == 0){
                        edges[2] = node-1;
                    }
                }
            }else{
                // Check left, right, up and down
                int left = Math.abs(map[r][c-1] - map[r][c]);
                int right = Math.abs(map[r][c+1] - map[r][c]);
                /*
                * Added edges if hight difference is 1 or zero
                */
                if(left == 1 || left == 0){
                    edges[2] = node-1;
                }
                if(right == 1 || right == 0){
                    edges[3] = node+1;
                }
            }
        }
        return edges;
    }

    public Main()throws Exception{
        String file = "test.txt";
        FileInputStream fIn = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fIn));
        String line;
        int cols = reader.readLine().length();
        int rows = 1;
        while(reader.ready()){
            line = reader.readLine();
            rows++;
        }
        fIn.getChannel().position(0);
        reader = new BufferedReader(new InputStreamReader(fIn));
        char[][] map = new char[rows][cols];
        // extract the map inorder to construct graph
        int row = 0;
        int s = -1;
        int e = -1;
        while(reader.ready()){
            line = reader.readLine();
            for(int col = 0; col < line.length(); col++){
                map[row][col] = line.charAt(col);
                if(line.charAt(col) == 'S'){
                    s = line.length()*row + col;
                    map[row][col] = 'a';
                }
                if(line.charAt(col) == 'E'){

                    e = line.length()*row + col;
                    map[row][col] = 'z';

                }


            }
            row++;
        }
        printMap(map);
        /* Construct graph from map.
         * Use an adjecency list representation.
         * edge from u to v if the level difference is okay 
         * 
         */

        int nodes = rows * cols;
        ArrayList<Integer>[] adjL = new ArrayList[nodes];
        for(int i = 0; i < adjL.length; i++){
            adjL[i] = new ArrayList<>();
        }
        // Start adding edges, first node is top left on map, second node is map[0][1], 3:d node is map[0][2] ...
        int v = 0; // vertex number corresponds to adjL index;
        for(int r = 0; r < map.length; r++){
            for (int c = 0; c < map[r].length; c++) {
                int[] edges = findEdges(r,c,map,v);
                /* add all found edges both ways */
                for(int u: edges){
                    if(u != -1){ // edge exits
                        // System.out.println("r=" + r + " c= " +c);
                        adjL[v].add(u);
                        adjL[u].add(v);
                    }
                }
                v++;
            }
        }
        
        System.out.println("E=" + e +" S=" + s);
        
        reader.close();
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
    }
}