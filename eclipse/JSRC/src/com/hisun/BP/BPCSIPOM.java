package com.hisun.BP;

public class BPCSIPOM {
    public char FUNC = ' ';
    public String CCY = " ";
    public String B_TYPE = " ";
    public String TENOR = " ";
    public char EFF_FLG = ' ';
    public char DEL_FLG = ' ';
    public BPCSIPOM_TEN_TBL[] TEN_TBL = new BPCSIPOM_TEN_TBL[20];
    public char RETURN_INFO = ' ';
    public char OUTPUT_FLG = ' ';
    public char CHECK_RESULT = ' ';
    public BPCSIPOM() {
        for (int i=0;i<20;i++) TEN_TBL[i] = new BPCSIPOM_TEN_TBL();
    }
}
