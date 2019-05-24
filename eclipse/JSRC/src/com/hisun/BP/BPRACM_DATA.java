package com.hisun.BP;

public class BPRACM_DATA {
    public String MOD_NO = " ";
    public String MOD_NAME = " ";
    public char MOD_TYP = ' ';
    public BPRACM_EVENT[] EVENT = new BPRACM_EVENT[60];
    public String UPD_TEL = " ";
    public int UPD_DATE = 0;
    public int UPD_TIME = 0;
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPRACM_DATA() {
        for (int i=0;i<60;i++) EVENT[i] = new BPRACM_EVENT();
    }
}
