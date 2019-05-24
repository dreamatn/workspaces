package com.hisun.GD;

public class GDCIQLDR {
    public GDCIQLDR_RC RC = new GDCIQLDR_RC();
    public String RSEQ = " ";
    public String IN_AC = " ";
    public int IN_SEQ = 0;
    public int TCNT = 0;
    public double TAMT = 0;
    public char FILLER9 = 0X01;
    public GDCIQLDR_INFO[] INFO = new GDCIQLDR_INFO[99];
    public GDCIQLDR() {
        for (int i=0;i<99;i++) INFO[i] = new GDCIQLDR_INFO();
    }
}
