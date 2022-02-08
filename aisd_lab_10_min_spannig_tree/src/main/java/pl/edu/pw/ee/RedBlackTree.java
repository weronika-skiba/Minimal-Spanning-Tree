package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;

    private Node<K, V> nil;
    private boolean isGraphConnectedPrim;
    private boolean isGraphConnectedKruskal;

    public RedBlackTree() {
        nil = null;
        isGraphConnectedPrim = true;
        isGraphConnectedKruskal = true;
    }

    public PrimVertix getRoot() {
        return (PrimVertix) root.getValue();
    }

    public boolean checkConnectionPrim() {
        isGraphConnectedPrim = true;
        Node<K, V> node = root;
        if (node == nil)
            return true;
        checkConnectionPrim(node);
        return isGraphConnectedPrim;
    }

    private void checkConnectionPrim(Node<K, V> node) {
        if (node == nil)
            return;
        if (!(((PrimVertix) node.getValue()).getVisited()))
            isGraphConnectedPrim = false;
        checkConnectionPrim(node.getLeft());
        checkConnectionPrim(node.getRight());
    }

    public boolean checkConnectionKruskal() {
        isGraphConnectedKruskal = true;
        Node<K, V> node = root;
        if (node == nil)
            return true;
        checkConnectionKruskal(node, node.getValue());
        return isGraphConnectedKruskal;
    }

    private void checkConnectionKruskal(Node<K, V> node, V id) {
        if (node == nil || id == null)
            return;
        if (!((node.getValue()).equals(id)))
            isGraphConnectedKruskal = false;
        checkConnectionKruskal(node.getLeft(), id);
        checkConnectionKruskal(node.getRight(), id);
    }

    public void updateIds(V firstId, V secondId) {
        if (firstId == null || secondId == null)
            throw new IllegalArgumentException("Input values cannot be null.");
        Node<K, V> node = root;
        updateIds(node, firstId, secondId);
    }

    private void updateIds(Node<K, V> node, V firstId, V secondId) {
        if (node == nil)
            return;
        if ((node.getValue()).equals(firstId))
            node.setValue(secondId);
        updateIds(node.getLeft(), firstId, secondId);
        updateIds(node.getRight(), firstId, secondId);
    }

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void put(K key, V value) {
        validateParams(key, value);
        root = put(root, key, value);
        root.setColor(BLACK);
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == nil) {
            return new Node<>(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);
        return head;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == nil
                ? false
                : node.isRed();
    }
}
