package com.hisun.DD;

public class DDB5122_AWA_5122 {
    public char I_OPT = ' ';
    public short I_OPT_NO = 0;
    public String I_DD_AC = " ";
    public short I_DD_AC_NO = 0;
    public String I_CCY = " ";
    public short I_CCY_NO = 0;
    public char I_FLG = ' ';
    public short I_FLG_NO = 0;
    public String I_CI_CNM = " ";
    public short I_CI_CNM_NO = 0;
    public String I_CI_ENM = " ";
    public short I_CI_ENM_NO = 0;
    public String I_PROLNO = " ";
    public short I_PROLNO_NO = 0;
    public int I_MTDT = 0;
    public short I_MTDT_NO = 0;
    public int I_STDT = 0;
    public short I_STDT_NO = 0;
    public int I_EDDT = 0;
    public short I_EDDT_NO = 0;
    public char I_JXOPT = ' ';
    public short I_JXOPT_NO = 0;
    public short I_SEQ2 = 0;
    public short I_SEQ2_NO = 0;
    public char I_FCTYP = ' ';
    public short I_FCTYP_NO = 0;
    public char I_CJTYP = ' ';
    public short I_CJTYP_NO = 0;
    public DDB5122_TIR_INF[] TIR_INF = new DDB5122_TIR_INF[5];
    public DDB5122_AWA_5122() {
        for (int i=0;i<5;i++) TIR_INF[i] = new DDB5122_TIR_INF();
    }
}
