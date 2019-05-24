package com.hisun.BP;

public class BPCFBTAQ {
    public BPCFBTAQ_RC RC = new BPCFBTAQ_RC();
    public char OP_CODE = ' ';
    public String TLR = " ";
    public int BR = 0;
    public int CNT = 0;
    public BPCFBTAQ_ARRAY[] ARRAY = new BPCFBTAQ_ARRAY[300];
    public BPCFBTAQ() {
        for (int i=0;i<300;i++) ARRAY[i] = new BPCFBTAQ_ARRAY();
    }
}
