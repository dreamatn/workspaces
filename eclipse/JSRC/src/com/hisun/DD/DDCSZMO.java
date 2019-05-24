package com.hisun.DD;

public class DDCSZMO {
    public int AC_CNT = 0;
    public DDCSZMO_AC_LIST[] AC_LIST = new DDCSZMO_AC_LIST[6];
    public int BV_CNT = 0;
    public DDCSZMO_BV_LIST[] BV_LIST = new DDCSZMO_BV_LIST[99];
    public DDCSZMO() {
        for (int i=0;i<6;i++) AC_LIST[i] = new DDCSZMO_AC_LIST();
        for (int i=0;i<99;i++) BV_LIST[i] = new DDCSZMO_BV_LIST();
    }
}
