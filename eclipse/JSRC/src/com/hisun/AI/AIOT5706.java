package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5706 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSCMIB AICSCMIB = new AICSCMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQITM AICPQITM = new AICPQITM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5700_AWA_5700 AIB5700_AWA_5700;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5706 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5700_AWA_5700>");
        AIB5700_AWA_5700 = (AIB5700_AWA_5700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_REA_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5700_AWA_5700.ITM);
        if (AIB5700_AWA_5700.ITM.trim().length() > 0) {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.NO = AIB5700_AWA_5700.ITM;
            AICPQITM.INPUT_DATA.BOOK_FLG = AIB5700_AWA_5700.GL_BOOK;
            S00_CALL_AIZPQITM();
        }
        CEP.TRC(SCCGWA, AIB5700_AWA_5700.BR);
        if (AIB5700_AWA_5700.BR != 0 
            && AIB5700_AWA_5700.BR != 999999) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB5700_AWA_5700.BR;
            S00_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            if (BPCPQORG.ATTR != '2' 
                && BPCPQORG.ATTR != '0') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_NOT_BOOK_BR);
            }
        }
    }
    public void B020_REA_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSCMIB);
        AICSCMIB.FUNC = 'R';
        AICSCMIB.GL_BOOK = AIB5700_AWA_5700.GL_BOOK;
        AICSCMIB.ITM = AIB5700_AWA_5700.ITM;
        AICSCMIB.BR = AIB5700_AWA_5700.BR;
        AICSCMIB.SEQ = AIB5700_AWA_5700.SEQ;
        AICSCMIB.EXP_DATE = AIB5700_AWA_5700.EXP_DT;
        AICSCMIB.AC_EXP = AIB5700_AWA_5700.AC_EXPDT;
        CEP.TRC(SCCGWA, AICSCMIB.ITM);
        CEP.TRC(SCCGWA, AICSCMIB.BR);
        S00_CALL_AIZSCMIB();
    }
    public void S00_CALL_AIZSCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-CMIB", AICSCMIB);
    }
    public void S00_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
        }
        if (AICPQITM.OUTPUT_DATA.STS == 'C' 
            || AICPQITM.OUTPUT_DATA.STS == 'S') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.ITM_STS_NOT_ALLOW_CMIB;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.MIB_FLG);
        if (AICPQITM.OUTPUT_DATA.MIB_FLG == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.ITM_MIB_FLG_NOT_CMIB;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, AIB5700_AWA_5700.AC_EXPDT);
        CEP.TRC(SCCGWA, AIB5700_AWA_5700.EFF_DT);
        CEP.TRC(SCCGWA, AIB5700_AWA_5700.EXP_DT);
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.EXP_DATE);
        if (AIB5700_AWA_5700.EXP_DT > AICPQITM.OUTPUT_DATA.EXP_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.GL_EXP_MUST_GT_CMIB;
            S000_ERR_MSG_PROC();
        }
        if (AIB5700_AWA_5700.EFF_DT >= AIB5700_AWA_5700.EXP_DT) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.EFF_DT_MUST_GT_EXP_DT;
            S000_ERR_MSG_PROC();
        }
        if (AIB5700_AWA_5700.AC_EXPDT > AICPQITM.OUTPUT_DATA.EXP_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.GL_EXP_MUST_GT_EXPDT;
            S000_ERR_MSG_PROC();
        }
        if (AIB5700_AWA_5700.EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXP_MUST_GT_ACDT;
            S000_ERR_MSG_PROC();
        }
        if (AIB5700_AWA_5700.AC_EXPDT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AC_EXP_MUST_GT_AC_DT;
            S000_ERR_MSG_PROC();
        }
        if (AIB5700_AWA_5700.AC_EXPDT < AIB5700_AWA_5700.EXP_DT) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AC_EXP_MUST_GT_EXP_DT;
            S000_ERR_MSG_PROC();
        }
        if (AIB5700_AWA_5700.AC_EXPDT < AIB5700_AWA_5700.EFF_DT) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXPDT_LESS_EFFDT;
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
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
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
