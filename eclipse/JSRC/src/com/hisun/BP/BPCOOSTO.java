package com.hisun.BP;

public class BPCOOSTO {
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public char CS_KIND = ' ';
    public int BV_DATE = 0;
    public String BV_NO = " ";
    public int MOVE_DT = 0;
    public int CONF_SEQ = 0;
    public int CCY_CNT = 0;
    public int DT_CNT = 0;
    public BPCOOSTO_CCY_INFO[] CCY_INFO = new BPCOOSTO_CCY_INFO[5];
    public BPCOOSTO_DT_INFO[] DT_INFO = new BPCOOSTO_DT_INFO[60];
    public char FLG = ' ';
    public int APP_NO = 0;
    public int ONWAY_DT = 0;
    public char ALLOT_TYPE = ' ';
    public BPCOOSTO() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCOOSTO_CCY_INFO();
        for (int i=0;i<60;i++) DT_INFO[i] = new BPCOOSTO_DT_INFO();
    }
}
