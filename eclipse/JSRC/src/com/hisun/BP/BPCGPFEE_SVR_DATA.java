package com.hisun.BP;

public class BPCGPFEE_SVR_DATA {
    public char AUT_FLG = ' ';
    public BPCGPFEE_SVR_PARM[] SVR_PARM = new BPCGPFEE_SVR_PARM[4];
    public BPCGPFEE_SVR_DATA() {
        for (int i=0;i<4;i++) SVR_PARM[i] = new BPCGPFEE_SVR_PARM();
    }
}
