package com.hisun.BP;

public class BPCHPBL {
    public String CNTR_TYPE = " ";
    public String PROD_TYPE = " ";
    public int ACCT_CTR = 0;
    public String BOOK_FLG = " ";
    public String ITM_PNT = " ";
    public String ITM_NO = " ";
    public int ITM_SEQ = 0;
    public BPCHPBL_AMT_PNT[] AMT_PNT = new BPCHPBL_AMT_PNT[76];
    public String REMARK = " ";
    public BPCHPBL() {
        for (int i=0;i<76;i++) AMT_PNT[i] = new BPCHPBL_AMT_PNT();
    }
}
