package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCXJS {
    int JIBS_tmp_int;
    DBParm DDTVCH_RD;
    DBParm DDTMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "5122";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_HIS_COPYBOOK_NAME = "DDCSCXJS";
    String K_HIS_REMARKS = "CHUXU ACC TRANS JSUAN ACC";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_AC_TYPE = ' ';
    DDZSCXJS_WS_OUT_INF WS_OUT_INF = new DDZSCXJS_WS_OUT_INF();
    char WS_AC_HK_FLG = ' ';
    char WS_MST_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDRMST DDRMST = new DDRMST();
    DDRVCH DDRVCH = new DDRVCH();
    CICACCU CICACCU = new CICACCU();
    DDCHCXJS DDCHCXJSO = new DDCHCXJS();
    DDCHCXJS DDCHCXJSN = new DDCHCXJS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICSCPRD AICSCPRD = new AICSCPRD();
    CICSACAC CICSACAC = new CICSACAC();
    CICQACAC CICQACAC = new CICQACAC();
    CICCUST CICCUST = new CICCUST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSCXJS DDCSCXJS;
    public void MP(SCCGWA SCCGWA, DDCSCXJS DDCSCXJS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCXJS = DDCSCXJS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCXJS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_DDTMST_REC();
        if (pgmRtn) return;
        B025_CHK_DDTVCH_REC();
        if (pgmRtn) return;
        B030_TRANS_ITM_PROC();
        if (pgmRtn) return;
        B040_UPD_DDTMST_REC();
        if (pgmRtn) return;
        B040_UPD_ACAC_REC();
        if (pgmRtn) return;
        B050_OUTPUT_PROC();
        if (pgmRtn) return;
        B170_NFIN_TX_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
            if (DDCSCXJS.CUS_AC == null) DDCSCXJS.CUS_AC = "";
            JIBS_tmp_int = DDCSCXJS.CUS_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCSCXJS.CUS_AC += " ";
            if (!DDCSCXJS.CUS_AC.substring(0, 2).equalsIgnoreCase("FT")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FTBR_CANT_CHG_FZMQAC);
            }
        } else {
            if (DDCSCXJS.CUS_AC == null) DDCSCXJS.CUS_AC = "";
            JIBS_tmp_int = DDCSCXJS.CUS_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCSCXJS.CUS_AC += " ";
            if (DDCSCXJS.CUS_AC.substring(0, 2).equalsIgnoreCase("FT")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FZMQBR_CANT_CHG_FTAC);
            }
        }
    }
    public void B020_GET_DDTMST_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSCXJS.CUS_AC;
        T000_READ_UPD_DDTMST();
        if (pgmRtn) return;
        WS_AC_TYPE = DDRMST.AC_TYPE;
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCXJS.FUNC == '1') {
            if (DDRMST.AC_TYPE == 'A') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ALR_JSUAN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_TYPE != 'B') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_CHUXU;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HTM_CX_CANT_JS);
            }
        }
        if (DDCSCXJS.FUNC == '2') {
            if (DDRMST.AC_TYPE == 'B') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ALR_CXU;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_TYPE != 'A') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_CHUXU;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B023_CHECK_AC_HK_FLG();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSCXJS.CUS_AC;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
    }
    public void B023_CHECK_AC_HK_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = DDCSCXJS.CUS_AC;
        S000_CALL_CISOCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_REG_CNTY);
        if (CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("334") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("446") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("158") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("HKG") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("MAC") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("TWN")) {
            WS_AC_HK_FLG = '1';
        }
    }
    public void B025_CHK_DDTVCH_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSCXJS.CUS_AC;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        DDTVCH_RD.col = "CUS_AC,PAY_TYPE,PAY_IDTYPE,PAY_IDNO, PAY_SIGN_NO,PSBK_NO,PSBK_STS,PSBK_SEQ,PRT_LINE, UPT_CNT,UPT_LAST_NO,LAST_PB_CCY,LAST_PB_BAL, W_LOST_DATE,LOST_EXP_DATE, LOST_NO,PWD_LOST_NO";
        DDTVCH_RD.fst = true;
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_NOTFND;
            S000_ERR_MSG_PROC();
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
    public void B040_UPD_DDTMST_REC() throws IOException,SQLException,Exception {
        if (DDCSCXJS.FUNC == '1') {
            DDRMST.AC_TYPE = 'A';
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 23 - 1) + "0" + DDRMST.AC_STS_WORD.substring(23 + 1 - 1);
        }
        if (DDCSCXJS.FUNC == '2') {
            DDRMST.AC_TYPE = 'B';
            if (WS_AC_HK_FLG == '1') {
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 23 - 1) + "1" + DDRMST.AC_STS_WORD.substring(23 + 1 - 1);
            }
        }
        DDRMST.TRF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
    }
    public void B040_UPD_ACAC_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSCXJS.CUS_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL);
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'M';
        CICSACAC.DATA.ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        if (DDCSCXJS.FUNC == '1') {
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 5 - 1) + "1" + CICSACAC.DATA.ACAC_CTL.substring(5 + 1 - 1);
        }
        if (DDCSCXJS.FUNC == '2') {
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 5 - 1) + "0" + CICSACAC.DATA.ACAC_CTL.substring(5 + 1 - 1);
        }
        CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_CTL);
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void B030_TRANS_ITM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSCPRD);
        AICSCPRD.FUNC = 'A';
        AICSCPRD.DEAL_FLG = '2';
        AICSCPRD.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICSCPRD.AC_NO = DDCSCXJS.CUS_AC;
        AICSCPRD.CI_NO = CICACCU.DATA.CI_NO;
        AICSCPRD.OTH_AC = "00000001";
        AICSCPRD.BILL_NO = "00000000";
        AICSCPRD.AP_MMO = "DD";
        AICSCPRD.OLD_PROD_CD = DDRMST.PROD_CODE;
        AICSCPRD.NEW_PROD_CD = DDRMST.PROD_CODE;
        AICSCPRD.OLD_CI_TYP = CICACCU.DATA.CI_TYP;
        AICSCPRD.NEW_CI_TYP = CICACCU.DATA.CI_TYP;
        if (DDCSCXJS.FUNC == '1') {
            AICSCPRD.OLD_AC_TYP = 'B';
            AICSCPRD.NEW_AC_TYP = 'A';
        }
        if (DDCSCXJS.FUNC == '2') {
            AICSCPRD.OLD_AC_TYP = 'A';
            AICSCPRD.NEW_AC_TYP = 'B';
        }
        S000_CALL_AIZSCPRD();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_OUT_INF);
        WS_OUT_INF.WS_FUNC = DDCSCXJS.FUNC;
        WS_OUT_INF.WS_CUS_AC = DDRMST.KEY.CUS_AC;
        WS_OUT_INF.WS_CI_CNM = DDCSCXJS.CI_CNM;
        WS_OUT_INF.WS_AC_TYP_O = WS_AC_TYPE;
        WS_OUT_INF.WS_AC_TYP_N = DDCSCXJS.AC_TYP_N;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_INF);
        SCCMPAG.DATA_LEN = 288;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B170_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_NFHIS_NEW();
        if (pgmRtn) return;
        R000_TRANS_NFHIS_OLD();
        if (pgmRtn) return;
        R000_NFIN_TX_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_TRANS_NFHIS_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCHCXJSO);
        DDCHCXJSO.CUS_AC = DDCSCXJS.CUS_AC;
        DDCHCXJSO.CI_CNM = DDCSCXJS.CI_CNM;
        DDCHCXJSO.AC_TYPE = WS_AC_TYPE;
    }
    public void R000_TRANS_NFHIS_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCHCXJSN);
        DDCHCXJSO.CUS_AC = DDCSCXJS.CUS_AC;
        DDCHCXJSO.CI_CNM = DDCSCXJS.CI_CNM;
        DDCHCXJSO.AC_TYPE = DDCSCXJS.AC_TYP_N;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 288;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = DDCHCXJSO;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCHCXJSN;
        BPCPNHIS.INFO.AC = DDRMST.KEY.CUS_AC;
        BPCPNHIS.INFO.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        BPCPNHIS.INFO.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        BPCPNHIS.INFO.TX_CD = "0115460";
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        if (DDCSCXJS.FUNC == '1') {
            BPCPNHIS.INFO.TX_TYP_CD = "P162";
        }
        if (DDCSCXJS.FUNC == '2') {
            BPCPNHIS.INFO.TX_TYP_CD = "P162";
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_UPD_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
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
    public void S000_CALL_AIZSCPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-CPRD", AICSCPRD);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_CISOCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACAC.RC);
        }
    }
    public void S000_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
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
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
