package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT3130 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    char WS_FUNC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    DDCSCINT DDCSCINT = new DDCSCINT();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDB3130_AWA_3130 DDB3130_AWA_3130;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT3130 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB3130_AWA_3130>");
        DDB3130_AWA_3130 = (DDB3130_AWA_3130) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_OUT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB3130_AWA_3130.AC_NO);
        CEP.TRC(SCCGWA, DDB3130_AWA_3130.CCY);
        CEP.TRC(SCCGWA, DDB3130_AWA_3130.CCY_TYPE);
        CEP.TRC(SCCGWA, DDB3130_AWA_3130.STR_DATE);
        CEP.TRC(SCCGWA, DDB3130_AWA_3130.END_DATE);
        CEP.TRC(SCCGWA, DDB3130_AWA_3130.PAGE_ROW);
        CEP.TRC(SCCGWA, DDB3130_AWA_3130.PAGE_NUM);
        if (DDB3130_AWA_3130.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB3130_AWA_3130.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB3130_AWA_3130.CCY_TYPE != '1' 
            && DDB3130_AWA_3130.CCY_TYPE != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DDB3130_AWA_3130.STR_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FROM_DT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB3130_AWA_3130.END_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_DT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB3130_AWA_3130.STR_DATE > SCCGWA.COMM_AREA.TR_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_DATE_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DDB3130_AWA_3130.END_DATE < DDB3130_AWA_3130.STR_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_DATE_LT_FROM_DATE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCINT);
        DDCSCINT.AC_NO = DDB3130_AWA_3130.AC_NO;
        DDCSCINT.CCY = DDB3130_AWA_3130.CCY;
        DDCSCINT.CCY_TYPE = DDB3130_AWA_3130.CCY_TYPE;
        DDCSCINT.STR_DATE = DDB3130_AWA_3130.STR_DATE;
        DDCSCINT.END_DATE = DDB3130_AWA_3130.END_DATE;
        DDCSCINT.PAGE_ROW = DDB3130_AWA_3130.PAGE_ROW;
        DDCSCINT.PAGE_NUM = DDB3130_AWA_3130.PAGE_NUM;
        DDCSCINT.FUNC = 'Q';
        S000_CALL_DDZSCINT();
    }
    public void S000_CALL_DDZSCINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSCINT", DDCSCINT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
