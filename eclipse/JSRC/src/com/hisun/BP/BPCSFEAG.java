package com.hisun.BP;

public class BPCSFEAG {
    public BPCSFEAG_RC RC = new BPCSFEAG_RC();
    public char RETURN_INFO = ' ';
    public char FUNC = ' ';
    public String PRDT_CODE = " ";
    public char CLT_TYPE = ' ';
    public char SGN_TYPE = ' ';
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public int ST_DATE = 0;
    public int ED_DATE = 0;
    public String CCY = " ";
    public char CAL_CYC = ' ';
    public short PERD_CNT = 0;
    public int FCHG_DATE = 0;
    public int LCHG_DATE = 0;
    public int NCHG_DATE = 0;
    public int COMP_DATE = 0;
    public char HOLI_MTH = ' ';
    public char SGN_RNG = ' ';
    public BPCSFEAG_FEE_CDS[] FEE_CDS = new BPCSFEAG_FEE_CDS[20];
    public String REMARK = " ";
    public BPCSFEAG() {
        for (int i=0;i<20;i++) FEE_CDS[i] = new BPCSFEAG_FEE_CDS();
    }
}
