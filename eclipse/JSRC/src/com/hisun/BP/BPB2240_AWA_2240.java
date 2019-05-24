package com.hisun.BP;

public class BPB2240_AWA_2240 {
    public String RCV_TLR = " ";
    public short RCV_TLR_NO = 0;
    public String TLR_PSW = " ";
    public short TLR_PSW_NO = 0;
    public String CASH_TYP = " ";
    public short CASH_TYP_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public double GD_AMT = 0;
    public short GD_AMT_NO = 0;
    public double BD_AMT = 0;
    public short BD_AMT_NO = 0;
    public double HBD_AMT = 0;
    public short HBD_AMT_NO = 0;
    public BPB2240_PAR_INFO[] PAR_INFO = new BPB2240_PAR_INFO[36];
    public String OUT_TLR = " ";
    public short OUT_TLR_NO = 0;
    public BPB2240_AWA_2240() {
        for (int i=0;i<36;i++) PAR_INFO[i] = new BPB2240_PAR_INFO();
    }
}
