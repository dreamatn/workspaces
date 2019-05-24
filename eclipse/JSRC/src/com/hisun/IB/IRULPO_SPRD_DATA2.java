package com.hisun.IB;

public class IRULPO_SPRD_DATA2 {
    IRULPO_TERMS[] TERMS = new IRULPO_TERMS[25];
    IRULPO_AMTS[] AMTS = new IRULPO_AMTS[25];
    IRULPO_LVLS[] LVLS = new IRULPO_LVLS[25];
    IRULPO_GRPS_NOS[] GRPS_NOS = new IRULPO_GRPS_NOS[25];
    IRULPO_CUS_AUMS[] CUS_AUMS = new IRULPO_CUS_AUMS[25];
    IRULPO_CUS_ISTRYS[] CUS_ISTRYS = new IRULPO_CUS_ISTRYS[25];
    IRULPO_CHNL_NOS[] CHNL_NOS = new IRULPO_CHNL_NOS[25];
    IRULPO_RGN_NOS[] RGN_NOS = new IRULPO_RGN_NOS[25];
    public IRULPO_SPRD_DATA2() {
        for (int i=0;i<25;i++) TERMS[i] = new IRULPO_TERMS();
        for (int i=0;i<25;i++) AMTS[i] = new IRULPO_AMTS();
        for (int i=0;i<25;i++) LVLS[i] = new IRULPO_LVLS();
        for (int i=0;i<25;i++) GRPS_NOS[i] = new IRULPO_GRPS_NOS();
        for (int i=0;i<25;i++) CUS_AUMS[i] = new IRULPO_CUS_AUMS();
        for (int i=0;i<25;i++) CUS_ISTRYS[i] = new IRULPO_CUS_ISTRYS();
        for (int i=0;i<25;i++) CHNL_NOS[i] = new IRULPO_CHNL_NOS();
        for (int i=0;i<25;i++) RGN_NOS[i] = new IRULPO_RGN_NOS();
    }
}
