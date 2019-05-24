package com.hisun.BP;

public class BPCIASF {
    public String CTRT_NO = " ";
    public String REL_CTRT_NO = " ";
    public String CTRT_TYPE = " ";
    public int START_DATE = 0;
    public int MATURITY_DATE = 0;
    public char PAY_IND = ' ';
    public String CHARGE_CCY = " ";
    public double CHARGE_AMT = 0;
    public double CHARGE_EQV = 0;
    public double DAILY_ACCR = 0;
    public double FEE_MTH = 0;
    public BPCIASF_GL_MASTERS[] GL_MASTERS = new BPCIASF_GL_MASTERS[4];
    public String PROD_CODE = " ";
    public String PROD_DESC = " ";
    public int BOOK_CENTRE = 0;
    public String BR_DESC = " ";
    public BPCIASF() {
        for (int i=0;i<4;i++) GL_MASTERS[i] = new BPCIASF_GL_MASTERS();
    }
}
