package com.hisun.ibscore.core.domain;

import java.util.HashMap;

public class IbsMemoryTree {
    private HashMap<String,HashMap<String,Object>>  memoryTree;

    public HashMap <String, HashMap <String, Object>> getMemoryTree() {
        return memoryTree;
    }

    public void setMemoryTree(HashMap <String, HashMap <String, Object>> memoryTree) {
        this.memoryTree = memoryTree;
    }

    @Override
    public String toString() {
        return "IbsMemoryTree{" +
                "memoryTree=" + memoryTree +
                '}';
    }
}
