package com.hisun.CI;

public class CICQACJR {
    public CICQACJR_RC RC = new CICQACJR_RC();
    public CICQACJR_DATA DATA = new CICQACJR_DATA();
    public CICQACJR_JRL_DATA[] JRL_DATA = new CICQACJR_JRL_DATA[10];
    public CICQACJR() {
        for (int i=0;i<10;i++) JRL_DATA[i] = new CICQACJR_JRL_DATA();
    }
}
