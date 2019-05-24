package com.hisun.BP;

public class BPCO168 {
    public int CNT = 0;
    public char VB_FLG = ' ';
    public BPCO168_INFO[] INFO = new BPCO168_INFO[99];
    public char CK_TYP = ' ';
    public String REP_TLR = " ";
    public BPCO168() {
        for (int i=0;i<99;i++) INFO[i] = new BPCO168_INFO();
    }
}
