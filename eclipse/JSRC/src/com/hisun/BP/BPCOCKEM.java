package com.hisun.BP;

public class BPCOCKEM {
    public BPCOCKEM_RC RC = new BPCOCKEM_RC();
    public String CAL_CODE = " ";
    public short YEAR = 0;
    public BPCOCKEM_EOM_DATE[] EOM_DATE = new BPCOCKEM_EOM_DATE[12];
    public BPCOCKEM() {
        for (int i=0;i<12;i++) EOM_DATE[i] = new BPCOCKEM_EOM_DATE();
    }
}
