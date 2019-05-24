package com.hisun.LN;

public class LNCCNBD_COMM_DATA {
    public String LN_AC = " ";
    public String SUF_NO = " ";
    public LNCCNBD_CNTL_FLG[] CNTL_FLG = new LNCCNBD_CNTL_FLG[99];
    public LNCCNBD_COMM_DATA() {
        for (int i=0;i<99;i++) CNTL_FLG[i] = new LNCCNBD_CNTL_FLG();
    }
}
