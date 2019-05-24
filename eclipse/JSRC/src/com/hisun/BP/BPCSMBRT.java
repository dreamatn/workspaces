package com.hisun.BP;

public class BPCSMBRT {
    public BPCSMBRT_RC RC = new BPCSMBRT_RC();
    public char FUNC = ' ';
    public int BR = 0;
    public String BASE_TYP = " ";
    public String TENOR = " ";
    public BPCSMBRT_UPD_DATA[] UPD_DATA = new BPCSMBRT_UPD_DATA[14];
    public BPCSMBRT() {
        for (int i=0;i<14;i++) UPD_DATA[i] = new BPCSMBRT_UPD_DATA();
    }
}
