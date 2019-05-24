package com.hisun.CI;

public class CIB8530_AWA_8530 {
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public String AGR_NO = " ";
    public short AGR_NO_NO = 0;
    public char SRCH_FLG = ' ';
    public short SRCH_FLG_NO = 0;
    public CIB8530_REL_AREA[] REL_AREA = new CIB8530_REL_AREA[30];
    public char ENTY_TYP = ' ';
    public short ENTY_TYP_NO = 0;
    public String FRM_APP = " ";
    public short FRM_APP_NO = 0;
    public String CNTR_TYP = " ";
    public short CNTR_TYP_NO = 0;
    public String PROD_CD = " ";
    public short PROD_CD_NO = 0;
    public CIB8530_AWA_8530() {
        for (int i=0;i<30;i++) REL_AREA[i] = new CIB8530_REL_AREA();
    }
}
