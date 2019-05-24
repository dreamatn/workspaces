package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8030 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    CICCUST CICCUST = new CICCUST();
    LNCSTCET LNCSTCET = new LNCSTCET();
    SCCGWA SCCGWA;
    LNB8030_AWA_8030 LNB8030_AWA_8030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8030 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8030_AWA_8030>");
        LNB8030_AWA_8030 = (LNB8030_AWA_8030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TCTRANS_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB8030_AWA_8030.CI_NO);
        CEP.TRC(SCCGWA, LNB8030_AWA_8030.LOAN_AC);
        CEP.TRC(SCCGWA, LNB8030_AWA_8030.START_DT);
        CEP.TRC(SCCGWA, LNB8030_AWA_8030.MATU_DT);
        CEP.TRC(SCCGWA, LNB8030_AWA_8030.TR_TYPE);
        CEP.TRC(SCCGWA, LNB8030_AWA_8030.TR_DATE);
        CEP.TRC(SCCGWA, LNB8030_AWA_8030.JRN_NO);
        if (LNB8030_AWA_8030.CI_NO.trim().length() == 0 
            && LNB8030_AWA_8030.LOAN_AC.trim().length() == 0 
            && (LNB8030_AWA_8030.TR_DATE == 0 
            || LNB8030_AWA_8030.JRN_NO == 0)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, LNB8030_AWA_8030.LOAN_AC);
        if (LNB8030_AWA_8030.LOAN_AC.trim().length() > 0) {
            IBS.init(SCCGWA, LNCRCONT);
            IBS.init(SCCGWA, LNRCONT);
            LNCRCONT.FUNC = 'I';
            LNRCONT.KEY.CONTRACT_NO = LNB8030_AWA_8030.LOAN_AC;
            S000_CALL_LNZRCONT();
        }
        if (LNB8030_AWA_8030.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = LNB8030_AWA_8030.CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
        }
        if (LNB8030_AWA_8030.CI_NO.trim().length() > 0 
            && LNB8030_AWA_8030.LOAN_AC.trim().length() > 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOT_NEED_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TCTRANS_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSTCET);
        LNCSTCET.CI_NO = LNB8030_AWA_8030.CI_NO;
        LNCSTCET.CTA_NO = LNB8030_AWA_8030.LOAN_AC;
        LNCSTCET.STA_DT = LNB8030_AWA_8030.START_DT;
        LNCSTCET.DUE_DT = LNB8030_AWA_8030.MATU_DT;
        LNCSTCET.TRAN_TYP = LNB8030_AWA_8030.TR_TYPE;
        LNCSTCET.TRAN_DTE = LNB8030_AWA_8030.TR_DATE;
        LNCSTCET.TRAN_JRNNO = LNB8030_AWA_8030.JRN_NO;
        if (LNB8030_AWA_8030.PAGE_ROW == 0) {
            LNCSTCET.PAGE_ROW = 20;
        } else {
            if (LNB8030_AWA_8030.PAGE_ROW > 20) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
            } else {
                LNCSTCET.PAGE_ROW = LNB8030_AWA_8030.PAGE_ROW;
            }
        }
        if (LNB8030_AWA_8030.PAGE_NUM == 0) {
            LNCSTCET.PAGE_NUM = 1;
        } else {
            LNCSTCET.PAGE_NUM = LNB8030_AWA_8030.PAGE_NUM;
        }
        CEP.TRC(SCCGWA, LNCSTCET.TRAN_TYP);
        CEP.TRC(SCCGWA, LNCSTCET.PAGE_ROW);
        CEP.TRC(SCCGWA, LNCSTCET.PAGE_NUM);
        S000_CALL_LNZSTCET();
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
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
    public void S000_CALL_LNZSTCET() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-TRCHCMMT-ENTY", LNCSTCET);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
