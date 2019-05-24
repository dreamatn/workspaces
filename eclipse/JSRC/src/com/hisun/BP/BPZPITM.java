package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPITM {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PARM_TYPE = "AMBKP";
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    String WS_ITM_NO = " ";
    String WS_TMP_ITM = " ";
    BPZPITM_WS_KEY WS_KEY = new BPZPITM_WS_KEY();
    BPZPITM_WS_VAL WS_VAL = new BPZPITM_WS_VAL();
    BPZPITM_WS_RC WS_RC = new BPZPITM_WS_RC();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    AICPQITM AICQITM = new AICPQITM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRBKPM BPRBKPM = new BPRBKPM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCCGWA SCCGWA;
    BPCPITM BPCPITM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPITM BPCPITM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPITM = BPCPITM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPITM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_FND_FLG = ' ';
        WS_RC.WS_RC_MMO = "BP";
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCPITM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_ITM();
        if (pgmRtn) return;
        B020_CHECK_GL_MASTER();
        if (pgmRtn) return;
        B030_MOVE_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_ITM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPITM.INPUT_DATA.COA_FLG);
        CEP.TRC(SCCGWA, BPCPITM.OUTPUT_DATA.GLMST);
        CEP.TRC(SCCGWA, BPCPITM.INPUT_DATA.NO);
        CEP.TRC(SCCGWA, BPCPITM.OUTPUT_DATA.POST_TYPE);
        IBS.init(SCCGWA, AICQITM);
        AICQITM.INPUT_DATA.COA_FLG = BPCPITM.INPUT_DATA.COA_FLG;
        AICQITM.INPUT_DATA.NO = BPCPITM.INPUT_DATA.NO;
        WS_ITM_NO = BPCPITM.INPUT_DATA.NO;
        WS_FND_FLG = 'Y';
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        if (WS_FND_FLG == 'N') {
            WS_INFO = "T" + "COA NO:" + WS_ITM_NO;
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_ITMNO_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.STS);
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
        if (AICQITM.OUTPUT_DATA.STS != 'A') {
            WS_INFO = "T" + "COA NO:" + WS_ITM_NO;
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_ITM_STS_MUST_ACTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TMP_ITM = AICQITM.INPUT_DATA.NO;
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
        CEP.TRC(SCCGWA, WS_TMP_ITM);
        if (WS_TMP_ITM == null) WS_TMP_ITM = "";
        JIBS_tmp_int = WS_TMP_ITM.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_TMP_ITM += " ";
        if (!WS_TMP_ITM.substring(8 - 1, 8 + 3 - 1).equalsIgnoreCase("000")) {
            CEP.TRC(SCCGWA, " ===== LAST DIGIT NOT = 0");
            if (AICQITM.OUTPUT_DATA.LOW_LVL_FLG == 'N') {
                WS_INFO = "T" + "COA NO:" + WS_ITM_NO;
                WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_ITM_MUST_LOW_LVL_FLG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCPITM.OUTPUT_DATA.POST_TYPE != ' ') {
            if (BPCPITM.OUTPUT_DATA.POST_TYPE != AICQITM.OUTPUT_DATA.POST_TYPE) {
                WS_INFO = "T" + "COA NO:" + WS_ITM_NO;
                WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_ITM_POST_TYPE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.GLMST);
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.COA_FLG);
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
    }
    public void B020_CHECK_GL_MASTER() throws IOException,SQLException,Exception {
        if (BPCPITM.OUTPUT_DATA.GLMST != 0 
            && BPCPITM.OUTPUT_DATA.GLMST != AICQITM.OUTPUT_DATA.GLMST) {
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_GL_MASTER_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_MOVE_DATA() throws IOException,SQLException,Exception {
        BPCPITM.OUTPUT_DATA.TYPE = AICQITM.OUTPUT_DATA.TYPE;
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICQITM);
        if (AICQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICQITM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND)) {
                WS_FND_FLG = 'N';
            } else {
                WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, AICQITM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR, WS_INFO, WS_FLD_NO);
        Z_RET();
        if (pgmRtn) return;
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
        CEP.TRC(SCCGWA, BPCPITM);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPITM.RC.RTNCODE != 0) {
            CEP.TRC(SCCGWA, " BPCPITM = ");
            CEP.TRC(SCCGWA, BPCPITM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
