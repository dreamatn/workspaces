package com.hisun.BP;

public class BPCI5381 {
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public String FAV_DESC = " ";
    public String FAV_CDES = " ";
    public String CCY = " ";
    public String FEE_TYPE = " ";
    public char CAL_MTH = ' ';
    public BPCI5381_STR_INFO[] STR_INFO = new BPCI5381_STR_INFO[10];
    public BPCI5381() {
        for (int i=0;i<10;i++) STR_INFO[i] = new BPCI5381_STR_INFO();
    }
}
