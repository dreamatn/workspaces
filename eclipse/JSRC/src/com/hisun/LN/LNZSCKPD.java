package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSCKPD {
    LNZSCKPD_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZSCKPD_WS_TEMP_VARIABLE();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCCTLPM LNCCTLPM = new LNCCTLPM();
    BPCPCKPD BPCPCKPD = new BPCPCKPD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    LNCSCKPD LNCSCKPD;
    public void MP(SCCGWA SCCGWA, LNCSCKPD LNCSCKPD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSCKPD = LNCSCKPD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNZSCKPD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSCKPD.FUNC);
        if (LNCSCKPD.FUNC == '0') {
            B100_INQ_PROD();
        } else if (LNCSCKPD.FUNC == '1') {
            B200_CHK_PROD();
        } else {
            CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE);
        }
    }
    public void B100_INQ_PROD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = LNCSCKPD.PROD_CD;
        S000_CALL_BPZPQPRD();
        IBS.init(SCCGWA, LNCCTLPM);
        LNCCTLPM.FUNC = 'I';
        LNCCTLPM.KEY.CD = BPCPQPRD.PARM_CODE;
        LNCCTLPM.KEY.TYP = "PRDPR";
        S000_CALL_LNZCTLPM();
        B300_OUTPUT();
    }
    public void B200_CHK_PROD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCKPD);
        BPCPCKPD.PRDT_CODE = LNCSCKPD.PROD_CD;
        S000_CALL_BPZPCKPD();
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = LNCSCKPD.PROD_CD;
        S000_CALL_BPZPQPRD();
        IBS.init(SCCGWA, LNCCTLPM);
        LNCCTLPM.FUNC = 'I';
        LNCCTLPM.KEY.CD = BPCPQPRD.PARM_CODE;
        LNCCTLPM.KEY.TYP = "PRDPR";
        S000_CALL_LNZCTLPM();
        B210_CHK_INPUT();
        B300_OUTPUT();
    }
    public void B210_CHK_INPUT() throws IOException,SQLException,Exception {
        if (LNCSCKPD.DATE != 0) {
            if (LNCSCKPD.DATE < LNCCTLPM.EFF_DATE 
                || LNCSCKPD.DATE > LNCCTLPM.EXP_DATE) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.PRODMO.trim().length() > 0) {
            if (!LNCSCKPD.PRODMO.equalsIgnoreCase(LNCCTLPM.DATA_TXT.PRODMO)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.PROD_MOD != ' ') {
            if (LNCSCKPD.PROD_MOD != LNCCTLPM.DATA_TXT.PROD_MOD) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.PROD_CLS.trim().length() > 0) {
            if (!LNCSCKPD.PROD_CLS.equalsIgnoreCase(LNCCTLPM.DATA_TXT.PROD_CLS)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.CCY_CPY.trim().length() > 0) {
            if (!LNCSCKPD.CCY_CPY.equalsIgnoreCase(LNCCTLPM.DATA_TXT.CCY_CPY)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.DD != 0) {
            if (LNCSCKPD.DD > LNCCTLPM.DATA_TXT.MAX_DD 
                || LNCSCKPD.DD < LNCCTLPM.DATA_TXT.MIN_DD) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.ACCRUAL_TYPE != ' ') {
            if (LNCSCKPD.ACCRUAL_TYPE != LNCCTLPM.DATA_TXT.ACCRUAL_TYPE) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.CM_TYP != ' ') {
            if (LNCSCKPD.CM_TYP != LNCCTLPM.DATA_TXT.CM_TYP) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.BSRT_0 != ' ') {
            if (LNCSCKPD.BSRT_0 != LNCCTLPM.DATA_TXT.BSRT_0) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.BSRT_1 != ' ') {
            if (LNCSCKPD.BSRT_1 != LNCCTLPM.DATA_TXT.BSRT_1) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.BSRT_2 != ' ') {
            if (LNCSCKPD.BSRT_2 != LNCCTLPM.DATA_TXT.BSRT_2) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.BSRT_3 != ' ') {
            if (LNCSCKPD.BSRT_3 != LNCCTLPM.DATA_TXT.BSRT_3) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_TYP != ' ') {
            if (LNCSCKPD.RT_TYP != LNCCTLPM.DATA_TXT.RT_TYP) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_M060.trim().length() > 0) {
            if (!LNCSCKPD.RT_M060.equalsIgnoreCase(LNCCTLPM.DATA_TXT.RT_M060)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_M120.trim().length() > 0) {
            if (!LNCSCKPD.RT_M120.equalsIgnoreCase(LNCCTLPM.DATA_TXT.RT_M120)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_Y103.trim().length() > 0) {
            if (!LNCSCKPD.RT_Y103.equalsIgnoreCase(LNCCTLPM.DATA_TXT.RT_Y103)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_Y305.trim().length() > 0) {
            if (!LNCSCKPD.RT_Y305.equalsIgnoreCase(LNCCTLPM.DATA_TXT.RT_Y305)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_Y050.trim().length() > 0) {
            if (!LNCSCKPD.RT_Y050.equalsIgnoreCase(LNCCTLPM.DATA_TXT.RT_Y050)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_Y500.trim().length() > 0) {
            if (!LNCSCKPD.RT_Y500.equalsIgnoreCase(LNCCTLPM.DATA_TXT.RT_Y500)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RAT_0 != ' ') {
            if (LNCSCKPD.RAT_0 != LNCCTLPM.DATA_TXT.RAT_0) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RAT_1 != ' ') {
            if (LNCSCKPD.RAT_1 != LNCCTLPM.DATA_TXT.RAT_1) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RAT_2 != ' ') {
            if (LNCSCKPD.RAT_2 != LNCCTLPM.DATA_TXT.RAT_2) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RAT_3 != ' ') {
            if (LNCSCKPD.RAT_3 != LNCCTLPM.DATA_TXT.RAT_3) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.CFT_FLG != ' ') {
            if (LNCSCKPD.CFT_FLG != LNCCTLPM.DATA_TXT.CFT_FLG) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_B != ' ') {
            if (LNCSCKPD.RT_B != LNCCTLPM.DATA_TXT.RT_B) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_D != ' ') {
            if (LNCSCKPD.RT_D != LNCCTLPM.DATA_TXT.RT_D) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_H != ' ') {
            if (LNCSCKPD.RT_H != LNCCTLPM.DATA_TXT.RT_H) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_M != ' ') {
            if (LNCSCKPD.RT_M != LNCCTLPM.DATA_TXT.RT_M) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_Q != ' ') {
            if (LNCSCKPD.RT_Q != LNCCTLPM.DATA_TXT.RT_Q) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_W != ' ') {
            if (LNCSCKPD.RT_W != LNCCTLPM.DATA_TXT.RT_W) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_Y != ' ') {
            if (LNCSCKPD.RT_Y != LNCCTLPM.DATA_TXT.RT_Y) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_PERD != ' ') {
            if (LNCSCKPD.RT_PERD != LNCCTLPM.DATA_TXT.RT_PERD) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_MTH0 != ' ') {
            if (LNCSCKPD.RT_MTH0 != LNCCTLPM.DATA_TXT.RT_MTH0) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_MTH1 != ' ') {
            if (LNCSCKPD.RT_MTH1 != LNCCTLPM.DATA_TXT.RT_MTH1) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_MTH2 != ' ') {
            if (LNCSCKPD.RT_MTH2 != LNCCTLPM.DATA_TXT.RT_MTH2) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_MTH3 != ' ') {
            if (LNCSCKPD.RT_MTH3 != LNCCTLPM.DATA_TXT.RT_MTH3) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.RT_MTH4 != ' ') {
            if (LNCSCKPD.RT_MTH4 != LNCCTLPM.DATA_TXT.RT_MTH4) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPYMTH0 != ' ') {
            if (LNCSCKPD.REPYMTH0 != LNCCTLPM.DATA_TXT.REPYMTH0) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPYMTH1 != ' ') {
            if (LNCSCKPD.REPYMTH1 != LNCCTLPM.DATA_TXT.REPYMTH1) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPYMTH2 != ' ') {
            if (LNCSCKPD.REPYMTH2 != LNCCTLPM.DATA_TXT.REPYMTH2) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPYMTH3 != ' ') {
            if (LNCSCKPD.REPYMTH3 != LNCCTLPM.DATA_TXT.REPYMTH3) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPYMTH4 != ' ') {
            if (LNCSCKPD.REPYMTH4 != LNCCTLPM.DATA_TXT.REPYMTH4) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPYMTH5 != ' ') {
            if (LNCSCKPD.REPYMTH5 != LNCCTLPM.DATA_TXT.REPYMTH5) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPY_CO1 != ' ') {
            if (LNCSCKPD.REPY_CO1 != LNCCTLPM.DATA_TXT.REPY_CO1) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPY_CT1.trim().length() > 0) {
            if (!LNCSCKPD.REPY_CT1.equalsIgnoreCase(LNCCTLPM.DATA_TXT.REPY_CT1)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPY_CO2 != ' ') {
            if (LNCSCKPD.REPY_CO2 != LNCCTLPM.DATA_TXT.REPY_CO2) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPY_CT2.trim().length() > 0) {
            if (!LNCSCKPD.REPY_CT2.equalsIgnoreCase(LNCCTLPM.DATA_TXT.REPY_CT2)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPY_CO3 != ' ') {
            if (LNCSCKPD.REPY_CO3 != LNCCTLPM.DATA_TXT.REPY_CO3) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.REPY_CT3.trim().length() > 0) {
            if (!LNCSCKPD.REPY_CT3.equalsIgnoreCase(LNCCTLPM.DATA_TXT.REPY_CT3)) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.PAY_LEVL != ' ') {
            if (LNCSCKPD.PAY_LEVL != LNCCTLPM.DATA_TXT.PAY_LEVL) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.BLN_FLG != ' ') {
            if (LNCSCKPD.BLN_FLG != LNCCTLPM.DATA_TXT.BLN_FLG) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.BLN_PP != ' ') {
            if (LNCSCKPD.BLN_PP != LNCCTLPM.DATA_TXT.BLN_PP) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.VCT_FLG != ' ') {
            if (LNCSCKPD.VCT_FLG != LNCCTLPM.DATA_TXT.VCT_FLG) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.GRA_D != ' ') {
            if (LNCSCKPD.GRA_D != LNCCTLPM.DATA_TXT.GRA_D) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.PGRAMT != ' ') {
            if (LNCSCKPD.PGRAMT != LNCCTLPM.DATA_TXT.PGRAMT) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.CGRAMT != ' ') {
            if (LNCSCKPD.CGRAMT != LNCCTLPM.DATA_TXT.CGRAMT) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.MAR_FLG != ' ') {
            if (LNCSCKPD.MAR_FLG != LNCCTLPM.DATA_TXT.MAR_FLG) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
        if (LNCSCKPD.MAR_MTH != ' ') {
            if (LNCSCKPD.MAR_MTH != LNCCTLPM.DATA_TXT.MAR_MTH) {
                CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
            }
        }
    }
    public void B300_OUTPUT() throws IOException,SQLException,Exception {
        LNCSCKPD.PROD_CDB = BPCPQPRD.PARM_CODE;
        LNCSCKPD.PROD_NM = BPCPQPRD.PRDT_NAME;
        LNCSCKPD.EXP_DATE = BPCPQPRD.EXP_DATE;
        LNCSCKPD.PRODMO = LNCCTLPM.DATA_TXT.PRODMO;
        LNCSCKPD.PROD_MOD = LNCCTLPM.DATA_TXT.PROD_MOD;
        LNCSCKPD.PROD_CLS = LNCCTLPM.DATA_TXT.PROD_CLS;
        LNCSCKPD.CCY_CPY = LNCCTLPM.DATA_TXT.CCY_CPY;
        LNCSCKPD.ACCRUAL_TYPE = LNCCTLPM.DATA_TXT.ACCRUAL_TYPE;
        LNCSCKPD.CM_TYP = LNCCTLPM.DATA_TXT.CM_TYP;
        LNCSCKPD.BSRT_0 = LNCCTLPM.DATA_TXT.BSRT_0;
        LNCSCKPD.BSRT_1 = LNCCTLPM.DATA_TXT.BSRT_1;
        LNCSCKPD.BSRT_2 = LNCCTLPM.DATA_TXT.BSRT_2;
        LNCSCKPD.BSRT_3 = LNCCTLPM.DATA_TXT.BSRT_3;
        LNCSCKPD.RT_TYP = LNCCTLPM.DATA_TXT.RT_TYP;
        LNCSCKPD.RT_M060 = LNCCTLPM.DATA_TXT.RT_M060;
        LNCSCKPD.RT_M120 = LNCCTLPM.DATA_TXT.RT_M120;
        LNCSCKPD.RT_Y103 = LNCCTLPM.DATA_TXT.RT_Y103;
        LNCSCKPD.RT_Y305 = LNCCTLPM.DATA_TXT.RT_Y305;
        LNCSCKPD.RT_Y050 = LNCCTLPM.DATA_TXT.RT_Y050;
        LNCSCKPD.RT_Y500 = LNCCTLPM.DATA_TXT.RT_Y500;
        LNCSCKPD.RAT_0 = LNCCTLPM.DATA_TXT.RAT_0;
        LNCSCKPD.RAT_1 = LNCCTLPM.DATA_TXT.RAT_1;
        LNCSCKPD.RAT_2 = LNCCTLPM.DATA_TXT.RAT_2;
        LNCSCKPD.RAT_3 = LNCCTLPM.DATA_TXT.RAT_3;
        LNCSCKPD.CFT_FLG = LNCCTLPM.DATA_TXT.CFT_FLG;
        LNCSCKPD.RT_B = LNCCTLPM.DATA_TXT.RT_B;
        LNCSCKPD.RT_D = LNCCTLPM.DATA_TXT.RT_D;
        LNCSCKPD.RT_H = LNCCTLPM.DATA_TXT.RT_H;
        LNCSCKPD.RT_M = LNCCTLPM.DATA_TXT.RT_M;
        LNCSCKPD.RT_Q = LNCCTLPM.DATA_TXT.RT_Q;
        LNCSCKPD.RT_W = LNCCTLPM.DATA_TXT.RT_W;
        LNCSCKPD.RT_Y = LNCCTLPM.DATA_TXT.RT_Y;
        LNCSCKPD.RT_PERD = LNCCTLPM.DATA_TXT.RT_PERD;
        LNCSCKPD.RT_MTH0 = LNCCTLPM.DATA_TXT.RT_MTH0;
        LNCSCKPD.RT_MTH1 = LNCCTLPM.DATA_TXT.RT_MTH1;
        LNCSCKPD.RT_MTH2 = LNCCTLPM.DATA_TXT.RT_MTH2;
        LNCSCKPD.RT_MTH3 = LNCCTLPM.DATA_TXT.RT_MTH3;
        LNCSCKPD.RT_MTH4 = LNCCTLPM.DATA_TXT.RT_MTH4;
        LNCSCKPD.REPYMTH0 = LNCCTLPM.DATA_TXT.REPYMTH0;
        LNCSCKPD.REPYMTH1 = LNCCTLPM.DATA_TXT.REPYMTH1;
        LNCSCKPD.REPYMTH2 = LNCCTLPM.DATA_TXT.REPYMTH2;
        LNCSCKPD.REPYMTH3 = LNCCTLPM.DATA_TXT.REPYMTH3;
        LNCSCKPD.REPYMTH4 = LNCCTLPM.DATA_TXT.REPYMTH4;
        LNCSCKPD.REPYMTH5 = LNCCTLPM.DATA_TXT.REPYMTH5;
        LNCSCKPD.REPY_CO1 = LNCCTLPM.DATA_TXT.REPY_CO1;
        LNCSCKPD.REPY_CT1 = LNCCTLPM.DATA_TXT.REPY_CT1;
        LNCSCKPD.REPY_CO2 = LNCCTLPM.DATA_TXT.REPY_CO2;
        LNCSCKPD.REPY_CT2 = LNCCTLPM.DATA_TXT.REPY_CT2;
        LNCSCKPD.REPY_CO3 = LNCCTLPM.DATA_TXT.REPY_CO3;
        LNCSCKPD.REPY_CT3 = LNCCTLPM.DATA_TXT.REPY_CT3;
        LNCSCKPD.PAY_LEVL = LNCCTLPM.DATA_TXT.PAY_LEVL;
        LNCSCKPD.BLN_FLG = LNCCTLPM.DATA_TXT.BLN_FLG;
        LNCSCKPD.BLN_PP = LNCCTLPM.DATA_TXT.BLN_PP;
        LNCSCKPD.VCT_FLG = LNCCTLPM.DATA_TXT.VCT_FLG;
        LNCSCKPD.GRA_D = LNCCTLPM.DATA_TXT.GRA_D;
        LNCSCKPD.PGRAMT = LNCCTLPM.DATA_TXT.PGRAMT;
        LNCSCKPD.CGRAMT = LNCCTLPM.DATA_TXT.CGRAMT;
        LNCSCKPD.MAR_FLG = LNCCTLPM.DATA_TXT.MAR_FLG;
        LNCSCKPD.MAR_MTH = LNCCTLPM.DATA_TXT.MAR_MTH;
    }
    public void S000_CALL_BPZPCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-PRDT-COM-CHECK", BPCPCKPD);
        if (BPCPCKPD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPCKPD.RC);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQPRD.RC);
        }
    }
    public void S000_CALL_LNZCTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CTLPM-MAINT", LNCCTLPM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
