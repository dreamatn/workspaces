package com.hisun.EA;

public class EACSWINQ {
    public String REQ_SYS = " ";
    public int BEG_SEQ = 0;
    public String LST_AC = " ";
    public String AC_NAME = " ";
    public char FUNC = ' ';
    public EACSWINQ_AC_ARRAY[] AC_ARRAY = new EACSWINQ_AC_ARRAY[10];
    public EACSWINQ() {
        for (int i=0;i<10;i++) AC_ARRAY[i] = new EACSWINQ_AC_ARRAY();
    }
}
