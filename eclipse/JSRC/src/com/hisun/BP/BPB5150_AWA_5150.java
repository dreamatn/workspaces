package com.hisun.BP;

public class BPB5150_AWA_5150 {
    public String CCY = " ";
    public short CCY_NO = 0;
    public int EFF_DT = 0;
    public short EFF_DT_NO = 0;
    public int EFF_TM = 0;
    public short EFF_TM_NO = 0;
    public BPB5150_RULE_DAT[] RULE_DAT = new BPB5150_RULE_DAT[20];
    public BPB5150_AWA_5150() {
        for (int i=0;i<20;i++) RULE_DAT[i] = new BPB5150_RULE_DAT();
    }
}
