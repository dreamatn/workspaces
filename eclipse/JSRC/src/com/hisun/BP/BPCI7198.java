package com.hisun.BP;

public class BPCI7198 {
    public String CI_NO = " ";
    public String CHG_CCY = " ";
    public char AC_TYP = ' ';
    public String FEE_AC = " ";
    public String RMK = " ";
    public int BR = 0;
    public String C_P_NO = " ";
    public String CHQ_NO = " ";
    public int SALE_DT = 0;
    public String FEE_CTRT = " ";
    public char CHG_MOD = ' ';
    public BPCI7198_FEE_INF[] FEE_INF = new BPCI7198_FEE_INF[20];
    public char CCY_TYPE = ' ';
    public String BSNS_NO = " ";
    public String CREV_NO = " ";
    public char CHG_FLG = ' ';
    public BPCI7198() {
        for (int i=0;i<20;i++) FEE_INF[i] = new BPCI7198_FEE_INF();
    }
}
