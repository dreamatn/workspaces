package com.hisun.LN;

public class LNCSTRPD_OUTPUT_DATA {
    public String APP = " ";
    public int BRH_OLD = 0;
    public int BRH_NEW = 0;
    public String CNTR_TYPE = " ";
    public String PROD_TYPE = " ";
    public String CCY = " ";
    public String AC = " ";
    public String REF_NO = " ";
    public String CI_NO = " ";
    public LNCSTRPD_GLM_INFO[] GLM_INFO = new LNCSTRPD_GLM_INFO[10];
    public LNCSTRPD_ITM_INFO[] ITM_INFO = new LNCSTRPD_ITM_INFO[10];
    public LNCSTRPD_BAL_INFO[] BAL_INFO = new LNCSTRPD_BAL_INFO[76];
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
    public LNCSTRPD_REDEFINES44 REDEFINES44 = new LNCSTRPD_REDEFINES44();
    public LNCSTRPD_OUTPUT_DATA() {
        for (int i=0;i<10;i++) GLM_INFO[i] = new LNCSTRPD_GLM_INFO();
        for (int i=0;i<10;i++) ITM_INFO[i] = new LNCSTRPD_ITM_INFO();
        for (int i=0;i<76;i++) BAL_INFO[i] = new LNCSTRPD_BAL_INFO();
    }
}
