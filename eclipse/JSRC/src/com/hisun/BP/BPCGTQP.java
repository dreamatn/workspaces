package com.hisun.BP;

public class BPCGTQP {
    public String RC_MMO = " ";
    public short RC_CODE = 0;
    public char FUNC = ' ';
    public int EFF_DATE = 0;
    public int EFF_TIME = 0;
    public BPCGTQP_MKT_RAT_INFO[] MKT_RAT_INFO = new BPCGTQP_MKT_RAT_INFO[20];
    public BPCGTQP() {
        for (int i=0;i<20;i++) MKT_RAT_INFO[i] = new BPCGTQP_MKT_RAT_INFO();
    }
}
