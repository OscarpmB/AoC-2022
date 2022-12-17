import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main{

    public char[][] map;
    public void printMap(){
        for (int row = 0; row < this.map.length; row++) {
            for (int c = 0; c < this.map[row].length; c++) {
                System.out.print(this.map[row][c]);
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
            int du = map[r-1][c] - map[r][c];
            if( du < 2){
                edges[0] = node - map[r].length;
            }
        }
        if(down){
            int dd = map[r+1][c] - map[r][c];
            if(dd < 2){
                edges[1] = node + map[r].length;
            }
        }
        if(left){
            int dl = map[r][c-1] - map[r][c];
            if(dl < 2){
                edges[2] = node -1;
            }
        }
        if(right){
            int dr = map[r][c+1]-map[r][c];
            if(dr <2){
                edges[3] = node + 1;
            }
        }
        return edges;
    }
    public void updateMap(int node){
        int c = node % this.map[0].length;
        int r = -1;
        int bound = 0;
        int low= 0;
        int high = this.map[0].length;
        while(bound < this.map.length){
            if(low <= node && node < high){
                r = bound;
                break;
            }
            low = high;
            high += this.map[0].length;
            bound++;
        }
        this.map[r][c] = '.';
    }

    public int BFS(ArrayList<Integer>[] adjL, int s, int e){
        // Array for distances
        int[] distances = new int[adjL.length];
        for(int i = 0; i < distances.length; i++){
            distances[i] = Integer.MIN_VALUE;
        }
        Queue<Integer> q = new LinkedList<>();
        distances[s] = 0;
        q.add(s);
        boolean eFound = false;
        // do BFS until e is found and note distance from s
        while(q.size() > 0 && !eFound){
            int u = q.poll();
            for(int v : adjL[u]){
                if(distances[v] == Integer.MIN_VALUE){
                    distances[v] = distances[u] + 1;
                    q.add(v);
                    updateMap(v);
                }
                if(v == e){
                    eFound = true;
                    break;
                }
            }
        }
        return distances[e];
    }
    

    public Main()throws Exception{
        String file = "input.txt";
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
        this.map = new char[rows][cols];
        // extract the map inorder to construct graph
        int row = 0;
        int s = -1;
        int e = -1;
        ArrayList<Integer> starts = new ArrayList<>();
        while(reader.ready()){
            line = reader.readLine();
            for(int col = 0; col < line.length(); col++){
                this.map[row][col] = line.charAt(col);
                if(line.charAt(col) == 'a'){
                    starts.add(line.length()*row + col);
                }
                if(line.charAt(col) == 'S'){
                    s = line.length()*row + col;
                    this.map[row][col] = 'a';
                }
                if(line.charAt(col) == 'E'){
                    e = line.length()*row + col;
                    this.map[row][col] = 'z';
                }
            }
            row++;
        }
        // printthis.Map(this.map);
        /* Construct graph from this.map.
         * Use an adjecency list representation.
         * edge from u to v if the level difference is okay 
         * 
         */

        int nodes = rows * cols;
        ArrayList<Integer>[] adjL = new ArrayList[nodes];
        for(int i = 0; i < adjL.length; i++){
            adjL[i] = new ArrayList<>();
        }
        // Start adding edges, first node is top left on this.map, second node is this.map[0][1], 3:d node is this.map[0][2] ...
        int v = 0; // vertex number corresponds to adjL index;
        for(int r = 0; r < this.map.length; r++){
            for (int c = 0; c < this.map[r].length; c++) {
                int[] edges = findEdges(r,c,this.map,v);
                /* add all found edges both ways */
                for(int u: edges){
                    if(u != -1){ // edge exits
                        // System.out.println("r=" + r + " c= " +c);
                        if(!adjL[v].contains(u)){
                            adjL[v].add(u);
                        }
                        // if(!adjL[u].contains(v)){
                        //     adjL[u].add(v);
                        // }
                    }
                }
                v++;
            }
        }

        // printAdjL(adjL);

        System.out.println("E=" + e +" S=" + s);

        
        int part1 = BFS(adjL, s, e);
        printMap();
        System.out.println(part1);

        // Part 2, find overall shortest path
        int part2 = Integer.MAX_VALUE;
        for(int start : starts){
            int tmp = BFS(adjL, start, e);
            if(tmp < part2 && tmp > 0){
                part2 = tmp;
            }
        }
        
        System.out.println("Part2= " + part2);
        reader.close();
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
    }
}