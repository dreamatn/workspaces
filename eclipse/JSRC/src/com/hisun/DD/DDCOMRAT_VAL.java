package com.hisun.DD;

public class DDCOMRAT_VAL {
    public char TIER_TYPE = ' ';
    public char AMT_TIER_TYPE = ' ';
    public DDCOMRAT_TIER_AMT[] TIER_AMT = new DDCOMRAT_TIER_AMT[5];
    public String OD_INT_RBAS = " ";
    public String OD_INT_RCD = " ";
    public double OD_SPR = 0;
    public char FILLER50 = 0X01;
    public double OD_PCT = 0;
    public double OD_RATE = 0;
    public char FILLER53 = 0X01;
    public String UOD_INT_RBAS = " ";
    public String UOD_INT_RCD = " ";
    public double UOD_SPR = 0;
    public char FILLER57 = 0X01;
    public double UOD_PCT = 0;
    public double UOD_RATE = 0;
    public char FILLER60 = 0X01;
    public String TOD_INT_RBAS = " ";
    public String TOD_INT_RCD = " ";
    public double TOD_SPR = 0;
    public char FILLER64 = 0X01;
    public double TOD_PCT = 0;
    public double TOD_RATE = 0;
    public char FILLER67 = 0X01;
    public DDCOMRAT_VAL() {
        for (int i=0;i<5;i++) TIER_AMT[i] = new DDCOMRAT_TIER_AMT();
    }
}
