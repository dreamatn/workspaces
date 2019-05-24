package com.hisun.VT;

public class VTCI7011 {
    public short EVT_CNT = 0;
    public VTCI7011_ARRAY[] ARRAY = new VTCI7011_ARRAY[10];
    public VTCI7011() {
        for (int i=0;i<10;i++) ARRAY[i] = new VTCI7011_ARRAY();
    }
}
