package com.hisun.BP;

public class BPCPQTNR_DAT_TXT {
    public char DATE_CHECK = ' ';
    public BPCPQTNR_CODE_INFO[] CODE_INFO = new BPCPQTNR_CODE_INFO[20];
    public BPCPQTNR_DAT_TXT() {
        for (int i=0;i<20;i++) CODE_INFO[i] = new BPCPQTNR_CODE_INFO();
    }
}
