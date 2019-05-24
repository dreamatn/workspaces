package com.hisun.BP;

public class BPCO173 {
    public int BR = 0;
    public String PB_NO = " ";
    public String TLR = " ";
    public char VB_FLG = ' ';
    public BPCO173_VB_INFO[] VB_INFO = new BPCO173_VB_INFO[20];
    public BPCO173() {
        for (int i=0;i<20;i++) VB_INFO[i] = new BPCO173_VB_INFO();
    }
}
