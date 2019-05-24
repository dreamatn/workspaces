package com.hisun.BP;

public class BPCSTBVC {
    public BPCSTBVC_RC RC = new BPCSTBVC_RC();
    public String OUTPUT_FMT = " ";
    public char VB_FLG = ' ';
    public int CNT = 0;
    public BPCSTBVC_INFO[] INFO = new BPCSTBVC_INFO[99];
    public char CK_TYP = ' ';
    public String REP_TLR = " ";
    public BPCSTBVC() {
        for (int i=0;i<99;i++) INFO[i] = new BPCSTBVC_INFO();
    }
}
