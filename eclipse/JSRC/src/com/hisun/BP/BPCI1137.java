package com.hisun.BP;

public class BPCI1137 {
    public String REB_CODE = " ";
    public String REB_DESC = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public String TX_MMO = " ";
    public char R_CYCLE = ' ';
    public short PER_CNT = 0;
    public short REB_STDT = 0;
    public short REB_DATE = 0;
    public int L_REB_DT = 0;
    public int N_REB_DT = 0;
    public char REB_TYPE = ' ';
    public char AGG_TYPE = ' ';
    public BPCI1137_REB_INFO[] REB_INFO = new BPCI1137_REB_INFO[5];
    public BPCI1137() {
        for (int i=0;i<5;i++) REB_INFO[i] = new BPCI1137_REB_INFO();
    }
}
