package com.hisun.LN;

public class LNCOTACT {
    public char FUNC = ' ';
    public String CNTR_NO = " ";
    public String PAPER_NO = " ";
    public String FATHER_NAME = " ";
    public char FILLER8 = 0X02;
    public String FATHER_CNTR = " ";
    public char STS = ' ';
    public char CNTR_TP = ' ';
    public String CI_NO = " ";
    public String CI_CNAME = " ";
    public char FILLER14 = 0X02;
    public int SIGN_DATE = 0;
    public int BOOK_BR = 0;
    public int DOM_BR = 0;
    public String PROD_CD = " ";
    public String PROD_NM = " ";
    public char FILLER20 = 0X02;
    public char REVOLVING_FLG = ' ';
    public char REV_CMMT = ' ';
    public String CCY = " ";
    public double AMT = 0;
    public double OPEN_AMT = 0;
    public double AVAILABLE_AMT = 0;
    public int AVAIL_SDATE = 0;
    public int AVAIL_EDATE = 0;
    public char SMART_FG = ' ';
    public char PART_ROLE = ' ';
    public double LOCAL_AMT = 0;
    public int CHG_LAL_DT = 0;
    public String AGENT = " ";
    public double GLOBAL_AMT = 0;
    public LNCOTACT_CNTY[] CNTY = new LNCOTACT_CNTY[5];
    public String REMARK1 = " ";
    public char FILLER38 = 0X02;
    public String REMARK2 = " ";
    public char FILLER40 = 0X02;
    public String REMARK3 = " ";
    public char FILLER42 = 0X02;
    public String REMARK4 = " ";
    public char FILLER44 = 0X02;
    public String REMARK5 = " ";
    public char FILLER46 = 0X02;
    public int CREATE_DT = 0;
    public String CREATE_TLR = " ";
    public int UPD_DT = 0;
    public String UPD_TLR = " ";
    public String AUTH1 = " ";
    public String AUTH2 = " ";
    public LNCOTACT() {
        for (int i=0;i<5;i++) CNTY[i] = new LNCOTACT_CNTY();
    }
}
