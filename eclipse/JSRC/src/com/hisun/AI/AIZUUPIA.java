package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCBWEVT;
import com.hisun.BP.BPCOVAWR;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCQBKPM;
import com.hisun.BP.BPCQCCY;
import com.hisun.BP.BPRVWA;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class AIZUUPIA {
    boolean pgmRtn = false;
    int K_OCCURS_MAX1 = 10;
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY      ";
    String WS_ERR_MSG = " ";
    int WS_BOOK_CNT = 0;
    char WS_ONL_FLG = ' ';
    char WS_RVS_TYP = ' ';
    char WS_RVS_KND = ' ';
    char WS_PROC_TYPE = ' ';
    char WS_DRCR_FLG = ' ';
    AIZUUPIA_WS_IA_AC WS_IA_AC = new AIZUUPIA_WS_IA_AC();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    AIRCMIB AIRCMIB = new AIRCMIB();
    SCCKDGAC SCCKDGAC = new SCCKDGAC();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    BPCBWEVT BPCBWEVT = new BPCBWEVT();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICUIANO AICUIANO = new AICUIANO();
    AICUPRVS AICUPRVS = new AICUPRVS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AIRGINF AIRGINF = new AIRGINF();
    AIRGRVS AIRGRVS = new AIRGRVS();
    AIRCRVS AIRCRVS = new AIRCRVS();
    AICRGINF AICRGINF = new AICRGINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRVWA BPRVWA;
    AICUUPIA AICUUPIA;
    public void MP(SCCGWA SCCGWA, AICUUPIA AICUUPIA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICUUPIA = AICUUPIA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZUUPIA return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRVWA = (BPRVWA) GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
        CEP.TRC(SCCGWA, BPRVWA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
        if (AICUUPIA.DATA.AMT != 0) {
            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                CEP.TRC(SCCGWA, "11111111");
                B040_GEN_EWA_BAT();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "22222222");
                if ((WS_RVS_TYP == 'D' 
                    || WS_RVS_TYP == 'C') 
                    && AICUUPIA.DATA.RVS_NO.trim().length() == 0) {
                    B050_GEN_RVS_NO();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
                B030_GEN_EWA_ONL();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHK_INP() throws IOException,SQLException,Exception {
        if (AICUUPIA.DATA.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_IA_NO_MUST_IPT, AICUUPIA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCQBKPM);
            BPCQBKPM.FUNC = 'B';
            CEP.TRC(SCCGWA, BPCQBKPM);
            S000_CALL_BPZQBKPM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCQBKPM.DATA[1-1].BOOK_FLG);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
            for (WS_BOOK_CNT = 1; WS_BOOK_CNT <= 5 
                && BPCQBKPM.DATA[WS_BOOK_CNT-1].BOOK_FLG.trim().length() != 0; WS_BOOK_CNT += 1) {
                IBS.init(SCCGWA, AICPQMIB);
                IBS.init(SCCGWA, AICUIANO);
                AICPQMIB.INPUT_DATA.GL_BOOK = BPCQBKPM.DATA[WS_BOOK_CNT-1].BOOK_FLG;
                AICPQMIB.INPUT_DATA.AC = AICUUPIA.DATA.AC_NO;
                AICPQMIB.INPUT_DATA.CCY = AICUUPIA.DATA.CCY;
                S000_CALL_AIZPQMIB();
                if (pgmRtn) return;
                WS_RVS_TYP = AICPQMIB.OUTPUT_DATA.RVS_TYP;
                WS_RVS_KND = AICPQMIB.OUTPUT_DATA.RVS_KND;
                AICUUPIA.DATA.AC_NO = AICPQMIB.INPUT_DATA.AC;
                WS_ONL_FLG = AICPQMIB.OUTPUT_DATA.ONL_FLG;
                CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.CARD_FLG);
                if (AICPQMIB.OUTPUT_DATA.CARD_FLG == 'Y') {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_IA_NOT_ALLOW_ACCOUNT, AICUUPIA.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                AICUIANO.INPUT_DATA.GL_BOOK = AICPQMIB.INPUT_DATA.GL_BOOK;
                AICUIANO.INPUT_DATA.AC = AICPQMIB.INPUT_DATA.AC;
                AICUIANO.INPUT_DATA.CCY = AICPQMIB.INPUT_DATA.CCY;
                S000_CALL_AIZUIANO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICUUPIA.DATA.CCY);
                if (!AICPQMIB.INPUT_DATA.CCY.equalsIgnoreCase(AICUUPIA.DATA.CCY)) {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.M_CHK_CCY, AICUUPIA.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, AICUUPIA.DATA.CCY);
        if (AICUUPIA.DATA.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_CCY_MUST_INPUT, AICUUPIA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = AICUUPIA.DATA.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, AICUUPIA.DATA.AC_NO, WS_IA_AC);
            if (!AICUUPIA.DATA.CCY.equalsIgnoreCase(WS_IA_AC.WS_IA_CCY)) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.M_CHK_CCY;
                SCCBINF.OTHER_INFO = "UUPIA-CCY = " + AICUUPIA.DATA.CCY + ",MIB_CCY = " + WS_IA_AC.WS_IA_CCY + "  NOT MATCH";
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (WS_IA_AC.WS_IA_BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = WS_IA_AC.WS_IA_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        if (!AICUUPIA.DATA.EVENT_CODE.equalsIgnoreCase("CR") 
            && !AICUUPIA.DATA.EVENT_CODE.equalsIgnoreCase("DR")) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_DCFLG_NOT_VAL, AICUUPIA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (AICUUPIA.DATA.EVENT_CODE.equalsIgnoreCase("CR")) {
                AICUUPIA.DATA.EVENT_CODE = "CR";
            } else {
                AICUUPIA.DATA.EVENT_CODE = "DR";
            }
        }
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        if (AICUUPIA.DATA.EVENT_CODE == null) AICUUPIA.DATA.EVENT_CODE = "";
        JIBS_tmp_int = AICUUPIA.DATA.EVENT_CODE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) AICUUPIA.DATA.EVENT_CODE += " ";
        WS_DRCR_FLG = AICUUPIA.DATA.EVENT_CODE.substring(0, 1).charAt(0);
        if (WS_DRCR_FLG == WS_RVS_TYP 
            && (WS_RVS_KND == '1' 
            || WS_RVS_KND == '2') 
            && AICUUPIA.DATA.RVS_NO.trim().length() > 0 
            && AICUUPIA.DATA.AMT > 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.1G_CAN_NOT_INPUT_RVS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICUUPIA.DATA.RVS_NO.trim().length() > 0) {
            IBS.init(SCCGWA, AIRGINF);
            IBS.init(SCCGWA, AICRGINF);
            AICRGINF.INFO.FUNC = 'Q';
            AIRGINF.KEY.RVS_NO = AICUUPIA.DATA.RVS_NO;
            S000_CALL_AIZRGINF();
            if (pgmRtn) return;
            if (AICRGINF.RETURN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_NOT_EXIST, AICUUPIA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, AIRGINF.AC);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
            CEP.TRC(SCCGWA, WS_DRCR_FLG);
            CEP.TRC(SCCGWA, WS_RVS_TYP);
            if (!AIRGINF.AC.equalsIgnoreCase(AICUUPIA.DATA.AC_NO) 
                && WS_DRCR_FLG == WS_RVS_TYP) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_RVS_NO_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICUUPIA.DATA.RVS_NO.trim().length() > 0 
                && WS_RVS_TYP == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_RVS_NO_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.CPY2CLS(SCCGWA, AICUUPIA.DATA.AC_NO, WS_IA_AC);
        if (WS_IA_AC.WS_IA_SEQ == 999999) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_MUST_OPEN_BEFORE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_GEN_EWA_ONL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "IA";
        BPCPOEWA.DATA.BR_OLD = AICUIANO.INPUT_DATA.BR;
        BPCPOEWA.DATA.BR_NEW = AICUIANO.INPUT_DATA.BR;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.EVENT_CODE);
        BPCPOEWA.DATA.EVENT_CODE = AICUUPIA.DATA.EVENT_CODE;
        if (AICUUPIA.DATA.AC_NO == null) AICUUPIA.DATA.AC_NO = "";
        JIBS_tmp_int = AICUUPIA.DATA.AC_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AICUUPIA.DATA.AC_NO += " ";
        BPCPOEWA.DATA.AC_NO = AICUUPIA.DATA.AC_NO.substring(7 - 1, 7 + 19 - 1);
        BPCPOEWA.DATA.RVS_NO = AICUUPIA.DATA.RVS_NO;
        BPCPOEWA.DATA.RVS_SEQ = AICUUPIA.DATA.RVS_SEQ;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_SEQ);
        BPCPOEWA.DATA.PROD_CODE = AICUUPIA.DATA.PROD_CODE;
        BPCPOEWA.DATA.AC_FLG = 'I';
        BPCPOEWA.DATA.GLAC_INFO[1-1].ITM1 = AICUIANO.INPUT_DATA.ITM;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = AICUUPIA.DATA.CCY;
        if (AICUUPIA.DATA.AMT > 0) {
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = AICUUPIA.DATA.AMT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = AICUUPIA.DATA.AMT * ( -1 );
        }
        BPCPOEWA.DATA.VALUE_DATE = AICUUPIA.DATA.VALUE_DATE;
        BPCPOEWA.DATA.CI_NO = AICUUPIA.DATA.CI_NO;
        BPCPOEWA.DATA.REF_NO = AICUUPIA.DATA.REF_NO;
        BPCPOEWA.DATA.PORT = AICUUPIA.DATA.PORT;
        BPCPOEWA.DATA.POST_DATE = AICUUPIA.DATA.POST_DATE;
        BPCPOEWA.DATA.POST_NARR = AICUUPIA.DATA.POST_NARR;
        BPCPOEWA.DATA.NARR_CD = AICUUPIA.DATA.NARR_CD;
