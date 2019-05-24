package com.hisun.LN;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZCLOAN {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_TRUE_ID = '1';
    char K_ENTER = 'E';
    char K_ADD = 'A';
    char K_MODIFY = 'M';
    char K_UPDATE = 'U';
    char K_CONFIRM = 'F';
    String K_CONTRACT_TYPE_CLGU = "CLGU";
    short WS_CNT = 0;
    short WS_IDX = 0;
    String WS_LN_AC = " ";
    char WS_CHECK_TYP = ' ';
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCRICTL LNCRICTL = new LNCRICTL();
    SCCGWA SCCGWA;
    LNCCLOAN LNCCLOAN;
    public void MP(SCCGWA SCCGWA, LNCCLOAN LNCCLOAN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCCLOAN = LNCCLOAN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZCLOAN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_GET_LNTLOAN();
        if (pgmRtn) return;
        B200_GET_ICTL_INFO();
        if (pgmRtn) return;
        B300_CHECK_DATA();
        if (pgmRtn) return;
    }
    public void B100_GET_LNTLOAN() throws IOException,SQLException,Exception {
        WS_LN_AC = LNCCLOAN.COMM_DATA.LN_AC;
        WS_CHECK_TYP = LNCCLOAN.COMM_DATA.CHECK_TYP;
        if (WS_LN_AC.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCCLOAN.COMM_DATA.LN_AC;
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        S000_CALL_SRC_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B200_GET_ICTL_INFO() throws IOException,SQLException,Exception {
        if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CONTRACT_TYPE_CLGU)) {
            IBS.init(SCCGWA, LNCRICTL);
            IBS.init(SCCGWA, LNRICTL);
            LNCRICTL.FUNC = 'I';
            LNRICTL.KEY.CONTRACT_NO = WS_LN_AC;
            LNRICTL.KEY.SUB_CTA_NO = 0;
            LNCRICTL.REC_PTR = LNRICTL;
            LNCRICTL.REC_LEN = 425;
            S000_CALL_SRC_LNZRICTL();
            if (pgmRtn) return;
        }
    }
    public void B300_CHECK_DATA() throws IOException,SQLException,Exception {
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CONTRACT_TYPE_CLGU) 
            && (LNCCLOAN.COMM_DATA.CHECK_TYP == K_ENTER 
            || LNCCLOAN.COMM_DATA.CHECK_TYP == K_ADD 
            || LNCCLOAN.COMM_DATA.CHECK_TYP == K_MODIFY 
            || LNCCLOAN.COMM_DATA.CHECK_TYP == K_UPDATE 
            || LNCCLOAN.COMM_DATA.CHECK_TYP == K_CONFIRM)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_REPAY_LGU_LMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CONTRACT_TYPE_CLGU)) {
            CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_TRUE_ID+"")) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SRC_LNZRCONT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRICTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
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
        if (LNCCLOAN.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCCLOAN=");
            CEP.TRC(SCCGWA, LNCCLOAN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
