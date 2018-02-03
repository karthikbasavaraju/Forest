import java.util.*;

public class BuildTree {
    public void buildSubTree(LinkedList mainList,String bestAttribute){

            ListIterator mainListIterator = mainList.listIterator();
            Set tempchoosenAttributesValues = new HashSet();
            while (mainListIterator.hasNext()) {
                LinkedHashMap<String, String> hm = (LinkedHashMap) mainListIterator.next();
                if (hm.containsKey(bestAttribute))
                    tempchoosenAttributesValues.add(hm.get(bestAttribute));
            }

            LinkedList chosenAttribute = new LinkedList(tempchoosenAttributesValues);


            System.out.println("best attribute in main = " + bestAttribute);
            LinkedList<MyTree> childTree = new LinkedList<>();
            ListIterator chosenAttributeIterator = chosenAttribute.listIterator();
            while (chosenAttributeIterator.hasNext()) {

                String chosenAttributeString = chosenAttributeIterator.next().toString();
                System.out.println("chosenAtributeString = " + chosenAttributeString);
                mainListIterator = mainList.listIterator();
                LinkedList<HashMap<String, String>> newMainList = new LinkedList<>();
                while (mainListIterator.hasNext()) {
                    HashMap hm = (HashMap) mainListIterator.next();
                    HashMap newhm = new LinkedHashMap();
                    for (Object Ohmkeys : hm.keySet()) {
                        String hmkey = Ohmkeys.toString();
                        //System.out.println("chosen aattr="+chosenAttributeString+"---"+hm.get(hmkey));
                        if (hm.get(hmkey).equals(chosenAttributeString) && hmkey.equals(bestAttribute)) {
                            newhm = new LinkedHashMap(hm);
                        }
                    }
                    if (newhm.size() != 0) {
                        newhm.remove(bestAttribute);
                        newMainList.add(newhm);
                    }
                }

                MyTree nodes = null;

                int resultIndex = (newMainList.get(0)).size() - 1;
                if (resultIndex > 0) {

                    LinkedList<LinkedList> mainList1 = (new MapToList()).mapToList(newMainList);
                    new Entropy(mainList1, resultIndex);


                    String nextBestAttribute = Gain.bestAttribute;

/*
                ListIterator test = newMainList.listIterator();
                System.out.println("newMainList=");
                while (test.hasNext()){
                    System.out.println(test.next());
                }
*/


                    if (nextBestAttribute.equals("EnjoySport") || Gain.rootEntropy.equals(0.0)) {
                        System.out.println("Classification = " + Entropy.classtype.get(0));
                        System.out.println("parent=" + bestAttribute);
                        System.out.println();
                        nodes = new MyTree(Entropy.classtype.get(0), chosenAttributeString);
                    /*System.out.println("//////////");
                    System.out.println(Entropy.classtype.get(0));
                    System.out.println("Chosen Attribute string = "+chosenAttributeString);
                    System.out.println("parent="+bestAttribute);
                    System.out.println("//////////\n");
                   */// System.out.println("Chosen Attribute string og 0.0 = "+chosenAttributeString)
                    } else {
                        System.out.println("next best attribute=" + nextBestAttribute);
                        System.out.println("parent=" + bestAttribute);
                        System.out.println();
                        nodes = new MyTree(nextBestAttribute, chosenAttributeString);
                    /*System.out.println("Chosen Attribute string = "+chosenAttributeString);
                    System.out.println("next best attribute="+nextBestAttribute);
                    System.out.println("parent="+bestAttribute);
                    System.out.println("\n");

                    */
                        new BuildTree().buildSubTree(newMainList, nextBestAttribute);
                    }
                } else if (resultIndex == 0) {
                    LinkedList classif = new LinkedList(newMainList.get(0).values());
                    System.out.println("Classification = " + classif.get(0));
                    System.out.println("parent=" + bestAttribute);
                    System.out.println();
                    nodes = new MyTree(classif.get(0), chosenAttributeString);
                }
                childTree.add(nodes);
            }
        }
//        MyTree rootNode = new MyTree();
}
