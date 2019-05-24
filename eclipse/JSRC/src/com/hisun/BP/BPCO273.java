package com.hisun.BP;

public class BPCO273 {
    public int BR = 0;
    public String CCY = " ";
    public char CMP_FLG = ' ';
    public BPCO273_AUTH_SET[] AUTH_SET = new BPCO273_AUTH_SET[10];
    public BPCO273() {
        for (int i=0;i<10;i++) AUTH_SET[i] = new BPCO273_AUTH_SET();
    }
}
