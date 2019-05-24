package com.hisun.TD;

public class TDCBKBAL {
    public String OPP_AC_NO = " ";
    public char END_FLG = ' ';
    public TDCBKBAL_ITEM[] ITEM = new TDCBKBAL_ITEM[20];
    public String SMK = " ";
    public TDCBKBAL() {
        for (int i=0;i<20;i++) ITEM[i] = new TDCBKBAL_ITEM();
    }
}
