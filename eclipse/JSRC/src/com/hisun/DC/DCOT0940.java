package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0940 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "DC940";
    String K_HIS_CPB_NM = "DCZSBIND";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSBIND DCCSBIND = new DCCSBIND();
    DCCF940 DCCF940 = new DCCF940();
    SCCGWA SCCGWA;
    DCB0940_AWA_0940 DCB0940_AWA_0940;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT0940 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0940_AWA_0940>");
        DCB0940_AWA_0940 = (DCB0940_AWA_0940) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SET_SUB();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB0940_AWA_0940.FUNC);
        CEP.TRC(SCCGWA, DCB0940_AWA_0940.CARD_NO);
        CEP.TRC(SCCGWA, DCB0940_AWA_0940.CI_NAME);
        CEP.TRC(SCCGWA, DCB0940_AWA_0940.PSWD);
        if (DCB0940_AWA_0940.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            WS_FLD_NO = DCB0940_AWA_0940.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0940_AWA_0940.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            WS_FLD_NO = DCB0940_AWA_0940.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0940_AWA_0940.CI_NAME.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CI_NAME;
            WS_FLD_NO = DCB0940_AWA_0940.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_SET_SUB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSBIND);
        DCCSBIND.FUNC = DCB0940_AWA_0940.FUNC;
        DCCSBIND.CARD_NO = DCB0940_AWA_0940.CARD_NO;
        DCCSBIND.CI_NAME = DCB0940_AWA_0940.CI_NAME;
        DCCSBIND.PSWD = DCB0940_AWA_0940.PSWD;
        if (DCB0940_AWA_0940.FUNC == '1') {
            DCCSBIND.FUNC = '1';
        } else if (DCB0940_AWA_0940.FUNC == '2') {
            DCCSBIND.FUNC = '2';
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OPR_INVALID;
            WS_FLD_NO = DCB0940_AWA_0940.FUNC_NO;
        }
        S000_CALL_DCZSBIND();
    }
    public void S000_CALL_DCZSBIND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-BINDING", DCCSBIND);
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
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
