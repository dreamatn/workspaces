package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2090 {
    String K_OUTPUT_FMT = "BAC01";
    String WS_ERR_MSG = " ";
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCFMT SCCFMT = new SCCFMT();
    BACUHOLD BACUHOLD = new BACUHOLD();
    SCCGWA SCCGWA;
    BAB2090_AWA_2090 BAB2090_AWA_2090;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAOT2090 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2090_AWA_2090>");
        BAB2090_AWA_2090 = (BAB2090_AWA_2090) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANCHE_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB2090_AWA_2090.ID_NO);
        CEP.TRC(SCCGWA, BAB2090_AWA_2090.OPT_CODE);
        if (BAB2090_AWA_2090.OPT_CODE != '1' 
            && BAB2090_AWA_2090.OPT_CODE != '2') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_OPT_CODE_ERROR;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BAB2090_AWA_2090.OPT_CODE == '1' 
            && BAB2090_AWA_2090.GSZF_NM.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_FREE_NM_CANNOT_SPACE;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BAB2090_AWA_2090.OPT_CODE == '1' 
            && BAB2090_AWA_2090.GSZF_ADD.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_FRZE_ADDR_CNNOT_SPA;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BAB2090_AWA_2090.OPT_CODE == '1' 
            && BAB2090_AWA_2090.GSZF_TEL.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_FRZE_TEL_CNNOT_SPA;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BAB2090_AWA_2090.OPT_CODE == '1' 
            && BAB2090_AWA_2090.GSZF_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_FRZE_GZ_NO_CNNOT_SPA;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BAB2090_AWA_2090.OPT_CODE == '1' 
            && BAB2090_AWA_2090.GSZF_BN.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_FRZE_GZ_BN_CNNOT_SPA;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BAB2090_AWA_2090.OPT_CODE == '1' 
            && BAB2090_AWA_2090.GS_EN_DT == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_FRZE_DUE_DT_CNOT_SPA;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BAB2090_AWA_2090.OPT_CODE == '1' 
            && BAB2090_AWA_2090.GS_BRK.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_FRZE_REASON_CNOT_SPA;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BAB2090_AWA_2090.OPT_CODE == '2' 
            && BAB2090_AWA_2090.JG_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_UNFRE_JG_NO_CNOT_SPA;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BAB2090_AWA_2090.OPT_CODE == '2' 
            && BAB2090_AWA_2090.JG_RMK.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_UNFR_JG_RES_CNOT_SPA;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BACMSG_ERROR_MSG.BA_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUHOLD);
        BACUHOLD.DATA.BILL_NO = BAB2090_AWA_2090.ID_NO;
        BACUHOLD.DATA.FUN_COD = BAB2090_AWA_2090.OPT_CODE;
        BACUHOLD.DATA.GSZF_NM = BAB2090_AWA_2090.GSZF_NM;
        BACUHOLD.DATA.GSZF_ADD = BAB2090_AWA_2090.GSZF_ADD;
        BACUHOLD.DATA.GSZF_TEL = BAB2090_AWA_2090.GSZF_TEL;
        BACUHOLD.DATA.GSZF_NO = BAB2090_AWA_2090.GSZF_NO;
        BACUHOLD.DATA.GSZF_BN = BAB2090_AWA_2090.GSZF_BN;
        BACUHOLD.DATA.GS_EN_DT = BAB2090_AWA_2090.GS_EN_DT;
        BACUHOLD.DATA.GS_BRK = BAB2090_AWA_2090.GS_BRK;
        BACUHOLD.DATA.JG_NO = BAB2090_AWA_2090.JG_NO;
        BACUHOLD.DATA.JG_RMK = BAB2090_AWA_2090.JG_RMK;
        BACUHOLD.DATA.RMK = BAB2090_AWA_2090.RMK;
        S000_CALL_BAZUHOLD();
    }
    public void S000_CALL_BAZUHOLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-HOLD-REM", BACUHOLD);
        if (BACUHOLD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUHOLD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
