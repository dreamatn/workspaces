package com.hisun.LN;

public class LNCCMSCH_O_DATA {
    public String O_CONTRACT_NO = " ";
    public long O_TRAN_SEQ = 0;
    public char O_PAY_TYPE = ' ';
    public LNCCMSCH_O_LIST[] O_LIST = new LNCCMSCH_O_LIST[20];
    public LNCCMSCH_O_DATA() {
        for (int i=0;i<20;i++) O_LIST[i] = new LNCCMSCH_O_LIST();
    }
}
