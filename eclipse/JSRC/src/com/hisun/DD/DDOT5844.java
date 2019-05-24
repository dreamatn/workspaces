package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5844 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSCCLC DDCSCCLC = new DDCSCCLC();
    SCCGWA SCCGWA;
    DDB5844_AWA_5844 DDB5844_AWA_5844;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5844 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5844_AWA_5844>");
        DDB5844_AWA_5844 = (DDB5844_AWA_5844) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5844_AWA_5844.CARD_NO);
        CEP.TRC(SCCGWA, DDB5844_AWA_5844.AC);
        CEP.TRC(SCCGWA, DDB5844_AWA_5844.CCY);
        CEP.TRC(SCCGWA, DDB5844_AWA_5844.CCY_TYPE);
        CEP.TRC(SCCGWA, DDB5844_AWA_5844.PSBK_NO);
        CEP.TRC(SCCGWA, DDB5844_AWA_5844.NRTV);
        CEP.TRC(SCCGWA, DDB5844_AWA_5844.RMK);
        if (DDB5844_AWA_5844.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB5844_AWA_5844.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5844_AWA_5844.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB5844_AWA_5844.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5844_AWA_5844.CCY_TYPE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT);
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCCLC);
        DDCSCCLC.FUNC = 'V';
        DDCSCCLC.CARD_NO = DDB5844_AWA_5844.CARD_NO;
        DDCSCCLC.AC = DDB5844_AWA_5844.AC;
        DDCSCCLC.CCY = DDB5844_AWA_5844.CCY;
        DDCSCCLC.CCY_TYPE = DDB5844_AWA_5844.CCY_TYPE;
        DDCSCCLC.PSBK_NO = DDB5844_AWA_5844.PSBK_NO;
        DDCSCCLC.RMK = DDB5844_AWA_5844.RMK;
        DDCSCCLC.NRTV = DDB5844_AWA_5844.NRTV;
        CEP.TRC(SCCGWA, DDCSCCLC.FUNC);
        CEP.TRC(SCCGWA, DDCSCCLC.CARD_NO);
        CEP.TRC(SCCGWA, DDCSCCLC.AC);
        CEP.TRC(SCCGWA, DDCSCCLC.CCY);
        CEP.TRC(SCCGWA, DDCSCCLC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCCLC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSCCLC.RMK);
        CEP.TRC(SCCGWA, DDCSCCLC.NRTV);
        S000_CALL_DDZSCCLC();
    }
    public void S000_CALL_DDZSCCLC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CLS-C-AC", DDCSCCLC);
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
