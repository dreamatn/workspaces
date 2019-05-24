package com.hisun.BP;

public class BPCSNCSO {
    public int FROM_BR = 0;
    public String PLBOX_NO = " ";
    public String TO_TLR = " ";
    public char CS_KIND = ' ';
    public int CCY_CNT = 0;
    public int DT_CNT = 0;
    public BPCSNCSO_CCY_INFO[] CCY_INFO = new BPCSNCSO_CCY_INFO[5];
    public BPCSNCSO_DT_INFO[] DT_INFO = new BPCSNCSO_DT_INFO[60];
    public BPCSNCSO() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCSNCSO_CCY_INFO();
        for (int i=0;i<60;i++) DT_INFO[i] = new BPCSNCSO_DT_INFO();
    }
}
