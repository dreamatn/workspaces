package com.hisun.BP;

public class BPCSBLMO {
    public String OUTPUT_FMT = " ";
    public int RCV_BR = 0;
    public String RCV_TLR = " ";
    public int CNT = 0;
    public BPCSBLMO_BV_DATA[] BV_DATA = new BPCSBLMO_BV_DATA[4];
    public int MOV_DT = 0;
    public long CNF_NO = 0;
    public BPCSBLMO() {
        for (int i=0;i<4;i++) BV_DATA[i] = new BPCSBLMO_BV_DATA();
    }
}
