package com.hisun.PY;

public class QPRD1_OTH_PRM {
    char PRD_TYP = ' ';
    short MAX_NUM = 0;
    int PAY_GRCE = 0;
    String PAY_PERD = " ";
    char PLAN_FLG = ' ';
    String MIN_TERM = " ";
    String MAX_TERM = " ";
    char INT_PRD1 = ' ';
    char INT_PRD2 = ' ';
    char INT_PRD3 = ' ';
    char COMP_FLG = ' ';
    char INTP_FLG = ' ';
    char PENA_FLG = ' ';
    int UNIT_DAY = 0;
    String INT_PERD = " ";
    int MAX_GRCE = 0;
    char CCY_TYP = ' ';
    char DOCU_FLG = ' ';
    String REF_CCY = " ";
    QPRD1_AVA_CCY_ARRY[] AVA_CCY_ARRY = new QPRD1_AVA_CCY_ARRY[16];
    QPRD1_CCY_INF[] CCY_INF = new QPRD1_CCY_INF[25];
    char NO_NOTIFY_FLG = ' ';
    char BAL_FLG = ' ';
    char MID_FLG = ' ';
    char DELA_FLG = ' ';
    char CPRA_TYP = ' ';
    char ACTI_FLG = ' ';
    char FRZ_FLG = ' ';
    public QPRD1_OTH_PRM() {
        for (int i=0;i<16;i++) AVA_CCY_ARRY[i] = new QPRD1_AVA_CCY_ARRY();
        for (int i=0;i<25;i++) CCY_INF[i] = new QPRD1_CCY_INF();
    }
}
