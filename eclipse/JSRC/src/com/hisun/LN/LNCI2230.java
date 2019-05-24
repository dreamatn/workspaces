package com.hisun.LN;

public class LNCI2230 {
    public String CTA = " ";
    public int BR = 0;
    public String CI_NO = " ";
    public String CI_CNM = " ";
    public String PROD_CD = " ";
    public String PROD_DE = " ";
    public String CCY = " ";
    public double PRINCIPA = 0;
    public double BALANCE = 0;
    public int TR_VAL_D = 0;
    public double TOT_P_AM = 0;
    public char INT_MODE = ' ';
    public double PC_RATE = 0;
    public double TOT_I_AM = 0;
    public double PC_AMT = 0;
    public double TOT_AMT = 0;
    public double HRG_AMT = 0;
    public double SUN_AMT = 0;
    public char SYN_FLG = ' ';
    public short EE_TERM = 0;
    public char INT_RATT = ' ';
    public double INT_IRA = 0;
    public char PEN_RATT = ' ';
    public double PEN_IRA = 0;
    public char CPND_USE = ' ';
    public char CPNDRATT = ' ';
    public double CPND_IRA = 0;
    public String CHK_NO = " ";
    public char CLN_CUT = ' ';
    public String TR_MMO = " ";
    public char MLT_STL = ' ';
    public LNCI2230_STL_DATA[] STL_DATA = new LNCI2230_STL_DATA[5];
    public LNCI2230_FEE_DATA[] FEE_DATA = new LNCI2230_FEE_DATA[5];
    public String FEE_ACT = " ";
    public String FEE_AC = " ";
    public LNCI2230_JOINDATA[] JOINDATA = new LNCI2230_JOINDATA[10];
    public LNCI2230() {
        for (int i=0;i<5;i++) STL_DATA[i] = new LNCI2230_STL_DATA();
        for (int i=0;i<5;i++) FEE_DATA[i] = new LNCI2230_FEE_DATA();
        for (int i=0;i<10;i++) JOINDATA[i] = new LNCI2230_JOINDATA();
    }
}
