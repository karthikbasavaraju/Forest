import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RandomDecisionForest {

    public static void main(String[] args) throws FileNotFoundException {

        // Assuming the csv file doesn't have missing data, no space in any values and the result is at the last column
        LinkedList mainList = Miscellaneous.getData("car.csv");

        //Splitting data
        SplitSets s = new SplitSets();
        s.SplitToTrainingTestingSet(mainList,10);  //splitting data to training and testing sets
        LinkedList<HashMap<String,String>> testingSet= s.getTestSet();
        LinkedList<HashMap<String,String>> trainingSet= s.getTrainSet();

        s.SplitSetForRandomForest(trainingSet,10);        //splitting data for Random decisions trees
        LinkedList RandomSet = s.RandomSet();

        LinkedList<MyTree> RandomForestTrees=new LinkedList<>();       //To store all trees
        ListIterator RandomSetIterator = RandomSet.listIterator();
        int i=1;
        while(RandomSetIterator.hasNext()) {
            testingSet= s.getTestSet();
            LinkedList decisionTree = (LinkedList)RandomSetIterator.next();
            MyTree tree = new BuildTree().buildSubTree(decisionTree, null);
            RandomForestTrees.add(tree);
            /*System.out.println("\nTree-"+i++);                     //Visualization of Tree
            new Disp().displayNodes(tree, "", "");*/
        }
        new Predict(RandomForestTrees,testingSet);

        //Single Decision Tree
        MyTree tree = new BuildTree().buildSubTree(trainingSet, null);
        RandomForestTrees = new LinkedList<>();
        RandomForestTrees.add(tree);
        /*System.out.println("Simple Decision Tree");                           //visualization of tree
        new Disp().displayNodes(tree, "", ""); */
        new Predict(RandomForestTrees, testingSet);
    }
}
