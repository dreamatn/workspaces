package com.hisun.BP;

public class BPCSTWND {
    public BPCSTWND_RC RC = new BPCSTWND_RC();
    public char FUNC = ' ';
    public char FUNC_OPT = ' ';
    public BPCSTWND_KEY KEY = new BPCSTWND_KEY();
    public int DATE = 0;
    public BPCSTWND_HOL_DATA[] HOL_DATA = new BPCSTWND_HOL_DATA[7];
    public int LAST_DATE = 0;
    public String LAST_TELLER = " ";
    public String TS = " ";
    public BPCSTWND() {
        for (int i=0;i<7;i++) HOL_DATA[i] = new BPCSTWND_HOL_DATA();
    }
}
