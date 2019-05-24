package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZT2110 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSCNTA LNCSCNTA = new LNCSCNTA();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    LNCT2110_IPT_2110 LN_AWA_2110;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCT2110_IPT_2110 LN_AWA_2110) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LN_AWA_2110 = LN_AWA_2110;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNZT2110 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
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
        CEP.TRC(SCCGWA, LN_AWA_2110.CI_NO);
        CEP.TRC(SCCGWA, LN_AWA_2110.CI_CNAME);
        CEP.TRC(SCCGWA, LN_AWA_2110.ID_TYP);
        CEP.TRC(SCCGWA, LN_AWA_2110.CTA_TYP);
        CEP.TRC(SCCGWA, LN_AWA_2110.ID_NO);
        CEP.TRC(SCCGWA, LN_AWA_2110.COMM_NO);
        CEP.TRC(SCCGWA, LN_AWA_2110.CONT_NO);
        CEP.TRC(SCCGWA, LN_AWA_2110.STS);
        CEP.TRC(SCCGWA, LN_AWA_2110.PAGE_ROW);
        CEP.TRC(SCCGWA, LN_AWA_2110.PAGE_NUM);
        CEP.TRC(SCCGWA, LN_AWA_2110.CI_NO);
        CEP.TRC(SCCGWA, LN_AWA_2110.CONT_NO);
        if (LN_AWA_2110.CI_NO.trim().length() == 0 
            && LN_AWA_2110.CONT_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LN_AWA_2110.CONT_NO.trim().length() > 0) {
            IBS.init(SCCGWA, LNRCONT);
            IBS.init(SCCGWA, LNCRCONT);
            LNCRCONT.FUNC = 'I';
            LNRCONT.KEY.CONTRACT_NO = LN_AWA_2110.CONT_NO;
            S000_CALL_LNZRCONT();
        }
        if (LN_AWA_2110.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = LN_AWA_2110.CI_NO;
            S000_CALL_CIZCUST();
        }
        if (LN_AWA_2110.CONT_NO.trim().length() > 0) {
            if (LN_AWA_2110.CI_NO.trim().length() > 0 
                || LN_AWA_2110.ID_TYP.trim().length() > 0 
                || LN_AWA_2110.ID_NO.trim().length() > 0 
                || LN_AWA_2110.COMM_NO.trim().length() > 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOT_NEED_INPUT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_FUNC_MAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCNTA);
        LNCSCNTA.DATA.CI_NO = LN_AWA_2110.CI_NO;
        LNCSCNTA.DATA.ID_TYP = LN_AWA_2110.ID_TYP;
        LNCSCNTA.DATA.STS = LN_AWA_2110.STS;
        LNCSCNTA.DATA.ID_NO = LN_AWA_2110.ID_NO;
        LNCSCNTA.DATA.COMM_NO = LN_AWA_2110.COMM_NO;
        LNCSCNTA.DATA.CONT_NO = LN_AWA_2110.CONT_NO;
        LNCSCNTA.DATA.CTA_TYP = LN_AWA_2110.CTA_TYP;
        LNCSCNTA.DATA.PAGE_NUM = LN_AWA_2110.PAGE_NUM;
        if (LN_AWA_2110.PAGE_ROW == 0) {
            LNCSCNTA.DATA.PAGE_ROW = 10;
        } else {
            if (LN_AWA_2110.PAGE_ROW > 10) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
            } else {
                LNCSCNTA.DATA.PAGE_ROW = LN_AWA_2110.PAGE_ROW;
            }
        }
        LNCSCNTA.FUNC = 'L';
        if (LN_AWA_2110.CI_NO.trim().length() > 0) {
            LNCSCNTA.Q_MTH = 'C';
        } else {
            if (LN_AWA_2110.CONT_NO.trim().length() > 0) {
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
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
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
