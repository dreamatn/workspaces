package com.hisun.DC;

public class DCB5941_AWA_5941 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String PROD_CD = " ";
    public short PROD_CD_NO = 0;
    public String CNAME = " ";
    public short CNAME_NO = 0;
    public String STR_DATE = " ";
    public short STR_DATE_NO = 0;
    public String EXP_DATE = " ";
    public short EXP_DATE_NO = 0;
    public int AC_NUM = 0;
    public short AC_NUM_NO = 0;
    public int AS_NUM = 0;
    public short AS_NUM_NO = 0;
    public char CI_TYP = ' ';
    public short CI_TYP_NO = 0;
    public DCB5941_DD_INFO[] DD_INFO = new DCB5941_DD_INFO[10];
    public DCB5941_LN_INFO[] LN_INFO = new DCB5941_LN_INFO[10];
    public String CCY = " ";
    public short CCY_NO = 0;
    public char CCY_TYP = ' ';
    public short CCY_TYP_NO = 0;
    public char FUND_TYP = ' ';
    public short FUND_TYP_NO = 0;
    public char AMTC_MTH = ' ';
    public short AMTC_MTH_NO = 0;
    public DCB5941_AMT_INFO[] AMT_INFO = new DCB5941_AMT_INFO[5];
    public DCB5941_PCT_INFO[] PCT_INFO = new DCB5941_PCT_INFO[5];
    public short DAYS = 0;
    public short DAYS_NO = 0;
    public short PERD = 0;
    public short PERD_NO = 0;
    public char PERD_UN = ' ';
    public short PERD_UN_NO = 0;
    public char REPY_MTH = ' ';
    public short REPY_MTH_NO = 0;
    public double COMP_AMT = 0;
    public short COMP_AMT_NO = 0;
    public char PERM_KND = ' ';
    public short PERM_KND_NO = 0;
    public short MMDD = 0;
    public short MMDD_NO = 0;
    public DCB5941_AWA_5941() {
        for (int i=0;i<10;i++) DD_INFO[i] = new DCB5941_DD_INFO();
        for (int i=0;i<10;i++) LN_INFO[i] = new DCB5941_LN_INFO();
        for (int i=0;i<5;i++) AMT_INFO[i] = new DCB5941_AMT_INFO();
        for (int i=0;i<5;i++) PCT_INFO[i] = new DCB5941_PCT_INFO();
    }
}
