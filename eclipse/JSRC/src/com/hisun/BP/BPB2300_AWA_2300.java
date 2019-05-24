package com.hisun.BP;

public class BPB2300_AWA_2300 {
    public String CCY = " ";
    public short CCY_NO = 0;
    public String CASH_TYP = " ";
    public short CASH_TYP_NO = 0;
    public double AMT = 0;
    public short AMT_NO = 0;
    public char CS_KIND = ' ';
    public short CS_KIND_NO = 0;
    public char ML_OPT = ' ';
    public short ML_OPT_NO = 0;
    public char BOX_FLG = ' ';
    public short BOX_FLG_NO = 0;
    public long ACCT_CD = 0;
    public short ACCT_CD_NO = 0;
    public char METHOD = ' ';
    public short METHOD_NO = 0;
    public int GD_NUM = 0;
    public short GD_NUM_NO = 0;
    public String AC_NO = " ";
    public short AC_NO_NO = 0;
    public int TRANS_BR = 0;
    public short TRANS_BR_NO = 0;
    public long TRA_ACCT = 0;
    public short TRA_ACCT_NO = 0;
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public String TR_TLR = " ";
    public short TR_TLR_NO = 0;
    public BPB2300_P_INFO[] P_INFO = new BPB2300_P_INFO[20];
    public String REV_NO = " ";
    public short REV_NO_NO = 0;
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public char SUSP_TYP = ' ';
    public short SUSP_TYP_NO = 0;
    public char AC_TYPE = ' ';
    public short AC_TYPE_NO = 0;
    public BPB2300_AWA_2300() {
        for (int i=0;i<20;i++) P_INFO[i] = new BPB2300_P_INFO();
    }
}
