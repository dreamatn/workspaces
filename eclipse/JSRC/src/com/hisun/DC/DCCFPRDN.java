package com.hisun.DC;

import com.hisun.IB.FPRDN_INT_INFO;
import com.hisun.IB.FPRDN_PERM_INFO;
import com.hisun.IB.FPRDN_PROD_DD_INFO;
import com.hisun.IB.FPRDN_PROD_TD_INFO;
import com.hisun.IB.FPRDN_USAGE_INFO;

public class DCCFPRDN {
    String CON_MDEL = " ";
    String PROD_CODE = " ";
    String PROD_DESC = " ";
    String EFFDAT = " ";
    String EXPDAT = " ";
    char CI_TYP = ' ';
    String CCY = " ";
    char CCY_TYPE = ' ';
    short PERM_MMDD = 0;
    char OVR_CARD_FLG = ' ';
    FPRDN_PROD_DD_INFO[] PROD_DD_INFO = new FPRDN_PROD_DD_INFO[10];
    FPRDN_PROD_TD_INFO[] PROD_TD_INFO = new FPRDN_PROD_TD_INFO[10];
    char DD_TRS_FLG = ' ';
    FPRDN_USAGE_INFO[] USAGE_INFO = new FPRDN_USAGE_INFO[10];
    char DRAW_MTH = ' ';
    char DRAW_ORDER = ' ';
    char INOUT_FG = ' ';
    short OUT_LVL = 0;
    short IN_LVL = 0;
    char OVR_BANK_FLG = ' ';
    char TRIG_TMS = ' ';
    FPRDN_PERM_INFO[] PERM_INFO = new FPRDN_PERM_INFO[5];
    FPRDN_INT_INFO[] INT_INFO = new FPRDN_INT_INFO[5];
    public DCCFPRDN() {
        for (int i=0;i<10;i++) PROD_DD_INFO[i] = new FPRDN_PROD_DD_INFO();
        for (int i=0;i<10;i++) PROD_TD_INFO[i] = new FPRDN_PROD_TD_INFO();
        for (int i=0;i<10;i++) USAGE_INFO[i] = new FPRDN_USAGE_INFO();
        for (int i=0;i<5;i++) PERM_INFO[i] = new FPRDN_PERM_INFO();
        for (int i=0;i<5;i++) INT_INFO[i] = new FPRDN_INT_INFO();
    }
}
