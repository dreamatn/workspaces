package com.hisun.BP;

public class BPCODRCS {
    public int BR = 0;
    public int IN_BR = 0;
    public String C_SWIFT = " ";
    public char CS_KIND = ' ';
    public int MOVE_DT = 0;
    public int CONF_SEQ = 0;
    public int CCY_CNT = 0;
    public int DT_CNT = 0;
    public BPCODRCS_CCY_INFO[] CCY_INFO = new BPCODRCS_CCY_INFO[5];
    public BPCODRCS_DT_INFO[] DT_INFO = new BPCODRCS_DT_INFO[60];
    public char FLG = ' ';
    public String AC_NO = " ";
    public int APP_NO = 0;
    public BPCODRCS() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCODRCS_CCY_INFO();
        for (int i=0;i<60;i++) DT_INFO[i] = new BPCODRCS_DT_INFO();
    }
}
