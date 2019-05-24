package com.hisun.BP;

public class BPB5220_AWA_5220 {
    public String UOD_BK = " ";
    public short UOD_BK_NO = 0;
    public int UOD_BR = 0;
    public short UOD_BR_NO = 0;
    public int EFF_DT = 0;
    public short EFF_DT_NO = 0;
    public int EXP_DT = 0;
    public short EXP_DT_NO = 0;
    public BPB5220_UOD_AMT[] UOD_AMT = new BPB5220_UOD_AMT[10];
    public int UPT_DT = 0;
    public short UPT_DT_NO = 0;
    public String UPT_TLR = " ";
    public short UPT_TLR_NO = 0;
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public BPB5220_AWA_5220() {
        for (int i=0;i<10;i++) UOD_AMT[i] = new BPB5220_UOD_AMT();
    }
}
