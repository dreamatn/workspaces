package com.hisun.BP;

public class BPCPORUP_DATA_INFO {
    public String BNK = " ";
    public int BR = 0;
    public char LVL = ' ';
    public int CNT = 0;
    public BPCPORUP_SUPR_GRP[] SUPR_GRP = new BPCPORUP_SUPR_GRP[20];
    public char ATTR = ' ';
    public String TYP = " ";
    public String ABBR = " ";
    public String FX_BUSI = " ";
    public long CNAP_NO = 0;
    public char ACCT_FLG = ' ';
    public String CALD_CD = " ";
    public int BBR = 0;
    public BPCPORUP_DATA_INFO() {
        for (int i=0;i<20;i++) SUPR_GRP[i] = new BPCPORUP_SUPR_GRP();
    }
}
