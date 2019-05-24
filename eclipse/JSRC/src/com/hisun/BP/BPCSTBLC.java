package com.hisun.BP;

public class BPCSTBLC {
    public BPCSTBLC_RC RC = new BPCSTBLC_RC();
    public String OUTPUT_FMT = " ";
    public char VB_FLG = ' ';
    public BPCSTBLC_INFO[] INFO = new BPCSTBLC_INFO[10];
    public BPCSTBLC() {
        for (int i=0;i<10;i++) INFO[i] = new BPCSTBLC_INFO();
    }
}
