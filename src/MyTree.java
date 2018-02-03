import java.util.LinkedList;

public class MyTree<String> {

    private String attribute;
    private String edge;
    private LinkedList<MyTree<String>> children;
    private String parent;

    public MyTree() {
        super();
        children = new LinkedList<MyTree<String>>();
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }

    public MyTree(String attribute) {
        this();
        this.attribute = attribute;
        this.edge = null;
    }

    public MyTree(String attribute,String edge) {
        this();
        setData(attribute,edge);
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