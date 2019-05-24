package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7721 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSQHOL DCCSQHOL = new DCCSQHOL();
    SCCGWA SCCGWA;
    DDB7721_AWA_7721 DDB7721_AWA_7721;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT7721 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB7721_AWA_7721>");
        DDB7721_AWA_7721 = (DDB7721_AWA_7721) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        B200_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (DDB7721_AWA_7721.AC.trim().length() == 0 
            && DDB7721_AWA_7721.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_OR_HLDNO_MST_INP;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSQHOL);
        DCCSQHOL.AC = DDB7721_AWA_7721.AC;
        DCCSQHOL.SEQ = DDB7721_AWA_7721.SEQ;
        DCCSQHOL.CCY = DDB7721_AWA_7721.CCY;
        DCCSQHOL.CCY_TYPE = DDB7721_AWA_7721.CCY_TYP;
        DCCSQHOL.BV_NO = DDB7721_AWA_7721.BV_NO;
        DCCSQHOL.HLD_STS = DDB7721_AWA_7721.HLD_STS;
        DCCSQHOL.HLD_NO = DDB7721_AWA_7721.HLD_NO;
        DCCSQHOL.SPR_TYP = DDB7721_AWA_7721.SPR_TYP;
        DCCSQHOL.PAGE_NUM = DDB7721_AWA_7721.PAGE_NUM;
        DCCSQHOL.PAGE_ROW = DDB7721_AWA_7721.PAGE_ROW;
        DCCSQHOL.HLD_FLG = '2';
        CEP.TRC(SCCGWA, DCCSQHOL.AC);
        CEP.TRC(SCCGWA, DCCSQHOL.HLD_NO);
        if (DCCSQHOL.AC.trim().length() > 0) {
            DCCSQHOL.FUNC = 'B';
        }
        if (DCCSQHOL.HLD_NO.trim().length() > 0) {
            DCCSQHOL.FUNC = 'I';
        }
        S000_CALL_DCZSQHOL();
    }
    public void S000_CALL_DCZSQHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-QHOL", DCCSQHOL);
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
