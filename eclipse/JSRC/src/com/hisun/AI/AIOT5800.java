package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPORLO;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class AIOT5800 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_BR = 0;
    char WS_GRANT = ' ';
    int WS_CNTA = 0;
    char WS_SUCC = ' ';
    char WS_LVL = ' ';
    char WS_LVL1 = ' ';
    AIOT5800_WS_SUPR_GRP WS_SUPR_GRP = new AIOT5800_WS_SUPR_GRP();
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
    AICSMIB AICSMIB = new AICSMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQITM AICPQITM = new AICPQITM();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    BPCPORLO BPCPORLO = new BPCPORLO();
    AICRONA AICRONA = new AICRONA();
    AIRONA AIRONA = new AIRONA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5800_AWA_5800 AIB5800_AWA_5800;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT5800 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5800_AWA_5800>");
        AIB5800_AWA_5800 = (AIB5800_AWA_5800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BRW_REC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.ITM);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.AC);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.ITM_NM);
        if (AIB5800_AWA_5800.AC.trim().length() == 0 
            && AIB5800_AWA_5800.ITM.trim().length() == 0 
            && AIB5800_AWA_5800.ITM_NM.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AIB5800_AWA_5800.ITM.trim().length() > 0) {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.NO = AIB5800_AWA_5800.ITM;
            AICPQITM.INPUT_DATA.BOOK_FLG = AIB5800_AWA_5800.GL_BOOK;
            S00_CALL_AIZPQITM();
            if (pgmRtn) return;
        }
        if (AIB5800_AWA_5800.ITM.trim().length() > 0 
            && AIB5800_AWA_5800.SEQ != 0) {
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICRCMIB);
            AICRCMIB.FUNC = 'F';
            AIRCMIB.KEY.GL_BOOK = AIB5800_AWA_5800.GL_BOOK;
            AIRCMIB.KEY.ITM = AIB5800_AWA_5800.ITM;
            AIRCMIB.KEY.SEQ = AIB5800_AWA_5800.SEQ;
            AICRCMIB.POINTER = AIRCMIB;
            AICRCMIB.REC_LEN = 407;
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
            if (AICRCMIB.RETURN_INFO == 'N') {
                AIRCMIB.KEY.SEQ = 999999;
                AICRCMIB.POINTER = AIRCMIB;
                AICRCMIB.REC_LEN = 407;
                S000_CALL_AIZRCMIB();
                if (pgmRtn) return;
            }
            if (AICRCMIB.RETURN_INFO == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.BR);
        if (AIB5800_AWA_5800.BR != 0 
            && AIB5800_AWA_5800.BR != 999999) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB5800_AWA_5800.BR;
            S00_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSMIB);
        AICSMIB.FUNC = 'B';
        AICSMIB.GL_BOOK = AIB5800_AWA_5800.GL_BOOK;
        AICSMIB.ITM_NO = AIB5800_AWA_5800.ITM;
        AICSMIB.BR = AIB5800_AWA_5800.BR;
        AICSMIB.SEQ = AIB5800_AWA_5800.SEQ;
        AICSMIB.CCY = AIB5800_AWA_5800.CCY;
        AICSMIB.AC = AIB5800_AWA_5800.AC;
        AICSMIB.TX_FLG = AIB5800_AWA_5800.TX_FLG;
