package com.hisun.BP;

public class BPCO667 {
    public int MOV_DT = 0;
    public long CONF_NO = 0;
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public char BV_STS = ' ';
    public int CNT = 0;
    public BPCO667_BV_DATA[] BV_DATA = new BPCO667_BV_DATA[10];
    public String FEE_CD = " ";
    public String FEE_ACNO = " ";
    public String FEE_CCY = " ";
    public double FEE_AMT = 0;
    public char IN_TYP = ' ';
    public BPCO667() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPCO667_BV_DATA();
    }
}
