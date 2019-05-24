package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DB.*;
import com.hisun.SM.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSBEXT {
    brParm LNTEXTN_BR = new brParm();
    DBParm LNTEXTN_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short Q_MAX_CNT = 1000;
    String FMT_ID = "LN807";
    LNZSBEXT_WS_VARIABLES WS_VARIABLES = new LNZSBEXT_WS_VARIABLES();
    LNZSBEXT_WS_OUT_RECODE WS_OUT_RECODE = new LNZSBEXT_WS_OUT_RECODE();
    LNZSBEXT_WS_COND_FLAG WS_COND_FLAG = new LNZSBEXT_WS_COND_FLAG();
    LNREXTN LNREXTN = new LNREXTN();
    LNCHUEXT LNCHUEXT = new LNCHUEXT();
    BPRNHIST BPRNHIST = new BPRNHIST();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPRPARP BPRPARP = new BPRPARP();
    BPRPARM BPRPARM = new BPRPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCCLNQ_WS_DB_VARS WS_DB_VARS = new LNCCLNQ_WS_DB_VARS();
    int RTCD = 0;
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT BPRTRT;
    LNCBEXT LNCBEXT;
    public void MP(SCCGWA SCCGWA, LNCBEXT LNCBEXT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCBEXT = LNCBEXT;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSBEXT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, WS_COND_FLAG);
        IBS.init(SCCGWA, WS_DB_VARS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_O_HEAD);
        WS_VARIABLES.CNT = 0;
        if (LNCBEXT.PAGE_NUM == 0) {
            WS_OUT_RECODE.WS_O_HEAD.CURR_PAGE = 1;
        } else {
            WS_OUT_RECODE.WS_O_HEAD.CURR_PAGE = (short) LNCBEXT.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_O_HEAD.LAST_PAGE = 'N';
        if (LNCBEXT.PAGE_ROW == 0) {
            WS_VARIABLES.WS_PAGE.PAGE_ROW = 25;
        } else {
            WS_VARIABLES.WS_PAGE.PAGE_ROW = LNCBEXT.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, LNCBEXT.PAGE_NUM);
        CEP.TRC(SCCGWA, LNCBEXT.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.CURR_PAGE);
        WS_VARIABLES.WS_PAGE.NEXT_START_NUM = ( ( WS_OUT_RECODE.WS_O_HEAD.CURR_PAGE - 1 ) * WS_VARIABLES.WS_PAGE.PAGE_ROW ) + 1;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_PAGE.NEXT_START_NUM);
        R000_CHECK_GROUP_LNTEXTN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.TOTAL_NUM);
        if (WS_OUT_RECODE.WS_O_HEAD.TOTAL_NUM != 0) {
            WS_VARIABLES.WS_PAGE.BAL_CNT = WS_OUT_RECODE.WS_O_HEAD.TOTAL_NUM % WS_VARIABLES.WS_PAGE.PAGE_ROW;
            WS_OUT_RECODE.WS_O_HEAD.TOTAL_PAGE = (short) ((WS_OUT_RECODE.WS_O_HEAD.TOTAL_NUM - WS_VARIABLES.WS_PAGE.BAL_CNT) / WS_VARIABLES.WS_PAGE.PAGE_ROW);
            if (WS_VARIABLES.WS_PAGE.BAL_CNT != 0) {
                WS_OUT_RECODE.WS_O_HEAD.TOTAL_PAGE += 1;
            }
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.TOTAL_PAGE);
        } else {
            WS_OUT_RECODE.WS_O_HEAD.TOTAL_PAGE = 1;
            WS_OUT_RECODE.WS_O_HEAD.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_PAGE.PAGE_ROW = 0;
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_VARIABLES.WS_PAGE.IDX = 0;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_PAGE.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
        WS_COND_FLAG.EXTN_FOUND_FLG = 'N';
        T00_STARTBR_LNTEXTN();
        if (pgmRtn) return;
        T00_READNEXT_LNTEXTN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "11111111111111");
        if (WS_COND_FLAG.EXTN_FOUND_FLG == 'N') {
            WS_VARIABLES.WS_PAGE.PAGE_ROW = 0;
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B100_GET_RECORD();
        if (pgmRtn) return;
        WS_VARIABLES.WS_PAGE.IDX = 0;
        CEP.TRC(SCCGWA, "22222222222222222");
        while (WS_VARIABLES.WS_PAGE.IDX < WS_VARIABLES.WS_PAGE.PAGE_ROW 
            && WS_COND_FLAG.EXTN_FOUND_FLG != 'N' 
            && WS_VARIABLES.CNT <= 1000) {
            CEP.TRC(SCCGWA, "33333333333333333");
            WS_VARIABLES.WS_PAGE.IDX += 1;
            IBS.init(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1]);
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_CTA_TYP = 'D';
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_CTA_NO = LNREXTN.KEY.CONTRACT_NO;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_CI_NO = LNCCLNQ.DATA.CI_NO;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_PROC_CD = LNCCLNQ.DATA.PROD_CD;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_CCY = LNCCLNQ.DATA.CCY;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_PRIN = LNCCLNQ.DATA.AMT;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_BAL = LNCCLNQ.DATA.TOT_BAL;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_OLD_DATE = LNREXTN.OLD_DUE_DT;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_EE_DATE = LNREXTN.NEW_DUE_DT;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_RMK = LNREXTN.EXT_RSN;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_VAL_DATE = LNREXTN.KEY.VAL_DT;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_AMT = LNREXTN.EXT_AMT;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_STS = LNREXTN.KEY.STATUS;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_APP_DATE = LNREXTN.CRT_DATE;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_EXT_FLG = LNREXTN.KEY.EXT_FLG;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_INT_RAT = LNREXTN.ALL_IN_RATE;
            CEP.TRC(SCCGWA, "LOOP BEGIN:");
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_PAGE.IDX);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_CTA_TYP);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_CTA_NO);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_CI_NO);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_PROC_CD);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_CCY);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_PRIN);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_BAL);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_OLD_DATE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_EE_DATE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_RMK);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_VAL_DATE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_AMT);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_STS);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_INT_RAT);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_APP_DATE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.WS_PAGE.IDX-1].O_EXT_FLG);
            WS_VARIABLES.WS_PAGE.NEXT_START_NUM += 1;
            WS_DB_VARS.START_NUM = WS_VARIABLES.WS_PAGE.NEXT_START_NUM;
            T00_READNEXT_LNTEXTN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "44444444444");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_OUT_RECODE.WS_O_HEAD.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_PAGE.PAGE_ROW = WS_VARIABLES.WS_PAGE.IDX;
        }
        T00_ENDBR_LNTEXTN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "555555555555555555");
        WS_OUT_RECODE.WS_O_HEAD.CURR_PAGE_ROW = (short) WS_VARIABLES.WS_PAGE.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.CURR_PAGE_ROW);
        R020_OUTPUT_WRITE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "6666666666666666");
    }
    public void B100_GET_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCBEXT.CTA_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CI_NO);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.PROD_CD);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CCY);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.AMT);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.TOT_BAL);
    }
    public void T00_STARTBR_LNTEXTN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCBEXT.CTA_NO;
        CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
        LNTEXTN_BR.rp = new DBParm();
        LNTEXTN_BR.rp.TableName = "LNTEXTN";
        LNTEXTN_BR.rp.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNREXTN, this, LNTEXTN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_FWDH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "STARTBR LNTEXTN NORMAL");
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T00_READNEXT_LNTEXTN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
        WS_VARIABLES.CNT = WS_VARIABLES.CNT + 1;
        WS_COND_FLAG.EXTN_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNREXTN, this, LNTEXTN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLAG.EXTN_FOUND_FLG = 'Y';
        } else {
            WS_COND_FLAG.EXTN_FOUND_FLG = 'N';
        }
    }
    public void T00_ENDBR_LNTEXTN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTEXTN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R020_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = FMT_ID;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 6869;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_GROUP_LNTEXTN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCBEXT.CTA_NO;
        CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.set = "WS-CNT2=COUNT(*)";
        LNTEXTN_RD.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO";
        IBS.GROUP(SCCGWA, LNREXTN, this, LNTEXTN_RD);
        WS_OUT_RECODE.WS_O_HEAD.TOTAL_NUM = WS_DB_VARS.CNT2;
    }
    public void S000_CALL_LNZICIQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-CI-INFO", LNCICIQ);
        if (LNCICIQ.RC.RC_CODE != 0) {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = IBS.CLS2CPY(SCCGWA, LNCICIQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
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
