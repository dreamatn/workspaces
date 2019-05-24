package com.hisun.LN;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSAPT {
    brParm LNTDISB_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char LNZSAPT_FILLER1 = ' ';
    int WS_REC_CNT = 20;
    LNZSAPT_WS_REPAY_DETAIL WS_REPAY_DETAIL = new LNZSAPT_WS_REPAY_DETAIL();
    char WS_FOUND_FLG = ' ';
    char WS_EOF_FLG = ' ';
    LNRDISB LNRDISB = new LNRDISB();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNCRDISB LNCRDISB = new LNCRDISB();
    SCCGWA SCCGWA;
    LNCSAPT LNCSAPT;
    public void MP(SCCGWA SCCGWA, LNCSAPT LNCSAPT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSAPT = LNCSAPT;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSAPT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSAPT.RC.RC_APP = "LN";
        LNCSAPT.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if ((LNCSAPT.COMM_DATA.FUNC_CODE != 'A' 
            && LNCSAPT.COMM_DATA.FUNC_CODE != 'B' 
            && LNCSAPT.COMM_DATA.FUNC_CODE != 'C' 
            && LNCSAPT.COMM_DATA.FUNC_CODE != 'D' 
            && LNCSAPT.COMM_DATA.FUNC_CODE != 'E' 
            && LNCSAPT.COMM_DATA.FUNC_CODE != 'L' 
            && LNCSAPT.COMM_DATA.FUNC_CODE != 'V')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSAPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSAPT.COMM_DATA.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT, LNCSAPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSAPT.COMM_DATA.APT_REF.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APTREF_EMPTY, LNCSAPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSAPT.COMM_DATA.TR_VAL_DTE == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_VALUE_DATE_M_INPUT, LNCSAPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSAPT.COMM_DATA.TYPE);
        if (LNCSAPT.COMM_DATA.FUNC_CODE == 'A' 
            || LNCSAPT.COMM_DATA.FUNC_CODE == 'C' 
            || LNCSAPT.COMM_DATA.FUNC_CODE == 'D' 
            || LNCSAPT.COMM_DATA.FUNC_CODE == 'E') {
            if ((LNCSAPT.COMM_DATA.TYPE != 'P' 
                && LNCSAPT.COMM_DATA.TYPE != 'I' 
                && LNCSAPT.COMM_DATA.TYPE != 'T' 
                && LNCSAPT.COMM_DATA.TYPE != 'C')) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMTTYP_INVAILD, LNCSAPT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCSAPT.COMM_DATA.FUNC_CODE == 'A') {
            B100_APT_RECORD_ADD();
            if (pgmRtn) return;
        } else if (LNCSAPT.COMM_DATA.FUNC_CODE == 'B') {
            B100_APT_RECORD_BRW();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSAPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_APT_RECORD_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRDISB);
        IBS.init(SCCGWA, LNRDISB);
        LNCRDISB.FUNC = 'A';
        LNRDISB.KEY.DISB_REF = LNCSAPT.COMM_DATA.APT_REF;
        CEP.TRC(SCCGWA, LNRDISB.KEY.DISB_REF);
        LNRDISB.KEY.TR_VAL_DTE = LNCSAPT.COMM_DATA.TR_VAL_DTE;
        LNRDISB.KEY.CTA_NO = LNCSAPT.COMM_DATA.CTA_NO;
        if (LNCSAPT.COMM_DATA.CTA_SEQ.trim().length() == 0) LNRDISB.KEY.CTA_SEQ = 0;
        else LNRDISB.KEY.CTA_SEQ = Integer.parseInt(LNCSAPT.COMM_DATA.CTA_SEQ);
        LNRDISB.KEY.AMT_TYP = LNCSAPT.COMM_DATA.TYPE;
        LNRDISB.KEY.TERM = LNCSAPT.COMM_DATA.TERM;
        LNRDISB.KEY.SUBS_PROJ_NO = LNCSAPT.COMM_DATA.SUBS_PROJ_NO;
        LNRDISB.VAL_DTE = LNCSAPT.COMM_DATA.VAL_DTE;
        LNRDISB.DUE_DTE = LNCSAPT.COMM_DATA.DUE_DTE;
        LNRDISB.LENDER_TYP = LNCSAPT.COMM_DATA.PARTI_ATTR;
        LNRDISB.PART_LVL = LNCSAPT.COMM_DATA.PARTI_LEVEL;
        LNRDISB.PART_ROLE = LNCSAPT.COMM_DATA.PARTI_ROLE;
        LNRDISB.P_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.P_AMT;
        LNRDISB.D_P_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.D_P_AMT;
        LNRDISB.I_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.I_AMT;
        LNRDISB.D_I_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.D_I_AMT;
        LNRDISB.O_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.O_AMT;
        LNRDISB.D_O_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.D_O_AMT;
        LNRDISB.L_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.L_AMT;
        LNRDISB.D_L_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.D_L_AMT;
        LNRDISB.W_O_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.W_O_AMT;
        LNRDISB.W_L_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.W_L_AMT;
        LNRDISB.PC_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.PC_AMT;
        LNRDISB.BFC_AMT = LNCSAPT.COMM_DATA.TOT_AMTS.BFC_AMT;
        LNRDISB.EQU = LNCSAPT.COMM_DATA.TOT_AMTS.EQU_AMT;
        LNRDISB.CRT_DATE = SCCGWA.COMM_AREA.TR_DATE;
        LNRDISB.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_LNZRDISB();
        if (pgmRtn) return;
    }
    public void B100_APT_RECORD_BRW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_REPAY_DETAIL);
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        T000_STARTBR_LNTDISB_CUST();
        if (pgmRtn) return;
        T000_READNEXT_LNTDISB();
        if (pgmRtn) return;
        WS_REPAY_DETAIL.WS_DUE_DTE = LNRDISB.DUE_DTE;
        while (WS_EOF_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            B110_TENOR_DETAIL_PRINT();
            if (pgmRtn) return;
            T000_READNEXT_LNTDISB();
            if (pgmRtn) return;
        }
        if (WS_REPAY_DETAIL.WS_TOT_PAID > 0 
            || WS_REPAY_DETAIL.WS_PLC_WAIVE > 0 
            || WS_REPAY_DETAIL.WS_ILC_WAIVE > 0 
            || WS_REPAY_DETAIL.WS_BRKFUND_COST > 0) {
            R000_TRANS_DATA_MPAGE_OUTPUT();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTDISB();
        if (pgmRtn) return;
    }
    public void B110_TENOR_DETAIL_PRINT() throws IOException,SQLException,Exception {
        if (LNRDISB.DUE_DTE != WS_REPAY_DETAIL.WS_DUE_DTE) {
            if (WS_REPAY_DETAIL.WS_TOT_PAID > 0 
                || WS_REPAY_DETAIL.WS_PLC_WAIVE > 0 
                || WS_REPAY_DETAIL.WS_ILC_WAIVE > 0 
                || WS_REPAY_DETAIL.WS_BRKFUND_COST > 0) {
                R000_TRANS_DATA_MPAGE_OUTPUT();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, WS_REPAY_DETAIL);
            WS_REPAY_DETAIL.WS_DUE_DTE = LNRDISB.DUE_DTE;
        }
        WS_REPAY_DETAIL.WS_PRIN_RECV += LNRDISB.P_AMT;
        WS_REPAY_DETAIL.WS_PRIN_PAID += LNRDISB.D_P_AMT;
        WS_REPAY_DETAIL.WS_INT_RECV += LNRDISB.I_AMT;
        WS_REPAY_DETAIL.WS_INT_PAID += LNRDISB.D_I_AMT;
        WS_REPAY_DETAIL.WS_PLC_RECV += LNRDISB.O_AMT;
        WS_REPAY_DETAIL.WS_PLC_PAID += LNRDISB.D_O_AMT;
        WS_REPAY_DETAIL.WS_PLC_WAIVE += LNRDISB.W_O_AMT;
        WS_REPAY_DETAIL.WS_ILC_RECV += LNRDISB.L_AMT;
        WS_REPAY_DETAIL.WS_ILC_PAID += LNRDISB.D_L_AMT;
        WS_REPAY_DETAIL.WS_ILC_WAIVE += LNRDISB.W_L_AMT;
        WS_REPAY_DETAIL.WS_PREPAY_CHG += LNRDISB.PC_AMT;
        WS_REPAY_DETAIL.WS_BRKFUND_COST += LNRDISB.BFC_AMT;
        WS_REPAY_DETAIL.WS_TOT_PAID = WS_REPAY_DETAIL.WS_PRIN_PAID + WS_REPAY_DETAIL.WS_INT_PAID + WS_REPAY_DETAIL.WS_PLC_PAID + WS_REPAY_DETAIL.WS_ILC_PAID + WS_REPAY_DETAIL.WS_PREPAY_CHG;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 216;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_REPAY_DETAIL);
        SCCMPAG.DATA_LEN = 216;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRDISB() throws IOException,SQLException,Exception {
        LNCRDISB.REC_PTR = LNRDISB;
        LNCRDISB.REC_LEN = 378;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTDISB", LNCRDISB);
        if (LNCRDISB.RC.RC_CODE != 0) {
            LNCSAPT.RC.RC_APP = LNCRDISB.RC.RC_MMO;
            LNCSAPT.RC.RC_RTNCODE = LNCRDISB.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTDISB_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRDISB);
        LNRDISB.KEY.DISB_REF = LNCSAPT.COMM_DATA.APT_REF;
        LNRDISB.KEY.TR_VAL_DTE = LNCSAPT.COMM_DATA.TR_VAL_DTE;
        LNRDISB.KEY.CTA_NO = LNCSAPT.COMM_DATA.CTA_NO;
        if (LNCSAPT.COMM_DATA.CTA_SEQ.trim().length() == 0) LNRDISB.KEY.CTA_SEQ = 0;
        else LNRDISB.KEY.CTA_SEQ = Integer.parseInt(LNCSAPT.COMM_DATA.CTA_SEQ);
        LNRDISB.KEY.SUBS_PROJ_NO = 0;
        if (LNCSAPT.COMM_DATA.SUBS_FLG == 'Y') {
            LNTDISB_BR.rp = new DBParm();
            LNTDISB_BR.rp.TableName = "LNTDISB";
            LNTDISB_BR.rp.where = "DISB_REF = :LNRDISB.KEY.DISB_REF "
                + "AND TR_VAL_DTE = :LNRDISB.KEY.TR_VAL_DTE "
                + "AND CTA_NO = :LNRDISB.KEY.CTA_NO "
                + "AND CTA_SEQ = :LNRDISB.KEY.CTA_SEQ "
                + "AND SUBS_PROJ_NO > :LNRDISB.KEY.SUBS_PROJ_NO";
            LNTDISB_BR.rp.order = "DUE_DTE, AMT_TYP";
            IBS.STARTBR(SCCGWA, LNRDISB, this, LNTDISB_BR);
        } else {
            LNTDISB_BR.rp = new DBParm();
            LNTDISB_BR.rp.TableName = "LNTDISB";
            LNTDISB_BR.rp.where = "DISB_REF = :LNRDISB.KEY.DISB_REF "
                + "AND TR_VAL_DTE = :LNRDISB.KEY.TR_VAL_DTE "
                + "AND CTA_NO = :LNRDISB.KEY.CTA_NO "
                + "AND CTA_SEQ = :LNRDISB.KEY.CTA_SEQ "
                + "AND SUBS_PROJ_NO = :LNRDISB.KEY.SUBS_PROJ_NO";
            LNTDISB_BR.rp.order = "DUE_DTE, AMT_TYP";
            IBS.STARTBR(SCCGWA, LNRDISB, this, LNTDISB_BR);
        }
    }
    public void T000_READNEXT_LNTDISB() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRDISB, this, LNTDISB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_EOF_FLG = 'Y';
        }
    }
    public void T000_ENDBR_LNTDISB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTDISB_BR);
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
        if (LNCSAPT.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSAPT=");
            CEP.TRC(SCCGWA, LNCSAPT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
