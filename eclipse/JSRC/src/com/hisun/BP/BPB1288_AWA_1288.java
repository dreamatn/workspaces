package com.hisun.BP;

public class BPB1288_AWA_1288 {
    public String CMMT_NO = " ";
    public short CMMT_NO_NO = 0;
    public int PROC_DT = 0;
    public short PROC_DT_NO = 0;
    public char CLT_TYPE = ' ';
    public short CLT_TYPE_NO = 0;
    public char DRMCR = ' ';
    public short DRMCR_NO = 0;
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public char AC_TY = ' ';
    public short AC_TY_NO = 0;
    public String CARD_NO = " ";
    public short CARD_NO_NO = 0;
    public String CHG_AC = " ";
    public short CHG_AC_NO = 0;
    public String CHG_CCY = " ";
    public short CHG_CCY_NO = 0;
    public char CCY_TYPE = ' ';
    public short CCY_TYPE_NO = 0;
    public char CHG_MOD = ' ';
    public short CHG_MOD_NO = 0;
    public int ORG_DT = 0;
    public short ORG_DT_NO = 0;
    public long ORG_JRN = 0;
    public short ORG_JRN_NO = 0;
    public int ORG_SEQ = 0;
    public short ORG_SEQ_NO = 0;
    public String CHNL = " ";
    public short CHNL_NO = 0;
    public String BSNS_NO = " ";
    public short BSNS_NO_NO = 0;
    public String HLD_NO = " ";
    public short HLD_NO_NO = 0;
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public short FEE_CNT = 0;
    public short FEE_CNT_NO = 0;
    public BPB1288_FEE_INFO[] FEE_INFO = new BPB1288_FEE_INFO[20];
    public double VAT_AMT = 0;
    public short VAT_AMT_NO = 0;
    public BPB1288_AWA_1288() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPB1288_FEE_INFO();
    }
}
