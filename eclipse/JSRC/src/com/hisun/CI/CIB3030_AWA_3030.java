package com.hisun.CI;

public class CIB3030_AWA_3030 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String TYPE = " ";
    public short TYPE_NO = 0;
    public CIB3030_TIMES[] TIMES = new CIB3030_TIMES[50];
    public CIB3030_AWA_3030() {
        for (int i=0;i<50;i++) TIMES[i] = new CIB3030_TIMES();
    }
}
