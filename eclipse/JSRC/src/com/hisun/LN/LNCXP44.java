package com.hisun.LN;

public class LNCXP44 {
    public String AC = " ";
    public String SUFFIX = " ";
    public char PAY_TYP = ' ';
    public short START_TE = 0;
    public short STRIN_TE = 0;
    public short TOTAL_TE = 0;
    public LNCXP44_TERMINFO[] TERMINFO = new LNCXP44_TERMINFO[20];
    public LNCXP44() {
        for (int i=0;i<20;i++) TERMINFO[i] = new LNCXP44_TERMINFO();
    }
}
