package com.hisun.EA;

public class EACO870 {
    public String REQ_SYS = " ";
    public char END_FLG = ' ';
    public int BEG_SEQ = 0;
    public int TOTAL_CNT = 0;
    public EACO870_AC_ARRAY[] AC_ARRAY = new EACO870_AC_ARRAY[10];
    public EACO870() {
        for (int i=0;i<10;i++) AC_ARRAY[i] = new EACO870_AC_ARRAY();
    }
}
