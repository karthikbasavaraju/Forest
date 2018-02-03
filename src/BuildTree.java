import java.util.*;

public class BuildTree {
    public MyTree buildSubTree(LinkedList<HashMap<String,String>> mainList, String bestAttribute) {
        MyTree tree = null;
        ListIterator mainListIterator = mainList.listIterator();
        Set tempchoosenAttributesValues = new HashSet();
        while (mainListIterator.hasNext()) {
            LinkedHashMap<String, String> hm = (LinkedHashMap) mainListIterator.next();
            if (hm.containsKey(bestAttribute))
                tempchoosenAttributesValues.add(hm.get(bestAttribute));
        }

        LinkedList chosenAttribute = new LinkedList(tempchoosenAttributesValues);

        LinkedList<MyTree> childTree = new LinkedList<>();
        if(bestAttribute==null){
            int resultIndex = ((HashMap)(mainList.get(0))).size() - 1;
            if (resultIndex > 0) {

                LinkedList<LinkedList> mainList1 = (new MapToList()).mapToList(mainList);
                new Entropy(mainList1, resultIndex);

                String nextBestAttribute = Gain.bestAttribute;

                if (Gain.rootEntropy.equals(0.0)) {
                    tree = new MyTree(Entropy.classtype.get(0), "_root_");
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
        else{
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

                    if (Gain.rootEntropy.equals(0.0)) {
                        nodes = new MyTree(Entropy.classtype.get(0), chosenAttributeString);
                        nodes.setLeaf();
                    }
                    else {
                        nodes = new BuildTree().buildSubTree(newMainList, nextBestAttribute);
                        nodes.setEdge(chosenAttributeString);
                    }
                }
                else if (resultIndex == 0) {
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

