package com.hisun.LN;

public class LNZSPAIP_WS_VARIABLES {
    LNZSPAIP_WS_ERR_MSG WS_ERR_MSG = new LNZSPAIP_WS_ERR_MSG();
    int I = 0;
    short J = 0;
    double TOT_SCH_AMT = 0;
    short TOT_SCH_TERM = 0;
    short PHS_TOT_TERM = 0;
    short MOD_TOT_TERM = 0;
    double TOT_AMT = 0;
    double REL_TOT_AMT = 0;
    double REM_AMT = 0;
    int BEG_DATE = 0;
    int END_DATE = 0;
    int LAST_I_DUE_DT = 0;
    short PRE_TOT_TENORS = 0;
    short TOT_TENORS = 0;
    double TOT_PRIN = 0;
    short TENORS = 0;
    short CUR_PHS_TENORS = 0;
    int SCH_DT = 0;
    int SCH_STRT_DT = 0;
    int SCH_END_DT = 0;
    String FORM_CODE = " ";
    int YYYYMMDD = 0;
    LNZSPAIP_REDEFINES30 REDEFINES30 = new LNZSPAIP_REDEFINES30();
    int OUT_DATE = 0;
    double N_RATE = 0;
    double PRIN_AMT = 0;
    String CONTRACT_NO = " ";
    LNZSPAIP_WS_PRM_KEY WS_PRM_KEY = new LNZSPAIP_WS_PRM_KEY();
    LNZSPAIP_WS_PAY_MTH_AREA[] WS_PAY_MTH_AREA = new LNZSPAIP_WS_PAY_MTH_AREA[4];
    LNZSPAIP_WS_INST_MTH_AREA[] WS_INST_MTH_AREA = new LNZSPAIP_WS_INST_MTH_AREA[3];
    LNZSPAIP_WS_DATE WS_DATE = new LNZSPAIP_WS_DATE();
    public LNZSPAIP_WS_VARIABLES() {
        for (int i=0;i<4;i++) WS_PAY_MTH_AREA[i] = new LNZSPAIP_WS_PAY_MTH_AREA();
        for (int i=0;i<3;i++) WS_INST_MTH_AREA[i] = new LNZSPAIP_WS_INST_MTH_AREA();
    }
}
