import java.util.ArrayList;

public class Dir {
    public String name;
    public ArrayList<File> files;
    public ArrayList<Dir> subDirs;
    public Dir parent;

    public Dir(String dirName, Dir dirParent){
        this.name = dirName;
        this.files = new ArrayList<>();
        this.subDirs = new ArrayList<>();
        this.parent = dirParent;
    }
    public void add(File f){
        this.files.add(f);
    }
    public void add(Dir d){
        this.subDirs.add(d);
    }
}
