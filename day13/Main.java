import java.io.BufferedReader;
import java.io.FileReader;

public class Main{



    public Main() throws Exception{
        String file = "test.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int c = 0;
        while(reader.ready()){
            String left = reader.readLine();
            String right = reader.readLine();

            if(validate(left, right)){
                c++;
            }
            reader.readLine();

        }



    }

    
    public boolean validate(String left, String right){
        if(left.isEmpty() || (left.equals("]") || right.equals("]"))){ // end of string reached with out fail. return ture
            return true;
        }
        if(left.length()>1 && right.length() < 2){ // Right ran out before left
            return false;
        }

        if(left.charAt(0)== ','){
            left = left.substring(1);
        }
        if(right.charAt(0)== ','){
            right = right.substring(1);
        }
        char lc = left.charAt(0);
        char rc = right.charAt(0);
        String newLeft = null;
        String newRight = null;

        // check if two integers
        if(Character.isDigit(lc) && Character.isDigit(rc)){
            if( lc > rc){ // validate that 
                return false;
            }else{
                newLeft = left.substring(2);
                newRight = right.substring(2);
                // return validate(newLeft, newRight);
            }
        }else if(rc == '[' && lc == '['){
            /* sublist found. find soub string that represents list and validatde */
            int rightStop = getSublists(right);
            int leftStop = getSublists(left);
            String tempLeft = left.substring(1, leftStop+1);
            String tempRight = right.substring(1, rightStop+1);
            if(!validate(tempLeft, tempRight)){
                return false;
            }
            newLeft = left.substring(leftStop+1);
            newRight = right.substring(rightStop+1);
        }else{ // left and right are of different sort
            // Find which is list and which is digit
            if(Character.isDigit(rc) && !Character.isDigit(lc)){
                int leftStop = getSublists(left);
                String tempLeft = left.substring(0, leftStop+1);
                String tempRight = "["+rc+"]";
                if(!validate(tempLeft, tempRight)){
                    return false;
                }
                newLeft = left.substring(leftStop+1);
                newRight = right.substring(1);
            }else if(!Character.isDigit(rc) && Character.isDigit(lc)){
                int rightStop = getSublists(right);
                String tempRight = right.substring(0, rightStop+1);
                String tempLeft = "["+lc+"]";
                if(!validate(tempLeft, tempRight)){
                    return false;
                }
                newLeft = left.substring(1);
                newRight = right.substring(rightStop+1);
            }

        }
        
        return validate(newLeft, newRight);
    }

    public int getSublists(String seq){
        int ret = -1;
        int opened = 0;
        for(int i = 0; i < seq.length(); i++){
            if(seq.charAt(i) == '['){
                opened++;
            }
            if(seq.charAt(i) == ']'){
                if(opened == 1){
                    ret = i;
                    break;
                }
                opened--;
            }
        }
        return ret;
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
    }
}