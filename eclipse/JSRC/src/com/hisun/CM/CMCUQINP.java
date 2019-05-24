package com.hisun.CM;

public class CMCUQINP {
    public CMCUQINP_RC RC = new CMCUQINP_RC();
    public int AC_DT = 0;
    public long JRNNO = 0;
    public String SVR_CODE = " ";
    public CMCUQINP_INFO[] INFO = new CMCUQINP_INFO[2];
    public CMCUQINP() {
        for (int i=0;i<2;i++) INFO[i] = new CMCUQINP_INFO();
    }
}
