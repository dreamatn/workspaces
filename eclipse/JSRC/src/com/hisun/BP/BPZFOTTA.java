package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFOTTA {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MGM_TXIF = "BP-R-MGM-TXIF       ";
    String CPN_R_MGM_TATH = "BP-R-MGM-TATH       ";
    String CPN_R_MGM_TROL = "BP-R-MGM-TROL       ";
    String CPN_R_MGM_GRPL = "BP-R-MGM-GRPL       ";
    char WS_EMP_RECORD = ' ';
    short WS_I = 0;
    char WS_TBL_TXIF_FLAG = ' ';
    char WS_TBL_TATH_FLAG = ' ';
    char WS_TBL_TROL_FLAG = ' ';
    char WS_TBL_GRPL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTXIF BPRTXIF = new BPRTXIF();
    BPRTATH BPRTATH = new BPRTATH();
    BPRTROL BPRTROL = new BPRTROL();
    BPRGRPL BPRGRPL = new BPRGRPL();
    BPCRGRPL BPCRGRPL = new BPCRGRPL();
    BPCRTROL BPCRTROL = new BPCRTROL();
    BPCRTATH BPCRTATH = new BPCRTATH();
    BPCRTXIF BPCRTXIF = new BPCRTXIF();
    SCCGWA SCCGWA;
    BPCFOTTA BPCFOTTA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCFOTTA BPCFOTTA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFOTTA = BPCFOTTA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFOTTA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPRGRPL);
        IBS.init(SCCGWA, BPCRGRPL);
        IBS.init(SCCGWA, BPRTXIF);
        IBS.init(SCCGWA, BPCRTXIF);
        IBS.init(SCCGWA, BPRTATH);
        IBS.init(SCCGWA, BPCRTATH);
        IBS.init(SCCGWA, BPRTROL);
        IBS.init(SCCGWA, BPCRTROL);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFOTTA.RC);
        BPCFOTTA.PRIV_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRBANK.TLR_COND);
        if (BPCRBANK.TLR_COND == '0' 
            || BPCRBANK.TLR_COND == '2') {
            B020_CHECK_TATH();
            if (pgmRtn) return;
        }
        if (BPCRBANK.TLR_COND == '0' 
            || BPCRBANK.TLR_COND == '1') {
            B030_CHECK_GRPL();
            if (pgmRtn) return;
        }
        if (BPCRBANK.TLR_COND == '3') {
            BPCFOTTA.PRIV_FLG = 'Y';
        }
        BPCFOTTA.PRIV_FLG = 'Y';
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFOTTA);
        if ((BPCFOTTA.ASSTYP != 'O' 
            && BPCFOTTA.ASSTYP != 'T' 
            && BPCFOTTA.ASSTYP != 'C')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FOTTA_ASSTYP, BPCFOTTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFOTTA.ASS_ID.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FOTTA_ASS_ID, BPCFOTTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((BPCFOTTA.ATH_TYP != '0' 
            && BPCFOTTA.ATH_TYP != '1')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FOTTA_ATHTYP, BPCFOTTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFOTTA.IN_FLG == 'I'
            || BPCFOTTA.IN_FLG == 'O') {
            IBS.init(SCCGWA, BPCRTXIF);
            IBS.init(SCCGWA, BPRTXIF);
            BPCRTXIF.INFO.FUNC = 'Q';
            BPRTXIF.KEY.IN_FLG = BPCFOTTA.IN_FLG;
            BPRTXIF.KEY.SYS_MMO = BPCFOTTA.SYS_MMO;
            BPRTXIF.KEY.TX_CD = BPCFOTTA.TX_CD;
            CEP.TRC(SCCGWA, BPRTXIF.KEY.IN_FLG);
            CEP.TRC(SCCGWA, BPRTXIF.KEY.SYS_MMO);
            CEP.TRC(SCCGWA, BPRTXIF.KEY.TX_CD);
            S000_CALL_BPZRTXIF();
            if (pgmRtn) return;
            if (BPCRTXIF.RETURN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TXIF_NOTFND, BPCFOTTA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPRTXIF.STS == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TXIF_INV, BPCFOTTA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPRTXIF.SATH_FLG == 'N') {
                CEP.TRC(SCCGWA, "F1");
                BPCFOTTA.PRIV_FLG = 'Y';
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (BPCFOTTA.IN_FLG == 'F') {
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FOTTA_INFLG, BPCFOTTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFOTTA.SYS_MMO.trim().length() == 0 
            || BPCFOTTA.TX_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FOTTA_INFLG, BPCFOTTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_TATH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTATH);
        IBS.init(SCCGWA, BPRTATH);
        BPCRTATH.INFO.FUNC = 'Q';
        BPRTATH.KEY.ASS_TYP = BPCFOTTA.ASSTYP;
        BPRTATH.KEY.ASS_ID = BPCFOTTA.ASS_ID;
        BPRTATH.KEY.ATH_TYP = BPCFOTTA.ATH_TYP;
        S000_CALL_BPZRTATH();
        if (pgmRtn) return;
        if (BPCRTATH.RETURN_INFO == 'F') {
            for (WS_I = 1; WS_I <= BPRTATH.TXIF_CNT 
                && BPCFOTTA.PRIV_FLG != 'Y'; WS_I += 1) {
                CEP.TRC(SCCGWA, "AAAAA");
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
                CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EFF_DT);
                CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EXP_DT);
                if (SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase("HDCPT61") 
                    || SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase("HDCPT62")) {
                    CEP.TRC(SCCGWA, "BBBBB");
                    if (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].IN_FLG == BPCFOTTA.IN_FLG 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].SYS_MMO.equalsIgnoreCase(BPCFOTTA.SYS_MMO) 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].TX_CD.equalsIgnoreCase(BPCFOTTA.TX_CD) 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EFF_DT <= 20140508 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EXP_DT > 20140508 
                        && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '0' 
                        || BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '2' 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '1' 
                        && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT < 20140508 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT == 20140508 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_TM < 180000)) 
                        && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT > 20140508) 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT == 20140508 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_TM > 180000)) 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '3' 
                        && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT > 20140508 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT == 20140508 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_TM > 180000)) 
                        || BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT < 20140508 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT == 20140508 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_TM < 180000)))) {
                        CEP.TRC(SCCGWA, "OK");
                        BPCFOTTA.PRIV_FLG = 'Y';
                        Z_RET();
                        if (pgmRtn) return;
                    }
                } else {
                    CEP.TRC(SCCGWA, "BBBBB");
                    if (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].IN_FLG == BPCFOTTA.IN_FLG 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].SYS_MMO.equalsIgnoreCase(BPCFOTTA.SYS_MMO) 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].TX_CD.equalsIgnoreCase(BPCFOTTA.TX_CD) 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EXP_DT > SCCGWA.COMM_AREA.AC_DATE 
                        && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '0' 
                        || BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '2' 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '1' 
                        && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT < SCCGWA.COMM_AREA.AC_DATE 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_TM < SCCGWA.COMM_AREA.TR_TIME)) 
                        && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT > SCCGWA.COMM_AREA.AC_DATE) 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '3' 
                        && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                        || BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT < SCCGWA.COMM_AREA.AC_DATE 
                        || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                        && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_TM < SCCGWA.COMM_AREA.TR_TIME)))) {
                        CEP.TRC(SCCGWA, "F2");
                        BPCFOTTA.PRIV_FLG = 'Y';
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B030_CHECK_GRPL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRGRPL);
        IBS.init(SCCGWA, BPRGRPL);
        BPCRGRPL.INFO.FUNC = 'Q';
        BPRGRPL.KEY.ASS_TYP = BPCFOTTA.ASSTYP;
        BPRGRPL.KEY.ASS_ID = BPCFOTTA.ASS_ID;
        BPRGRPL.KEY.ATH_TYP = BPCFOTTA.ATH_TYP;
        S000_CALL_BPZRGRPL();
        if (pgmRtn) return;
        if (BPCRGRPL.RETURN_INFO == 'F') {
            for (WS_I = 1; WS_I <= BPRGRPL.ROLE_CNT 
                && BPCFOTTA.PRIV_FLG != 'Y'; WS_I += 1) {
                if (SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase("HDCPT61") 
                    || SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase("HDCPT62")) {
                    if (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EFF_DT <= 20140508 
                        && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EXP_DT > 20140508 
                        && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '0' 
                        || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '2' 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '1' 
                        && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT < 20140508 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == 20140508 
                        && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM < 180000)) 
                        && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT > 20140508) 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == 20140508 
                        && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM > 180000)) 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '3' 
                        && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT > 20140508 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == 20140508 
                        && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM > 180000)) 
                        || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT < 20140508 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == 20140508 
                        && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM < 180000)))) {
                        IBS.init(SCCGWA, BPCRTROL);
                        IBS.init(SCCGWA, BPRTROL);
                        BPCRTROL.INFO.FUNC = 'Q';
                        BPRTROL.KEY.IN_FLG = BPCFOTTA.IN_FLG;
                        BPRTROL.KEY.SYS_MMO = BPCFOTTA.SYS_MMO;
                        BPRTROL.KEY.TX_CD = BPCFOTTA.TX_CD;
                        BPRTROL.KEY.ATH_TYP = BPCFOTTA.ATH_TYP;
                        BPRTROL.KEY.ROLE_CD = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD;
                        S000_CALL_BPZRTROL();
                        if (pgmRtn) return;
                        if (BPCRTROL.RETURN_INFO == 'F' 
                            && BPRTROL.EFF_DT <= 20130508 
                            && BPRTROL.EXP_DT > 20130508) {
                            CEP.TRC(SCCGWA, "F3");
                            BPCFOTTA.PRIV_FLG = 'Y';
                            Z_RET();
                            if (pgmRtn) return;
                        }
                    }
                } else {
                    if (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                        && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EXP_DT > SCCGWA.COMM_AREA.AC_DATE 
                        && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '0' 
                        || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '2' 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '1' 
                        && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT < SCCGWA.COMM_AREA.AC_DATE 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                        && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM < SCCGWA.COMM_AREA.TR_TIME)) 
                        && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT > SCCGWA.COMM_AREA.AC_DATE) 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                        && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '3' 
                        && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                        && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                        || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT < SCCGWA.COMM_AREA.AC_DATE 
                        || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                        && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM < SCCGWA.COMM_AREA.TR_TIME)))) {
                        IBS.init(SCCGWA, BPCRTROL);
                        IBS.init(SCCGWA, BPRTROL);
                        BPCRTROL.INFO.FUNC = 'Q';
                        BPRTROL.KEY.IN_FLG = BPCFOTTA.IN_FLG;
                        BPRTROL.KEY.SYS_MMO = BPCFOTTA.SYS_MMO;
                        BPRTROL.KEY.TX_CD = BPCFOTTA.TX_CD;
                        BPRTROL.KEY.ATH_TYP = BPCFOTTA.ATH_TYP;
                        BPRTROL.KEY.ROLE_CD = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD;
                        S000_CALL_BPZRTROL();
                        if (pgmRtn) return;
                        if (BPCRTROL.RETURN_INFO == 'F' 
                            && BPRTROL.EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                            && BPRTROL.EXP_DT > SCCGWA.COMM_AREA.AC_DATE) {
                            CEP.TRC(SCCGWA, "F3");
                            BPCFOTTA.PRIV_FLG = 'Y';
                            Z_RET();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
        }
    }
    public void S000_CALL_BPZRTXIF() throws IOException,SQLException,Exception {
        BPCRTXIF.INFO.POINTER = BPRTXIF;
        BPCRTXIF.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TXIF       ", BPCRTXIF);
        CEP.TRC(SCCGWA, BPCRTXIF.RC);
        if (BPCRTXIF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTXIF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFOTTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTATH() throws IOException,SQLException,Exception {
        BPCRTATH.INFO.POINTER = BPRTATH;
        BPCRTATH.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TATH       ", BPCRTATH);
        if (BPCRTATH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTATH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFOTTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTROL() throws IOException,SQLException,Exception {
        BPCRTROL.INFO.POINTER = BPRTROL;
        BPCRTROL.INFO.LEN = 50;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TROL       ", BPCRTROL);
        if (BPCRTROL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTROL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFOTTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRGRPL() throws IOException,SQLException,Exception {
        BPCRGRPL.INFO.POINTER = BPRGRPL;
        BPCRGRPL.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GRPL       ", BPCRGRPL);
        if (BPCRGRPL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRGRPL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFOTTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFOTTA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFOTTA = ");
            CEP.TRC(SCCGWA, BPCFOTTA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
