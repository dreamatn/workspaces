package com.hisun.LN;

public class LNCBALB_COMM_DATA {
    public String LN_AC = " ";
    public String SUF_NO = " ";
    public String CTNR_NO = " ";
    public int BEG_DATE = 0;
    public int END_DATE = 0;
    public int ITEM_CNT = 0;
    public double PYIF_INT_AMT = 0;
    public LNCBALB_IETM_RATES[] IETM_RATES = new LNCBALB_IETM_RATES[50];
    public LNCBALB_COMM_DATA() {
        for (int i=0;i<50;i++) IETM_RATES[i] = new LNCBALB_IETM_RATES();
    }
}
