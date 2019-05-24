package com.hisun.BP;

public class BPCSITD_DATA_TXT {
    public BPCSITD_REL_ITMS[] REL_ITMS = new BPCSITD_REL_ITMS[32];
    public String UPD_TLR = " ";
    public int UPD_DATE = 0;
    public int UPD_TIME = 0;
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCSITD_DATA_TXT() {
        for (int i=0;i<32;i++) REL_ITMS[i] = new BPCSITD_REL_ITMS();
    }
}
