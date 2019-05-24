package com.hisun.EA;

public class EACO890 {
    public String CARD_NO = " ";
    public char END_FLG = ' ';
    public int BEG_SEQ = 0;
    public EACO890_AC_ARRAY[] AC_ARRAY = new EACO890_AC_ARRAY[5];
    public EACO890() {
        for (int i=0;i<5;i++) AC_ARRAY[i] = new EACO890_AC_ARRAY();
    }
}
