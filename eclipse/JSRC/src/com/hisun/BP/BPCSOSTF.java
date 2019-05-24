package com.hisun.BP;

public class BPCSOSTF {
    public BPCSOSTF_RC RC = new BPCSOSTF_RC();
    public int MOVE_DT = 0;
    public int CONF_NO = 0;
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public BPCSOSTF_DATA_INFO[] DATA_INFO = new BPCSOSTF_DATA_INFO[5];
    public String PLBOX_NO = " ";
    public char VB_BOX_OT = ' ';
    public char VB_BOX_IN = ' ';
    public String FMT = " ";
    public int ONWAY_DT = 0;
    public char PLBOX_TP = ' ';
    public BPCSOSTF() {
        for (int i=0;i<5;i++) DATA_INFO[i] = new BPCSOSTF_DATA_INFO();
    }
}
