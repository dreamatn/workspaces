package com.hisun.SM;

public class SMCSQWFW {
    public SMCSQWFW_RC RC = new SMCSQWFW_RC();
    public String CODE = " ";
    public String Q_NAME = " ";
    public String Q_STS = " ";
    public char REL_TYPE = ' ';
    public String Q_FMCODE = " ";
    public String QCODE = " ";
    public char OUT_FLG = ' ';
    public char UPD_FLG = ' ';
    public SMCSQWFW_DATA[] DATA = new SMCSQWFW_DATA[9];
    public SMCSQWFW() {
        for (int i=0;i<9;i++) DATA[i] = new SMCSQWFW_DATA();
    }
}
