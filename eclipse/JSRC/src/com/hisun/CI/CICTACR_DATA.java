package com.hisun.CI;

public class CICTACR_DATA {
    public String CI_NO = " ";
    public String AGR_NO = " ";
    public CICTACR_REL_AREA[] REL_AREA = new CICTACR_REL_AREA[30];
    public char ENTY_TYP = ' ';
    public String FRM_APP = " ";
    public String CNTRCT_TYP = " ";
    public String PROD_CD = " ";
    public CICTACR_DATA() {
        for (int i=0;i<30;i++) REL_AREA[i] = new CICTACR_REL_AREA();
    }
}
