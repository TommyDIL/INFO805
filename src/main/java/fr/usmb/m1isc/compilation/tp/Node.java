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

    private Boolean isLeaf() {
        return (left == null) && (right == null);
    }

    public String getElement() {
        return (String) element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Object getLeft() {
        return left;
    }

    public void setLeft(Object left) {
        this.left = (Node) left;
    }

    public Object getRight() {
        return right;
    }

    public void setRight(Object right) {
        this.right = (Node) right;
    }
}
