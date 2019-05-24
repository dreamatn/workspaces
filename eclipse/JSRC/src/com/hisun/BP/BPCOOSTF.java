package com.hisun.BP;

public class BPCOOSTF {
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public char OUT_VB_FLG = ' ';
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public char IN_VB_FLG = ' ';
    public BPCOOSTF_CCY_INF[] CCY_INF = new BPCOOSTF_CCY_INF[5];
    public int CONF_NO = 0;
    public int MOV_DT = 0;
    public BPCOOSTF() {
        for (int i=0;i<5;i++) CCY_INF[i] = new BPCOOSTF_CCY_INF();
    }
}
