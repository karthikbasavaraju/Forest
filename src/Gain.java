import java.util.Collections;
import java.util.LinkedHashMap;

public class Gain {

    private static String bestAttribute;
    private static Double rootEntropy;

    public static String getBestAttribute()
    {
        return bestAttribute;
    }

    public static Double getRootEntropy()
    {
        return rootEntropy;
    }

    /*To calculate Gain*/
    public Gain(Double rootEntropy,LinkedHashMap<String,LinkedHashMap<String,Double>> childEntropy, LinkedHashMap<String,LinkedHashMap> classificationCountWithAttributes)
    {
        Miscellaneous.display("start of Gain");

        LinkedHashMap<String,Double> rootGain = new LinkedHashMap<>();

        for(String root : childEntropy.keySet())
        {
            Double rootEntropyValue = rootEntropy;                                              //H(s)
            LinkedHashMap<String,Double> childEntropyValues= childEntropy.get(root);
            Double count =0.0 ;
            for(String child : childEntropy.get(root).keySet())
            {
                LinkedHashMap<String,Integer> temp = (LinkedHashMap<String, Integer>) classificationCountWithAttributes.get(root).get(child);
                for(String countName : temp.keySet())
                {
                    count = count + Integer.parseInt(temp.get(countName).toString());           //s = set of examples{x}
                }
            }

            for(String child : childEntropyValues.keySet())
            {
                Double classificationCount =0.0;
                LinkedHashMap<String,Integer> temp = (LinkedHashMap<String, Integer>) classificationCountWithAttributes.get(root).get(child);
                for(String countName : temp.keySet())
                {
                    classificationCount = classificationCount + Integer.parseInt(temp.get(countName).toString());   //S(v):- subset where x(A) = v
                }
                classificationCountWithAttributes.get(root).get(child);
                rootEntropyValue -= Math.abs(classificationCount/count)*childEntropyValues.get(child);        //Gain(S,A) =H(s) - ∑((|S(v)|%|S|)*H(S(v))
            }
            rootGain.put(root,Math.round(rootEntropyValue*10000.0)/10000.0);                                //Rounding off to 4 decimal place

        }
        Double cc = Collections.max(rootGain.values());
        for(String s : rootGain.keySet())
        {
            if(rootGain.get(s).equals(cc))
            {
                this.bestAttribute=s;                                               //best attribute to build the sub-tree
                break;
            }
        }
        this.rootEntropy = rootEntropy;
        Miscellaneous.display("end of Gain");
    }
}
