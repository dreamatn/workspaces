package com.hisun.BP;

public class BPCSFCLT {
    public BPCSFCLT_RC RC = new BPCSFCLT_RC();
    public char RETURN_INFO = ' ';
    public char FUNC = ' ';
    public int PAGE_NUM = 0;
    public int PAGE_ROW = 0;
    public char CLT_TYPE = ' ';
    public char SGN_TYPE = ' ';
    public String SGN_CINO = " ";
    public String SGN_AC = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public int ST_DATE = 0;
    public int ED_DATE = 0;
    public String CHG_AC = " ";
    public String CCY = " ";
    public char CAL_CYC = ' ';
    public short PERD_CNT = 0;
    public int FCHG_DATE = 0;
    public int LCHG_DATE = 0;
    public int NCHG_DATE = 0;
    public char HOLI_MTH = ' ';
    public String HLD_NO = " ";
    public char SGN_RNG = ' ';
    public BPCSFCLT_FEE_CDS[] FEE_CDS = new BPCSFCLT_FEE_CDS[20];
    public String REMARK = " ";
    public BPCSFCLT() {
        for (int i=0;i<20;i++) FEE_CDS[i] = new BPCSFCLT_FEE_CDS();
    }
}
