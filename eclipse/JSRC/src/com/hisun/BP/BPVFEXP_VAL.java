package com.hisun.BP;

public class BPVFEXP_VAL {
    public String DER_DESC = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public String FEE_MASK1 = " ";
    public String FEE_MASK2 = " ";
    public String FEE_MASK3 = " ";
    public String FEE_MASK4 = " ";
    public BPVFEXP_EXP_DATA[] EXP_DATA = new BPVFEXP_EXP_DATA[50];
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPVFEXP_VAL() {
        for (int i=0;i<50;i++) EXP_DATA[i] = new BPVFEXP_EXP_DATA();
    }
}
