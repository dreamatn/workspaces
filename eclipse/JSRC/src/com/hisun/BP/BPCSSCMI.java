package com.hisun.BP;

public class BPCSSCMI {
    public BPCSSCMI_RC RC = new BPCSSCMI_RC();
    public char FLG = ' ';
    public int BR = 0;
    public int OUT_BR = 0;
    public String PL_BOX_NO = " ";
    public String TLR = " ";
    public String TLR_CHG = " ";
    public BPCSSCMI_DATA_INFO[] DATA_INFO = new BPCSSCMI_DATA_INFO[10];
    public BPCSSCMI() {
        for (int i=0;i<10;i++) DATA_INFO[i] = new BPCSSCMI_DATA_INFO();
    }
}
