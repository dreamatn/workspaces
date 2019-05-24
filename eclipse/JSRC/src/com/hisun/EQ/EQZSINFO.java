package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQZSINFO {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm EQTBVT_RD;
    DBParm EQTACT_RD;
    DBParm EQTWDZ_RD;
    DBParm EQTNPBK_RD;
    brParm EQTACT_BR = new brParm();
    brParm EQTNPBK_BR = new brParm();
    brParm EQTWDZ_BR = new brParm();
    boolean pgmRtn = false;
    int K_MAX_ROW = 21;
    int K_MAX_ROW2 = 11;
    int K_MAX_COL = 500;
    String K_OUTPUT_FMT_9 = "EQ504";
    String K_OUTPUT_FMT_2 = "EQ502";
    String K_OUTPUT_FMT_3 = "EQ503";
    String K_OUTPUT_FMT_4 = "EQ510";
    String K_OUTPUT_FMT_5 = "EQ511";
    String K_OUTPUT_FMT_6 = "EQ512";
    char K_AC_STS = 'C';
    char K_ROL_STS = 'R';
    char K_BVT_STS = 'U';
    String K_BSZ_BANKID = "01";
    String K_CCY_CNY = "156";
    String K_HIS_FMT = "EQCSINFO";
    String K_HIS_RMK = "EQ INFORIMATION CHANGE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ACT_FLG = ' ';
    char WS_BVT_FLG = ' ';
    char WS_NPBK_FLG = ' ';
    char WS_WDZ_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_HIS_PRT_FLG = ' ';
    String WS_EQ_TYPE = " ";
    char WS_AC_STS = ' ';
    String WS_PSBK_NO = " ";
    short WS_PRT_LINE = 0;
    int WS_UPT_CNT = 0;
    int WS_UPT_NO = 0;
    int WS_K = 0;
    int WS_TMP_CNT = 0;
    int WS_CNT = 0;
    int WS_CNT0 = 0;
    int WS_CNT1 = 0;
    int WS_CNT11 = 0;
    int WS_CNT12 = 0;
    int WS_CNT2 = 0;
    int WS_CNT21 = 0;
    int WS_CNT22 = 0;
    int WS_CNT3 = 0;
    int WS_CNT31 = 0;
    int WS_CNT32 = 0;
    int WS_UT_INCNT = 0;
    int WS_UT_CHCNT = 0;
    int WS_UT_BOCNT = 0;
    char WS_MOD_FLD = ' ';
    char WS_PRT_TYP = ' ';
    int WS_PRT_DT = 0;
    String WS_PSBK_TYP = " ";
    String WS_PREV_VAL = " ";
    String WS_AFTER_VAL = " ";
    String WS_EQ_CINO = " ";
    String WS_EQ_ACT = " ";
    char WS_LAST_UPT_TYP = ' ';
    String WS_ADD_DTL = " ";
    String WS_TEL_DTL = " ";
    String WS_AC_DTL = " ";
    String WS_WDZ_TYP = " ";
    int WS_NUM = 0;
    String WS_TX_MMO = " ";
    EQZSINFO_WS_TMP_WDZ WS_TMP_WDZ = new EQZSINFO_WS_TMP_WDZ();
    char WS_NORMAL_STS = 'N';
    int WS_DB_STDT = 0;
    int WS_DB_EDDT = 0;
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRACT EQRACT = new EQRACT();
    EQRBVT EQRBVT = new EQRBVT();
    EQRNPBK EQRNPBK = new EQRNPBK();
    EQRWDZ EQRWDZ = new EQRWDZ();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    EQCRACT EQCRACT = new EQCRACT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DDCIMMST DDCIMMST = new DDCIMMST();
    EQCOQ500_OPT_8500 EQCOQ500_OPT_8500 = new EQCOQ500_OPT_8500();
    EQCOQ502_OPT_8502 EQCOQ502_OPT_8502 = new EQCOQ502_OPT_8502();
    EQCOQ503_OPT_8503 EQCOQ503_OPT_8503 = new EQCOQ503_OPT_8503();
    EQCOQ504_OPT_8504 EQCOQ504_OPT_8504 = new EQCOQ504_OPT_8504();
    EQCOQ505_OPT_8505 EQCOQ505_OPT_8505 = new EQCOQ505_OPT_8505();
    EQCOQ510_OPT_8510 EQCOQ510_OPT_8510 = new EQCOQ510_OPT_8510();
    EQCOQ511_OPT_8511 EQCOQ511_OPT_8511 = new EQCOQ511_OPT_8511();
    EQCOQ512_OPT_8512 EQCOQ512_OPT_8512 = new EQCOQ512_OPT_8512();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    CICQACRI CICQACRI = new CICQACRI();
    DCCUCINF DCCUCINF = new DCCUCINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQCSINFO EQCSINFO;
    public void MP(SCCGWA SCCGWA, EQCSINFO EQCSINFO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EQCSINFO = EQCSINFO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQZSINFO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        EQCSINFO.RC.RC_MMO = "EQ";
        EQCSINFO.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (EQCSINFO.DATA.EQ_BKID.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_EQ_TYPE = "" + EQCSINFO.DATA.EQ_TYP;
        JIBS_tmp_int = WS_EQ_TYPE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) WS_EQ_TYPE = "0" + WS_EQ_TYPE;
        if ((!WS_EQ_TYPE.equalsIgnoreCase("1") 
            && !WS_EQ_TYPE.equalsIgnoreCase("2") 
            && !WS_EQ_TYPE.equalsIgnoreCase("3") 
            && !WS_EQ_TYPE.equalsIgnoreCase("4"))) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_TYP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_AC_STS = EQCSINFO.DATA.AC_STS;
        if ((WS_AC_STS != 'N' 
            && WS_AC_STS != 'C' 
            && WS_AC_STS != 'R')) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_AC_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQCSINFO.FUNC);
        if (EQCSINFO.FUNC == 'I') {
            B010_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (EQCSINFO.FUNC == 'B') {
            B020_BROWSE_ALL_PROC();
            if (pgmRtn) return;
        } else if (EQCSINFO.FUNC == 'D') {
            B030_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (EQCSINFO.FUNC == 'U') {
            B040_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (EQCSINFO.FUNC == 'C') {
            B050_CHANGE_PROC();
            if (pgmRtn) return;
        } else if (EQCSINFO.FUNC == 'A') {
            B060_ADD_PROC();
            if (pgmRtn) return;
        } else if (EQCSINFO.FUNC == 'P') {
            B070_ADD_PRINT_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQCRACT.FUNC = 'Q';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        B010_01_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        B010_02_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_ALL_PROC() throws IOException,SQLException,Exception {
        B020_01_CHECK();
        if (pgmRtn) return;
        IBS.init(SCCGWA, EQRACT);
        WS_ACT_FLG = ' ';
        EQRACT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRACT.EQ_ACT = EQCSINFO.DATA.EQ_ACT;
        EQRACT.EQ_CINO = EQCSINFO.DATA.EQ_CINO;
        EQRACT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQRACT.CI_NO = EQCSINFO.DATA.CI_NO;
        EQRACT.EQ_TYP = EQCSINFO.DATA.EQ_TYP;
        EQRACT.AC_STS = EQCSINFO.DATA.AC_STS;
        T000_STARTBR_EQTACT();
        if (pgmRtn) return;
        T000_READNEXT_EQTACT();
        if (pgmRtn) return;
        if (WS_ACT_FLG == 'Y') {
            B020_02_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_ACT_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B020_03_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_EQTACT();
            if (pgmRtn) return;
        }
        T000_ENDBR_EQTACT();
        if (pgmRtn) return;
    }
    public void B030_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        WS_ACT_FLG = ' ';
        EQRACT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRACT.EQ_ACT = EQCSINFO.DATA.EQ_ACT;
        EQRACT.EQ_CINO = EQCSINFO.DATA.EQ_CINO;
        EQRACT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQRACT.CI_NO = EQCSINFO.DATA.CI_NO;
        T001_STARTBR_EQTACT();
        if (pgmRtn) return;
        T000_READNEXT_EQTACT();
        if (pgmRtn) return;
        if (WS_ACT_FLG == 'Y') {
            B030_01_OUT_TITLE();
            if (pgmRtn) return;
        } else {
            if (EQCSINFO.DATA.EQ_ACT.trim().length() > 0) {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQACT_NOTFND);
            }
            if (EQCSINFO.DATA.EQ_CINO.trim().length() > 0) {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQCINO_NOTFND);
            }
            if (EQCSINFO.DATA.EQ_AC.trim().length() > 0) {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND);
            }
            if (EQCSINFO.DATA.CI_NO.trim().length() > 0) {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_CINO_NOT_EXIST);
            }
        }
        while (WS_ACT_FLG != 'N') {
            CEP.TRC(SCCGWA, EQRACT.KEY.EQ_AC);
            IBS.init(SCCGWA, EQRNPBK);
            WS_NPBK_FLG = ' ';
            EQRNPBK.KEY.EQ_BKID = EQRACT.KEY.EQ_BKID;
            EQRNPBK.KEY.EQ_AC = EQRACT.KEY.EQ_AC;
            WS_DB_STDT = EQCSINFO.DATA.START_DT;
            WS_DB_EDDT = EQCSINFO.DATA.FIN_DT;
            T000_STARTBR_EQTNPBK();
            if (pgmRtn) return;
            T000_READNEXT_EQTNPBK();
            if (pgmRtn) return;
            while (WS_NPBK_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                CEP.TRC(SCCGWA, EQRNPBK.KEY.JRN_NO);
                B030_02_OUT_BRW_DATA();
                if (pgmRtn) return;
                T000_READNEXT_EQTNPBK();
                if (pgmRtn) return;
            }
            T000_ENDBR_EQTNPBK();
            if (pgmRtn) return;
            T000_READNEXT_EQTACT();
            if (pgmRtn) return;
        }
        T000_ENDBR_EQTACT();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQCRACT.FUNC = 'R';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        EQRACT.CHG_CNT += 1;
        B040_01_CHECK_INPUT();
        if (pgmRtn) return;
        B040_02_GET_VAL();
        if (pgmRtn) return;
        EQRACT.DIV_AC = EQCSINFO.DATA.DIV_AC;
        EQRACT.EQ_ADDR = EQCSINFO.DATA.EQ_ADDR;
        EQRACT.TEL_NO = EQCSINFO.DATA.TEL_NO;
        EQRACT.REMARK = EQCSINFO.DATA.REMARK;
        if (EQRACT.KEY.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
            EQRACT.EQ_CINO = EQCSINFO.DATA.EQ_CINO;
            EQRACT.EQ_ACT = EQCSINFO.DATA.EQ_ACT;
        }
        EQCRACT.FUNC = 'U';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        WS_TX_MMO = "T013";
        R000_NFIANCE_HIS();
        if (pgmRtn) return;
        if (!EQCSINFO.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID) 
            && WS_UPDATE_FLG == 'Y') {
            IBS.init(SCCGWA, EQRBVT);
            WS_BVT_FLG = ' ';
            EQRBVT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
            EQRBVT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
            EQRBVT.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
            T000_READUP_EQTBVT();
            if (pgmRtn) return;
            WS_UPT_NO = EQRBVT.PT_INCNT + EQRBVT.UT_INCNT + 1;
            EQRBVT.UPT_CNT = EQRBVT.UPT_CNT + 1;
            EQRBVT.UT_INCNT = EQRBVT.UT_INCNT + 1;
            EQRBVT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            EQRBVT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_EQTBVT();
            if (pgmRtn) return;
            B040_03_WRITE_EQTWDZ();
            if (pgmRtn) return;
        }
    }
    public void B050_CHANGE_PROC() throws IOException,SQLException,Exception {
        WS_CNT = 0;
        WS_CNT1 = 0;
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQCRACT.FUNC = 'Q';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        if (EQRACT.AC_STS == K_AC_STS) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_AC_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQRACT.AC_STS == K_ROL_STS) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_AC_ROLLBACK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQRACT.EQ_TYP == '2') {
            WS_PSBK_TYP = "001";
        } else {
            WS_PSBK_TYP = "002";
        }
        WS_PRT_DT = EQRACT.ADD_DT;
        CEP.TRC(SCCGWA, EQCSINFO.DATA.TRAN_FLG);
        if (EQCSINFO.DATA.TRAN_FLG != 'Y') {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            IBS.init(SCCGWA, BPCUBUSE);
            BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUBUSE.TYPE = '0';
            if (WS_PSBK_TYP.equalsIgnoreCase("001")) {
                BPCUBUSE.BV_CODE = "085";
            } else {
                BPCUBUSE.BV_CODE = "086";
            }
            BPCUBUSE.BEG_NO = EQCSINFO.DATA.NPSBK_NO;
            BPCUBUSE.END_NO = EQCSINFO.DATA.NPSBK_NO;
            BPCUBUSE.NUM = 1;
            S000_CALL_BPZUBUSE();
            if (pgmRtn) return;
            IBS.init(SCCGWA, EQRBVT);
            WS_BVT_FLG = ' ';
            EQRBVT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
            EQRBVT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
            EQRBVT.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
            T000_READUP_EQTBVT();
            if (pgmRtn) return;
            if (WS_BVT_FLG == 'Y') {
                if (EQRBVT.PSBK_STS == K_AC_STS) {
                    WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_PSBK_CLOSED;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (EQRBVT.UT_CHCNT == 0) {
                    WS_PRT_TYP = '2';
                } else {
                    WS_PRT_TYP = '3';
                }
                WS_PRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                EQRBVT.CHG_RSN = EQCSINFO.DATA.CHG_RSN;
                EQRBVT.PSBK_STS = K_BVT_STS;
                T000_REWRITE_EQTBVT();
                if (pgmRtn) return;
                R000_CHENGE_DATA();
                if (pgmRtn) return;
                IBS.init(SCCGWA, EQRBVT);
                WS_BVT_FLG = ' ';
                EQRBVT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
                EQRBVT.KEY.PSBK_NO = EQCSINFO.DATA.NPSBK_NO;
                T000_READ_EQTBVT2();
                if (pgmRtn) return;
                if (WS_BVT_FLG == 'Y') {
                    WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BVT_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                R000_WRITE_EQTBVT();
                if (pgmRtn) return;
                WS_MOD_FLD = '6';
                WS_PREV_VAL = EQCSINFO.DATA.PSBK_NO;
                WS_AFTER_VAL = EQCSINFO.DATA.NPSBK_NO;
                WS_TX_MMO = "T012";
                R000_NFIANCE_HIS();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_BVT_NOT_FOUND);
            }
        } else {
            WS_PRT_TYP = '1';
            IBS.init(SCCGWA, EQRBVT);
            WS_BVT_FLG = ' ';
            EQRBVT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
            EQRBVT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
            EQRBVT.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
            T000_READ_EQTBVT();
            if (pgmRtn) return;
            if (WS_BVT_FLG == 'Y') {
                if (EQRBVT.PSBK_STS == K_AC_STS) {
                    WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_PSBK_CLOSED;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_BVT_NOT_FOUND);
            }
        }
        B050_01_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        B050_02_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
        if (WS_PRT_TYP == '1') {
            B050_03_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            B050_05_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        }
        if (WS_PRT_TYP == '2') {
            B050_04_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            B050_05_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        }
    }
    public void B060_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ510_OPT_8510);
        IBS.init(SCCGWA, EQCOQ511_OPT_8511);
        IBS.init(SCCGWA, EQCOQ512_OPT_8512);
        WS_HIS_PRT_FLG = 'Y';
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQCRACT.FUNC = 'Q';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, EQRBVT);
        WS_BVT_FLG = ' ';
        EQRBVT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRBVT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQRBVT.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
        T000_READUP_EQTBVT();
        if (pgmRtn) return;
        if (WS_BVT_FLG == 'Y') {
            if (EQRBVT.PT_INCNT > 4 
                || EQRBVT.PT_CHCNT > 20 
                || EQRBVT.PT_BOCNT > 40 
                || (EQRBVT.PT_INCNT == 4 
                && EQRBVT.UT_INCNT > 0) 
                || (EQRBVT.PT_CHCNT == 20 
                && EQRBVT.UT_CHCNT > 0) 
                || (EQRBVT.PT_BOCNT == 40 
                && EQRBVT.UT_BOCNT > 0)) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_PSBK_ENOUGH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (EQRBVT.UT_INCNT == 0 
                && EQRBVT.UT_CHCNT == 1 
                && EQRBVT.UT_BOCNT == 0) {
                IBS.init(SCCGWA, EQRWDZ);
                WS_WDZ_FLG = ' ';
                EQRWDZ.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
                EQRWDZ.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
                EQRWDZ.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
                EQRWDZ.KEY.UPT_TYP = '2';
                EQRWDZ.PRT_FLG = 'N';
                T000_READ_EQTWDZ();
                if (pgmRtn) return;
                if (EQRWDZ.EQ_TXN_TYPE == '3') {
                    WS_HIS_PRT_FLG = 'N';
                }
            }
            WS_CNT1 = 0;
            WS_CNT2 = 0;
            WS_CNT3 = 0;
            WS_TMP_CNT = 0;
            WS_UPT_NO = 0;
            WS_CNT0 = EQRBVT.UPT_CNT;
            WS_CNT11 = EQRBVT.PT_INCNT;
            WS_CNT12 = EQRBVT.UT_INCNT;
            WS_CNT21 = EQRBVT.PT_CHCNT;
            WS_CNT22 = EQRBVT.UT_CHCNT;
            WS_CNT31 = EQRBVT.PT_BOCNT;
            WS_CNT32 = EQRBVT.UT_BOCNT;
            WS_CNT1 = WS_CNT11 + WS_CNT12;
            WS_CNT2 = WS_CNT21 + WS_CNT22;
            WS_CNT3 = WS_CNT31 + WS_CNT32;
            WS_TMP_CNT = WS_CNT12 + WS_CNT22 + WS_CNT32;
            if (WS_TMP_CNT != WS_CNT0) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_UPTNO_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_END_FLG = 'N';
            CEP.TRC(SCCGWA, WS_CNT12);
            CEP.TRC(SCCGWA, WS_CNT22);
            CEP.TRC(SCCGWA, WS_CNT32);
            if (WS_CNT0 > 0) {
                B020_02_OUT_TITLE();
                if (pgmRtn) return;
            }
            if (WS_CNT12 > 0) {
                if (WS_CNT1 > 4) {
                    WS_TMP_CNT = 4 - WS_CNT11;
                    CEP.TRC(SCCGWA, WS_TMP_CNT);
                    EQRBVT.PT_INCNT = 4;
                    EQRBVT.UPT_CNT = EQRBVT.UPT_CNT - WS_TMP_CNT;
                    EQRBVT.UT_INCNT = EQRBVT.UT_INCNT - WS_TMP_CNT;
                    WS_UPDATE_FLG = 'Y';
                    T000_REWRITE_EQTBVT();
                    if (pgmRtn) return;
                    for (WS_K = 1; WS_K <= WS_TMP_CNT; WS_K += 1) {
                        WS_UPT_NO = WS_CNT11 + WS_K;
                        IBS.init(SCCGWA, EQRWDZ);
                        WS_WDZ_FLG = ' ';
                        EQRWDZ.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
                        EQRWDZ.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
                        EQRWDZ.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
                        EQRWDZ.KEY.UPT_TYP = '1';
                        EQRWDZ.KEY.UPT_NO = WS_UPT_NO;
                        EQRWDZ.PRT_FLG = 'N';
                        T000_READUP_EQTWDZ();
                        if (pgmRtn) return;
                        EQRWDZ.PRT_FLG = 'Y';
                        T000_REWRITE_EQTWDZ();
                        if (pgmRtn) return;
                        B060_01_OUT_BRW_DATA();
                        if (pgmRtn) return;
                    }
                    B060_02_DATA_OUTPUT_FMT();
                    if (pgmRtn) return;
                    WS_END_FLG = 'Y';
                } else {
                    EQRBVT.UT_INCNT = 0;
                    EQRBVT.PT_INCNT = WS_CNT1;
                    EQRBVT.UPT_CNT = EQRBVT.UPT_CNT - WS_CNT12;
                    WS_UPDATE_FLG = 'N';
                    for (WS_K = 1; WS_K <= WS_CNT12; WS_K += 1) {
                        WS_UPT_NO = WS_CNT11 + WS_K;
                        IBS.init(SCCGWA, EQRWDZ);
                        WS_WDZ_FLG = ' ';
                        EQRWDZ.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
                        EQRWDZ.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
                        EQRWDZ.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
                        EQRWDZ.KEY.UPT_TYP = '1';
                        EQRWDZ.KEY.UPT_NO = WS_UPT_NO;
                        EQRWDZ.PRT_FLG = 'N';
                        T000_READUP_EQTWDZ();
                        if (pgmRtn) return;
                        EQRWDZ.PRT_FLG = 'Y';
                        T000_REWRITE_EQTWDZ();
                        if (pgmRtn) return;
                        B060_01_OUT_BRW_DATA();
                        if (pgmRtn) return;
                    }
                    B060_02_DATA_OUTPUT_FMT();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, EQRBVT.UPT_CNT);
            if (WS_CNT22 > 0 
                && WS_END_FLG == 'N') {
                if (WS_CNT2 > 20) {
                    WS_TMP_CNT = 20 - WS_CNT21;
                    CEP.TRC(SCCGWA, WS_TMP_CNT);
                    EQRBVT.PT_CHCNT = 20;
                    EQRBVT.UPT_CNT = EQRBVT.UPT_CNT - WS_TMP_CNT;
                    EQRBVT.UT_CHCNT = EQRBVT.UT_CHCNT - WS_TMP_CNT;
                    WS_UPDATE_FLG = 'Y';
                    T000_REWRITE_EQTBVT();
                    if (pgmRtn) return;
                    for (WS_K = 1; WS_K <= WS_TMP_CNT; WS_K += 1) {
                        WS_UPT_NO = WS_CNT21 + WS_K;
                        IBS.init(SCCGWA, EQRWDZ);
                        WS_WDZ_FLG = ' ';
                        EQRWDZ.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
                        EQRWDZ.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
                        EQRWDZ.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
                        EQRWDZ.KEY.UPT_TYP = '2';
                        EQRWDZ.KEY.UPT_NO = WS_UPT_NO;
                        EQRWDZ.PRT_FLG = 'N';
                        T000_READUP_EQTWDZ();
                        if (pgmRtn) return;
                        EQRWDZ.PRT_FLG = 'Y';
                        T000_REWRITE_EQTWDZ();
                        if (pgmRtn) return;
                        B060_03_OUT_BRW_DATA();
                        if (pgmRtn) return;
                    }
                    B060_04_DATA_OUTPUT_FMT();
                    if (pgmRtn) return;
                    WS_END_FLG = 'Y';
                } else {
                    EQRBVT.UT_CHCNT = 0;
                    EQRBVT.PT_CHCNT = WS_CNT2;
                    EQRBVT.UPT_CNT = EQRBVT.UPT_CNT - WS_CNT22;
                    WS_UPDATE_FLG = 'N';
                    for (WS_K = 1; WS_K <= WS_CNT22; WS_K += 1) {
                        WS_UPT_NO = WS_CNT21 + WS_K;
                        IBS.init(SCCGWA, EQRWDZ);
                        WS_WDZ_FLG = ' ';
                        EQRWDZ.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
                        EQRWDZ.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
                        EQRWDZ.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
                        EQRWDZ.KEY.UPT_TYP = '2';
                        EQRWDZ.KEY.UPT_NO = WS_UPT_NO;
                        EQRWDZ.PRT_FLG = 'N';
                        T000_READUP_EQTWDZ();
                        if (pgmRtn) return;
                        EQRWDZ.PRT_FLG = 'Y';
                        T000_REWRITE_EQTWDZ();
                        if (pgmRtn) return;
                        B060_03_OUT_BRW_DATA();
                        if (pgmRtn) return;
                    }
                    B060_04_DATA_OUTPUT_FMT();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, EQRBVT.UPT_CNT);
            if (WS_CNT32 > 0 
                && WS_END_FLG == 'N') {
                if (WS_CNT3 > 40) {
                    WS_TMP_CNT = 40 - WS_CNT31;
                    CEP.TRC(SCCGWA, WS_TMP_CNT);
                    EQRBVT.PT_BOCNT = 40;
                    EQRBVT.UPT_CNT = EQRBVT.UPT_CNT - WS_TMP_CNT;
                    EQRBVT.UT_BOCNT = EQRBVT.UT_BOCNT - WS_TMP_CNT;
                    WS_UPDATE_FLG = 'Y';
                    T000_REWRITE_EQTBVT();
                    if (pgmRtn) return;
                    for (WS_K = 1; WS_K <= WS_TMP_CNT; WS_K += 1) {
                        WS_UPT_NO = WS_CNT31 + WS_K;
                        IBS.init(SCCGWA, EQRWDZ);
                        WS_WDZ_FLG = ' ';
                        EQRWDZ.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
                        EQRWDZ.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
                        EQRWDZ.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
                        EQRWDZ.KEY.UPT_TYP = '3';
                        EQRWDZ.KEY.UPT_NO = WS_UPT_NO;
                        EQRWDZ.PRT_FLG = 'N';
                        T000_READUP_EQTWDZ();
                        if (pgmRtn) return;
                        EQRWDZ.PRT_FLG = 'Y';
                        T000_REWRITE_EQTWDZ();
                        if (pgmRtn) return;
                        B060_05_OUT_BRW_DATA();
                        if (pgmRtn) return;
                    }
                    B060_06_DATA_OUTPUT_FMT();
                    if (pgmRtn) return;
                    WS_END_FLG = 'Y';
                } else {
                    EQRBVT.UT_BOCNT = 0;
                    EQRBVT.PT_BOCNT = WS_CNT3;
                    EQRBVT.UPT_CNT = EQRBVT.UPT_CNT - WS_CNT32;
                    WS_UPDATE_FLG = 'Y';
                    T000_REWRITE_EQTBVT();
                    if (pgmRtn) return;
                    for (WS_K = 1; WS_K <= WS_CNT32; WS_K += 1) {
                        WS_UPT_NO = WS_CNT31 + WS_K;
                        IBS.init(SCCGWA, EQRWDZ);
                        WS_WDZ_FLG = ' ';
                        EQRWDZ.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
                        EQRWDZ.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
                        EQRWDZ.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
                        EQRWDZ.KEY.UPT_TYP = '3';
                        EQRWDZ.KEY.UPT_NO = WS_UPT_NO;
                        EQRWDZ.PRT_FLG = 'N';
                        T000_READUP_EQTWDZ();
                        if (pgmRtn) return;
                        EQRWDZ.PRT_FLG = 'Y';
                        T000_REWRITE_EQTWDZ();
                        if (pgmRtn) return;
                        B060_05_OUT_BRW_DATA();
                        if (pgmRtn) return;
                    }
                    B060_06_DATA_OUTPUT_FMT();
                    if (pgmRtn) return;
                }
            }
            if (WS_UPDATE_FLG == 'N') {
                T000_REWRITE_EQTBVT();
                if (pgmRtn) return;
            }
            if (WS_HIS_PRT_FLG == 'Y') {
                WS_TX_MMO = "P103";
                R000_NFIANCE_HIS();
                if (pgmRtn) return;
            }
        } else {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_BVT_NOT_FOUND);
        }
    }
    public void B070_ADD_PRINT_PROC() throws IOException,SQLException,Exception {
    }
    public void B010_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ504_OPT_8504);
        IBS.init(SCCGWA, CICCUST);
        CEP.TRC(SCCGWA, EQRACT.CI_NO);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCOQ504_OPT_8504.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
            EQCOQ504_OPT_8504.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            EQCOQ504_OPT_8504.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READ_BV_FIRST();
        if (pgmRtn) return;
        EQCOQ504_OPT_8504.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCOQ504_OPT_8504.EQ_AC = EQRACT.KEY.EQ_AC;
        EQCOQ504_OPT_8504.CI_NO = EQRACT.CI_NO;
        EQCOQ504_OPT_8504.EQ_ACT = EQRACT.EQ_ACT;
        EQCOQ504_OPT_8504.EQ_CINO = EQRACT.EQ_CINO;
        EQCOQ504_OPT_8504.PSBK_NO = WS_PSBK_NO;
        EQCOQ504_OPT_8504.DIV_AC = EQRACT.DIV_AC;
        EQCOQ504_OPT_8504.EQ_ADDR = EQRACT.EQ_ADDR;
        EQCOQ504_OPT_8504.TEL_NO = EQRACT.TEL_NO;
        EQCOQ504_OPT_8504.PRT_LINE = WS_PRT_LINE;
        EQCOQ504_OPT_8504.UPT_CNT = WS_UPT_CNT;
        EQCOQ504_OPT_8504.REMARK = EQRACT.REMARK;
    }
    public void B010_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = EQCOQ504_OPT_8504;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_01_CHECK() throws IOException,SQLException,Exception {
        if (EQCSINFO.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = EQCSINFO.DATA.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.RC.RC_CODE != 0) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (EQCSINFO.DATA.EQ_AC.trim().length() > 0) {
            IBS.init(SCCGWA, EQRACT);
            EQRACT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
            T000_READ_EQTACT_AC();
            if (pgmRtn) return;
            if (WS_ACT_FLG == 'N') {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (EQCSINFO.DATA.EQ_ACT.trim().length() > 0) {
            IBS.init(SCCGWA, EQRACT);
            EQRACT.EQ_ACT = EQCSINFO.DATA.EQ_ACT;
            T000_READ_EQTACT_ACT();
            if (pgmRtn) return;
            if (WS_ACT_FLG == 'N') {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_EQACT_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (EQCSINFO.DATA.EQ_CINO.trim().length() > 0) {
            IBS.init(SCCGWA, EQRACT);
            EQRACT.EQ_CINO = EQCSINFO.DATA.EQ_CINO;
            T000_READ_EQTACT_CINO();
            if (pgmRtn) return;
            if (WS_ACT_FLG == 'N') {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_EQCINO_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_02_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_03_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ500_OPT_8500);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCOQ500_OPT_8500.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
            EQCOQ500_OPT_8500.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READ_BV_FIRST();
        if (pgmRtn) return;
        EQCOQ500_OPT_8500.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCOQ500_OPT_8500.EQ_AC = EQRACT.KEY.EQ_AC;
        EQCOQ500_OPT_8500.CI_NO = EQRACT.CI_NO;
        EQCOQ500_OPT_8500.EQ_ACT = EQRACT.EQ_ACT;
        EQCOQ500_OPT_8500.EQ_CINO = EQRACT.EQ_CINO;
        EQCOQ500_OPT_8500.PSBK_NO = WS_PSBK_NO;
        EQCOQ500_OPT_8500.DIV_AC = EQRACT.DIV_AC;
        EQCOQ500_OPT_8500.PRT_LINE = WS_PRT_LINE;
        EQCOQ500_OPT_8500.UPT_CNT = WS_UPT_CNT;
        EQCOQ500_OPT_8500.AC_STS = EQRACT.AC_STS;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCOQ500_OPT_8500);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW2;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ505_OPT_8505);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCOQ505_OPT_8505.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
            EQCOQ505_OPT_8505.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            EQCOQ505_OPT_8505.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        EQCOQ505_OPT_8505.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCOQ505_OPT_8505.EQ_ACT = EQRACT.EQ_ACT;
        EQCOQ505_OPT_8505.EQ_CINO = EQRACT.EQ_CINO;
        EQCOQ505_OPT_8505.EQ_AC = EQRACT.KEY.EQ_AC;
        EQCOQ505_OPT_8505.TXN_DT = EQRNPBK.TXN_DATE;
        EQCOQ505_OPT_8505.UPT_NO = EQRNPBK.KEY.UPT_NO;
        EQCOQ505_OPT_8505.MOD_FLD = "" + EQRNPBK.MOD_FLD;
        JIBS_tmp_int = EQCOQ505_OPT_8505.MOD_FLD.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) EQCOQ505_OPT_8505.MOD_FLD = "0" + EQCOQ505_OPT_8505.MOD_FLD;
        EQCOQ505_OPT_8505.PREV_VAL = EQRNPBK.PREV_VAL;
        EQCOQ505_OPT_8505.AFT_VAL = EQRNPBK.AFTER_VAL;
        EQCOQ505_OPT_8505.UPT_TLR = EQRNPBK.UPDTBL_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCOQ505_OPT_8505);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (EQRACT.AC_STS == K_AC_STS) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_AC_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQRACT.AC_STS == K_ROL_STS) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_AC_ROLLBACK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQRACT.KEY.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
            if (EQRACT.EQ_CINO.equalsIgnoreCase(EQCSINFO.DATA.EQ_CINO) 
                && EQRACT.EQ_ACT.equalsIgnoreCase(EQCSINFO.DATA.EQ_ACT) 
                && EQRACT.DIV_AC.equalsIgnoreCase(EQCSINFO.DATA.DIV_AC) 
                && EQRACT.EQ_ADDR.equalsIgnoreCase(EQCSINFO.DATA.EQ_ADDR) 
                && EQRACT.TEL_NO.equalsIgnoreCase(EQCSINFO.DATA.TEL_NO) 
                && EQRACT.REMARK.equalsIgnoreCase(EQCSINFO.DATA.REMARK)) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DATA_NO_CHAGE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!EQRACT.EQ_CINO.equalsIgnoreCase(EQCSINFO.DATA.EQ_CINO) 
                || !EQRACT.EQ_ACT.equalsIgnoreCase(EQCSINFO.DATA.EQ_ACT)) {
                WS_EQ_CINO = EQRACT.EQ_CINO;
                WS_EQ_ACT = EQRACT.EQ_ACT;
                if (!WS_EQ_CINO.equalsIgnoreCase(EQCSINFO.DATA.EQ_CINO)) {
                    IBS.init(SCCGWA, EQRACT);
                    WS_ACT_FLG = ' ';
                    EQRACT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
                    EQRACT.EQ_CINO = EQCSINFO.DATA.EQ_CINO;
                    EQRACT.AC_STS = 'N';
                    T000_READ_EQTACT_EQCINO();
                    if (pgmRtn) return;
                    if (WS_ACT_FLG == 'Y') {
                        WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_EQCINO_EXIST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (!WS_EQ_ACT.equalsIgnoreCase(EQCSINFO.DATA.EQ_ACT)) {
                    IBS.init(SCCGWA, EQRACT);
                    WS_ACT_FLG = ' ';
                    EQRACT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
                    EQRACT.EQ_ACT = EQCSINFO.DATA.EQ_ACT;
                    EQRACT.AC_STS = 'N';
                    T000_READ_EQTACT_EQACT();
                    if (pgmRtn) return;
                    if (WS_ACT_FLG == 'Y') {
                        WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_EQACT_EXIST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                IBS.init(SCCGWA, EQRACT);
                IBS.init(SCCGWA, EQCRACT);
                EQRACT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
                EQRACT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
                EQCRACT.FUNC = 'R';
                S000_CALL_EQZRACT();
                if (pgmRtn) return;
            }
        } else {
            if (!EQRACT.EQ_ACT.equalsIgnoreCase(EQCSINFO.DATA.EQ_ACT) 
                || !EQRACT.EQ_CINO.equalsIgnoreCase(EQCSINFO.DATA.EQ_CINO)) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_EQIN_CHANGED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (EQRACT.DIV_AC.equalsIgnoreCase(EQCSINFO.DATA.DIV_AC) 
                && EQRACT.EQ_ADDR.equalsIgnoreCase(EQCSINFO.DATA.EQ_ADDR) 
                && EQRACT.TEL_NO.equalsIgnoreCase(EQCSINFO.DATA.TEL_NO) 
                && EQRACT.REMARK.equalsIgnoreCase(EQCSINFO.DATA.REMARK)) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DATA_NO_CHAGE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = EQCSINFO.DATA.DIV_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DIVAC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = EQCSINFO.DATA.DIV_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
            if (DDCIMMST.DATA.AC_STS == 'C') {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DDAC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            if (DDCIMMST.DATA.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DDAC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCIMMST.DATA.AC_TYPE == 'B') {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DDAC_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = EQCSINFO.DATA.DIV_AC;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == '0' 
                || DCCUCINF.CARD_STS == '1' 
                || DCCUCINF.CARD_STS == 'C') {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DDAC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = EQCSINFO.DATA.DIV_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(EQCSINFO.DATA.CI_NO)) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DIV_CINO_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_02_GET_VAL() throws IOException,SQLException,Exception {
        WS_ADD_DTL = " ";
        WS_TEL_DTL = " ";
        WS_AC_DTL = " ";
        WS_CNT = 0;
        WS_UPDATE_FLG = 'N';
        WS_WDZ_TYP = "000";
        if (!EQRACT.EQ_ACT.equalsIgnoreCase(EQCSINFO.DATA.EQ_ACT)) {
            WS_CNT += 1;
            WS_MOD_FLD = '1';
            WS_PREV_VAL = EQRACT.EQ_ACT;
            WS_AFTER_VAL = EQCSINFO.DATA.EQ_ACT;
            R000_WRITE_EQTNPBK();
            if (pgmRtn) return;
        }
        if (!EQRACT.EQ_CINO.equalsIgnoreCase(EQCSINFO.DATA.EQ_CINO)) {
            WS_CNT += 1;
            WS_MOD_FLD = '2';
            WS_PREV_VAL = EQRACT.EQ_CINO;
            WS_AFTER_VAL = EQCSINFO.DATA.EQ_CINO;
            R000_WRITE_EQTNPBK();
            if (pgmRtn) return;
        }
        if (!EQRACT.REMARK.equalsIgnoreCase(EQCSINFO.DATA.REMARK)) {
            WS_CNT += 1;
            WS_MOD_FLD = 'A';
            WS_PREV_VAL = EQRACT.REMARK;
            WS_AFTER_VAL = EQCSINFO.DATA.REMARK;
            R000_WRITE_EQTNPBK();
            if (pgmRtn) return;
        }
        if (!EQRACT.EQ_ADDR.equalsIgnoreCase(EQCSINFO.DATA.EQ_ADDR)) {
            WS_UPDATE_FLG = 'Y';
            if (WS_WDZ_TYP == null) WS_WDZ_TYP = "";
            JIBS_tmp_int = WS_WDZ_TYP.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_WDZ_TYP += " ";
            WS_WDZ_TYP = "1" + WS_WDZ_TYP.substring(1);
            WS_CNT += 1;
            WS_MOD_FLD = '5';
            WS_PREV_VAL = EQRACT.EQ_ADDR;
            WS_AFTER_VAL = EQCSINFO.DATA.EQ_ADDR;
            WS_ADD_DTL = EQCSINFO.DATA.EQ_ADDR;
            R000_WRITE_EQTNPBK();
            if (pgmRtn) return;
        }
        if (!EQRACT.TEL_NO.equalsIgnoreCase(EQCSINFO.DATA.TEL_NO)) {
            WS_UPDATE_FLG = 'Y';
            if (WS_WDZ_TYP == null) WS_WDZ_TYP = "";
            JIBS_tmp_int = WS_WDZ_TYP.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_WDZ_TYP += " ";
            WS_WDZ_TYP = WS_WDZ_TYP.substring(0, 2 - 1) + "1" + WS_WDZ_TYP.substring(2 + 1 - 1);
            WS_CNT += 1;
            WS_MOD_FLD = '4';
            WS_PREV_VAL = EQRACT.TEL_NO;
            WS_AFTER_VAL = EQCSINFO.DATA.TEL_NO;
            WS_TEL_DTL = EQCSINFO.DATA.TEL_NO;
            R000_WRITE_EQTNPBK();
            if (pgmRtn) return;
        }
        if (!EQRACT.DIV_AC.equalsIgnoreCase(EQCSINFO.DATA.DIV_AC)) {
            WS_UPDATE_FLG = 'Y';
            if (WS_WDZ_TYP == null) WS_WDZ_TYP = "";
            JIBS_tmp_int = WS_WDZ_TYP.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_WDZ_TYP += " ";
            WS_WDZ_TYP = WS_WDZ_TYP.substring(0, 3 - 1) + "1" + WS_WDZ_TYP.substring(3 + 1 - 1);
            WS_CNT += 1;
            WS_MOD_FLD = '3';
            WS_PREV_VAL = EQRACT.DIV_AC;
            WS_AFTER_VAL = EQCSINFO.DATA.DIV_AC;
            WS_AC_DTL = EQCSINFO.DATA.DIV_AC;
            R000_WRITE_EQTNPBK();
            if (pgmRtn) return;
        }
    }
    public void B040_03_WRITE_EQTWDZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRWDZ);
        EQRWDZ.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRWDZ.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQRWDZ.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
        EQRWDZ.KEY.UPT_TYP = '1';
        EQRWDZ.KEY.UPT_NO = WS_UPT_NO;
        EQRWDZ.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRWDZ.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.TXN_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = EQRWDZ.TXN_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) EQRWDZ.TXN_CODE = "0" + EQRWDZ.TXN_CODE;
        EQRWDZ.TXN_MMO = "088";
        EQRWDZ.CCY = K_CCY_CNY;
        EQRWDZ.CCY_TYPE = '1';
        EQRWDZ.EQ_TXN_TYPE = '7';
        EQRWDZ.TXN_QTY = 0;
        EQRWDZ.TXN_PRICE = 0;
        EQRWDZ.TXN_AMT = 0;
        EQRWDZ.BAL_QTY = 0;
        EQRWDZ.ADD_DTL = WS_ADD_DTL;
        EQRWDZ.TEL_DTL = WS_TEL_DTL;
        EQRWDZ.AC_DTL = WS_AC_DTL;
        EQRWDZ.PRT_FLG = 'N';
        if (EQRWDZ.REMARK == null) EQRWDZ.REMARK = "";
        JIBS_tmp_int = EQRWDZ.REMARK.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) EQRWDZ.REMARK += " ";
        if (WS_WDZ_TYP == null) WS_WDZ_TYP = "";
        JIBS_tmp_int = WS_WDZ_TYP.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) WS_WDZ_TYP += " ";
        EQRWDZ.REMARK = WS_WDZ_TYP + EQRWDZ.REMARK.substring(3);
        EQRWDZ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRWDZ.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRWDZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRWDZ.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRWDZ.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRWDZ.TS = "0" + EQRWDZ.TS;
        T000_WRITE_EQTWDZ();
        if (pgmRtn) return;
    }
    public void B050_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ502_OPT_8502);
        IBS.init(SCCGWA, CICCUST);
        CEP.TRC(SCCGWA, EQRACT.CI_NO);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCOQ502_OPT_8502.PRT_NAME = CICCUST.O_DATA.O_CI_NM;
            EQCOQ502_OPT_8502.PRT_ID = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        EQCOQ502_OPT_8502.PRT_TYP = WS_PRT_TYP;
        EQCOQ502_OPT_8502.PSBK_TYP = WS_PSBK_TYP;
        EQCOQ502_OPT_8502.PRT_AC = EQCSINFO.DATA.EQ_AC;
        EQCOQ502_OPT_8502.PRT_ADD = EQRACT.EQ_ADDR;
        EQCOQ502_OPT_8502.PRT_DT = WS_PRT_DT;
    }
    public void B050_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_2;
        SCCFMT.DATA_PTR = EQCOQ502_OPT_8502;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_03_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ503_OPT_8503);
        EQCOQ503_OPT_8503.PRT_CNT = 1;
        EQCOQ503_OPT_8503.PRT_DT1 = WS_PRT_DT;
        EQCOQ503_OPT_8503.PRT_INF = '1';
        EQCOQ503_OPT_8503.PRT_FSE = EQRACT.EQ_QTY;
        EQCOQ503_OPT_8503.PRT_BAL = EQRACT.EQ_QTY;
        EQCOQ503_OPT_8503.PRT_BR = EQRACT.ADD_BR;
        EQCOQ503_OPT_8503.PRT_TLR = EQRACT.ADD_TLR;
    }
    public void B050_04_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ503_OPT_8503);
        EQCOQ503_OPT_8503.PRT_CNT = 1;
        EQCOQ503_OPT_8503.PRT_DT1 = WS_PRT_DT;
        EQCOQ503_OPT_8503.PRT_INF = '7';
        EQCOQ503_OPT_8503.PRT_FSE = 0;
        EQCOQ503_OPT_8503.PRT_BAL = EQRACT.EQ_QTY;
        EQCOQ503_OPT_8503.PRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQCOQ503_OPT_8503.PRT_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B050_05_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_3;
        SCCFMT.DATA_PTR = EQCOQ503_OPT_8503;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_01_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        EQCOQ510_OPT_8510.CYCLE[WS_K-1].UPT_CNT1 = EQRWDZ.KEY.UPT_NO;
        EQCOQ510_OPT_8510.CYCLE[WS_K-1].PRT_DT1 = EQRWDZ.TXN_DATE;
        if (EQRWDZ.REMARK == null) EQRWDZ.REMARK = "";
        JIBS_tmp_int = EQRWDZ.REMARK.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) EQRWDZ.REMARK += " ";
        EQCOQ510_OPT_8510.CYCLE[WS_K-1].PRT_TYP = EQRWDZ.REMARK.substring(0, 3);
        EQCOQ510_OPT_8510.CYCLE[WS_K-1].PRT_DTL1 = EQRWDZ.ADD_DTL;
        EQCOQ510_OPT_8510.CYCLE[WS_K-1].PRT_DTL2 = EQRWDZ.TEL_DTL;
        EQCOQ510_OPT_8510.CYCLE[WS_K-1].PRT_DTL3 = EQRWDZ.AC_DTL;
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_4;
        SCCFMT.DATA_PTR = EQCOQ510_OPT_8510;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_03_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        EQCOQ511_OPT_8511.CYCLE[WS_K-1].UPT_CNT2 = EQRWDZ.KEY.UPT_NO;
        EQCOQ511_OPT_8511.CYCLE[WS_K-1].PRT_DT2 = EQRWDZ.TXN_DATE;
        EQCOQ511_OPT_8511.CYCLE[WS_K-1].PRT_INF2 = EQRWDZ.EQ_TXN_TYPE;
        EQCOQ511_OPT_8511.CYCLE[WS_K-1].PRT_FSE = EQRWDZ.TXN_QTY;
        EQCOQ511_OPT_8511.CYCLE[WS_K-1].PRT_BAL = EQRWDZ.BAL_QTY;
        EQCOQ511_OPT_8511.CYCLE[WS_K-1].PRT_BR = EQRWDZ.OWNER_BK;
        EQCOQ511_OPT_8511.CYCLE[WS_K-1].PRT_TLR1 = EQRWDZ.CRT_TLR;
    }
    public void B060_04_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_5;
        SCCFMT.DATA_PTR = EQCOQ511_OPT_8511;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_05_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        WS_NUM = 0;
        EQCOQ512_OPT_8512.CYCLE[WS_K-1].UPT_CNT3 = EQRWDZ.KEY.UPT_NO;
        EQCOQ512_OPT_8512.CYCLE[WS_K-1].PRT_DT3 = EQRWDZ.TXN_DATE;
        WS_NUM = EQRWDZ.TXN_DATE;
        JIBS_tmp_str[0] = "" + WS_NUM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) EQCOQ512_OPT_8512.CYCLE[WS_K-1].PRT_YEA = 0;
        else EQCOQ512_OPT_8512.CYCLE[WS_K-1].PRT_YEA = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        EQCOQ512_OPT_8512.CYCLE[WS_K-1].PRT_PCT = EQRWDZ.CAL_PCT;
        EQCOQ512_OPT_8512.CYCLE[WS_K-1].PRT_AMT = EQRWDZ.BAL_QTY;
        EQCOQ512_OPT_8512.CYCLE[WS_K-1].PRT_AC = EQRACT.DIV_AC;
        EQCOQ512_OPT_8512.CYCLE[WS_K-1].PRT_TLR2 = EQRWDZ.CRT_TLR;
    }
    public void B060_06_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_6;
        SCCFMT.DATA_PTR = EQCOQ512_OPT_8512;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHENGE_DATA() throws IOException,SQLException,Exception {
        WS_UPT_CNT = EQRBVT.UPT_CNT;
        WS_UT_INCNT = EQRBVT.UT_INCNT;
        WS_UT_CHCNT = EQRBVT.UT_CHCNT;
        WS_UT_BOCNT = EQRBVT.UT_BOCNT;
        if (WS_UT_INCNT > 0 
            || WS_UT_CHCNT > 0 
            || WS_UT_BOCNT > 0) {
            T000_STARTBR_EQTWDZ();
            if (pgmRtn) return;
            T000_READNEXT_EQTWDZ();
            if (pgmRtn) return;
            while (WS_WDZ_FLG != 'N') {
                CEP.TRC(SCCGWA, WS_CNT);
                WS_CNT += 1;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_UPT_TYP = EQRWDZ.KEY.UPT_TYP;
                WS_LAST_UPT_TYP = EQRWDZ.KEY.UPT_TYP;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_UPT_NO = WS_CNT1;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_TXN_DATE = EQRWDZ.TXN_DATE;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_TXN_CODE = EQRWDZ.TXN_CODE;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_EQ_TXN_TYPE = EQRWDZ.EQ_TXN_TYPE;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_TXN_QTY = EQRWDZ.TXN_QTY;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_TXN_PRICE = EQRWDZ.TXN_PRICE;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_TXN_AMT = EQRWDZ.TXN_AMT;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_CAL_PCT = EQRWDZ.CAL_PCT;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_BAL_QTY = EQRWDZ.BAL_QTY;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_ADD_DTL = EQRWDZ.ADD_DTL;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_TEL_DTL = EQRWDZ.TEL_DTL;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_AC_DTL = EQRWDZ.AC_DTL;
                if (EQRWDZ.REMARK == null) EQRWDZ.REMARK = "";
                JIBS_tmp_int = EQRWDZ.REMARK.length();
                for (int i=0;i<120-JIBS_tmp_int;i++) EQRWDZ.REMARK += " ";
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_CHANGE_TYP = EQRWDZ.REMARK.substring(0, 3);
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_OWNER_BK = EQRWDZ.OWNER_BK;
                WS_TMP_WDZ.WS_TMP_WDZ1[WS_CNT-1].WS_TMP_CRT_TLR = EQRWDZ.CRT_TLR;
                T000_READNEXT_EQTWDZ();
                if (pgmRtn) return;
                if (EQRWDZ.KEY.UPT_TYP != WS_LAST_UPT_TYP 
                    && EQRWDZ.KEY.UPT_TYP != ' ') {
                    WS_CNT1 = 0;
                }
            }
            T000_ENDBR_EQTWDZ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT);
            for (WS_K = 1; WS_K <= WS_CNT 
                && WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_UPT_TYP != ' '; WS_K += 1) {
                R000_WRITE_EQTWDZ1();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_NFIANCE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = EQCSINFO.DATA.EQ_AC;
        BPCPNHIS.INFO.FMT_ID = K_HIS_FMT;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = EQCSINFO.DATA.CI_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.CCY = "156";
        BPCPNHIS.INFO.CCY_TYPE = '1';
        BPCPNHIS.INFO.TX_TYP_CD = WS_TX_MMO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_WRITE_EQTWDZ1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRWDZ);
        EQRWDZ.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRWDZ.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQRWDZ.KEY.PSBK_NO = EQCSINFO.DATA.NPSBK_NO;
        EQRWDZ.KEY.UPT_TYP = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_UPT_TYP;
        EQRWDZ.KEY.UPT_NO = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_UPT_NO;
        EQRWDZ.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRWDZ.TXN_DATE = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_TXN_DATE;
        EQRWDZ.TXN_CODE = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_TXN_CODE;
        EQRWDZ.TXN_MMO = "088";
        EQRWDZ.CCY = K_CCY_CNY;
        EQRWDZ.CCY_TYPE = '1';
        EQRWDZ.EQ_TXN_TYPE = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_EQ_TXN_TYPE;
        EQRWDZ.TXN_QTY = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_TXN_QTY;
        EQRWDZ.TXN_PRICE = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_TXN_PRICE;
        EQRWDZ.TXN_AMT = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_TXN_AMT;
        EQRWDZ.CAL_PCT = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_CAL_PCT;
        EQRWDZ.BAL_QTY = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_BAL_QTY;
        EQRWDZ.ADD_DTL = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_ADD_DTL;
        EQRWDZ.TEL_DTL = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_TEL_DTL;
        EQRWDZ.AC_DTL = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_AC_DTL;
        EQRWDZ.PRT_FLG = 'N';
        if (EQRWDZ.REMARK == null) EQRWDZ.REMARK = "";
        JIBS_tmp_int = EQRWDZ.REMARK.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) EQRWDZ.REMARK += " ";
        if (WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_CHANGE_TYP == null) WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_CHANGE_TYP = "";
        JIBS_tmp_int = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_CHANGE_TYP.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_CHANGE_TYP += " ";
        EQRWDZ.REMARK = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_CHANGE_TYP + EQRWDZ.REMARK.substring(3);
        EQRWDZ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.CRT_TLR = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_CRT_TLR;
        EQRWDZ.OWNER_BK = WS_TMP_WDZ.WS_TMP_WDZ1[WS_K-1].WS_TMP_OWNER_BK;
        EQRWDZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRWDZ.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRWDZ.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRWDZ.TS = "0" + EQRWDZ.TS;
        T000_WRITE_EQTWDZ();
        if (pgmRtn) return;
    }
    public void R000_WRITE_EQTWDZ2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRWDZ);
        EQRWDZ.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRWDZ.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQRWDZ.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
        EQRWDZ.KEY.UPT_TYP = '1';
        EQRWDZ.KEY.UPT_NO = EQRBVT.UT_INCNT;
        EQRWDZ.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRWDZ.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.TXN_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = EQRWDZ.TXN_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) EQRWDZ.TXN_CODE = "0" + EQRWDZ.TXN_CODE;
        EQRWDZ.TXN_MMO = "088";
        EQRWDZ.CCY = K_CCY_CNY;
        EQRWDZ.CCY_TYPE = '1';
        EQRWDZ.EQ_TXN_TYPE = '7';
        EQRWDZ.TXN_QTY = 0;
        EQRWDZ.TXN_PRICE = 0;
        EQRWDZ.TXN_AMT = 0;
        EQRWDZ.BAL_QTY = 0;
        EQRWDZ.ADD_DTL = " ";
        EQRWDZ.TEL_DTL = " ";
        EQRWDZ.AC_DTL = " ";
        EQRWDZ.PRT_FLG = ' ';
        EQRWDZ.REMARK = " ";
        EQRWDZ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRWDZ.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRWDZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRWDZ.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRWDZ.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRWDZ.TS = "0" + EQRWDZ.TS;
        T000_WRITE_EQTWDZ();
        if (pgmRtn) return;
    }
    public void R000_WRITE_EQTBVT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRBVT);
        EQRBVT.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRBVT.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQRBVT.KEY.PSBK_NO = EQCSINFO.DATA.NPSBK_NO;
        EQRBVT.PSBK_STS = 'N';
        EQRBVT.PSBK_TYP = "001";
        EQRBVT.PSBK_SEQ = 0;
        EQRBVT.PRT_LINE = 0;
        EQRBVT.UPT_CNT = WS_UPT_CNT;
        EQRBVT.UPT_TYP = ' ';
        EQRBVT.PT_INCNT = 0;
        if (WS_PRT_TYP == '2') {
            EQRBVT.PT_CHCNT = 1;
        } else {
            EQRBVT.PT_CHCNT = 0;
        }
        EQRBVT.PT_BOCNT = 0;
        EQRBVT.UT_INCNT = WS_UT_INCNT;
        EQRBVT.UT_CHCNT = WS_UT_CHCNT;
        EQRBVT.UT_BOCNT = WS_UT_BOCNT;
        EQRBVT.UPT_STR_NO = 0;
        EQRBVT.UPT_LAST_NO = 0;
        EQRBVT.LAST_PB_CCY = "" + 0;
        JIBS_tmp_int = EQRBVT.LAST_PB_CCY.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) EQRBVT.LAST_PB_CCY = "0" + EQRBVT.LAST_PB_CCY;
        EQRBVT.PAY_TYPE = ' ';
        EQRBVT.CI_PSWD_FLG = ' ';
        EQRBVT.PAY_PSWD = " ";
        EQRBVT.PSWD_ERR_NUM = 0;
        EQRBVT.PSWD_ERR_DATE = 0;
        EQRBVT.PSBK_RANGE = ' ';
        EQRBVT.CHG_RSN = EQCSINFO.DATA.CHG_RSN;
        EQRBVT.REMARK = EQCSINFO.DATA.REMARK;
        EQRBVT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRBVT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRBVT.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRBVT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRBVT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRBVT.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRBVT.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRBVT.TS = "0" + EQRBVT.TS;
        T000_WRITE_EQTBVT();
        if (pgmRtn) return;
    }
    public void R000_WRITE_EQTNPBK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRNPBK);
        EQRNPBK.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRNPBK.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQRNPBK.KEY.UPT_NO = EQRACT.CHG_CNT;
        EQRNPBK.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRNPBK.KEY.SEQ = (short) WS_CNT;
        EQRNPBK.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRNPBK.MOD_FLD = WS_MOD_FLD;
        EQRNPBK.PREV_VAL = WS_PREV_VAL;
        EQRNPBK.AFTER_VAL = WS_AFTER_VAL;
        EQRNPBK.REMARK = " ";
        EQRNPBK.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRNPBK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRNPBK.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRNPBK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRNPBK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRNPBK.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRNPBK.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRNPBK.TS = "0" + EQRNPBK.TS;
        T000_WRITE_EQTNPBK();
        if (pgmRtn) return;
    }
    public void T000_READ_BV_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRBVT);
        WS_BVT_FLG = ' ';
        EQRBVT.KEY.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQRBVT.KEY.EQ_AC = EQRACT.KEY.EQ_AC;
        T000_READ_EQTBVT1();
        if (pgmRtn) return;
        if (WS_BVT_FLG == 'Y') {
            WS_PSBK_NO = EQRBVT.KEY.PSBK_NO;
            WS_PRT_LINE = (short) (EQRBVT.PT_INCNT + EQRBVT.PT_CHCNT + EQRBVT.PT_BOCNT);
            WS_UPT_CNT = EQRBVT.UPT_CNT;
        } else {
            WS_PSBK_NO = " ";
            WS_PRT_LINE = 0;
            WS_UPT_CNT = 0;
        }
    }
    public void T000_READ_EQTBVT() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.where = "EQ_BKID = :EQRBVT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRBVT.KEY.EQ_AC "
            + "AND PSBK_NO = :EQRBVT.KEY.PSBK_NO";
        IBS.READ(SCCGWA, EQRBVT, this, EQTBVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BVT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTBVT1() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.where = "EQ_BKID = :EQRBVT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRBVT.KEY.EQ_AC "
            + "AND PSBK_STS = :WS_NORMAL_STS";
        EQTBVT_RD.fst = true;
        EQTBVT_RD.order = "EQ_BKID,EQ_AC,PSBK_NO";
        IBS.READ(SCCGWA, EQRBVT, this, EQTBVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BVT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTBVT2() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.where = "EQ_BKID = :EQRBVT.KEY.EQ_BKID "
            + "AND PSBK_NO = :EQRBVT.KEY.PSBK_NO";
        IBS.READ(SCCGWA, EQRBVT, this, EQTBVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BVT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTACT_EQCINO() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_CINO = :EQRACT.EQ_CINO "
            + "AND AC_STS = :EQRACT.AC_STS";
        EQTACT_RD.fst = true;
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTACT_EQACT() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_ACT = :EQRACT.EQ_ACT "
            + "AND AC_STS = :EQRACT.AC_STS";
        EQTACT_RD.fst = true;
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTACT_AC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_AC = :EQRACT.KEY.EQ_AC";
        EQTACT_RD.fst = true;
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTACT_ACT() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_ACT = :EQRACT.EQ_ACT";
        EQTACT_RD.fst = true;
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTACT_CINO() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_CINO = :EQRACT.EQ_CINO";
        EQTACT_RD.fst = true;
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_READUP_EQTBVT() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.where = "EQ_BKID = :EQRBVT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRBVT.KEY.EQ_AC "
            + "AND PSBK_NO = :EQRBVT.KEY.PSBK_NO";
        EQTBVT_RD.upd = true;
        IBS.READ(SCCGWA, EQRBVT, this, EQTBVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BVT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_FLG = 'N';
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BVT_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_EQTWDZ() throws IOException,SQLException,Exception {
        EQTWDZ_RD = new DBParm();
        EQTWDZ_RD.TableName = "EQTWDZ";
        EQTWDZ_RD.where = "EQ_BKID = :EQRWDZ.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRWDZ.KEY.EQ_AC "
            + "AND PSBK_NO = :EQRWDZ.KEY.PSBK_NO "
            + "AND UPT_TYP = :EQRWDZ.KEY.UPT_TYP "
            + "AND PRT_FLG = :EQRWDZ.PRT_FLG";
        EQTWDZ_RD.fst = true;
        IBS.READ(SCCGWA, EQRWDZ, this, EQTWDZ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_WDZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_WDZ_FLG = 'N';
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_WDZ_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READUP_EQTWDZ() throws IOException,SQLException,Exception {
        EQTWDZ_RD = new DBParm();
        EQTWDZ_RD.TableName = "EQTWDZ";
        EQTWDZ_RD.where = "EQ_BKID = :EQRWDZ.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRWDZ.KEY.EQ_AC "
            + "AND PSBK_NO = :EQRWDZ.KEY.PSBK_NO "
            + "AND UPT_TYP = :EQRWDZ.KEY.UPT_TYP "
            + "AND UPT_NO = :EQRWDZ.KEY.UPT_NO "
            + "AND PRT_FLG = :EQRWDZ.PRT_FLG";
        EQTWDZ_RD.upd = true;
        IBS.READ(SCCGWA, EQRWDZ, this, EQTWDZ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_WDZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_WDZ_FLG = 'N';
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_WDZ_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_REWRITE_EQTBVT() throws IOException,SQLException,Exception {
        EQRBVT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRBVT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        IBS.REWRITE(SCCGWA, EQRBVT, EQTBVT_RD);
    }
    public void T000_REWRITE_EQTWDZ() throws IOException,SQLException,Exception {
        EQRWDZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQTWDZ_RD = new DBParm();
        EQTWDZ_RD.TableName = "EQTWDZ";
        IBS.REWRITE(SCCGWA, EQRWDZ, EQTWDZ_RD);
    }
    public void T000_WRITE_EQTBVT() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, EQRBVT, EQTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BVT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_EQTNPBK() throws IOException,SQLException,Exception {
        EQTNPBK_RD = new DBParm();
        EQTNPBK_RD.TableName = "EQTNPBK";
        EQTNPBK_RD.errhdl = true;
        IBS.WRITE(SCCGWA, EQRNPBK, EQTNPBK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_NPBK_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_EQTWDZ() throws IOException,SQLException,Exception {
        EQTWDZ_RD = new DBParm();
        EQTWDZ_RD.TableName = "EQTWDZ";
        EQTWDZ_RD.errhdl = true;
        IBS.WRITE(SCCGWA, EQRWDZ, EQTWDZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_WDZ_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_EQTACT() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.where = "( EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "OR ' ' = :EQRACT.KEY.EQ_BKID ) "
            + "AND ( EQ_AC = :EQRACT.KEY.EQ_AC "
            + "OR ' ' = :EQRACT.KEY.EQ_AC ) "
            + "AND ( CI_NO = :EQRACT.CI_NO "
            + "OR ' ' = :EQRACT.CI_NO ) "
            + "AND ( EQ_ACT = :EQRACT.EQ_ACT "
            + "OR ' ' = :EQRACT.EQ_ACT ) "
            + "AND ( EQ_CINO = :EQRACT.EQ_CINO "
            + "OR ' ' = :EQRACT.EQ_CINO ) "
            + "AND ( EQ_TYP = :EQRACT.EQ_TYP "
            + "OR ' ' = :EQRACT.EQ_TYP ) "
            + "AND ( AC_STS = :EQRACT.AC_STS "
            + "OR ' ' = :EQRACT.AC_STS )";
        EQTACT_BR.rp.order = "EQ_BKID,EQ_AC";
        IBS.STARTBR(SCCGWA, EQRACT, this, EQTACT_BR);
    }
    public void T001_STARTBR_EQTACT() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND ( ' ' = :EQRACT.EQ_ACT "
            + "OR EQ_ACT = :EQRACT.EQ_ACT ) "
            + "AND ( ' ' = :EQRACT.EQ_CINO "
            + "OR EQ_CINO = :EQRACT.EQ_CINO ) "
            + "AND ( ' ' = :EQRACT.KEY.EQ_AC "
            + "OR EQ_AC = :EQRACT.KEY.EQ_AC ) "
            + "AND ( ' ' = :EQRACT.CI_NO "
            + "OR CI_NO = :EQRACT.CI_NO )";
        EQTACT_BR.rp.order = "EQ_AC";
        IBS.STARTBR(SCCGWA, EQRACT, this, EQTACT_BR);
    }
    public void T000_READNEXT_EQTACT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRACT, this, EQTACT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_EQTACT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTACT_BR);
    }
    public void T000_STARTBR_EQTNPBK() throws IOException,SQLException,Exception {
        EQTNPBK_BR.rp = new DBParm();
        EQTNPBK_BR.rp.TableName = "EQTNPBK";
        EQTNPBK_BR.rp.where = "EQ_BKID = :EQRNPBK.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRNPBK.KEY.EQ_AC "
            + "AND ( TXN_DATE >= :WS_DB_STDT "
            + "AND TXN_DATE <= :WS_DB_EDDT )";
        EQTNPBK_BR.rp.order = "EQ_AC,TXN_DATE";
        IBS.STARTBR(SCCGWA, EQRNPBK, this, EQTNPBK_BR);
    }
    public void T000_READNEXT_EQTNPBK() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRNPBK, this, EQTNPBK_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_NPBK_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_NPBK_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_EQTNPBK() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTNPBK_BR);
    }
    public void T000_STARTBR_EQTWDZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRWDZ);
        WS_WDZ_FLG = ' ';
        EQRWDZ.KEY.EQ_BKID = EQCSINFO.DATA.EQ_BKID;
        EQRWDZ.KEY.EQ_AC = EQCSINFO.DATA.EQ_AC;
        EQRWDZ.KEY.PSBK_NO = EQCSINFO.DATA.PSBK_NO;
        EQRWDZ.PRT_FLG = 'N';
        EQTWDZ_BR.rp = new DBParm();
        EQTWDZ_BR.rp.TableName = "EQTWDZ";
        EQTWDZ_BR.rp.where = "EQ_BKID = :EQRWDZ.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRWDZ.KEY.EQ_AC "
            + "AND PSBK_NO = :EQRWDZ.KEY.PSBK_NO "
            + "AND PRT_FLG = :EQRWDZ.PRT_FLG";
        EQTWDZ_BR.rp.order = "UPT_TYP,TXN_DATE,JRN_NO";
        IBS.STARTBR(SCCGWA, EQRWDZ, this, EQTWDZ_BR);
    }
    public void T000_READNEXT_EQTWDZ() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRWDZ, this, EQTWDZ_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_WDZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_WDZ_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_EQTWDZ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTWDZ_BR);
    }
    public void S000_CALL_EQZRACT() throws IOException,SQLException,Exception {
        EQCRACT.REC_PTR = EQRACT;
        EQCRACT.REC_LEN = 724;
        IBS.CALLCPN(SCCGWA, "EQ-RSC-EQTACT", EQCRACT);
        if (EQCRACT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, EQCRACT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
