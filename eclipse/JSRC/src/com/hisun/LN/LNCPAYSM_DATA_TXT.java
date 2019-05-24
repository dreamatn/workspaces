package com.hisun.LN;

public class LNCPAYSM_DATA_TXT {
    public char REPY_MTH = ' ';
    public LNCPAYSM_DATA[] DATA = new LNCPAYSM_DATA[5];
    public LNCPAYSM_DATA_TXT() {
        for (int i=0;i<5;i++) DATA[i] = new LNCPAYSM_DATA();
    }
}
