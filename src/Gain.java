import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Gain {

    public static String bestAttribute;
    public static Double rootEntropy;
    public Double informationGain(Double classificationCount, Double count){
        Double n = Math.abs(classificationCount/count);
        if(n ==0.0)
            return 0.0;
        Double log_n = Math.log(n) / Math.log(2);
        log_n = n*log_n;
        return log_n;
    }
/*
    Concentrate on fentropy // child entropy
    and rootgain //gain
 */

    public Gain(Double rootEntropy,LinkedHashMap<String,LinkedHashMap<String,Double>> childEntropy, LinkedHashMap<String,LinkedHashMap> classificationCountWithAttributes){
        Disp.display("start of Gain");

        LinkedHashMap<String,Double> rootGain = new LinkedHashMap<>();
        LinkedHashMap<String,Double> gainRatio = new LinkedHashMap<>();
        LinkedHashMap<String,Double> splitEntropy = new LinkedHashMap<>();
        Double infoGain;

        for(String root : childEntropy.keySet()){

            infoGain = 0.0;
            Double rootEntropyValue = rootEntropy;
            LinkedHashMap<String,Double> childEntropyValues= childEntropy.get(root);
            Double count =0.0 ;
            for(String child : childEntropy.get(root).keySet()){
                LinkedHashMap<String,Integer> temp = (LinkedHashMap<String, Integer>) classificationCountWithAttributes.get(root).get(child);
                for(String countName : temp.keySet()){
                    count = count + Integer.parseInt(temp.get(countName).toString());
                }

            }
            for(String child : childEntropyValues.keySet()){
                Double classificationCount =0.0;
                LinkedHashMap<String,Integer> temp = (LinkedHashMap<String, Integer>) classificationCountWithAttributes.get(root).get(child);
                for(String countName : temp.keySet()){
                    classificationCount = classificationCount + Integer.parseInt(temp.get(countName).toString());
                }
                classificationCountWithAttributes.get(root).get(child);
                rootEntropyValue -= Math.abs(classificationCount/count)*childEntropyValues.get(child);
                infoGain += informationGain(classificationCount,count);

            }
            infoGain = -infoGain;
            rootGain.put(root,Math.round(rootEntropyValue*10000.0)/10000.0);
           splitEntropy.put(root,Math.round(infoGain*10000.0)/10000.0);
            gainRatio.put(root,(rootEntropyValue)/(infoGain));

        }
        //System.out.println("gain ="+rootGain);              //Information Gain
       // System.out.println("child entropy="+childEntropy);
        /*System.out.println("Splitentropy = "+splitEntropy);
        System.out.println("gain ratio = "+gainRatio);
        */
        Double cc = Collections.max(rootGain.values());
        for(String s : rootGain.keySet()){
            if(rootGain.get(s).equals(cc)){
                this.bestAttribute=s;
                break;
            }
        }
        this.rootEntropy = rootEntropy;
        Disp.display("end of Gain");
    }
}
