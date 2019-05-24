package com.hisun.BP;

public class BPB5211_AWA_5211 {
    public int IN_BR = 0;
    public short IN_BR_NO = 0;
    public BPB5211_RT_INFO[] RT_INFO = new BPB5211_RT_INFO[30];
    public BPB5211_AWA_5211() {
        for (int i=0;i<30;i++) RT_INFO[i] = new BPB5211_RT_INFO();
    }
}
