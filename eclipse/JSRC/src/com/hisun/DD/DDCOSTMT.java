package com.hisun.DD;

public class DDCOSTMT {
    public String AC_NO = " ";
    public String CNAME = " ";
    public char FILLER3 = 0X02;
    public String ENAME = " ";
    public char STM_NTXP_FLG = ' ';
    public char STM_NPRT_FLG = ' ';
    public DDCOSTMT_STM_CYC_INFO[] STM_CYC_INFO = new DDCOSTMT_STM_CYC_INFO[2];
    public DDCOSTMT_STM_DELV_INFO[] STM_DELV_INFO = new DDCOSTMT_STM_DELV_INFO[3];
    public DDCOSTMT() {
        for (int i=0;i<2;i++) STM_CYC_INFO[i] = new DDCOSTMT_STM_CYC_INFO();
        for (int i=0;i<3;i++) STM_DELV_INFO[i] = new DDCOSTMT_STM_DELV_INFO();
    }
}
