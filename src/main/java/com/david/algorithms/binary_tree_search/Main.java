package com.david.algorithms.binary_tree_search;

public class Main {

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        tree.add(6);
        tree.add(4);
        tree.add(8);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);

        System.out.println("¿Contiene 5? " + tree.containsNode(5)); // true
        System.out.println("¿Contiene 10? " + tree.containsNode(10)); // false

        System.out.println("Recorrido en orden:");
        tree.traverseInOrder(tree.root); // 3 4 5 6 7 8 9
        System.out.println();
        
        tree.delete(4);
        System.out.println("Recorrido en orden después de eliminar 4:");
        tree.traverseInOrder(tree.root); // 3 5 6 7 8 9
        System.out.println();

    }

}
