package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPQORG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class AIOT5700 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_AA = 0;
    int WS_B = 0;
    char WS_SIGN = ' ';
    char WS_FLG = ' ';
    int WS_K = 0;
    int WS_J = 0;
    int WS_X = 0;
    int WS_Y = 0;
    int WS_Z = 0;
    int WS_S = 0;
    int WS_N = 0;
    int WS_SAVE_J = 0;
    int WS_I = 0;
    short WS_TOT_LEN = 0;
    short WS_TOT_LEN_1 = 0;
    short WS_TOT_LEN_2 = 0;
    String WS_M_CNAME = " ";
    String WS_Y_CNAME = " ";
    String WS_Z_CNAME = " ";
    String WS_CHN_NM = " ";
    String WS_TMP_CHN_NM = " ";
    char WS_INQ_FLG2 = ' ';
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
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT5700 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5700_AWA_5700>");
        AIB5700_AWA_5700 = (AIB5700_AWA_5700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5700_AWA_5700.ITM);
        if (AIB5700_AWA_5700.ITM.trim().length() > 0) {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.NO = AIB5700_AWA_5700.ITM;
            AICPQITM.INPUT_DATA.BOOK_FLG = AIB5700_AWA_5700.GL_BOOK;
            S00_CALL_AIZPQITM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK BR");
        CEP.TRC(SCCGWA, AIB5700_AWA_5700.BR);
        if (AIB5700_AWA_5700.BR != 0 
            && AIB5700_AWA_5700.BR != 999999) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB5700_AWA_5700.BR;
            S00_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSCMIB);
        AICSCMIB.FUNC = 'B';
        AICSCMIB.GL_BOOK = AIB5700_AWA_5700.GL_BOOK;
        AICSCMIB.ITM = AIB5700_AWA_5700.ITM;
        if (AIB5700_AWA_5700.BR == 999999) {
            AICSCMIB.BR = AIB5700_AWA_5700.BR;
        } else {
            AICSCMIB.BR = AIB5700_AWA_5700.BR;
        }
        AICSCMIB.SEQ = AIB5700_AWA_5700.SEQ;
