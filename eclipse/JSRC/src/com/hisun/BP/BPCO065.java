package com.hisun.BP;

public class BPCO065 {
    public BPCO065_AC_DATA[] AC_DATA = new BPCO065_AC_DATA[10];
    public BPCO065() {
        for (int i=0;i<10;i++) AC_DATA[i] = new BPCO065_AC_DATA();
    }
}
