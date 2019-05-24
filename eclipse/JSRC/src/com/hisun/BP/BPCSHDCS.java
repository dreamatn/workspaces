package com.hisun.BP;

public class BPCSHDCS {
    public int MOVE_DT = 0;
    public int CONF_NO = 0;
    public char CS_KIND = ' ';
    public String TO_BANK = " ";
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public String IN_AC = " ";
    public String BV_CODE = " ";
    public int BV_DATE = 0;
    public String BV_NO = " ";
    public BPCSHDCS_DATA_INFO[] DATA_INFO = new BPCSHDCS_DATA_INFO[5];
    public BPCSHDCS() {
        for (int i=0;i<5;i++) DATA_INFO[i] = new BPCSHDCS_DATA_INFO();
    }
}
