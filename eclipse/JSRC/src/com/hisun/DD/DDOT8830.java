package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT8830 {
    String CDD_I_VS_INFO = "DD-I-VS-INFO";
    char K_FUNC_BROWSE = '1';
    char K_FUNC_QUERY = '2';
    char K_VS_STS_NORMAL = 'N';
    char K_VS_STS_CLOSE = 'C';
    char K_VS_STS_FORBID = 'T';
    char K_VS_STS_HOLD = 'F';
    short K_MAX_ROWS = 18;
    String WS_MSG_ID = " ";
    int WS_PAGE_ROW = 0;
    int WS_PAGE_NUM = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCUINVS DDCUINVS = new DDCUINVS();
    SCCGWA SCCGWA;
    DDB8830_AWA_8830 DDB8830_AWA_8830;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT8830 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8830_AWA_8830>");
        DDB8830_AWA_8830 = (DDB8830_AWA_8830) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB8830_AWA_8830.PARE_AC);
        if (DDB8830_AWA_8830.PARE_AC.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDB8830_AWA_8830.VS_STS);
        if (DDB8830_AWA_8830.VS_STS == ' ' 
            || DDB8830_AWA_8830.VS_STS == K_VS_STS_NORMAL 
            || DDB8830_AWA_8830.VS_STS == K_VS_STS_CLOSE 
            || DDB8830_AWA_8830.VS_STS == K_VS_STS_FORBID 
            || DDB8830_AWA_8830.VS_STS == K_VS_STS_HOLD) {
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_STS_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDB8830_AWA_8830.PAGE_ROW);
        if (DDB8830_AWA_8830.PAGE_ROW != 0) {
            if (DDB8830_AWA_8830.PAGE_ROW < 0 
                || DDB8830_AWA_8830.PAGE_ROW > K_MAX_ROWS) {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_ROWS_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDB8830_AWA_8830.PAGE_ROW == 0 
            || DDB8830_AWA_8830.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROWS;
        } else {
            WS_PAGE_ROW = DDB8830_AWA_8830.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, DDB8830_AWA_8830.PAGE_NUM);
        if (DDB8830_AWA_8830.PAGE_NUM == 0) {
            WS_PAGE_NUM = 0;
        } else {
            WS_PAGE_NUM = DDB8830_AWA_8830.PAGE_NUM;
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUINVS);
        DDCUINVS.I_FUNC = K_FUNC_BROWSE;
        DDCUINVS.PARE_AC = DDB8830_AWA_8830.PARE_AC;
        DDCUINVS.VS_STS = DDB8830_AWA_8830.VS_STS;
        DDCUINVS.PAGE_ROW = WS_PAGE_ROW;
        DDCUINVS.PAGE_NUM = WS_PAGE_NUM;
        CEP.TRC(SCCGWA, DDB8830_AWA_8830.PAGE_NUM);
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
