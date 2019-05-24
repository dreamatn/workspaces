package com.hisun.BP;

public class BPCSBLMI {
    public String OUTPUT_FMT = " ";
    public int MOV_DT = 0;
    public long CNF_NO = 0;
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public int CNT = 0;
    public BPCSBLMI_BV_DATA[] BV_DATA = new BPCSBLMI_BV_DATA[4];
    public BPCSBLMI() {
        for (int i=0;i<4;i++) BV_DATA[i] = new BPCSBLMI_BV_DATA();
    }
}
