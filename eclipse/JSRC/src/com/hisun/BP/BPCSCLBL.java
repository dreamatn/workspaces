package com.hisun.BP;

public class BPCSCLBL {
    public BPCSCLBL_RC RC = new BPCSCLBL_RC();
    public char FUN = ' ';
    public String OUTPUT_FMT = " ";
    public int BRANCH = 0;
    public String TLR = " ";
    public char PLBOX_TP = ' ';
    public String PLBOX_NO = " ";
    public String CASH_TYP = " ";
    public String CCY = " ";
    public char VB_FLG = ' ';
    public double ACTU_AMT = 0;
    public char FILLER16 = 0X01;
    public double GD_AMT = 0;
    public BPCSCLBL_PAR_INFO1[] PAR_INFO1 = new BPCSCLBL_PAR_INFO1[30];
    public double BD_AMT = 0;
    public BPCSCLBL_PAR_INFO2[] PAR_INFO2 = new BPCSCLBL_PAR_INFO2[30];
    public double HBD_AMT = 0;
    public BPCSCLBL_PAR_INFO3[] PAR_INFO3 = new BPCSCLBL_PAR_INFO3[30];
    public double L_AMT = 0;
    public double AMT_C = 0;
    public double AMT_D = 0;
    public double BAL = 0;
    public short TX_CNT = 0;
    public String TX_CODE = " ";
    public char CHK_TYPE = ' ';
    public char STS = ' ';
    public double AMT = 0;
    public char BOXP_TYPE = ' ';
    public char INVNTYP = ' ';
    public String INVNTY = " ";
    public String HANDLER = " ";
    public String INTY_NM = " ";
    public String HLD_NM = " ";
    public BPCSCLBL() {
        for (int i=0;i<30;i++) PAR_INFO1[i] = new BPCSCLBL_PAR_INFO1();
        for (int i=0;i<30;i++) PAR_INFO2[i] = new BPCSCLBL_PAR_INFO2();
        for (int i=0;i<30;i++) PAR_INFO3[i] = new BPCSCLBL_PAR_INFO3();
    }
}
