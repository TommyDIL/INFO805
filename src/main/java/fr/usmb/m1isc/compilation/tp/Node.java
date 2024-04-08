package fr.usmb.m1isc.compilation.tp;

public class Node {
    private Object element;
    private Node left;
    private Node right;

    public Node() {
        this.element = null;
        this.left = null;
        this.right = null;
    }

    /**
     * For a leaf.
     *
     * @param element the element of the leaf.
     */
    public Node(Object element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }


    /**
     * For a branch.
     *
     * @param element the central element.
     * @param left the left child
     * @param right the right child
     */
    public Node(Object element, Object left, Object right) {
        this.element = element;
        this.left = new Node(left);
        this.right = new Node(right);
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        if (element == null) return "";

        if (isLeaf()) return this.element.toString();

        assert left != null;
        assert right != null;

        return "(" + element + " " + left + " " + right+ ")";
    }

    /**
     * Check if a node correspond to a leaf.
     * @return true if the node is a leaf, false if not.
     */
    public Boolean isLeaf() {
//        System.out.println("left: " + this.left);
//        System.out.println("right: " + this.right);

        return (left == null) && (right == null);
    }

    /**
     * Check if a node correspond to a variable.
     * @return true if the node is a variable, false if not.
     */
    public Boolean isVariable() {
        if (element == null) return false;
        if (element instanceof Node) return ((Node) element).isVariable();
//        System.out.println("Current elt: "+element+", Is let: "+ element.equals("LET") + (element instanceof Node));
        if (isLeaf()) return false;
        return element.equals("LET");
    }

    public Object getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Object left) {
        this.left = (Node) left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Object right) {
        this.right = (Node) right;
    }
}
