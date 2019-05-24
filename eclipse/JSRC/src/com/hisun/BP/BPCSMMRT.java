package com.hisun.BP;

public class BPCSMMRT {
    public BPCSMMRT_RC RC = new BPCSMMRT_RC();
    public char FUNC = ' ';
    public char BRW_BTH_OPT = ' ';
    public char BTH_OPT = ' ';
    public int BR = 0;
    public String BASE_TYP = " ";
    public String CCY = " ";
    public BPCSMMRT_UPD_DATA[] UPD_DATA = new BPCSMMRT_UPD_DATA[40];
    public BPCSMMRT() {
        for (int i=0;i<40;i++) UPD_DATA[i] = new BPCSMMRT_UPD_DATA();
    }
}
