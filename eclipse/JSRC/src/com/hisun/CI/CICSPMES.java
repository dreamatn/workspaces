package com.hisun.CI;

public class CICSPMES {
    public CICSPMES_RC RC = new CICSPMES_RC();
    public CICSPMES_IOPUT_CNT IOPUT_CNT = new CICSPMES_IOPUT_CNT();
    public CICSPMES_IOPUT_INFO[] IOPUT_INFO = new CICSPMES_IOPUT_INFO[10];
    public CICSPMES() {
        for (int i=0;i<10;i++) IOPUT_INFO[i] = new CICSPMES_IOPUT_INFO();
    }
}
