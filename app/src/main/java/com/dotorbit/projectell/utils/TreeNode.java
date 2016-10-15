package com.dotorbit.projectell.utils;

import java.util.ArrayList;

/**
 * Created by sunit on 15/10/16.
 */

public class TreeNode<Type> {

    private Type node;
    private TreeNode parent;
    private ArrayList childrens;

   public TreeNode(){
       this.node= null;
       this.parent= null;
       this.childrens= new ArrayList<TreeNode>();
    }

    public TreeNode(Type node,TreeNode<Type> parent, ArrayList<TreeNode> childrens){
        this.node = node;
        this.parent = parent;
        this.childrens = childrens;
    }

    public Type getNode() {
        return node;
    }

    public void setNode(Type node) {
        this.node = node;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public ArrayList<TreeNode> getChildrens() {
        return childrens;
    }

    public void setChildrens(ArrayList<TreeNode> childrens) {
        this.childrens = childrens;
    }
}