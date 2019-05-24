package com.hisun.BP;

public class BPRBTR_DATA_TXT {
    public BPRBTR_FILE_TBL[] FILE_TBL = new BPRBTR_FILE_TBL[15];
    public BPRBTR_DATA_TXT() {
        for (int i=0;i<15;i++) FILE_TBL[i] = new BPRBTR_FILE_TBL();
    }
}
