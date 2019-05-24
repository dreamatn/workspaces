package com.hisun.TD;

import com.hisun.BA.BACSTRPD_TRPD_REDEFINES43;

public class TRPD_OUTPUT_DATA {
    String APP = " ";
    int BRH_OLD = 0;
    int BRH_NEW = 0;
    String CNTR_TYPE = " ";
    String PROD_TYPE = " ";
    String CCY = " ";
    String AC = " ";
    String REF_NO = " ";
    String CI_NO = " ";
    TRPD_GLM_INFO[] GLM_INFO = new TRPD_GLM_INFO[10];
    TRPD_ITM_INFO[] ITM_INFO = new TRPD_ITM_INFO[10];
    TRPD_BAL_INFO[] BAL_INFO = new TRPD_BAL_INFO[76];
    double PRI_AMT = 0;
    int IB_BKD_DT = 0;
    double DY_AMT = 0;
    double DY_AMT_ADJ = 0;
    double DY_AMT_POST = 0;
    double DY_AMT_TOT = 0;
    double DY_AMT_TOT_ALL = 0;
    int VAL_DATE = 0;
    int MATU_DATE = 0;
    double INT_RATE = 0;
    char REMARK = ' ';
    char DORMANT_FLG = ' ';
    String FLR = " ";
    BACSTRPD_TRPD_REDEFINES43 TRPD_REDEFINES43 = new BACSTRPD_TRPD_REDEFINES43();
    String O_DRAWDOWN_MTH = " ";
    public TRPD_OUTPUT_DATA() {
        for (int i=0;i<10;i++) GLM_INFO[i] = new TRPD_GLM_INFO();
        for (int i=0;i<10;i++) ITM_INFO[i] = new TRPD_ITM_INFO();
        for (int i=0;i<76;i++) BAL_INFO[i] = new TRPD_BAL_INFO();
    }
}
