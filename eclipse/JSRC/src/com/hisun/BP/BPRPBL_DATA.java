package com.hisun.BP;

public class BPRPBL_DATA {
    public String ITM_PNT = " ";
    public String ITM_NO = " ";
    public int ITM_SEQ = 0;
    public BPRPBL_AMT_PNT[] AMT_PNT = new BPRPBL_AMT_PNT[76];
    public String REMARK = " ";
    public BPRPBL_DATA() {
        for (int i=0;i<76;i++) AMT_PNT[i] = new BPRPBL_AMT_PNT();
    }
}
