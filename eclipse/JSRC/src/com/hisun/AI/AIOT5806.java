package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5806 {
    int JIBS_tmp_int;
    String CPN_S_MIB_MAIN = "AI-S-MAIN-MIB  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICPQITM AICPQITM = new AICPQITM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    AICSMIB AICSMIB = new AICSMIB();
    AICRONA AICRONA = new AICRONA();
    AIRONA AIRONA = new AIRONA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5800_AWA_5800 AIB5800_AWA_5800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5806 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5800_AWA_5800>");
        AIB5800_AWA_5800 = (AIB5800_AWA_5800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUE_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.GL_BOOK);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.BR);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.ITM);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.SEQ);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.CCY);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.AC);
        if (AIB5800_AWA_5800.GL_BOOK.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (AIB5800_AWA_5800.AC.trim().length() == 0 
            && (AIB5800_AWA_5800.BR == 0 
            || AIB5800_AWA_5800.ITM.trim().length() == 0 
            || AIB5800_AWA_5800.SEQ == 0 
            || AIB5800_AWA_5800.CCY.trim().length() == 0)) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (AIB5800_AWA_5800.AC.trim().length() > 0) {
            AICRONA.FUNC = 'B';
            AIRONA.KEY.OAC_NO = AIB5800_AWA_5800.AC;
            S000_CALL_AIZRONA();
            if (AIB5800_AWA_5800.AC == null) AIB5800_AWA_5800.AC = "";
            JIBS_tmp_int = AIB5800_AWA_5800.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AIB5800_AWA_5800.AC += " ";
            if (AIB5800_AWA_5800.AC == null) AIB5800_AWA_5800.AC = "";
            JIBS_tmp_int = AIB5800_AWA_5800.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AIB5800_AWA_5800.AC += " ";
            if ((AIB5800_AWA_5800.AC.substring(25 - 1, 25 + 1 - 1).trim().length() == 0 
                || AIB5800_AWA_5800.AC.substring(26 - 1, 26 + 1 - 1).trim().length() > 0) 
                && AICRONA.RETURN_INFO == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.NOT_INVALID_ACCOUNT;
                S000_ERR_MSG_PROC();
            }
            if (AICRONA.RETURN_INFO == 'F') {
                AIB5800_AWA_5800.AC = AIRONA.AC_NO;
                if (AIB5800_AWA_5800.AC == null) AIB5800_AWA_5800.AC = "";
                JIBS_tmp_int = AIB5800_AWA_5800.AC.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) AIB5800_AWA_5800.AC += " ";
                if (AIB5800_AWA_5800.AC.substring(0, 6).trim().length() == 0) AIB5800_AWA_5800.BR = 0;
                else AIB5800_AWA_5800.BR = Integer.parseInt(AIB5800_AWA_5800.AC.substring(0, 6));
                if (AIB5800_AWA_5800.AC == null) AIB5800_AWA_5800.AC = "";
                JIBS_tmp_int = AIB5800_AWA_5800.AC.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) AIB5800_AWA_5800.AC += " ";
                AIB5800_AWA_5800.CCY = AIB5800_AWA_5800.AC.substring(7 - 1, 7 + 3 - 1);
                if (AIB5800_AWA_5800.AC == null) AIB5800_AWA_5800.AC = "";
                JIBS_tmp_int = AIB5800_AWA_5800.AC.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) AIB5800_AWA_5800.AC += " ";
                AIB5800_AWA_5800.ITM = AIB5800_AWA_5800.AC.substring(10 - 1, 10 + 10 - 1);
                if (AIB5800_AWA_5800.AC == null) AIB5800_AWA_5800.AC = "";
                JIBS_tmp_int = AIB5800_AWA_5800.AC.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) AIB5800_AWA_5800.AC += " ";
                if (AIB5800_AWA_5800.AC.substring(20 - 1, 20 + 6 - 1).trim().length() == 0) AIB5800_AWA_5800.SEQ = 0;
                else AIB5800_AWA_5800.SEQ = Integer.parseInt(AIB5800_AWA_5800.AC.substring(20 - 1, 20 + 6 - 1));
            }
        }
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.BOOK_FLG = AIB5800_AWA_5800.GL_BOOK;
        AICPQITM.INPUT_DATA.NO = AIB5800_AWA_5800.ITM;
        S000_CALL_AIZPQITM();
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = AIB5800_AWA_5800.CCY;
        S000_CALL_BPZQCCY();
    }
    public void B020_QUE_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSMIB);
        AICSMIB.FUNC = 'Q';
        AICSMIB.GL_BOOK = AIB5800_AWA_5800.GL_BOOK;
        AICSMIB.BR = AIB5800_AWA_5800.BR;
        AICSMIB.ITM_NO = AIB5800_AWA_5800.ITM;
        AICSMIB.SEQ = AIB5800_AWA_5800.SEQ;
        AICSMIB.CCY = AIB5800_AWA_5800.CCY;
        CEP.TRC(SCCGWA, AICSMIB.BR);
        CEP.TRC(SCCGWA, AICSMIB.ITM_NO);
        CEP.TRC(SCCGWA, AICSMIB.SEQ);
        S000_CALL_AIZSMIB();
    }
    public void S000_CALL_AIZSMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MIB_MAIN, AICSMIB);
        if (AICSMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSMIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZRONA() throws IOException,SQLException,Exception {
        AICRONA.POINTER = AIRONA;
        AICRONA.REC_LEN = 82;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-ONA", AICRONA);
        CEP.TRC(SCCGWA, AICRONA.RC);
        CEP.TRC(SCCGWA, AICRONA.RETURN_INFO);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
