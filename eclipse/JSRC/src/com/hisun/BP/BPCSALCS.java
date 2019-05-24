package com.hisun.BP;

public class BPCSALCS {
    public BPCSALCS_RC RC = new BPCSALCS_RC();
    public String OUTPUT_FMT = " ";
    public String MGR_TLR = " ";
    public String CASH_TYP = " ";
    public String CCY = " ";
    public double GD_AMT = 0;
    public BPCSALCS_PAR_INFO1[] PAR_INFO1 = new BPCSALCS_PAR_INFO1[12];
    public double BD_AMT = 0;
    public BPCSALCS_PAR_INFO2[] PAR_INFO2 = new BPCSALCS_PAR_INFO2[12];
    public double HBD_AMT = 0;
    public BPCSALCS_PAR_INFO3[] PAR_INFO3 = new BPCSALCS_PAR_INFO3[12];
    public String PLBOX_NO = " ";
    public char PLBOX_TP = ' ';
    public String OUT_TLR = " ";
    public BPCSALCS() {
        for (int i=0;i<12;i++) PAR_INFO1[i] = new BPCSALCS_PAR_INFO1();
        for (int i=0;i<12;i++) PAR_INFO2[i] = new BPCSALCS_PAR_INFO2();
        for (int i=0;i<12;i++) PAR_INFO3[i] = new BPCSALCS_PAR_INFO3();
    }
}
