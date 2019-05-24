package com.hisun.LN;

public class LNCICONT {
    public LNCICONT_RC RC = new LNCICONT_RC();
    public char FUNC = ' ';
    public char FLAG = ' ';
    public char CONT_TYP = ' ';
    public String CONTRACT_NO = " ";
    public String FATHER_CTA_REQ = " ";
    public String TOP_CTA_REQ = " ";
    public String CTA_NO_FROM = " ";
    public String CI_NO = " ";
    public Object CONT_POINTER;
    public int CONT_LEN = 0;
    public Object LOAN_POINTER;
    public int LOAN_LEN = 0;
    public Object GMST_POINTER;
    public int GMST_LEN = 0;
    public Object ICTL_POINTER;
    public int ICTL_LEN = 0;
    public short VAL_CNT = 0;
    public LNCICONT_GROUP_DATA GROUP_DATA = new LNCICONT_GROUP_DATA();
    public int MAX_DATE = 0;
    public LNCICONT_AMT_AREA AMT_AREA = new LNCICONT_AMT_AREA();
}
