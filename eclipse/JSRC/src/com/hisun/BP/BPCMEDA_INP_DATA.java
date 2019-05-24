package com.hisun.BP;

public class BPCMEDA_INP_DATA {
    public String EXR_TYP = " ";
    public String CCY = " ";
    public char CMP_FLG = ' ';
    public String TENOR = " ";
    public BPCMEDA_AUTH_SET[] AUTH_SET = new BPCMEDA_AUTH_SET[10];
    public BPCMEDA_INP_DATA() {
        for (int i=0;i<10;i++) AUTH_SET[i] = new BPCMEDA_AUTH_SET();
    }
}
