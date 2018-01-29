import java.util.*;

public class Entropy {

    private HashMap<String,HashMap<String,Double>> allEntropy;
    private  HashMap<String,Integer> totalclassificationCount;
    private Double rootEntropy;

    private static double Log2(double n) {
        if(n ==0)
            return 0;
        return Math.log(n) / Math.log(2);
    }

    public HashMap<String, HashMap<String, Double>> getAllEntropy() {
        return allEntropy;
    }

    public void topEntropy() {                            //entropy of an attribute
        double rootEntropy = 0;
        Integer total = 0;
        for (Integer b : totalclassificationCount.values()) {
            total = total + b;
        }

        for (String b : totalclassificationCount.keySet()) {
            int p = totalclassificationCount.get(b);
            double prob = (double) p / total;
            rootEntropy -= prob * Log2(prob);                                  //
        }
        System.out.println(rootEntropy);
        this.rootEntropy=rootEntropy;
    }


    public Entropy(LinkedList allAttributeValues, Integer resultIndex){

        HashMap<String,HashMap<String,Double>> fentropy = new HashMap<>();     //To store the entropy of all attributes
        LinkedList attribute = (LinkedList)allAttributeValues.get(0);
        allAttributeValues.remove(0);                                     // To remove heading

        ListIterator allAttributeValuesIterator = allAttributeValues.listIterator();
        HashMap<String,Integer> totalclassificationCount = new HashMap<>();
        Set<String> uniqueClassifications= new HashSet();                          //To find the unique classifications

        while(allAttributeValuesIterator.hasNext()){
            LinkedList temp = (LinkedList)allAttributeValuesIterator.next();
            uniqueClassifications.add(temp.get(resultIndex).toString());

            if(totalclassificationCount.containsKey(temp.get(resultIndex).toString()))
            {
                int count = 1+ totalclassificationCount.get(temp.get(resultIndex).toString());
                totalclassificationCount.put(temp.get(resultIndex).toString(),count);
            }
            else{
                totalclassificationCount.put(temp.get(resultIndex).toString(),1);
            }
        }
        this.totalclassificationCount = totalclassificationCount;
        LinkedList classificationType = new LinkedList(uniqueClassifications);                    //Unique attributes

        for(int k=0;k<resultIndex;k++) {                        //TO calculate Entropy for all attributes

            HashMap<String, HashMap> classificationCount = new HashMap<>();      //Classification count with respect to value's
            allAttributeValuesIterator = allAttributeValues.listIterator();                                             // Values list
            while (allAttributeValuesIterator.hasNext()) {
                LinkedList conditionData = (LinkedList) allAttributeValuesIterator.next();   //values(D6,Rain,Cool,Normal,Strong=No)
                ListIterator classificationTypeIterator = classificationType.listIterator();
                while (classificationTypeIterator.hasNext()) {
                    String classification = classificationTypeIterator.next().toString();
                    if (conditionData.get(resultIndex).equals(classification)) {            //to check which classification(yes or no) it belongs to
                        if (classificationCount.containsKey(conditionData.get(k))) {        //if found then increment the corresponding classification count
                            HashMap hms = classificationCount.get(conditionData.get(k));
                            int i = 1 + Integer.parseInt(hms.get(classification).toString());
                            hms.put(classification, i);
                        } else {                                                            //if not found then add the classification
                            ListIterator t = classificationType.listIterator();
                            HashMap<String, Integer> temp = new HashMap();
                            while (t.hasNext()) {
                                temp.put(t.next().toString(), 0);
                            }
                            temp.put(classification, 1);
                            classificationCount.put(conditionData.get(k).toString(), temp); //Classification attribute, name and count
                        }
                    }
                }
            }
            System.out.println(classificationCount);

            HashMap<String, Double> entropy = new HashMap<>();                              //entropy of an attribute
            for (String a : classificationCount.keySet()) {

                double entropyValue = 0;

                HashMap<String, Integer> t = classificationCount.get(a);
                Integer total = 0;
                for (Integer b : t.values()) {
                    total = total + b;
                }

                for (String b : t.keySet()) {
                    int p = t.get(b);
                    double prob = (double) p / total;
                    entropyValue -= prob * Log2(prob);                                  //
                }
                entropy.put(a, entropyValue);
            }
            fentropy.put(attribute.get(k).toString(),entropy);
        }
        System.out.println(fentropy);
        this.allEntropy = fentropy;
        System.out.println(this.totalclassificationCount);
        topEntropy();
    }
}
