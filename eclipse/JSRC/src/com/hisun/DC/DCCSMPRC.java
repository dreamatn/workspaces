package com.hisun.DC;

public class DCCSMPRC {
    public char FUNC = ' ';
    public String OVR_NO = " ";
    public String AC_NO = " ";
    public String PROD_CODE = " ";
    public DCCSMPRC_CNTR_INF[] CNTR_INF = new DCCSMPRC_CNTR_INF[20];
    public int PROCS_DT = 0;
    public int PROCL_DT = 0;
    public String SMR = " ";
    public char FUND_TYP = ' ';
    public char AMTC_MTH = ' ';
    public DCCSMPRC_COMP_INFO[] COMP_INFO = new DCCSMPRC_COMP_INFO[5];
    public double PROC_ACP = 0;
    public double LN_PCTS = 0;
    public double GDD_PCTS = 0;
    public DCCSMPRC_AC_INFO[] AC_INFO = new DCCSMPRC_AC_INFO[5];
    public char FUNC_AC = ' ';
    public DCCSMPRC() {
        for (int i=0;i<20;i++) CNTR_INF[i] = new DCCSMPRC_CNTR_INF();
        for (int i=0;i<5;i++) COMP_INFO[i] = new DCCSMPRC_COMP_INFO();
        for (int i=0;i<5;i++) AC_INFO[i] = new DCCSMPRC_AC_INFO();
    }
}
