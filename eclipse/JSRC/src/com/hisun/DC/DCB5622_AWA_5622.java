package com.hisun.DC;

public class DCB5622_AWA_5622 {
    public String AGR_NO = " ";
    public short AGR_NO_NO = 0;
    public String CI_NAME = " ";
    public short CI_NAME_NO = 0;
    public String PROD_CDE = " ";
    public short PROD_CDE_NO = 0;
    public short PROD_LVL = 0;
    public short PROD_LVL_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public char CCY_TYP = ' ';
    public short CCY_TYP_NO = 0;
    public int PROCS_DT = 0;
    public short PROCS_DT_NO = 0;
    public int PROCL_DT = 0;
    public short PROCL_DT_NO = 0;
    public char PROC_TYP = ' ';
    public short PROC_TYP_NO = 0;
    public char PERM_KND = ' ';
    public short PERM_KND_NO = 0;
    public short TRIG_MD = 0;
    public short TRIG_MD_NO = 0;
    public short PERD = 0;
    public short PERD_NO = 0;
    public char TRIG_TMS = ' ';
    public short TRIG_TMS_NO = 0;
    public double TRM_AMT = 0;
    public short TRM_AMT_NO = 0;
    public char TRIG_MTH = ' ';
    public short TRIG_MTH_NO = 0;
    public char INT_MTH = ' ';
    public short INT_MTH_NO = 0;
    public double MRM_AMT = 0;
    public short MRM_AMT_NO = 0;
    public double TRC_AMT = 0;
    public short TRC_AMT_NO = 0;
    public double TRPCT_S = 0;
    public short TRPCT_S_NO = 0;
    public String TR_AGRNO = " ";
    public short TR_AGRNO_NO = 0;
    public String DEP_TERM = " ";
    public short DEP_TERM_NO = 0;
    public char DRAW_FLG = ' ';
    public short DRAW_FLG_NO = 0;
    public String DRAW_USE = " ";
    public short DRAW_USE_NO = 0;
    public double DRAW_MAX = 0;
    public short DRAW_MAX_NO = 0;
    public double DRAW_MIN = 0;
    public short DRAW_MIN_NO = 0;
    public double DRAW_AMT = 0;
    public short DRAW_AMT_NO = 0;
    public double LIMT_AMT = 0;
    public short LIMT_AMT_NO = 0;
    public DCB5622_LNK_INFO[] LNK_INFO = new DCB5622_LNK_INFO[5];
    public DCB5622_AWA_5622() {
        for (int i=0;i<5;i++) LNK_INFO[i] = new DCB5622_LNK_INFO();
    }
}