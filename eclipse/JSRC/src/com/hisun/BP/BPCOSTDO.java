package com.hisun.BP;

public class BPCOSTDO {
    public String FEE_CD = " ";
    public String FEE_DESC = " ";
    public char FILLER3 = 0X02;
    public String REG_CODE = " ";
    public String CHN_NO = " ";
    public String FREE_FMT = " ";
    public String REF_CCY = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public double START_AMT = 0;
    public char FILLER11 = 0X01;
    public double FIX_AMT = 0;
    public char FILLER13 = 0X01;
    public String FEE_CCY = " ";
    public double MIN_AMT = 0;
    public char FILLER16 = 0X01;
    public double MAX_AMT = 0;
    public char FILLER18 = 0X01;
    public char CAL_TYPE = ' ';
    public char CAL_SOURCE = ' ';
    public char CAL_CYC = ' ';
    public short CYC_NUM = 0;
    public char AGR_TYPE = ' ';
    public char PRICE_MTH = ' ';
    public char AGGR_TYPE = ' ';
    public String TXT = " ";
    public BPCOSTDO_FEE_DATA[] FEE_DATA = new BPCOSTDO_FEE_DATA[10];
    public BPCOSTDO() {
        for (int i=0;i<10;i++) FEE_DATA[i] = new BPCOSTDO_FEE_DATA();
    }
}
