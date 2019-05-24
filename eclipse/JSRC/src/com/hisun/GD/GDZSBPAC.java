package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.DC.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.LN.*;
import com.hisun.BP.*;
import com.hisun.IB.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSBPAC {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm GDTPLDR_RD;
    DBParm DDTCCY_RD;
    brParm GDTPLDR_BR = new brParm();
    DBParm GDTTRAN_RD;
    DBParm DDTMST_RD;
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    DBParm GDTSTAC_RD;
    boolean pgmRtn = false;
    String LOC_CCY = "156";
    String I_INQ_BAL = "DD-I-INQ-CCY-BAL";
    String OUTPUT_FMT = "GD140";
    String TD_CHQ_CODE = "TDOCS";
    String AI_ITM = "20190201";
    String TXREF_NO = "999999";
    GDZSBPAC_WS_VARIABLES WS_VARIABLES = new GDZSBPAC_WS_VARIABLES();
    GDZSBPAC_WS_DR_SECTION WS_DR_SECTION = new GDZSBPAC_WS_DR_SECTION();
    GDZSBPAC_WS_DR_USE_SEC WS_DR_USE_SEC = new GDZSBPAC_WS_DR_USE_SEC();
    GDZSBPAC_WS_OUT_DATA WS_OUT_DATA = new GDZSBPAC_WS_OUT_DATA();
    GDZSBPAC_WS_COND_FLG WS_COND_FLG = new GDZSBPAC_WS_COND_FLG();
    DDCMSG_ERROR_MSG ERROR_MSG = new DDCMSG_ERROR_MSG();
    GDCMSG_ERROR_MSG ERROR_MSG = new GDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    TDCACDRU TDCACDRU = new TDCACDRU();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCRMST DDCRMST = new DDCRMST();
    GDCOPAAC GDCOPAAC = new GDCOPAAC();
    CICACCU CICACCU = new CICACCU();
    CICQACRI CICQACRI = new CICQACRI();
    LNCSWLAA LNCSWLAA = new LNCSWLAA();
    LNCSWLAA LNCSWLAA = new LNCSWLAA();
    LNCIGECR LNCIGECR = new LNCIGECR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQIA AICPQIA = new AICPQIA();
    IBCQRAC IBCQRAC = new IBCQRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    DDCSCINM DDCSCINM = new DDCSCINM();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    TDCACE TDCACE = new TDCACE();
    CICQACAC CICQACAC = new CICQACAC();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    GDCRTRAN GDCRTRAN = new GDCRTRAN();
    GDRTRAN GDRTRAN = new GDRTRAN();
    GDCSRLSR GDCSRLSR = new GDCSRLSR();
    GDCSCLDD GDCSCLDD = new GDCSCLDD();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPORUP BPCPORUP = new BPCPORUP();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCOCLWD_WS_DB_VARS WS_DB_VARS = new BPCOCLWD_WS_DB_VARS();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    DDRCCY DDRCCY = new DDRCCY();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    DDRMST DDRMST = new DDRMST();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO DATA_INFO;
    GDCSBPAC GDCSBPAC;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGSCA_SC_AREA SC_AREA;
    public void MP(SCCGWA SCCGWA, GDCSBPAC GDCSBPAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSBPAC = GDCSBPAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSBPAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_DB_VARS);
        IBS.init(SCCGWA, WS_OUT_DATA);
        IBS.init(SCCGWA, WS_DR_SECTION);
        IBS.init(SCCGWA, WS_DR_USE_SEC);
        DATA_INFO = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CEP.TRC(SCCGWA, "1;");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.PRO_SUCESS_FLG = 'N';
        B010_PRE_CHECK();
        if (pgmRtn) return;
        if (WS_VARIABLES.PRO_SUCESS_FLG == 'N') {
            B020_PROCESS_DR_SECTION();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_VARIABLES.LACK_AMT);
            if (WS_VARIABLES.LACK_AMT > 0 
                && GDCSBPAC.MN_FLG == 'M') {
                WS_OUT_DATA.DK_AMT = WS_VARIABLES.LACK_AMT;
                B030_PROCESS_LACK_SECTION();
                if (pgmRtn) return;
            }
            if (GDCSBPAC.MN_FLG == 'N') {
                B030_2_REWRITE_TRAN_N();
                if (pgmRtn) return;
                if (WS_VARIABLES.LACK_AMT > 0) {
                    B111_CALL_LNCIGECR();
                    if (pgmRtn) return;
                    WS_OUT_DATA.CNTRT_NO = GDCSBPAC.CNTRT_NO;
                    B112_CALL_LNCSWLAA();
                    if (pgmRtn) return;
                    WS_OUT_DATA.DRW_AMT = GDCSBPAC.DRW_AMT;
                }
            }
            B040_PEOCESS_CR_SECTION();
            if (pgmRtn) return;
            if (GDCSBPAC.DR_SEC[1-1].TXALLO_FLG == '1' 
                && GDCSBPAC.MN_FLG == 'M') {
                B050_PROCESS_CZDQ();
                if (pgmRtn) return;
            }
        }
        B060_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_PRE_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRTRAN);
        if (GDCSBPAC.DR_SEC[1-1].TXCTA_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.GD_CTA_NO_M_INPUT);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, GDCSBPAC.REQ_DATE);
        if (GDCSBPAC.REQ_DATE == 0) {
            GDCSBPAC.REQ_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (GDCSBPAC.REQ_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_INPUT_DATE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDCSBPAC.TR_BR);
        IBS.init(SCCGWA, BPCPORUP);
        BPCPORUP.DATA_INFO.BR = GDCSBPAC.TR_BR;
        S000_CALL_BPZPORUP();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCPORUP.DATA_INFO.CALD_CD;
        BPCOCLWD.DATE1 = GDCSBPAC.REQ_DATE;
        BPCOCLWD.WDAYS = 1;
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
        if (BPCOCLWD.DATE2 > GDCSBPAC.REQ_DATE) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_ERR_HOL_ORG_EQL_N;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        GDRTRAN.KEY.DEAL_CD = GDCSBPAC.DR_SEC[1-1].TXCTA_NO;
        GDRTRAN.KEY.BSREF = GDCSBPAC.DR_SEC[1-1].TXREF_NO;
        GDRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_COND_FLG.TRAN_FLG = 'N';
        T000_READUP_GDTTRAN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TRAN_FLG = 'Y';
            WS_VARIABLES.LACK_AMT = GDRTRAN.ADV_AMT;
            WS_VARIABLES.TOT_DRAMT = GDRTRAN.ADV_AMT;
            WS_OUT_DATA.DK_AMT = GDRTRAN.ADV_AMT;
            if ((GDCSBPAC.MN_FLG == 'M' 
                || ((WS_VARIABLES.LACK_AMT == 0) 
                && GDCSBPAC.MN_FLG == 'N'))) {
                WS_VARIABLES.PRO_SUCESS_FLG = 'Y';
            }
        } else {
            WS_VARIABLES.LACK_AMT = GDCSBPAC.TXAMT;
            WS_VARIABLES.TOT_DRAMT = GDCSBPAC.TXAMT;
        }
    }
    public void B020_PROCESS_DR_SECTION() throws IOException,SQLException,Exception {
        WS_VARIABLES.CNT_END_FLG = 'N';
        for (WS_VARIABLES.CNT = 1; WS_VARIABLES.LACK_AMT != 0 
            && WS_VARIABLES.CNT < 6 
            && (GDCSBPAC.DR_SEC[WS_VARIABLES.CNT-1].TX_DR_AC.trim().length() != 0 
            || GDCSBPAC.DR_SEC[WS_VARIABLES.CNT-1].TXCTA_NO.trim().length() != 0); WS_VARIABLES.CNT += 1) {
            IBS.init(SCCGWA, WS_DR_SECTION);
            IBS.init(SCCGWA, WS_DR_USE_SEC);
            IBS.init(SCCGWA, DDRCCY);
            IBS.init(SCCGWA, DDRMST);
            WS_VARIABLES.DR_FLG = 'Y';
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, GDCSBPAC.DR_SEC[WS_VARIABLES.CNT-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DR_SECTION);
            CEP.TRC(SCCGWA, WS_DR_SECTION);
            DDRCCY.CUS_AC = WS_DR_SECTION.DR_SEC_DRAC;
            DDRMST.KEY.CUS_AC = WS_DR_SECTION.DR_SEC_DRAC;
            DDRCCY.CCY = GDCSBPAC.TX_CCY;
            DDRCCY.CCY_TYPE = GDCSBPAC.TX_CCYTYP;
            T000_READUP_DDTCCY();
            if (pgmRtn) return;
            T000_READ_DDTMST_N();
            if (pgmRtn) return;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1") 
                || DDRMST.AC_STS_WORD.substring(25 - 1, 25 + 1 - 1).equalsIgnoreCase("1") 
                || DDRMST.AC_STS_WORD.substring(26 - 1, 26 + 1 - 1).equalsIgnoreCase("1"))) {
                WS_VARIABLES.DR_FLG = 'N';
            }
            if (WS_VARIABLES.DR_FLG != 'N') {
                if (GDCSBPAC.MN_FLG == 'N' 
                    && WS_VARIABLES.CNT == 1) {
                    IBS.init(SCCGWA, DDRMST);
                    DDRMST.KEY.CUS_AC = WS_DR_SECTION.DR_SEC_DRAC;
                    T000_READ_DDTMST_N();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DDRMST.YW_TYP);
                    if (DDRMST.YW_TYP.equalsIgnoreCase("08")) {
                        WS_VARIABLES.N_FIRST_PROCESS_FLG = 'Y';
                    }
                }
                if (GDCSBPAC.MN_FLG == 'M' 
                    || (GDCSBPAC.MN_FLG == 'N' 
                    && WS_VARIABLES.CNT != 1) 
                    || WS_VARIABLES.N_FIRST_PROCESS_FLG == 'Y') {
                    if ((GDCSBPAC.MN_FLG == 'M' 
                        && WS_VARIABLES.CNT == 1) 
                        || WS_VARIABLES.N_FIRST_PROCESS_FLG == 'Y') {
                        WS_VARIABLES.N_FIRST_PROCESS_FLG = 'N';
                        if (WS_DR_SECTION.DR_SEC_DRAC.trim().length() > 0) {
                            WS_VARIABLES.DR_AC = WS_DR_SECTION.DR_SEC_DRAC;
                            WS_VARIABLES.DR_SEQ = WS_DR_SECTION.DR_SEC_SEQ;
                            B021_INQUIRE_AC_APP();
                            if (pgmRtn) return;
                            if (WS_DR_SECTION.DR_SEC_DRAMT_I <= WS_VARIABLES.LACK_AMT) {
                                WS_DR_SECTION.DR_SEC_DRAMT_I = WS_VARIABLES.LACK_AMT;
                            }
                            R000_CHECK_AVAIL_AMT();
                            if (pgmRtn) return;
                            if (WS_DR_SECTION.DR_SEC_DRAMT_I < WS_VARIABLES.DR_SEC_DRAMT) {
                                WS_VARIABLES.DR_SEC_DRAMT = WS_DR_SECTION.DR_SEC_DRAMT_I;
                            }
                            if (WS_VARIABLES.DR_SEC_DRAMT > 0) {
                                R000_GET_DR_AMT_NOTPLDR();
                                if (pgmRtn) return;
                                WS_OUT_DATA.WS_DR_AMT_ARRAY[WS_VARIABLES.CNT-1].DR_AMT = WS_VARIABLES.DR_SEC_DRAMT;
                                B023_PROCESS_DR_AC();
                                if (pgmRtn) return;
                            }
                        } else {
                            T000_STARTBR_GDTPLDR();
                            if (pgmRtn) return;
                            T000_READNEXT_GDTPLDR();
                            if (pgmRtn) return;
                            if (WS_COND_FLG.PLDR_FLG == 'N') {
                                CEP.ERR(SCCGWA, ERROR_MSG.GD_PLDR_REC_NOTFND);
                            }
                            WS_VARIABLES.DR_AC = GDRPLDR.KEY.AC;
                            WS_VARIABLES.DR_SEQ = GDRPLDR.KEY.AC_SEQ;
                            B021_INQUIRE_AC_APP();
                            if (pgmRtn) return;
                            B022_CHECK_STS_AND_RESET_AC_AMT();
                            if (pgmRtn) return;
                            WS_OUT_DATA.WS_DR_AMT_ARRAY[WS_VARIABLES.CNT-1].DR_AMT = WS_DB_VARS.DR_SEC_PLDR_AMT;
                            if (WS_VARIABLES.MPRD_TD_FLG == '0') {
                                B024_RELEASE_PLDR();
                                if (pgmRtn) return;
                                R000_CHECK_AVAIL_AMT();
                                if (pgmRtn) return;
                                if (WS_VARIABLES.DR_SEC_DRAMT > 0) {
                                    R000_GET_DR_AMT_PLDR();
                                    if (pgmRtn) return;
                                    if (WS_VARIABLES.DR_SEC_DRAMT > 0) {
                                        WS_OUT_DATA.WS_DR_AMT_ARRAY[WS_VARIABLES.CNT-1].DR_AMT = WS_VARIABLES.DR_SEC_DRAMT;
                                        B023_PROCESS_DR_AC();
                                        if (pgmRtn) return;
                                    }
                                }
                            } else {
                                while (WS_COND_FLG.PLDR_FLG != 'N') {
                                    WS_VARIABLES.DR_AC = GDRPLDR.KEY.AC;
                                    WS_VARIABLES.DR_SEQ = GDRPLDR.KEY.AC_SEQ;
                                    B021_INQUIRE_AC_APP();
                                    if (pgmRtn) return;
                                    WS_DB_VARS.DR_SEC_PLDR_AMT = GDRPLDR.RELAT_AMT;
                                    B024_RELEASE_PLDR();
                                    if (pgmRtn) return;
                                    R000_CHECK_AVAIL_AMT();
                                    if (pgmRtn) return;
                                    if (WS_VARIABLES.DR_SEC_DRAMT > 0 
                                        && WS_DB_VARS.DR_SEC_PLDR_AMT > 0) {
                                        R000_GET_DR_AMT_PLDR();
                                        if (pgmRtn) return;
                                        WS_OUT_DATA.WS_DR_AMT_ARRAY[WS_VARIABLES.CNT-1].DR_AMT = WS_VARIABLES.DR_SEC_DRAMT;
                                        B023_PROCESS_DR_AC();
                                        if (pgmRtn) return;
                                    }
                                    T000_READNEXT_GDTPLDR();
                                    if (pgmRtn) return;
                                }
                            }
                            T000_ENDBR_GDTPLDR();
                            if (pgmRtn) return;
                        }
                    } else {
                        WS_VARIABLES.DR_AC = WS_DR_SECTION.DR_SEC_DRAC;
                        WS_VARIABLES.DR_SEQ = WS_DR_SECTION.DR_SEC_SEQ;
                        B021_INQUIRE_AC_APP();
                        if (pgmRtn) return;
                        if (WS_DR_USE_SEC.DR_GD_FLG != 'Y') {
                            R000_CHECK_AVAIL_AMT();
                            if (pgmRtn) return;
                            if (WS_VARIABLES.DR_SEC_DRAMT > 0) {
                                R000_GET_DR_AMT_NOTPLDR();
                                if (pgmRtn) return;
                                WS_OUT_DATA.WS_DR_AMT_ARRAY[WS_VARIABLES.CNT-1].DR_AMT = WS_VARIABLES.DR_SEC_DRAMT;
                                B023_PROCESS_DR_AC();
                                if (pgmRtn) return;
                            }
                        }
                    }
                }
            }
        }
    }
    public void R000_GET_DR_AMT_PLDR() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.DR_SEC_DRAMT > WS_DB_VARS.DR_SEC_PLDR_AMT) {
            WS_VARIABLES.DR_SEC_DRAMT = WS_DB_VARS.DR_SEC_PLDR_AMT;
            if (WS_VARIABLES.DR_SEC_DRAMT > WS_VARIABLES.LACK_AMT) {
                WS_VARIABLES.DR_SEC_DRAMT = WS_VARIABLES.LACK_AMT;
                WS_VARIABLES.LACK_AMT = 0;
            } else {
                WS_VARIABLES.LACK_AMT = WS_VARIABLES.LACK_AMT - WS_VARIABLES.DR_SEC_DRAMT;
            }
        } else {
            WS_VARIABLES.LACK_AMT = WS_VARIABLES.LACK_AMT - WS_VARIABLES.DR_SEC_DRAMT;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.DR_SEC_DRAMT);
        CEP.TRC(SCCGWA, WS_DB_VARS.DR_SEC_PLDR_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.LACK_AMT);
    }
    public void R000_GET_DR_AMT_NOTPLDR() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.DR_SEC_DRAMT > WS_VARIABLES.LACK_AMT) {
            WS_VARIABLES.DR_SEC_DRAMT = WS_VARIABLES.LACK_AMT;
            WS_VARIABLES.LACK_AMT = 0;
        } else {
            WS_VARIABLES.LACK_AMT = WS_VARIABLES.LACK_AMT - WS_VARIABLES.DR_SEC_DRAMT;
        }
    }
    public void B021_INQUIRE_AC_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_VARIABLES.DR_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.RC.RC_CODE == 0) {
            WS_DR_USE_SEC.DR_AC_TYP = CICQACRI.O_DATA.O_FRM_APP;
        } else {
            WS_DR_USE_SEC.DR_AC_TYP = "AI";
        }
        if (WS_DR_USE_SEC.DR_AC_TYP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = WS_VARIABLES.DR_AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRMST.YW_TYP);
            WS_DR_USE_SEC.DR_AC_YW_TYP = DDRMST.YW_TYP;
            B015_CHECK_GDKD_PROC();
            if (pgmRtn) return;
            WS_VARIABLES.MPRD_TD_FLG = DDVMPRD.VAL.TD_FLG;
        } else if (WS_DR_USE_SEC.DR_AC_TYP.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = WS_VARIABLES.DR_AC;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRCMST.REF_TYP);
            WS_DR_USE_SEC.DR_AC_YW_TYP = TDRCMST.REF_TYP;
        } else {
        }
        WS_DR_USE_SEC.DR_GD_FLG = 'N';
        if ((WS_DR_USE_SEC.DR_AC_YW_TYP.equalsIgnoreCase("01") 
            || WS_DR_USE_SEC.DR_AC_YW_TYP.equalsIgnoreCase("02") 
            || WS_DR_USE_SEC.DR_AC_YW_TYP.equalsIgnoreCase("03") 
            || WS_DR_USE_SEC.DR_AC_YW_TYP.equalsIgnoreCase("04") 
            || WS_DR_USE_SEC.DR_AC_YW_TYP.equalsIgnoreCase("07") 
            || WS_DR_USE_SEC.DR_AC_YW_TYP.equalsIgnoreCase("09") 
            || WS_DR_USE_SEC.DR_AC_YW_TYP.equalsIgnoreCase("10") 
            || WS_DR_USE_SEC.DR_AC_YW_TYP.equalsIgnoreCase("15") 
            || WS_DR_USE_SEC.DR_AC_YW_TYP.equalsIgnoreCase("16") 
            || WS_DR_USE_SEC.DR_AC_YW_TYP.equalsIgnoreCase("17") 
            || WS_DR_USE_SEC.DR_AC_YW_TYP.equalsIgnoreCase("18"))) {
            WS_DR_USE_SEC.DR_GD_FLG = 'Y';
        }
        if (WS_DR_USE_SEC.DR_AC_YW_TYP.trim().length() == 0 
            && WS_VARIABLES.COUNTER_AC.trim().length() == 0) {
            WS_VARIABLES.COUNTER_AC = WS_VARIABLES.DR_AC;
        }
    }
    public void B015_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = WS_VARIABLES.DR_AC;
        DDRCCY.CCY = GDCSBPAC.TX_CCY;
        DDRCCY.CCY_TYPE = GDCSBPAC.TX_CCYTYP;
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDCSBPAC.TX_CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, GDCSBPAC.TX_CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        WS_VARIABLES.MPRD_TD_FLG = DDVMPRD.VAL.TD_FLG;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void B022_CHECK_STS_AND_RESET_AC_AMT() throws IOException,SQLException,Exception {
        if (WS_DR_USE_SEC.DR_GD_FLG == 'Y') {
            GDRPLDR.DEAL_CD = WS_DR_SECTION.DR_SEC_CTA_NO;
            GDRPLDR.BSREF = WS_DR_SECTION.DR_SEC_REF_NO;
            GDRPLDR.RELAT_STS = 'N';
            T000_GROUP_AMT_GDTPLDR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_DB_VARS.DR_SEC_PLDR_AMT);
            CEP.TRC(SCCGWA, WS_DR_SECTION.DR_SEC_DRAMT_I);
            if (WS_DB_VARS.DR_SEC_PLDR_AMT != 0) {
                if (WS_DB_VARS.DR_SEC_PLDR_AMT != WS_DR_SECTION.DR_SEC_DRAMT_I) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_RL_AMT_GT_LMT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_CHECK_AVAIL_AMT() throws IOException,SQLException,Exception {
        if (WS_DR_USE_SEC.DR_AC_TYP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = WS_VARIABLES.DR_AC;
            DDRCCY.CCY = GDCSBPAC.TX_CCY;
            DDRCCY.CCY_TYPE = GDCSBPAC.TX_CCYTYP;
            T000_READUP_DDTCCY();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_VARIABLES.DR_SEC_DRAMT = 0;
            } else {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if ((DDRCCY.STS != 'N' 
                    || DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1"))) {
                    WS_VARIABLES.DR_SEC_DRAMT = 0;
                } else {
                    WS_VARIABLES.DR_SEC_DRAMT = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.HOLD_BAL;
                    if (WS_VARIABLES.DR_SEC_DRAMT < 0) {
                        WS_VARIABLES.DR_SEC_DRAMT = 0;
                    }
                }
            }
        } else if (WS_DR_USE_SEC.DR_AC_TYP.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = WS_VARIABLES.DR_AC;
            CICQACAC.DATA.AGR_SEQ = WS_VARIABLES.DR_SEQ;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            if (CICQACAC.RC.RC_CODE != 0) {
                WS_VARIABLES.DR_SEC_DRAMT = 0;
            } else {
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                T000_READUP_TDTSMST_BY_ACOAC();
                if (pgmRtn) return;
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if ((TDRSMST.ACO_STS != '0' 
                    || !TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
                    || !TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0") 
                    || !TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("0") 
                    || !TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("0") 
                    || !TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0"))) {
                    WS_VARIABLES.DR_SEC_DRAMT = 0;
                } else {
                    WS_VARIABLES.DR_SEC_DRAMT = TDRSMST.BAL - TDRSMST.HBAL;
                }
            }
        } else {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = WS_VARIABLES.DR_AC;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            WS_VARIABLES.DR_SEC_DRAMT = AICPQMIB.OUTPUT_DATA.CBAL;
        }
    }
    public void B023_PROCESS_DR_AC() throws IOException,SQLException,Exception {
        if (WS_DR_USE_SEC.DR_AC_TYP.equalsIgnoreCase("DD")) {
            B023_01_PROCESS_DD();
            if (pgmRtn) return;
        } else if (WS_DR_USE_SEC.DR_AC_TYP.equalsIgnoreCase("TD")) {
            B023_02_PROCESS_TD();
            if (pgmRtn) return;
        } else if (WS_DR_USE_SEC.DR_AC_TYP.equalsIgnoreCase("AI")) {
            B023_03_PROCESS_AI();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, ERROR_MSG.GD_AC_NO_M_INPUT);
        }
    }
    public void B023_01_PROCESS_DD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = WS_VARIABLES.DR_AC;
        DDCUDRAC.CCY = GDCSBPAC.TX_CCY;
        DDCUDRAC.CCY_TYPE = GDCSBPAC.TX_CCYTYP;
        DDCUDRAC.TX_AMT = WS_VARIABLES.DR_SEC_DRAMT;
        if (GDCSBPAC.MN_FLG == 'N') {
            DDCUDRAC.OTHER_AC = GDRTRAN.ADV_AC;
        } else {
            DDCUDRAC.OTHER_AC = GDCSBPAC.TX_CRAC;
        }
        WS_VARIABLES.OTHER_AC = DDCUDRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        DDCUDRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUDRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
        DDCUDRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUDRAC.REMARKS = GDCSBPAC.TXRMK;
        if (GDCSBPAC.TXMMO.trim().length() == 0) {
            DDCUDRAC.TX_MMO = "A019";
        } else {
            DDCUDRAC.TX_MMO = GDCSBPAC.TXMMO;
        }
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_VARIABLES.DR_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        WS_VARIABLES.PAY_MAN = CICACCU.DATA.AC_CNM;
        WS_VARIABLES.PAYING_INT = DDCUDRAC.MARGIN_INT;
        if (WS_VARIABLES.PAYING_INT > 0) {
            WS_VARIABLES.DR_SEQ = 0;
            R000_GET_INT_AC();
            if (pgmRtn) return;
            R000_CR_INT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B023_02_PROCESS_TD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACDRU);
        TDCACDRU.INRT_MTH = '2';
        TDCACDRU.MAC_CNO = WS_VARIABLES.DR_AC;
        TDCACDRU.AC_SEQ = WS_VARIABLES.DR_SEQ;
        TDCACDRU.CCY = GDCSBPAC.TX_CCY;
        TDCACDRU.CCY_TYP = GDCSBPAC.TX_CCYTYP;
        TDCACDRU.TXN_AMT = WS_VARIABLES.DR_SEC_DRAMT;
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
        if (GDCSBPAC.MN_FLG == 'N') {
            TDCACDRU.OPP_AC_CNO = GDRTRAN.ADV_AC;
        } else {
            TDCACDRU.OPP_AC_CNO = GDCSBPAC.TX_CRAC;
        }
        TDCACDRU.CT_FLG = '1';
        CEP.TRC(SCCGWA, TDCACDRU.OPT);
        CEP.TRC(SCCGWA, TDCACDRU.BAL);
        CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
        TDCACDRU.PRDMO_CD = "ALL";
        if (GDCSBPAC.TXMMO.trim().length() == 0) {
            TDCACDRU.TXN_MMO = "A019";
        } else {
            TDCACDRU.TXN_MMO = GDCSBPAC.TXMMO;
        }
        if (TDRSMST.BAL > WS_VARIABLES.DR_SEC_DRAMT) {
            TDCACDRU.OPT = '8';
        } else {
            TDCACDRU.OPT = '9';
        }
        S000_CALL_TDZACDRU();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_VARIABLES.DR_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        WS_VARIABLES.PAY_MAN = CICACCU.DATA.AC_CNM;
        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
        WS_VARIABLES.PAYING_INT = TDCACDRU.PAYING_INT;
        if (WS_VARIABLES.PAYING_INT > 0) {
            R000_GET_INT_AC();
            if (pgmRtn) return;
            R000_CR_INT_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CR_INT_PROC() throws IOException,SQLException,Exception {
        if (WS_OUT_DATA.PAYING_INT_FLG == 'Y') {
            CEP.TRC(SCCGWA, TDRSMST.CHE_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.AC_TYP = "3";
            AICPQIA.CD.BUSI_KND = TD_CHQ_CODE;
            if (GDCSBPAC.ADV_AC.trim().length() == 0) AICPQIA.BR = 0;
            else AICPQIA.BR = Integer.parseInt(GDCSBPAC.ADV_AC);
            AICPQIA.CCY = GDCSBPAC.TX_CCY;
            AICPQIA.SIGN = 'C';
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICPQIA.AC);
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = AICPQIA.AC;
            AICUUPIA.DATA.RVS_SEQ = 0;
            AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICUUPIA.DATA.PAY_MAN = WS_VARIABLES.PAY_MAN;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.AMT = WS_VARIABLES.PAYING_INT;
            AICUUPIA.DATA.CCY = GDCSBPAC.TX_CCY;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.POST_NARR = " ";
            AICUUPIA.DATA.RVS_NO = " ";
            AICUUPIA.DATA.EVENT_CODE = "CR";
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        if ((WS_VARIABLES.TXINT_AC.trim().length() > 0) 
            || WS_DR_SECTION.DR_SEC_INT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            if (WS_DR_SECTION.DR_SEC_INT_AC.trim().length() > 0) {
                DDCUCRAC.AC = WS_DR_SECTION.DR_SEC_INT_AC;
            } else {
                DDCUCRAC.AC = WS_VARIABLES.TXINT_AC;
            }
            DDCUCRAC.OTHER_AC = WS_VARIABLES.DR_AC;
            WS_VARIABLES.OTHER_AC = DDCUCRAC.OTHER_AC;
            B170_02_GET_RLT_BR_INFO();
            if (pgmRtn) return;
            DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
            DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
            JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
            DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
            DDCUCRAC.CCY = GDCSBPAC.TX_CCY;
            DDCUCRAC.CCY_TYPE = GDCSBPAC.TX_CCYTYP;
            DDCUCRAC.TX_AMT = WS_VARIABLES.PAYING_INT;
            DDCUCRAC.TX_MMO = "S101";
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B023_03_PROCESS_AI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_VARIABLES.DR_AC;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = WS_VARIABLES.DR_SEC_DRAMT;
        AICUUPIA.DATA.CCY = GDCSBPAC.TX_CCY;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.PAY_MAN = "11";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            AICUUPIA.DATA.VALUE_DATE = BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B023_04_PROCESS_ALL() throws IOException,SQLException,Exception {
    }
    public void B024_RELEASE_PLDR() throws IOException,SQLException,Exception {
        if (WS_DR_USE_SEC.DR_AC_TYP.equalsIgnoreCase("DD") 
                && WS_VARIABLES.MPRD_TD_FLG != '0') {
            B024_01_RELEASE_DD_PLDR();
            if (pgmRtn) return;
        } else if (WS_DR_USE_SEC.DR_AC_TYP.equalsIgnoreCase("TD")) {
            B024_02_RELEASE_TD_PLDR();
            if (pgmRtn) return;
        } else if (WS_DR_USE_SEC.DR_AC_TYP.equalsIgnoreCase("DD") 
                && WS_VARIABLES.MPRD_TD_FLG == '0') {
            B024_03_RELEASE_ALL_PLDR();
            if (pgmRtn) return;
        }
    }
    public void B024_01_RELEASE_DD_PLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSRLSR);
        GDCSRLSR.VAL.RL_SEQ = GDRPLDR.KEY.RSEQ;
        GDCSRLSR.VAL.AC = GDRPLDR.KEY.AC;
        S000_CALL_GDZSRLSR();
        if (pgmRtn) return;
    }
    public void B024_02_RELEASE_TD_PLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSRLSR);
        GDCSRLSR.VAL.RL_SEQ = GDRPLDR.KEY.RSEQ;
        GDCSRLSR.VAL.AC = GDRPLDR.KEY.AC;
        GDCSRLSR.VAL.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
        S000_CALL_GDZSRLSR();
        if (pgmRtn) return;
    }
    public void B024_03_RELEASE_ALL_PLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSRLSR);
        GDCSRLSR.VAL.RL_SEQ = GDRPLDR.KEY.RSEQ;
        GDCSRLSR.VAL.AC = GDRPLDR.KEY.AC;
        GDCSRLSR.VAL.CTA_NO = GDRPLDR.DEAL_CD;
        GDCSRLSR.VAL.REF_NO = GDRPLDR.BSREF;
        S000_CALL_GDZSRLSR();
        if (pgmRtn) return;
    }
    public void B030_PROCESS_LACK_SECTION() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSBPAC.ADV_AC);
        CEP.TRC(SCCGWA, GDCSBPAC.TX_CCY);
        CEP.TRC(SCCGWA, GDCSBPAC.CNTR_NO);
        if (WS_VARIABLES.DR_AIAC == null) WS_VARIABLES.DR_AIAC = "";
        JIBS_tmp_int = WS_VARIABLES.DR_AIAC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_VARIABLES.DR_AIAC += " ";
        if (GDCSBPAC.ADV_AC == null) GDCSBPAC.ADV_AC = "";
        JIBS_tmp_int = GDCSBPAC.ADV_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) GDCSBPAC.ADV_AC += " ";
        WS_VARIABLES.DR_AIAC = GDCSBPAC.ADV_AC + WS_VARIABLES.DR_AIAC.substring(6);
        if (WS_VARIABLES.DR_AIAC == null) WS_VARIABLES.DR_AIAC = "";
        JIBS_tmp_int = WS_VARIABLES.DR_AIAC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_VARIABLES.DR_AIAC += " ";
        if (GDCSBPAC.TX_CCY == null) GDCSBPAC.TX_CCY = "";
        JIBS_tmp_int = GDCSBPAC.TX_CCY.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) GDCSBPAC.TX_CCY += " ";
        WS_VARIABLES.DR_AIAC = WS_VARIABLES.DR_AIAC.substring(0, 7 - 1) + GDCSBPAC.TX_CCY + WS_VARIABLES.DR_AIAC.substring(7 + 3 - 1);
        CEP.TRC(SCCGWA, GDCSBPAC.CNTR_NO);
        if (WS_VARIABLES.DR_AIAC == null) WS_VARIABLES.DR_AIAC = "";
        JIBS_tmp_int = WS_VARIABLES.DR_AIAC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_VARIABLES.DR_AIAC += " ";
        if (GDCSBPAC.CNTR_NO == null) GDCSBPAC.CNTR_NO = "";
        JIBS_tmp_int = GDCSBPAC.CNTR_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) GDCSBPAC.CNTR_NO += " ";
        WS_VARIABLES.DR_AIAC = WS_VARIABLES.DR_AIAC.substring(0, 10 - 1) + GDCSBPAC.CNTR_NO + WS_VARIABLES.DR_AIAC.substring(10 + 10 - 1);
        if (WS_VARIABLES.DR_AIAC == null) WS_VARIABLES.DR_AIAC = "";
        JIBS_tmp_int = WS_VARIABLES.DR_AIAC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_VARIABLES.DR_AIAC += " ";
        WS_VARIABLES.DR_AIAC = WS_VARIABLES.DR_AIAC.substring(0, 20 - 1) + "000001" + WS_VARIABLES.DR_AIAC.substring(20 + 6 - 1);
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_VARIABLES.DR_AIAC;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = WS_VARIABLES.LACK_AMT;
        AICUUPIA.DATA.CCY = GDCSBPAC.TX_CCY;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.PAY_MAN = "11";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            AICUUPIA.DATA.VALUE_DATE = BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
        B080_WHITE_TRAN_PROC();
        if (pgmRtn) return;
    }
    public void B030_2_REWRITE_TRAN_N() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.LACK_AMT);
        GDRTRAN.ADV_AMT = WS_VARIABLES.LACK_AMT;
        WS_OUT_DATA.DK_AMT = WS_VARIABLES.LACK_AMT;
        GDRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (WS_COND_FLG.TRAN_FLG == 'Y') {
            T000_REWRITE_GDTTRAN();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, ERROR_MSG.GD_TRAN_REC_NOFUND);
        }
    }
    public void B080_WHITE_TRAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCRTRAN);
        IBS.init(SCCGWA, GDRTRAN);
        GDRTRAN.KEY.DEAL_CD = GDCSBPAC.DR_SEC[1-1].TXCTA_NO;
        GDRTRAN.KEY.BSREF = GDCSBPAC.DR_SEC[1-1].TXREF_NO;
        GDRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (GDCSBPAC.ADV_AC.trim().length() == 0) GDRTRAN.ADV_BR = 0;
        else GDRTRAN.ADV_BR = Integer.parseInt(GDCSBPAC.ADV_AC);
        GDRTRAN.ADV_AMT = WS_VARIABLES.LACK_AMT;
        GDRTRAN.ADV_AC = WS_VARIABLES.DR_AIAC;
        GDRTRAN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRTRAN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRTRAN.FUNC = 'A';
        GDCRTRAN.REC_PTR = GDRTRAN;
        GDCRTRAN.REC_LEN = 518;
        S000_CALL_GDZRTRAN();
        if (pgmRtn) return;
    }
    public void B040_PEOCESS_CR_SECTION() throws IOException,SQLException,Exception {
        if (GDCSBPAC.MN_FLG == 'N') {
            WS_VARIABLES.TOT_DRAMT = WS_VARIABLES.TOT_DRAMT - WS_VARIABLES.LACK_AMT;
            if (WS_VARIABLES.TOT_DRAMT > 0) {
                B030_03_DEP_AI_PROC_N();
                if (pgmRtn) return;
            }
        } else {
            if (GDCSBPAC.DR_SEC[1-1].TXALLO_FLG == '2') {
                B030_04_DEP_THDQ_IBAC_PROC();
                if (pgmRtn) return;
            } else {
                B030_01_DEP_AI_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_01_DEP_AI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = GDCSBPAC.TX_CRAC;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = WS_VARIABLES.TOT_DRAMT;
        AICUUPIA.DATA.CCY = GDCSBPAC.TX_CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = " ";
        if (WS_VARIABLES.COUNTER_AC.trim().length() > 0) {
            WS_VARIABLES.OTHER_AC = WS_VARIABLES.COUNTER_AC;
            B170_02_GET_RLT_BR_INFO();
            if (pgmRtn) return;
            AICUUPIA.DATA.PAY_MAN = CICQACRI.O_DATA.O_AC_CNM;
        } else {
            AICUUPIA.DATA.PAY_MAN = "银票到期入账";
        }
        AICUUPIA.DATA.DESC = "银票到期入账: " + GDCSBPAC.DR_SEC[1-1].TXREF_NO;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            AICUUPIA.DATA.VALUE_DATE = BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B030_03_DEP_AI_PROC_N() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = GDRTRAN.ADV_AC;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = WS_VARIABLES.TOT_DRAMT;
        AICUUPIA.DATA.CCY = GDCSBPAC.TX_CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = " ";
        if (WS_VARIABLES.COUNTER_AC.trim().length() > 0) {
            WS_VARIABLES.OTHER_AC = WS_VARIABLES.COUNTER_AC;
            B170_02_GET_RLT_BR_INFO();
            if (pgmRtn) return;
            AICUUPIA.DATA.PAY_MAN = CICQACRI.O_DATA.O_AC_CNM;
        } else {
            AICUUPIA.DATA.PAY_MAN = "银票到期还垫";
        }
        AICUUPIA.DATA.DESC = "银票到期还垫: " + GDCSBPAC.DR_SEC[1-1].TXREF_NO;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            AICUUPIA.DATA.VALUE_DATE = BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        AICUUPIA.DATA.EVENT_CODE = "CR";
        CEP.TRC(SCCGWA, "UUPIA-PAY-MAN:");
        CEP.TRC(SCCGWA, AICUUPIA.DATA.PAY_MAN);
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B030_04_DEP_THDQ_IBAC_PROC() throws IOException,SQLException,Exception {
        if (GDCSBPAC.DR_SEC[1-1].TXALLO_FLG == '2' 
            && GDCSBPAC.DR_SEC[1-1].TXBR_TYP == ' ') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_THDQ_BR_MST_IPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.EQ_BKID == null) WS_VARIABLES.EQ_BKID = "";
        JIBS_tmp_int = WS_VARIABLES.EQ_BKID.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) WS_VARIABLES.EQ_BKID += " ";
        WS_VARIABLES.EQ_BKID = "A" + WS_VARIABLES.EQ_BKID.substring(1);
        if (WS_VARIABLES.EQ_BKID == null) WS_VARIABLES.EQ_BKID = "";
        JIBS_tmp_int = WS_VARIABLES.EQ_BKID.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) WS_VARIABLES.EQ_BKID += " ";
        JIBS_tmp_str[0] = "" + GDCSBPAC.DR_SEC[1-1].TXBR_TYP;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_VARIABLES.EQ_BKID = WS_VARIABLES.EQ_BKID.substring(0, 2 - 1) + JIBS_tmp_str[0] + WS_VARIABLES.EQ_BKID.substring(2 + 1 - 1);
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_VARIABLES.EQ_BKID;
        IBCQRAC.AC_TYP = "DQ";
        S000_CALL_IBZQRAC();
        if (pgmRtn) return;
        WS_VARIABLES.THDQ_IBAC = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.AC = WS_VARIABLES.THDQ_IBAC;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = "156";
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.AMT = GDCSBPAC.TXAMT;
        IBCPOSTA.OTH_AC_NO = GDCSBPAC.DR_SEC[2-1].TX_DR_AC;
        WS_VARIABLES.OTHER_AC = IBCPOSTA.OTH_AC_NO;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        IBCPOSTA.OTH_CNM = CICQACRI.O_DATA.O_AC_CNM;
        IBCPOSTA.OTH_BR = CICQACRI.O_DATA.O_OPN_BR;
        IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
        S000_CALL_IBZCRAC();
        if (pgmRtn) return;
    }
    public void B030_02_DEP_DD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = GDCSBPAC.TX_CRAC;
        DDCUCRAC.CCY = GDCSBPAC.TX_CCY;
        if (GDCSBPAC.TX_CCY.equalsIgnoreCase("156")) {
            DDCUCRAC.CCY_TYPE = '1';
        } else {
            DDCUCRAC.CCY_TYPE = GDCSBPAC.TX_CCYTYP;
        }
        DDCUCRAC.TX_AMT = WS_VARIABLES.TOT_DRAMT;
        DDCUCRAC.OTHER_AC = WS_VARIABLES.DR_AC;
        WS_VARIABLES.OTHER_AC = DDCUCRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
        DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUCRAC.REMARKS = GDCSBPAC.TXRMK;
        DDCUCRAC.TX_MMO = "A001";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUCRAC.VAL_DATE = BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B050_PROCESS_CZDQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = GDCSBPAC.DR_SEC[1-1].TXALLO_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_VARIABLES.EQ_BKID = BPCPQORG.VIL_TYP;
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_VARIABLES.EQ_BKID;
        IBCQRAC.AC_TYP = "04";
        S000_CALL_IBZQRAC();
        if (pgmRtn) return;
        WS_VARIABLES.IB_AC1 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_VARIABLES.EQ_BKID;
        IBCQRAC.AC_TYP = "03";
        S000_CALL_IBZQRAC();
        if (pgmRtn) return;
        WS_VARIABLES.IB_AC2 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.AC = WS_VARIABLES.IB_AC1;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = "156";
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.AMT = GDCSBPAC.TXAMT;
        IBCPOSTA.OTH_AC_NO = WS_VARIABLES.IB_AC2;
        WS_VARIABLES.OTHER_AC = IBCPOSTA.OTH_AC_NO;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        IBCPOSTA.OTH_CNM = CICQACRI.O_DATA.O_AC_CNM;
        IBCPOSTA.OTH_BR = CICQACRI.O_DATA.O_OPN_BR;
        IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.AC = WS_VARIABLES.IB_AC2;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = "156";
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.AMT = GDCSBPAC.TXAMT;
        IBCPOSTA.OTH_AC_NO = WS_VARIABLES.IB_AC1;
        WS_VARIABLES.OTHER_AC = IBCPOSTA.OTH_AC_NO;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        IBCPOSTA.OTH_CNM = CICQACRI.O_DATA.O_AC_CNM;
        IBCPOSTA.OTH_BR = CICQACRI.O_DATA.O_OPN_BR;
        IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
        S000_CALL_IBZCRAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_VARIABLES.EQ_BKID;
        IBCQRAC.AC_TYP = "01";
        S000_CALL_IBZQRAC();
        if (pgmRtn) return;
        WS_VARIABLES.DD_AC1 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_VARIABLES.EQ_BKID;
        IBCQRAC.AC_TYP = "02";
        S000_CALL_IBZQRAC();
        if (pgmRtn) return;
        WS_VARIABLES.DD_AC2 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'C';
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = WS_VARIABLES.DD_AC1;
        DDCUCRAC.CCY = "156";
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_AMT = GDCSBPAC.TXAMT;
        DDCUCRAC.OTHER_AC = WS_VARIABLES.DD_AC2;
        WS_VARIABLES.OTHER_AC = DDCUCRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
        DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUCRAC.TX_MMO = "A001";
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCUDRAC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = WS_VARIABLES.DD_AC2;
        DDCUDRAC.CCY = "156";
        DDCUDRAC.CCY_TYPE = '1';
        DDCUDRAC.TX_AMT = GDCSBPAC.TXAMT;
        DDCUDRAC.OTHER_AC = WS_VARIABLES.DD_AC1;
        WS_VARIABLES.OTHER_AC = DDCUDRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        DDCUDRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUDRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
        DDCUDRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUDRAC.REMARKS = GDCSBPAC.TXRMK;
        DDCUDRAC.TX_MMO = "A019";
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void R000_GET_INT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.KEY.AC = WS_VARIABLES.DR_AC;
        GDRSTAC.KEY.AC_SEQ = WS_VARIABLES.DR_SEQ;
        T000_READ_GDTSTAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRSTAC.KEY.AC);
        CEP.TRC(SCCGWA, GDRSTAC.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
        if (GDRSTAC.INT_AC.trim().length() > 0) {
            WS_VARIABLES.TXINT_AC = GDRSTAC.INT_AC;
        } else {
            WS_VARIABLES.TXINT_AC = GDRSTAC.ST_AC;
        }
        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
        CEP.TRC(SCCGWA, WS_VARIABLES.TXINT_AC);
        if (WS_DR_SECTION.DR_SEC_INT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = WS_DR_SECTION.DR_SEC_INT_AC;
            DDRCCY.CCY = GDCSBPAC.TX_CCY;
            DDRCCY.CCY_TYPE = GDCSBPAC.TX_CCYTYP;
            T000_READUP_DDTCCY();
            if (pgmRtn) return;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS != 'N' 
                || DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1"))) {
                WS_OUT_DATA.INT_TO_AI_FLG = 'Y';
                WS_DR_SECTION.DR_SEC_INT_AC = " ";
            }
        } else {
            WS_OUT_DATA.INT_TO_AI_FLG = 'Y';
            WS_DR_SECTION.DR_SEC_INT_AC = " ";
        }
        if (WS_VARIABLES.TXINT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = WS_VARIABLES.TXINT_AC;
            DDRCCY.CCY = GDCSBPAC.TX_CCY;
            DDRCCY.CCY_TYPE = GDCSBPAC.TX_CCYTYP;
            T000_READUP_DDTCCY();
            if (pgmRtn) return;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS != 'N' 
                || DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1"))) {
                WS_OUT_DATA.ST_TO_AI_FLG = 'Y';
                WS_VARIABLES.TXINT_AC = " ";
            }
        } else {
            WS_OUT_DATA.ST_TO_AI_FLG = 'Y';
            WS_VARIABLES.TXINT_AC = " ";
        }
        if (WS_OUT_DATA.ST_TO_AI_FLG == 'Y' 
            && WS_OUT_DATA.INT_TO_AI_FLG == 'Y') {
            WS_OUT_DATA.PAYING_INT_FLG = 'Y';
        }
    }
    public void B060_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 147;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B170_02_GET_RLT_BR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_VARIABLES.OTHER_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        IBS.init(SCCGWA, BPCPQORG);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = WS_VARIABLES.OTHER_AC;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            BPCPQORG.BR = AICPQMIB.INPUT_DATA.BR;
        } else {
            BPCPQORG.BR = CICQACRI.O_DATA.O_OPN_BR;
        }
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
    }
    public void B111_CALL_LNCIGECR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIGECR);
        LNCIGECR.INPUT_INFO.PAPER_NO = GDCSBPAC.DR_SEC[1-1].TXCTA_NO;
        if (GDCSBPAC.DR_SEC[1-1].TXREF_NO.trim().length() > 0) {
            LNCIGECR.INPUT_INFO.DRAW_NO = GDCSBPAC.DR_SEC[1-1].TXREF_NO;
        } else {
            LNCIGECR.INPUT_INFO.DRAW_NO = TXREF_NO;
        }
        LNCIGECR.INPUT_INFO.CTA_FROM = '3';
        LNCIGECR.INPUT_INFO.BOOK_BR = GDCSBPAC.TX_BR;
        LNCIGECR.INPUT_INFO.CCY = GDCSBPAC.TX_CCY;
        LNCIGECR.INPUT_INFO.CI_NO = GDCSBPAC.CI_NO;
        S000_CALL_LNZIGECR();
        if (pgmRtn) return;
        GDCSBPAC.CNTRT_NO = LNCIGECR.OUTPOUT_INFO.CONTRACT_NO;
        CEP.TRC(SCCGWA, GDCSBPAC.CNTRT_NO);
    }
    public void B112_CALL_LNCSWLAA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSWLAA);
        IBS.init(SCCGWA, LNCSWLAA);
        LNCSWLAA.COMM_DATA.ADV_TYPE = GDCSBPAC.ADV_TYPE;
        if (GDCSBPAC.DR_SEC[1-1].TXREF_NO.trim().length() > 0) {
            LNCSWLAA.COMM_DATA.DRAW_NO = GDCSBPAC.DR_SEC[1-1].TXREF_NO;
        } else {
            LNCSWLAA.COMM_DATA.DRAW_NO = TXREF_NO;
        }
        LNCSWLAA.COMM_DATA.CMMT_NO = GDCSBPAC.DR_SEC[1-1].TXCTA_NO;
        CEP.TRC(SCCGWA, GDCSBPAC.CNTRT_NO);
        LNCSWLAA.COMM_DATA.CONT_NO = GDCSBPAC.CNTRT_NO;
        LNCSWLAA.COMM_DATA.DOMI_BR = GDCSBPAC.TX_BR;
        LNCSWLAA.COMM_DATA.CI_NO = GDCSBPAC.CI_NO;
        LNCSWLAA.COMM_DATA.PROD_TYP = GDCSBPAC.PROD_TYP;
        LNCSWLAA.COMM_DATA.CCY = GDCSBPAC.TX_CCY;
        LNCSWLAA.COMM_DATA.CCY_TYP = GDCSBPAC.TX_CCYTYP;
        LNCSWLAA.COMM_DATA.AMT = WS_OUT_DATA.DK_AMT;
        LNCSWLAA.COMM_DATA.START_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNCSWLAA.COMM_DATA.MATU_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNCSWLAA.COMM_DATA.PEN_RRAT = GDCSBPAC.PEN_RRAT;
        LNCSWLAA.COMM_DATA.PEN_TYP = GDCSBPAC.PEN_TYP;
        LNCSWLAA.COMM_DATA.PEN_RATC = GDCSBPAC.PEN_RATC;
        LNCSWLAA.COMM_DATA.PEN_SPR = GDCSBPAC.PEN_SPR;
        LNCSWLAA.COMM_DATA.PEN_PCT = GDCSBPAC.PEN_PCT;
        LNCSWLAA.COMM_DATA.CPND_RTYPE = GDCSBPAC.CPND_RTY;
        LNCSWLAA.COMM_DATA.CPND_TYP = GDCSBPAC.CPND_TYP;
        LNCSWLAA.COMM_DATA.CPND_RATC = GDCSBPAC.CPNDRATC;
        LNCSWLAA.COMM_DATA.CPND_SPR = GDCSBPAC.CPND_SPR;
        LNCSWLAA.COMM_DATA.CPND_PCT = GDCSBPAC.CPND_PCT;
        LNCSWLAA.COMM_DATA.INT_MTH = GDCSBPAC.INT_MTH;
        LNCSWLAA.COMM_DATA.PEN_RATT = GDCSBPAC.PEN_RATT;
        LNCSWLAA.COMM_DATA.PEN_IRAT = GDCSBPAC.PEN_IRAT;
        LNCSWLAA.COMM_DATA.CPND_USE = GDCSBPAC.CPND_USE;
        LNCSWLAA.COMM_DATA.CPND_RATT = GDCSBPAC.CPNDRATT;
        LNCSWLAA.COMM_DATA.CPND_IRATE = GDCSBPAC.CPNDIRAT;
        LNCSWLAA.COMM_DATA.OCAL_PD = GDCSBPAC.OCAL_PD;
        LNCSWLAA.COMM_DATA.OCAL_PDU = GDCSBPAC.OCAL_UT;
        LNCSWLAA.COMM_DATA.DRAW_BK_TP = GDCSBPAC.DW_BK_TP;
        LNCSWLAA.COMM_DATA.DRAW_ACT = GDCSBPAC.DRAW_ACT;
        LNCSWLAA.COMM_DATA.DRAW_AC = GDRTRAN.ADV_AC;
        LNCSWLAA.COMM_DATA.PAY_BK_TP = GDCSBPAC.PA_BK_TP;
        LNCSWLAA.COMM_DATA.PAY_AC_T = GDCSBPAC.PAY_AC_T;
        LNCSWLAA.COMM_DATA.PAY_AC = GDCSBPAC.PAY_AC;
        LNCSWLAA.COMM_DATA.REMARK1 = GDCSBPAC.TXRMK;
        S000_CALL_LNZSWLAA();
        if (pgmRtn) return;
        GDCSBPAC.DRW_AMT = LNCSWLAA.COMM_DATA.AMT;
        CEP.TRC(SCCGWA, GDCSBPAC.DRW_AMT);
    }
    public void T000_GROUP_AMT_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-DR-SEC-PLDR-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_GROUP_AMT_GDTPLDR_AC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS "
            + "AND AC = :GDRPLDR.KEY.AC";
        GDTPLDR_RD.set = "WS-DR-SEC-PLDR-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_GROUP_AMT_GDTPLDR_AC_SEQ() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS "
            + "AND AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
        GDTPLDR_RD.set = "WS-DR-SEC-PLDR-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void T000_STARTBR_GDTPLDR() throws IOException,SQLException,Exception {
        WS_COND_FLG.PLDR_FLG = 'Y';
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.DEAL_CD = WS_DR_SECTION.DR_SEC_CTA_NO;
        GDRPLDR.BSREF = WS_DR_SECTION.DR_SEC_REF_NO;
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "RELAT_STS = 'N' "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF";
        GDTPLDR_BR.rp.upd = true;
        GDTPLDR_BR.rp.order = "AC,AC_SEQ DESC";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.PLDR_FLG = 'N';
        }
    }
    public void T000_READNEXT_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.PLDR_FLG = 'Y';
        } else {
            WS_COND_FLG.PLDR_FLG = 'N';
        }
    }
    public void T000_ENDBR_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTPLDR_BR);
    }
    public void T000_READ_GDTTRAN() throws IOException,SQLException,Exception {
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        IBS.READ(SCCGWA, GDRTRAN, GDTTRAN_RD);
    }
    public void T000_READUP_GDTTRAN() throws IOException,SQLException,Exception {
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        GDTTRAN_RD.upd = true;
        IBS.READ(SCCGWA, GDRTRAN, GDTTRAN_RD);
    }
    public void T000_REWRITE_GDTTRAN() throws IOException,SQLException,Exception {
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        IBS.REWRITE(SCCGWA, GDRTRAN, GDTTRAN_RD);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST_N() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.eqWhere = "CUS_AC";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_TDTSMST_BY_ACOAC() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.eqWhere = "ACO_AC";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_GDTSTAC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.where = "AC = :GDRSTAC.KEY.AC "
            + "AND AC_SEQ = :GDRSTAC.KEY.AC_SEQ";
        IBS.READ(SCCGWA, GDRSTAC, this, GDTSTAC_RD);
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZCRAC", IBCPOSTA, true);
        CEP.TRC(SCCGWA, IBCPOSTA.RC);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPORUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-SUPR-ORG", BPCPORUP);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_GDZSRLSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSRLSR", GDCSRLSR, true);
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU, true);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG, true);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB, true);
        CEP.TRC(SCCGWA, AICPQMIB.RC.RC_CODE);
        if (AICPQMIB.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void S000_CALL_IBZQRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-INQ-RELA-AC", IBCQRAC, true);
        CEP.TRC(SCCGWA, IBCQRAC.RC);
        if (IBCQRAC.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCQRAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZDRAC", IBCPOSTA, true);
        CEP.TRC(SCCGWA, IBCPOSTA.RC);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_GDZRTRAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRTRAN", GDCRTRAN, true);
        if (GDCRTRAN.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRTRAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSWLAA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-LNZSWLAA", LNCSWLAA);
        if (LNCSWLAA.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSWLAA.RC);
            SCCMSG.INFO = "CALL-LNZSWLAA ERROR";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIGECR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-GENERATE-CNTRNO", LNCIGECR);
        if (LNCIGECR.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIGECR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
