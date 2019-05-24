package com.hisun.BP;

public class BPC3130 {
    public char VB_FLG = ' ';
    public BPC3130_BV_INFO[] BV_INFO = new BPC3130_BV_INFO[99];
    public char CK_TYP = ' ';
    public String REP_TLR = " ";
    public char CHK_TYP = ' ';
    public String TX_CODE = " ";
    public String INVNTY = " ";
    public char STS = ' ';
    public String PLBOX_NO = " ";
    public String HANDLER = " ";
    public String INTY_NM = " ";
    public String HLD_NM = " ";
    public BPC3130() {
        for (int i=0;i<99;i++) BV_INFO[i] = new BPC3130_BV_INFO();
    }
}
