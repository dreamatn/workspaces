package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5527 {
    String K_PROC_END = "AIPBSP05";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCBSTS SCCCBSTS = new SCCCBSTS();
    SCCMSG SCCMSG = new SCCMSG();
    AICSRRVS AICSRRVS = new AICSRRVS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5527_AWA_5527 AIB5527_AWA_5527;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5527 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5527_AWA_5527>");
        AIB5527_AWA_5527 = (AIB5527_AWA_5527) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCRCWA.BH_FLG == 'B') {
            IBS.init(SCCGWA, SCCCBSTS);
            SCCCBSTS.PROC_NAME = K_PROC_END;
            CEP.TRC(SCCGWA, SCCCBSTS);
            S000_CALL_SCZCBSTS();
            CEP.TRC(SCCGWA, SCCCBSTS);
            if (SCCCBSTS.PROC_STUS != 'F') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.BRANCH_RUNNIG_CAN_NOT;
                S000_ERR_MSG_PROC();
            }
        }
        B010_CHECK_INPUT();
        B020_BROWSE_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB5527_AWA_5527.GRVS_NO.trim().length() == 0 
            || AIB5527_AWA_5527.CRVS_NO.trim().length() == 0 
            || AIB5527_AWA_5527.AMT == 0 
            || AIB5527_AWA_5527.GRVS_NO.equalsIgnoreCase(AIB5527_AWA_5527.CRVS_NO)) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BROWSE_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSRRVS);
        AICSRRVS.GRVS_NO = AIB5527_AWA_5527.GRVS_NO;
        AICSRRVS.CRVS_NO = AIB5527_AWA_5527.CRVS_NO;
        AICSRRVS.AMT = AIB5527_AWA_5527.AMT;
        S00_CALL_AIZSRRVS();
    }
    public void S000_CALL_SCZCBSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-CHECK-BAT-STATUS", SCCCBSTS);
        if (SCCCBSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCCBSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_AIZSRRVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-RRVS", AICSRRVS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
