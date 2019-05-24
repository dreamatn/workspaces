package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0204 {
    String K_PROD_MDEL = "IRLN";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 1;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSIRLN DCCSIRLN = new DCCSIRLN();
    SCCGWA SCCGWA;
    DCB0204_AWA_0204 DCB0204_AWA_0204;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT0204 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0204_AWA_0204>");
        DCB0204_AWA_0204 = (DCB0204_AWA_0204) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SET_SUB();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.FUNC);
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.PROD_CD);
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.PROD_NM);
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.CI_TYP);
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, DCB0204_AWA_0204.CCY_INF[WS_I-1].CCY);
            CEP.TRC(SCCGWA, DCB0204_AWA_0204.CCY_INF[WS_I-1].CCY_TYP);
        }
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.LN_MTH);
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.LN_PER);
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.DD_PER);
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.TERM);
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.STRAMT);
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.STRDT);
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.EXPDT);
        if (DCB0204_AWA_0204.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            WS_FLD_NO = DCB0204_AWA_0204.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0204_AWA_0204.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            WS_FLD_NO = DCB0204_AWA_0204.PROD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0204_AWA_0204.PROD_NM.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_DSC_M_INPUT;
            WS_FLD_NO = DCB0204_AWA_0204.PROD_NM_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0204_AWA_0204.CI_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_TYP_M_INPUT;
            WS_FLD_NO = DCB0204_AWA_0204.CI_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0204_AWA_0204.CI_TYP != '1' 
            && DCB0204_AWA_0204.CI_TYP != '2' 
            && DCB0204_AWA_0204.CI_TYP != '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_TYP_NOT_SPT;
            WS_FLD_NO = DCB0204_AWA_0204.CI_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0204_AWA_0204.CCY_INF[1-1].CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
            WS_FLD_NO = DCB0204_AWA_0204.CCY_INF[1-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0204_AWA_0204.CCY_INF[1-1].CCY_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYPE_M_INPUT;
            WS_FLD_NO = DCB0204_AWA_0204.CCY_INF[1-1].CCY_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, DCB0204_AWA_0204.LN_MTH);
        if (DCB0204_AWA_0204.LN_MTH == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_MTH_M_INPUT;
            WS_FLD_NO = DCB0204_AWA_0204.LN_MTH_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0204_AWA_0204.LN_MTH != '0' 
            && DCB0204_AWA_0204.LN_MTH != '1' 
            && DCB0204_AWA_0204.LN_MTH != '2' 
            && DCB0204_AWA_0204.LN_MTH != '3' 
            && DCB0204_AWA_0204.LN_MTH != '4') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_MTH_NOT_SPT;
            WS_FLD_NO = DCB0204_AWA_0204.LN_MTH_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0204_AWA_0204.LN_PER == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_PER_M_INPUT;
            WS_FLD_NO = DCB0204_AWA_0204.LN_PER_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB0204_AWA_0204.DD_PER == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_PER_M_INPUT;
            WS_FLD_NO = DCB0204_AWA_0204.DD_PER_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_SET_SUB() throws IOException,SQLException,Exception {
        DCCSIRLN.DATA.KEY.PRDMO_CD = K_PROD_MDEL;
        DCCSIRLN.FUNC = DCB0204_AWA_0204.FUNC;
        DCCSIRLN.DATA.KEY.PROD_CODE = DCB0204_AWA_0204.PROD_CD;
        DCCSIRLN.DATA.PROD_NM = DCB0204_AWA_0204.PROD_NM;
        DCCSIRLN.DATA.CI_TYP = DCB0204_AWA_0204.CI_TYP;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            DCCSIRLN.DATA.CCY_INF[WS_I-1].CCY = DCB0204_AWA_0204.CCY_INF[WS_I-1].CCY;
            DCCSIRLN.DATA.CCY_INF[WS_I-1].CCY_TYP = DCB0204_AWA_0204.CCY_INF[WS_I-1].CCY_TYP;
        }
        DCCSIRLN.DATA.LN_MTH = DCB0204_AWA_0204.LN_MTH;
        DCCSIRLN.DATA.LN_PER = DCB0204_AWA_0204.LN_PER;
        DCCSIRLN.DATA.DD_PER = DCB0204_AWA_0204.DD_PER;
        DCCSIRLN.DATA.TERM = DCB0204_AWA_0204.TERM;
        DCCSIRLN.DATA.STRAMT = DCB0204_AWA_0204.STRAMT;
        DCCSIRLN.DATA.STRDT = DCB0204_AWA_0204.STRDT;
        DCCSIRLN.DATA.EXPDT = DCB0204_AWA_0204.EXPDT;
        CEP.TRC(SCCGWA, DCCSIRLN);
        if (DCB0204_AWA_0204.FUNC == 'Q') {
            DCCSIRLN.FUNC = 'Q';
        } else if (DCB0204_AWA_0204.FUNC == 'A') {
            DCCSIRLN.FUNC = 'A';
        } else if (DCB0204_AWA_0204.FUNC == 'M') {
            DCCSIRLN.FUNC = 'M';
        } else if (DCB0204_AWA_0204.FUNC == 'D') {
            DCCSIRLN.FUNC = 'D';
        } else {
        }
        S000_CALL_DCZSIRLN();
    }
    public void S000_CALL_DCZSIRLN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-SIR-LN", DCCSIRLN);
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
