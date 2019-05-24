package com.hisun.BP;

public class BPCUFCAL_DATA {
    public String TX_AC = " ";
    public String OTHER_AC = " ";
    public short FEE_NO = 0;
    public String REG_CODE = " ";
    public int ACCT_CENTRE = 0;
    public String CHNL_NO = " ";
    public String FREE_FMT = " ";
    public char EXG_FLG = ' ';
    public String EXG_GROUP = " ";
    public String CNT_CCY = " ";
    public double TX_AMT = 0;
    public short TX_CNT = 0;
    public char CHG_FLG = ' ';
    public String CHG_CCY = " ";
    public String FEE_CCY = " ";
    public double FEE_AMT = 0;
    public double CHG_AMT = 0;
    public int EXG_DATE = 0;
    public int EXG_TIME = 0;
    public String EXT_APP = " ";
    public int POINT_LEN = 0;
    public Object POINTER;
    public char FROM_FLG = ' ';
    public String FEE_CODE = " ";
    public char CAL_TYPE = ' ';
    public char CAL_SOURCE = ' ';
    public char CAL_CYC = ' ';
    public short CYC_NUM = 0;
    public char AGR_TYPE = ' ';
    public char REFER_MTH = ' ';
    public char PRICE_MTH = ' ';
    public char AGGR_TYPE = ' ';
    public String REF_CCY = " ";
    public BPCUFCAL_FEE_DATA[] FEE_DATA = new BPCUFCAL_FEE_DATA[5];
    public String INT_BAS = " ";
    public String REL_CTRT_NO = " ";
    public String R_CT_TYP = " ";
    public String R_PR_TYP = " ";
    public short AMT_TYPE = 0;
    public double MULTI = 0;
    public int START_DATE = 0;
    public int END_DATE = 0;
    public String CI_NO = " ";
    public String REL_TX = " ";
    public BPCUFCAL_DATA() {
        for (int i=0;i<5;i++) FEE_DATA[i] = new BPCUFCAL_FEE_DATA();
    }
}
