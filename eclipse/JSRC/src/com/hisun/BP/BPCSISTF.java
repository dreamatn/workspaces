package com.hisun.BP;

public class BPCSISTF {
    public int MOV_DT = 0;
    public int CONF_NO = 0;
    public int BV_DATE = 0;
    public String BV_NO = " ";
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public BPCSISTF_DATA_INFO[] DATA_INFO = new BPCSISTF_DATA_INFO[5];
    public String PLBOX_NO = " ";
    public char VB_BOX_OT = ' ';
    public char VB_BOX_IN = ' ';
    public String FMT = " ";
    public char PLBOX_TP = ' ';
    public BPCSISTF() {
        for (int i=0;i<5;i++) DATA_INFO[i] = new BPCSISTF_DATA_INFO();
    }
}
