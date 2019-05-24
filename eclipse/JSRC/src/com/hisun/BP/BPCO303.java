package com.hisun.BP;

public class BPCO303 {
    public String RATE_TYPE = " ";
    public BPCO303_UPD_DATA[] UPD_DATA = new BPCO303_UPD_DATA[40];
    public int ACCT_CENTER = 0;
    public int CNT = 0;
    public String BASE_NAME = " ";
    public char FILLER12 = 0X02;
    public BPCO303() {
        for (int i=0;i<40;i++) UPD_DATA[i] = new BPCO303_UPD_DATA();
    }
}
