package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMOT8888 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM888";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    short WS_R = 0;
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRL CICQACRL = new CICQACRL();
    CMCSIQAC CMCSIQAC = new CMCSIQAC();
    CMCF888 CMCF888 = new CMCF888();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCMPAG SCCMPAG;
    SCCBATH SCCBATH;
    CMCS8888 CMCS8888;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMOT8888 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CMCS8888 = new CMCS8888();
        IBS.init(SCCGWA, CMCS8888);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, CMCS8888);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        IBS.init(SCCGWA, CMCF888);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B050_INPUT_CHECK();
        if (pgmRtn) return;
        B100_INQ_CI();
        if (pgmRtn) return;
    }
    public void B050_INPUT_CHECK() throws IOException,SQLException,Exception {
        if (CMCS8888.PAYER[1-1].ACCT_I.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_ERR);
        }
    }
    public void B100_INQ_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSIQAC);
        for (WS_I = 1; CMCS8888.PAYER[WS_I-1].ACCT_I.trim().length() != 0 
            && WS_I <= 20; WS_I += 1) {
            CEP.TRC(SCCGWA, CMCS8888.PAYER[WS_I-1].CCY);
            CEP.TRC(SCCGWA, CMCS8888.PAYER[WS_I-1].AC_SEQ);
            CEP.TRC(SCCGWA, CMCS8888.PAYER[WS_I-1].CCY_TYP);
            CMCSIQAC.I_CUS_AC = CMCS8888.PAYER[WS_I-1].ACCT_I;
            CMCSIQAC.I_FUNC = '3';
            CMCSIQAC.I_AC_SEQ = CMCS8888.PAYER[WS_I-1].AC_SEQ;
            CMCSIQAC.I_CCY = CMCS8888.PAYER[WS_I-1].CCY;
            CMCSIQAC.I_CCY_TYP = CMCS8888.PAYER[WS_I-1].CCY_TYP;
            S000_CALL_CMZSIQAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_TEL_NO);
            CMCF888.OUT[WS_I-1].ACCT_O = CMCSIQAC.OUT_INF.BAS_INF.BAS_CUS_AC;
            CMCF888.OUT[WS_I-1].CI_NO = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_NO;
            CMCF888.OUT[WS_I-1].NAME = CMCSIQAC.OUT_INF.BAS_INF.BAS_CPN_NM;
            CMCF888.OUT[WS_I-1].PAPER_NO = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_NO;
            CMCF888.OUT[WS_I-1].PHONE_NO = CMCSIQAC.OUT_INF.BAS_INF.BAS_TEL_NO;
            CMCF888.OUT[WS_I-1].AC_STS = "" + CMCSIQAC.OUT_INF.BAS_INF.BAS_ACO_STS;
            JIBS_tmp_int = CMCF888.OUT[WS_I-1].AC_STS.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) CMCF888.OUT[WS_I-1].AC_STS = "0" + CMCF888.OUT[WS_I-1].AC_STS;
            CMCF888.OUT[WS_I-1].STATUS = CMCSIQAC.OUT_INF.BAS_INF.BAS_ACO_STSW;
            CEP.TRC(SCCGWA, CMCF888.OUT[WS_I-1].ACCT_O);
            CEP.TRC(SCCGWA, CMCF888.OUT[WS_I-1].CI_NO);
            CEP.TRC(SCCGWA, CMCF888.OUT[WS_I-1].NAME);
            CEP.TRC(SCCGWA, CMCF888.OUT[WS_I-1].PAPER_NO);
            CEP.TRC(SCCGWA, CMCF888.OUT[WS_I-1].PHONE_NO);
            CEP.TRC(SCCGWA, CMCF888.OUT[WS_I-1].AC_STS);
            CEP.TRC(SCCGWA, CMCF888.OUT[WS_I-1].STATUS);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        SCCFMT.DATA_PTR = CMCF888;
        SCCFMT.DATA_LEN = 3560;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CMZSIQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-INQ-CUS-AC", CMCSIQAC);
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
