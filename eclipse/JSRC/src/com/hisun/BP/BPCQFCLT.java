package com.hisun.BP;

public class BPCQFCLT {
    public BPCQFCLT_KEY KEY = new BPCQFCLT_KEY();
    public int EXP_DATE = 0;
    public String CHG_AC = " ";
    public String CCY = " ";
    public char CAL_CYCLE = ' ';
    public short PERIDO_CNT = 0;
    public int FCHG_DATE = 0;
    public int LCHG_DATE = 0;
    public int NCHG_DATE = 0;
    public char HOLI_METHOD = ' ';
    public String HLD_NO = " ";
    public char SGN_RNG = ' ';
    public BPCQFCLT_FEE_CDS[] FEE_CDS = new BPCQFCLT_FEE_CDS[20];
    public String REMARK = " ";
    public char FILLER21 = 0X02;
    public int CREATE_DATE = 0;
    public int CREATE_TIME = 0;
    public String CREATE_TELL = " ";
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCQFCLT() {
        for (int i=0;i<20;i++) FEE_CDS[i] = new BPCQFCLT_FEE_CDS();
    }
}
