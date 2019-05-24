package com.hisun.BP;

public class BPB1110_AWA_1110 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String SVR_NO = " ";
    public short SVR_NO_NO = 0;
    public int EFF_DT = 0;
    public short EFF_DT_NO = 0;
    public int EXP_DT = 0;
    public short EXP_DT_NO = 0;
    public char AUT_FLG = ' ';
    public short AUT_FLG_NO = 0;
    public char DMCR_FLG = ' ';
    public short DMCR_FLG_NO = 0;
    public char MAT_FLG = ' ';
    public short MAT_FLG_NO = 0;
    public BPB1110_FEE_INFO[] FEE_INFO = new BPB1110_FEE_INFO[20];
    public BPB1110_AWA_1110() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPB1110_FEE_INFO();
    }
}
