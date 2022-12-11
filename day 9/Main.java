import java.io.BufferedReader;
import java.io.FileReader;
import java.text.BreakIterator;


public class Main{
    private void updateTail(End head, End tail){
        int dx = head.x - tail.x;
        int dy = head.y - tail.y;
        if((dx > 1) || (dx < -1)){
            tail.x = dx > 0 ? tail.x + 1: tail.x -1;
        }
        if((dy > 1) || (dy < -1)){
            tail.x = dy > 0 ? tail.y+ 1: tail.y-1;
        }
    }

    private void updatePos(End head, String move){
        switch(move){
            case "U":
                head.y++;
                break;
            case "D":
                head.y--;
                break;
            case "R":
                head.x++;
                break;
            case "L":
                head.x--;
                break;
        }
    }
    public Main()throws Exception{
        String file = "test.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        End head = new End();
        End tail = new End();
        while(reader.ready()){
            String[] move = reader.readLine().split(" ");
            int n = Integer.parseInt(move[1]);
            for(int i = 0 ; i < n ; i++){
                updatePos(head, move[0]);
                updateTail(head, tail);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
    }
}