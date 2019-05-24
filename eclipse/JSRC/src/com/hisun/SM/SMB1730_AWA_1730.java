package com.hisun.SM;

public class SMB1730_AWA_1730 {
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
    public int FILE_CNT = 0;
    public short FILE_CNT_NO = 0;
    public SMB1730_FILE[] FILE = new SMB1730_FILE[30];
    public SMB1730_AWA_1730() {
        for (int i=0;i<30;i++) FILE[i] = new SMB1730_FILE();
    }
}
