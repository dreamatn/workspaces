package com.hisun.LN;

public class LNB7040_AWA_7040 {
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
    public LNB7040_CTNR_DAT[] CTNR_DAT = new LNB7040_CTNR_DAT[10];
    public LNB7040_AWA_7040() {
        for (int i=0;i<10;i++) CTNR_DAT[i] = new LNB7040_CTNR_DAT();
    }
}
