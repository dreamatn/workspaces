package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT8550 {
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
    AICSXBAL AICSXBAL = new AICSXBAL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    AIB8550_AWA_8550 AIB8550_AWA_8550;
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
        CEP.TRC(SCCGWA, "AIOT8550 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB8550_AWA_8550>");
        AIB8550_AWA_8550 = (AIB8550_AWA_8550) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        if (AIB8550_AWA_8550.GL_BOOK.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            if (AIB8550_AWA_8550.GL_BOOK.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(AIB8550_AWA_8550.GL_BOOK);
            WS_ERR_INF = "GL-BOOK IS SPACE!";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "BB33B");
        CEP.TRC(SCCGWA, AIB8550_AWA_8550.GL_BOOK);
        CEP.TRC(SCCGWA, "BB33B1");
        if (AIB8550_AWA_8550.BR_ST != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB8550_AWA_8550.BR_ST;
            WS_ERR_INF = "START BR ERROR!";
            WS_FLD_NO = AIB8550_AWA_8550.BR_ST_NO;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        if (AIB8550_AWA_8550.BR_EN != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB8550_AWA_8550.BR_EN;
            WS_ERR_INF = "END BR ERROR!";
            WS_FLD_NO = AIB8550_AWA_8550.BR_EN_NO;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSXBAL);
        AICSXBAL.GL_BOOK = AIB8550_AWA_8550.GL_BOOK;
        AICSXBAL.BR_ST = AIB8550_AWA_8550.BR_ST;
        AICSXBAL.BR_EN = AIB8550_AWA_8550.BR_EN;
        AICSXBAL.DT_ST = AIB8550_AWA_8550.DT_ST;
        AICSXBAL.DT_EN = AIB8550_AWA_8550.DT_EN;
        CEP.TRC(SCCGWA, "BB44");
        S000_CALL_AIZHFXBL();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZHFXBL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB55");
        IBS.CALLCPN(SCCGWA, "AI-SVR-AIZHFXBL", AICSXBAL);
        CEP.TRC(SCCGWA, "BB551");
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB66");
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
