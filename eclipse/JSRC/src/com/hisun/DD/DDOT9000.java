package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT9000 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSPLOS DDCSPLOS = new DDCSPLOS();
    SCCGWA SCCGWA;
    DDB9000_AWA_9000 DDB9000_AWA_9000;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT9000 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB9000_AWA_9000>");
        DDB9000_AWA_9000 = (DDB9000_AWA_9000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB9000_AWA_9000.REG_MTH);
        CEP.TRC(SCCGWA, DDB9000_AWA_9000.PSBK_NO);
        CEP.TRC(SCCGWA, DDB9000_AWA_9000.AC_NO);
        CEP.TRC(SCCGWA, DDB9000_AWA_9000.ID_TYPE);
        CEP.TRC(SCCGWA, DDB9000_AWA_9000.ID_NO);
        if (DDB9000_AWA_9000.REG_MTH == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            WS_FLD_NO = DDB9000_AWA_9000.REG_MTH_NO;
        }
        if (DDB9000_AWA_9000.PSBK_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PBSK_NO_M_INPUT;
            WS_FLD_NO = DDB9000_AWA_9000.PSBK_NO_NO;
        }
        if (DDB9000_AWA_9000.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB9000_AWA_9000.AC_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB9000_AWA_9000.ID_TYPE.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_TYP_M_INPUT;
            WS_FLD_NO = DDB9000_AWA_9000.ID_TYPE_NO;
        }
        if (DDB9000_AWA_9000.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_NO_M_INPUT;
            WS_FLD_NO = DDB9000_AWA_9000.ID_NO_NO;
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSPLOS);
        DDCSPLOS.REG_METHOD = DDB9000_AWA_9000.REG_MTH;
        DDCSPLOS.BV_CD = DDB9000_AWA_9000.BV_CD;
        DDCSPLOS.BV_TYPE = DDB9000_AWA_9000.BV_TYPE;
        DDCSPLOS.PSBK_NO = DDB9000_AWA_9000.PSBK_NO;
        DDCSPLOS.CARD_NO = DDB9000_AWA_9000.CARD_NO;
        DDCSPLOS.AC_NO = DDB9000_AWA_9000.AC_NO;
        DDCSPLOS.AC_CNM = DDB9000_AWA_9000.AC_CNM;
        DDCSPLOS.AC_ENM = DDB9000_AWA_9000.AC_ENM;
        DDCSPLOS.DRAW_MTH = DDB9000_AWA_9000.DRAW_MTH;
        DDCSPLOS.ID_TYPE = DDB9000_AWA_9000.ID_TYPE;
        DDCSPLOS.ID_NO = DDB9000_AWA_9000.ID_NO;
        DDCSPLOS.LOS_NO = DDB9000_AWA_9000.LOS_NO;
        S000_CALL_DDZSPLOS();
    }
    public void S000_CALL_DDZSPLOS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-PSB-PSW-LOS", DDCSPLOS);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
