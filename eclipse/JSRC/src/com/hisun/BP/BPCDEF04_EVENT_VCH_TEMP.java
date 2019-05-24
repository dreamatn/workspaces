package com.hisun.BP;

public class BPCDEF04_EVENT_VCH_TEMP {
    public String T_EVN_MOD_NO = " ";
    public String T_EVN_EVENT_CD = " ";
    public short T_EVN_CNT = 0;
    public BPCDEF04_T_EVN_VCH_DETAIL[] T_EVN_VCH_DETAIL = new BPCDEF04_T_EVN_VCH_DETAIL[100];
    public BPCDEF04_EVENT_VCH_TEMP() {
        for (int i=0;i<100;i++) T_EVN_VCH_DETAIL[i] = new BPCDEF04_T_EVN_VCH_DETAIL();
    }
}
