package com.hisun.BP;

public class BPCSDRCS {
    public int BR = 0;
    public int MOVE_DT = 0;
    public int CONF_NO = 0;
    public char CS_KIND = ' ';
    public String SWIFT = " ";
    public String CCY_AC = " ";
    public BPCSDRCS_DATA_INFO[] DATA_INFO = new BPCSDRCS_DATA_INFO[5];
    public char FLG = ' ';
    public String AC_NO = " ";
    public int APP_NO = 0;
    public BPCSDRCS() {
        for (int i=0;i<5;i++) DATA_INFO[i] = new BPCSDRCS_DATA_INFO();
    }
}
