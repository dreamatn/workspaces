package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

public class SOZSMGJ {
    boolean pgmRtn = false;
    Object CWA_PTR;
    String WS_MSGID = " ";
    int RESP = 0;
    long WK_TIME = 0;
    short WK_IDX = 0;
    short WK_POS = 0;
    int WK_DATE = 0;
    int WK_YYYY_MM_DD = 0;
    int WK_HH_MM_SS = 0;
    int WK_AC_DATE = 0;
    String SOZSMGJ_FILLER1 = "SO";
    short WK_DD = 0;
    String WK_ID2 = "EOD ";
    String SOZSMGJ_FILLER1 = "//         JOB ,'SOBSUBJB',CLASS=A,MSGCLASS=X,";
    String SOZSMGJ_FILLER2 = "//         MSGLEVEL=(1,1)";
    String SOZSMGJ_FILLER3 = "//         SET LVL=";
    String WK_JCL_DSN = " ";
    String SOZSMGJ_FILLER5 = "                                                     ";
    String SOZSMGJ_FILLER6 = "//LIBSRCH  JCLLIB ORDER=(&LVL..IBS.PROCLIB,";
    String SOZSMGJ_FILLER7 = "//         &LVL..IBS.PARMLIB,";
    String SOZSMGJ_FILLER8 = "//         &LVL..BAT.PROCLIB,&LVL..BAT.PARMLIB)";
    String SOZSMGJ_FILLER9 = "//JOBLIB   DD  DSN=&LVL..BAT.LOADLIB,DISP=SHR";
    String SOZSMGJ_FILLER10 = "//         INCLUDE MEMBER=SCPENV";
    String WK_INP_DATA = "//         SET ";
    String WK_EXEC_PROC = "//         EXEC PROC=";
    String SOZSMGJ_FILLER13 = "//*";
