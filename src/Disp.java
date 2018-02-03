import java.util.LinkedList;
import java.util.ListIterator;

public class Disp {
    static void display(String position){
        //System.out.println(position);

    }

    void displayNodes(MyTree tree, String space,String padding){
        System.out.print(padding);
        System.out.println("edge = "+tree.getEdge());
        System.out.println(space+"node = "+tree.getAttribute());
        System.out.println(space+"parent = "+tree.getParent());
        LinkedList childTreeList = tree.getChildren();
        padding = padding + "--------";
        space = space + "\t\t";
        ListIterator childTreeListIterator = childTreeList.listIterator();
        while(childTreeListIterator.hasNext()){
            MyTree childTree = (MyTree)childTreeListIterator.next();
            displayNodes(childTree, space, padding);
        }

    }
}
