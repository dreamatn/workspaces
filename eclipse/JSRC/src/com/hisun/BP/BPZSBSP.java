package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBSP {
    boolean pgmRtn = false;
    short WS_CNT = 0;
    short WS_POS = 0;
    int RESP = 0;
    String WS_PROC_NAME = " ";
    String WS_PROC_DATA = " ";
    String WS_PROC_DATA2 = " ";
    String WS_PROC_DATA3 = " ";
    BPZSBSP_WS_MSGID WS_MSGID = new BPZSBSP_WS_MSGID();
    String BPZSBSP_FILLER1 = "MO";
    String WK_ID2 = "BSP";
    short WK_DD = 0;
    String BPZSBSP_FILLER1 = "//         JOB ,'SCBSUBJB',CLASS=A,MSGCLASS=X,";
    String BPZSBSP_FILLER2 = "//         MSGLEVEL=(1,1)";
    String BPZSBSP_FILLER3 = "//         SET LVL=";
    String WK_JCL_DSN = " ";
    String BPZSBSP_FILLER5 = ",P=";
    String WK_JCL_DSN2 = " ";
    String BPZSBSP_FILLER7 = ".BAT";
    String BPZSBSP_FILLER8 = "                                      ";
    String BPZSBSP_FILLER9 = "//LIBSRCH  JCLLIB ORDER=(&LVL..IBS.PROCLIB,";
    String BPZSBSP_FILLER10 = "//         &LVL..IBS.PARMLIB,";
    String BPZSBSP_FILLER11 = "//         &LVL..BAT.PROCLIB,&LVL..BAT.PARMLIB)";
    String BPZSBSP_FILLER12 = "//         INCLUDE MEMBER=SCPENV";
    String BPZSBSP_FILLER13 = "//JOBLIB   DD  DSN=&LVL..BAT.LOADLIB,DISP=SHR";
    String BPZSBSP_FILLER14 = "//         DD DSN=&DBLOAD,DISP=SHR";
    String WK_INP_DATA = "//         SET ";
    String WK_INP_DATA2 = "//         ";
    String WK_INP_DATA3 = "//         ";
    String WK_EXEC_PROC = "//         EXEC PROC=";
    String BPZSBSP_FILLER19 = "//*";
