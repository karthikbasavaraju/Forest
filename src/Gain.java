import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

public class Gain {

    public Double informationGain(Double classificationCount, Double count){
        Double n = Math.abs(classificationCount/count);
        System.out.println("n="+n);
        System.out.println("classcount="+classificationCount+", count="+count);
        if(n ==0.0)
            return 0.0;
        Double log_n = Math.log(n) / Math.log(2);
        System.out.println("logn "+log_n);
        log_n = n*log_n;
        System.out.println("logn "+log_n);
        return log_n;
    }


    public Gain(Double rootEntropy,HashMap<String,HashMap<String,Double>> childEntropy, HashMap<String,HashMap> classificationCountWithAttributes){
        HashMap<String,Double> rootGain = new HashMap<>();
        HashMap<String,Double> gainRatio = new HashMap<>();
        HashMap<String,Double> splitEntropy = new HashMap<>();
        Double infoGain;
        for(String root : childEntropy.keySet()){

            infoGain = 0.0;
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
                rootEntropyValue -= Math.abs(classificationCount/count)*childEntropyValues.get(child);
                infoGain += informationGain(classificationCount,count);

            }
            System.out.println();
            infoGain = -infoGain;
            rootGain.put(root,rootEntropyValue);
            splitEntropy.put(root,infoGain);
            gainRatio.put(root,(rootEntropyValue)/(infoGain));

        }

        System.out.println(rootGain);
        System.out.println(splitEntropy);
        System.out.println(gainRatio);

        System.out.println();
    }
}
