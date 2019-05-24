package com.hisun.VT;

public class VTCI7012 {
    public short EVT_CNT = 0;
    public VTCI7012_ARRY[] ARRY = new VTCI7012_ARRY[10];
    public VTCI7012() {
        for (int i=0;i<10;i++) ARRY[i] = new VTCI7012_ARRY();
    }
}
