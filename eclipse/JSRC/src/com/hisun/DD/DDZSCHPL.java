package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCHPL {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTVCH_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_CPB_NM = "DDCHCHPL";
    String K_HIS_RMKS = "CHANGE PASSBOOK PRINT LINE";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    String WS_TS_REC = " ";
    short WS_LEN = 0;
    int WS_NEW_LINE = 0;
    int WS_OLD_LINE = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCHCHPL DDCCHPLO = new DDCHCHPL();
    DDCHCHPL DDCCHPLN = new DDCHCHPL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCCPRL BPCCPRL = new BPCCPRL();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    CICACCU CICACCU = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCCINTI BPCCINTI = new BPCCINTI();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDCPBKS DDCPBKS = new DDCPBKS();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACRL CICQACRL = new CICQACRL();
    DDRMST DDRMST = new DDRMST();
    DDRVCH DDRVCH = new DDRVCH();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSCHPL DDCSCHPL;
    public void MP(SCCGWA SCCGWA, DDCSCHPL DDCSCHPL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCHPL = DDCSCHPL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCHPL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
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
        B080_CHA_LINE_PROC();
        if (pgmRtn) return;
        B098_UPD_MST_INF_PROC();
        if (pgmRtn) return;
        B170_NFIN_TXN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B001_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCSCHPL.AC;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        DDCSCHPL.AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DDCSCHPL.AC);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCHPL.PSBK_PAGE);
        CEP.TRC(SCCGWA, DDCSCHPL.PSBK_LINE);
        CEP.TRC(SCCGWA, DDCSCHPL.OPSBK_PAGE);
        CEP.TRC(SCCGWA, DDCSCHPL.OPSBK_LINE);
        if (DDCSCHPL.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCHPL.PSBK_PAGE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCHPL.PSBK_LINE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_GET_PASSBOOK_PARM();
        if (pgmRtn) return;
        WS_NEW_LINE = DDCPBKS.DATA_TXT.PAGE_LINE * ( DDCSCHPL.PSBK_PAGE - 1 ) + DDCSCHPL.PSBK_LINE;
        WS_OLD_LINE = DDCPBKS.DATA_TXT.PAGE_LINE * ( DDCSCHPL.OPSBK_PAGE - 1 ) + DDCSCHPL.OPSBK_LINE;
        CEP.TRC(SCCGWA, WS_OLD_LINE);
        CEP.TRC(SCCGWA, WS_NEW_LINE);
        if (WS_NEW_LINE < WS_OLD_LINE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSB_LINE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_NEW_LINE == WS_OLD_LINE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DATA_NO_CHANGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.PRT_LINE);
        if (WS_NEW_LINE <= DDRVCH.PRT_LINE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSB_LINE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_OLD_LINE != DDRVCH.PRT_LINE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSB_OLINE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCHPL.PSBK_PAGE > DDCPBKS.DATA_TXT.MAX_PAGE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSB_PAGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCHPL.PSBK_LINE > DDCPBKS.DATA_TXT.PAGE_LINE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSB_LINE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.REL_AC_NO = DDCSCHPL.AC;
        CICQACRL.DATA.AC_REL = "12";
        CICQACRL.FUNC = '4';
        CICQACRL.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
        if (CICQACRL.RC.RC_CODE == 0) {
            DDCSCHPL.AC = CICQACRL.O_DATA.O_AC_NO;
            B010_CHK_CARD_NO_PROC();
            if (pgmRtn) return;
        } else {
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSCHPL.AC;
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
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
    public void B035_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH_ORAL_LOSS_NOT_TR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.VCH_FORMAL_LOSS_NOT_TR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSCHPL.AC;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
    }
    public void B080_CHA_LINE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.AC = DDCSCHPL.AC;
        DDCIPSBK.FUNC = 'L';
        CEP.TRC(SCCGWA, DDCSCHPL.AC_CNAME);
        CEP.TRC(SCCGWA, DDCSCHPL.AC_ENAME);
        DDCIPSBK.AC_ENAME = DDCSCHPL.AC_ENAME;
        DDCIPSBK.AC_CNAME = DDCSCHPL.AC_CNAME;
        DDCIPSBK.CARD_NO = DDCSCHPL.CARD_NO;
        DDCIPSBK.PSBK_PAGE = DDCSCHPL.PSBK_PAGE;
        DDCIPSBK.PSBK_LINE = DDCSCHPL.PSBK_LINE;
        S000_CALL_DDZIPSBK();
        if (pgmRtn) return;
    }
    public void B098_UPD_MST_INF_PROC() throws IOException,SQLException,Exception {
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
    }
    public void B170_NFIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCCHPLO);
        DDCCHPLO.PSBK_PAGE = DDCSCHPL.OPSBK_PAGE;
        DDCCHPLO.PSBK_LINE = DDCSCHPL.OPSBK_LINE;
        IBS.init(SCCGWA, DDCCHPLN);
        DDCCHPLN.PSBK_PAGE = DDCSCHPL.PSBK_PAGE;
        DDCCHPLN.PSBK_LINE = DDCSCHPL.PSBK_LINE;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = DDCCHPLO;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCCHPLN;
        BPCPNHIS.INFO.AC = DDCSCHPL.AC;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_GET_PASSBOOK_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPBKS);
        DDCPBKS.KEY.TYP = "PDD01";
        DDCPBKS.KEY.CD = "PASSBOOK";
        BPCPRMR.DAT_PTR = DDCPBKS;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.MAX_PAGE);
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.PAGE_LINE);
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.MAX_LINE);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-PSBK-PROC", DDCIPSBK);
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
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSCHPL.AC;
        DDRVCH.VCH_TYPE = '1';
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.col = "PRT_LINE";
        IBS.READ(SCCGWA, DDRVCH, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
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
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
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
