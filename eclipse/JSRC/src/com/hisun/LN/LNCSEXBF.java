package com.hisun.LN;

public class LNCSEXBF {
    public char FUNC = ' ';
    public LNCSEXBF_KEY KEY = new LNCSEXBF_KEY();
    public String CCY = " ";
    public double EXG_BUF = 0;
    public LNCSEXBF_CONTRACT_NO_DATA[] CONTRACT_NO_DATA = new LNCSEXBF_CONTRACT_NO_DATA[9];
    public String TS = " ";
    public char OPT = ' ';
    public LNCSEXBF() {
        for (int i=0;i<9;i++) CONTRACT_NO_DATA[i] = new LNCSEXBF_CONTRACT_NO_DATA();
    }
}
