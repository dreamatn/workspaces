package com.hisun.BP;

public class BPCSQFIN {
    public int MOV_DT = 0;
    public int CONF_NO = 0;
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public char CS_KIND = ' ';
    public BPCSQFIN_DATA_INFO[] DATA_INFO = new BPCSQFIN_DATA_INFO[5];
    public String PLBOX_NO = " ";
    public BPCSQFIN() {
        for (int i=0;i<5;i++) DATA_INFO[i] = new BPCSQFIN_DATA_INFO();
    }
}
