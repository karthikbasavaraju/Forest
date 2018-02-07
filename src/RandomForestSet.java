import java.util.*;

public class RandomForestSet {
    private LinkedList RandomForest;

    public RandomForestSet(LinkedList mainList,int forests){

        int size = mainList.size();

        int noOfSet = size/forests;
        int trees_3 = size/3;

        LinkedList<HashMap<String,String>> trainset = new LinkedList(mainList);
        Random r = new Random();
        LinkedList<LinkedList> setOf3 = new LinkedList<>();

        int k =3;
        while(k>0) {
            LinkedList<LinkedHashMap<String,String>> temp = new LinkedList<>();
            for (int i = 0; i < trees_3; i++) {
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

        size = mainList.size();

        k =3;
        while(k>0) {
            LinkedList<LinkedHashMap<String,String>> temp = new LinkedList<>();
            trainset = new LinkedList(mainList);
            for (int i = 0; i < trees_3; i++) {
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
       // System.out.println(setOf3);
        RandomForest = setOf3;
    }

    public LinkedList<HashMap<String, String>> RandomSet() {
        return this.RandomForest;
    }

}
