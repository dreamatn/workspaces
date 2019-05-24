package com.hisun.BP;

public class BPVFREB_VAL {
    public String DESC = " ";
    public char FILLER5 = 0X02;
    public char REB_CLFY = ' ';
    public short CAL_CNT = 0;
    public char REB_MTH = ' ';
    public char AGGR_TYPE = ' ';
    public double MAX_REB_AMT = 0;
    public char FILLER11 = 0X01;
    public char REB_CYCLE = ' ';
    public short REB_CNT = 0;
    public short REB_DATE = 0;
    public char REB_CON = ' ';
    public String REF_CCY = " ";
    public double STR_AMT = 0;
    public short STR_CNT = 0;
    public String REB_CCY = " ";
    public String REB_MMO = " ";
    public String REB_CPNT = " ";
    public BPVFREB_REB_DATA[] REB_DATA = new BPVFREB_REB_DATA[5];
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPVFREB_VAL() {
        for (int i=0;i<5;i++) REB_DATA[i] = new BPVFREB_REB_DATA();
    }
}
