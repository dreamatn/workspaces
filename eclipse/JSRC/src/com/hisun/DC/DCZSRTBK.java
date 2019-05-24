package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.EA.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSRTBK {
    int JIBS_tmp_int;
    DBParm DDTCCY_RD;
    DBParm DCTINRCD_RD;
    DBParm DCTCDDAT_RD;
    DBParm DCTICERD_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "DCZSRTBK";
    String CPN_PARM_MT = "DC-GET-CARD-BACK";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_HIS_REMARK = "GET CARD BACK";
    String K_HIS_COPYBOOK = "DCRINRCD";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_OUTPUT_FMT = "DC301";
    String WS_ERR_MSG = " ";
    double WS_CURR_BAL = 0;
    String WS_CARD_NO_TEMP = " ";
    double WS_TEMP_AMT = 0;
    DCZSRTBK_WS_OUTPUT WS_OUTPUT = new DCZSRTBK_WS_OUTPUT();
    char WS_TBL_FLAG = ' ';
    char WS_STOP_CHECK_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCRAND SCCRAND = new SCCRAND();
    SCCCALL SCCCALL = new SCCCALL();
    DDRCCY DDRCCY = new DDRCCY();
    DCRINRCD DCRINRCD = new DCRINRCD();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICQACAC CICQACAC = new CICQACAC();
    SCCSTAR SCCSTAR = new SCCSTAR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCMTTP DDCMTTP = new DDCMTTP();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICQACRL CICQACRL = new CICQACRL();
    EACSBINQ EACSBINQ = new EACSBINQ();
    DCRICERD DCRICERD = new DCRICERD();
    SCCGWA SCCGWA;
    DCCSRTBK DCCSRTBK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSRTBK DCCSRTBK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSRTBK = DCCSRTBK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        B010_OUTPUT_PROCESS();
        if (pgmRtn) return;
        B020_HISTORY_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSRTBK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCSRTBK.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        if (DCCSRTBK.READ_FLG == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_READ_FLG);
        }
        if (DCCSRTBK.NEXT_STS == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_NEXT_STS);
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSRTBK.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STS != 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_ALREADY_FROZEN);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_BACK_STS);
        }
        if (DCCSRTBK.READ_FLG == '1') {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'I';
                CICQACAC.DATA.AGR_NO = DCCSRTBK.CARD_NO;
                CICQACAC.DATA.AID = "003";
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                IBS.init(SCCGWA, DDCMTTP);
                DDCMTTP.FUNC = '2';
                DDCMTTP.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                DDCMTTP.TAMT = DCCSRTBK.BALANCE;
                S000_CALL_DDZMTTP();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DCRINRCD);
            DCRINRCD.KEY.CARD_NO = DCCSRTBK.CARD_NO;
            DCRINRCD.KEY.CARD_SEQ = 1;
            DCRINRCD.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            DCRINRCD.KEY.INCD_TYPE = "12";
            DCRINRCD.PEND_STS = DCCSRTBK.NEXT_STS;
            DCRINRCD.PEND_DT = 0;
            DCRINRCD.PEND_AMT = 0;
            DCRINRCD.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DCRINRCD.AUT_TLR = SCCGWA.COMM_AREA.SUP1_ID;
            DCRINRCD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRINRCD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRINRCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRINRCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DCTINRCD();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCSRTBK.CARD_NO;
            T000_READ_DCTCDDAT_UPDATE();
            if (pgmRtn) return;
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 12 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(12 + 1 - 1);
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTCDDAT();
            if (pgmRtn) return;
        }
        if (DCCSRTBK.READ_FLG == '2') {
            CEP.TRC(SCCGWA, DCCSRTBK.NEXT_STS);
            if (DCCSRTBK.NEXT_STS == '2') {
                B050_CHECK_AC_LINK();
                if (pgmRtn) return;
                B060_CHECK_EALINK();
                if (pgmRtn) return;
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, DDCIQBAL);
                DDCIQBAL.DATA.AC = DCCSRTBK.CARD_NO;
                DDCIQBAL.DATA.AID = "003";
                S000_CALL_DDZIQBAL();
                if (pgmRtn) return;
                WS_CURR_BAL = DDCIQBAL.DATA.CURR_BAL;
                CEP.TRC(SCCGWA, WS_CURR_BAL);
            }
            WS_STOP_CHECK_FLG = 'N';
            WS_CARD_NO_TEMP = DCCSRTBK.CARD_NO;
            while (WS_STOP_CHECK_FLG != 'Y') {
                IBS.init(SCCGWA, DCRINRCD);
                DCRINRCD.NEW_CARD_NO = WS_CARD_NO_TEMP;
                T000_READ_DCTINRCD();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'Y' 
                    && DCRINRCD.PEND_AMT > 0) {
                    WS_TEMP_AMT = WS_TEMP_AMT + DCRINRCD.PEND_AMT;
                    WS_CARD_NO_TEMP = DCRINRCD.KEY.CARD_NO;
                }
                if (WS_TBL_FLAG == 'N' 
                    || DCRINRCD.PEND_AMT == 0) {
                    if (DCRINRCD.PEND_AMT == 0) {
                        IBS.init(SCCGWA, DCRICERD);
                        DCRICERD.KEY.CARD_NO = DCRINRCD.KEY.CARD_NO;
                        T000_READ_DCTICERD();
                        if (pgmRtn) return;
                        if (WS_TBL_FLAG == 'Y') {
                            WS_TEMP_AMT = DCRICERD.CARD_AMT;
                        }
                    }
                    WS_STOP_CHECK_FLG = 'Y';
                }
            }
            CEP.TRC(SCCGWA, WS_TEMP_AMT);
            if (WS_CURR_BAL >= WS_TEMP_AMT) {
                WS_CURR_BAL = WS_CURR_BAL - WS_TEMP_AMT;
            }
            CEP.TRC(SCCGWA, WS_CURR_BAL);
            IBS.init(SCCGWA, DCRINRCD);
            DCRINRCD.KEY.CARD_NO = DCCSRTBK.CARD_NO;
            DCRINRCD.KEY.CARD_SEQ = 1;
            DCRINRCD.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            DCRINRCD.KEY.INCD_TYPE = "11";
            DCRINRCD.PEND_STS = DCCSRTBK.NEXT_STS;
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                && (WS_CURR_BAL > 0)) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
                SCCCLDT.DAYS = 31;
                SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
                SCSSCLDT1.MP(SCCGWA, SCCCLDT);
                CEP.TRC(SCCGWA, SCCCLDT.RC);
                if (SCCCLDT.RC == 0) {
                    CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                    DCRINRCD.PEND_DT = SCCCLDT.DATE2;
                }
                DCRINRCD.PEND_AMT = WS_CURR_BAL;
            } else {
                DCRINRCD.PEND_DT = 0;
                DCRINRCD.PEND_AMT = 0;
            }
            DCRINRCD.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DCRINRCD.AUT_TLR = SCCGWA.COMM_AREA.SUP1_ID;
            DCRINRCD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRINRCD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRINRCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRINRCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DCTINRCD();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCSRTBK.CARD_NO;
            T000_READ_DCTCDDAT_UPDATE();
            if (pgmRtn) return;
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 11 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(11 + 1 - 1);
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTCDDAT();
            if (pgmRtn) return;
        }
    }
    public void B010_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CARD_NO = DCCSRTBK.CARD_NO;
        WS_OUTPUT.WS_PROCESS_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_PROCESS_TLR = SCCGWA.COMM_AREA.TL_ID;
        WS_OUTPUT.WS_PROCESS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 41;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 156;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.NEW_DAT_PT = DCRINRCD;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_CHECK_AC_LINK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.FUNC = '3';
        CICQCIAC.DATA.AGR_NO = DCCSRTBK.CARD_NO;
        CICQCIAC.DATA.FRM_APP = "TD";
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO);
        if (CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO.trim().length() > 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_EST_TD_BAL);
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.REL_AC_NO = DCCSRTBK.CARD_NO;
        CICQACRL.DATA.AC_REL = "05";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        if (CICQACRL.O_DATA.O_REL_STS == '0') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_LINK_OTH_AC);
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.REL_AC_NO = DCCSRTBK.CARD_NO;
        CICQACRL.DATA.AC_REL = "06";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        if (CICQACRL.O_DATA.O_REL_STS == '0') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_LINK_OTH_AC);
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.REL_AC_NO = DCCSRTBK.CARD_NO;
        CICQACRL.DATA.AC_REL = "07";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        if (CICQACRL.O_DATA.O_REL_STS == '0') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_LINK_OTH_AC);
        }
    }
    public void B060_CHECK_EALINK() throws IOException,SQLException,Exception {
        if (DCRCDDAT.ACNO_TYPE == '1') {
            IBS.init(SCCGWA, EACSBINQ);
            EACSBINQ.I_AC = DCCSRTBK.CARD_NO;
            S000_CALL_EAZSBINQ();
            if (pgmRtn) return;
            if (EACSBINQ.END_SEQ > 0) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_EALINK_EXIST);
            }
        }
    }
    public void T000_READ_DDTCCY_UPDATE() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.col = "AC,NOT_INT_BAL";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_DD_CCY_NOTFND);
        }
    }
    public void T000_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_WRITE_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        DCTINRCD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRINRCD, DCTINRCD_RD);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "CARD_NO,CARD_STS,CARD_STSW";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
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
    public void T000_READ_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        DCTINRCD_RD.where = "NEW_CARD_NO = :DCRINRCD.NEW_CARD_NO";
        IBS.READ(SCCGWA, DCRINRCD, this, DCTINRCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTINRCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTICERD() throws IOException,SQLException,Exception {
        DCTICERD_RD = new DBParm();
        DCTICERD_RD.TableName = "DCTICERD";
        DCTICERD_RD.where = "CARD_NO = :DCRICERD.KEY.CARD_NO "
            + "AND NEXT_PROCESS_STS = '0'";
        DCTICERD_RD.fst = true;
        DCTICERD_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, DCRICERD, this, DCTICERD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTICERD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT_UPDATE() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "CARD_NO,CARD_STSW";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
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
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DDZMTTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-ZMTTP-TAM", DDCMTTP);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_EAZSBINQ() throws IOException,SQLException,Exception {
        EAZSBINQ EAZSBINQ = new EAZSBINQ();
        EAZSBINQ.MP(SCCGWA, EACSBINQ);
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
