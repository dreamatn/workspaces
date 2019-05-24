package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSERHD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC759";
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCUUPAY DDCUUPAY = new DDCUUPAY();
    DCCOERHD DCCOERHD = new DCCOERHD();
    SCCBINF SCCBINF = new SCCBINF();
    SCCGWA SCCGWA;
    DCCSERHD DCCSERHD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSERHD DCCSERHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSERHD = DCCSERHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSERHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_AC_REL_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSERHD.DATA.DD_AC);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.CCY);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.CCY_TYP);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.AMT);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.RSN);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.RMK);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.LNCNTR_NO);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.LNTX_SEQ);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.LMTX_SEQ);
        if (DCCSERHD.DATA.DD_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSERHD.DATA.LNCNTR_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CNTR_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSERHD.DATA.LNTX_SEQ == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LNTX_SEQ_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSERHD.DATA.LMTX_SEQ == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LMTX_SEQ_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_AC_REL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUUPAY);
        DDCUUPAY.FUNC = '7';
        DDCUUPAY.DD_AC = DCCSERHD.DATA.DD_AC;
        DDCUUPAY.CCY = DCCSERHD.DATA.CCY;
        DDCUUPAY.CCY_TYP = DCCSERHD.DATA.CCY_TYP;
        DDCUUPAY.ACMT_AMT = DCCSERHD.DATA.AMT;
        DDCUUPAY.LNCNTR_NO = DCCSERHD.DATA.LNCNTR_NO;
        DDCUUPAY.LNTX_SEQ = DCCSERHD.DATA.LNTX_SEQ;
        DDCUUPAY.LMTX_SEQ = DCCSERHD.DATA.LMTX_SEQ;
        S000_CALL_DDZUUPAY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUUPAY.PROL_NO);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.DD_AC);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.CCY);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.CCY_TYP);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.AMT);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.RSN);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.RMK);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.LNCNTR_NO);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.LNTX_SEQ);
        CEP.TRC(SCCGWA, DCCSERHD.DATA.LMTX_SEQ);
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOERHD);
        DCCOERHD.DATA.DD_AC = DDCUUPAY.DD_AC;
        DCCOERHD.DATA.CCY = DDCUUPAY.CCY;
        DCCOERHD.DATA.CCY_TYP = DDCUUPAY.CCY_TYP;
        DCCOERHD.DATA.AMT = DDCUUPAY.ACMT_AMT;
        DCCOERHD.DATA.RSN = DCCSERHD.DATA.RSN;
        DCCOERHD.DATA.RMK = DCCSERHD.DATA.RMK;
        DCCOERHD.DATA.LNCNTR_NO = DDCUUPAY.LNCNTR_NO;
        DCCOERHD.DATA.LNTX_SEQ = (short) DDCUUPAY.LNTX_SEQ;
        DCCOERHD.DATA.LMTX_SEQ = DDCUUPAY.LMTX_SEQ;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCOERHD;
        SCCFMT.DATA_LEN = 331;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, DCCOERHD.DATA.DD_AC);
        CEP.TRC(SCCGWA, DCCOERHD.DATA.CCY);
        CEP.TRC(SCCGWA, DCCOERHD.DATA.CCY_TYP);
        CEP.TRC(SCCGWA, DCCOERHD.DATA.AMT);
        CEP.TRC(SCCGWA, DCCOERHD.DATA.RSN);
        CEP.TRC(SCCGWA, DCCOERHD.DATA.RMK);
    }
    public void S000_CALL_DDZUUPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-MOD-UPAY", DDCUUPAY);
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
