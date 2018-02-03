import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class Predict {

    private String detailResult;
    private int passCount=0;
    private int totalCount=0;
    private String key;
    public Predict(MyTree tree, LinkedList<HashMap<String,String>> predictList){
        for(String key : predictList.get(0).keySet()){
            this.key=key;
        }
        this.totalCount = predictList.size();
        ListIterator predictListIterator = predictList.listIterator();
        while(predictListIterator.hasNext()){
            HashMap toPredict = (HashMap)predictListIterator.next();
            guessResult(tree,toPredict);
        }
    }

    void guessResult(MyTree tree, HashMap<String,String> toPredict){
        if(tree.isLeaf()) {
           this.detailResult = this.detailResult + toPredict + "---" + tree.getAttribute()+"\n";
           if(toPredict.get(key).equals(tree.getAttribute())){
               this.passCount++;
           }
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
