package com.hisun.GL;

public class GLCDEF04_EVENT_VCH_GROUP {
    public String EVN_MOD_NO = " ";
    public String EVN_EVENT_CD = " ";
    public short EVN_CNT = 0;
    public GLCDEF04_EVN_VCH_DETAIL[] EVN_VCH_DETAIL = new GLCDEF04_EVN_VCH_DETAIL[100];
    public GLCDEF04_EVENT_VCH_GROUP() {
        for (int i=0;i<100;i++) EVN_VCH_DETAIL[i] = new GLCDEF04_EVN_VCH_DETAIL();
    }
}
