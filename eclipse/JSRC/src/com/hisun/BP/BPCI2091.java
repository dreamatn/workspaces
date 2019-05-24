package com.hisun.BP;

public class BPCI2091 {
    public int APP_BR = 0;
    public int UP_BR = 0;
    public int APP_DT = 0;
    public BPCI2091_APP_INFO[] APP_INFO = new BPCI2091_APP_INFO[5];
    public BPCI2091() {
        for (int i=0;i<5;i++) APP_INFO[i] = new BPCI2091_APP_INFO();
    }
}
