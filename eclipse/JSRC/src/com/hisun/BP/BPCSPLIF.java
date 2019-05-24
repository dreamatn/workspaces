package com.hisun.BP;

public class BPCSPLIF {
    public BPCSPLIF_RC RC = new BPCSPLIF_RC();
    public String OUTPUT_FMT = " ";
    public char VB_FLAG = ' ';
    public char PLBOX_TYPE = ' ';
    public String PLBOX_NO = " ";
    public String TLR = " ";
    public int BR = 0;
    public int UPT_DT = 0;
    public char IN_FLAG = ' ';
    public String IN_CCY = " ";
    public double IN_D_AMT = 0;
    public double IN_R_AMT = 0;
    public double IN_S_AMT = 0;
    public String IN_CASH_TYPE = " ";
    public BPCSPLIF_IN_PVAL_INFO[] IN_PVAL_INFO = new BPCSPLIF_IN_PVAL_INFO[12];
    public char OT_FLAG = ' ';
    public String OT_CCY = " ";
    public double OT_D_AMT = 0;
    public double OT_R_AMT = 0;
    public double OT_S_AMT = 0;
    public String OT_CASH_TYPE = " ";
    public BPCSPLIF_OT_PVAL_INFO[] OT_PVAL_INFO = new BPCSPLIF_OT_PVAL_INFO[12];
    public BPCSPLIF() {
        for (int i=0;i<12;i++) IN_PVAL_INFO[i] = new BPCSPLIF_IN_PVAL_INFO();
        for (int i=0;i<12;i++) OT_PVAL_INFO[i] = new BPCSPLIF_OT_PVAL_INFO();
    }
}
