package com.hisun.GD;

public class GDCSELRL {
    public String RSEQ = " ";
    public String CTA_NO = " ";
    public String REF_NO = " ";
    public String R_TYP = " ";
    public short CNT = 0;
    public GDCSELRL_AC_INFO[] AC_INFO = new GDCSELRL_AC_INFO[10];
    public GDCSELRL() {
        for (int i=0;i<10;i++) AC_INFO[i] = new GDCSELRL_AC_INFO();
    }
}
