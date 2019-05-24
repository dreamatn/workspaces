package com.hisun.DC;

public class DCCSDCCD {
    public char FUNC = ' ';
    public String PROD_CODE = " ";
    public char TYPE = ' ';
    public char STS = ' ';
    public DCCSDCCD_TXT[] TXT = new DCCSDCCD_TXT[10];
    public DCCSDCCD() {
        for (int i=0;i<10;i++) TXT[i] = new DCCSDCCD_TXT();
    }
}
