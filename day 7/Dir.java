import java.util.ArrayList;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

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

    public Dir getSubDir(String dir){
        for (Dir d : this.subDirs) {
            if(d.name.equals(dir)){
                return d;
            }
        }
        return null;
    }
    public int getDirSize(){
        int size = 0;
        for(File f : this.files){
            size += f.size;
        }
        for(Dir d : this.subDirs){
            size += d.getDirSize();
        }
        return size;
    }

    public ArrayList<Integer> sizeSubDirs(Dir d, ArrayList<Integer> sizes){
        sizes.add(getDirSize());
        for (Dir subD : this.subDirs) {
            ArrayList<Integer> subsizes = new ArrayList<>();
            for(int i : subD.sizeSubDirs(subD, subsizes)){
                sizes.add(i);
            }
        }
        return sizes;
    }
}
