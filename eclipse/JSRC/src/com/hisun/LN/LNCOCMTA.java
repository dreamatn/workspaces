package com.hisun.LN;

public class LNCOCMTA {
    public String CMT_NO = " ";
    public String PAPER_NO = " ";
    public String CMT_NM = " ";
    public char FILLER4 = 0X02;
    public String CI_NO = " ";
    public String CI_CNAME = " ";
    public char FILLER7 = 0X02;
    public String REL_FACTNO = " ";
    public int SIGN_DATE = 0;
    public int BOOK_BR = 0;
    public int DOM_BR = 0;
    public String PROD_CD = " ";
    public String PROD_NM = " ";
    public char FILLER14 = 0X02;
    public String CCY = " ";
    public double AMT = 0;
    public double OPEN_AMT = 0;
    public char REVOLVING_FLG = ' ';
    public char UNAL_FLG = ' ';
    public int AVAIL_SDATE = 0;
    public int AVAIL_EDATE = 0;
    public char STS = ' ';
    public char PART_ROLE = ' ';
    public double LAL_AMT = 0;
    public char FILLER25 = 0X01;
    public String AGENT = " ";
    public char FILLER27 = 0X02;
    public double GLOBAL_AMT = 0;
    public LNCOCMTA_CNTY[] CNTY = new LNCOCMTA_CNTY[5];
    public String AC_TYP = " ";
    public String DD_AC = " ";
    public String DD_NM = " ";
    public char FILLER34 = 0X02;
    public double C_AMT = 0;
    public String REMARK1 = " ";
    public char FILLER37 = 0X02;
    public String REMARK2 = " ";
    public char FILLER39 = 0X02;
    public String REMARK3 = " ";
    public char FILLER41 = 0X02;
    public String REMARK4 = " ";
    public char FILLER43 = 0X02;
    public String REMARK5 = " ";
    public char FILLER45 = 0X02;
    public LNCOCMTA() {
        for (int i=0;i<5;i++) CNTY[i] = new LNCOCMTA_CNTY();
    }
}
