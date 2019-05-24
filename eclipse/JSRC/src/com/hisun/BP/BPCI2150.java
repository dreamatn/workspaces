package com.hisun.BP;

public class BPCI2150 {
    public int MOVE_DT = 0;
    public int CONF_NO = 0;
    public char CS_KIND = ' ';
    public BPCI2150_CCY_INFO[] CCY_INFO = new BPCI2150_CCY_INFO[5];
    public BPCI2150_P_INFO[] P_INFO = new BPCI2150_P_INFO[60];
    public BPCI2150() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCI2150_CCY_INFO();
        for (int i=0;i<60;i++) P_INFO[i] = new BPCI2150_P_INFO();
    }
}
