package com.hisun.GL;

public class GLCDEF03_EVENT_VCH {
    public String EVN_MOD_NO = " ";
    public String EVN_EVENT_CD = " ";
    public short EVN_CNT = 0;
    public GLCDEF03_EVN_VCH_DETAIL[] EVN_VCH_DETAIL = new GLCDEF03_EVN_VCH_DETAIL[1000];
    public GLCDEF03_EVENT_VCH() {
        for (int i=0;i<1000;i++) EVN_VCH_DETAIL[i] = new GLCDEF03_EVN_VCH_DETAIL();
    }
}
