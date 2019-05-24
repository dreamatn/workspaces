package com.hisun.FS;

public class FSCOQCSH {
    public short ACCTS_NUM = 0;
    public FSCOQCSH_INQ_ARRAY[] INQ_ARRAY = new FSCOQCSH_INQ_ARRAY[20];
    public FSCOQCSH() {
        for (int i=0;i<20;i++) INQ_ARRAY[i] = new FSCOQCSH_INQ_ARRAY();
    }
}
