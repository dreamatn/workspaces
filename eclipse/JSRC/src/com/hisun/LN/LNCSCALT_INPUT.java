package com.hisun.LN;

public class LNCSCALT_INPUT {
    public char CAL_PERD_UNIT = ' ';
    public short CAL_PERD = 0;
    public int VAL_DT = 0;
    public int DUE_DT = 0;
    public int CAL_FST_DT = 0;
    public short CAL_PAY_DAY = 0;
    public char CAL_FST_FLG = ' ';
    public char PHS_FLG = ' ';
    public short PHS_NUM = 0;
    public LNCSCALT_PHS_DATA[] PHS_DATA = new LNCSCALT_PHS_DATA[5];
    public char REPY_MTH = ' ';
    public char PROD_MOD = ' ';
    public LNCSCALT_INPUT() {
        for (int i=0;i<5;i++) PHS_DATA[i] = new LNCSCALT_PHS_DATA();
    }
}
