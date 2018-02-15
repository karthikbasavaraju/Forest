import java.util.*;

public class SplitSets {
    private LinkedList<HashMap<String,String>> testSet;
    private LinkedList<HashMap<String,String>> trainSet;

    /*
    Spliting Data set to Training and Testing sets
     */
    public void SplitToTrainingTestingSet(LinkedList mainList,double testingPercentage)
    {

        int size = mainList.size();
        long testSetCount = Math.round(size*(testingPercentage/100));

        LinkedList<HashMap<String,String>> trainset = new LinkedList(mainList);
        LinkedList<HashMap<String,String>> testSet = new LinkedList<>();
        Random r = new Random();

        for(int i=0;i<testSetCount;i++)
        {
            HashMap hm = (HashMap)trainset.remove(r.nextInt(size-1));
            size--;
            testSet.add(hm);
        }
        this.testSet = testSet;
        this.trainSet = trainset;
    }

    public LinkedList<HashMap<String, String>> getTestSet()
    {
        return testSet;
    }

    public LinkedList<HashMap<String, String>> getTrainSet()
    {
        return trainSet;
    }


    /*
    Splitting sets for Random Decision Trees
     */
    private LinkedList RandomForest;

    public void SplitSetForRandomForest(LinkedList mainList,int forests)
    {
        int size = mainList.size();
        LinkedList<HashMap<String,String>> trainset =  new LinkedList<>(mainList);
        Random r = new Random();
        LinkedList<LinkedList> subSets = new LinkedList<>();

        int random_subset_count = forests;
        int random_subset_rows=(size*(2))/3;            //66.6%% of training set for every tree in random forest
        size = mainList.size();
        int k =random_subset_count;
        while(k>0)
        {
            LinkedList<LinkedHashMap<String,String>> temp = new LinkedList<>();
            trainset = new LinkedList(mainList);
            for (int i = 0; i < random_subset_rows; i++) {
                if(size>1) {
                    LinkedHashMap hm = (LinkedHashMap) trainset.remove(r.nextInt(size - 1));
                    size--;
                    temp.add(hm);
                }
                else if(size==1){
                    LinkedHashMap hm = (LinkedHashMap) trainset.remove(0);
                    temp.add(hm);
                }
            }
            subSets.add(temp);
            k--;
        }
        RandomForest = subSets;
    }

    //returns all the trees of random forest
    public LinkedList<HashMap<String, String>> RandomSet() {
        return this.RandomForest;
    }
}
