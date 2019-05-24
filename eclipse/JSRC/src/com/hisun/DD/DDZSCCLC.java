package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.GD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCCLC {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String K_OUTPUT_FMT = "DD845";
    String K_HIS_REMARKS = "CLOSE ACCOUNT";
    String K_HIS_MMO = "P115";
    String K_HIS_COPYBOOK_NAME = "DDCSCCLC";
    String WS_ERR_MSG = " ";
    char WS_IAACR_INFO = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCOCCLC DDCOCCLC = new DDCOCCLC();
    DDCBSP32 DDCBSP32 = new DDCBSP32();
    CICMACR CICMACR = new CICMACR();
    CICACCU CICACCU = new CICACCU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICSACAC CICSACAC = new CICSACAC();
    BPCRFPDD BPCRFPDD = new BPCRFPDD();
    GDCRSTAC GDCRSTAC = new GDCRSTAC();
    GDCUSTPL GDCUSTPL = new GDCUSTPL();
    GDCUSTAC GDCUSTAC = new GDCUSTAC();
    DDCUFEES DDCUFEES = new DDCUFEES();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    DDCSCCLC DDCSCCLC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSCCLC DDCSCCLC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCCLC = DDCSCCLC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSCCLC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_RTV_AC_INFO();
        B030_CLS_STD_AC_PROC();
        B040_WAVE_ANNUAL_FEES();
        B080_NON_FIN_HIS_PROC();
        B090_OUTPUT_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCCLC.FUNC);
        CEP.TRC(SCCGWA, DDCSCCLC.CARD_NO);
        CEP.TRC(SCCGWA, DDCSCCLC.AC);
        CEP.TRC(SCCGWA, DDCSCCLC.CCY);
        CEP.TRC(SCCGWA, DDCSCCLC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCCLC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSCCLC.RMK);
        CEP.TRC(SCCGWA, DDCSCCLC.NRTV);
        if (DDCSCCLC.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCCLC.FUNC != 'V' 
            && DDCSCCLC.FUNC != 'M') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCCLC.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCCLC.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCCLC.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_RTV_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCSCCLC.AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_STSW);
        B020_10_CHK_STD_AC_STS_PROC();
    }
    public void B020_10_CHK_STD_AC_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSCCLC.AC;
        T000_READ_UPDATE_DDTMST();
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        if (DDRMST.CI_TYP != '2' 
            && DDRMST.CI_TYP != '3') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_MUST_UNIT_AC;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if ((DDRMST.AC_TYPE == 'N' 
            || DDRMST.AC_TYPE == 'J' 
            || DDRMST.AC_TYPE == 'O') 
            && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && JIBS_tmp_str[1].equalsIgnoreCase("115846")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.M_GDWR_CNEL_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (CICCUST.O_DATA.O_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && DDRMST.AC_STS == 'V') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_FORBID_AC_NOCHECK;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDCSCCLC.FUNC);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDCSCCLC.FUNC == 'V' 
            && DDRMST.AC_STS != 'V' 
            && !DDRMST.AC_STS_WORD.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_STS_NOT_V;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        if ((DDRMST.AC_TYPE != ' ' 
            && DDRMST.AC_TYPE != 'N' 
            && DDRMST.AC_TYPE != 'J' 
            && DDRMST.AC_TYPE != 'O') 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B067_CHK_GD_CONTRACT();
        }
        if ((DDRMST.AC_TYPE == 'N' 
            || DDRMST.AC_TYPE == 'J' 
            || DDRMST.AC_TYPE == 'O') 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B068_CHK_GD_RLTSHIP();
        }
        if ((DDRMST.AC_TYPE == 'N' 
            || DDRMST.AC_TYPE == 'J' 
            || DDRMST.AC_TYPE == 'O') 
            && SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && (DDRMST.YW_TYP.equalsIgnoreCase("01") 
            || DDRMST.YW_TYP.equalsIgnoreCase("02") 
            || DDRMST.YW_TYP.equalsIgnoreCase("04") 
            || DDRMST.YW_TYP.equalsIgnoreCase("09") 
            || DDRMST.YW_TYP.equalsIgnoreCase("10"))) {
            B069_CHK_AC_RLTSHIP();
        }
    }
    public void B030_CLS_STD_AC_PROC() throws IOException,SQLException,Exception {
        B030_10_CHK_CCY_PROC();
        if (DDRMST.AC_STS == 'V') {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCSCCLC.AC;
            DDTMST_RD = new DBParm();
            DDTMST_RD.TableName = "DDTMST";
            DDTMST_RD.upd = true;
            IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, "VVVVVV");
            DDRMST.CLOSE_AC_STS = DDRMST.AC_STS;
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = "0" + DDRMST.AC_STS_WORD.substring(1);
            DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTMST();
        }
        IBS.init(SCCGWA, DDCBSP32);
        DDCBSP32.AC = DDCSCCLC.AC;
        DDCBSP32.CCY_CCY = DDCSCCLC.CCY;
        DDCBSP32.CCY_TYPE = DDCSCCLC.CCY_TYPE;
        DDCBSP32.CLOSE_INT = DDCSCCLC.CLOSE_INT;
        DDCBSP32.CLOSE_AMT = DDCSCCLC.CLOSE_AMT;
        S000_CALL_DDZSBSP32();
    }
    public void B030_10_CHK_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CCY = DDCSCCLC.CCY;
        DDRCCY.CCY_TYPE = DDCSCCLC.CCY_TYPE;
        DDRCCY.CUS_AC = DDCSCCLC.AC;
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_READ_UPDATE_DDTCCY();
        if (DDRCCY.CURR_BAL != 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CCY_HAS_FLT_BAL;
            S000_ERR_MSG_PROC();
        }
    }
    public void B040_WAVE_ANNUAL_FEES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUFEES);
        DDCUFEES.FUNC = '3';
        DDCUFEES.AC_NO = DDCSCCLC.AC;
        DDCUFEES.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        S000_CALL_DDZUFEES();
    }
    public void B080_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = DDCSCCLC.AC;
        BPCPNHIS.INFO.CCY = DDCSCCLC.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDCSCCLC.CCY_TYPE;
        BPCPNHIS.INFO.CI_NO = CICCUST.O_DATA.O_CI_NO;
        if (DDCSCCLC.FUNC == 'V') {
            BPCPNHIS.INFO.TX_CD = "0115844";
        }
        if (DDCSCCLC.FUNC == 'M') {
            BPCPNHIS.INFO.TX_CD = "0115845";
        }
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TOOL = DDCSCCLC.AC;
        BPCPNHIS.INFO.TX_TYP_CD = K_HIS_MMO;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCSCCLC;
        S000_CALL_BPZPNHIS();
    }
    public void B067_CHK_GD_CONTRACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCRSTAC);
        IBS.init(SCCGWA, GDRSTAC);
        GDCRSTAC.FUNC = 'B';
        GDCRSTAC.OPT = 'C';
        GDRSTAC.ST_AC = DDCSCCLC.AC;
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
        GDCRSTAC.OPT = 'R';
        GDRSTAC.ST_AC = DDCSCCLC.AC;
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAVE_GD_CONT;
            S000_ERR_MSG_PROC();
        }
        GDCRSTAC.OPT = 'E';
        IBS.init(SCCGWA, GDCRSTAC);
        IBS.init(SCCGWA, GDRSTAC);
        GDCRSTAC.FUNC = 'B';
        GDCRSTAC.OPT = 'T';
        GDRSTAC.INT_AC = DDCSCCLC.AC;
        CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
        GDCRSTAC.OPT = 'R';
        GDRSTAC.INT_AC = DDCSCCLC.AC;
        CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAVE_GD_CONT;
            S000_ERR_MSG_PROC();
        }
        GDCRSTAC.OPT = 'E';
        S000_CALL_GDZRSTAC();
    }
    public void B068_CHK_GD_RLTSHIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUSTPL);
        GDCUSTPL.INPUT.AC = DDCSCCLC.AC;
        S000_CALL_GDZUSTPL();
        CEP.TRC(SCCGWA, GDCUSTPL.OUTPUT.RLT_STS);
        if (GDCUSTPL.OUTPUT.RLT_STS == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_GDAC_HAS_RLT);
        }
    }
    public void B069_CHK_AC_RLTSHIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUSTAC);
        GDCUSTAC.AC = DDCSCCLC.AC;
        S000_CALL_GDZUSTAC();
        CEP.TRC(SCCGWA, GDCUSTAC.AC);
    }
    public void B090_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOCCLC);
        DDCOCCLC.CARD_NO = DDCSCCLC.CARD_NO;
        DDCOCCLC.AC = DDCSCCLC.AC;
        DDCOCCLC.AC_CNM = CICQACRI.O_DATA.O_AC_CNM;
        DDCOCCLC.AC_ENM = CICQACRI.O_DATA.O_AC_ENM;
        DDCOCCLC.CCY = DDCSCCLC.CCY;
        DDCOCCLC.CCY_TYPE = DDCSCCLC.CCY_TYPE;
        DDCOCCLC.PSBK_NO = DDCSCCLC.PSBK_NO;
        DDCOCCLC.NRTV = DDCSCCLC.NRTV;
        DDCOCCLC.RMK = DDCSCCLC.RMK;
        CEP.TRC(SCCGWA, DDCOCCLC.CARD_NO);
        CEP.TRC(SCCGWA, DDCOCCLC.AC);
        CEP.TRC(SCCGWA, DDCOCCLC.AC_CNM);
        CEP.TRC(SCCGWA, DDCOCCLC.AC_ENM);
        CEP.TRC(SCCGWA, DDCOCCLC.CCY);
        CEP.TRC(SCCGWA, DDCOCCLC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCOCCLC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCOCCLC.NRTV);
        CEP.TRC(SCCGWA, DDCOCCLC.RMK);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOCCLC;
        SCCFMT.DATA_LEN = 835;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZSBSP32() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-AC-CLS", DDCBSP32);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACAC.RC);
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSOCAC.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUFEES() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-M-WAVE-FEES", DDCUFEES);
        CEP.TRC(SCCGWA, DDCUFEES.RC);
        if (DDCUFEES.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUFEES.RC);
        }
    }
    public void T000_READ_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "ENDBR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = DDRCCY.KEY.AC;
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S000_CALL_GDZRSTAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRSTAC", GDCRSTAC);
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE != 0 
            && GDCRSTAC.RC.RC_CODE != 2130) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRSTAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZUSTPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRV-GDZUSTPL", GDCUSTPL);
    }
    public void S000_CALL_GDZUSTAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-STAC-UP", GDCUSTAC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
