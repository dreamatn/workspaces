package com.hisun.BP;

public class BPCGCFEE_FEE_DATA {
    public char SEND_FLG = ' ';
    public String EXP_CODE = " ";
    public short FEE_CNT = 0;
    public BPCGCFEE_FEE_INFO[] FEE_INFO = new BPCGCFEE_FEE_INFO[20];
    public int ACCOUNT_BR = 0;
    public double EX_RATE = 0;
    public String TICKET_NO = " ";
    public String REMARK = " ";
    public char PROC_TYPE = ' ';
    public double VAT_AMT = 0;
    public String FILLER39 = " ";
    public BPCGCFEE_FEE_DATA() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCGCFEE_FEE_INFO();
    }
}
