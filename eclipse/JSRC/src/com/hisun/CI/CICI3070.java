package com.hisun.CI;

public class CICI3070 {
    public char FUNC = ' ';
    public String TYPE = " ";
    public String OBJ_TYP = " ";
    public String LC_OBJ = " ";
    public String CUS_AC = " ";
    public String OOBJ_TYP = " ";
    public String OLC_OBJ = " ";
    public String TX_TYPE = " ";
    public int LC_NO = 0;
    public int SEQ = 0;
    public String CON_TYP = " ";
    public String LVL_TYP = " ";
    public short LVL = 0;
    public String LC_CCY = " ";
    public int DAY_STA = 0;
    public int DAY_END = 0;
    public double TXN_AMT = 0;
    public double DLY_AMT = 0;
    public int DLY_VOL = 0;
    public double MLY_AMT = 0;
    public int MLY_VOL = 0;
    public double SYY_AMT = 0;
    public double YLY_AMT = 0;
    public double TM_AMT = 0;
    public double SE_AMT = 0;
    public double LMT_AMT1 = 0;
    public double LMT_AMT2 = 0;
    public double LMT_AMT3 = 0;
    public double LMT_AMT4 = 0;
    public char REL_TYP = ' ';
    public double BAL_AMT = 0;
    public char BAL_TYP = ' ';
    public String LMT_STSW = " ";
    public CICI3070_TIMES50[] TIMES50 = new CICI3070_TIMES50[50];
    public CICI3070() {
        for (int i=0;i<50;i++) TIMES50[i] = new CICI3070_TIMES50();
    }
}