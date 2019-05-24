package com.hisun.BP;

public class BPVTHOL {
    public BPVTHOL_KEY KEY = new BPVTHOL_KEY();
    public BPVTHOL_HOL_DATA[] HOL_DATA = new BPVTHOL_HOL_DATA[50];
    public BPVTHOL() {
        for (int i=0;i<50;i++) HOL_DATA[i] = new BPVTHOL_HOL_DATA();
    }
}
