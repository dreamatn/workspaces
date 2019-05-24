package com.hisun.BP;

public class BPCOCFDR {
    public int BR = 0;
    public char CS_KIND = ' ';
    public int MOVE_DT = 0;
    public int CONF_SEQ = 0;
    public int CCY_CNT = 0;
    public int DT_CNT = 0;
    public BPCOCFDR_CCY_INFO[] CCY_INFO = new BPCOCFDR_CCY_INFO[5];
    public BPCOCFDR_DT_INFO[] DT_INFO = new BPCOCFDR_DT_INFO[60];
    public int APP_NO = 0;
    public BPCOCFDR() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCOCFDR_CCY_INFO();
        for (int i=0;i<60;i++) DT_INFO[i] = new BPCOCFDR_DT_INFO();
    }
}
