package com.hisun.BP;

public class BPCFFCII_INFO {
    public String CUS_NO = " ";
    public String CUS_TYPE = " ";
    public char CUS_STS = ' ';
    public String CUS_GRP = " ";
    public String CUS_SEG = " ";
    public double CUS_CON = 0;
    public BPCFFCII_PRD[] PRD = new BPCFFCII_PRD[10];
    public double CUS_AVG = 0;
    public BPCFFCII_INFO() {
        for (int i=0;i<10;i++) PRD[i] = new BPCFFCII_PRD();
    }
}
