package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACRL;
import com.hisun.DC.DCCMSG_ERROR_MSG;
import com.hisun.DC.DCCPACTY;
import com.hisun.DC.DCCUCINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class DDZSRPPB {
    boolean pgmRtn = false;
    short PBKS_LEFT_LINE = 0;
    String K_HIS_COPYBOOK_NAME = "DDCORPPB";
    String K_HIS_REMARKS = "ACCOUNT REPRINT PASSBOOK";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_UPT_CCY = " ";
    int WS_TXN_DATE = 0;
    String WS_MMO = " ";
    short WS_LINE_CNT = 0;
    short WS_PUPT_CNT = 0;
    short WS_RPRT_LINE = 0;
    short WS_PRT_LINE = 0;
    double WS_PB_BAL = 0;
    String WS_VCH_TYPE = " ";
    short WS_LINE_NUM = 0;
    short WS_REPRINT_NUM = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDRUPT DDRUPT = new DDRUPT();
    DDCRUPT DDCRUPT = new DDCRUPT();
    DDRMST DDRMST = new DDRMST();
    DDCORPPB DDCORPPB = new DDCORPPB();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    DDCPBKS DDCPBKS = new DDCPBKS();
    DDRCCY DDRCCY = new DDRCCY();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCRVCH DDCRVCH = new DDCRVCH();
    DDRVCH DDRVCH = new DDRVCH();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACRL CICQACRL = new CICQACRL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    DDCSRPPB DDCSRPPB;
    public void MP(SCCGWA SCCGWA, DDCSRPPB DDCSRPPB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSRPPB = DDCSRPPB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSRPPB return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT();
        if (pgmRtn) return;
        B010_CHK_AC_STS_PROC();
        if (pgmRtn) return;
        B020_GET_PSBK_PROC();
        if (pgmRtn) return;
        B021_PASSBOOK_PARM();
        if (pgmRtn) return;
        B022_READ_DDTVCH_PROC();
        if (pgmRtn) return;
        B030_CAL_PRT_LINE_OR_PRT_NUM();
        if (pgmRtn) return;
        B040_BROWSE_UPT_PROC();
        if (pgmRtn) return;
        B090_NFIN_TXN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSRPPB.OLD_PAGE);
        CEP.TRC(SCCGWA, DDCSRPPB.OLD_LINE);
        CEP.TRC(SCCGWA, DDCSRPPB.NEW_PAGE);
        CEP.TRC(SCCGWA, DDCSRPPB.NEW_LINE);
        CEP.TRC(SCCGWA, DDCSRPPB.RPT_NUM);
        if (DDCSRPPB.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSRPPB.OLD_PAGE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OLD_PAGE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSRPPB.OLD_LINE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OLD_LINE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSRPPB.RPT_NUM == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSRPPB.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
    }
    public void B001_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCSRPPB.AC;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        DDCSRPPB.AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DDCSRPPB.AC);
    }
    public void B010_CHK_AC_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.REL_AC_NO = DDCSRPPB.AC;
        CICQACRL.DATA.AC_REL = "12";
        CICQACRL.FUNC = '4';
        CICQACRL.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
        if (CICQACRL.RC.RC_CODE == 0) {
            DDCSRPPB.AC = CICQACRL.O_DATA.O_AC_NO;
            B010_CHK_CARD_NO_PROC();
            if (pgmRtn) return;
        } else {
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSRPPB.AC;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'O') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PSWD_LOCK);
        }
    }
    public void B010_CHK_CARD_NO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = CICQACRL.DATA.REL_AC_NO;
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
    }
    public void B020_GET_PSBK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.FUNC = 'Q';
        DDCIPSBK.AC = DDCSRPPB.AC;
        S000_CALL_DDZIPSBK();
        if (pgmRtn) return;
    }
    public void B022_READ_DDTVCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSRPPB.AC;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        DDTVCH_RD.col = "PRT_LINE,PSBK_STS,PSBK_SEQ";
        DDTVCH_RD.fst = true;
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_ACTION_INVALID, DDCRUPT.RC);
            DDCRVCH.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDRVCH.PSBK_STS == 'U') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_UNWRITE_LOST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRVCH.PSBK_STS == 'W') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_WRITE_LOST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_PASSBOOK_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DDCPBKS);
        BPRPRMT.KEY.TYP = "PDD01";
        BPRPRMT.KEY.CD = "PASSBOOK";
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCPBKS);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_NOTFND;
            Z_RET();
            if (pgmRtn) return;
        } else {
