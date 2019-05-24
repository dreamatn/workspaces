package com.hisun.BP;

public class BPCOLAMI {
    public String FROM_TLR = " ";
    public String TO_TLR = " ";
    public int MOVE_DT = 0;
    public int CONF_SEQ = 0;
    public int CCY_CNT = 0;
    public int DT_CNT = 0;
    public BPCOLAMI_CCY_INFO[] CCY_INFO = new BPCOLAMI_CCY_INFO[5];
    public BPCOLAMI_DT_INFO[] DT_INFO = new BPCOLAMI_DT_INFO[60];
    public BPCOLAMI() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCOLAMI_CCY_INFO();
        for (int i=0;i<60;i++) DT_INFO[i] = new BPCOLAMI_DT_INFO();
    }
}
