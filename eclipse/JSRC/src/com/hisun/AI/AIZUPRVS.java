package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPRMR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class AIZUPRVS {
    boolean pgmRtn = false;
    String K_COA_FLG = "1";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_CNT = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_C_MAX_NO = 0;
    int WS_OPR_BR = 0;
    String WS_IA_AC = " ";
    int WS_IA_BR = 0;
    String WS_IA_ITM = " ";
    int WS_IA_SEQ = 0;
    int WS_IA_BR_S = 0;
    String WS_IA_ITM_S = " ";
    int WS_IA_SEQ_S = 0;
    int WS_GRVS_MAXNO = 0;
    int WS_CRVS_MAXNO = 0;
    short WS_BOOK_CNT = 0;
    AIZUPRVS_WS_RVS_NO WS_RVS_NO = new AIZUPRVS_WS_RVS_NO();
    int WS_CAL_DATE = 0;
    int WS_RVS_EXP_DT = 0;
    short WS_RVS_EXP = 0;
    char WS_RVS_UNIT = ' ';
    AIZUPRVS_WS_BOOK_PARM[] WS_BOOK_PARM = new AIZUPRVS_WS_BOOK_PARM[10];
    double WS_RVS_AMT = 0;
    double WS_TEMP_AMT = 0;
    String WS_TEMP_NO = " ";
    char WS_TEMP_STS = ' ';
    double WS_CRVS_AMT = 0;
    char WS_RVS_TYP = ' ';
    char WS_RVS_KND = ' ';
    char WS_PROC_TYPE = ' ';
    char WS_DRCR_FLG = ' ';
    char WS_RM_FLG = ' ';
    int WS_AC_EXP = 0;
    int WS_GWA_AC_DATE = 0;
    AIZUPRVS_WS_INTERNAL_AC WS_INTERNAL_AC = new AIZUPRVS_WS_INTERNAL_AC();
    AIZUPRVS_WS_PART WS_PART = new AIZUPRVS_WS_PART();
    char WS_GENNO_FLG = ' ';
    char WS_WRITEOFF_FLG = ' ';
    char WS_AITCRVS_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    AICRGINF AICRGINF = new AICRGINF();
    AICPQITM AICPQITM = new AICPQITM();
    AICRGRVS AICRGRVS = new AICRGRVS();
    AICRCRVS AICRCRVS = new AICRCRVS();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPRMR BPCPRMR = new BPCPRMR();
    AIRPAI7 AIRPAI7 = new AIRPAI7();
    AIRPAI1 AIRPAI1 = new AIRPAI1();
    SCCCLDT SCCCLDT = new SCCCLDT();
    AICUGRNO AICUGRNO = new AICUGRNO();
    SCCIMSG SCCIMSG = new SCCIMSG();
    AIRGINF AIRGINF = new AIRGINF();
    AIRGRVS AIRGRVS = new AIRGRVS();
    AIRCRVS AIRCRVS = new AIRCRVS();
    AIRCMIB AIRCMIB = new AIRCMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICUPRVS AICUPRVS;
    public AIZUPRVS() {
        for (int i=0;i<10;i++) WS_BOOK_PARM[i] = new AIZUPRVS_WS_BOOK_PARM();
    }
    public void MP(SCCGWA SCCGWA, AICUPRVS AICUPRVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICUPRVS = AICUPRVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZUPRVS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICUPRVS.DATA.FUNC);
        JIBS_tmp_str[0] = "" + GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(0, 4));
        if (AICUPRVS.DATA.TX_DT == 0) {
            AICUPRVS.DATA.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, AICUPRVS.DATA.TX_DT);
        if (AICUPRVS.DATA.FUNC == 'C') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (!JIBS_tmp_str[0].equalsIgnoreCase("0015527") 
                || !AICUPRVS.DATA.TR_CODE.equalsIgnoreCase("0015527")) {
                B010_CHECK_INPUT();
                if (pgmRtn) return;
                B020_GET_REV_TYPE();
                if (pgmRtn) return;
                B030_GET_PROC_TYPE();
                if (pgmRtn) return;
                B040_OTHER_CHECK_PROC();
                if (pgmRtn) return;
            }
        } else if (AICUPRVS.DATA.FUNC == 'G') {
            R000_GET_SUS_AC();
            if (pgmRtn) return;
        } else if (AICUPRVS.DATA.FUNC == 'P') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (JIBS_tmp_str[0].equalsIgnoreCase("0015527") 
                || AICUPRVS.DATA.TR_CODE.equalsIgnoreCase("0015527")) {
                B070_CANCEL_RVS_NO();
                if (pgmRtn) return;
            } else {
                B020_GET_REV_PROC();
                if (pgmRtn) return;
                B030_GET_PROC_TYPE();
                if (pgmRtn) return;
                B050_REV_PROC();
                if (pgmRtn) return;
            }
        } else if (AICUPRVS.DATA.FUNC == 'A') {
            B060_RETURN_RVS_NO();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + AICUPRVS.DATA.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICUPRVS.DATA.CHNL_NO);
        CEP.TRC(SCCGWA, AICUPRVS.DATA.RVS_NO);
        CEP.TRC(SCCGWA, AICUPRVS.DATA.GL_BOOK);
        if (AICUPRVS.DATA.GL_BOOK.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BOOK_FLG_MUST_INPUT, AICUPRVS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICUPRVS.DATA.AC);
        if (AICUPRVS.DATA.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_MUST_IPT_AC, AICUPRVS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICUPRVS.DATA.CCY);
        if (AICUPRVS.DATA.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_CUR_MUST_INPUT, AICUPRVS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICUPRVS.DATA.SIGN);
        if (AICUPRVS.DATA.SIGN != 'D' 
            && AICUPRVS.DATA.SIGN != 'C') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_DCFLG_NOT_VAL, AICUPRVS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_REV_TYPE() throws IOException,SQLException,Exception {
        R000_GET_IA_INFO();
        if (pgmRtn) return;
    }
    public void B020_GET_REV_PROC() throws IOException,SQLException,Exception {
        if (AICUPRVS.DATA.SUS_FLG == 'Y') {
            R000_GET_SUS_AC();
            if (pgmRtn) return;
        } else {
            R000_GET_IA_INFO();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_PROC_TYPE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICUPRVS.DATA.RED_FLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        CEP.TRC(SCCGWA, AICUPRVS.DATA.SIGN);
        CEP.TRC(SCCGWA, WS_RVS_TYP);
        if (WS_RVS_TYP == 'N') {
            AICUPRVS.DATA.RVS_NO = " ";
            AICUPRVS.DATA.RVS_SEQ = 0;
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (WS_RVS_KND != '1' 
                && WS_RVS_KND != '2' 
                && WS_RVS_KND != '3' 
                && WS_RVS_KND != '4') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_IPT_FUNC_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && AICUPRVS.DATA.SUS_FLG != 'Y') {
                CEP.TRC(SCCGWA, "TEST CANCEL BY DJD");
                CEP.TRC(SCCGWA, AICUPRVS.DATA.CNTR_TYPE);
                CEP.TRC(SCCGWA, AICUPRVS.DATA.EVENT_TYPE);
                WS_DRCR_FLG = AICUPRVS.DATA.SIGN;
                if (WS_DRCR_FLG == WS_RVS_TYP) {
                    if (AICUPRVS.DATA.RED_FLG == 'R') {
                        WS_PROC_TYPE = '3';
                    } else {
                        if (AICUPRVS.DATA.CNTR_TYPE.equalsIgnoreCase("CAS") 
                            && (AICUPRVS.DATA.EVENT_TYPE.equalsIgnoreCase("CR") 
                            || AICUPRVS.DATA.EVENT_TYPE.equalsIgnoreCase("DR"))) {
                            WS_PROC_TYPE = '1';
                        } else {
                            WS_PROC_TYPE = '4';
                        }
                    }
                } else {
                    if (AICUPRVS.DATA.RED_FLG == 'R') {
                        WS_PROC_TYPE = '4';
                    } else {
                        WS_PROC_TYPE = '3';
                    }
                }
                JIBS_tmp_str[0] = "" + GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                if (JIBS_tmp_str[0].substring(0, 4).compareTo(JIBS_tmp_str[1].substring(0, 4)) < 0 
                    && !(AICUPRVS.DATA.CNTR_TYPE.equalsIgnoreCase("CAS") 
                    && (AICUPRVS.DATA.EVENT_TYPE.equalsIgnoreCase("CR") 
                    || AICUPRVS.DATA.EVENT_TYPE.equalsIgnoreCase("DR")))) {
                    if (WS_DRCR_FLG == WS_RVS_TYP) {
                        WS_PROC_TYPE = '4';
                    } else {
                        WS_PROC_TYPE = '3';
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "TEST NOT CANCEL BY DJD");
                if (AICUPRVS.DATA.RED_FLG == 'R') {
                    if (AICUPRVS.DATA.SIGN == 'D') {
                        WS_DRCR_FLG = 'C';
                    } else {
                        WS_DRCR_FLG = 'D';
                    }
                } else {
                    WS_DRCR_FLG = AICUPRVS.DATA.SIGN;
                }
                CEP.TRC(SCCGWA, WS_DRCR_FLG);
                CEP.TRC(SCCGWA, WS_RVS_TYP);
                if (WS_DRCR_FLG == WS_RVS_TYP) {
                    WS_PROC_TYPE = '1';
                } else {
                    WS_PROC_TYPE = '2';
                }
            }
        }
        if (AICUPRVS.DATA.RED_FLG == 'R') {
            WS_RVS_AMT = AICUPRVS.DATA.AMT * ( -1 );
        } else {
            WS_RVS_AMT = AICUPRVS.DATA.AMT;
        }
        CEP.TRC(SCCGWA, WS_RVS_AMT);
        if (AICUPRVS.DATA.SUS_FLG == 'Y') {
            WS_PROC_TYPE = '1';
            CEP.TRC(SCCGWA, AICUPRVS.DATA.SIGN);
            CEP.TRC(SCCGWA, WS_RVS_TYP);
            CEP.TRC(SCCGWA, AICUPRVS.DATA.RED_FLG);
            if (AICUPRVS.DATA.RED_FLG == 'R') {
                if (WS_RVS_TYP == AICUPRVS.DATA.SIGN) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.ERR_RVS_NEG_AMT_DIR_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (WS_RVS_TYP != AICUPRVS.DATA.SIGN) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.ERR_RVS_POS_AMT_DIR_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B040_OTHER_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PROC_TYPE);
        if (WS_PROC_TYPE == '1') {
            R000_CHECK_GRVS();
            if (pgmRtn) return;
        } else if (WS_PROC_TYPE == '2') {
            R000_CHECK_CRVS();
            if (pgmRtn) return;
        } else if (WS_PROC_TYPE == '3') {
            R000_CHECK_GRVS_C();
            if (pgmRtn) return;
        } else if (WS_PROC_TYPE == '4') {
            R000_CHECK_CRVS_C();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + WS_PROC_TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B050_REV_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PROC_TYPE);
        if (WS_PROC_TYPE == '1') {
            R000_GRVS_PROC();
            if (pgmRtn) return;
        } else if (WS_PROC_TYPE == '2') {
            R000_CRVS_PROC();
            if (pgmRtn) return;
        } else if (WS_PROC_TYPE == '3') {
            R000_GRVS_C_PROC();
            if (pgmRtn) return;
        } else if (WS_PROC_TYPE == '4') {
            R000_CRVS_C_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + WS_PROC_TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_RETURN_RVS_NO() throws IOException,SQLException,Exception {
        WS_IA_BR = AICUPRVS.DATA.BR;
        CEP.TRC(SCCGWA, WS_IA_BR);
        R000_GEN_RVS_NO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AICRGINF);
        AICRGINF.INFO.FUNC = 'C';
        AIRGINF.KEY.RVS_NO = IBS.CLS2CPY(SCCGWA, WS_RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        AIRGINF.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRGINF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, AICUPRVS.DATA.RVS_EXP);
        WS_CAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        R000_CAL_EXP_DT();
        if (pgmRtn) return;
        AIRGINF.RVS_EXP = WS_RVS_EXP_DT;
        if (AICUPRVS.DATA.RVS_EXP != 0) {
            if (AICUPRVS.DATA.RVS_EXP <= WS_RVS_EXP_DT) {
                AIRGINF.RVS_EXP = AICUPRVS.DATA.RVS_EXP;
            }
        }
        CEP.TRC(SCCGWA, AIRGINF.RVS_EXP);
        S000_CALL_AIZRGINF();
        if (pgmRtn) return;
        AICUPRVS.DATA.RVS_NO = AIRGINF.KEY.RVS_NO;
    }
    public void B070_CANCEL_RVS_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AICRGINF);
        AICRGINF.INFO.FUNC = 'R';
        AIRGINF.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
        S000_CALL_AIZRGINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRGINF.STS);
        if (AIRGINF.STS == 'C') {
            B071_CANCEL_CRVS_INFO();
            if (pgmRtn) return;
        } else if (AIRGINF.STS == 'N') {
            B072_CANCEL_GRVS_INFO();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_GRVS_STS_WRONG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B071_CANCEL_CRVS_INFO() throws IOException,SQLException,Exception {
        AICRGINF.INFO.FUNC = 'U';
        AIRGINF.STS = 'N';
        AIRGINF.G_AMT = AICUPRVS.DATA.AMT;
        AIRGINF.G_BAL = AICUPRVS.DATA.AMT;
        AIRGINF.C_AMT = 0;
        WS_C_MAX_NO = AIRGINF.C_MAX_NO;
        AIRGINF.C_MAX_NO = 0;
        AIRGINF.LST_TR_DT = AICUPRVS.DATA.TX_DT;
        AIRGINF.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRGINF.UPDTBL_DATE = AICUPRVS.DATA.TX_DT;
        S000_CALL_AIZRGINF();
        if (pgmRtn) return;
        AIRCRVS.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
        for (WS_I = 1; WS_I <= WS_C_MAX_NO; WS_I += 1) {
            IBS.init(SCCGWA, AIRCRVS);
            IBS.init(SCCGWA, AICRCRVS);
            AICRCRVS.INFO.FUNC = 'Q';
            AIRCRVS.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
            AIRCRVS.KEY.RVS_SEQ = WS_I;
            CEP.TRC(SCCGWA, AICUPRVS.DATA.RVS_SEQ);
            S000_CALL_AIZRCRVS();
            if (pgmRtn) return;
            if (AICRCRVS.RETURN_INFO == 'F') {
                AICRCRVS.INFO.FUNC = 'R';
                S000_CALL_AIZRCRVS();
                if (pgmRtn) return;
                AIRCRVS.STS = 'R';
                AIRCRVS.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
                AIRCRVS.UPDTBL_DATE = AICUPRVS.DATA.TX_DT;
                AICRCRVS.INFO.FUNC = 'U';
                S000_CALL_AIZRCRVS();
                if (pgmRtn) return;
            }
        }
        AICUPRVS.DATA.RVS_NO = AIRCRVS.KEY.RVS_NO;
    }
    public void B072_CANCEL_GRVS_INFO() throws IOException,SQLException,Exception {
        AICRGINF.INFO.FUNC = 'U';
        AIRGINF.STS = 'R';
        AIRGINF.G_AMT = 0;
        AIRGINF.G_BAL = 0;
        AIRGINF.LST_TR_DT = AICUPRVS.DATA.TX_DT;
        AIRGINF.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRGINF.UPDTBL_DATE = AICUPRVS.DATA.TX_DT;
        S000_CALL_AIZRGINF();
        if (pgmRtn) return;
        for (WS_J = 1; WS_J <= AIRGINF.G_MAX_NO; WS_J += 1) {
            IBS.init(SCCGWA, AIRGRVS);
            IBS.init(SCCGWA, AICRGRVS);
            AICRGRVS.INFO.FUNC = 'Q';
            AIRGRVS.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
            AIRGRVS.KEY.RVS_SEQ = WS_J;
            S000_CALL_AIZRGRVS();
            if (pgmRtn) return;
            if (AICRGRVS.RETURN_INFO == 'F') {
                AICRGRVS.INFO.FUNC = 'R';
                S000_CALL_AIZRGRVS();
                if (pgmRtn) return;
                AIRGRVS.STS = 'R';
                AIRGRVS.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
                AIRGRVS.UPDTBL_DATE = AICUPRVS.DATA.TX_DT;
                AICRGRVS.INFO.FUNC = 'U';
                S000_CALL_AIZRGRVS();
                if (pgmRtn) return;
            }
        }
        AICUPRVS.DATA.RVS_NO = AIRGRVS.KEY.RVS_NO;
    }
    public void R000_CHECK_GRVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RVS_KND);
        CEP.TRC(SCCGWA, AICUPRVS.DATA.RVS_NO);
        CEP.TRC(SCCGWA, AICUPRVS.DATA.RETURN_FLG);
        CEP.TRC(SCCGWA, "1G");
        if ((WS_RVS_KND == '1' 
            || WS_RVS_KND == '2') 
            && AICUPRVS.DATA.RVS_NO.trim().length() > 0) {
            if (AICUPRVS.DATA.RETURN_FLG != 'Y') {
                AICUPRVS.DATA.RVS_NO = " ";
            }
            if (AICUPRVS.DATA.RETURN_FLG == 'Y') {
                IBS.init(SCCGWA, AIRGINF);
                IBS.init(SCCGWA, AICRGINF);
                AICRGINF.INFO.FUNC = 'Q';
                AIRGINF.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                if (AICRGINF.RETURN_INFO == 'N') {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.CREAT_RVS_DEFULT, AICUPRVS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, "DG");
        if ((WS_RVS_KND == '3' 
            || WS_RVS_KND == '4')) {
            if (AICUPRVS.DATA.RVS_NO.trim().length() > 0) {
                IBS.init(SCCGWA, AIRGINF);
                IBS.init(SCCGWA, AICRGINF);
                AICRGINF.INFO.FUNC = 'Q';
                AIRGINF.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                if (AICRGINF.RETURN_INFO == 'F') {
                    if (!AICUPRVS.DATA.AC.equalsIgnoreCase(AIRGINF.AC) 
                        && AICUPRVS.DATA.RETURN_FLG != 'Y') {
                        AICUPRVS.DATA.RVS_NO = " ";
                    } else {
                        if (AIRGINF.RVS_EXP < AICUPRVS.DATA.TX_DT 
                            && AIRGINF.RVS_EXP != 0) {
                            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_HAVE_EXP, AICUPRVS.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        }
                        WS_WRITEOFF_FLG = 'N';
                        IBS.init(SCCGWA, AIRCRVS);
                        IBS.init(SCCGWA, AICRCRVS);
                        AICRCRVS.INFO.FUNC = 'B';
                        AICRCRVS.INFO.OPT = '1';
                        AIRCRVS.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
                        AIRCRVS.STS = 'N';
                        S000_CALL_AIZRCRVS();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_SEQ);
                        if (AICRCRVS.RETURN_INFO == 'F') {
                            WS_WRITEOFF_FLG = 'Y';
                        }
                        CEP.TRC(SCCGWA, WS_WRITEOFF_FLG);
                        if (AIRGINF.C_MAX_NO > 0 
                            && WS_WRITEOFF_FLG == 'Y') {
                            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_WRITEOFF_HAPPENED, AICUPRVS.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        }
                        if (AIRGINF.RVS_KND == '1' 
                            || AIRGINF.RVS_KND == '2') {
                            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.GRVS_NO_NOT_ALLOW_USED, AICUPRVS.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        }
                    }
                } else {
                    AICUPRVS.DATA.RVS_NO = " ";
                }
            }
            if (AICUPRVS.DATA.RVS_NO.trim().length() == 0) {
                WS_GENNO_FLG = 'Y';
            }
        }
    }
    public void R000_CHECK_CRVS() throws IOException,SQLException,Exception {
        if (AICUPRVS.DATA.RVS_NO.trim().length() == 0) {
            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BSP") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVSNO_MUST_IPT, AICUPRVS.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_RVSNO_MUST_IPT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, WS_OPR_BR);
            CEP.TRC(SCCGWA, AICUPRVS.DATA.BR);
            IBS.init(SCCGWA, AIRGINF);
            IBS.init(SCCGWA, AICRGINF);
            AICRGINF.INFO.FUNC = 'Q';
            AIRGINF.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
            S000_CALL_AIZRGINF();
            if (pgmRtn) return;
            if (AICRGINF.RETURN_INFO == 'F') {
                CEP.TRC(SCCGWA, AIRGINF.CCY);
                CEP.TRC(SCCGWA, AICUPRVS.DATA.CCY);
                CEP.TRC(SCCGWA, AIRGINF.AC);
                CEP.TRC(SCCGWA, AICUPRVS.DATA.AC);
                if (!AIRGINF.CCY.equalsIgnoreCase(AICUPRVS.DATA.CCY)) {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.CRVS_CCY_NOT_EQ_GRVS, AICUPRVS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (AIRGINF.STS == 'R' 
                    || AIRGINF.STS == 'C') {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.GINF_STS_ERROR, AICUPRVS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (!AIRGINF.AC.equalsIgnoreCase(AICUPRVS.DATA.AC)) {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_NO_ERROR, AICUPRVS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (AIRGINF.RVS_KND == '1' 
                    || AIRGINF.RVS_KND == '3') {
                    CEP.TRC(SCCGWA, "CHECK AMT");
                    if (AIRGINF.G_AMT != WS_RVS_AMT 
                        || AIRGINF.G_BAL != WS_RVS_AMT) {
                        CEP.TRC(SCCGWA, "AMT NOT RIGHT");
                        IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_AMT_NOT_RIGHT, AICUPRVS.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                } else {
                    if (AIRGINF.G_BAL < WS_RVS_AMT) {
                        CEP.TRC(SCCGWA, "AMT NOT RIGHT2");
                        IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_AMT_NOT_RIGHT, AICUPRVS.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            } else {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_NOT_EXIST, AICUPRVS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_GRVS_C() throws IOException,SQLException,Exception {
        if (AICUPRVS.DATA.RVS_NO.trim().length() == 0 
            || AICUPRVS.DATA.RVS_SEQ == 0) {
            if (AICUPRVS.DATA.RVS_NO.trim().length() == 0) {
                if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BSP") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVSNO_MUST_IPT, AICUPRVS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_RVSNO_MUST_IPT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (AICUPRVS.DATA.RVS_SEQ == 0) {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_SEQ_MUST_IPT, AICUPRVS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, AIRGRVS);
            IBS.init(SCCGWA, AICRGRVS);
            AICRGRVS.INFO.FUNC = 'Q';
            AIRGRVS.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
            AIRGRVS.KEY.RVS_SEQ = AICUPRVS.DATA.RVS_SEQ;
            S000_CALL_AIZRGRVS();
            if (pgmRtn) return;
            if (AICRGRVS.RETURN_INFO == 'F') {
                if (AIRGRVS.STS == 'R') {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_STS_NOT_NORMAL, AICUPRVS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (AIRGRVS.AMT != WS_RVS_AMT) {
                    CEP.TRC(SCCGWA, "1");
                    CEP.TRC(SCCGWA, AIRGRVS.AMT);
                    CEP.TRC(SCCGWA, WS_RVS_AMT);
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_CANCEL_AMT_NOT_RIGHT, AICUPRVS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_NOT_EXIST, AICUPRVS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, AIRGINF);
            IBS.init(SCCGWA, AICRGINF);
            AICRGINF.INFO.FUNC = 'Q';
            AIRGINF.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
            S000_CALL_AIZRGINF();
            if (pgmRtn) return;
            if (AICRGINF.RETURN_INFO == 'F') {
                if (AIRGINF.G_BAL < WS_RVS_AMT) {
                    CEP.TRC(SCCGWA, "2");
                    CEP.TRC(SCCGWA, AIRGRVS.AMT);
                    CEP.TRC(SCCGWA, WS_RVS_AMT);
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.GINF_AMT_LT_CANCEL_AMT, AICUPRVS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_NOT_EXIST, AICUPRVS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_CRVS_C() throws IOException,SQLException,Exception {
        if (AICUPRVS.DATA.RVS_NO.trim().length() == 0 
            || AICUPRVS.DATA.RVS_SEQ == 0) {
            if (AICUPRVS.DATA.RVS_NO.trim().length() == 0) {
                if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BSP") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVSNO_MUST_IPT, AICUPRVS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_RVSNO_MUST_IPT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (AICUPRVS.DATA.RVS_SEQ == 0) {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_SEQ_MUST_IPT, AICUPRVS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, AIRCRVS);
            IBS.init(SCCGWA, AICRCRVS);
            AICRCRVS.INFO.FUNC = 'Q';
            AIRCRVS.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
            AIRCRVS.KEY.RVS_SEQ = AICUPRVS.DATA.RVS_SEQ;
            S000_CALL_AIZRCRVS();
            if (pgmRtn) return;
            if (AICRCRVS.RETURN_INFO == 'F') {
                if (AIRCRVS.STS == 'R') {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_STS_NOT_NORMAL, AICUPRVS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (AIRCRVS.AMT != WS_RVS_AMT) {
                    CEP.TRC(SCCGWA, "3");
                    CEP.TRC(SCCGWA, AIRGRVS.AMT);
                    CEP.TRC(SCCGWA, WS_RVS_AMT);
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_CANCEL_AMT_NOT_RIGHT, AICUPRVS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_NOT_EXIST, AICUPRVS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GET_SUS_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, AIRPAI7);
        AIRPAI7.KEY.TYP = "PAI07";
        AIRPAI7.KEY.REDEFINES6.GL_BOOK = AICUPRVS.DATA.GL_BOOK;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.AC_TYP = "1";
        AIRPAI7.KEY.REDEFINES6.BUSI_KND = AICUPRVS.DATA.APP;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        CEP.TRC(SCCGWA, AIRPAI7.KEY.REDEFINES6.GL_BOOK);
        CEP.TRC(SCCGWA, AIRPAI7.KEY.REDEFINES6.BUSI_KND);
        BPCPRMR.DAT_PTR = AIRPAI7;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_PROC_TYPE = '1';
        R000_GET_ITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C);
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C);
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.ITM_M_C);
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_C);
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.ITM_R_D);
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_D);
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.ITM_M_D);
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_D);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND)) {
            if (AICPQITM.INPUT_DATA.NO.trim().length() > 0) {
                if (AICPQITM.INPUT_DATA.NO == null) AICPQITM.INPUT_DATA.NO = "";
                JIBS_tmp_int = AICPQITM.INPUT_DATA.NO.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) AICPQITM.INPUT_DATA.NO += " ";
                if (AICPQITM.INPUT_DATA.NO.substring(0, 1).equalsIgnoreCase("8")) {
                    WS_RM_FLG = 'M';
                } else {
                    WS_RM_FLG = 'R';
                }
                CEP.TRC(SCCGWA, WS_RM_FLG);
            } else {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND;
                WS_ERR_INFO = "ITM = EMPTY";
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICUPRVS.DATA.RED_FLG == 'R') {
            if (AICUPRVS.DATA.SIGN == 'D') {
                if (AICPQITM.OUTPUT_DATA.TYPE.trim().length() == 0 
                    && WS_RM_FLG != ' ') {
                    CEP.TRC(SCCGWA, WS_RM_FLG);
                    if (WS_RM_FLG == 'R') {
                        WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C;
                        WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C;
                    } else {
                        if (WS_RM_FLG == 'M') {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_M_C;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_C;
                        }
                    }
                } else {
                    if (AICPQITM.OUTPUT_DATA.TYPE.trim().length() > 0 
                        && WS_RM_FLG == ' ') {
                        if (!AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8")) {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C;
                        } else {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_M_C;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_C;
                        }
                    } else {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_8060;
                        WS_ERR_INFO = "ITM TYPE = EMPTY";
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            } else {
                if (AICPQITM.OUTPUT_DATA.TYPE.trim().length() == 0 
                    && WS_RM_FLG != ' ') {
                    CEP.TRC(SCCGWA, WS_RM_FLG);
                    if (WS_RM_FLG == 'R') {
                        WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_D;
                        WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_D;
                    } else {
                        if (WS_RM_FLG == 'M') {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_M_D;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_D;
                        }
                    }
                } else {
                    if (AICPQITM.OUTPUT_DATA.TYPE.trim().length() > 0 
                        && WS_RM_FLG == ' ') {
                        if (!AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8")) {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_D;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_D;
                        } else {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_M_D;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_D;
                        }
                    } else {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_8060;
                        WS_ERR_INFO = "ITM TYPE = EMPTY";
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            if (AICUPRVS.DATA.SIGN == 'D') {
                if (AICPQITM.OUTPUT_DATA.TYPE.trim().length() == 0 
                    && WS_RM_FLG != ' ') {
                    CEP.TRC(SCCGWA, WS_RM_FLG);
                    if (WS_RM_FLG == 'R') {
                        WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_D;
                        WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_D;
                    } else {
                        if (WS_RM_FLG == 'M') {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_M_D;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_D;
                        }
                    }
                } else {
                    if (AICPQITM.OUTPUT_DATA.TYPE.trim().length() > 0 
                        && WS_RM_FLG == ' ') {
                        if (!AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8")) {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_D;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_D;
                        } else {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_M_D;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_D;
                        }
                    } else {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_8060;
                        WS_ERR_INFO = "ITM TYPE = EMPTY";
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            } else {
                if (AICPQITM.OUTPUT_DATA.TYPE.trim().length() == 0 
                    && WS_RM_FLG != ' ') {
                    CEP.TRC(SCCGWA, WS_RM_FLG);
                    if (WS_RM_FLG == 'R') {
                        WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C;
                        WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C;
                    } else {
                        if (WS_RM_FLG == 'M') {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_M_C;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_C;
                        }
                    }
                } else {
                    if (AICPQITM.OUTPUT_DATA.TYPE.trim().length() > 0 
                        && WS_RM_FLG == ' ') {
                        if (!AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8")) {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C;
                        } else {
                            WS_IA_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_M_C;
                            WS_IA_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_C;
                        }
                    } else {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_8060;
                        WS_ERR_INFO = "ITM TYPE = EMPTY";
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_IA_ITM);
        CEP.TRC(SCCGWA, WS_IA_SEQ);
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.GL_BOOK = AICUPRVS.DATA.GL_BOOK;
        AICPQMIB.INPUT_DATA.BR = AICUPRVS.DATA.BR;
        AICPQMIB.INPUT_DATA.ITM_NO = WS_IA_ITM;
        AICPQMIB.INPUT_DATA.SEQ = WS_IA_SEQ;
        AICPQMIB.INPUT_DATA.CCY = AICUPRVS.DATA.CCY;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQMIB.RC.RC_CODE);
        if (AICPQMIB.RC.RC_CODE == 0) {
            WS_IA_AC = AICPQMIB.INPUT_DATA.AC;
            WS_RVS_TYP = AICPQMIB.OUTPUT_DATA.RVS_TYP;
            WS_RVS_KND = AICPQMIB.OUTPUT_DATA.RVS_KND;
            WS_RVS_EXP = AICPQMIB.OUTPUT_DATA.RVS_EXP;
            WS_RVS_UNIT = AICPQMIB.OUTPUT_DATA.RVS_UNIT;
            WS_IA_BR = AICPQMIB.INPUT_DATA.BR;
            CEP.TRC(SCCGWA, WS_IA_BR);
            WS_IA_ITM = AICPQMIB.INPUT_DATA.ITM_NO;
            WS_IA_SEQ = AICPQMIB.INPUT_DATA.SEQ;
            if (WS_RVS_TYP == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.THE_ERR_AC_CANNOT_RVS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_IA_BR = AICPQMIB.INPUT_DATA.BR;
            CEP.TRC(SCCGWA, WS_IA_BR);
            WS_IA_ITM = AICPQMIB.INPUT_DATA.ITM_NO;
            WS_IA_SEQ = AICPQMIB.INPUT_DATA.SEQ;
            WS_INTERNAL_AC.WS_INTERNAL_BR = WS_IA_BR;
            WS_INTERNAL_AC.WS_INTERNAL_ITM = WS_IA_ITM;
            WS_INTERNAL_AC.WS_INTERNAL_SEQ = WS_IA_SEQ;
            WS_INTERNAL_AC.WS_INTERNAL_CCY = AICPQMIB.INPUT_DATA.CCY;
            WS_IA_AC = IBS.CLS2CPY(SCCGWA, WS_INTERNAL_AC);
        }
        CEP.TRC(SCCGWA, AICUPRVS.DATA.FUNC);
        CEP.TRC(SCCGWA, AICUPRVS.DATA.ITM);
        CEP.TRC(SCCGWA, "*GET-SUS-AC");
        CEP.TRC(SCCGWA, WS_IA_AC);
        CEP.TRC(SCCGWA, WS_IA_BR);
        CEP.TRC(SCCGWA, WS_IA_ITM);
        CEP.TRC(SCCGWA, WS_IA_SEQ);
        AICUPRVS.DATA.SUS_INFO.SUS_AC = WS_IA_AC;
        AICUPRVS.DATA.SUS_INFO.SUS_BR = WS_IA_BR;
        AICUPRVS.DATA.SUS_INFO.SUS_ITM = WS_IA_ITM;
        AICUPRVS.DATA.SUS_INFO.SUS_SEQ = WS_IA_SEQ;
        AICUPRVS.DATA.RVS_NO = " ";
    }
    public void R000_GET_ITM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICUPRVS.DATA.AC);
        CEP.TRC(SCCGWA, AICUPRVS.DATA.GL_BOOK);
        CEP.TRC(SCCGWA, AICUPRVS.DATA.ITM);
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.BOOK_FLG = AICUPRVS.DATA.GL_BOOK;
        if (AICUPRVS.DATA.AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, "III");
            IBS.CPY2CLS(SCCGWA, AICUPRVS.DATA.AC, WS_INTERNAL_AC);
            AICPQITM.INPUT_DATA.NO = WS_INTERNAL_AC.WS_INTERNAL_ITM;
            WS_IA_ITM_S = WS_INTERNAL_AC.WS_INTERNAL_ITM;
            WS_IA_BR_S = WS_INTERNAL_AC.WS_INTERNAL_BR;
            WS_IA_SEQ_S = WS_INTERNAL_AC.WS_INTERNAL_SEQ;
        } else {
            if (AICUPRVS.DATA.ITM.trim().length() > 0) {
                AICPQITM.INPUT_DATA.NO = AICUPRVS.DATA.ITM;
                WS_IA_ITM_S = AICUPRVS.DATA.ITM;
                WS_IA_BR_S = WS_INTERNAL_AC.WS_INTERNAL_BR;
            } else {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITMP_GL_ITEM_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, AICPQITM.INPUT_DATA.BOOK_FLG);
        CEP.TRC(SCCGWA, AICPQITM.INPUT_DATA.NO);
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.STS);
        if (AICPQITM.OUTPUT_DATA.STS == 'C' 
            || AICPQITM.OUTPUT_DATA.STS == 'H') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_STS_WRONG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_RVS_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AICRGINF);
        WS_GENNO_FLG = 'N';
        AICRGINF.INFO.FUNC = 'B';
        AICRGINF.INFO.OPT = 'F';
        AIRGINF.AC = WS_IA_AC;
        AIRGINF.BOOK_FLG = AICUPRVS.DATA.GL_BOOK;
        AIRGINF.CCY = AICUPRVS.DATA.CCY;
        CEP.TRC(SCCGWA, AIRGINF.AC);
        CEP.TRC(SCCGWA, AIRGINF.CCY);
        CEP.TRC(SCCGWA, AIRGINF.BOOK_FLG);
        S000_CALL_AIZRGINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        if (AICRGINF.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "NORMAL");
            AICUPRVS.DATA.RVS_NO = AIRGINF.KEY.RVS_NO;
            if (AIRGINF.STS == 'C' 
                || AIRGINF.STS == 'P') {
                WS_GENNO_FLG = 'Y';
            } else {
                WS_GENNO_FLG = 'N';
            }
        } else {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GENNO_FLG = 'Y';
        }
    }
    public void R000_GET_IA_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICUPRVS.DATA.GL_BOOK);
        CEP.TRC(SCCGWA, AICUPRVS.DATA.AC);
        CEP.TRC(SCCGWA, AICUPRVS.DATA.CCY);
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.GL_BOOK = AICUPRVS.DATA.GL_BOOK;
        AICPQMIB.INPUT_DATA.AC = AICUPRVS.DATA.AC;
        AICPQMIB.INPUT_DATA.CCY = AICUPRVS.DATA.CCY;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQMIB.RC.RC_CODE);
        if (AICPQMIB.RC.RC_CODE == 0) {
            CEP.TRC(SCCGWA, "FND");
            WS_IA_AC = AICUPRVS.DATA.AC;
            WS_RVS_TYP = AICPQMIB.OUTPUT_DATA.RVS_TYP;
            WS_RVS_KND = AICPQMIB.OUTPUT_DATA.RVS_KND;
            WS_RVS_EXP = AICPQMIB.OUTPUT_DATA.RVS_EXP;
            WS_RVS_UNIT = AICPQMIB.OUTPUT_DATA.RVS_UNIT;
            WS_IA_BR = AICPQMIB.INPUT_DATA.BR;
            CEP.TRC(SCCGWA, WS_IA_BR);
            WS_IA_ITM = AICPQMIB.INPUT_DATA.ITM_NO;
            WS_IA_SEQ = AICPQMIB.INPUT_DATA.SEQ;
            WS_IA_BR_S = AICPQMIB.INPUT_DATA.BR;
            WS_IA_ITM_S = AICPQMIB.INPUT_DATA.ITM_NO;
            WS_IA_SEQ_S = AICPQMIB.INPUT_DATA.SEQ;
            WS_AC_EXP = AICPQMIB.OUTPUT_DATA.AC_EXP;
            CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.AC_EXP);
        } else {
            IBS.CPY2CLS(SCCGWA, AICUPRVS.DATA.AC, WS_INTERNAL_AC);
            WS_IA_AC = AICUPRVS.DATA.AC;
            WS_IA_BR = WS_INTERNAL_AC.WS_INTERNAL_BR;
            WS_IA_BR_S = WS_INTERNAL_AC.WS_INTERNAL_BR;
            CEP.TRC(SCCGWA, WS_IA_BR);
            WS_IA_ITM = WS_INTERNAL_AC.WS_INTERNAL_ITM;
            WS_IA_ITM_S = WS_INTERNAL_AC.WS_INTERNAL_ITM;
            WS_IA_SEQ = WS_INTERNAL_AC.WS_INTERNAL_SEQ;
            WS_IA_SEQ_S = WS_INTERNAL_AC.WS_INTERNAL_SEQ;
            IBS.init(SCCGWA, AICPCMIB);
            IBS.init(SCCGWA, AIRCMIB);
            AIRCMIB.KEY.GL_BOOK = AICUPRVS.DATA.GL_BOOK;
            AIRCMIB.KEY.BR = WS_IA_BR;
            AIRCMIB.KEY.ITM = WS_IA_ITM;
            AIRCMIB.KEY.SEQ = WS_IA_SEQ;
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            if (AICPCMIB.RETURN_INFO == 'F' 
                && AIRCMIB.KEY.BR != 999999 
                && AIRCMIB.STS != 'N') {
                IBS.init(SCCGWA, AICPCMIB);
                IBS.init(SCCGWA, AIRCMIB);
                AIRCMIB.KEY.GL_BOOK = AICUPRVS.DATA.GL_BOOK;
                AIRCMIB.KEY.BR = 999999;
                AIRCMIB.KEY.ITM = WS_IA_ITM;
                AIRCMIB.KEY.SEQ = WS_IA_SEQ;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
            }
            if (AICPCMIB.RETURN_INFO == 'N') {
                IBS.init(SCCGWA, AICPCMIB);
                IBS.init(SCCGWA, AIRCMIB);
                AIRCMIB.KEY.GL_BOOK = AICUPRVS.DATA.GL_BOOK;
                AIRCMIB.KEY.BR = WS_IA_BR;
                AIRCMIB.KEY.ITM = WS_IA_ITM;
                AIRCMIB.KEY.SEQ = 999999;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
            }
            if (AICPCMIB.RETURN_INFO == 'F') {
                CEP.TRC(SCCGWA, AIRCMIB.RVS_TYP);
                WS_IA_AC = AICUPRVS.DATA.AC;
                WS_RVS_TYP = AIRCMIB.RVS_TYP;
                WS_RVS_KND = AIRCMIB.RVS_KND;
                WS_RVS_EXP = AIRCMIB.RVS_EXP;
                WS_RVS_UNIT = AIRCMIB.RVS_UNIT;
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPCMIB.RC);
                if (AICUPRVS.DATA.FUNC == 'C') {
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, "NOTFND");
        }
        CEP.TRC(SCCGWA, WS_IA_AC);
        CEP.TRC(SCCGWA, WS_RVS_TYP);
        CEP.TRC(SCCGWA, WS_RVS_KND);
        CEP.TRC(SCCGWA, WS_RVS_EXP);
        CEP.TRC(SCCGWA, WS_RVS_UNIT);
        CEP.TRC(SCCGWA, WS_IA_BR);
        CEP.TRC(SCCGWA, WS_IA_ITM);
        CEP.TRC(SCCGWA, WS_IA_SEQ);
    }
    public void R000_GRVS_PROC() throws IOException,SQLException,Exception {
        WS_CAL_DATE = AICUPRVS.DATA.TX_DT;
        R000_CAL_EXP_DT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICUPRVS.DATA.RVS_NO);
        if (WS_RVS_KND == '3' 
            || WS_RVS_KND == '4') {
            CEP.TRC(SCCGWA, "DG");
            if (AICUPRVS.DATA.RVS_NO.trim().length() > 0) {
                IBS.init(SCCGWA, AIRGINF);
                IBS.init(SCCGWA, AICRGINF);
                AICRGINF.INFO.FUNC = 'Q';
                AIRGINF.KEY.RVS_NO = AICUPRVS.DATA.RVS_NO;
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_IA_AC);
                CEP.TRC(SCCGWA, AICUPRVS.DATA.AC);
                if (AICRGINF.RETURN_INFO == 'F' 
                    && !WS_IA_AC.equalsIgnoreCase(AIRGINF.AC) 
                    && AIRGINF.AC.trim().length() > 0 
                    && AICUPRVS.DATA.RETURN_FLG != 'Y') {
                    AICUPRVS.DATA.RVS_NO = " ";
                }
            }
            if (AICUPRVS.DATA.RVS_NO.trim().length() == 0) {
                WS_GENNO_FLG = 'Y';
            } else {
                CEP.TRC(SCCGWA, "GEN-N");
                WS_GENNO_FLG = 'N';
            }
        }
        if (WS_RVS_KND == '1' 
            || WS_RVS_KND == '2') {
            CEP.TRC(SCCGWA, "1G");
            CEP.TRC(SCCGWA, AICUPRVS.DATA.RVS_NO);
            if (AICUPRVS.DATA.RETURN_FLG != 'Y') {
                AICUPRVS.DATA.RVS_NO = " ";
            }
            if (AICUPRVS.DATA.RVS_NO.trim().length() == 0) {
                CEP.TRC(SCCGWA, "GEN-Y");
                WS_GENNO_FLG = 'Y';
            } else {
                CEP.TRC(SCCGWA, "GEN-N");
                WS_GENNO_FLG = 'N';
            }
        }
        CEP.TRC(SCCGWA, "DJDD");
        CEP.TRC(SCCGWA, AICUPRVS.DATA.RVS_NO);
        CEP.TRC(SCCGWA, WS_GENNO_FLG);
        if (WS_GENNO_FLG == 'Y') {
            R000_GEN_RVS_NO();
            if (pgmRtn) return;
            IBS.init(SCCGWA, AIRGINF);
            IBS.init(SCCGWA, AICRGINF);
            AICRGINF.INFO.FUNC = 'C';
            AIRGINF.KEY.RVS_NO = IBS.CLS2CPY(SCCGWA, WS_RVS_NO);
            AIRGINF.AC = WS_IA_AC;
            AIRGINF.BOOK_FLG = AICUPRVS.DATA.GL_BOOK;
            AIRGINF.BR = WS_IA_BR;
            AIRGINF.ITM = WS_IA_ITM;
            AIRGINF.SEQ = WS_IA_SEQ;
            AIRGINF.CCY = AICUPRVS.DATA.CCY;
            AIRGINF.C_MAX_NO = 0;
            WS_CRVS_MAXNO = AIRGINF.C_MAX_NO;
            AIRGINF.RVS_KND = WS_RVS_KND;
            if (AIRGINF.RVS_EXP == 0) {
                AIRGINF.RVS_EXP = WS_RVS_EXP_DT;
            }
            AIRGINF.RVS_BR = AICUPRVS.DATA.BR;
            AIRGINF.G_MAX_NO += 1;
            WS_GRVS_MAXNO = AIRGINF.G_MAX_NO;
            AIRGINF.G_AMT = WS_RVS_AMT;
            AIRGINF.C_AMT = 0;
            AIRGINF.G_BAL = WS_RVS_AMT;
            if (AICUPRVS.DATA.SUS_FLG == 'Y') {
                AIRGINF.ERR_FLG = 'Y';
            } else {
                AIRGINF.ERR_FLG = 'N';
            }
            CEP.TRC(SCCGWA, AICUPRVS.DATA.DESC);
            if (AICUPRVS.DATA.DESC.equalsIgnoreCase("NOT BAL SUSPENSE ENTRY")) {
                AIRGINF.ERR_FLG = 'Y';
            }
            AIRGINF.STS = 'N';
            AIRGINF.FST_G_DT = AICUPRVS.DATA.TX_DT;
            AIRGINF.LST_G_DT = AICUPRVS.DATA.TX_DT;
            AIRGINF.LST_TR_DT = AICUPRVS.DATA.TX_DT;
            AIRGINF.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AIRGINF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_AIZRGINF();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICUPRVS.DATA.RVS_NO, WS_RVS_NO);
            AICRGINF.INFO.FUNC = 'R';
            AIRGINF.KEY.RVS_NO = IBS.CLS2CPY(SCCGWA, WS_RVS_NO);
            S000_CALL_AIZRGINF();
            if (pgmRtn) return;
            if (AIRGINF.AC.trim().length() == 0) {
                AIRGINF.AC = WS_IA_AC;
                AIRGINF.BOOK_FLG = AICUPRVS.DATA.GL_BOOK;
                AIRGINF.BR = WS_IA_BR;
                AIRGINF.ITM = WS_IA_ITM;
                AIRGINF.SEQ = WS_IA_SEQ;
                AIRGINF.CCY = AICUPRVS.DATA.CCY;
                AIRGINF.C_MAX_NO = 0;
                WS_CRVS_MAXNO = AIRGINF.C_MAX_NO;
                AIRGINF.RVS_KND = WS_RVS_KND;
                if (AIRGINF.RVS_EXP == 0) {
                    AIRGINF.RVS_EXP = WS_RVS_EXP_DT;
                }
                AIRGINF.RVS_BR = AICUPRVS.DATA.BR;
            }
            AICRGINF.INFO.FUNC = 'U';
            WS_CRVS_MAXNO = AIRGINF.C_MAX_NO;
            if (AIRGINF.RVS_EXP == 0) {
                AIRGINF.RVS_EXP = WS_RVS_EXP_DT;
            }
            WS_TEMP_NO = IBS.CLS2CPY(SCCGWA, WS_RVS_NO);
            R000_GET_GRVS_MAXNO();
            if (pgmRtn) return;
            WS_GRVS_MAXNO += 1;
            AIRGINF.G_MAX_NO = WS_GRVS_MAXNO;
            AIRGINF.G_AMT += WS_RVS_AMT;
            AIRGINF.G_BAL += WS_RVS_AMT;
            AIRGINF.LST_G_DT = AICUPRVS.DATA.TX_DT;
            AIRGINF.LST_TR_DT = AICUPRVS.DATA.TX_DT;
            AIRGINF.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AIRGINF.UPDTBL_DATE = AICUPRVS.DATA.TX_DT;
            if (AICUPRVS.DATA.RETURN_FLG == 'Y') {
                AIRGINF.AC = WS_IA_AC;
                AIRGINF.BOOK_FLG = AICUPRVS.DATA.GL_BOOK;
                AIRGINF.BR = WS_IA_BR;
                AIRGINF.ITM = WS_IA_ITM;
                AIRGINF.SEQ = WS_IA_SEQ;
                AIRGINF.CCY = AICUPRVS.DATA.CCY;
                AIRGINF.RVS_KND = WS_RVS_KND;
                if (AIRGINF.RVS_EXP == 0) {
                    AIRGINF.RVS_EXP = WS_RVS_EXP_DT;
                }
                AIRGINF.RVS_BR = AICUPRVS.DATA.BR;
                AIRGINF.STS = 'N';
                AIRGINF.FST_G_DT = AICUPRVS.DATA.TX_DT;
            }
            S000_CALL_AIZRGINF();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "ADD GRVS");
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, AICRGRVS);
        AICRGRVS.INFO.FUNC = 'C';
        AIRGRVS.KEY.RVS_NO = AIRGINF.KEY.RVS_NO;
        AIRGRVS.KEY.RVS_SEQ = WS_GRVS_MAXNO;
        AIRGRVS.AC = WS_IA_AC;
        AIRGRVS.BOOK_FLG = AICUPRVS.DATA.GL_BOOK;
        AIRGRVS.BR = WS_IA_BR;
        AIRGRVS.ITM = WS_IA_ITM;
        AIRGRVS.SEQ = WS_IA_SEQ;
        AIRGRVS.CCY = AICUPRVS.DATA.CCY;
        AIRGRVS.TX_DT = AICUPRVS.DATA.TX_DT;
        AIRGRVS.VAL_DT = AICUPRVS.DATA.VAL_DT;
        AIRGRVS.AMT = WS_RVS_AMT;
        AIRGRVS.SIGN = WS_DRCR_FLG;
        AIRGRVS.STS = 'N';
        AIRGRVS.CI_NO = AICUPRVS.DATA.CI_NO;
        AIRGRVS.REF_NO = AICUPRVS.DATA.REF_NO;
        AIRGRVS.PART = AICUPRVS.DATA.PART;
        AIRGRVS.TR_CODE = AICUPRVS.DATA.TR_CODE;
        AIRGRVS.TR_BR = AICUPRVS.DATA.TR_BR;
        if (AICUPRVS.DATA.TR_TELLER.trim().length() == 0) {
            AIRGRVS.TR_TELLER = SCCGWA.COMM_AREA.TL_ID;
        } else {
            AIRGRVS.TR_TELLER = AICUPRVS.DATA.TR_TELLER;
        }
        AIRGRVS.SET_NO = AICUPRVS.DATA.SET_NO;
        AIRGRVS.SET_SEQ = (short) AICUPRVS.DATA.SET_SEQ;
        AIRGRVS.APP = AICUPRVS.DATA.APP;
        CEP.TRC(SCCGWA, AICUPRVS.DATA.PAY_MAN);
        CEP.TRC(SCCGWA, AICUPRVS.DATA.APP);
        if (!AICUPRVS.DATA.APP.equalsIgnoreCase("AI") 
            && AICUPRVS.DATA.PAY_MAN.trim().length() == 0) {
            CEP.TRC(SCCGWA, "PAY MAN MUST INPUT");
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_PAY_MAN_MUST_INPUT);
        }
        AIRGRVS.CHNL_NO = AICUPRVS.DATA.CHNL_NO;
        AIRGRVS.PAY_MAN = AICUPRVS.DATA.PAY_MAN;
        AIRGRVS.PAY_BR = AICUPRVS.DATA.PAY_BR;
        AIRGRVS.THEIR_AC = AICUPRVS.DATA.THEIR_AC;
        AIRGRVS.GLMST = AICUPRVS.DATA.GLMST;
        AIRGRVS.TX_SUP_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        CEP.TRC(SCCGWA, AICUPRVS.DATA.DESC);
        if (AICUPRVS.DATA.SUS_FLG == 'Y') {
            AIRGRVS.ERR_FLG = 'Y';
            AIRGRVS.BR_S = WS_IA_BR_S;
            AIRGRVS.ITM_S = WS_IA_ITM_S;
            AIRGRVS.SEQ_S = WS_IA_SEQ_S;
            if (AICUPRVS.DATA.MSG_ID.trim().length() > 0) {
                AIRGRVS.MSG_ID = AICUPRVS.DATA.MSG_ID;
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                AICUPRVS.DATA.DESC = SCCIMSG.TXT;
            }
        } else {
            AIRGRVS.ERR_FLG = 'N';
        }
        if (AICUPRVS.DATA.DESC.equalsIgnoreCase("NOT BAL SUSPENSE ENTRY")) {
            AIRGRVS.ERR_FLG = 'Y';
            AIRGRVS.MSG_ID = "AI8311";
        }
        if (AICUPRVS.DATA.DESC.equalsIgnoreCase("GL MASTER ITM NOT FOUND")) {
            AIRGRVS.ERR_FLG = 'Y';
            AIRGRVS.MSG_ID = "BP9426";
        }
        if (AICUPRVS.DATA.DESC == null) AICUPRVS.DATA.DESC = "";
        JIBS_tmp_int = AICUPRVS.DATA.DESC.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) AICUPRVS.DATA.DESC += " ";
        if (AICUPRVS.DATA.DESC.substring(8 - 1, 8 + 53 - 1).equalsIgnoreCase("GL MASTER ITM NOT FOUND")) {
            AIRGRVS.ERR_FLG = 'Y';
            AIRGRVS.MSG_ID = "BP9426";
        }
        CEP.TRC(SCCGWA, AICUPRVS.DATA.DESC);
        AIRGRVS.DESC = AICUPRVS.DATA.DESC;
        AIRGRVS.CNTR_TYPE = AICUPRVS.DATA.CNTR_TYPE;
        AIRGRVS.PROD_TYPE = AICUPRVS.DATA.PROD_TYPE;
        AIRGRVS.AC_NO = AICUPRVS.DATA.AC_NO;
        AIRGRVS.EVENT_TYPE = AICUPRVS.DATA.EVENT_TYPE;
        AIRGRVS.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRGRVS.UPDTBL_DATE = AICUPRVS.DATA.TX_DT;
        S000_CALL_AIZRGRVS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_SEQ);
        AICUPRVS.DATA.RVS_NO = AIRGRVS.KEY.RVS_NO;
        AICUPRVS.DATA.RVS_SEQ = AIRGRVS.KEY.RVS_SEQ;
    }
    public void R000_CRVS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RVS_NO);
        IBS.CPY2CLS(SCCGWA, AICUPRVS.DATA.RVS_NO, WS_RVS_NO);
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AICRGINF);
        AICRGINF.INFO.FUNC = 'R';
        AIRGINF.KEY.RVS_NO = IBS.CLS2CPY(SCCGWA, WS_RVS_NO);
        S000_CALL_AIZRGINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRGINF.C_MAX_NO);
        if (AICRGINF.RETURN_INFO == 'F') {
            AICRGINF.INFO.FUNC = 'U';
            if (AIRGINF.C_AMT == 0) {
                AIRGINF.FST_C_DT = AICUPRVS.DATA.TX_DT;
            }
            WS_TEMP_NO = IBS.CLS2CPY(SCCGWA, WS_RVS_NO);
            WS_CRVS_MAXNO = AIRGINF.C_MAX_NO;
            WS_CRVS_MAXNO += 1;
            AIRGINF.C_MAX_NO = WS_CRVS_MAXNO;
            WS_GRVS_MAXNO = AIRGINF.G_MAX_NO;
            AIRGINF.C_AMT += WS_RVS_AMT;
            AIRGINF.G_BAL -= WS_RVS_AMT;
            if (AIRGINF.G_BAL == 0) {
                AIRGINF.STS = 'C';
            } else {
                if (AIRGINF.G_BAL != AIRGINF.G_AMT) {
                    AIRGINF.STS = 'P';
                }
            }
            AIRGINF.LST_C_DT = AICUPRVS.DATA.TX_DT;
            AIRGINF.LST_TR_DT = AICUPRVS.DATA.TX_DT;
            AIRGINF.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AIRGINF.UPDTBL_DATE = AICUPRVS.DATA.TX_DT;
            S000_CALL_AIZRGINF();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICRGINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICUPRVS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, AIRCRVS);
        IBS.init(SCCGWA, AICRCRVS);
        IBS.init(SCCGWA, WS_PART);
        AICRCRVS.INFO.FUNC = 'C';
        AIRCRVS.KEY.RVS_NO = IBS.CLS2CPY(SCCGWA, WS_RVS_NO);
        AIRCRVS.KEY.RVS_SEQ = WS_CRVS_MAXNO;
        AIRCRVS.AC = WS_IA_AC;
        AIRCRVS.BOOK_FLG = AICUPRVS.DATA.GL_BOOK;
        AIRCRVS.BR = WS_IA_BR;
        AIRCRVS.ITM = WS_IA_ITM;
        AIRCRVS.SEQ = WS_IA_SEQ;
        AIRCRVS.CCY = AICUPRVS.DATA.CCY;
        AIRCRVS.TX_DT = AICUPRVS.DATA.TX_DT;
        AIRCRVS.VAL_DT = AICUPRVS.DATA.VAL_DT;
        AIRCRVS.AMT = WS_RVS_AMT;
        AIRCRVS.STS = 'N';
        AIRCRVS.CI_NO = AICUPRVS.DATA.CI_NO;
