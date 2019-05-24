package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT4541 {
    int JIBS_tmp_int;
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSRPNG AICSRPNG = new AICSRPNG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB4530_AWA_4530 AIB4530_AWA_4530;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT4541 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB4530_AWA_4530>");
        AIB4530_AWA_4530 = (AIB4530_AWA_4530) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_ADD_REC_PROC();
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSRPNG);
        AICSRPNG.I_FUNC = 'A';
        AICSRPNG.INFO.RPT_ID = AIB4530_AWA_4530.RPT_ID;
        AICSRPNG.INFO.ITM_CNRT = AIB4530_AWA_4530.GL_ITEM;
        AICSRPNG.INFO.SEQ = AIB4530_AWA_4530.SEQ;
        AICSRPNG.INFO.CALC_MTH = AIB4530_AWA_4530.CAL_MTH;
        AICSRPNG.INFO.DESC = AIB4530_AWA_4530.DESC;
        CEP.TRC(SCCGWA, AIB4530_AWA_4530.SEQ);
        for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
            AICSRPNG.INFO.CNT[WS_CNT-1].SEQ_NO = WS_CNT;
            if (AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 == null) AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 = "";
            JIBS_tmp_int = AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1.length();
            for (int i=0;i<34-JIBS_tmp_int;i++) AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 += " ";
            AICSRPNG.INFO.CNT[WS_CNT-1].COA_FR = AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1.substring(0, 10);
            if (AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 == null) AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 = "";
            JIBS_tmp_int = AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1.length();
            for (int i=0;i<34-JIBS_tmp_int;i++) AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 += " ";
            AICSRPNG.INFO.CNT[WS_CNT-1].COA_TO = AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1.substring(11 - 1, 11 + 10 - 1);
            if (AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 == null) AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 = "";
            JIBS_tmp_int = AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1.length();
            for (int i=0;i<34-JIBS_tmp_int;i++) AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 += " ";
            if (AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1.substring(21 - 1, 21 + 11 - 1).trim().length() == 0) AICSRPNG.INFO.CNT[WS_CNT-1].AC_CTR = 0;
            else AICSRPNG.INFO.CNT[WS_CNT-1].AC_CTR = Integer.parseInt(AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1.substring(21 - 1, 21 + 11 - 1));
            if (AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 == null) AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 = "";
            JIBS_tmp_int = AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1.length();
            for (int i=0;i<34-JIBS_tmp_int;i++) AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1 += " ";
            AICSRPNG.INFO.CNT[WS_CNT-1].CCY = AIB4530_AWA_4530.TIMS[WS_CNT-1].STR1.substring(32 - 1, 32 + 3 - 1);
            AICSRPNG.INFO.CNT[WS_CNT-1].MLTI = AIB4530_AWA_4530.TIMS[WS_CNT-1].MULTIPLR;
            AICSRPNG.INFO.CNT[WS_CNT-1].COA_LVL = AIB4530_AWA_4530.TIMS[WS_CNT-1].COL_IND;
            AICSRPNG.INFO.CNT[WS_CNT-1].DR_CR = AIB4530_AWA_4530.TIMS[WS_CNT-1].DRCR;
            CEP.TRC(SCCGWA, AICSRPNG.INFO.CNT[WS_CNT-1].COA_TO);
            CEP.TRC(SCCGWA, AIB4530_AWA_4530.TIMS[WS_CNT-1].MULTIPLR);
            CEP.TRC(SCCGWA, AIB4530_AWA_4530.TIMS[WS_CNT-1].DRCR);
        }
        AICSRPNG.INFO.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S00_CALL_AIZSRPNG();
    }
    public void S00_CALL_AIZSRPNG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-RPNG-MAINT", AICSRPNG);
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
