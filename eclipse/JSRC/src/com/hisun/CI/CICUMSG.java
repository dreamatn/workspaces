package com.hisun.CI;

public class CICUMSG {
    public CICUMSG_RC RC = new CICUMSG_RC();
    public char FUNC = ' ';
    public CICUMSG_DATA[] DATA = new CICUMSG_DATA[10];
    public CICUMSG() {
        for (int i=0;i<10;i++) DATA[i] = new CICUMSG_DATA();
    }
}
