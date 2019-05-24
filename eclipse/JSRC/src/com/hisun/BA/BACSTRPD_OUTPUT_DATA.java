package com.hisun.BA;

public class BACSTRPD_OUTPUT_DATA {
    public String APP = " ";
    public int BRH_OLD = 0;
    public int BRH_NEW = 0;
    public String CNTR_TYPE = " ";
    public String PROD_TYPE = " ";
    public String CCY = " ";
    public String AC = " ";
    public String REF_NO = " ";
    public String CI_NO = " ";
    public BACSTRPD_GLM_INFO[] GLM_INFO = new BACSTRPD_GLM_INFO[10];
    public BACSTRPD_ITM_INFO[] ITM_INFO = new BACSTRPD_ITM_INFO[10];
    public BACSTRPD_BAL_INFO[] BAL_INFO = new BACSTRPD_BAL_INFO[76];
    public double PRI_AMT = 0;
    public int IB_BKD_DT = 0;
    public double DY_AMT = 0;
    public double DY_AMT_ADJ = 0;
    public double DY_AMT_POST = 0;
    public double DY_AMT_TOT = 0;
    public double DY_AMT_TOT_ALL = 0;
    public int VAL_DATE = 0;
    public int MATU_DATE = 0;
    public double INT_RATE = 0;
    public char REMARK = ' ';
    public char DORMANT_FLG = ' ';
    public String FLR = " ";
    public BACSTRPD_REDEFINES43 REDEFINES43 = new BACSTRPD_REDEFINES43();
    public String O_DRAWDOWN_MTH = " ";
    public BACSTRPD_OUTPUT_DATA() {
        for (int i=0;i<10;i++) GLM_INFO[i] = new BACSTRPD_GLM_INFO();
        for (int i=0;i<10;i++) ITM_INFO[i] = new BACSTRPD_ITM_INFO();
        for (int i=0;i<76;i++) BAL_INFO[i] = new BACSTRPD_BAL_INFO();
    }
}
