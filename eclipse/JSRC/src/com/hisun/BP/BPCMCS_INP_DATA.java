package com.hisun.BP;

public class BPCMCS_INP_DATA {
    public String EXR_TYP = " ";
    public String FWD_TENOR = " ";
    public int BR = 0;
    public int EFF_DT = 0;
    public int EFF_TM = 0;
    public BPCMCS_CCY_SPRD[] CCY_SPRD = new BPCMCS_CCY_SPRD[30];
    public BPCMCS_INP_DATA() {
        for (int i=0;i<30;i++) CCY_SPRD[i] = new BPCMCS_CCY_SPRD();
    }
}
