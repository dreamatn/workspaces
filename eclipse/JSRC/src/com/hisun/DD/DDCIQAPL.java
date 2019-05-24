package com.hisun.DD;

public class DDCIQAPL {
    public String APP = " ";
    public int BRH_OLD = 0;
    public int BRH_NEW = 0;
    public String CNTR_TYPE = " ";
    public String PROD_TYPE = " ";
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public String AC = " ";
    public String REF_NO = " ";
    public String CI_NO = " ";
    public DDCIQAPL_GLM_INFO[] GLM_INFO = new DDCIQAPL_GLM_INFO[10];
    public DDCIQAPL_ITM_INFO[] ITM_INFO = new DDCIQAPL_ITM_INFO[10];
    public DDCIQAPL_BAL_INFO[] BAL_INFO = new DDCIQAPL_BAL_INFO[76];
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
    public DDCIQAPL_REDEFINES31 REDEFINES31 = new DDCIQAPL_REDEFINES31();
    public String FRG_CODE = " ";
    public DDCIQAPL() {
        for (int i=0;i<10;i++) GLM_INFO[i] = new DDCIQAPL_GLM_INFO();
        for (int i=0;i<10;i++) ITM_INFO[i] = new DDCIQAPL_ITM_INFO();
        for (int i=0;i<76;i++) BAL_INFO[i] = new DDCIQAPL_BAL_INFO();
    }
}
