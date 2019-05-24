package com.hisun.TD;

public class TDCCESH {
    public String PROD_CD = " ";
    public TDCCESH_OUT_INFO[] OUT_INFO = new TDCCESH_OUT_INFO[5];
    public String SMK = " ";
    public char END_FLG = ' ';
    public TDCCESH() {
        for (int i=0;i<5;i++) OUT_INFO[i] = new TDCCESH_OUT_INFO();
    }
}
