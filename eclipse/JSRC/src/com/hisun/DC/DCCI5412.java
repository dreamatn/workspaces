package com.hisun.DC;

public class DCCI5412 {
    public char FUNC_M = ' ';
    public String AGR_NO = " ";
    public String CI_NAME = " ";
    public String PROD_CDE = " ";
    public short PROD_LVL = 0;
    public String CCY = " ";
    public char CCY_TYP = ' ';
    public int PROCS_DT = 0;
    public int PROCL_DT = 0;
    public char PROC_TYP = ' ';
    public char PERM_KND = ' ';
    public short TRIG_MD = 0;
    public short PERD = 0;
    public char TRIG_TMS = ' ';
    public double TRM_AMT = 0;
    public char TRIG_MTH = ' ';
    public char INT_MTH = ' ';
    public double MRM_AMT = 0;
    public double TRC_AMT = 0;
    public double TRPCT_S = 0;
    public String TR_AGRNO = " ";
    public String DEP_TERM = " ";
    public char DRAW_FLG = ' ';
    public String DRAW_USE = " ";
    public double DRAW_MAX = 0;
    public double DRAW_MIN = 0;
    public double DRAW_AMT = 0;
    public double LIMT_AMT = 0;
    public DCCI5412_LNK_INFO[] LNK_INFO = new DCCI5412_LNK_INFO[5];
    public DCCI5412() {
        for (int i=0;i<5;i++) LNK_INFO[i] = new DCCI5412_LNK_INFO();
    }
}
