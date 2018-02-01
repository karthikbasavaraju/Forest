import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class MapToList {

    public LinkedList<LinkedList> mapToList(LinkedList<HashMap<String,String>> list){
        Disp.display("start of maptolist");
        LinkedList<LinkedList> listList = new LinkedList<>();
        ListIterator ll = list.listIterator();
        HashMap<String,String> header = (HashMap)ll.next();
        listList.add(new LinkedList(header.keySet()));
        ll = list.listIterator();

        while(ll.hasNext()){
            HashMap<String,String> map = (HashMap)ll.next();
            //System.out.println(map);
            LinkedList innerList = new LinkedList();
            for(String values : map.keySet()){
                innerList.add(map.get(values));
            }
            listList.add(innerList);
        }
        Disp.display("end of maptolist");
        return listList;
    }
}
