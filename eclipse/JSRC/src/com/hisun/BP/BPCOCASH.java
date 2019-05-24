package com.hisun.BP;

public class BPCOCASH {
    public char PLBOX_TYPE = ' ';
    public String CCY = " ";
    public double EXCH_AMT = 0;
    public String IN_CASH_TYPE = " ";
    public char IN_CS_KIND = ' ';
    public BPCOCASH_IN_PAR_INFO[] IN_PAR_INFO = new BPCOCASH_IN_PAR_INFO[12];
    public String OUT_CASH_TYPE = " ";
    public char OUT_CS_KIND = ' ';
    public BPCOCASH_OUT_PAR_INFO[] OUT_PAR_INFO = new BPCOCASH_OUT_PAR_INFO[12];
    public BPCOCASH() {
        for (int i=0;i<12;i++) IN_PAR_INFO[i] = new BPCOCASH_IN_PAR_INFO();
        for (int i=0;i<12;i++) OUT_PAR_INFO[i] = new BPCOCASH_OUT_PAR_INFO();
    }
}
