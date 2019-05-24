package com.hisun.BP;

public class BPCSTAMT {
    public BPCSTAMT_RC RC = new BPCSTAMT_RC();
    public char FUNC = ' ';
    public String TAMT_APP = " ";
    public String TBL_NO = " ";
    public String CHNL = " ";
    public int BR = 0;
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public char TAMT_STS = ' ';
    public String DESC = " ";
    public String FLAG = " ";
    public short FLD_CNT = 0;
    public String EX_TYPE = " ";
    public short U_FLD_CNT = 0;
    public BPCSTAMT_TAMT_ARR[] TAMT_ARR = new BPCSTAMT_TAMT_ARR[6];
    public BPCSTAMT_U_TAMT_ARR[] U_TAMT_ARR = new BPCSTAMT_U_TAMT_ARR[6];
    public BPCSTAMT() {
        for (int i=0;i<6;i++) TAMT_ARR[i] = new BPCSTAMT_TAMT_ARR();
        for (int i=0;i<6;i++) U_TAMT_ARR[i] = new BPCSTAMT_U_TAMT_ARR();
    }
}
