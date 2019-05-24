package com.hisun.BP;

public class BPCQCMZR {
    public char CMZ_FLG = ' ';
    public String CI_NO = " ";
    public String CMZ_AC = " ";
    public String CMZ_BIZ = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public char ENTI_FLG = ' ';
    public String CMZ_DESC = " ";
    public char FILLER9 = 0X02;
    public BPCQCMZR_FEE_INFO[] FEE_INFO = new BPCQCMZR_FEE_INFO[20];
    public BPCQCMZR() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCQCMZR_FEE_INFO();
    }
}
