import java.io.BufferedReader;
import java.io.FileReader;

public class Main{

    public void part1() throws Exception{
        String file = "input.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int c = 0;
        int pair= 1;
        int sum = 0;
        while(reader.ready()){
            String left = reader.readLine();
            String right = reader.readLine();

            if(validate(left, right)){
                c++;
                sum += pair;
            }
            pair++;
            reader.readLine();

        }
        System.out.println(sum);
    }

    public Main() throws Exception{
        part1();
    }
    /*
     * @left is string representation of list [e,e,[...],...]
     */
    public int noOfElements(String list){
        int elements = 0;
        String elem = getElem(list);
        int opened = 0;
        while (elem != null){
            elements++;
            list = updateList(list, elem);
            elem = getElem(list);
        }
        // for (int i = 0; i < list.length(); i++) {
        //     if(opened == 0 && i != 0){
        //         break;
        //     }
        //     if(list.charAt(i) == '['){
        //         opened++;
        //     }else if(list.charAt(i) == ']'){
        //         opened--;
        //     }else if(list.charAt(i) == ','){
        //         continue;
        //     }else if(opened == 1 && list.charAt(i) == ','){
        //         elements++;
        //     }
            
        // }
        return elements;
    }

    public String getElem(String list){
        String elem = (list.length() > 2) ? list.substring(1, list.length()-1) : null;
        int openedBrckets = 0;
        if(Character.isDigit(list.charAt(1))){
            elem = list.substring(1, 2);
        }else{ // elem is list
            for(int i = 0; i < list.length(); i ++){
                if(list.charAt(i) == '['){
                    openedBrckets++;
                }else if(list.charAt(i) == ']'){
                    openedBrckets--;
                }else if(list.charAt(i) == ',' && openedBrckets == 1){
                    elem = list.substring(1, i);
                    break;
                }
            }
        }
        return elem;
    }

    public boolean isList(String elem){
        return (elem.charAt(0) == '[' && elem.charAt(elem.length()-1) == ']') ? true : false;
    }

    /*
     * Removes first element of list and returns remainging list
     */
    public String updateList(String list, String elem){
        if(elem.matches("\\[.*\\]")){
            StringBuilder regex = new StringBuilder();
            for(int i = 0; i < elem.length(); i++){
                if(elem.charAt(i) == '[' || elem.charAt(i) == ']'){
                    regex.append("\\");
                }
                regex.append(elem.charAt(i));
            }
            elem = regex.toString();
        }
        String updated = list.replaceFirst(elem, "");
        if(updated.charAt(1) == ','){
            return "[" + updated.substring(2);
        }else{
            return updated;
        }
        // int start = 1;
        // int end = -1;

        // for (int i = 0; i < list.length(); i++) {
        //     if(list.charAt(i) == ','){
        //         end = i;
        //         break;
        //     }
        // }
        // if(end > -1){
        //     return "[" + list.substring(end+1);
        // }else{
        //     return "[]";
        // }
    }

    /*
     * Revices list that can contain sublists
     */
    public boolean validateList(String left, String right){

        if(left.equals("[]") || right.equals("[]")){
            return true;
        }
        
        String currentLeft = getElem(left);
        String currentRight = getElem(right);
        String newLeft = null;
        String newRight = null;
        boolean twoDigits = (currentLeft.matches("\\d+") && currentRight.matches("\\d+")) ? true : false;
        boolean twoLists = (isList(currentLeft) && isList(currentRight)) ? true: false;

        if(twoDigits){
            if(!validateDigit(currentLeft, currentRight)){ // Confirm left is smaller than right
                return false;
            }
        }else if(twoLists){
            if(!validate(currentLeft, currentRight)){
                return false;
            }
        }else if(isList(currentLeft) && !isList(currentRight)){
            String tempRight = "[" + currentRight + "]";
            if(!validateList(currentLeft, tempRight)){
                return false;
            }
        }else if(!isList(currentLeft) && isList(currentRight)){
            String tempLeft = "[" + currentLeft + "]";
            if(!validateList(tempLeft, currentRight)){
                return false;
            }
        }
        newLeft = updateList(left, currentLeft);
        newRight = updateList(right, currentRight);

        
        return validateList(newLeft, newRight);
    }

    public boolean validateDigit(String left, String right){
        boolean r = false;
        if(Integer.parseInt(left) <= Integer.parseInt(right)){
            r = true;
        }
        return r;
    }
    
    public boolean validate(String left, String right){
        int elemsLeft = noOfElements(left);
        int elemsRight = noOfElements(right);
        if(elemsLeft > elemsRight){
            return false;
        }
        return validateList(left, right);
    }
    public static void main(String[] args) throws Exception{
        Main m = new Main();
    }
}