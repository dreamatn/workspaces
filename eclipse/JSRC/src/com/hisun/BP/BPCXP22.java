package com.hisun.BP;

public class BPCXP22 {
    public char FUNC = ' ';
    public String TYPE = " ";
    public String CODE = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public char FLAG = ' ';
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public short VAL_LEN = 0;
    public int FILE_CNT = 0;
    public BPCXP22_FILE[] FILE = new BPCXP22_FILE[30];
    public BPCXP22() {
        for (int i=0;i<30;i++) FILE[i] = new BPCXP22_FILE();
    }
}
