package com.hisun.BP;

public class BPRAPBL {
    public String APP = " ";
    public int BRH_OLD = 0;
    public int BRH_NEW = 0;
    public String CNTR_TYPE = " ";
    public String PROD_TYPE = " ";
    public String PROD_TYPE_REL = " ";
    public int PART_NO = 0;
    public String CCY = " ";
    public String AC = " ";
    public String REF_NO = " ";
    public String CI_NO = " ";
    public BPRAPBL_GLM_INFO[] GLM_INFO = new BPRAPBL_GLM_INFO[10];
    public BPRAPBL_ITM_INFO[] ITM_INFO = new BPRAPBL_ITM_INFO[10];
    public BPRAPBL_BAL_INFO[] BAL_INFO = new BPRAPBL_BAL_INFO[76];
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
    public BPRAPBL_REDEFINES32 REDEFINES32 = new BPRAPBL_REDEFINES32();
    public BPRAPBL() {
        for (int i=0;i<10;i++) GLM_INFO[i] = new BPRAPBL_GLM_INFO();
        for (int i=0;i<10;i++) ITM_INFO[i] = new BPRAPBL_ITM_INFO();
        for (int i=0;i<76;i++) BAL_INFO[i] = new BPRAPBL_BAL_INFO();
    }
}
