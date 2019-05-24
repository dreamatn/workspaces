package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5702 {
    DBParm CITACR_RD;
    boolean pgmRtn = false;
    String WS_ENTY_NO = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    char WS_ACR_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACR CIRACR = new CIRACR();
    DCCUABRW DCCUABRW = new DCCUABRW();
    DCCILNKR DCCILNKR = new DCCILNKR();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICSSMS CICSSMS = new CICSSMS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5702_AWA_5702 CIB5702_AWA_5702;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT5702 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5702_AWA_5702>");
        CIB5702_AWA_5702 = (CIB5702_AWA_5702) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICSSMS);
        CICSSMS.FUNC = CIB5702_AWA_5702.FUNC;
        CICSSMS.DATA.CI_NO = CIB5702_AWA_5702.CI_NO;
        CICSSMS.DATA.AGT_NO = CIB5702_AWA_5702.AGT_NO;
        CICSSMS.DATA.AGT_TYP = CIB5702_AWA_5702.AGT_TYP;
        CICSSMS.DATA.ENTY_TYP = CIB5702_AWA_5702.ENTY_TYP;
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.ENTY_NO);
        CICSSMS.DATA.ENTY_NO = WS_ENTY_NO;
        CICSSMS.DATA.FRM_APP = CIB5702_AWA_5702.FRM_APP;
        CICSSMS.DATA.AGT_LVL = CIB5702_AWA_5702.AGT_LVL;
        CICSSMS.DATA.EFF_DATE = CIB5702_AWA_5702.EFF_DATE;
        CICSSMS.DATA.EXP_DATE = CIB5702_AWA_5702.EXP_DATE;
        CICSSMS.DATA.SGN_DATE = CIB5702_AWA_5702.SGN_DATE;
        CICSSMS.DATA.AGT_STS = CIB5702_AWA_5702.AGT_STS;
        CICSSMS.DATA.ORG_NO = CIB5702_AWA_5702.ORG_NO;
        CICSSMS.DATA.SGN_CHNL = CIB5702_AWA_5702.SGN_CHNL;
        CICSSMS.DATA.SVR_CD = CIB5702_AWA_5702.SVR_CD;
        CICSSMS.DATA.CCY = CIB5702_AWA_5702.CCY;
        CICSSMS.DATA.CAMT = CIB5702_AWA_5702.CAMT;
        CICSSMS.DATA.DAMT = CIB5702_AWA_5702.DAMT;
        CICSSMS.DATA.PSN_DAT = CIB5702_AWA_5702.PSN_DAT;
        CICSSMS.DATA.REMARK = CIB5702_AWA_5702.REMARK;
        CICSSMS.DATA.TEL_NO = CIB5702_AWA_5702.TEL_NO;
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.FUNC);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.TEL_NO);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.CI_NO);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.AGT_NO);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.AGT_TYP);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.ENTY_TYP);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.ENTY_NO);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.FRM_APP);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.AGT_LVL);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.EFF_DATE);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.EXP_DATE);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.SGN_DATE);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.AGT_STS);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.ORG_NO);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.SGN_CHNL);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.REMARK);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.SVR_CD);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.CCY);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.CAMT);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.DAMT);
        CEP.TRC(SCCGWA, CIB5702_AWA_5702.PSN_DAT);
        S000_LINK_CIZSSMS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5702_AWA_5702.SVR_CD.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5702_AWA_5702.SVR_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (CIB5702_AWA_5702.AGT_TYP.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5702_AWA_5702.AGT_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (CIB5702_AWA_5702.ENTY_NO.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5702_AWA_5702.ENTY_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (CIB5702_AWA_5702.AGT_LVL == ' ') {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5702_AWA_5702.AGT_LVL_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (CIB5702_AWA_5702.AGT_STS == ' ') {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5702_AWA_5702.AGT_STS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (CIB5702_AWA_5702.ENTY_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIB5702_AWA_5702.ENTY_NO;
            T000_READ_CITACR_AGR_NO();
            if (pgmRtn) return;
            if (WS_ACR_FLG == 'Y') {
                WS_ENTY_NO = CIRACR.KEY.AGR_NO;
            } else {
                IBS.init(SCCGWA, DCCPACTY);
                DCCPACTY.INPUT.AC = CIB5702_AWA_5702.ENTY_NO;
                S000_CALL_DCZPACTY();
                if (pgmRtn) return;
                if (DCCPACTY.OUTPUT.STD_AC.trim().length() > 0) {
                    IBS.init(SCCGWA, CIRACR);
                    CIRACR.KEY.AGR_NO = DCCPACTY.OUTPUT.STD_AC;
                    T000_READ_CITACR_AGR_NO();
                    if (pgmRtn) return;
                    if (WS_ACR_FLG == 'Y') {
                        WS_ENTY_NO = CIRACR.KEY.AGR_NO;
                    } else {
                        WS_ERR_MSG = CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND;
                        WS_FLD_NO = CIB5702_AWA_5702.ENTY_NO_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                        if (pgmRtn) return;
                    }
                } else {
                    WS_ERR_MSG = CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND;
                    WS_FLD_NO = CIB5702_AWA_5702.ENTY_NO_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            }
        }
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void T000_READ_CITACR_AGR_NO() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.where = "AGR_NO = :CIRACR.KEY.AGR_NO";
        IBS.READ(SCCGWA, CIRACR, this, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACR_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCPACTY.RC);
        }
    }
    public void S000_CALL_DCZUABRW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-AC-BROWSER", DCCUABRW);
        if (DCCUABRW.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUABRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZILNKR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-ACLNK", DCCILNKR);
    }
    public void S000_LINK_CIZSSMS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-SMS", CICSSMS);
        if (CICSSMS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSSMS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
