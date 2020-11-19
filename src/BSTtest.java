public class BSTtest {
    public static void main(String[] args)
    {
        BST<Integer> tree = new BST<Integer>();

        tree.insert(10);
        tree.insert(5);
        tree.insert(18);
        tree.insert(7);
        tree.insert(3);
        tree.print();
        System.out.println("Sum of nodes within range (3,10): " + tree.rangeSum(3, 10));
    }
}
