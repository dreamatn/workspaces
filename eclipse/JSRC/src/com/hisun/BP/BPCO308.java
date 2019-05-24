package com.hisun.BP;

public class BPCO308 {
    public String RATE_TYPE = " ";
    public String CCY = " ";
    public BPCO308_UPD_DATA[] UPD_DATA = new BPCO308_UPD_DATA[40];
    public int ACCT_CENTER = 0;
    public int CNT = 0;
    public String BASE_NAME = " ";
    public char FILLER13 = 0X02;
    public BPCO308() {
        for (int i=0;i<40;i++) UPD_DATA[i] = new BPCO308_UPD_DATA();
    }
}
