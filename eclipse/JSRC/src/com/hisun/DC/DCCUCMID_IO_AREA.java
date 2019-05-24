package com.hisun.DC;

public class DCCUCMID_IO_AREA {
    public char FUNC_M = ' ';
    public String AC_NO = " ";
    public String AC_NAME = " ";
    public String PROD_CDE = " ";
    public String CCY = " ";
    public char CCY_TYP = ' ';
    public String OVR_NO = " ";
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
    public String TD_PROD = " ";
    public String TD_TERM = " ";
    public String AGR_NO = " ";
    public String TR_AGRNO = " ";
    public short PROD_LVL = 0;
    public String PROC_TYP = " ";
    public short TRIG_MD = 0;
    public int TRIG_TMS = 0;
    public char INT_MTH = ' ';
    public String TRC_TDAC = " ";
    public char DRAW_FLG = ' ';
    public String DRAW_USE = " ";
    public double DRAW_MAX = 0;
    public double DRAW_MIN = 0;
    public double DRAW_AMT = 0;
    public double LIMT_AMT = 0;
    public DCCUCMID_LNK_INFO[] LNK_INFO = new DCCUCMID_LNK_INFO[5];
    public DCCUCMID_IO_AREA() {
        for (int i=0;i<5;i++) LNK_INFO[i] = new DCCUCMID_LNK_INFO();
    }
}
