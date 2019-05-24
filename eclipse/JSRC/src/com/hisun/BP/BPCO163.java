package com.hisun.BP;

public class BPCO163 {
    public String GIV_TLR = " ";
    public String RCV_TLR = " ";
    public String PB_NO = " ";
    public BPCO163_BV_INFO[] BV_INFO = new BPCO163_BV_INFO[99];
    public BPCO163() {
        for (int i=0;i<99;i++) BV_INFO[i] = new BPCO163_BV_INFO();
    }
}
