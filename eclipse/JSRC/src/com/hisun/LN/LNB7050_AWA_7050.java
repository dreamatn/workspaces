package com.hisun.LN;

public class LNB7050_AWA_7050 {
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
    public char VLDT_FLG = ' ';
    public short VLDT_FLG_NO = 0;
    public LNB7050_FRM_CNTR[] FRM_CNTR = new LNB7050_FRM_CNTR[10];
    public char RATE_TYP = ' ';
    public short RATE_TYP_NO = 0;
    public String INT_FML = " ";
    public short INT_FML_NO = 0;
    public short TO_NO = 0;
    public short TO_NO_NO = 0;
    public LNB7050_AWA_7050() {
        for (int i=0;i<10;i++) FRM_CNTR[i] = new LNB7050_FRM_CNTR();
    }
}
