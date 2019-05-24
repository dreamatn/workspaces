package com.hisun.BP;

public class BPCPQPBL_DATA {
    public String ITM_PNT = " ";
    public String ITM_NO = " ";
    public int ITM_SEQ = 0;
    public BPCPQPBL_AMT_PNT[] AMT_PNT = new BPCPQPBL_AMT_PNT[76];
    public String REMARK = " ";
    public BPCPQPBL_DATA() {
        for (int i=0;i<76;i++) AMT_PNT[i] = new BPCPQPBL_AMT_PNT();
    }
}
