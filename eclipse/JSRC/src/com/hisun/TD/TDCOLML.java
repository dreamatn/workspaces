package com.hisun.TD;

public class TDCOLML {
    public String PROD_CD = " ";
    public char END_FLG = ' ';
    public TDCOLML_CATY_CURRE[] CATY_CURRE = new TDCOLML_CATY_CURRE[10];
    public String SMK = " ";
    public TDCOLML() {
        for (int i=0;i<10;i++) CATY_CURRE[i] = new TDCOLML_CATY_CURRE();
    }
}
