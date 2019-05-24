package com.hisun.TD;

public class TDC5000 {
    public String PRDMO_CD = " ";
    public String PROD_CD = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public char FILLER5 = 0X02;
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public char FUNC = ' ';
    public TDC5000_TXN_PRM TXN_PRM = new TDC5000_TXN_PRM();
    public TDC5000_INT_PRM INT_PRM = new TDC5000_INT_PRM();
    public TDC5000_EXP_PRM EXP_PRM = new TDC5000_EXP_PRM();
    public TDC5000_OTH_PRM OTH_PRM = new TDC5000_OTH_PRM();
}
