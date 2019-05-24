package com.hisun.DC;

public class DCCUHDQU {
    public DCCUHDQU_INPUT INPUT = new DCCUHDQU_INPUT();
    public DCCUHDQU_OUTPUT[] OUTPUT = new DCCUHDQU_OUTPUT[500];
    public DCCUHDQU() {
        for (int i=0;i<500;i++) OUTPUT[i] = new DCCUHDQU_OUTPUT();
    }
}
