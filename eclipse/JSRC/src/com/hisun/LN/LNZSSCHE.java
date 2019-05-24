package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSSCHE {
    int JIBS_tmp_int;
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTICTL_RD;
    DBParm LNTPAIP_RD;
    DBParm LNTPLPI_RD;
    brParm LNTSCHT_BR = new brParm();
    brParm LNTRCVD_BR = new brParm();
    brParm LNTPLPI_BR = new brParm();
    DBParm LNTPYIF_RD;
    DBParm LNTSCHT_RD;
    brParm LNTRELA_BR = new brParm();
    DBParm LNTSUBS_RD;
    DBParm LNTSEAJ_RD;
    DBParm LNTINTA_RD;
    boolean pgmRtn = false;
    char RATE_TYP_NORMAL = 'N';
    String SCSSCLDT = "SCSSCLDT";
    String SCSSCKDT = "SCSSCKDT";
    short MAX_CNT = 99;
    String SIMULATION_INFO = "SIMULATION";
    short PAGE_ROW = 10;
    LNZSSCHE_WS_VARIABLES WS_VARIABLES = new LNZSSCHE_WS_VARIABLES();
    LNZSSCHE_WS_OUT_RECODE WS_OUT_RECODE = new LNZSSCHE_WS_OUT_RECODE();
    LNZSSCHE_WS_COND_FLG WS_COND_FLG = new LNZSSCHE_WS_COND_FLG();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRICTL LNRICTL = new LNRICTL();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRRATN LNRRATN = new LNRRATN();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRSCHT LNRSCHT = new LNRSCHT();
    LNRPYIF LNRPYIF = new LNRPYIF();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    LNCSCALT LNCSCALT = new LNCSCALT();
    LNRINTA LNRINTA = new LNRINTA();
    LNCRINTA LNCRINTA = new LNCRINTA();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNCRLOAN LNCRLOAN = new LNCRLOAN();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNCRRATN LNCRRATN = new LNCRRATN();
    LNCRPAIP LNCRPAIP = new LNCRPAIP();
    LNCICUT LNCICUT = new LNCICUT();
    LNCRATQ LNCRATQ = new LNCRATQ();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCSLNQ LNCSLNQ = new LNCSLNQ();
    LNCICAL LNCICAL = new LNCICAL();
    LNCILCM LNCILCM = new LNCILCM();
    LNRRELA LNRRELA = new LNRRELA();
    LNRSUBS LNRSUBS = new LNRSUBS();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNRTMPP LNRTMPP = new LNRTMPP();
    LNCRTMPP LNCRTMPP = new LNCRTMPP();
    LNCRSCHT LNCRSCHT = new LNCRSCHT();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCFCPL LNCFCPL = new LNCFCPL();
    LNCENSCH LNCENSCH = new LNCENSCH();
    LNCSUBP LNCSUBP = new LNCSUBP();
    LNCRATB LNCRATB = new LNCRATB();
    LNRSEAJ LNRSEAJ = new LNRSEAJ();
    LNRSEAJ_WS_DB_VARS WS_DB_VARS = new LNRSEAJ_WS_DB_VARS();
    SCCGWA SCCGWA;
    LNCSSCHE LNCSSCHE;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCSSCHE LNCSSCHE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSSCHE = LNCSSCHE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSSCHE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_DB_VARS);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, WS_OUT_RECODE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSSCHE.DATA_AREA.CONTRACT_NO);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_CALL_ICTL_PROC();
        if (pgmRtn) return;
        B030_CALL_APRD_PROC();
        if (pgmRtn) return;
        B040_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSSCHE.DATA_AREA.SUF_NO);
        B021_GET_CONTRACT_REC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CONTRACT_TYPE);
        if (LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDD")) {
            B010_00_QUERY_LOAN();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN0392;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_00_QUERY_LOAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRLOAN);
        IBS.init(SCCGWA, LNCRLOAN);
        LNCRLOAN.FUNC = 'I';
        LNRLOAN.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNCRLOAN.REC_PTR = LNRLOAN;
        LNCRLOAN.REC_LEN = 550;
        S000_CALL_LNZRLOAN();
        if (pgmRtn) return;
        if (LNCRLOAN.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CTA_TYP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_VARIABLES.EMBEZ_DATE = LNRLOAN.EMBEZ_DATE;
        WS_VARIABLES.EMBEZ_CANCEL_DATE = LNRLOAN.EMBEZ_CANCEL_DATE;
    }
    public void B020_CALL_ICTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRICTL.KEY.SUB_CTA_NO = 0;
        else LNRICTL.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNCRICTL.FUNC = 'I';
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 830;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_HSA_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_HAS_DELETION;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.XBD_FLG = 'Y';
        }
        WS_VARIABLES.CUR_TERM = LNRICTL.IC_CAL_TERM;
    }
    public void B030_CALL_APRD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNCAPRDM.FUNC = '3';
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        WS_VARIABLES.APRD_PAY_DAY = LNCAPRDM.REC_DATA.PAY_DAY;
        if (LNCSSCHE.DATA_AREA.PAY_TYP == ' ' 
            && LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            LNCSSCHE.DATA_AREA.PAY_TYP = 'C';
        }
    }
    public void B040_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSSCHE.SEQ_NO);
        if (LNCSSCHE.SEQ_NO == 0) {
            S000_GEN_JRNNO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
            WS_VARIABLES.SEQ_TMP = SCCGWA.COMM_AREA.JRN_NO;
        } else {
            WS_VARIABLES.SEQ_TMP = LNCSSCHE.SEQ_NO;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.SEQ_TMP);
        CEP.TRC(SCCGWA, LNRICTL.IC_CUR_TERM);
        CEP.TRC(SCCGWA, LNRICTL.P_CUR_TERM);
        T00_STARTBR_LNTRELA();
        if (pgmRtn) return;
        T00_READNEXT_LNTRELA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRSUBS);
        if (WS_COND_FLG.RELA_FLAG == 'Y') {
            IBS.init(SCCGWA, LNRSUBS);
            LNRSUBS.KEY.PROJ_NO = LNRRELA.PROJ_NO;
            T000_READ_LNTSUBS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRRELA.ST_TERM);
            CEP.TRC(SCCGWA, LNRRELA.TERM);
            WS_VARIABLES.ST_TERM = LNRRELA.ST_TERM;
            WS_VARIABLES.SUBTERM = (short) (LNRRELA.ST_TERM + LNRRELA.TERM - 1);
        } else {
            WS_VARIABLES.ST_TERM = 0;
            WS_VARIABLES.SUBTERM = 0;
        }
        T00_ENDBR_LNTRELA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQUDUP";
        LNCCNEX.COMM_DATA.LN_AC = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = "" + WS_VARIABLES.SUB_CTA_NO;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        WS_VARIABLES.CALC_INT_BAL = LNCCNEX.COMM_DATA.INQ_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.CALC_INT_BAL);
        if (LNCAPRDM.REC_DATA.PAY_MTH != '4' 
            && LNCSSCHE.SEQ_NO == 0) {
            B999_00_PRE_PROC_OF_OUTPUT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSSCHE.DATA_AREA.PAY_TYP);
        if (LNCSSCHE.DATA_AREA.PAY_TYP == 'C' 
            && LNCAPRDM.REC_DATA.PAY_MTH != '4') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_PAY_TYP_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_COND_FLG.SPCL_PROC_FLAG = 'N';
        CEP.TRC(SCCGWA, LNCSSCHE.DATA_AREA.PAY_TYP);
        CEP.TRC(SCCGWA, "201810171111");
        if (LNCSSCHE.DATA_AREA.PAY_TYP == 'I') {
            B031_BRW_INT_PLAN();
            if (pgmRtn) return;
        } else if (LNCSSCHE.DATA_AREA.PAY_TYP == 'P') {
            B032_BRW_PRIN_PLAN();
            if (pgmRtn) return;
        } else if (LNCSSCHE.DATA_AREA.PAY_TYP == ' ') {
            B033_BRW_ALL_PLAN();
            if (pgmRtn) return;
        } else if (LNCSSCHE.DATA_AREA.PAY_TYP == 'C') {
            B035_BRW_INST_PLAN();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_PAY_TYP_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[1-1]);
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[2-1]);
    }
    public void B999_00_PRE_PROC_OF_OUTPUT() throws IOException,SQLException,Exception {
        if (LNCAPRDM.REC_DATA.PAY_MTH == '1' 
            || LNCAPRDM.REC_DATA.PAY_MTH == '2' 
            || LNCAPRDM.REC_DATA.PAY_MTH == '3' 
            || LNCAPRDM.REC_DATA.PAY_MTH == '5') {
            WS_VARIABLES.REPY_MTH = 'I';
            T000_STARTBR_PLPI_DUE_DT_DSC();
            if (pgmRtn) return;
            T000_READNEXT_LNTPLPI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                if (LNCAPRDM.REC_DATA.PAY_MTH == '1') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_PLPI_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    SCCCLDT.DATE1 = LNCCONTM.REC_DATA.START_DATE;
                    SCCCLDT.DATE2 = LNRLOAN.FST_CAL_DT;
                }
            } else {
                CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = LNRPLPI.DUE_DT;
                if (LNCAPRDM.REC_DATA.PAY_MTH != '1') {
                    if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'D') {
                        SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD;
                    }
                    if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'M') {
                        SCCCLDT.MTHS = LNCAPRDM.REC_DATA.CAL_PERD;
                    }
                    S000_CALL_SCSSCLDT();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                }
            }
            T000_ENDBR_PLPI();
            if (pgmRtn) return;
            if (SCCCLDT.DATE2 < LNRICTL.IC_CAL_DUE_DT) {
                SCCCLDT.DATE2 = LNRICTL.IC_CAL_DUE_DT;
            }
            if (SCCCLDT.DATE2 > LNCCONTM.REC_DATA.MAT_DATE) {
                SCCCLDT.DATE2 = LNCCONTM.REC_DATA.MAT_DATE;
            }
            if (LNCSSCHE.CUR_FLG != 'Y') {
                if (SCCCLDT.DATE1 < LNCCONTM.REC_DATA.MAT_DATE 
                    && LNCAPRDM.REC_DATA.CAL_PERD > 0 
                    && LNCAPRDM.REC_DATA.CAL_PERD_UNIT > SPACE) {
                    IBS.init(SCCGWA, LNCENSCH);
                    LNCENSCH.COMM_DATA.CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
                    LNCENSCH.COMM_DATA.SUB_CTA_NO = 0;
                    LNCENSCH.COMM_DATA.REPY_TYP = 'I';
                    LNCENSCH.COMM_DATA.IFNP_DAT = SCCCLDT.DATE2;
                    LNCENSCH.COMM_DATA.IFRE_TERM = LNCAPRDM.REC_DATA.CAL_PERD;
                    CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.CAL_PERD);
                    LNCENSCH.COMM_DATA.IFRE_TYP = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
                    CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.CAL_PERD_UNIT);
                    LNCENSCH.COMM_DATA.SEQ = WS_VARIABLES.SEQ_TMP;
                    S000_CALL_LNZENSCH();
                    if (pgmRtn) return;
                } else {
                    WS_VARIABLES.REPY_MTH = 'I';
                    WS_VARIABLES.REPAY_TERM = LNRICTL.IC_CAL_TERM;
                    B999_00_MOVE_PLPI_TO_SCHT();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, "AAAAA");
                if (!(!(LNRICTL.IC_CAL_VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
                    && LNRICTL.IC_CAL_DUE_DT > SCCGWA.COMM_AREA.AC_DATE) 
                    || SCCGWA.COMM_AREA.AC_DATE >= LNCCONTM.REC_DATA.MAT_DATE)) {
                    CEP.TRC(SCCGWA, LNCSSCHE.DATA_AREA.CONTRACT_NO);
                    CEP.TRC(SCCGWA, LNRICTL.IC_CAL_TERM);
                    IBS.init(SCCGWA, LNRPLPI);
                    LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
                    if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
                    else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
                    LNRPLPI.KEY.REPY_MTH = 'I';
                    LNRPLPI.KEY.TERM = LNRICTL.IC_CAL_TERM;
                    T000_READ_LNTPLPI_P();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        CEP.TRC(SCCGWA, LNRICTL.IC_CAL_TERM);
                        CEP.TRC(SCCGWA, LNRPLPI.KEY.CONTRACT_NO);
                        CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
                        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
                        CEP.TRC(SCCGWA, LNRPLPI.VAL_DT);
                        CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
                        LNRRCVD.KEY.AMT_TYP = LNRPLPI.KEY.REPY_MTH;
                        LNRRCVD.KEY.TERM = LNRPLPI.KEY.TERM;
                        LNRRCVD.VAL_DT = LNRPLPI.VAL_DT;
                        LNRRCVD.DUE_DT = LNRPLPI.DUE_DT;
                        LNRRCVD.ALL_IN_RATE = 0;
                        WS_VARIABLES.PAY_AMT_PRIN = LNRPLPI.PRIN_AMT;
                        WS_VARIABLES.PAY_AMT = LNRPLPI.PRIN_AMT;
                        WS_VARIABLES.PAY_AMT_INT = 0;
                        WS_VARIABLES.REMARK = LNRPLPI.REMARK;
                        B220_CALL_LNZRSCHT_2();
                        if (pgmRtn) return;
                    }
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                        LNRRCVD.KEY.AMT_TYP = 'I';
                        LNRRCVD.KEY.TERM = LNRICTL.IC_CAL_TERM;
                        LNRRCVD.VAL_DT = LNRICTL.IC_CAL_VAL_DT;
                        LNRRCVD.DUE_DT = LNRICTL.IC_CAL_DUE_DT;
                        LNRRCVD.ALL_IN_RATE = 0;
                        WS_VARIABLES.PAY_AMT_PRIN = 0;
                        WS_VARIABLES.PAY_AMT = 0;
                        WS_VARIABLES.PAY_AMT_INT = 0;
                        WS_VARIABLES.REMARK = " ";
                        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
                        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
                        CEP.TRC(SCCGWA, LNRRCVD.VAL_DT);
                        CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
                        B220_CALL_LNZRSCHT_2();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
            || LNCAPRDM.REC_DATA.PAY_MTH == '1' 
            || LNCAPRDM.REC_DATA.PAY_MTH == '2' 
            || LNCAPRDM.REC_DATA.PAY_MTH == '3' 
            || LNCAPRDM.REC_DATA.PAY_MTH == '5') {
            WS_VARIABLES.REPY_MTH = 'I';
            T000_STARTBR_PLPI_DUE_DT_DSC2();
            if (pgmRtn) return;
            T000_READNEXT_LNTPLPI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_VARIABLES.SCH_STR_DT = LNCCONTM.REC_DATA.START_DATE;
            } else {
                WS_VARIABLES.SCH_STR_DT = LNRPLPI.DUE_DT;
            }
            T000_ENDBR_PLPI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_VARIABLES.SCH_STR_DT);
            if (LNCAPRDM.REC_DATA.PAY_MTH == '3') {
                B211_GET_REPAY_AMT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT);
                WS_VARIABLES.NO_SCH_P_AMT = LNCCONTM.REC_DATA.LN_TOT_AMT - LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
            }
            CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.MAT_DATE);
            CEP.TRC(SCCGWA, WS_VARIABLES.NO_SCH_P_AMT);
            if (LNCSSCHE.CUR_FLG != 'Y') {
                if (WS_VARIABLES.SCH_STR_DT < LNCCONTM.REC_DATA.MAT_DATE 
                    && WS_VARIABLES.NO_SCH_P_AMT > 0 
                    && LNCAPRDM.REC_DATA.BPAYP_PERD > '0' 
                    && LNCAPRDM.REC_DATA.PAYP_PERD_UNIT > SPACE) {
                    IBS.init(SCCGWA, LNCENSCH);
                    LNCENSCH.COMM_DATA.CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
                    LNCENSCH.COMM_DATA.SUB_CTA_NO = 0;
                    LNCENSCH.COMM_DATA.REPY_TYP = 'P';
                    LNCENSCH.COMM_DATA.PFNP_DAT = LNRICTL.P_CMP_DUE_DT;
                    if (LNCAPRDM.REC_DATA.BPAYP_PERD == ' ') LNCENSCH.COMM_DATA.PFRE_TERM = 0;
                    else LNCENSCH.COMM_DATA.PFRE_TERM = Short.parseShort(""+LNCAPRDM.REC_DATA.BPAYP_PERD);
                    LNCENSCH.COMM_DATA.PFRE_TERM = LNCAPRDM.REC_DATA.PAYP_PERD;
                    CEP.TRC(SCCGWA, "SHI");
                    CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.BPAYP_PERD);
                    LNCENSCH.COMM_DATA.PFRE_TYP = LNCAPRDM.REC_DATA.PAYP_PERD_UNIT;
                    CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.PAYP_PERD_UNIT);
                    LNCENSCH.COMM_DATA.SEQ = WS_VARIABLES.SEQ_TMP;
                    S000_CALL_LNZENSCH();
                    if (pgmRtn) return;
                } else {
                    WS_VARIABLES.REPY_MTH = 'P';
                    WS_VARIABLES.REPAY_TERM = LNRICTL.P_CAL_TERM;
                    B999_00_MOVE_PLPI_TO_SCHT();
                    if (pgmRtn) return;
                }
            } else {
                IBS.init(SCCGWA, LNRPLPI);
                LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
                if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
                else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
                T000_READ_PLPI_MAX_PROC();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_VARIABLES.PLPI_VAL_DT = LNRPLPI.DUE_DT;
                } else {
                    WS_VARIABLES.PLPI_VAL_DT = LNCCONTM.REC_DATA.START_DATE;
                }
                WS_VARIABLES.PLPI_DUE_DT = LNRICTL.P_CAL_DUE_DT;
                if (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
                    || LNCAPRDM.REC_DATA.PAY_MTH == '1' 
                    || LNCAPRDM.REC_DATA.PAY_MTH == '2') {
                    WS_VARIABLES.PLPI_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
                }
                CEP.TRC(SCCGWA, WS_VARIABLES.PLPI_VAL_DT);
                CEP.TRC(SCCGWA, WS_VARIABLES.PLPI_DUE_DT);
                if (!(!(WS_VARIABLES.PLPI_VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
                    && WS_VARIABLES.PLPI_DUE_DT > SCCGWA.COMM_AREA.AC_DATE) 
                    || SCCGWA.COMM_AREA.AC_DATE >= LNCCONTM.REC_DATA.MAT_DATE)) {
                    IBS.init(SCCGWA, LNRPLPI);
                    LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
                    if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
                    else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
                    LNRPLPI.KEY.REPY_MTH = 'P';
                    LNRPLPI.KEY.TERM = LNRICTL.P_CAL_TERM;
                    T000_READ_LNTPLPI_P();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
                        CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
                        CEP.TRC(SCCGWA, LNRPLPI.VAL_DT);
                        CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
                        LNRRCVD.KEY.AMT_TYP = LNRPLPI.KEY.REPY_MTH;
                        LNRRCVD.KEY.TERM = LNRPLPI.KEY.TERM;
                        LNRRCVD.VAL_DT = LNRPLPI.VAL_DT;
                        LNRRCVD.DUE_DT = LNRPLPI.DUE_DT;
                        LNRRCVD.ALL_IN_RATE = 0;
                        WS_VARIABLES.PAY_AMT_PRIN = LNRPLPI.PRIN_AMT;
                        WS_VARIABLES.PAY_AMT = LNRPLPI.PRIN_AMT;
                        WS_VARIABLES.PAY_AMT_INT = 0;
                        WS_VARIABLES.REMARK = LNRPLPI.REMARK;
                        B220_CALL_LNZRSCHT_2();
                        if (pgmRtn) return;
                    }
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
                        && LNCAPRDM.REC_DATA.PAY_MTH != '5') {
                        IBS.init(SCCGWA, LNCSCALT);
                        LNCSCALT.INPUT.CAL_FST_DT = LNRICTL.P_CAL_DUE_DT;
                        LNCSCALT.INPUT.VAL_DT = WS_VARIABLES.PLPI_VAL_DT;
                        LNCSCALT.INPUT.DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
                        LNCSCALT.INPUT.PHS_FLG = 'N';
                        LNCSCALT.INPUT.PHS_NUM = 0;
                        if (LNCAPRDM.REC_DATA.BPAYP_PERD == ' ') LNCSCALT.INPUT.CAL_PERD = 0;
                        else LNCSCALT.INPUT.CAL_PERD = Short.parseShort(""+LNCAPRDM.REC_DATA.BPAYP_PERD);
                        LNCSCALT.INPUT.CAL_PERD_UNIT = LNCAPRDM.REC_DATA.PAYP_PERD_UNIT;
                        LNCSCALT.INPUT.CAL_PAY_DAY = LNCAPRDM.REC_DATA.PAY_DAY;
                        LNCSCALT.INPUT.CAL_FST_FLG = LNCAPRDM.REC_DATA.FST_PAY_FLG;
                        LNCSCALT.INPUT.REPY_MTH = LNCAPRDM.REC_DATA.PAY_MTH;
                        S000_CALL_LNZSCALT();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, LNCSCALT.OUTPUT.INST_TERM);
                        if (LNCSCALT.OUTPUT.INST_TERM > 0) {
                            IBS.init(SCCGWA, LNRPLPI);
                            LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
                            if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
                            else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
                            LNRPLPI.KEY.REPY_MTH = 'P';
                            LNRPLPI.REC_STS = '1';
                            T000_GET_PLPI_SUM_AMT();
                            if (pgmRtn) return;
                            WS_VARIABLES.OS_IPLN_AMT = LNCCONTM.REC_DATA.LN_TOT_AMT - WS_DB_VARS.TOT_P_AMT;
                            WS_VARIABLES.ORIGN_REPY_AMT = WS_VARIABLES.OS_IPLN_AMT / LNCSCALT.OUTPUT.INST_TERM;
                            bigD = new BigDecimal(WS_VARIABLES.ORIGN_REPY_AMT);
                            WS_VARIABLES.ORIGN_REPY_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
                            WS_VARIABLES.LAST_TERM_AMT = WS_VARIABLES.OS_IPLN_AMT - WS_VARIABLES.ORIGN_REPY_AMT * ( LNCSCALT.OUTPUT.INST_TERM - 1 );
                            CEP.TRC(SCCGWA, WS_VARIABLES.OS_IPLN_AMT);
                            CEP.TRC(SCCGWA, WS_VARIABLES.ORIGN_REPY_AMT);
                            CEP.TRC(SCCGWA, WS_VARIABLES.LAST_TERM_AMT);
                        }
                        LNRRCVD.KEY.AMT_TYP = 'P';
                        LNRRCVD.KEY.TERM = LNRICTL.P_CAL_TERM;
                        LNRRCVD.VAL_DT = LNCSCALT.INPUT.VAL_DT;
                        LNRRCVD.DUE_DT = LNRICTL.P_CAL_DUE_DT;
                        LNRRCVD.ALL_IN_RATE = 0;
                        if (LNCSCALT.OUTPUT.INST_TERM == 1) {
                            WS_VARIABLES.PAY_AMT_PRIN = WS_VARIABLES.LAST_TERM_AMT;
                        } else {
                            WS_VARIABLES.PAY_AMT_PRIN = WS_VARIABLES.ORIGN_REPY_AMT;
                        }
                        WS_VARIABLES.PAY_AMT = WS_VARIABLES.PAY_AMT_PRIN;
                        WS_VARIABLES.PAY_AMT_INT = 0;
                        WS_VARIABLES.REMARK = " ";
                        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
                        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
                        CEP.TRC(SCCGWA, LNRRCVD.VAL_DT);
                        CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
                        CEP.TRC(SCCGWA, WS_VARIABLES.PAY_AMT);
                        B220_CALL_LNZRSCHT_2();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B211_GET_REPAY_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQRCVP";
        LNCCNEX.COMM_DATA.LN_AC = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void B999_00_MOVE_PLPI_TO_SCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = WS_VARIABLES.REPY_MTH;
        LNRPLPI.KEY.TERM = WS_VARIABLES.REPAY_TERM;
        T000_STARTBR_LNTPLPI();
        if (pgmRtn) return;
        T000_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
            CEP.TRC(SCCGWA, LNRPLPI.VAL_DT);
            CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
            CEP.TRC(SCCGWA, LNRPLPI.PRIN_AMT);
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                LNRRCVD.KEY.AMT_TYP = LNRPLPI.KEY.REPY_MTH;
                LNRRCVD.KEY.TERM = LNRPLPI.KEY.TERM;
                LNRRCVD.VAL_DT = LNRPLPI.VAL_DT;
                LNRRCVD.DUE_DT = LNRPLPI.DUE_DT;
                LNRRCVD.ALL_IN_RATE = 0;
                WS_VARIABLES.PAY_AMT_PRIN = LNRPLPI.PRIN_AMT;
                WS_VARIABLES.PAY_AMT = LNRPLPI.PRIN_AMT;
                WS_VARIABLES.PAY_AMT_INT = 0;
                WS_VARIABLES.REMARK = LNRPLPI.REMARK;
                B220_CALL_LNZRSCHT_2();
                if (pgmRtn) return;
                T000_READNEXT_LNTPLPI();
                if (pgmRtn) return;
            }
        }
        T000_ENDBR_PLPI();
        if (pgmRtn) return;
    }
    public void B031_BRW_INT_PLAN() throws IOException,SQLException,Exception {
        B401_COMPUTE_PAGE_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
        WS_VARIABLES.PAY_TYP = 'I';
        CEP.TRC(SCCGWA, LNCSSCHE.CUR_FLG);
        CEP.TRC(SCCGWA, LNRICTL.IC_CAL_TERM);
        CEP.TRC(SCCGWA, LNRICTL.IC_CUR_TERM);
        if (LNCSSCHE.CUR_FLG == 'Y') {
            WS_VARIABLES.REPAY_TERM = LNRICTL.IC_CAL_TERM;
        } else {
            WS_VARIABLES.REPAY_TERM = LNRICTL.IC_CUR_TERM;
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH != '0') {
            WS_COND_FLG.RCVD_FLAG = 'N';
            if (LNCSSCHE.CUR_FLG != 'Y' 
                && LNCSSCHE.CUR_FLG != 'M') {
                R000_STARTBR_LNTRCVD();
                if (pgmRtn) return;
                R000_READNEXT_LNTRCVD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_COND_FLG.RCVD_FLAG);
                if (WS_COND_FLG.RCVD_FLAG != 'Y') {
                    while (WS_COND_FLG.RCVD_FLAG != 'Y') {
                        if (LNRRCVD.DUE_DT == SCCGWA.COMM_AREA.AC_DATE) {
                            WS_VARIABLES.PAY_AMT_INT = LNRRCVD.I_REC_AMT;
                            if (LNRRCVD.I_WAV_AMT > 0) {
                                WS_VARIABLES.PAY_AMT_INT -= LNRRCVD.I_WAV_AMT;
                            }
                            if (LNRRCVD.I_PAY_AMT > 0) {
                                WS_VARIABLES.PAY_AMT_INT -= LNRRCVD.I_PAY_AMT;
                            }
                            WS_VARIABLES.PAY_AMT = WS_VARIABLES.PAY_AMT_INT;
                            WS_VARIABLES.PAY_AMT_PRIN = 0;
                            WS_VARIABLES.REMARK = " ";
                            if (LNRICTL.IC_CUR_TERM < LNRICTL.IC_CAL_TERM) {
                                B220_CALL_LNZRSCHT_2();
                                if (pgmRtn) return;
                            }
                        }
                        R000_READNEXT_LNTRCVD();
                        if (pgmRtn) return;
                    }
                }
                R000_ENDBR_LNTRCVD();
                if (pgmRtn) return;
            }
            WS_COND_FLG.SCHT_FLAG = 'N';
            if (LNCSSCHE.CUR_FLG == 'Y' 
                && ((LNRICTL.IC_CAL_VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
                && LNRICTL.IC_CAL_DUE_DT > SCCGWA.COMM_AREA.AC_DATE) 
                || SCCGWA.COMM_AREA.AC_DATE >= LNCCONTM.REC_DATA.MAT_DATE)) {
                IBS.init(SCCGWA, LNCSSCHE.OUT_INFO[1-1]);
                IBS.init(SCCGWA, LNCSSCHE.OUT_INFO[2-1]);
                IBS.init(SCCGWA, LNRPLPI);
                IBS.init(SCCGWA, LNRSCHT);
                IBS.init(SCCGWA, LNCRSCHT);
                IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[1-1]);
                IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[2-1]);
                WS_COND_FLG.SCHT_FLAG = 'Y';
            } else {
                T000_STARTBR_LNTSCHT();
                if (pgmRtn) return;
                T000_READNEXT_LNTSCHT();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_COND_FLG.SCHT_FLAG);
            if (WS_COND_FLG.SCHT_FLAG != 'Y') {
                for (WS_VARIABLES.WS_DATA.IDX = 1; (WS_VARIABLES.WS_DATA.IDX <= WS_VARIABLES.WS_DATA.PAGE_ROW) 
                    && WS_COND_FLG.SCHT_FLAG != 'Y'; WS_VARIABLES.WS_DATA.IDX += 1) {
                    CEP.TRC(SCCGWA, LNRSCHT.KEY.TERM);
                    if (LNRSCHT.KEY.TERM < LNRICTL.IC_CAL_TERM) {
                        WS_VARIABLES.PAY_AMT_INT = LNRSCHT.PAY_INT;
                    } else {
                        B031_01_CALL_ICUT_PROC();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
                        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.RATE);
                        WS_VARIABLES.PAY_AMT_INT = LNCICUT.COMM_DATA.INT_AMT;
                        LNRSCHT.ALL_IN_RATE = LNCICUT.COMM_DATA.RATE;
                        if (LNCAPRDM.REC_DATA.PAY_MTH == '3' 
                            || LNCAPRDM.REC_DATA.PAY_MTH == '5') {
                            WS_VARIABLES.SCHT_TERM = LNRSCHT.KEY.TERM;
                            WS_VARIABLES.SCHT_VAL_DTE = LNRSCHT.VAL_DTE;
                            WS_VARIABLES.SCHT_TYPE = 'P';
                            WS_VARIABLES.SCHT_DUE_DTE = LNRSCHT.DUE_DTE;
                            T000_READ_LNTSCHT_BY_DUE_DTE();
                            if (pgmRtn) return;
                            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                                WS_VARIABLES.CALC_INT_BAL = WS_VARIABLES.CALC_INT_BAL - LNRSCHT.PAY_PRIN;
                            }
                            LNRSCHT.KEY.TYPE = 'I';
                            LNRSCHT.KEY.TERM = WS_VARIABLES.SCHT_TERM;
                            LNRSCHT.VAL_DTE = WS_VARIABLES.SCHT_VAL_DTE;
                        }
                    }
                    WS_VARIABLES.PAY_AMT = WS_VARIABLES.PAY_AMT_INT;
                    WS_VARIABLES.PAY_AMT_PRIN = 0;
                    R010_TRANS_OUTPUT_DATA_SCHT();
                    if (pgmRtn) return;
                    R010_TRANS_OUTPUT_DATA_SCHT_C();
                    if (pgmRtn) return;
                    WS_VARIABLES.WS_DATA.NEXT_START_NUM += 1;
                    WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATA.NEXT_START_NUM;
                    T000_READNEXT_LNTSCHT();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, WS_COND_FLG.SCHT_FLAG);
                if (WS_COND_FLG.SCHT_FLAG == 'Y') {
                    WS_VARIABLES.WS_DATA.TOTAL_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
                    WS_VARIABLES.WS_DATA.BAL_CNT = WS_VARIABLES.WS_DATA.IDX - 1;
                    WS_DB_VARS.TOTAL_NUM = ( WS_VARIABLES.WS_DATA.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATA.PAGE_ROW + WS_VARIABLES.WS_DATA.BAL_CNT;
                    WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
                    WS_VARIABLES.WS_DATA.PAGE_ROW = (short) WS_VARIABLES.WS_DATA.BAL_CNT;
                } else {
                    T000_GET_TOTAL_LNTSCHT_P_OR_I();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_DB_VARS.TOTAL_NUM);
                    if (WS_VARIABLES.WS_DATA.PAGE_ROW == 0) {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_PAGE_ROW;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    WS_VARIABLES.WS_DATA.BAL_CNT = WS_DB_VARS.TOTAL_NUM % WS_VARIABLES.WS_DATA.PAGE_ROW;
                    WS_VARIABLES.WS_DATA.TOTAL_PAGE = (short) ((WS_DB_VARS.TOTAL_NUM - WS_VARIABLES.WS_DATA.BAL_CNT) / WS_VARIABLES.WS_DATA.PAGE_ROW);
                    if (WS_VARIABLES.WS_DATA.BAL_CNT != 0) {
                        WS_VARIABLES.WS_DATA.TOTAL_PAGE += 1;
                    }
                }
            } else {
                WS_VARIABLES.WS_DATA.TOTAL_PAGE = 1;
                WS_DB_VARS.TOTAL_NUM = 0;
                WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATA.PAGE_ROW = 0;
            }
            if (LNCSSCHE.CUR_FLG == 'Y' 
                && ((LNRICTL.IC_CAL_VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
                && LNRICTL.IC_CAL_DUE_DT > SCCGWA.COMM_AREA.AC_DATE) 
                || SCCGWA.COMM_AREA.AC_DATE >= LNCCONTM.REC_DATA.MAT_DATE)) {
            } else {
                T103_ENDBR_LNTSCHT();
                if (pgmRtn) return;
            }
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATA.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_DB_VARS.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATA.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.WS_DATA.PAGE_ROW;
        R020_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B031_01_CALL_ICUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNCICUT.COMM_DATA.SUF_NO = LNCSSCHE.DATA_AREA.SUF_NO;
        LNCICUT.COMM_DATA.TERM = LNRSCHT.KEY.TERM;
        LNCICUT.COMM_DATA.TYPE = 'I';
        if (LNCAPRDM.REC_DATA.PAY_MTH == '5') {
            LNCICUT.COMM_DATA.AMT = 0;
        } else {
            if (LNRSCHT.KEY.TERM == LNRICTL.IC_CAL_TERM) {
                LNCICUT.COMM_DATA.AMT = 0;
            } else {
                if (WS_COND_FLG.SPCL_PROC_FLAG == 'Y') {
                    CEP.TRC(SCCGWA, WS_VARIABLES.LST_CALC_INT_BAL);
                    LNCICUT.COMM_DATA.AMT = WS_VARIABLES.LST_CALC_INT_BAL;
                } else {
                    CEP.TRC(SCCGWA, WS_VARIABLES.CALC_INT_BAL);
                    LNCICUT.COMM_DATA.AMT = WS_VARIABLES.CALC_INT_BAL;
                }
            }
        }
        LNCICUT.COMM_DATA.BEG_DATE = LNRSCHT.VAL_DTE;
        LNCICUT.COMM_DATA.END_DATE = LNRSCHT.DUE_DTE;
        if (LNRSCHT.KEY.TERM == LNRICTL.IC_CAL_TERM 
            && LNRSCHT.VAL_DTE < LNRICTL.INT_CUT_DT 
            && LNRSCHT.DUE_DTE > LNRICTL.INT_CUT_DT) {
            LNCICUT.COMM_DATA.BEG_DATE = LNRICTL.INT_CUT_DT;
        }
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.AMT);
        S000_CALL_LNZICUT();
        if (pgmRtn) return;
    }
    public void B031_01_CALL_ICUT_PROC_PLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNCICUT.COMM_DATA.SUF_NO = LNCSSCHE.DATA_AREA.SUF_NO;
        LNCICUT.COMM_DATA.TERM = LNRPLPI.KEY.TERM;
        LNCICUT.COMM_DATA.TYPE = 'I';
        LNCICUT.COMM_DATA.AMT = 0;
        LNCICUT.COMM_DATA.BEG_DATE = LNRPLPI.VAL_DT;
        LNCICUT.COMM_DATA.END_DATE = LNRPLPI.DUE_DT;
        if (LNRPLPI.KEY.TERM == LNRICTL.IC_CAL_TERM 
            && LNRPLPI.VAL_DT < LNRICTL.INT_CUT_DT 
            && LNRPLPI.DUE_DT > LNRICTL.INT_CUT_DT) {
            LNCICUT.COMM_DATA.BEG_DATE = LNRICTL.INT_CUT_DT;
        }
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.AMT);
        S000_CALL_LNZICUT();
        if (pgmRtn) return;
    }
    public void B032_BRW_PRIN_PLAN() throws IOException,SQLException,Exception {
        B401_COMPUTE_PAGE_INFO();
        if (pgmRtn) return;
        WS_VARIABLES.PAY_TYP = 'P';
        if (LNCSSCHE.CUR_FLG == 'Y') {
            WS_VARIABLES.REPAY_TERM = LNRICTL.P_CAL_TERM;
        } else {
            WS_VARIABLES.REPAY_TERM = LNRICTL.P_CUR_TERM;
        }
        WS_COND_FLG.RCVD_FLAG = 'N';
        if (LNCSSCHE.CUR_FLG != 'Y' 
            && LNCSSCHE.CUR_FLG != 'M') {
            R000_STARTBR_LNTRCVD();
            if (pgmRtn) return;
            R000_READNEXT_LNTRCVD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_COND_FLG.RCVD_FLAG);
            if (WS_COND_FLG.RCVD_FLAG != 'Y') {
                while (WS_COND_FLG.RCVD_FLAG != 'Y') {
                    if (LNRRCVD.DUE_DT == SCCGWA.COMM_AREA.AC_DATE) {
                        WS_VARIABLES.PAY_AMT_PRIN = LNRRCVD.P_REC_AMT;
                        if (LNRRCVD.P_WAV_AMT > 0) {
                            WS_VARIABLES.PAY_AMT_PRIN -= LNRRCVD.P_WAV_AMT;
                        }
                        if (LNRRCVD.P_PAY_AMT > 0) {
                            WS_VARIABLES.PAY_AMT_PRIN -= LNRRCVD.P_PAY_AMT;
                        }
                        WS_VARIABLES.PAY_AMT_INT = 0;
                        WS_VARIABLES.PAY_AMT = WS_VARIABLES.PAY_AMT_PRIN;
                        WS_VARIABLES.REMARK = " ";
                        if (LNRICTL.P_CUR_TERM < LNRICTL.P_CAL_TERM) {
                            B220_CALL_LNZRSCHT_2();
                            if (pgmRtn) return;
                        }
                    }
                    R000_READNEXT_LNTRCVD();
                    if (pgmRtn) return;
                }
            }
            R000_ENDBR_LNTRCVD();
            if (pgmRtn) return;
        }
        WS_COND_FLG.SCHT_FLAG = 'N';
        if (LNCSSCHE.CUR_FLG == 'Y' 
            && ((LNRICTL.IC_CAL_VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
            && LNRICTL.IC_CAL_DUE_DT > SCCGWA.COMM_AREA.AC_DATE) 
            || SCCGWA.COMM_AREA.AC_DATE >= LNCCONTM.REC_DATA.MAT_DATE)) {
            IBS.init(SCCGWA, LNCSSCHE.OUT_INFO[1-1]);
            IBS.init(SCCGWA, LNCSSCHE.OUT_INFO[2-1]);
            IBS.init(SCCGWA, LNRPLPI);
            IBS.init(SCCGWA, LNRSCHT);
            IBS.init(SCCGWA, LNCRSCHT);
            IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[1-1]);
            IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[2-1]);
            WS_COND_FLG.SCHT_FLAG = 'Y';
        } else {
            T000_STARTBR_LNTSCHT();
            if (pgmRtn) return;
            T000_READNEXT_LNTSCHT();
            if (pgmRtn) return;
        }
        if (WS_COND_FLG.SCHT_FLAG != 'Y') {
            for (WS_VARIABLES.WS_DATA.IDX = 1; (WS_VARIABLES.WS_DATA.IDX <= WS_VARIABLES.WS_DATA.PAGE_ROW) 
                && WS_COND_FLG.SCHT_FLAG != 'Y'; WS_VARIABLES.WS_DATA.IDX += 1) {
                WS_VARIABLES.PAY_AMT_PRIN = LNRSCHT.AMOUNT;
                WS_VARIABLES.PAY_AMT = LNRSCHT.AMOUNT;
                WS_VARIABLES.PAY_AMT_INT = 0;
                LNRSCHT.ALL_IN_RATE = 0;
                R010_TRANS_OUTPUT_DATA_SCHT();
                if (pgmRtn) return;
                R010_TRANS_OUTPUT_DATA_SCHT_C();
                if (pgmRtn) return;
                WS_VARIABLES.WS_DATA.NEXT_START_NUM += 1;
                WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATA.NEXT_START_NUM;
                T000_READNEXT_LNTSCHT();
                if (pgmRtn) return;
            }
            if (WS_COND_FLG.SCHT_FLAG == 'Y') {
                WS_VARIABLES.WS_DATA.TOTAL_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
                WS_VARIABLES.WS_DATA.BAL_CNT = WS_VARIABLES.WS_DATA.IDX - 1;
                WS_DB_VARS.TOTAL_NUM = ( WS_VARIABLES.WS_DATA.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATA.PAGE_ROW + WS_VARIABLES.WS_DATA.BAL_CNT;
                WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATA.PAGE_ROW = (short) WS_VARIABLES.WS_DATA.BAL_CNT;
            } else {
                T000_GET_TOTAL_LNTSCHT_P_OR_I();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_DB_VARS.TOTAL_NUM);
                if (WS_VARIABLES.WS_DATA.PAGE_ROW == 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_PAGE_ROW;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_VARIABLES.WS_DATA.BAL_CNT = WS_DB_VARS.TOTAL_NUM % WS_VARIABLES.WS_DATA.PAGE_ROW;
                WS_VARIABLES.WS_DATA.TOTAL_PAGE = (short) ((WS_DB_VARS.TOTAL_NUM - WS_VARIABLES.WS_DATA.BAL_CNT) / WS_VARIABLES.WS_DATA.PAGE_ROW);
                if (WS_VARIABLES.WS_DATA.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATA.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_VARIABLES.WS_DATA.TOTAL_PAGE = 1;
            WS_DB_VARS.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATA.PAGE_ROW = 0;
        }
        if (LNCSSCHE.CUR_FLG == 'Y' 
            && ((LNRICTL.IC_CAL_VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
            && LNRICTL.IC_CAL_DUE_DT > SCCGWA.COMM_AREA.AC_DATE) 
            || SCCGWA.COMM_AREA.AC_DATE >= LNCCONTM.REC_DATA.MAT_DATE)) {
        } else {
            T103_ENDBR_LNTSCHT();
            if (pgmRtn) return;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATA.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_DB_VARS.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATA.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.WS_DATA.PAGE_ROW;
        R020_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B033_BRW_ALL_PLAN() throws IOException,SQLException,Exception {
        B401_COMPUTE_PAGE_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.PAGE_ROW);
        CEP.TRC(SCCGWA, LNCSSCHE.CUR_FLG);
        CEP.TRC(SCCGWA, LNRICTL.IC_CUR_TERM);
        CEP.TRC(SCCGWA, LNRICTL.IC_CAL_TERM);
        CEP.TRC(SCCGWA, LNRICTL.P_CUR_TERM);
        CEP.TRC(SCCGWA, LNRICTL.P_CAL_TERM);
        if (LNCSSCHE.CUR_FLG == 'Y' 
            || LNCSSCHE.CUR_FLG == 'M') {
            WS_DB_VARS.I_TERM = LNRICTL.IC_CAL_TERM;
            WS_DB_VARS.P_TERM = LNRICTL.P_CAL_TERM;
        } else {
            WS_DB_VARS.I_TERM = LNRICTL.IC_CUR_TERM;
            WS_DB_VARS.P_TERM = LNRICTL.P_CUR_TERM;
        }
        WS_COND_FLG.RCVD_FLAG = 'N';
        if (LNCSSCHE.CUR_FLG != 'Y' 
            && LNCSSCHE.CUR_FLG != 'M') {
            T000_STARTBR_RCVD_P_AND_I();
            if (pgmRtn) return;
            T000_READNEXT_LNTRCVD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_COND_FLG.RCVD_FLAG);
            if (WS_COND_FLG.RCVD_FLAG != 'Y') {
                while (WS_COND_FLG.RCVD_FLAG != 'Y') {
                    CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
                    CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
                    if (LNRRCVD.DUE_DT == SCCGWA.COMM_AREA.AC_DATE) {
                        if (LNRRCVD.KEY.AMT_TYP == 'I') {
                            WS_VARIABLES.PAY_AMT_INT = LNRRCVD.I_REC_AMT;
                            WS_VARIABLES.PAY_AMT_PRIN = 0;
                            if (LNRRCVD.I_WAV_AMT > 0) {
                                WS_VARIABLES.PAY_AMT_INT -= LNRRCVD.I_WAV_AMT;
                            }
                            if (LNRRCVD.I_PAY_AMT > 0) {
                                WS_VARIABLES.PAY_AMT_INT -= LNRRCVD.I_PAY_AMT;
                            }
                            WS_VARIABLES.PAY_AMT = WS_VARIABLES.PAY_AMT_INT;
                        }
                        if (LNRRCVD.KEY.AMT_TYP == 'P') {
                            WS_VARIABLES.PAY_AMT_PRIN = LNRRCVD.P_REC_AMT;
                            WS_VARIABLES.PAY_AMT_INT = 0;
                            if (LNRRCVD.P_WAV_AMT > 0) {
                                WS_VARIABLES.PAY_AMT_PRIN -= LNRRCVD.P_WAV_AMT;
                            }
                            if (LNRRCVD.P_PAY_AMT > 0) {
                                WS_VARIABLES.PAY_AMT_PRIN -= LNRRCVD.P_PAY_AMT;
                            }
                            WS_VARIABLES.PAY_AMT = WS_VARIABLES.PAY_AMT_PRIN;
                        }
                        WS_VARIABLES.REMARK = " ";
                        if (LNRRCVD.KEY.AMT_TYP == 'I' 
                            && LNCAPRDM.REC_DATA.PAY_MTH == '0') {
                        } else {
                            if ((LNRRCVD.KEY.AMT_TYP == 'I' 
                                && LNRICTL.IC_CUR_TERM < LNRICTL.IC_CAL_TERM) 
                                || (LNRRCVD.KEY.AMT_TYP == 'P' 
                                && LNRICTL.P_CUR_TERM < LNRICTL.P_CAL_TERM)) {
                                B220_CALL_LNZRSCHT_2();
                                if (pgmRtn) return;
                            }
                        }
                    }
                    T000_READNEXT_LNTRCVD();
                    if (pgmRtn) return;
                }
            }
            T000_ENDBR_LNTRCVD();
            if (pgmRtn) return;
        }
        WS_COND_FLG.SCHT_FLAG = 'N';
        if (LNCSSCHE.CUR_FLG == 'Y' 
            && ((LNRICTL.IC_CAL_VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
            && LNRICTL.IC_CAL_DUE_DT > SCCGWA.COMM_AREA.AC_DATE) 
            || SCCGWA.COMM_AREA.AC_DATE >= LNCCONTM.REC_DATA.MAT_DATE)) {
            IBS.init(SCCGWA, LNCSSCHE.OUT_INFO[1-1]);
            IBS.init(SCCGWA, LNCSSCHE.OUT_INFO[2-1]);
            IBS.init(SCCGWA, LNRPLPI);
            IBS.init(SCCGWA, LNRSCHT);
            IBS.init(SCCGWA, LNCRSCHT);
            IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[1-1]);
            IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[2-1]);
            WS_COND_FLG.SCHT_FLAG = 'Y';
        } else {
            R010_STARTBR_LNTSCHT_P_AND_I();
            if (pgmRtn) return;
            T000_READNEXT_LNTSCHT_2();
            if (pgmRtn) return;
        }
        WS_VARIABLES.WS_DATA.IDX = 0;
        if (WS_COND_FLG.SCHT_FLAG != 'Y') {
            for (WS_VARIABLES.WS_DATA.COUNT = 1; WS_COND_FLG.SCHT_FLAG != 'Y' 
                && WS_VARIABLES.WS_DATA.COUNT < 360; WS_VARIABLES.WS_DATA.COUNT += 1) {
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.COUNT);
                CEP.TRC(SCCGWA, LNRSCHT.KEY.TYPE);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                if (!JIBS_tmp_str[0].equalsIgnoreCase("0133020")) {
                    if (LNRSCHT.KEY.TYPE == 'I') {
                        CEP.TRC(SCCGWA, LNRSCHT.KEY.TERM);
                        CEP.TRC(SCCGWA, LNRICTL.IC_CAL_TERM);
                        if (LNRSCHT.KEY.TERM < LNRICTL.IC_CAL_TERM) {
                            WS_VARIABLES.PAY_AMT_INT = LNRSCHT.PAY_INT;
                        } else {
                            B031_01_CALL_ICUT_PROC();
                            if (pgmRtn) return;
                            CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
                            CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.RATE);
                            WS_VARIABLES.PAY_AMT_INT = LNCICUT.COMM_DATA.INT_AMT;
                            if (LNCICUT.COMM_DATA.RATE > 0) {
                                LNRSCHT.ALL_IN_RATE = LNCICUT.COMM_DATA.RATE;
                            } else {
                                WS_VARIABLES.VAL_DATE = LNRSCHT.VAL_DTE;
                                WS_VARIABLES.DUE_DATE = LNRSCHT.DUE_DTE;
                                R000_GET_RATB_RATE();
                                if (pgmRtn) return;
                                CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.AVER_RATE);
                                LNRSCHT.ALL_IN_RATE = LNCRATB.COMM_DATA.AVER_RATE;
                            }
                        }
                        WS_VARIABLES.PAY_AMT_PRIN = 0;
                    } else {
                        WS_VARIABLES.PAY_AMT_INT = 0;
                        WS_VARIABLES.PAY_AMT_PRIN = LNRSCHT.AMOUNT;
                        WS_VARIABLES.CALC_INT_BAL = WS_VARIABLES.CALC_INT_BAL - WS_VARIABLES.PAY_AMT_PRIN;
                    }
                    WS_VARIABLES.PAY_AMT = WS_VARIABLES.PAY_AMT_PRIN + WS_VARIABLES.PAY_AMT_INT;
                    R010_TRANS_OUTPUT_DATA_SCHT_C();
                    if (pgmRtn) return;
                } else {
                    WS_VARIABLES.LST_CALC_INT_BAL = WS_VARIABLES.CALC_INT_BAL;
                    CEP.TRC(SCCGWA, "AAAAA");
                    CEP.TRC(SCCGWA, WS_VARIABLES.LST_CALC_INT_BAL);
                    if (LNRSCHT.KEY.TYPE == 'I') {
                        WS_VARIABLES.PAY_AMT_PRIN = 0;
                    } else {
                        WS_VARIABLES.PAY_AMT_PRIN = LNRSCHT.AMOUNT;
                        WS_VARIABLES.CALC_INT_BAL = WS_VARIABLES.CALC_INT_BAL - WS_VARIABLES.PAY_AMT_PRIN;
                    }
                    WS_DB_VARS.TOTAL_NUM += 1;
                    if (WS_DB_VARS.TOTAL_NUM >= WS_VARIABLES.WS_DATA.NEXT_START_NUM 
                        && WS_VARIABLES.WS_DATA.IDX < WS_VARIABLES.WS_DATA.PAGE_ROW) {
                        WS_VARIABLES.WS_DATA.IDX += 1;
                        WS_COND_FLG.OUTPUT_FLAG = 'Y';
                        if (LNRSCHT.KEY.TYPE == 'I') {
                            if (LNRSCHT.KEY.TERM < LNRICTL.IC_CAL_TERM) {
                                WS_VARIABLES.PAY_AMT_INT = LNRSCHT.PAY_INT;
                            } else {
                                WS_COND_FLG.SPCL_PROC_FLAG = 'Y';
                                B031_01_CALL_ICUT_PROC();
                                if (pgmRtn) return;
                                WS_COND_FLG.SPCL_PROC_FLAG = 'N';
                                WS_VARIABLES.PAY_AMT_INT = LNCICUT.COMM_DATA.INT_AMT;
                                if (LNCICUT.COMM_DATA.RATE > 0) {
                                    LNRSCHT.ALL_IN_RATE = LNCICUT.COMM_DATA.RATE;
                                } else {
                                    WS_VARIABLES.VAL_DATE = LNRSCHT.VAL_DTE;
                                    WS_VARIABLES.DUE_DATE = LNRSCHT.DUE_DTE;
                                    R000_GET_RATB_RATE();
                                    if (pgmRtn) return;
                                    LNRSCHT.ALL_IN_RATE = LNCRATB.COMM_DATA.AVER_RATE;
                                }
                            }
                        } else {
                            WS_VARIABLES.PAY_AMT_INT = 0;
                        }
                        WS_VARIABLES.PAY_AMT = WS_VARIABLES.PAY_AMT_PRIN + WS_VARIABLES.PAY_AMT_INT;
                        R010_TRANS_OUTPUT_DATA_SCHT();
                        if (pgmRtn) return;
                    }
                }
                T000_READNEXT_LNTSCHT_2();
                if (pgmRtn) return;
            }
            if (WS_COND_FLG.OUTPUT_FLAG == 'Y') {
                WS_VARIABLES.WS_DATA.BAL_CNT = WS_DB_VARS.TOTAL_NUM % WS_VARIABLES.WS_DATA.PAGE_ROW;
                WS_VARIABLES.WS_DATA.TOTAL_PAGE = (short) ((WS_DB_VARS.TOTAL_NUM - WS_VARIABLES.WS_DATA.BAL_CNT) / WS_VARIABLES.WS_DATA.PAGE_ROW);
                if (WS_VARIABLES.WS_DATA.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATA.TOTAL_PAGE += 1;
                }
                if (WS_VARIABLES.WS_DATA.TOTAL_PAGE == WS_VARIABLES.WS_DATA.CURR_PAGE) {
                    WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
                }
                WS_VARIABLES.WS_DATA.PAGE_ROW = WS_VARIABLES.WS_DATA.IDX;
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
            } else {
                WS_VARIABLES.WS_DATA.TOTAL_PAGE = 1;
                WS_DB_VARS.TOTAL_NUM = 0;
                WS_VARIABLES.WS_DATA.CURR_PAGE = 1;
                WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATA.PAGE_ROW = 0;
            }
        } else {
            WS_VARIABLES.WS_DATA.TOTAL_PAGE = 1;
            WS_DB_VARS.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATA.CURR_PAGE = 1;
            WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATA.PAGE_ROW = 0;
        }
        if (LNCSSCHE.CUR_FLG == 'Y' 
            && ((LNRICTL.IC_CAL_VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
            && LNRICTL.IC_CAL_DUE_DT > SCCGWA.COMM_AREA.AC_DATE) 
            || SCCGWA.COMM_AREA.AC_DATE >= LNCCONTM.REC_DATA.MAT_DATE)) {
        } else {
            T103_ENDBR_LNTSCHT();
            if (pgmRtn) return;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATA.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_DB_VARS.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATA.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.WS_DATA.PAGE_ROW;
        R020_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B035_BRW_INST_PLAN() throws IOException,SQLException,Exception {
        WS_COND_FLG.PROC_END_FLAG = 'N';
        WS_COND_FLG.PROC_DONE_FLAG = 'N';
        WS_VARIABLES.MAX_PHS_NO = 0;
        R000_GET_MAX_PHS_NO();
        if (pgmRtn) return;
        WS_VARIABLES.MAX_PHS_NO = LNRPAIP.KEY.PHS_NO;
        CEP.TRC(SCCGWA, WS_VARIABLES.MAX_PHS_NO);
        if (LNCSSCHE.DATA_AREA.TRAN_SEQ == 0) {
            if (LNCAPRDM.REC_DATA.SPEC_LN_FLG == '1') {
                B036_GET_BALLOON_TOT_TERM();
                if (pgmRtn) return;
            } else {
                R000_GROUP_LNTPAIP();
                if (pgmRtn) return;
            }
        } else {
            R000_GROUP_LNTTMPP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.TOT_TERM);
        if (WS_VARIABLES.TOT_TERM == 0) {
            WS_COND_FLG.PROC_END_FLAG = 'Y';
        } else {
            WS_COND_FLG.PAIP_FLAG = 'N';
            if (LNCSSCHE.DATA_AREA.TRAN_SEQ == 0) {
                R000_STARTBR_LNTPAIP();
                if (pgmRtn) return;
                R000_READNEXT_LNTPAIP();
                if (pgmRtn) return;
            } else {
                R000_STARTBR_LNTTMPP();
                if (pgmRtn) return;
                R000_READNEXT_LNTTMPP();
                if (pgmRtn) return;
            }
            WS_VARIABLES.PHS_TERM_LAST = 0;
            if (LNCRPAIP.RETURN_INFO != 'E') {
                WS_VARIABLES.PHS_TERM = LNRPAIP.PHS_TOT_TERM;
                WS_VARIABLES.PHS_TOT_AMT = LNRPAIP.PHS_PRIN_AMT - LNRPAIP.PHS_REM_PRIN_AMT;
                WS_VARIABLES.PHS_REM_PRIN = LNRPAIP.PHS_REM_PRIN_AMT;
                if (WS_VARIABLES.PHS_REM_PRIN > WS_VARIABLES.CALC_INT_BAL) {
                    WS_VARIABLES.PHS_REM_PRIN = WS_VARIABLES.CALC_INT_BAL;
                }
                WS_VARIABLES.WS_DATA.COUNT = LNRPAIP.PHS_CAL_TERM;
                WS_VARIABLES.PHS_CAL_TERM = LNRPAIP.PHS_CAL_TERM;
            }
            while (WS_VARIABLES.PHS_TERM <= WS_VARIABLES.PHS_CAL_TERM 
                && LNCRPAIP.RETURN_INFO != 'E') {
                if (LNCSSCHE.DATA_AREA.TRAN_SEQ == 0) {
                    R000_READNEXT_LNTPAIP();
                    if (pgmRtn) return;
                } else {
                    R000_READNEXT_LNTTMPP();
                    if (pgmRtn) return;
                }
                if (LNCRPAIP.RETURN_INFO != 'E') {
                    if (LNRPAIP.KEY.CONTRACT_NO.equalsIgnoreCase(LNCSSCHE.DATA_AREA.CONTRACT_NO)) {
                        WS_VARIABLES.PHS_TERM_LAST = WS_VARIABLES.PHS_TERM;
                        WS_VARIABLES.PHS_TOT_AMT = LNRPAIP.PHS_PRIN_AMT - LNRPAIP.PHS_REM_PRIN_AMT;
                        WS_VARIABLES.PHS_TERM += LNRPAIP.PHS_TOT_TERM;
                        WS_VARIABLES.PHS_REM_PRIN = LNRPAIP.PHS_REM_PRIN_AMT;
                        if (WS_VARIABLES.PHS_REM_PRIN > WS_VARIABLES.CALC_INT_BAL) {
                            WS_VARIABLES.PHS_REM_PRIN = WS_VARIABLES.CALC_INT_BAL;
                        }
                        WS_VARIABLES.WS_DATA.COUNT = LNRPAIP.PHS_CAL_TERM;
                        WS_VARIABLES.PHS_CAL_TERM += LNRPAIP.PHS_CAL_TERM;
                    } else {
                        WS_COND_FLG.PROC_END_FLAG = 'Y';
                    }
                }
            }
            if (WS_VARIABLES.PHS_CAL_TERM >= 0 
                && LNRICTL.IC_CAL_TERM > WS_VARIABLES.PHS_CAL_TERM) {
                WS_VARIABLES.PHS_TERM = (short) (WS_VARIABLES.PHS_TERM + LNRICTL.IC_CAL_TERM - 1 - WS_VARIABLES.PHS_CAL_TERM);
            }
        }
        if (WS_COND_FLG.PROC_END_FLAG == 'N') {
            WS_VARIABLES.PAY_TYP = 'C';
            if (LNCSSCHE.CUR_FLG == 'Y') {
                WS_VARIABLES.REPAY_TERM = LNRICTL.IC_CAL_TERM;
            } else {
                WS_VARIABLES.REPAY_TERM = LNRICTL.IC_CUR_TERM;
            }
            WS_COND_FLG.PLPI_FLAG = 'N';
            WS_COND_FLG.RCVD_FLAG = 'N';
            R000_STARTBR_LNTPLPI();
            if (pgmRtn) return;
            R000_STARTBR_LNTRCVD();
            if (pgmRtn) return;
            R000_READNEXT_LNTPLPI();
            if (pgmRtn) return;
            R000_READNEXT_LNTRCVD();
            if (pgmRtn) return;
            WS_COND_FLG.PROC_DONE_FLAG = 'Y';
            CEP.TRC(SCCGWA, WS_VARIABLES.DUE_DATE);
            CEP.TRC(SCCGWA, LNRICTL.IC_CAL_VAL_DT);
            if (WS_COND_FLG.RCVD_FLAG == 'Y') {
                WS_VARIABLES.SPE_TERM = LNRICTL.IC_CAL_TERM;
                WS_VARIABLES.DUE_DATE = LNRICTL.IC_CAL_VAL_DT;
            } else {
                WS_VARIABLES.SPE_TERM = LNRRCVD.KEY.TERM;
                WS_VARIABLES.DUE_DATE = LNRRCVD.DUE_DT;
            }
        }
        if (LNCSSCHE.SEQ_NO == 0) {
            B210_GET_NEW_DATE();
            if (pgmRtn) return;
            WS_COND_FLG.FRST_TIME_RUN_FLAG = 'Y';
            while (WS_COND_FLG.PROC_END_FLAG != 'Y') {
                if (LNCSSCHE.CUR_FLG == 'Y' 
                    && ((LNRICTL.IC_CAL_VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
                    && LNRICTL.IC_CAL_DUE_DT > SCCGWA.COMM_AREA.AC_DATE) 
                    || SCCGWA.COMM_AREA.AC_DATE >= LNCCONTM.REC_DATA.MAT_DATE)) {
                    IBS.init(SCCGWA, LNCSSCHE.OUT_INFO[1-1]);
                    IBS.init(SCCGWA, LNCSSCHE.OUT_INFO[2-1]);
                    IBS.init(SCCGWA, LNRPLPI);
                    IBS.init(SCCGWA, LNRSCHT);
                    IBS.init(SCCGWA, LNCRSCHT);
                    IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[1-1]);
                    IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[2-1]);
                    WS_COND_FLG.PROC_END_FLAG = 'Y';
                } else {
                    B035_PROC_ONE_SCHD();
                    if (pgmRtn) return;
                    if (LNRPLPI.VAL_DT < WS_VARIABLES.13MONTH_DT 
                        || !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        if (WS_VARIABLES.RCVD_DUE_DATE == SCCGWA.COMM_AREA.AC_DATE) {
                            B220_CALL_LNZRSCHT();
                            if (pgmRtn) return;
                        }
                        WS_VARIABLES.DUE_DATE = LNRPLPI.DUE_DT;
                        WS_VARIABLES.SPE_TERM += 1;
                        if (LNCSSCHE.CUR_FLG == 'Y') {
                            WS_COND_FLG.PROC_END_FLAG = 'Y';
                        }
                        CEP.TRC(SCCGWA, WS_VARIABLES.SPE_TERM);
                        CEP.TRC(SCCGWA, LNRICTL.IC_CMP_TERM);
                        if (WS_VARIABLES.SPE_TERM < LNRICTL.IC_CMP_TERM) {
                            R000_READNEXT_LNTPLPI();
                            if (pgmRtn) return;
                        } else {
                            WS_COND_FLG.PLPI_FLAG = 'Y';
                        }
                        if (WS_VARIABLES.DUE_DATE >= LNCCONTM.REC_DATA.MAT_DATE) {
                            WS_COND_FLG.PROC_END_FLAG = 'Y';
                        }
                        if (WS_VARIABLES.SPE_TERM > WS_VARIABLES.TOT_TERM) {
                            WS_COND_FLG.PROC_END_FLAG = 'Y';
                        } else {
                            B035_PROC_PAIP_ADJUST();
                            if (pgmRtn) return;
                        }
                        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                        if (WS_VARIABLES.CALC_INT_BAL <= 0 
                            && !LNRICTL.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                            WS_COND_FLG.PROC_END_FLAG = 'Y';
                        }
                    }
                    if (LNRPLPI.DUE_DT >= WS_VARIABLES.13MONTH_DT 
                        && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        WS_COND_FLG.PROC_END_FLAG = 'Y';
                    }
                }
            }
            if (WS_COND_FLG.UP_ICTL_FLG == 'Y' 
                && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                R000_UPDATE_LNTICTL();
                if (pgmRtn) return;
            }
        } else {
            WS_VARIABLES.SEQ_TMP = LNCSSCHE.SEQ_NO;
        }
        if (WS_COND_FLG.PROC_DONE_FLAG == 'Y') {
            R000_ENDBR_LNTRCVD();
            if (pgmRtn) return;
            R000_ENDBR_LNTPLPI();
            if (pgmRtn) return;
            if (LNCSSCHE.DATA_AREA.TRAN_SEQ == 0) {
                R000_ENDBR_LNTPAIP();
                if (pgmRtn) return;
            } else {
                R000_ENDBR_LNTTMPP();
                if (pgmRtn) return;
            }
        }
        B401_COMPUTE_PAGE_INFO();
        if (pgmRtn) return;
        T101_STARTBR_LNTSCHT();
        if (pgmRtn) return;
        T102_READNEXT_LNTSCHT();
        if (pgmRtn) return;
        B401_GET_PAGE_INFO();
        if (pgmRtn) return;
        T103_ENDBR_LNTSCHT();
        if (pgmRtn) return;
    }
    public void R000_UPDATE_LNTICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRICTL.KEY.SUB_CTA_NO = 0;
        else LNRICTL.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.upd = true;
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ICTL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        LNRICTL.IC_CMP_VAL_DT = WS_VARIABLES.ICTL_IC_CMP_VAL_DT;
        LNRICTL.IC_CMP_PHS_NO = WS_VARIABLES.ICTL_IC_CMP_PHS_NO;
        LNRICTL.IC_CMP_TERM = (short) (WS_VARIABLES.LAST_PLPI_TERM + 1);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = LNRICTL.IC_CMP_VAL_DT;
        if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'D') {
            SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD;
        } else {
            SCCCLDT.MTHS = LNCAPRDM.REC_DATA.CAL_PERD;
        }
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        if (SCCCLDT.DATE2 < LNCCONTM.REC_DATA.MAT_DATE) {
            LNRICTL.IC_CMP_DUE_DT = SCCCLDT.DATE2;
        } else {
            LNRICTL.IC_CMP_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
        }
        LNRICTL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRICTL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.REWRITE(SCCGWA, LNRICTL, LNTICTL_RD);
    }
    public void B210_GET_NEW_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.MTHS = 13;
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_VARIABLES.13MONTH_DT = SCCCLDT.DATE2;
    }
    public void B036_GET_BALLOON_TOT_TERM() throws IOException,SQLException,Exception {
        WS_VARIABLES.TOT_TERM = 0;
        WS_VARIABLES.TOT_TERM = (short) (WS_VARIABLES.TOT_TERM + LNRICTL.IC_CAL_TERM - 1);
        WS_VARIABLES.DATE = LNRICTL.IC_CAL_DUE_DT;
        WS_VARIABLES.DATE1 = LNRICTL.IC_CAL_DUE_DT;
        while (WS_VARIABLES.DATE < LNCCONTM.REC_DATA.MAT_DATE) {
            WS_VARIABLES.TOT_TERM += 1;
            IBS.init(SCCGWA, SCCCLDT);
            if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'M') {
                SCCCLDT.MTHS = LNCAPRDM.REC_DATA.CAL_PERD;
            } else {
                SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD;
            }
            SCCCLDT.DATE1 = WS_VARIABLES.DATE;
            WS_VARIABLES.DATE1 = WS_VARIABLES.DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_VARIABLES.DATE = SCCCLDT.DATE2;
        }
        if (LNRICTL.IC_CAL_DUE_DT == LNCCONTM.REC_DATA.MAT_DATE 
            && LNRICTL.IC_CAL_VAL_DT < LNRICTL.IC_CAL_DUE_DT) {
            WS_VARIABLES.TOT_TERM += 1;
        }
        if (LNCAPRDM.REC_DATA.FST_PAY_FLG == '2' 
            || LNCAPRDM.REC_DATA.FST_PAY_FLG == ' ') {
            if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'M') {
                IBS.CPY2CLS(SCCGWA, WS_VARIABLES.DATE1+"", WS_VARIABLES.WS_LAST_DT);
                WS_VARIABLES.MAT_DT = LNCCONTM.REC_DATA.MAT_DATE;
                IBS.CPY2CLS(SCCGWA, WS_VARIABLES.MAT_DT+"", WS_VARIABLES.WS_END_DT);
                if (WS_VARIABLES.WS_LAST_DT.LAST_DT_YYYYMM != WS_VARIABLES.WS_END_DT.END_DT_YYYYMM) {
                    WS_VARIABLES.TOT_TERM += 1;
                }
            } else {
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = WS_VARIABLES.DATE1;
                S000_CALL_SCSSCKDT();
                if (pgmRtn) return;
                if (SCCCKDT.WEEK_NO == 7) {
                    WS_VARIABLES.DAYS = 6;
                } else {
                    WS_VARIABLES.DAYS = 6 - SCCCKDT.WEEK_NO;
                }
                if (WS_VARIABLES.DAYS == 0) {
                    WS_VARIABLES.DATE2 = WS_VARIABLES.DATE1;
                } else {
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DAYS = WS_VARIABLES.DAYS;
                    SCCCLDT.DATE1 = WS_VARIABLES.DATE1;
                    S000_CALL_SCSSCLDT();
                    if (pgmRtn) return;
                    WS_VARIABLES.DATE2 = SCCCLDT.DATE2;
                }
                if (WS_VARIABLES.DATE2 < LNCCONTM.REC_DATA.MAT_DATE) {
                    WS_VARIABLES.TOT_TERM += 1;
                }
            }
        } else {
            WS_VARIABLES.TOT_TERM += 1;
        }
    }
    public void B035_PROC_ONE_SCHD() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.SPE_TERM < LNRICTL.IC_CAL_TERM) {
            CEP.TRC(SCCGWA, LNRPLPI.KEY.CONTRACT_NO);
            while (WS_COND_FLG.RCVD_FLAG != 'Y' 
                && LNRRCVD.KEY.TERM < WS_VARIABLES.SPE_TERM) {
                R000_READNEXT_LNTRCVD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
            }
            if (WS_COND_FLG.RCVD_FLAG == 'N' 
                && LNRRCVD.KEY.TERM == WS_VARIABLES.SPE_TERM) {
                WS_VARIABLES.DUE_DATE = LNRRCVD.DUE_DT;
                WS_VARIABLES.RCVD_DUE_DATE = LNRRCVD.DUE_DT;
                WS_VARIABLES.PAY_AMT_INT = LNRRCVD.I_REC_AMT - LNRRCVD.I_WAV_AMT - LNRRCVD.I_PAY_AMT;
                WS_VARIABLES.PAY_AMT_PRIN = LNRRCVD.P_REC_AMT - LNRRCVD.P_WAV_AMT - LNRRCVD.P_PAY_AMT;
                WS_VARIABLES.PAY_AMT = WS_VARIABLES.PAY_AMT_INT + WS_VARIABLES.PAY_AMT_PRIN;
                WS_VARIABLES.ALL_IN_RATE = LNRRCVD.ALL_IN_RATE;
                if (WS_VARIABLES.XBD_FLG == 'Y') {
                    WS_VARIABLES.PAY_XBD_FEE = LNRRCVD.F_REC_AMT - LNRRCVD.F_PAY_AMT - LNRRCVD.F_WAV_AMT;
                    CEP.TRC(SCCGWA, WS_VARIABLES.PAY_XBD_FEE);
                }
                CEP.TRC(SCCGWA, WS_VARIABLES.PAY_AMT);
            }
        } else {
            WS_VARIABLES.RCVD_DUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            WS_VARIABLES.WS_DATA.COUNT += 1;
            if (WS_VARIABLES.SPE_TERM >= LNRICTL.IC_CMP_TERM) {
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.COUNT);
                CEP.TRC(SCCGWA, WS_VARIABLES.DUE_DATE);
                WS_VARIABLES.VAL_DATE = WS_VARIABLES.DUE_DATE;
                B035_03_SIMULATE_DUE_DATE();
                if (pgmRtn) return;
                R000_CHECK_FULL_INT();
                if (pgmRtn) return;
                B035_CALC_INT_BY_PHS_BAL();
                if (pgmRtn) return;
                WS_VARIABLES.PAY_AMT_INT = WS_VARIABLES.CALC_BY_PHS_BAL_INT;
                if (WS_VARIABLES.PHS_REM_PRIN != WS_VARIABLES.CALC_INT_BAL) {
                    B035_CALC_INT_BY_LN_BAL();
                    if (pgmRtn) return;
                    WS_VARIABLES.PAY_AMT_INT = WS_VARIABLES.CALC_BY_LN_BAL_INT;
                }
                if (LNCICUT.COMM_DATA.RATE > 0) {
                    WS_VARIABLES.ALL_IN_RATE = LNCICUT.COMM_DATA.RATE;
                } else {
                    R000_GET_RATB_RATE();
                    if (pgmRtn) return;
                    WS_VARIABLES.ALL_IN_RATE = LNCRATB.COMM_DATA.AVER_RATE;
                }
                if (WS_COND_FLG.FRST_TIME_RUN_FLAG == 'Y') {
                    WS_VARIABLES.LAST_ALL_IN_RATE = LNRICTL.CUR_RAT;
                    WS_COND_FLG.FRST_TIME_RUN_FLAG = 'N';
                }
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (WS_COND_FLG.PROC_END_FLAG == 'N' 
                    && LNRICTL.CTL_STSW.substring(29 - 1, 29 + 1 - 1).equalsIgnoreCase("0")) {
                    if (WS_VARIABLES.ALL_IN_RATE != WS_VARIABLES.LAST_ALL_IN_RATE 
                        && LNRPAIP.INST_MTH == '1') {
                        R000_RECOMP_INST_AMT();
                        if (pgmRtn) return;
                        WS_VARIABLES.LAST_ALL_IN_RATE = WS_VARIABLES.ALL_IN_RATE;
                    } else {
                        if (WS_VARIABLES.PLPI_DUE_REPY_AMT > 0) {
                            CEP.TRC(SCCGWA, WS_VARIABLES.PLPI_DUE_REPY_AMT);
                            LNRPAIP.CUR_INST_AMT = WS_VARIABLES.PLPI_DUE_REPY_AMT;
                        }
                    }
                }
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (WS_COND_FLG.PROC_END_FLAG == 'N' 
                    && LNRICTL.CTL_STSW.substring(29 - 1, 29 + 1 - 1).equalsIgnoreCase("1")) {
                    R000_RECOMP_INST_AMT();
                    if (pgmRtn) return;
                }
                B035_01_SIMULATE_PLPI();
                if (pgmRtn) return;
                if (LNRPAIP.INST_MTH == '1' 
                    && LNRPLPI.DUE_REPY_AMT != 0) {
                    WS_VARIABLES.PAIP_PAY_AMT = LNRPLPI.DUE_REPY_AMT;
                    if ((WS_VARIABLES.SPE_TERM == 1) 
                        || (WS_COND_FLG.FULL_INT_FLG == 'Y' 
                        && WS_COND_FLG.PROC_END_FLAG != 'Y' 
                        && WS_VARIABLES.SPE_TERM != 1)) {
                        B035_CALC_FULL_INT_PHS_BAL();
                        if (pgmRtn) return;
                        WS_VARIABLES.FULL_TERM_INT = WS_VARIABLES.INT_AMT0;
                        if (WS_VARIABLES.FULL_TERM_INT > 0) {
                            WS_VARIABLES.PAY_AMT_PRIN = WS_VARIABLES.PAIP_PAY_AMT - WS_VARIABLES.FULL_TERM_INT;
                        } else {
                            WS_VARIABLES.PAY_AMT_PRIN = WS_VARIABLES.PAIP_PAY_AMT;
                        }
                    } else {
                        if ((WS_COND_FLG.PROC_END_FLAG == 'Y' 
                            && LNRPAIP.PHS_PRIN_AMT > 0) 
                            || WS_VARIABLES.SPE_TERM == WS_VARIABLES.PHS_TERM) {
                            WS_VARIABLES.PAY_AMT_PRIN = LNRPAIP.PHS_PRIN_AMT - WS_VARIABLES.PHS_TOT_AMT;
                        } else {
                            WS_VARIABLES.PAY_AMT_PRIN = WS_VARIABLES.PAIP_PAY_AMT - WS_VARIABLES.PAY_AMT_INT;
                        }
                    }
                } else {
                    WS_VARIABLES.PAY_AMT_PRIN = LNRPLPI.PRIN_AMT;
                    if (WS_COND_FLG.PROC_END_FLAG == 'Y' 
                        && LNRPAIP.PHS_PRIN_AMT > 0) {
                        WS_VARIABLES.PAY_AMT_PRIN = LNRPAIP.PHS_PRIN_AMT - WS_VARIABLES.PHS_TOT_AMT;
                    }
                }
            } else {
                WS_COND_FLG.FRST_TIME_RUN_FLAG = 'N';
                WS_VARIABLES.VAL_DATE = LNRPLPI.VAL_DT;
                WS_VARIABLES.DUE_DATE = LNRPLPI.DUE_DT;
                B035_CALC_INT_BY_PHS_BAL();
                if (pgmRtn) return;
                WS_VARIABLES.PAY_AMT_INT = WS_VARIABLES.CALC_BY_PHS_BAL_INT;
                if (WS_VARIABLES.PHS_REM_PRIN != WS_VARIABLES.CALC_INT_BAL) {
                    B035_CALC_INT_BY_LN_BAL();
                    if (pgmRtn) return;
                    WS_VARIABLES.PAY_AMT_INT = WS_VARIABLES.CALC_BY_LN_BAL_INT;
                }
                if (LNCICUT.COMM_DATA.RATE > 0) {
                    WS_VARIABLES.ALL_IN_RATE = LNCICUT.COMM_DATA.RATE;
                    WS_VARIABLES.LAST_ALL_IN_RATE = LNCICUT.COMM_DATA.RATE;
                } else {
                    R000_GET_RATB_RATE();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.AVER_RATE);
                    WS_VARIABLES.ALL_IN_RATE = LNCRATB.COMM_DATA.AVER_RATE;
                    WS_VARIABLES.LAST_ALL_IN_RATE = LNCRATB.COMM_DATA.AVER_RATE;
                }
                if (LNRPAIP.INST_MTH == '1') {
                    WS_VARIABLES.PAIP_PAY_AMT = LNRPLPI.DUE_REPY_AMT;
                    WS_VARIABLES.PLPI_DUE_REPY_AMT = LNRPLPI.DUE_REPY_AMT;
                    WS_VARIABLES.PAY_AMT_PRIN = WS_VARIABLES.PAIP_PAY_AMT - WS_VARIABLES.PAY_AMT_INT;
                } else {
                    WS_VARIABLES.PAY_AMT_PRIN = LNRPLPI.PRIN_AMT;
                    WS_VARIABLES.PLPI_DUE_REPY_AMT = LNRPLPI.PRIN_AMT;
                }
            }
            if (WS_VARIABLES.PAY_AMT_PRIN > WS_VARIABLES.PHS_REM_PRIN) {
                WS_VARIABLES.PAY_AMT_PRIN = WS_VARIABLES.PHS_REM_PRIN;
            }
            if (WS_VARIABLES.PAY_AMT_PRIN > WS_VARIABLES.CALC_INT_BAL) {
                WS_VARIABLES.PAY_AMT_PRIN = WS_VARIABLES.CALC_INT_BAL;
            }
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(29 - 1, 29 + 1 - 1).equalsIgnoreCase("1") 
                && (WS_VARIABLES.WS_DATA.COUNT < LNRPAIP.PHS_TOT_TERM 
                || WS_VARIABLES.SPE_TERM < WS_VARIABLES.PHS_TERM)) {
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.COUNT);
                CEP.TRC(SCCGWA, LNRPAIP.PHS_TOT_TERM);
                CEP.TRC(SCCGWA, WS_VARIABLES.SPE_TERM);
                CEP.TRC(SCCGWA, WS_VARIABLES.PHS_TERM);
                IBS.init(SCCGWA, LNRSEAJ);
                LNRSEAJ.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
                JIBS_tmp_str[0] = "" + WS_VARIABLES.DUE_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) LNRSEAJ.KEY.YEAR = 0;
                else LNRSEAJ.KEY.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
                JIBS_tmp_str[0] = "" + WS_VARIABLES.DUE_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) LNRSEAJ.KEY.MONTH = 0;
                else LNRSEAJ.KEY.MONTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                LNRSEAJ.STATUS = '1';
                T000_READ_LNTSEAJ();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_VARIABLES.PAY_AMT_PRIN = 0;
                }
            }
            WS_VARIABLES.PHS_TOT_AMT = WS_VARIABLES.PHS_TOT_AMT + WS_VARIABLES.PAY_AMT_PRIN;
            WS_VARIABLES.PHS_REM_PRIN = WS_VARIABLES.PHS_REM_PRIN - WS_VARIABLES.PAY_AMT_PRIN;
            if (WS_VARIABLES.PHS_TOT_AMT > LNRPAIP.PHS_PRIN_AMT) {
                WS_VARIABLES.PHS_TOT_AMT = WS_VARIABLES.PHS_TOT_AMT - WS_VARIABLES.PAY_AMT_PRIN;
                WS_VARIABLES.PAY_AMT_PRIN = LNRPAIP.PHS_PRIN_AMT - WS_VARIABLES.PHS_TOT_AMT;
                WS_VARIABLES.PHS_TOT_AMT = LNRPAIP.PHS_PRIN_AMT;
            }
            WS_VARIABLES.CALC_INT_BAL = WS_VARIABLES.CALC_INT_BAL - WS_VARIABLES.PAY_AMT_PRIN;
            if (WS_VARIABLES.CALC_INT_BAL <= 0) {
                WS_VARIABLES.DUE_DATE = LNCCONTM.REC_DATA.MAT_DATE;
                LNRPLPI.DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
            }
            if (WS_VARIABLES.ST_TERM > 0 
                && WS_VARIABLES.SUBTERM > 0 
                && WS_VARIABLES.SPE_TERM >= WS_VARIABLES.ST_TERM 
                && WS_VARIABLES.SPE_TERM <= WS_VARIABLES.SUBTERM 
                && (WS_VARIABLES.PAY_AMT_PRIN > 0 
                || WS_VARIABLES.PAY_AMT_INT > 0)) {
                IBS.init(SCCGWA, LNCSUBP);
                LNCSUBP.COMM_DATA.FUNC_CODE = 'I';
                LNCSUBP.COMM_DATA.LN_AC = LNCSSCHE.DATA_AREA.CONTRACT_NO;
                LNCSUBP.COMM_DATA.TERM = WS_VARIABLES.SPE_TERM;
                LNCSUBP.COMM_DATA.BEG_DATE = LNRPLPI.VAL_DT;
                LNCSUBP.COMM_DATA.END_DATE = LNRPLPI.DUE_DT;
                LNCSUBP.COMM_DATA.PROJ_NO = LNRSUBS.KEY.PROJ_NO;
                LNCSUBP.COMM_DATA.TOT_P_AMT = WS_VARIABLES.PAY_AMT_PRIN;
                if (WS_COND_FLG.PYIF_FLAG == 'Y' 
                    && LNRPYIF.REIM_INT_AMT > 0) {
                    WS_VARIABLES.PAY_AMT_INT += LNRPYIF.REIM_INT_AMT;
                }
                LNCSUBP.COMM_DATA.TOT_I_AMT = WS_VARIABLES.PAY_AMT_INT;
                LNCSUBP.COMM_DATA.RAT = WS_VARIABLES.ALL_IN_RATE;
                S000_CALL_LNZSUBP();
                if (pgmRtn) return;
                if (LNCSUBP.COMM_DATA.P_AMT > 0) {
                    WS_VARIABLES.PAY_AMT_PRIN -= LNCSUBP.COMM_DATA.P_AMT;
                }
                if (LNCSUBP.COMM_DATA.I_AMT > 0) {
                    WS_VARIABLES.PAY_AMT_INT -= LNCSUBP.COMM_DATA.I_AMT;
                }
                if (WS_COND_FLG.PYIF_FLAG == 'Y' 
                    && LNRPYIF.REIM_INT_AMT > 0) {
                    WS_VARIABLES.PAY_AMT_INT -= LNRPYIF.REIM_INT_AMT;
                }
            }
            WS_VARIABLES.PAY_AMT = WS_VARIABLES.PAY_AMT_PRIN + WS_VARIABLES.PAY_AMT_INT;
        }
    }
    public void B040_ADJ_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "201810150001");
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.IDX);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM);
        CEP.TRC(SCCGWA, WS_VARIABLES.CUR_TERM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_TYP);
        CEP.TRC(SCCGWA, LNCSSCHE.DATA_AREA.PAY_TYP);
        if (WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM == WS_VARIABLES.CUR_TERM 
            && (WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_TYP == 'I' 
            || WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_TYP == 'C')) {
            CEP.TRC(SCCGWA, "201810150002");
            T00_READ_LNTINTA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "201810150003");
            if (WS_COND_FLG.INTA_FLAG == 'Y') {
                if (LNCSSCHE.DATA_AREA.PAY_TYP == ' ') {
                    LNCSSCHE.DATA_AREA.PAY_TYP = 'I';
                }
                T000_READ_LNZRPLPI();
                if (pgmRtn) return;
                if (LNRPLPI.REC_STS != '1') {
                    CEP.TRC(SCCGWA, LNRINTA.ADJ_AMT);
                    WS_VARIABLES.ADJ_TOT = LNRINTA.ADJ_AMT;
                    WS_OUT_RECODE.WS_OUT_INFO[1-1].O_PAY_INT = WS_VARIABLES.ADJ_TOT + WS_OUT_RECODE.WS_OUT_INFO[1-1].O_PAY_INT;
                    WS_OUT_RECODE.WS_OUT_INFO[1-1].O_PAY_AMT = WS_VARIABLES.ADJ_TOT + WS_OUT_RECODE.WS_OUT_INFO[1-1].O_PAY_AMT;
                }
            }
        }
    }
    public void R000_CHECK_FULL_INT() throws IOException,SQLException,Exception {
        WS_COND_FLG.FULL_INT_FLG = 'N';
        CEP.TRC(SCCGWA, WS_COND_FLG.FULL_INT_FLG);
        if (LNRLOAN.STOP_VAL_DT < WS_VARIABLES.DUE_DATE 
            && LNRLOAN.STOP_DUE_DT > WS_VARIABLES.VAL_DATE 
            && (LNRLOAN.STOP_VAL_DT != 0 
            || LNRLOAN.STOP_VAL_DT != LNRLOAN.STOP_DUE_DT)) {
            WS_COND_FLG.FULL_INT_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.FULL_INT_FLG);
        if (LNRLOAN.STOP_VAL_DT < WS_VARIABLES.DUE_DATE 
            && LNRLOAN.STOP_DUE_DT == 0 
            && LNRLOAN.STOP_VAL_DT != 0) {
            WS_COND_FLG.FULL_INT_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.FULL_INT_FLG);
        if (LNRLOAN.EMBEZ_DATE < WS_VARIABLES.DUE_DATE 
            && LNRLOAN.EMBEZ_CANCEL_DATE > WS_VARIABLES.VAL_DATE 
            && (LNRLOAN.EMBEZ_DATE != 0 
            || LNRLOAN.EMBEZ_DATE != LNRLOAN.EMBEZ_CANCEL_DATE)) {
            WS_COND_FLG.FULL_INT_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.FULL_INT_FLG);
        if (LNRLOAN.EMBEZ_DATE < WS_VARIABLES.DUE_DATE 
            && LNRLOAN.EMBEZ_CANCEL_DATE == 0 
            && LNRLOAN.EMBEZ_DATE != 0) {
            WS_COND_FLG.FULL_INT_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.FULL_INT_FLG);
        if (WS_COND_FLG.FULL_INT_FLG == 'N' 
            && LNRPAIP.PERD > 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_VARIABLES.VAL_DATE;
            WS_VARIABLES.YYYYMMDD1 = WS_VARIABLES.VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_VARIABLES.YYYYMMDD1+"", WS_VARIABLES.REDEFINES106);
            if (LNRPAIP.PERD_UNIT == 'D') {
                SCCCLDT.DAYS = LNRPAIP.PERD;
            }
            if (LNRPAIP.PERD_UNIT == 'M') {
                SCCCLDT.MTHS = LNRPAIP.PERD;
            }
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            if (LNRPAIP.PERD_UNIT == 'M') {
                WS_VARIABLES.YYYYMMDD2 = SCCCLDT.DATE2;
                IBS.CPY2CLS(SCCGWA, WS_VARIABLES.YYYYMMDD2+"", WS_VARIABLES.REDEFINES111);
                R000_MOD_DAY();
                if (pgmRtn) return;
                SCCCLDT.DATE2 = WS_VARIABLES.YYYYMMDD2;
            }
            if (SCCCLDT.DATE2 != WS_VARIABLES.DUE_DATE) {
                WS_COND_FLG.FULL_INT_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.FULL_INT_FLG);
        WS_COND_FLG.PYIF_FLAG = 'N';
        if (WS_COND_FLG.FULL_INT_FLG == 'N' 
            && WS_VARIABLES.SPE_TERM == LNRICTL.IC_CAL_TERM) {
            IBS.init(SCCGWA, LNRPYIF);
            LNRPYIF.KEY.CONTRACT_NO = LNRICTL.KEY.CONTRACT_NO;
            LNRPYIF.KEY.SUB_CTA_NO = 0;
            LNRPYIF.KEY.TERM = WS_VARIABLES.SPE_TERM;
            T000_STARTBR_LNTPYIF();
            if (pgmRtn) return;
            if (WS_COND_FLG.PYIF_FLAG == 'Y' 
                && LNRPYIF.CUR_REPY_DT >= WS_VARIABLES.VAL_DATE 
                && LNRPYIF.CUR_REPY_DT < WS_VARIABLES.DUE_DATE) {
                WS_COND_FLG.FULL_INT_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.FULL_INT_FLG);
    }
    public void R000_MOD_DAY() throws IOException,SQLException,Exception {
        WS_COND_FLG.CHANGE_FLG = 'N';
        if ((WS_VARIABLES.REDEFINES106.MM1 == 1 
            && WS_VARIABLES.REDEFINES106.DD1 == 31 
            || WS_VARIABLES.REDEFINES106.MM1 == 3 
            && WS_VARIABLES.REDEFINES106.DD1 == 31 
            || WS_VARIABLES.REDEFINES106.MM1 == 4 
            && WS_VARIABLES.REDEFINES106.DD1 == 30 
            || WS_VARIABLES.REDEFINES106.MM1 == 5 
            && WS_VARIABLES.REDEFINES106.DD1 == 31 
            || WS_VARIABLES.REDEFINES106.MM1 == 6 
            && WS_VARIABLES.REDEFINES106.DD1 == 30 
            || WS_VARIABLES.REDEFINES106.MM1 == 7 
            && WS_VARIABLES.REDEFINES106.DD1 == 31 
            || WS_VARIABLES.REDEFINES106.MM1 == 8 
            && WS_VARIABLES.REDEFINES106.DD1 == 31 
            || WS_VARIABLES.REDEFINES106.MM1 == 9 
            && WS_VARIABLES.REDEFINES106.DD1 == 30 
            || WS_VARIABLES.REDEFINES106.MM1 == 10 
            && WS_VARIABLES.REDEFINES106.DD1 == 31 
            || WS_VARIABLES.REDEFINES106.MM1 == 11 
            && WS_VARIABLES.REDEFINES106.DD1 == 30 
            || WS_VARIABLES.REDEFINES106.MM1 == 12 
            && WS_VARIABLES.REDEFINES106.DD1 == 31) 
            && WS_VARIABLES.REDEFINES111.DD2 < WS_VARIABLES.APRD_PAY_DAY 
            && WS_VARIABLES.APRD_PAY_DAY > 28) {
            CEP.TRC(SCCGWA, "YCHC1");
            WS_COND_FLG.CHANGE_FLG = 'Y';
        }
        if (WS_VARIABLES.REDEFINES106.MM1 == 2 
            && WS_VARIABLES.REDEFINES111.DD2 < WS_VARIABLES.APRD_PAY_DAY 
            && WS_VARIABLES.APRD_PAY_DAY > 28) {
            if (WS_VARIABLES.REDEFINES106.YYYY1 % 100 == 0) {
                if (WS_VARIABLES.REDEFINES106.YYYY1 % 400 == 0) {
                    if (WS_VARIABLES.REDEFINES106.DD1 == 29) {
                        CEP.TRC(SCCGWA, "YCHC3");
                        WS_COND_FLG.CHANGE_FLG = 'Y';
                    }
                } else {
                    if (WS_VARIABLES.REDEFINES106.DD1 == 28) {
                        CEP.TRC(SCCGWA, "YCHC4");
                        WS_COND_FLG.CHANGE_FLG = 'Y';
                    }
                }
            } else {
                if (WS_VARIABLES.REDEFINES106.YYYY1 % 4 == 0) {
                    if (WS_VARIABLES.REDEFINES106.DD1 == 29) {
                        CEP.TRC(SCCGWA, "YCHC5");
                        WS_COND_FLG.CHANGE_FLG = 'Y';
                    }
                } else {
                    if (WS_VARIABLES.REDEFINES106.DD1 == 28) {
                        CEP.TRC(SCCGWA, "YCHC6");
                        WS_COND_FLG.CHANGE_FLG = 'Y';
                    }
                }
            }
        }
        if (WS_COND_FLG.CHANGE_FLG == 'Y') {
            if (WS_VARIABLES.APRD_PAY_DAY < 29) {
            } else {
                if (WS_VARIABLES.APRD_PAY_DAY == 29 
                    || WS_VARIABLES.APRD_PAY_DAY == 30) {
                    if (WS_VARIABLES.REDEFINES111.MM2 == 2) {
                        if (WS_VARIABLES.REDEFINES111.YYYY2 % 100 == 0) {
                            if (WS_VARIABLES.REDEFINES111.YYYY2 % 400 == 0) {
                                WS_VARIABLES.REDEFINES111.DD2 = 29;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES111);
                                WS_VARIABLES.YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                            } else {
                                WS_VARIABLES.REDEFINES111.DD2 = 28;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES111);
                                WS_VARIABLES.YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                            }
                        } else {
                            if (WS_VARIABLES.REDEFINES111.YYYY2 % 4 == 0) {
                                WS_VARIABLES.REDEFINES111.DD2 = 29;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES111);
                                WS_VARIABLES.YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                            } else {
                                WS_VARIABLES.REDEFINES111.DD2 = 28;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES111);
                                WS_VARIABLES.YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                            }
                        }
                    } else {
                        WS_VARIABLES.REDEFINES111.DD2 = WS_VARIABLES.APRD_PAY_DAY;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES111);
                        WS_VARIABLES.YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                }
                if (WS_VARIABLES.APRD_PAY_DAY == 31) {
                    if (WS_VARIABLES.REDEFINES111.MM2 == 2) {
                        if (WS_VARIABLES.REDEFINES111.YYYY2 % 100 == 0) {
                            if (WS_VARIABLES.REDEFINES111.YYYY2 % 400 == 0) {
                                WS_VARIABLES.REDEFINES111.DD2 = 29;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES111);
                                WS_VARIABLES.YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                            } else {
                                WS_VARIABLES.REDEFINES111.DD2 = 28;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES111);
                                WS_VARIABLES.YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                            }
                        } else {
                            if (WS_VARIABLES.REDEFINES111.YYYY2 % 4 == 0) {
                                WS_VARIABLES.REDEFINES111.DD2 = 29;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES111);
                                WS_VARIABLES.YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                            } else {
                                WS_VARIABLES.REDEFINES111.DD2 = 28;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES111);
                                WS_VARIABLES.YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                            }
                        }
                    } else {
                        if (WS_VARIABLES.REDEFINES111.MM2 == 4 
                            || WS_VARIABLES.REDEFINES111.MM2 == 6 
                            || WS_VARIABLES.REDEFINES111.MM2 == 9 
                            || WS_VARIABLES.REDEFINES111.MM2 == 11) {
                            WS_VARIABLES.REDEFINES111.DD2 = 30;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES111);
                            WS_VARIABLES.YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            WS_VARIABLES.REDEFINES111.DD2 = 31;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES111);
                            WS_VARIABLES.YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    }
                }
            }
        }
    }
    public void B035_PROC_PAIP_ADJUST() throws IOException,SQLException,Exception {
        WS_COND_FLG.PAIP_ADJUST_FLAG = 'N';
        if (WS_VARIABLES.WS_DATA.COUNT >= LNRPAIP.PHS_TOT_TERM 
            && WS_VARIABLES.SPE_TERM > WS_VARIABLES.PHS_TERM) {
            if (LNCSSCHE.DATA_AREA.TRAN_SEQ == 0) {
                R000_READNEXT_LNTPAIP();
                if (pgmRtn) return;
            } else {
                R000_READNEXT_LNTTMPP();
                if (pgmRtn) return;
            }
            if (LNCRPAIP.RETURN_INFO != 'E') {
                if (LNRPAIP.KEY.CONTRACT_NO.equalsIgnoreCase(LNCSSCHE.DATA_AREA.CONTRACT_NO)) {
                    WS_VARIABLES.PHS_TERM_LAST = WS_VARIABLES.PHS_TERM;
                    WS_VARIABLES.PHS_TERM += LNRPAIP.PHS_TOT_TERM;
                    WS_VARIABLES.PHS_TOT_AMT = LNRPAIP.PHS_PRIN_AMT - LNRPAIP.PHS_REM_PRIN_AMT;
                    WS_VARIABLES.PHS_REM_PRIN = LNRPAIP.PHS_REM_PRIN_AMT;
                    if (WS_VARIABLES.PHS_REM_PRIN > WS_VARIABLES.CALC_INT_BAL) {
                        WS_VARIABLES.PHS_REM_PRIN = WS_VARIABLES.CALC_INT_BAL;
                    }
                    WS_VARIABLES.WS_DATA.COUNT = LNRPAIP.PHS_CAL_TERM;
                    WS_VARIABLES.LAST_ALL_IN_RATE = LNRICTL.CUR_RAT;
                } else {
                    WS_COND_FLG.PROC_END_FLAG = 'Y';
                }
            } else {
                WS_COND_FLG.PROC_END_FLAG = 'Y';
            }
        }
    }
    public void B035_01_SIMULATE_PLPI() throws IOException,SQLException,Exception {
        LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = WS_VARIABLES.PAY_TYP;
        LNRPLPI.KEY.TERM = WS_VARIABLES.SPE_TERM;
        LNRPLPI.VAL_DT = WS_VARIABLES.VAL_DATE;
        LNRPLPI.DUE_DT = WS_VARIABLES.DUE_DATE;
        LNRPLPI.REC_STS = '0';
        if (LNRPAIP.INST_MTH == '1') {
            LNRPLPI.DUE_REPY_AMT = LNRPAIP.CUR_INST_AMT;
        } else {
            LNRPLPI.PRIN_AMT = LNRPAIP.CUR_INST_AMT;
        }
        LNRPLPI.REMARK = SIMULATION_INFO;
    }
    public void R000_UPDATE_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPAIP.KEY.SUB_CTA_NO = 0;
        else LNRPAIP.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        WS_VARIABLES.PAIP_PHS_NO = 1;
        LNRPAIP.KEY.PHS_NO = WS_VARIABLES.PAIP_PHS_NO;
        T00_READUP_LNTPAIP();
        if (pgmRtn) return;
        LNRPAIP.PHS_CMP_TERM += 1;
        T00_REWRITE_LNTPAIP();
        if (pgmRtn) return;
        if (LNRPAIP.PHS_CMP_TERM == LNRPAIP.PHS_TOT_TERM) {
            WS_VARIABLES.PAIP_PHS_NO += 1;
        }
    }
    public void T00_READUP_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.upd = true;
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_RECO_NOTFND, WS_VARIABLES.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T00_REWRITE_LNTPAIP() throws IOException,SQLException,Exception {
        LNRPAIP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPAIP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.REWRITE(SCCGWA, LNRPAIP, LNTPAIP_RD);
    }
    public void T00_WRITE_LNTPLPI() throws IOException,SQLException,Exception {
        LNRPLPI.PRIN_AMT = WS_VARIABLES.PAY_AMT_PRIN;
        LNRPLPI.DUE_REPY_AMT = WS_VARIABLES.PAY_AMT;
        LNRPLPI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLPI.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRPLPI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLPI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.WRITE(SCCGWA, LNRPLPI, LNTPLPI_RD);
    }
    public void B035_CALC_INT_BY_LN_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNCICUT.COMM_DATA.SUF_NO = LNCSSCHE.DATA_AREA.SUF_NO;
        LNCICUT.COMM_DATA.TERM = WS_VARIABLES.SPE_TERM;
        if (WS_VARIABLES.SPE_TERM > LNRICTL.IC_CAL_TERM) {
            LNCICUT.COMM_DATA.CAL_TERM = WS_VARIABLES.SPE_TERM;
        }
        LNCICUT.COMM_DATA.TYPE = 'I';
        LNCICUT.COMM_DATA.BEG_DATE = WS_VARIABLES.VAL_DATE;
        if (WS_VARIABLES.SPE_TERM > LNRICTL.IC_CAL_TERM) {
            LNCICUT.COMM_DATA.AMT = WS_VARIABLES.CALC_INT_BAL;
        }
        LNCICUT.COMM_DATA.END_DATE = WS_VARIABLES.DUE_DATE;
        if (WS_VARIABLES.SPE_TERM == LNRICTL.IC_CAL_TERM 
            && WS_VARIABLES.VAL_DATE < LNRICTL.INT_CUT_DT 
            && WS_VARIABLES.DUE_DATE > LNRICTL.INT_CUT_DT) {
            LNCICUT.COMM_DATA.BEG_DATE = LNRICTL.INT_CUT_DT;
        } else {
            LNCICUT.COMM_DATA.BEG_DATE = WS_VARIABLES.VAL_DATE;
        }
        S000_CALL_LNZICUT();
        if (pgmRtn) return;
        WS_VARIABLES.CALC_BY_LN_BAL_INT = LNCICUT.COMM_DATA.INT_AMT;
    }
    public void B035_CALC_INT_BY_PHS_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.PHS_REM_PRIN);
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNCICUT.COMM_DATA.SUF_NO = LNCSSCHE.DATA_AREA.SUF_NO;
        LNCICUT.COMM_DATA.TERM = WS_VARIABLES.SPE_TERM;
        if (WS_VARIABLES.SPE_TERM > LNRICTL.IC_CAL_TERM) {
            LNCICUT.COMM_DATA.CAL_TERM = WS_VARIABLES.SPE_TERM;
        }
        LNCICUT.COMM_DATA.TYPE = 'I';
        LNCICUT.COMM_DATA.BEG_DATE = WS_VARIABLES.VAL_DATE;
        if (WS_VARIABLES.SPE_TERM > LNRICTL.IC_CAL_TERM 
            || (WS_VARIABLES.SPE_TERM == LNRICTL.IC_CAL_TERM 
            && LNCAPRDM.REC_DATA.BPAY_MTH == '7' 
            && LNRPAIP.KEY.PHS_NO != WS_VARIABLES.MAX_PHS_NO 
            && WS_COND_FLG.PYIF_FLAG == 'N')) {
            LNCICUT.COMM_DATA.AMT = WS_VARIABLES.PHS_REM_PRIN;
        }
        LNCICUT.COMM_DATA.END_DATE = WS_VARIABLES.DUE_DATE;
        if (WS_VARIABLES.SPE_TERM == LNRICTL.IC_CAL_TERM 
            && WS_VARIABLES.VAL_DATE < LNRICTL.INT_CUT_DT 
            && WS_VARIABLES.DUE_DATE > LNRICTL.INT_CUT_DT) {
            LNCICUT.COMM_DATA.BEG_DATE = LNRICTL.INT_CUT_DT;
        } else {
            LNCICUT.COMM_DATA.BEG_DATE = WS_VARIABLES.VAL_DATE;
        }
        S000_CALL_LNZICUT();
        if (pgmRtn) return;
        WS_VARIABLES.CALC_BY_PHS_BAL_INT = LNCICUT.COMM_DATA.INT_AMT;
    }
    public void B035_02_01_SIMULATE_CALC_INT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.CALC_INT_BAL);
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNCICUT.COMM_DATA.SUF_NO = LNCSSCHE.DATA_AREA.SUF_NO;
        LNCICUT.COMM_DATA.TERM = LNRPLPI.KEY.TERM;
        LNCICUT.COMM_DATA.TYPE = 'I';
        if (LNCICUT.COMM_DATA.TERM != LNRICTL.IC_CAL_TERM) {
            LNCICUT.COMM_DATA.AMT = WS_VARIABLES.CALC_INT_BAL;
            LNCICUT.COMM_DATA.BEG_DATE = LNRPLPI.VAL_DT;
        }
        LNCICUT.COMM_DATA.END_DATE = LNRPLPI.DUE_DT;
        if (LNRPLPI.KEY.TERM == LNRICTL.IC_CAL_TERM 
            && LNRPLPI.VAL_DT < LNRICTL.INT_CUT_DT 
            && LNRPLPI.DUE_DT > LNRICTL.INT_CUT_DT) {
            LNCICUT.COMM_DATA.BEG_DATE = LNRICTL.INT_CUT_DT;
        }
        if (LNCICUT.COMM_DATA.AMT != 0) {
            S000_CALL_LNZICUT();
            if (pgmRtn) return;
        }
    }
    public void B035_CALC_FULL_INT_PHS_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.INT_DBAS_STD);
        if (LNCAPRDM.REC_DATA.INT_DBAS_STD.equalsIgnoreCase("01")
            || LNCAPRDM.REC_DATA.INT_DBAS_STD.equalsIgnoreCase("04")
            || LNCAPRDM.REC_DATA.INT_DBAS_STD.equalsIgnoreCase("05")) {
            WS_VARIABLES.BASDAYS = 360;
        } else if (LNCAPRDM.REC_DATA.INT_DBAS_STD.equalsIgnoreCase("02")) {
            WS_VARIABLES.BASDAYS = 365;
        } else if (LNCAPRDM.REC_DATA.INT_DBAS_STD.equalsIgnoreCase("03")) {
            WS_VARIABLES.BASDAYS = 366;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_INTDAY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRPAIP.PERD_UNIT == 'M') {
            WS_VARIABLES.DAYS1 = LNRPAIP.PERD * 30;
        } else {
            WS_VARIABLES.DAYS1 = LNRPAIP.PERD;
        }
        if (WS_VARIABLES.PHS_REM_PRIN >= 0) {
            WS_VARIABLES.INT_AMT0 = WS_VARIABLES.PHS_REM_PRIN * WS_VARIABLES.ALL_IN_RATE * WS_VARIABLES.DAYS1 / ( 100 * WS_VARIABLES.BASDAYS );
            bigD = new BigDecimal(WS_VARIABLES.INT_AMT0);
            WS_VARIABLES.INT_AMT0 = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        } else {
            WS_VARIABLES.INT_AMT0 = 0;
        }
        if (!LNCCONTM.REC_DATA.CCY.equalsIgnoreCase("156")) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = LNCCONTM.REC_DATA.CCY;
            S00_CALL_BPZQCCY();
            if (pgmRtn) return;
            if (BPCQCCY.DATA.DEC_MTH == 0) {
                WS_VARIABLES.LOW_CCY_INT_AMT = WS_VARIABLES.INT_AMT0 + 0;
                bigD = new BigDecimal(WS_VARIABLES.LOW_CCY_INT_AMT);
                WS_VARIABLES.LOW_CCY_INT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_VARIABLES.INT_AMT0 = (double)WS_VARIABLES.LOW_CCY_INT_AMT;
            }
            if (BPCQCCY.DATA.DEC_MTH == 1) {
                WS_VARIABLES.LOW_CCY_INT_AMT1 = WS_VARIABLES.INT_AMT0 + 0;
                bigD = new BigDecimal(WS_VARIABLES.LOW_CCY_INT_AMT1);
                WS_VARIABLES.LOW_CCY_INT_AMT1 = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_VARIABLES.INT_AMT0 = WS_VARIABLES.LOW_CCY_INT_AMT1;
            }
            if (BPCQCCY.DATA.DEC_MTH == 2) {
                WS_VARIABLES.LOW_CCY_INT_AMT2 = WS_VARIABLES.INT_AMT0 + 0;
                bigD = new BigDecimal(WS_VARIABLES.LOW_CCY_INT_AMT2);
                WS_VARIABLES.LOW_CCY_INT_AMT2 = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_VARIABLES.INT_AMT0 = WS_VARIABLES.LOW_CCY_INT_AMT2;
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.INT_AMT0);
        }
    }
    public void B035_03_SIMULATE_DUE_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_VARIABLES.VAL_DATE;
        if (LNRPAIP.PERD_UNIT == 'M') {
            SCCCLDT.MTHS = LNRPAIP.PERD;
        } else {
            SCCCLDT.DAYS = LNRPAIP.PERD;
        }
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_VARIABLES.DUE_DATE = SCCCLDT.DATE2;
        WS_VARIABLES.DUE_DATE1 = SCCCLDT.DATE2;
        if (LNRPAIP.PERD_UNIT == 'M') {
            WS_VARIABLES.YYYYMMDD = WS_VARIABLES.DUE_DATE;
            IBS.CPY2CLS(SCCGWA, WS_VARIABLES.YYYYMMDD+"", WS_VARIABLES.REDEFINES101);
            B035_04_MOD_DAY();
            if (pgmRtn) return;
            WS_VARIABLES.DUE_DATE = WS_VARIABLES.OUT_DATE;
        }
        if (WS_VARIABLES.SPE_TERM == LNRICTL.IC_CAL_TERM) {
            WS_VARIABLES.DUE_DATE = LNRICTL.IC_CAL_DUE_DT;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.DUE_DATE);
        if (WS_VARIABLES.SPE_TERM == WS_VARIABLES.TOT_TERM 
            || WS_VARIABLES.DUE_DATE >= LNCCONTM.REC_DATA.MAT_DATE) {
            WS_VARIABLES.DUE_DATE = LNCCONTM.REC_DATA.MAT_DATE;
            WS_COND_FLG.PROC_END_FLAG = 'Y';
        }
    }
    public void B035_04_MOD_DAY() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.APRD_PAY_DAY < 29) {
            if (WS_VARIABLES.APRD_PAY_DAY > 0) {
                WS_VARIABLES.REDEFINES101.DD = WS_VARIABLES.APRD_PAY_DAY;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
            }
        } else {
            if (WS_VARIABLES.APRD_PAY_DAY == 29 
                || WS_VARIABLES.APRD_PAY_DAY == 30) {
                if (WS_VARIABLES.REDEFINES101.MM == 2) {
                    if (WS_VARIABLES.REDEFINES101.YYYY % 100 == 0) {
                        if (WS_VARIABLES.REDEFINES101.YYYY % 400 == 0) {
                            WS_VARIABLES.REDEFINES101.DD = 29;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            WS_VARIABLES.REDEFINES101.DD = 28;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    } else {
                        if (WS_VARIABLES.REDEFINES101.YYYY % 4 == 0) {
                            WS_VARIABLES.REDEFINES101.DD = 29;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            WS_VARIABLES.REDEFINES101.DD = 28;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    }
                } else {
                    WS_VARIABLES.REDEFINES101.DD = WS_VARIABLES.APRD_PAY_DAY;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                    WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                }
            }
            if (WS_VARIABLES.APRD_PAY_DAY == 31) {
                if (WS_VARIABLES.REDEFINES101.MM == 2) {
                    if (WS_VARIABLES.REDEFINES101.YYYY % 100 == 0) {
                        if (WS_VARIABLES.REDEFINES101.YYYY % 400 == 0) {
                            WS_VARIABLES.REDEFINES101.DD = 29;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            WS_VARIABLES.REDEFINES101.DD = 28;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    } else {
                        if (WS_VARIABLES.REDEFINES101.YYYY % 4 == 0) {
                            WS_VARIABLES.REDEFINES101.DD = 29;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            WS_VARIABLES.REDEFINES101.DD = 28;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    }
                } else {
                    if (WS_VARIABLES.REDEFINES101.MM == 4 
                        || WS_VARIABLES.REDEFINES101.MM == 6 
                        || WS_VARIABLES.REDEFINES101.MM == 9 
                        || WS_VARIABLES.REDEFINES101.MM == 11) {
                        WS_VARIABLES.REDEFINES101.DD = 30;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        WS_VARIABLES.REDEFINES101.DD = 31;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES101);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                }
            }
        }
        WS_VARIABLES.OUT_DATE = WS_VARIABLES.YYYYMMDD;
    }
    public void B035_06_MOD_DAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_VARIABLES.DUE_DATE;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        WS_VARIABLES.DUE_DATE_WKNO = SCCCKDT.WEEK_NO;
        if (WS_VARIABLES.APRD_PAY_DAY != 1 
            && WS_VARIABLES.APRD_PAY_DAY != 2 
            && WS_VARIABLES.APRD_PAY_DAY != 3 
            && WS_VARIABLES.APRD_PAY_DAY != 4 
            && WS_VARIABLES.APRD_PAY_DAY != 5 
            && WS_VARIABLES.APRD_PAY_DAY != 6 
            && WS_VARIABLES.APRD_PAY_DAY != 7) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_APPINTED_DAY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.DUE_DATE_WKNO != 0 
            && WS_VARIABLES.DUE_DATE_WKNO != WS_VARIABLES.APRD_PAY_DAY) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_VARIABLES.DUE_DATE;
            if (WS_VARIABLES.DUE_DATE_WKNO < WS_VARIABLES.APRD_PAY_DAY) {
                WS_VARIABLES.INTRVL_DAYS = (short) (WS_VARIABLES.APRD_PAY_DAY - WS_VARIABLES.DUE_DATE_WKNO);
                SCCCLDT.DAYS = WS_VARIABLES.INTRVL_DAYS - 7;
            } else {
                SCCCLDT.DAYS = WS_VARIABLES.APRD_PAY_DAY - WS_VARIABLES.DUE_DATE_WKNO;
            }
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_VARIABLES.DUE_DATE = SCCCLDT.DATE2;
        }
    }
    public void B060_30_STBR_PLPI_BY_KEY() throws IOException,SQLException,Exception {
        if (LNRPLPI.KEY.REPY_MTH == ' ') {
            T000_STARTBR_PLPI_BY_KEY_PROC();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_PLPI_BY_KEY_PROC1();
            if (pgmRtn) return;
        }
    }
    public void B401_COMPUTE_PAGE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (LNCSSCHE.PAGE_NUM == 0) {
            WS_VARIABLES.WS_DATA.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_DATA.CURR_PAGE = (short) LNCSSCHE.PAGE_NUM;
        }
        WS_VARIABLES.WS_DATA.LAST_PAGE = 'N';
        if (LNCSSCHE.PAGE_ROW == 0) {
            WS_VARIABLES.WS_DATA.PAGE_ROW = PAGE_ROW;
        } else {
            if (LNCSSCHE.PAGE_ROW > PAGE_ROW) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.WS_DATA.PAGE_ROW = (short) LNCSSCHE.PAGE_ROW;
            }
        }
        WS_VARIABLES.WS_DATA.NEXT_START_NUM = ( ( WS_VARIABLES.WS_DATA.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATA.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATA.NEXT_START_NUM;
    }
    public void B220_CALL_LNZRSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.SUB_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRSCHT.KEY.TYPE = LNRPLPI.KEY.REPY_MTH;
        LNRSCHT.KEY.TERM = LNRPLPI.KEY.TERM;
        CEP.TRC(SCCGWA, WS_VARIABLES.SEQ_TMP);
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_TMP;
        if (WS_COND_FLG.PLPI_FLAG == 'Y') {
            LNRSCHT.ACTION = 'A';
            LNCSSCHE.MOD_FLG = 'Y';
        } else {
            LNRSCHT.ACTION = 'I';
        }
        LNRSCHT.VAL_DTE = LNRPLPI.VAL_DT;
        LNRSCHT.DUE_DTE = LNRPLPI.DUE_DT;
        LNRSCHT.ALL_IN_RATE = WS_VARIABLES.ALL_IN_RATE;
        LNRSCHT.AMOUNT = WS_VARIABLES.PAY_AMT;
        LNRSCHT.PAY_PRIN = WS_VARIABLES.PAY_AMT_PRIN;
        LNRSCHT.PAY_INT = WS_VARIABLES.PAY_AMT_INT;
        LNRSCHT.REMARK = LNRPLPI.REMARK;
        LNRSCHT.APPORT_REF = 0;
        LNRSCHT.XBD_FEE = WS_VARIABLES.PAY_XBD_FEE;
        LNCRSCHT.FUNC = 'A';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 869;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B220_CALL_LNZRSCHT_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.SUB_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRSCHT.KEY.TYPE = LNRRCVD.KEY.AMT_TYP;
        LNRSCHT.KEY.TERM = LNRRCVD.KEY.TERM;
        CEP.TRC(SCCGWA, WS_VARIABLES.SEQ_TMP);
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_TMP;
        LNRSCHT.ACTION = 'I';
        LNRSCHT.VAL_DTE = LNRRCVD.VAL_DT;
        LNRSCHT.DUE_DTE = LNRRCVD.DUE_DT;
        LNRSCHT.ALL_IN_RATE = LNRRCVD.ALL_IN_RATE;
        LNRSCHT.AMOUNT = WS_VARIABLES.PAY_AMT;
        LNRSCHT.PAY_PRIN = WS_VARIABLES.PAY_AMT_PRIN;
        LNRSCHT.PAY_INT = WS_VARIABLES.PAY_AMT_INT;
        LNRSCHT.REMARK = WS_VARIABLES.REMARK;
        LNRSCHT.APPORT_REF = 0;
        LNCRSCHT.FUNC = 'A';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 869;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B401_GET_PAGE_INFO() throws IOException,SQLException,Exception {
        B401_COMPUTE_PAGE_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "201810158888");
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.IDX);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && WS_VARIABLES.WS_DATA.IDX < WS_VARIABLES.WS_DATA.PAGE_ROW) {
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.IDX);
                WS_VARIABLES.WS_DATA.IDX += 1;
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.IDX);
                WS_VARIABLES.WS_DATA.NEXT_START_NUM += 1;
                WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATA.NEXT_START_NUM;
                WS_VARIABLES.NS = 0;
                while (WS_VARIABLES.WS_DATA.IDX != WS_VARIABLES.NK) {
                    PAGE_ROW -= WS_VARIABLES.NS;
                    WS_VARIABLES.NK = PAGE_ROW;
                    WS_VARIABLES.NS += 1;
                    IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.NK-1]);
                }
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_TYP = LNRSCHT.KEY.TYPE;
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_TYP = LNRSCHT.KEY.TYPE;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM = LNRSCHT.KEY.TERM;
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM = LNRSCHT.KEY.TERM;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_VAL_DATE = LNRSCHT.VAL_DTE;
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_VAL_DATE = LNRSCHT.VAL_DTE;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_DUE_DATE = LNRSCHT.DUE_DTE;
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_DUE_DATE = LNRSCHT.DUE_DTE;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_INT_RATE = LNRSCHT.ALL_IN_RATE;
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_INT_RATE = LNRSCHT.ALL_IN_RATE;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_AMT = LNRSCHT.AMOUNT;
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_AMT = LNRSCHT.AMOUNT;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_PRIN = LNRSCHT.PAY_PRIN;
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_PRIN = LNRSCHT.PAY_PRIN;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_INT = LNRSCHT.PAY_INT;
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_INT = LNRSCHT.PAY_INT;
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.IDX);
                CEP.TRC(SCCGWA, "201810157777");
                if (LNCAPRDM.REC_DATA.CHG_DB_FLG == '3') {
                    if (LNCAPRDM.REC_DATA.HAND_CHG_RATE > 0) {
                        WS_VARIABLES.ENT_L_HD_CHG = LNRSCHT.PAY_INT * LNCAPRDM.REC_DATA.HAND_CHG_RATE / 100;
                    }
                }
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_ENT_L_HD_CHG = WS_VARIABLES.ENT_L_HD_CHG;
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_ENT_L_HD_CHG = WS_VARIABLES.ENT_L_HD_CHG;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_REMARK = LNRSCHT.REMARK;
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_REMARK = LNRSCHT.REMARK;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CTA_NO = LNRSCHT.KEY.SUB_CTA_NO;
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CTA_NO = LNRSCHT.KEY.SUB_CTA_NO;
                if (WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM >= WS_VARIABLES.ST_TERM 
                    && WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM <= WS_VARIABLES.SUBTERM 
                    && WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_TYP != 'P') {
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_SUBS_FLG = 'Y';
                } else {
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_SUBS_FLG = 'N';
                }
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_SUBS_FLG = WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_SUBS_FLG;
                if (WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM >= WS_VARIABLES.ST_TERM 
                    && WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM <= WS_VARIABLES.SUBTERM) {
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PROJ_NO = LNRSUBS.KEY.PROJ_NO;
                    LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PROJ_NO = LNRSUBS.KEY.PROJ_NO;
                }
                T102_READNEXT_LNTSCHT();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_VARIABLES.WS_DATA.TOTAL_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
                WS_VARIABLES.WS_DATA.BAL_CNT = WS_VARIABLES.WS_DATA.IDX;
                WS_DB_VARS.TOTAL_NUM = ( WS_VARIABLES.WS_DATA.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATA.PAGE_ROW + WS_VARIABLES.WS_DATA.BAL_CNT;
                WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATA.PAGE_ROW = (short) WS_VARIABLES.WS_DATA.BAL_CNT;
            } else {
                T104_GET_TOTAL_LNTSCHT();
                if (pgmRtn) return;
                if (WS_VARIABLES.WS_DATA.PAGE_ROW == 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_PAGE_ROW;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_VARIABLES.WS_DATA.BAL_CNT = WS_DB_VARS.TOTAL_NUM % WS_VARIABLES.WS_DATA.PAGE_ROW;
                WS_VARIABLES.WS_DATA.TOTAL_PAGE = (short) ((WS_DB_VARS.TOTAL_NUM - WS_VARIABLES.WS_DATA.BAL_CNT) / WS_VARIABLES.WS_DATA.PAGE_ROW);
                if (WS_VARIABLES.WS_DATA.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATA.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_VARIABLES.WS_DATA.TOTAL_PAGE = 1;
            WS_DB_VARS.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATA.PAGE_ROW = 0;
            WS_VARIABLES.WS_DATA.CURR_PAGE = 1;
        }
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATA.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_DB_VARS.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATA.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.WS_DATA.PAGE_ROW;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_SEQ_NO = WS_VARIABLES.SEQ_TMP;
        R020_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void R000_GET_RATB_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRATB);
        LNCRATB.COMM_DATA.LN_AC = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNCRATB.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCRATB.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCRATB.COMM_DATA.SUF_NO = "0" + LNCRATB.COMM_DATA.SUF_NO;
        LNCRATB.COMM_DATA.RATE_TYPE = 'N';
        LNCRATB.COMM_DATA.BEG_DATE = WS_VARIABLES.VAL_DATE;
        LNCRATB.COMM_DATA.END_DATE = WS_VARIABLES.DUE_DATE;
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.RATE_TYPE);
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.END_DATE);
        S000_CALL_LNZRATB();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRATB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-RATH-BRWQ", LNCRATB);
        if (LNCRATB.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRATB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_STARTBR_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        IBS.init(SCCGWA, LNCRPLPI);
        LNCRPLPI.FUNC = 'B';
        LNCRPLPI.OPT = 'A';
        LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = WS_VARIABLES.PAY_TYP;
        LNRPLPI.KEY.TERM = WS_VARIABLES.REPAY_TERM;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 727;
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void R010_STARTBR_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        IBS.init(SCCGWA, LNCRPLPI);
        LNCRPLPI.FUNC = 'B';
        LNCRPLPI.OPT = 'A';
        LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = WS_VARIABLES.PAY_TYP;
        LNRPLPI.KEY.TERM = WS_VARIABLES.REPAY_TERM;
        B060_30_STBR_PLPI_BY_KEY();
        if (pgmRtn) return;
    }
    public void R010_STARTBR_LNTSCHT_P_AND_I() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSCHT);
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_TMP;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRSCHT.KEY.SUB_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.CUR_FLG == 'Y') {
            T000_STARTBR_SCHT_BY_TERM();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_SCHT_P_AND_I();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_SCHT_BY_TERM() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND ( ( TYPE = 'I' "
            + "AND TERM = :WS_DB_VARS.I_TERM ) "
            + "OR ( TYPE = 'P' "
            + "AND TERM = :WS_DB_VARS.P_TERM ) )";
        LNTSCHT_BR.rp.order = "DUE_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_SCHT_P_AND_I() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND ( ( TYPE = 'I' "
            + "AND TERM >= :WS_DB_VARS.I_TERM ) "
            + "OR ( TYPE = 'P' "
            + "AND TERM >= :WS_DB_VARS.P_TERM ) )";
        LNTSCHT_BR.rp.order = "DUE_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_RCVD_P_AND_I() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
        else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO";
        LNTRCVD_BR.rp.where = "SUB_CTA_NO = :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND ( ( AMT_TYP = 'I' "
            + "AND TERM >= :WS_DB_VARS.I_TERM ) "
            + "OR ( AMT_TYP = 'P' "
            + "AND TERM >= :WS_DB_VARS.P_TERM ) )";
        LNTRCVD_BR.rp.order = "DUE_DT,AMT_TYP";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_READNEXT_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RCVD_FLAG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_COND_FLG.RCVD_FLAG = 'N';
            }
        }
    }
    public void T000_ENDBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, WS_VARIABLES.WS_MSGID);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void R000_READNEXT_LNTPLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.FUNC = 'B';
        LNCRPLPI.OPT = 'R';
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void R000_ENDBR_LNTPLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.FUNC = 'B';
        LNCRPLPI.OPT = 'E';
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void R010_ENDBR_LNTPLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.FUNC = 'B';
        LNCRPLPI.OPT = 'E';
        T000_ENDBR_PLPI();
        if (pgmRtn) return;
    }
    public void R000_STARTBR_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        IBS.init(SCCGWA, LNCRPAIP);
        LNCRPAIP.FUNC = 'B';
        LNCRPAIP.OPT = 'S';
        LNRPAIP.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPAIP.KEY.SUB_CTA_NO = 0;
        else LNRPAIP.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRPAIP.KEY.PHS_NO = 0;
        LNCRPAIP.REC_PTR = LNRPAIP;
        LNCRPAIP.REC_LEN = 632;
        S000_CALL_LNZPAIPL();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_LNTPAIP() throws IOException,SQLException,Exception {
        LNCRPAIP.FUNC = 'B';
        LNCRPAIP.OPT = 'R';
        S000_CALL_LNZPAIPL();
        if (pgmRtn) return;
    }
    public void R000_ENDBR_LNTPAIP() throws IOException,SQLException,Exception {
        LNCRPAIP.FUNC = 'B';
        LNCRPAIP.OPT = 'E';
        S000_CALL_LNZPAIPL();
        if (pgmRtn) return;
    }
    public void R000_GROUP_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNRPAIP);
        LNCRPAIP.FUNC = 'G';
        LNRPAIP.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPAIP.KEY.SUB_CTA_NO = 0;
        else LNRPAIP.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRPAIP.KEY.PHS_NO = 999;
        LNCRPAIP.REC_LEN = 632;
        LNCRPAIP.REC_PTR = LNRPAIP;
        S000_CALL_LNZPAIPL();
        if (pgmRtn) return;
        WS_VARIABLES.TOT_TERM = LNCRPAIP.TOT_TENORS;
        CEP.TRC(SCCGWA, WS_VARIABLES.TOT_TERM);
    }
    public void R000_READ_LNTRCVD_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRRCVD);
        IBS.init(SCCGWA, LNRRCVD);
        LNCRRCVD.FUNC = 'I';
        LNRRCVD.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
        else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRRCVD.KEY.AMT_TYP = WS_VARIABLES.PAY_TYP;
        LNRRCVD.KEY.TERM = LNRPLPI.KEY.TERM;
        LNCRRCVD.REC_PTR = LNRRCVD;
        LNCRRCVD.REC_LEN = 0;
        S000_CALL_LNZRRCVD_2();
        if (pgmRtn) return;
    }
    public void R000_STARTBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRRCVD);
        IBS.init(SCCGWA, LNRRCVD);
        LNCRRCVD.FUNC = 'B';
        LNCRRCVD.OPT = '3';
        LNRRCVD.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
        else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRRCVD.KEY.AMT_TYP = WS_VARIABLES.PAY_TYP;
        LNRRCVD.KEY.TERM = WS_VARIABLES.REPAY_TERM;
        LNCRRCVD.REC_PTR = LNRRCVD;
        LNCRRCVD.REC_LEN = 1008;
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_LNTRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.FUNC = 'B';
        LNCRRCVD.OPT = 'R';
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "H");
    }
    public void R000_ENDBR_LNTRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.FUNC = 'B';
        LNCRRCVD.OPT = 'E';
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
    }
    public void R010_TRANS_OUTPUT_DATA_SCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1]);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.IDX);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAY_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAY_AMT_PRIN);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAY_AMT_INT);
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_TYP = LNRSCHT.KEY.TYPE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM = LNRSCHT.KEY.TERM;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_VAL_DATE = LNRSCHT.VAL_DTE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_DUE_DATE = LNRSCHT.DUE_DTE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_INT_RATE = LNRSCHT.ALL_IN_RATE;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_INT_RATE = LNRSCHT.ALL_IN_RATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_AMT = WS_VARIABLES.PAY_AMT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_PRIN = WS_VARIABLES.PAY_AMT_PRIN;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_INT = WS_VARIABLES.PAY_AMT_INT;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.IDX);
        CEP.TRC(SCCGWA, "201810157777");
        if (LNCAPRDM.REC_DATA.CHG_DB_FLG == '3') {
            if (LNCAPRDM.REC_DATA.HAND_CHG_RATE > 0) {
                WS_VARIABLES.ENT_L_HD_CHG = WS_VARIABLES.PAY_AMT_INT * LNCAPRDM.REC_DATA.HAND_CHG_RATE / 100;
            }
        }
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_ENT_L_HD_CHG = WS_VARIABLES.ENT_L_HD_CHG;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_REMARK = LNRSCHT.REMARK;
        if (WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM >= WS_VARIABLES.ST_TERM 
            && WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM <= WS_VARIABLES.SUBTERM 
            && WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_TYP != 'P') {
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_SUBS_FLG = 'Y';
        } else {
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_SUBS_FLG = 'N';
        }
        if (WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM >= WS_VARIABLES.ST_TERM 
            && WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM <= WS_VARIABLES.SUBTERM) {
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PROJ_NO = LNRSUBS.KEY.PROJ_NO;
        }
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
    }
    public void R010_TRANS_OUTPUT_DATA_SCHT_C() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_DATA.C_IDX += 1;
        CEP.TRC(SCCGWA, LNRSCHT.PAY_PRIN);
        if (WS_VARIABLES.WS_DATA.C_IDX <= 20) {
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_PAY_TYP = LNRSCHT.KEY.TYPE;
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_TERM = LNRSCHT.KEY.TERM;
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_VAL_DATE = LNRSCHT.VAL_DTE;
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_DUE_DATE = LNRSCHT.DUE_DTE;
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_INT_RATE = LNRSCHT.ALL_IN_RATE;
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_PAY_AMT = WS_VARIABLES.PAY_AMT;
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_PAY_PRIN = WS_VARIABLES.PAY_AMT_PRIN;
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_PAY_INT = WS_VARIABLES.PAY_AMT_INT;
            if (LNCAPRDM.REC_DATA.CHG_DB_FLG == '3') {
                if (LNCAPRDM.REC_DATA.HAND_CHG_RATE > 0) {
                    WS_VARIABLES.ENT_L_HD_CHG = WS_VARIABLES.PAY_AMT_INT * LNCAPRDM.REC_DATA.HAND_CHG_RATE / 100;
                }
            }
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_ENT_L_HD_CHG = WS_VARIABLES.ENT_L_HD_CHG;
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_REMARK = LNRSCHT.REMARK;
            if (WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_TERM >= WS_VARIABLES.ST_TERM 
                && WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_TERM <= WS_VARIABLES.SUBTERM 
                && LNRSCHT.KEY.TYPE != 'P') {
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_SUBS_FLG = 'Y';
            } else {
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_SUBS_FLG = 'N';
            }
            if (WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_TERM >= WS_VARIABLES.ST_TERM 
                && WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_TERM <= WS_VARIABLES.SUBTERM) {
                LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_PROJ_NO = LNRSUBS.KEY.PROJ_NO;
            }
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.C_IDX-1].O_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        }
    }
    public void R010_TRANS_OUTPUT_DATA_PLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1]);
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_TYP = LNRPLPI.KEY.REPY_MTH;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_TYP = LNRPLPI.KEY.REPY_MTH;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM = LNRPLPI.KEY.TERM;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM = LNRPLPI.KEY.TERM;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_VAL_DATE = LNRPLPI.VAL_DT;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_VAL_DATE = LNRPLPI.VAL_DT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_DUE_DATE = LNRPLPI.DUE_DT;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_DUE_DATE = LNRPLPI.DUE_DT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_INT_RATE = LNCRATQ.COMM_DATA.RATE;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_INT_RATE = LNCRATQ.COMM_DATA.RATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_AMT = WS_VARIABLES.PAY_AMT;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_AMT = WS_VARIABLES.PAY_AMT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_PRIN = WS_VARIABLES.PAY_AMT_PRIN;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_PRIN = WS_VARIABLES.PAY_AMT_PRIN;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_INT = WS_VARIABLES.PAY_AMT_INT;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_INT = WS_VARIABLES.PAY_AMT_INT;
        if (LNCAPRDM.REC_DATA.CHG_DB_FLG == '3') {
            if (LNCAPRDM.REC_DATA.HAND_CHG_RATE > 0) {
                WS_VARIABLES.ENT_L_HD_CHG = WS_VARIABLES.PAY_AMT_INT * LNCAPRDM.REC_DATA.HAND_CHG_RATE / 100;
            }
        }
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_ENT_L_HD_CHG = WS_VARIABLES.ENT_L_HD_CHG;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_ENT_L_HD_CHG = WS_VARIABLES.ENT_L_HD_CHG;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_REMARK = LNRPLPI.REMARK;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_REMARK = LNRPLPI.REMARK;
        if (WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM >= WS_VARIABLES.ST_TERM 
            && WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM <= WS_VARIABLES.SUBTERM 
            && WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PAY_TYP != 'P') {
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_SUBS_FLG = 'Y';
        } else {
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_SUBS_FLG = 'N';
        }
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_SUBS_FLG = WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_SUBS_FLG;
        if (WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM >= WS_VARIABLES.ST_TERM 
            && WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM <= WS_VARIABLES.SUBTERM) {
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PROJ_NO = LNRSUBS.KEY.PROJ_NO;
            LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PROJ_NO = LNRSUBS.KEY.PROJ_NO;
        }
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNCSSCHE.OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
    }
    public void B021_GET_CONTRACT_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRLOAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTLOAN", LNCRLOAN);
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.WEEK_NO);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            WS_VARIABLES.ERR_MSG = "" + SCCCKDT.RC;
            JIBS_tmp_int = WS_VARIABLES.ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_VARIABLES.ERR_MSG = "0" + WS_VARIABLES.ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRPLPI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RETURN_INFO == 'E') {
            WS_COND_FLG.PLPI_FLAG = 'Y';
        } else {
            if (LNCRPLPI.RC.RC_CODE != 0) {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZPAIPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-R-PAIPL-MAIN", LNCRPAIP);
        if (LNCRPAIP.RETURN_INFO == 'E') {
            WS_COND_FLG.PAIP_FLAG = 'Y';
        } else {
            if (LNCRPAIP.RC.RC_CODE != 0) {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZSCALT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CAL-TERM", LNCSCALT);
        if (LNCSCALT.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSCALT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PLPI_MAX_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_RD.where = "REPY_MTH IN ( 'C' , 'P' ) "
            + "AND REC_STS = '1'";
        LNTPLPI_RD.fst = true;
        LNTPLPI_RD.order = "REPY_MTH DESC,TERM DESC";
        IBS.READ(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
    }
    public void T000_GET_PLPI_SUM_AMT() throws IOException,SQLException,Exception {
        WS_DB_VARS.TOT_P_AMT = 0;
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.set = "WS-TOT-P-AMT=IFNULL(SUM(PRIN_AMT),0)";
        LNTPLPI_RD.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH";
        LNTPLPI_RD.where = "REC_STS = :LNRPLPI.REC_STS";
        IBS.GROUP(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
    }
    public void R000_STARTBR_LNTTMPP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTMPP);
        IBS.init(SCCGWA, LNCRTMPP);
        LNCRTMPP.FUNC = 'B';
        LNCRTMPP.OPT = 'O';
        LNRTMPP.KEY.TRAN_SEQ = LNCSSCHE.DATA_AREA.TRAN_SEQ;
        LNRTMPP.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRTMPP.KEY.SUB_CTA_NO = 0;
        else LNRTMPP.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRTMPP.KEY.PHS_NO = 0;
        LNCRTMPP.REC_PTR = LNRTMPP;
        LNCRTMPP.REC_LEN = 703;
        S000_CALL_LNZTMPPL();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_LNTTMPP() throws IOException,SQLException,Exception {
        LNCRTMPP.FUNC = 'B';
        LNCRTMPP.OPT = 'R';
        S000_CALL_LNZTMPPL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNRTMPP.KEY.CONTRACT_NO;
        LNRPAIP.KEY.SUB_CTA_NO = LNRTMPP.KEY.SUB_CTA_NO;
        LNRPAIP.KEY.PHS_NO = LNRTMPP.KEY.PHS_NO;
        LNRPAIP.INST_MTH = LNRTMPP.INST_MTH;
        LNRPAIP.PERD = LNRTMPP.PERD;
        LNRPAIP.PERD_UNIT = LNRTMPP.PERD_UNIT;
        LNRPAIP.PHS_INST_AMT = LNRTMPP.PHS_INST_AMT;
        LNRPAIP.PHS_PRIN_AMT = LNRTMPP.PHS_PRIN_AMT;
        LNRPAIP.PHS_TOT_TERM = LNRTMPP.PHS_TOT_TERM;
        LNRPAIP.PHS_REM_PRIN_AMT = LNRTMPP.PHS_REM_PRIN_AMT;
        LNRPAIP.PHS_CAL_TERM = LNRTMPP.PHS_CAL_TERM;
        LNRPAIP.PHS_CMP_TERM = LNRTMPP.PHS_CMP_TERM;
        LNRPAIP.CUR_INST_AMT = LNRTMPP.CUR_INST_AMT;
        LNRPAIP.CUR_INST_IRAT = LNRTMPP.CUR_INST_IRAT;
        LNRPAIP.CRT_DATE = LNRTMPP.CRT_DATE;
        LNRPAIP.CRT_TLR = LNRTMPP.CRT_TLR;
        LNRPAIP.UPDTBL_DATE = LNRTMPP.UPDTBL_DATE;
        LNRPAIP.UPDTBL_TLR = LNRTMPP.UPDTBL_TLR;
        LNRPAIP.TS = LNRTMPP.TS;
    }
    public void R000_ENDBR_LNTTMPP() throws IOException,SQLException,Exception {
        LNCRTMPP.FUNC = 'B';
        LNCRTMPP.OPT = 'E';
        S000_CALL_LNZTMPPL();
        if (pgmRtn) return;
    }
    public void R000_GROUP_LNTTMPP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRTMPP);
        IBS.init(SCCGWA, LNRTMPP);
        LNCRTMPP.FUNC = 'G';
        LNRTMPP.KEY.TRAN_SEQ = LNCSSCHE.DATA_AREA.TRAN_SEQ;
        LNRTMPP.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRTMPP.KEY.SUB_CTA_NO = 0;
        else LNRTMPP.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRTMPP.KEY.PHS_NO = 999;
        LNCRTMPP.REC_LEN = 703;
        LNCRTMPP.REC_PTR = LNRTMPP;
        S000_CALL_LNZTMPPL();
        if (pgmRtn) return;
        WS_VARIABLES.TOT_TERM = LNCRTMPP.TOT_TENORS;
        CEP.TRC(SCCGWA, WS_VARIABLES.TOT_TERM);
    }
    public void R020_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && JIBS_tmp_str[1].equalsIgnoreCase("0133020")) {
            if (LNCSSCHE.CUR_FLG == 'Y' 
                && WS_VARIABLES.WS_DATA.CURR_PAGE != 1) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_1683;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCSSCHE.CUR_FLG == 'Y' 
                && WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW > 1) {
                if ((LNCSSCHE.DATA_AREA.PAY_TYP == 'I' 
                    || LNCSSCHE.DATA_AREA.PAY_TYP == 'P' 
                    || LNCSSCHE.DATA_AREA.PAY_TYP == 'C')) {
                    WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = 1;
                    WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = 1;
                    WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = 1;
                    WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = 'Y';
                    WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = 1;
                    IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[2-1]);
                    IBS.init(SCCGWA, LNCSSCHE.OUT_INFO[2-1]);
                } else {
                    if (WS_OUT_RECODE.WS_OUT_INFO[1-1].O_DUE_DATE != WS_OUT_RECODE.WS_OUT_INFO[2-1].O_DUE_DATE) {
                        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = 1;
                        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = 1;
                        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = 1;
                        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = 'Y';
                        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = 1;
                        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[2-1]);
                        IBS.init(SCCGWA, LNCSSCHE.OUT_INFO[2-1]);
                    }
                }
            }
            WS_OUT_RECODE.WS_OUT_HEAD.O_SEQ_NO = WS_VARIABLES.SEQ_TMP;
            WS_VARIABLES.J = WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW;
            WS_VARIABLES.J += 1;
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = "LN302";
            SCCFMT.DATA_PTR = WS_OUT_RECODE;
            SCCFMT.DATA_LEN = 2641;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void R000_RECOMP_INST_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCILCM);
        if (LNRPAIP.INST_MTH == '1') {
            LNCILCM.COMM_DATA.FORM_CODE = "31";
        } else if (LNRPAIP.INST_MTH == '2') {
            LNCILCM.COMM_DATA.FORM_CODE = "34";
        }
        LNCILCM.COMM_DATA.FUNC_CODE = '1';
        LNCILCM.COMM_DATA.PRIN_AMT = WS_VARIABLES.PHS_REM_PRIN;
        LNCILCM.COMM_DATA.RATE = WS_VARIABLES.ALL_IN_RATE;
        LNCILCM.COMM_DATA.BASDAYS_STD = LNCAPRDM.REC_DATA.INT_DBAS_STD;
        LNCILCM.COMM_DATA.PERIOD = LNRPAIP.PERD;
        LNCILCM.COMM_DATA.PERIOD_UNIT = LNRPAIP.PERD_UNIT;
        LNCILCM.COMM_DATA.ROUND_MODE = '2';
        LNCILCM.COMM_DATA.CCY = LNCCONTM.REC_DATA.CCY;
        WS_VARIABLES.INST_TERM = (short) (WS_VARIABLES.PHS_TERM - WS_VARIABLES.SPE_TERM + 1);
        LNCILCM.COMM_DATA.TERM = WS_VARIABLES.INST_TERM;
        S000_CALL_LNZILCM();
        if (pgmRtn) return;
        LNRPAIP.CUR_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
    }
    public void R000_GET_MAX_PHS_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPAIP.KEY.SUB_CTA_NO = 0;
        else LNRPAIP.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.where = "CONTRACT_NO = :LNRPAIP.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPAIP.KEY.SUB_CTA_NO";
        LNTPAIP_RD.fst = true;
        LNTPAIP_RD.order = "PHS_NO DESC";
        IBS.READ(SCCGWA, LNRPAIP, this, LNTPAIP_RD);
    }
    public void S000_CALL_LNZTMPPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-R-TMPP-MAIN", LNCRTMPP);
        if (LNCRTMPP.RETURN_INFO == 'E') {
            WS_COND_FLG.PAIP_FLAG = 'Y';
        } else {
            if (LNCRTMPP.RC.RC_CODE != 0) {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRTMPP.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZRRCVD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRCVD", LNCRRCVD);
        if (LNCRRCVD.RETURN_INFO == 'E') {
            CEP.TRC(SCCGWA, "B");
            WS_COND_FLG.RCVD_FLAG = 'Y';
        } else {
            if (LNCRRCVD.RC.RC_CODE != 0) {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRRCVD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZRRCVD_2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRCVD", LNCRRCVD);
        if (LNCRRCVD.RETURN_INFO == 'N' 
            || LNCRRCVD.RETURN_INFO == 'E') {
            WS_COND_FLG.RCVD_READ = 'N';
        } else {
            if (LNCRRCVD.RC.RC_CODE != 0) {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRRCVD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_COND_FLG.RCVD_READ = 'Y';
            }
        }
    }
    public void S000_CALL_SRC_LNZRSCHT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSCHT", LNCRSCHT);
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICUT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSUBP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-SUBS-DUE-PROC", LNCSUBP);
        if (LNCSUBP.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSUBP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        if (SCCCLDT.RC != 0) {
            WS_VARIABLES.WS_MSGID.MSG_AP = "SC";
            WS_VARIABLES.WS_MSGID.MSG_CODE = SCCCLDT.RC;
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRRATN() throws IOException,SQLException,Exception {
        LNCRRATN.REC_PTR = LNRRATN;
        LNCRRATN.REC_LEN = 783;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRATN", LNCRRATN);
    }
    public void S000_CALL_LNZILCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INST-AMT-CMP", LNCILCM);
        if (LNCILCM.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCILCM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZENSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-ENTRY-SCH", LNCENSCH);
        if (LNCENSCH.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCENSCH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PLPI_DUE_DT_DSC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = WS_VARIABLES.REPY_MTH;
        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRPLPI.KEY.REPY_MTH";
        LNTPLPI_BR.rp.order = "DUE_DT DESC";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STARTBR_PLPI_DUE_DT_DSC2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = WS_VARIABLES.REPY_MTH;
        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH < > :LNRPLPI.KEY.REPY_MTH";
        LNTPLPI_BR.rp.order = "DUE_DT DESC";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STARTBR_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRPLPI.KEY.REPY_MTH "
            + "AND TERM >= :LNRPLPI.KEY.TERM";
        LNTPLPI_BR.rp.order = "DUE_DT";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STARTBR_LNTPYIF() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        LNTPYIF_RD.where = "CONTRACT_NO = :LNRPYIF.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPYIF.KEY.SUB_CTA_NO "
            + "AND TERM = :LNRPYIF.KEY.TERM";
        LNTPYIF_RD.fst = true;
        LNTPYIF_RD.order = "REPY_SEQ";
        IBS.READ(SCCGWA, LNRPYIF, this, LNTPYIF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.PYIF_FLAG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_COND_FLG.PYIF_FLAG = 'N';
            }
        }
    }
    public void T000_READNEXT_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T101_STARTBR_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSCHT);
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRSCHT.KEY.SUB_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_TMP;
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ";
        LNTSCHT_BR.rp.order = "TERM";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_TMP;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.SUB_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRSCHT.KEY.TYPE = WS_VARIABLES.PAY_TYP;
        LNRSCHT.KEY.TERM = WS_VARIABLES.REPAY_TERM;
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TYPE);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TERM);
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM";
        LNTSCHT_BR.rp.order = "DUE_DTE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_READ_LNTSCHT_BY_DUE_DTE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSCHT);
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_TMP;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.SUB_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRSCHT.KEY.TYPE = WS_VARIABLES.SCHT_TYPE;
        LNRSCHT.DUE_DTE = WS_VARIABLES.SCHT_DUE_DTE;
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND DUE_DTE = :LNRSCHT.DUE_DTE";
        IBS.READ(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
    }
    public void T000_GET_TOTAL_LNTSCHT_P_OR_I() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_TMP;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.SUB_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRSCHT.KEY.TYPE = WS_VARIABLES.PAY_TYP;
        LNRSCHT.KEY.TERM = WS_VARIABLES.REPAY_TERM;
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.set = "WS-TOTAL-NUM=COUNT(*)";
        LNTSCHT_RD.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM";
        IBS.GROUP(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
    }
    public void T102_READNEXT_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1'
            || SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, WS_VARIABLES.WS_MSGID);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T103_ENDBR_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, WS_VARIABLES.WS_MSGID);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T104_GET_TOTAL_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSCHT);
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRSCHT.KEY.SUB_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_TMP;
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.set = "WS-TOTAL-NUM=COUNT(*)";
        LNTSCHT_RD.where = "CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ";
        IBS.GROUP(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
    }
    public void T00_STARTBR_LNTRELA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRELA);
        LNRRELA.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRRELA.PROJ_NO = 0;
        LNRRELA.KEY.TYPE = 'S';
        LNTRELA_BR.rp = new DBParm();
        LNTRELA_BR.rp.TableName = "LNTRELA";
        LNTRELA_BR.rp.where = "CONTRACT_NO = :LNRRELA.KEY.CONTRACT_NO "
            + "AND TYPE = :LNRRELA.KEY.TYPE "
            + "AND PROJ_NO > :LNRRELA.PROJ_NO";
        LNTRELA_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRRELA, this, LNTRELA_BR);
    }
    public void T00_READNEXT_LNTRELA() throws IOException,SQLException,Exception {
        WS_COND_FLG.RELA_FLAG = ' ';
        IBS.READNEXT(SCCGWA, LNRRELA, this, LNTRELA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.RELA_FLAG = 'Y';
        } else {
            WS_COND_FLG.RELA_FLAG = 'N';
        }
    }
    public void T00_ENDBR_LNTRELA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRELA_BR);
    }
    public void T000_READ_LNTSUBS() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.SUBS_FLAG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_COND_FLG.SUBS_FLAG = 'N';
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_SUBS_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_STARTBR_PLPI_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH >= :LNRPLPI.KEY.REPY_MTH "
            + "AND TERM >= :LNRPLPI.KEY.TERM";
        LNTPLPI_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_READ_LNTPLPI_P() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRPLPI.KEY.REPY_MTH "
            + "AND TERM = :LNRPLPI.KEY.TERM";
        IBS.READ(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
    }
    public void T000_STARTBR_PLPI_BY_KEY_PROC1() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRPLPI.KEY.REPY_MTH "
            + "AND TERM >= :LNRPLPI.KEY.TERM";
        LNTPLPI_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T104_GET_TOTAL_LNTPLPI_P_OR_I() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        if (LNCSSCHE.DATA_AREA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCSSCHE.DATA_AREA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = WS_VARIABLES.PAY_TYP;
        LNRPLPI.KEY.TERM = WS_VARIABLES.REPAY_TERM;
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.set = "WS-TOTAL-NUM=COUNT(*)";
        LNTPLPI_RD.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRPLPI.KEY.REPY_MTH "
            + "AND TERM >= :LNRPLPI.KEY.TERM";
        IBS.GROUP(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
    }
    public void T000_READNEXT_PLPI() throws IOException,SQLException,Exception {
        WS_DB_VARS.DUE_DT = 0;
        WS_DB_VARS.DUE_P_AMT = 0;
        IBS.READNEXT(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'E';
        }
        LNCRPLPI.DUE_DT = WS_DB_VARS.DUE_DT;
        LNCRPLPI.DUE_P_AMT = WS_DB_VARS.DUE_P_AMT;
    }
    public void T000_READNEXT_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.SCHT_FLAG = 'Y';
        }
        CEP.TRC(SCCGWA, LNRSCHT.DUE_DTE);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TYPE);
    }
    public void T000_READNEXT_LNTSCHT_2() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.SCHT_FLAG = 'N';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_COND_FLG.SCHT_FLAG = 'Y';
            }
        }
    }
    public void T000_ENDBR_PLPI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, WS_VARIABLES.WS_MSGID);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_DELETE_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSCHT);
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_TMP;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.SUB_CTA_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.where = "CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO";
        IBS.DELETE(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
    }
    public void T000_READ_LNTSEAJ() throws IOException,SQLException,Exception {
        LNTSEAJ_RD = new DBParm();
        LNTSEAJ_RD.TableName = "LNTSEAJ";
        LNTSEAJ_RD.eqWhere = "CONTRACT_NO,YEAR,MONTH,STATUS";
        IBS.READ(SCCGWA, LNRSEAJ, LNTSEAJ_RD);
    }
    public void T00_READ_LNTINTA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRINTA);
        LNRINTA.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRINTA.KEY.REPY_TERM = WS_VARIABLES.CUR_TERM;
        LNRINTA.KEY.ADJ_SEQ = 0;
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        LNTINTA_RD.where = "CONTRACT_NO = :LNRINTA.KEY.CONTRACT_NO "
            + "AND REPY_TERM = :LNRINTA.KEY.REPY_TERM "
            + "AND ADJ_SEQ = :LNRINTA.KEY.ADJ_SEQ";
        IBS.READ(SCCGWA, LNRINTA, this, LNTINTA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.INTA_FLAG = 'Y';
        }
    }
    public void T000_READ_LNZRPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        IBS.init(SCCGWA, LNCRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCSSCHE.DATA_AREA.CONTRACT_NO;
        LNRPLPI.KEY.REPY_MTH = LNCSSCHE.DATA_AREA.PAY_TYP;
        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
        LNRPLPI.KEY.TERM = WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_TERM;
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND TERM = :LNRPLPI.KEY.TERM "
            + "AND REPY_MTH = :LNRPLPI.KEY.REPY_MTH";
        IBS.READ(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
    }
    public void S000_GEN_JRNNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
        SCCCALL.COMMAREA_PTR = null;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
