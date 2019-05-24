package com.hisun.BP;

public class BPB2400_AWA_2400 {
    public String TLR = " ";
    public short TLR_NO = 0;
    public String ATM = " ";
    public short ATM_NO = 0;
    public BPB2400_CCY_INFO[] CCY_INFO = new BPB2400_CCY_INFO[5];
    public BPB2400_PAR_INFO[] PAR_INFO = new BPB2400_PAR_INFO[60];
    public BPB2400_AWA_2400() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPB2400_CCY_INFO();
        for (int i=0;i<60;i++) PAR_INFO[i] = new BPB2400_PAR_INFO();
    }
}
