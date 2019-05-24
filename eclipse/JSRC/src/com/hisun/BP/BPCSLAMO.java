package com.hisun.BP;

public class BPCSLAMO {
    public BPCSLAMO_RC RC = new BPCSLAMO_RC();
    public String OUTPUT_FMT = " ";
    public char FUNC = ' ';
    public int FROM_BR = 0;
    public String FROM_TLR = " ";
    public int TO_BR = 0;
    public String TO_TLR = " ";
    public int CONF_NO = 0;
    public int MOVE_DT = 0;
    public char CS_KIND = ' ';
    public BPCSLAMO_CCY_INFO[] CCY_INFO = new BPCSLAMO_CCY_INFO[5];
    public String TLR = " ";
    public BPCSLAMO() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCSLAMO_CCY_INFO();
    }
}
