package com.hisun.LN;

public class LNCOPART {
    public char LVL_FLG = ' ';
    public String CTA_NO = " ";
    public String CTA_NAME = " ";
    public char FILLER = 0X02;
    public String CTA_CCY = " ";
    public double CTA_AMT = 0;
    public String CI_NO = " ";
    public String PARTI_FNAME = " ";
    public String PARTI_CTY_CODE = " ";
    public String PARTI_CITY_CODE = " ";
    public char LOCAL_BANK_FLAG = ' ';
    public char ADJ_BANK_FLAG = ' ';
    public char REL_TYPE = ' ';
    public char OLD_REL_TYPE = ' ';
    public double PARTI_PCT = 0;
    public double PARTI_AMT = 0;
    public char TAX_CHARGE = ' ';
    public double TAX_RATE = 0;
    public double INT_ACC = 0;
    public double INT_GUAR = 0;
    public LNCOPART_ENG_ADDR_DATA[] ENG_ADDR_DATA = new LNCOPART_ENG_ADDR_DATA[6];
    public LNCOPART_LOCAL_ADDR_DATA[] LOCAL_ADDR_DATA = new LNCOPART_LOCAL_ADDR_DATA[6];
    public String POSTAL_CTY = " ";
    public String POSTAL_AREA = " ";
    public String ENG_CONTACT_NAME = " ";
    public String LOC_CONTACT_NAME = " ";
    public char FILLER_LOC = 0X02;
    public LNCOPART_EMAIL_ADDR_DATA[] EMAIL_ADDR_DATA = new LNCOPART_EMAIL_ADDR_DATA[10];
    public LNCOPART_TELEPHONE_NO_DATA[] TELEPHONE_NO_DATA = new LNCOPART_TELEPHONE_NO_DATA[10];
    public LNCOPART_MOBILE_PHONE_DATA[] MOBILE_PHONE_DATA = new LNCOPART_MOBILE_PHONE_DATA[10];
    public LNCOPART_CONTACT_FAX_DATA[] CONTACT_FAX_DATA = new LNCOPART_CONTACT_FAX_DATA[10];
    public LNCOPART() {
        for (int i=0;i<6;i++) ENG_ADDR_DATA[i] = new LNCOPART_ENG_ADDR_DATA();
        for (int i=0;i<6;i++) LOCAL_ADDR_DATA[i] = new LNCOPART_LOCAL_ADDR_DATA();
        for (int i=0;i<10;i++) EMAIL_ADDR_DATA[i] = new LNCOPART_EMAIL_ADDR_DATA();
        for (int i=0;i<10;i++) TELEPHONE_NO_DATA[i] = new LNCOPART_TELEPHONE_NO_DATA();
        for (int i=0;i<10;i++) MOBILE_PHONE_DATA[i] = new LNCOPART_MOBILE_PHONE_DATA();
        for (int i=0;i<10;i++) CONTACT_FAX_DATA[i] = new LNCOPART_CONTACT_FAX_DATA();
    }
}
