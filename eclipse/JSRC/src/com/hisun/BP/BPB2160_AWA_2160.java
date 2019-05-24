package com.hisun.BP;

public class BPB2160_AWA_2160 {
    public String C_SWIFT = " ";
    public short C_SWIFT_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public String IN_AC = " ";
    public short IN_AC_NO = 0;
    public String IN_TLR = " ";
    public short IN_TLR_NO = 0;
    public int IN_BR = 0;
    public short IN_BR_NO = 0;
    public String OUT_TLR = " ";
    public short OUT_TLR_NO = 0;
    public int OUT_BR = 0;
    public short OUT_BR_NO = 0;
    public String OUT_AC = " ";
    public short OUT_AC_NO = 0;
    public String AC_NAME = " ";
    public short AC_NAME_NO = 0;
    public int CONF_NO = 0;
    public short CONF_NO_NO = 0;
    public int MOVE_DT = 0;
    public short MOVE_DT_NO = 0;
    public double TOT_AMT = 0;
    public short TOT_AMT_NO = 0;
    public double P_AMT = 0;
    public short P_AMT_NO = 0;
    public double C_AMT = 0;
    public short C_AMT_NO = 0;
    public char CS_KIND = ' ';
    public short CS_KIND_NO = 0;
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public String TR_TLR = " ";
    public short TR_TLR_NO = 0;
    public char ML_OPT = ' ';
    public short ML_OPT_NO = 0;
    public char BOX_FLG = ' ';
    public short BOX_FLG_NO = 0;
    public long ACCT_CD = 0;
    public short ACCT_CD_NO = 0;
    public char CON_KIND = ' ';
    public short CON_KIND_NO = 0;
    public BPB2160_CCY_INFO[] CCY_INFO = new BPB2160_CCY_INFO[5];
    public BPB2160_P_INFO[] P_INFO = new BPB2160_P_INFO[60];
    public String CCY_AC = " ";
    public short CCY_AC_NO = 0;
    public BPB2160_AWA_2160() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPB2160_CCY_INFO();
        for (int i=0;i<60;i++) P_INFO[i] = new BPB2160_P_INFO();
    }
}
