import java.util.ArrayList;
import java.util.LinkedList;

public class Monkey {
    private int divisor;
    public LinkedList<Integer> items;
    public int t;
    public int f;
    private int operator;


    public Monkey(int tr, int fa, int op, int d) {
        this.divisor = d;
        this.items = new LinkedList<>();
        this.f = fa;
        this.t = tr;
        this.operator = op;
    }

    public int operation(int i){
        return this.operator * i;
    }

    public void addItem(int i){
        this.items.add(i);
    }

    public boolean test(int a){
        return (a % this.divisor) == 0 ? true : false;
    }
}

