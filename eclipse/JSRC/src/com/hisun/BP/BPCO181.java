package com.hisun.BP;

public class BPCO181 {
    public BPCO181_RC RC = new BPCO181_RC();
    public String OUTPUT_FMT = " ";
    public int CNT = 0;
    public char VB_FLG = ' ';
    public BPCO181_INFO[] INFO = new BPCO181_INFO[99];
    public BPCO181() {
        for (int i=0;i<99;i++) INFO[i] = new BPCO181_INFO();
    }
}
