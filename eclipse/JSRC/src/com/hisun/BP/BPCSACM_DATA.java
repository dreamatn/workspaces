package com.hisun.BP;

public class BPCSACM_DATA {
    public String MOD_NO = " ";
    public String MOD_NAME = " ";
    public char MOD_TYP = ' ';
    public BPCSACM_EVENT[] EVENT = new BPCSACM_EVENT[60];
    public BPCSACM_DATA() {
        for (int i=0;i<60;i++) EVENT[i] = new BPCSACM_EVENT();
    }
}
