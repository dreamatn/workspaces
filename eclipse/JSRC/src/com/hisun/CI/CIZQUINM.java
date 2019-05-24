package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQUINM {
    int JIBS_tmp_int;
    String JIBS_NumStr;
    brParm CITHSTOT_BR = new brParm();
    DBParm CITTYP_RD;
    DBParm CITDTL_RD;
    DBParm CITGOPP_RD;
    brParm CITDTL_BR = new brParm();
    String K_PRDP_TYP = "PRDPR";
    String K_IRUL_TYP = "TIRUL";
    String K_AP_MMO = "CI";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_MSGID = " ";
    String WS_MMO = " ";
    char WS_EC = ' ';
    char WS_PROC_TYP = ' ';
    short WS_I = 0;
    short WS_J = 0;
    short WS_IDX = 0;
    short WS_X = 0;
    short WS_C = 0;
    short WS_D = 0;
    int WS_LC_SEQ = 0;
    int WS_SUB_SEQ = 0;
    int WS_SEQ = 0;
    CIZQUINM_WS_HS_VAL_INF WS_HS_VAL_INF = new CIZQUINM_WS_HS_VAL_INF();
    CIZQUINM_WS_IN_VAL_INF WS_IN_VAL_INF = new CIZQUINM_WS_IN_VAL_INF();
    short WS_LTH = 0;
    double WS_DLY_LMT_AMT = 0;
    double WS_MLY_LMT_AMT = 0;
    double WS_SYY_LMT_AMT = 0;
    double WS_YLY_LMT_AMT = 0;
    double WS_TM_LMT_AMT = 0;
    double WS_SE_LMT_AMT = 0;
    CIZQUINM_WS_AMT_INFO WS_AMT_INFO = new CIZQUINM_WS_AMT_INFO();
    double WS_LAST_DLY_TOT_AMT = 0;
    int WS_LAST_DLY_TOT_VOL = 0;
    double WS_LAST_MLY_TOT_AMT = 0;
    int WS_LAST_MLY_TOT_VOL = 0;
    double WS_LAST_SYY_TOT_AMT = 0;
    int WS_DAY1 = 0;
    CIZQUINM_REDEFINES46 REDEFINES46 = new CIZQUINM_REDEFINES46();
    int WS_DAY2 = 0;
    CIZQUINM_REDEFINES51 REDEFINES51 = new CIZQUINM_REDEFINES51();
    short WS_MM_COMP1 = 0;
    short WS_MM_COMP2 = 0;
    short WS_MM1_RES = 0;
    short WS_MM2_RES = 0;
    String WS_OBJ_TYP = " ";
    char WS_LC_OBJ_TYP = ' ';
    String WS_ACT_SCENE = " ";
    String WS_CCY = " ";
    int WS_LAST_LC_NO = 0;
    int WS_LAST_SEQ = 0;
    String WS_LAST_CON_TYPE = " ";
    int WS_LAST_SUB_SEQ = 0;
    char WS_MSREL_FLG = ' ';
    char WS_TYP_FLG = ' ';
    char WS_HSTOT_FLG = ' ';
    char WS_DTL_FLG = ' ';
    char WS_AC_LMT = ' ';
    char WS_QUIT_FLG = ' ';
    char WS_GOPP_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    CICOUINF CICOUINF = new CICOUINF();
    CIRTYP CIRTYP = new CIRTYP();
    CIRDTL CIRDTL = new CIRDTL();
    CIRHSTOT CIRHSTOT = new CIRHSTOT();
    CIRDINF CIRDINF = new CIRDINF();
    CIRSTYP CIRSTYP = new CIRSTYP();
    CIRGOPP CIRGOPP = new CIRGOPP();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICQUINF CICQUINF;
    public void MP(SCCGWA SCCGWA, CICQUINF CICQUINF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQUINF = CICQUINF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZQUINM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, CIRDTL);
        IBS.init(SCCGWA, CIRTYP);
        IBS.init(SCCGWA, CIRHSTOT);
        IBS.init(SCCGWA, CICOUINF);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, CICQUINF.FUNC);
        CEP.TRC(SCCGWA, CICQUINF.TYPE);
        CEP.TRC(SCCGWA, CICQUINF.OBJ_TYP);
        CEP.TRC(SCCGWA, CICQUINF.LC_OBJ);
        CEP.TRC(SCCGWA, CICQUINF.CUS_AC);
        CEP.TRC(SCCGWA, CICQUINF.TX_TYPE);
        CEP.TRC(SCCGWA, CICQUINF.RAG_FLG);
        CEP.TRC(SCCGWA, CICQUINF.SEQ);
        CEP.TRC(SCCGWA, CICQUINF.LC_NO);
        CEP.TRC(SCCGWA, CICQUINF.LC_SEQ);
        B100_CHECK_INPUT();
        if (CICQUINF.FUNC == '3') {
            B240_QRY_HSTOT_INFO();
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRTYP);
        CIRTYP.KEY.TYPE = CICQUINF.TYPE;
        T000_READ_CITTYP();
        if (WS_TYP_FLG == 'Y') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
        WS_OBJ_TYP = CIRTYP.OBJ_TYP;
        if (CIRTYP.OBJ_TYP == null) CIRTYP.OBJ_TYP = "";
        JIBS_tmp_int = CIRTYP.OBJ_TYP.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.OBJ_TYP += " ";
        for (WS_J = 1; (CIRTYP.OBJ_TYP.substring(WS_J - 1, WS_J + 1 - 1).trim().length() != 0 
            && WS_J <= 10); WS_J += 1) {
            if (CIRTYP.OBJ_TYP == null) CIRTYP.OBJ_TYP = "";
            JIBS_tmp_int = CIRTYP.OBJ_TYP.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.OBJ_TYP += " ";
            if (CIRTYP.OBJ_TYP.substring(WS_J - 1, WS_J + 1 - 1).equalsIgnoreCase("1")) {
                WS_C = WS_J;
                JIBS_NumStr = "" + WS_C;
                WS_LC_OBJ_TYP = JIBS_NumStr.charAt(0);
            }
        }
    }
    public void B240_QRY_HSTOT_INFO() throws IOException,SQLException,Exception {
        R000_GET_DTL_INFO();
        R000_GET_HSTOT_INFO();
        CEP.TRC(SCCGWA, WS_AC_LMT);
        if (WS_AC_LMT != 'N') {
            R000_GET_DTL_INFO_1();
            if (WS_GOPP_FLG == 'Y') {
                R000_GET_HSTOT_INFO();
            }
        }
        R000_OUTPUT_INF();
    }
    public void R000_GET_DTL_INFO() throws IOException,SQLException,Exception {
        WS_I = 1;
        IBS.init(SCCGWA, CIRDTL);
        CIRDTL.KEY.LC_NO = CICQUINF.LC_NO;
        CIRDTL.KEY.LC_SEQ = CICQUINF.LC_SEQ;
        T000_READ_CITDTL();
        if (WS_DTL_FLG == 'Y') {
            WS_DLY_LMT_AMT = CIRDTL.DLY_LMT_AMT;
            WS_MLY_LMT_AMT = CIRDTL.MLY_LMT_AMT;
            WS_SYY_LMT_AMT = CIRDTL.SYY_LMT_AMT;
            WS_YLY_LMT_AMT = CIRDTL.YLY_LMT_AMT;
            WS_TM_LMT_AMT = CIRDTL.TM_LMT_AMT;
            WS_SE_LMT_AMT = CIRDTL.SE_LMT_AMT;
            WS_ACT_SCENE = CIRDTL.ACT_SCENE;
            CICOUINF.DTL[WS_I-1].ACT_SCENE = CIRDTL.ACT_SCENE;
        }
    }
    public void R000_GET_HSTOT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRHSTOT);
        CIRHSTOT.LC_NO = CICQUINF.LC_NO;
        CIRHSTOT.LC_SEQ = CICQUINF.LC_SEQ;
        CIRHSTOT.KEY.RAG_FLG = CICQUINF.RAG_FLG;
        CIRHSTOT.KEY.LC_OBJ = CICQUINF.LC_OBJ;
        T000_STARTBR_CITHSTOT();
        T000_READNEXT_CITHSTOT();
        while (WS_HSTOT_FLG != 'Y') {
            R000_GEN_AMT_PROC();
            T000_READNEXT_CITHSTOT();
        }
        T000_ENDBR_CITHSTOT();
    }
    public void R000_GET_DTL_INFO_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGOPP);
        CIRGOPP.KEY.LC_OBJ = CICQUINF.LC_OBJ;
        CIRGOPP.KEY.TYPE = CICQUINF.TYPE;
        CIRGOPP.KEY.TX_TYPE = CICQUINF.TX_TYPE;
        CIRGOPP.KEY.LC_OBJ_TYP = "" + WS_LC_OBJ_TYP;
        JIBS_tmp_int = CIRGOPP.KEY.LC_OBJ_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRGOPP.KEY.LC_OBJ_TYP = "0" + CIRGOPP.KEY.LC_OBJ_TYP;
        CEP.TRC(SCCGWA, CIRGOPP.KEY.LC_OBJ);
        CEP.TRC(SCCGWA, CIRGOPP.KEY.TYPE);
        CEP.TRC(SCCGWA, CIRGOPP.KEY.TX_TYPE);
        CEP.TRC(SCCGWA, CIRGOPP.KEY.LC_OBJ_TYP);
        T000_READ_CITGOPP();
        if (WS_GOPP_FLG == 'Y') {
            IBS.init(SCCGWA, CIRDTL);
            CIRDTL.KEY.LC_NO = CIRGOPP.LC_NO;
            T000_STARTBR_CITDTL();
            T000_READNEXT_CITDTL();
            while (WS_DTL_FLG != 'N' 
                && WS_QUIT_FLG != 'Y') {
                if (CIRDTL.ACT_SCENE.equalsIgnoreCase(WS_ACT_SCENE)) {
                    CEP.TRC(SCCGWA, "---AC-DTL----");
                    CEP.TRC(SCCGWA, CIRDTL.KEY.LC_NO);
                    CEP.TRC(SCCGWA, CIRDTL.KEY.LC_SEQ);
                    WS_DLY_LMT_AMT = CIRDTL.DLY_LMT_AMT;
                    WS_MLY_LMT_AMT = CIRDTL.MLY_LMT_AMT;
                    WS_SYY_LMT_AMT = CIRDTL.SYY_LMT_AMT;
                    WS_YLY_LMT_AMT = CIRDTL.YLY_LMT_AMT;
                    WS_TM_LMT_AMT = CIRDTL.TM_LMT_AMT;
                    WS_SE_LMT_AMT = CIRDTL.SE_LMT_AMT;
                    CICOUINF.DTL[WS_I-1].ACT_SCENE = CIRDTL.ACT_SCENE;
                    IBS.init(SCCGWA, CIRHSTOT);
                    CICQUINF.LC_NO = CIRDTL.KEY.LC_NO;
                    CICQUINF.LC_SEQ = CIRDTL.KEY.LC_SEQ;
                    WS_QUIT_FLG = 'Y';
                }
                T000_READNEXT_CITDTL();
            }
            T000_ENDBR_CITDTL();
        }
    }
    public void R000_GEN_AMT_PROC() throws IOException,SQLException,Exception {
        if (CIRHSTOT.LAST_UPD_DT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_AMT_INFO.WS_DLY_TOT_AMT += CIRHSTOT.DLY_TOT_AMT;
            WS_AMT_INFO.WS_DLY_TOT_VOL += CIRHSTOT.DLY_TOT_VOL;
        } else {
            WS_LAST_DLY_TOT_AMT += CIRHSTOT.DLY_TOT_AMT;
            WS_LAST_DLY_TOT_VOL += CIRHSTOT.DLY_TOT_VOL;
        }
        WS_DAY1 = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DAY1+"", REDEFINES46);
        WS_DAY2 = CIRHSTOT.LAST_UPD_DT;
        IBS.CPY2CLS(SCCGWA, WS_DAY2+"", REDEFINES51);
        if ((REDEFINES46.WS_MM1 == REDEFINES51.WS_MM2) 
            && (REDEFINES46.WS_YY1 == REDEFINES51.WS_YY2)) {
            WS_AMT_INFO.WS_MLY_TOT_AMT += CIRHSTOT.MLY_TOT_AMT;
            WS_AMT_INFO.WS_MLY_TOT_VOL += CIRHSTOT.MLY_TOT_VOL;
        } else {
            WS_LAST_MLY_TOT_AMT += CIRHSTOT.MLY_TOT_AMT;
            WS_LAST_MLY_TOT_VOL += CIRHSTOT.MLY_TOT_VOL;
        }
        WS_MM_COMP1 = (short) (REDEFINES46.WS_MM1 - 1);
        WS_MM_COMP2 = (short) (REDEFINES51.WS_MM2 - 1);
        WS_MM1_RES = (short) ((WS_MM_COMP1 - WS_MM_COMP1 % 6) / 6);
        WS_MM2_RES = (short) ((WS_MM_COMP2 - WS_MM_COMP2 % 6) / 6);
        if (REDEFINES46.WS_MM1 > 6 
            && REDEFINES46.WS_YY1 == REDEFINES51.WS_YY2) {
            WS_LAST_SYY_TOT_AMT += CIRHSTOT.SYY_TOT_AMT;
        } else {
            if (REDEFINES46.WS_YY1 == REDEFINES51.WS_YY2) {
                WS_AMT_INFO.WS_SYY_TOT_AMT += CIRHSTOT.SYY_TOT_AMT;
            }
        }
        if (REDEFINES46.WS_YY1 == REDEFINES51.WS_YY2) {
            WS_AMT_INFO.WS_YLY_TOT_AMT += CIRHSTOT.YLY_TOT_AMT;
        }
        if (CIRDTL.CON_TYP == null) CIRDTL.CON_TYP = "";
        JIBS_tmp_int = CIRDTL.CON_TYP.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) CIRDTL.CON_TYP += " ";
        if (CIRDTL.CON_TYP.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, CIRHSTOT.SE_DAY_LMT_AMT);
            WS_AMT_INFO.WS_SE_DAY_LMT_AMT += CIRHSTOT.SE_DAY_LMT_AMT;
        }
        if (CIRDTL.CON_TYP == null) CIRDTL.CON_TYP = "";
        JIBS_tmp_int = CIRDTL.CON_TYP.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) CIRDTL.CON_TYP += " ";
        if (CIRDTL.CON_TYP.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            if (CIRHSTOT.LAST_UPD_DT == SCCGWA.COMM_AREA.AC_DATE) {
                WS_AMT_INFO.WS_SE_TM_LMT_AMT += CIRHSTOT.SE_TM_LMT_AMT;
            }
        }
        WS_CCY = CIRHSTOT.CCY;
    }
    public void R000_OUTPUT_INF() throws IOException,SQLException,Exception {
        CICOUINF.LC_OBJ = CICQUINF.LC_OBJ;
        CICOUINF.LC_OBJ_TYP = WS_OBJ_TYP;
        CICOUINF.DTL[WS_I-1].LC_NO = "" + CICQUINF.LC_NO;
        JIBS_tmp_int = CICOUINF.DTL[WS_I-1].LC_NO.length();
        for (int i=0;i<9-JIBS_tmp_int;i++) CICOUINF.DTL[WS_I-1].LC_NO = "0" + CICOUINF.DTL[WS_I-1].LC_NO;
        CICOUINF.DTL[WS_I-1].LC_SEQ = CICQUINF.LC_SEQ;
        CICOUINF.DTL[WS_I-1].TYPE = CICQUINF.TYPE;
        CICOUINF.DTL[WS_I-1].TX_TYPE = CICQUINF.TX_TYPE;
        CICOUINF.DTL[WS_I-1].RAG_FLG = CICQUINF.RAG_FLG;
        CICOUINF.DTL[WS_I-1].DLY_TOT_AMT = WS_AMT_INFO.WS_DLY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].DLY_TOT_VOL = WS_AMT_INFO.WS_DLY_TOT_VOL;
        CICOUINF.DTL[WS_I-1].LAST_DLY_TOT_AMT = WS_LAST_DLY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].LAST_DLY_TOT_VOL = WS_LAST_DLY_TOT_VOL;
        CICOUINF.DTL[WS_I-1].MLY_TOT_AMT = WS_AMT_INFO.WS_MLY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].MLY_TOT_VOL = WS_AMT_INFO.WS_MLY_TOT_VOL;
        CICOUINF.DTL[WS_I-1].LAST_MLY_TOT_AMT = WS_LAST_MLY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].LAST_MLY_TOT_VOL = WS_LAST_MLY_TOT_VOL;
        CICOUINF.DTL[WS_I-1].LAST_SYY_TOT_AMT = WS_LAST_SYY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].SYY_TOT_AMT = WS_AMT_INFO.WS_SYY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].YLY_TOT_AMT = WS_AMT_INFO.WS_YLY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].SE_DAY_LMT_AMT = WS_AMT_INFO.WS_SE_DAY_LMT_AMT;
        CICOUINF.DTL[WS_I-1].SE_TM_LMT_AMT = WS_AMT_INFO.WS_SE_TM_LMT_AMT;
        if (WS_DLY_LMT_AMT != 99999999999999.99) {
            WS_DLY_LMT_AMT -= WS_AMT_INFO.WS_DLY_TOT_AMT;
            CICOUINF.DTL[WS_I-1].LV_DLY_AMT = WS_DLY_LMT_AMT;
        } else {
            CICOUINF.DTL[WS_I-1].LV_DLY_AMT = WS_DLY_LMT_AMT;
        }
        if (WS_MLY_LMT_AMT != 99999999999999.99) {
            WS_MLY_LMT_AMT -= WS_AMT_INFO.WS_MLY_TOT_AMT;
            CICOUINF.DTL[WS_I-1].LV_MLY_AMT = WS_MLY_LMT_AMT;
        } else {
            CICOUINF.DTL[WS_I-1].LV_MLY_AMT = WS_MLY_LMT_AMT;
        }
        if (WS_SYY_LMT_AMT != 99999999999999.99) {
            WS_SYY_LMT_AMT -= WS_AMT_INFO.WS_SYY_TOT_AMT;
            CICOUINF.DTL[WS_I-1].LV_SYY_AMT = WS_SYY_LMT_AMT;
        } else {
            CICOUINF.DTL[WS_I-1].LV_SYY_AMT = WS_SYY_LMT_AMT;
        }
        if (WS_YLY_LMT_AMT != 99999999999999.99) {
            WS_YLY_LMT_AMT -= WS_AMT_INFO.WS_YLY_TOT_AMT;
            CICOUINF.DTL[WS_I-1].LV_YLY_AMT = WS_YLY_LMT_AMT;
        } else {
            CICOUINF.DTL[WS_I-1].LV_YLY_AMT = WS_YLY_LMT_AMT;
        }
        if (WS_TM_LMT_AMT != 99999999999999.99) {
            WS_TM_LMT_AMT -= WS_AMT_INFO.WS_SE_TM_LMT_AMT;
            CICOUINF.DTL[WS_I-1].LV_SEDAY_AMT = WS_TM_LMT_AMT;
        } else {
            CICOUINF.DTL[WS_I-1].LV_SEDAY_AMT = WS_TM_LMT_AMT;
        }
        if (WS_TM_LMT_AMT != 99999999999999.99) {
            WS_SE_LMT_AMT -= WS_AMT_INFO.WS_SE_DAY_LMT_AMT;
            CICOUINF.DTL[WS_I-1].LV_SETM_AMT = WS_SE_LMT_AMT;
        } else {
            CICOUINF.DTL[WS_I-1].LV_SETM_AMT = WS_SE_LMT_AMT;
        }
        CICOUINF.END_FLG = 'Y';
        CICOUINF.DTL[WS_I-1].CCY = WS_CCY;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIO50";
        SCCFMT.DATA_PTR = CICOUINF;
        SCCFMT.DATA_LEN = 5041;
        CEP.TRC(SCCGWA, CICOUINF.END_FLG);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_CITHSTOT() throws IOException,SQLException,Exception {
        CITHSTOT_BR.rp = new DBParm();
        CITHSTOT_BR.rp.TableName = "CITHSTOT";
        CITHSTOT_BR.rp.where = "LC_NO = :CIRHSTOT.LC_NO "
            + "AND LC_SEQ = :CIRHSTOT.LC_SEQ "
            + "AND RAG_FLG = :CIRHSTOT.KEY.RAG_FLG "
            + "AND LC_OBJ = :CIRHSTOT.KEY.LC_OBJ";
        CITHSTOT_BR.rp.order = "LC_SEQ";
        IBS.STARTBR(SCCGWA, CIRHSTOT, this, CITHSTOT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_CITHSTOT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRHSTOT, this, CITHSTOT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_HSTOT_FLG = 'Y';
        } else {
            WS_HSTOT_FLG = 'N';
            WS_AC_LMT = 'N';
        }
    }
    public void T000_ENDBR_CITHSTOT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITHSTOT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_HSTOT_FLG = 'Y';
        }
    }
    public void T000_READ_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        CITTYP_RD.where = "TYPE = :CIRTYP.KEY.TYPE";
        IBS.READ(SCCGWA, CIRTYP, this, CITTYP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void T000_READ_CITDTL() throws IOException,SQLException,Exception {
        CITDTL_RD = new DBParm();
        CITDTL_RD.TableName = "CITDTL";
        IBS.READ(SCCGWA, CIRDTL, CITDTL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        WS_DTL_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DTL_FLG = 'Y';
        } else {
            WS_DTL_FLG = 'N';
        }
    }
    public void T000_READ_CITGOPP() throws IOException,SQLException,Exception {
        CITGOPP_RD = new DBParm();
        CITGOPP_RD.TableName = "CITGOPP";
        IBS.READ(SCCGWA, CIRGOPP, CITGOPP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GOPP_FLG = 'Y';
        } else {
            WS_GOPP_FLG = 'N';
        }
    }
    public void T000_STARTBR_CITDTL() throws IOException,SQLException,Exception {
        CITDTL_BR.rp = new DBParm();
        CITDTL_BR.rp.TableName = "CITDTL";
        CITDTL_BR.rp.where = "LC_NO = :CIRGOPP.LC_NO";
        IBS.STARTBR(SCCGWA, CIRDTL, this, CITDTL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_CITDTL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRDTL, this, CITDTL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        WS_DTL_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DTL_FLG = 'Y';
        } else {
            WS_DTL_FLG = 'N';
        }
    }
    public void T000_ENDBR_CITDTL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITDTL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
