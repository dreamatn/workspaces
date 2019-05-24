package com.hisun.BP;

public class BPCFFCAL_DATA {
    public String TX_AC = " ";
    public String OTHER_AC = " ";
    public String FEE_CODE = " ";
    public String REG_CODE = " ";
    public String CHNL_NO = " ";
    public String FREE_FMT = " ";
    public char EXG_TYP = ' ';
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
    public char STD_REF_FLG = ' ';
    public char CAL_TYPE = ' ';
    public char AGGR_TYPE = ' ';
    public BPCFFCAL_FEE_DATA[] FEE_DATA = new BPCFFCAL_FEE_DATA[5];
    public int POINT_LEN = 0;
    public Object POINTER;
    public BPCFFCAL_DATA() {
        for (int i=0;i<5;i++) FEE_DATA[i] = new BPCFFCAL_FEE_DATA();
    }
}
