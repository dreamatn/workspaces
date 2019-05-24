package com.hisun.AI;

public class AIB0010_AWA_0010 {
    public AIB0010_ACCTBR[] ACCTBR = new AIB0010_ACCTBR[20];
    public AIB0010_ACCT_GRP[] ACCT_GRP = new AIB0010_ACCT_GRP[5];
    public String COA_FLG = " ";
    public short COA_FLG_NO = 0;
    public AIB0010_COA_OLD[] COA_OLD = new AIB0010_COA_OLD[5];
    public char COA_TYPE = ' ';
    public short COA_TYPE_NO = 0;
    public char COA_CAT = ' ';
    public short COA_CAT_NO = 0;
    public char POSTTYP = ' ';
    public short POSTTYP_NO = 0;
    public String COA_FR = " ";
    public short COA_FR_NO = 0;
    public String COA_TO = " ";
    public short COA_TO_NO = 0;
    public int ADJ_DATE = 0;
    public short ADJ_DATE_NO = 0;
    public String COA_NEW = " ";
    public short COA_NEW_NO = 0;
    public String COA_ENM2 = " ";
    public short COA_ENM2_NO = 0;
    public String COA_CNM2 = " ";
    public short COA_CNM2_NO = 0;
    public String TS = " ";
    public short TS_NO = 0;
    public int YDATE = 0;
    public short YDATE_NO = 0;
    public char YSTS = ' ';
    public short YSTS_NO = 0;
    public AIB0010_AWA_0010() {
        for (int i=0;i<20;i++) ACCTBR[i] = new AIB0010_ACCTBR();
        for (int i=0;i<5;i++) ACCT_GRP[i] = new AIB0010_ACCT_GRP();
        for (int i=0;i<5;i++) COA_OLD[i] = new AIB0010_COA_OLD();
    }
}
