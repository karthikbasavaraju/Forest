import java.util.*;

public class BuildTree {
    /*
        Builds the tree recursively
     */
    public MyTree buildSubTree(LinkedList<HashMap<String,String>> mainList, String bestAttribute) {
        MyTree tree = null;
        ListIterator mainListIterator = mainList.listIterator();
        Set tempchosenAttributesValues = new HashSet();
        while (mainListIterator.hasNext()) {
            LinkedHashMap<String, String> hm = (LinkedHashMap) mainListIterator.next();
            if (hm.containsKey(bestAttribute))
                tempchosenAttributesValues.add(hm.get(bestAttribute));
        }

        LinkedList chosenAttribute = new LinkedList(tempchosenAttributesValues);

        LinkedList<MyTree> childTree = new LinkedList<>();
        if(bestAttribute==null){                                            //Initial case when no Attribute is selected
            int resultIndex = ((HashMap)(mainList.get(0))).size() - 1;
            if (resultIndex > 0) {

                LinkedList<LinkedList> mainList1 = Miscellaneous.mapToList(mainList);
                Entropy e = new Entropy();
                e.calculateEntropy(mainList1, resultIndex);

                String nextBestAttribute = Gain.getBestAttribute();

                if (Gain.getRootEntropy().equals(0.0)) {
                    tree = new MyTree(Entropy.getClasstype().get(0), "_root_");
                    tree.setLeaf();
                }
                else{
                    tree = new BuildTree().buildSubTree(mainList, nextBestAttribute);
                    tree.setEdge("_root_");
                }
            }
            else if (resultIndex == 0) {
                LinkedList classif = new LinkedList(mainList.get(0).values());
                tree = new MyTree(classif.get(0), "_root_");
                tree.setLeaf();
            }
        }
        else{                                                                                  //when Attribute to build node is known
            ListIterator chosenAttributeIterator = chosenAttribute.listIterator();
            while (chosenAttributeIterator.hasNext()) {
                String chosenAttributeString = chosenAttributeIterator.next().toString();
                mainListIterator = mainList.listIterator();
                LinkedList<HashMap<String, String>> newMainList = new LinkedList<>();
                while (mainListIterator.hasNext()) {
                    HashMap hm = (HashMap) mainListIterator.next();
                    HashMap newhm = new LinkedHashMap();
                    for (Object Ohmkeys : hm.keySet()) {
                        String hmkey = Ohmkeys.toString();
                        if (hm.get(hmkey).equals(chosenAttributeString) && hmkey.equals(bestAttribute)) {
                            newhm = new LinkedHashMap(hm);                                            //subsets with same attribute type
                        }
                    }
                    if (newhm.size() != 0) {
                        newhm.remove(bestAttribute);                                                  //remove the chosen attribute from the row
                        newMainList.add(newhm);
                    }
                }

                MyTree nodes = null;
                int resultIndex = (newMainList.get(0)).size() - 1;
                if (resultIndex > 0) {                                                                 //If the attribute selected is not the last row(result row)

                    LinkedList<LinkedList> mainList1 = Miscellaneous.mapToList(newMainList);

                    Entropy e =new Entropy();
                    e.calculateEntropy(mainList1, resultIndex);

                    String nextBestAttribute = Gain.getBestAttribute();

                    if (Gain.getRootEntropy().equals(0.0)) {                                            //if gain is 0 then make it as leaf
                        nodes = new MyTree(Entropy.getClasstype().get(0), chosenAttributeString);
                        nodes.setLeaf();
                    }
                    else {                                                                              //get the next node to build the tree
                        nodes = new BuildTree().buildSubTree(newMainList, nextBestAttribute);
                        nodes.setEdge(chosenAttributeString);
                    }
                }
                else if (resultIndex == 0) {                                                            //if chosen attribute is result column then make is as leaf
                    LinkedList classif = new LinkedList(newMainList.get(0).values());
                    nodes = new MyTree(classif.get(0), chosenAttributeString);
                    nodes.setLeaf();
                }
                childTree.add(nodes);
            }
            tree = new MyTree();
            tree.setAttribute(bestAttribute);
            tree.setChildren(childTree);
        }
        return tree;
    }
}