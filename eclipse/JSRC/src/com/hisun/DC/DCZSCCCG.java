package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCCCG {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    DBParm DCTCITCD_RD;
    DBParm DCTCTCDC_RD;
    boolean pgmRtn = false;
    String K_TBL_CDDAT = "DCTCDDAT";
    String K_TBL_CTCDC = "DCTCTCDC";
    String K_HIS_REMARK = "APPLICATION FOR CITIZEN CARD FILLING OR CHANGING";
    String K_HIS_COPYBOOK = "DCRCTCDC";
    String K_OUTPUT_FMT = "DC443";
    String WS_ERR_MSG = " ";
    String WS_CHG_SEQ = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    DCRCITCD DCRCITCD = new DCRCITCD();
    CICCUST CICCUST = new CICCUST();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    CICSAGEN CICSAGEN = new CICSAGEN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCF443 DCCF443 = new DCCF443();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    DCCS4403 DCCS4403;
    public void MP(SCCGWA SCCGWA, DCCS4403 DCCS4403) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS4403 = DCCS4403;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCCCG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS4403.CZ_TYP);
        CEP.TRC(SCCGWA, "GO");
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_APPLY_PROCESS();
        if (pgmRtn) return;
        B030_HISTORY_PROCESS();
        if (pgmRtn) return;
        B040_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS4403.CZ_TYP);
        CEP.TRC(SCCGWA, DCCS4403.CARD_NO);
        CEP.TRC(SCCGWA, DCCS4403.ID_TYP);
        CEP.TRC(SCCGWA, DCCS4403.ID_NO);
        CEP.TRC(SCCGWA, DCCS4403.CHARGE_FLG);
        if (DCCS4403.CZ_TYP == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CZ_TYP_M_INPUT);
        }
        if (DCCS4403.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        if (DCCS4403.ID_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_IDTYPE_IDNO_SAMETIME);
        }
        if (DCCS4403.ID_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_IDTYPE_IDNO_SAMETIME);
        }
        if (DCCS4403.CZ_TYP == '1') {
            if (DCCS4403.URGENT_FLG == ' ') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_UR_FLG_M_INPUT);
            }
        }
        if (DCCS4403.CHARGE_FLG == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_FEE_FLG_M_INPUT);
        }
        CEP.TRC(SCCGWA, "CHECK INPUT MID");
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS4403.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CHECK STS");
        if (DCRCDDAT.CARD_STS != 'N' 
            && DCRCDDAT.CARD_STS != '2') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCCS4403.CZ_TYP == '1' 
            && !DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_LOSS_STS);
        }
        IBS.init(SCCGWA, DCRCTCDC);
        DCRCTCDC.KEY.OLD_CARD_NO = DCCS4403.CARD_NO;
        T000_READ_DCTCTCDC();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_ALR_REG);
        }
        if (DCCS4403.CZ_TYP == '1' 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.KEY.CARD_NO = DCCS4403.CARD_NO;
            T000_READ_DCTCITCD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "DISPLAY-CITCD-CI-INF");
            CEP.TRC(SCCGWA, DCRCITCD.ID_TYP);
            CEP.TRC(SCCGWA, DCRCITCD.ID_NO);
            CEP.TRC(SCCGWA, DCRCITCD.CI_NM);
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "DISPLAY-CUST-CI-INF");
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
            if ((DCRCITCD.ID_TYP.equalsIgnoreCase("10701") 
                || DCRCITCD.ID_TYP.equalsIgnoreCase("10703")) 
                && (!DCCS4403.ID_TYP.equalsIgnoreCase(CICCUST.O_DATA.O_ID_TYPE) 
                || !DCCS4403.ID_NO.equalsIgnoreCase(CICCUST.O_DATA.O_ID_NO) 
                || (!DCCS4403.ID_TYP.equalsIgnoreCase("10701") 
                && !DCCS4403.ID_TYP.equalsIgnoreCase("10703")) 
                || !DCCS4403.ID_NO.equalsIgnoreCase(DCRCITCD.ID_NO) 
                || (!DCCS4403.CI_NM.equalsIgnoreCase(CICCUST.O_DATA.O_CI_NM) 
                && DCCS4403.CI_NM.trim().length() > 0) 
                || (!DCCS4403.CI_NM.equalsIgnoreCase(DCRCITCD.CI_NM) 
                && DCCS4403.CI_NM.trim().length() > 0) 
                || !CICCUST.O_DATA.O_CI_NM.equalsIgnoreCase(DCRCITCD.CI_NM) 
                || !CICCUST.O_DATA.O_ID_NO.equalsIgnoreCase(DCRCITCD.ID_NO) 
                || (!CICCUST.O_DATA.O_ID_TYPE.equalsIgnoreCase("10701") 
                && !CICCUST.O_DATA.O_ID_TYPE.equalsIgnoreCase("10703")))) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ID_INF_ERR);
            }
            if ((!DCRCITCD.ID_TYP.equalsIgnoreCase("10701") 
                && !DCRCITCD.ID_TYP.equalsIgnoreCase("10703")) 
                && (!DCCS4403.ID_TYP.equalsIgnoreCase(CICCUST.O_DATA.O_ID_TYPE) 
                || !DCCS4403.ID_NO.equalsIgnoreCase(CICCUST.O_DATA.O_ID_NO) 
                || !DCCS4403.ID_TYP.equalsIgnoreCase(DCRCITCD.ID_TYP) 
                || !DCCS4403.ID_NO.equalsIgnoreCase(DCRCITCD.ID_NO) 
                || (!DCCS4403.CI_NM.equalsIgnoreCase(CICCUST.O_DATA.O_CI_NM) 
                && DCCS4403.CI_NM.trim().length() > 0) 
                || (!DCCS4403.CI_NM.equalsIgnoreCase(DCRCITCD.CI_NM) 
                && DCCS4403.CI_NM.trim().length() > 0) 
                || !CICCUST.O_DATA.O_CI_NM.equalsIgnoreCase(DCRCITCD.CI_NM) 
                || !CICCUST.O_DATA.O_ID_NO.equalsIgnoreCase(DCRCITCD.ID_NO) 
                || !CICCUST.O_DATA.O_ID_TYPE.equalsIgnoreCase(DCRCITCD.ID_TYP))) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ID_INF_ERR);
            }
        }
    }
    public void B020_APPLY_PROCESS() throws IOException,SQLException,Exception {
        if (DCCS4403.CZ_TYP == '1' 
            && DCCS4403.URGENT_FLG == '0') {
            IBS.init(SCCGWA, BPCPLOSS);
            BPCPLOSS.DATA_INFO.AC = DCCS4403.CARD_NO;
            BPCPLOSS.DATA_INFO.STS = '1';
            BPCPLOSS.INFO.FUNC = 'I';
            BPCPLOSS.INFO.INDEX_FLG = "2";
            S000_CALL_BPZPLOSS();
            if (pgmRtn) return;
            if (BPCPLOSS.DATA_INFO.LOS_ORG != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_M_LOST_BR_FIL_CHG);
            }
        }
        if ((DCCS4403.AGENT_FLG == '1' 
            || DCCS4403.AGENT_FLG == 'Y') 
            || SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B099_REG_AGENT_INF();
            if (pgmRtn) return;
        }
        if (DCCS4403.CZ_TYP == '1' 
            && DCCS4403.CHARGE_FLG == '0') {
            B100_FEE_PROCESS();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_CHG_SEQ == null) WS_CHG_SEQ = "";
        JIBS_tmp_int = WS_CHG_SEQ.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) WS_CHG_SEQ += " ";
        WS_CHG_SEQ = JIBS_tmp_str[0].substring(5 - 1, 5 + 4 - 1) + WS_CHG_SEQ.substring(4);
        if (WS_CHG_SEQ == null) WS_CHG_SEQ = "";
        JIBS_tmp_int = WS_CHG_SEQ.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) WS_CHG_SEQ += " ";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_CHG_SEQ = WS_CHG_SEQ.substring(0, 5 - 1) + JIBS_tmp_str[0] + WS_CHG_SEQ.substring(5 + 12 - 1);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, WS_CHG_SEQ);
        IBS.init(SCCGWA, DCRCTCDC);
        DCRCTCDC.KEY.OLD_CARD_NO = DCCS4403.CARD_NO;
        DCRCTCDC.CI_NM = DCCS4403.CI_NM;
        DCRCTCDC.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCTCDC.CHG_SEQ = WS_CHG_SEQ;
        DCRCTCDC.CHG_APP_NO = DCCS4403.CHANGE_BOOK_NO;
        DCRCTCDC.CHG_STS = '1';
        DCRCTCDC.CHG_TYP = DCCS4403.CZ_TYP;
        DCRCTCDC.FAST_FLG = DCCS4403.URGENT_FLG;
        DCRCTCDC.ID_TYP = DCCS4403.ID_TYP;
        DCRCTCDC.ID_NO = DCCS4403.ID_NO;
        DCRCTCDC.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCTCDC.APP_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCTCDC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCTCDC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCTCDC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCTCDC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y' 
            || (DCCS4403.AGENT_FLG == '1' 
            || DCCS4403.AGENT_FLG == 'Y')) {
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                DCRCTCDC.AGT_CI_NM = CICGAGA_AGENT_AREA.CI_NM;
                DCRCTCDC.AGT_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
                DCRCTCDC.AGT_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            } else {
                DCRCTCDC.AGT_ID_TYP = DCCS4403.AGENT_ID_TYP;
                DCRCTCDC.AGT_ID_NO = DCCS4403.AGENT_ID_NO;
                DCRCTCDC.AGT_CI_NM = DCCS4403.AGENT_CI_NM;
            }
        }
        T000_WRITE_DCTCTCDC();
        if (pgmRtn) return;
    }
    public void B030_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DCCS4403.CARD_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCS4403.CARD_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "PB09";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 971;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.NEW_DAT_PT = DCRCTCDC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF443);
        DCCF443.CHANGE_NO = WS_CHG_SEQ;
        DCCF443.CARD_NO = DCCS4403.CARD_NO;
        DCCF443.CI_NAME = DCCS4403.CI_NM;
        DCCF443.ID_TYP = DCCS4403.ID_TYP;
        DCCF443.ID_NO = DCCS4403.ID_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF443;
        SCCFMT.DATA_LEN = 362;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B099_REG_AGENT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS4403.AGENT_FLG);
        CICSAGEN.FUNC = 'A';
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.ID_NO);
            CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.CI_NM);
            CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.PHONE);
            CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
            CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        } else {
            CICSAGEN.ID_TYP = DCCS4403.AGENT_ID_TYP;
            CICSAGEN.ID_NO = DCCS4403.AGENT_ID_NO;
            CICSAGEN.CI_NAME = DCCS4403.AGENT_CI_NM;
            CICSAGEN.PHONE = DCCS4403.AGENT_PHONE;
        }
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = "0264403";
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.OUT_AC = DCCS4403.CARD_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.START_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.LAST_DT = 20991231;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void B100_FEE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = DCCS4403.CARD_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "156";
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = "0264403";
        BPCFFTXI.TX_DATA.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.PROD_CODE = DCRCDDAT.PROD_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = DCRCDDAT.PROD_CD;
        BPCTCALF.INPUT_AREA.TX_AC = DCCS4403.CARD_NO;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.PROD_CODE1);
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_CI = DCRCDDAT.CARD_OWN_CINO;
        BPCTCALF.INPUT_AREA.TX_CCY = "156";
        if (DCRCDDAT.CARD_MEDI == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "04";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "05";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        }
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-LOSS-INFO", BPCPLOSS);
        if (BPCPLOSS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSAGEN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST);
        }
    }
    public void T000_READ_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        IBS.READ(SCCGWA, DCRCITCD, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST);
        }
    }
    public void T000_READ_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        IBS.READ(SCCGWA, DCRCTCDC, DCTCTCDC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_WRITE_DCTCTCDC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "N999-REW");
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        IBS.WRITE(SCCGWA, DCRCTCDC, DCTCTCDC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DUPKEY;
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
