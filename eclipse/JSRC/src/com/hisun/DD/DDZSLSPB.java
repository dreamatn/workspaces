package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSLSPB {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTVCH_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "5542";
    String K_CHK_ULSTS_TBL = "5541";
    String K_HIS_CPB_NM = "DDCHCHPL";
    String K_HIS_RMKS_ULST = "PASSBOOK ORAL LOST PROCESS";
    String K_HIS_RMKS_WLST = "PASSBOOK FORMAL LOST PROCESS";
    String CPN_REC_NHIS = "BP-REC-NHIS  ";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_I_PSBK_PROC = "DD-I-PSBK-PROC";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    String WS_TS_REC = " ";
    short WS_LEN = 0;
    String WS_CCY = " ";
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
    CICACCU CICACCU = new CICACCU();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCFSCHG BPCFSCHG = new BPCFSCHG();
    DCCIMSTU DCCIMSTU = new DCCIMSTU();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    CICCUST CICCUST = new CICCUST();
    CICQACRI CICQACRI = new CICQACRI();
    DDRMST DDRMST = new DDRMST();
    DDRVCH DDRVCH = new DDRVCH();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCGPFEE BPCGPFEE;
    BPCGCFEE BPCGCFEE;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCSLSPB DDCSLSPB;
    public void MP(SCCGWA SCCGWA, DDCSLSPB DDCSLSPB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSLSPB = DDCSLSPB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSLSPB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B035_CHK_AC_STS();
        if (pgmRtn) return;
        B040_CI_INF_PROC();
        if (pgmRtn) return;
        B050_RST_PSW_PROC();
        if (pgmRtn) return;
        if (DDCSLSPB.FUNC == 'W' 
            || DDCSLSPB.FUNC == '2') {
            B190_FEE_PROC();
            if (pgmRtn) return;
        }
        B080_LOST_PSBK_PROC();
        if (pgmRtn) return;
        B098_UPD_MST_INF_PROC();
        if (pgmRtn) return;
        B170_NFIN_TXN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B001_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCSLSPB.CARD_NO;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        DDCSLSPB.AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.VIA_AC);
        CEP.TRC(SCCGWA, DDCSLSPB.AC);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSLSPB.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSLSPB.FUNC == 'U'
            || DDCSLSPB.FUNC == 'W'
            || DDCSLSPB.FUNC == '1'
            || DDCSLSPB.FUNC == '2') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID SLSPB-FUNC(" + DDCSLSPB.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B001_CALL_AC_CHK_PROC();
        if (pgmRtn) return;
    }
    public void B001_CALL_AC_CHK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCSLSPB.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSLSPB.AC;
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
    }
    public void B035_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = DDCSLSPB.AC;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
    }
    public void B050_RST_PSW_PROC() throws IOException,SQLException,Exception {
        if (DDCSLSPB.PSWD.trim().length() > 0) {
            CEP.TRC(SCCGWA, "RST PSWD");
            IBS.init(SCCGWA, DDCIMPAY);
            DDCIMPAY.FUNC = 'R';
            DDCIMPAY.AC = DDCSLSPB.AC;
            DDCIMPAY.ID_NO = CICCUST.O_DATA.O_ID_NO;
            DDCIMPAY.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
            DDCIMPAY.AC_CNAME = CICCUST.O_DATA.O_CI_NM;
            DDCIMPAY.PAY_MTH = DDCSLSPB.PAY_TYPE;
            DDCIMPAY.PSWD_NEW = DDCSLSPB.PSWD;
            S000_CALL_DDZIMPAY();
            if (pgmRtn) return;
        }
    }
    public void B050_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        if (DDCSLSPB.FUNC == 'W' 
            || DDCSLSPB.FUNC == '2') {
            BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        } else {
            BPCFCSTS.TBL_NO = K_CHK_ULSTS_TBL;
        }
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICCUST.O_DATA.O_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B080_LOST_PSBK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.AC = DDCSLSPB.AC;
        DDCIPSBK.FUNC = DDCSLSPB.FUNC;
        DDCIPSBK.AC_ENAME = DDCSLSPB.AC_ENAME;
        DDCIPSBK.AC_CNAME = DDCSLSPB.AC_CNAME;
        DDCIPSBK.CARD_NO = DDCSLSPB.CARD_NO;
        DDCIPSBK.LOST_NO = DDCSLSPB.LOST_NO;
        DDCIPSBK.ID_NO = CICCUST.O_DATA.O_ID_NO;
        DDCIPSBK.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        DDCIPSBK.HLD_FLG = DDCSLSPB.HLD_FLG;
        DDCIPSBK.UPT_RMK = DDCSLSPB.REMARKS;
        CEP.TRC(SCCGWA, DDCIPSBK.LOST_NO);
        CEP.TRC(SCCGWA, DDCIPSBK.FUNC);
        S000_CALL_DDZIPSBK();
        if (pgmRtn) return;
    }
    public void B098_UPD_MST_INF_PROC() throws IOException,SQLException,Exception {
        if (DDCSLSPB.FUNC == 'W' 
            || DDCSLSPB.FUNC == '2') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 7 - 1) + "01" + DDRMST.AC_STS_WORD.substring(7 + 2 - 1);
        } else {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 7 - 1) + "1" + DDRMST.AC_STS_WORD.substring(7 + 1 - 1);
        }
        if ((DDCSLSPB.FUNC == 'W' 
            && DDCSLSPB.HLD_FLG == 'Y')) {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 9 - 1) + "1" + DDRMST.AC_STS_WORD.substring(9 + 1 - 1);
        }
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
    }
    public void B170_NFIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPD_DDTVCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.PSBK_NO);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.AC = DDCSLSPB.AC;
        BPCPNHIS.INFO.CCY = "156";
        BPCPNHIS.INFO.CCY_TYPE = '1';
        BPCPNHIS.INFO.REF_NO = DDRVCH.PSBK_NO;
        BPCPNHIS.INFO.TX_TOOL = DDCSLSPB.CARD_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICCUST.O_DATA.O_CI_NO;
        BPCPNHIS.INFO.REF_NO = DDCIPSBK.LOST_NO;
        if (DDCSLSPB.FUNC == 'U') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS_ULST;
        } else {
            BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS_WLST;
        }
        if (DDCSLSPB.FUNC == 'W' 
            || DDCSLSPB.FUNC == '2') {
            BPCPNHIS.INFO.TX_TYP_CD = "P135";
        }
        if (DDCSLSPB.FUNC == 'U' 
            || DDCSLSPB.FUNC == '1') {
            BPCPNHIS.INFO.TX_TYP_CD = "P134";
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B190_FEE_PROC() throws IOException,SQLException,Exception {
        WS_CCY = "156";
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = DDCSLSPB.AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = WS_CCY;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CI_NO);
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CCY = WS_CCY;
        if ("1".trim().length() == 0) BPCTCALF.INPUT_AREA.TX_CNT = 0;
        else BPCTCALF.INPUT_AREA.TX_CNT = Short.parseShort("1");
        BPCTCALF.INPUT_AREA.TX_AC = DDCSLSPB.AC;
        BPCTCALF.INPUT_AREA.TX_CI = CICCUST.O_DATA.O_CI_NO;
        BPCTCALF.INPUT_AREA.BVF_CODE = "A00015";
        BPCTCALF.INPUT_AREA.PROD_CODE1 = DDRMST.PROD_CODE;
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        CEP.TRC(SCCGWA, BPCTCALF);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CI);
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.FEE_DATA_IND);
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_BPZFSCHG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-CHG-FEE", BPCFSCHG);
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
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPD_DDTVCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSLSPB.AC;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        DDTVCH_RD.upd = true;
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
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
    public void B180_CALL_MSGS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        S000_CALL_SCCWRMSG();
        if (pgmRtn) return;
    }
    public void S000_CALL_SCCWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
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
