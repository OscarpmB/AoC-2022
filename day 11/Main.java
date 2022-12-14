import java.io.BufferedReader;
import java.io.FileReader;

public class Main{
    public Main()throws Exception{
        String file = "test.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        Monkey monkies[] = new Monkey[7];
        int counter[] = new int[7];
        while(reader.ready()){
            line = reader.readLine();
            if(line.matches("Monkey\\s\\d:")){
                int no = Integer.parseInt(line.substring(7, 8));
                // Read the next 5 lines to create all monkies
                String items[] = reader.readLine().replace(",", "").split(" ");
                int op = Integer.parseInt(reader.readLine().split(" ")[7]);
                int dividor = Integer.parseInt(reader.readLine().split(" ")[5]);
                int t = Integer.parseInt(reader.readLine().split(" ")[9]);
                int f = Integer.parseInt(reader.readLine().split(" ")[9]);
                monkies[no] = new Monkey(t, f, op, dividor);
                for(int i = 0; i < items.length ; i++){
                    if(items[i].matches("\\d+")){
                        monkies[no].addItem(Integer.parseInt(items[i]));
                    }
                }
            }
        }

        /* Run 20 round and count how many times each monkey counts an item */
        for(int i = 0; i < 20; i++){
            for (int j = 7; j < monkies.length; j++) {
                Integer item = monkies[j].items.pollFirst();
                if(item == null) continue;
                if(monkies[j].test(monkies[j].operation(item))){
                    monkies[monkies[j].t-1].addItem(item);
                }else{
                    monkies[monkies[j].f-1].addItem(item);
                }
            }
        }

        reader.close();
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
    }
}