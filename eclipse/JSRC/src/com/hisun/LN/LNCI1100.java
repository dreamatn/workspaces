package com.hisun.LN;

public class LNCI1100 {
    public char FUNC = ' ';
    public String PTYP = " ";
    public String CODE = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public int EFFDATE = 0;
    public int EXPDATE = 0;
    public char REPY_MTH = ' ';
    public LNCI1100_DATA[] DATA = new LNCI1100_DATA[5];
    public LNCI1100() {
        for (int i=0;i<5;i++) DATA[i] = new LNCI1100_DATA();
    }
}
