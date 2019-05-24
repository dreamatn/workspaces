package com.hisun.BP;

public class BPCDEF04_EVENT_VCH_GROUP {
    public String EVN_MOD_NO = " ";
    public String EVN_EVENT_CD = " ";
    public short EVN_CNT = 0;
    public BPCDEF04_EVN_VCH_DETAIL[] EVN_VCH_DETAIL = new BPCDEF04_EVN_VCH_DETAIL[100];
    public BPCDEF04_EVENT_VCH_GROUP() {
        for (int i=0;i<100;i++) EVN_VCH_DETAIL[i] = new BPCDEF04_EVN_VCH_DETAIL();
    }
}
