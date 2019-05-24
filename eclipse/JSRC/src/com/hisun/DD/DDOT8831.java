package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT8831 {
    String CDD_I_VS_INFO = "DD-I-VS-INFO";
    char K_FUNC_BROWSE = '1';
    char K_FUNC_QUERY = '2';
    short K_MAX_ROWS = 25;
    String WS_MSG_ID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCUINVS DDCUINVS = new DDCUINVS();
    SCCGWA SCCGWA;
    DDB8831_AWA_8831 DDB8831_AWA_8831;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT8831 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8831_AWA_8831>");
        DDB8831_AWA_8831 = (DDB8831_AWA_8831) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "AA");
        if (DDB8831_AWA_8831.VS_AC.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "BB");
        if (DDB8831_AWA_8831.CCY.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "CC");
        if (DDB8831_AWA_8831.CCY_TYP == ' ') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUINVS);
        DDCUINVS.I_FUNC = K_FUNC_QUERY;
        DDCUINVS.VS_AC = DDB8831_AWA_8831.VS_AC;
        DDCUINVS.CCY = DDB8831_AWA_8831.CCY;
        DDCUINVS.CCY_TYP = DDB8831_AWA_8831.CCY_TYP;
        S000_CALL_DDZUINVS();
    }
    public void S000_CALL_DDZUINVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_I_VS_INFO, DDCUINVS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
