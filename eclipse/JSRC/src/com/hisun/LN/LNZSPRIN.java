package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPRIN {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_PRINCIPAL = 'P';
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    double WS_TOT_IPLN_AMT = 0;
    double WS_TOT_REPY_AMT = 0;
    LNZSPRIN_WS_OUTPUT_DATA WS_OUTPUT_DATA = new LNZSPRIN_WS_OUTPUT_DATA();
    LNZSPRIN_WS_MSG_INFO WS_MSG_INFO = new LNZSPRIN_WS_MSG_INFO();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCRPYIF LNCRPYIF = new LNCRPYIF();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNRPYIF LNRPYIF = new LNRPYIF();
    LNRPLPI LNRPLPI = new LNRPLPI();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    SCCGWA SCCGWA;
    LNCSPRIN LNCSPRIN;
    public void MP(SCCGWA SCCGWA, LNCSPRIN LNCSPRIN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPRIN = LNCSPRIN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPRIN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        if (LNCSPRIN.COMM_DATA.CTA_NO.compareTo(SPACE) <= 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B1000_SUMUP_PLPI();
        if (pgmRtn) return;
        B2000_SUMUP_PYIF();
        if (pgmRtn) return;
        LNCSPRIN.COMM_DATA.P_TOT_IPLN_AMT = WS_TOT_IPLN_AMT + WS_TOT_REPY_AMT;
    }
    public void B1000_SUMUP_PLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        IBS.init(SCCGWA, LNCRPLPI);
        LNCRPLPI.FUNC = 'S';
        LNRPLPI.KEY.CONTRACT_NO = LNCSPRIN.COMM_DATA.CTA_NO;
        if (LNCSPRIN.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCSPRIN.COMM_DATA.SUF_NO);
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
        WS_TOT_IPLN_AMT = LNCRPLPI.TOT_SUM;
    }
    public void B2000_SUMUP_PYIF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPYIF);
        IBS.init(SCCGWA, LNCRPYIF);
        LNCRPYIF.FUNC = 'G';
        LNRPYIF.KEY.CONTRACT_NO = LNCSPRIN.COMM_DATA.CTA_NO;
        if (LNCSPRIN.COMM_DATA.SUF_NO.trim().length() == 0) LNRPYIF.KEY.SUB_CTA_NO = 0;
        else LNRPYIF.KEY.SUB_CTA_NO = Integer.parseInt(LNCSPRIN.COMM_DATA.SUF_NO);
        LNCRPYIF.REC_PTR = LNRPYIF;
        LNCRPYIF.REC_LEN = 232;
        S000_CALL_SRC_LNZRPYIF();
        if (pgmRtn) return;
        WS_TOT_REPY_AMT = LNCRPYIF.TOT_SUM;
    }
    public void S000_CALL_SRC_LNZRPLPI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRPYIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPYIF", LNCRPYIF);
        CEP.TRC(SCCGWA, LNCRPYIF.RC);
        if (LNCRPYIF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPYIF.RC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
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
        if (LNCSPRIN.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSPRIN=");
            CEP.TRC(SCCGWA, LNCSPRIN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
