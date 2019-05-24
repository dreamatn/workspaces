package com.hisun.BP;

public class BPCO275 {
    public int BR = 0;
    public String CCY = " ";
    public String CORR_CCY = " ";
    public char CMP_FLG = ' ';
    public BPCO275_AUTH_SET[] AUTH_SET = new BPCO275_AUTH_SET[10];
    public BPCO275() {
        for (int i=0;i<10;i++) AUTH_SET[i] = new BPCO275_AUTH_SET();
    }
}
