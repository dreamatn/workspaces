package com.hisun.BP;

public class BPCFTBVC {
    public BPCFTBVC_RC RC = new BPCFTBVC_RC();
    public char BV_FLG = ' ';
    public char VB_FLG = ' ';
    public int IN_CNT = 0;
    public int RT_CNT = 0;
    public BPCFTBVC_INFO[] INFO = new BPCFTBVC_INFO[99];
    public char CK_TYP = ' ';
    public String REP_TLR = " ";
    public BPCFTBVC() {
        for (int i=0;i<99;i++) INFO[i] = new BPCFTBVC_INFO();
    }
}
