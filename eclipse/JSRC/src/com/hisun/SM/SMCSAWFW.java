package com.hisun.SM;

public class SMCSAWFW {
    public SMCSAWFW_RC RC = new SMCSAWFW_RC();
    public String CODE = " ";
    public String Q_NAME = " ";
    public String Q_STS = " ";
    public char REL_TYPE = ' ';
    public String Q_FMCODE = " ";
    public String QCODE = " ";
    public char OUT_FLG = ' ';
    public char UPD_FLG = ' ';
    public int SUB_BR = 0;
    public short STEP_NO = 0;
    public String APP_MMO = " ";
    public SMCSAWFW_DATA[] DATA = new SMCSAWFW_DATA[9];
    public SMCSAWFW() {
        for (int i=0;i<9;i++) DATA[i] = new SMCSAWFW_DATA();
    }
}
