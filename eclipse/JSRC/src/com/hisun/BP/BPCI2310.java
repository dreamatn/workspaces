package com.hisun.BP;

public class BPCI2310 {
    public String CCY = " ";
    public String CASH_TYP = " ";
    public char CS_KIND = ' ';
    public char ML_OPT = ' ';
    public char BOX_FLG = ' ';
    public double AMT = 0;
    public char METHOD = ' ';
    public String REV_NO = " ";
    public String AC_NO = " ";
    public int TRANS_BR = 0;
    public BPCI2310_P_INFO[] P_INFO = new BPCI2310_P_INFO[12];
    public int TR_BR = 0;
    public String TR_TLR = " ";
    public char AC_TYPE = ' ';
    public char SUSP_TYP = ' ';
    public BPCI2310() {
        for (int i=0;i<12;i++) P_INFO[i] = new BPCI2310_P_INFO();
    }
}
