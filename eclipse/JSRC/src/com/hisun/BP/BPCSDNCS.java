package com.hisun.BP;

public class BPCSDNCS {
    public BPCSDNCS_RC RC = new BPCSDNCS_RC();
    public String OUTPUT_FMT = " ";
    public char FUNC = ' ';
    public String PLBOX_NO = " ";
    public int FROM_BR = 0;
    public String FROM_TLR = " ";
    public int TO_BR = 0;
    public String TO_TLR = " ";
    public char PLBOX_TP1 = ' ';
    public char PLBOX_TP2 = ' ';
    public char CS_KIND = ' ';
    public BPCSDNCS_CCY_INFO[] CCY_INFO = new BPCSDNCS_CCY_INFO[5];
    public BPCSDNCS() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCSDNCS_CCY_INFO();
    }
}
