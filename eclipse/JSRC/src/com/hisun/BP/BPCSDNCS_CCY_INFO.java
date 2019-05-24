package com.hisun.BP;

public class BPCSDNCS_CCY_INFO {
    public String CASH_TYP = " ";
    public String CCY = " ";
    public double CCY_AMT = 0;
    public BPCSDNCS_PAR_INFO[] PAR_INFO = new BPCSDNCS_PAR_INFO[12];
    public BPCSDNCS_CCY_INFO() {
        for (int i=0;i<12;i++) PAR_INFO[i] = new BPCSDNCS_PAR_INFO();
    }
}
