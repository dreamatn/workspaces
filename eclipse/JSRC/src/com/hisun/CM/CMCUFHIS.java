package com.hisun.CM;

public class CMCUFHIS {
    public CMCUFHIS_RC RC = new CMCUFHIS_RC();
    public int AC_DT = 0;
    public long JRNNO = 0;
    public CMCUFHIS_INFO[] INFO = new CMCUFHIS_INFO[50];
    public CMCUFHIS() {
        for (int i=0;i<50;i++) INFO[i] = new CMCUFHIS_INFO();
    }
}
