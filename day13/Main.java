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

        }



    }

    public boolean validate(String left, String right){
    
        return false;
    }

    public static void main(String[] args) throws Exception{
        
    }
}