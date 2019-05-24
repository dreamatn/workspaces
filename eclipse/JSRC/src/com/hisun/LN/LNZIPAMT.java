package com.hisun.LN;

import com.hisun.SC.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZIPAMT {
    BigDecimal bigD;
    brParm LNTPARS_BR = new brParm();
    boolean pgmRtn = false;
    short WS_I = 0;
    short WS_ADJ_NUM = 0;
    double WS_SUM_PCT = 0;
    double WS_P_AMT = 0;
    double WS_I_AMT = 0;
    double WS_O_AMT = 0;
    double WS_L_AMT = 0;
    double WS_F_AMT = 0;
    int WS_WUCHA_BK_SEQ = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    LNRPARS LNRPARS = new LNRPARS();
    SCCGWA SCCGWA;
    LNCIPAMT LNCIPAMT;
    public void MP(SCCGWA SCCGWA, LNCIPAMT LNCIPAMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIPAMT = LNCIPAMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZIPAMT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCIPAMT.RC.RC_MMO = "LN";
        LNCIPAMT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_COUNT_PART_AMT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (LNCIPAMT.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_NO_M_INPUT, LNCIPAMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_COUNT_PART_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPARS);
        LNRPARS.KEY.CONTRACT_NO = LNCIPAMT.CTA_NO;
        T000_STARTBR_LNTPARS();
        if (pgmRtn) return;
        T000_READNEXT_LNTPARS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PARS_NOTFND, LNCIPAMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        for (WS_I = 1; WS_I <= 10 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_I += 1) {
            WS_SUM_PCT += LNRPARS.PARTI_PCT;
            LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO = LNRPARS.KEY.CONTRACT_NO;
            LNCIPAMT.PART_DATA[WS_I-1].PART_NM = LNRPARS.PARTI_NAME;
            LNCIPAMT.PART_DATA[WS_I-1].PART_BR = LNRPARS.BOOK_BR;
            LNCIPAMT.PART_DATA[WS_I-1].PART_NO = LNRPARS.KEY.SEQ_NO;
            LNCIPAMT.PART_DATA[WS_I-1].REL_TYPE = LNRPARS.REL_TYPE;
            LNCIPAMT.INNER_OUT_FLG = LNRPARS.INNER_OUT_FLG;
            LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG = LNRPARS.LOCAL_BANK_FLAG;
            LNCIPAMT.PART_DATA[WS_I-1].ADJ_BANK_FLAG = LNRPARS.ADJ_BANK_FLAG;
            LNCIPAMT.PART_DATA[WS_I-1].PARTI_PCT = LNRPARS.PARTI_PCT;
            LNCIPAMT.PART_DATA[WS_I-1].PART_YHS_AMT = LNRPARS.YHS_AMT;
            if (LNRPARS.ADJ_BANK_FLAG == 'N') {
                LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT = LNCIPAMT.P_AMT * LNRPARS.PARTI_PCT / 100;
                bigD = new BigDecimal(LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT);
                LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_P_AMT += LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT = LNCIPAMT.I_AMT * LNRPARS.PARTI_PCT / 100;
                bigD = new BigDecimal(LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT);
                LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_I_AMT += LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT = LNCIPAMT.O_AMT * LNRPARS.PARTI_PCT / 100;
                bigD = new BigDecimal(LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT);
                LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_O_AMT += LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT = LNCIPAMT.L_AMT * LNRPARS.PARTI_PCT / 100;
                bigD = new BigDecimal(LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT);
                LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_L_AMT += LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                LNCIPAMT.PART_DATA[WS_I-1].PART_F_AMT = LNCIPAMT.F_AMT * LNRPARS.PARTI_PCT / 100;
                bigD = new BigDecimal(LNCIPAMT.PART_DATA[WS_I-1].PART_F_AMT);
                LNCIPAMT.PART_DATA[WS_I-1].PART_F_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_F_AMT += LNCIPAMT.PART_DATA[WS_I-1].PART_F_AMT;
            } else {
                WS_ADJ_NUM += 1;
                WS_WUCHA_BK_SEQ = WS_I;
            }
            T000_READNEXT_LNTPARS();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTPARS();
        if (pgmRtn) return;
        if (WS_WUCHA_BK_SEQ == 0) {
        } else {
            LNCIPAMT.PART_DATA[WS_WUCHA_BK_SEQ-1].PART_P_AMT = LNCIPAMT.P_AMT - WS_P_AMT;
            LNCIPAMT.PART_DATA[WS_WUCHA_BK_SEQ-1].PART_I_AMT = LNCIPAMT.I_AMT - WS_I_AMT;
            LNCIPAMT.PART_DATA[WS_WUCHA_BK_SEQ-1].PART_O_AMT = LNCIPAMT.O_AMT - WS_O_AMT;
            LNCIPAMT.PART_DATA[WS_WUCHA_BK_SEQ-1].PART_L_AMT = LNCIPAMT.L_AMT - WS_L_AMT;
            LNCIPAMT.PART_DATA[WS_WUCHA_BK_SEQ-1].PART_F_AMT = LNCIPAMT.F_AMT - WS_F_AMT;
        }
        if (WS_SUM_PCT != 100) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PART_PCT_SUM_ERR, LNCIPAMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_ADJ_NUM != 1) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ADJ_NUM_MUST_ONE, LNCIPAMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTPARS() throws IOException,SQLException,Exception {
        LNTPARS_BR.rp = new DBParm();
        LNTPARS_BR.rp.TableName = "LNTPARS";
        LNTPARS_BR.rp.eqWhere = "CONTRACT_NO";
        LNTPARS_BR.rp.order = "SEQ_NO";
        IBS.STARTBR(SCCGWA, LNRPARS, LNTPARS_BR);
    }
    public void T000_READNEXT_LNTPARS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPARS, this, LNTPARS_BR);
    }
    public void T000_ENDBR_LNTPARS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPARS_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
