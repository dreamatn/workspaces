package com.hisun.BP;

public class BPCI1188 {
    public String CHG_AC = " ";
    public String FABF_AC = " ";
    public String FEE_CODE = " ";
    public String PRD_CODE = " ";
    public double FEE_AMT = 0;
    public char AC_TYP = ' ';
    public String CCY = " ";
    public String MMO = " ";
    public BPCI1188_FEE_INFO[] FEE_INFO = new BPCI1188_FEE_INFO[40];
    public BPCI1188() {
        for (int i=0;i<40;i++) FEE_INFO[i] = new BPCI1188_FEE_INFO();
    }
}
