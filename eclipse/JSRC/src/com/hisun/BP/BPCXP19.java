package com.hisun.BP;

public class BPCXP19 {
    public char FUNC = ' ';
    public String TYPE = " ";
    public String CODE = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public char FLAG = ' ';
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public short VAL_LEN = 0;
    public BPCXP19_FILE_TBL[] FILE_TBL = new BPCXP19_FILE_TBL[15];
    public BPCXP19() {
        for (int i=0;i<15;i++) FILE_TBL[i] = new BPCXP19_FILE_TBL();
    }
}
