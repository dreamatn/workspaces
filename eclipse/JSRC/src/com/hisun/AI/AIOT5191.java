package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5191 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_ERR_INF = " ";
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    AICSIADP AICSIADP = new AICSIADP();
    AICSITAD AICSITAD = new AICSITAD();
    AICPQITM AICQITM = new AICPQITM();
    SCCGWA SCCGWA;
    AIB0005_AWA_0005 AIB0005_AWA_0005;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BB11");
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT5191 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0005_AWA_0005>");
        AIB0005_AWA_0005 = (AIB0005_AWA_0005) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B100_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB22");
        if (AIB0005_AWA_0005.COA_NO.equalsIgnoreCase("0") 
            || AIB0005_AWA_0005.COA_NO.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            WS_FLD_NO = (short) AIB0005_AWA_0005.ADJ_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "BB33B");
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.GL_BOOK);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_NO);
        IBS.init(SCCGWA, AICQITM);
        AICQITM.INPUT_DATA.BOOK_FLG = AIB0005_AWA_0005.GL_BOOK;
        AICQITM.INPUT_DATA.NO = AIB0005_AWA_0005.COA_NO;
        S000_CALL_AIZQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BB33B1");
    }
    public void B100_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSITAD);
        AICSIADP.ITM = AIB0005_AWA_0005.COA_NO;
        AICSIADP.GL_BOOK_FLG = AIB0005_AWA_0005.GL_BOOK;
        CEP.TRC(SCCGWA, "BB44");
        S000_CALL_AIZSIADP();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZSIADP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB55");
        IBS.CALLCPN(SCCGWA, "AI-SVR-AIZSIADP", AICSIADP);
        CEP.TRC(SCCGWA, "BB551");
    }
    public void S000_CALL_AIZQITM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB66A");
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICQITM);
        CEP.TRC(SCCGWA, "BB66B");
        CEP.TRC(SCCGWA, AICQITM.RC.RTNCODE);
        if (AICQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_NOT_VAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICQITM.OUTPUT_DATA.LOW_LVL_FLG == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LOW_LVL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB66");
        WS_ERR_INF = " ";
        WS_ERR_INF = "ITM: " + AICQITM.INPUT_DATA.NO;
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
