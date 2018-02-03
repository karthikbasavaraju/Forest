import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class CollectData {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("C:\\Users\\kbasa\\IdeaProjects\\Decision Tree\\src\\Aldo.csv"));
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
        MyTree tree = new BuildTree().buildSubTree(mainList,null);
        new Disp().displayNodes(tree,"","");
        
    }
}