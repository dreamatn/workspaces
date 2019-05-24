package com.hisun.BP;

public class BPB7198_AWA_7198 {
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public String CHG_CCY = " ";
    public short CHG_CCY_NO = 0;
    public char AC_TYP = ' ';
    public short AC_TYP_NO = 0;
    public String FEE_AC = " ";
    public short FEE_AC_NO = 0;
    public String RMK = " ";
    public short RMK_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public String C_P_NO = " ";
    public short C_P_NO_NO = 0;
    public String CHQ_NO = " ";
    public short CHQ_NO_NO = 0;
    public int SALE_DT = 0;
    public short SALE_DT_NO = 0;
    public String FEE_CTRT = " ";
    public short FEE_CTRT_NO = 0;
    public char CHG_MOD = ' ';
    public short CHG_MOD_NO = 0;
    public BPB7198_FEE_INF[] FEE_INF = new BPB7198_FEE_INF[20];
    public char CCY_TYPE = ' ';
    public short CCY_TYPE_NO = 0;
    public String BSNS_NO = " ";
    public short BSNS_NO_NO = 0;
    public String CREV_NO = " ";
    public short CREV_NO_NO = 0;
    public char CHG_FLG = ' ';
    public short CHG_FLG_NO = 0;
    public BPB7198_AWA_7198() {
        for (int i=0;i<20;i++) FEE_INF[i] = new BPB7198_FEE_INF();
    }
}
