package com.hisun.BP;

public class BPCI2100 {
    public int MOVE_DT = 0;
    public int CONF_NO = 0;
    public int TR_BR = 0;
    public String TR_TLR = " ";
    public String C_SWIFT = " ";
    public char CS_KIND = ' ';
    public BPCI2100_CCY_INFO[] CCY_INFO = new BPCI2100_CCY_INFO[5];
    public char FLG = ' ';
    public String AC_NO = " ";
    public int APP_NO = 0;
    public BPCI2100() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCI2100_CCY_INFO();
    }
}
