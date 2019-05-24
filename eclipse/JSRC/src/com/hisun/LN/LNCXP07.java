package com.hisun.LN;

public class LNCXP07 {
    public char FUNC = ' ';
    public String TYPE = " ";
    public String CODE = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public char FLAG = ' ';
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public short VAL_LEN = 0;
    public double MIN_DRAW_AMT = 0;
    public double MAX_DRAW_AMT = 0;
    public short MIN_TERM = 0;
    public short MAX_TERM = 0;
    public double MIN_RAT = 0;
    public char FLAG_MIN_RAT = ' ';
    public double MAX_RAT = 0;
    public char FLAG_MAX_RAT = ' ';
    public double IRAT_FLR_PER = 0;
    public double IRAT_CEL_PER = 0;
    public int IRAT_FLR_PNT = 0;
    public int IRAT_CEL_PNT = 0;
    public double LRAT_FLR_PER = 0;
    public double LRAT_CEL_PER = 0;
    public int LRAT_FLR_PNT = 0;
    public int LRAT_CEL_PNT = 0;
    public LNCXP07_PAY_MTH_AREA[] PAY_MTH_AREA = new LNCXP07_PAY_MTH_AREA[5];
    public LNCXP07_INST_MTH_AREA[] INST_MTH_AREA = new LNCXP07_INST_MTH_AREA[5];
    public char STMP_FLG = ' ';
    public char ESTMP_FLG = ' ';
    public char PENALTY_FLG = ' ';
    public char EPEN_FLG = ' ';
    public double MIN_PEN_AMT = 0;
    public double MAX_PEN_AMT = 0;
    public LNCXP07() {
        for (int i=0;i<5;i++) PAY_MTH_AREA[i] = new LNCXP07_PAY_MTH_AREA();
        for (int i=0;i<5;i++) INST_MTH_AREA[i] = new LNCXP07_INST_MTH_AREA();
    }
}
