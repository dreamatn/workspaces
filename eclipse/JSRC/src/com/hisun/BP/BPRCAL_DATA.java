package com.hisun.BP;

public class BPRCAL_DATA {
    public BPRCAL_MM[] MM = new BPRCAL_MM[12];
    public String CNTYS_CD = " ";
    public String CITY_CD = " ";
    public BPRCAL_DATA() {
        for (int i=0;i<12;i++) MM[i] = new BPRCAL_MM();
    }
}
