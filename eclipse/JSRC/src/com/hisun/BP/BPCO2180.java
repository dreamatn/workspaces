package com.hisun.BP;

public class BPCO2180 {
    public int BR = 0;
    public char FUNC = ' ';
    public String CI_NO = " ";
    public String CI_NM = " ";
    public String ITM_NO = " ";
    public int SEQ = 0;
    public BPCO2180_CCYS[] CCYS = new BPCO2180_CCYS[10];
    public BPCO2180() {
        for (int i=0;i<10;i++) CCYS[i] = new BPCO2180_CCYS();
    }
}
