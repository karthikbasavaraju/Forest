import java.util.*;

public class Predict {

    private String detailResult="";
    private int passCount=0;
    private int totalCount=0;
    private String key;
    private String classifi = "";
    private String classification;
    private int k =5;
    public Predict(LinkedList RandomForest, LinkedList<HashMap<String,String>> predictList){
        for(String key : predictList.get(0).keySet()){
            this.key=key;
        }

        this.totalCount = predictList.size();
        ListIterator predictListIterator = predictList.listIterator();
        int k =5;
        while(predictListIterator.hasNext()){
            ListIterator RandomForestLterator = RandomForest.listIterator();
            HashMap toPredict = (HashMap) predictListIterator.next();
            HashMap<String,Integer> majority = new HashMap();
            while(RandomForestLterator.hasNext()) {
                MyTree tree = (MyTree) RandomForestLterator.next();
                //System.out.println(toPredict);
                guessResult(tree, toPredict);
                if(majority.containsKey(classification)){
                    int t = majority.get(classification);
                    t++;
                    majority.put(classification,t);
                }
                else{
                    majority.put(classification,1);
                }

            }
            int max =Collections.max(majority.values());

            for(String key : majority.keySet()){
                if(majority.get(key)==max){
                    classifi = key;
                    break;
                }
            }
            if(toPredict.get(key).equals(classifi)){
                this.passCount++;
            }
            //System.out.println(toPredict + "---" + classifi);
        }
        predictionAccuracy();
    }

    void guessResult(MyTree tree, HashMap<String,String> toPredict){
        if(tree.isLeaf()) {
           this.detailResult = this.detailResult + toPredict + "---" + tree.getAttribute()+"\n";
           //System.out.println(toPredict + "---" + tree.getAttribute());
           if(toPredict.get(key).equals(tree.getAttribute())){
               //this.passCount++;
           }
            this.classification = tree.getAttribute().toString();
        }
        else{
            String value = toPredict.get(tree.getAttribute()).toString();
            LinkedList childTreeList = tree.getChildren();
            ListIterator childTreeListIterator = childTreeList.listIterator();
            while (childTreeListIterator.hasNext()) {
                MyTree childTree = (MyTree) childTreeListIterator.next();
                if (childTree.getEdge().equals(value)) {
                    guessResult(childTree, toPredict);
                    break;
                }
            }
        }
    }

    void printDetailedResult(){
        System.out.println("Detailed result :-\n"+detailResult);
    }

    void predictionAccuracy(){
        double accuracy = ((double)passCount/(double)totalCount)*100;
        accuracy = Math.round(accuracy*100)/100;
        System.out.println("Prediction accuracy = "+accuracy+"%");
    }
}
