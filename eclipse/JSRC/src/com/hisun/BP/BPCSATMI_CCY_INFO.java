package com.hisun.BP;

public class BPCSATMI_CCY_INFO {
    public String CASH_TYP = " ";
    public String CCY = " ";
    public double CCY_AMT = 0;
    public BPCSATMI_PAR_INFO[] PAR_INFO = new BPCSATMI_PAR_INFO[12];
    public BPCSATMI_CCY_INFO() {
        for (int i=0;i<12;i++) PAR_INFO[i] = new BPCSATMI_PAR_INFO();
    }
}
