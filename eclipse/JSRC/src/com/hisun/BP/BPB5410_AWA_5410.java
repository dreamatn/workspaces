package com.hisun.BP;

public class BPB5410_AWA_5410 {
    public char FUNC_CD = ' ';
    public short FUNC_CD_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String B_TYPE = " ";
    public short B_TYPE_NO = 0;
    public short TENOR = 0;
    public short TENOR_NO = 0;
    public char EFF_FLG = ' ';
    public short EFF_FLG_NO = 0;
    public char DEL_FLG = ' ';
    public short DEL_FLG_NO = 0;
    public BPB5410_TEN_TBL[] TEN_TBL = new BPB5410_TEN_TBL[20];
    public BPB5410_AWA_5410() {
        for (int i=0;i<20;i++) TEN_TBL[i] = new BPB5410_TEN_TBL();
    }
}
