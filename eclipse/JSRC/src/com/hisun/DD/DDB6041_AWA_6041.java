package com.hisun.DD;

public class DDB6041_AWA_6041 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String LCNTR_NO = " ";
    public short LCNTR_NO_NO = 0;
    public short LNTX_SEQ = 0;
    public short LNTX_SEQ_NO = 0;
    public short NUM = 0;
    public short NUM_NO = 0;
    public char INR_FLG = ' ';
    public short INR_FLG_NO = 0;
    public char LNN_FLG = ' ';
    public short LNN_FLG_NO = 0;
    public DDB6041_AC_LIST[] AC_LIST = new DDB6041_AC_LIST[10];
    public DDB6041_AWA_6041() {
        for (int i=0;i<10;i++) AC_LIST[i] = new DDB6041_AC_LIST();
    }
}
