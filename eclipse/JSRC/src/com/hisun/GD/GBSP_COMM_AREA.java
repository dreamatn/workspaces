package com.hisun.GD;

public class GBSP_COMM_AREA {
    String AP_MMO = " ";
    String AP_EXT_MMO = " ";
    GBSP_MSG_PROC_AREA MSG_PROC_AREA = new GBSP_MSG_PROC_AREA();
    char DBIO_FLG = ' ';
    char CANCEL_IND = ' ';
    char REEN_IND = ' ';
    long JRN_NO = 0;
    long CANCEL_JRN_NO = 0;
    String SERV_CODE = " ";
    GBSP_TR_ID TR_ID = new GBSP_TR_ID();
    String TR_MMO = " ";
    String REQ_SYSTEM = " ";
    String CHNL = " ";
    String TERM_ID = " ";
    int TR_DATE = 0;
    int TR_TIME = 0;
    int AC_DATE = 0;
    int POST_DATE = 0;
    String TL_ID = " ";
    String VCH_NO = " ";
    int HQT_BANK = 0;
    String TR_BANK = " ";
    GBSP_BR_DP BR_DP = new GBSP_BR_DP();
    String PROD_CODE = " ";
    short SERV_SEQ = 0;
    int CALL_SEQ = 0;
    short PGM_NO = 0;
    GBSP_ERR_MSG_ID ERR_MSG_ID = new GBSP_ERR_MSG_ID();
    String ERR_MSG_INFO = " ";
    char FEE_IND = ' ';
    char FEE_DATA_IND = ' ';
    char REVERSAL_IND = ' ';
    char PRDT_FUNC_CTRL_IND = ' ';
    String SUP1_ID = " ";
    String SUP2_ID = " ";
    char BUSS_MODE = ' ';
    char MODIFY_FLAG = ' ';
    String BSP_FLG = " ";
    char SYS_OPT = ' ';
    String SERVER_ID = " ";
    long PROCESS_ID = 0;
    char DEADLOCK_FLG = ' ';
    String FILLER11 = " ";
    String DATE_IND = " ";
    Object BAT_AREA_PTR;
    short CALL_DEPTH = 0;
    char JRN_IN_USE = ' ';
    char MST_IN_USE = ' ';
    char FILLER21 = ' ';
    char EXCP_FLG = ' ';
    char AGENT_FLG = ' ';
    short PARM_CHANGED = 0;
    Object CWA_PTR;
    char PARM_IND = ' ';
    String TOR_SYSID = " ";
    short MOBILE_MSG_SEQ = 0;
    String AOR_SYSID = " ";
    short AI_BATCH_NO = 0;
    int CLEAR_DATE = 0;
    String REQ_CHNL2 = " ";
    String CHNL_DTL = " ";
    char DB_UPD_FLG = ' ';
    String PROC = " ";
    GBSP_TRC TRC = new GBSP_TRC();
    String ENV_TYPE = " ";
    String FILLER31 = " ";
    GBSP_TRC_ARR[] TRC_ARR = new GBSP_TRC_ARR[10];
    String MBANK_NO = " ";
    char LOF_FLG = ' ';
    long JRN_SEQ = 0;
    String PARM_BK = " ";
    String FILLER41 = " ";
    Object MEM_PTR;
    Object RDS_PTR;
    Object CITBAS_PTR;
    Object CITACR_PTR;
    Object PERFORMACE_PTR;
    Object SC_MMSG_PTR;
    Object AWA_AREA_PTR;
    Object AWAC_AREA_PTR;
    Object TLT_AREA_PTR;
    Object TR_BR_PTR;
    Object BANK_AREA_PTR;
    public GBSP_COMM_AREA() {
        for (int i=0;i<10;i++) TRC_ARR[i] = new GBSP_TRC_ARR();
    }
}