package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPORLO;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCPQVCH;
import com.hisun.BP.BPCPRMB;
import com.hisun.BP.BPRPARM;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class AIZSQSUS {
    boolean pgmRtn = false;
    String CPN_R_MAIN_GRVS = "AI-R-MAIN-GRVS";
    String CPN_P_INQ_VCH = "BP-P-INQ-VCH";
    String CPN_P_INQ_ITM = "AI-P-INQ-ITM";
    String TBL_BPTPARM = "BPTPARM";
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_BOOK_FLG = " ";
    String WS_ITM = " ";
    int WS_I = 0;
    int WS_M = 0;
    int WS_CNT = 0;
    String WS_TMP_ITM = " ";
    int WS_TMP_SEQ = 0;
    int WS_BR = 0;
    int WS_J = 0;
    int WS_X1 = 0;
    AIZSQSUS_WS_OUTPUT_GRVS WS_OUTPUT_GRVS = new AIZSQSUS_WS_OUTPUT_GRVS();
    AIZSQSUS_WS_OUTPUT_VCH WS_OUTPUT_VCH = new AIZSQSUS_WS_OUTPUT_VCH();
    int WS_LAST_BR = 0;
    int WS_CNT1 = 0;
    AIZSQSUS_WS_OUTPUT_VCH1 WS_OUTPUT_VCH1 = new AIZSQSUS_WS_OUTPUT_VCH1();
    List<AIZSQSUS_WS_ERR_SUS_DATA> WS_ERR_SUS_DATA = new ArrayList<AIZSQSUS_WS_ERR_SUS_DATA>();
    List<AIZSQSUS_WS_BR_DATA> WS_BR_DATA = new ArrayList<AIZSQSUS_WS_BR_DATA>();
    int WS_BEGDAT = 0;
    int WS_ENDDAT = 0;
    char WS_REC_BRW_FLAG = ' ';
    char WS_PARM_FLG = ' ';
    char WS_ADD_CNT_FLG = ' ';
    char WS_OUT_FLG = ' ';
    char WS_BR_FLG = ' ';
    char WS_GRVS_FLG = ' ';
    char WS_GINF_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AIRGRVS AIRGRVS = new AIRGRVS();
    AIRCRVS AIRCRVS = new AIRCRVS();
    AIRMIB AIRMIB = new AIRMIB();
    AICRGRVS AICRGRVS = new AICRGRVS();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    AIRPAI7 AIRPAI7 = new AIRPAI7();
    AICPQITM AICPQITM = new AICPQITM();
    AIRGINF AIRGINF = new AIRGINF();
    AICRGINF AICRGINF = new AICRGINF();
    BPCPQVCH BPCPQVCH = new BPCPQVCH();
    BPCPORLO BPCPORLO = new BPCPORLO();
    SCCFMT SCCFMT = new SCCFMT();
    AICPQMIB AICPQMIB = new AICPQMIB();
    SCCGWA SCCGWA;
    AICSQSUS AICSQSUS;
    AIB5600_AWA_5600 AIB5600_AWA_5600;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, AICSQSUS AICSQSUS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSQSUS = AICSQSUS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSQSUS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CEP.TRC(SCCGWA, AICSQSUS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSQSUS.FUNC == '1') {
            B020_INQ_ERR_VCH_GRVS();
            if (pgmRtn) return;
        } else if (AICSQSUS.FUNC == '2') {
            B030_INQ_VCH_DTL_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID SQSUS-FUNC(" + AICSQSUS.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICSQSUS.FUNC);
        CEP.TRC(SCCGWA, AICSQSUS.TR_DATE);
        CEP.TRC(SCCGWA, AICSQSUS.SET_NO);
        CEP.TRC(SCCGWA, AICSQSUS.RVS_NO);
        CEP.TRC(SCCGWA, AICSQSUS.BR);
        CEP.TRC(SCCGWA, AICSQSUS.STS);
        if (AICSQSUS.FUNC == '1') {
            if (AICSQSUS.SET_NO.trim().length() > 0) {
            }
        }
