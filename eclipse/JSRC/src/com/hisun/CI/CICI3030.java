package com.hisun.CI;

public class CICI3030 {
    public char FUNC = ' ';
    public String TYPE = " ";
    public CICI3030_TIMES[] TIMES = new CICI3030_TIMES[50];
    public CICI3030() {
        for (int i=0;i<50;i++) TIMES[i] = new CICI3030_TIMES();
    }
}
