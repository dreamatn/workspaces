package com.hisun.CI;

public class CICMJRL_DATA {
    public CICMJRL_JRL_DATA[] JRL_DATA = new CICMJRL_JRL_DATA[10];
    public String JRL_CI_NO = " ";
    public String AC_NO = " ";
    public CICMJRL_DATA() {
        for (int i=0;i<10;i++) JRL_DATA[i] = new CICMJRL_JRL_DATA();
    }
}
