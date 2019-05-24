package com.hisun.BP;

public class BPB2210_AWA_2210 {
    public char TR_FUNC = ' ';
    public short TR_FUNC_NO = 0;
    public String RCV_TLR = " ";
    public short RCV_TLR_NO = 0;
    public String PLBOX_NO = " ";
    public short PLBOX_NO_NO = 0;
    public String LAST_TLR = " ";
    public short LAST_TLR_NO = 0;
    public String TLR_PSW = " ";
    public short TLR_PSW_NO = 0;
    public BPB2210_CCY_INFO[] CCY_INFO = new BPB2210_CCY_INFO[5];
    public BPB2210_PAR_INFO[] PAR_INFO = new BPB2210_PAR_INFO[60];
    public BPB2210_AWA_2210() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPB2210_CCY_INFO();
        for (int i=0;i<60;i++) PAR_INFO[i] = new BPB2210_PAR_INFO();
    }
}
