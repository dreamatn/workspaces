package com.hisun.BP;

public class BPCOTAMT {
    public String TAMT_APP = " ";
    public String TBL_NO = " ";
    public String CHNL = " ";
    public int BR = 0;
    public String EX_TYPE = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public String DESC = " ";
    public char FILLER9 = 0X02;
    public String FLAG = " ";
    public BPCOTAMT_TAMT_ARR[] TAMT_ARR = new BPCOTAMT_TAMT_ARR[6];
    public BPCOTAMT_U_TAMT_ARR[] U_TAMT_ARR = new BPCOTAMT_U_TAMT_ARR[6];
    public BPCOTAMT() {
        for (int i=0;i<6;i++) TAMT_ARR[i] = new BPCOTAMT_TAMT_ARR();
        for (int i=0;i<6;i++) U_TAMT_ARR[i] = new BPCOTAMT_U_TAMT_ARR();
    }
}
