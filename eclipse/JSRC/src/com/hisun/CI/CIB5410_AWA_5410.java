package com.hisun.CI;

public class CIB5410_AWA_5410 {
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public String NMTYP = " ";
    public short NMTYP_NO = 0;
    public String CI_NM = " ";
    public short CI_NM_NO = 0;
    public char OPEN = ' ';
    public short OPEN_NO = 0;
    public char STD = ' ';
    public short STD_NO = 0;
    public CIB5410_OCCURS11[] OCCURS11 = new CIB5410_OCCURS11[5];
    public CIB5410_AWA_5410() {
        for (int i=0;i<5;i++) OCCURS11[i] = new CIB5410_OCCURS11();
    }
}
