package com.hisun.BP;

public class BPZFSSTD_MPOUT_DATA {
    String MPOUT_FEE_CD = " ";
    String MPOUT_FEE_DESC = " ";
    char BPZFSSTD_FILLER6 = 0X02;
    String MPOUT_REG_CODE = " ";
    String MPOUT_CHN_NO = " ";
    String MPOUT_FREE_FMT = " ";
    String MPOUT_REF_CCY = " ";
    double MPOUT_START_AMT = 0;
    double MPOUT_FIX_AMT = 0;
    String MPOUT_FEE_CCY = " ";
    double MPOUT_MIN_AMT = 0;
    double MPOUT_MAX_AMT = 0;
    char MPOUT_CAL_TYPE = ' ';
    char MPOUT_CAL_SOURCE = ' ';
    char MPOUT_CAL_CYC = ' ';
    short MPOUT_CYC_NUM = 0;
    char MPOUT_AGR_TYPE = ' ';
    char MPOUT_PRICE_MTH = ' ';
    char MPOUT_AGGR_TYPE = ' ';
    int MPOUT_EFF_DATE = 0;
    int MPOUT_EXP_DATE = 0;
    BPZFSSTD_MPOUT_FEE_DATA[] MPOUT_FEE_DATA = new BPZFSSTD_MPOUT_FEE_DATA[5];
    public BPZFSSTD_MPOUT_DATA() {
        for (int i=0;i<5;i++) MPOUT_FEE_DATA[i] = new BPZFSSTD_MPOUT_FEE_DATA();
    }
}
