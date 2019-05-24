package com.hisun.BP;

public class BPCI5810 {
    public char CMZ_FLG = ' ';
    public String CI_NO = " ";
    public String CMZ_AC = " ";
    public String CMZ_BIZ = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public char ENTI_FLG = ' ';
    public String CMZ_DESC = " ";
    public char CCY_RULE = ' ';
    public String CMZ_CCY = " ";
    public BPCI5810_FEE_INFO[] FEE_INFO = new BPCI5810_FEE_INFO[20];
    public BPCI5810() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCI5810_FEE_INFO();
    }
}
