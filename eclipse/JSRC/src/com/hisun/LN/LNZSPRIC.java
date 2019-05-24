package com.hisun.LN;

import com.hisun.SC.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPRIC {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    double WS_BAL = 0;
    char WS_INT_MODE_FLAG = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCSPRFC LNCSPRFC = new LNCSPRFC();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCSLNQ LNCSLNQ = new LNCSLNQ();
    LNCICUT LNCICUT = new LNCICUT();
    LNCICNQ LNCICNQ = new LNCICNQ();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    LNCSPRIC LNCSPRIC;
    public void MP(SCCGWA SCCGWA, LNCSPRIC LNCSPRIC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPRIC = LNCSPRIC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPRIC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        CEP.TRC(SCCGWA, LNCSPRIC.INT_MODE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_CUT_INT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        LNCSPRIC.RC.RC_APP = " ";
        LNCSPRIC.RC.RC_RTNCODE = 0;
        if (LNCSPRIC.FUN_CODE == 'I'
            || LNCSPRIC.FUN_CODE == 'U') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSPRIC.FUN_CODE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (LNCSPRIC.CTA.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            R000_CHECK_CTA_NO_VALID();
            if (pgmRtn) return;
        }
        if (LNCSPRIC.TOT_P_AMT != 0) {
            R000_CHECK_EXHIBIT_AMT();
            if (pgmRtn) return;
        }
        WS_INT_MODE_FLAG = LNCSPRIC.INT_MODE;
        if ((WS_INT_MODE_FLAG != '0' 
            && WS_INT_MODE_FLAG != '1' 
            && WS_INT_MODE_FLAG != '2')) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CUT_INT() throws IOException,SQLException,Exception {
        B030_01_INQ_ICTL();
        if (pgmRtn) return;
        B030_05_INQ_POST();
        if (pgmRtn) return;
        B030_10_INT_CUT();
        if (pgmRtn) return;
    }
    public void B030_01_INQ_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPRIC.CTA;
        if (LNCSPRIC.CTA_SEQ.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCSPRIC.CTA_SEQ);
        S000_CALL_LNZICTL();
        if (pgmRtn) return;
    }
    public void B030_05_INQ_POST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICNQ);
        LNCICNQ.COMM_DATA.INQ_CODE = "INQTMPI";
        LNCICNQ.COMM_DATA.CONTRACT_NO = LNCSPRIC.CTA;
        if (LNCSPRIC.CTA_SEQ.trim().length() == 0) LNCICNQ.COMM_DATA.SUB_CTA_NO = 0;
        else LNCICNQ.COMM_DATA.SUB_CTA_NO = Integer.parseInt(LNCSPRIC.CTA_SEQ);
    }
    public void B030_10_INT_CUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = LNCSPRIC.FUN_CODE;
        LNCICUT.COMM_DATA.LN_AC = LNCSPRIC.CTA;
        LNCICUT.COMM_DATA.SUF_NO = LNCSPRIC.CTA_SEQ;
        LNCICUT.COMM_DATA.TYPE = 'I';
        LNCICUT.COMM_DATA.BEG_DATE = LNCICTLM.REC_DATA.INT_CUT_DT;
        LNCICUT.COMM_DATA.END_DATE = LNCSPRIC.TR_VAL_DTE;
        LNCICUT.COMM_DATA.ADJ_INT = LNCSPRIC.ADJ_INT;
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.END_DATE);
        S000_CALL_LNZICUT();
        if (pgmRtn) return;
        if (WS_INT_MODE_FLAG == '0') {
            LNCSPRIC.TOT_I_AMT = 0;
        } else if (WS_INT_MODE_FLAG == '2') {
            if (WS_BAL == 0) {
                LNCSPRIC.TOT_I_AMT = LNCICUT.COMM_DATA.INT_AMT + LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_AMT;
            } else {
                LNCSPRIC.TOT_I_AMT = ( LNCICUT.COMM_DATA.SPE_INT + LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_AMT ) * LNCSPRIC.TOT_P_AMT / WS_BAL;
                bigD = new BigDecimal(LNCSPRIC.TOT_I_AMT);
                LNCSPRIC.TOT_I_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        } else if (WS_INT_MODE_FLAG == '1') {
            LNCSPRIC.TOT_I_AMT = LNCICUT.COMM_DATA.INT_AMT + LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_AMT;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID INT MODE(" + WS_INT_MODE_FLAG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        LNCSPRIC.TMP_INT = LNCICUT.COMM_DATA.INT_AMT + LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_AMT;
    }
    public void R000_CHECK_CTA_NO_VALID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCSLNQ.COMM_DATA.LN_AC = LNCSPRIC.CTA;
        LNCSLNQ.COMM_DATA.SUF_NO = LNCSPRIC.CTA_SEQ;
        LNCCLNQ.FUNC = '1';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        WS_BAL = LNCCLNQ.DATA.TOT_BAL;
    }
    public void R000_CHECK_EXHIBIT_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQUDUP";
        LNCCNEX.COMM_DATA.LN_AC = LNCSPRIC.CTA;
        LNCCNEX.COMM_DATA.SUF_NO = LNCSPRIC.CTA_SEQ;
        LNCCNEX.COMM_DATA.INQ_AMT = LNCSPRIC.TOT_P_AMT;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.INQ_AMT);
        CEP.TRC(SCCGWA, LNCSPRIC.TOT_P_AMT);
        if (LNCCNEX.COMM_DATA.INQ_AMT < LNCSPRIC.TOT_P_AMT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_GE_PAY_P_AMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111111111111");
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        CEP.TRC(SCCGWA, LNCCNEX.RC);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-LOAN-INQUIRY", LNCSLNQ);
        if (LNCSLNQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSLNQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CONT-INQ", LNCICNQ);
        if (LNCICNQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICNQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICUT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        if (LNCSPRIC.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSPRIC=");
            CEP.TRC(SCCGWA, LNCSPRIC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
