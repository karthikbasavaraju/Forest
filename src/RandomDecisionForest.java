import java.io.FileNotFoundException;
import java.util.*;

public class RandomDecisionForest {

    public static void main(String[] args) throws FileNotFoundException
    {

        // Assuming the csv file doesn't have missing data, no space in any values and the result is at the last column
        LinkedList mainList = Miscellaneous.getData("heart.csv");  // car.csv, heart.csv

        //Splitting data
        SplitSets s = new SplitSets();
        s.SplitToTrainingTestingSet(mainList,20);  //splitting data to training and testing sets
        LinkedList<HashMap<String,String>> testingSet= s.getTestSet();
        LinkedList<HashMap<String,String>> trainingSet= s.getTrainSet();
        s.SplitSetForRandomForest(trainingSet,8);        //splitting data for Random decisions trees
        LinkedList RandomSet = s.RandomSet();

        LinkedList<MyTree> RandomForestTrees=new LinkedList<>();       //To store all trees
        ListIterator RandomSetIterator = RandomSet.listIterator();
        int i=1;
        while(RandomSetIterator.hasNext())
        {
            testingSet= s.getTestSet();
            LinkedList decisionTree = (LinkedList)RandomSetIterator.next();
            MyTree tree = new BuildTree().buildSubTree(decisionTree, null);  //To build trees of Random Forest
            RandomForestTrees.add(tree);
            /*System.out.println("\nTree-"+i++);                     //Visualization of Tree
            new Disp().displayNodes(tree, "", "");*/
        }
        //checking for training data accuracy
        System.out.println("Random Forest Training test accuracy:-");
        new Predict(RandomForestTrees,trainingSet);

        System.out.println("Random forest prediction accuracy on testing set:-");
        Predict p =new Predict(RandomForestTrees,testingSet);

        //p.printDetailedResult();                                   //To print results of every tree in random forest

        /*Single Decision Tree*/
        MyTree tree = new BuildTree().buildSubTree(trainingSet, null);
        RandomForestTrees = new LinkedList<>();
        RandomForestTrees.add(tree);
        /*System.out.println("Decision Tree");                           //visualization of tree
        new Disp().displayNodes(tree, "", ""); */
        System.out.println("Single Decision tree prediction accuracy:-");
        new Predict(RandomForestTrees, testingSet);
    }
}
