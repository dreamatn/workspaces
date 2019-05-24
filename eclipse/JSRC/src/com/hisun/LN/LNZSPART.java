package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DC.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPART {
    BigDecimal bigD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTPARS_RD;
    DBParm LNTSETL_RD;
    brParm LNTPARS_BR = new brParm();
    brParm LNTSETL_BR = new brParm();
    boolean pgmRtn = false;
    String K_TX_RMK_ADD = "ADD PART BANK INF";
    String K_TX_RMK_DEL = "DELETE PART BANK INF";
    String K_TX_RMK_MOD = "MODIFY PART BANK INF";
    short WS_I = 0;
    short WS_J = 0;
    String WS_AC_TYP = " ";
    double WS_SUM_PCT = 0;
    String WS_CCY = " ";
    LNZSPART_WS_IA_AC WS_IA_AC = new LNZSPART_WS_IA_AC();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    DCCPACTY DCCPACTY = new DCCPACTY();
    LNCSPART LNCHOLD = new LNCSPART();
    LNCSPART LNCHNEW = new LNCSPART();
    LNRPARS LNRPARS = new LNRPARS();
    LNRSETL LNRSETL = new LNRSETL();
    SCCGWA SCCGWA;
    LNCSPART LNCSPART;
    public void MP(SCCGWA SCCGWA, LNCSPART LNCSPART) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPART = LNCSPART;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPART return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCHOLD);
        IBS.init(SCCGWA, BPCPNHIS);
        LNCSPART.RC.RC_MMO = "LN";
        LNCSPART.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (LNCSPART.FUNC == 'A') {
            B200_ADD_PART_INF();
            if (pgmRtn) return;
            B210_CHECK_ADD_DATA();
            if (pgmRtn) return;
        } else if (LNCSPART.FUNC == 'D') {
            B300_DELETE_PART_INF();
            if (pgmRtn) return;
        } else if (LNCSPART.FUNC == 'M') {
            B400_MODIFY_PART_INF();
            if (pgmRtn) return;
        } else if (LNCSPART.FUNC == 'I') {
            B500_QUERY_PART_INF();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = " INVALID FUNC(" + LNCSPART.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (LNCSPART.FUNC != 'I' 
            && LNCSPART.DATA.LN_CTANO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PAPER_NO_M_INPUT, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSPART.FUNC == 'I' 
            && LNCSPART.DATA.LN_CTANO.trim().length() == 0 
            && LNCSPART.DATA.LN_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_CHECK_ADD_DATA() throws IOException,SQLException,Exception {
        if (LNCSPART.DATA.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CCY_M_INPUT, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = LNCSPART.DATA.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        if (LNCSPART.DATA.AMT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PAPER_AMT_M_INPUT, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSPART.DATA.AMT <= 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_M_GREATER_THAN_Z, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSPART.DATA.MAIN_BR == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_MAIN_BR_M_INPUT, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSPART.DATA.SYN_TYP == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SYN_TYP_M_INPUT, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSPART.DATA.SYN_TYP != 'I' 
            && LNCSPART.DATA.SYN_TYP != 'O') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SYN_TYP_VAL_ERR, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRPARS);
        LNRPARS.KEY.CONTRACT_NO = LNCSPART.DATA.LN_CTANO;
    }
    public void B200_ADD_PART_INF() throws IOException,SQLException,Exception {
        B000_CHECK_PART_INF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "ADD-PARS-INF");
        S000_ADD_PARS_INF();
        if (pgmRtn) return;
        if (LNCSPART.DATA.SYN_TYP == 'O') {
            CEP.TRC(SCCGWA, "ADD-SETL-INF");
            S000_ADD_SETL_INF();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "MAKE-HIS-NEW");
        S000_MAKE_HIS_NEW();
        if (pgmRtn) return;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = K_TX_RMK_ADD;
        S000_WRITE_TRAN_HIS();
        if (pgmRtn) return;
    }
    public void B300_DELETE_PART_INF() throws IOException,SQLException,Exception {
        S000_MODIFY_PARS_STS();
        if (pgmRtn) return;
        if (LNCSPART.DATA.SYN_TYP == 'O') {
            S000_DELETE_SETL_INF();
            if (pgmRtn) return;
        }
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.TX_RMK = K_TX_RMK_DEL;
        S000_WRITE_TRAN_HIS();
        if (pgmRtn) return;
    }
    public void B400_MODIFY_PART_INF() throws IOException,SQLException,Exception {
        B000_CHECK_PART_INF();
        if (pgmRtn) return;
        S000_DELETE_SETL_INF();
        if (pgmRtn) return;
        S000_DELETE_PARS_INF();
        if (pgmRtn) return;
        S000_ADD_PARS_INF();
        if (pgmRtn) return;
        if (LNCSPART.DATA.SYN_TYP == 'O') {
            S000_ADD_SETL_INF();
            if (pgmRtn) return;
        }
        S000_MAKE_HIS_NEW();
        if (pgmRtn) return;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.TX_RMK = K_TX_RMK_MOD;
        S000_WRITE_TRAN_HIS();
        if (pgmRtn) return;
    }
    public void B500_QUERY_PART_INF() throws IOException,SQLException,Exception {
        S000_QUERY_PARS_INF();
        if (pgmRtn) return;
        if (LNRPARS.INNER_OUT_FLG == 'O') {
            S000_QUERY_SETL_INF();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK_PART_INF() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            WS_SUM_PCT += LNCSPART.DATA.PART_INF[WS_I-1].PART_PCT;
        }
        if (WS_SUM_PCT != 100) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PART_PCT_SUM_ERR, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_SUM_PCT = 0;
        for (WS_I = 1; WS_SUM_PCT != 100; WS_I += 1) {
            if (LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_LOC_BANK_M_INPUT, LNCSPART.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK != 'Y' 
                && LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK != 'N') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_LOC_BANK_VAL_ERR, LNCSPART.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSPART.DATA.SYN_TYP == 'I' 
                && LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK != 'Y') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SYNI_M_LOC_BANK, LNCSPART.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSPART.DATA.SYN_TYP == 'O') {
                if (LNCSPART.DATA.PART_INF[WS_I-1].PART_NO == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PART_NO_M_INPUT, LNCSPART.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK == 'N') {
                    if (LNCSPART.DATA.PART_INF[WS_I-1].AC_TYP.trim().length() == 0) {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ACT_TYP_M_I, LNCSPART.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, LNCSPART.DATA.PART_INF[WS_I-1].AC_TYP);
                    if (!LNCSPART.DATA.PART_INF[WS_I-1].AC_TYP.equalsIgnoreCase("01") 
                        && !LNCSPART.DATA.PART_INF[WS_I-1].AC_TYP.equalsIgnoreCase("02") 
                        && !LNCSPART.DATA.PART_INF[WS_I-1].AC_TYP.equalsIgnoreCase("03")) {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_TYPE, LNCSPART.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                    if (LNCSPART.DATA.PART_INF[WS_I-1].PART_AC.trim().length() == 0) {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_MUST_INPUT, LNCSPART.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
            if (LNCSPART.DATA.PART_INF[WS_I-1].PART_NM.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PART_NAME_M_INPUT, LNCSPART.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSPART.DATA.PART_INF[WS_I-1].REL_TYPE == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REL_TYPE_M_INPUT, LNCSPART.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSPART.DATA.PART_INF[WS_I-1].REL_TYPE != 'P' 
                && LNCSPART.DATA.PART_INF[WS_I-1].REL_TYPE != 'M') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REL_TYPE_VAL_ERR, LNCSPART.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSPART.DATA.PART_INF[WS_I-1].REL_TYPE == 'M' 
                && LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK != 'Y') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_MUST_LOC_BANK, LNCSPART.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK == 'Y') {
                if (LNCSPART.DATA.PART_INF[WS_I-1].PART_BR == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PART_BR_M_INPUT, LNCSPART.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = LNCSPART.DATA.PART_INF[WS_I-1].PART_BR;
                    CEP.TRC(SCCGWA, BPCPQORG.BR);
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCPQORG.RC);
                }
                if (LNCSPART.DATA.PART_INF[WS_I-1].REL_TYPE == 'M' 
                    && LNCSPART.DATA.PART_INF[WS_I-1].PART_BR != LNCSPART.DATA.MAIN_BR) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PARTBR_NOTEQ_MAINBR, LNCSPART.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            if (LNCSPART.DATA.PART_INF[WS_I-1].ADJ_BANK == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PART_PCT_M_INPUT, LNCSPART.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_J = 0;
            if (LNCSPART.DATA.PART_INF[WS_I-1].ADJ_BANK == 'Y') {
                WS_J += 1;
            }
            if (WS_J > 1) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ADJ_BANK, LNCSPART.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSPART.DATA.PART_INF[WS_I-1].PART_PCT == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PART_PCT_M_INPUT, LNCSPART.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_SUM_PCT += LNCSPART.DATA.PART_INF[WS_I-1].PART_PCT;
            CEP.TRC(SCCGWA, "PCT");
            CEP.TRC(SCCGWA, WS_SUM_PCT);
        }
        CEP.TRC(SCCGWA, "GWA-TR-BRANCH");
    }
    public void S000_ADD_PARS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPARS);
        LNRPARS.KEY.CONTRACT_NO = LNCSPART.DATA.LN_CTANO;
        LNRPARS.INNER_OUT_FLG = LNCSPART.DATA.SYN_TYP;
        LNRPARS.STATUS = 'A';
        LNRPARS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPARS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRPARS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPARS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        for (WS_I = 1; WS_I <= 10 
            && LNCSPART.DATA.PART_INF[WS_I-1].PART_NO != 0; WS_I += 1) {
            LNRPARS.KEY.SEQ_NO = LNCSPART.DATA.PART_INF[WS_I-1].PART_NO;
            LNRPARS.BOOK_BR = LNCSPART.DATA.PART_INF[WS_I-1].PART_BR;
            LNRPARS.PARTI_NAME = LNCSPART.DATA.PART_INF[WS_I-1].PART_NM;
            LNRPARS.LOCAL_BANK_FLAG = LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK;
            LNRPARS.ADJ_BANK_FLAG = LNCSPART.DATA.PART_INF[WS_I-1].ADJ_BANK;
            LNRPARS.REL_TYPE = LNCSPART.DATA.PART_INF[WS_I-1].REL_TYPE;
            LNRPARS.PARTI_PCT = LNCSPART.DATA.PART_INF[WS_I-1].PART_PCT;
            LNRPARS.PARTI_AMT = LNCSPART.DATA.AMT * LNCSPART.DATA.PART_INF[WS_I-1].PART_PCT / 100;
            bigD = new BigDecimal(LNRPARS.PARTI_AMT);
            LNRPARS.PARTI_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            CEP.TRC(SCCGWA, LNCSPART.DATA.AMT);
            CEP.TRC(SCCGWA, LNCSPART.DATA.PART_INF[WS_I-1].PART_PCT);
            CEP.TRC(SCCGWA, LNRPARS.PARTI_AMT);
            LNRPARS.AVAIL_AMT = LNCSPART.DATA.AMT * LNCSPART.DATA.PART_INF[WS_I-1].PART_PCT / 100;
            bigD = new BigDecimal(LNRPARS.AVAIL_AMT);
            LNRPARS.AVAIL_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            T000_WRITE_LNTPARS();
            if (pgmRtn) return;
        }
    }
    public void S000_ADD_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNCSPART.DATA.LN_CTANO;
        LNRSETL.KEY.CI_TYPE = 'P';
        LNRSETL.KEY.CCY = LNCSPART.DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = 'C';
        LNRSETL.KEY.SEQ_NO = 0;
        LNRSETL.MWHD_AC_FLG = 'N';
        LNRSETL.AC_FLG = '0';
        LNRSETL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRSETL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        for (WS_I = 1; WS_I <= 10 
            && LNCSPART.DATA.PART_INF[WS_I-1].PART_NO != 0; WS_I += 1) {
            if (LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK == 'N') {
                LNRSETL.KEY.PART_BK = "" + LNCSPART.DATA.PART_INF[WS_I-1].PART_NO;
                JIBS_tmp_int = LNRSETL.KEY.PART_BK.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) LNRSETL.KEY.PART_BK = "0" + LNRSETL.KEY.PART_BK;
                CEP.TRC(SCCGWA, LNRSETL.KEY.PART_BK);
                LNRSETL.AC_TYP = LNCSPART.DATA.PART_INF[WS_I-1].AC_TYP;
                LNRSETL.AC = LNCSPART.DATA.PART_INF[WS_I-1].PART_AC;
                T000_WRITE_LNTSETL();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_MODIFY_PARS_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPARS);
        LNRPARS.KEY.CONTRACT_NO = LNCSPART.DATA.LN_CTANO;
        LNCHOLD.DATA.LN_CTANO = LNCSPART.DATA.LN_CTANO;
        T000_STARTBR_UPD_LNTPARS();
        if (pgmRtn) return;
        T000_READNEXT_LNTPARS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PARS_REC_NOTFND, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCHOLD.DATA.SYN_TYP = LNRPARS.INNER_OUT_FLG;
        for (WS_I = 1; WS_I <= 10 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_I += 1) {
            LNCHOLD.DATA.PART_INF[WS_I-1].LOC_BANK = LNRPARS.LOCAL_BANK_FLAG;
            LNCHOLD.DATA.PART_INF[WS_I-1].PART_NO = LNRPARS.KEY.SEQ_NO;
            LNCHOLD.DATA.PART_INF[WS_I-1].PART_BR = LNRPARS.BOOK_BR;
            LNCHOLD.DATA.PART_INF[WS_I-1].PART_NM = LNRPARS.PARTI_NAME;
            LNCHOLD.DATA.PART_INF[WS_I-1].REL_TYPE = LNRPARS.REL_TYPE;
            LNCHOLD.DATA.PART_INF[WS_I-1].PART_PCT = LNRPARS.PARTI_PCT;
            LNCHOLD.DATA.PART_INF[WS_I-1].PART_AMT = LNRPARS.PARTI_AMT;
            LNRPARS.STATUS = 'D';
            LNRPARS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRPARS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_LNTPARS();
            if (pgmRtn) return;
            T000_READNEXT_LNTPARS();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTPARS();
        if (pgmRtn) return;
    }
    public void S000_DELETE_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNCSPART.DATA.LN_CTANO;
        T000_STARTBR_UPD_LNTSETL();
        if (pgmRtn) return;
        T000_READNEXT_LNTSETL();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_NOTFND, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        for (WS_I = 1; WS_I <= 10 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_I += 1) {
            LNCHOLD.DATA.PART_INF[WS_I-1].AC_TYP = LNRSETL.AC_TYP;
            LNCHOLD.DATA.PART_INF[WS_I-1].PART_AC = LNRSETL.AC;
            T000_DELETE_LNTSETL();
            if (pgmRtn) return;
            T000_READNEXT_LNTSETL();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTSETL();
        if (pgmRtn) return;
    }
    public void S000_DELETE_PARS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPARS);
        LNRPARS.KEY.CONTRACT_NO = LNCSPART.DATA.LN_CTANO;
        LNCHOLD.DATA.LN_CTANO = LNCSPART.DATA.LN_CTANO;
        T000_STARTBR_UPD_LNTPARS();
        if (pgmRtn) return;
        T000_READNEXT_LNTPARS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PARS_REC_NOTFND, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCHOLD.DATA.SYN_TYP = LNRPARS.INNER_OUT_FLG;
        for (WS_I = 1; WS_I <= 10 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_I += 1) {
            LNCHOLD.DATA.PART_INF[WS_I-1].LOC_BANK = LNRPARS.LOCAL_BANK_FLAG;
            LNCHOLD.DATA.PART_INF[WS_I-1].PART_NO = LNRPARS.KEY.SEQ_NO;
            LNCHOLD.DATA.PART_INF[WS_I-1].PART_BR = LNRPARS.BOOK_BR;
            LNCHOLD.DATA.PART_INF[WS_I-1].PART_NM = LNRPARS.PARTI_NAME;
            LNCHOLD.DATA.PART_INF[WS_I-1].REL_TYPE = LNRPARS.REL_TYPE;
            LNCHOLD.DATA.PART_INF[WS_I-1].LOC_BANK = LNRPARS.LOCAL_BANK_FLAG;
            LNCHOLD.DATA.PART_INF[WS_I-1].ADJ_BANK = LNRPARS.ADJ_BANK_FLAG;
            LNCHOLD.DATA.PART_INF[WS_I-1].PART_PCT = LNRPARS.PARTI_PCT;
            LNCHOLD.DATA.PART_INF[WS_I-1].PART_AMT = LNRPARS.PARTI_AMT;
            T000_DELETE_LNTPARS();
            if (pgmRtn) return;
            T000_READNEXT_LNTPARS();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTPARS();
        if (pgmRtn) return;
    }
    public void S000_QUERY_PARS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPARS);
        LNRPARS.KEY.CONTRACT_NO = LNCSPART.DATA.LN_CTANO;
        T000_STARTBR_LNTPARS();
        if (pgmRtn) return;
        T000_READNEXT_LNTPARS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PARS_REC_NOTFND, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCSPART.DATA.SYN_TYP = LNRPARS.INNER_OUT_FLG;
        for (WS_I = 1; WS_I <= 10 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_I += 1) {
            LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK = LNRPARS.LOCAL_BANK_FLAG;
            LNCSPART.DATA.PART_INF[WS_I-1].ADJ_BANK = LNRPARS.ADJ_BANK_FLAG;
            LNCSPART.DATA.PART_INF[WS_I-1].PART_NO = LNRPARS.KEY.SEQ_NO;
            LNCSPART.DATA.PART_INF[WS_I-1].PART_BR = LNRPARS.BOOK_BR;
            LNCSPART.DATA.PART_INF[WS_I-1].PART_NM = LNRPARS.PARTI_NAME;
            LNCSPART.DATA.PART_INF[WS_I-1].REL_TYPE = LNRPARS.REL_TYPE;
            LNCSPART.DATA.PART_INF[WS_I-1].PART_PCT = LNRPARS.PARTI_PCT;
            LNCSPART.DATA.PART_INF[WS_I-1].PART_AMT = LNRPARS.PARTI_AMT;
            LNCSPART.DATA.PART_INF[WS_I-1].STATUS = LNRPARS.STATUS;
            T000_READNEXT_LNTPARS();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTPARS();
        if (pgmRtn) return;
    }
    public void S000_QUERY_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNCSPART.DATA.LN_CTANO;
        T000_STARTBR_LNTSETL();
        if (pgmRtn) return;
        T000_READNEXT_LNTSETL();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_NOTFND, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        for (WS_I = 1; WS_I <= 10 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_I += 1) {
            LNCSPART.DATA.PART_INF[WS_I-1].AC_TYP = LNRSETL.AC_TYP;
            LNCSPART.DATA.PART_INF[WS_I-1].PART_AC = LNRSETL.AC;
            T000_READNEXT_LNTSETL();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTSETL();
        if (pgmRtn) return;
    }
    public void S000_MAKE_HIS_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCHNEW);
        LNCHNEW.DATA.LN_CTANO = LNCSPART.DATA.LN_CTANO;
        LNCHNEW.DATA.PAPER_NM = LNCSPART.DATA.PAPER_NM;
        LNCHNEW.DATA.CCY = LNCSPART.DATA.CCY;
        LNCHNEW.DATA.AMT = LNCSPART.DATA.AMT;
        LNCHNEW.DATA.MAIN_BR = LNCSPART.DATA.MAIN_BR;
        LNCHNEW.DATA.SYN_TYP = LNCSPART.DATA.SYN_TYP;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            LNCHNEW.DATA.PART_INF[WS_I-1].LOC_BANK = LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK;
            LNCHNEW.DATA.PART_INF[WS_I-1].ADJ_BANK = LNCSPART.DATA.PART_INF[WS_I-1].ADJ_BANK;
            LNCHNEW.DATA.PART_INF[WS_I-1].PART_NO = LNCSPART.DATA.PART_INF[WS_I-1].PART_NO;
            LNCHNEW.DATA.PART_INF[WS_I-1].PART_BR = LNCSPART.DATA.PART_INF[WS_I-1].PART_BR;
            LNCHNEW.DATA.PART_INF[WS_I-1].PART_NM = LNCSPART.DATA.PART_INF[WS_I-1].PART_NM;
            LNCHNEW.DATA.PART_INF[WS_I-1].REL_TYPE = LNCSPART.DATA.PART_INF[WS_I-1].REL_TYPE;
            LNCHNEW.DATA.PART_INF[WS_I-1].PART_PCT = LNCSPART.DATA.PART_INF[WS_I-1].PART_PCT;
            LNCHNEW.DATA.PART_INF[WS_I-1].PART_AMT = LNCSPART.DATA.PART_INF[WS_I-1].PART_AMT;
            LNCHNEW.DATA.PART_INF[WS_I-1].AC_TYP = LNCSPART.DATA.PART_INF[WS_I-1].AC_TYP;
            LNCHNEW.DATA.PART_INF[WS_I-1].PART_AC = LNCSPART.DATA.PART_INF[WS_I-1].PART_AC;
        }
    }
    public void S000_WRITE_TRAN_HIS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = LNCSPART.DATA.LN_CTANO;
        BPCPNHIS.INFO.FMT_ID = "LNCI5000";
        BPCPNHIS.INFO.FMT_ID_LEN = 3477;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.OLD_DAT_PT = LNCHOLD;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCHNEW;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTPARS_FIRST() throws IOException,SQLException,Exception {
        LNTPARS_RD = new DBParm();
        LNTPARS_RD.TableName = "LNTPARS";
        LNTPARS_RD.eqWhere = "CONTRACT_NO";
        LNTPARS_RD.fst = true;
        IBS.READ(SCCGWA, LNRPARS, LNTPARS_RD);
    }
    public void T000_WRITE_LNTPARS() throws IOException,SQLException,Exception {
        LNTPARS_RD = new DBParm();
        LNTPARS_RD.TableName = "LNTPARS";
        LNTPARS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRPARS, LNTPARS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRSETL, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTPARS() throws IOException,SQLException,Exception {
        LNTPARS_BR.rp = new DBParm();
        LNTPARS_BR.rp.TableName = "LNTPARS";
        LNTPARS_BR.rp.eqWhere = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRPARS, LNTPARS_BR);
    }
    public void T000_STARTBR_UPD_LNTPARS() throws IOException,SQLException,Exception {
        LNTPARS_BR.rp = new DBParm();
        LNTPARS_BR.rp.TableName = "LNTPARS";
        LNTPARS_BR.rp.eqWhere = "CONTRACT_NO";
        LNTPARS_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, LNRPARS, LNTPARS_BR);
    }
    public void T000_READNEXT_LNTPARS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPARS, this, LNTPARS_BR);
    }
    public void T000_REWRITE_LNTPARS() throws IOException,SQLException,Exception {
        LNTPARS_RD = new DBParm();
        LNTPARS_RD.TableName = "LNTPARS";
        IBS.REWRITE(SCCGWA, LNRPARS, LNTPARS_RD);
    }
    public void T000_DELETE_LNTPARS() throws IOException,SQLException,Exception {
        LNTPARS_RD = new DBParm();
        LNTPARS_RD.TableName = "LNTPARS";
        IBS.DELETE(SCCGWA, LNRPARS, LNTPARS_RD);
    }
    public void T000_ENDBR_LNTPARS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPARS_BR);
    }
    public void T000_STARTBR_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.eqWhere = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRSETL, LNTSETL_BR);
    }
    public void T000_STARTBR_UPD_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.eqWhere = "CONTRACT_NO";
        LNTSETL_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, LNRSETL, LNTSETL_BR);
    }
    public void T000_READNEXT_LNTSETL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_DELETE_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.DELETE(SCCGWA, LNRSETL, LNTSETL_RD);
    }
    public void T000_ENDBR_LNTSETL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSETL_BR);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPART.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
