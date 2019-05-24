package com.hisun.TD;

import com.hisun.PY.QPRD1_EXP_PRM;
import com.hisun.PY.QPRD1_INT_PRM;
import com.hisun.PY.QPRD1_OTH_PRM;
import com.hisun.PY.QPRD1_TXN_PRM;

public class TDCQPRD1 {
    String PRDMO_CD = " ";
    String PROD_CD = " ";
    String DESC = " ";
    String CDESC = " ";
    char FILLER5 = 0X02;
    int EFF_DT = 0;
    int EXP_DT = 0;
    char FUNC = ' ';
    QPRD1_TXN_PRM TXN_PRM = new QPRD1_TXN_PRM();
    QPRD1_INT_PRM INT_PRM = new QPRD1_INT_PRM();
    QPRD1_EXP_PRM EXP_PRM = new QPRD1_EXP_PRM();
    QPRD1_OTH_PRM OTH_PRM = new QPRD1_OTH_PRM();
}
