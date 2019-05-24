package com.hisun.SM;

public class SMCUQWFW_RC {
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
    public SMCUQWFW_DATA[] DATA = new SMCUQWFW_DATA[9];
    public SMCUQWFW_RC() {
        for (int i=0;i<9;i++) DATA[i] = new SMCUQWFW_DATA();
    }
}
