import java.util.*;

public class SplitSets {
    private LinkedList<HashMap<String,String>> testSet;
    private LinkedList<HashMap<String,String>> trainSet;

    /*
    Spliting Data set to Training and Testing sets
     */
    public void SplitToTrainingTestingSet(LinkedList mainList,double testingPercentage){

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


    /*
    Splitting sets for Random Decision Trees
     */
    private LinkedList RandomForest;

    public void SplitSetForRandomForest(LinkedList mainList,int forests){

        int size = mainList.size();

        int no_of_unique_subset= forests; //int no_of_unique_subset = forests/2;
        int random_subset = forests-no_of_unique_subset;
        int no_of_unique_subset_rows=0;
        if(no_of_unique_subset>0)no_of_unique_subset_rows = size/no_of_unique_subset;
        int random_subset_rows=0;
        if(random_subset>0)random_subset_rows= size/random_subset;

        LinkedList<HashMap<String,String>> trainset = new LinkedList(mainList);
        Random r = new Random();
        LinkedList<LinkedList> setOf3 = new LinkedList<>();

        /*Splits data without considering rows of previous sub data set.
        So that every tree has unique set and also so all rows are covered*/

        int k =no_of_unique_subset;
        while(k>0) {
            LinkedList<LinkedHashMap<String,String>> temp = new LinkedList<>();
            for (int i = 0; i < no_of_unique_subset_rows; i++) {
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
            setOf3.add(temp);
            k--;
        }
/*
        //Splits data randomly with covered sets
        size = mainList.size();
        k =random_subset;
        while(k>0) {
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
            setOf3.add(temp);
            k--;
        }

        //Splits data Attribute wise
        LinkedList ll = new LinkedList(((HashMap)(mainList.get(0))).keySet());
        ListIterator llIterator = ll.listIterator();
        while(llIterator.hasNext()){
            System.out.println();
            String attr = llIterator.next().toString();
            if(llIterator.hasNext()) {
                LinkedList<LinkedHashMap<String, String>> temp = new LinkedList<>();
                trainset = (LinkedList<HashMap<String, String>>) mainList.clone();
                ListIterator trainsetIterator = trainset.listIterator();
                while (trainsetIterator.hasNext()) {
                    LinkedHashMap hm = (LinkedHashMap) new LinkedHashMap((LinkedHashMap) trainsetIterator.next());
                    System.out.println("lol=" + hm);
                    hm.remove(attr);
                    System.out.println(hm);
                    temp.add(hm);
                }
                setOf3.add(temp);
            }
        }
        System.out.println(setOf3);
*/

        RandomForest = setOf3;
    }

    public LinkedList<HashMap<String, String>> RandomSet() {
        return this.RandomForest;
    }



}
