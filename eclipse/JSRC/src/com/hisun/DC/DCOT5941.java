package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5941 {
    String K_PROD_MDEL = "IRAC";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 1;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSIMAC DCCSIMAC = new DCCSIMAC();
    SCCGWA SCCGWA;
    DCB5941_AWA_5941 DCB5941_AWA_5941;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5941 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5941_AWA_5941>");
        DCB5941_AWA_5941 = (DCB5941_AWA_5941) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, DCCSIMAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.FUNC);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.PROD_CD);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.CNAME);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.STR_DATE);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.EXP_DATE);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.AC_NUM);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.AS_NUM);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.CI_TYP);
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, DCB5941_AWA_5941.DD_INFO[WS_I-1].DD_PROD);
        }
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, DCB5941_AWA_5941.LN_INFO[WS_I-1].LN_PROD);
        }
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.CCY);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.CCY_TYP);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.DAYS);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.PERD);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.REPY_MTH);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.PERM_KND);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.MMDD);
        if (DCB5941_AWA_5941.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.PROD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.CNAME.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_DSC_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.CNAME_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.STR_DATE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_DT_INVALID;
            WS_FLD_NO = DCB5941_AWA_5941.STR_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.EXP_DATE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EXP_DT_INVALID;
            WS_FLD_NO = DCB5941_AWA_5941.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.AS_NUM == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AS_NUM_INVALID;
            WS_FLD_NO = DCB5941_AWA_5941.AS_NUM_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.CI_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_TYP_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.CI_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.DD_INFO[1-1].DD_PROD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.DD_INFO[1-1].DD_PROD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.LN_INFO[1-1].LN_PROD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.LN_INFO[1-1].LN_PROD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.DAYS == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TERM_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.DAYS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.PERD == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PERD_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.PERD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.PERD_UN == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PERD_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.PERD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.REPY_MTH == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_MTH_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.REPY_MTH_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5941_AWA_5941.PERM_KND == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_KND_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.PERM_KND_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            if (DCB5941_AWA_5941.PERM_KND == 'Y') {
                if (DCB5941_AWA_5941.MMDD > 1231 
                    || DCB5941_AWA_5941.MMDD < 101) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_DT_INVALID;
                    WS_FLD_NO = DCB5941_AWA_5941.MMDD_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            } else if (DCB5941_AWA_5941.PERM_KND == 'M') {
                if (DCB5941_AWA_5941.MMDD < 1 
                    || DCB5941_AWA_5941.MMDD > 31) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_DT_INVALID;
                    WS_FLD_NO = DCB5941_AWA_5941.MMDD_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            } else if (DCB5941_AWA_5941.PERM_KND == 'D') {
                if (DCB5941_AWA_5941.MMDD != 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_DT_M_BLANK;
                    WS_FLD_NO = DCB5941_AWA_5941.MMDD_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_KND_INVALID;
                WS_FLD_NO = DCB5941_AWA_5941.PERM_KND_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_ADD_PRD_INFO() throws IOException,SQLException,Exception {
        DCCSIMAC.KEY.CON_MDEL = K_PROD_MDEL;
        DCCSIMAC.KEY.PROD_CODE = DCB5941_AWA_5941.PROD_CD;
        DCCSIMAC.CNAME = DCB5941_AWA_5941.CNAME;
        DCCSIMAC.STR_DATE = DCB5941_AWA_5941.STR_DATE;
        DCCSIMAC.EXP_DATE = DCB5941_AWA_5941.EXP_DATE;
        DCCSIMAC.PROD_INFO.AC_NUM = DCB5941_AWA_5941.AC_NUM;
        DCCSIMAC.PROD_INFO.AS_NUM = DCB5941_AWA_5941.AS_NUM;
        DCCSIMAC.PROD_INFO.CI_TYP = DCB5941_AWA_5941.CI_TYP;
        DCCSIMAC.PROD_INFO.CCY = DCB5941_AWA_5941.CCY;
        DCCSIMAC.PROD_INFO.CCY_TYPE = DCB5941_AWA_5941.CCY_TYP;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            DCCSIMAC.PROD_INFO.PROD_DD_INFO[WS_I-1].DD_PROD = DCB5941_AWA_5941.DD_INFO[WS_I-1].DD_PROD;
            DCCSIMAC.PROD_INFO.PROD_DD_INFO[WS_I-1].PROD_DNM = DCB5941_AWA_5941.DD_INFO[WS_I-1].PROD_DNM;
            DCCSIMAC.PROD_INFO.PROD_TD_INFO[WS_I-1].LN_PROD = DCB5941_AWA_5941.LN_INFO[WS_I-1].LN_PROD;
            DCCSIMAC.PROD_INFO.PROD_TD_INFO[WS_I-1].PROD_LNM = DCB5941_AWA_5941.LN_INFO[WS_I-1].PROD_LNM;
        }
        DCCSIMAC.PROD_INFO.DAYS = DCB5941_AWA_5941.DAYS;
        DCCSIMAC.PROD_INFO.PERD = DCB5941_AWA_5941.PERD;
        DCCSIMAC.PROD_INFO.PERD_UNIT = DCB5941_AWA_5941.PERD_UN;
        DCCSIMAC.PROD_INFO.REPY_MTH = DCB5941_AWA_5941.REPY_MTH;
        DCCSIMAC.PROD_INFO.PERM_KND = DCB5941_AWA_5941.PERM_KND;
        DCCSIMAC.PROD_INFO.MMDD = DCB5941_AWA_5941.MMDD;
        CEP.TRC(SCCGWA, DCCSIMAC);
        DCCSIMAC.FUNC = 'A';
        S000_CALL_DCZSIMAC();
    }
    public void S000_CALL_DCZSIMAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-SIM-AC", DCCSIMAC);
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
