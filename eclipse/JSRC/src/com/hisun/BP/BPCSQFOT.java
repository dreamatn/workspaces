package com.hisun.BP;

public class BPCSQFOT {
    public int MOVE_DT = 0;
    public int CONF_NO = 0;
    public char CS_KIND = ' ';
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public BPCSQFOT_DATA_INFO[] DATA_INFO = new BPCSQFOT_DATA_INFO[5];
    public String PLBOX_NO = " ";
    public BPCSQFOT() {
        for (int i=0;i<5;i++) DATA_INFO[i] = new BPCSQFOT_DATA_INFO();
    }
}
