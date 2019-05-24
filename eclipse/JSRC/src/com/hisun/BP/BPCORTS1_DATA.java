package com.hisun.BP;

public class BPCORTS1_DATA {
    public String REMT_CCY = " ";
    public double REMT_AMT = 0;
    public String PAY_CCY = " ";
    public double SEL_RAT1 = 0;
    public int TC_NO1 = 0;
    public String BIC_NO1 = " ";
    public String AC_NO1 = " ";
    public double AC_AMT1 = 0;
    public double CASH_AMT = 0;
    public double TRF_AMT = 0;
    public String PAY_CCY2 = " ";
    public double SEL_RAT2 = 0;
    public int TC_NO2 = 0;
    public double AC_AMT2 = 0;
    public char OTH_METH = ' ';
    public String CHG_CCY = " ";
    public double COM_USD = 0;
    public double COM_PAY = 0;
    public double COR_USD = 0;
    public double COR_PAY = 0;
    public double LIE_USD = 0;
    public double LIE_PAY = 0;
    public double CAB_USD = 0;
    public double CAB_PAY = 0;
    public double OTH_USD = 0;
    public double OTH_PAY = 0;
    public String CHG_AC = " ";
    public double C_AC_AMT = 0;
    public double C_CS_AMT = 0;
    public double C_TR_AMT = 0;
    public char SEND_MSG = ' ';
    public String CPNT_ID = " ";
    public String PROD_CD = " ";
    public String DER_CODE = " ";
    public BPCORTS1_FEE_INFO[] FEE_INFO = new BPCORTS1_FEE_INFO[20];
    public BPCORTS1_DATA() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCORTS1_FEE_INFO();
    }
}
