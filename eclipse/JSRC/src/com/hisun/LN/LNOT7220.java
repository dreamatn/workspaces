package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7220 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ENTER = 'E';
    LNOT7220_WS_MSGID WS_MSGID = new LNOT7220_WS_MSGID();
    short WS_FLD_NO = 0;
    long WS_SEQ_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCSPAIP LNCSPAIP = new LNCSPAIP();
    LNCSTMPP LNCSTMPP = new LNCSTMPP();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    LNCTMPP1 LNCTMPP1 = new LNCTMPP1();
    LNRCONT LNRCONT = new LNRCONT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRICTL LNRICTL = new LNRICTL();
    LNCCLOAN LNCCLOAN = new LNCCLOAN();
    SCCGWA SCCGWA;
    LNB7220_AWA_7220 LNB7220_AWA_7220;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT7220 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7220_AWA_7220>");
        LNB7220_AWA_7220 = (LNB7220_AWA_7220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        WS_SEQ_NO = SCCGWA.COMM_AREA.JRN_NO;
        B200_UPD_PTR_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB7220_AWA_7220.LN_AC.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB7220_AWA_7220.LN_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.LN_AC);
        IBS.init(SCCGWA, LNCCLOAN);
        LNCCLOAN.COMM_DATA.LN_AC = LNB7220_AWA_7220.LN_AC;
        LNCCLOAN.COMM_DATA.CHECK_TYP = K_ENTER;
        S000_CALL_FUN_LNZCLOAN();
        if (pgmRtn) return;
    }
    public void B200_UPD_PTR_PROCESS() throws IOException,SQLException,Exception {
        if (LNB7220_AWA_7220.TRAN_SEQ == 0) {
            IBS.init(SCCGWA, LNCSPAIP);
            LNCSPAIP.REC_DATA.KEY.LN_AC = LNB7220_AWA_7220.LN_AC;
            LNCSPAIP.REC_DATA.KEY.SUF_NO = LNB7220_AWA_7220.SUF_NO;
            LNCSPAIP.REC_DATA.KEY.PHS_NO = LNB7220_AWA_7220.PHS_NO;
            LNCSPAIP.FUNC = '5';
            LNCSPAIP.REC_DATA.TRAN_SEQ = WS_SEQ_NO;
            LNCSPAIP.REC_DATA.WRITE_TMPP_FLG = 'Y';
            LNCSPAIP.REC_DATA.PAGE_ROW = LNB7220_AWA_7220.PAGE_ROW;
            LNCSPAIP.REC_DATA.PAGE_NUM = LNB7220_AWA_7220.PAGE_NUM;
            S000_CALL_LNZSPAIP();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, LNCSTMPP);
            LNCSTMPP.REC_DATA.KEY.LN_AC = LNB7220_AWA_7220.LN_AC;
            LNCSTMPP.REC_DATA.KEY.SUF_NO = LNB7220_AWA_7220.SUF_NO;
            LNCSTMPP.REC_DATA.KEY.PHS_NO = LNB7220_AWA_7220.PHS_NO;
            LNCSTMPP.FUNC = '5';
            LNCSTMPP.REC_DATA.KEY.TRAN_SEQ = LNB7220_AWA_7220.TRAN_SEQ;
            LNCSTMPP.REC_DATA.PAGE_ROW = LNB7220_AWA_7220.PAGE_ROW;
            LNCSTMPP.REC_DATA.PAGE_NUM = LNB7220_AWA_7220.PAGE_NUM;
            S000_CALL_LNZSTMPP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSPAIP.FUNC);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY);
    }
    public void S000_CALL_LNZSPAIP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-PAIPM-MAIN", LNCSPAIP);
        if (LNCSPAIP.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPAIP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSTMPP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-TMPP-MAIN", LNCSTMPP);
        if (LNCSTMPP.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSTMPP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_FUN_LNZCLOAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CHECK-LNTLOAN", LNCCLOAN);
        if (LNCCLOAN.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLOAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
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
