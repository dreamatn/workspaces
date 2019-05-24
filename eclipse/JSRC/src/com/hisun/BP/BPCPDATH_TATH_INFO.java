package com.hisun.BP;

public class BPCPDATH_TATH_INFO {
    public BPCPDATH_TATH_DATA[] TATH_DATA = new BPCPDATH_TATH_DATA[300];
    public BPCPDATH_TATH_INFO() {
        for (int i=0;i<300;i++) TATH_DATA[i] = new BPCPDATH_TATH_DATA();
    }
}
