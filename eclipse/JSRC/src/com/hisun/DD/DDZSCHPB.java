package com.hisun.DD;

import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCHPB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    DBParm DDTVCH_RD;
    DBParm DDTCCY_RD;
    DBParm DDTPBBL_RD;
    brParm DDTCCY_BR = new brParm();
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DD";
    String K_CHA_PSBK_FMT = "DD051";
    String K_PRT_COV_FMT = "DD013";
    String K_WRITE_MAGNET_FMT = "DD050";
    String K_HIS_CPB_NM = "DDCHCHPB";
    String K_CHK_STS_TBL = "5530";
    String K_HIS_RMKS = "CHANGE PASSBOOK        ";
    String K_HIS_RMKS_1 = "DZB CREATE        ";
    String CPN_I_PSBK_PROC = "DD-I-PSBK-PROC";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    String WS_TS_REC = " ";
    short WS_LEN = 0;
    String WS_AC = " ";
    char WS_RCCY_END = ' ';
    char WS_TABLE_FLG = ' ';
    char WS_PBBL_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCHCHPB DDCCHPBO = new DDCHCHPB();
    DDCHCHPB DDCCHPBN = new DDCHCHPB();
    DDCOPCHP DDCOPCHP = new DDCOPCHP();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    CICACCU CICACCU = new CICACCU();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCCGAC BPCCGAC = new BPCCGAC();
    CICCUST CICCUST = new CICCUST();
    CICSACR CICSACR = new CICSACR();
    CICQACAC CICQACAC = new CICQACAC();
    DDCOPHAD DDCOPHAD = new DDCOPHAD();
    DDCOPMAG DDCOPMAG = new DDCOPMAG();
    DDCOPUPT DDCOPUPT = new DDCOPUPT();
    DDCOUPT DDCOUPT = new DDCOUPT();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    CICQACRL CICQACRL = new CICQACRL();
    CICSACRL CICSACRL = new CICSACRL();
    CICQACRI CICQACRI = new CICQACRI();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUCINF DCCUCINF = new DCCUCINF();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDRMST DDRMST = new DDRMST();
    DDRVCH DDRVCH = new DDRVCH();
    DDRCCY DDRCCY = new DDRCCY();
    DDRPBBL DDRPBBL = new DDRPBBL();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSCHPB DDCSCHPB;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DDCSCHPB DDCSCHPB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCHPB = DDCSCHPB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCHPB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCHPB.CARD_NO);
        CEP.TRC(SCCGWA, DDCSCHPB.AC);
        CEP.TRC(SCCGWA, DDCSCHPB.AC_CNAME);
        CEP.TRC(SCCGWA, DDCSCHPB.AC_ENAME);
        CEP.TRC(SCCGWA, DDCSCHPB.PSBK_OLD);
        CEP.TRC(SCCGWA, DDCSCHPB.PSBK_NEW);
        CEP.TRC(SCCGWA, DDCSCHPB.CHA_REASON);
        CEP.TRC(SCCGWA, DDCSCHPB.LOST_NO);
        CEP.TRC(SCCGWA, DDCSCHPB.PAY_MTH);
        CEP.TRC(SCCGWA, DDCSCHPB.PB_PSW);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCSCHPB.CHA_REASON != 'D') {
            B030_GET_AC_INF_PROC();
            if (pgmRtn) return;
            B035_CHK_AC_STS();
            if (pgmRtn) return;
            B040_CI_INF_PROC();
            if (pgmRtn) return;
            B050_CHECK_STS_TBL_PROC();
            if (pgmRtn) return;
            B080_CHA_PSBK_PROC();
            if (pgmRtn) return;
            B098_UPD_MST_INF_PROC();
            if (pgmRtn) return;
            B170_NFIN_TXN_HIS_PROC();
            if (pgmRtn) return;
        } else {
            B010_CHK_CARD_NO_PROC();
            if (pgmRtn) return;
            B020_GET_CI_NO_PROC();
            if (pgmRtn) return;
            B040_CI_INF_PROC();
            if (pgmRtn) return;
            B020_GENERATE_ACNO();
            if (pgmRtn) return;
            B020_CUS_RLT_PROC();
            if (pgmRtn) return;
            B020_CUS_REL_PROC();
            if (pgmRtn) return;
            B020_ADD_MST_PROC();
            if (pgmRtn) return;
            B020_GEN_DZB_PROC();
            if (pgmRtn) return;
            B099_OUTPUT_PROC();
            if (pgmRtn) return;
            B180_NFIN_TXN_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCSCHPB.AC;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        DDCSCHPB.AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DDCSCHPB.AC);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCHPB.AC);
        CEP.TRC(SCCGWA, DDCSCHPB.PSBK_OLD);
        CEP.TRC(SCCGWA, DDCSCHPB.PSBK_NEW);
        if (DDCSCHPB.CHA_REASON == 'C') {
            if (DDCSCHPB.CARD_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CARD_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DDCSCHPB.CARD_NO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCSCHPB.CARD_NO);
            CEP.TRC(SCCGWA, DCCUCINF.CARD_PHY_TYP);
            if (DCCUCINF.CARD_PHY_TYP != 'P') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NO_CANCEL_PSBK;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSCHPB.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B010_GET_RAC_PROC();
        if (pgmRtn) return;
        if (DDCSCHPB.CHA_REASON != 'D') {
            IBS.init(SCCGWA, DDRVCH);
            DDRVCH.KEY.CUS_AC = DDCSCHPB.AC;
            T000_READ_DDTVCH();
            if (pgmRtn) return;
            if (WS_TABLE_FLG == 'Y' 
                && DDCSCHPB.CHA_REASON != 'L') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_LOST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_TABLE_FLG == 'N' 
                && DDCSCHPB.CHA_REASON == 'L') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_NOT_LOST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSCHPB.CHA_REASON != 'D') {
                if (DDCSCHPB.CHA_REASON != 'C' 
                    && CICQACRL.RC.RC_CODE != 0 
                    && DDCSCHPB.PSBK_NEW.trim().length() == 0) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NPSBNO_MUST_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_CHK_CARD_NO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DDCSCHPB.AC;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STS != 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VCH_ORAL_LOSS_CNOT);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.VCH_FORMAL_LOSS_CNOT_TR);
        }
        R000_GET_ACO_AC();
        if (pgmRtn) return;
    }
    public void B010_GET_RAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        WS_AC = DDCSCHPB.AC;
        CICQACRL.DATA.REL_AC_NO = DDCSCHPB.AC;
        CICQACRL.DATA.AC_REL = "12";
        CICQACRL.FUNC = '4';
        CICQACRL.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
        CEP.TRC(SCCGWA, DDCSCHPB.AC);
        if (CICQACRL.RC.RC_CODE == 0) {
            B010_CHK_CARD_NO_PROC();
            if (pgmRtn) return;
            DDCSCHPB.AC = CICQACRL.O_DATA.O_AC_NO;
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
            if (DDCSCHPB.CHA_REASON == 'D') {
                WS_ERR_MSG = CICMSG_ERROR_MSG.CI_ACRL_INF_FND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
        }
    }
    public void B020_GET_CI_NO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = DDCSCHPB.AC;
        CICQACRI.FUNC = 'A';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
    }
    public void B020_GENERATE_ACNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.AC_KIND = '1';
        BPCCGAC.DATA.CI_AC_TYPE = '1';
        BPCCGAC.DATA.CI_AC_FLG = '6';
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        CEP.TRC(SCCGWA, BPCCGAC.RC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_AC);
    }
    public void B020_CUS_RLT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = BPCCGAC.DATA.CI_AC;
        CEP.TRC(SCCGWA, CICSACR.DATA.AGR_NO);
        CICSACR.DATA.ENTY_TYP = '1';
        CICSACR.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        CICSACR.DATA.CNTRCT_TYP = "199";
        CICSACR.DATA.CCY = "156";
        CICSACR.DATA.STSW = "10010000";
        CICSACR.DATA.PROD_CD = "1202040101";
        CICSACR.DATA.FRM_APP = "DD";
        CICSACR.DATA.SHOW_FLG = 'N';
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        CEP.TRC(SCCGWA, CICSACR.RC.RC_CODE);
        if (CICSACR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void B020_CUS_REL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.DATA.AC_NO = BPCCGAC.DATA.CI_AC;
        CICSACRL.DATA.AC_REL = "12";
        CICSACRL.DATA.REL_AC_NO = DDCSCHPB.AC;
        CICSACRL.DATA.DEFAULT = '0';
        CICSACRL.DATA.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACRL.FUNC = 'A';
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAAA");
        if (CICSACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B020_ADD_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = BPCCGAC.DATA.CI_AC;
        DDRMST.OWNER_BK = BPCPORUP.DATA_INFO.BR;
        DDRMST.PROD_CODE = "1202040101";
        DDRMST.AC_STS = 'N';
        DDRMST.AC_STS_WORD = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        DDRMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.OPEN_DP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        DDRMST.OWNER_BR = BPCPORUP.DATA_INFO.BBR;
        DDRMST.OWNER_BRDP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRMST.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.CARD_FLG = 'N';
        DDRMST.AC_TYPE = 'B';
        DDRMST.CASH_FLG = '4';
        DDRMST.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        DDRMST.CLOSE_DATE = 0;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_FN_DATE = 0;
        DDRMST.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GEN_DZB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = BPCCGAC.DATA.CI_AC;
        DDRVCH.VCH_TYPE = '2';
        DDRVCH.PAY_TYPE = '5';
        DDRVCH.OWNER_BK = BPCPORUP.DATA_INFO.BR;
        DDRVCH.PAY_PSWD = " ";
        DDRVCH.PAY_IDTYPE = CICCUST.O_DATA.O_ID_TYPE;
        DDRVCH.PAY_IDNO = CICCUST.O_DATA.O_ID_NO;
        DDRVCH.PSBK_NO = "0000000000000001";
        DDRVCH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVCH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        R000_PRT_HEAD_PAGE();
        if (pgmRtn) return;
        R000_WRITE_MAGNET();
        if (pgmRtn) return;
        R000_PRT_FIRST_BAL_CARDPB();
        if (pgmRtn) return;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRVCH, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_CHK_JOIN_CUS_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DDCSCHPB.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.JOIN_CUS_FLG);
        if (DCCUCINF.JOIN_CUS_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JOINCARD_NOT_PB;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHK_PSBK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSCHPB.AC;
        T000_READ_DDTVCH1();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B025_CHK_PSBK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSCHPB.AC;
        T000_READ_DDTVCH1();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REISSU_PB_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSCHPB.AC;
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
    }
    public void B035_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSCHPB.AC;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
    }
    public void B050_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        if (DDCSCHPB.CHA_REASON != 'L') {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 24 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(24 + 1 - 1);
        }
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B080_CHA_PSBK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.AC = DDCSCHPB.AC;
        DDCIPSBK.FUNC = 'C';
        DDCIPSBK.AC_ENAME = DDCSCHPB.AC_ENAME;
        DDCIPSBK.AC_CNAME = DDCSCHPB.AC_CNAME;
        DDCIPSBK.CARD_NO = DDCSCHPB.CARD_NO;
        DDCIPSBK.PSBK_NO = DDCSCHPB.PSBK_OLD;
        CEP.TRC(SCCGWA, DDCIPSBK.PSBK_NO);
        if (DDCSCHPB.CHA_REASON != 'C') {
            DDCIPSBK.PSBK_NO_NEW = DDCSCHPB.PSBK_NEW;
        }
        CEP.TRC(SCCGWA, DDCIPSBK.PSBK_NO);
        DDCIPSBK.CHA_REASON = DDCSCHPB.CHA_REASON;
        DDCIPSBK.LOST_NO = DDCSCHPB.LOST_NO;
        DDCIPSBK.AC_ATTR = DDRMST.AC_TYPE;
        DDCIPSBK.UPT_MMO = "P120";
        S000_CALL_DDZIPSBK();
        if (pgmRtn) return;
    }
    public void B098_UPD_MST_INF_PROC() throws IOException,SQLException,Exception {
        if (DDCSCHPB.CHA_REASON == 'L') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 7 - 1) + "000" + DDRMST.AC_STS_WORD.substring(7 + 3 - 1);
            DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTMST();
            if (pgmRtn) return;
        }
    }
    public void R000_PRT_HEAD_PAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPHAD);
        DDCOPHAD.AC = WS_AC;
        DDCOPHAD.ENAME = DCCUCINF.CARD_HLDR_ENM;
        DDCOPHAD.CNAME = DCCUCINF.CARD_HLDR_CNM;
        DDCOPHAD.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCOPHAD.PSBK_NO = "0000000000000001";
        DDCOPHAD.PSBK_SEQ = 0;
        DDCOPHAD.PAY_MTH = '1';
        DDCOPHAD.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCOPHAD.TL_ID = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, DDCOPHAD);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PRT_COV_FMT;
        SCCFMT.DATA_PTR = DDCOPHAD;
        SCCFMT.DATA_LEN = 581;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_WRITE_MAGNET() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPMAG);
        DDCOPMAG.AC = WS_AC;
        DDCOPMAG.PSBK_NO = "0000000000000001";
        DDCOPMAG.PSBK_SEQ = 0;
        CEP.TRC(SCCGWA, DDCOPMAG);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_WRITE_MAGNET_FMT;
        SCCFMT.DATA_PTR = DDCOPMAG;
        SCCFMT.DATA_LEN = 52;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_PRT_FIRST_BAL_CARDPB() throws IOException,SQLException,Exception {
        DDCOPUPT.CNT = 0;
        IBS.init(SCCGWA, DDRCCY);
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (WS_RCCY_END != 'E' 
            && SCCMPAG.FUNC != 'E') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 2 - 1) + "2" + DDRCCY.STS_WORD.substring(2 + 1 - 1);
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDTCCY_RD = new DBParm();
            DDTCCY_RD.TableName = "DDTCCY";
            IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
            IBS.init(SCCGWA, DDRPBBL);
            DDRPBBL.KEY.CUS_AC = DDRVCH.KEY.CUS_AC;
            DDRPBBL.KEY.CCY = DDRCCY.CCY;
            DDRPBBL.KEY.CCY_TYPE = DDRCCY.CCY_TYPE;
            T000_READ_UPD_DDTPBBL();
            if (pgmRtn) return;
            DDRPBBL.PSBK_BAL = DDRCCY.CURR_BAL;
            DDRPBBL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRPBBL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRPBBL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRPBBL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            if (WS_PBBL_FLAG == 'N') {
                T000_WRITE_DDTPBBL();
                if (pgmRtn) return;
            }
            DDCOPUPT.CNT += 1;
            IBS.init(SCCGWA, DDCOUPT);
            DDCOUPT.DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCOUPT.CCY = DDRCCY.CCY;
            DDCOUPT.CCY_TYPE = DDRCCY.CCY_TYPE;
            DDCOUPT.NUM += 1;
            DDCOUPT.AMT = 0;
            DDCOUPT.DRCR_FLG = 'C';
            DDCOUPT.AC_ATTR = DDCIPSBK.AC_ATTR;
            DDCOUPT.BALANCE = DDRCCY.CURR_BAL;
            DDCOUPT.REF = "P141";
            DDCOUPT.TL_ID = SCCGWA.COMM_AREA.TL_ID;
            R000_MPAGE_OUTPUT();
            if (pgmRtn) return;
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
    }
    public void B099_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPCHP);
        DDCOPCHP.AC = DDCSCHPB.AC;
        DDCOPCHP.CHA_REASON = DDCSCHPB.CHA_REASON;
        DDCOPCHP.ENAME = DDCSCHPB.AC_ENAME;
        DDCOPCHP.CNAME = DDCSCHPB.AC_CNAME;
        DDCOPCHP.PSBK_NO_OLD = " ";
        DDCOPCHP.PSBK_NO_NEW = DDRVCH.PSBK_NO;
        DDCOPCHP.LOST_NO = " ";
        CEP.TRC(SCCGWA, DDCOPCHP);
        CEP.TRC(SCCGWA, DDCOPCHP.LOST_NO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_CHA_PSBK_FMT;
        SCCFMT.DATA_PTR = DDCOPCHP;
        SCCFMT.DATA_LEN = 591;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B180_WRT_VCH_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRMST.PROD_CODE;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = DDRMST.PROD_CODE;
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = "CNY";
        BPCPOEWA.DATA.AMT_INFO[9-1].AMT = 1;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = DDCSCHPB.AC;
        BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPOEWA.DATA.REF_NO = " ";
    }
    public void B170_NFIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCCHPBO);
        DDCCHPBO.PSBK_NO = DDCSCHPB.PSBK_OLD;
        IBS.init(SCCGWA, DDCCHPBN);
        DDCCHPBN.PSBK_NO = DDCSCHPB.PSBK_NEW;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = DDCCHPBO;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCCHPBN;
        BPCPNHIS.INFO.AC = DDCSCHPB.AC;
        if (CICQACRL.RC.RC_CODE == 0) {
            BPCPNHIS.INFO.AC = WS_AC;
        }
        BPCPNHIS.INFO.TX_TOOL = DDCSCHPB.CARD_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CCY = CICACCU.DATA.CCY;
        if (CICACCU.DATA.CCY.equalsIgnoreCase("156") 
            && CICACCU.DATA.CCY.trim().length() > 0) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P120";
        if (DDCSCHPB.CHA_REASON == 'L') {
            BPCPNHIS.INFO.TX_TYP_CD = "P105";
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_GET_ACO_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSCHPB.AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY_PROC();
        if (pgmRtn) return;
    }
    public void B180_NFIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = DDCSCHPB.AC;
        BPCPNHIS.INFO.TX_TOOL = DDCSCHPB.AC;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS_1;
        BPCPNHIS.INFO.TX_TYP_CD = "P141";
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 69;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCOUPT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DDCOUPT);
        SCCMPAG.DATA_LEN = 69;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_UPD_DDTPBBL() throws IOException,SQLException,Exception {
        DDTPBBL_RD = new DBParm();
        DDTPBBL_RD.TableName = "DDTPBBL";
        DDTPBBL_RD.col = "PSBK_BAL";
        DDTPBBL_RD.upd = true;
        IBS.READ(SCCGWA, DDRPBBL, DDTPBBL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PBBL_FLAG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PBBL_FLAG = 'N';
        }
    }
    public void T000_WRITE_DDTPBBL() throws IOException,SQLException,Exception {
        DDTPBBL_RD = new DBParm();
        DDTPBBL_RD.TableName = "DDTPBBL";
        DDTPBBL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRPBBL, DDTPBBL_RD);
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = WS_AC;
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC";
        DDTCCY_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RCCY_END = 'E';
        }
    }
    public void T000_READ_DDTCCY_PROC() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_PSBK_PROC, DDCIPSBK);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCHMPW);
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD", SCCHMPW);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.M_SET_PSW_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_SET_MTH_PROC() throws IOException,SQLException,Exception {
        DDCIPSBK.PAY_MTH = DDCSCHPB.PAY_MTH;
        CEP.TRC(SCCGWA, DDCSCHPB.PAY_MTH);
        if (DDCSCHPB.PAY_MTH == '2') {
            CEP.TRC(SCCGWA, DDCSCHPB.PB_PSW);
            CEP.TRC(SCCGWA, DDCIPSBK.AC);
            CEP.TRC(SCCGWA, DDCSCHPB.CARD_NO);
            if (DDCSCHPB.PB_PSW.trim().length() > 0) {
                IBS.init(SCCGWA, SCCHMPW);
                SCCHMPW.INP_DATA.SERV_ID = "E143";
                SCCHMPW.INP_DATA.AC_FLG = '0';
                CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.AC_FLG);
                SCCHMPW.INP_DATA.OLD_AC = DDCSCHPB.CARD_NO;
                SCCHMPW.INP_DATA.NEW_AC = DDCSCHPB.CARD_NO;
                SCCHMPW.INP_DATA.PIN_DA = DDCSCHPB.PB_PSW;
                S000_CALL_SCZHMPW();
                if (pgmRtn) return;
                DDCSCHPB.PB_PSW = SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW;
                CEP.TRC(SCCGWA, DDCSCHPB.PB_PSW);
            }
            DDCIPSBK.PSWD = DDCSCHPB.PB_PSW;
        }
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "PROD_CODE,AC_STS,AC_STS_WORD,AC_TYPE, LAST_TLR,LAST_DATE, UPDTBL_DATE,UPDTBL_TLR";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC "
            + "AND ( PSBK_STS = 'U' "
            + "OR PSBK_STS = 'W' )";
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        }
    }
    public void T000_READ_DDTVCH1() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
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
