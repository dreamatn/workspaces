package com.hisun.BP;

public class BPCSMUTF {
    public BPCSMUTF_RC RC = new BPCSMUTF_RC();
    public String CMMT_NO = " ";
    public int PROC_DT = 0;
    public char CLT_TYPE = ' ';
    public char DRMCR_FLG = ' ';
    public String CHG_CI_NO = " ";
    public char CHG_AC_TY = ' ';
    public String CARD_PSBK_NO = " ";
    public String CHG_AC = " ";
    public String CHG_CCY = " ";
    public char CHG_CCY_TYPE = ' ';
    public char CHG_MOD = ' ';
    public int BAT_ORG_DT = 0;
    public long BAT_ORG_JRN = 0;
    public int BAT_ORG_SEQ = 0;
    public String CHNL_NO = " ";
    public String BSNS_NO = " ";
    public String HLD_NO = " ";
    public String REMARK = " ";
    public short FEE_CNT = 0;
    public BPCSMUTF_FEE_INFO[] FEE_INFO = new BPCSMUTF_FEE_INFO[20];
    public double VAT_AMT = 0;
    public BPCSMUTF() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCSMUTF_FEE_INFO();
    }
}
