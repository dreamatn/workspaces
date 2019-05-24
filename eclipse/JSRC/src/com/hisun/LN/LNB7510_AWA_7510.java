package com.hisun.LN;

public class LNB7510_AWA_7510 {
    public LNB7510_ACCOUNT[] ACCOUNT = new LNB7510_ACCOUNT[10];
    public String LOAN_AC = " ";
    public short LOAN_AC_NO = 0;
    public int SUFFIX = 0;
    public short SUFFIX_NO = 0;
    public String CENAME = " ";
    public short CENAME_NO = 0;
    public String CCNAME = " ";
    public short CCNAME_NO = 0;
    public double UCINT = 0;
    public short UCINT_NO = 0;
    public char ADJUST = ' ';
    public short ADJUST_NO = 0;
    public double ADJINT = 0;
    public short ADJINT_NO = 0;
    public String LNSTS = " ";
    public short LNSTS_NO = 0;
    public String PRCODE = " ";
    public short PRCODE_NO = 0;
    public String PRNAME = " ";
    public short PRNAME_NO = 0;
    public long LIMNO = 0;
    public short LIMNO_NO = 0;
    public double PRINALL = 0;
    public short PRINALL_NO = 0;
    public double OSBAL = 0;
    public short OSBAL_NO = 0;
    public LNB7510_TERMIFO[] TERMIFO = new LNB7510_TERMIFO[10];
    public short CALTERM = 0;
    public short CALTERM_NO = 0;
    public long JRNNO = 0;
    public short JRNNO_NO = 0;
    public String ADJRSN = " ";
    public short ADJRSN_NO = 0;
    public double INT_AMT = 0;
    public short INT_AMT_NO = 0;
    public char ICAL_ID = ' ';
    public short ICAL_ID_NO = 0;
    public int VAL_DTE = 0;
    public short VAL_DTE_NO = 0;
    public double PRIN_AMT = 0;
    public short PRIN_AMT_NO = 0;
    public char STCL_IND = ' ';
    public short STCL_IND_NO = 0;
    public LNB7510_AWA_7510() {
        for (int i=0;i<10;i++) ACCOUNT[i] = new LNB7510_ACCOUNT();
        for (int i=0;i<10;i++) TERMIFO[i] = new LNB7510_TERMIFO();
    }
}
