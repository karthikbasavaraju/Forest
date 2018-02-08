import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Miscellaneous {

/*
Extracts data from csv file and stores it in LinkedList of Hashmap
 */
    static public LinkedList<HashMap<String,String>> getData(String filename) throws FileNotFoundException {

        // Assuming the csv files doesnt have missing data, no space in any values and the result to predict is at the last column
        Scanner scanner = new Scanner(new File(System.getProperty("user.dir")+"\\src\\DataSet\\"+filename));
        LinkedList<HashMap<String, String>> mainList = new LinkedList<>();
        LinkedHashMap<String, String> subList;
        LinkedList<String> attribute = new LinkedList<>();
        StringTokenizer stemp1 = new StringTokenizer(scanner.next(), ",");

        while (stemp1.hasMoreTokens()) {
            attribute.add(stemp1.nextToken());

        }
        System.out.println(attribute);
        while (scanner.hasNext()) {
            StringTokenizer stemp = new StringTokenizer(scanner.next(), ",");
            subList = new LinkedHashMap<>();
            int i = 0;
            while (stemp.hasMoreTokens()) {
                String temp = stemp.nextToken();
                subList.put(attribute.get(i), temp);
                i++;
            }
            mainList.add(subList);
        }
        System.out.println("main = "+mainList);

        return  mainList;
    }


    static void display(String position){
        //System.out.println(position);

    }

    /*
        Simple visualization of Decision tree
    */
    static void displayNodes(MyTree tree, String space,String padding){
        System.out.print(padding);
        System.out.println("edge = "+tree.getEdge());
        System.out.println(space+"node = "+tree.getAttribute());
        System.out.println(space+"parent = "+tree.getParent());
        LinkedList childTreeList = tree.getChildren();
        padding = padding + "--------";
        space = space + "\t\t";
        ListIterator childTreeListIterator = childTreeList.listIterator();
        while(childTreeListIterator.hasNext()){
            MyTree childTree = (MyTree)childTreeListIterator.next();
            displayNodes(childTree, space, padding);
        }
    }

    /*
    Converts List of HashMap to List of List
     */
    static public LinkedList<LinkedList> mapToList(LinkedList<HashMap<String,String>> list){
        display("start of maptolist");
        LinkedList<LinkedList> listList = new LinkedList<>();
        ListIterator ll = list.listIterator();
        HashMap<String,String> header = (HashMap)ll.next();
        listList.add(new LinkedList(header.keySet()));
        ll = list.listIterator();

        while(ll.hasNext()){
            HashMap<String,String> map = (HashMap)ll.next();
            LinkedList innerList = new LinkedList();
            for(String values : map.keySet()){
                innerList.add(map.get(values));
            }
            listList.add(innerList);
        }
        display("end of maptolist");
        return listList;
    }

}
