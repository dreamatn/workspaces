package com.hisun.BP;

public class BPCMBDA_INP_DATA {
    public int BR = 0;
    public String CCY = " ";
    public String CORR_CCY = " ";
    public char CMP_FLG = ' ';
    public BPCMBDA_AUTH_SET[] AUTH_SET = new BPCMBDA_AUTH_SET[10];
    public BPCMBDA_INP_DATA() {
        for (int i=0;i<10;i++) AUTH_SET[i] = new BPCMBDA_AUTH_SET();
    }
}
