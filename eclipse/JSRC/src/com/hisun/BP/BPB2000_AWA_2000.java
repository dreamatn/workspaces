package com.hisun.BP;

public class BPB2000_AWA_2000 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String TLR = " ";
    public short TLR_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public char FLAG = ' ';
    public short FLAG_NO = 0;
    public BPB2000_CCYS[] CCYS = new BPB2000_CCYS[20];
    public String PLBOX_NO = " ";
    public short PLBOX_NO_NO = 0;
    public String BLMT_CCY = " ";
    public short BLMT_CCY_NO = 0;
    public String UP_PBNO = " ";
    public short UP_PBNO_NO = 0;
    public String INSR_CCY = " ";
    public short INSR_CCY_NO = 0;
    public char PLBOX_TP = ' ';
    public short PLBOX_TP_NO = 0;
    public double INSR_AMT = 0;
    public short INSR_AMT_NO = 0;
    public double BLMT_U = 0;
    public short BLMT_U_NO = 0;
    public double BLMT_L = 0;
    public short BLMT_L_NO = 0;
    public char BIND_TYP = ' ';
    public short BIND_TYP_NO = 0;
    public BPB2000_AWA_2000() {
        for (int i=0;i<20;i++) CCYS[i] = new BPB2000_CCYS();
    }
}
