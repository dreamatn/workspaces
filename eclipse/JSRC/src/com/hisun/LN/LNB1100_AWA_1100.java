package com.hisun.LN;

public class LNB1100_AWA_1100 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String PTYP = " ";
    public short PTYP_NO = 0;
    public String CODE = " ";
    public short CODE_NO = 0;
    public int EFFDATE = 0;
    public short EFFDATE_NO = 0;
    public int EXPDATE = 0;
    public short EXPDATE_NO = 0;
    public String DESC = " ";
    public short DESC_NO = 0;
    public String CDESC = " ";
    public short CDESC_NO = 0;
    public char REPY_MTH = ' ';
    public short REPY_MTH_NO = 0;
    public LNB1100_DATA[] DATA = new LNB1100_DATA[5];
    public LNB1100_AWA_1100() {
        for (int i=0;i<5;i++) DATA[i] = new LNB1100_DATA();
    }
}
