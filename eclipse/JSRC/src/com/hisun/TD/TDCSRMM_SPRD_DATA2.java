package com.hisun.TD;

public class TDCSRMM_SPRD_DATA2 {
    public TDCSRMM_TERMS[] TERMS = new TDCSRMM_TERMS[25];
    public TDCSRMM_AMTS[] AMTS = new TDCSRMM_AMTS[25];
    public TDCSRMM_LVLS[] LVLS = new TDCSRMM_LVLS[25];
    public TDCSRMM_GRPS_NOS[] GRPS_NOS = new TDCSRMM_GRPS_NOS[25];
    public TDCSRMM_CUS_AUMS[] CUS_AUMS = new TDCSRMM_CUS_AUMS[25];
    public TDCSRMM_CUS_ISTRYS[] CUS_ISTRYS = new TDCSRMM_CUS_ISTRYS[25];
    public TDCSRMM_CHNL_NOS[] CHNL_NOS = new TDCSRMM_CHNL_NOS[25];
    public TDCSRMM_RGN_NOS[] RGN_NOS = new TDCSRMM_RGN_NOS[25];
    public TDCSRMM_SPRD_DATA2() {
        for (int i=0;i<25;i++) TERMS[i] = new TDCSRMM_TERMS();
        for (int i=0;i<25;i++) AMTS[i] = new TDCSRMM_AMTS();
        for (int i=0;i<25;i++) LVLS[i] = new TDCSRMM_LVLS();
        for (int i=0;i<25;i++) GRPS_NOS[i] = new TDCSRMM_GRPS_NOS();
        for (int i=0;i<25;i++) CUS_AUMS[i] = new TDCSRMM_CUS_AUMS();
        for (int i=0;i<25;i++) CUS_ISTRYS[i] = new TDCSRMM_CUS_ISTRYS();
        for (int i=0;i<25;i++) CHNL_NOS[i] = new TDCSRMM_CHNL_NOS();
        for (int i=0;i<25;i++) RGN_NOS[i] = new TDCSRMM_RGN_NOS();
    }
}
