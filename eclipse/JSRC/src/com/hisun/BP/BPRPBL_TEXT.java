package com.hisun.BP;

public class BPRPBL_TEXT {
    public short CNT = 0;
    public BPRPBL_DATA[] DATA = new BPRPBL_DATA[60];
    public String UPD_TEL = " ";
    public int UPD_DATE = 0;
    public int UPD_TIME = 0;
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPRPBL_TEXT() {
        for (int i=0;i<60;i++) DATA[i] = new BPRPBL_DATA();
    }
}
