package org.service;

import java.util.LinkedList;
import java.util.List;

public class Node<T> implements Comparable{

    private int dirFileSize;
    private LinkedList<Node<T>> children;
    private Node<T> parent;
    private String name;

    public Node(Node<T> parent, String name){
        this.children  = new LinkedList<>();
        this.parent = parent;
        this.dirFileSize = 0;
        this.name = name;
    }
    public void addSubDir(Node<T> n) {
        this.children.add(n);
    }
    public void addFile(int size) {this.dirFileSize+=size;}
    public int getDirSize() {
        int res = 0;
        for(Node<T> n:this.children) {
            res+=n.getDirSize();
        }
        res+=this.dirFileSize;
        return res;
    }
    public List<Node<T>> getChildren(){return this.children;}
    public Node getParent() {return this.parent;}
    public void printNode(int i) {
        System.out.println(this.toString(i));
        children.forEach(n->n.printNode(i+1));
    }
    public String toString(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append("----".repeat(Math.max(0, depth)));
        sb.append(this.name);
        sb.append(" with a size of: ").append(getDirSize());
        sb.append(System.lineSeparator());
        return sb.toString();
    }
    @Override
    public int compareTo(Object o) {
        Node other = (Node) o;
        if((this.getDirSize())<(other.getDirSize()))
            return -1;
        else if((this.getDirSize())>(other.getDirSize()))
            return 1;
        else
            return 0;
    }
}