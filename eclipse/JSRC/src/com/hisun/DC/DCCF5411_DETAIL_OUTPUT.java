package com.hisun.DC;

public class DCCF5411_DETAIL_OUTPUT {
    public String DTL_OVR_NO = " ";
    public String DTL_AGR_NO = " ";
    public String DTL_CI_NAME = " ";
    public char FILLER4 = 0X02;
    public String DTL_PROD_CODE = " ";
    public short DTL_PROD_LVL = 0;
    public String DTL_CCY = " ";
    public char DTL_CCY_TYPE = ' ';
    public int DTL_PROCS_DATE = 0;
    public int DTL_PROCL_DATE = 0;
    public char DTL_PROC_STS = ' ';
    public char DTL_PROC_TYP = ' ';
    public char DTL_PERM_KND = ' ';
    public int DTL_TRIG_MD = 0;
    public short DTL_PERD = 0;
    public char DTL_TRIG_TMS = ' ';
    public double DTL_TRM_AMT = 0;
    public char FILLER18 = 0X01;
    public char DTL_TRIG_MTH = ' ';
    public char DTL_INT_MTH = ' ';
    public double DTL_MRM_AMT = 0;
    public char FILLER25 = 0X01;
    public double DTL_TRC_AMT = 0;
    public char FILLER27 = 0X01;
    public double DTL_TRPCT_S = 0;
    public char FILLER29 = 0X01;
    public String DTL_TRC_TDAC = " ";
    public String DTL_TERM = " ";
    public int DTL_NEXT_DT = 0;
    public int CRT_DATE = 0;
    public char DTL_DRAW_FLG = ' ';
    public String DTL_DRAW_USE = " ";
    public double DTL_DRAW_MAX = 0;
    public char FILLER37 = 0X01;
    public double DTL_DRAW_MIN = 0;
    public char FILLER39 = 0X01;
    public double DTL_DRAW_AMT = 0;
    public char FILLER41 = 0X01;
    public double DTL_LIMT_AMT = 0;
    public char FILLER43 = 0X01;
    public DCCF5411_DTL_LNK_INFO[] DTL_LNK_INFO = new DCCF5411_DTL_LNK_INFO[5];
    public DCCF5411_DETAIL_OUTPUT() {
        for (int i=0;i<5;i++) DTL_LNK_INFO[i] = new DCCF5411_DTL_LNK_INFO();
    }
}
