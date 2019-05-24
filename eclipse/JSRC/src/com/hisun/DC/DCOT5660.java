package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5660 {
    String K_OUT_FMT = "DC660";
    String K_PROD_MDEL = "IRDW";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSMCED DCCSMCED = new DCCSMCED();
    DCCOMCED DCCOMCED = new DCCOMCED();
    DCRICPRD DCRICPRD = new DCRICPRD();
    SCCGWA SCCGWA;
    DCB5660_AWA_5660 DCB5660_AWA_5660;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5660 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5660_AWA_5660>");
        DCB5660_AWA_5660 = (DCB5660_AWA_5660) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, DCCSMCED);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCB5660_AWA_5660.FUNC == 'I') {
            B010_CHECK_INPUT();
            B020_TRANS_INFO();
            B030_TRANS_DATA_OUTPUT();
        } else if (DCB5660_AWA_5660.FUNC == 'A') {
            B010_CHECK_INPUT();
            B020_TRANS_INFO();
        } else if (DCB5660_AWA_5660.FUNC == 'M') {
            B010_CHECK_INPUT();
            B020_TRANS_INFO();
        } else if (DCB5660_AWA_5660.FUNC == 'D') {
            B010_CHECK_INPUT();
            B020_TRANS_INFO();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DCB5660_AWA_5660.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCB5660_AWA_5660.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            WS_FLD_NO = DCB5660_AWA_5660.PROD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5660_AWA_5660.FUNC == 'A' 
            || DCB5660_AWA_5660.FUNC == 'M') {
            if (DCB5660_AWA_5660.EFFDAT == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_DT_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.EFFDAT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.EXPDAT == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EXP_DT_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.EXPDAT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.CI_TYP == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_TYP_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.CI_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.CCY.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.CCY_TYPE == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYPE_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.CCY_TYPE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.PERM_KND == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_KND_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.PERM_KND_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.PERM_MTH == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_MTH_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.PERM_MTH_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.MRM_AMT == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MRM_AMT_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.MRM_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.INT_MTH == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INT_MTH_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.INT_MTH_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.PROD_DD.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.PROD_DD_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.DESC_DD.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_DESC_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.DESC_DD_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.PROD_TD.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.PROD_TD_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.DESC_TD.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_DESC_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.DESC_TD_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5660_AWA_5660.DRAW_ORD == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DRAW_ORD_M_INPUT;
                WS_FLD_NO = DCB5660_AWA_5660.DRAW_ORD_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_TRANS_INFO() throws IOException,SQLException,Exception {
        DCCSMCED.KEY.CON_MDEL = K_PROD_MDEL;
        DCCSMCED.KEY.PROD_CD = DCB5660_AWA_5660.PROD_CD;
        DCCSMCED.PROD_DSC = DCB5660_AWA_5660.PROD_DSC;
        DCCSMCED.EFFDAT = DCB5660_AWA_5660.EFFDAT;
        DCCSMCED.EXPDAT = DCB5660_AWA_5660.EXPDAT;
        DCCSMCED.CI_TYP = DCB5660_AWA_5660.CI_TYP;
        DCCSMCED.CCY = DCB5660_AWA_5660.CCY;
        DCCSMCED.CCY_TYPE = DCB5660_AWA_5660.CCY_TYPE;
        DCCSMCED.PERM_KND = DCB5660_AWA_5660.PERM_KND;
        DCCSMCED.PERM_MD = DCB5660_AWA_5660.PERM_MD;
        DCCSMCED.PERM_MTH = DCB5660_AWA_5660.PERM_MTH;
        DCCSMCED.MRM_AMT = DCB5660_AWA_5660.MRM_AMT;
        DCCSMCED.INT_MTH = DCB5660_AWA_5660.INT_MTH;
        DCCSMCED.PROD_DD = DCB5660_AWA_5660.PROD_DD;
        DCCSMCED.DESC_DD = DCB5660_AWA_5660.DESC_DD;
        DCCSMCED.PROD_TD = DCB5660_AWA_5660.PROD_TD;
        DCCSMCED.DESC_TD = DCB5660_AWA_5660.DESC_TD;
        DCCSMCED.DRAW_ORD = DCB5660_AWA_5660.DRAW_ORD;
        if (DCB5660_AWA_5660.FUNC == 'I') {
            DCCSMCED.FUNC = 'I';
        } else if (DCB5660_AWA_5660.FUNC == 'A') {
            DCCSMCED.FUNC = 'A';
        } else if (DCB5660_AWA_5660.FUNC == 'M') {
            DCCSMCED.FUNC = 'M';
        } else if (DCB5660_AWA_5660.FUNC == 'D') {
            DCCSMCED.FUNC = 'D';
        }
        S000_CALL_DCZSMCED();
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOMCED);
        DCCOMCED.PROD_CD = DCCSMCED.KEY.PROD_CD;
        DCCOMCED.PROD_DSC = DCCSMCED.PROD_DSC;
        DCCOMCED.EFFDAT = DCCSMCED.EFFDAT;
        DCCOMCED.EXPDAT = DCCSMCED.EXPDAT;
        DCCOMCED.CI_TYP = DCCSMCED.CI_TYP;
        DCCOMCED.CCY = DCCSMCED.CCY;
        DCCOMCED.CCY_TYPE = DCCSMCED.CCY_TYPE;
        DCCOMCED.PERM_KND = DCCSMCED.PERM_KND;
        DCCOMCED.PERM_MD = DCCSMCED.PERM_MD;
        DCCOMCED.PERM_MTH = DCCSMCED.PERM_MTH;
        DCCOMCED.MRM_AMT = DCCSMCED.MRM_AMT;
        DCCOMCED.INT_MTH = DCCSMCED.INT_MTH;
        DCCOMCED.PROD_DD = DCCSMCED.PROD_DD;
        DCCOMCED.DESC_DD = DCCSMCED.DESC_DD;
        DCCOMCED.PROD_TD = DCCSMCED.PROD_TD;
        DCCOMCED.DESC_TD = DCCSMCED.DESC_TD;
        DCCOMCED.DRAW_ORD = DCCSMCED.DRAW_ORD;
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "FORMAT OUTPUT");
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = DCCOMCED;
        SCCFMT.DATA_LEN = 258;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZSMCED() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-COMPANY-DD", DCCSMCED);
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
