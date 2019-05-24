package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFCSTS;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPGDIN;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCUINTI;
import com.hisun.BP.BPRPRMT;
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
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCMSG_ERROR_MSG;
import com.hisun.TD.TDCOGEDT;

public class DDZSMTDR {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD125";
    String K_OUTPUT_FMT2 = "DD126";
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "5126";
    String K_BANK_DR_STS_TBL = "0006";
    String K_CUS_DR_STS_TBL = "0001";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_PROC_FHIS = "BP-PROC-FHIS";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_HIS_COPYBOOK_NAME = "DDCSMTDR";
    String K_HIS_REMARKS = "ACTIVATION ACCOUNT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_TENOR = "S301";
    int WS_DD_I_VSTRDT = 0;
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    double WS_SPR = 0;
    double WS_SPR_PCT = 0;
    double WS_RATE = 0;
    int WS_ADP_STRDATE = 0;
    int WS_OUT_CNT = 0;
    String WS_PQORG_TYP1 = " ";
    String WS_PQORG_TYP2 = " ";
    int WS_PQORG_SUPR_BR1 = 0;
    int WS_PQORG_SUPR_BR2 = 0;
    DDZSMTDR_WS_OUT_INF WS_OUT_INF = new DDZSMTDR_WS_OUT_INF();
    char WS_MSTR_FLG = ' ';
    char WS_CCY_FLG = ' ';
    char WS_MSTR_SAME_DAY_FLG = ' ';
    char WS_MSTR_OPEN_FLG = ' ';
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
    DDCSMTDO DDCSMTDO = new DDCSMTDO();
    TDCOGEDT TDCOGEDT = new TDCOGEDT();
    BPCUINTI BPCUINTI = new BPCUINTI();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    DDCPUADP DDCPUADP = new DDCPUADP();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICMAGT CICMAGT = new CICMAGT();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCSMTDR DDCSMTDR;
    public void MP(SCCGWA SCCGWA, DDCSMTDR DDCSMTDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSMTDR = DDCSMTDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSMTDR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMTDR.I_OPT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_DD_AC);
        CEP.TRC(SCCGWA, DDCSMTDR.I_CCY);
        CEP.TRC(SCCGWA, DDCSMTDR.I_FLG);
        CEP.TRC(SCCGWA, DDCSMTDR.I_CI_NO);
        CEP.TRC(SCCGWA, DDCSMTDR.I_BALS);
        CEP.TRC(SCCGWA, DDCSMTDR.I_STR_DT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_RATETY);
        CEP.TRC(SCCGWA, DDCSMTDR.I_TERM);
        CEP.TRC(SCCGWA, DDCSMTDR.I_INTRAT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_TYP);
        CEP.TRC(SCCGWA, DDCSMTDR.I_RATEOF);
        CEP.TRC(SCCGWA, DDCSMTDR.I_PCTS);
        CEP.TRC(SCCGWA, DDCSMTDR.I_CXRAT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_CRTDT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_VSTRDT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_ENDDT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_RCVDT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_PROL_NO);
        if (DDCSMTDR.I_DD_AC.trim().length() > 0) {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B015_CALL_ACTY_PROC();
            if (pgmRtn) return;
            B020_GET_AC_INF_PROC();
            if (pgmRtn) return;
            B025_GET_CCY_INF_PROC();
            if (pgmRtn) return;
            B030_GET_CI_INF_PROC();
            if (pgmRtn) return;
            if (DDCSMTDR.I_OPT != '4' 
                && DDCSMTDR.I_OPT != '5') {
                B040_CHK_AC_STS();
                if (pgmRtn) return;
            }
        }
        B050_MSTR_INF_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.JRN_NO != 0) {
            B170_NFIN_TX_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMTDR.I_DD_AC);
        if (DDCSMTDR.I_OPT != '4') {
            if (DDCSMTDR.I_DD_AC.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSMTDR.I_CCY.trim().length() == 0 
            && DDCSMTDR.I_OPT == '4') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSMTDR.I_FLG == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
    }
    IBS.init(SCCGWA, DDRMST);
    DDRMST.KEY.CUS_AC = DDCSMTDR.I_DD_AC;
    T000_READ_MST_PROC();
    if (pgmRtn) return;
    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = DDCSMTDR.I_DD_AC;
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        DDCSMTDR.I_DD_AC = CICQACRL.O_DATA.O_AC_REL;
    }
    public void B030_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSMTDR.I_DD_AC;
        B021_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSMTDR.I_OPT);
        if (DDCSMTDR.I_OPT == '1' 
            || DDCSMTDR.I_OPT == '2' 
            || DDCSMTDR.I_OPT == '3') {
            B031_CHK_CI_TST_PROC();
            if (pgmRtn) return;
        }
    }
    public void B031_CHK_CI_TST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        CEP.TRC(SCCGWA, "111111111111111111S");
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_STSW);
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        CEP.TRC(SCCGWA, "1111111111111111111E");
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 201 - 1) + DDRCCY.STS_WORD + BPCFCSTS.STATUS_WORD.substring(201 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        CEP.TRC(SCCGWA, "000000000000000000");
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B020_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSMTDR.I_DD_AC;
        T000_READ_MST_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND);
        }
        CEP.TRC(SCCGWA, DDCSMTDR.I_OPT);
        CEP.TRC(SCCGWA, DDRMST.OWNER_BRDP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = DDRMST.OWNER_BRDP;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_PQORG_TYP1 = BPCPQORG.TYP;
        if (WS_PQORG_TYP1.equalsIgnoreCase("21")) {
            WS_PQORG_SUPR_BR1 = BPCPQORG.SUPR_BR;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_PQORG_TYP2 = BPCPQORG.TYP;
        if (WS_PQORG_TYP2.equalsIgnoreCase("21")) {
            WS_PQORG_SUPR_BR2 = BPCPQORG.SUPR_BR;
        }
        if (WS_PQORG_TYP1.equalsIgnoreCase(WS_PQORG_TYP2)) {
            if (DDRMST.OWNER_BRDP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
                && (DDCSMTDR.I_OPT != '5')) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BRANCH_NOT_SAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (WS_PQORG_TYP1.equalsIgnoreCase("21")) {
                if (WS_PQORG_SUPR_BR1 != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
                    && (DDCSMTDR.I_OPT != '5')) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BRANCH_NOT_SAME;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (WS_PQORG_TYP2.equalsIgnoreCase("21")) {
                if (DDRMST.OWNER_BRDP != WS_PQORG_SUPR_BR2 
                    && (DDCSMTDR.I_OPT != '5')) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BRANCH_NOT_SAME;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, DDRMST.OWNER_BK);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = DDRMST.OWNER_BK;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_PQORG_SUPR_BR1 = BPCPQORG.SUPR_BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_PQORG_SUPR_BR2 = BPCPQORG.SUPR_BR;
        CEP.TRC(SCCGWA, WS_PQORG_SUPR_BR1);
        CEP.TRC(SCCGWA, WS_PQORG_SUPR_BR2);
        if (WS_PQORG_SUPR_BR1 != WS_PQORG_SUPR_BR2 
            && (DDCSMTDR.I_OPT != '5')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BRANCH_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
    }
    public void B025_GET_CCY_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCSMTDR.I_DD_AC;
        CICQACAC.DATA.CCY_ACAC = DDCSMTDR.I_CCY;
        CICQACAC.DATA.CR_FLG = DDCSMTDR.I_FLG;
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
    }
    public void B021_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void B050_MSTR_INF_PROC() throws IOException,SQLException,Exception {
        if (DDCSMTDR.I_OPT == '1') {
            B035_GEN_CI_AGT_NO();
            if (pgmRtn) return;
            B080_1_ADD_RAT_PROC();
            if (pgmRtn) return;
            B080_2_UPD_CCY_PROC();
            if (pgmRtn) return;
            B080_6_OUT_OVR_PROC();
            if (pgmRtn) return;
        } else if (DDCSMTDR.I_OPT == '2') {
            B080_2_UPD_RAT_PROC();
            if (pgmRtn) return;
            B080_6_OUT_OVR_PROC();
            if (pgmRtn) return;
        } else if (DDCSMTDR.I_OPT == '3') {
            B035_CAN_CI_AGT_NO();
            if (pgmRtn) return;
            B080_3_UPD_RAT_PROC();
            if (pgmRtn) return;
            B080_2_UPD_CCY_PROC();
            if (pgmRtn) return;
            B080_6_OUT_OVR_PROC();
            if (pgmRtn) return;
        } else if (DDCSMTDR.I_OPT == '4') {
            B080_4_QRY_RAT_PROC_NEW();
            if (pgmRtn) return;
        } else if (DDCSMTDR.I_OPT == '5') {
            B080_4_QRY_RAT_ALL();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSMTDR.I_OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B035_GEN_CI_AGT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'A';
        CICMAGT.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CICMAGT.DATA.AGT_TYP = "3001013";
        CICMAGT.DATA.EFF_DATE = DDCSMTDR.I_VSTRDT;
        CICMAGT.DATA.AGT_STS = 'N';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DDCSMTDR.I_DD_AC;
        CICMAGT.DATA.AGT_LVL = 'A';
        CEP.TRC(SCCGWA, CICMAGT.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_TYP);
        CEP.TRC(SCCGWA, CICMAGT.DATA.EFF_DATE);
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
        CICMAGT.DATA.AGT_TYP = "3001013";
        CICMAGT.DATA.AGT_STS = 'C';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DDCSMTDR.I_DD_AC;
        CICMAGT.DATA.AGT_LVL = 'A';
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
    }
    public void B007_03_GET_AC_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = DDCSMTDR.I_DD_AC;
        DDRMSTR.ADP_STS = 'O';
        DDRMSTR.KEY.ADP_STRDATE = DDCSMTDR.I_VSTRDT;
        CEP.TRC(SCCGWA, DDRMSTR.KEY.AC);
        CEP.TRC(SCCGWA, DDCSMTDR.I_VSTRDT);
        T000_READ_DDTMSTR();
        if (pgmRtn) return;
        if (WS_MSTR_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_RATH_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMSTR() throws IOException,SQLException,Exception {
        DDRMSTR.KEY.ADP_TYPE = "2";
        WS_MSTR_FLG = 'N';
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_STS < > 'F' "
            + "AND ADP_TYPE = :DDRMSTR.KEY.ADP_TYPE";
        DDTMSTR_RD.upd = true;
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "XXXX1111");
            WS_MSTR_FLG = 'Y';
            CEP.TRC(SCCGWA, DDRMSTR);
        } else {
            CEP.TRC(SCCGWA, "XXXX2222");
            WS_MSTR_FLG = 'N';
        }
    }
    public void T000_READ_DDTMSTR_ALL() throws IOException,SQLException,Exception {
        DDRMSTR.KEY.ADP_TYPE = "2";
        WS_MSTR_FLG = 'N';
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_TYPE = :DDRMSTR.KEY.ADP_TYPE";
        DDTMSTR_RD.upd = true;
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTR_FLG = 'Y';
            CEP.TRC(SCCGWA, "Data FOUND");
        } else {
            WS_MSTR_FLG = 'N';
        }
    }
    public void T000_READ_DDTMSTR_01() throws IOException,SQLException,Exception {
        DDRMSTR.KEY.ADP_TYPE = "2";
        WS_MSTR_FLG = 'N';
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.where = "ADP_STRDATE >= :DDRMSTR.KEY.ADP_STRDATE "
            + "AND ADP_STRDATE <= :DDRMSTR.ADP_END_DT "
            + "AND ADP_STS < > 'F' "
            + "AND ADP_TYPE = :DDRMSTR.KEY.ADP_TYPE";
        DDTMSTR_RD.upd = true;
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTR_FLG = 'Y';
        } else {
            WS_MSTR_FLG = 'N';
        }
    }
    public void B080_1_ADD_RAT_PROC() throws IOException,SQLException,Exception {
        B080_1_DDCPUADP_PARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSMTDR.I_BALS);
        CEP.TRC(SCCGWA, DDCPUADP.DATA_TXT.AGRM_BAL);
        if (DDCSMTDR.I_BALS < DDCPUADP.DATA_TXT.AGRM_BAL) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_LG_AGRM_BAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCSMTDR.I_STR_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (DDCSMTDR.I_STR_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DATE_LT_ACDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCSMTDR.I_STR_DT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_ENDDT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_VSTRDT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_RCVDT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_END_DT);
        if (DDCSMTDR.I_STR_DT != 0 
            && DDCSMTDR.I_ENDDT != 0) {
            if (DDCSMTDR.I_STR_DT > DDCSMTDR.I_ENDDT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ED_DT_LESSTHAN_ST_DT);
            }
        }
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRMSTR.ADP_STS = 'O';
        if (DDCSMTDR.I_VSTRDT > SCCGWA.COMM_AREA.AC_DATE) {
            DDRMSTR.ADP_STS = 'U';
        } else {
            DDRMSTR.ADP_STS = 'O';
        }
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
        DDRMST.KEY.CUS_AC = DDCSMTDR.I_DD_AC;
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 44 - 1) + "1" + DDRMST.AC_STS_WORD.substring(44 + 1 - 1);
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
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 91 - 1) + "1" + DDRCCY.STS_WORD.substring(91 + 1 - 1);
        }
        if (DDRMSTR.ADP_STS == 'F') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 91 - 1) + "0" + DDRCCY.STS_WORD.substring(91 + 1 - 1);
        }
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void T000_READ_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "XXXX111155");
            WS_CCY_FLG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "XXXX222255");
            WS_CCY_FLG = 'N';
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void B080_2_UPD_RAT_PROC() throws IOException,SQLException,Exception {
        B080_1_DDCPUADP_PARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSMTDR.I_BALS);
        CEP.TRC(SCCGWA, DDCPUADP.DATA_TXT.AGRM_BAL);
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDCSMTDR.I_CXRAT);
        T000_READ_UPDATE_DDTMSTR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSMTDR.I_CXRAT);
        IBS.CLONE(SCCGWA, DDRMSTR, DDRMSTR1);
        CEP.TRC(SCCGWA, DDCSMTDR.I_CXRAT);
        B080_1_MOVE_TO_DDRMSTR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMSTR);
        CEP.TRC(SCCGWA, DDRMSTR1);
        CEP.TRC(SCCGWA, DDRMSTR.KEY.ADP_STRDATE);
        CEP.TRC(SCCGWA, DDRMSTR1.KEY.ADP_STRDATE);
