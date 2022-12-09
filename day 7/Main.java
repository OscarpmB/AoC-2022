import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{

    public Main() throws Exception{
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        // Read root dir
        String line = reader.readLine();
        String[] args = line.split(" ");
        Dir root = new Dir(args[2], null);
        Dir current;
        current = root;
        while(reader.ready()){
            line = reader.readLine();
            args = line.split(" ");
            switch (args[1]){
                case "ls":
                    // read content of dir
                    boolean keepReading = true;
                    while(keepReading && reader.ready()){
                        line = reader.readLine();
                        args = line.split(" ");
                        if(!args[0].equals("$")){
                            /* Add content of dir */
                            if(args[0].equals("dir")){
                                current.add(new Dir(args[1], current));
                            }else if(args[0].matches("[0-9]+.+")){
                                current.add(new File(args[1], args[0]));
                            }
                        }else{
                            keepReading = false;
                        }
                    }
                    // break;
                case "cd":
                    if(reader.ready()){
                        if(args[2].equals("..")){
                            current = current.parent;
                        }else{
                            current = current.getSubDir(args[2]);
                        }
                    }
                    break;
            }
        }
        current = root;
        int part1=0;
        ArrayList<Integer> sizes = new ArrayList<>();
        sizes.add(0);
        sizes = current.sizeSubDirs(current, sizes);
        for(int i : sizes){
            if( i <= 100_000){
                part1 += i;
            }
        }

        System.out.println(part1);
        reader.close();
    }

    private int getSizes(Dir d){
        ArrayList<Integer> sizes = new ArrayList<>();
        
        return 0;
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
    }
}