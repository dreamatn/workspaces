package com.hisun.DC;

public class DCCIQBAL {
    public DCCIQBAL_RC RC = new DCCIQBAL_RC();
    public DCCIQBAL_INP_DATA INP_DATA = new DCCIQBAL_INP_DATA();
    public DCCIQBAL_OUT_DATA[] OUT_DATA = new DCCIQBAL_OUT_DATA[99];
    public DCCIQBAL() {
        for (int i=0;i<99;i++) OUT_DATA[i] = new DCCIQBAL_OUT_DATA();
    }
}
