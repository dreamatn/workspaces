package com.hisun.BP;

public class BPB9152_AWA_9152 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String CTRT_NO = " ";
    public short CTRT_NO_NO = 0;
    public String REL_CTNO = " ";
    public short REL_CTNO_NO = 0;
    public String PRD_TYP = " ";
    public short PRD_TYP_NO = 0;
    public String FEE_TYP = " ";
    public short FEE_TYP_NO = 0;
    public char PAY_IND = ' ';
    public short PAY_IND_NO = 0;
    public int VAL_DT = 0;
    public short VAL_DT_NO = 0;
    public char PRC_MTH = ' ';
    public short PRC_MTH_NO = 0;
    public String INT_BAS = " ";
    public short INT_BAS_NO = 0;
    public double MULTI = 0;
    public short MULTI_NO = 0;
    public char AGGR_TYP = ' ';
    public short AGGR_TYP_NO = 0;
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public char FILLER1 = ' ';
    public short FILLER1_NO = 0;
    public String REF_CCY = " ";
    public short REF_CCY_NO = 0;
    public char REF_MTH = ' ';
    public short REF_MTH_NO = 0;
    public BPB9152_RATE_TBL[] RATE_TBL = new BPB9152_RATE_TBL[5];
    public BPB9152_AWA_9152() {
        for (int i=0;i<5;i++) RATE_TBL[i] = new BPB9152_RATE_TBL();
    }
}
