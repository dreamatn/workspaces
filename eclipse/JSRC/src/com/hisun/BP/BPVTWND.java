package com.hisun.BP;

public class BPVTWND {
    public BPVTWND_KEY KEY = new BPVTWND_KEY();
    public BPVTWND_WND_DATA[] WND_DATA = new BPVTWND_WND_DATA[7];
    public BPVTWND() {
        for (int i=0;i<7;i++) WND_DATA[i] = new BPVTWND_WND_DATA();
    }
}
