package com.hisun.BP;

public class BPCRHIST {
    public BPCRHIST_RC RC = new BPCRHIST_RC();
    public char FUNC = ' ';
    public String TLR = " ";
    public BPCRHIST_INFO[] INFO = new BPCRHIST_INFO[30];
    public BPCRHIST() {
        for (int i=0;i<30;i++) INFO[i] = new BPCRHIST_INFO();
    }
}
