package com.hisun.TD;

public class TDCQIRUL_SPRD_DATA2 {
    public TDCQIRUL_TERMS1[] TERMS1 = new TDCQIRUL_TERMS1[25];
    public TDCQIRUL_AMTS[] AMTS = new TDCQIRUL_AMTS[25];
    public TDCQIRUL_LVLS[] LVLS = new TDCQIRUL_LVLS[25];
    public TDCQIRUL_GRPS_NOS[] GRPS_NOS = new TDCQIRUL_GRPS_NOS[25];
    public TDCQIRUL_CUS_AUMS[] CUS_AUMS = new TDCQIRUL_CUS_AUMS[25];
    public TDCQIRUL_CUS_ISTRYS[] CUS_ISTRYS = new TDCQIRUL_CUS_ISTRYS[25];
    public TDCQIRUL_CHNL_NOS[] CHNL_NOS = new TDCQIRUL_CHNL_NOS[25];
    public TDCQIRUL_TERMS[] TERMS = new TDCQIRUL_TERMS[25];
    public TDCQIRUL_SPRD_DATA2() {
        for (int i=0;i<25;i++) TERMS1[i] = new TDCQIRUL_TERMS1();
        for (int i=0;i<25;i++) AMTS[i] = new TDCQIRUL_AMTS();
        for (int i=0;i<25;i++) LVLS[i] = new TDCQIRUL_LVLS();
        for (int i=0;i<25;i++) GRPS_NOS[i] = new TDCQIRUL_GRPS_NOS();
        for (int i=0;i<25;i++) CUS_AUMS[i] = new TDCQIRUL_CUS_AUMS();
        for (int i=0;i<25;i++) CUS_ISTRYS[i] = new TDCQIRUL_CUS_ISTRYS();
        for (int i=0;i<25;i++) CHNL_NOS[i] = new TDCQIRUL_CHNL_NOS();
        for (int i=0;i<25;i++) TERMS[i] = new TDCQIRUL_TERMS();
    }
}
