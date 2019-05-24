package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0640 {
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSJSTL DCCSJSTL = new DCCSJSTL();
    SCCGWA SCCGWA;
    DCB0640_AWA_0640 DCB0640_AWA_0640;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT0640 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0640_AWA_0640>");
        DCB0640_AWA_0640 = (DCB0640_AWA_0640) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB0640_AWA_0640.FUNC);
        CEP.TRC(SCCGWA, DCB0640_AWA_0640.CARD_NO);
        CEP.TRC(SCCGWA, DCB0640_AWA_0640.CARD_TYP);
        CEP.TRC(SCCGWA, DCB0640_AWA_0640.AC_NO);
        CEP.TRC(SCCGWA, DCB0640_AWA_0640.DEF_FLG);
        CEP.TRC(SCCGWA, DCB0640_AWA_0640.AC_NO1);
        CEP.TRC(SCCGWA, DCB0640_AWA_0640.AC_NAME);
        CEP.TRC(SCCGWA, DCB0640_AWA_0640.AC_TYPE);
        CEP.TRC(SCCGWA, DCB0640_AWA_0640.AC_STS);
        CEP.TRC(SCCGWA, DCB0640_AWA_0640.AC_FREZ);
        CEP.TRC(SCCGWA, DCB0640_AWA_0640.TRF_FLG);
        if (DCB0640_AWA_0640.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            WS_FLD_NO = DCB0640_AWA_0640.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0640_AWA_0640.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NO_MUST_INPUT;
            WS_FLD_NO = DCB0640_AWA_0640.CARD_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0640_AWA_0640.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACNO_MUST_INPUT;
            WS_FLD_NO = DCB0640_AWA_0640.AC_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0640_AWA_0640.FUNC == 'M') {
            if (DCB0640_AWA_0640.AC_NO1.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NEW_MUST_INPUT;
                WS_FLD_NO = DCB0640_AWA_0640.AC_NO1_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSJSTL);
        DCCSJSTL.CARD_NO = DCB0640_AWA_0640.CARD_NO;
        DCCSJSTL.CARD_TYP = DCB0640_AWA_0640.CARD_TYP;
        DCCSJSTL.AC_NO = DCB0640_AWA_0640.AC_NO;
        DCCSJSTL.DEF_FLG = DCB0640_AWA_0640.DEF_FLG;
        DCCSJSTL.AC_NO1 = DCB0640_AWA_0640.AC_NO1;
        DCCSJSTL.AC_NAME = DCB0640_AWA_0640.AC_NAME;
        DCCSJSTL.AC_TYPE = DCB0640_AWA_0640.AC_TYPE;
        DCCSJSTL.AC_STS = DCB0640_AWA_0640.AC_STS;
        DCCSJSTL.AC_FREZ = DCB0640_AWA_0640.AC_FREZ;
        DCCSJSTL.TRF_FLG = DCB0640_AWA_0640.TRF_FLG;
        if (DCB0640_AWA_0640.FUNC == 'A') {
            DCCSJSTL.FUNC = 'A';
        } else if (DCB0640_AWA_0640.FUNC == 'M') {
            DCCSJSTL.FUNC = 'M';
        } else if (DCB0640_AWA_0640.FUNC == 'D') {
            DCCSJSTL.FUNC = 'D';
        }
        S000_CALL_DCZSJSTL();
    }
    public void S000_CALL_DCZSJSTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-JST-LINK-BROW", DCCSJSTL);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
