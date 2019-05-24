package com.hisun.CI;

public class CICOMRC2_DATA {
    public long LIST_NO = 0;
    public String ID_TYPE = " ";
    public String ID_NO = " ";
    public char FILLER5 = 0X02;
    public String CI_NM = " ";
    public char FILLER7 = 0X02;
    public String CI_ENM = " ";
    public char FILLER9 = 0X02;
    public char S83_EXEMPT = ' ';
    public CICOMRC2_IDE_AREA[] IDE_AREA = new CICOMRC2_IDE_AREA[30];
    public CICOMRC2_DATA() {
        for (int i=0;i<30;i++) IDE_AREA[i] = new CICOMRC2_IDE_AREA();
    }
}
