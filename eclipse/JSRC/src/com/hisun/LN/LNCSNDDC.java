package com.hisun.LN;

public class LNCSNDDC {
    public LNCSNDDC_RC RC = new LNCSNDDC_RC();
    public String TYP = " ";
    public String CNTR_NO = " ";
    public String BILL_NO = " ";
    public double RCN_AMT = 0;
    public int BOOK_BR = 0;
    public String CCY = " ";
    public short CNT = 0;
    public LNCSNDDC_BOOK_ARY[] BOOK_ARY = new LNCSNDDC_BOOK_ARY[20];
    public String TR_MMO = " ";
    public String RMK = " ";
    public LNCSNDDC() {
        for (int i=0;i<20;i++) BOOK_ARY[i] = new LNCSNDDC_BOOK_ARY();
    }
}
