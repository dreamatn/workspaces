package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPOEWA {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    BPREWA15_EVENTS EVENTS;
    boolean pgmRtn = false;
    String PUBLIC_TYPE = "EVENT";
    BPZPOEWA_WS_VARIABLES WS_VARIABLES = new BPZPOEWA_WS_VARIABLES();
    BPZPOEWA_WS_COND_FLG WS_COND_FLG = new BPZPOEWA_WS_COND_FLG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG ERROR_MSG1 = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    BPRTRT BPRTRT;
    BPRVWA BPRVWA;
    BPREWA BPREWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPCPOEWA BPCPOEWA;
    public void MP(SCCGWA SCCGWA, BPCPOEWA BPCPOEWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPOEWA = BPCPOEWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPOEWA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPREWA = (BPREWA) BP_AREA.EWA_AREA.EWA_AREA_PTR;
        BPRTRT = (BPRTRT) SC_AREA.TR_PARM_PTR;
        IBS.init(SCCGWA, WS_COND_FLG);
        BPCPOEWA.RC.RC_MMO = "BP";
        BPCPOEWA.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCPOEWA.DATA.FILLER == null) BPCPOEWA.DATA.FILLER = "";
        JIBS_tmp_int = BPCPOEWA.DATA.FILLER.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCPOEWA.DATA.FILLER += " ";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && !BPCPOEWA.DATA.FILLER.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y") 
            && BPRTRT.DATA_TXT.REEN_IND == 'N') {
        } else {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B020_WRITE_EWA_DATA();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE_REL);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_NEW);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1]);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[2-1]);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[3-1]);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[4-1]);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CI_NO);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.EFF_DAYS);
        if (SCCGWA.COMM_AREA.JRN_NO <= 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_TXN_NOT_IND_LOG, BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BP_AREA.EWA_AREA.EWA_MAX_CNT <= 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_NOT_ALLO_MAX_CNT, BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPOEWA.DATA.PROD_CODE == null) BPCPOEWA.DATA.PROD_CODE = "";
        JIBS_tmp_int = BPCPOEWA.DATA.PROD_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCPOEWA.DATA.PROD_CODE += " ";
        if (BPCPOEWA.DATA.PROD_CODE.substring(10 - 1, 10 + 1 - 1).trim().length() > 0 
            && !BPCPOEWA.DATA.CNTR_TYPE.equalsIgnoreCase("FEES")) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = BPCPOEWA.DATA.PROD_CODE;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "FEIYONGDAIMAJIACHA");
        if (BPCPOEWA.DATA.PROD_CODE_REL == null) BPCPOEWA.DATA.PROD_CODE_REL = "";
        JIBS_tmp_int = BPCPOEWA.DATA.PROD_CODE_REL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCPOEWA.DATA.PROD_CODE_REL += " ";
        if (BPCPOEWA.DATA.PROD_CODE_REL.substring(10 - 1, 10 + 1 - 1).trim().length() > 0 
            && BPCPOEWA.DATA.CNTR_TYPE.equalsIgnoreCase("FEES")) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = BPCPOEWA.DATA.PROD_CODE_REL;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BP_AREA.EWA_AREA.EWA_MAX_CNT);
        if (BP_AREA.EWA_AREA.EWA_CNT >= BP_AREA.EWA_AREA.EWA_MAX_CNT) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_9392, BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPOEWA.DATA.CNTR_TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_9384, BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (!BPCPOEWA.DATA.CNTR_TYPE.equalsIgnoreCase("IB") 
                && !BPCPOEWA.DATA.CNTR_TYPE.equalsIgnoreCase("FEEV") 
                && !BPCPOEWA.DATA.CNTR_TYPE.equalsIgnoreCase("GL")) {
                IBS.init(SCCGWA, BPCPQPDM);
                BPCPQPDM.PRDT_MODEL = BPCPOEWA.DATA.CNTR_TYPE;
                BPCPQPDM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
                WS_COND_FLG.REC_FLAG = 'Y';
                S000_CALL_BPZPQPDM();
                if (pgmRtn) return;
                if (WS_COND_FLG.REC_FLAG == 'N') {
                    IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_CNTR_TYPE_NOT_FOUND, BPCPOEWA.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPCPOEWA.DATA.EVENT_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_9385, BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = PUBLIC_TYPE;
            BPCOQPCD.INPUT_DATA.CODE = BPCPOEWA.DATA.EVENT_CODE;
            CEP.TRC(SCCGWA, PUBLIC_TYPE);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
            S010_CALL_BPZPQPCD();
            if (pgmRtn) return;
            if (BPCOQPCD.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPOEWA.RC);
            }
        }
        if (BPCPOEWA.DATA.BR_OLD != 0) {
            CEP.TRC(SCCGWA, "CHK BR OLD");
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCPOEWA.DATA.BR_OLD;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            CEP.TRC(SCCGWA, BPCPQORG.BR);
            CEP.TRC(SCCGWA, BPCPQORG.BBR);
            WS_COND_FLG.TEMP_BBR = BPCPQORG.BBR;
            if (BPCPQORG.ATTR != '2') {
                if (BPCPQORG.ATTR == '3') {
                    if (BPCPQORG.BBR != 0) {
                        IBS.init(SCCGWA, BPCPQORG);
                        CEP.TRC(SCCGWA, WS_COND_FLG.TEMP_BBR);
                        BPCPQORG.BR = WS_COND_FLG.TEMP_BBR;
                        S000_CALL_BPZPQORG();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                        CEP.TRC(SCCGWA, BPCPQORG.BBR);
                        if (BPCPQORG.ATTR != '2') {
                            CEP.ERR(SCCGWA, ERROR_MSG1.AI_NOT_BOOK_BR);
                        } else {
                            BPCPOEWA.DATA.BR_OLD = BPCPQORG.BBR;
                        }
                    } else {
                        CEP.ERR(SCCGWA, ERROR_MSG.BP_BR_INPUT_ERROR);
                    }
                } else {
                    CEP.ERR(SCCGWA, ERROR_MSG1.AI_NOT_BOOK_BR);
                }
            }
            WS_COND_FLG.FX_BUSI_OLD = BPCPQORG.FX_BUSI;
            CEP.TRC(SCCGWA, WS_COND_FLG.FX_BUSI_OLD);
        }
        if (BPCPOEWA.DATA.BR_NEW != 0) {
            CEP.TRC(SCCGWA, "CHK BR NEW");
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCPOEWA.DATA.BR_NEW;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            CEP.TRC(SCCGWA, BPCPQORG.BR);
            CEP.TRC(SCCGWA, BPCPQORG.BBR);
            WS_COND_FLG.TEMP_BBR = BPCPQORG.BBR;
            if (BPCPQORG.ATTR != '2') {
                if (BPCPQORG.ATTR == '3') {
                    if (BPCPQORG.BBR != 0) {
                        IBS.init(SCCGWA, BPCPQORG);
                        CEP.TRC(SCCGWA, WS_COND_FLG.TEMP_BBR);
                        BPCPQORG.BR = WS_COND_FLG.TEMP_BBR;
                        S000_CALL_BPZPQORG();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                        CEP.TRC(SCCGWA, BPCPQORG.BBR);
                        if (BPCPQORG.ATTR != '2') {
                            CEP.ERR(SCCGWA, ERROR_MSG1.AI_NOT_BOOK_BR);
                        } else {
                            BPCPOEWA.DATA.BR_NEW = BPCPQORG.BBR;
                        }
                    } else {
                        CEP.ERR(SCCGWA, ERROR_MSG.BP_BR_INPUT_ERROR);
                    }
                } else {
                    CEP.ERR(SCCGWA, ERROR_MSG1.AI_NOT_BOOK_BR);
                }
            }
            WS_COND_FLG.FX_BUSI_NEW = BPCPQORG.FX_BUSI;
            CEP.TRC(SCCGWA, WS_COND_FLG.FX_BUSI_NEW);
        }
        if (BPCPOEWA.DATA.BR_3 != 0) {
            CEP.TRC(SCCGWA, "CHK BR 3");
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCPOEWA.DATA.BR_3;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            CEP.TRC(SCCGWA, BPCPQORG.BR);
            CEP.TRC(SCCGWA, BPCPQORG.BBR);
            WS_COND_FLG.TEMP_BBR = BPCPQORG.BBR;
            if (BPCPQORG.ATTR != '2') {
                if (BPCPQORG.ATTR == '3') {
                    if (BPCPQORG.BBR != 0) {
                        IBS.init(SCCGWA, BPCPQORG);
                        CEP.TRC(SCCGWA, WS_COND_FLG.TEMP_BBR);
                        BPCPQORG.BR = WS_COND_FLG.TEMP_BBR;
                        S000_CALL_BPZPQORG();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                        CEP.TRC(SCCGWA, BPCPQORG.BBR);
                        if (BPCPQORG.ATTR != '2') {
                            CEP.ERR(SCCGWA, ERROR_MSG1.AI_NOT_BOOK_BR);
                        } else {
                            BPCPOEWA.DATA.BR_3 = BPCPQORG.BBR;
                        }
                    } else {
                        CEP.ERR(SCCGWA, ERROR_MSG.BP_BR_INPUT_ERROR);
                    }
                } else {
                    CEP.ERR(SCCGWA, ERROR_MSG1.AI_NOT_BOOK_BR);
                }
            }
            WS_COND_FLG.FX_BUSI_3 = BPCPQORG.FX_BUSI;
            CEP.TRC(SCCGWA, WS_COND_FLG.FX_BUSI_NEW);
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        if (BPCPOEWA.DATA.VALUE_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_9387, BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        if (BPCPOEWA.DATA.CCY_INFO[1-1].CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_9388, BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (!BPCPOEWA.DATA.CCY_INFO[1-1].CCY.equalsIgnoreCase("156") 
            && BPCPOEWA.DATA.CNTR_TYPE.equalsIgnoreCase("CAS")) {
            CEP.TRC(SCCGWA, "CHECK BR FX BUSI");
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.FX_BUSI);
            if (BPCPQORG.FX_BUSI.equalsIgnoreCase("00")) {
                CEP.ERR(SCCGWA, ERROR_MSG.BP_TR_BR_NO_FX_AUTH);
            }
        }
        WS_COND_FLG.AMT_FLAG = 'N';
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 76; WS_VARIABLES.I += 1) {
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[WS_VARIABLES.I-1].AMT);
            if (BPCPOEWA.DATA.AMT_INFO[WS_VARIABLES.I-1].AMT != 0) {
                WS_COND_FLG.AMT_FLAG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.AMT_FLAG);
        if (WS_COND_FLG.AMT_FLAG == 'N') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_9389, BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 10; WS_VARIABLES.I += 1) {
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].GLM1);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].GLM2);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].ITM1);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].ITM2);
            if (BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].GLM1 != 0 
                && (BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].ITM1.trim().length() > 0 
                || BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].ITM2.trim().length() > 0)) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_9391, BPCPOEWA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].ITM1.trim().length() > 0 
                && (BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].GLM1 != 0 
                || BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].GLM2 != 0)) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_9391, BPCPOEWA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].GLM1 != 0 
                && BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].GLM2 == 0) {
                BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].GLM2 = BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].GLM1;
            }
            if (BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].ITM1.trim().length() > 0 
                && BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].ITM2.trim().length() == 0) {
                BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].ITM2 = BPCPOEWA.DATA.GLAC_INFO[WS_VARIABLES.I-1].ITM1;
            }
        }
    }
    public void B020_WRITE_EWA_DATA() throws IOException,SQLException,Exception {
        if (WS_COND_FLG.POEWA_FLG != 'Y') {
            BP_AREA.EWA_AREA.EWA_CNT += 1;
            BPREWA.BASIC_AREA.CNT += 1;
            EVENTS = new BPREWA15_EVENTS();
            BPREWA.EVENTS.add(EVENTS);
            IBS.init(SCCGWA, EVENTS);
            CEP.TRC(SCCGWA, BPREWA.BASIC_AREA.CNT);
            if (BPREWA.BASIC_AREA.CNT > BP_AREA.EWA_AREA.EWA_MAX_CNT) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_9392, BPCPOEWA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            BPCPOEWA.DATA.CPN_CALL_SEQ = (short) SCCGWA.COMM_AREA.CALL_SEQ;
            IBS.CLONE(SCCGWA, BPCPOEWA.DATA, EVENTS.EVENT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.MOD_NO);
            CEP.TRC(SCCGWA, BPREWA.BASIC_AREA.CNT);
            CEP.TRC(SCCGWA, BPREWA.EVENTS.get(BPREWA.BASIC_AREA.CNT-1).EVENT.MOD_NO);
        }
        CEP.TRC(SCCGWA, BPREWA);
    }
    public void S000_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-MODEL", BPCPQPDM);
        CEP.TRC(SCCGWA, BPCPQPDM.RC);
        if (BPCPQPDM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_PARM_NOTFND)) {
                WS_COND_FLG.REC_FLAG = 'N';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPOEWA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.RC);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S010_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        CEP.TRC(SCCGWA, BPCOQPCD.RC);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPOEWA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPOEWA = ");
            CEP.TRC(SCCGWA, BPCPOEWA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
