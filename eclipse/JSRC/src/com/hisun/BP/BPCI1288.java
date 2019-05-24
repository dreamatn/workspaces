package com.hisun.BP;

public class BPCI1288 {
    public String CMMT_NO = " ";
    public int PROC_DT = 0;
    public char CLT_TYPE = ' ';
    public char DRMCR = ' ';
    public String CI_NO = " ";
    public char AC_TY = ' ';
    public String CARD_NO = " ";
    public String CHG_AC = " ";
    public String CHG_CCY = " ";
    public char CCY_TYPE = ' ';
    public char CHG_MOD = ' ';
    public int ORG_DT = 0;
    public long ORG_JRN = 0;
    public int ORG_SEQ = 0;
    public String CHNL = " ";
    public String BSNS_NO = " ";
    public String HLD_NO = " ";
    public String REMARK = " ";
    public short FEE_CNT = 0;
    public BPCI1288_FEE_INFO[] FEE_INFO = new BPCI1288_FEE_INFO[20];
    public double VAT_AMT = 0;
    public BPCI1288() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCI1288_FEE_INFO();
    }
}
