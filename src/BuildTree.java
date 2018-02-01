import java.util.*;

public class BuildTree {
    static int count=0;
    public BuildTree(LinkedList mainList,String bestAttribute){

        ListIterator mainListIterator = mainList.listIterator();
        Set tempchoosenAttributesValues = new HashSet();
        while(mainListIterator.hasNext()){
            LinkedHashMap<String,String> hm = (LinkedHashMap)mainListIterator.next();
            if(hm.containsKey(bestAttribute))
                tempchoosenAttributesValues.add(hm.get(bestAttribute));
        }
        //System.out.println("chosen attribue "+tempchoosenAttributesValues);

        LinkedList chosenAttribute = new LinkedList(tempchoosenAttributesValues);
        //System.out.println("chosen attribue "+chosenAttribute);


        ListIterator chosenAttributeIterator = chosenAttribute.listIterator();
        while(chosenAttributeIterator.hasNext()) {
            String chosenAttributeString = chosenAttributeIterator.next().toString();
            mainListIterator = mainList.listIterator();
            LinkedList<HashMap<String,String>> newMainList = new LinkedList<>();
            while (mainListIterator.hasNext()) {
                HashMap hm = (HashMap) mainListIterator.next();
                HashMap newhm = new LinkedHashMap();
                for (Object Ohmkeys : hm.keySet()) {
                    String hmkey = Ohmkeys.toString();
                    if (hm.get(hmkey).equals(chosenAttributeString)) {
                        newhm = new LinkedHashMap(hm);
                    }
                }
                if (newhm.size() != 0) {
                    newhm.remove(bestAttribute);
                    newMainList.add(newhm);
                }
            }

            //System.out.println("---chosen attribute value="+chosenAttributeString);
            //System.out.println("newMainList="+newMainList);
            int resultIndex = (newMainList.get(0)).size() - 1;
            count++;
            //System.out.println("resultIndex="+resultIndex+" --count = "+count);
            if(resultIndex>0) {

                LinkedList<LinkedList> mainList1 = (new MapToList()).mapToList(newMainList);
                new Entropy(mainList1, resultIndex);


                String nextBestAttribute = Gain.bestAttribute;


                if (/*nextBestAttribute.equals("EnjoySport") ||*/ Gain.rootEntropy.equals(0.0) );
                else {
                    System.out.println("Chosen Attribute string = "+chosenAttributeString);
                    System.out.println("next best attribute="+nextBestAttribute);
                    System.out.println("parent="+bestAttribute);
                    System.out.println("\n");
                    new BuildTree(newMainList, nextBestAttribute);


                }
            }
            /*ListIterator ll = mainList.listIterator();
            Set s = new HashSet();
            while (ll.hasNext()) {
                HashMap hm = (HashMap) ll.next();
                s.add(hm.get(Gain.bestAttribute));
            }

            Iterator lls = s.iterator();
            while (lls.hasNext()) {
                List o = mainList;
                LinkedList sublist = new LinkedList();
                ll = o.listIterator();
                String q = lls.next().toString();
                while (ll.hasNext()) {
                    HashMap hm = (HashMap) ll.next();
                    if (hm.get(Gain.best_Attribute).equals(q)) {
                        //hm.remove(Gain.best_Attribute);
                        sublist.add(hm);
                    }
                }
                System.out.println(sublist);
            }*/
        }
    }

}
