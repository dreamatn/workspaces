package com.hisun.GL;

public class GLCDEF04_EVENT_VCH_TEMP {
    public String T_EVN_MOD_NO = " ";
    public String T_EVN_EVENT_CD = " ";
    public short T_EVN_CNT = 0;
    public GLCDEF04_T_EVN_VCH_DETAIL[] T_EVN_VCH_DETAIL = new GLCDEF04_T_EVN_VCH_DETAIL[100];
    public GLCDEF04_EVENT_VCH_TEMP() {
        for (int i=0;i<100;i++) T_EVN_VCH_DETAIL[i] = new GLCDEF04_T_EVN_VCH_DETAIL();
    }
}
