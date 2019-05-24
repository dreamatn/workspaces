package com.hisun.BP;

public class BPCI4752 {
    public String CALCD = " ";
    public String CAL_NAME = " ";
    public short YEAR = 0;
    public short END_YEAR = 0;
    public String CNTYS_CD = " ";
    public String CITY_CD = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public BPCI4752_MONTH[] MONTH = new BPCI4752_MONTH[12];
    public BPCI4752() {
        for (int i=0;i<12;i++) MONTH[i] = new BPCI4752_MONTH();
    }
}
