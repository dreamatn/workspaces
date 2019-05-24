package com.hisun.PY;

public class PYRTSCF {
    public String APP = " ";
    public int BRH_OLD = 0;
    public int BRH_NEW = 0;
    public String CNTR_TYPE = " ";
    public PYRTSCF_AC AC = new PYRTSCF_AC();
    public PYRTSCF_BAL_INFO[] BAL_INFO = new PYRTSCF_BAL_INFO[76];
    public char BUSI_TYP = ' ';
    public PYRTSCF() {
        for (int i=0;i<76;i++) BAL_INFO[i] = new PYRTSCF_BAL_INFO();
    }
}
