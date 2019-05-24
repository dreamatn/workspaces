package com.hisun.DD;

public class DDCOSIGN {
    public String AC_NO = " ";
    public char TX_TYP = ' ';
    public String TXBV_NO = " ";
    public String CNAME = " ";
    public char FILLER5 = 0X02;
    public String ENAME = " ";
    public char FILLER7 = 0X02;
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public DDCOSIGN_CHQ_DATA[] CHQ_DATA = new DDCOSIGN_CHQ_DATA[5];
    public char CBK_SIZE = ' ';
    public short ROW_CNT = 0;
    public String AUTH_TYP = " ";
    public String AUTH_NO = " ";
    public String AUTH_CNM = " ";
    public char FILLER31 = 0X02;
    public String TXSMR = " ";
    public char FILLER33 = 0X02;
    public DDCOSIGN() {
        for (int i=0;i<5;i++) CHQ_DATA[i] = new DDCOSIGN_CHQ_DATA();
    }
}
