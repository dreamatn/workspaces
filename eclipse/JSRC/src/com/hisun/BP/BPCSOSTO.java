package com.hisun.BP;

public class BPCSOSTO {
    public BPCSOSTO_RC RC = new BPCSOSTO_RC();
    public int MOVE_DT = 0;
    public int CONF_NO = 0;
    public char CS_KIND = ' ';
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public String IN_AC = " ";
    public int BV_DATE = 0;
    public String BV_NO = " ";
    public BPCSOSTO_DATA_INFO[] DATA_INFO = new BPCSOSTO_DATA_INFO[5];
    public String PLBOX_NO = " ";
    public int APP_NO = 0;
    public char APP_TYPE = ' ';
    public int MOV_DT = 0;
    public int ONWAY_DT = 0;
    public char ALLOT_TYPE = ' ';
    public int CNT = 0;
    public BPCSOSTO() {
        for (int i=0;i<5;i++) DATA_INFO[i] = new BPCSOSTO_DATA_INFO();
    }
}
