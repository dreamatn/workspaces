package com.hisun.BP;

public class BPB0003_AWA_0003 {
    public short ALLNUM = 0;
    public short ALLNUM_NO = 0;
    public short CURRNUM = 0;
    public short CURRNUM_NO = 0;
    public String ORIGIN = " ";
    public short ORIGIN_NO = 0;
    public BPB0003_RATE_VAL[] RATE_VAL = new BPB0003_RATE_VAL[99];
    public BPB0003_AWA_0003() {
        for (int i=0;i<99;i++) RATE_VAL[i] = new BPB0003_RATE_VAL();
    }
}
