package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZGTSCD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTBINPM_RD;
    DBParm DCTCDDAT_RD;
    DBParm DCTCDORD_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC066";
    String WS_ERR_MSG = " ";
    String WS_VOUCHER_CARD_NO = " ";
    String WS_CI_NO = " ";
    String WS_ID_TYPE = " ";
    String WS_ID_NO = " ";
    String WS_CI_NM = " ";
    String WS_SOCIAL_PSW = " ";
    String WS_CARD_PSW = " ";
    String WS_CDORD_BV_CD_NO = " ";
    String WS_CARD_PROD = " ";
    char WS_CARD_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRBINPM DCRBINPM = new DCRBINPM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    CICCUST CICCUST = new CICCUST();
    DCCUCSET DCCUCSET = new DCCUCSET();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCGTSCD DCCHISTY = new DCCGTSCD();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    CICQCHDC CICQCHDC = new CICQCHDC();
    CICSACAC CICSACAC = new CICSACAC();
    CICSACRL CICSACRL = new CICSACRL();
    CICSAGEN CICSAGEN = new CICSAGEN();
    CICSACR CICSACR = new CICSACR();
    CICCKOC CICCKOC = new CICCKOC();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    DCCF066 DCCF066 = new DCCF066();
    BPROCAC BPROCAC = new BPROCAC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS0066 DCCS0066;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DCCS0066 DCCS0066) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS0066 = DCCS0066;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZGTSCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        WS_CARD_FLG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B015_CHECK_CARD_INFO();
        if (pgmRtn) return;
        B020_CARD_VOUCHER();
        if (pgmRtn) return;
        B036_GENERATE_ACR();
        if (pgmRtn) return;
        B037_GENERATE_ACRL();
        if (pgmRtn) return;
        B040_UPDATE_ORDER_INFO();
        if (pgmRtn) return;
        if (DCCS0066.AGENT_FLG == '1') {
            B045_REGIST_AGENT_INF();
            if (pgmRtn) return;
        }
        B046_REGIST_OCAC_INF();
        if (pgmRtn) return;
        B050_OUTPUT_PROCESS();
        if (pgmRtn) return;
        B060_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS0066.CARD_NO);
        CEP.TRC(SCCGWA, DCCS0066.ID_TYP);
        CEP.TRC(SCCGWA, DCCS0066.ID_NO);
        CEP.TRC(SCCGWA, DCCS0066.CI_NAME);
        CEP.TRC(SCCGWA, DCCS0066.CI_NO);
        if (DCCS0066.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCCS0066.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_ID_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCCS0066.ID_TYP.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_ID_TYPE;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCCS0066.CI_NAME.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CI_NAME;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCCS0066.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_NO_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCCS0066.AGENT_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AGENT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCCS0066.DB_FREE == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DB_FREE_FLG_MISSING;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B015_CHECK_CARD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS0066.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_EXIST);
        }
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCS0066.CARD_NO;
        DCRCDORD.KEY.EXC_CARD_TMS = 0;
        T000_READ_DCTCDORD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        WS_CDORD_BV_CD_NO = DCRCDORD.BV_CD_NO;
        WS_CARD_PROD = DCRCDORD.CARD_PROD;
        if (!WS_CDORD_BV_CD_NO.equalsIgnoreCase("035")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_PROD_CD_INVALID);
        }
        IBS.init(SCCGWA, CICCKOC);
        CICCKOC.DATA.CI_NO = DCCS0066.CI_NO;
        CICCKOC.DATA.OPEN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICCKOC.DATA.PROD_CD = WS_CARD_PROD;
        CICCKOC.DATA.AGENT_FLG = DCCS0066.AGENT_FLG;
        CEP.TRC(SCCGWA, CICCKOC.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICCKOC.DATA.OPEN_BR);
        CEP.TRC(SCCGWA, CICCKOC.DATA.PROD_CD);
        S000_CALL_CIZCKOC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCCFCDGG);
        DCCFCDGG.VAL.FUNC = 'C';
        DCCFCDGG.VAL.CARD_NO = DCCS0066.CARD_NO;
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
        S000_CALL_DCZFCDGG();
        if (pgmRtn) return;
    }
    public void B020_CARD_VOUCHER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        if (DCCS0066.CARD_NO == null) DCCS0066.CARD_NO = "";
        JIBS_tmp_int = DCCS0066.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCS0066.CARD_NO += " ";
        DCRBINPM.KEY.BIN = DCCS0066.CARD_NO.substring(0, 6);
        T000_READ_DCTBINPM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.VB_FLG = '0';
        BPCUBUSE.COUNT_MTH = '1';
        if (DCCS0066.CARD_NO == null) DCCS0066.CARD_NO = "";
        JIBS_tmp_int = DCCS0066.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCS0066.CARD_NO += " ";
        BPCUBUSE.BEG_NO = DCCS0066.CARD_NO.substring(0, 18);
        BPCUBUSE.END_NO = DCCS0066.CARD_NO.substring(0, 18);
        BPCUBUSE.BV_CODE = WS_CDORD_BV_CD_NO;
        BPCUBUSE.NUM = 1;
        CEP.TRC(SCCGWA, "------------UBUSE-BEG-NO----------");
        CEP.TRC(SCCGWA, BPCUBUSE.BV_CODE);
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.END_NO);
        CEP.TRC(SCCGWA, BPCUBUSE);
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void B036_GENERATE_ACR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = DCCS0066.CARD_NO;
        CICSACR.DATA.ENTY_TYP = '2';
        CICSACR.DATA.STSW = "11011000";
        CICSACR.DATA.CI_NO = DCCS0066.CI_NO;
        CICSACR.DATA.PROD_CD = WS_CARD_PROD;
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.FRM_APP = "DC";
        CICSACR.DATA.CNTRCT_TYP = "229";
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B037_GENERATE_ACRL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.FUNC = 'A';
        CICSACRL.DATA.AC_NO = "123456";
        CICSACRL.DATA.AC_REL = "09";
        CICSACRL.DATA.REL_AC_NO = DCCS0066.CARD_NO;
        CICSACRL.DATA.DEFAULT = '0';
        S000_CALL_CIZSACRL();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_ORDER_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCS0066.CARD_NO;
        DCRCDORD.KEY.EXC_CARD_TMS = 0;
        T000_READUPD_DCTCDORD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        DCRCDORD.CRT_STS = '3';
        DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_DCTCDORD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCCUCSET);
        DCCUCSET.CARD_PROD_CD = DCRCDORD.CARD_PROD;
        CEP.TRC(SCCGWA, DCRCDORD.CARD_PROD);
        DCCUCSET.CARD_NO = DCCS0066.CARD_NO;
        DCCUCSET.HOLDER_CINO = DCCS0066.CI_NO;
        DCCUCSET.OWNER_CINO = DCCS0066.CI_NO;
        DCCUCSET.CARD_TYPE = '0';
        DCCUCSET.CARD_PSW = DCRCDORD.TRAN_PIN_DAT;
        DCCUCSET.SNAME_TRAN_FLG = 'Y';
        DCCUCSET.DNAME_TRAN_FLG = 'Y';
        DCCUCSET.OUT_DRAW_FLG = 'Y';
        DCCUCSET.LNK_TYP = '1';
        DCCUCSET.PROD_LMT_FLG = 'Y';
        DCCUCSET.CARD_STS = '2';
        DCCUCSET.HOLD_AC_FLG = 'N';
        DCCUCSET.CARD_TYP = "ZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROS";
        DCCUCSET.CARD_CLS_PROD = DCRCDORD.CARD_CLS_CD;
        DCCUCSET.BV_CD_NO = DCRCDORD.BV_CD_NO;
        DCCUCSET.SF_FLG = DCRCDORD.SF_FLG;
        DCCUCSET.CERT_EXP_DATE = DCRCDORD.CERT_EXP_DATE;
        CEP.TRC(SCCGWA, DCCUCSET.CARD_TYP);
        DCCUCSET.AC_TYPE = '1';
        DCCUCSET.DB_FREE = DCCS0066.DB_FREE;
        S000_CALL_DCZUCSET();
        if (pgmRtn) return;
        DCRCDDAT.KEY.CARD_NO = DCCS0066.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "KIA TESTING");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRCDDAT.EMBS_DT = DCRCDORD.CRT_DT;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDDAT.EXC_CARD_TMS = DCRCDORD.KEY.EXC_CARD_TMS;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B045_REGIST_AGENT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = DCCS0066.CI_NO;
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
            CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        } else {
            CICSAGEN.ID_TYP = DCCS0066.AGENT_IDTYP;
            CICSAGEN.ID_NO = DCCS0066.AGENT_IDNO;
            CICSAGEN.CI_NAME = DCCS0066.AGENT_NAME;
            CICSAGEN.PHONE = DCCS0066.AGENT_PHONE;
        }
        CICSAGEN.AGENT_TP = "01";
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void B046_REGIST_OCAC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        IBS.init(SCCGWA, BPROCAC);
        BPCSOCAC.FUNC = 'C';
        BPCSOCAC.AC = DCCS0066.CARD_NO;
        BPCSOCAC.STS = 'N';
        BPCSOCAC.WORK_TYP = "21";
        BPCSOCAC.CI_TYPE = '1';
        BPCSOCAC.BV_TYP = '1';
        if (DCCS0066.CARD_NO == null) DCCS0066.CARD_NO = "";
        JIBS_tmp_int = DCCS0066.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCS0066.CARD_NO += " ";
        BPCSOCAC.BV_NO = DCCS0066.CARD_NO.substring(0, 18);
        BPCSOCAC.ID_TYP = DCCS0066.ID_TYP;
        BPCSOCAC.ID_NO = DCCS0066.ID_NO;
        BPCSOCAC.CI_CNM = DCCS0066.CI_NAME;
        BPCSOCAC.CARD_FLG = '2';
        BPCSOCAC.CCY = "156";
        BPCSOCAC.CCY_TYPE = '1';
        BPCSOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.PROD_CD = WS_CARD_PROD;
        BPCSOCAC.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF066);
        DCCF066.CARD_NO = DCCS0066.CARD_NO;
        DCCF066.HOLDER_IDTYP = DCCS0066.ID_TYP;
        DCCF066.HOLDER_IDNO = DCCS0066.ID_NO;
        DCCF066.HOLDER_NAME = DCCS0066.CI_NAME;
        CEP.TRC(SCCGWA, DCCF066);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF066;
        SCCFMT.DATA_LEN = 346;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCHISTY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCS0066);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCHISTY);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "GET SOCIAL CARD";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCS0066";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCS0066.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCCS0066.CI_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.FMT_ID_LEN = 350;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCHISTY;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.col = "BIN, UNION_SHORT_NAME, UNION_NAME, CHK_DIG_MTH, PIN_MTH, CVV_TYP, CARD_LEN, SEG_NUM, SEG1_LEN, SEG1_RMK, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICSACRL);
        if (CICSACRL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZCKOC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-OPEN-CARD", CICCKOC);
        CEP.TRC(SCCGWA, CICCKOC.RC);
        if (CICCKOC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCKOC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZFCDGG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-F-CHK-DIGIT-GEN", DCCFCDGG);
        CEP.TRC(SCCGWA, DCCFCDGG.RC);
        if (DCCFCDGG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "1");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCFCDGG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCSET() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-SETUP", DCCUCSET);
        if (DCCUCSET.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READ_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.REWRITE(SCCGWA, DCRCDORD, DCTCDORD_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
