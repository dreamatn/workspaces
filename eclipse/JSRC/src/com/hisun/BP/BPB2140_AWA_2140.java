package com.hisun.BP;

public class BPB2140_AWA_2140 {
    public char IN_FLAG = ' ';
    public short IN_FLAG_NO = 0;
    public String IN_CCY = " ";
    public short IN_CCY_NO = 0;
    public double IN_D_AMT = 0;
    public short IN_D_AMT_NO = 0;
    public double IN_R_AMT = 0;
    public short IN_R_AMT_NO = 0;
    public double IN_S_AMT = 0;
    public short IN_S_AMT_NO = 0;
    public String IN_CS_TP = " ";
    public short IN_CS_TP_NO = 0;
    public BPB2140_IN_PAR[] IN_PAR = new BPB2140_IN_PAR[12];
    public char OT_FLAG = ' ';
    public short OT_FLAG_NO = 0;
    public String OT_CCY = " ";
    public short OT_CCY_NO = 0;
    public double OT_D_AMT = 0;
    public short OT_D_AMT_NO = 0;
    public double OT_R_AMT = 0;
    public short OT_R_AMT_NO = 0;
    public double OT_S_AMT = 0;
    public short OT_S_AMT_NO = 0;
    public String OT_CS_TP = " ";
    public short OT_CS_TP_NO = 0;
    public BPB2140_OT_PAR[] OT_PAR = new BPB2140_OT_PAR[12];
    public BPB2140_AWA_2140() {
        for (int i=0;i<12;i++) IN_PAR[i] = new BPB2140_IN_PAR();
        for (int i=0;i<12;i++) OT_PAR[i] = new BPB2140_OT_PAR();
    }
}
