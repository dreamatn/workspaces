package com.hisun.SM;

public class SMCSUWFW {
    public SMCSUWFW_RC RC = new SMCSUWFW_RC();
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
    public SMCSUWFW_DATA[] DATA = new SMCSUWFW_DATA[9];
    public SMCSUWFW() {
        for (int i=0;i<9;i++) DATA[i] = new SMCSUWFW_DATA();
    }
}
