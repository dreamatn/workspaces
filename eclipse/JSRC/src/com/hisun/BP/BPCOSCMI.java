package com.hisun.BP;

public class BPCOSCMI {
    public char FUNC = ' ';
    public String PL_BOX_NO = " ";
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public BPCOSCMI_DATA_INFO[] DATA_INFO = new BPCOSCMI_DATA_INFO[10];
    public BPCOSCMI() {
        for (int i=0;i<10;i++) DATA_INFO[i] = new BPCOSCMI_DATA_INFO();
    }
}
