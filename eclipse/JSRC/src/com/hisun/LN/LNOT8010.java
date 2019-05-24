package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8010 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSCNTA LNCSCNTA = new LNCSCNTA();
    LNCCONTM LNCCONTM = new LNCCONTM();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    LNB8010_AWA_8010 LNB8010_AWA_8010;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8010_AWA_8010>");
        LNB8010_AWA_8010 = (LNB8010_AWA_8010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCSCNTA.RC.RC_MMO = "LN";
        LNCSCNTA.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_FUNC_MAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.CI_NO);
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.CI_CNAME);
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.ID_TYP);
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.CTA_TYP);
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.ID_NO);
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.COMM_NO);
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.CONT_NO);
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.STS);
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.PAGE_ROW);
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.PAGE_NUM);
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.CI_NO);
        CEP.TRC(SCCGWA, LNB8010_AWA_8010.CONT_NO);
        if (LNB8010_AWA_8010.CI_NO.trim().length() == 0 
            && LNB8010_AWA_8010.CONT_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CINO_ERR;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB8010_AWA_8010.CONT_NO.trim().length() > 0) {
            IBS.init(SCCGWA, LNCCONTM);
            LNCCONTM.FUNC = '3';
            LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNB8010_AWA_8010.CONT_NO;
            S000_CALL_LNZCONTM();
        }
        if (LNB8010_AWA_8010.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = LNB8010_AWA_8010.CI_NO;
            S000_CALL_CIZCUST();
        }
        if (LNB8010_AWA_8010.CONT_NO.trim().length() > 0) {
            if (LNB8010_AWA_8010.CI_NO.trim().length() > 0 
                || LNB8010_AWA_8010.ID_TYP.trim().length() > 0 
                || LNB8010_AWA_8010.ID_NO.trim().length() > 0 
                || LNB8010_AWA_8010.COMM_NO.trim().length() > 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOT_NEED_INPUT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_FUNC_MAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCNTA);
        LNCSCNTA.DATA.CI_NO = LNB8010_AWA_8010.CI_NO;
        LNCSCNTA.DATA.ID_TYP = LNB8010_AWA_8010.ID_TYP;
        LNCSCNTA.DATA.STS = LNB8010_AWA_8010.STS;
        LNCSCNTA.DATA.ID_NO = LNB8010_AWA_8010.ID_NO;
        LNCSCNTA.DATA.COMM_NO = LNB8010_AWA_8010.COMM_NO;
        LNCSCNTA.DATA.CONT_NO = LNB8010_AWA_8010.CONT_NO;
        LNCSCNTA.DATA.CTA_TYP = LNB8010_AWA_8010.CTA_TYP;
        LNCSCNTA.DATA.PAGE_NUM = LNB8010_AWA_8010.PAGE_NUM;
        LNCSCNTA.DATA.PAGE_ROW = LNB8010_AWA_8010.PAGE_ROW;
        LNCSCNTA.FUNC = 'L';
        if (LNB8010_AWA_8010.CI_NO.trim().length() > 0) {
            LNCSCNTA.Q_MTH = 'C';
        } else {
            if (LNB8010_AWA_8010.CONT_NO.trim().length() > 0) {
                LNCSCNTA.Q_MTH = 'L';
            }
        }
        S000_CALL_LNZSCNTA();
    }
    public void S000_CALL_LNZSCNTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CNTA", LNCSCNTA);
        if (LNCSCNTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSCNTA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
