package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMSTS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITCISTS_RD;
    DBParm CITCNT_RD;
    DBParm CITADR_RD;
    DBParm CITBAS_RD;
    DBParm CITID_RD;
    DBParm CITPDM_RD;
    brParm CITID_BR = new brParm();
    brParm CITRELN_BR = new brParm();
    brParm CITAGT_BR = new brParm();
    brParm CITACR_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_X = "CIX01";
    String K_OUTPUT_FMT_9 = "CI045";
    int K_MAX_ROW = 30;
    int K_MAX_COL = 99;
    int K_COL_STS = 9;
    String K_CI_STS_NORMAL = "00";
    String K_CI_STS_STOP = "01";
    String K_CI_STS_CLOSE = "02";
    String K_CI_STS_DEATH = "11";
    String K_CI_STS_DEPOSIT = "27";
    String K_CI_STS_DELAY = "28";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "MAIN STS        ";
    String K_HIS_CPY1 = "CIRCISTS";
    String K_HIS_CPY2 = "CIRBAS";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    CIZMSTS_WS_STS_INFO WS_STS_INFO = new CIZMSTS_WS_STS_INFO();
    CIZMSTS_WS_STS_LIST WS_STS_LIST = new CIZMSTS_WS_STS_LIST();
    String WS_BAS_STS = " ";
    short WS_BAS_STSCD = 0;
    short WS_STSCD = 0;
    String WS_MUTEX_STSCD = " ";
    int WS_MIN_DT = 0;
    int WS_DATE = 0;
    CIZMSTS_WS_MSGID WS_MSGID = new CIZMSTS_WS_MSGID();
    char WS_CI_STSW_24 = ' ';
    char WS_STS_FLG = ' ';
    char WS_CISTS_FLG = ' ';
    char WS_AGT_FLG = ' ';
    char WS_ACR_FLG = ' ';
    char WS_ID_FLG = ' ';
    char WS_RELN_FLG = ' ';
    char WS_CPY_FLG = ' ';
    char WS_CITPDM_FLG = ' ';
    char WS_CITADR_FLG = ' ';
    char WS_CITCNT_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CICPAGT CICPAGT = new CICPAGT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CIRCISTS CIRCISTO = new CIRCISTS();
    CIRCISTS CIRCISTN = new CIRCISTS();
    CIRBAS CIRBASO = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    CIRPDM CIRPDM = new CIRPDM();
    CIRCNT CIRCNT = new CIRCNT();
    CIRADR CIRADR = new CIRADR();
    CIRBAS CIRBAS = new CIRBAS();
    CIRAGT CIRAGT = new CIRAGT();
    CIRACR CIRACR = new CIRACR();
    CIRCISTS CIRCISTS = new CIRCISTS();
    CIRID CIRID = new CIRID();
    CIRRELN CIRRELN = new CIRRELN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMSTS CICMSTS;
    public void MP(SCCGWA SCCGWA, CICMSTS CICMSTS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMSTS = CICMSTS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMSTS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        if (CICMSTS.FUNC == 'M') {
        } else if (CICMSTS.FUNC == 'I') {
            B070_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (CICMSTS.FUNC == 'B') {
            B080_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (CICMSTS.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if (CICMSTS.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else if (CICMSTS.FUNC == 'C') {
            B060_CHECK_INF_STS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICMSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICMSTS.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        WS_BAS_STS = CIRBAS.STSW;
        if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_DEATH) 
            && (CIRBAS.CI_TYP == '2' 
            || CIRBAS.CI_TYP == '3')) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_STS_CANT_DEATH, CICMSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        S000_CALL_SCZGJRN();
        if (pgmRtn) return;
        GWA_BP_AREA.NFHIS_CUR_SEQ = 0;
    } //FROM #ENDIF
    }
    public void B030_ADD_PROC() throws IOException,SQLException,Exception {
        if (WS_BAS_STS == null) WS_BAS_STS = "";
        JIBS_tmp_int = WS_BAS_STS.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) WS_BAS_STS += " ";
        if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_STOP) 
            && WS_BAS_STS.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "CLOSE CUST CANT BE STOP");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CI_STS_CLOSE_E, CICMSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CICMSTS.DATA.STS_CODE.trim().length() == 0) WS_STSCD = 0;
        else WS_STSCD = Short.parseShort(CICMSTS.DATA.STS_CODE);
        CEP.TRC(SCCGWA, WS_STSCD);
        WS_STSCD += 1;
        CEP.TRC(SCCGWA, WS_STSCD);
        if (WS_BAS_STS == null) WS_BAS_STS = "";
        JIBS_tmp_int = WS_BAS_STS.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) WS_BAS_STS += " ";
        if (WS_BAS_STS.substring(WS_STSCD - 1, WS_STSCD + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_STS_CD_EXIST, CICMSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_CLOSE)) {
            B030_03_CHECK_CITACR_CLOSE();
            if (pgmRtn) return;
            B030_02_CHECK_CITAGT_CLOSE();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICMSTS.DATA.CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRBASN);
        IBS.init(SCCGWA, CIRBASO);
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASO);
        R000_TRANS_DATA_TO_TBL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        WS_CPY_FLG = '2';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        T000_REWRITE_CITBAS();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B030_02_CHECK_CITAGT_CLOSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.CI_NO = CICMSTS.DATA.CI_NO;
        T000_OPEN_CITAGT();
        if (pgmRtn) return;
        T000_FETCH_CITAGT();
        if (pgmRtn) return;
        while (WS_AGT_FLG != 'N') {
            IBS.init(SCCGWA, CICPAGT);
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, BPRPRMT);
            BPRPRMT.KEY.TYP = "CIAGT";
            BPRPRMT.KEY.CD = CIRAGT.AGT_TYP;
            BPCPRMR.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPAGT);
            if (CICPAGT.CLO_CTL == '1') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_REFUSE_AGT_CLOSE, CICMSTS.RC);
                Z_RET();
                if (pgmRtn) return;
            } else if (CICPAGT.CLO_CTL == '2') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AUTHOR_AGT_CLOSE, CICMSTS.RC);
                Z_RET();
                if (pgmRtn) return;
            } else if (CICPAGT.CLO_CTL == '3') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_WARRING_AGT_CLOSE, CICMSTS.RC);
                Z_RET();
                if (pgmRtn) return;
            } else if (CICPAGT.CLO_CTL == '4') {
            } else {
            }
            T000_FETCH_CITAGT();
            if (pgmRtn) return;
        }
        T000_CLOSE_CITAGT();
        if (pgmRtn) return;
    }
    public void B030_03_CHECK_CITACR_CLOSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICMSTS.DATA.CI_NO;
        T000_OPEN_CITACR();
        if (pgmRtn) return;
        T000_FETCH_CITACR();
        if (pgmRtn) return;
        while (WS_ACR_FLG != 'N') {
            if (CIRACR.STS == '0') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_CLOSE_ACR, CICMSTS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            T000_FETCH_CITACR();
            if (pgmRtn) return;
        }
        T000_CLOSE_CITACR();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        if (CICMSTS.DATA.STS_CODE.trim().length() == 0) WS_STSCD = 0;
        else WS_STSCD = Short.parseShort(CICMSTS.DATA.STS_CODE);
        CEP.TRC(SCCGWA, WS_STSCD);
        WS_STSCD += 1;
        CEP.TRC(SCCGWA, WS_STSCD);
        if (WS_BAS_STS == null) WS_BAS_STS = "";
        JIBS_tmp_int = WS_BAS_STS.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) WS_BAS_STS += " ";
        if (WS_BAS_STS.substring(WS_STSCD - 1, WS_STSCD + 1 - 1).equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_STS_NOT_EXIST, CICMSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICMSTS.DATA.CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRBASN);
        IBS.init(SCCGWA, CIRBASO);
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASO);
        R000_DEL_DATA_TO_TBL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        WS_CPY_FLG = '2';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        T000_REWRITE_CITBAS();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B070_INQUIRE_PROC() throws IOException,SQLException,Exception {
        if (CICMSTS.DATA.STS_CODE.trim().length() == 0) WS_STSCD = 0;
        else WS_STSCD = Short.parseShort(CICMSTS.DATA.STS_CODE);
        CEP.TRC(SCCGWA, WS_STSCD);
        WS_STSCD += 1;
        CEP.TRC(SCCGWA, WS_STSCD);
        if (WS_BAS_STS == null) WS_BAS_STS = "";
        JIBS_tmp_int = WS_BAS_STS.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) WS_BAS_STS += " ";
        if (WS_BAS_STS.substring(WS_STSCD - 1, WS_STSCD + 1 - 1).equalsIgnoreCase("0")) {
            CEP.TRC(SCCGWA, "STS NOT FOUND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STS_NOT_EXIST);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRCISTS);
        if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_STOP) 
            || CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_DEPOSIT) 
            || CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_DELAY)) {
            CIRCISTS.KEY.CI_NO = CICMSTS.DATA.CI_NO;
            CIRCISTS.KEY.STS_CODE = CICMSTS.DATA.STS_CODE;
            T000_READ_CITCISTS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CIRCISTS.KEY.CI_NO);
        CEP.TRC(SCCGWA, CIRCISTS.KEY.STS_CODE);
        CEP.TRC(SCCGWA, CIRCISTS.REASON);
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B080_BROWSE_PROC() throws IOException,SQLException,Exception {
        B080_01_OUT_TITLE();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 80; WS_I += 1) {
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
        }
    }
    public void B060_CHECK_INF_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        WS_CI_STSW_24 = ' ';
        CIRBAS.KEY.CI_NO = CICMSTS.DATA.CI_NO;
        T000_READ_CITBAS_CI_NO_UPD();
        if (pgmRtn) return;
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(24 - 1, 24 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CLONE(SCCGWA, CIRBAS, CIRBASO);
            if (CIRBAS.CI_TYP == ' ' 
                || CIRBAS.ID_TYPE.trim().length() == 0 
                || CIRBAS.ID_TYPE.equalsIgnoreCase("00000") 
                || CIRBAS.ID_NO.trim().length() == 0 
                || CIRBAS.CI_NM.trim().length() == 0) {
                WS_CI_STSW_24 = 'Y';
            } else {
                IBS.init(SCCGWA, CIRID);
                WS_ID_FLG = ' ';
                CIRID.KEY.CI_NO = CICMSTS.DATA.CI_NO;
                T000_READ_CITID_CI_NO_FIRST();
                if (pgmRtn) return;
                if (WS_ID_FLG == 'Y') {
                    WS_CI_STSW_24 = 'Y';
                } else {
                    IBS.init(SCCGWA, CIRCNT);
                    CIRCNT.KEY.CI_NO = CICMSTS.DATA.CI_NO;
                    T000_READ_CITCNT_CI_FIRST();
                    if (pgmRtn) return;
                    if (WS_CITCNT_FLG == 'N') {
                        WS_CI_STSW_24 = 'Y';
                    } else {
                        IBS.init(SCCGWA, CIRADR);
                        CIRADR.KEY.CI_NO = CICMSTS.DATA.CI_NO;
                        T000_READ_CITADR_CI_FIRST();
                        if (pgmRtn) return;
                        if (WS_CITADR_FLG == 'N') {
                            WS_CI_STSW_24 = 'Y';
                        } else {
                            if (CIRBAS.CI_TYP == '1' 
                                && WS_CI_STSW_24 != 'Y') {
                                R000_CHECK_CI_INF_P();
                                if (pgmRtn) return;
                            }
                        }
                    }
                }
            }
            if (WS_CI_STSW_24 == 'Y') {
            } else {
                if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                JIBS_tmp_int = CIRBAS.STSW.length();
                for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "0" + CIRBAS.STSW.substring(24 + 1 - 1);
                T000_REWRITE_CITBAS();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
                IBS.init(SCCGWA, BPCPNHIS);
                BPCPNHIS.INFO.TX_TYP = 'M';
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
                BPCPNHIS.INFO.CI_NO = CICMSTS.DATA.CI_NO;
                BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCPNHIS.INFO.TX_RMK = "MOD BAS INF";
                BPCPNHIS.INFO.FMT_ID = "CIRBAS";
                BPCPNHIS.INFO.OLD_DAT_PT = CIRBASO;
                BPCPNHIS.INFO.NEW_DAT_PT = CIRBASN;
                S000_CALL_BPZPNHIS();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_CI_INF_P() throws IOException,SQLException,Exception {
        if (WS_CI_STSW_24 != 'Y') {
            IBS.init(SCCGWA, CIRPDM);
            CIRPDM.KEY.CI_NO = CICMSTS.DATA.CI_NO;
            T000_READ_CITPDM();
            if (pgmRtn) return;
            if (CIRPDM.REG_CNTY.trim().length() == 0 
                || CIRPDM.REG_CNTY.equalsIgnoreCase("000")) {
                WS_CI_STSW_24 = 'Y';
            }
            if (CIRPDM.SEX == ' ' 
                || CIRPDM.SEX == '9') {
                WS_CI_STSW_24 = 'Y';
            }
            if (CIRPDM.OCCUP.trim().length() == 0 
                || CIRPDM.OCCUP.equalsIgnoreCase("99991")) {
                WS_CI_STSW_24 = 'Y';
            }
        }
    }
    public void B080_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_STS;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
            R000_DATA_TRANS_TO_MPAG();
            if (pgmRtn) return;
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_STS_LIST);
            SCCMPAG.DATA_LEN = 135;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_TO_TBL() throws IOException,SQLException,Exception {
        if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_STOP)) {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = "010" + CIRBAS.STSW.substring(3);
            CIRBAS.CLOSE_DT = 0;
        } else if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_CLOSE)) {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = "001" + CIRBAS.STSW.substring(3);
            CIRBAS.CLOSE_DT = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, WS_BAS_STS);
        } else if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_DEPOSIT)) {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = CIRBAS.STSW.substring(0, 28 - 1) + "10" + CIRBAS.STSW.substring(28 + 2 - 1);
        } else if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_DELAY)) {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW.substring(28 - 1, 28 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, CIRCISTS);
                CIRCISTS.KEY.CI_NO = CICMSTS.DATA.CI_NO;
                CIRCISTS.KEY.STS_CODE = K_CI_STS_DEPOSIT;
                T000_READ_CITCISTS();
                if (pgmRtn) return;
                if (WS_CISTS_FLG == 'Y') {
                    if (CIRCISTS.REASON.trim().length() > 0) {
                        IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_EXIST_MANUAL_ZDJ, CICMSTS.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                        JIBS_tmp_int = CIRBAS.STSW.length();
                        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                        CIRBAS.STSW = CIRBAS.STSW.substring(0, 28 - 1) + "01" + CIRBAS.STSW.substring(28 + 2 - 1);
                    }
                }
            } else {
                if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                JIBS_tmp_int = CIRBAS.STSW.length();
                for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                CIRBAS.STSW = CIRBAS.STSW.substring(0, 28 - 1) + "01" + CIRBAS.STSW.substring(28 + 2 - 1);
            }
        } else {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = CIRBAS.STSW.substring(0, WS_STSCD - 1) + "1" + CIRBAS.STSW.substring(WS_STSCD + 1 - 1);
        }
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void R000_DEL_DATA_TO_TBL() throws IOException,SQLException,Exception {
        if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_STOP)) {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = "100" + CIRBAS.STSW.substring(3);
        } else if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_CLOSE)) {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = "100" + CIRBAS.STSW.substring(3);
            CIRBAS.CLOSE_DT = 0;
        } else if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_DEPOSIT)) {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = CIRBAS.STSW.substring(0, 28 - 1) + "0" + CIRBAS.STSW.substring(28 + 1 - 1);
        } else if (CICMSTS.DATA.STS_CODE.equalsIgnoreCase(K_CI_STS_DELAY)) {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
                R000_CHK_ZDJ_STS();
                if (pgmRtn) return;
            }
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = CIRBAS.STSW.substring(0, 29 - 1) + "0" + CIRBAS.STSW.substring(29 + 1 - 1);
        } else {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = CIRBAS.STSW.substring(0, WS_STSCD - 1) + "0" + CIRBAS.STSW.substring(WS_STSCD + 1 - 1);
        }
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void R000_CHK_ZDJ_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CICMSTS.DATA.CI_NO;
        WS_MIN_DT = 0;
        T000_STARTBR_CITID();
        if (pgmRtn) return;
        T000_READNEXT_CITID();
        if (pgmRtn) return;
        while (WS_ID_FLG != 'N') {
            CEP.TRC(SCCGWA, "*** ZDJ 1 ***");
            CEP.TRC(SCCGWA, CIRID.EXP_DT);
            CEP.TRC(SCCGWA, WS_MIN_DT);
            if (WS_MIN_DT == 0) {
                WS_MIN_DT = CIRID.EXP_DT;
            } else {
                if (CIRID.EXP_DT < WS_MIN_DT) {
                    WS_MIN_DT = CIRID.EXP_DT;
                }
            }
            T000_READNEXT_CITID();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITID();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRRELN);
        CIRRELN.KEY.CI_NO = CICMSTS.DATA.CI_NO;
        T000_STARTBR_CITRELN();
        if (pgmRtn) return;
        T000_READNEXT_CITRELN();
        if (pgmRtn) return;
        while (WS_RELN_FLG != 'N') {
            CEP.TRC(SCCGWA, "*** ZDJ 2 ***");
            CEP.TRC(SCCGWA, CIRRELN.RCI_ID_EXP);
            CEP.TRC(SCCGWA, WS_MIN_DT);
            if (WS_MIN_DT == 0) {
                WS_MIN_DT = CIRRELN.RCI_ID_EXP;
            } else {
                if (CIRRELN.RCI_ID_EXP < WS_MIN_DT) {
                    WS_MIN_DT = CIRRELN.RCI_ID_EXP;
                }
            }
            T000_READNEXT_CITRELN();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITRELN();
        if (pgmRtn) return;
        if (WS_MIN_DT != 0) {
            CEP.TRC(SCCGWA, "COMP ZDJ DT");
            CEP.TRC(SCCGWA, WS_MIN_DT);
            WS_DATE = WS_MIN_DT;
            WS_I = 3;
            IBS.init(SCCGWA, SCCCLDT);
            if (WS_DATE != 0 
                && WS_DATE < 20990101 
                && WS_DATE > 18010101) {
                S000_GET_DATE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            if (SCCCLDT.DATE2 < SCCGWA.COMM_AREA.AC_DATE 
                && SCCCLDT.DATE2 != 0) {
                if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                JIBS_tmp_int = CIRBAS.STSW.length();
                for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                CIRBAS.STSW = CIRBAS.STSW.substring(0, 28 - 1) + "1" + CIRBAS.STSW.substring(28 + 1 - 1);
                CICMSTS.DATA.STS_CODE = K_CI_STS_DEPOSIT;
                CICMSTS.DATA.STS_CODE = K_CI_STS_DELAY;
            }
        }
    }
    public void R000_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_STS_INFO);
        WS_STS_INFO.WS_CI_NO = CICMSTS.DATA.CI_NO;
        WS_STS_INFO.WS_STS_CODE = CICMSTS.DATA.STS_CODE;
        WS_STS_INFO.WS_REASON = CIRCISTS.REASON;
    }
    public void R000_DATA_TRANS_TO_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_STS_LIST);
        CEP.TRC(SCCGWA, WS_I);
        WS_STSCD = (short) (WS_I - 1);
        CEP.TRC(SCCGWA, WS_STSCD);
        WS_STS_LIST.WS_CI_NOL = CIRBAS.KEY.CI_NO;
        WS_STS_LIST.WS_STS_CODEL = "" + WS_STSCD;
        JIBS_tmp_int = WS_STS_LIST.WS_STS_CODEL.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) WS_STS_LIST.WS_STS_CODEL = "0" + WS_STS_LIST.WS_STS_CODEL;
        if (WS_STS_LIST.WS_STS_CODEL.equalsIgnoreCase(K_CI_STS_STOP) 
            || WS_STS_LIST.WS_STS_CODEL.equalsIgnoreCase(K_CI_STS_DEPOSIT) 
            || WS_STS_LIST.WS_STS_CODEL.equalsIgnoreCase(K_CI_STS_DELAY)) {
            IBS.init(SCCGWA, CIRCISTS);
            CIRCISTS.KEY.CI_NO = WS_STS_LIST.WS_CI_NOL;
            CIRCISTS.KEY.STS_CODE = WS_STS_LIST.WS_STS_CODEL;
            T000_READ_CITCISTS();
            if (pgmRtn) return;
            WS_STS_LIST.WS_REASONL = CIRCISTS.REASON;
        }
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (CICMSTS.FUNC == 'A' 
            || CICMSTS.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        CEP.TRC(SCCGWA, CICMSTS.FUNC);
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        SCCFMT.DATA_PTR = WS_STS_INFO;
        SCCFMT.DATA_LEN = 135;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        CEP.TRC(SCCGWA, WS_STS_INFO);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_GET_DATE() throws IOException,SQLException,Exception {
        SCCCLDT.DATE1 = WS_DATE;
        SCCCLDT.MTHS = (short) WS_I;
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_MSGID.WS_MSG_AP = "SC";
            WS_MSGID.WS_MSG_CODE = SCCCLDT.RC;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_CITCISTS() throws IOException,SQLException,Exception {
        CITCISTS_RD = new DBParm();
        CITCISTS_RD.TableName = "CITCISTS";
        IBS.READ(SCCGWA, CIRCISTS, CITCISTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CISTS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CISTS_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCISTS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITCISTS_UP() throws IOException,SQLException,Exception {
        CITCISTS_RD = new DBParm();
        CITCISTS_RD.TableName = "CITCISTS";
        CITCISTS_RD.upd = true;
        IBS.READ(SCCGWA, CIRCISTS, CITCISTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CISTS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CISTS_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCISTS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITCNT_CI_FIRST() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.where = "CI_NO = :CIRCNT.KEY.CI_NO "
            + "AND CNT_TYPE IN ( '13' , '15' , '16' )";
        CITCNT_RD.fst = true;
        IBS.READ(SCCGWA, CIRCNT, this, CITCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CITCNT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CITCNT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCNT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITADR_CI_FIRST() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        CITADR_RD.where = "CI_NO = :CIRADR.KEY.CI_NO";
        CITADR_RD.fst = true;
        IBS.READ(SCCGWA, CIRADR, this, CITADR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CITADR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CITADR_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITADR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND, CICMSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITID_CI_NO_FIRST() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.where = "CI_NO = :CIRID.KEY.CI_NO "
            + "AND OPEN = 'Y' "
            + "AND ( ID_TYPE = '00000' "
            + "OR ID_TYPE = ' ' "
            + "OR EXP_DT = 0 )";
        CITID_RD.fst = true;
        IBS.READ(SCCGWA, CIRID, this, CITID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ID_FLG = 'Y';
        }
    }
    public void T000_READ_CITPDM() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        IBS.READ(SCCGWA, CIRPDM, CITPDM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_PDM_NOTFND, CICMSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND, CICMSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS_CI_NO_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_INSERT_CITCISTS() throws IOException,SQLException,Exception {
        CITCISTS_RD = new DBParm();
        CITCISTS_RD.TableName = "CITCISTS";
        IBS.WRITE(SCCGWA, CIRCISTS, CITCISTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCISTS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_CITCISTS() throws IOException,SQLException,Exception {
        CITCISTS_RD = new DBParm();
        CITCISTS_RD.TableName = "CITCISTS";
        IBS.DELETE(SCCGWA, CIRCISTS, CITCISTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCISTS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_CITID() throws IOException,SQLException,Exception {
        CITID_BR.rp = new DBParm();
        CITID_BR.rp.TableName = "CITID";
        CITID_BR.rp.where = "CI_NO = :CIRID.KEY.CI_NO "
            + "AND ID_TYPE IN ( '20500' , '23200' , '23300' , '22600' , '21900' , '23900' , '23400' )";
        IBS.STARTBR(SCCGWA, CIRID, this, CITID_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ID_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ID_FLG = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITID";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_CITRELN() throws IOException,SQLException,Exception {
        CITRELN_BR.rp = new DBParm();
        CITRELN_BR.rp.TableName = "CITRELN";
        CITRELN_BR.rp.where = "CI_NO = :CIRRELN.KEY.CI_NO "
            + "AND RCI_IDTYP = '10100' "
            + "AND CIREL_CD IN ( '01' , '10' , '19' )";
        IBS.STARTBR(SCCGWA, CIRRELN, this, CITRELN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RELN_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RELN_FLG = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITRELN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_CITID() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRID, this, CITID_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ID_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ID_FLG = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITID";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_CITRELN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRRELN, this, CITRELN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RELN_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RELN_FLG = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITRELN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_CITID() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITID_BR);
    }
    public void T000_ENDBR_CITRELN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITRELN_BR);
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        if (WS_CPY_FLG == '1') {
            BPCPNHIS.INFO.CI_NO = CIRCISTS.KEY.CI_NO;
            BPCPNHIS.INFO.FMT_ID = K_HIS_CPY1;
            BPCPNHIS.INFO.FMT_ID_LEN = 204;
            BPCPNHIS.INFO.OLD_DAT_PT = CIRCISTO;
            BPCPNHIS.INFO.NEW_DAT_PT = CIRCISTN;
        }
        if (WS_CPY_FLG == '2') {
            BPCPNHIS.INFO.CI_NO = CIRBAS.KEY.CI_NO;
            BPCPNHIS.INFO.FMT_ID = K_HIS_CPY2;
            BPCPNHIS.INFO.FMT_ID_LEN = 568;
            BPCPNHIS.INFO.OLD_DAT_PT = CIRBASO;
            BPCPNHIS.INFO.NEW_DAT_PT = CIRBASN;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
    }
    public void S000_CALL_SCZGJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
        SCCCALL.COMMAREA_PTR = null;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void T000_OPEN_CITAGT() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRAGT, CITAGT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_FETCH_CITAGT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRAGT, this, CITAGT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AGT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AGT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_CLOSE_CITAGT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITAGT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_OPEN_CITACR() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRACR, CITACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_FETCH_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACR_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_CLOSE_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICMSTS.RC);
            Z_RET();
            if (pgmRtn) return;
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
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICMSTS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICMSTS=");
            CEP.TRC(SCCGWA, CICMSTS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
