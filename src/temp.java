import java.util.*;

public class temp {

    private LinkedHashMap<String, LinkedHashMap<String, Double>> childEntropy;
    private LinkedHashMap<String, Integer> totalclassificationCount;
    private LinkedHashMap<String, LinkedHashMap> classificationCountWithAttributea;
    private Double rootEntropy;
    LinkedHashMap<String, Double> rootGain = new LinkedHashMap<>();
    LinkedHashMap<String, Double> gainRatio = new LinkedHashMap<>();
    LinkedHashMap<String, Double> splitEntropy = new LinkedHashMap<>();

    public LinkedHashMap<String, Double> getGainRatio() {
        return gainRatio;
    }

    public LinkedHashMap<String, Double> getSplitEntropy() {
        return splitEntropy;
    }

    public LinkedHashMap<String, Double> getRootGain() {
        return rootGain;
    }

    private static double Log2(double n) {
        if (n == 0)
            return 0;
        return Math.log(n) / Math.log(2);
    }

    public LinkedHashMap<String, LinkedHashMap<String, Double>> getchildEntropy() {
        return childEntropy;
    }

    public void calculateGain() {
        new Gain(rootEntropy, childEntropy, classificationCountWithAttributea);
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
        //System.out.println(rootEntropy);
        this.rootEntropy = rootEntropy;
    }


    public temp(LinkedList allAttributeValues, String resultIndex) {

        LinkedHashMap attribute = (LinkedHashMap) allAttributeValues.get(0);
        // System.out.println(allAttributeValues);
        ListIterator allAttributeValuesIterator = allAttributeValues.listIterator();

        totalclassificationCount = new LinkedHashMap<>();

        while (allAttributeValuesIterator.hasNext()) {
            LinkedHashMap temp = (LinkedHashMap) allAttributeValuesIterator.next();

            if (totalclassificationCount.containsKey(temp.get(resultIndex).toString())) {
                int count = 1 + totalclassificationCount.get(temp.get(resultIndex).toString());
                totalclassificationCount.put(temp.get(resultIndex).toString(), count);
            } else {
                totalclassificationCount.put(temp.get(resultIndex).toString(), 1);
            }
        }
        System.out.println(totalclassificationCount);
        ArrayList classification = new ArrayList(totalclassificationCount.keySet());
        allAttributeValuesIterator = allAttributeValues.listIterator();
        LinkedHashMap<String, LinkedHashMap> hm1 = new LinkedHashMap<>();
        while (allAttributeValuesIterator.hasNext()) {
            LinkedHashMap temp = (LinkedHashMap) allAttributeValuesIterator.next();
            System.out.println("temp=" + temp);
            LinkedHashMap<String, LinkedHashMap> hm2 = new LinkedHashMap<String, LinkedHashMap>();
            for (Object s1 : temp.keySet()) {
                String s = s1.toString();
                System.out.println("s=" + s);    //s has wind
                System.out.println("classification=" + classification);
                ListIterator classificationIterator = classification.listIterator();
                LinkedHashMap<String, Integer> hm3 = new LinkedHashMap<>();
                while (classificationIterator.hasNext()) {
                    String classifi = classificationIterator.next().toString();
                    System.out.println("classific = " + classifi);
                    System.out.println();
                    //   System.out.println(hm3);
                    //System.out.println(hm2);
                }
                //System.out.println(hm1);


            }
        }
    }
}
