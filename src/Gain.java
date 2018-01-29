import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

public class Gain {

    public Gain(Double rootEntropy,HashMap<String,HashMap<String,Double>> childEntropy, HashMap<String,HashMap> classificationCountWithAttributes){
        HashMap<String,Double> rootGain = new HashMap<>();
        for(String root : childEntropy.keySet()){
            //System.out.println(root);
            Double rootEntropyValue = rootEntropy;
            HashMap<String,Double> childEntropyValues= childEntropy.get(root);
            Double count =0.0 ;
            for(String child : childEntropy.get(root).keySet()){
                //System.out.println(child);
                HashMap<String,Integer> temp = (HashMap<String, Integer>) classificationCountWithAttributes.get(root).get(child);
               // System.out.println();
                for(String countName : temp.keySet()){
                    count = count + Integer.parseInt(temp.get(countName).toString());
                }

            }
            //System.out.println("count = "+count);

            for(String child : childEntropyValues.keySet()){
                //System.out.println(child);
                Double classificationCount =0.0;
                HashMap<String,Integer> temp = (HashMap<String, Integer>) classificationCountWithAttributes.get(root).get(child);
                // System.out.println();
                for(String countName : temp.keySet()){
                    classificationCount = classificationCount + Integer.parseInt(temp.get(countName).toString());
                }
                classificationCountWithAttributes.get(root).get(child);
                System.out.println(classificationCount+","+count+","+childEntropyValues.get(child));
                rootEntropyValue -= (classificationCount/count)*childEntropyValues.get(child);

            }
            System.out.println();
            rootGain.put(root,rootEntropyValue);
        }
        System.out.println(rootGain);
    }
}
