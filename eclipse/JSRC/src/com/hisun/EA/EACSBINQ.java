package com.hisun.EA;

public class EACSBINQ {
    public String CARD_NO = " ";
    public int BEG_SEQ = 0;
    public String I_AC = " ";
    public int END_SEQ = 0;
    public char END_FLG = ' ';
    public EACSBINQ_AC_ARRAY[] AC_ARRAY = new EACSBINQ_AC_ARRAY[5];
    public EACSBINQ() {
        for (int i=0;i<5;i++) AC_ARRAY[i] = new EACSBINQ_AC_ARRAY();
    }
}
