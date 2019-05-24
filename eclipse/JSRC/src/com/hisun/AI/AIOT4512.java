package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT4512 {
    String CPN_S_IRNG_MAINT = "AI-S-IRNG-MAINT ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSIRNG AICSIRNG = new AICSIRNG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB4511_AWA_4511 AIB4511_AWA_4511;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT4512 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB4511_AWA_4511>");
        AIB4511_AWA_4511 = (AIB4511_AWA_4511) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_UPD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB4511_AWA_4511.ITM_CNRT.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
            WS_FLD_NO = AIB4511_AWA_4511.ITM_CNRT_NO;
            S000_ERR_MSG_PROC();
        }
        if (AIB4511_AWA_4511.COA_FR.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
            WS_FLD_NO = AIB4511_AWA_4511.COA_FR_NO;
            S000_ERR_MSG_PROC();
        }
        if (AIB4511_AWA_4511.COA_TO.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
            WS_FLD_NO = AIB4511_AWA_4511.COA_TO_NO;
            S000_ERR_MSG_PROC();
        }
        if (AIB4511_AWA_4511.CCY_FLG == ' ') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
            WS_FLD_NO = AIB4511_AWA_4511.CCY_FLG_NO;
            S000_ERR_MSG_PROC();
        }
        if (AIB4511_AWA_4511.COA_FR.compareTo(AIB4511_AWA_4511.COA_TO) > 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_FR_GT_COA_TO;
            WS_FLD_NO = AIB4511_AWA_4511.COA_FR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_UPD_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICSIRNG.MLTI);
        CEP.TRC(SCCGWA, "555555555555555555555");
        IBS.init(SCCGWA, AICSIRNG);
        AICSIRNG.I_FUNC = 'U';
        AICSIRNG.ITM_CNRT = AIB4511_AWA_4511.ITM_CNRT;
        AICSIRNG.SEQ_NO = AIB4511_AWA_4511.SEQ_NO;
        AICSIRNG.COA_FR = AIB4511_AWA_4511.COA_FR;
        AICSIRNG.COA_TO = AIB4511_AWA_4511.COA_TO;
        AICSIRNG.AC_CTR = AIB4511_AWA_4511.AC_CTR;
        AICSIRNG.CCY_FLG = AIB4511_AWA_4511.CCY_FLG;
        AICSIRNG.CCY = AIB4511_AWA_4511.CCY;
        AICSIRNG.MLTI = AIB4511_AWA_4511.MLTI;
        AICSIRNG.DR_CR = AIB4511_AWA_4511.DR_CR;
        AICSIRNG.COA_LVL = AIB4511_AWA_4511.COA_LVL;
        CEP.TRC(SCCGWA, AICSIRNG.MLTI);
        CEP.TRC(SCCGWA, "666666666666666666666");
        S00_CALL_AIZSIRNG();
        CEP.TRC(SCCGWA, AICSIRNG.MLTI);
        CEP.TRC(SCCGWA, "777777777777777777777");
    }
    public void S00_CALL_AIZSIRNG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_IRNG_MAINT, AICSIRNG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
