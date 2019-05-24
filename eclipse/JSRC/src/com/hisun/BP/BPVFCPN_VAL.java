package com.hisun.BP;

public class BPVFCPN_VAL {
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public BPVFCPN_VAL1[] VAL1 = new BPVFCPN_VAL1[20];
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPVFCPN_VAL() {
        for (int i=0;i<20;i++) VAL1[i] = new BPVFCPN_VAL1();
    }
}
