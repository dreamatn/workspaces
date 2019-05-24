package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSRLPB {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTVCH_RD;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "5540";
    String K_HIS_CPB_NM = "DDCHCHPL";
    String K_HIS_RMKS = "PASSBOOK RLS LOST PROCESS";
    String CPN_I_PSBK_PROC = "DD-I-PSBK-PROC";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    String WS_TS_REC = " ";
    short WS_LEN = 0;
    char WS_DDTVCH_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCHLSPB DDCLSPBO = new DDCHLSPB();
    DDCHLSPB DDCLSPBN = new DDCHLSPB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCCPRL BPCCPRL = new BPCCPRL();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    CICACCU CICACCU = new CICACCU();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCSCHPB DDCSCHPB = new DDCSCHPB();
    DDCUPINT DDCUPINT = new DDCUPINT();
    DDCSCCCY DDCSCCCY = new DDCSCCCY();
    DDCSCLAC DDCSCLAC = new DDCSCLAC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCIMSTU DCCIMSTU = new DCCIMSTU();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRVCH DDRVCH = new DDRVCH();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSRLPB DDCSRLPB;
    public void MP(SCCGWA SCCGWA, DDCSRLPB DDCSRLPB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSRLPB = DDCSRLPB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSRLPB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCSRLPB.PROC_TYP == '5' 
            || DDCSRLPB.PROC_TYP == '6' 
            || DDCSRLPB.PROC_TYP == '4') {
            B030_GET_AC_INF_PROC();
            if (pgmRtn) return;
            B035_CHK_AC_STS();
            if (pgmRtn) return;
            B040_CI_INF_PROC();
            if (pgmRtn) return;
            B045_RST_PSW_PROC();
            if (pgmRtn) return;
            B050_CHECK_STS_TBL_PROC();
            if (pgmRtn) return;
            B080_RLST_PSBK_PROC();
            if (pgmRtn) return;
            B098_UPD_MST_INF_PROC();
            if (pgmRtn) return;
            if (DDCSRLPB.PROC_TYP == '5' 
                || DDCSRLPB.PROC_TYP == '6') {
                B170_NFIN_TXN_HIS_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSRLPB.PROC_TYP == '3') {
            B200_RLST_PSBK_PROC();
            if (pgmRtn) return;
        }
        if (DDCSRLPB.PROC_TYP == '4') {
            B300_INQ_AC_INT_PROC();
            if (pgmRtn) return;
            B310_INQ_CCY_BAL_PROC();
            if (pgmRtn) return;
            B320_CLEAR_CCY_PROC();
            if (pgmRtn) return;
            B330_SVR_CLOSE_AC();
            if (pgmRtn) return;
        }
    }
    public void B001_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSRLPB.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B001_CALL_ACTY_PROC();
        if (pgmRtn) return;
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSRLPB.AC;
        CEP.TRC(SCCGWA, DDCSRLPB.AC);
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
    }
    public void B035_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDRMST.KEY.CUS_AC;
        DDRVCH.VCH_TYPE = '1';
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.LOST_NO);
        if (WS_DDTVCH_FLG == 'N' 
            || DDRVCH.PSBK_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NOT_USE_PSBK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSRLPB.PROC_TYP == '5' 
            && DDRVCH.PSBK_STS != 'U') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSBK_NOT_ULST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSRLPB.PROC_TYP == '6' 
            && DDRVCH.PSBK_STS != 'W') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSBK_NOT_WLST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DDRVCH.LOST_NO.equalsIgnoreCase(DDCSRLPB.LOST_NO) 
            && DDCSRLPB.PROC_TYP == '6') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LOSTNO_NOT_MATCH);
        }
        if (DDRVCH.LOST_NO.trim().length() > 0 
            && DDCSRLPB.PROC_TYP == '6') {
            IBS.init(SCCGWA, BPCPLOSS);
            BPCPLOSS.DATA_INFO.LOS_NO = DDRVCH.LOST_NO;
            BPCPLOSS.INFO.FUNC = 'I';
            BPCPLOSS.INFO.INDEX_FLG = "1";
            IBS.CALLCPN(SCCGWA, "BP-P-QUERY-LOSS-INFO", BPCPLOSS);
            if (BPCPLOSS.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_ORG);
            R000_GET_BBR_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.BBR);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
            if (BPCPQORG.BBR != BPCPORUP.DATA_INFO.BBR) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_BR_NOT_MATCH);
            }
        }
    }
    public void R000_GET_BBR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCPLOSS.DATA_INFO.LOS_ORG;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSRLPB.AC;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
    }
    public void B045_RST_PSW_PROC() throws IOException,SQLException,Exception {
        if (DDCSRLPB.PSWD.trim().length() > 0) {
            CEP.TRC(SCCGWA, "RST PSWD");
            IBS.init(SCCGWA, DDCIMPAY);
            DDCIMPAY.FUNC = 'R';
            DDCIMPAY.AC = DDCSRLPB.AC;
            DDCIMPAY.ID_NO = CICACCU.DATA.ID_NO;
            DDCIMPAY.ID_TYPE = CICACCU.DATA.ID_TYPE;
            DDCIMPAY.AC_CNAME = CICACCU.DATA.CI_CNM;
            DDCIMPAY.PAY_MTH = DDRVCH.PAY_TYPE;
            DDCIMPAY.PSWD_NEW = DDCSRLPB.PSWD;
            S000_CALL_DDZIMPAY();
            if (pgmRtn) return;
        }
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
        if (DDCSRLPB.PROC_TYP == '5') {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 24 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(24 + 1 - 1);
        }
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_STSW);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
    }
    public void B080_RLST_PSBK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSRLPB.LOST_NO);
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.AC = DDCSRLPB.AC;
        DDCIPSBK.FUNC = DDCSRLPB.PROC_TYP;
        DDCIPSBK.AC_ENAME = DDCSRLPB.AC_ENAME;
        DDCIPSBK.AC_CNAME = DDCSRLPB.AC_CNAME;
        DDCIPSBK.CARD_NO = DDCSRLPB.CARD_NO;
        DDCIPSBK.LOST_NO = DDCSRLPB.LOST_NO;
        DDCIPSBK.R_TEL_NO = DDCSRLPB.R_TEL_NO;
        CEP.TRC(SCCGWA, DDCSRLPB.AC);
        CEP.TRC(SCCGWA, DDCIPSBK.LOST_NO);
        CEP.TRC(SCCGWA, DDCIPSBK.R_TEL_NO);
        S000_CALL_DDZIPSBK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.CARD_FLG);
    }
    public void B098_UPD_MST_INF_PROC() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 7 - 1) + "00" + DDRMST.AC_STS_WORD.substring(7 + 2 - 1);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 9 - 1) + "0" + DDRMST.AC_STS_WORD.substring(9 + 1 - 1);
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
    }
    public void B170_NFIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.AC = DDCSRLPB.AC;
        BPCPNHIS.INFO.CCY = CICACCU.DATA.CCY;
        if (CICACCU.DATA.CCY.equalsIgnoreCase("156") 
            && CICACCU.DATA.CCY.trim().length() > 0) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.TX_TOOL = DDCSRLPB.CARD_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.REF_NO = DDCIPSBK.LOST_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P113";
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B200_RLST_PSBK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSRLPB.LOST_NO);
        IBS.init(SCCGWA, DDCSCHPB);
        DDCSCHPB.AC = DDCSRLPB.AC;
        DDCSCHPB.CHA_REASON = 'L';
        DDCSCHPB.AC_ENAME = DDCSRLPB.AC_ENAME;
        DDCSCHPB.AC_CNAME = DDCSRLPB.AC_CNAME;
        DDCSCHPB.CARD_NO = DDCSRLPB.CARD_NO;
        DDCSCHPB.LOST_NO = DDCSRLPB.LOST_NO;
        DDCSCHPB.PSBK_OLD = DDCSRLPB.PSBK_NO_OLD;
        DDCSCHPB.PSBK_NEW = DDCSRLPB.PSBK_NO_NEW;
        S000_CALL_DDZSCHPB();
        if (pgmRtn) return;
    }
    public void B300_INQ_AC_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DDCSRLPB.AC;
        DDCUPINT.CCY = "CNY";
        DDCUPINT.TX_TYP = 'I';
        S000_CALL_DDZUPINT();
        if (pgmRtn) return;
    }
    public void B310_INQ_CCY_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDCSRLPB.AC;
        DDRCCY.CCY = "CNY";
        T000_READ_DDTCCY();
        if (pgmRtn) return;
    }
    public void B320_CLEAR_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCCCY);
        DDCSCCCY.CARD_NO = DDCSRLPB.CARD_NO;
        DDCSCCCY.AC = DDCSRLPB.AC;
        DDCSCCCY.AC_CNM = DDCSRLPB.AC_CNAME;
        DDCSCCCY.AC_ENM = DDCSRLPB.AC_ENAME;
        DDCSCCCY.CCY = "CNY";
        DDCSCCCY.PSBK_NO = DDCSRLPB.PSBK_NO_OLD;
        DDCSCCCY.CHQ_TYPE = DDCSRLPB.CHQ_TYPE;
        DDCSCCCY.CHQ_NO = DDCSRLPB.CHQ_NO;
        DDCSCCCY.PAY_TYPE = DDCSRLPB.PAY_TYPE;
        DDCSCCCY.PSWD = DDCSRLPB.PSWD;
        DDCSCCCY.ID_TYPE = DDCSRLPB.ID_TYPE;
        DDCSCCCY.ID_NO = DDCSRLPB.ID_NO;
        DDCSCCCY.PRIN = DDRCCY.CURR_BAL;
        DDCSCCCY.DEP_INT = DDCUPINT.DEP_INT;
        DDCSCCCY.OD_INT = DDCUPINT.OD_INT + DDCUPINT.UOD_INT;
        DDCSCCCY.INT_TAX = DDCUPINT.INT_TAX;
        DDCSCCCY.TOT_BAL = DDRCCY.CURR_BAL + DDCSCCCY.DEP_INT - DDCSCCCY.OD_INT - DDCSCCCY.INT_TAX;
        DDCSCCCY.PAY_MTH = 'C';
        DDCSCCCY.TRF_AC = DDCSRLPB.TRF_AC;
        DDCSCCCY.TRF_CNM = DDCSRLPB.TRF_CNM;
        DDCSCCCY.TRF_ENM = DDCSRLPB.TRF_ENM;
        DDCSCCCY.CASH_NO = DDCSRLPB.CASH_NO;
        DDCSCCCY.NARRATIV = DDCSRLPB.NARRATIV;
        DDCSCCCY.RMK = DDCSRLPB.RMK;
        S000_CALL_DDZSCCCY();
        if (pgmRtn) return;
    }
    public void B330_SVR_CLOSE_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCLAC);
        DDCSCLAC.AC_NO = DDCSRLPB.AC;
        DDCSCLAC.PSBK_NO = DDCSRLPB.PSBK_NO_OLD;
        DDCSCLAC.DRW_PSW = DDCSRLPB.PSWD;
        DDCSCLAC.NARRATIVE = DDCSRLPB.CLOSE_REASON;
        DDCSCLAC.REMARK = DDCSRLPB.RMK;
        S000_CALL_DDZSCLAC();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void S000_CALL_DDZSCLAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CLOSE-AC", DDCSCLAC);
    }
    public void S000_CALL_DDZSCCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CLEAR-CCY", DDCSCCCY);
    }
    public void S000_CALL_DDZUPINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-POST-INT", DDCUPINT);
    }
    public void S000_CALL_DDZSCHPB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-CHG-PSB", DDCSCHPB);
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_PSBK_PROC, DDCIPSBK);
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
    public void S000_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_STS,AC_STS_WORD,LAST_DATE,LAST_TLR, CARD_FLG, UPDTBL_DATE,UPDTBL_TLR";
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
            + "AND VCH_TYPE = '1'";
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTVCH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTVCH_FLG = 'N';
        } else {
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.col = "AC,CCY,CCY_TYPE,CURR_BAL";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIMSTU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-UPD-IAMST", DCCIMSTU);
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
