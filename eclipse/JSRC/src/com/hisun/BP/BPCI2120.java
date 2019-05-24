package com.hisun.BP;

public class BPCI2120 {
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public char CS_KIND = ' ';
    public int BV_DATE = 0;
    public String BV_NO = " ";
    public BPCI2120_CCY_INFO[] CCY_INFO = new BPCI2120_CCY_INFO[5];
    public BPCI2120_P_INFO[] P_INFO = new BPCI2120_P_INFO[60];
    public int MOVE_DT = 0;
    public String CONF_NOG = " ";
    public int TR_BR = 0;
    public String TR_TLR = " ";
    public String APP_NO_G = " ";
    public char ALLOT_TP = ' ';
    public BPCI2120() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCI2120_CCY_INFO();
        for (int i=0;i<60;i++) P_INFO[i] = new BPCI2120_P_INFO();
    }
}
