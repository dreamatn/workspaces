package com.hisun.BP;

public class BPCDEF04_VARIABLES {
    public String ERR_MSG = " ";
    public BPCDEF04_GL_INF[] GL_INF = new BPCDEF04_GL_INF[10];
    public String SUS_ITM_MD = " ";
    public int SUS_SEQ_MD = 0;
    public String SUS_ITM_MC = " ";
    public int SUS_SEQ_MC = 0;
    public String SUS_ITM_RD = " ";
    public int SUS_SEQ_RD = 0;
    public String SUS_ITM_RC = " ";
    public int SUS_SEQ_RC = 0;
    public String SUS_RSN = " ";
    public short SUS_BOOK = 0;
    public int ITM_SEQ = 0;
    public String STR = " ";
    public String MOD_NO = " ";
    public int VCNT = 0;
    public int BCNT = 0;
    public int CCNT = 0;
    public int ICNT = 0;
    public int ECNT = 0;
    public int ACNT = 0;
    public int SCNT = 0;
    public int MCNT = 0;
    public int CUR_BCNT = 0;
    public int I = 0;
    public int B = 0;
    public int J = 0;
    public int CNT = 0;
    public int CNT1 = 0;
    public int PRDM_CNT = 0;
    public int CNGL_CNT = 0;
    public int CNGM_CNT = 0;
    public int PARM_CNT = 0;
    public int GLM_CNT = 0;
    public int EVENT_CNT = 0;
    public short CUR_EVENT_CNT = 0;
    public int VCH_LENGTH = 0;
    public int LST_MST1 = 0;
    public int LST_MST2 = 0;
    public String LST_VCH_SET_NO = " ";
    public char INNER_PRDT_IND = ' ';
    public int GL_MST_NO = 0;
    public int READ_BPQWVET_CNT = 0;
    public int WRITE_BPQWVETT_CNT = 0;
    public int WRITE_BPQBVCH_CNT = 0;
    public int WRITE_GLQVETE_CNT = 0;
    public String CNTR_TYPE = " ";
    public int RESULT = 0;
    public int REMAIN = 0;
    public BPCDEF04_PARM_CNGL[] PARM_CNGL = new BPCDEF04_PARM_CNGL[2000];
    public BPCDEF04_BOOK_PARM[] BOOK_PARM = new BPCDEF04_BOOK_PARM[10];
    public BPCDEF04_CUR_BOOK CUR_BOOK = new BPCDEF04_CUR_BOOK();
    public BPCDEF04_CUR_GLMST CUR_GLMST = new BPCDEF04_CUR_GLMST();
    public BPCDEF04_GLMST_GROUP[] GLMST_GROUP = new BPCDEF04_GLMST_GROUP[2000];
    public BPCDEF04_CURR_GLMST_INFO CURR_GLMST_INFO = new BPCDEF04_CURR_GLMST_INFO();
    public BPCDEF04_EVENT_VCH_GROUP[] EVENT_VCH_GROUP = new BPCDEF04_EVENT_VCH_GROUP[1000];
    public BPCDEF04_EVENT_VCH_TEMP EVENT_VCH_TEMP = new BPCDEF04_EVENT_VCH_TEMP();
    public BPCDEF04_CUR_EVN_VCH CUR_EVN_VCH = new BPCDEF04_CUR_EVN_VCH();
    public BPCDEF04_CUR_EVN_AMT_DETAIL[] CUR_EVN_AMT_DETAIL = new BPCDEF04_CUR_EVN_AMT_DETAIL[76];
    public BPCDEF04_INTER_AC INTER_AC = new BPCDEF04_INTER_AC();
    public char VCH_AC_FLG = ' ';
    public BPCDEF04_VCH VCH = new BPCDEF04_VCH();
    public BPCDEF04_DATE_COMPUTE DATE_COMPUTE = new BPCDEF04_DATE_COMPUTE();
    public BPCDEF04_CLS_INFO[] CLS_INFO = new BPCDEF04_CLS_INFO[400];
    public BPCDEF04_BAL_INFO[] BAL_INFO = new BPCDEF04_BAL_INFO[400];
    public BPCDEF04_PARM_PRDM[] PARM_PRDM = new BPCDEF04_PARM_PRDM[200];
    public BPCDEF04_CURR_KEY CURR_KEY = new BPCDEF04_CURR_KEY();
    public BPCDEF04_COMP_KEY COMP_KEY = new BPCDEF04_COMP_KEY();
    public char[] N_FLG = new char[100];
    public char LIMIT_FLG = ' ';
    public char EWAB_FLG = ' ';
    public char GUP_FLG = ' ';
    public char BOOK_FLAG = ' ';
    public char FST_FLG = ' ';
    public char N_PNT_FLG = ' ';
    public char FND_ORG_FLG = ' ';
    public char FND_AUTH_FLG = ' ';
    public char AUTH_RECOND_FLG = ' ';
    public char DB2_REC_STATUS = ' ';
    public char ITM_BAL_SN_FLG = ' ';
    public char ITM_FND_FLG = ' ';
    public char ITM_LVL_FLG = ' ';
    public char MEMO_ITM_FLG = ' ';
    public char CLS_KEY_FLG = ' ';
    public char BKPM_FLG = ' ';
    public char SUS_FLG = ' ';
    public char BAL_KEY_FLG = ' ';
    public char WVET_FLG = ' ';
    public char ERR_FLG = ' ';
    public char SKIP_FLG = ' ';
    public char PARM_EOF_FLG = ' ';
    public char CNGM_EOF_FLG = ' ';
    public char PRDM_FOUND_FLG = ' ';
    public char CNGL_FOUND_FLG = ' ';
    public char CNGM_FOUND_FLG = ' ';
    public char EVET_FOUND_FLG = ' ';
    public char GLM_FOUND_FLG = ' ';
    public char MATCH_FLG = ' ';
    public BPCDEF04_VARIABLES() {
        for (int i=0;i<10;i++) GL_INF[i] = new BPCDEF04_GL_INF();
        for (int i=0;i<2000;i++) PARM_CNGL[i] = new BPCDEF04_PARM_CNGL();
        for (int i=0;i<10;i++) BOOK_PARM[i] = new BPCDEF04_BOOK_PARM();
        for (int i=0;i<2000;i++) GLMST_GROUP[i] = new BPCDEF04_GLMST_GROUP();
        for (int i=0;i<1000;i++) EVENT_VCH_GROUP[i] = new BPCDEF04_EVENT_VCH_GROUP();
        for (int i=0;i<76;i++) CUR_EVN_AMT_DETAIL[i] = new BPCDEF04_CUR_EVN_AMT_DETAIL();
        for (int i=0;i<400;i++) CLS_INFO[i] = new BPCDEF04_CLS_INFO();
        for (int i=0;i<400;i++) BAL_INFO[i] = new BPCDEF04_BAL_INFO();
        for (int i=0;i<200;i++) PARM_PRDM[i] = new BPCDEF04_PARM_PRDM();
    }
}