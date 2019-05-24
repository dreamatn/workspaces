package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSATAC {
    int JIBS_tmp_int;
    DBParm DDTINF_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    String K_OUTPUT_FMT = "DD014";
    String K_STS_TABLE_APP = "DD";
    String K_CUS_DR_STS_TBL = "5405";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_PROC_FHIS = "BP-PROC-FHIS";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_HIS_COPYBOOK_NAME = "DDCSATAC";
    String K_HIS_REMARKS = "ACTIVATION ACCOUNT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    String WS_STD_AC = " ";
    char WS_UPT_MST_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
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
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICQACAC CICQACAC = new CICQACAC();
    CICACCU CICACCU = new CICACCU();
    DDCOATAC DDCOATAC = new DDCOATAC();
    DDCSCINM DDCSCINM = new DDCSCINM();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMACR CICMACR = new CICMACR();
    DDRINF DDRINF = new DDRINF();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    DDCSATAC DDCSATAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCSATAC DDCSATAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSATAC = DDCSATAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSATAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_CHK_AC_STS();
        B040_GET_CI_INF_PROC();
        B050_CHECK_STS_TBL_PROC();
        B098_UPD_MST_INF_PROC();
        B000_UPD_BP_OCAC_PROC();
        if (WS_UPT_MST_FLAG == 'Y') {
            B170_NFIN_TX_HIS_PROC();
        }
        B100_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSATAC.AC);
        CEP.TRC(SCCGWA, DDCSATAC.OPEN_NO);
        CEP.TRC(SCCGWA, DDCSATAC.EFF_DATE);
        CEP.TRC(SCCGWA, DDCSATAC.EXP_DATE);
        CEP.TRC(SCCGWA, DDCSATAC.CHK_DATE);
        if (DDCSATAC.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSATAC.EFF_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCSATAC.EFF_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCSATAC.EXP_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCSATAC.EXP_DATE;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TEXP_DT_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCSATAC.EFF_DATE != 0 
            && DDCSATAC.EXP_DATE != 0 
            && DDCSATAC.EXP_DATE <= DDCSATAC.EFF_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EXPDT_M_GREAT_EFFDT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSATAC.AC;
        T000_READ_UPDATE_DDTMST();
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, DDRMST.OPEN_DP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, DDRMST.OWNER_BR);
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        if (DDRMST.OWNER_BR != BPCPQORG.BBR 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ERR_TXN_BRANCH);
        }
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        if (DDRMST.AC_TYPE == 'P') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ACTIVE_NOT_ALLOW);
        }
        if (DDRMST.AC_TYPE == 'F' 
            && DDCSATAC.EXP_DATE == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TEMP_AC_EXP_DT_MUST);
        }
        CEP.TRC(SCCGWA, DDRMST.CHCK_IND);
        if (DDRMST.CHCK_IND == '1') {
            if (DDCSATAC.CHK_DATE == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4012;
                S000_ERR_MSG_PROC();
            } else {
                if (DDCSATAC.CHK_DATE != 0) {
                    IBS.init(SCCGWA, SCCCKDT);
                    SCCCKDT.DATE = DDCSATAC.CHK_DATE;
                    SCSSCKDT SCSSCKDT3 = new SCSSCKDT();
                    SCSSCKDT3.MP(SCCGWA, SCCCKDT);
                    if (SCCCKDT.RC != 0) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
                        S000_ERR_MSG_PROC();
                    }
                    if (DDCSATAC.EFF_DATE == 0) {
                        IBS.init(SCCGWA, BPCPGDIN);
                        BPCPGDIN.INPUT_DATA.FUNC = '2';
                        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
                        BPCPGDIN.INPUT_DATA.CCY = "156";
                        CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.CCY);
                        BPCPGDIN.INPUT_DATA.DATE_1 = DDCSATAC.CHK_DATE;
                        BPCPGDIN.INPUT_DATA.WDAYS = 4;
                        CEP.TRC(SCCGWA, DDCSATAC.CHK_DATE);
                        CEP.TRC(SCCGWA, DDCSATAC.EFF_DATE);
                        S00_CALL_BPZPGDIN();
                        DDCSATAC.EFF_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
                        CEP.TRC(SCCGWA, DDCSATAC.EFF_DATE);
                    }
                    if (DDCSATAC.EFF_DATE < DDCSATAC.CHK_DATE) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4013;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        } else {
            if (DDRMST.CHCK_IND == '2' 
                && DDCSATAC.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4014;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ACTIVED;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS != 'O') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4016;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_INFO_NOT_INTO;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.CHCK_IND == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHK_IND_EMPTY);
        }
    }
    public void B040_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSATAC.AC;
        CICACCU.DATA.ENTY_TYP = '1';
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_STSW);
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CUST_F_JUS_FF);
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSATAC.AC;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
    }
    public void B050_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL;
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
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(2 - 1, 2 + 1 - 1));
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
    }
    public void B060_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRMST.PROD_CODE;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        S000_CALL_DDZIQPRD();
        if (DDVMPRD.VAL.CUST_TYPE == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_COMP_FIN_PROD_ONLY;
            S000_ERR_MSG_PROC();
        }
    }
    public void B098_UPD_MST_INF_PROC() throws IOException,SQLException,Exception {
        if (DDRMST.CHCK_IND == '2' 
            || DDRMST.CHCK_IND == '3') {
            DDRMST.AC_STS = 'N';
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 2 - 1) + "0" + DDRMST.AC_STS_WORD.substring(2 + 1 - 1);
            DDRMST.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_DDTMST();
            WS_UPT_MST_FLAG = 'Y';
        } else {
            if (DDRMST.CHCK_IND == '1') {
                CEP.TRC(SCCGWA, DDCSATAC.EFF_DATE);
                CEP.TRC(SCCGWA, DDCSATAC.OPEN_NO);
                CEP.TRC(SCCGWA, DDCSATAC.CHK_DATE);
                CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
                IBS.init(SCCGWA, DDRINF);
                DDRINF.KEY.CUS_AC = DDCSATAC.AC;
                T000_READ_UPD_DDTINF();
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    DDRINF.BASE_NO = DDCSATAC.OPEN_NO;
                    T000_REWRITE_DDTINF();
                } else {
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                        DDRINF.BASE_NO = DDCSATAC.OPEN_NO;
                        T000_WRITE_DDTINF();
                    }
                }
                DDRMST.EFF_DATE = DDCSATAC.EFF_DATE;
                if (DDRMST.AC_TYPE == 'F') {
                    DDRMST.EXP_DATE = DDCSATAC.EXP_DATE;
                }
                DDRMST.PBC_APPR_DATE = DDCSATAC.CHK_DATE;
                if (DDCSATAC.EFF_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                    CEP.TRC(SCCGWA, DDRMST.AC_STS);
                    if (DDRMST.AC_STS == 'N') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_REC_EXIST;
                        S000_ERR_MSG_PROC();
                    } else {
                        DDRMST.AC_STS = 'N';
                        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 2 - 1) + "0" + DDRMST.AC_STS_WORD.substring(2 + 1 - 1);
                    }
                }
                T000_REWRITE_DDTMST();
                WS_UPT_MST_FLAG = 'Y';
            }
        }
    }
    public void B000_UPD_BP_OCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = DDCSATAC.AC;
        BPCSOCAC.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCSOCAC.OPEN_NO = DDCSATAC.OPEN_NO;
        BPCSOCAC.STS = 'N';
        S000_CALL_BPZSOCAC();
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSOCAC.RC);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void T000_READ_UPD_DDTINF() throws IOException,SQLException,Exception {
        DDTINF_RD = new DBParm();
        DDTINF_RD.TableName = "DDTINF";
        DDTINF_RD.upd = true;
        IBS.READ(SCCGWA, DDRINF, DDTINF_RD);
    }
    public void T000_REWRITE_DDTINF() throws IOException,SQLException,Exception {
        DDRINF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTINF_RD = new DBParm();
        DDTINF_RD.TableName = "DDTINF";
        IBS.REWRITE(SCCGWA, DDRINF, DDTINF_RD);
    }
    public void T000_WRITE_DDTINF() throws IOException,SQLException,Exception {
        DDRINF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRINF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTINF_RD = new DBParm();
        DDTINF_RD.TableName = "DDTINF";
        IBS.WRITE(SCCGWA, DDRINF, DDTINF_RD);
    }
    public void B110_REG_BASE_NO() throws IOException,SQLException,Exception {
        if (DDCSATAC.OPEN_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICMACR);
            CICMACR.FUNC = '6';
            CICMACR.DATA.ENTY_TYP = '1';
            CICMACR.DATA.OPEN_AC = DDCSATAC.AC;
            CICMACR.DATA.AGR_NO = DDCSATAC.AC;
            CICMACR.DATA.CI_NO = CICACCU.DATA.CI_NO;
            CICMACR.DATA.BASE_NO = DDCSATAC.OPEN_NO;
            CEP.TRC(SCCGWA, CICMACR.DATA.CI_NO);
            S000_CALL_CIZMACR();
        }
    }
    public void B130_CALL_MSGS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        CEP.TRC(SCCGWA, DDRMST.OPEN_DP);
        T000_CALL_MSGS_PROC();
    }
    public void B100_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOATAC);
        DDCOATAC.AC = DDCSATAC.AC;
        DDCOATAC.AC_NAME = CICACCU.DATA.AC_CNM;
        DDCOATAC.EFF_DATE = DDCSATAC.EFF_DATE;
        DDCOATAC.CHK_DATE = DDCSATAC.CHK_DATE;
        DDCOATAC.OPEN_NO = DDCSATAC.OPEN_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOATAC;
        SCCFMT.DATA_LEN = 321;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B170_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = DDCSATAC.AC;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.TX_CD = "0115810";
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "PB07";
        S000_CALL_BPZPNHIS();
    }
    public void R000_RTV_STD_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.FUNC = '3';
        DCCPACTY.INPUT.AC = DDCSATAC.AC;
        S000_CALL_DCZPACTY();
        WS_STD_AC = DCCPACTY.OUTPUT.STD_AC;
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
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
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        CEP.TRC(SCCGWA, BPCFCSTS.RC);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PROC_FHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_DDPRD, DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void S00_CALL_BPZPGDIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-GET-DT-INFO", BPCPGDIN);
        if (BPCPGDIN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_CALL_MSGS_PROC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
