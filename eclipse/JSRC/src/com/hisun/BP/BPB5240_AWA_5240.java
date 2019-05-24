package com.hisun.BP;

public class BPB5240_AWA_5240 {
    public String RATE_TYP = " ";
    public short RATE_TYP_NO = 0;
    public String TENOR = " ";
    public short TENOR_NO = 0;
    public BPB5240_BRT_TBL[] BRT_TBL = new BPB5240_BRT_TBL[40];
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public BPB5240_AWA_5240() {
        for (int i=0;i<40;i++) BRT_TBL[i] = new BPB5240_BRT_TBL();
    }
}
