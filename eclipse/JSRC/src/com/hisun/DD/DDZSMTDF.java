package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFCSTS;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPGDIN;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICMACR;
import com.hisun.CI.CICMAGT;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRL;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCMSG_ERROR_MSG;

public class DDZSMTDF {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD121";
    String K_OUTPUT_FMT2 = "DD122";
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "5122";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_HIS_COPYBOOK_NAME = "DDCSMTDF";
    String K_HIS_REMARKS = "ACTIVATION ACCOUNT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_MTDF_ADP_STRDATE = 0;
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    String WS_MTDF_AC = " ";
    double WS_MAX_AMT = 0;
    double WS_MAX_AMT1 = 0;
    double WS_MAX_AMT2 = 0;
    double WS_MAX_AMT3 = 0;
    double WS_MAX_AMT4 = 0;
    double WS_MAX_AMT5 = 0;
    short WS_CNT1 = 0;
    short WS_CNT2 = 0;
    short WS_CNT3 = 0;
    short WS_CNT4 = 0;
    short WS_CNT5 = 0;
    String WS_PQORG_TYP1 = " ";
    String WS_PQORG_TYP2 = " ";
    int WS_PQORG_SUPR_BR1 = 0;
    int WS_PQORG_SUPR_BR2 = 0;
    DDZSMTDF_WS_CHEK_INF[] WS_CHEK_INF = new DDZSMTDF_WS_CHEK_INF[5];
    char WS_CI_TYPE = ' ';
    char WS_MSTR_FLG = ' ';
    char WS_CCY_FLG = ' ';
    char WS_MSTR_DU_KEY_FLG = ' ';
    char WS_MSTR_OPEN_FLG = ' ';
    char WS_MSTR_SAME_DAY_FLG = ' ';
    char WS_MSTR_SAME_STRDATE_FLG = ' ';
    DDCSRATE DDCSRATE = new DDCSRATE();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRFHIS DDRFHIS = new DDRFHIS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDCOATAC DDCOATAC = new DDCOATAC();
    DDCSCINM DDCSCINM = new DDCSCINM();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMACR CICMACR = new CICMACR();
    CICACCU CICACCU = new CICACCU();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDRMSTR DDRMSTR1 = new DDRMSTR();
    DDCSMTDQ DDCSMTDQ = new DDCSMTDQ();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    CICMAGT CICMAGT = new CICMAGT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCSMTDF DDCSMTDF;
    public DDZSMTDF() {
        for (int i=0;i<5;i++) WS_CHEK_INF[i] = new DDZSMTDF_WS_CHEK_INF();
    }
    public void MP(SCCGWA SCCGWA, DDCSMTDF DDCSMTDF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSMTDF = DDCSMTDF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSMTDF return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B015_CALL_ACTY_PROC();
        if (pgmRtn) return;
        B020_GET_CI_INF_PROC();
        if (pgmRtn) return;
        B030_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B035_GET_CCY_INF_PROC();
        if (pgmRtn) return;
        B040_CHK_AC_STS();
        if (pgmRtn) return;
        B050_MSTR_INF_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.JRN_NO != 0) {
            B170_NFIN_TX_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMTDF.AC);
        if (DDCSMTDF.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSMTDF.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSMTDF.FLG == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCSMTDF.ADP_STRDATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if ((DDCSMTDF.ADP_STRDATE < SCCGWA.COMM_AREA.AC_DATE) 
            && (DDCSMTDF.OPT == '1')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DATE_LT_ACDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        WS_MTDF_AC = DDCSMTDF.AC;
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSMTDF.AC;
        T000_READ_MST_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = DDCSMTDF.AC;
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            DDCSMTDF.AC = CICQACRL.O_DATA.O_AC_REL;
        }
    }
    public void B020_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSMTDF.AC;
        B021_CALL_CIZACCU();
        if (pgmRtn) return;
        if (CICACCU.DATA.CI_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_NOMTCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CICACCU.DATA.CI_TYP == '3' 
            && DDCSMTDF.OPT != '4' 
            && DDCSMTDF.OPT != '5') {
            if (DDCSMTDF.ADP_POST_PERIOD == ' ') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_POST_PERIOD_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSMTDF.AC;
        CEP.TRC(SCCGWA, DDCSMTDF.AC);
        T000_READ_MST_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND);
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, DDRMST.CI_TYP);
                WS_CI_TYPE = DDRMST.CI_TYP;
            }
        }
    }
    public void B035_GET_CCY_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCSMTDF.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSMTDF.CCY;
        CICQACAC.DATA.CR_FLG = DDCSMTDF.FLG;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
    }
    public void B040_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'M') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_CLOSED_AND_GET_INT);
        }
        if (DDRMST.AC_STS == 'D') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER);
        }
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD);
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_ALR_FBIDEN_STS);
        }
        CEP.TRC(SCCGWA, DDRMST.OWNER_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (DDRMST.OWNER_BR != BPCPQORG.BBR 
            && (DDCSMTDF.OPT != '4')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BRANCH_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCSMTDF.OPT);
        if (DDCSMTDF.OPT == '1' 
            || DDCSMTDF.OPT == '2' 
            || DDCSMTDF.OPT == '3') {
            B041_CHK_CI_TST_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void B050_MSTR_INF_PROC() throws IOException,SQLException,Exception {
        if (DDCSMTDF.OPT == '1') {
            B080_1_ADD_RAT_PROC();
            if (pgmRtn) return;
            B080_2_UPD_CCY_PROC();
            if (pgmRtn) return;
            B080_6_OUT_OVR_PROC();
            if (pgmRtn) return;
        } else if (DDCSMTDF.OPT == '2') {
            B080_2_UPD_RAT_PROC();
            if (pgmRtn) return;
            B080_6_OUT_OVR_PROC();
            if (pgmRtn) return;
        } else if (DDCSMTDF.OPT == '3') {
            B080_3_UPD_RAT_PROC();
            if (pgmRtn) return;
            B080_2_UPD_CCY_PROC();
            if (pgmRtn) return;
            B080_6_OUT_OVR_1_PROC();
            if (pgmRtn) return;
        } else if (DDCSMTDF.OPT == '4') {
            B080_4_QRY_RAT_PROC();
            if (pgmRtn) return;
        } else if (DDCSMTDF.OPT == '5') {
            B080_2_ADD_ANOTHER_RAT_PROC();
            if (pgmRtn) return;
            B080_2_UPD_CCY_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSMTDF.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B007_03_GET_AC_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = DDCSMTDF.AC;
        DDRMSTR.ADP_STS = 'O';
        CEP.TRC(SCCGWA, DDRMSTR.KEY.AC);
        T000_READ_DDTMSTR();
        if (pgmRtn) return;
        if (WS_MSTR_FLG == 'F') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_RATH_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMSTR() throws IOException,SQLException,Exception {
        DDRMSTR.KEY.ADP_TYPE = "1";
        WS_MSTR_FLG = 'N';
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_STS < > 'F' "
            + "AND ADP_TYPE = :DDRMSTR.KEY.ADP_TYPE";
        DDTMSTR_RD.fst = true;
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTR_FLG = 'F';
        } else {
            WS_MSTR_FLG = 'N';
        }
    }
    public void B035_GEN_CI_AGT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'A';
        CICMAGT.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CICMAGT.DATA.AGT_TYP = "IBS002";
        CICMAGT.DATA.AGT_STS = 'N';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DDCSMTDF.AC;
        CICMAGT.DATA.AGT_LVL = 'A';
        CEP.TRC(SCCGWA, CICMAGT.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_TYP);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_STS);
        CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_LVL);
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
    }
    public void B035_CAN_CI_AGT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'D';
        CICMAGT.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CICMAGT.DATA.AGT_TYP = "IBS002";
        CICMAGT.DATA.AGT_STS = 'C';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DDCSMTDF.AC;
        CICMAGT.DATA.AGT_LVL = 'A';
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
    }
    public void B080_1_ADD_RAT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = DDRCCY.KEY.AC;
        if (DDCSMTDF.ADP_STRDATE > SCCGWA.COMM_AREA.AC_DATE) {
            DDRMSTR.ADP_STS = 'U';
        } else {
            DDRMSTR.ADP_STS = 'O';
        }
        CEP.TRC(SCCGWA, DDRMSTR.KEY.AC);
        T000_READ_DDTMSTR_FOR_OPEN();
        if (pgmRtn) return;
        if (WS_MSTR_OPEN_FLG == 'O') {
            if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("2")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MSTR_REC_ALR_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MSTR_REC_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        T000_READ_DDTMSTR_FOR_DUPKEY();
        if (pgmRtn) return;
        if (WS_MSTR_SAME_STRDATE_FLG == 'Y') {
            B080_0_MOVE_TO_DDRMSTR();
            if (pgmRtn) return;
            T000_REWRITE_DDTMSTR();
            if (pgmRtn) return;
        } else {
            B080_0_MOVE_TO_DDRMSTR();
            if (pgmRtn) return;
            T000_WRITE_DDTMSTR();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSMTDF.AC;
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 41 - 1) + "1" + DDRMST.AC_STS_WORD.substring(41 + 1 - 1);
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
    }
    public void B080_2_UPD_CCY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_UPDATE_DDTCCY();
        if (pgmRtn) return;
        if (DDRMSTR.ADP_STS == 'O') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 90 - 1) + "1" + DDRCCY.STS_WORD.substring(90 + 1 - 1);
        }
        if (DDRMSTR.ADP_STS == 'F') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 90 - 1) + "0" + DDRCCY.STS_WORD.substring(90 + 1 - 1);
        }
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B080_2_ADD_ANOTHER_RAT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = DDRCCY.KEY.AC;
        if (DDCSMTDF.ADP_STRDATE > SCCGWA.COMM_AREA.AC_DATE) {
            DDRMSTR.ADP_STS = 'U';
        } else {
            DDRMSTR.ADP_STS = 'O';
        }
        CEP.TRC(SCCGWA, DDRMSTR.KEY.AC);
        T000_READ_DDTMSTR_FOR_OPEN();
        if (pgmRtn) return;
        if (WS_MSTR_OPEN_FLG == 'O') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MSTR_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            B100_GET_PRD_INF_PROC();
            if (pgmRtn) return;
            B080_1_MOVE_TO_DDRMSTR();
            if (pgmRtn) return;
            T000_WRITE_DDTMSTR();
            if (pgmRtn) return;
        }
    }
    public void B080_2_UPD_RAT_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_DDTMSTR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRMSTR, DDRMSTR1);
        B080_2_TRANS_MSTR_DATA();
        if (pgmRtn) return;
