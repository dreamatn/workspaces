package com.hisun.BP;

import com.hisun.LN.LAPBL_BAL_INFO;
import com.hisun.LN.LAPBL_GLM_INFO;
import com.hisun.LN.LAPBL_ITM_INFO;

public class BPRLAPBL {
    String APP = " ";
    int BRH_OLD = 0;
    int BRH_NEW = 0;
    String CNTR_TYPE = " ";
    String PROD_TYPE = " ";
    String PROD_TYPE_REL = " ";
    int PART_NO = 0;
    String CCY = " ";
    String AC = " ";
    String REF_NO = " ";
    String CI_NO = " ";
    LAPBL_GLM_INFO[] GLM_INFO = new LAPBL_GLM_INFO[10];
    LAPBL_ITM_INFO[] ITM_INFO = new LAPBL_ITM_INFO[10];
    LAPBL_BAL_INFO[] BAL_INFO = new LAPBL_BAL_INFO[76];
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
    BPRAPBL_LAPBL_REDEFINES32 LAPBL_REDEFINES32 = new BPRAPBL_LAPBL_REDEFINES32();
    public BPRLAPBL() {
        for (int i=0;i<10;i++) GLM_INFO[i] = new LAPBL_GLM_INFO();
        for (int i=0;i<10;i++) ITM_INFO[i] = new LAPBL_ITM_INFO();
        for (int i=0;i<76;i++) BAL_INFO[i] = new LAPBL_BAL_INFO();
    }
}
