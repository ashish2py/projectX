package com.developerbyweekend.bunker.utils;

/**
 * Created by sunit on 15/10/16.
 */

/**
 * Class to represent Tree
 */
public class Tree {

    private TreeNode rootnode;

    public Tree(TreeNode rootnode) {
        this.rootnode = rootnode;
    }

    public Tree() throws Exception {
        throw new Exception("Tree must have a root node");
    }

    public TreeNode getRootnode() {
        return rootnode;
    }
}
