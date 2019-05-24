package com.hisun.BP;

public class BPCTQFLT_DAT {
    public BPCTQFLT_DAT_TXT[] DAT_TXT = new BPCTQFLT_DAT_TXT[10];
    public String RMK = " ";
    public BPCTQFLT_DAT() {
        for (int i=0;i<10;i++) DAT_TXT[i] = new BPCTQFLT_DAT_TXT();
    }
}
