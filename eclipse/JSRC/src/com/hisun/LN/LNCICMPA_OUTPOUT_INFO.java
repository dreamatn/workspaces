package com.hisun.LN;

public class LNCICMPA_OUTPOUT_INFO {
    public char PART_FLG = ' ';
    public LNCICMPA_PART_INFO[] PART_INFO = new LNCICMPA_PART_INFO[99];
    public LNCICMPA_OUTPOUT_INFO() {
        for (int i=0;i<99;i++) PART_INFO[i] = new LNCICMPA_PART_INFO();
    }
}
