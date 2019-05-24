package com.hisun.BP;

public class BPCNRULE_DATA {
    public BPCNRULE_DAT_TXT[] DAT_TXT = new BPCNRULE_DAT_TXT[10];
    public String RMK = " ";
    public BPCNRULE_DATA() {
        for (int i=0;i<10;i++) DAT_TXT[i] = new BPCNRULE_DAT_TXT();
    }
}
