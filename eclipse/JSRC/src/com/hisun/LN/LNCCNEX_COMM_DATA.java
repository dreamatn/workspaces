package com.hisun.LN;

public class LNCCNEX_COMM_DATA {
    public String INQ_CODE = " ";
    public String LN_AC = " ";
    public String SUF_NO = " ";
    public double INQ_AMT = 0;
    public LNCCNEX_CTNR_DATA[] CTNR_DATA = new LNCCNEX_CTNR_DATA[10];
    public LNCCNEX_COMM_DATA() {
        for (int i=0;i<10;i++) CTNR_DATA[i] = new LNCCNEX_CTNR_DATA();
    }
}
