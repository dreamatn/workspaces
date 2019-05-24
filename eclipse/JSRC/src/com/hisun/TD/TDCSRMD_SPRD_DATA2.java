package com.hisun.TD;

public class TDCSRMD_SPRD_DATA2 {
    public TDCSRMD_TERMS[] TERMS = new TDCSRMD_TERMS[25];
    public TDCSRMD_AMTS[] AMTS = new TDCSRMD_AMTS[25];
    public TDCSRMD_LVLS[] LVLS = new TDCSRMD_LVLS[25];
    public TDCSRMD_GRPS_NOS[] GRPS_NOS = new TDCSRMD_GRPS_NOS[25];
    public TDCSRMD_CUS_AUMS[] CUS_AUMS = new TDCSRMD_CUS_AUMS[25];
    public TDCSRMD_CUS_ISTRYS[] CUS_ISTRYS = new TDCSRMD_CUS_ISTRYS[25];
    public TDCSRMD_CHNL_NOS[] CHNL_NOS = new TDCSRMD_CHNL_NOS[25];
    public TDCSRMD_RGN_NOS[] RGN_NOS = new TDCSRMD_RGN_NOS[25];
    public TDCSRMD_SPRD_DATA2() {
        for (int i=0;i<25;i++) TERMS[i] = new TDCSRMD_TERMS();
        for (int i=0;i<25;i++) AMTS[i] = new TDCSRMD_AMTS();
        for (int i=0;i<25;i++) LVLS[i] = new TDCSRMD_LVLS();
        for (int i=0;i<25;i++) GRPS_NOS[i] = new TDCSRMD_GRPS_NOS();
        for (int i=0;i<25;i++) CUS_AUMS[i] = new TDCSRMD_CUS_AUMS();
        for (int i=0;i<25;i++) CUS_ISTRYS[i] = new TDCSRMD_CUS_ISTRYS();
        for (int i=0;i<25;i++) CHNL_NOS[i] = new TDCSRMD_CHNL_NOS();
        for (int i=0;i<25;i++) RGN_NOS[i] = new TDCSRMD_RGN_NOS();
    }
}
