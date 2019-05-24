package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCBWEVT;
import com.hisun.BP.BPCOVAWR;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCQCCY;
import com.hisun.BP.BPRVWA;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class AIZPUITM {
    boolean pgmRtn = false;
    String K_ITM_BOOK_FLG = "BK001";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY      ";
    String WS_ERR_MSG = " ";
    int WS_BOOK_CNT = 0;
    short WS_A_SET_SEQ = 0;
    char WS_ONL_FLG = ' ';
    int WS_TMP_BBR = 0;
    char WS_RVS_TYP = ' ';
    char WS_RVS_KND = ' ';
    char WS_PROC_TYPE = ' ';
    char WS_DRCR_FLG = ' ';
    char WS_HMIB_FLG = ' ';
    AIZPUITM_WS_IA_AC WS_IA_AC = new AIZPUITM_WS_IA_AC();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    SCCKDGAC SCCKDGAC = new SCCKDGAC();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    BPCBWEVT BPCBWEVT = new BPCBWEVT();
    AICPQITM AICPQITM = new AICPQITM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AIRHMIB AIRHMIB = new AIRHMIB();
    AICRHMIB AICRHMIB = new AICRHMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRVWA BPRVWA;
    AICPUITM AICPUITM;
    public void MP(SCCGWA SCCGWA, AICPUITM AICPUITM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICPUITM = AICPUITM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPUITM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRVWA = (BPRVWA) GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPUITM.DATA.AMT);
        if (AICPUITM.DATA.AMT != 0) {
            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                CEP.TRC(SCCGWA, "BAT PROCESS");
                B200_GEN_EWA_BAT();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "ONL PROCESS");
                B300_GEN_EWA_ONL();
                if (pgmRtn) return;
            }
            B400_ADD_AITHMIB();
            if (pgmRtn) return;
        }
    }
    public void B100_CHK_INPUT() throws IOException,SQLException,Exception {
        if (AICPUITM.DATA.ITM_NO.trim().length() == 0 
            || AICPUITM.DATA.ITM_NO.equalsIgnoreCase("0")) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MUST_INPUT);
        } else {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.BOOK_FLG = K_ITM_BOOK_FLG;
            AICPQITM.INPUT_DATA.NO = AICPUITM.DATA.ITM_NO;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICPQITM.OUTPUT_DATA.MIB_FLG != 'G') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_ISNOT_G);
            }
            if (AICPQITM.OUTPUT_DATA.ITM_LVL != '3') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.MIB_NOT_MATCH_ODE);
            }
        }
        CEP.TRC(SCCGWA, AICPUITM.DATA.CCY);
        if (AICPUITM.DATA.CCY.trim().length() == 0 
            || AICPUITM.DATA.CCY.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_CCY_MUST_INPUT, AICPUITM.RC);
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_CCY_MUST_INPUT);
        } else {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = AICPUITM.DATA.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
        if (AICPUITM.DATA.BR_OLD != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AICPUITM.DATA.BR_OLD;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        if (!AICPUITM.DATA.EVENT_CODE.equalsIgnoreCase("CR") 
            && !AICPUITM.DATA.EVENT_CODE.equalsIgnoreCase("DR")) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_DCFLG_NOT_VAL);
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_DCFLG_NOT_VAL, AICPUITM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_GEN_EWA_ONL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "GL";
        BPCPOEWA.DATA.BR_OLD = AICPUITM.DATA.BR_OLD;
        BPCPOEWA.DATA.BR_NEW = AICPUITM.DATA.BR_NEW;
        CEP.TRC(SCCGWA, AICPUITM.DATA.EVENT_CODE);
        BPCPOEWA.DATA.EVENT_CODE = AICPUITM.DATA.EVENT_CODE;
        BPCPOEWA.DATA.PROD_CODE = AICPUITM.DATA.PROD_CODE;
        BPCPOEWA.DATA.CI_NO = AICPUITM.DATA.CI_NO;
        BPCPOEWA.DATA.AC_NO = AICPUITM.DATA.ITM_NO;
        BPCPOEWA.DATA.AC_FLG = 'G';
        BPCPOEWA.DATA.GLAC_INFO[1-1].ITM1 = AICPUITM.DATA.ITM_NO;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = AICPUITM.DATA.CCY;
        if (AICPUITM.DATA.AMT > 0) {
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = AICPUITM.DATA.AMT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = AICPUITM.DATA.AMT * ( -1 );
        }
        BPCPOEWA.DATA.VALUE_DATE = AICPUITM.DATA.VALUE_DATE;
        BPCPOEWA.DATA.REF_NO = AICPUITM.DATA.REF_NO;
        BPCPOEWA.DATA.PORT = AICPUITM.DATA.PORT;
        BPCPOEWA.DATA.POST_DATE = AICPUITM.DATA.POST_DATE;
        BPCPOEWA.DATA.POST_NARR = AICPUITM.DATA.POST_NARR;
        BPCPOEWA.DATA.NARR_CD = AICPUITM.DATA.NARR_CD;
