package com.hisun.BP;

public class BPCOFCPN_VAL {
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public BPCOFCPN_CPN_DATE[] CPN_DATE = new BPCOFCPN_CPN_DATE[20];
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCOFCPN_VAL() {
        for (int i=0;i<20;i++) CPN_DATE[i] = new BPCOFCPN_CPN_DATE();
    }
}
