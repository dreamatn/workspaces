package com.hisun.BP;

public class BPB2260_AWA_2260 {
    public char PLBOX_TP = ' ';
    public short PLBOX_TP_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public double EXCH_AMT = 0;
    public short EXCH_AMT_NO = 0;
    public String IN_CS_TP = " ";
    public short IN_CS_TP_NO = 0;
    public char IN_CS_KD = ' ';
    public short IN_CS_KD_NO = 0;
    public BPB2260_EXIN_PAR[] EXIN_PAR = new BPB2260_EXIN_PAR[12];
    public String OT_CS_TP = " ";
    public short OT_CS_TP_NO = 0;
    public char OT_CS_KD = ' ';
    public short OT_CS_KD_NO = 0;
    public BPB2260_EXOT_PAR[] EXOT_PAR = new BPB2260_EXOT_PAR[12];
    public BPB2260_AWA_2260() {
        for (int i=0;i<12;i++) EXIN_PAR[i] = new BPB2260_EXIN_PAR();
        for (int i=0;i<12;i++) EXOT_PAR[i] = new BPB2260_EXOT_PAR();
    }
}
