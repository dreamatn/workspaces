package com.hisun.BP;

public class BPCCLBLO {
    public String TLR = " ";
    public String CASH_TYP = " ";
    public String CCY = " ";
    public double MACH_AMT = 0;
    public char FILLER5 = 0X01;
    public double ACTU_AMT = 0;
    public char FILLER7 = 0X01;
    public double GD_AMT = 0;
    public char FIL1 = ' ';
    public int CNT1 = 0;
    public BPCCLBLO_PAR_INFO1[] PAR_INFO1 = new BPCCLBLO_PAR_INFO1[20];
    public double BD_AMT = 0;
    public char FIL2 = ' ';
    public double HBD_AMT = 0;
    public char FIL3 = ' ';
    public String PLBOX_NO = " ";
    public char VB_FLG = ' ';
    public double L_AMT = 0;
    public char FIL4 = ' ';
    public double AMT_C = 0;
    public char FIL5 = ' ';
    public double AMT_D = 0;
    public char FIL6 = ' ';
    public double BAL = 0;
    public char FIL7 = ' ';
    public short TX_CNT = 0;
    public char STS = ' ';
    public char INVNTYP = ' ';
    public BPCCLBLO() {
        for (int i=0;i<20;i++) PAR_INFO1[i] = new BPCCLBLO_PAR_INFO1();
    }
}
