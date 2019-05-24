package com.hisun.BP;

public class BPVFSTD1_VAL {
    public double MIN_AMT = 0;
    public double MAX_AMT = 0;
    public double START_AMT = 0;
    public String FEE_CCY = " ";
    public char CAL_TYPE = ' ';
    public char CAL_SOURCE = ' ';
    public char CAL_CYC = ' ';
    public short CYC_NUM = 0;
    public char AGR_TYPE = ' ';
    public char AGGR_TYPE = ' ';
    public char REFER_MTH = ' ';
    public char PRICE_MTH = ' ';
    public BPVFSTD1_FEE_DATA[] FEE_DATA = new BPVFSTD1_FEE_DATA[5];
    public int CREATE_DATE = 0;
    public String CREATE_TELL = " ";
    public int UPDTBL_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPVFSTD1_VAL() {
        for (int i=0;i<5;i++) FEE_DATA[i] = new BPVFSTD1_FEE_DATA();
    }
}
