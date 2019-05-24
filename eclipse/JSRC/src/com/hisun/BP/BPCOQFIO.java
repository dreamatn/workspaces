package com.hisun.BP;

public class BPCOQFIO {
    public char MOVE_TYP = ' ';
    public int MOVE_DT = 0;
    public int CONF_SEQ = 0;
    public char CS_KIND = ' ';
    public int CCY_CNT = 0;
    public int DT_CNT = 0;
    public BPCOQFIO_CCY_INFO[] CCY_INFO = new BPCOQFIO_CCY_INFO[5];
    public BPCOQFIO_DT_INFO[] DT_INFO = new BPCOQFIO_DT_INFO[60];
    public BPCOQFIO() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCOQFIO_CCY_INFO();
        for (int i=0;i<60;i++) DT_INFO[i] = new BPCOQFIO_DT_INFO();
    }
}
