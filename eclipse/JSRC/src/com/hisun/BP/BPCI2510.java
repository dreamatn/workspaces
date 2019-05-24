package com.hisun.BP;

public class BPCI2510 {
    public int CONF_NO = 0;
    public int MOVE_DT = 0;
    public BPCI2510_CCY_INFO[] CCY_INFO = new BPCI2510_CCY_INFO[5];
    public String ATM = " ";
    public BPCI2510() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCI2510_CCY_INFO();
    }
}
