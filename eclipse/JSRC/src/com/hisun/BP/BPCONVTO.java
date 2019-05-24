package com.hisun.BP;

public class BPCONVTO {
    public int DATE = 0;
    public long JRNNO = 0;
    public int BR = 0;
    public String TLR = " ";
    public String INVNTY1 = " ";
    public String INVNAM1 = " ";
    public char FILLER7 = 0X02;
    public String INVNTY2 = " ";
    public String INVNAM2 = " ";
    public char FILLER10 = 0X02;
    public String HANDLER1 = " ";
    public String HANDNAM1 = " ";
    public char FILLER13 = 0X02;
    public String HANDLER2 = " ";
    public String HANDNAM2 = " ";
    public char FILLER16 = 0X02;
    public char CB_TYP = ' ';
    public char STS = ' ';
    public char CASH_STS = ' ';
    public String CCY = " ";
    public double MACH_AMT = 0;
    public char FILLER22 = 0X01;
    public double ACTU_AMT = 0;
    public char FILLER24 = 0X01;
    public char BV_STS = ' ';
    public BPCONVTO_BV_INFO[] BV_INFO = new BPCONVTO_BV_INFO[10];
    public int LAST_DT = 0;
    public String UPD_TLR = " ";
    public int OPEN_DT = 0;
    public String OPEN_TLR = " ";
    public String TS = " ";
    public BPCONVTO() {
        for (int i=0;i<10;i++) BV_INFO[i] = new BPCONVTO_BV_INFO();
    }
}
