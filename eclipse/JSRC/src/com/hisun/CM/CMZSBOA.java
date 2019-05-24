package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCOCLWD;
import com.hisun.BP.BPCQCCY;
import com.hisun.BP.BPRFHIS;
import com.hisun.BP.BPRFHIST;
import com.hisun.CI.CICQACRI;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRJRN;

public class CMZSBOA {
    boolean pgmRtn = false;
    int WS_LEN = 0;
    int WS_I = 0;
    int WS_JRN_CNT = 0;
    int WS_ACRL_CNT = 0;
    int WS_START_DATE = 0;
    CMZSBOA_WS_ERR_MSG WS_ERR_MSG = new CMZSBOA_WS_ERR_MSG();
    CMZSBOA_WS_BATH_PARM WS_BATH_PARM = new CMZSBOA_WS_BATH_PARM();
    CMZSBOA_WS_OUT_FIL_NAME WS_OUT_FIL_NAME = new CMZSBOA_WS_OUT_FIL_NAME();
    CMZSBOA_WS_CHK_INF WS_CHK_INF = new CMZSBOA_WS_CHK_INF();
    CMZSBOA_WS_EQTR_DATA WS_EQTR_DATA = new CMZSBOA_WS_EQTR_DATA();
    CMZSBOA_WS_BOA_DATA WS_BOA_DATA = new CMZSBOA_WS_BOA_DATA();
    char WS_TCTLOGR_FLG = ' ';
    char WS_DDTHLDR_FLG = ' ';
    char WS_DDTHLD_FLG = ' ';
    char WS_EOF_FLG = ' ';
    char WS_ACRL_EOF_FLG = ' ';
    char WS_FHIS_FOUND_FLG = ' ';
    char WS_CHK_COND1_FLG = ' ';
    char WS_CHK_COND2_FLG = ' ';
    char WS_CHK_COND3_FLG = ' ';
    char WS_CHK_COND4_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCBINF SCCBINF = new SCCBINF();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCRJRNH SCRJRNH = new SCRJRNH();
    SCRJRN SCRJRN = new SCRJRN();
    CMRBOA CMRBOA = new CMRBOA();
    CMCREQTR CMCREQTR = new CMCREQTR();
    TCRLOG TCRLOG = new TCRLOG();
    TCRLOGR TCRLOGR = new TCRLOGR();
    CMCQFHIS CMCQFHIS = new CMCQFHIS();
    DDRHLDR DDRHLDR = new DDRHLDR();
    DDRHLD DDRHLD = new DDRHLD();
    CICQACRI CICQACRI = new CICQACRI();
    CIRACRL CIRACRL = new CIRACRL();
    AIRHMIB AIRHMIB = new AIRHMIB();
    BPRFHIS BPRFHIS = new BPRFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    int WS_TR_BR1 = 0;
    int WS_TR_BR2 = 0;
    int WS_S_DT = 0;
    int WS_E_DT = 0;
    int WS_AC_DT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS9970 CMCS9970;
    public void MP(SCCGWA SCCGWA, CMCS9970 CMCS9970) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS9970 = CMCS9970;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZSBOA return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        WS_JRN_CNT = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK_PROC();
        if (pgmRtn) return;
        S000_OPEN_CMQBOA();
        if (pgmRtn) return;
        if (WS_EQTR_DATA.WS_FUNC == '1') {
            B300_INQ_ALL_FHIS_MAIN_PROC();
            if (pgmRtn) return;
        } else if (WS_EQTR_DATA.WS_FUNC == '2') {
            B400_INQ_AC_FHIS_MAIN_PROC();
            if (pgmRtn) return;
        } else if (WS_EQTR_DATA.WS_FUNC == '3') {
            B500_INQ_TR_MAIN_PROC();
            if (pgmRtn) return;
        } else if (WS_EQTR_DATA.WS_FUNC == '4') {
            B600_INQ_VIRTUAL_FHIS_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            B300_INQ_ALL_FHIS_MAIN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
        B110_GET_REQTR_INF();
        if (pgmRtn) return;
        WS_OUT_FIL_NAME.WS_OUT_FILE.WS_BUS_TYPE = CMCS9970.BUS_TYP;
        WS_OUT_FIL_NAME.WS_OUT_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUT_FIL_NAME.WS_OUT_FILE.WS_BAT_NO = SCCGWA.COMM_AREA.JRN_NO;
        if (WS_EQTR_DATA.WS_CUS_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = WS_EQTR_DATA.WS_CUS_AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            if (CICQACRI.O_DATA.O_FRM_APP_OLD.trim().length() > 0) {
                WS_EQTR_DATA.WS_FRM_APP = CICQACRI.O_DATA.O_FRM_APP_OLD;
            } else {
                WS_EQTR_DATA.WS_FRM_APP = CICQACRI.O_DATA.O_FRM_APP;
            }
            WS_EQTR_DATA.WS_ENTY_TYP = CICQACRI.O_DATA.O_ENTY_TYP;
        }
    }
    public void B110_GET_REQTR_INF() throws IOException,SQLException,Exception {
        WS_EQTR_DATA.WS_REQ_CHNL_ID = " ";
        WS_EQTR_DATA.WS_FUNC = CMCS9970.FUNC;
        WS_EQTR_DATA.WS_AP_REF = CMCS9970.REQ_REF;
        WS_EQTR_DATA.WS_TR_BR = CMCS9970.BR;
        WS_EQTR_DATA.WS_REQ_SYS_ID = CMCS9970.REQ_SYS;
        WS_EQTR_DATA.WS_DATE_TYP = CMCS9970.DATE_TYP;
        WS_EQTR_DATA.WS_BEG_DATE = CMCS9970.BEG_DATE;
        WS_EQTR_DATA.WS_END_DATE = CMCS9970.END_DATE;
        WS_EQTR_DATA.WS_REV_FLG = CMCS9970.REV_FLG;
        WS_EQTR_DATA.WS_SEQ_TYP = CMCS9970.SEQ_TYP;
        WS_EQTR_DATA.WS_BEG_SEQ = CMCS9970.BEG_SEQ;
        WS_EQTR_DATA.WS_END_SEQ = CMCS9970.END_SEQ;
        WS_EQTR_DATA.WS_CUS_AC = CMCS9970.CUS_AC;
        WS_EQTR_DATA.WS_AC_SEQ = CMCS9970.AC_SEQ;
        WS_EQTR_DATA.WS_CCY = CMCS9970.CCY;
        WS_EQTR_DATA.WS_CCY_TYP = CMCS9970.CCY_TYP;
        WS_EQTR_DATA.WS_DC_FLG = CMCS9970.DC_FLG;
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_REQ_CHNL_ID);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_REQ_SYS_ID);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_AP_REF);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_TR_BR);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_DATE_TYP);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_BEG_DATE);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_END_DATE);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_REV_FLG);
        if (WS_EQTR_DATA.WS_FUNC == '2' 
            && (WS_EQTR_DATA.WS_CUS_AC.trim().length() == 0 
            || WS_EQTR_DATA.WS_CUS_AC.charAt(0) == 0X00)) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_NO_MUST_IN, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() == 0 
            && WS_EQTR_DATA.WS_CUS_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_INPUT_REQ_SYS, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_EQTR_DATA.WS_DATE_TYP != '1' 
            && WS_EQTR_DATA.WS_DATE_TYP != '2') {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_ERR_DATE_TYPE, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_EQTR_DATA.WS_BEG_DATE == 0 
            || WS_EQTR_DATA.WS_END_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_INPUT_BE_DATE, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((WS_EQTR_DATA.WS_SEQ_TYP == '1' 
            || WS_EQTR_DATA.WS_SEQ_TYP == '2') 
            && ((WS_EQTR_DATA.WS_BEG_SEQ.trim().length() == 0 
            || WS_EQTR_DATA.WS_BEG_SEQ.charAt(0) == 0X00) 
            || (WS_EQTR_DATA.WS_END_SEQ.trim().length() == 0 
            || WS_EQTR_DATA.WS_END_SEQ.charAt(0) == 0X00))) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_INPUT_INQ_SEQ, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_EQTR_DATA.WS_REV_FLG != '1' 
            && WS_EQTR_DATA.WS_REV_FLG != '2') {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_INPUT_REV_FLG, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_EQTR_DATA.WS_BEG_DATE > WS_EQTR_DATA.WS_END_DATE) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_IPT_DT_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_INQ_ALL_FHIS_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_BATH_PARM.WS_JRN_FLG = 'H';
        B310_DETAIL_INQ_PROC();
        if (pgmRtn) return;
        WS_BATH_PARM.WS_JRN_FLG = '1';
        B310_DETAIL_INQ_PROC();
        if (pgmRtn) return;
        WS_BATH_PARM.WS_JRN_FLG = '2';
        B310_DETAIL_INQ_PROC();
        if (pgmRtn) return;
    }
    public void B310_DETAIL_INQ_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_JRN_FLG);
        T000_STARTBR_SCTJRN();
        if (pgmRtn) return;
        T000_READNEXT_SCTJRN();
        if (pgmRtn) return;
        while (WS_EOF_FLG != 'Y') {
            if (WS_BOA_DATA.WS_BOA_VCH_CNT >= 0) {
                B320_CHOOSE_BOA_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_SCTJRN();
            if (pgmRtn) return;
        }
        T000_ENDBR_SCTJRN();
        if (pgmRtn) return;
    }
    public void B320_CHOOSE_BOA_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_JRN_FLG);
        CEP.TRC(SCCGWA, SCRJRN.REQ_REF);
        CEP.TRC(SCCGWA, SCRJRN.KEY.JRN_NO);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_SEQ_TYP);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_BEG_SEQ);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_END_SEQ);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_DC_FLG);
        if (WS_BATH_PARM.WS_JRN_FLG == 'H') {
            WS_CHK_INF.WS_CHK_REQ_REF = SCRJRNH.REQ_REF;
            WS_CHK_INF.WS_CHK_JRN_NO = SCRJRNH.KEY.JRN_NO;
        } else {
            WS_CHK_INF.WS_CHK_REQ_REF = SCRJRN.REQ_REF;
            WS_CHK_INF.WS_CHK_JRN_NO = SCRJRN.KEY.JRN_NO;
        }
        WS_CHK_COND1_FLG = 'Y';
        if (WS_EQTR_DATA.WS_REV_FLG == '1') {
            WS_CHK_COND2_FLG = 'Y';
        } else {
            if (WS_BOA_DATA.WS_BOA_REV_STS != 'Y') {
                WS_CHK_COND2_FLG = 'Y';
            } else {
                WS_CHK_COND2_FLG = 'N';
            }
        }
        if (WS_CHK_COND1_FLG == 'Y' 
            && WS_CHK_COND2_FLG == 'Y') {
            R000_PREP_JRN_DATA();
            if (pgmRtn) return;
            WS_CHK_COND1_FLG = 'N';
            WS_CHK_COND2_FLG = 'N';
        }
    }
    public void B400_INQ_AC_FHIS_MAIN_PROC() throws IOException,SQLException,Exception {
        if (WS_EQTR_DATA.WS_FRM_APP.equalsIgnoreCase("AI")) {
            B460_INQ_MIB_AC_HMIB_PROC();
            if (pgmRtn) return;
        } else {
            B410_INQ_CUS_AC_FHIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B410_INQ_CUS_AC_FHIS_PROC() throws IOException,SQLException,Exception {
        WS_BATH_PARM.WS_JRN_FLG = 'H';
        B420_FHIS_DETAIL_INQ_PROC();
        if (pgmRtn) return;
        WS_BATH_PARM.WS_JRN_FLG = '1';
        B420_FHIS_DETAIL_INQ_PROC();
        if (pgmRtn) return;
        WS_BATH_PARM.WS_JRN_FLG = '2';
        B420_FHIS_DETAIL_INQ_PROC();
        if (pgmRtn) return;
    }
    public void B420_FHIS_DETAIL_INQ_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_JRN_FLG);
        T000_STARTBR_BPTFHIS();
        if (pgmRtn) return;
        T000_READNEXT_BPTFHIS();
        if (pgmRtn) return;
        while (WS_EOF_FLG != 'Y') {
            B430_CHOOSE_FHIS_DATA();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIS();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTFHIS();
        if (pgmRtn) return;
    }
    public void B430_CHOOSE_FHIS_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_JRN_FLG);
        CEP.TRC(SCCGWA, SCRJRN.REQ_REF);
        CEP.TRC(SCCGWA, SCRJRN.KEY.JRN_NO);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_SEQ_TYP);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_BEG_SEQ);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_END_SEQ);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_DC_FLG);
        if (WS_BATH_PARM.WS_JRN_FLG == 'H') {
            WS_CHK_INF.WS_CHK_REQ_REF = BPRFHIST.TX_SYS_JRN;
            WS_CHK_INF.WS_CHK_JRN_NO = BPRFHIST.KEY.JRNNO;
        } else {
            WS_CHK_INF.WS_CHK_REQ_REF = BPRFHIS.TX_SYS_JRN;
            WS_CHK_INF.WS_CHK_JRN_NO = BPRFHIS.KEY.JRNNO;
        }
        WS_CHK_COND1_FLG = 'Y';
        if (WS_EQTR_DATA.WS_REV_FLG == '1') {
            WS_CHK_COND2_FLG = 'Y';
        } else {
            if (WS_BOA_DATA.WS_BOA_REV_STS != 'Y') {
                WS_CHK_COND2_FLG = 'Y';
            } else {
                WS_CHK_COND2_FLG = 'N';
            }
        }
        if (WS_EQTR_DATA.WS_DC_FLG == ' ') {
            WS_CHK_COND3_FLG = 'Y';
        } else {
            if (WS_BOA_DATA.WS_BOA_DC_FLG == WS_EQTR_DATA.WS_DC_FLG) {
                WS_CHK_COND3_FLG = 'Y';
            } else {
                WS_CHK_COND3_FLG = 'N';
            }
        }
        if (WS_CHK_COND1_FLG == 'Y' 
            && WS_CHK_COND2_FLG == 'Y' 
            && WS_CHK_COND3_FLG == 'Y') {
            R000_PREP_FHIS_DATA();
            if (pgmRtn) return;
            WS_CHK_COND1_FLG = 'N';
            WS_CHK_COND2_FLG = 'N';
            WS_CHK_COND3_FLG = 'N';
        }
    }
    public void B460_INQ_MIB_AC_HMIB_PROC() throws IOException,SQLException,Exception {
        WS_BATH_PARM.WS_JRN_FLG = 'H';
        B470_HMIB_DETAIL_INQ_PROC();
        if (pgmRtn) return;
    }
    public void B470_HMIB_DETAIL_INQ_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_JRN_FLG);
        T000_STARTBR_AITHMIB();
        if (pgmRtn) return;
        T000_READNEXT_AITHMIB();
        if (pgmRtn) return;
        while (WS_EOF_FLG != 'Y') {
            B480_CHOOSE_HMIB_DATA();
            if (pgmRtn) return;
            T000_READNEXT_AITHMIB();
            if (pgmRtn) return;
        }
        T000_ENDBR_AITHMIB();
        if (pgmRtn) return;
    }
    public void B480_CHOOSE_HMIB_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_JRN_FLG);
        CEP.TRC(SCCGWA, SCRJRN.REQ_REF);
        CEP.TRC(SCCGWA, SCRJRN.KEY.JRN_NO);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_SEQ_TYP);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_BEG_SEQ);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_END_SEQ);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_DC_FLG);
        if (WS_BATH_PARM.WS_JRN_FLG == 'H') {
            WS_CHK_INF.WS_CHK_REQ_REF = AIRHMIB.OTHSYS_KEY;
            if (AIRHMIB.KEY.SET_NO.trim().length() == 0) WS_CHK_INF.WS_CHK_JRN_NO = 0;
            else WS_CHK_INF.WS_CHK_JRN_NO = Long.parseLong(AIRHMIB.KEY.SET_NO);
        } else {
            WS_CHK_INF.WS_CHK_REQ_REF = AIRHMIB.OTHSYS_KEY;
            if (AIRHMIB.KEY.SET_NO.trim().length() == 0) WS_CHK_INF.WS_CHK_JRN_NO = 0;
            else WS_CHK_INF.WS_CHK_JRN_NO = Long.parseLong(AIRHMIB.KEY.SET_NO);
        }
        WS_CHK_COND1_FLG = 'Y';
        if (WS_EQTR_DATA.WS_REV_FLG == '1') {
            WS_CHK_COND2_FLG = 'Y';
        } else {
            if (WS_BOA_DATA.WS_BOA_REV_STS != 'Y') {
                WS_CHK_COND2_FLG = 'Y';
            } else {
                WS_CHK_COND2_FLG = 'N';
            }
        }
        if (WS_EQTR_DATA.WS_DC_FLG == ' ') {
            WS_CHK_COND3_FLG = 'Y';
        } else {
            if (WS_BOA_DATA.WS_BOA_DC_FLG == WS_EQTR_DATA.WS_DC_FLG) {
                WS_CHK_COND3_FLG = 'Y';
            } else {
                WS_CHK_COND3_FLG = 'N';
            }
        }
        if (WS_CHK_COND1_FLG == 'Y' 
            && WS_CHK_COND2_FLG == 'Y' 
            && WS_CHK_COND3_FLG == 'Y') {
            R000_PREP_HMIB_DATA();
            if (pgmRtn) return;
            WS_CHK_COND1_FLG = 'N';
            WS_CHK_COND2_FLG = 'N';
            WS_CHK_COND3_FLG = 'N';
        }
    }
    public void B500_INQ_TR_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_BATH_PARM.WS_JRN_FLG = '1';
        B510_LOG_DETAIL_INQ_PROC();
        if (pgmRtn) return;
        WS_BATH_PARM.WS_JRN_FLG = '2';
        B510_LOG_DETAIL_INQ_PROC();
        if (pgmRtn) return;
    }
    public void B510_LOG_DETAIL_INQ_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_JRN_FLG);
        T000_STARTBR_TCTLOG();
        if (pgmRtn) return;
        T000_READNEXT_TCTLOG();
        if (pgmRtn) return;
        while (WS_EOF_FLG != 'Y') {
            B520_CHOOSE_LOG_DATA();
            if (pgmRtn) return;
            T000_READNEXT_TCTLOG();
            if (pgmRtn) return;
        }
        T000_ENDBR_TCTLOG();
        if (pgmRtn) return;
    }
    public void B520_CHOOSE_LOG_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_JRN_FLG);
        CEP.TRC(SCCGWA, SCRJRN.REQ_REF);
        CEP.TRC(SCCGWA, SCRJRN.KEY.JRN_NO);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_SEQ_TYP);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_BEG_SEQ);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_END_SEQ);
        R000_PREP_LOG_DATA();
        if (pgmRtn) return;
    }
    public void B600_INQ_VIRTUAL_FHIS_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.REL_AC_NO = CMCS9970.CUS_AC;
        T000_STARTBR_CITACRL();
        if (pgmRtn) return;
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        while (WS_ACRL_EOF_FLG != 'Y' 
            && WS_ACRL_CNT <= 50) {
            WS_ACRL_CNT += 1;
            WS_EQTR_DATA.WS_CUS_AC = CIRACRL.KEY.AC_NO;
            B410_INQ_CUS_AC_FHIS_PROC();
            if (pgmRtn) return;
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
    }
    public void R000_PREP_JRN_DATA() throws IOException,SQLException,Exception {
        WS_JRN_CNT += 1;
        IBS.init(SCCGWA, CMCQFHIS);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_NEW_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_TXN_DATE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_TXN_JRN);
        IBS.init(SCCGWA, CMRBOA);
        CMRBOA.REQ_SYS = WS_BOA_DATA.WS_BOA_REQ_SYS;
        CMRBOA.REQ_SYS_JRN = WS_BOA_DATA.WS_BOA_REQ_SYS_JRN;
        CMRBOA.REQ_SYS_DATE = WS_BOA_DATA.WS_BOA_REQ_SYS_DATE;
        CMRBOA.TXN_BR = WS_BOA_DATA.WS_BOA_TXN_BR;
        CMRBOA.TXN_DATE = WS_BOA_DATA.WS_BOA_TXN_DATE;
        CMRBOA.TXN_JRN = WS_BOA_DATA.WS_BOA_TXN_JRN;
        CMRBOA.TX_CODE = WS_BOA_DATA.WS_BOA_NEW_TR_CODE;
        CMRBOA.CHNL_TR_CODE = WS_BOA_DATA.WS_BOA_OLD_TR_CODE;
        CMRBOA.AP_REF = WS_BOA_DATA.WS_BOA_AP_REF;
        CMRBOA.CHNL = WS_BOA_DATA.WS_BOA_CHNL;
        CMRBOA.CHNL_REF = WS_BOA_DATA.WS_BOA_CHNL_REF;
        CMRBOA.CLEAR_DATE = WS_BOA_DATA.WS_BOA_CLEAR_DATE;
        JIBS_tmp_str[0] = "" + WS_BOA_DATA.WS_BOA_TR_CODE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("9")) {
            CMRBOA.TXN_TYPE = 'C';
        } else {
            CMRBOA.TXN_TYPE = 'N';
        }
        CMRBOA.TR_STS = '1';
        if (WS_BOA_DATA.WS_BOA_REV_STS == 'Y') {
            CMRBOA.REV_STS = 'Y';
            CMRBOA.TR_STS = '3';
        } else {
            CMRBOA.REV_STS = 'N';
        }
        if (WS_BOA_DATA.WS_BOA_NEW_TR_CODE.equalsIgnoreCase("0117510") 
            || WS_BOA_DATA.WS_BOA_NEW_TR_CODE.equalsIgnoreCase("0117500")) {
            IBS.init(SCCGWA, DDRHLDR);
            DDRHLDR.KEY.TR_DATE = WS_BOA_DATA.WS_BOA_TXN_DATE;
            DDRHLDR.KEY.TR_JRNNO = WS_BOA_DATA.WS_BOA_TXN_JRN;
            T000_READ_DDTHLDR();
            if (pgmRtn) return;
            if (WS_DDTHLDR_FLG == 'Y') {
                CMRBOA.FHIS_INF.JRN_SEQ = 1;
                IBS.init(SCCGWA, DDRHLD);
                DDRHLD.KEY.HLD_NO = DDRHLDR.KEY.HLD_NO;
                T000_READ_DDTHLD();
                if (pgmRtn) return;
                if (WS_DDTHLD_FLG == 'Y') {
                    CMRBOA.FHIS_INF.CUS_AC = DDRHLD.CARD_NO;
                    CMRBOA.FHIS_INF.TXN_AMT = DDRHLDR.TR_AMT;
                    CMRBOA.FHIS_INF.OPP_AC = DDRHLDR.KEY.HLD_NO;
                    CMRBOA.GEN_TYPE = 'D';
                    S000_WRITE_CMQBOA();
                    if (pgmRtn) return;
                }
            }
        } else {
            CMCQFHIS.AC_DT = WS_BOA_DATA.WS_BOA_TXN_DATE;
            CMCQFHIS.JRNNO = WS_BOA_DATA.WS_BOA_TXN_JRN;
            CMCQFHIS.DC_FLG = WS_EQTR_DATA.WS_DC_FLG;
            CMCQFHIS.INQ_CTL = "11111";
            S000_CALL_CMZQFHIS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CMCQFHIS.CNT);
            WS_FHIS_FOUND_FLG = ' ';
            for (WS_I = 1; WS_I <= 60 
                && WS_I <= CMCQFHIS.CNT; WS_I += 1) {
                WS_FHIS_FOUND_FLG = 'Y';
                CMRBOA.FHIS_INF.JRN_SEQ = CMCQFHIS.INFO[WS_I-1].JRN_SEQ;
                CMRBOA.FHIS_INF.CUS_AC = CMCQFHIS.INFO[WS_I-1].AC;
                CMRBOA.FHIS_INF.CCY = CMCQFHIS.INFO[WS_I-1].TX_CCY;
                CMRBOA.FHIS_INF.CCY_TYP = CMCQFHIS.INFO[WS_I-1].TX_CCY_TYP.charAt(0);
                CMRBOA.FHIS_INF.TXN_AMT = CMCQFHIS.INFO[WS_I-1].TX_AMT;
                if (CMCQFHIS.INFO[WS_I-1].TX_AMT_FLG == '-') {
                    CMRBOA.FHIS_INF.TXN_AMT = 0 - CMRBOA.FHIS_INF.TXN_AMT;
                }
                CMRBOA.FHIS_INF.CI_NO = CMCQFHIS.INFO[WS_I-1].CI_NO;
                CMRBOA.FHIS_INF.DC_FLG = CMCQFHIS.INFO[WS_I-1].DRCRFLG;
                CMRBOA.FHIS_INF.OPP_AC = CMCQFHIS.INFO[WS_I-1].OPP_AC;
                CMRBOA.GEN_TYPE = CMCQFHIS.INFO[WS_I-1].AC_TYP.charAt(0);
                S000_WRITE_CMQBOA();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_FHIS_FOUND_FLG);
            if ((WS_FHIS_FOUND_FLG != 'Y') 
                && (WS_BOA_DATA.WS_BOA_NEW_TR_CODE.equalsIgnoreCase("0112200") 
                || WS_BOA_DATA.WS_BOA_NEW_TR_CODE.equalsIgnoreCase("0112209"))) {
                S000_WRITE_CMQBOA();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_FETCH_JRN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BOA_DATA);
        if (WS_BATH_PARM.WS_JRN_FLG == 'H') {
            WS_BOA_DATA.WS_BOA_REQ_SYS = SCRJRNH.REQ_SYSTEM;
            WS_BOA_DATA.WS_BOA_REQ_SYS_JRN = SCRJRNH.REQ_REF;
            WS_BOA_DATA.WS_BOA_REQ_SYS_DATE = SCRJRNH.REQ_SYS_DATE;
            WS_BOA_DATA.WS_BOA_TXN_BR = SCRJRNH.TR_BRANCH;
            WS_BOA_DATA.WS_BOA_CLEAR_DATE = SCRJRNH.CLEAR_DATE;
            WS_BOA_DATA.WS_BOA_TXN_DATE = SCRJRNH.AC_DATE;
            WS_BOA_DATA.WS_BOA_TXN_JRN = SCRJRNH.KEY.JRN_NO;
            if (WS_BOA_DATA.WS_BOA_NEW_TR_CODE == null) WS_BOA_DATA.WS_BOA_NEW_TR_CODE = "";
            JIBS_tmp_int = WS_BOA_DATA.WS_BOA_NEW_TR_CODE.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) WS_BOA_DATA.WS_BOA_NEW_TR_CODE += " ";
            JIBS_tmp_str[0] = "" + SCRJRNH.TR_AP;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<3-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_BOA_DATA.WS_BOA_NEW_TR_CODE = JIBS_tmp_str[0] + WS_BOA_DATA.WS_BOA_NEW_TR_CODE.substring(3);
            if (WS_BOA_DATA.WS_BOA_NEW_TR_CODE == null) WS_BOA_DATA.WS_BOA_NEW_TR_CODE = "";
            JIBS_tmp_int = WS_BOA_DATA.WS_BOA_NEW_TR_CODE.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) WS_BOA_DATA.WS_BOA_NEW_TR_CODE += " ";
            JIBS_tmp_str[0] = "" + SCRJRNH.TR_CODE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_BOA_DATA.WS_BOA_NEW_TR_CODE = WS_BOA_DATA.WS_BOA_NEW_TR_CODE.substring(0, 4 - 1) + JIBS_tmp_str[0] + WS_BOA_DATA.WS_BOA_NEW_TR_CODE.substring(4 + 4 - 1);
            WS_BOA_DATA.WS_BOA_OLD_TR_CODE = SCRJRNH.CHNL_TR_CODE;
            WS_BOA_DATA.WS_BOA_AP_REF = SCRJRNH.AP_REF;
            WS_BOA_DATA.WS_BOA_CHNL = SCRJRNH.CHNL;
            WS_BOA_DATA.WS_BOA_CHNL2 = SCRJRNH.CHNL2;
            WS_BOA_DATA.WS_BOA_CHNL_DTL = SCRJRNH.CHNL_DTL;
            WS_BOA_DATA.WS_BOA_CHNL_REF = SCRJRNH.CHNL_REF;
            WS_BOA_DATA.WS_BOA_REV_STS = SCRJRNH.CANCEL_IND;
            WS_BOA_DATA.WS_BOA_REL_TXN_DATE = SCRJRNH.AC_DATE;
            WS_BOA_DATA.WS_BOA_REL_TXN_JRN = SCRJRNH.CANCEL_JRN_NO;
            WS_BOA_DATA.WS_BOA_TR_CODE = SCRJRNH.TR_CODE;
            WS_BOA_DATA.WS_BOA_VCH_CNT = SCRJRNH.VCH_CNT;
        } else {
            WS_BOA_DATA.WS_BOA_REQ_SYS = SCRJRN.REQ_SYSTEM;
            WS_BOA_DATA.WS_BOA_REQ_SYS_JRN = SCRJRN.REQ_REF;
            WS_BOA_DATA.WS_BOA_REQ_SYS_DATE = SCRJRN.REQ_SYS_DATE;
            WS_BOA_DATA.WS_BOA_TXN_BR = SCRJRN.TR_BRANCH;
            WS_BOA_DATA.WS_BOA_CLEAR_DATE = SCRJRN.CLEAR_DATE;
            WS_BOA_DATA.WS_BOA_TXN_DATE = SCRJRN.AC_DATE;
            WS_BOA_DATA.WS_BOA_TXN_JRN = SCRJRN.KEY.JRN_NO;
            if (WS_BOA_DATA.WS_BOA_NEW_TR_CODE == null) WS_BOA_DATA.WS_BOA_NEW_TR_CODE = "";
            JIBS_tmp_int = WS_BOA_DATA.WS_BOA_NEW_TR_CODE.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) WS_BOA_DATA.WS_BOA_NEW_TR_CODE += " ";
            JIBS_tmp_str[0] = "" + SCRJRN.TR_AP;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<3-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_BOA_DATA.WS_BOA_NEW_TR_CODE = JIBS_tmp_str[0] + WS_BOA_DATA.WS_BOA_NEW_TR_CODE.substring(3);
            if (WS_BOA_DATA.WS_BOA_NEW_TR_CODE == null) WS_BOA_DATA.WS_BOA_NEW_TR_CODE = "";
            JIBS_tmp_int = WS_BOA_DATA.WS_BOA_NEW_TR_CODE.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) WS_BOA_DATA.WS_BOA_NEW_TR_CODE += " ";
            JIBS_tmp_str[0] = "" + SCRJRN.TR_CODE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_BOA_DATA.WS_BOA_NEW_TR_CODE = WS_BOA_DATA.WS_BOA_NEW_TR_CODE.substring(0, 4 - 1) + JIBS_tmp_str[0] + WS_BOA_DATA.WS_BOA_NEW_TR_CODE.substring(4 + 4 - 1);
            WS_BOA_DATA.WS_BOA_OLD_TR_CODE = SCRJRN.CHNL_TR_CODE;
            WS_BOA_DATA.WS_BOA_AP_REF = SCRJRN.AP_REF;
            WS_BOA_DATA.WS_BOA_CHNL = SCRJRN.CHNL;
            WS_BOA_DATA.WS_BOA_CHNL2 = SCRJRN.CHNL2;
            WS_BOA_DATA.WS_BOA_CHNL_DTL = SCRJRN.CHNL_DTL;
            WS_BOA_DATA.WS_BOA_CHNL_REF = SCRJRN.CHNL_REF;
            WS_BOA_DATA.WS_BOA_REV_STS = SCRJRN.CANCEL_IND;
            WS_BOA_DATA.WS_BOA_REL_TXN_DATE = SCRJRN.AC_DATE;
            WS_BOA_DATA.WS_BOA_REL_TXN_JRN = SCRJRN.CANCEL_JRN_NO;
            WS_BOA_DATA.WS_BOA_TR_CODE = SCRJRN.TR_CODE;
            WS_BOA_DATA.WS_BOA_VCH_CNT = SCRJRN.VCH_CNT;
        }
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_NEW_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_OLD_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REV_STS);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REL_TXN_DATE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REL_TXN_JRN);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_VCH_CNT);
    }
    public void R000_PREP_FHIS_DATA() throws IOException,SQLException,Exception {
        WS_JRN_CNT += 1;
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_NEW_TR_CODE);
        IBS.init(SCCGWA, CMRBOA);
        CMRBOA.REQ_SYS = WS_BOA_DATA.WS_BOA_REQ_SYS;
        CMRBOA.REQ_SYS_JRN = WS_BOA_DATA.WS_BOA_REQ_SYS_JRN;
        CMRBOA.REQ_SYS_DATE = WS_BOA_DATA.WS_BOA_REQ_SYS_DATE;
        CMRBOA.TXN_BR = WS_BOA_DATA.WS_BOA_TXN_BR;
        CMRBOA.TXN_DATE = WS_BOA_DATA.WS_BOA_TXN_DATE;
        CMRBOA.TXN_JRN = WS_BOA_DATA.WS_BOA_TXN_JRN;
        CMRBOA.TX_CODE = WS_BOA_DATA.WS_BOA_NEW_TR_CODE;
        CMRBOA.CHNL_TR_CODE = WS_BOA_DATA.WS_BOA_OLD_TR_CODE;
        CMRBOA.AP_REF = WS_EQTR_DATA.WS_AP_REF;
        CMRBOA.CHNL = WS_BOA_DATA.WS_BOA_CHNL;
        CMRBOA.CHNL_REF = WS_BOA_DATA.WS_BOA_CHNL_REF;
        CMRBOA.GEN_TYPE = 'W';
        CMRBOA.CLEAR_DATE = WS_BOA_DATA.WS_BOA_CLEAR_DATE;
        JIBS_tmp_str[0] = "" + WS_BOA_DATA.WS_BOA_TR_CODE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("9")) {
            CMRBOA.TXN_TYPE = 'C';
        } else {
            CMRBOA.TXN_TYPE = 'N';
        }
        CMRBOA.TR_STS = '1';
        if (WS_BOA_DATA.WS_BOA_REV_STS == 'Y') {
            CMRBOA.REV_STS = 'Y';
            CMRBOA.TR_STS = '3';
        } else {
            CMRBOA.REV_STS = 'N';
        }
        if (WS_BATH_PARM.WS_JRN_FLG == 'H') {
            CMRBOA.FHIS_INF.JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
            CMRBOA.FHIS_INF.CUS_AC = BPRFHIST.KEY.AC;
            CMRBOA.FHIS_INF.CCY = BPRFHIST.TX_CCY;
            CMRBOA.FHIS_INF.CCY_TYP = BPRFHIST.TX_CCY_TYPE;
            CMRBOA.FHIS_INF.CI_NO = BPRFHIST.CI_NO;
            CMRBOA.FHIS_INF.TXN_AMT = BPRFHIST.TX_AMT;
            CMRBOA.FHIS_INF.DC_FLG = BPRFHIST.DRCRFLG;
            CMRBOA.FHIS_INF.OPP_AC = BPRFHIST.OTH_AC;
        } else {
            CMRBOA.FHIS_INF.JRN_SEQ = BPRFHIS.KEY.JRN_SEQ;
            CMRBOA.FHIS_INF.CUS_AC = BPRFHIS.KEY.AC;
            CMRBOA.FHIS_INF.CCY = BPRFHIS.TX_CCY;
            CMRBOA.FHIS_INF.CCY_TYP = BPRFHIS.TX_CCY_TYPE;
            CMRBOA.FHIS_INF.CI_NO = BPRFHIS.CI_NO;
            CMRBOA.FHIS_INF.TXN_AMT = BPRFHIS.TX_AMT;
            CMRBOA.FHIS_INF.DC_FLG = BPRFHIS.DRCRFLG;
            CMRBOA.FHIS_INF.OPP_AC = BPRFHIS.OTH_AC;
        }
        S000_WRITE_CMQBOA();
        if (pgmRtn) return;
    }
    public void R000_FETCH_FHIS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BOA_DATA);
        if (WS_BATH_PARM.WS_JRN_FLG == 'H') {
            WS_BOA_DATA.WS_BOA_REQ_SYS = BPRFHIST.TX_REQFM;
            WS_BOA_DATA.WS_BOA_REQ_SYS_JRN = BPRFHIST.TX_SYS_JRN;
            WS_BOA_DATA.WS_BOA_REQ_SYS_DATE = 0;
            WS_BOA_DATA.WS_BOA_REQ_SYS_DATE = BPRFHIST.TX_DT;
            WS_BOA_DATA.WS_BOA_TXN_BR = BPRFHIST.TX_BR;
            WS_BOA_DATA.WS_BOA_CLEAR_DATE = 0;
            WS_BOA_DATA.WS_BOA_TXN_DATE = BPRFHIST.KEY.AC_DT;
            WS_BOA_DATA.WS_BOA_TXN_JRN = BPRFHIST.KEY.JRNNO;
            WS_BOA_DATA.WS_BOA_NEW_TR_CODE = BPRFHIST.TX_CD;
            WS_BOA_DATA.WS_BOA_OLD_TR_CODE = " ";
            WS_BOA_DATA.WS_BOA_AP_REF = BPRFHIST.REF_NO;
            WS_BOA_DATA.WS_BOA_CHNL = BPRFHIST.TX_CHNL;
            WS_BOA_DATA.WS_BOA_CHNL2 = " ";
            WS_BOA_DATA.WS_BOA_CHNL_DTL = " ";
            WS_BOA_DATA.WS_BOA_CHNL_REF = " ";
            if (BPRFHIST.TX_STS == 'C' 
                || BPRFHIST.TX_STS == 'R') {
                WS_BOA_DATA.WS_BOA_REV_STS = 'Y';
            }
            WS_BOA_DATA.WS_BOA_REL_TXN_DATE = BPRFHIST.ORG_AC_DT;
            WS_BOA_DATA.WS_BOA_REL_TXN_JRN = BPRFHIST.ORG_JRNNO;
            if (BPRFHIST.TX_CD == null) BPRFHIST.TX_CD = "";
            JIBS_tmp_int = BPRFHIST.TX_CD.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) BPRFHIST.TX_CD += " ";
            if (BPRFHIST.TX_CD.substring(4 - 1, 4 + 4 - 1).trim().length() == 0) WS_BOA_DATA.WS_BOA_TR_CODE = 0;
            else WS_BOA_DATA.WS_BOA_TR_CODE = Short.parseShort(BPRFHIST.TX_CD.substring(4 - 1, 4 + 4 - 1));
            WS_BOA_DATA.WS_BOA_VCH_CNT = 1;
            WS_BOA_DATA.WS_BOA_DC_FLG = BPRFHIST.DRCRFLG;
        } else {
            WS_BOA_DATA.WS_BOA_REQ_SYS = BPRFHIS.TX_REQFM;
            WS_BOA_DATA.WS_BOA_REQ_SYS_JRN = BPRFHIS.TX_SYS_JRN;
            WS_BOA_DATA.WS_BOA_REQ_SYS_DATE = 0;
            WS_BOA_DATA.WS_BOA_REQ_SYS_DATE = BPRFHIS.TX_DT;
            WS_BOA_DATA.WS_BOA_TXN_BR = BPRFHIS.TX_BR;
            WS_BOA_DATA.WS_BOA_CLEAR_DATE = 0;
            WS_BOA_DATA.WS_BOA_TXN_DATE = BPRFHIS.KEY.AC_DT;
            WS_BOA_DATA.WS_BOA_TXN_JRN = BPRFHIS.KEY.JRNNO;
            WS_BOA_DATA.WS_BOA_NEW_TR_CODE = BPRFHIS.TX_CD;
            WS_BOA_DATA.WS_BOA_OLD_TR_CODE = " ";
            WS_BOA_DATA.WS_BOA_AP_REF = BPRFHIS.REF_NO;
            WS_BOA_DATA.WS_BOA_CHNL = BPRFHIS.TX_CHNL;
            WS_BOA_DATA.WS_BOA_CHNL2 = " ";
            WS_BOA_DATA.WS_BOA_CHNL_DTL = " ";
            WS_BOA_DATA.WS_BOA_CHNL_REF = " ";
            if (BPRFHIS.TX_STS == 'C' 
                || BPRFHIS.TX_STS == 'R') {
                WS_BOA_DATA.WS_BOA_REV_STS = 'Y';
            }
            WS_BOA_DATA.WS_BOA_REL_TXN_DATE = BPRFHIS.ORG_AC_DT;
            WS_BOA_DATA.WS_BOA_REL_TXN_JRN = BPRFHIS.ORG_JRNNO;
            if (BPRFHIS.TX_CD == null) BPRFHIS.TX_CD = "";
            JIBS_tmp_int = BPRFHIS.TX_CD.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) BPRFHIS.TX_CD += " ";
            if (BPRFHIS.TX_CD.substring(4 - 1, 4 + 4 - 1).trim().length() == 0) WS_BOA_DATA.WS_BOA_TR_CODE = 0;
            else WS_BOA_DATA.WS_BOA_TR_CODE = Short.parseShort(BPRFHIS.TX_CD.substring(4 - 1, 4 + 4 - 1));
            WS_BOA_DATA.WS_BOA_VCH_CNT = 1;
            WS_BOA_DATA.WS_BOA_DC_FLG = BPRFHIS.DRCRFLG;
        }
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_NEW_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_OLD_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REV_STS);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REL_TXN_DATE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REL_TXN_JRN);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_VCH_CNT);
    }
    public void R000_PREP_HMIB_DATA() throws IOException,SQLException,Exception {
        WS_JRN_CNT += 1;
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_NEW_TR_CODE);
        IBS.init(SCCGWA, CMRBOA);
        CMRBOA.REQ_SYS = WS_BOA_DATA.WS_BOA_REQ_SYS;
        CMRBOA.REQ_SYS_JRN = WS_BOA_DATA.WS_BOA_REQ_SYS_JRN;
        CMRBOA.REQ_SYS_DATE = WS_BOA_DATA.WS_BOA_REQ_SYS_DATE;
        CMRBOA.TXN_BR = WS_BOA_DATA.WS_BOA_TXN_BR;
        CMRBOA.TXN_DATE = WS_BOA_DATA.WS_BOA_TXN_DATE;
        CMRBOA.TXN_JRN = WS_BOA_DATA.WS_BOA_TXN_JRN;
        CMRBOA.TX_CODE = WS_BOA_DATA.WS_BOA_NEW_TR_CODE;
        CMRBOA.CHNL_TR_CODE = WS_BOA_DATA.WS_BOA_OLD_TR_CODE;
        CMRBOA.AP_REF = WS_EQTR_DATA.WS_AP_REF;
        CMRBOA.CHNL = WS_BOA_DATA.WS_BOA_CHNL;
        CMRBOA.CHNL_REF = WS_BOA_DATA.WS_BOA_CHNL_REF;
        CMRBOA.CLEAR_DATE = WS_BOA_DATA.WS_BOA_CLEAR_DATE;
        JIBS_tmp_str[0] = "" + WS_BOA_DATA.WS_BOA_TR_CODE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("9")) {
            CMRBOA.TXN_TYPE = 'C';
        } else {
            CMRBOA.TXN_TYPE = 'N';
        }
        CMRBOA.TR_STS = '1';
        if (WS_BOA_DATA.WS_BOA_REV_STS == 'Y') {
            CMRBOA.REV_STS = 'Y';
            CMRBOA.TR_STS = '3';
        } else {
            CMRBOA.REV_STS = 'N';
        }
        CMRBOA.FHIS_INF.JRN_SEQ = AIRHMIB.KEY.SET_SEQ;
        CMRBOA.FHIS_INF.CUS_AC = AIRHMIB.KEY.AC;
        CMRBOA.FHIS_INF.CCY = AIRHMIB.CCY;
        CMRBOA.FHIS_INF.CCY_TYP = ' ';
        CMRBOA.FHIS_INF.CI_NO = " ";
        CMRBOA.FHIS_INF.TXN_AMT = AIRHMIB.AMT;
        CMRBOA.FHIS_INF.DC_FLG = AIRHMIB.SIGN;
        CMRBOA.FHIS_INF.OPP_AC = AIRHMIB.ORI_AC;
        CMRBOA.GEN_TYPE = 'N';
        S000_WRITE_CMQBOA();
        if (pgmRtn) return;
    }
    public void R000_FETCH_HMIB_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BOA_DATA);
        WS_BOA_DATA.WS_BOA_REQ_SYS = AIRHMIB.REQFM_NO;
        WS_BOA_DATA.WS_BOA_REQ_SYS_JRN = AIRHMIB.OTHSYS_KEY;
        WS_BOA_DATA.WS_BOA_REQ_SYS_DATE = AIRHMIB.REQFM_DT;
        WS_BOA_DATA.WS_BOA_TXN_BR = AIRHMIB.TR_BR;
        WS_BOA_DATA.WS_BOA_CLEAR_DATE = 0;
        WS_BOA_DATA.WS_BOA_TXN_DATE = AIRHMIB.VAL_DATE;
        if (AIRHMIB.KEY.SET_NO.trim().length() == 0) WS_BOA_DATA.WS_BOA_TXN_JRN = 0;
        else WS_BOA_DATA.WS_BOA_TXN_JRN = Long.parseLong(AIRHMIB.KEY.SET_NO);
        WS_BOA_DATA.WS_BOA_NEW_TR_CODE = AIRHMIB.TR_CODE;
        WS_BOA_DATA.WS_BOA_OLD_TR_CODE = " ";
        WS_BOA_DATA.WS_BOA_AP_REF = " ";
        WS_BOA_DATA.WS_BOA_CHNL = AIRHMIB.CHNL_NO;
        WS_BOA_DATA.WS_BOA_CHNL2 = " ";
        WS_BOA_DATA.WS_BOA_CHNL_DTL = " ";
        WS_BOA_DATA.WS_BOA_CHNL_REF = " ";
        WS_BOA_DATA.WS_BOA_REV_STS = AIRHMIB.EC_IND;
        if (AIRHMIB.EC_IND == 'Y' 
            || AIRHMIB.EC_IND == 'R') {
            WS_BOA_DATA.WS_BOA_REV_STS = 'Y';
        }
        WS_BOA_DATA.WS_BOA_REL_TXN_DATE = 0;
        WS_BOA_DATA.WS_BOA_REL_TXN_JRN = 0;
        if (AIRHMIB.TR_CODE == null) AIRHMIB.TR_CODE = "";
        JIBS_tmp_int = AIRHMIB.TR_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) AIRHMIB.TR_CODE += " ";
        if (AIRHMIB.TR_CODE.substring(4 - 1, 4 + 4 - 1).trim().length() == 0) WS_BOA_DATA.WS_BOA_TR_CODE = 0;
        else WS_BOA_DATA.WS_BOA_TR_CODE = Short.parseShort(AIRHMIB.TR_CODE.substring(4 - 1, 4 + 4 - 1));
        WS_BOA_DATA.WS_BOA_VCH_CNT = 1;
        WS_BOA_DATA.WS_BOA_DC_FLG = AIRHMIB.SIGN;
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_NEW_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_OLD_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REV_STS);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REL_TXN_DATE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REL_TXN_JRN);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_VCH_CNT);
    }
    public void R000_PREP_LOG_DATA() throws IOException,SQLException,Exception {
        WS_JRN_CNT += 1;
        IBS.init(SCCGWA, TCRLOGR);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_NEW_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REQ_SYS_JRN);
        TCRLOGR.KEY.REQ_SYS_JRN = WS_BOA_DATA.WS_BOA_REQ_SYS_JRN;
        T000_READ_TCTLOGR();
        if (pgmRtn) return;
        if (WS_TCTLOGR_FLG == 'Y') {
            IBS.init(SCCGWA, CMRBOA);
            CMRBOA.REQ_SYS = WS_BOA_DATA.WS_BOA_REQ_SYS;
            CMRBOA.REQ_SYS_JRN = WS_BOA_DATA.WS_BOA_REQ_SYS_JRN;
            CMRBOA.REQ_SYS_DATE = WS_BOA_DATA.WS_BOA_REQ_SYS_DATE;
            CMRBOA.TXN_BR = WS_BOA_DATA.WS_BOA_TXN_BR;
            CMRBOA.TXN_DATE = TCRLOGR.AC_DATE;
            CMRBOA.TXN_JRN = TCRLOGR.JRN_NO;
            CMRBOA.TX_CODE = WS_BOA_DATA.WS_BOA_NEW_TR_CODE;
            CMRBOA.CHNL_TR_CODE = WS_BOA_DATA.WS_BOA_OLD_TR_CODE;
            CMRBOA.AP_REF = WS_EQTR_DATA.WS_AP_REF;
            CMRBOA.CHNL = WS_BOA_DATA.WS_BOA_CHNL;
            CMRBOA.CHNL_REF = WS_BOA_DATA.WS_BOA_CHNL_REF;
            CMRBOA.GEN_TYPE = 'W';
            CMRBOA.CLEAR_DATE = WS_BOA_DATA.WS_BOA_CLEAR_DATE;
            JIBS_tmp_str[0] = "" + WS_BOA_DATA.WS_BOA_TR_CODE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("9")) {
                CMRBOA.TXN_TYPE = 'C';
            } else {
                CMRBOA.TXN_TYPE = 'N';
            }
            CMRBOA.TR_STS = TCRLOGR.TR_STS;
            if (TCRLOGR.TR_STS == '3') {
                CMRBOA.REV_STS = 'Y';
            } else {
                CMRBOA.REV_STS = 'N';
            }
            S000_WRITE_CMQBOA();
            if (pgmRtn) return;
        }
    }
    public void R000_FETCH_LOG_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BOA_DATA);
        WS_BOA_DATA.WS_BOA_REQ_SYS = TCRLOG.REQ_SYS;
        WS_BOA_DATA.WS_BOA_REQ_SYS_JRN = TCRLOG.KEY.REQ_SYS_JRN;
        WS_BOA_DATA.WS_BOA_REQ_SYS_DATE = TCRLOG.REQ_SYS_DATE;
        WS_BOA_DATA.WS_BOA_NEW_TR_CODE = TCRLOG.SERV_CODE;
        WS_BOA_DATA.WS_BOA_OLD_TR_CODE = TCRLOG.CHNL_TR_ID;
        WS_BOA_DATA.WS_BOA_AP_REF = TCRLOG.REQ_REF;
        WS_BOA_DATA.WS_BOA_CHNL = TCRLOG.REQ_CHNL;
        WS_BOA_DATA.WS_BOA_CHNL_REF = TCRLOG.REQ_CHNL_JRN;
        if (SCRJRNH.SERV_CODE == null) SCRJRNH.SERV_CODE = "";
        JIBS_tmp_int = SCRJRNH.SERV_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) SCRJRNH.SERV_CODE += " ";
        if (SCRJRNH.SERV_CODE.substring(4 - 1, 4 + 4 - 1).trim().length() == 0) WS_BOA_DATA.WS_BOA_TR_CODE = 0;
        else WS_BOA_DATA.WS_BOA_TR_CODE = Short.parseShort(SCRJRNH.SERV_CODE.substring(4 - 1, 4 + 4 - 1));
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_NEW_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_OLD_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REV_STS);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REL_TXN_DATE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REL_TXN_JRN);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_TR_CODE);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_VCH_CNT);
        CEP.TRC(SCCGWA, WS_BOA_DATA.WS_BOA_REQ_SYS_JRN);
    }
    public void T000_READ_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        DDTHLDR_RD.where = "TR_DATE = :DDRHLDR.KEY.TR_DATE "
            + "AND TR_JRNNO = :DDRHLDR.KEY.TR_JRNNO";
        IBS.READ(SCCGWA, DDRHLDR, this, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTHLDR_FLG = 'Y';
        } else {
            WS_DDTHLDR_FLG = 'N';
        }
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "HLD_NO = :DDRHLD.KEY.HLD_NO";
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTHLD_FLG = 'Y';
        } else {
            WS_DDTHLD_FLG = 'N';
        }
    }
    public void T000_STARTBR_SCTJRN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_JRN_FLG);
        WS_EOF_FLG = 'Y';
        WS_S_DT = WS_EQTR_DATA.WS_BEG_DATE;
        CEP.TRC(SCCGWA, WS_S_DT);
        WS_E_DT = WS_EQTR_DATA.WS_END_DATE;
        CEP.TRC(SCCGWA, WS_E_DT);
        CEP.TRC(SCCGWA, WS_AC_DT);
        IBS.init(SCCGWA, SCRJRN);
        SCRJRN.CHNL = WS_EQTR_DATA.WS_REQ_CHNL_ID;
        CEP.TRC(SCCGWA, SCRJRN.CHNL);
        SCRJRN.REQ_SYSTEM = WS_EQTR_DATA.WS_REQ_SYS_ID;
        CEP.TRC(SCCGWA, SCRJRN.REQ_SYSTEM);
        SCRJRN.AP_REF = WS_EQTR_DATA.WS_AP_REF;
        CEP.TRC(SCCGWA, SCRJRN.AP_REF);
        SCRJRN.TR_BRANCH = WS_EQTR_DATA.WS_TR_BR;
        CEP.TRC(SCCGWA, SCRJRN.TR_BRANCH);
        SCRJRN.AC_DATE = WS_START_DATE;
        CEP.TRC(SCCGWA, SCRJRN.AC_DATE);
        IBS.init(SCCGWA, SCRJRNH);
        SCRJRNH.AC_DATE = WS_START_DATE;
        SCRJRNH.CHNL = WS_EQTR_DATA.WS_REQ_CHNL_ID;
        SCRJRNH.REQ_SYSTEM = WS_EQTR_DATA.WS_REQ_SYS_ID;
        SCRJRNH.AP_REF = WS_EQTR_DATA.WS_AP_REF;
        SCRJRNH.TR_BRANCH = WS_EQTR_DATA.WS_TR_BR;
        WS_TR_BR1 = 0;
        WS_TR_BR2 = 999999;
        if (WS_EQTR_DATA.WS_TR_BR != 0) {
            WS_TR_BR1 = WS_EQTR_DATA.WS_TR_BR;
            WS_TR_BR2 = WS_EQTR_DATA.WS_TR_BR;
        }
        CEP.TRC(SCCGWA, "INQ CONDITION********");
        CEP.TRC(SCCGWA, SCRJRNH.AC_DATE);
        CEP.TRC(SCCGWA, SCRJRNH.CHNL);
        CEP.TRC(SCCGWA, SCRJRNH.REQ_SYSTEM);
        CEP.TRC(SCCGWA, SCRJRNH.AP_REF);
        CEP.TRC(SCCGWA, SCRJRNH.TR_BRANCH);
        CEP.TRC(SCCGWA, WS_TR_BR1);
        CEP.TRC(SCCGWA, WS_TR_BR2);
        CEP.TRC(SCCGWA, "INQ CONDITION********");
        if (WS_BATH_PARM.WS_JRN_FLG == 'H' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBR0H1");
            SCTJRNH_BR.rp = new DBParm();
            SCTJRNH_BR.rp.TableName = "SCTJRNH";
            SCTJRNH_BR.rp.where = "( REQ_SYSTEM = :SCRJRNH.REQ_SYSTEM ) "
                + "AND ( REQ_SYS_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( AP_REF = :SCRJRNH.AP_REF ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRNH, this, SCTJRNH_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == 'H' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '2' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBR0H2");
            SCTJRNH_BR.rp = new DBParm();
            SCTJRNH_BR.rp.TableName = "SCTJRNH";
            SCTJRNH_BR.rp.where = "( REQ_SYSTEM = :SCRJRNH.REQ_SYSTEM ) "
                + "AND ( AC_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( AP_REF = :SCRJRNH.AP_REF ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRNH, this, SCTJRNH_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == 'H' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBR0H3");
            SCTJRNH_BR.rp = new DBParm();
            SCTJRNH_BR.rp.TableName = "SCTJRNH";
            SCTJRNH_BR.rp.where = "( REQ_SYSTEM = :SCRJRNH.REQ_SYSTEM ) "
                + "AND ( REQ_SYS_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRNH, this, SCTJRNH_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == 'H' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '2' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBR0H4");
            SCTJRNH_BR.rp = new DBParm();
            SCTJRNH_BR.rp.TableName = "SCTJRNH";
            SCTJRNH_BR.rp.where = "( REQ_SYSTEM = :SCRJRNH.REQ_SYSTEM ) "
                + "AND ( AC_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRNH, this, SCTJRNH_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBR011");
            SCTJRN1_BR.rp = new DBParm();
            SCTJRN1_BR.rp.TableName = "SCTJRN1";
            SCTJRN1_BR.rp.where = "( REQ_SYSTEM = :SCRJRN.REQ_SYSTEM ) "
                + "AND ( REQ_SYS_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( AP_REF = :SCRJRN.AP_REF ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRN, this, SCTJRN1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '2' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBR002");
            SCTJRN1_BR.rp = new DBParm();
            SCTJRN1_BR.rp.TableName = "SCTJRN1";
            SCTJRN1_BR.rp.where = "( REQ_SYSTEM = :SCRJRN.REQ_SYSTEM ) "
                + "AND ( AC_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( AP_REF = :SCRJRN.AP_REF ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRN, this, SCTJRN1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBR003");
            SCTJRN1_BR.rp = new DBParm();
            SCTJRN1_BR.rp.TableName = "SCTJRN1";
            SCTJRN1_BR.rp.where = "( REQ_SYSTEM = :SCRJRN.REQ_SYSTEM ) "
                + "AND ( REQ_SYS_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRN, this, SCTJRN1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '2' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBR004");
            SCTJRN1_BR.rp = new DBParm();
            SCTJRN1_BR.rp.TableName = "SCTJRN1";
            SCTJRN1_BR.rp.where = "( REQ_SYSTEM = :SCRJRN.REQ_SYSTEM ) "
                + "AND ( AC_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRN, this, SCTJRN1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBR011");
            SCTJRN2_BR.rp = new DBParm();
            SCTJRN2_BR.rp.TableName = "SCTJRN2";
            SCTJRN2_BR.rp.where = "( REQ_SYSTEM = :SCRJRN.REQ_SYSTEM ) "
                + "AND ( REQ_SYS_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( AP_REF = :SCRJRN.AP_REF ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRN, this, SCTJRN2_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '2' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBR002");
            SCTJRN2_BR.rp = new DBParm();
            SCTJRN2_BR.rp.TableName = "SCTJRN2";
            SCTJRN2_BR.rp.where = "( REQ_SYSTEM = :SCRJRN.REQ_SYSTEM ) "
                + "AND ( AC_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( AP_REF = :SCRJRN.AP_REF ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRN, this, SCTJRN2_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBR003");
            SCTJRN2_BR.rp = new DBParm();
            SCTJRN2_BR.rp.TableName = "SCTJRN2";
            SCTJRN2_BR.rp.where = "( REQ_SYSTEM = :SCRJRN.REQ_SYSTEM ) "
                + "AND ( REQ_SYS_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRN, this, SCTJRN2_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '2' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBR004");
            SCTJRN2_BR.rp = new DBParm();
            SCTJRN2_BR.rp.TableName = "SCTJRN2";
            SCTJRN2_BR.rp.where = "( REQ_SYSTEM = :SCRJRN.REQ_SYSTEM ) "
                + "AND ( AC_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TR_BRANCH BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, SCRJRN, this, SCTJRN2_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_INQ_COND_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_SCTJRN() throws IOException,SQLException,Exception {
        if (WS_BATH_PARM.WS_JRN_FLG == 'H') {
            IBS.READNEXT(SCCGWA, SCRJRNH, this, SCTJRNH_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1') {
            IBS.READNEXT(SCCGWA, SCRJRN, this, SCTJRN1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2') {
            IBS.READNEXT(SCCGWA, SCRJRN, this, SCTJRN2_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_FLG_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_FLG = 'N';
            R000_FETCH_JRN_DATA();
            if (pgmRtn) return;
        } else {
            WS_EOF_FLG = 'Y';
        }
    }
    public void T000_ENDBR_SCTJRN() throws IOException,SQLException,Exception {
        if (WS_BATH_PARM.WS_JRN_FLG == 'H') {
            IBS.ENDBR(SCCGWA, SCTJRNH_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1') {
            IBS.ENDBR(SCCGWA, SCTJRN1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2') {
            IBS.ENDBR(SCCGWA, SCTJRN2_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_FLG_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TCTLOG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_JRN_FLG);
        WS_EOF_FLG = 'Y';
        WS_S_DT = WS_EQTR_DATA.WS_BEG_DATE;
        CEP.TRC(SCCGWA, WS_S_DT);
        WS_E_DT = WS_EQTR_DATA.WS_END_DATE;
        CEP.TRC(SCCGWA, WS_E_DT);
        CEP.TRC(SCCGWA, WS_AC_DT);
        IBS.init(SCCGWA, TCRLOG);
        TCRLOG.REQ_CHNL = WS_EQTR_DATA.WS_REQ_CHNL_ID;
        TCRLOG.REQ_SYS = WS_EQTR_DATA.WS_REQ_SYS_ID;
        TCRLOG.REQ_REF = WS_EQTR_DATA.WS_AP_REF;
        CEP.TRC(SCCGWA, "INQ CONDITION********");
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_JRN_FLG);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_REQ_SYS_ID);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_DATE_TYP);
        CEP.TRC(SCCGWA, WS_EQTR_DATA.WS_AP_REF);
        CEP.TRC(SCCGWA, "INQ CONDITION********");
        if (WS_BATH_PARM.WS_JRN_FLG == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBRT01");
            TCTLOG1_BR.rp = new DBParm();
            TCTLOG1_BR.rp.TableName = "TCTLOG1";
            TCTLOG1_BR.rp.where = "( REQ_SYS = :TCRLOG.REQ_SYS ) "
                + "AND ( REQ_SYS_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( REQ_REF = :TCRLOG.REQ_REF )";
            IBS.STARTBR(SCCGWA, TCRLOG, this, TCTLOG1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBRT02");
            TCTLOG2_BR.rp = new DBParm();
            TCTLOG2_BR.rp.TableName = "TCTLOG2";
            TCTLOG2_BR.rp.where = "( REQ_SYS = :TCRLOG.REQ_SYS ) "
                + "AND ( REQ_SYS_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( REQ_REF = :TCRLOG.REQ_REF )";
            IBS.STARTBR(SCCGWA, TCRLOG, this, TCTLOG2_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBRT03");
            TCTLOG1_BR.rp = new DBParm();
            TCTLOG1_BR.rp.TableName = "TCTLOG1";
            TCTLOG1_BR.rp.where = "( REQ_SYS = :TCRLOG.REQ_SYS ) "
                + "AND ( REQ_SYS_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT )";
            IBS.STARTBR(SCCGWA, TCRLOG, this, TCTLOG1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBRT04");
            TCTLOG2_BR.rp = new DBParm();
            TCTLOG2_BR.rp.TableName = "TCTLOG2";
            TCTLOG2_BR.rp.where = "( REQ_SYS = :TCRLOG.REQ_SYS ) "
                + "AND ( REQ_SYS_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT )";
            IBS.STARTBR(SCCGWA, TCRLOG, this, TCTLOG2_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_INQ_COND_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TCTLOG() throws IOException,SQLException,Exception {
        if (WS_BATH_PARM.WS_JRN_FLG == '1') {
            IBS.READNEXT(SCCGWA, TCRLOG, this, TCTLOG1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2') {
            IBS.READNEXT(SCCGWA, TCRLOG, this, TCTLOG2_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_FLG_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_FLG = 'N';
            R000_FETCH_LOG_DATA();
            if (pgmRtn) return;
        } else {
            WS_EOF_FLG = 'Y';
        }
    }
    public void T000_ENDBR_TCTLOG() throws IOException,SQLException,Exception {
        if (WS_BATH_PARM.WS_JRN_FLG == '1') {
            IBS.ENDBR(SCCGWA, TCTLOG1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2') {
            IBS.ENDBR(SCCGWA, TCTLOG2_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_FLG_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TCTLOGR() throws IOException,SQLException,Exception {
        if (WS_BATH_PARM.WS_JRN_FLG == '1') {
            TCTLOGR1_RD = new DBParm();
            TCTLOGR1_RD.TableName = "TCTLOGR1";
            TCTLOGR1_RD.where = "REQ_SYS_JRN = :TCRLOGR.KEY.REQ_SYS_JRN";
            IBS.READ(SCCGWA, TCRLOGR, this, TCTLOGR1_RD);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2') {
            TCTLOGR2_RD = new DBParm();
            TCTLOGR2_RD.TableName = "TCTLOGR2";
            TCTLOGR2_RD.where = "REQ_SYS_JRN = :TCRLOGR.KEY.REQ_SYS_JRN";
            IBS.READ(SCCGWA, TCRLOGR, this, TCTLOGR2_RD);
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_FLG_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TCTLOGR_FLG = 'Y';
        } else {
            WS_TCTLOGR_FLG = 'N';
        }
    }
    public void T000_STARTBR_BPTFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        BPRFHIS.KEY.AC = WS_EQTR_DATA.WS_CUS_AC;
        BPRFHIST.KEY.AC = WS_EQTR_DATA.WS_CUS_AC;
        BPRFHIS.KEY.AC_DT = WS_START_DATE;
        BPRFHIST.KEY.AC_DT = WS_START_DATE;
        BPRFHIS.TX_CHNL = WS_EQTR_DATA.WS_REQ_CHNL_ID;
        BPRFHIST.TX_CHNL = WS_EQTR_DATA.WS_REQ_CHNL_ID;
        BPRFHIS.TX_REQFM = WS_EQTR_DATA.WS_REQ_SYS_ID;
        BPRFHIST.TX_REQFM = WS_EQTR_DATA.WS_REQ_SYS_ID;
        BPRFHIS.REF_NO = WS_EQTR_DATA.WS_AP_REF;
        BPRFHIST.REF_NO = WS_EQTR_DATA.WS_AP_REF;
        BPRFHIS.TX_BR = WS_EQTR_DATA.WS_TR_BR;
        BPRFHIST.TX_BR = WS_EQTR_DATA.WS_TR_BR;
        WS_TR_BR1 = 0;
        WS_TR_BR2 = 999999;
        if (WS_EQTR_DATA.WS_TR_BR != 0) {
            WS_TR_BR1 = WS_EQTR_DATA.WS_TR_BR;
            WS_TR_BR2 = WS_EQTR_DATA.WS_TR_BR;
        }
        WS_S_DT = WS_EQTR_DATA.WS_BEG_DATE;
        WS_E_DT = WS_EQTR_DATA.WS_END_DATE;
        CEP.TRC(SCCGWA, WS_S_DT);
        CEP.TRC(SCCGWA, WS_E_DT);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIS.TX_CHNL);
        CEP.TRC(SCCGWA, BPRFHIS.TX_REQFM);
        CEP.TRC(SCCGWA, BPRFHIS.REF_NO);
        CEP.TRC(SCCGWA, BPRFHIS.TX_BR);
        CEP.TRC(SCCGWA, WS_TR_BR1);
        CEP.TRC(SCCGWA, WS_TR_BR2);
        if (WS_BATH_PARM.WS_JRN_FLG == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIS11");
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "AC = :BPRFHIS.KEY.AC "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND TX_REQFM = :BPRFHIS.TX_REQFM "
                + "AND REF_NO = :BPRFHIS.REF_NO "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() == 0 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIS12");
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "AC = :BPRFHIS.KEY.AC "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND REF_NO = :BPRFHIS.REF_NO "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIS13");
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "AC = :BPRFHIS.KEY.AC "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND TX_REQFM = :BPRFHIS.TX_REQFM "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() == 0 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIS14");
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "AC = :BPRFHIS.KEY.AC "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIS21");
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "AC = :BPRFHIS.KEY.AC "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND TX_REQFM = :BPRFHIS.TX_REQFM "
                + "AND REF_NO = :BPRFHIS.REF_NO "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS2_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() == 0 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIS22");
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "AC = :BPRFHIS.KEY.AC "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND REF_NO = :BPRFHIS.REF_NO "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS2_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIS23");
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "AC = :BPRFHIS.KEY.AC "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND TX_REQFM = :BPRFHIS.TX_REQFM "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS2_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() == 0 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIS24");
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "AC = :BPRFHIS.KEY.AC "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS2_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == 'H' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIST1");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :BPRFHIST.KEY.AC "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND TX_REQFM = :BPRFHIST.TX_REQFM "
                + "AND REF_NO = :BPRFHIST.REF_NO "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == 'H' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() == 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '2' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIST2");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :BPRFHIST.KEY.AC "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND REF_NO = :BPRFHIST.REF_NO "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == 'H' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '2' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIST3");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :BPRFHIST.KEY.AC "
                + "AND TX_REQFM = :BPRFHIST.TX_REQFM "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == 'H' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() == 0 
                && WS_EQTR_DATA.WS_DATE_TYP == '2' 
                && WS_EQTR_DATA.WS_AP_REF.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBRFHIST4");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :BPRFHIS.KEY.AC "
                + "AND ( AC_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TX_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_INQ_COND_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTFHIS() throws IOException,SQLException,Exception {
        if (WS_BATH_PARM.WS_JRN_FLG == 'H') {
            IBS.READNEXT(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1') {
            IBS.READNEXT(SCCGWA, BPRFHIS, this, BPTFHIS1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2') {
            IBS.READNEXT(SCCGWA, BPRFHIS, this, BPTFHIS2_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_FLG_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_FLG = 'N';
            R000_FETCH_FHIS_DATA();
            if (pgmRtn) return;
        } else {
            WS_EOF_FLG = 'Y';
        }
    }
    public void T000_ENDBR_BPTFHIS() throws IOException,SQLException,Exception {
        if (WS_BATH_PARM.WS_JRN_FLG == 'H') {
            IBS.ENDBR(SCCGWA, BPTFHIST_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '1') {
            IBS.ENDBR(SCCGWA, BPTFHIS1_BR);
        } else if (WS_BATH_PARM.WS_JRN_FLG == '2') {
            IBS.ENDBR(SCCGWA, BPTFHIS2_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_FLG_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITHMIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRHMIB);
        AIRHMIB.KEY.AC = WS_EQTR_DATA.WS_CUS_AC;
        AIRHMIB.VAL_DATE = WS_START_DATE;
        AIRHMIB.CHNL_NO = WS_EQTR_DATA.WS_REQ_CHNL_ID;
        AIRHMIB.REQFM_NO = WS_EQTR_DATA.WS_REQ_SYS_ID;
        AIRHMIB.TR_BR = WS_EQTR_DATA.WS_TR_BR;
        WS_TR_BR1 = 0;
        WS_TR_BR2 = 999999;
        if (WS_EQTR_DATA.WS_TR_BR != 0) {
            WS_TR_BR1 = WS_EQTR_DATA.WS_TR_BR;
            WS_TR_BR2 = WS_EQTR_DATA.WS_TR_BR;
        }
        WS_S_DT = WS_EQTR_DATA.WS_BEG_DATE;
        WS_E_DT = WS_EQTR_DATA.WS_END_DATE;
        CEP.TRC(SCCGWA, WS_S_DT);
        CEP.TRC(SCCGWA, WS_E_DT);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.AC);
        CEP.TRC(SCCGWA, AIRHMIB.VAL_DATE);
        CEP.TRC(SCCGWA, AIRHMIB.CHNL_NO);
        CEP.TRC(SCCGWA, AIRHMIB.REQFM_NO);
        CEP.TRC(SCCGWA, AIRHMIB.TR_BR);
        CEP.TRC(SCCGWA, WS_TR_BR1);
        CEP.TRC(SCCGWA, WS_TR_BR2);
        CEP.TRC(SCCGWA, WS_TR_BR2);
        CEP.TRC(SCCGWA, "STARTHMIB");
        CEP.TRC(SCCGWA, AIRHMIB.KEY.TR_DATE);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.SET_NO);
        CEP.TRC(SCCGWA, AIRHMIB.SIGN);
        if (WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBRHMIBT1");
            AITHMIB_BR.rp = new DBParm();
            AITHMIB_BR.rp.TableName = "AITHMIB";
            AITHMIB_BR.rp.where = "AC = :AIRHMIB.KEY.AC "
                + "AND REQFM_NO = :AIRHMIB.REQFM_NO "
                + "AND ( REQFM_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TR_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, AIRHMIB, this, AITHMIB_BR);
        } else if (WS_EQTR_DATA.WS_DATE_TYP == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() > 0) {
            CEP.TRC(SCCGWA, "STARTBRHMIBT2");
            AITHMIB_BR.rp = new DBParm();
            AITHMIB_BR.rp.TableName = "AITHMIB";
            AITHMIB_BR.rp.where = "AC = :AIRHMIB.KEY.AC "
                + "AND REQFM_NO = :AIRHMIB.REQFM_NO "
                + "AND ( VAL_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TR_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, AIRHMIB, this, AITHMIB_BR);
        } else if (WS_EQTR_DATA.WS_DATE_TYP == '1' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBRHMIBT3");
            AITHMIB_BR.rp = new DBParm();
            AITHMIB_BR.rp.TableName = "AITHMIB";
            AITHMIB_BR.rp.where = "AC = :AIRHMIB.KEY.AC "
                + "AND ( REQFM_DT BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TR_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, AIRHMIB, this, AITHMIB_BR);
        } else if (WS_EQTR_DATA.WS_DATE_TYP == '2' 
                && WS_EQTR_DATA.WS_REQ_SYS_ID.trim().length() == 0) {
            CEP.TRC(SCCGWA, "STARTBRHMIBT4");
            AITHMIB_BR.rp = new DBParm();
            AITHMIB_BR.rp.TableName = "AITHMIB";
            AITHMIB_BR.rp.where = "AC = :AIRHMIB.KEY.AC "
                + "AND ( VAL_DATE BETWEEN :WS_S_DT "
                + "AND :WS_E_DT ) "
                + "AND ( TR_BR BETWEEN :WS_TR_BR1 "
                + "AND :WS_TR_BR2 )";
            IBS.STARTBR(SCCGWA, AIRHMIB, this, AITHMIB_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_INQ_COND_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_AITHMIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRHMIB, this, AITHMIB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_FLG = 'N';
            R000_FETCH_HMIB_DATA();
            if (pgmRtn) return;
        } else {
            WS_EOF_FLG = 'Y';
        }
    }
    public void T000_ENDBR_AITHMIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITHMIB_BR);
    }
    public void T000_STARTBR_CITACRL() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.where = "REL_AC_NO = :CIRACRL.KEY.REL_AC_NO "
            + "AND AC_REL = '01'";
        IBS.STARTBR(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_READNEXT_CITACRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACRL, this, CITACRL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACRL_EOF_FLG = 'N';
        } else {
            WS_ACRL_EOF_FLG = 'Y';
        }
    }
    public void T000_ENDBR_CITACRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACRL_BR);
    }
    public void S000_CALL_CMZREQTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-U-BRW-REQTR", CMCREQTR);
        if (CMCREQTR.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, CMCREQTR.RC.RC_CODE+"", WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CMZQFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-BROWS-HIS", CMCQFHIS);
        if (CMCQFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCQFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_OPEN_CMQBOA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_OUT_FIL_NAME);
