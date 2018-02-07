import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class CollectData {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("C:\\Users\\kbasa\\IdeaProjects\\Decision Tree\\src\\irish.csv"));
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

        SplitSets s = new SplitSets(mainList,30);

        LinkedList<HashMap<String,String>> testingSet= s.getTestSet();
        LinkedList<HashMap<String,String>> trainingSet= s.getTrainSet();
        //System.out.println(trainingSet);
        //System.out.println(testingSet);
        LinkedList RandomSet = new RandomForestSet(trainingSet,6).RandomSet();

        LinkedList<MyTree> RandomForestTree=new LinkedList<>();
        ListIterator RandomSetIterator = RandomSet.listIterator();
        int i=1;
        while(RandomSetIterator.hasNext()) {
            testingSet= s.getTestSet();
            LinkedList decisionTree = (LinkedList)RandomSetIterator.next();
            MyTree tree = new BuildTree().buildSubTree(decisionTree, null);
            RandomForestTree.add(tree);
            System.out.println("Tree-"+i++);
           // new Disp().displayNodes(tree, "", "");
            //System.out.println(testingSet);
            //Predict n = new Predict(tree, testingSet);
            //n.printDetailedResult();
            //n.predictionAccuracy();
        }

        new Predict(RandomForestTree,testingSet);



        System.out.println("complete");
        MyTree tree = new BuildTree().buildSubTree(trainingSet, null);
        RandomForestTree = new LinkedList<>();
        RandomForestTree.add(tree);
        //new Disp().displayNodes(tree, "", "");
        new Predict(RandomForestTree, testingSet);
    }
}