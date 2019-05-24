package com.hisun.BP;

public class BPCFFCON_FEE_INFO {
    public String DER_CODE = " ";
    public short FEE_CNT = 0;
    public BPCFFCON_FEE_INFO1[] FEE_INFO1 = new BPCFFCON_FEE_INFO1[20];
    public int ACCOUNT_BR = 0;
    public double EX_RATE = 0;
    public String TICKET_NO = " ";
    public String REMARK = " ";
    public String FILLER38 = " ";
    public double VAT_AMT = 0;
    public String PROD_CODE1 = " ";
    public BPCFFCON_FEE_INFO() {
        for (int i=0;i<20;i++) FEE_INFO1[i] = new BPCFFCON_FEE_INFO1();
    }
}
