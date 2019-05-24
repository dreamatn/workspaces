package com.hisun.BP;

public class BPCOCPHM {
    public char FUNC = ' ';
    public String CTRT_NO = " ";
    public String REL_CTRT_NO = " ";
    public String PRD_TYPE = " ";
    public String FEE_TYP = " ";
    public char PAY_IND = ' ';
    public int VALUE_DATE = 0;
    public char PRICE_MTH = ' ';
    public String INT_BAS = " ";
    public double MULTI = 0;
    public char AGGR_TYPE = ' ';
    public String REF_CCY = " ";
    public char REF_MTH = ' ';
    public BPCOCPHM_RATE_TBL[] RATE_TBL = new BPCOCPHM_RATE_TBL[5];
    public String REMARK = " ";
    public BPCOCPHM() {
        for (int i=0;i<5;i++) RATE_TBL[i] = new BPCOCPHM_RATE_TBL();
    }
}
