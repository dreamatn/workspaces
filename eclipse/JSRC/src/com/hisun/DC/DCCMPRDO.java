package com.hisun.DC;

public class DCCMPRDO {
    public char FUNC = ' ';
    public String PROD_CODE = " ";
    public String CNAME = " ";
    public char FILLER4 = 0X02;
    public String STR_DATE = " ";
    public String EXP_DATE = " ";
    public int AC_NUM = 0;
    public int AS_NUM = 0;
    public char CI_TYP = ' ';
    public DCCMPRDO_PROD_DD_INFO[] PROD_DD_INFO = new DCCMPRDO_PROD_DD_INFO[10];
    public DCCMPRDO_PROD_LN_INFO[] PROD_LN_INFO = new DCCMPRDO_PROD_LN_INFO[10];
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public short DAYS = 0;
    public String PERD = " ";
    public char PERD_UNIT = ' ';
    public char REPY_MTH = ' ';
    public char PERM_KND = ' ';
    public short MMDD = 0;
    public DCCMPRDO() {
        for (int i=0;i<10;i++) PROD_DD_INFO[i] = new DCCMPRDO_PROD_DD_INFO();
        for (int i=0;i<10;i++) PROD_LN_INFO[i] = new DCCMPRDO_PROD_LN_INFO();
    }
}
