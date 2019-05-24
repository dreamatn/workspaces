package com.hisun.BP;

public class BPCSCFHD {
    public int MOVE_DT = 0;
    public int CONF_NO = 0;
    public char CS_KIND = ' ';
    public String SWIFT = " ";
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public char CONF_KIND = ' ';
    public BPCSCFHD_DATA_INFO[] DATA_INFO = new BPCSCFHD_DATA_INFO[5];
    public BPCSCFHD() {
        for (int i=0;i<5;i++) DATA_INFO[i] = new BPCSCFHD_DATA_INFO();
    }
}
