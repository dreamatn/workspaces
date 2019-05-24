package com.hisun.BP;

public class BPCO274 {
    public String EXR_TYP = " ";
    public String CCY = " ";
    public char CMP_FLG = ' ';
    public BPCO274_AUTH_SET[] AUTH_SET = new BPCO274_AUTH_SET[10];
    public BPCO274() {
        for (int i=0;i<10;i++) AUTH_SET[i] = new BPCO274_AUTH_SET();
    }
}
