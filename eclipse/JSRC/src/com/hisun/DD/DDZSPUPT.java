package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSPUPT {
    int JIBS_tmp_int;
    DBParm DDTVCH_RD;
    DBParm DDTUPT_RD;
    DBParm DDTMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DD";
    String K_STS_TBL = "5500";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String CPN_REC_NHIS = "BP-REC-NHIS  ";
    String K_HIS_RMKS = "CHANGE PASSBOOK PRT UPT";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_I_PSBK_PROC = "DD-I-PSBK-PROC";
    String K_HIS_COPYBOOK_NAME = "DDCSPUPT";
    String K_HIS_REMARKS = "ACCOUNT PRINT PASSBOOK";
    String WS_ERR_MSG = " ";
    char WS_TABLE_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCCPRL BPCCPRL = new BPCCPRL();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    CICACCU CICACCU = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACRL CICQACRL = new CICQACRL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCCINTI BPCCINTI = new BPCCINTI();
    DDRMST DDRMST = new DDRMST();
    DDRUPT DDRUPT = new DDRUPT();
    DDRVCH DDRVCH = new DDRVCH();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSPUPT DDCSPUPT;
    public void MP(SCCGWA SCCGWA, DDCSPUPT DDCSPUPT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSPUPT = DDCSPUPT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSPUPT return!");
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
        B040_CI_INF_PROC();
        if (pgmRtn) return;
        B050_CHECK_STS_TBL_PROC();
        if (pgmRtn) return;
        B080_PRT_UPT_PROC();
        if (pgmRtn) return;
        B098_UPD_MST_INF_PROC();
        if (pgmRtn) return;
        B170_NFIN_TXN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B001_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCSPUPT.AC;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        CEP.TRC(SCCGWA, DDCSPUPT.AC);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSPUPT.AC);
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.REL_AC_NO = DDCSPUPT.AC;
        CICQACRL.DATA.AC_REL = "12";
        CICQACRL.FUNC = '4';
        CICQACRL.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
        if (CICQACRL.RC.RC_CODE == 0) {
            DDCSPUPT.AC = CICQACRL.O_DATA.O_AC_NO;
            B010_CHK_CARD_NO_PROC();
            if (pgmRtn) return;
        } else {
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSPUPT.AC;
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
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSPUPT.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
    }
    public void B050_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_STS_TBL;
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
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 100 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(100 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B070_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRMST.PROD_CODE;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
    }
    public void B080_PRT_UPT_PROC() throws IOException,SQLException,Exception {
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRUPT);
        DDRUPT.KEY.CUS_AC = DDCSPUPT.AC;
        T000_STARTBR_DDTUPT();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_UPT_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.AC = DDCSPUPT.AC;
        DDCIPSBK.AC_ATTR = DDRMST.AC_TYPE;
        DDCIPSBK.FUNC = 'P';
        S000_CALL_DDZIPSBK();
        if (pgmRtn) return;
    }
    public void B098_UPD_MST_INF_PROC() throws IOException,SQLException,Exception {
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
    }
    public void B170_NFIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        if (CICQACRL.RC.RC_CODE == 0) {
            BPCPNHIS.INFO.AC = CICQACRL.DATA.REL_AC_NO;
            BPCPNHIS.INFO.TX_TOOL = CICQACRL.DATA.REL_AC_NO;
        } else {
            BPCPNHIS.INFO.AC = DDCSPUPT.AC;
            BPCPNHIS.INFO.TX_TOOL = DDCSPUPT.AC;
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CCY = CICACCU.DATA.CCY;
        if (CICACCU.DATA.CCY.equalsIgnoreCase("156") 
            && CICACCU.DATA.CCY.trim().length() > 0) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P103";
        CEP.TRC(SCCGWA, BPCPNHIS);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSPUPT.AC;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRVCH.UPT_CNT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && DDCIPSBK.PSBK_NO.equalsIgnoreCase("999999")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_CHANGE_PSB;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRVCH.UPT_CNT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_UPT_CNT_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRVCH.PSBK_STS == 'U') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ULIST_PSBK);
        }
        if (DDRVCH.PSBK_STS == 'W') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_WLST_PSBK);
        }
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
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_DDPRD, DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_PSBK_PROC, DDCIPSBK);
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTUPT() throws IOException,SQLException,Exception {
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        DDTUPT_RD.where = "CUS_AC = :DDRUPT.KEY.CUS_AC";
        DDTUPT_RD.fst = true;
        DDTUPT_RD.order = "UPT_NO";
        IBS.READ(SCCGWA, DDRUPT, this, DDTUPT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSPUPT.AC);
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
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
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
