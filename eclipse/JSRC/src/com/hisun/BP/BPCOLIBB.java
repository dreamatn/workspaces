package com.hisun.BP;

public class BPCOLIBB {
    public BPCOLIBB_RC RC = new BPCOLIBB_RC();
    public char FUNC = ' ';
    public char MODIFY_STS = ' ';
    public String OUTPUT_FMT = " ";
    public int APP_NO = 0;
    public int APP_BR = 0;
    public int UP_BR = 0;
    public String UP_TLR = " ";
    public char APP_TYPE = ' ';
    public char APP_STS = ' ';
    public int BEG_DT = 0;
    public int END_DT = 0;
    public char ALLOT_TP = ' ';
    public int APP_DT = 0;
    public int CONF_SEQ = 0;
    public String CCY = " ";
    public String CASH_TYP = " ";
    public double APP_AMT = 0;
    public char FILLER33 = '01';
    public double OUT_AMT = 0;
    public char FILLER35 = '01';
    public BPCOLIBB_PVAL_INFO[] PVAL_INFO = new BPCOLIBB_PVAL_INFO[20];
    public int MOV_DT = 0;
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public char FLG = ' ';
    public int SEQ = 0;
    public int CNT = 0;
    public String APP_NOTE = " ";
    public BPCOLIBB() {
        for (int i=0;i<20;i++) PVAL_INFO[i] = new BPCOLIBB_PVAL_INFO();
    }
}
