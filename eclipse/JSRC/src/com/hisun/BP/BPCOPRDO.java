package com.hisun.BP;

public class BPCOPRDO {
    public char FUNC = ' ';
    public BPCOPRDO_KEY KEY = new BPCOPRDO_KEY();
    public BPCOPRDO_DATA[] DATA = new BPCOPRDO_DATA[20];
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCOPRDO() {
        for (int i=0;i<20;i++) DATA[i] = new BPCOPRDO_DATA();
    }
}
