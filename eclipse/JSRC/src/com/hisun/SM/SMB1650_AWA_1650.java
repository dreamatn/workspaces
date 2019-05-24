package com.hisun.SM;

public class SMB1650_AWA_1650 {
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
    public SMB1650_TRAN_TAB[] TRAN_TAB = new SMB1650_TRAN_TAB[3];
    public char VCH_IND = ' ';
    public short VCH_IND_NO = 0;
    public String FILLER = " ";
    public short FILLER_NO = 0;
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public SMB1650_AWA_1650() {
        for (int i=0;i<3;i++) TRAN_TAB[i] = new SMB1650_TRAN_TAB();
    }
}
