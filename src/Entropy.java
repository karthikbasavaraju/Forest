import java.util.*;

public class Entropy {

    private LinkedHashMap<String,LinkedHashMap<String,Double>> childEntropy;
    private  LinkedHashMap<String,Integer> totalclassificationCount;
    private LinkedHashMap<String,LinkedHashMap> classificationCountWithAttributea;
    private Double rootEntropy;
    static private LinkedList classtype;
    LinkedHashMap<String,Double> rootGain = new LinkedHashMap<>();
    LinkedHashMap<String,Double> gainRatio = new LinkedHashMap<>();
    LinkedHashMap<String,Double> splitEntropy = new LinkedHashMap<>();

    private static double Log2(double n)
    {
        if(n ==0)
            return 0;
        return Math.log(n) / Math.log(2);
    }

    public static LinkedList getClasstype()
    {
        return classtype;
    }


    /*Entropy of attribute  H(s)*/
    public void topEntropy()
    {
        double rootEntropy = 0;
        Integer total = 0;
        for (Integer b : totalclassificationCount.values())
            total = total + b;

        for (String b : totalclassificationCount.keySet())
        {
            int p = totalclassificationCount.get(b);
            double prob = (double) p / total;
            rootEntropy -= prob * Log2(prob);                                  //∑ -p(x)*log2(p(x))
        }
        this.rootEntropy=rootEntropy;
    }

    /*
        Calculates Entropy of child nodes
     */
    public void calculateEntropy(LinkedList allAttributeValues, Integer resultIndex)
    {
        Miscellaneous.display("start of Entropy");

        LinkedHashMap<String,LinkedHashMap<String,Double>> fentropy = new LinkedHashMap<>();     //To store the entropy of all attributes
        LinkedList attribute = (LinkedList)allAttributeValues.get(0);
        allAttributeValues.remove(0);                                                       // To remove heading

        ListIterator allAttributeValuesIterator = allAttributeValues.listIterator();
        LinkedHashMap<String,Integer> totalclassificationCount = new LinkedHashMap<>();
        LinkedHashSet<String> uniqueClassifications= new LinkedHashSet<>();                          //To find the unique classifications

        while(allAttributeValuesIterator.hasNext())
        {
            LinkedList temp = (LinkedList)allAttributeValuesIterator.next();
            uniqueClassifications.add(temp.get(resultIndex).toString());

            if(totalclassificationCount.containsKey(temp.get(resultIndex).toString()))
            {
                int count = 1+ totalclassificationCount.get(temp.get(resultIndex).toString());
                totalclassificationCount.put(temp.get(resultIndex).toString(),count);
            }
            else
                totalclassificationCount.put(temp.get(resultIndex).toString(),1);
        }
        this.totalclassificationCount = totalclassificationCount;
        LinkedList classificationType = new LinkedList(uniqueClassifications);                    //Unique attributes
        this.classtype = new LinkedList(uniqueClassifications);
        LinkedHashMap<String,LinkedHashMap> classificationCountWithAttributes = new LinkedHashMap<>();
        for(int k=0;k<resultIndex;k++)                        //TO calculate Entropy for all attributes
        {
            LinkedHashMap<String, LinkedHashMap> classificationCount = new LinkedHashMap<>();      //Classification count with respect to value's
            allAttributeValuesIterator = allAttributeValues.listIterator();                                             // Values list
            while (allAttributeValuesIterator.hasNext())
            {
                LinkedList conditionData = (LinkedList) allAttributeValuesIterator.next();        //values(Ex : D6,Rain,Cool,Normal,Strong=No)
                ListIterator classificationTypeIterator = classificationType.listIterator();
                while (classificationTypeIterator.hasNext())
                {
                    String classification = classificationTypeIterator.next().toString();
                    if (conditionData.get(resultIndex).equals(classification))             //to check which classification(yes or no) it belongs to
                    {
                        if (classificationCount.containsKey(conditionData.get(k)))         //if found then increment the corresponding classification count
                        {
                            LinkedHashMap hms = classificationCount.get(conditionData.get(k));
                            int i = 1 + Integer.parseInt(hms.get(classification).toString());
                            hms.put(classification, i);
                        }
                        else                                                             //if not found then add the classification
                        {   ListIterator t = classificationType.listIterator();
                            LinkedHashMap<String, Integer> temp = new LinkedHashMap();
                            while (t.hasNext())
                            {
                                temp.put(t.next().toString(), 0);
                            }
                            temp.put(classification, 1);
                            classificationCount.put(conditionData.get(k).toString(), temp); //attribute, attribute's fields and count
                        }
                    }
                }
            }


            LinkedHashMap<String, Double> entropy = new LinkedHashMap<>();                              //entropy of an attribute
            for (String a : classificationCount.keySet()) {

                double entropyValue = 0;

                LinkedHashMap<String, Integer> t = classificationCount.get(a);
                Integer total = 0;
                for (Integer b : t.values())
                    total = total + b;
                for (String b : t.keySet())
                {
                    int p = t.get(b);
                    double prob = (double) p / total;                               // p(x) = |S(v)|%|S|;
                    entropyValue -= prob * Log2(prob);                              //∑ -p(x)*log2(p(x))
                }
                entropy.put(a, entropyValue);
            }
            fentropy.put(attribute.get(k).toString(),entropy);                      //Ccontains all the entropy of attribute values
            classificationCountWithAttributes.put(attribute.get(k).toString(),classificationCount);
        }

        this.classificationCountWithAttributea = classificationCountWithAttributes;
        this.childEntropy = fentropy;

        topEntropy();
        new Gain(rootEntropy,childEntropy,classificationCountWithAttributea);    //To calculate Gain
        Miscellaneous.display("End of Entropy");
    }
}
