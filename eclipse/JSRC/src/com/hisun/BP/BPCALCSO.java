package com.hisun.BP;

public class BPCALCSO {
    public String RCV_TLR = " ";
    public String CASH_TYP = " ";
    public char PLBOX_TP = ' ';
    public String PLBOX_NO = " ";
    public BPCALCSO_CCY_INFO[] CCY_INFO = new BPCALCSO_CCY_INFO[20];
    public double GD_AMT = 0;
    public char FIL1 = ' ';
    public double BD_AMT = 0;
    public char FIL2 = ' ';
    public BPCALCSO() {
        for (int i=0;i<20;i++) CCY_INFO[i] = new BPCALCSO_CCY_INFO();
    }
}
