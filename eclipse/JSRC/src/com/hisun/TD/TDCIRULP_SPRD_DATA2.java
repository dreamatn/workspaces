package com.hisun.TD;

public class TDCIRULP_SPRD_DATA2 {
    public TDCIRULP_TERMS[] TERMS = new TDCIRULP_TERMS[25];
    public TDCIRULP_AMTS[] AMTS = new TDCIRULP_AMTS[25];
    public TDCIRULP_LVLS[] LVLS = new TDCIRULP_LVLS[25];
    public TDCIRULP_GRPS_NOS[] GRPS_NOS = new TDCIRULP_GRPS_NOS[25];
    public TDCIRULP_CUS_AUMS[] CUS_AUMS = new TDCIRULP_CUS_AUMS[25];
    public TDCIRULP_CUS_ISTRYS[] CUS_ISTRYS = new TDCIRULP_CUS_ISTRYS[25];
    public TDCIRULP_CHNL_NOS[] CHNL_NOS = new TDCIRULP_CHNL_NOS[25];
    public TDCIRULP_RGN_NOS[] RGN_NOS = new TDCIRULP_RGN_NOS[25];
    public TDCIRULP_SPRD_DATA2() {
        for (int i=0;i<25;i++) TERMS[i] = new TDCIRULP_TERMS();
        for (int i=0;i<25;i++) AMTS[i] = new TDCIRULP_AMTS();
        for (int i=0;i<25;i++) LVLS[i] = new TDCIRULP_LVLS();
        for (int i=0;i<25;i++) GRPS_NOS[i] = new TDCIRULP_GRPS_NOS();
        for (int i=0;i<25;i++) CUS_AUMS[i] = new TDCIRULP_CUS_AUMS();
        for (int i=0;i<25;i++) CUS_ISTRYS[i] = new TDCIRULP_CUS_ISTRYS();
        for (int i=0;i<25;i++) CHNL_NOS[i] = new TDCIRULP_CHNL_NOS();
        for (int i=0;i<25;i++) RGN_NOS[i] = new TDCIRULP_RGN_NOS();
    }
}
