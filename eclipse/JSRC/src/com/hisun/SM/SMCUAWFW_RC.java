package com.hisun.SM;

public class SMCUAWFW_RC {
    public String RC_MMO = " ";
    public short RC_CODE = 0;
    public String CODE = " ";
    public String Q_NAME = " ";
    public String Q_STS = " ";
    public char REL_TYPE = ' ';
    public String Q_FMCODE = " ";
    public String QCODE = " ";
    public char OUT_FLG = ' ';
    public char UPD_FLG = ' ';
    public short STEP_NO = 0;
    public int SUB_BR = 0;
    public String APP_MMO = " ";
    public SMCUAWFW_DATA[] DATA = new SMCUAWFW_DATA[9];
    public SMCUAWFW_RC() {
        for (int i=0;i<9;i++) DATA[i] = new SMCUAWFW_DATA();
    }
}
