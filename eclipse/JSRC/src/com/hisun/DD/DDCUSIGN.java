package com.hisun.DD;

public class DDCUSIGN {
    public String AC_NO = " ";
    public char TX_TYP = ' ';
    public String TXBV_NO = " ";
    public String CNAME = " ";
    public String ENAME = " ";
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public DDCUSIGN_CHQ_DATA[] CHQ_DATA = new DDCUSIGN_CHQ_DATA[5];
    public char CBK_SIZE = ' ';
    public short ROW_CNT = 0;
    public String AUTH_TYP = " ";
    public String AUTH_NO = " ";
    public String AUTH_CNM = " ";
    public String TXSMR = " ";
    public DDCUSIGN() {
        for (int i=0;i<5;i++) CHQ_DATA[i] = new DDCUSIGN_CHQ_DATA();
    }
}
