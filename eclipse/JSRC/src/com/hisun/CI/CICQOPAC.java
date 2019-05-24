package com.hisun.CI;

public class CICQOPAC {
    public CICQOPAC_RC RC = new CICQOPAC_RC();
    public CICQOPAC_DATA DATA = new CICQOPAC_DATA();
    public CICQOPAC_AGR_NO_AREA[] AGR_NO_AREA = new CICQOPAC_AGR_NO_AREA[200];
    public CICQOPAC() {
        for (int i=0;i<200;i++) AGR_NO_AREA[i] = new CICQOPAC_AGR_NO_AREA();
    }
}
