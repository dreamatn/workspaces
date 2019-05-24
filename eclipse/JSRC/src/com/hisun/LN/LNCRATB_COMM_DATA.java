package com.hisun.LN;

public class LNCRATB_COMM_DATA {
    public String LN_AC = " ";
    public String SUF_NO = " ";
    public char RATE_TYPE = ' ';
    public int BEG_DATE = 0;
    public int END_DATE = 0;
    public double ACCR_RATE = 0;
    public double AVER_RATE = 0;
    public int ITEM_CNT = 0;
    public LNCRATB_IETM_RATES[] IETM_RATES = new LNCRATB_IETM_RATES[20];
    public LNCRATB_COMM_DATA() {
        for (int i=0;i<20;i++) IETM_RATES[i] = new LNCRATB_IETM_RATES();
    }
}
