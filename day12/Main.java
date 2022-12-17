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

    public void printAdjL(ArrayList<Integer>[] adjL){
        for(int i = 0; i < adjL.length; i++){
            System.out.print(i+ ": ");
            ArrayList<Integer> edged = adjL[i];
            for (int j = 0; j < edged.size(); j++) {
                System.out.print(edged.get(j) + ", ");
            }
            System.out.println();
        }
    }
    private int[] findEdges(int r, int c, char[][] map, int node){
        int[] edges = {-1,-1,-1,-1};
        boolean up = r==0 ? false : true;
        boolean down = r == map.length-1 ? false : true;
        boolean left = c == 0 ? false : true;
        boolean right = c == map[r].length -1 ? false : true;
        if(up){
            int du = Math.abs(map[r-1][c] - map[r][c]);
            if(du == 1 || du == 0){
                edges[0] = node - map[r].length;
            }
        }
        if(down){
            int dd = Math.abs(map[r+1][c] - map[r][c]);
            if(dd == 1 || dd == 0){
                edges[1] = node + map[r].length;
            }
        }
        if(left){
            int dl = Math.abs(map[r][c-1] - map[r][c]);
            if(dl == 1 || dl == 0){
                edges[2] = node -1;
            }
        }
        if(right){
            int dr = Math.abs(map[r][c+1]-map[r][c]);
            if(dr == 1 || dr == 0){
                edges[3] = node + 1;
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
                        if(!adjL[v].contains(u)){
                            adjL[v].add(u);
                        }
                        if(!adjL[u].contains(v)){
                            adjL[u].add(v);
                        }
                    }
                }
                v++;
            }
        }

        printAdjL(adjL);

        System.out.println("E=" + e +" S=" + s);
        
        reader.close();
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
    }
}