package com.hisun.BP;

public class BPCO152 {
    public int MOV_DT = 0;
    public long CONF_NO = 0;
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public char BV_STS = ' ';
    public int CNT = 0;
    public BPCO152_BV_DATA[] BV_DATA = new BPCO152_BV_DATA[10];
    public String FEE_CD = " ";
    public String FEE_ACNO = " ";
    public String FEE_CCY = " ";
    public double FEE_AMT = 0;
    public char OUT_TYP = ' ';
    public BPCO152() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPCO152_BV_DATA();
    }
}
