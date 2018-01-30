import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class MapToList {

    public LinkedList<LinkedList> mapToList(LinkedList<HashMap<String,String>> list){
        LinkedList<LinkedList> listList = new LinkedList<>();
        ListIterator ll = list.listIterator();
        HashMap<String,String> header = (HashMap)ll.next();
        listList.add(new LinkedList(header.keySet()));
        ll = list.listIterator();

        while(ll.hasNext()){
            HashMap<String,String> map = (HashMap)ll.next();
            LinkedList innerList = new LinkedList();
            for(String values : map.keySet()){
                innerList.add(map.get(values));
            }
            listList.add(innerList);
        }
        return listList;
    }
}
