package com.hisun.GD;

public class GDCOMIAC_VAL {
    public short S_CNT = 0;
    public short CNT = 0;
    public GDCOMIAC_INF[] INF = new GDCOMIAC_INF[25];
    public GDCOMIAC_VAL() {
        for (int i=0;i<25;i++) INF[i] = new GDCOMIAC_INF();
    }
}
