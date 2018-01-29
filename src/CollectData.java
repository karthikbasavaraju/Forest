import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class CollectData {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\kbasa\\IdeaProjects\\Decision Tree\\src\\Aldo.csv"));
        LinkedList<LinkedList<String>> mainList = new LinkedList<>();
        LinkedList<String> subList;
        while(scanner.hasNext()){
            StringTokenizer stemp = new StringTokenizer(scanner.next(),",");
            subList = new LinkedList<>();
            while(stemp.hasMoreTokens()){
                String temp = stemp.nextToken();
                subList.add(temp);
            }
            mainList.add(subList);
        }
        new Entropy(mainList,5);
        //System.out.println(mainList);
    }
}