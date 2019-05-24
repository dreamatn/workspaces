package com.hisun.TD;

public class TDCQPRD {
    public String PRDMO_CD = " ";
    public String PROD_CD = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public char FILLER5 = 0X02;
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public char FUNC = ' ';
    public TDCQPRD_TXN_PRM TXN_PRM = new TDCQPRD_TXN_PRM();
    public TDCQPRD_INT_PRM INT_PRM = new TDCQPRD_INT_PRM();
    public TDCQPRD_EXP_PRM EXP_PRM = new TDCQPRD_EXP_PRM();
    public TDCQPRD_OTH_PRM OTH_PRM = new TDCQPRD_OTH_PRM();
}
