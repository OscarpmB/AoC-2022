import java.util.LinkedList;

public class Monkey {

    private Long divisor;
    public LinkedList<Long> items;
    public int t;
    public int f;
    private String operator;
    private String operand1;
    private String operand2;


    public Monkey(int tr, int fa, String[] op, Long d) {
        this.divisor = d;
        this.items = new LinkedList<>();
        this.f = fa;
        this.t = tr;
        this.operator = op[op.length-2];
        this.operand1 = op[op.length-3];
        this.operand2 = op[op.length-1];
    }

    public Long operation(Long i){

        Long old;
        if(this.operand2.equals("old")){
            old = i;
        }else{
            old = Long.parseLong(this.operand2);
        }
        switch(this.operator){
            case "+":
                return i + (old);
            case "*":
                return i*old;
        }
        return 0L;
    }

    public void addItem(Long i){
        this.items.add(i);
    }

    public boolean test(Long a){
        return (a % (Long) this.divisor) == 0 ? true : false;
    }
}

