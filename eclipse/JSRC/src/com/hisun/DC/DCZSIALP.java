package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.LN.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSIALP {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DCTDDLN_RD;
    DBParm DDTCCY_RD;
    brParm DCTDDLN_BR = new brParm();
    boolean pgmRtn = false;
    String K_PRDPR_TYPE = "PRDPR";
    String K_OUT_FMT = "DC941";
    String K_HIS_CPB_NM = "DCCILPRD";
    String K_HIS_RMKS = "IR(DEPOSIT FOR LOANS) PRD PARM MAINTENANCE";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    String WS_DD_AC = " ";
    String WS_LN_CI = " ";
    String WS_DD_ACAC = " ";
    String WS_LN_ACAC = " ";
    String WS_REL_AC = " ";
    String WS_CCY = " ";
    char WS_CCY_TYPE = ' ';
    char WS_AC_CCY_TYPE = ' ';
    String WS_AC_CCY = " ";
    String WS_AC_DD = " ";
    String WS_AC_LN = " ";
    char WS_LN_TYP = ' ';
    String WS_DD_Z_AC = " ";
    String WS_Z_CCY = " ";
    char WS_CCY_TYP = ' ';
    String WS_TMP_LN_AC = " ";
    String WS_TMP_DD_AC = " ";
    String WS_TMP_CCY = " ";
    char WS_TMP_CCY_TYP = ' ';
    String WS_CI_NO = " ";
    char WS_CARD_FEE_FLG = ' ';
    char WS_CCY_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCUMATP DCCUMATP = new DCCUMATP();
    DCCLPRDO DCCLPRDO = new DCCLPRDO();
    DCCIRPRD DCCIRPRD = new DCCIRPRD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    LNRCTLPM LNRCTLPM = new LNRCTLPM();
    DCCSIRLN DCCSIRLN = new DCCSIRLN();
    DCCUGBAL DCCUGBAL = new DCCUGBAL();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DDCSABLH DDCSABLH = new DDCSABLH();
    LNCSTSW LNCSTSW = new LNCSTSW();
    CICQACAC CICDACAC = new CICQACAC();
    DDRCCY DDRCCY = new DDRCCY();
    LNCSIQIF LNCSIQIF = new LNCSIQIF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICUAGT CICUAGT = new CICUAGT();
    CICMAGT CICMAGT = new CICMAGT();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    DDCIMCYY DDCIMCYY = new DDCIMCYY();
    DCRIRPR DCRIRPR = new DCRIRPR();
    String WS_SQL_AC = " ";
    int WS_SQL_DT = 0;
    DCRDDLN DCRDDLN = new DCRDDLN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSIALP DCCSIALP;
    public void MP(SCCGWA SCCGWA, DCCSIALP DCCSIALP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSIALP = DCCSIALP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSIALP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        A001_TMPL_PROCESS();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************INPUT************");
        CEP.TRC(SCCGWA, DCCSIALP.FUNC);
        CEP.TRC(SCCGWA, DCCSIALP.KEY.PROD_CD);
        CEP.TRC(SCCGWA, DCCSIALP.PROD_NM);
        CEP.TRC(SCCGWA, DCCSIALP.LN_AC);
        CEP.TRC(SCCGWA, DCCSIALP.DD_AC);
        CEP.TRC(SCCGWA, DCCSIALP.CCY);
        CEP.TRC(SCCGWA, DCCSIALP.CCY_TYP);
        CEP.TRC(SCCGWA, DCCSIALP.LN_TYP);
        CEP.TRC(SCCGWA, DCCSIALP.ADP_TYP);
        CEP.TRC(SCCGWA, DCCSIALP.DED_MTH);
        CEP.TRC(SCCGWA, DCCSIALP.D_MN_AMT);
        CEP.TRC(SCCGWA, DCCSIALP.D_MX_AMT);
        CEP.TRC(SCCGWA, DCCSIALP.DD_D_PER);
        CEP.TRC(SCCGWA, DCCSIALP.LN_D_PER);
        CEP.TRC(SCCGWA, DCCSIALP.C_ED_PER);
        CEP.TRC(SCCGWA, DCCSIALP.T_ED_PER);
        CEP.TRC(SCCGWA, DCCSIALP.STRAMT);
        CEP.TRC(SCCGWA, DCCSIALP.STRDT);
        CEP.TRC(SCCGWA, DCCSIALP.EXPDT);
        if (DCCSIALP.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (DCCSIALP.FUNC == 'A') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B033_GET_DD_AC_PROC();
            if (pgmRtn) return;
            B011_GET_LN_AC_PROC();
            if (pgmRtn) return;
            B012_GET_PROD_INFO();
            if (pgmRtn) return;
            B031_CHECK_EXPD_PROCL();
            if (pgmRtn) return;
            B020_CHECK_DD_ACSTSW();
            if (pgmRtn) return;
            B030_CHECK_BAL_PROC();
            if (pgmRtn) return;
            B040_GET_CI_AGT_PROC();
            if (pgmRtn) return;
            B050_ADD_PROCESS();
            if (pgmRtn) return;
            B060_SET_AC_STSW_PROC();
            if (pgmRtn) return;
            B061_ADD_BALH_PROC();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSIALP.FUNC == 'M') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B070_MODIFY_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSIALP.FUNC == 'D') {
            B001_CHECK_DEL_DATA();
            if (pgmRtn) return;
            B090_DELETE_PROCESS();
            if (pgmRtn) return;
            B033_GET_DD_AC_PROC();
            if (pgmRtn) return;
            B032_CAN_CI_AGT_PROC();
            if (pgmRtn) return;
            B031_CAN_AC_STSW_PROC();
            if (pgmRtn) return;
            if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                B110_WRITE_HIS_PROC();
                if (pgmRtn) return;
            }
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DCCSIALP.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B001_CHECK_DEL_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSIALP.LN_AC);
        CEP.TRC(SCCGWA, DCCSIALP.DD_AC);
        CEP.TRC(SCCGWA, DCCSIALP.CCY);
        CEP.TRC(SCCGWA, DCCSIALP.CCY_TYP);
        CEP.TRC(SCCGWA, DCCSIALP.LN_TYP);
        if (DCCSIALP.LN_AC.trim().length() == 0) {
            CEP.TRC(SCCGWA, "111111111");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_AC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.LN_TYP != ' ' 
            && DCCSIALP.LN_TYP != '0' 
            && DCCSIALP.LN_TYP != '1' 
            && DCCSIALP.LN_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TMP_LN_AC = DCCSIALP.LN_AC;
        WS_LN_TYP = DCCSIALP.LN_TYP;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSIALP.LN_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_AC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.DD_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.CCY_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.CCY_TYP != '1' 
            && DCCSIALP.CCY_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.ADP_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ADP_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.ADP_TYP != '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_TYP_NOT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.DED_MTH == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DED_MTH_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.DED_MTH != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DED_MTH_NOT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.C_ED_PER == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_C_PER_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.T_ED_PER == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_T_PER_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.STRDT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_STRDT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.EXPDT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EXPDT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.EXPDT <= DCCSIALP.STRDT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_MUST_LT_EXP_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIALP.FUNC == 'A') {
            if (DCCSIALP.STRDT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_DT_GT_AC_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_TMP_LN_AC = DCCSIALP.LN_AC;
        WS_TMP_DD_AC = DCCSIALP.DD_AC;
        WS_TMP_CCY = DCCSIALP.CCY;
        WS_TMP_CCY_TYP = DCCSIALP.CCY_TYP;
    }
    public void B012_GET_PROD_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSIALP.KEY.PROD_CD);
        if (DCCSIALP.KEY.PROD_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = DCCSIALP.KEY.PROD_CD;
            CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
            if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID, BPCPQPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DCCSIRLN);
        IBS.init(SCCGWA, DCCIRPRD);
        DCCSIRLN.FUNC = 'Q';
        DCCSIRLN.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCSIRLN.DATA.KEY.PRDMO_CD = "IRLN";
        DCCSIRLN.DATA.KEY.PROD_CODE = BPCPQPRD.PARM_CODE;
        DCCSIRLN.DATA.STRDT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.KEY.PRDMO_CD);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.KEY.PROD_CODE);
        S000_CALL_DCZSIRLN();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSIRLN.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCIRPRD);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, DCCIRPRD.CI_TYP);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.CI_TYP);
        if (CICQACRI.O_DATA.O_CI_TYP != DCCSIRLN.DATA.CI_TYP) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CI_NOTMATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIRPRD.LN_MTH);
        CEP.TRC(SCCGWA, LNCSIQIF.PAY_MTH);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.LN_MTH);
        if (DCCSIRLN.DATA.LN_MTH != LNCSIQIF.PAY_MTH) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_MTH_NOT_SPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIRPRD.LN_PER);
        CEP.TRC(SCCGWA, LNCSIQIF.PAYP_PER);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.LN_PER);
        if (DCCSIRLN.DATA.LN_PER == 'M') {
            DCCSIRLN.DATA.LN_PER = '4';
        }
        if (DCCSIRLN.DATA.LN_PER != LNCSIQIF.PAYP_PER) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_PER_NOT_SPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B033_GET_DD_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_TMP_DD_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_TYP_NOT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = WS_TMP_DD_AC;
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        } else if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09")
            || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_REL_CARD_HAVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_REL_AC = WS_TMP_DD_AC;
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = WS_REL_AC;
        CICQACAC.DATA.CCY_ACAC = WS_TMP_CCY;
        CICQACAC.DATA.CR_FLG = WS_TMP_CCY_TYP;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CICQACAC, CICDACAC);
        WS_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        WS_DD_ACAC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_STS);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CUSTOM_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B011_GET_LN_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSIQIF);
        LNCSIQIF.CONTRACT_NO = WS_TMP_LN_AC;
        S000_CALL_LNZSIQIF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSIQIF.STS);
        if (LNCSIQIF.STS != 'A') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_STS_INV;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSIQIF.PAYP_PER);
        if (LNCSIQIF.PAYP_PER != '4') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_PER_NOT_SPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = WS_TMP_LN_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_LN_CI = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        WS_LN_ACAC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        WS_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        WS_CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        if (!WS_CCY.equalsIgnoreCase(DCCSIALP.CCY)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, WS_LN_CI);
        if (!WS_CI_NO.equalsIgnoreCase(WS_LN_CI)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NOT_SAME_CUS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_DD_ACSTSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = WS_REL_AC;
        DDCIQBAL.DATA.CCY = DCCSIALP.CCY;
        DDCIQBAL.DATA.CCY_TYPE = DCCSIALP.CCY_TYP;
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
        if (DDCIQBAL.DATA.CCY_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DDLN_AGT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(66 - 1, 66 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DDLN_AGT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUGBAL);
        DCCUGBAL.I_DATA.FUNC = '1';
        DCCUGBAL.I_DATA.AC_NO = WS_REL_AC;
        DCCUGBAL.I_DATA.CCY = DCCSIALP.CCY;
        S000_CALL_DCZUGBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUGBAL.O_DATA.TOT_BAL);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.STRAMT);
        if (DCCUGBAL.O_DATA.TOT_BAL < DCCSIRLN.DATA.STRAMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BAL_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSIALP.LN_AC);
        if (DCCSIALP.LN_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_AC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRDDLN);
        DCRDDLN.LN_AC = DCCSIALP.LN_AC;
        DCRDDLN.DD_AC = DCCSIALP.DD_AC;
        DCRDDLN.CCY = DCCSIALP.CCY;
        DCRDDLN.CCY_TYPE = DCCSIALP.CCY_TYP;
        DCRDDLN.LN_TYP = '0';
        T000_READ_DCTDDLN();
        if (pgmRtn) return;
        if (WS_CARD_FEE_FLG == 'Y') {
            if (DCRDDLN.EXPDT <= SCCGWA.COMM_AREA.AC_DATE) {
                WS_TMP_LN_AC = DCRDDLN.LN_AC;
                WS_TMP_DD_AC = DCRDDLN.DD_AC;
                WS_TMP_CCY = DCRDDLN.CCY;
                WS_TMP_CCY_TYP = DCRDDLN.CCY_TYPE;
                T000_READ_UPDATE_DCTDDLN();
                if (pgmRtn) return;
                DCRDDLN.LN_TYP = '3';
                DCRDDLN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRDDLN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DCTDDLN();
                if (pgmRtn) return;
                B033_GET_DD_AC_PROC();
                if (pgmRtn) return;
                B031_CAN_AC_STSW_PROC();
                if (pgmRtn) return;
                B032_CAN_CI_AGT_PROC();
                if (pgmRtn) return;
            } else {
                C000_OUTPUT_INFO();
                if (pgmRtn) return;
            }
        }
    }
    public void C000_OUTPUT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCLPRDO);
        DCCLPRDO.AGT_NO = DCRDDLN.KEY.AGT_NO;
        DCCLPRDO.PROD_CD = DCRDDLN.PROD_CD;
        DCCLPRDO.LN_AC = DCRDDLN.LN_AC;
        DCCLPRDO.DD_AC = DCRDDLN.DD_AC;
        DCCSIALP.DD_AC = DCRDDLN.DD_AC;
        DCCLPRDO.CCY = DCRDDLN.CCY;
        DCCLPRDO.CCY_TYP = DCRDDLN.CCY_TYPE;
        DCCLPRDO.LN_TYP = DCRDDLN.LN_TYP;
        DCCLPRDO.ADP_TYP = DCRDDLN.AGT_TYP;
        DCCLPRDO.LN_MTH = DCRDDLN.LN_MTH;
        DCCLPRDO.LN_PER = DCRDDLN.LN_PER;
        DCCLPRDO.DD_PER = DCRDDLN.DD_PER;
        DCCLPRDO.TERM = DCRDDLN.TERM;
        DCCLPRDO.DED_MTH = DCRDDLN.DED_MTH;
        DCCLPRDO.D_MN_AMT = DCRDDLN.DED_MIN_AMT;
        DCCLPRDO.D_MX_AMT = DCRDDLN.DED_MAX_AMT;
        DCCLPRDO.DD_D_PER = DCRDDLN.DD_DED_PER;
        DCCLPRDO.LN_D_PER = DCRDDLN.LN_DED_PER;
        DCCLPRDO.C_ED_PER = DCRDDLN.CON_END_PER;
        DCCSIALP.C_ED_PER = DCRDDLN.CON_END_PER;
        DCCLPRDO.T_ED_PER = DCRDDLN.TOT_END_PER;
        DCCSIALP.T_ED_PER = DCRDDLN.TOT_END_PER;
        DCCLPRDO.STRDT = DCRDDLN.STRDT;
        DCCLPRDO.EXPDT = DCRDDLN.EXPDT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = DCCLPRDO;
        SCCFMT.DATA_LEN = 424;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B040_GET_CI_AGT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'A';
        CICMAGT.DATA.CI_NO = WS_CI_NO;
        CICMAGT.DATA.AGT_TYP = "3001001";
        CICMAGT.DATA.AGT_STS = 'N';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DCCSIALP.DD_AC;
        CICMAGT.DATA.AGT_LVL = 'A';
        CICMAGT.DATA.EXP_DATE = DCCSIALP.EXPDT;
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
    }
    public void B032_CAN_CI_AGT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'D';
        CICMAGT.DATA.CI_NO = WS_CI_NO;
        CICMAGT.DATA.AGT_TYP = "3001001";
        CICMAGT.DATA.AGT_STS = 'C';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = WS_TMP_DD_AC;
        CICMAGT.DATA.AGT_LVL = 'A';
        CICMAGT.DATA.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
    }
    public void B050_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDDLN);
        DCRDDLN.KEY.AGT_NO = CICMAGT.DATA.AGT_NO;
        DCRDDLN.AGT_TYP = DCCSIALP.ADP_TYP;
        DCRDDLN.LN_AC = DCCSIALP.LN_AC;
        DCRDDLN.DD_AC = DCCSIALP.DD_AC;
        DCRDDLN.PROD_CD = DCCSIALP.KEY.PROD_CD;
        DCRDDLN.CCY = DCCSIALP.CCY;
        DCRDDLN.CCY_TYPE = DCCSIALP.CCY_TYP;
        DCRDDLN.LN_TYP = '0';
        DCRDDLN.DED_MTH = DCCSIALP.DED_MTH;
        DCRDDLN.DED_MIN_AMT = DCCSIALP.D_MN_AMT;
        DCRDDLN.DED_MAX_AMT = DCCSIALP.D_MX_AMT;
        DCRDDLN.DD_DED_PER = DCCSIALP.DD_D_PER;
        DCRDDLN.LN_DED_PER = DCCSIALP.LN_D_PER;
        DCRDDLN.LN_MTH = DCCIRPRD.LN_MTH;
        DCRDDLN.LN_PER = DCCIRPRD.LN_PER;
        DCRDDLN.DD_PER = DCCIRPRD.DD_PER;
        DCRDDLN.TERM = DCCIRPRD.TERM;
        DCRDDLN.STRDT = DCCSIALP.STRDT;
        DCRDDLN.EXPDT = DCCSIALP.EXPDT;
        DCRDDLN.CON_END_PER = DCCSIALP.C_ED_PER;
        DCRDDLN.TOT_END_PER = DCCSIALP.T_ED_PER;
        DCRDDLN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDDLN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDDLN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDDLN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDDLN();
        if (pgmRtn) return;
    }
    public void B060_SET_AC_STSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.KEY.AC = WS_DD_ACAC;
        DDCIMCYY.DATA.STS_CD = "66";
        DDCIMCYY.DATA.SET_FLG = 'O';
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCSTSW);
        LNCSTSW.FUNC = 'S';
        LNCSTSW.LN_AC = DCCSIALP.LN_AC;
        LNCSTSW.DD_AC = DCCSIALP.DD_AC;
        LNCSTSW.CCY = DCCSIALP.CCY;
        LNCSTSW.CCY_TYP = DCCSIALP.CCY_TYP;
        S000_CALL_LNZSTSW();
        if (pgmRtn) return;
    }
    public void B031_CAN_AC_STSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.KEY.AC = WS_DD_ACAC;
        DDCIMCYY.DATA.STS_CD = "66";
        DDCIMCYY.DATA.SET_FLG = 'F';
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCSTSW);
        LNCSTSW.FUNC = 'C';
        LNCSTSW.LN_AC = WS_TMP_LN_AC;
        LNCSTSW.DD_AC = WS_TMP_DD_AC;
        LNCSTSW.CCY = WS_TMP_CCY;
        LNCSTSW.CCY_TYP = WS_TMP_CCY_TYP;
        S000_CALL_LNZSTSW();
        if (pgmRtn) return;
    }
    public void B061_ADD_BALH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSABLH);
        CEP.TRC(SCCGWA, WS_DD_ACAC);
        DDCSABLH.AC = WS_DD_ACAC;
        S000_CALL_DDZSABLH();
        if (pgmRtn) return;
    }
    public void B034_CAN_CIAGT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DCRDDLN.DD_AC;
        DDRCCY.CCY = DCRDDLN.CCY;
        DDRCCY.CCY_TYPE = DCRDDLN.CCY_TYPE;
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        if (WS_CCY_FLG == 'Y') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 66 - 1) + "0" + DDRCCY.STS_WORD.substring(66 + 1 - 1);
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCSTSW);
        LNCSTSW.FUNC = 'C';
        LNCSTSW.LN_AC = DCRDDLN.LN_AC;
        LNCSTSW.DD_AC = DCRDDLN.DD_AC;
        LNCSTSW.CCY = DCRDDLN.CCY;
        LNCSTSW.CCY_TYP = DCRDDLN.CCY_TYPE;
        S000_CALL_LNZSTSW();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'D';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DCRDDLN.DD_AC;
        CICMAGT.DATA.AGT_TYP = "3001001";
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
    }
    public void T000_WRITE_DCTDDLN() throws IOException,SQLException,Exception {
        DCTDDLN_RD = new DBParm();
        DCTDDLN_RD.TableName = "DCTDDLN";
        IBS.WRITE(SCCGWA, DCRDDLN, DCTDDLN_RD);
    }
    public void T000_READ_DCTDDLN() throws IOException,SQLException,Exception {
        DCTDDLN_RD = new DBParm();
        DCTDDLN_RD.TableName = "DCTDDLN";
        DCTDDLN_RD.where = "( LN_AC = :DCRDDLN.LN_AC "
            + "OR ( DD_AC = :DCRDDLN.DD_AC "
            + "AND CCY = :DCRDDLN.CCY "
            + "AND CCY_TYPE = :DCRDDLN.CCY_TYPE ) ) "
            + "AND LN_TYP = :DCRDDLN.LN_TYP";
        IBS.READ(SCCGWA, DCRDDLN, this, DCTDDLN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CARD_FEE_FLG = 'Y';
        } else {
            WS_CARD_FEE_FLG = 'N';
        }
    }
    public void T000_READUPD_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "( CUS_AC = :DDRCCY.CUS_AC "
            + "OR CUS_AC = :WS_SQL_AC ) "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CCY_FLG = 'N';
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_REWRITE_DCTDDLN() throws IOException,SQLException,Exception {
        DCTDDLN_RD = new DBParm();
        DCTDDLN_RD.TableName = "DCTDDLN";
        IBS.REWRITE(SCCGWA, DCRDDLN, DCTDDLN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_UPDATE_DCTDDLN() throws IOException,SQLException,Exception {
        DCTDDLN_RD = new DBParm();
        DCTDDLN_RD.TableName = "DCTDDLN";
        DCTDDLN_RD.where = "LN_AC = :DCRDDLN.LN_AC "
            + "AND LN_TYP = :DCRDDLN.LN_TYP";
        DCTDDLN_RD.upd = true;
        IBS.READ(SCCGWA, DCRDDLN, this, DCTDDLN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B070_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDDLN);
        DCRDDLN.LN_AC = DCCSIALP.LN_AC;
        DCRDDLN.LN_TYP = '0';
        T000_READ_UPDATE_DCTDDLN();
        if (pgmRtn) return;
        if (DCRDDLN.STRDT > SCCGWA.COMM_AREA.AC_DATE 
            && DCCSIALP.STRDT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_DT_GT_AC_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRDDLN.DED_MIN_AMT = DCCSIALP.D_MN_AMT;
        DCRDDLN.DED_MAX_AMT = DCCSIALP.D_MX_AMT;
        DCRDDLN.DD_DED_PER = DCCSIALP.DD_D_PER;
        DCRDDLN.LN_DED_PER = DCCSIALP.LN_D_PER;
        DCRDDLN.STRDT = DCCSIALP.STRDT;
        DCRDDLN.EXPDT = DCCSIALP.EXPDT;
        DCRDDLN.CON_END_PER = DCCSIALP.C_ED_PER;
        DCRDDLN.TOT_END_PER = DCCSIALP.T_ED_PER;
        DCRDDLN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDDLN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTDDLN();
        if (pgmRtn) return;
    }
    public void B090_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDDLN);
        DCRDDLN.LN_AC = DCCSIALP.LN_AC;
        DCRDDLN.LN_TYP = '0';
        T000_READ_UPDATE_DCTDDLN();
        if (pgmRtn) return;
        if (DCCSIALP.LN_TYP == '0' 
            || DCCSIALP.LN_TYP == ' ') {
            DCRDDLN.LN_TYP = '4';
        } else {
            DCRDDLN.LN_TYP = DCCSIALP.LN_TYP;
        }
        DCRDDLN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDDLN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTDDLN();
        if (pgmRtn) return;
        WS_TMP_DD_AC = DCRDDLN.DD_AC;
        WS_TMP_CCY = DCRDDLN.CCY;
        WS_TMP_CCY_TYP = DCRDDLN.CCY_TYPE;
    }
    public void B110_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DCCSIALP.DD_AC;
        BPCPNHIS.INFO.REF_NO = DCCSIALP.KEY.PROD_CD;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.NEW_DAT_PT = DCCSIALP;
        BPCPNHIS.INFO.FMT_ID = "DCZSIALP";
        BPCPNHIS.INFO.FMT_ID_LEN = 368;
        if (DCCSIALP.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_RMK = "ADD PROTOCOL";
            BPCPNHIS.INFO.TX_TYP_CD = "L611";
        } else if (DCCSIALP.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_RMK = "MODIFY PROTOCOL";
            BPCPNHIS.INFO.TX_TYP_CD = "L612";
        } else if (DCCSIALP.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.TX_RMK = "DELETE PROTOCOL";
            BPCPNHIS.INFO.TX_TYP_CD = "L612";
        } else {
        }
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_RMK);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B130_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSIALP.DD_AC);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
        IBS.init(SCCGWA, DCCLPRDO);
        DCCLPRDO.FUNC = DCCSIALP.FUNC;
        DCCLPRDO.AGT_NO = CICMAGT.DATA.AGT_NO;
        DCCLPRDO.PROD_CD = DCCSIALP.KEY.PROD_CD;
        DCCLPRDO.PROD_NM = DCCSIALP.PROD_NM;
        DCCLPRDO.LN_AC = DCCSIALP.LN_AC;
        DCCLPRDO.DD_AC = DCCSIALP.DD_AC;
        DCCLPRDO.CCY = DCCSIALP.CCY;
        DCCLPRDO.CCY_TYP = DCCSIALP.CCY_TYP;
        DCCLPRDO.LN_TYP = DCCSIALP.LN_TYP;
        DCCLPRDO.ADP_TYP = DCCSIALP.ADP_TYP;
        DCCLPRDO.LN_MTH = DCCIRPRD.LN_MTH;
        DCCLPRDO.LN_PER = DCCIRPRD.LN_PER;
        DCCLPRDO.DD_PER = DCCIRPRD.DD_PER;
        DCCLPRDO.TERM = DCCIRPRD.TERM;
        DCCLPRDO.DED_MTH = DCCSIALP.DED_MTH;
        DCCLPRDO.D_MN_AMT = DCCSIALP.D_MN_AMT;
        DCCLPRDO.D_MX_AMT = DCCSIALP.D_MX_AMT;
        DCCLPRDO.DD_D_PER = DCCSIALP.DD_D_PER;
        DCCLPRDO.LN_D_PER = DCCSIALP.LN_D_PER;
        DCCLPRDO.C_ED_PER = DCCSIALP.C_ED_PER;
        DCCLPRDO.T_ED_PER = DCCSIALP.T_ED_PER;
        DCCLPRDO.STRAMT = DCCSIALP.STRAMT;
        DCCLPRDO.STRDT = DCCSIALP.STRDT;
        DCCLPRDO.EXPDT = DCCSIALP.EXPDT;
        CEP.TRC(SCCGWA, DCCLPRDO.FUNC);
        CEP.TRC(SCCGWA, "************OUTPUT************");
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "FORMAT OUTPUT");
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = DCCLPRDO;
        SCCFMT.DATA_LEN = 424;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B031_CHECK_EXPD_PROCL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDDLN);
        DCRDDLN.LN_AC = DCCSIALP.LN_AC;
        WS_SQL_AC = WS_REL_AC;
        DCRDDLN.DD_AC = DCCSIALP.DD_AC;
        DCRDDLN.CCY = DCCSIALP.CCY;
        DCRDDLN.CCY_TYPE = DCCSIALP.CCY_TYP;
        WS_SQL_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_STARTBR_DCTDDLN1();
        if (pgmRtn) return;
        T000_READNEXT_DCTDDLN();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            DCRDDLN.LN_TYP = '3';
            DCRDDLN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDDLN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTDDLN();
            if (pgmRtn) return;
            B034_CAN_CIAGT_PROC();
            if (pgmRtn) return;
            T000_READNEXT_DCTDDLN();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTDDLN();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUGBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-GET-AC-BAL", DCCUGBAL);
        if (DCCUGBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUGBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSABLH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-MAINT-DDTBALH", DDCSABLH);
        if (DDCSABLH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCSABLH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSTSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-STSW", LNCSTSW);
        CEP.TRC(SCCGWA, LNCSTSW.RC);
        if (LNCSTSW.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSTSW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD);
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMAGT.RC);
        }
    }
    public void S000_CALL_DDZIMCYY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-ZIMCYY-AC", DDCIMCYY);
        CEP.TRC(SCCGWA, DDCIMCYY.RC);
        if (DDCIMCYY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMCYY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSIQIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-INF", LNCSIQIF);
        if (LNCSIQIF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, LNCSIQIF.RC.RC_CODE);
        }
    }
    public void S000_CALL_DCZSIRLN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-SIR-LN", DCCSIRLN);
        if (DCCSIRLN.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + DCCSIRLN.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void A001_TMPL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDDLN);
        T000_STARTBR_DCTDDLN();
        if (pgmRtn) return;
        T000_READNEXT_DCTDDLN();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (DCRDDLN.EXPDT == DCRDDLN.UPDTBL_DATE) {
                DCRDDLN.LN_TYP = '3';
            } else {
                DCRDDLN.LN_TYP = '0';
            }
            T000_REWRITE_DCTDDLN();
            if (pgmRtn) return;
            T000_READNEXT_DCTDDLN();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTDDLN();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTDDLN() throws IOException,SQLException,Exception {
        DCTDDLN_BR.rp = new DBParm();
        DCTDDLN_BR.rp.TableName = "DCTDDLN";
        DCTDDLN_BR.rp.where = "LN_TYP = ' '";
        DCTDDLN_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DCRDDLN, this, DCTDDLN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DCTDDLN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRDDLN, this, DCTDDLN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_ENDBR_DCTDDLN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTDDLN_BR);
    }
    public void T000_STARTBR_DCTDDLN1() throws IOException,SQLException,Exception {
        DCTDDLN_BR.rp = new DBParm();
        DCTDDLN_BR.rp.TableName = "DCTDDLN";
        DCTDDLN_BR.rp.where = "( LN_AC = :DCRDDLN.LN_AC "
            + "OR ( ( DD_AC = :DCRDDLN.DD_AC "
            + "OR DD_AC = :WS_SQL_AC ) "
            + "AND CCY = :DCRDDLN.CCY "
            + "AND CCY_TYPE = :DCRDDLN.CCY_TYPE ) ) "
            + "AND ( EXPDT <= :WS_SQL_DT ) "
            + "AND LN_TYP = '0'";
        DCTDDLN_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DCRDDLN, this, DCTDDLN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
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
