package com.hisun.BP;

public class BPCOSCMO {
    public char FUNC = ' ';
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public String PL_BOX_NO = " ";
    public int IN_BR = 0;
    public long CONF_NO = 0;
    public int MOVE_DT = 0;
    public BPCOSCMO_DATA_INFO[] DATA_INFO = new BPCOSCMO_DATA_INFO[10];
    public BPCOSCMO() {
        for (int i=0;i<10;i++) DATA_INFO[i] = new BPCOSCMO_DATA_INFO();
    }
}
