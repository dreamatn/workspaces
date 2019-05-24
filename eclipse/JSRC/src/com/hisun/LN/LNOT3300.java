package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT3300 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ENTER = 'E';
    LNOT3300_WS_MSGID WS_MSGID = new LNOT3300_WS_MSGID();
    short WS_FLD_NO = 0;
    long WS_SEQ_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCSPAIP LNCSPAIP = new LNCSPAIP();
    LNCSTMPP LNCSTMPP = new LNCSTMPP();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNCCLOAN LNCCLOAN = new LNCCLOAN();
    LNCMATIP LNCMATIP = new LNCMATIP();
    SCCGWA SCCGWA;
    LNB3300_AWA_3300 LNB3300_AWA_3300;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT3300 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB3300_AWA_3300>");
        LNB3300_AWA_3300 = (LNB3300_AWA_3300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MOVE_TO_INTERFACE();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB3300_AWA_3300.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            if (LNB3300_AWA_3300.CTA_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB3300_AWA_3300.CTA_NO);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCLOAN);
        LNCCLOAN.COMM_DATA.LN_AC = LNB3300_AWA_3300.CTA_NO;
        LNCCLOAN.COMM_DATA.CHECK_TYP = K_ENTER;
        S000_CALL_FUN_LNZCLOAN();
        if (pgmRtn) return;
        if (LNB3300_AWA_3300.PRIN_AMT < 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.E_LN_AMT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_MOVE_TO_INTERFACE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCMATIP);
        LNCMATIP.COMM_DATA.FUNC = LNB3300_AWA_3300.FUNC;
        LNCMATIP.COMM_DATA.CTA_NO = LNB3300_AWA_3300.CTA_NO;
        LNCMATIP.COMM_DATA.SUF_NO = LNB3300_AWA_3300.SUF_NO;
        LNCMATIP.COMM_DATA.PHS_NO = LNB3300_AWA_3300.PHS_NO;
        LNCMATIP.COMM_DATA.PRIN_AMT = LNB3300_AWA_3300.PRIN_AMT;
        LNCMATIP.COMM_DATA.REMP_AMT = LNB3300_AWA_3300.REMP_AMT;
        LNCMATIP.COMM_DATA.TOT_TERM = LNB3300_AWA_3300.TOT_TERM;
        LNCMATIP.COMM_DATA.INST_MTH = LNB3300_AWA_3300.INST_MTH;
        LNCMATIP.COMM_DATA.INST_AMT = LNB3300_AWA_3300.INST_AMT;
        LNCMATIP.COMM_DATA.CNST_AMT = LNB3300_AWA_3300.CNST_AMT;
        LNCMATIP.COMM_DATA.INT_FLG = LNB3300_AWA_3300.INT_FLG;
        LNCMATIP.COMM_DATA.INCR_PER = LNB3300_AWA_3300.INCR_PER;
        LNCMATIP.COMM_DATA.INCR_AMT = LNB3300_AWA_3300.INCR_AMT;
        LNCMATIP.COMM_DATA.CAL_TERM = LNB3300_AWA_3300.CAL_TERM;
        LNCMATIP.COMM_DATA.CMP_TERM = LNB3300_AWA_3300.CMP_TERM;
        LNCMATIP.COMM_DATA.LNST_AMT = LNB3300_AWA_3300.LNST_AMT;
        LNCMATIP.COMM_DATA.FNST_AMT = LNB3300_AWA_3300.FNST_AMT;
        LNCMATIP.COMM_DATA.INST_RAT = LNB3300_AWA_3300.INST_RAT;
        LNCMATIP.COMM_DATA.GNST_AMT = LNB3300_AWA_3300.GNST_AMT;
        LNCMATIP.COMM_DATA.COG_TERM = LNB3300_AWA_3300.COG_TERM;
        LNCMATIP.COMM_DATA.S_DT = LNB3300_AWA_3300.S_DT;
        LNCMATIP.COMM_DATA.E_DT = LNB3300_AWA_3300.E_DT;
        LNCMATIP.COMM_DATA.TRAN_SEQ = LNB3300_AWA_3300.TRAN_SEQ;
        LNCMATIP.COMM_DATA.ACTION = LNB3300_AWA_3300.ACTION;
        LNCMATIP.COMM_DATA.CI_NO = LNB3300_AWA_3300.CI_NO;
        LNCMATIP.COMM_DATA.CI_CNAME = LNB3300_AWA_3300.CI_CNAME;
        LNCMATIP.COMM_DATA.PROD_CD = LNB3300_AWA_3300.PROD_CD;
        LNCMATIP.COMM_DATA.PROD_NM = LNB3300_AWA_3300.PROD_NM;
        LNCMATIP.COMM_DATA.CCY = LNB3300_AWA_3300.CCY;
        LNCMATIP.COMM_DATA.OS_BAL = LNB3300_AWA_3300.OS_BAL;
        LNCMATIP.COMM_DATA.PERD = LNB3300_AWA_3300.PERD;
        LNCMATIP.COMM_DATA.PER_UNIT = LNB3300_AWA_3300.PER_UNIT;
        LNCMATIP.COMM_DATA.SPEC_FLG = LNB3300_AWA_3300.SPEC_FLG;
        LNCMATIP.COMM_DATA.PAGE_ROW = LNB3300_AWA_3300.PAGE_ROW;
        LNCMATIP.COMM_DATA.PAGE_NUM = LNB3300_AWA_3300.PAGE_NUM;
        S000_CALL_LNZMATIP();
        if (pgmRtn) return;
    }
    public void S000_CALL_FUN_LNZCLOAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CHECK-LNTLOAN", LNCCLOAN);
        if (LNCCLOAN.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLOAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZMATIP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-MATIP-P", LNCMATIP);
        CEP.TRC(SCCGWA, LNCMATIP.RC);
        if (LNCMATIP.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCMATIP.RC);
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
