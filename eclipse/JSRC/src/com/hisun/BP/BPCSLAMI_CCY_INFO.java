package com.hisun.BP;

public class BPCSLAMI_CCY_INFO {
    public String CASH_TYP = " ";
    public String CCY = " ";
    public double CCY_AMT = 0;
    public BPCSLAMI_PAR_INFO[] PAR_INFO = new BPCSLAMI_PAR_INFO[12];
    public BPCSLAMI_CCY_INFO() {
        for (int i=0;i<12;i++) PAR_INFO[i] = new BPCSLAMI_PAR_INFO();
    }
}
