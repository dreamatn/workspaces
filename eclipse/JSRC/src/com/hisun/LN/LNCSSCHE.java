package com.hisun.LN;

public class LNCSSCHE {
    public LNCSSCHE_DATA_AREA DATA_AREA = new LNCSSCHE_DATA_AREA();
    public LNCSSCHE_OUTPUT_LIST OUTPUT_LIST = new LNCSSCHE_OUTPUT_LIST();
    public int PAGE_ROW = 0;
    public int PAGE_NUM = 0;
    public long SEQ_NO = 0;
    public char CUR_FLG = ' ';
    public char MOD_FLG = ' ';
    public LNCSSCHE_OUT_INFO[] OUT_INFO = new LNCSSCHE_OUT_INFO[20];
    public LNCSSCHE() {
        for (int i=0;i<20;i++) OUT_INFO[i] = new LNCSSCHE_OUT_INFO();
    }
}
