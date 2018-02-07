import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class SplitSets {
    private LinkedList<HashMap<String,String>> testSet;
    private LinkedList<HashMap<String,String>> trainSet;

    public SplitSets(LinkedList mainList,double testingPercentage){

        int size = mainList.size();
        long testSetCount = Math.round(size*(testingPercentage/100));

        LinkedList<HashMap<String,String>> trainset = new LinkedList(mainList);
        LinkedList<HashMap<String,String>> testSet = new LinkedList<>();
        Random r = new Random();

        for(int i=0;i<testSetCount;i++) {
            HashMap hm = (HashMap)trainset.remove(r.nextInt(size-1));
            size--;
            testSet.add(hm);
        }
        this.testSet = testSet;
        this.trainSet = trainset;
    }

    public LinkedList<HashMap<String, String>> getTestSet() {
        return testSet;
    }

    public LinkedList<HashMap<String, String>> getTrainSet() {
        return trainSet;
    }
}
