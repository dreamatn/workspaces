package com.hisun.SM;

public class SMB1700_AWA_1700 {
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
    public SMB1700_FILE_TAB[] FILE_TAB = new SMB1700_FILE_TAB[15];
    public SMB1700_AWA_1700() {
        for (int i=0;i<15;i++) FILE_TAB[i] = new SMB1700_FILE_TAB();
    }
}
