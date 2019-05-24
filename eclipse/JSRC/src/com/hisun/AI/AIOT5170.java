package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5170 {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    char WS_FUNC_FLG = ' ';
    SCCCKDT SCCCKDT = new SCCCKDT();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    AICSCITM AICSCITM = new AICSCITM();
    AICPQITM AICQITM = new AICPQITM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    AIB5176_AWA_5176 AIB5176_AWA_5176;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT5170 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5176_AWA_5176>");
        AIB5176_AWA_5176 = (AIB5176_AWA_5176) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_DATA();
        if (pgmRtn) return;
        B200_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_DATA() throws IOException,SQLException,Exception {
        if (AIB5176_AWA_5176.ADJ_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = AIB5176_AWA_5176.ADJ_DATE;
            S000_LINK_SCSSCKDT();
            if (pgmRtn) return;
        }
        if (AIB5176_AWA_5176.ITM_OLD.trim().length() > 0) {
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.COA_FLG = AIB5176_AWA_5176.COA_TYP;
            AICQITM.INPUT_DATA.NO = AIB5176_AWA_5176.ITM_OLD;
            if (AIB5176_AWA_5176.ITM_OLD.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(AIB5176_AWA_5176.ITM_OLD);
            B100_10_CALL_AIZPQITM();
            if (pgmRtn) return;
        }
        if (AIB5176_AWA_5176.ITM_NEW.trim().length() > 0) {
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.COA_FLG = AIB5176_AWA_5176.COA_TYP;
            AICQITM.INPUT_DATA.NO = AIB5176_AWA_5176.ITM_NEW;
            if (AIB5176_AWA_5176.ITM_NEW.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(AIB5176_AWA_5176.ITM_NEW);
            B100_10_CALL_AIZPQITM();
            if (pgmRtn) return;
        }
    }
    public void S000_LINK_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, "CKDT-----------------");
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = "" + SCCCKDT.RC;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_10_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICQITM);
        CEP.TRC(SCCGWA, "AIOT5170----------------------RC");
        CEP.TRC(SCCGWA, AICQITM.RC.RTNCODE);
        if (AICQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICQITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSCITM);
        CEP.TRC(SCCGWA, "OT5170-----------------------");
        AICSCITM.COMM_DATA.ADJ_DATE = AIB5176_AWA_5176.ADJ_DATE;
        AICSCITM.COMM_DATA.COA_TYP = AIB5176_AWA_5176.COA_TYP;
        AICSCITM.COMM_DATA.ITM_OLD = AIB5176_AWA_5176.ITM_OLD;
        AICSCITM.COMM_DATA.ITM_NEW = AIB5176_AWA_5176.ITM_NEW;
        AICSCITM.COMM_DATA.PRC_STS = AIB5176_AWA_5176.PRC_STS;
        CEP.TRC(SCCGWA, AICSCITM.COMM_DATA.ADJ_DATE);
        CEP.TRC(SCCGWA, AICSCITM.COMM_DATA.COA_TYP);
        CEP.TRC(SCCGWA, AICSCITM.COMM_DATA.ITM_OLD);
        CEP.TRC(SCCGWA, AICSCITM.COMM_DATA.ITM_NEW);
        CEP.TRC(SCCGWA, AICSCITM.COMM_DATA.PRC_STS);
        S000_CALL_AIZSCITM();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZSCITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-AIZS5170", AICSCITM);
        CEP.TRC(SCCGWA, AICSCITM.RC);
        if (AICSCITM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSCITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
