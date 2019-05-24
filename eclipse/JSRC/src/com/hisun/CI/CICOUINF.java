package com.hisun.CI;

public class CICOUINF {
    public String LC_OBJ = " ";
    public String LC_OBJ_TYP = " ";
    public CICOUINF_DTL[] DTL = new CICOUINF_DTL[9];
    public char END_FLG = ' ';
    public CICOUINF() {
        for (int i=0;i<9;i++) DTL[i] = new CICOUINF_DTL();
    }
}
