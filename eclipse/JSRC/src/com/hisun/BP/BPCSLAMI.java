package com.hisun.BP;

public class BPCSLAMI {
    public BPCSLAMI_RC RC = new BPCSLAMI_RC();
    public String OUTPUT_FMT = " ";
    public char FUNC = ' ';
    public int FROM_BR = 0;
    public String FROM_TLR = " ";
    public int TO_BR = 0;
    public String TO_TLR = " ";
    public int CONF_NO = 0;
    public int MOVE_DT = 0;
    public char CS_KIND = ' ';
    public BPCSLAMI_CCY_INFO[] CCY_INFO = new BPCSLAMI_CCY_INFO[5];
    public char PLBOX_TYP = ' ';
    public BPCSLAMI() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCSLAMI_CCY_INFO();
    }
}
