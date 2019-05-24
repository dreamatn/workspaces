package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSLOST {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    brParm DCTAPPLC_BR = new brParm();
    DBParm DCTAPPLC_RD;
    DBParm DCTCTCDC_RD;
    boolean pgmRtn = false;
    String DCZUCINF = "DC-U-CARD-INF";
    String REC_NHIS = "BP-REC-NHIS";
    String F_F_CAL_FEE = "BP-F-F-CAL-FEE";
    String F_F_TX_INFO = "BP-F-F-TX-INFO";
    String HIS_REMARK_1 = " REPORTING OF LOSE";
    String HIS_REMARK_2 = "CANCEL THE REPORT THE LOSS";
    double MAX_BL = 50000;
    int MAX_ROW = 99;
    int MAX_COL = 99;
    int COL_CNT = 3;
    String OUTPUT_FMT = "DCX01";
    String HIS_COPYBOOK = "DCRCDDAT";
    String TBL_CDDAT = "DCTCDDAT";
    DCZSLOST_WS_VARIABLES WS_VARIABLES = new DCZSLOST_WS_VARIABLES();
    DCZSLOST_WS_COND_FLG WS_COND_FLG = new DCZSLOST_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCRAPPLC DCRAPPLC = new DCRAPPLC();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICSAGEN CICSAGEN = new CICSAGEN();
    CICCUST CICCUST = new CICCUST();
    DCCUCDLP DCCUCDLP = new DCCUCDLP();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CICQACRL CICQACRL = new CICQACRL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    SCCGWA SCCGWA;
    BPCPORUP BPCPORUP;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    CICGAGA_AGENT_AREA_AGENT_AREA AGENT_AREA_AGENT_AREA;
    DCCS0132 DCCS0132;
    public void MP(SCCGWA SCCGWA, DCCS0132 DCCS0132) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS0132 = DCCS0132;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSLOST return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        AGENT_AREA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, AGENT_AREA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, AGENT_AREA_AGENT_AREA);
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, BPCPQORG.WS_OUTPUT);
        IBS.init(SCCGWA, DCRCDDAT);
        IBS.init(SCCGWA, DCRCDDAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_ADD_AGENT_INF();
        if (pgmRtn) return;
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B015_RESET_PASSWORD();
        if (pgmRtn) return;
        if (DCCS0132.FUNC_CODE == 'A') {
            B020_ADD_LOST_CHECK();
            if (pgmRtn) return;
            B020_ADD_LOST_REPORTING();
            if (pgmRtn) return;
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                B100_FEE_PROCESS();
                if (pgmRtn) return;
            }
            B099_APPLE_PAY_PROC();
            if (pgmRtn) return;
        } else if (DCCS0132.FUNC_CODE == 'C') {
            B030_CANCEL_LOST_REPORTING();
            if (pgmRtn) return;
        } else if (DCCS0132.FUNC_CODE == 'R') {
            B040_R_ADD_LOST_REPORTING();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B060_HISTORY_PROCESS();
        if (pgmRtn) return;
        B070_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B001_CHECK_ADD_AGENT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS0132.AGENT_FLG);
        if ((DCCS0132.AGENT_FLG == '1' 
            || DCCS0132.AGENT_FLG == 'Y') 
            || SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            IBS.init(SCCGWA, CICSAGEN);
            CICSAGEN.FUNC = 'A';
            if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
                CEP.TRC(SCCGWA, AGENT_AREA_AGENT_AREA.ID_NO);
                CEP.TRC(SCCGWA, AGENT_AREA_AGENT_AREA.CI_NM);
                CEP.TRC(SCCGWA, AGENT_AREA_AGENT_AREA.PHONE);
                CICSAGEN.ID_TYP = AGENT_AREA_AGENT_AREA.ID_TYP;
                CICSAGEN.ID_NO = AGENT_AREA_AGENT_AREA.ID_NO;
                CICSAGEN.CI_NAME = AGENT_AREA_AGENT_AREA.CI_NM;
                CICSAGEN.PHONE = AGENT_AREA_AGENT_AREA.PHONE;
            } else {
                CICSAGEN.ID_TYP = DCCS0132.AGENT_ID_TYPE;
                CICSAGEN.ID_NO = DCCS0132.AGENT_ID_NO;
                CICSAGEN.CI_NAME = DCCS0132.AGENT_CI_NAME;
                CICSAGEN.PHONE = DCCS0132.AGENT_PHONE;
            }
            CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
            CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CICSAGEN.TX_CODE = "0260132";
            CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
            CICSAGEN.OUT_AC = DCCS0132.CARD_NO;
            CICSAGEN.AGENT_TP = "04";
            CICSAGEN.START_DT = SCCGWA.COMM_AREA.AC_DATE;
            CICSAGEN.LAST_DT = 20991231;
            S000_CALL_CIZSAGEN();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS0132.ID_TYPE);
        CEP.TRC(SCCGWA, DCCS0132.ID_NO);
        CEP.TRC(SCCGWA, DCCS0132.FUNC_CODE);
        if (DCCS0132.ID_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_ID_TYPE);
        }
        if (DCCS0132.ID_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_ID_NO);
        }
        if (DCCS0132.CI_NAME.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_CI_NAME);
        }
        if (DCCS0132.AGENT_FLG == 'Y') {
            if (DCCS0132.AGENT_CI_NAME.trim().length() == 0) {
                CEP.ERR(SCCGWA, ERROR_MSG.DC_AG_CI_NAME_M_INPUT);
            }
            if (DCCS0132.AGENT_ID_TYPE.trim().length() == 0) {
                CEP.ERR(SCCGWA, ERROR_MSG.DC_AG_ID_TYPE_M_INPUT);
            }
            if (DCCS0132.AGENT_ID_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, ERROR_MSG.DC_AG_ID_NO_M_INPUT);
            }
        }
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCS0132.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            DCCUCDLP.CARD_NO = DCCS0132.CARD_NO;
            DCCUCDLP.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_DCZUCDLP();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = DCCUCINF.CARD_HLDR_CINO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "ADD-DISPLAY-CI-INF");
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        if ((!DCCS0132.ID_TYPE.equalsIgnoreCase(CICCUST.O_DATA.O_ID_TYPE)) 
            || (!DCCS0132.ID_NO.equalsIgnoreCase(CICCUST.O_DATA.O_ID_NO)) 
            || (!DCCS0132.CI_NAME.equalsIgnoreCase(CICCUST.O_DATA.O_CI_NM))) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_INPUT_ID_INF_ERR);
        }
    }
    public void B015_RESET_PASSWORD() throws IOException,SQLException,Exception {
        if (DCCS0132.NEW_PSW.trim().length() > 0 
            && ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10103"))) {
            WS_COND_FLG.RESET_FLG = 'Y';
            IBS.init(SCCGWA, DCCUPSWM);
            DCCUPSWM.FUNC = 'R';
            DCCUPSWM.OLD_AGR_NO = DCCS0132.CARD_NO;
            DCCUPSWM.AGR_NO = DCCS0132.CARD_NO;
            if (DCCS0132.CARD_NO == null) DCCS0132.CARD_NO = "";
            JIBS_tmp_int = DCCS0132.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCS0132.CARD_NO += " ";
            DCCUPSWM.AGR_NO_6 = DCCS0132.CARD_NO.substring(14 - 1, 14 + 6 - 1);
            DCCUPSWM.PSW_TYP = 'T';
            DCCUPSWM.CARD_PSW_NEW = DCCS0132.NEW_PSW;
            DCCUPSWM.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            DCCUPSWM.ID_NO = CICCUST.O_DATA.O_ID_NO;
            DCCUPSWM.CI_NM = CICCUST.O_DATA.O_CI_NM;
            S000_CALL_DCZUPSWM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUPSWM.O_NEW_PSW);
        }
    }
    public void B020_ADD_LOST_CHECK() throws IOException,SQLException,Exception {
        if (DCCUCINF.CARD_PROD_FLG == 'S' 
            && DCCUCINF.CARD_STS == '3') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CITICARD_NOT_RECEVIE);
        }
        if (DCCUCINF.CARD_STS != 'N') {
            if (DCCUCINF.CARD_STS == '2') {
            } else {
                CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
            }
        }
        if (DCCUCINF.CARD_PROD_FLG == 'Y') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_ALICARD_NOT_LOSS);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCS0132.FUNC_CODE == 'A' 
            && DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_HAS_LOST_LOST);
        }
        if (DCCS0132.HLD_FLG == 'Y') {
            if (DCCUCINF.ADSC_TYPE != 'P') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_UNABLE_HLD_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_VARIABLES.HLD_FLG = '0';
        } else {
            WS_VARIABLES.HLD_FLG = '1';
        }
    }
    public void B020_ADD_LOST_REPORTING() throws IOException,SQLException,Exception {
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            B050_QUERY_BP_REC_PROC();
            if (pgmRtn) return;
            WS_VARIABLES.LOST_NO = BPCPLOSS.DATA_INFO.LOS_NO;
            B051_UPD_BPTLOSS_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCUCINF.ADSC_TYPE);
        if (DCCUCINF.ADSC_TYPE == 'C') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = DCCS0132.CARD_NO;
            CICQACRL.DATA.AC_REL = "04";
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            WS_VARIABLES.BAL_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_VARIABLES.BAL_CARD_NO = DCCS0132.CARD_NO;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.BAL_CARD_NO);
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = WS_VARIABLES.BAL_CARD_NO;
        DDCIQBAL.DATA.CCY = "156";
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
        R000_GEN_LOST_NO();
        if (pgmRtn) return;
        B050_ADD_WRITE_BPTLOSS_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS0132.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DCRCDDAT, DCRCDDAT);
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = "0" + DCRCDDAT.CARD_STSW.substring(1);
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 2 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(2 + 1 - 1);
        if (DCCS0132.HLD_FLG == 'Y') {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 14 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(14 + 1 - 1);
        } else {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 14 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(14 + 1 - 1);
        }
        if (WS_COND_FLG.RESET_FLG == 'Y') {
            DCRCDDAT.TRAN_PIN_DAT = DCCUPSWM.O_NEW_PSW;
            DCRCDDAT.PIN_ERR_CNT = 0;
            DCRCDDAT.PIN_LCK_DT = 0;
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 9 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(9 + 1 - 1);
            DCRCDDAT.PSW_TYP = 'N';
        }
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B030_CANCEL_LOST_REPORTING() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS0132.LOST_NO);
        if (DCCS0132.LOST_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_LOST_MUST_INPUT);
        }
        if (DCCUCINF.CARD_STS == 'C') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (!DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_WRIT_LOST);
        }
        if (DCCUCINF.CARD_PROD_FLG == 'S') {
            IBS.init(SCCGWA, DCRCTCDC);
            DCRCTCDC.KEY.OLD_CARD_NO = DCCS0132.CARD_NO;
            T000_READ_DCTCTCDC();
            if (pgmRtn) return;
            if (WS_COND_FLG.TBL_FLAG == 'Y') {
                CEP.ERR(SCCGWA, ERROR_MSG.DC_CTCARD_CDC_REC);
            }
        }
        B050_QUERY_BP_REC_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "N888CHA");
        CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_NO);
        CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.AC);
        CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.STS);
        if (BPCPLOSS.DATA_INFO.STS != '1') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_LOST_NO_NOT_NORMAL);
        }
        if (!BPCPLOSS.DATA_INFO.AC.equalsIgnoreCase(DCCS0132.CARD_NO)) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_NO_CORRESPOND_ERR);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCPLOSS.DATA_INFO.LOS_ORG;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
        if (BPCPQORG.BBR != BPCPORUP.DATA_INFO.BBR) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_LOST_BR_N_CONSISTENT);
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS0132.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DCRCDDAT, DCRCDDAT);
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 14 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(14 + 1 - 1);
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 2 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(2 + 1 - 1);
        if (WS_COND_FLG.RESET_FLG == 'Y') {
            DCRCDDAT.TRAN_PIN_DAT = DCCUPSWM.O_NEW_PSW;
            DCRCDDAT.PIN_ERR_CNT = 0;
            DCRCDDAT.PIN_LCK_DT = 0;
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 9 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(9 + 1 - 1);
            DCRCDDAT.PSW_TYP = 'N';
        }
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCDDAT();
        if (pgmRtn) return;
        WS_VARIABLES.HLD_FLG = '1';
        B050_CANCEL_WRITE_BPTLOSS_PROC();
        if (pgmRtn) return;
    }
    public void B040_R_ADD_LOST_REPORTING() throws IOException,SQLException,Exception {
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (!DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_WRIT_LOST);
        }
        B050_QUERY_BP_REC_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCPLOSS.DATA_INFO.LOS_ORG;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PR0067");
        CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_ORG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
        if (BPCPQORG.BBR != BPCPORUP.DATA_INFO.BBR) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_LOST_ONE_BK);
        }
        CEP.TRC(SCCGWA, "N-RADD");
        CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_NO);
        DCCS0132.LOST_NO = BPCPLOSS.DATA_INFO.LOS_NO;
        B050_CANCEL_WRITE_BPTLOSS_PROC();
        if (pgmRtn) return;
        B020_ADD_LOST_CHECK();
        if (pgmRtn) return;
        B020_ADD_LOST_REPORTING();
        if (pgmRtn) return;
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            B100_FEE_PROCESS();
            if (pgmRtn) return;
        }
        B099_APPLE_PAY_PROC();
        if (pgmRtn) return;
    }
    public void B050_QUERY_BP_REC_PROC() throws IOException,SQLException,Exception {
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCS0132.FUNC_CODE == 'A' 
            && DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, BPCPLOSS);
            BPCPLOSS.DATA_INFO.AC = DCCS0132.CARD_NO;
            BPCPLOSS.DATA_INFO.STS = '2';
            BPCPLOSS.INFO.FUNC = 'I';
            BPCPLOSS.INFO.INDEX_FLG = "2";
            S000_CALL_BPZPLOSS();
            if (pgmRtn) return;
        }
        if (DCCS0132.FUNC_CODE == 'R') {
            IBS.init(SCCGWA, BPCPLOSS);
            BPCPLOSS.DATA_INFO.AC = DCCS0132.CARD_NO;
            BPCPLOSS.DATA_INFO.STS = '1';
            BPCPLOSS.INFO.FUNC = 'I';
            BPCPLOSS.INFO.INDEX_FLG = "2";
            S000_CALL_BPZPLOSS();
            if (pgmRtn) return;
        }
        if (DCCS0132.FUNC_CODE == 'C') {
            IBS.init(SCCGWA, BPCPLOSS);
            BPCPLOSS.INFO.FUNC = 'I';
            BPCPLOSS.INFO.INDEX_FLG = "1";
            BPCPLOSS.DATA_INFO.LOS_NO = DCCS0132.LOST_NO;
            S000_CALL_BPZPLOSS();
            if (pgmRtn) return;
        }
    }
    public void B050_ADD_WRITE_BPTLOSS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLOSS);
        BPCSLOSS.FUNC = 'C';
        BPCSLOSS.LOS_NO = WS_VARIABLES.LOST_NO;
        BPCSLOSS.AC = DCCS0132.CARD_NO;
        if (DCCUCINF.CARD_MEDI == '1') {
            BPCSLOSS.BV_TYP = '2';
        } else if (DCCUCINF.CARD_MEDI == '2') {
            BPCSLOSS.BV_TYP = '1';
        } else if (DCCUCINF.CARD_MEDI == '3') {
            BPCSLOSS.BV_TYP = '1';
        }
        if (DCCUCINF.ADSC_TYPE == 'P') {
            BPCSLOSS.PER_FLG = '1';
        } else {
            BPCSLOSS.PER_FLG = '2';
            BPCSLOSS.BV_TYP = '3';
        }
        BPCSLOSS.AC_TYPE = "21";
        BPCSLOSS.STS = '1';
        BPCSLOSS.BV_CODE = DCCUCINF.BV_CD_NO;
        BPCSLOSS.ID_TYP = DCCS0132.ID_TYPE;
        BPCSLOSS.ID_NO = DCCS0132.ID_NO;
        BPCSLOSS.CI_CNM = DCCS0132.CI_NAME;
        BPCSLOSS.LOS_TELE = DCCS0132.PHONE;
        BPCSLOSS.OPEN_BR = DCCUCINF.ISSU_BR;
        BPCSLOSS.OPEN_AMT = DDCIQBAL.DATA.CURR_BAL;
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y' 
            || (DCCS0132.AGENT_FLG == '1' 
            || DCCS0132.AGENT_FLG == 'Y')) {
            if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
                BPCSLOSS.OTH_NM = AGENT_AREA_AGENT_AREA.CI_NM;
                BPCSLOSS.OTH_ID_TYP = AGENT_AREA_AGENT_AREA.ID_TYP;
                BPCSLOSS.OTH_ID_NO = AGENT_AREA_AGENT_AREA.ID_NO;
                BPCSLOSS.OTH_TELE = AGENT_AREA_AGENT_AREA.PHONE;
            } else {
                BPCSLOSS.OTH_ID_TYP = DCCS0132.AGENT_ID_TYPE;
                BPCSLOSS.OTH_ID_NO = DCCS0132.AGENT_ID_NO;
                BPCSLOSS.OTH_NM = DCCS0132.AGENT_CI_NAME;
                BPCSLOSS.OTH_TELE = DCCS0132.AGENT_PHONE;
            }
        }
        BPCSLOSS.LOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLOSS.LOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.LOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.LOS_RMK = DCCS0132.LOS_CA;
        BPCSLOSS.HLD_FLG = WS_VARIABLES.HLD_FLG;
        S000_CALL_BPZSLOSS();
        if (pgmRtn) return;
    }
    public void B050_CANCEL_WRITE_BPTLOSS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLOSS);
        BPCSLOSS.LOS_NO = DCCS0132.LOST_NO;
        BPCSLOSS.FUNC = 'U';
        BPCSLOSS.STS = '3';
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y' 
            || (DCCS0132.AGENT_FLG == '1' 
            || DCCS0132.AGENT_FLG == 'Y')) {
            if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
                BPCSLOSS.RLOS_NM = AGENT_AREA_AGENT_AREA.CI_NM;
                BPCSLOSS.RLOS_ID_TYP = AGENT_AREA_AGENT_AREA.ID_TYP;
                BPCSLOSS.RLOS_ID_NO = AGENT_AREA_AGENT_AREA.ID_NO;
                BPCSLOSS.RLOS_TELE = AGENT_AREA_AGENT_AREA.PHONE;
            } else {
                BPCSLOSS.RLOS_ID_TYP = DCCS0132.AGENT_ID_TYPE;
                BPCSLOSS.RLOS_ID_NO = DCCS0132.AGENT_ID_NO;
                BPCSLOSS.RLOS_NM = DCCS0132.AGENT_CI_NAME;
                BPCSLOSS.RLOS_TELE = DCCS0132.AGENT_PHONE;
            }
        } else {
            BPCSLOSS.RLOS_NM = DCCS0132.CI_NAME;
            BPCSLOSS.RLOS_ID_TYP = DCCS0132.ID_TYPE;
            BPCSLOSS.RLOS_ID_NO = DCCS0132.ID_NO;
        }
        BPCSLOSS.HLD_FLG = WS_VARIABLES.HLD_FLG;
        BPCSLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLOSS.RLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
        S000_CALL_BPZSLOSS();
        if (pgmRtn) return;
    }
    public void B051_UPD_BPTLOSS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLOSS);
        BPCSLOSS.LOS_NO = WS_VARIABLES.LOST_NO;
        CEP.TRC(SCCGWA, "N-ORAL-CANCEL");
        BPCSLOSS.FUNC = 'U';
        BPCSLOSS.STS = '4';
        BPCSLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLOSS.RLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
        S000_CALL_BPZSLOSS();
        if (pgmRtn) return;
    }
    public void B060_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DCCS0132.CARD_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCS0132.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCCUCINF.CARD_OWN_CINO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = HIS_COPYBOOK;
        if (DCCS0132.FUNC_CODE == 'A' 
            || DCCS0132.FUNC_CODE == 'R') {
            BPCPNHIS.INFO.TX_TYP_CD = "P135";
            BPCPNHIS.INFO.TX_RMK = HIS_REMARK_1;
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P113";
            BPCPNHIS.INFO.TX_RMK = HIS_REMARK_2;
        }
        BPCPNHIS.INFO.FMT_ID_LEN = 489;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.NEW_DAT_PT = DCRCDDAT;
        BPCPNHIS.INFO.OLD_DAT_PT = DCRCDDAT;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B070_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG.WS_OUTPUT);
        BPCPQORG.WS_OUTPUT.CARD_NO = DCCS0132.CARD_NO;
        BPCPQORG.WS_OUTPUT.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPQORG.WS_OUTPUT.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        BPCPQORG.WS_OUTPUT.LAST_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (DCCS0132.FUNC_CODE == 'A' 
            || DCCS0132.FUNC_CODE == 'R') {
            BPCPQORG.WS_OUTPUT.LOST_NO0 = WS_VARIABLES.LOST_NO;
        }
        if (DCCS0132.FUNC_CODE == 'C') {
            BPCPQORG.WS_OUTPUT.LOST_NO0 = DCCS0132.LOST_NO;
        }
        CEP.TRC(SCCGWA, "N999-OUTPUT-LOST-NO");
        CEP.TRC(SCCGWA, BPCPQORG.WS_OUTPUT.LOST_NO0);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCPQORG.WS_OUTPUT;
        SCCFMT.DATA_LEN = 61;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B099_APPLE_PAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRAPPLC);
        IBS.init(SCCGWA, SCCTPCL);
        T000_STARTBR_DCTAPPLC();
        if (pgmRtn) return;
        T000_READNEXT_DCTAPPLC();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG != 'N') {
            while (WS_COND_FLG.TBL_FLAG != 'N') {
                CEP.TRC(SCCGWA, DCRAPPLC.MPAN);
                CEP.TRC(SCCGWA, DCRAPPLC.MPAN_ID);
                T000_REWRITE_DCTAPPLC();
                if (pgmRtn) return;
                WS_VARIABLES.APPLE_CNT += 1;
                if (WS_VARIABLES.APPLE_CNT == 1) {
                    SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF.MPAN_ID1 = DCRAPPLC.MPAN_ID;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_CHG_INFO);
                } else if (WS_VARIABLES.APPLE_CNT == 2) {
                    SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF.MPAN_ID2 = DCRAPPLC.MPAN_ID;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_CHG_INFO);
                } else if (WS_VARIABLES.APPLE_CNT == 3) {
                    SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF.MPAN_ID3 = DCRAPPLC.MPAN_ID;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_CHG_INFO);
                } else if (WS_VARIABLES.APPLE_CNT == 4) {
                    SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF.MPAN_ID4 = DCRAPPLC.MPAN_ID;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_CHG_INFO);
                } else if (WS_VARIABLES.APPLE_CNT == 5) {
                    SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF.MPAN_ID5 = DCRAPPLC.MPAN_ID;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_CHG_INFO);
                } else if (WS_VARIABLES.APPLE_CNT == 6) {
                    SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF.MPAN_ID6 = DCRAPPLC.MPAN_ID;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_CHG_INFO);
                } else if (WS_VARIABLES.APPLE_CNT == 7) {
                    SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF.MPAN_ID7 = DCRAPPLC.MPAN_ID;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_CHG_INFO);
                } else if (WS_VARIABLES.APPLE_CNT == 8) {
                    SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF.MPAN_ID8 = DCRAPPLC.MPAN_ID;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_CHG_INFO);
                }
                T000_READNEXT_DCTAPPLC();
                if (pgmRtn) return;
            }
        }
        T000_ENDBR_DCTAPPLC();
        if (pgmRtn) return;
    }
    public void B099_01_BROADCAST_PROC() throws IOException,SQLException,Exception {
        SCCTPCL.SERV_AREA.OBJ_SYSTEM = "ESBP";
        SCCTPCL.SERV_AREA.TRANS_FLG = '0';
        SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
        SCCTPCL.INP_AREA.BD_BROADCAST.FUNCTION_ID = "IBS_0203";
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST);
        SCCTPCL.INP_AREA.BD_BROADCAST.RECE_SYS_ID = "060800";
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST);
        SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF.CARD_TRAN_TYPE = "02";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_CHG_INFO);
        SCCTPCL.INP_AREA.SERV_DATA_LEN = 53310;
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void B100_FEE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        if (DCCUCINF.ADSC_TYPE == 'C') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = WS_VARIABLES.BAL_CARD_NO;
        }
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = DCCS0132.CARD_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "156";
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = "0260132";
        BPCFFTXI.TX_DATA.CI_NO = DCCUCINF.CARD_OWN_CINO;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        if (DCCUCINF.CARD_LNK_TYP == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "00";
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "01";
        }
        BPCTCALF.INPUT_AREA.PROD_CODE = DCCUCINF.PROD_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = DCCUCINF.PROD_CD;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.PROD_CODE1);
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_CI = DCCUCINF.CARD_OWN_CINO;
        BPCTCALF.INPUT_AREA.TX_CCY = "156";
        if (DCCUCINF.CARD_MEDI == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "04";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "05";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        }
        BPCTCALF.INPUT_AREA.TX_AC = DCCS0132.CARD_NO;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
    }
    public void R000_GEN_LOST_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "LOSEQ";
        BPCSGSEQ.CODE = "01";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_VARIABLES.LOST_NO == null) WS_VARIABLES.LOST_NO = "";
        JIBS_tmp_int = WS_VARIABLES.LOST_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_VARIABLES.LOST_NO += " ";
        WS_VARIABLES.LOST_NO = JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1) + WS_VARIABLES.LOST_NO.substring(2);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_VARIABLES.LOST_NO == null) WS_VARIABLES.LOST_NO = "";
        JIBS_tmp_int = WS_VARIABLES.LOST_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_VARIABLES.LOST_NO += " ";
        WS_VARIABLES.LOST_NO = WS_VARIABLES.LOST_NO.substring(0, 3 - 1) + JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1) + WS_VARIABLES.LOST_NO.substring(3 + 2 - 1);
        if (WS_VARIABLES.LOST_NO == null) WS_VARIABLES.LOST_NO = "";
        JIBS_tmp_int = WS_VARIABLES.LOST_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_VARIABLES.LOST_NO += " ";
        WS_VARIABLES.LOST_NO = WS_VARIABLES.LOST_NO.substring(0, 5 - 1) + "04" + WS_VARIABLES.LOST_NO.substring(5 + 2 - 1);
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_VARIABLES.LOST_NO == null) WS_VARIABLES.LOST_NO = "";
        JIBS_tmp_int = WS_VARIABLES.LOST_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_VARIABLES.LOST_NO += " ";
        WS_VARIABLES.LOST_NO = WS_VARIABLES.LOST_NO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1) + WS_VARIABLES.LOST_NO.substring(7 + 7 - 1);
    }
    public void S000_CALL_DCZUCDLP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CHECK-BR", DCCUCDLP);
        if (DCCUCDLP.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCDLP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-PSW-MAINTAIN", DCCUPSWM);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
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
    public void S000_CALL_BPZSLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-LOSS-INFO", BPCSLOSS);
        if (BPCSLOSS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSLOSS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_F_CAL_FEE, BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DDCIQBAL.RC);
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSAGEN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCCWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
    }
    public void S000_SEND_MASSAGE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "======TEST======");
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        SCCWRMSG.CI_NO = DCCUCINF.CARD_HLDR_CINO;
        SCCWRMSG.AC = DCCS0132.CARD_NO;
        if (DCCS0132.CARD_NO == null) DCCS0132.CARD_NO = "";
        JIBS_tmp_int = DCCS0132.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCS0132.CARD_NO += " ";
        SCCWRMSG.ACL4 = DCCS0132.CARD_NO.substring(16 - 1, 16 + 4 - 1);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) SCCWRMSG.YEAR = 0;
        else SCCWRMSG.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.MONTH = 0;
        else SCCWRMSG.MONTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) SCCWRMSG.DAY = 0;
        else SCCWRMSG.DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) SCCWRMSG.YEAR2 = 0;
        else SCCWRMSG.YEAR2 = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.MONTH2 = 0;
        else SCCWRMSG.MONTH2 = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) SCCWRMSG.DAY2 = 0;
        else SCCWRMSG.DAY2 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) SCCWRMSG.HOUR = 0;
        else SCCWRMSG.HOUR = Short.parseShort(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) SCCWRMSG.MINUTE = 0;
        else SCCWRMSG.MINUTE = Short.parseShort(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.SECOND = 0;
        else SCCWRMSG.SECOND = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        SCCWRMSG.AP_CODE = "362";
        CEP.TRC(SCCGWA, SCCWRMSG.ACL4);
        CEP.TRC(SCCGWA, SCCWRMSG.DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        CEP.TRC(SCCGWA, SCCWRMSG.CI_NO);
        CEP.TRC(SCCGWA, SCCWRMSG.JRNNO);
        CEP.TRC(SCCGWA, SCCWRMSG.AP_CODE);
        if (DCCS0132.FUNC_CODE == 'A' 
            || DCCS0132.FUNC_CODE == 'R') {
            CEP.TRC(SCCGWA, "ADD");
            S000_CALL_SCCWRMSG();
            if (pgmRtn) return;
        }
        if (DCCS0132.FUNC_CODE == 'C') {
            CEP.TRC(SCCGWA, "DELETE");
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_COND_FLG.TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTAPPLC() throws IOException,SQLException,Exception {
        DCRAPPLC.SPAN = DCCS0132.CARD_NO;
        DCTAPPLC_BR.rp = new DBParm();
        DCTAPPLC_BR.rp.TableName = "DCTAPPLC";
        DCTAPPLC_BR.rp.where = "SPAN = :DCRAPPLC.SPAN "
            + "AND STS = '01'";
        DCTAPPLC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DCRAPPLC, this, DCTAPPLC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        }
    }
    public void T000_READNEXT_DCTAPPLC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRAPPLC, this, DCTAPPLC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        }
    }
    public void T000_REWRITE_DCTAPPLC() throws IOException,SQLException,Exception {
        DCRAPPLC.STS = "02";
        DCRAPPLC.CHANGE_CHANL_ID = SCCGWA.COMM_AREA.CHNL;
        if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) DCRAPPLC.UPDTBL_DATE = 0;
        else DCRAPPLC.UPDTBL_DATE = Integer.parseInt(SCCGWA.COMM_AREA.TL_ID);
        DCRAPPLC.UPDTBL_TLR = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = DCRAPPLC.UPDTBL_TLR.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) DCRAPPLC.UPDTBL_TLR = "0" + DCRAPPLC.UPDTBL_TLR;
        CEP.TRC(SCCGWA, "N999-REW");
        DCTAPPLC_RD = new DBParm();
        DCTAPPLC_RD.TableName = "DCTAPPLC";
        IBS.REWRITE(SCCGWA, DCRAPPLC, DCTAPPLC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTAPPLC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTAPPLC_BR);
    }
    public void T000_READ_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        IBS.READ(SCCGWA, DCRCTCDC, DCTCTCDC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
        CEP.TRC(SCCGWA, "===DK====");
        if (SCCGWA.COMM_AREA.EXCP_FLG != 'Y' 
            && SCCCALL.RET_FLG != 'Y') {
            if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'N' 
                || SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'W') {
                S000_SEND_MASSAGE_PROC();
                if (pgmRtn) return;
            }
        }
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
