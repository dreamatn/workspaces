package com.hisun.BP;

public class BPCOPLIR {
    public int BR = 0;
    public char APP_STS = ' ';
    public String APP_TLR = " ";
    public BPCOPLIR_BV_INFO[] BV_INFO = new BPCOPLIR_BV_INFO[10];
    public int APP_BR = 0;
    public int APP_DT = 0;
    public String APP_NOTE = " ";
    public BPCOPLIR() {
        for (int i=0;i<10;i++) BV_INFO[i] = new BPCOPLIR_BV_INFO();
    }
}
