package com.hisun.DC;

public class DCCIIHLD_OUT_DATA_TEXT {
    public short OUT_INT = 0;
    public DCCIIHLD_OUT_DATA[] OUT_DATA = new DCCIIHLD_OUT_DATA[99];
    public DCCIIHLD_OUT_DATA_TEXT() {
        for (int i=0;i<99;i++) OUT_DATA[i] = new DCCIIHLD_OUT_DATA();
    }
}
