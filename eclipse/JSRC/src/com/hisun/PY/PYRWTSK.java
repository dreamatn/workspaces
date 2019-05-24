package com.hisun.PY;

public class PYRWTSK {
    public String APP = " ";
    public int BRH_OLD = 0;
    public int BRH_NEW = 0;
    public String CNTR_TYPE = " ";
    public PYRWTSK_AC AC = new PYRWTSK_AC();
    public PYRWTSK_BAL_INFO[] BAL_INFO = new PYRWTSK_BAL_INFO[76];
    public char BUSI_TYP = ' ';
    public PYRWTSK() {
        for (int i=0;i<76;i++) BAL_INFO[i] = new PYRWTSK_BAL_INFO();
    }
}
