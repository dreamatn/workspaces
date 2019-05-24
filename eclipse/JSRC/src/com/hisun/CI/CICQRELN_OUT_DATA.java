package com.hisun.CI;

public class CICQRELN_OUT_DATA {
    public CICQRELN_AREA[] AREA = new CICQRELN_AREA[100];
    public CICQRELN_OUT_DATA() {
        for (int i=0;i<100;i++) AREA[i] = new CICQRELN_AREA();
    }
}
