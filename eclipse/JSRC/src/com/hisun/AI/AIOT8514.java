package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT8514 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    AICSSBAL AICSSBAL = new AICSSBAL();
    AICPQITM AICQITM = new AICPQITM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    AIB0004_AWA_0004 AIB0004_AWA_0004;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "AIOT8514 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0004_AWA_0004>");
        AIB0004_AWA_0004 = (AIB0004_AWA_0004) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_DATA();
        B200_TRANS_DATA();
    }
    public void B100_CHECK_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.FLAG);
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.COANO);
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.ACCENTRE);
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.CURCODE);
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.ACCNAME);
        if (!AIB0004_AWA_0004.FLAG.equalsIgnoreCase("BK001")) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            WS_FLD_NO = AIB0004_AWA_0004.FLAG_NO;
            S000_ERR_MSG_PROC();
        }
        if (AIB0004_AWA_0004.COANO.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            WS_FLD_NO = AIB0004_AWA_0004.COANO_NO;
            S000_ERR_MSG_PROC();
        }
        if (AIB0004_AWA_0004.ACCENTRE == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            WS_FLD_NO = AIB0004_AWA_0004.ACCENTRE_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB0004_AWA_0004.ACCENTRE;
            S000_CALL_BPZPQORG();
        }
        IBS.init(SCCGWA, AICQITM);
        AICQITM.INPUT_DATA.BOOK_FLG = AIB0004_AWA_0004.FLAG;
        AICQITM.INPUT_DATA.NO = AIB0004_AWA_0004.COANO;
        S000_CALL_AIZPQITM();
        if (AIB0004_AWA_0004.CURCODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = AIB0004_AWA_0004.CURCODE;
            S000_CALL_BPZQCCY();
        }
    }
    public void B200_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSSBAL);
        AICSSBAL.COMM_DATA.BOOK_FLG = AIB0004_AWA_0004.FLAG;
        AICSSBAL.COMM_DATA.BR_START = AIB0004_AWA_0004.ACCENTRE;
        AICSSBAL.COMM_DATA.BR_END = AIB0004_AWA_0004.ACCENTRE;
        AICSSBAL.COMM_DATA.ITM_NO = AIB0004_AWA_0004.COANO;
        AICSSBAL.COMM_DATA.CCY = AIB0004_AWA_0004.CURCODE;
        AICSSBAL.FUNC = 'I';
        S000_CALL_AIZSSBAL();
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICQITM);
        if (AICQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICQITM.RC);
            if (AIB0004_AWA_0004.COANO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(AIB0004_AWA_0004.COANO);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            WS_FLD_NO = AIB0004_AWA_0004.CURCODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZSSBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-AIZS8530", AICSSBAL);
        CEP.TRC(SCCGWA, AICSSBAL.RC);
        if (AICSSBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSSBAL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
