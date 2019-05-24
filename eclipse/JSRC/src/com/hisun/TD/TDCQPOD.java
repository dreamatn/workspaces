package com.hisun.TD;

public class TDCQPOD {
    public char FUNC = ' ';
    public String PRDMO_CD = " ";
    public String PROD_CD = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public char FILLER6 = 0X02;
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public TDCQPOD_TXN_PRM TXN_PRM = new TDCQPOD_TXN_PRM();
    public TDCQPOD_INT_PRM INT_PRM = new TDCQPOD_INT_PRM();
    public TDCQPOD_EXP_PRM EXP_PRM = new TDCQPOD_EXP_PRM();
    public TDCQPOD_OTH_PRM OTH_PRM = new TDCQPOD_OTH_PRM();
}
