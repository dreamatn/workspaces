package com.hisun.DC;

public class DCCSUATP_IO_AREA {
    public char FUNC_MOD = ' ';
    public String AC_NO = " ";
    public String DR_CARD = " ";
    public String AGR_NO = " ";
    public String OVR_NO = " ";
    public String PROD_CDE = " ";
    public String CI_NAME = " ";
    public String CCY = " ";
    public char CCY_TYP = ' ';
    public int PROCS_DT = 0;
    public int PROCL_DT = 0;
    public char PROC_STS = ' ';
    public String SMR = " ";
    public char PERM_KND = ' ';
    public short PERD = 0;
    public double TRM_AMT = 0;
    public char TRIG_MTH = ' ';
    public double TRPCT_S = 0;
    public double MRM_AMT = 0;
    public double TRC_AMT = 0;
    public char DH_FLG = ' ';
    public String TRC_TD_AC = " ";
    public String TRC_CARD = " ";
    public String TD_PROD = " ";
    public String DEP_TERM = " ";
    public String PROC_TYP = " ";
    public short TRIG_MD = 0;
    public int TRIG_TMS = 0;
    public char INT_MTH = ' ';
    public String TR_AGRNO = " ";
    public char DRAW_FLG = ' ';
    public String DRAW_USE = " ";
    public double DRAW_MAX = 0;
    public double DRAW_MIN = 0;
    public double DRAW_AMT = 0;
    public double LIMT_AMT = 0;
    public DCCSUATP_LNK_INFO[] LNK_INFO = new DCCSUATP_LNK_INFO[5];
    public DCCSUATP_IO_AREA() {
        for (int i=0;i<5;i++) LNK_INFO[i] = new DCCSUATP_LNK_INFO();
    }
}
