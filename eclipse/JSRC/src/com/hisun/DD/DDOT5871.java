package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5871 {
    String CDD_SET_VTL_AC_STS = "DD-SET-VTL-AC-STS";
    char K_VS_STS_NORMAL = 'N';
    char K_VS_STS_FORBID = 'T';
    String WS_MSG_ID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCUSTVS DDCUSTVS = new DDCUSTVS();
    SCCGWA SCCGWA;
    DDB5871_AWA_5871 DDB5871_AWA_5871;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5871 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5871_AWA_5871>");
        DDB5871_AWA_5871 = (DDB5871_AWA_5871) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5871_AWA_5871.VS_AC);
        if (DDB5871_AWA_5871.VS_AC.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_MST_IPT;
            CEP.ERRC(SCCGWA, WS_MSG_ID, DDB5871_AWA_5871.VS_AC_NO);
        }
        if (DDB5871_AWA_5871.CCY.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            CEP.ERRC(SCCGWA, WS_MSG_ID, DDB5871_AWA_5871.CCY_NO);
        }
        CEP.TRC(SCCGWA, "CC");
        if (DDB5871_AWA_5871.CCY_TYP == ' ') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            CEP.ERRC(SCCGWA, WS_MSG_ID, DDB5871_AWA_5871.CCY_TYP_NO);
        }
        CEP.TRC(SCCGWA, DDB5871_AWA_5871.VS_STS);
        if (DDB5871_AWA_5871.VS_STS == K_VS_STS_NORMAL 
            || DDB5871_AWA_5871.VS_STS == K_VS_STS_FORBID) {
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_STS_ERR;
            CEP.ERRC(SCCGWA, WS_MSG_ID, DDB5871_AWA_5871.VS_STS_NO);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUSTVS);
        DDCUSTVS.IO_AREA.VS_AC = DDB5871_AWA_5871.VS_AC;
        DDCUSTVS.IO_AREA.CCY = DDB5871_AWA_5871.CCY;
        DDCUSTVS.IO_AREA.CCY_TYP = DDB5871_AWA_5871.CCY_TYP;
        DDCUSTVS.IO_AREA.VS_STS = DDB5871_AWA_5871.VS_STS;
        S000_CALL_DDZUSTVS();
    }
    public void S000_CALL_DDZUSTVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_SET_VTL_AC_STS, DDCUSTVS);
        CEP.TRC(SCCGWA, DDCUSTVS.O_AREA.RC_CODE);
        CEP.TRC(SCCGWA, DDCUSTVS.O_AREA.MSG_ID);
        if (DDCUSTVS.O_AREA.RC_CODE == 0) {
        } else {
            WS_MSG_ID = DDCUSTVS.O_AREA.MSG_ID;
            S000_ERR_MSG_PROC();
        }
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
