import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;

public class Main{
    public Main()throws Exception{
        String file = "input.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        Monkey monkies[] = new Monkey[8];
        int counter[] = new int[8];
        while(reader.ready()){
            line = reader.readLine();
            if(line.matches("Monkey\\s\\d:")){
                int no = Integer.parseInt(line.substring(7, 8));
                // Read the next 5 lines to create all monkies
                String items[] = reader.readLine().replace(",", "").split(" ");
                // items = reader.readLine().split(" ");
                String a[] = reader.readLine().split(" ");
                String[] ops = a;
                // items = reader.readLine().split(" ");
                Long dividor = Long.parseLong(reader.readLine().split(" ")[5]);
                // items = reader.readLine().split(" ");
                int t = Integer.parseInt(reader.readLine().split(" ")[9]);
                // items = reader.readLine().split(" ");
                int f = Integer.parseInt(reader.readLine().split(" ")[9]);
                // items = reader.readLine().split(" ");
                monkies[no] = new Monkey(t, f, ops, dividor);
                for(int i = 0; i < items.length ; i++){
                    if(items[i].matches("\\d+")){
                        monkies[no].addItem(Long.parseLong(items[i]));
                    }
                }
            }
        }
        /* Run 20 round and count how many times each monkey counts an item */

        int bound = monkies[4] == null ? 4 : 8;

        for(int i = 0; i < 10_000; i++){
            if(i==1 || i ==20 || (i % 1000) == 0){
                System.out.println("After round " + i);
                for (int j = 0; j < bound; j++) {
                    System.out.println("Moneky" + j + " " + counter[j] );
                }
            }
            for (int j = 0; j < bound; j++) {
                // System.out.println("j= " + j);
                int inspections = 0;
                for (Long item : monkies[j].items) {
                    if(item == null) continue;
                    Long modified = monkies[j].operation(item);
                    if(monkies[j].test(modified)){
                        monkies[monkies[j].t].addItem(modified);
                    }else{
                        monkies[monkies[j].f].addItem(modified);
                    }
                    counter[j]++;
                    inspections++;
                }
                for(int k = 0; k < inspections; k++){
                    monkies[j].items.pop();
                }
                
            }
        }
        // for (int i : counter) {
        //     System.out.println(i);
        // }

        // find the two larges values
        int max1 = 0;
        int max2 = 0;
        for(int c : counter){
            if (c > max1){
                max2= max1;
                max1 = c;
            }else if(max1 > c && c > max2){
                max2 = c;
            }
        }
        BigInteger A = BigInteger.valueOf(max1);
        BigInteger B = BigInteger.valueOf(max2);
        BigInteger part1 = A.multiply(B);
        System.out.println("part1 = " + part1);
        reader.close();
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
    }
}