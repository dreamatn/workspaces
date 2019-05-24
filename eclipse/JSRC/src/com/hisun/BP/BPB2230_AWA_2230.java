package com.hisun.BP;

public class BPB2230_AWA_2230 {
    public String RCV_TLR = " ";
    public short RCV_TLR_NO = 0;
    public String TLR_PSW = " ";
    public short TLR_PSW_NO = 0;
    public BPB2230_CCY_INFO[] CCY_INFO = new BPB2230_CCY_INFO[5];
    public BPB2230_PAR_INFO[] PAR_INFO = new BPB2230_PAR_INFO[60];
    public char CS_KIND = ' ';
    public short CS_KIND_NO = 0;
    public BPB2230_AWA_2230() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPB2230_CCY_INFO();
        for (int i=0;i<60;i++) PAR_INFO[i] = new BPB2230_PAR_INFO();
    }
}
