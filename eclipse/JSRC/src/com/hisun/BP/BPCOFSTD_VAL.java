package com.hisun.BP;

public class BPCOFSTD_VAL {
    public double START_AMT = 0;
    public double FIX_AMT = 0;
    public String FEE_CCY = " ";
    public double MIN_AMT = 0;
    public double MAX_AMT = 0;
    public char CAL_TYPE = ' ';
    public char CAL_SOURCE = ' ';
    public char CAL_CYC = ' ';
    public short CYC_NUM = 0;
    public char AGR_TYPE = ' ';
    public char PRICE_MTH = ' ';
    public char AGGR_TYPE = ' ';
    public BPCOFSTD_FEE_DATA[] FEE_DATA = new BPCOFSTD_FEE_DATA[10];
    public String TXT = " ";
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCOFSTD_VAL() {
        for (int i=0;i<10;i++) FEE_DATA[i] = new BPCOFSTD_FEE_DATA();
    }
}
