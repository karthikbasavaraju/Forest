import java.util.LinkedList;

public class MyTree<String> {

    private String attribute;                           //Stores attribute of the node
    private String edge;                               //stores attribute values
    private LinkedList<MyTree<String>> children;        // child nodes
    private boolean leaf=false;                        //Indicates the node is leaf or not
    private String parent;

    public MyTree() {
        children = new LinkedList<MyTree<String>>();
    }

    public MyTree(String attribute,String edge) {
        this();
        setData(attribute,edge);
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }

    public  void setLeaf(){
        this.leaf=true;
    }

    public boolean isLeaf() {
        return leaf;
    }



    public String getParent() {
        return this.parent;
    }

    public LinkedList<MyTree<String>> getChildren() {
        return this.children;
    }

    public void setChildren(LinkedList<MyTree<String>> children) {
        for(MyTree<String> child : children) {
            child.parent = this.getAttribute();
        }

        this.children = children;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(String attribute){
        this.attribute = attribute;
    }

    public void setData(String attribute,String edge) {
        this.attribute = attribute;
        this.edge = edge;
    }

    public String getEdge() {
        return edge;
    }
}