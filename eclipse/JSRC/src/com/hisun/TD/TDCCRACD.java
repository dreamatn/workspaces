package com.hisun.TD;

public class TDCCRACD {
    public String ACO_AC = " ";
    public String CI_NO = " ";
    public String FRM_APP = " ";
    public char RUL_TYP = ' ';
    public double RAT_BAS = 0;
    public TDCCRACD_RUL_CD_ALL[] RUL_CD_ALL = new TDCCRACD_RUL_CD_ALL[10];
    public double BAL = 0;
    public String TERM = " ";
    public String SVR_LVL = " ";
    public String GRPS_NO = " ";
    public int BR = 0;
    public String RGN_NO = " ";
    public String ASS_LVL = " ";
    public String CHNL_NO = " ";
    public short AGE = 0;
    public int BIRTH = 0;
    public int TODAY = 0;
    public char GENDER = ' ';
    public TDCCRACD_OUT_INF OUT_INF = new TDCCRACD_OUT_INF();
    public TDCCRACD() {
        for (int i=0;i<10;i++) RUL_CD_ALL[i] = new TDCCRACD_RUL_CD_ALL();
    }
}
