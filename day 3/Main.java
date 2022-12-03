import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
public class Main{
    public String input;

    public Main(String file) throws Exception{
        this.input = file;
    }

    public void part1() throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(this.input));
        String line;
        String bp1;
        String bp2;
        int sum = 0;
        while(reader.ready()){
            line = reader.readLine();
            bp1 = line.substring(0, line.length()/2);
            bp2 = line.substring(line.length()/2);
            char[] buf = new char[bp1.length()];
            bp1.getChars(0, bp1.length(), buf, 0);
            for (int i = 0; i < bp1.length(); i++) {
                if(bp2.contains(bp1.subSequence(i, i+1))){
                    if(buf[i] > 96){ // Use ascii value to check for lower or upper case
                        sum += buf[i]-96;
                    }else{
                        sum += buf[i]-65+27;
                    }
                    break;
                    
                }
            }
        }
        System.out.print("Part1: " + sum);
        reader.close();
    }

    public void part2() throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(this.input));
        String bp1;
        String bp2;
        String bp3;
        int sum = 0;
        while(reader.ready()){
            bp1 = reader.readLine();
            bp2 = reader.readLine();
            bp3 = reader.readLine();
            for(int i = 0; i < bp1.length(); i++){
                char a = bp1.charAt(i);
                if(bp2.contains(bp1.substring(i, i+1)) && bp3.contains(bp1.substring(i, i+1))){
                    if(bp1.charAt(i) > 96){
                        sum += bp1.charAt(i)-96;
                    }else{
                        sum += bp1.charAt(i)-65+27;
                    }
                    break;
                }
            }
        }
        System.out.println(" part2: " + sum);
        reader.close();
    }
    public static void main(String[] args) throws Exception{
        Main a = new Main("input.in");
        a.part1();
        a.part2();
    }
}