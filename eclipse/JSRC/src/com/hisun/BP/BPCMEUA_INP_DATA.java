package com.hisun.BP;

public class BPCMEUA_INP_DATA {
    public int BR = 0;
    public String CCY = " ";
    public char CMP_FLG = ' ';
    public BPCMEUA_AUTH_SET[] AUTH_SET = new BPCMEUA_AUTH_SET[10];
    public BPCMEUA_INP_DATA() {
        for (int i=0;i<10;i++) AUTH_SET[i] = new BPCMEUA_AUTH_SET();
    }
}
