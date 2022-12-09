import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{

    public Main() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Read root dir
        String line = reader.readLine();
        String[] args = line.split(" ");
        Dir root = new Dir(args[1], null);
        while(reader.ready()){
            line = reader.readLine();
            args = line.split(" ");
            switch (args[1]){
                case "ls":
                    // read content of dir
                    boolean keepReading = true;
                    while(keepReading){
                        line = reader.readLine();
                        args = line.split(" ");
                        if(!args[0].equals("$")){
                            /* Add content of dir */
                            
                        }else{
                            keepReading = false;
                        }
                    }
            }
        }
        reader.close();
    }
    public static void main(String[] args) throws Exception{
        Main m = new Main();
    }
}