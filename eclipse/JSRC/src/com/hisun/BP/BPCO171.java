package com.hisun.BP;

public class BPCO171 {
    public int BR = 0;
    public String TLR = " ";
    public int NO_CNT = 0;
    public BPCO171_BV_DATA[] BV_DATA = new BPCO171_BV_DATA[20];
    public int TOTAL_NUM = 0;
    public BPCO171() {
        for (int i=0;i<20;i++) BV_DATA[i] = new BPCO171_BV_DATA();
    }
}