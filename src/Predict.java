import java.util.*;

public class Predict {

    private String detailResult="";                 //Contains all the results from all the trees/tree of Random Forest/Decision Tree
    private int passCount=0;                        //No of rows correctly predicted by the tree
    private int totalCount=0;                       //Total no of rows to predict
    private String key;                             //Column name with which we are going to compare our result
    private String classification;                  //Stores result of current decision tree

    public Predict(LinkedList RandomForest, LinkedList<HashMap<String,String>> predictList){

        /*
        To get the result attribute's name
        */
        if (predictList.size()>0){
            for(String key : predictList.get(0).keySet()){
                this.key=key;
            }
        }

        this.totalCount = predictList.size();
        ListIterator predictListIterator = predictList.listIterator();
        while(predictListIterator.hasNext()){
            ListIterator RandomForestLterator = RandomForest.listIterator();
            HashMap toPredict = (HashMap) predictListIterator.next();
            HashMap<String,Integer> majority = new HashMap();
            while(RandomForestLterator.hasNext()) {
                MyTree tree = (MyTree) RandomForestLterator.next();

                guessResult(tree, toPredict);
                /*
                    Storing prediction count
                 */
                if(majority.containsKey(classification)){
                    int t = majority.get(classification);
                    t++;
                    majority.put(classification,t);
                }
                else{
                    majority.put(classification,1);
                }
            }

            /*
            Voting
             */
            String classifi = "";
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
            this.detailResult = this.detailResult +   "--------------------------" +classifi+"------------------------\n";
        }
        predictionAccuracy();
    }

    /*
    To travers(DFS) to the leaf based on the attribute and its corresponding values
     */
    void guessResult(MyTree tree, HashMap<String,String> toPredict){
        if(tree.isLeaf()) {
           this.detailResult = this.detailResult + toPredict + "---" + tree.getAttribute()+"\n";
           //System.out.println(toPredict + "---" + tree.getAttribute());
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
