package com.hisun.BP;

public class BPCQTWND {
    public BPCQTWND_RC RC = new BPCQTWND_RC();
    public BPCQTWND_INPUT_DAT INPUT_DAT = new BPCQTWND_INPUT_DAT();
    public BPCQTWND_WND_DATA[] WND_DATA = new BPCQTWND_WND_DATA[7];
    public BPCQTWND() {
        for (int i=0;i<7;i++) WND_DATA[i] = new BPCQTWND_WND_DATA();
    }
}
