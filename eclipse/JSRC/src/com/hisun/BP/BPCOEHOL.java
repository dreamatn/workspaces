package com.hisun.BP;

public class BPCOEHOL {
    public BPCOEHOL_KEY KEY = new BPCOEHOL_KEY();
    public BPCOEHOL_DATA DATA = new BPCOEHOL_DATA();
    public BPCOEHOL_UPD_DATA[] UPD_DATA = new BPCOEHOL_UPD_DATA[70];
    public int CNT = 0;
    public BPCOEHOL() {
        for (int i=0;i<70;i++) UPD_DATA[i] = new BPCOEHOL_UPD_DATA();
    }
}
