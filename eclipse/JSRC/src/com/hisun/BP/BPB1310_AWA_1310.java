package com.hisun.BP;

public class BPB1310_AWA_1310 {
    public String TYPE = " ";
    public short TYPE_NO = 0;
    public String CODE = " ";
    public short CODE_NO = 0;
    public long SEQ_NO = 0;
    public short SEQ_NO_NO = 0;
    public char CI_TYPE = ' ';
    public short CI_TYPE_NO = 0;
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public String CI_NO2 = " ";
    public short CI_NO2_NO = 0;
    public String AC_NO = " ";
    public short AC_NO_NO = 0;
    public String CT_NO = " ";
    public short CT_NO_NO = 0;
    public char USED_FLG = ' ';
    public short USED_FLG_NO = 0;
    public int USED_DT = 0;
    public short USED_DT_NO = 0;
    public String CI_NAME = " ";
    public short CI_NAME_NO = 0;
    public String AC_OFC = " ";
    public short AC_OFC_NO = 0;
    public int ACCT = 0;
    public short ACCT_NO = 0;
    public short AC_DIGIT = 0;
    public short AC_DIGIT_NO = 0;
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public String TYP_DESC = " ";
    public short TYP_DESC_NO = 0;
    public String CD_DESC = " ";
    public short CD_DESC_NO = 0;
    public String SEQ_DESC = " ";
    public short SEQ_DESC_NO = 0;
    public short NUM = 0;
    public short NUM_NO = 0;
    public short NUM2 = 0;
    public short NUM2_NO = 0;
    public BPB1310_ITMS[] ITMS = new BPB1310_ITMS[20];
    public BPB1310_AWA_1310() {
        for (int i=0;i<20;i++) ITMS[i] = new BPB1310_ITMS();
    }
}
