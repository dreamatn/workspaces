package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSCNTA {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    DBParm LNTRATN_RD;
    DBParm LNTLOAN_RD;
    DBParm LNTICTL_RD;
    DBParm LNTSETL_RD;
    DBParm LNTTRAN_RD;
    DBParm LNTAGRE_RD;
    DBParm LNTAPRD_RD;
    DBParm LNTACRU_RD;
    DBParm LNTPLPI_RD;
    brParm LNTCONT_BR = new brParm();
    brParm LNTAGRE_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String LN_CLDD = "CLDD";
    char CKPD_INQ = '0';
    LNZSCNTA_WS_OUT_RECODE WS_OUT_RECODE = new LNZSCNTA_WS_OUT_RECODE();
    LNZSCNTA_WS_PAGE_INFO WS_PAGE_INFO = new LNZSCNTA_WS_PAGE_INFO();
    LNZSCNTA_WS_VARIABLES WS_VARIABLES = new LNZSCNTA_WS_VARIABLES();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNRTRAN LNRTRAN = new LNRTRAN();
    CICCUST CICCUST = new CICCUST();
    CICQACR CICQACR = new CICQACR();
    LNCSLNQ LNCSLNQ = new LNCSLNQ();
    LNRCONT LNRCONT = new LNRCONT();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRICTL LNRICTL = new LNRICTL();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNRRATN LNRRATN = new LNRRATN();
    LNRDISC LNRDISC = new LNRDISC();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRSETL LNRSETL = new LNRSETL();
    LNRAGRE LNRAGRE = new LNRAGRE();
    CIRACR CIRACR = new CIRACR();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRGUAR LNCRGUAR = new LNCRGUAR();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCICUT LNCICUT = new LNCICUT();
    LNRACRU LNRACRU = new LNRACRU();
    LNRBALL LNRBALL = new LNRBALL();
    LNCRBALL LNCRBALL = new LNCRBALL();
    CICACCU CICACCU = new CICACCU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNCICSTS LNCICSTS = new LNCICSTS();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICQACAC CICQACAC = new CICQACAC();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    SCCGWA SCCGWA;
    LNCSCNTA LNCSCNTA;
    public void MP(SCCGWA SCCGWA, LNCSCNTA LNCSCNTA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSCNTA = LNCSCNTA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSCNTA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        IBS.init(SCCGWA, WS_PAGE_INFO);
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUT_RECODE);
        IBS.init(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD);
        SCCFMT.FMTID = "LNN01";
        LNCSCNTA.RC.RC_MMO = "LN";
        LNCSCNTA.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PAGE_INFO.NEXT_START_NUM);
        CEP.TRC(SCCGWA, "2018102245");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_GET_PAGE_INFO();
        if (pgmRtn) return;
        if (LNCSCNTA.Q_MTH == 'C') {
            B310_FUNC_QUERY_CINO();
            if (pgmRtn) return;
        } else if (LNCSCNTA.Q_MTH == 'L') {
            B320_FUNC_QUERY_LNNO();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSCNTA.Q_MTH + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B800_OUTPUT_DATA_BEGIN();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSCNTA.DATA.CI_NO);
        CEP.TRC(SCCGWA, LNCSCNTA.Q_MTH);
        if (LNCSCNTA.DATA.CI_NO.trim().length() == 0 
            && LNCSCNTA.Q_MTH == 'C') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSCNTA.DATA.CONT_NO);
        if (LNCSCNTA.DATA.CONT_NO.trim().length() == 0 
            && LNCSCNTA.Q_MTH == 'L') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_PAGE_INFO() throws IOException,SQLException,Exception {
        WS_PAGE_INFO.ROW_FLG = 'N';
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (LNCSCNTA.DATA.PAGE_NUM == 0) {
            WS_PAGE_INFO.CURR_PAGE = 1;
        } else {
            WS_PAGE_INFO.CURR_PAGE = (short) LNCSCNTA.DATA.PAGE_NUM;
        }
        WS_PAGE_INFO.LAST_PAGE = 'N';
        if (LNCSCNTA.DATA.PAGE_ROW == 0) {
            WS_PAGE_INFO.PAGE_ROW = 10;
        } else {
            if (LNCSCNTA.DATA.PAGE_ROW > 10) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAGE_ROW, LNCSCNTA.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_PAGE_INFO.PAGE_ROW = LNCSCNTA.DATA.PAGE_ROW;
            }
        }
        WS_PAGE_INFO.NEXT_START_NUM = ( ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW ) + 1;
    }
    public void B310_FUNC_QUERY_CINO() throws IOException,SQLException,Exception {
        if (LNCSCNTA.DATA.COMM_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = LNCSCNTA.DATA.COMM_NO;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(LNCSCNTA.DATA.CI_NO)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_CONT_NO_NOT_CITACR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, LNRCONT);
            LNRCONT.FATHER_CONTRACT = LNCSCNTA.DATA.COMM_NO;
            LNRCONT.CONTRACT_TYPE = LNCSCNTA.DATA.CTA_TYP;
            if (LNCSCNTA.DATA.CTA_TYP.equalsIgnoreCase(" ")) {
                T000_STARTBR_CONT();
                if (pgmRtn) return;
            } else if (LNCSCNTA.DATA.CTA_TYP.equalsIgnoreCase("C1C2")) {
                T000_STARTBR_CONT_C1C2();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_CONT_TYPE();
                if (pgmRtn) return;
            }
            WS_VARIABLES.CONT_FLAG = 'Y';
            T000_READNEXT_CONT();
            if (pgmRtn) return;
            while (WS_VARIABLES.CONT_FLAG != 'N') {
                WS_VARIABLES.CTA_TYP = LNRCONT.CONTRACT_TYPE;
                WS_VARIABLES.CONT_NO = LNRCONT.KEY.CONTRACT_NO;
                B300_QUERY_PROCESS();
                if (pgmRtn) return;
                T000_READNEXT_CONT();
                if (pgmRtn) return;
            }
            T000_ENDBR_CONT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CICQCIAC);
            CICQCIAC.FUNC = '2';
            CICQCIAC.DATA.CI_NO = LNCSCNTA.DATA.CI_NO;
            CICQCIAC.DATA.FRM_APP = "LN";
            CICQCIAC.DATA.CANCEL_INQ_FLG = 'Y';
            S000_CALL_CIZQCIAC();
            if (pgmRtn) return;
            for (CICQACR.CNT = 1; CICQCIAC.DATA.ACR_AREA.ENTY_INF[CICQACR.CNT-1].ENTY_NO.trim().length() != 0 
                && CICQACR.CNT < 100; CICQACR.CNT += 1) {
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[CICQACR.CNT-1].ENTY_NO);
                T000_01_GET_CONT_NO();
                if (pgmRtn) return;
            }
        }
    }
    public void B320_FUNC_QUERY_LNNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSCNTA.DATA.CONT_NO;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG);
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG == 'F') {
            WS_VARIABLES.CONT_NO = LNRCONT.KEY.CONTRACT_NO;
            WS_VARIABLES.CTA_TYP = LNRCONT.CONTRACT_TYPE;
            B300_QUERY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B300_QUERY_PROCESS() throws IOException,SQLException,Exception {
        if (LNCSCNTA.DATA.CTA_TYP.trim().length() == 0) {
            if (WS_VARIABLES.CTA_TYP.equalsIgnoreCase(LN_CLDD)) {
                R000_LOAN_REC_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (LNCSCNTA.DATA.CTA_TYP.equalsIgnoreCase(WS_VARIABLES.CTA_TYP)) {
                if (WS_VARIABLES.CTA_TYP.equalsIgnoreCase(LN_CLDD)) {
                    R000_LOAN_REC_PROC();
                    if (pgmRtn) return;
                }
            }
            if (LNCSCNTA.DATA.CTA_TYP.equalsIgnoreCase("C1C2")) {
                CEP.TRC(SCCGWA, "CLDD + CLDL");
                CEP.TRC(SCCGWA, WS_VARIABLES.CONT_NO);
                CEP.TRC(SCCGWA, WS_VARIABLES.CTA_TYP);
                if (WS_VARIABLES.CTA_TYP.equalsIgnoreCase(LN_CLDD)) {
                    R000_LOAN_REC_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_LOAN_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.CONT_NO);
        IBS.init(SCCGWA, LNRLOAN);
        LNRLOAN.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        T000_READ_LNTLOAN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTLOAN_FLG);
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTLOAN_FLG == 'F') {
            CEP.TRC(SCCGWA, "LOAN Y");
            R000_OUT_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void R000_OUT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSCNTA.DATA.STS);
        CEP.TRC(SCCGWA, WS_VARIABLES.CONT_NO);
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_CONT_NO = WS_VARIABLES.CONT_NO;
        WS_VARIABLES.WS_D_DETAIL_DD.D_CTA_TYP = WS_VARIABLES.CTA_TYP;
        WS_VARIABLES.WS_D_DETAIL_DD.D_START_DT = LNRCONT.START_DATE;
        WS_VARIABLES.WS_D_DETAIL_DD.D_MATU_DT = LNRCONT.MAT_DATE;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PROD_CD = LNRCONT.PROD_CD;
        WS_VARIABLES.WS_D_DETAIL_DD.D_CCY = LNRCONT.CCY;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PRIN_AMT = LNRCONT.LN_TOT_AMT;
        WS_VARIABLES.WS_D_DETAIL_DD.D_ALL_RATE = LNRICTL.CUR_RAT;
        WS_VARIABLES.WS_D_DETAIL_DD.O_RATE = LNRICTL.CUR_PO_RAT;
        WS_VARIABLES.WS_D_DETAIL_DD.D_CAL_DT = LNRICTL.IC_CAL_DUE_DT;
        WS_VARIABLES.WS_D_DETAIL_DD.D_BR = LNRCONT.BOOK_BR;
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = CKPD_INQ;
        LNCSCKPD.PROD_CD = LNRCONT.PROD_CD;
        CEP.TRC(SCCGWA, LNRCONT.PROD_CD);
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_CI_CNAME = LNCSCKPD.PROD_NM;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_CI_CNAME);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_VARIABLES.WS_D_DETAIL_DD.D_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_BR_NM = BPCPQORG.CHN_NM;
        WS_VARIABLES.WS_D_DETAIL_DD.D_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        LNRSETL.KEY.CI_TYPE = 'B';
        LNRSETL.KEY.CCY = LNRCONT.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '1';
        T000_READ_LNTSETL();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_AC = LNRSETL.AC;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_AC);
        WS_VARIABLES.WS_D_DETAIL_DD.D_CCY_TYP = LNRSETL.CCY_TYP;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_CCY_TYP);
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTTRAN();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_JRNNO = LNRTRAN.KEY.TR_JRN_NO;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_JRNNO);
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        LNRSETL.KEY.CI_TYPE = 'B';
        LNRSETL.KEY.CCY = LNRCONT.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '2';
        T000_READ_LNTSETL();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PAY_AC = LNRSETL.AC;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_PAY_AC);
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_DRAW_NO = LNRAGRE.DRAW_NO;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PA_NO = LNRAGRE.PAPER_NO;
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PAY_MTH = LNRAPRD.PAY_MTH;
        IBS.init(SCCGWA, LNRBALL);
        IBS.init(SCCGWA, LNCRBALL);
        LNCRBALL.FUNC = 'I';
        LNRBALL.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        S000_CALL_LNZRBALL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[01-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[02-1].LOAN_BAL);
        if (LNCRBALL.RC.RC_CODE == 0) {
            WS_VARIABLES.WS_D_DETAIL_DD.D_L_AMT = LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[52-1].LOAN_BAL;
            WS_VARIABLES.I1_AMT = LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[20-1].LOAN_BAL;
            WS_VARIABLES.I2_AMT = LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[22-1].LOAN_BAL;
            WS_VARIABLES.I3_AMT = LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[42-1].LOAN_BAL;
            WS_VARIABLES.WS_D_DETAIL_DD.D_O_AMT = WS_VARIABLES.I1_AMT + WS_VARIABLES.I2_AMT + WS_VARIABLES.I3_AMT;
        }
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.TERM = LNRICTL.IC_CAL_TERM;
        if (LNRAPRD.PAY_MTH == '4') {
            LNRPLPI.KEY.REPY_MTH = 'C';
        } else {
            LNRPLPI.KEY.REPY_MTH = 'I';
        }
        CEP.TRC(SCCGWA, LNRPLPI.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
        T000_READ_LNTPLPI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNRICTL.KEY.CONTRACT_NO;
        LNCICUT.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCICUT.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCICUT.COMM_DATA.SUF_NO = "0" + LNCICUT.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.TYPE = 'I';
        LNCICUT.COMM_DATA.BEG_DATE = LNRPLPI.VAL_DT;
        LNCICUT.COMM_DATA.END_DATE = LNRPLPI.DUE_DT;
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_I_AMT);
        CEP.TRC(SCCGWA, "201810221111");
        if (LNCICUT.COMM_DATA.BEG_DATE != LNCICUT.COMM_DATA.END_DATE) {
            CEP.TRC(SCCGWA, "201810222222");
            S000_CALL_LNZICUT();
            if (pgmRtn) return;
            if (LNCICUT.RC.RC_RTNCODE == 0) {
                WS_VARIABLES.WS_D_DETAIL_DD.D_I_AMT = LNCICUT.COMM_DATA.INT_AMT;
            }
        }
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQNORP";
        LNCCNEX.COMM_DATA.LN_AC = WS_VARIABLES.CONT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        if (LNCCNEX.RC.RC_RTNCODE == 0) {
            WS_VARIABLES.N_BAL = LNCCNEX.COMM_DATA.INQ_AMT;
            CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.INQ_AMT);
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.N_BAL);
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQOVDP";
        LNCCNEX.COMM_DATA.LN_AC = WS_VARIABLES.CONT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        if (LNCCNEX.RC.RC_RTNCODE == 0) {
            WS_VARIABLES.O_BAL = LNCCNEX.COMM_DATA.INQ_AMT;
            CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.INQ_AMT);
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.O_BAL);
        WS_VARIABLES.TOT_BAL = WS_VARIABLES.N_BAL + WS_VARIABLES.O_BAL;
        CEP.TRC(SCCGWA, WS_VARIABLES.TOT_BAL);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.WS_D_DETAIL_DD.D_BAL = WS_VARIABLES.TOT_BAL;
        } else {
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                WS_VARIABLES.WS_D_DETAIL_DD.D_BAL = 0;
            } else {
                WS_VARIABLES.WS_D_DETAIL_DD.D_BAL = LNRCONT.LN_TOT_AMT;
            }
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = WS_VARIABLES.CONT_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.RC.RC_CODE == 0) {
            WS_VARIABLES.WS_D_DETAIL_DD.D_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        }
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_DRAW_NO = LNRAGRE.DRAW_NO;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PA_NO = LNRAGRE.PAPER_NO;
        WS_VARIABLES.WS_D_DETAIL_DD.D_T_P_FLG = 'N';
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(42 - 1, 42 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.WS_D_DETAIL_DD.D_T_P_FLG = 'Y';
        }
        IBS.init(SCCGWA, LNCICSTS);
        LNCICSTS.COMM_DATA.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        S000_CALL_LNZICSTS();
        if (pgmRtn) return;
        if (BPCPQORG.RC.RC_CODE == 0) {
            WS_VARIABLES.WS_D_DETAIL_DD.D_STATUS = LNCICSTS.COMM_DATA.STS;
        }
        if (LNCSCNTA.DATA.STS == ' ') {
            R000_OUTPUT_DATA();
            if (pgmRtn) return;
        } else {
            if (LNCSCNTA.DATA.STS == WS_VARIABLES.WS_D_DETAIL_DD.D_STATUS) {
                R000_OUTPUT_DATA();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_OUT_GUAR_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.CONT_NO);
        WS_VARIABLES.WS_D_DETAIL_DD.D_CONT_NO = WS_VARIABLES.CONT_NO;
        WS_VARIABLES.WS_D_DETAIL_DD.D_CTA_TYP = WS_VARIABLES.CTA_TYP;
        WS_VARIABLES.WS_D_DETAIL_DD.D_START_DT = LNRCONT.START_DATE;
        WS_VARIABLES.WS_D_DETAIL_DD.D_MATU_DT = LNRCONT.MAT_DATE;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PROD_CD = LNRCONT.PROD_CD;
        WS_VARIABLES.WS_D_DETAIL_DD.D_CCY = LNRCONT.CCY;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PRIN_AMT = LNRCONT.LN_TOT_AMT;
        WS_VARIABLES.WS_D_DETAIL_DD.D_BR = LNRCONT.BOOK_BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_VARIABLES.WS_D_DETAIL_DD.D_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_BR_NM = BPCPQORG.CHN_NM;
        WS_VARIABLES.WS_D_DETAIL_DD.D_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        LNRSETL.KEY.CI_TYPE = 'B';
        LNRSETL.KEY.CCY = LNRCONT.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '1';
        T000_READ_LNTSETL();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_AC = LNRSETL.AC;
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        LNRSETL.KEY.CI_TYPE = 'B';
        LNRSETL.KEY.CCY = LNRCONT.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '2';
        T000_READ_LNTSETL();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PAY_AC = LNRSETL.AC;
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_DRAW_NO = LNRAGRE.DRAW_NO;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PA_NO = LNRAGRE.PAPER_NO;
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PAY_MTH = LNRAPRD.PAY_MTH;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = WS_VARIABLES.CONT_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        WS_VARIABLES.WS_D_DETAIL_DD.D_CI_CNAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = WS_VARIABLES.CONT_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_DRAW_NO = LNRAGRE.DRAW_NO;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PA_NO = LNRAGRE.PAPER_NO;
        CEP.TRC(SCCGWA, LNCSCNTA.DATA.STS);
        if (LNCSCNTA.DATA.STS == ' ') {
            R000_OUTPUT_DATA();
            if (pgmRtn) return;
        } else {
            if (LNCSCNTA.DATA.STS == WS_VARIABLES.WS_D_DETAIL_DD.D_STATUS) {
                R000_OUTPUT_DATA();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_STATUS);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_CONT_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_CTA_TYP);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_START_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_MATU_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_PROD_CD);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_CCY);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_PRIN_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_ALL_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.O_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_BAL);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_PA_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_DRAW_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_I_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_O_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_L_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_AC);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_PAY_MTH);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_CAL_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_PAY_AC);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_BR);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_BR_NM);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_JRNNO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_T_P_FLG);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD);
        WS_PAGE_INFO.TOTAL_NUM += 1;
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.NEXT_START_NUM);
        CEP.TRC(SCCGWA, "2018102287");
        if (WS_PAGE_INFO.PAGE_IDX < WS_PAGE_INFO.PAGE_ROW 
            && WS_PAGE_INFO.TOTAL_NUM >= WS_PAGE_INFO.NEXT_START_NUM) {
            WS_PAGE_INFO.ROW_FLG = 'Y';
            WS_PAGE_INFO.PAGE_IDX += 1;
            WS_PAGE_INFO.NEXT_START_NUM += 1;
            R000_WRITE_QUEUE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void R000_WRITE_QUEUE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1]);
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_STATUS_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_STATUS;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_CTA_TYP_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_CTA_TYP;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_CONT_NO_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_CONT_NO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_PRIN_AMT_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_PRIN_AMT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_BAL_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_BAL;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_START_DT_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_START_DT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_MATU_DT_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_MATU_DT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_PROD_CD_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_PROD_CD;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_CCY_TYP_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_CCY_TYP;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_CCY_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_CCY;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_ALL_RATE_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_ALL_RATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].O_RATE_OUT = WS_VARIABLES.WS_D_DETAIL_DD.O_RATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_CI_NO_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_CI_NO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_CI_CNAME_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_CI_CNAME;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_PA_NO_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_PA_NO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_DRAW_NO_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_DRAW_NO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_I_AMT_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_I_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_I_AMT);
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_O_AMT_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_O_AMT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_L_AMT_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_L_AMT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_AC_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_AC;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_PAY_MTH_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_PAY_MTH;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_CAL_DT_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_CAL_DT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_PAY_AC_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_PAY_AC;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_BR_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_BR;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_BR_NM_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_BR_NM;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_JRNNO_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_JRNNO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_T_P_FLG_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_T_P_FLG;
        IBS.init(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD);
    }
    public void B800_OUTPUT_DATA_BEGIN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.BAL_CNT);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.ROW_FLG);
        if (WS_PAGE_INFO.ROW_FLG == 'Y') {
            WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.TOTAL_NUM % WS_PAGE_INFO.PAGE_ROW;
            WS_PAGE_INFO.TOTAL_PAGE = (short) ((WS_PAGE_INFO.TOTAL_NUM - WS_PAGE_INFO.BAL_CNT) / WS_PAGE_INFO.PAGE_ROW);
            if (WS_PAGE_INFO.BAL_CNT != 0) {
                WS_PAGE_INFO.TOTAL_PAGE += 1;
            }
            if (WS_PAGE_INFO.TOTAL_PAGE <= WS_PAGE_INFO.CURR_PAGE) {
                WS_PAGE_INFO.TOTAL_PAGE = WS_PAGE_INFO.CURR_PAGE;
                WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.PAGE_IDX;
                WS_PAGE_INFO.TOTAL_NUM = ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW + WS_PAGE_INFO.BAL_CNT;
                WS_PAGE_INFO.LAST_PAGE = 'Y';
                if (WS_PAGE_INFO.BAL_CNT != 0) {
                    WS_PAGE_INFO.PAGE_ROW = (short) WS_PAGE_INFO.BAL_CNT;
                }
            }
        } else {
            WS_PAGE_INFO.TOTAL_PAGE = 1;
            WS_PAGE_INFO.TOTAL_NUM = 0;
            WS_PAGE_INFO.LAST_PAGE = 'Y';
            WS_PAGE_INFO.PAGE_ROW = 0;
        }
        if (WS_PAGE_INFO.TOTAL_NUM == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_PAGE_INFO.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_PAGE_INFO.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_PAGE_INFO.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_PAGE_INFO.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_PAGE_INFO.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        SCCFMT.FMTID = "LN801";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 6569;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTRATN() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        IBS.READ(SCCGWA, LNRRATN, LNTRATN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTRATN_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTRATN_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTRATN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTLOAN() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.READ(SCCGWA, LNRLOAN, LNTLOAN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTLOAN_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTLOAN_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTLOAN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTICTL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = :LNRSETL.KEY.CI_TYPE "
            + "AND CCY = :LNRSETL.KEY.CCY "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE";
        LNTSETL_RD.fst = true;
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTSETL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTSETL_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTSETL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTTRAN() throws IOException,SQLException,Exception {
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.where = "CONTRACT_NO = :LNRTRAN.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO";
        LNTTRAN_RD.fst = true;
        LNTTRAN_RD.order = "TS DESC";
        IBS.READ(SCCGWA, LNRTRAN, this, LNTTRAN_RD);
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTAGRE_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTAGRE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTAGRE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTAPRD_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTAPRD_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTAPRD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTACRU() throws IOException,SQLException,Exception {
        LNTACRU_RD = new DBParm();
        LNTACRU_RD.TableName = "LNTACRU";
        IBS.READ(SCCGWA, LNRACRU, LNTACRU_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTACRU_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTACRU_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTACRU";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTPLPI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_CONT() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.where = "FATHER_CONTRACT = :LNRCONT.FATHER_CONTRACT";
        IBS.STARTBR(SCCGWA, LNRCONT, this, LNTCONT_BR);
    }
    public void T000_STARTBR_CONT_C1C2() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.where = "FATHER_CONTRACT = :LNRCONT.FATHER_CONTRACT "
            + "AND ( CONTRACT_TYPE = 'CLDD' "
            + "OR CONTRACT_TYPE = 'CLDL' )";
        IBS.STARTBR(SCCGWA, LNRCONT, this, LNTCONT_BR);
    }
    public void T000_STARTBR_CONT_TYPE() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.where = "FATHER_CONTRACT = :LNRCONT.FATHER_CONTRACT "
            + "AND CONTRACT_TYPE = :LNRCONT.CONTRACT_TYPE";
        IBS.STARTBR(SCCGWA, LNRCONT, this, LNTCONT_BR);
    }
    public void T000_READNEXT_CONT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRCONT, this, LNTCONT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.CONT_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.CONT_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_CONT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTCONT_BR);
    }
    public void S000_CALL_LNZSLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-LOAN-INQUIRY", LNCSLNQ);
        if (LNCSLNQ.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSLNQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_01_GET_CONT_NO() throws IOException,SQLException,Exception {
        WS_VARIABLES.FND_FLG = 'N';
        T000_01_STARTBR_LNTAGRE();
        if (pgmRtn) return;
        T000_01_READNEXT_LNTAGRE();
        if (pgmRtn) return;
        if (WS_VARIABLES.FND_FLG == 'Y') {
            while (WS_VARIABLES.FND_FLG != 'N') {
                T000_01_GET_NO();
                if (pgmRtn) return;
                T000_01_READNEXT_LNTAGRE();
                if (pgmRtn) return;
            }
        }
        T000_01_ENDBR_LNTAGRE();
        if (pgmRtn) return;
    }
    public void T000_01_GET_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[CICQACR.CNT-1].ENTY_NO;
        CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG == 'F') {
            WS_VARIABLES.CONT_NO = LNRCONT.KEY.CONTRACT_NO;
            WS_VARIABLES.CTA_TYP = LNRCONT.CONTRACT_TYPE;
            B300_QUERY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void T000_01_STARTBR_LNTAGRE() throws IOException,SQLException,Exception {
        LNRAGRE.KEY.CONTRACT_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[CICQACR.CNT-1].ENTY_NO;
        LNTAGRE_BR.rp = new DBParm();
        LNTAGRE_BR.rp.TableName = "LNTAGRE";
        LNTAGRE_BR.rp.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRAGRE, this, LNTAGRE_BR);
    }
    public void T000_01_READNEXT_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, LNRAGRE, this, LNTAGRE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.FND_FLG = 'N';
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_01_ENDBR_LNTAGRE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTAGRE_BR);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "CI-INQ-ACAC";
        SCCCALL.COMMAREA_PTR = CICQACAC;
        SCCCALL.NOFMT_FLG = 'Y';
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0 
            && CICQACAC.RC.RC_CODE != CI8051) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "CI-INQ-CUST-ACR";
        SCCCALL.COMMAREA_PTR = CICQCIAC;
        SCCCALL.NOFMT_FLG = 'Y';
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        if (CICQCIAC.RC.RC_CODE != 0 
            && CICQCIAC.RC.RC_CODE != 8051) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
    }
    public void S000_CALL_LNZRBALL() throws IOException,SQLException,Exception {
        LNCRBALL.REC_PTR = LNRBALL;
        LNCRBALL.REC_LEN = 120058;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTBALL", LNCRBALL);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
    }
    public void S000_CALL_LNZICSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-GET-ICTL-STS", LNCICSTS);
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSCNTA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCSCNTA=");
            CEP.TRC(SCCGWA, LNCSCNTA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
