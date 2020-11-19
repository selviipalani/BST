import java.util.Stack;

public class BST <T extends Comparable <T>> {
    int sum = 0;
    boolean visited [];
    /*
     * The root of the BST
     */
    Node<T> root;

    /*
     * Node class for a BST
     */
    private class Node<T>
    {
        T data;
        Node<T> left;
        Node<T> right;
        int instance;

        Node(T item)
        {
            data = item;
            instance = 1;
        }
    }

    public BST()
    {
        root = null;
    }

    /*
     * Find function that finds an item in the BST
     * @param item to be found
     * @return boolean if the item was found
     */
    public boolean find(T item)
    {
        return find(item, root);
    }

    /*
     * Function override of the find function
     * @param item to be found
     * @param node the current node you are at
     * @return boolean if the item was found
     */
    private boolean find(T item, Node<T> node)
    {
        if(node == null){
            return false;
        }
        if(item.compareTo(node.data) == 0) {
            return true;
        }
        else if(item.compareTo(node.data) < 0 ) {
            return find(item, node.left);
        }
        else
            return find(item, node.right);

    }

    /*
     * Insert an item to the tree
     * @param item to insert
     */
    public void insert(T item)
    {
        root = insert(item, root);
    }

    /*
     * Helper function for insert
     * @param item to add
     * @param node you are at
     * @return node you traverse to
     */
    private Node<T> insert(T item, Node<T> node)
    {
        if(node == null) {
            return new Node(item);
        }
        else if(item.compareTo(node.data) < 0) {
            node.left =  insert(item, node.left);
        }
        else
           node.right = insert(item,node.right);
        return node;
    }

    /*
     * Function for deletion of a node
     * @param item to delete
     */
    public void delete(T item)
    {
        root = delete(item, root);
    }

    /*
     * Helper function for deletion of a node
     * @param item to delete
     * @param node you are at
     * @return node you traverse to
     */
    private Node<T> delete(T item, Node<T> node)
    {
        if(node == null){
            return null;
        }
        if(item.compareTo(node.data) < 0){
            node.right = delete(item,node.right);
            return node;
        }
        else if(item.compareTo(node.data) > 0){
            node.left = delete(item,node.left);
            return node;
        }
        else{
            if(node.left == null){
                return node.right;
            }else if(node.right == null){
                return node.left;
            }else{
                if(node.right.left == null){
                    node.data = node.right.data;
                    node.right = node.right.right;
                }else{
                    node.data = removeSmallest(node.right);
                }
                return node;
            }
        }

    }

    /**
     * removeSmallest helper function for delete
     * @param node
     * @return leaf
     */
    T removeSmallest(Node<T> node){
        if(node.left.left == null){
            T smallest  = node.left.data;
            node.left = node.left.right;
            return smallest;
        }else{
            return removeSmallest(node.left);
        }
    }

    /*
     * Function to find the range sum of the binary tree
     * @param L the left bound
     * @param R the right bound
     * @return The sum of the range in the binary tree
     */
    public int rangeSum(int L, int R)
    {
        if(rangeSum(root,L,R) != 0){
            return sum;
        }
        return 0;
    }

    private int rangeSum(Node<T> root, int L, int R){
        //create stack
        Stack<Node<T>> stack = new Stack();
        //push root to stack
        stack.push(root);
        //while stack is not empty
        while (!stack.isEmpty()) {
            //pop most recently added item
            Node node = stack.pop();
            //check if its null
            if (node != null) {
                //check if the value of the node lies within the range
                if (L <= (int)node.data && (int)node.data <= R)
                    //if so add that node's value to sum
                    sum += (int)node.data;
                //check if node's value is greater than the left range
                if (L < (int)node.data)
                    //if so push the node to the left onto the stack so its evaluated to see if its data lies within the range in above if statement
                    stack.push(node.left);
                //same as above if statement for opposite case
                if ((int)node.data < R)
                    stack.push(node.right);
            }
        }
        return sum;
    }


    /*
     * Function to print the Binary tree
     */
    public void print()
    {
        print(root);
    }

    /*
     * Helper Function to print the Binary tree
     * @param root the root of the tree
     */
    private void print(Node<T> root)
    {
        if(root != null){
            print(root.left);
            System.out.println(root.data);
            print(root.right);
        }

    }

}
