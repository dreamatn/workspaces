package com.hisun.BP;

public class BPRTRULE_DATA_TXT {
    public BPRTRULE_DAT_TXT[] DAT_TXT = new BPRTRULE_DAT_TXT[10];
    public String RMK = " ";
    public BPRTRULE_DATA_TXT() {
        for (int i=0;i<10;i++) DAT_TXT[i] = new BPRTRULE_DAT_TXT();
    }
}
