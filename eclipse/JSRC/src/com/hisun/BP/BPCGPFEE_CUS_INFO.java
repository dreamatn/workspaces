package com.hisun.BP;

public class BPCGPFEE_CUS_INFO {
    public String CUS_NO = " ";
    public String CUS_TYPE = " ";
    public char CUS_STS = ' ';
    public String CUS_GRP = " ";
    public String CUS_SEG = " ";
    public double CUS_CON = 0;
    public BPCGPFEE_PRD[] PRD = new BPCGPFEE_PRD[10];
    public double CUS_AVG = 0;
    public BPCGPFEE_CUS_INFO() {
        for (int i=0;i<10;i++) PRD[i] = new BPCGPFEE_PRD();
    }
}
