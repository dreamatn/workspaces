package com.hisun.EA;

import com.hisun.DC.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EAZSBIND {
    DBParm EATACLNK_RD;
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_CNT = 0;
    char WS_CON_STS = ' ';
    String WS_CARD_NO = " ";
    String WS_CON_NO = " ";
    String WS_CON_BNK = " ";
    char WS_EATACLNK_FLG = ' ';
    char WS_BIND_FLG = ' ';
    String WS_ERR_MSG = " ";
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_CURRENT_ROW = 0;
    int WS_MIN_ROW = 0;
    int WS_MAX_ROW = 0;
    int WS_RECORD_NUM = 0;
    char WS_BIND_CARD_STS = ' ';
    String WS_SBIND_CARD_CINO = "            ";
    String WS_CON_AC_CINO = "            ";
    String WS_BEFORE_CON_AC = "                                ";
    String WS_BEFORE_CON_BANK = "              ";
    EACMSG_ERROR_MSG EACMSG_ERROR_MSG = new EACMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCBINF SCCBINF = new SCCBINF();
    CICACCU CICACCU = new CICACCU();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCSCGET DCCSCGET = new DCCSCGET();
    CICQACAC CICQACAC = new CICQACAC();
    EACO590 EACO590 = new EACO590();
    CICCKLS CICCKLS = new CICCKLS();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DDCIMMST DDCIMMST = new DDCIMMST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DDRMST DDRMST = new DDRMST();
    EARACLNK EARACLNK = new EARACLNK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EACSBIND EACSBIND;
    public void MP(SCCGWA SCCGWA, EACSBIND EACSBIND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EACSBIND = EACSBIND;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EAZSBIND return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (EACSBIND.FUNC == 'B') {
            CEP.TRC(SCCGWA, "INSERT-SBIND-INF");
            B090_CHECK_DATA_FOR_INSERT();
            if (pgmRtn) return;
            B110_CHECK_SBIND_CARD_NO();
            if (pgmRtn) return;
            R000_READ_SBIND_INF();
            if (pgmRtn) return;
            if (WS_EATACLNK_FLG == 'Y') {
                B030_REWRITE_SBIND_INF();
                if (pgmRtn) return;
            } else {
                B010_INSERT_SBIND_FUNC_INF();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_BIND_CARD_STS);
            if (WS_BIND_CARD_STS != 'N') {
                B015_ACTIVE_CARD_STS();
                if (pgmRtn) return;
            }
        } else if (EACSBIND.FUNC == 'C') {
            CEP.TRC(SCCGWA, "REWRITE-SBIND-INF");
            R100_READ_FOR_SBIND_COUNT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            if (WS_CNT > 0) {
                B080_CHECK_DATA_FOR_DELETE();
                if (pgmRtn) return;
                R000_READUPDATE_SBIND_INF_C();
                if (pgmRtn) return;
                B030_REWRITE_SBIND_INF();
                if (pgmRtn) return;
            }
        } else if (EACSBIND.FUNC == 'U') {
            CEP.TRC(SCCGWA, "REPLACE-SBIND-INF");
            R100_READ_FOR_SBIND_COUNT();
            if (pgmRtn) return;
            if (WS_CNT == 1) {
                B090_CHECK_DATA_FOR_INSERT();
                if (pgmRtn) return;
                R000_READUPDATE_SBIND_INF_U();
                if (pgmRtn) return;
                B130_REPLACE_SBIND_INF();
                if (pgmRtn) return;
            } else {
                if (WS_CNT > 1) {
                    CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_BIND_AC_TOO_MORE);
                } else {
                    CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_NOT_BIND_AC);
                }
            }
        } else {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_FUNC_ERROR);
        }
        B300_REC_HISTRY();
        if (pgmRtn) return;
    }
    public void B010_INSERT_SBIND_FUNC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EARACLNK);
        EARACLNK.KEY.CARD_NO = EACSBIND.CARD_NO;
        EARACLNK.KEY.CON_AC = EACSBIND.CON_AC;
        EARACLNK.KEY.CON_BNK = EACSBIND.CON_BNK;
        EARACLNK.CON_NME = EACSBIND.CON_NME;
        EARACLNK.IO_FLG = EACSBIND.IO_FLG;
        EARACLNK.AC_NAME = EACSBIND.AC_NM;
        EARACLNK.CON_STS = EACSBIND.FUNC;
        EARACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EARACLNK.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EARACLNK.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EARACLNK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T100_INSERT_EATACLNK();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_FOR_EATACLNK();
        if (pgmRtn) return;
    }
    public void B015_ACTIVE_CARD_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSBIND.CARD_NO);
        IBS.init(SCCGWA, DCCSCGET);
        DCCSCGET.CARD_NO = EACSBIND.CARD_NO;
        DCCSCGET.DOUB_FLG = 'N';
        CEP.TRC(SCCGWA, DCCSCGET.CARD_NO);
        S000_CALL_DCZSCGET();
        if (pgmRtn) return;
    }
    public void T100_INSERT_EATACLNK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INSERT-EATACLNK--BEGIN");
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        IBS.WRITE(SCCGWA, EARACLNK, EATACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void B090_CHECK_DATA_FOR_INSERT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSBIND.CARD_NO);
        CEP.TRC(SCCGWA, EACSBIND.IO_FLG);
        CEP.TRC(SCCGWA, EACSBIND.CON_BNK);
        CEP.TRC(SCCGWA, EACSBIND.CON_NME);
        CEP.TRC(SCCGWA, EACSBIND.CON_AC);
        CEP.TRC(SCCGWA, EACSBIND.AC_NM);
        CEP.TRC(SCCGWA, EACSBIND.ID_TYPE);
        CEP.TRC(SCCGWA, EACSBIND.ID_NO);
        if (EACSBIND.FUNC == 'B') {
            if (EACSBIND.PSW.trim().length() > 0) {
                IBS.init(SCCGWA, DCCPCDCK);
                DCCPCDCK.FUNC_CODE = 'P';
                DCCPCDCK.CARD_NO = EACSBIND.CARD_NO;
                DCCPCDCK.CARD_PSW = EACSBIND.PSW;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            }
        }
        if (EACSBIND.FUNC == 'U') {
            if (EACSBIND.PSW.trim().length() > 0) {
                IBS.init(SCCGWA, DCCPCDCK);
                DCCPCDCK.FUNC_CODE = 'P';
                DCCPCDCK.CARD_NO = EACSBIND.CARD_NO;
                DCCPCDCK.CARD_PSW = EACSBIND.PSW;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            }
        }
        if (EACSBIND.CARD_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, EACMSG_ERROR_MSG.EA_SBIND_CARD_NOT_INPUT);
        }
        if (EACSBIND.CON_AC.trim().length() == 0) {
            CEP.ERRC(SCCGWA, EACMSG_ERROR_MSG.EA_CON_AC_NOT_MATCH);
        }
        if (EACSBIND.IO_FLG != 'I' 
            && EACSBIND.IO_FLG != 'O') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_BIND_IO_INCORRECT);
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            if (EACSBIND.IO_FLG != 'I') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CONAC_MUST_IN);
            }
        }
        if (EACSBIND.IO_FLG == 'O' 
            && (EACSBIND.CON_BNK.trim().length() == 0 
            || EACSBIND.CON_NME.trim().length() == 0)) {
            CEP.ERRC(SCCGWA, EACMSG_ERROR_MSG.EA_BND_BNK_NM_INPUT);
        }
        if (EACSBIND.FUNC == 'B' 
            && (EACSBIND.AC_NM.trim().length() == 0 
            || EACSBIND.ID_TYPE.trim().length() == 0 
            || EACSBIND.ID_NO.trim().length() == 0)) {
            CEP.ERRC(SCCGWA, EACMSG_ERROR_MSG.EA_BND_THREE_NOT_FULL);
        }
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = EACSBIND.CARD_NO;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.AC_TYP);
        WS_BIND_CARD_STS = DCCUCINF.CARD_STS;
        CEP.TRC(SCCGWA, DCCUCINF.PROD_CD);
        if ((DCCUCINF.AC_TYP == '2' 
            || DCCUCINF.AC_TYP == '3') 
            && DCCUCINF.CARD_STS != 'N' 
            && DCCUCINF.FACE_FLG != 'N') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_BIND_AC_NOT_ACT);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW.substring(0, 1));
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1));
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_HAS_LOST);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BCARD_NORMAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EACSBIND.IO_FLG == 'I') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = EACSBIND.CON_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
                IBS.init(SCCGWA, DCCUCINF);
                DCCUCINF.CARD_NO = EACSBIND.CON_AC;
                CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
                S000_CALL_DCZUCINF();
                if (pgmRtn) return;
                if (DCCUCINF.AC_TYP != '1') {
                    CEP.ERRC(SCCGWA, EACMSG_ERROR_MSG.EA_BIND_AC_NOT_I);
                }
                if (DCCUCINF.CARD_LNK_TYP == '2') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BIND_CARD_NO_SUBCARD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, DCCUCINF.CRD_LST_SW_FLG);
                if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
                JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
                if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE);
                }
                if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
                JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
                if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_HAS_LOST);
                }
                if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
                JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
                if (DCCUCINF.CARD_STSW.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_ALREADY_FROZEN);
                }
                IBS.init(SCCGWA, DDCIQBAL);
                DDCIQBAL.DATA.AC = EACSBIND.CON_AC;
                DDCIQBAL.DATA.CCY = "156";
                S000_CALL_DDZIQBAL();
                if (pgmRtn) return;
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 10 - 1));
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                if (DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                    || DDCIQBAL.DATA.CCY_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_HOLD);
                }
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                if (DDCIQBAL.DATA.CCY_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                    || DDCIQBAL.DATA.CCY_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_FORBID);
                }
            } else {
                IBS.init(SCCGWA, DDCIMMST);
                DDCIMMST.TX_TYPE = 'I';
                DDCIMMST.DATA.KEY.AC_NO = EACSBIND.CON_AC;
                S000_CALL_DDZIMMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_TYPE);
                if (DDCIMMST.DATA.AC_TYPE == 'B') {
                    CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_NOT_SETTLE_AC);
                }
                CEP.TRC(SCCGWA, DCCUCINF.CRD_LST_SW_FLG);
                if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
                JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
                if (DDCIMMST.DATA.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.M_PSBK_UNWRITE_LOST);
                }
                if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
                JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
                if (DDCIMMST.DATA.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.M_PSBK_WRITE_LOST);
                }
                IBS.init(SCCGWA, DDCIQBAL);
                DDCIQBAL.DATA.AC = EACSBIND.CON_AC;
                DDCIQBAL.DATA.CCY = "156";
                S000_CALL_DDZIQBAL();
                if (pgmRtn) return;
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 10 - 1));
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                if (DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                    || DDCIQBAL.DATA.CCY_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR);
                }
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                if (DDCIQBAL.DATA.CCY_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                    || DDCIQBAL.DATA.CCY_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_JSZM_AC_FBID);
                }
            }
            WS_CON_AC_CINO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        }
        B095_CHECK_CI_LIST();
        if (pgmRtn) return;
        B096_CHECK_II_LIST();
        if (pgmRtn) return;
        CEP.ERR(SCCGWA);
    }
    public void B095_CHECK_CI_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSBIND.CON_AC);
        IBS.init(SCCGWA, CICCKLS);
        CICCKLS.DATA.AGR_NO = EACSBIND.CON_AC;
        CICCKLS.DATA.AP_TYPE = SCCGWA.COMM_AREA.TR_MMO;
        CICCKLS.DATA.EXP_MMO = SCCGWA.COMM_AREA.TR_MMO;
        S000_CALL_CIZCKLS();
        if (pgmRtn) return;
    }
    public void B096_CHECK_II_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSBIND.CARD_NO);
        IBS.init(SCCGWA, CICCKLS);
        CICCKLS.DATA.AGR_NO = EACSBIND.CARD_NO;
        CICCKLS.DATA.AP_TYPE = SCCGWA.COMM_AREA.TR_MMO;
        CICCKLS.DATA.EXP_MMO = SCCGWA.COMM_AREA.TR_MMO;
        S000_CALL_CIZCKLS();
        if (pgmRtn) return;
    }
    public void R000_READ_FOR_SBIND_COUNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSBIND.CARD_NO);
        CEP.TRC(SCCGWA, EACSBIND.CON_AC);
        CEP.TRC(SCCGWA, EACSBIND.CON_BNK);
        EARACLNK.KEY.CARD_NO = EACSBIND.CARD_NO;
        EARACLNK.KEY.CON_AC = EACSBIND.CON_AC;
        EARACLNK.KEY.CON_BNK = EACSBIND.CON_BNK;
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        EATACLNK_RD.set = "WS-CNT=COUNT(*)";
        EATACLNK_RD.where = "CARD_NO = :EARACLNK.KEY.CARD_NO "
            + "AND CON_AC = :EARACLNK.KEY.CON_AC "
            + "AND CON_BNK = :EARACLNK.KEY.CON_BNK";
        IBS.GROUP(SCCGWA, EARACLNK, this, EATACLNK_RD);
        CEP.TRC(SCCGWA, WS_CNT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_RECORD_NOTFND);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R100_READ_FOR_SBIND_COUNT() throws IOException,SQLException,Exception {
        if (EACSBIND.FUNC == 'C') {
            if (EACSBIND.PSW.trim().length() > 0) {
                IBS.init(SCCGWA, DCCPCDCK);
                DCCPCDCK.FUNC_CODE = 'P';
                DCCPCDCK.CARD_NO = EACSBIND.CARD_NO;
                DCCPCDCK.CARD_PSW = EACSBIND.PSW;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, EACSBIND.CARD_NO);
        EARACLNK.KEY.CARD_NO = EACSBIND.CARD_NO;
        WS_CON_STS = 'C';
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        EATACLNK_RD.set = "WS-CNT=COUNT(*)";
        EATACLNK_RD.where = "CARD_NO = :EARACLNK.KEY.CARD_NO "
            + "AND CON_STS < > :WS_CON_STS";
        IBS.GROUP(SCCGWA, EARACLNK, this, EATACLNK_RD);
        CEP.TRC(SCCGWA, WS_CNT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_RECORD_NOTFND);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B110_CHECK_SBIND_CARD_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = EACSBIND.CARD_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        if ((!EACSBIND.AC_NM.equalsIgnoreCase(CICACCU.DATA.CI_CNM))) {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_AC_NM_NOT_MATCH);
        }
        if ((!CICACCU.DATA.ID_TYPE.equalsIgnoreCase(EACSBIND.ID_TYPE))) {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_ID_TYPE_NOT_MATCH);
        }
        if ((!CICACCU.DATA.ID_NO.equalsIgnoreCase(EACSBIND.ID_NO))) {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_ID_NO_NOT_MATCH);
        }
        WS_SBIND_CARD_CINO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, EACSBIND.IO_FLG);
        CEP.TRC(SCCGWA, WS_SBIND_CARD_CINO);
        CEP.TRC(SCCGWA, WS_CON_AC_CINO);
        if (EACSBIND.IO_FLG == 'I') {
            if (!WS_SBIND_CARD_CINO.equalsIgnoreCase(WS_CON_AC_CINO)) {
                CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_BND_CI_NO_NOT_SAME);
            }
        }
    }
    public void B080_CHECK_DATA_FOR_DELETE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSBIND.CARD_NO);
        CEP.TRC(SCCGWA, EACSBIND.IO_FLG);
        CEP.TRC(SCCGWA, EACSBIND.CON_BNK);
        CEP.TRC(SCCGWA, EACSBIND.CON_NME);
        CEP.TRC(SCCGWA, EACSBIND.CON_AC);
        CEP.TRC(SCCGWA, EACSBIND.AC_NM);
        CEP.TRC(SCCGWA, EACSBIND.ID_TYPE);
        CEP.TRC(SCCGWA, EACSBIND.ID_NO);
        if (EACSBIND.CARD_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, EACMSG_ERROR_MSG.EA_SBIND_CARD_NOT_INPUT);
        }
        if (EACSBIND.CON_AC.trim().length() == 0) {
            CEP.ERRC(SCCGWA, EACMSG_ERROR_MSG.EA_CON_AC_NOT_MATCH);
        }
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = EACSBIND.CARD_NO;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW.substring(0, 1));
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1));
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_HAS_LOST);
        }
        CEP.ERR(SCCGWA);
    }
    public void R000_OUTPUT_DATA_FOR_EATACLNK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EACO590);
        EACO590.CARD_NO = EARACLNK.KEY.CARD_NO;
        EACO590.AC_ARRAY[1-1].CON_AC = EARACLNK.KEY.CON_AC;
        EACO590.AC_ARRAY[1-1].AC_NM = EARACLNK.AC_NAME;
        EACO590.AC_ARRAY[1-1].CON_BNK = EARACLNK.KEY.CON_BNK;
        EACO590.AC_ARRAY[1-1].CON_NME = EARACLNK.CON_NME;
        EACO590.AC_ARRAY[1-1].IO_FLG = EARACLNK.IO_FLG;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "EA590";
        SCCFMT.DATA_PTR = EACO590;
        SCCFMT.DATA_LEN = 2299;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_REWRITE_SBIND_INF() throws IOException,SQLException,Exception {
        EARACLNK.KEY.CARD_NO = EACSBIND.CARD_NO;
        EARACLNK.IO_FLG = EACSBIND.IO_FLG;
        EARACLNK.KEY.CON_BNK = EACSBIND.CON_BNK;
        EARACLNK.CON_NME = EACSBIND.CON_NME;
        EARACLNK.KEY.CON_AC = EACSBIND.CON_AC;
        EARACLNK.AC_NAME = EACSBIND.AC_NM;
        EARACLNK.CON_STS = EACSBIND.FUNC;
        EARACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T300_UPDATE_EATACLNK();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_FOR_EATACLNK();
        if (pgmRtn) return;
    }
    public void B130_REPLACE_SBIND_INF() throws IOException,SQLException,Exception {
        WS_BIND_FLG = 'Y';
        R000_READ_SBIND_INF();
        if (pgmRtn) return;
        if (WS_EATACLNK_FLG == 'Y') {
            EARACLNK.CON_STS = 'C';
            EARACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T300_UPDATE_EATACLNK();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "CANCEL OLD BIND:");
            CEP.TRC(SCCGWA, EARACLNK.KEY.CON_AC);
            CEP.TRC(SCCGWA, "NEW I BIND     :");
            WS_BIND_FLG = 'N';
            R000_READ_SBIND_INF();
            if (pgmRtn) return;
            if (WS_EATACLNK_FLG == 'Y') {
                B030_REWRITE_SBIND_INF();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, EARACLNK);
                EARACLNK.KEY.CARD_NO = EACSBIND.CARD_NO;
                EARACLNK.IO_FLG = EACSBIND.IO_FLG;
                EARACLNK.KEY.CON_BNK = EACSBIND.CON_BNK;
                EARACLNK.CON_NME = EACSBIND.CON_NME;
                EARACLNK.KEY.CON_AC = EACSBIND.CON_AC;
                EARACLNK.AC_NAME = EACSBIND.AC_NM;
                EARACLNK.CON_STS = EACSBIND.FUNC;
                EARACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                EARACLNK.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                EARACLNK.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                EARACLNK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                T100_INSERT_EATACLNK();
                if (pgmRtn) return;
                R000_OUTPUT_DATA_FOR_EATACLNK();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_READUPDATE_SBIND_INF_C() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSBIND.CARD_NO);
        CEP.TRC(SCCGWA, EACSBIND.CON_AC);
        CEP.TRC(SCCGWA, EACSBIND.CON_BNK);
        EARACLNK.KEY.CARD_NO = EACSBIND.CARD_NO;
        WS_CARD_NO = EACSBIND.CARD_NO;
        EARACLNK.KEY.CON_AC = EACSBIND.CON_AC;
        WS_CON_NO = EACSBIND.CON_AC;
        WS_CON_STS = 'C';
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        EATACLNK_RD.where = "CARD_NO = :WS_CARD_NO "
            + "AND CON_AC = :WS_CON_NO "
            + "AND CON_STS < > :WS_CON_STS";
        EATACLNK_RD.upd = true;
        IBS.READ(SCCGWA, EARACLNK, this, EATACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_RECORD_NOTFND);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_READUPDATE_SBIND_INF_U() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSBIND.CARD_NO);
        CEP.TRC(SCCGWA, EACSBIND.CON_AC);
        CEP.TRC(SCCGWA, EACSBIND.CON_BNK);
        EARACLNK.KEY.CARD_NO = EACSBIND.CARD_NO;
        WS_CARD_NO = EACSBIND.CARD_NO;
        WS_CON_STS = 'C';
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        EATACLNK_RD.where = "CARD_NO = :WS_CARD_NO "
            + "AND CON_STS < > :WS_CON_STS";
        EATACLNK_RD.upd = true;
        IBS.READ(SCCGWA, EARACLNK, this, EATACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, EARACLNK.KEY.CON_AC);
            if (EACSBIND.CON_AC.equalsIgnoreCase(EARACLNK.KEY.CON_AC)) {
                CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_BIND_AC_NOT_DIFF);
            }
            WS_BEFORE_CON_AC = EARACLNK.KEY.CON_AC;
            WS_BEFORE_CON_BANK = EARACLNK.KEY.CON_BNK;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_RECORD_NOTFND);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_READ_SBIND_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BIND_FLG);
        CEP.TRC(SCCGWA, EACSBIND.CARD_NO);
        CEP.TRC(SCCGWA, EACSBIND.CON_AC);
        CEP.TRC(SCCGWA, EACSBIND.CON_BNK);
        CEP.TRC(SCCGWA, WS_BEFORE_CON_AC);
        CEP.TRC(SCCGWA, WS_BEFORE_CON_BANK);
        WS_EATACLNK_FLG = 'N';
        EARACLNK.KEY.CARD_NO = EACSBIND.CARD_NO;
        EARACLNK.KEY.CON_AC = EACSBIND.CON_AC;
        EARACLNK.KEY.CON_BNK = EACSBIND.CON_BNK;
        if (WS_BIND_FLG == 'Y') {
            EARACLNK.KEY.CON_AC = WS_BEFORE_CON_AC;
            EARACLNK.KEY.CON_BNK = WS_BEFORE_CON_BANK;
        }
        CEP.TRC(SCCGWA, EARACLNK.KEY.CON_AC);
        CEP.TRC(SCCGWA, EARACLNK.KEY.CON_BNK);
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        EATACLNK_RD.upd = true;
        IBS.READ(SCCGWA, EARACLNK, EATACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EATACLNK_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_EATACLNK_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T300_UPDATE_EATACLNK() throws IOException,SQLException,Exception {
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        IBS.REWRITE(SCCGWA, EARACLNK, EATACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T600_REPLACE_EATACLNK() throws IOException,SQLException,Exception {
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        IBS.REWRITE(SCCGWA, EARACLNK, EATACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "REPLACE :");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void B300_REC_HISTRY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = " ";
        BPCPNHIS.INFO.AC = EACSBIND.CARD_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        if (EACSBIND.FUNC == 'U') {
            BPCPNHIS.INFO.TX_RMK = "CHANGE  BIND";
            BPCPNHIS.INFO.TX_TYP_CD = "PE09";
        } else if (EACSBIND.FUNC == 'B') {
            BPCPNHIS.INFO.TX_RMK = "ADD     BIND";
            BPCPNHIS.INFO.TX_TYP_CD = "PE07";
        } else if (EACSBIND.FUNC == 'C') {
            BPCPNHIS.INFO.TX_RMK = "DELETE  BIND";
            BPCPNHIS.INFO.TX_TYP_CD = "PE08";
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = EACSBIND.CARD_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.NEW_DAT_PT = EACSBIND;
        BPCPNHIS.INFO.FMT_ID = "EAZSBIND";
        BPCPNHIS.INFO.FMT_ID_LEN = 686;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST ", DDCIMMST, true);
        CEP.TRC(SCCGWA, DDCIMMST.RC.RC_CODE);
        if (DDCIMMST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCIMMST.RC);
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICACCU.RC);
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZSCGET() throws IOException,SQLException,Exception {
        DCZSCGET DCZSCGET = new DCZSCGET();
        DCZSCGET.MP(SCCGWA, DCCSCGET);
        CEP.TRC(SCCGWA, DCCSCGET.CARD_STS);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_CIZCKLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-LST", CICCKLS);
        if (CICCKLS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCKLS.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST);
        }
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
