package com.hisun.LN;

public class LNB7060_AWA_7060 {
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
    public short LEN = 0;
    public short LEN_NO = 0;
    public char REPY_MTH = ' ';
    public short REPY_MTH_NO = 0;
    public LNB7060_DATA[] DATA = new LNB7060_DATA[5];
    public LNB7060_AWA_7060() {
        for (int i=0;i<5;i++) DATA[i] = new LNB7060_DATA();
    }
}
