public class File {
    public int size;
    public String name;

    public File(String name, String size){
        this.name = name;
        this.size = Integer.parseInt(size);
    }
}
