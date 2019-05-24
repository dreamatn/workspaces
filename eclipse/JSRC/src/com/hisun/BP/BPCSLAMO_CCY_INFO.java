package com.hisun.BP;

public class BPCSLAMO_CCY_INFO {
    public String CASH_TYP = " ";
    public String CCY = " ";
    public double CCY_AMT = 0;
    public BPCSLAMO_PAR_INFO[] PAR_INFO = new BPCSLAMO_PAR_INFO[12];
    public double SUSP_AMT = 0;
    public BPCSLAMO_CCY_INFO() {
        for (int i=0;i<12;i++) PAR_INFO[i] = new BPCSLAMO_PAR_INFO();
    }
}
