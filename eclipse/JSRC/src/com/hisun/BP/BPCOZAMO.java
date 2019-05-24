package com.hisun.BP;

public class BPCOZAMO {
    public String TO_TLR = " ";
    public int MOVE_DT = 0;
    public int CONF_SEQ = 0;
    public int CCY_CNT = 0;
    public BPCOZAMO_CCY_INFO[] CCY_INFO = new BPCOZAMO_CCY_INFO[5];
    public BPCOZAMO() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCOZAMO_CCY_INFO();
    }
}
