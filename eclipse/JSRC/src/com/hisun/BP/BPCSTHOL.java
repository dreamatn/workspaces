package com.hisun.BP;

public class BPCSTHOL {
    public BPCSTHOL_RC RC = new BPCSTHOL_RC();
    public char FUNC = ' ';
    public char FUNC_OPT = ' ';
    public BPCSTHOL_KEY KEY = new BPCSTHOL_KEY();
    public int DATE = 0;
    public BPCSTHOL_HOL_DATA[] HOL_DATA = new BPCSTHOL_HOL_DATA[50];
    public int LAST_DATE = 0;
    public String LAST_TELLER = " ";
    public String TS = " ";
    public BPCSTHOL() {
        for (int i=0;i<50;i++) HOL_DATA[i] = new BPCSTHOL_HOL_DATA();
    }
}
