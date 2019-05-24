package com.hisun.BP;

public class BPCOLIBM {
    public BPCOLIBM_RC RC = new BPCOLIBM_RC();
    public char FUNC = ' ';
    public String OUTPUT_FMT = " ";
    public int BR = 0;
    public String PLBOX_NO = " ";
    public char PLBOX_TP = ' ';
    public String TLR = " ";
    public String UP_PBNO = " ";
    public String INSR_CCY = " ";
    public double INSR_AMT = 0;
    public String BLMT_CCY = " ";
    public double BLMT_U = 0;
    public double BLMT_L = 0;
    public BPCOLIBM_CCYS[] CCYS = new BPCOLIBM_CCYS[20];
    public int UPT_DT = 0;
    public String UPT_TLR = " ";
    public char BIND_TYP = ' ';
    public String TX_TYP_CD = " ";
    public BPCOLIBM() {
        for (int i=0;i<20;i++) CCYS[i] = new BPCOLIBM_CCYS();
    }
}
