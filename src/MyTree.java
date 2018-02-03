import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTree<T> {

    private T data;
    private T edge;
    private LinkedList<MyTree<T>> children;
    private MyTree<T> parent;

    public MyTree() {
        super();
        children = new LinkedList<MyTree<T>>();
    }

    public MyTree(T data,T edge) {
        this();
        setData(data,edge);
    }

    public MyTree<T> getParent() {
        return this.parent;
    }

    public LinkedList<MyTree<T>> getChildren() {
        return this.children;
    }

    public int getNumberOfChildren() {
        return getChildren().size();
    }

    public boolean hasChildren() {
        return (getNumberOfChildren() > 0);
    }

    public void setChildren(LinkedList<MyTree<T>> children) {
        for(MyTree<T> child : children) {
            child.parent = this;
        }

        this.children = children;
    }

    public void addChild(MyTree<T> child) {
        child.parent = this;
        children.add(child);
    }

    public void addChildAt(int index, MyTree<T> child) throws IndexOutOfBoundsException {
        child.parent = this;
        children.add(index, child);
    }

    public void removeChildren() {
        this.children = new LinkedList<MyTree<T>>();
    }

    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

    public MyTree<T> getChildAt(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data,T edge) {
        this.data = data;
        this.edge = edge;
    }

    public String toString() {
        return getData().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MyTree<?> other = (MyTree<?>) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }

    public String toStringVerbose() {
        String stringRepresentation = getData().toString() + ":[";

        for (MyTree<T> node : getChildren()) {
            stringRepresentation += node.getData().toString() + ", ";
        }

        //Pattern.DOTALL causes ^ and $ to match. Otherwise it won't. It's retarded.
        Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringRepresentation);

        stringRepresentation = matcher.replaceFirst("");
        stringRepresentation += "]";

        return stringRepresentation;
    }
}