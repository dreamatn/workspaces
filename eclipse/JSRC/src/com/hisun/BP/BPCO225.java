package com.hisun.BP;

public class BPCO225 {
    public int APP_NO = 0;
    public int APP_BR = 0;
    public int UP_BR = 0;
    public String APP_TLR = " ";
    public int APP_DT = 0;
    public BPCO225_CCY_INFO[] CCY_INFO = new BPCO225_CCY_INFO[5];
    public BPCO225() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCO225_CCY_INFO();
    }
}
