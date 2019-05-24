package com.hisun.GL;

public class GLCBDF01_VARIABLES {
    public GLCBDF01_ERR_MSG ERR_MSG = new GLCBDF01_ERR_MSG();
    public GLCBDF01_CNT_INFO CNT_INFO = new GLCBDF01_CNT_INFO();
    public long READ_PROC_CNT = 0;
    public long WRITE_PROC_CNT = 0;
    public long ERROR_PROC_CNT = 0;
    public int RESULT = 0;
    public int REMAIN = 0;
    public int PRDM_CNT = 0;
    public int CNGL_CNT = 0;
    public int CNGM_CNT = 0;
    public int PARM_CNT = 0;
    public int GLM_CNT = 0;
    public String ERR_RSN = " ";
    public char INNER_PRDT_IND = ' ';
    public int GL_MST_NO = 0;
    public int AC_MSTNO = 0;
    public String LAST_MOD_NO = " ";
    public GLCBDF01_LAST_KEY LAST_KEY = new GLCBDF01_LAST_KEY();
    public GLCBDF01_CURR_KEY CURR_KEY = new GLCBDF01_CURR_KEY();
    public GLCBDF01_COMP_KEY COMP_KEY = new GLCBDF01_COMP_KEY();
    public String MOD_NO = " ";
    public int APBL_BR = 0;
    public int GLM = 0;
    public String ITM_NO = " ";
    public int ITM_SEQ = 0;
    public String CNTR_TYPE = " ";
    public GLCBDF01_DATA[] DATA = new GLCBDF01_DATA[10];
    public GLCBDF01_PARM_PRDM[] PARM_PRDM = new GLCBDF01_PARM_PRDM[200];
    public GLCBDF01_PARM_CNGL[] PARM_CNGL = new GLCBDF01_PARM_CNGL[3000];
    public GLCBDF01_PARM_CNGM[] PARM_CNGM = new GLCBDF01_PARM_CNGM[6000];
    public GLCBDF01_GL_MST_INFO[] GL_MST_INFO = new GLCBDF01_GL_MST_INFO[10];
    public GLCBDF01_VARIABLES() {
        for (int i=0;i<10;i++) DATA[i] = new GLCBDF01_DATA();
        for (int i=0;i<200;i++) PARM_PRDM[i] = new GLCBDF01_PARM_PRDM();
        for (int i=0;i<3000;i++) PARM_CNGL[i] = new GLCBDF01_PARM_CNGL();
        for (int i=0;i<6000;i++) PARM_CNGM[i] = new GLCBDF01_PARM_CNGM();
        for (int i=0;i<10;i++) GL_MST_INFO[i] = new GLCBDF01_GL_MST_INFO();
    }
}
