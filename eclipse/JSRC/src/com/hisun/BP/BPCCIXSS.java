package com.hisun.BP;

public class BPCCIXSS {
    public String RTN_CODE = " ";
    public String TENOR = " ";
    public String BASE_TYP = " ";
    public int CCY_CNT = 0;
    public BPCCIXSS_CCY_TBL[] CCY_TBL = new BPCCIXSS_CCY_TBL[100];
    public BPCCIXSS() {
        for (int i=0;i<100;i++) CCY_TBL[i] = new BPCCIXSS_CCY_TBL();
    }
}
