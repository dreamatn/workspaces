package com.hisun.IB;

public class IBCOATRN_HEAD_INFO {
    public char H_FILLER = ' ';
    public String H_NOS_CD = " ";
    public String H_ENAME = " ";
    public char FILLER5 = 0X02;
    public String H_CCY = " ";
    public double H_OPEN_VBAL = 0;
    public char FILLER8 = 0X01;
    public double H_OPEN_LBAL = 0;
    public char FILLER10 = 0X01;
    public int H_FROMDATE = 0;
    public double H_CLOS_VBAL = 0;
    public char FILLER13 = 0X01;
    public double H_CLOS_LBAL = 0;
    public char FILLER15 = 0X01;
    public int H_TODATE = 0;
}
