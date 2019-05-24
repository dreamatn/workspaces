package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPRFC {
    BigDecimal bigD;
    DBParm LNTAPRD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_BASDAYS = 0;
    int WS_FEE_DAYS = 0;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCSLNQ LNCSLNQ = new LNCSLNQ();
    LNRAPRD LNRAPRD = new LNRAPRD();
    BPCIDAY BPCIDAY = new BPCIDAY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    LNCSPRFC LNCSPRFC;
    public void MP(SCCGWA SCCGWA, LNCSPRFC LNCSPRFC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPRFC = LNCSPRFC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPRFC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B050_COMPUTE_FEE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        LNCSPRFC.RC.RC_APP = " ";
        LNCSPRFC.RC.RC_RTNCODE = 0;
        if (LNCSPRFC.TR_VAL_DTE == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPRFC.FEE_TYPE == 'P') {
            if (LNCSPRFC.PC_RATE == 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (LNCSPRFC.FEE_TYPE == 'B') {
            if (LNCSPRFC.BFC_RATE == 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FEE TYPE(" + LNCSPRFC.FEE_TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (LNCSPRFC.CTA.trim().length() == 0 
            && LNCSPRFC.FEE_TYPE == 'B') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            R000_CHECK_CTA_NO_VALID();
            if (pgmRtn) return;
        }
        if (LNCSPRFC.TOT_P_AMT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_COMPUTE_FEE() throws IOException,SQLException,Exception {
        if (LNCSPRFC.FEE_TYPE == 'B') {
            IBS.init(SCCGWA, LNRAPRD);
            LNRAPRD.KEY.CONTRACT_NO = LNCSPRFC.CTA;
            T000_READ_APRD_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRAPRD.PAY_MTH);
            LNCSPRFC.BFC_CALR = LNRAPRD.INT_DBAS_STD;
        }
        if (LNCSPRFC.FEE_TYPE == 'P') {
            B050_01_COMPUTE_PFE();
            if (pgmRtn) return;
        } else if (LNCSPRFC.FEE_TYPE == 'B') {
            B050_05_COMPUTE_BFE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FEE TYPE(" + LNCSPRFC.FEE_TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B050_01_COMPUTE_PFE() throws IOException,SQLException,Exception {
        LNCSPRFC.PC_AMT = LNCSPRFC.TOT_P_AMT * LNCSPRFC.PC_RATE / 100;
        bigD = new BigDecimal(LNCSPRFC.PC_AMT);
        LNCSPRFC.PC_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        CEP.TRC(SCCGWA, LNCSPRFC.PC_AMT);
    }
    public void B050_05_COMPUTE_BFE() throws IOException,SQLException,Exception {
        B050_05_01_INQ_ICTL();
        if (pgmRtn) return;
        B050_05_03_COMPUTE_BRK_DAY();
        if (pgmRtn) return;
        B050_05_05_GET_FEE();
        if (pgmRtn) return;
    }
    public void B050_05_01_INQ_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPRFC.CTA;
        S000_CALL_LNZICTL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.INT_CUT_DT);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_DUE_DT);
        CEP.TRC(SCCGWA, LNCSPRFC.TR_VAL_DTE);
        if (LNCSPRFC.TR_VAL_DTE < LNCICTLM.REC_DATA.INT_CUT_DT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_05_03_COMPUTE_BRK_DAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIDAY);
        CEP.TRC(SCCGWA, LNCSPRFC.TR_VAL_DTE);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_VAL_DT);
        BPCIDAY.I_CALR_STD = LNCSPRFC.BFC_CALR;
        BPCIDAY.I_BEGIN_DATE = LNCSPRFC.TR_VAL_DTE;
        BPCIDAY.I_END_DATE = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        S000_CALL_BPZCIDAY();
        if (pgmRtn) return;
        WS_BASDAYS = BPCIDAY.OUTPUT.O_STD_DAYS;
        WS_FEE_DAYS = BPCIDAY.OUTPUT.O_ORD_DAYS + BPCIDAY.OUTPUT.O_LEAP_DAYS;
        CEP.TRC(SCCGWA, WS_FEE_DAYS);
        CEP.TRC(SCCGWA, WS_BASDAYS);
    }
    public void B050_05_05_GET_FEE() throws IOException,SQLException,Exception {
        LNCSPRFC.BFC_AMT = LNCSPRFC.TOT_P_AMT * LNCSPRFC.BFC_RATE * WS_FEE_DAYS / ( WS_BASDAYS * 100 );
        bigD = new BigDecimal(LNCSPRFC.BFC_AMT);
        LNCSPRFC.BFC_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        CEP.TRC(SCCGWA, LNCSPRFC.BFC_AMT);
    }
    public void R000_CHECK_CTA_NO_VALID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSLNQ);
        LNCSLNQ.COMM_DATA.LN_AC = LNCSPRFC.CTA;
        S000_CALL_LNZSLNQ();
        if (pgmRtn) return;
    }
    public void T000_READ_APRD_PROC() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND;
            Z_RET();
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
    public void S000_CALL_LNZICTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCIDAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-CALC-INT-DAYS", BPCIDAY);
        if (BPCIDAY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCIDAY.RC);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
