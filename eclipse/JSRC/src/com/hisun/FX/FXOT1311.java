package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1311 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_EX_HOUR = 0;
    int WS_EX_MINU = 0;
    int WS_EX_SECO = 0;
    int WS_TR_HOUR = 0;
    int WS_TR_MINU = 0;
    int WS_TR_SECO = 0;
    int WS_EX_SEC = 0;
    int WS_TR_SEC = 0;
    int WS_TM_DIF = 0;
    int WS_TMDIF_TM_DIF = 0;
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    FXCSBFXT FXCSBFXT = new FXCSBFXT();
    BPCOIEC BPCOIEC = new BPCOIEC();
    FXRTMDIF FXRTMDIF = new FXRTMDIF();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGMSG SCCGMSG;
    FXB1310_AWA_1310 FXB1310_AWA_1310;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1311 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        IBS.init(SCCGWA, SCCMSG);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1310_AWA_1310>");
        FXB1310_AWA_1310 = (FXB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.CI_NO);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.TRA_AC);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.CI_CNM);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.PROD_CD);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.PROD_NM);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.IN_CCY);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.B_CS_FLG);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.BUY_CCY);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.BUY_AMT);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.S_CS_FLG);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.SELL_CCY);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.SELL_AMT);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.BEFF_TM);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.RATE_TM);
        if (FXB1310_AWA_1310.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_MUST_INPUT_ONE);
        }
        if (FXB1310_AWA_1310.PROD_CD.trim().length() == 0 
            || FXB1310_AWA_1310.PROD_CD.equalsIgnoreCase("0")) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_MUST_INPUT_PROD_CD);
        }
        if (!FXB1310_AWA_1310.BUY_CCY.equalsIgnoreCase("156")) {
            if (FXB1310_AWA_1310.B_CS_FLG != '0' 
                && FXB1310_AWA_1310.B_CS_FLG != '1') {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_INVALID);
            }
        }
        if (!FXB1310_AWA_1310.SELL_CCY.equalsIgnoreCase("156")) {
            if (FXB1310_AWA_1310.S_CS_FLG != '0' 
                && FXB1310_AWA_1310.S_CS_FLG != '1') {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_INVALID);
            }
        }
        if (FXB1310_AWA_1310.BUY_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CCY_MISSED);
        }
        if (FXB1310_AWA_1310.BUY_AMT == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_MUST_INPUT_AMT);
        }
        if (FXB1310_AWA_1310.SELL_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CCY_MISSED);
        }
        if (FXB1310_AWA_1310.SELL_AMT == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_MUST_INPUT_AMT);
        }
        if (FXB1310_AWA_1310.EX_CODE.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_EX_CODE_ERR);
        }
        if (FXB1310_AWA_1310.EX_GROUP.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_EX_TYP_ERR);
        }
        if (FXB1310_AWA_1310.RATE_TM == 0 
            || FXB1310_AWA_1310.RATE_TM == ' ') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_RATE_TM_ERR);
        }
        B010_10_CHK_RATE_TM();
    }
    public void B020_ADD_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.CRE_DT);
        IBS.init(SCCGWA, FXCSBFXT);
        FXCSBFXT.CI_NO = FXB1310_AWA_1310.CI_NO;
        FXCSBFXT.CI_ENM = FXB1310_AWA_1310.CI_ENM;
        FXCSBFXT.CI_CNM = FXB1310_AWA_1310.CI_CNM;
        FXCSBFXT.ID_TYP = FXB1310_AWA_1310.ID_TYP;
        FXCSBFXT.ID_NO = FXB1310_AWA_1310.ID_NO;
        FXCSBFXT.CNTY_CD = FXB1310_AWA_1310.CNTY_CD;
        FXCSBFXT.CNTY_FLG = FXB1310_AWA_1310.CNTY_FLG;
        FXCSBFXT.TRA_AC = FXB1310_AWA_1310.TRA_AC;
        FXCSBFXT.PROD_CD = FXB1310_AWA_1310.PROD_CD;
        FXCSBFXT.PROD_NM = FXB1310_AWA_1310.PROD_NM;
        FXCSBFXT.B_S_FLG = FXB1310_AWA_1310.B_S_FLG;
        FXCSBFXT.B_CS_FLG = FXB1310_AWA_1310.B_CS_FLG;
        FXCSBFXT.BUY_CCY = FXB1310_AWA_1310.BUY_CCY;
        FXCSBFXT.BUY_AMT = FXB1310_AWA_1310.BUY_AMT;
        FXCSBFXT.S_CS_FLG = FXB1310_AWA_1310.S_CS_FLG;
        FXCSBFXT.SELL_CCY = FXB1310_AWA_1310.SELL_CCY;
        FXCSBFXT.SELL_AMT = FXB1310_AWA_1310.SELL_AMT;
        FXCSBFXT.ORDER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        FXCSBFXT.EX_GROUP = FXB1310_AWA_1310.EX_GROUP;
        FXCSBFXT.SYS_RATE = FXB1310_AWA_1310.SYS_RATE;
        FXCSBFXT.PRE_RATE = FXB1310_AWA_1310.PRE_RATE;
        FXCSBFXT.SPREAD = FXB1310_AWA_1310.SPREAD;
        FXCSBFXT.EX_RATE = FXB1310_AWA_1310.EX_RATE;
        FXCSBFXT.RATE_TM = FXB1310_AWA_1310.RATE_TM;
        FXCSBFXT.VALUE_DT = FXB1310_AWA_1310.VALUE_DT;
        FXCSBFXT.OPT_END_DT = FXB1310_AWA_1310.END_DT;
        FXCSBFXT.BEFF_TM = FXB1310_AWA_1310.BEFF_TM;
        FXCSBFXT.RMK = FXB1310_AWA_1310.RMK;
        FXCSBFXT.CRE_DT = FXB1310_AWA_1310.CRE_DT;
        IBS.init(SCCGWA, BPCOIEC);
        BPCOIEC.CCY1 = FXB1310_AWA_1310.BUY_CCY;
        BPCOIEC.CCY2 = FXB1310_AWA_1310.SELL_CCY;
        if (FXB1310_AWA_1310.BUY_CCY.equalsIgnoreCase(FXB1310_AWA_1310.SELL_CCY)) {
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            if (FXB1310_AWA_1310.BUY_CCY == null) FXB1310_AWA_1310.BUY_CCY = "";
            JIBS_tmp_int = FXB1310_AWA_1310.BUY_CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) FXB1310_AWA_1310.BUY_CCY += " ";
            BPCOIEC.EXR_CODE = FXB1310_AWA_1310.BUY_CCY + BPCOIEC.EXR_CODE.substring(3);
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            if (FXB1310_AWA_1310.SELL_CCY == null) FXB1310_AWA_1310.SELL_CCY = "";
            JIBS_tmp_int = FXB1310_AWA_1310.SELL_CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) FXB1310_AWA_1310.SELL_CCY += " ";
            BPCOIEC.EXR_CODE = BPCOIEC.EXR_CODE.substring(0, 4 - 1) + FXB1310_AWA_1310.SELL_CCY + BPCOIEC.EXR_CODE.substring(4 + 3 - 1);
        } else {
            S000_CALL_BPZSIEC();
        }
        FXCSBFXT.EX_CODE = BPCOIEC.EXR_CODE;
        CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
        CEP.TRC(SCCGWA, FXCSBFXT.EX_CODE);
        FXCSBFXT.FUNC = 'A';
        S000_CALL_FXZSBFXT();
    }
    public void B010_10_CHK_RATE_TM() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + FXB1310_AWA_1310.RATE_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_EX_HOUR = 0;
        else WS_EX_HOUR = Integer.parseInt(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + FXB1310_AWA_1310.RATE_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_EX_MINU = 0;
        else WS_EX_MINU = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + FXB1310_AWA_1310.RATE_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_EX_SECO = 0;
        else WS_EX_SECO = Integer.parseInt(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_TR_HOUR = 0;
        else WS_TR_HOUR = Integer.parseInt(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_TR_MINU = 0;
        else WS_TR_MINU = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_TR_SECO = 0;
        else WS_TR_SECO = Integer.parseInt(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        CEP.TRC(SCCGWA, WS_EX_HOUR);
        CEP.TRC(SCCGWA, WS_EX_MINU);
        CEP.TRC(SCCGWA, WS_EX_SECO);
        CEP.TRC(SCCGWA, WS_TR_HOUR);
        CEP.TRC(SCCGWA, WS_TR_MINU);
        CEP.TRC(SCCGWA, WS_TR_SECO);
        WS_EX_SEC = WS_EX_HOUR * 3600 + WS_EX_MINU * 60 + WS_EX_SECO;
        WS_TR_SEC = WS_TR_HOUR * 3600 + WS_TR_MINU * 60 + WS_TR_SECO;
        if (WS_EX_SEC > WS_TR_SEC) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_RT_TM_ERR);
        } else {
            WS_TM_DIF = WS_TR_SEC - WS_EX_SEC;
        }
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, FXRTMDIF);
        FXRTMDIF.KEY.TYP = "EXTM";
        FXRTMDIF.KEY.CD = "999";
        BPCPRMR.DAT_PTR = FXRTMDIF;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, FXRTMDIF.DATA_TXT.FX_RATE_MIN);
        WS_TMDIF_TM_DIF = FXRTMDIF.DATA_TXT.FX_RATE_MIN * 60;
        CEP.TRC(SCCGWA, WS_TM_DIF);
        CEP.TRC(SCCGWA, WS_TMDIF_TM_DIF);
        if (WS_TM_DIF > WS_TMDIF_TM_DIF) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_RATE_TIMEOUT);
        }
    }
    public void S000_CALL_FXZSBFXT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-S-MAIN-BFXT", FXCSBFXT);
    }
    public void S000_CALL_BPZSIEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_EXR_CODE, BPCOIEC);
        if (BPCOIEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCOIEC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
