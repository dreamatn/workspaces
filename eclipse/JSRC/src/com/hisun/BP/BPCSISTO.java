package com.hisun.BP;

public class BPCSISTO {
    public int MOV_DT = 0;
    public int CONF_NO = 0;
    public int BV_DATE = 0;
    public String BV_NO = " ";
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public char CS_KIND = ' ';
    public BPCSISTO_DATA_INFO[] DATA_INFO = new BPCSISTO_DATA_INFO[5];
    public String PLBOX_NO = " ";
    public int APP_NO = 0;
    public char APP_TYPE = ' ';
    public int CNT = 0;
    public BPCSISTO() {
        for (int i=0;i<5;i++) DATA_INFO[i] = new BPCSISTO_DATA_INFO();
    }
}
