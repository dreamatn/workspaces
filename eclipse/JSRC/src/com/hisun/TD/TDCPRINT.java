package com.hisun.TD;

public class TDCPRINT {
    public TDCPRINT_RC_MSG RC_MSG = new TDCPRINT_RC_MSG();
    public char CANCEL_FLG = ' ';
    public long TXN_JRN = 0;
    public String BV_NO = " ";
    public String MAC_AC = " ";
    public int AC_SEQ = 0;
    public String AC = " ";
    public String AC_NAME = " ";
    public String CCY = " ";
    public int AC_DT = 0;
    public String PROD_CD = " ";
    public double DRAW_AMT = 0;
    public double PAYING_INT = 0;
    public double TAXING_INT = 0;
    public double TAX_RAT = 0;
    public double PAYING_TAX = 0;
    public double PAYED_INT = 0;
    public double TOT_AMT = 0;
    public short TOT_NUM = 0;
    public char OUT_TYPE = ' ';
    public TDCPRINT_INT_INFO[] INT_INFO = new TDCPRINT_INT_INFO[30];
    public char XH_FLG = ' ';
    public TDCPRINT() {
        for (int i=0;i<30;i++) INT_INFO[i] = new TDCPRINT_INT_INFO();
    }
}
