package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFUDER {
    int JIBS_tmp_int;
    String K_PGM_NAME = "BPZFUDER";
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_F_T_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_EXG_CURRENCY = "BP-EX               ";
    String WS_ERR_MSG = " ";
    char WS_DER_FLG = ' ';
    short WS_IDX = 0;
    short WS_NO = 0;
    short WS_CNT1 = 0;
    short WS_CNT2 = 0;
    char WS_FEE_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCFX BPCFX = new BPCFX();
    BPCFPARM BPCFPARM = new BPCFPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPVFEXP BPVFEXP = new BPVFEXP();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    SCCGWA SCCGWA;
    BPCOFDER BPCOUDER;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFDER BPCOUDER) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUDER = BPCOUDER;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFUDER return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCOUDER.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCOUDER.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOUDER.INFO.DER_CODE);
        if (BPCOUDER.INFO.DER_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FDER_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPVFEXP);
        BPVFEXP.KEY.DER_CODE = BPCOUDER.INFO.DER_CODE;
        BPCFPARM.INFO.POINTER = BPVFEXP;
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FEXP ";
        S000_CALL_BPZFPARM();
        if (BPCFPARM.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DER_CODE_NOTFOUND;
            S000_ERR_MSG_PROC();
        }
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            if (BPCOUDER.INFO.DATA[WS_CNT1-1].FEE_CODE.trim().length() > 0 
                && !BPCOUDER.INFO.DATA[WS_CNT1-1].FEE_CODE.equalsIgnoreCase("0")) {
                CEP.TRC(SCCGWA, WS_CNT1);
                CEP.TRC(SCCGWA, BPCOUDER.INFO.DATA[WS_CNT1-1].FEE_CODE);
                CEP.TRC(SCCGWA, BPCOUDER.INFO.DATA[WS_CNT1-1].CHG_CCY);
                R000_GET_FEE_NO();
                CEP.TRC(SCCGWA, BPRFBAS.FEE_NO);
                WS_IDX = (short) (BPRFBAS.FEE_NO / 254);
                WS_NO = (short) (BPRFBAS.FEE_NO - WS_IDX * 254);
                if (WS_IDX == 0) {
                    if (BPVFEXP.VAL.FEE_MASK1 == null) BPVFEXP.VAL.FEE_MASK1 = "";
                    JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK1.length();
                    for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK1 += " ";
                    WS_DER_FLG = BPVFEXP.VAL.FEE_MASK1.substring(WS_NO - 1, WS_NO + 1 - 1).charAt(0);
                } else if (WS_IDX == 1) {
                    if (BPVFEXP.VAL.FEE_MASK2 == null) BPVFEXP.VAL.FEE_MASK2 = "";
                    JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK2.length();
                    for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK2 += " ";
                    WS_DER_FLG = BPVFEXP.VAL.FEE_MASK2.substring(WS_NO - 1, WS_NO + 1 - 1).charAt(0);
                } else if (WS_IDX == 2) {
                    if (BPVFEXP.VAL.FEE_MASK3 == null) BPVFEXP.VAL.FEE_MASK3 = "";
                    JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK3.length();
                    for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK3 += " ";
                    WS_DER_FLG = BPVFEXP.VAL.FEE_MASK3.substring(WS_NO - 1, WS_NO + 1 - 1).charAt(0);
                } else if (WS_IDX == 3) {
                    if (BPVFEXP.VAL.FEE_MASK4 == null) BPVFEXP.VAL.FEE_MASK4 = "";
                    JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK4.length();
                    for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK4 += " ";
                    WS_DER_FLG = BPVFEXP.VAL.FEE_MASK4.substring(WS_NO - 1, WS_NO + 1 - 1).charAt(0);
                } else {
                }
                CEP.TRC(SCCGWA, WS_DER_FLG);
                if (WS_DER_FLG == '1') {
                    R000_GET_DER_AMT();
                }
            }
        }
    }
    public void R000_GET_FEE_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        BPRFBAS.KEY.FEE_CODE = BPCOUDER.INFO.DATA[WS_CNT1-1].FEE_CODE;
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPCTFBAS.INFO.FUNC = 'Q';
        S000_CALL_BPZTFBAS();
        if (BPCTFBAS.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_GET_DER_AMT() throws IOException,SQLException,Exception {
        WS_FEE_FLAG = 'D';
        for (WS_CNT2 = 1; WS_CNT2 <= 50 
            && WS_FEE_FLAG != 'N'; WS_CNT2 += 1) {
            CEP.TRC(SCCGWA, BPVFEXP.VAL.EXP_DATA[WS_CNT2-1].FEE_CODE);
            if (BPVFEXP.VAL.EXP_DATA[WS_CNT2-1].FEE_CODE.equalsIgnoreCase(BPCOUDER.INFO.DATA[WS_CNT1-1].FEE_CODE)) {
                BPCOUDER.INFO.DATA[WS_CNT1-1].REL_FLG = BPVFEXP.VAL.EXP_DATA[WS_CNT2-1].DER_FLG;
                CEP.TRC(SCCGWA, BPVFEXP.VAL.EXP_DATA[WS_CNT2-1].MAX_PERCENT);
                CEP.TRC(SCCGWA, BPVFEXP.VAL.EXP_DATA[WS_CNT2-1].MAX_AMT);
                if (BPVFEXP.VAL.EXP_DATA[WS_CNT2-1].MAX_PERCENT > 0) {
                    BPCOUDER.INFO.DATA[WS_CNT1-1].DER_AMT = BPCOUDER.INFO.DATA[WS_CNT1-1].CHG_AMT * BPVFEXP.VAL.EXP_DATA[WS_CNT2-1].MAX_PERCENT / 100;
                } else {
                    BPCOUDER.INFO.DATA[WS_CNT1-1].DER_AMT = BPVFEXP.VAL.EXP_DATA[WS_CNT2-1].MAX_AMT;
                    if (!BPCOUDER.INFO.DATA[WS_CNT1-1].CHG_CCY.equalsIgnoreCase(BPVFEXP.VAL.EXP_DATA[WS_CNT2-1].CCY)) {
                        T000_EXCHANGE_CURRENCY();
                    }
                }
                BPCOUDER.INFO.DATA[WS_CNT1-1].DER_AUTH = BPVFEXP.VAL.EXP_DATA[WS_CNT2-1].AUTH_LVL;
                WS_FEE_FLAG = 'N';
                CEP.TRC(SCCGWA, BPCOUDER.INFO.DATA[WS_CNT1-1].DER_AMT);
            }
        }
    }
    public void T000_EXCHANGE_CURRENCY() throws IOException,SQLException,Exception {
        R000_TRANS_BPCEX_IN();
        S000_CALL_BPZSFX();
        R000_TRANS_BPCEX_OUT();
    }
    public void R000_TRANS_BPCEX_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.EXR_TYPE = "MDR";
        BPCFX.SELL_AMT = BPCOUDER.INFO.DATA[WS_CNT1-1].DER_AMT;
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.BUY_CCY = BPCOUDER.INFO.DATA[WS_CNT1-1].CHG_CCY;
        BPCFX.SELL_CCY = BPVFEXP.VAL.EXP_DATA[WS_CNT2-1].CCY;
        CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
    }
    public void R000_TRANS_BPCEX_OUT() throws IOException,SQLException,Exception {
        BPCOUDER.INFO.DATA[WS_CNT1-1].DER_AMT = BPCFX.BUY_AMT;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_MAINTAIN_PARM, BPCFPARM);
        if (BPCFPARM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFPARM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_T_FEE_INFO, BPCTFBAS);
        if (BPCTFBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTFBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOUDER.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOUDER = ");
            CEP.TRC(SCCGWA, BPCOUDER);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
