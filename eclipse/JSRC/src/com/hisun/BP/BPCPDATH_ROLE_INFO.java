package com.hisun.BP;

public class BPCPDATH_ROLE_INFO {
    public BPCPDATH_ROLE_DATA[] ROLE_DATA = new BPCPDATH_ROLE_DATA[300];
    public BPCPDATH_ROLE_INFO() {
        for (int i=0;i<300;i++) ROLE_DATA[i] = new BPCPDATH_ROLE_DATA();
    }
}
