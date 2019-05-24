package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSACNO {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DCZSACNO_WS_OUT_INFO WS_OUT_INFO;
    DBParm DCTCDDAT_RD;
    DBParm DCTDIRAC_RD;
    brParm DCTDIRAC_BR = new brParm();
    boolean pgmRtn = false;
    short Q_MAX_CNT = 1000;
    short PAGE_ROW = 25;
    String OUTPUT_FMT = "DC160";
    int MAX_COL = 99;
    int MAX_ROW = 10;
    int COL_CNT = 7;
    String HIS_REMARK = "CARD PRODUCT TRANS LIMIT INFO. MAINTAIN";
    String HIS_COPYBOOK = "DCRDIRAC";
    String TBL_CDDAT = "DCTCDDAT";
    String TBL_DIRAC = "DCTDIRAC";
    DCZSACNO_WS_VARIABLES WS_VARIABLES = new DCZSACNO_WS_VARIABLES();
    DCZSACNO_WS_OUT_RECODE WS_OUT_RECODE = new DCZSACNO_WS_OUT_RECODE();
    DCZSACNO_WS_COND_FLG WS_COND_FLG = new DCZSACNO_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRDIRAC DCRDIRAC = new DCRDIRAC();
    DCRDIRAC DCRDIRAC = new DCRDIRAC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    CICSSCH CICSSCH = new CICSSCH();
    CICACCU CICACCU = new CICACCU();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    DCCUPRCD_WS_DB_VARS WS_DB_VARS = new DCCUPRCD_WS_DB_VARS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCSACNO DCCSACNO;
    public void MP(SCCGWA SCCGWA, DCCSACNO DCCSACNO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSACNO = DCCSACNO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSACNO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, DCRDIRAC);
        IBS.init(SCCGWA, DCRDIRAC);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, DCCSACNO);
        CEP.TRC(SCCGWA, DCCSACNO.FUNC);
        CEP.TRC(SCCGWA, DCCSACNO.CARD_NO);
        CEP.TRC(SCCGWA, DCCSACNO.BANK_FLG);
        CEP.TRC(SCCGWA, DCCSACNO.AC_NO);
        CEP.TRC(SCCGWA, DCCSACNO.CI_NO);
        CEP.TRC(SCCGWA, DCCSACNO.CI_CNAME);
        CEP.TRC(SCCGWA, DCCSACNO.BR);
        CEP.TRC(SCCGWA, DCCSACNO.OTH_BANK_NO);
        CEP.TRC(SCCGWA, DCCSACNO.BANK_FLG_N);
        CEP.TRC(SCCGWA, DCCSACNO.AC_NO_N);
        CEP.TRC(SCCGWA, DCCSACNO.CI_CNAME_N);
        CEP.TRC(SCCGWA, DCCSACNO.BR_N);
        CEP.TRC(SCCGWA, DCCSACNO.PAGE_ROW);
        CEP.TRC(SCCGWA, DCCSACNO.PAGE_NUM);
        if (DCCSACNO.FUNC == 'Q') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSACNO.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B002_CHECK_PROCESS();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B091_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSACNO.FUNC == 'U') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B003_CHECK_PRIM_CARD();
            if (pgmRtn) return;
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            B060_CHECK_PROCESS();
            if (pgmRtn) return;
            B070_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B100_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSACNO.FUNC == 'D') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B003_CHECK_PRIM_CARD();
            if (pgmRtn) return;
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSACNO.FUNC == 'B') {
            B050_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCSACNO.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSACNO.BANK_FLG == ' ') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_BANK_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSACNO.AC_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSACNO.CI_CNAME.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CI_NAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSACNO.BANK_FLG == '1') {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = DCCSACNO.AC_NO;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            if ((WS_COND_FLG.AC_FLG == 'N' 
                || CICACCU.DATA.STS != '0') 
                && DCCSACNO.FUNC != 'D') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_AC_NOT_FOUND_OR_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                if (DCCSACNO.BR == ' ') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_BR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, CICACCU.DATA.OPN_BR);
                DCCSACNO.BR = CICACCU.DATA.OPN_BR;
            }
        } else if (DCCSACNO.BANK_FLG == '2') {
            if (DCCSACNO.OTH_BANK_NO.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_OTH_BR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSACNO.FUNC == 'U') {
            if (DCCSACNO.BANK_FLG_N == ' ') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_BANK_FLG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCSACNO.AC_NO_N.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_AC_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCSACNO.CI_CNAME_N.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CI_NAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCSACNO.BANK_FLG_N == '1') {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = DCCSACNO.AC_NO_N;
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                if (WS_COND_FLG.AC_FLG == 'N' 
                    || CICACCU.DATA.STS != '0') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_AC_NOT_FOUND_OR_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, CICACCU.DATA.OPN_BR);
                DCCSACNO.BR_N = CICACCU.DATA.OPN_BR;
            } else if (DCCSACNO.BANK_FLG_N == '2') {
                if (DCCSACNO.OTH_BANK_NO_N.trim().length() == 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_OTH_BR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B002_CHECK_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSACNO.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_BE_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW.substring(0, 1));
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1));
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_HAS_LOST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRCDDAT.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.DC_PRD_REC_NOTFND)) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_PROD_NOTFND);
        }
        if (DCCUPRCD.RC.RC_CODE == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA.VAL);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.ADSC_TYP);
        if (DCRPRDPR.DATA_TXT.ADSC_TYP == 'P') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_COMPANY_CARD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRDIRAC);
        DCRDIRAC.KEY.CARD_NO = DCCSACNO.CARD_NO;
        DCRDIRAC.KEY.BANK_FLG = DCCSACNO.BANK_FLG;
        DCRDIRAC.KEY.AC_NO = DCCSACNO.AC_NO;
        DCRDIRAC.KEY.OTH_BANK_NO = DCCSACNO.OTH_BANK_NO;
        DCRDIRAC.CI_CNAME = DCCSACNO.CI_CNAME;
        T000_READ_DCTDIRAC_F();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'Y' 
            && DCCSACNO.FUNC == 'A') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_AC_RECORD_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B003_CHECK_PRIM_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSACNO.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_BE_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B004_CHECK_DA_ACCOUNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDIRAC);
        DCRDIRAC.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        DCRDIRAC.KEY.BANK_FLG = DCCSACNO.BANK_FLG;
        DCRDIRAC.KEY.AC_NO = DCCSACNO.AC_NO;
        DCRDIRAC.KEY.OTH_BANK_NO = DCCSACNO.OTH_BANK_NO;
        DCRDIRAC.CI_CNAME = DCCSACNO.CI_CNAME;
        T000_READ_DCTDIRAC_F();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'Y') {
            CEP.TRC(SCCGWA, "FIND --------");
            WS_VARIABLES.CNT += 1;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSACNO.CARD_NO);
        IBS.init(SCCGWA, DCRDIRAC);
        DCRDIRAC.KEY.CARD_NO = DCCSACNO.CARD_NO;
        DCRDIRAC.KEY.BANK_FLG = DCCSACNO.BANK_FLG;
        DCRDIRAC.KEY.AC_NO = DCCSACNO.AC_NO;
        DCRDIRAC.KEY.OTH_BANK_NO = DCCSACNO.OTH_BANK_NO;
        DCRDIRAC.CI_CNAME = DCCSACNO.CI_CNAME;
        T000_READ_DCTDIRAC_F();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDIRAC);
        DCRDIRAC.KEY.CARD_NO = DCCSACNO.CARD_NO;
        DCRDIRAC.KEY.BANK_FLG = DCCSACNO.BANK_FLG;
        DCRDIRAC.KEY.AC_NO = DCCSACNO.AC_NO;
        DCRDIRAC.KEY.OTH_BANK_NO = DCCSACNO.OTH_BANK_NO;
        DCRDIRAC.KEY.BR = DCCSACNO.BR;
        DCRDIRAC.CI_CNAME = DCCSACNO.CI_CNAME;
        DCRDIRAC.CI_NO = DCCSACNO.CI_NO;
        DCRDIRAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDIRAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDIRAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDIRAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDIRAC();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'D') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDIRAC);
        DCRDIRAC.KEY.CARD_NO = DCCSACNO.CARD_NO;
        DCRDIRAC.KEY.BANK_FLG = DCCSACNO.BANK_FLG;
        DCRDIRAC.KEY.AC_NO = DCCSACNO.AC_NO;
        DCRDIRAC.KEY.OTH_BANK_NO = DCCSACNO.OTH_BANK_NO;
        DCRDIRAC.CI_CNAME = DCCSACNO.CI_CNAME;
        T000_READ_DCTDIRAC_F();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READ_DCTDIRAC_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DCRDIRAC, DCRDIRAC);
        T000_DELETE_DCTDIRAC();
        if (pgmRtn) return;
    }
    public void B050_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        if (DCCSACNO.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSACNO.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRDIRAC);
        DCRDIRAC.KEY.CARD_NO = DCCSACNO.CARD_NO;
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            B051_OUTPUT_TITLE();
            if (pgmRtn) return;
            T000_STARTBR_DCTDIRAC();
            if (pgmRtn) return;
            T000_READNEXT_DCTDIRAC();
            if (pgmRtn) return;
            while (WS_COND_FLG.TBL_FLAG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                B052_OUTPUT_DETAIL();
                if (pgmRtn) return;
                T000_READNEXT_DCTDIRAC();
                if (pgmRtn) return;
            }
        } else {
            B053_OUTPUT_HEAD();
            if (pgmRtn) return;
            T000_STARTBR_DCTDIRAC_W();
            if (pgmRtn) return;
            T000_READNEXT_DCTDIRAC_W();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, WS_COND_FLG.TBL_FLAG);
            if (WS_COND_FLG.TBL_FLAG != 'N') {
                WS_VARIABLES.WS_DATA.IDX = 0;
                WS_VARIABLES.WS_DATA.TOTAL_NUM = 0;
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.IDX);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.PAGE_ROW);
                while (WS_VARIABLES.WS_DATA.IDX != WS_VARIABLES.WS_DATA.PAGE_ROW 
                    && WS_COND_FLG.TBL_FLAG != 'N') {
                    B054_WRITE_OUTPUT();
                    if (pgmRtn) return;
                    WS_VARIABLES.WS_DATA.NEXT_START_NUM += 1;
                    WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATA.NEXT_START_NUM;
                    T000_READNEXT_DCTDIRAC_W();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "-------READ NEXT----");
                }
                CEP.TRC(SCCGWA, WS_COND_FLG.TBL_FLAG);
                if (WS_COND_FLG.TBL_FLAG == 'N') {
                    CEP.TRC(SCCGWA, "--------------T--------");
                    WS_VARIABLES.WS_DATA.TOTAL_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
                    WS_VARIABLES.WS_DATA.BAL_CNT = WS_VARIABLES.WS_DATA.IDX;
                    WS_VARIABLES.WS_DATA.TOTAL_NUM = ( WS_VARIABLES.WS_DATA.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATA.PAGE_ROW + WS_VARIABLES.WS_DATA.BAL_CNT;
                    WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
                    WS_VARIABLES.WS_DATA.PAGE_ROW = (short) WS_VARIABLES.WS_DATA.BAL_CNT;
                    WS_OUT_INFO = new DCZSACNO_WS_OUT_INFO();
                    WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
                } else {
                    CEP.TRC(SCCGWA, "--------------G--------");
                    DCRDIRAC.KEY.CARD_NO = DCCSACNO.CARD_NO;
                    R000_GROUP_ALL();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.TOTAL_NUM);
                    CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.PAGE_ROW);
                    WS_VARIABLES.WS_DATA.BAL_CNT = WS_VARIABLES.WS_DATA.TOTAL_NUM % WS_VARIABLES.WS_DATA.PAGE_ROW;
                    WS_VARIABLES.WS_DATA.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_DATA.TOTAL_NUM - WS_VARIABLES.WS_DATA.BAL_CNT) / WS_VARIABLES.WS_DATA.PAGE_ROW);
                    if (WS_VARIABLES.WS_DATA.BAL_CNT != 0) {
                        WS_VARIABLES.WS_DATA.TOTAL_PAGE += 1;
                    }
                }
            } else {
                WS_VARIABLES.WS_DATA.TOTAL_PAGE = 1;
                WS_VARIABLES.WS_DATA.TOTAL_NUM = 0;
                WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATA.PAGE_ROW = 0;
                WS_OUT_INFO = new DCZSACNO_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        T000_ENDBR_DCTDIRAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, "--------------ENDBR---DCTDIRAC---------");
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATA.TOTAL_PAGE;
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATA.TOTAL_NUM;
            WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATA.LAST_PAGE;
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.WS_DATA.PAGE_ROW;
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
            B055_OUTPUT_WRITE();
            if (pgmRtn) return;
        }
    }
    public void B051_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = (short) MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B052_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES.WS_OUT_VAL);
        WS_VARIABLES.WS_OUT_VAL.CARD_NO = DCRDIRAC.KEY.CARD_NO;
        WS_VARIABLES.WS_OUT_VAL.BANK_FLG = DCRDIRAC.KEY.BANK_FLG;
        WS_VARIABLES.WS_OUT_VAL.AC_NO = DCRDIRAC.KEY.AC_NO;
        WS_VARIABLES.WS_OUT_VAL.CI_NO = DCRDIRAC.CI_NO;
        WS_VARIABLES.WS_OUT_VAL.CI_NM = DCRDIRAC.CI_CNAME;
        WS_VARIABLES.WS_OUT_VAL.BR = DCRDIRAC.KEY.BR;
        WS_VARIABLES.WS_OUT_VAL.OTH_BANK_NO = DCRDIRAC.KEY.OTH_BANK_NO;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUT_VAL);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUT_VAL.CARD_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUT_VAL.BANK_FLG);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUT_VAL.AC_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUT_VAL.CI_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUT_VAL.CI_NM);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUT_VAL.BR);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUT_VAL.OTH_BANK_NO);
        CEP.TRC(SCCGWA, "--------OUTPUT---------");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_OUT_VAL);
        SCCMPAG.DATA_LEN = 337;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B053_OUTPUT_HEAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (DCCSACNO.PAGE_NUM == 0) {
            WS_VARIABLES.WS_DATA.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_DATA.CURR_PAGE = (short) DCCSACNO.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
        WS_VARIABLES.WS_DATA.LAST_PAGE = 'N';
        if (DCCSACNO.PAGE_ROW == 0) {
            WS_VARIABLES.WS_DATA.PAGE_ROW = PAGE_ROW;
            WS_OUT_INFO = new DCZSACNO_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            CEP.TRC(SCCGWA, "99999");
        } else {
            if (DCCSACNO.PAGE_ROW > PAGE_ROW) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.WS_DATA.PAGE_ROW = (short) DCCSACNO.PAGE_ROW;
                WS_OUT_INFO = new DCZSACNO_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.PAGE_ROW);
        CEP.TRC(SCCGWA, PAGE_ROW);
        WS_VARIABLES.WS_DATA.NEXT_START_NUM = ( ( WS_VARIABLES.WS_DATA.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATA.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATA.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
    }
    public void B055_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "DC160";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 8444;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B054_WRITE_OUTPUT() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_DATA.IDX += 1;
        IBS.init(SCCGWA, WS_OUT_INFO);
        WS_OUT_INFO.O_CARD_NO = DCRDIRAC.KEY.CARD_NO;
        WS_OUT_INFO.O_BANK_FLG = DCRDIRAC.KEY.BANK_FLG;
        WS_OUT_INFO.O_AC_NO = DCRDIRAC.KEY.AC_NO;
        WS_OUT_INFO.O_CI_NO = DCRDIRAC.CI_NO;
        WS_OUT_INFO.O_CI_NM = DCRDIRAC.CI_CNAME;
        WS_OUT_INFO.O_BR = DCRDIRAC.KEY.BR;
        WS_OUT_INFO.O_OTH_BANK_NO = DCRDIRAC.KEY.OTH_BANK_NO;
        CEP.TRC(SCCGWA, "--------OUTPUT---------");
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATA.IDX-1));
    }
    public void B060_CHECK_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRCDDAT.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.ADSC_TYP);
        if (DCRPRDPR.DATA_TXT.ADSC_TYP == 'P') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_COMPANY_CARD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRDIRAC);
        DCRDIRAC.KEY.CARD_NO = DCCSACNO.CARD_NO;
        DCRDIRAC.KEY.BANK_FLG = DCCSACNO.BANK_FLG_N;
        DCRDIRAC.KEY.AC_NO = DCCSACNO.AC_NO_N;
        DCRDIRAC.KEY.OTH_BANK_NO = DCCSACNO.OTH_BANK_NO_N;
        DCRDIRAC.KEY.BR = DCCSACNO.BR_N;
        DCRDIRAC.CI_CNAME = DCCSACNO.CI_CNAME_N;
        T000_READ_DCTDIRAC();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'Y') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_AC_RECORD_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDIRAC);
        DCRDIRAC.KEY.CARD_NO = DCCSACNO.CARD_NO;
        DCRDIRAC.KEY.BANK_FLG = DCCSACNO.BANK_FLG_N;
        DCRDIRAC.KEY.AC_NO = DCCSACNO.AC_NO_N;
        DCRDIRAC.KEY.OTH_BANK_NO = DCCSACNO.OTH_BANK_NO_N;
        DCRDIRAC.KEY.BR = DCCSACNO.BR_N;
        DCRDIRAC.CI_CNAME = DCCSACNO.CI_CNAME_N;
        DCRDIRAC.CI_NO = DCCSACNO.CI_NO;
        DCRDIRAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDIRAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDIRAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDIRAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDIRAC();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'D') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSACNO.CARD_NO;
        BPCPNHIS.INFO.AC = DCCSACNO.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.TX_RMK = HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 2122;
        if (DCCSACNO.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP_CD = "P208";
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.NEW_DAT_PT = DCRDIRAC;
        }
        if (DCCSACNO.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP_CD = "P209";
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRDIRAC;
        }
        if (DCCSACNO.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRDIRAC;
            BPCPNHIS.INFO.NEW_DAT_PT = DCRDIRAC;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_VARIABLES.WS_OUT_VAL);
        WS_VARIABLES.WS_OUT_VAL.CARD_NO = DCCSACNO.CARD_NO;
        WS_VARIABLES.WS_OUT_VAL.BANK_FLG = DCCSACNO.BANK_FLG;
        WS_VARIABLES.WS_OUT_VAL.AC_NO = DCCSACNO.AC_NO;
        WS_VARIABLES.WS_OUT_VAL.CI_NO = DCCSACNO.CI_NO;
        WS_VARIABLES.WS_OUT_VAL.CI_NM = DCCSACNO.CI_CNAME;
        WS_VARIABLES.WS_OUT_VAL.BR = DCCSACNO.BR;
        WS_VARIABLES.WS_OUT_VAL.OTH_BANK_NO = DCCSACNO.OTH_BANK_NO;
        CEP.TRC(SCCGWA, "--------OUTPUT---------");
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUT_VAL);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_VARIABLES.WS_OUT_VAL;
        SCCFMT.DATA_LEN = 337;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B100_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_VARIABLES.WS_OUT_VAL);
        WS_VARIABLES.WS_OUT_VAL.CARD_NO = DCCSACNO.CARD_NO;
        WS_VARIABLES.WS_OUT_VAL.BANK_FLG = DCCSACNO.BANK_FLG_N;
        WS_VARIABLES.WS_OUT_VAL.AC_NO = DCCSACNO.AC_NO_N;
        WS_VARIABLES.WS_OUT_VAL.CI_NO = DCCSACNO.CI_NO;
        WS_VARIABLES.WS_OUT_VAL.CI_NM = DCCSACNO.CI_CNAME_N;
        WS_VARIABLES.WS_OUT_VAL.BR = DCCSACNO.BR_N;
        WS_VARIABLES.WS_OUT_VAL.OTH_BANK_NO = DCCSACNO.OTH_BANK_NO_N;
        CEP.TRC(SCCGWA, "--------OUTPUT2--------");
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUT_VAL);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_VARIABLES.WS_OUT_VAL;
        SCCFMT.DATA_LEN = 337;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B091_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_VARIABLES.WS_OUT_VAL);
        WS_VARIABLES.WS_OUT_VAL.CARD_NO = DCCSACNO.CARD_NO;
        WS_VARIABLES.WS_OUT_VAL.BANK_FLG = DCCSACNO.BANK_FLG;
        WS_VARIABLES.WS_OUT_VAL.AC_NO = DCCSACNO.AC_NO;
        WS_VARIABLES.WS_OUT_VAL.CI_NO = DCCSACNO.CI_NO;
        WS_VARIABLES.WS_OUT_VAL.CI_NM = DCCSACNO.CI_CNAME;
        WS_VARIABLES.WS_OUT_VAL.BR = DCCSACNO.BR;
        WS_VARIABLES.WS_OUT_VAL.OTH_BANK_NO = DCCSACNO.OTH_BANK_NO;
        CEP.TRC(SCCGWA, "--------OUTPUT---------");
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUT_VAL);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_VARIABLES.WS_OUT_VAL;
        SCCFMT.DATA_LEN = 337;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-CI", CICSSCH);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_COND_FLG.AC_FLG = 'N';
        } else {
            WS_COND_FLG.AC_FLG = 'Y';
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTDIRAC() throws IOException,SQLException,Exception {
        DCTDIRAC_RD = new DBParm();
        DCTDIRAC_RD.TableName = "DCTDIRAC";
        IBS.READ(SCCGWA, DCRDIRAC, DCTDIRAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_DIRAC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTDIRAC_F() throws IOException,SQLException,Exception {
        DCTDIRAC_RD = new DBParm();
        DCTDIRAC_RD.TableName = "DCTDIRAC";
        DCTDIRAC_RD.col = "CARD_NO, BANK_FLG, AC_NO, OTH_BANK_NO, BR, CI_CNAME, CI_NO, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTDIRAC_RD.where = "CARD_NO = :DCRDIRAC.KEY.CARD_NO "
            + "AND BANK_FLG = :DCRDIRAC.KEY.BANK_FLG "
            + "AND AC_NO = :DCRDIRAC.KEY.AC_NO "
            + "AND OTH_BANK_NO = :DCRDIRAC.KEY.OTH_BANK_NO";
        DCTDIRAC_RD.fst = true;
        IBS.READ(SCCGWA, DCRDIRAC, this, DCTDIRAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_DIRAC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTDIRAC() throws IOException,SQLException,Exception {
        DCTDIRAC_RD = new DBParm();
        DCTDIRAC_RD.TableName = "DCTDIRAC";
        DCTDIRAC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRDIRAC, DCTDIRAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_COND_FLG.TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_DIRAC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTDIRAC_UPD() throws IOException,SQLException,Exception {
        DCTDIRAC_RD = new DBParm();
        DCTDIRAC_RD.TableName = "DCTDIRAC";
        DCTDIRAC_RD.upd = true;
        IBS.READ(SCCGWA, DCRDIRAC, DCTDIRAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_DIRAC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTDIRAC() throws IOException,SQLException,Exception {
        DCTDIRAC_RD = new DBParm();
        DCTDIRAC_RD.TableName = "DCTDIRAC";
        IBS.REWRITE(SCCGWA, DCRDIRAC, DCTDIRAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_COND_FLG.TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_DIRAC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_DCTDIRAC() throws IOException,SQLException,Exception {
        DCTDIRAC_RD = new DBParm();
        DCTDIRAC_RD.TableName = "DCTDIRAC";
        IBS.DELETE(SCCGWA, DCRDIRAC, DCTDIRAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_DIRAC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTDIRAC() throws IOException,SQLException,Exception {
        DCTDIRAC_BR.rp = new DBParm();
        DCTDIRAC_BR.rp.TableName = "DCTDIRAC";
        DCTDIRAC_BR.rp.where = "CARD_NO = :DCRDIRAC.KEY.CARD_NO";
        IBS.STARTBR(SCCGWA, DCRDIRAC, this, DCTDIRAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_DIRAC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTDIRAC_W() throws IOException,SQLException,Exception {
        DCTDIRAC_BR.rp = new DBParm();
        DCTDIRAC_BR.rp.TableName = "DCTDIRAC";
        DCTDIRAC_BR.rp.where = "CARD_NO = :DCRDIRAC.KEY.CARD_NO";
        IBS.STARTBR(SCCGWA, DCRDIRAC, this, DCTDIRAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_DIRAC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTDIRAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRDIRAC, this, DCTDIRAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_DIRAC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTDIRAC_W() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRDIRAC, this, DCTDIRAC_BR);
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else {
            WS_COND_FLG.TBL_FLAG = 'N';
        }
    }
    public void T000_ENDBR_DCTDIRAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTDIRAC_BR);
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        DCTDIRAC_RD = new DBParm();
        DCTDIRAC_RD.TableName = "DCTDIRAC";
        DCTDIRAC_RD.set = "WS-NUM=COUNT(*)";
        DCTDIRAC_RD.where = "CARD_NO = :DCRDIRAC.KEY.CARD_NO";
        IBS.GROUP(SCCGWA, DCRDIRAC, this, DCTDIRAC_RD);
        WS_VARIABLES.WS_DATA.TOTAL_NUM = WS_DB_VARS.NUM;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.TOTAL_NUM);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_PARM_NOTFND)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_PROD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
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
