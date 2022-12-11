import java.util.ArrayList;

public class End {
    public int x;
    public int y;
    private ArrayList<Integer> visited;
   
    
    public End(){
        this.x = 0;
        this.y = 0;
        this.visited = new ArrayList<>();
    }

    public void updatePos(int dx, int dy){
        this.x = this.x + dx;
        this.y = this.y + dy;
    }
}
