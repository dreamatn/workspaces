package com.hisun.TD;

import java.util.ArrayList;
import java.util.List;


public class TDCQDINT {
    public long TXN_JRN = 0;
    public String BV_NO = " ";
    public int AC_SEQ = 0;
    public String AC = " ";
    public String AC_NAME = " ";
    public char FILLER6 = 0X02;
    public String CCY = " ";
    public int AC_DT = 0;
    public String PROD_CD = " ";
    public double DRAW_AMT = 0;
    public char FILLER11 = 0X01;
    public double PAYING_AMT = 0;
    public char FILLER13 = 0X01;
    public double TAXING_INT = 0;
    public char FILLER15 = 0X01;
    public double TAX_RAT = 0;
    public double PAYING_TAX = 0;
    public char FILLER18 = 0X01;
    public double PAYED_INT = 0;
    public char FILLER20 = 0X01;
    public double TOT_AMT = 0;
    public char FILLER22 = 0X01;
    public short TOT_NUM = 0;
    public List<TDCQDINT_INT_INFO> INT_INFO = new ArrayList<TDCQDINT_INT_INFO>();
}
