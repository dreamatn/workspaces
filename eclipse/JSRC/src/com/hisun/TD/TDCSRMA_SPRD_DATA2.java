package com.hisun.TD;

public class TDCSRMA_SPRD_DATA2 {
    public TDCSRMA_TERMS[] TERMS = new TDCSRMA_TERMS[25];
    public TDCSRMA_AMTS[] AMTS = new TDCSRMA_AMTS[25];
    public TDCSRMA_LVLS[] LVLS = new TDCSRMA_LVLS[25];
    public TDCSRMA_GRPS_NOS[] GRPS_NOS = new TDCSRMA_GRPS_NOS[25];
    public TDCSRMA_CUS_AUMS[] CUS_AUMS = new TDCSRMA_CUS_AUMS[25];
    public TDCSRMA_CUS_ISTRYS[] CUS_ISTRYS = new TDCSRMA_CUS_ISTRYS[25];
    public TDCSRMA_CHNL_NOS[] CHNL_NOS = new TDCSRMA_CHNL_NOS[25];
    public TDCSRMA_RGN_NOS[] RGN_NOS = new TDCSRMA_RGN_NOS[25];
    public TDCSRMA_SPRD_DATA2() {
        for (int i=0;i<25;i++) TERMS[i] = new TDCSRMA_TERMS();
        for (int i=0;i<25;i++) AMTS[i] = new TDCSRMA_AMTS();
        for (int i=0;i<25;i++) LVLS[i] = new TDCSRMA_LVLS();
        for (int i=0;i<25;i++) GRPS_NOS[i] = new TDCSRMA_GRPS_NOS();
        for (int i=0;i<25;i++) CUS_AUMS[i] = new TDCSRMA_CUS_AUMS();
        for (int i=0;i<25;i++) CUS_ISTRYS[i] = new TDCSRMA_CUS_ISTRYS();
        for (int i=0;i<25;i++) CHNL_NOS[i] = new TDCSRMA_CHNL_NOS();
        for (int i=0;i<25;i++) RGN_NOS[i] = new TDCSRMA_RGN_NOS();
    }
}
