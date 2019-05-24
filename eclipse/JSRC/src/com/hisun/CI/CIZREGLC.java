package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFX;
import com.hisun.BP.BPCPRMBR;
import com.hisun.DD.DDCIMMST;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCCWA;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIZREGLC {
    boolean pgmRtn = false;
    String K_PRDP_TYP = "PRDPR";
    String K_IRUL_TYP = "TIRUL";
    String K_AP_MMO = "TD";
    double WK_BAL = 99999999999999.99;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_CON_TYP_LW = "10011000000000000000000000000000000000000000000000";
    String WS_TYP_CON_TYP_01 = "11111000000000001101001000000000000000000000000000";
    String WS_MSGID = " ";
    String WS_MMO = " ";
    char WS_EC = ' ';
    char WS_PROC_TYP = ' ';
    short WS_I = 0;
    short WS_J = 0;
    short WS_L = 0;
    short WS_K = 0;
    short WS_M = 0;
    short WS_N = 0;
    short WS_P = 0;
    short WS_Q = 0;
    short WS_R = 0;
    short WS_S = 0;
    short WS_IDX = 0;
    short WS_X = 0;
    short WS_LTH = 0;
    short WS_C = 0;
    short WS_D = 0;
    short WS_E = 0;
    short WS_F = 0;
    short WS_U = 0;
    short WS_V = 0;
    short WS_W = 0;
    short WS_Y = 0;
    short WS_Z = 0;
    String WS_LNK_TEMP = " ";
    short WS_LENTH = 0;
    short WS_LK_NUM1 = 0;
    short WS_LK_NUM2 = 0;
    short WS_LK_NUM3 = 0;
    short WS_LK_NUM4 = 0;
    int WS_MAX_NUM = 0;
    int WS_LC_NO = 0;
    int WS_LC_SEQ = 0;
    int WS_SUB_SEQ = 0;
    int WS_SEQ = 0;
    int WS_LAST_LC_NO = 0;
    int WS_LAST_SEQ = 0;
    int WS_LAST_SUB_SEQ = 0;
    String WS_TX_TYPE = " ";
    String WS_TYPE = " ";
    char WS_HS_FLG = ' ';
    char WS_LC_OBJ_TYP = ' ';
    String WS_LC_OBJ = " ";
    String WS_CON_TYP = " ";
    String WS_LAST_CON_TYP = " ";
    char WS_LAST_VAL_COND = ' ';
    short WS_LAST_CON_SEQ = 0;
    String WS_LAST_PF_CON_TYP = " ";
    char WS_LAST_PF_VAL_COND = ' ';
    short WS_LAST_PF_CON_SEQ = 0;
    String WS_DINF_VAL = " ";
    String WS_DINF_VAL_1 = " ";
    String WS_DINF_VAL_2 = " ";
    String WS_TPINF_VAL = " ";
    String[] WS_CON_TYP_50 = new String[10];
    String WS_TYP_CON_TYP = " ";
    String WS_CON_VAL_MTH = " ";
    short[] WS_LVL = new short[10];
    String WS_CUS_AC = " ";
    String WS_CI_NO = " ";
    double WS_TX_AMT = 0;
    double WS_BAL = 0;
    double WS_AMT = 0;
    double WS_AMT1 = 0;
    int WS_DAY1 = 0;
    CIZREGLC_REDEFINES68 REDEFINES68 = new CIZREGLC_REDEFINES68();
    int WS_DAY2 = 0;
    CIZREGLC_REDEFINES73 REDEFINES73 = new CIZREGLC_REDEFINES73();
    short WS_MM_COMP1 = 0;
    short WS_MM_COMP2 = 0;
    short WS_MM1_RES = 0;
    short WS_MM2_RES = 0;
    short WS_AMT_NUM = 0;
    String WS_DINF_VAL_12 = " ";
    String WS_DINF_VAL_13 = " ";
    String WS_MTX_VAL = " ";
    String WS_LAST_DTL_CON_TYP = " ";
    short WS_LAST_LVL = 0;
    short WS_DINF_LAST_BEGSEQ = 0;
    short WS_DTL_LAST_BEGSEQ = 0;
    char WS_TRS_FLG = ' ';
    CIZREGLC_WS_HS_VAL_INF WS_HS_VAL_INF = new CIZREGLC_WS_HS_VAL_INF();
    CIZREGLC_WS_IN_VAL_INF WS_IN_VAL_INF = new CIZREGLC_WS_IN_VAL_INF();
    CIZREGLC_WS_AMT_INFO WS_AMT_INFO = new CIZREGLC_WS_AMT_INFO();
    CIZREGLC_WS_TYPE_INFO WS_TYPE_INFO = new CIZREGLC_WS_TYPE_INFO();
    CIZREGLC_WS_DINF_INFO WS_DINF_INFO = new CIZREGLC_WS_DINF_INFO();
    CIZREGLC_WS_TPINF_INFO WS_TPINF_INFO = new CIZREGLC_WS_TPINF_INFO();
    String WS_DINF_VAL1 = " ";
    String WS_ERR_INFO = " ";
    CIZREGLC_WS_CI_KEY1 WS_CI_KEY1 = new CIZREGLC_WS_CI_KEY1();
    CIZREGLC_WS_CI_KEY2 WS_CI_KEY2 = new CIZREGLC_WS_CI_KEY2();
    CIZREGLC_WS_B_SX_VAL_INF WS_B_SX_VAL_INF = new CIZREGLC_WS_B_SX_VAL_INF();
    CIZREGLC_WS_HOJ WS_HOJ = new CIZREGLC_WS_HOJ();
    String WS_TX_TYPE_DB1 = " ";
    String WS_TX_TYPE_DB2 = " ";
    String WS_TX_TYPE_DB3 = " ";
    String WS_TX_TYPE_DB4 = " ";
    String WS_TX_TYPE_DB5 = " ";
    char WS_MSREL_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_GOPP_USE_FLG = ' ';
    char WS_CHECK_FLG = ' ';
    char WS_DINF_CHK_FLG = ' ';
    char WS_GOPP_FLG = ' ';
    char WS_CMPR_FLG = ' ';
    char WS_DTL_FLG = ' ';
    char WS_DINF_FLG = ' ';
    char WS_DUP_FLG = ' ';
    char WS_LC_NO_FLG = ' ';
    char WS_TXTOT_FLG = ' ';
    char WS_USHIS_FLG = ' ';
    char WS_TPINF_FLG = ' ';
    char WS_HSTOT_FLG = ' ';
    char WS_REG_FLG = ' ';
    char WS_OPP_FLG = ' ';
    char WS_QUIT_TYP_FLG = ' ';
    char WS_DTL_QUIT_FLG = ' ';
    char WS_LVL_CHK_FLG = ' ';
    char WS_DINF_PROC_FLG = ' ';
    char WS_RAG_FLG = ' ';
    char WS_HSTOT_INS_FLG = ' ';
    char WS_HSTOT_PROC_FLG = ' ';
    char WS_EC_FLG = ' ';
    char WS_DINF_QUIT_FLG = ' ';
    char WS_EXIT_FLG = ' ';
    char WS_MTX_FLG = ' ';
    char WS_TPINF_CHK_FLG = ' ';
    char WS_SAME_SEQ_FLG = ' ';
    char WS_CHK_TPINF_FLG = ' ';
    char WS_CON_TYP_FLG = ' ';
    char WS_EQUAL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCFX BPCFX = new BPCFX();
    CICCUST CICCUST = new CICCUST();
    CICSPLC CICSPLC = new CICSPLC();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACRI CICQACRI = new CICQACRI();
    DDCIMMST DDCIMMST = new DDCIMMST();
    CIRTYP CIRTYP = new CIRTYP();
    CIRDTL CIRDTL = new CIRDTL();
    CIRGOPP CIRGOPP = new CIRGOPP();
    CIRDINF CIRDINF = new CIRDINF();
    CIRSTYP CIRSTYP = new CIRSTYP();
    CIRCMPR CIRCMPR = new CIRCMPR();
    CIRTXTOT CIRTXTOT = new CIRTXTOT();
    CIRUSHIS CIRUSHIS = new CIRUSHIS();
    CIRTPINF CIRTPINF = new CIRTPINF();
    EARACLNK EARACLNK = new EARACLNK();
    CIRHSTOT CIRHSTOT = new CIRHSTOT();
    BPCPRMBR BPCPRMBR = new BPCPRMBR();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICREGLC CICREGLC;
    SCCCWA SCCCWA;
    public void MP(SCCGWA SCCGWA, CICREGLC CICREGLC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICREGLC = CICREGLC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZREGLC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, CIRTYP);
        IBS.init(SCCGWA, CIRGOPP);
        IBS.init(SCCGWA, CIRDTL);
        IBS.init(SCCGWA, CIRDINF);
        IBS.init(SCCGWA, CIRSTYP);
        IBS.init(SCCGWA, CIRHSTOT);
        IBS.init(SCCGWA, CICQACRI);
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        R000_SET_LK_MMT();
        if (pgmRtn) return;
    } //FROM #ENDIF
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, CICREGLC.FUNC);
        CEP.TRC(SCCGWA, CICREGLC.TX_TYPE);
        CEP.TRC(SCCGWA, CICREGLC.CUS_AC);
        CEP.TRC(SCCGWA, CICREGLC.ACO_AC);
        CEP.TRC(SCCGWA, CICREGLC.LC_OBJ_TYP);
        CEP.TRC(SCCGWA, CICREGLC.LC_OBJ);
        CEP.TRC(SCCGWA, CICREGLC.DC_FLG);
        CEP.TRC(SCCGWA, CICREGLC.CCY);
        CEP.TRC(SCCGWA, CICREGLC.CCY_TYP);
        CEP.TRC(SCCGWA, CICREGLC.AMT);
        CEP.TRC(SCCGWA, CICREGLC.BAL);
        CEP.TRC(SCCGWA, CICREGLC.TRS_FLG);
        CEP.TRC(SCCGWA, CICREGLC.OPP_CUS_AC);
        CEP.TRC(SCCGWA, CICREGLC.OPP_ACO_AC);
        CEP.TRC(SCCGWA, CICREGLC.RLT_CUS_AC);
        CEP.TRC(SCCGWA, CICREGLC.CI_NO);
        CEP.TRC(SCCGWA, CICREGLC.OPP_CI_NO);
        CEP.TRC(SCCGWA, CICREGLC.PROD_CD);
        CEP.TRC(SCCGWA, CICREGLC.CITY_FLG);
        CEP.TRC(SCCGWA, CICREGLC.TX_CHNL_NO);
        CEP.TRC(SCCGWA, CICREGLC.AC_TYP);
        CEP.TRC(SCCGWA, CICREGLC.AC_BR);
        CEP.TRC(SCCGWA, CICREGLC.AC_SEQ);
        CEP.TRC(SCCGWA, CICREGLC.TX_BR);
        CEP.TRC(SCCGWA, CICREGLC.TX_COUNTRY);
        CEP.TRC(SCCGWA, CICREGLC.TX_DATE);
        CEP.TRC(SCCGWA, CICREGLC.TX_TIME);
        CEP.TRC(SCCGWA, CICREGLC.HS_FLG);
        CEP.TRC(SCCGWA, CICREGLC.BV_TYP);
        CEP.TRC(SCCGWA, CICREGLC.TX_TYP);
        CEP.TRC(SCCGWA, CICREGLC.MMO);
        CEP.TRC(SCCGWA, CICREGLC.TYPE);
        CEP.TRC(SCCGWA, CICREGLC.CARD_LNK_TYP);
        CEP.TRC(SCCGWA, CICREGLC.OTH_BK);
        CEP.TRC(SCCGWA, CICREGLC.BD_FLG);
        CEP.TRC(SCCGWA, CICREGLC.EC_FLG);
        CEP.TRC(SCCGWA, CICREGLC.CAN_DATE);
        CEP.TRC(SCCGWA, CICREGLC.ACO_PROD_CD);
        CEP.TRC(SCCGWA, CICREGLC.HLD_NO);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B220_CANCEL_LC_INFO();
            if (pgmRtn) return;
        } else {
            if (CICREGLC.EC_FLG == 'Y') {
                B230_CANCEL_PRE_INFO();
                if (pgmRtn) return;
            }
            R000_CHECK_INPUT();
            if (pgmRtn) return;
            B210_CHK_REG_LC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B210_CHK_REG_LC_INFO() throws IOException,SQLException,Exception {
        if (CICREGLC.FUNC == '0') {
            WS_REG_FLG = 'Y';
        } else {
            WS_REG_FLG = 'N';
        }
        IBS.init(SCCGWA, CIRCMPR);
        if (CICREGLC.TX_TYPE.trim().length() == 0) {
            if (CICREGLC.DC_FLG == 'D') {
                WS_TX_TYPE_DB1 = "01";
                WS_TX_TYPE_DB2 = "03";
            } else {
                WS_TX_TYPE_DB1 = "02";
                WS_TX_TYPE_DB2 = "03";
            }
        } else {
            WS_X = 1;
            if (CICREGLC.TX_TYPE == null) CICREGLC.TX_TYPE = "";
            JIBS_tmp_int = CICREGLC.TX_TYPE.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) CICREGLC.TX_TYPE += " ";
            for (WS_I = 1; WS_I <= 5 
                && CICREGLC.TX_TYPE.substring(WS_X - 1, WS_X + 2 - 1).trim().length() != 0; WS_I += 1) {
                if (WS_I == 1) {
                    if (CICREGLC.TX_TYPE == null) CICREGLC.TX_TYPE = "";
                    JIBS_tmp_int = CICREGLC.TX_TYPE.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) CICREGLC.TX_TYPE += " ";
                    WS_TX_TYPE_DB1 = CICREGLC.TX_TYPE.substring(WS_X - 1, WS_X + 2 - 1);
                } else if (WS_I == 2) {
                    if (CICREGLC.TX_TYPE == null) CICREGLC.TX_TYPE = "";
                    JIBS_tmp_int = CICREGLC.TX_TYPE.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) CICREGLC.TX_TYPE += " ";
                    WS_TX_TYPE_DB2 = CICREGLC.TX_TYPE.substring(WS_X - 1, WS_X + 2 - 1);
                } else if (WS_I == 3) {
                    if (CICREGLC.TX_TYPE == null) CICREGLC.TX_TYPE = "";
                    JIBS_tmp_int = CICREGLC.TX_TYPE.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) CICREGLC.TX_TYPE += " ";
                    WS_TX_TYPE_DB3 = CICREGLC.TX_TYPE.substring(WS_X - 1, WS_X + 2 - 1);
                } else if (WS_I == 4) {
                    if (CICREGLC.TX_TYPE == null) CICREGLC.TX_TYPE = "";
                    JIBS_tmp_int = CICREGLC.TX_TYPE.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) CICREGLC.TX_TYPE += " ";
                    WS_TX_TYPE_DB4 = CICREGLC.TX_TYPE.substring(WS_X - 1, WS_X + 2 - 1);
                } else if (WS_I == 5) {
                    if (CICREGLC.TX_TYPE == null) CICREGLC.TX_TYPE = "";
                    JIBS_tmp_int = CICREGLC.TX_TYPE.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) CICREGLC.TX_TYPE += " ";
                    WS_TX_TYPE_DB5 = CICREGLC.TX_TYPE.substring(WS_X - 1, WS_X + 2 - 1);
                } else {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
                }
                WS_X = (short) (WS_X + 2);
            }
        }
        T000_STARTBR_CITCMPR();
        if (pgmRtn) return;
        T000_READNEXT_CITCMPR();
        if (pgmRtn) return;
        while (WS_CMPR_FLG != 'Y') {
            IBS.init(SCCGWA, CIRTYP);
            CIRTYP.KEY.TYPE = CIRCMPR.KEY.TYPE;
            T000_READ_CITTYP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRTYP.OBJ_TYP);
            R000_CHK_TYP_INFO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRTYP.KEY.TYPE);
            CEP.TRC(SCCGWA, WS_QUIT_TYP_FLG);
            if (CIRTYP.OBJ_TYP == null) CIRTYP.OBJ_TYP = "";
            JIBS_tmp_int = CIRTYP.OBJ_TYP.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.OBJ_TYP += " ";
            for (WS_I = 1; (CIRTYP.OBJ_TYP.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0 
                && WS_I <= 10) 
                && WS_QUIT_TYP_FLG != 'Y'; WS_I += 1) {
                if (CIRTYP.OBJ_TYP == null) CIRTYP.OBJ_TYP = "";
                JIBS_tmp_int = CIRTYP.OBJ_TYP.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.OBJ_TYP += " ";
                if (CIRTYP.OBJ_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                    WS_C = WS_I;
                    JIBS_NumStr = "" + WS_C;
                    WS_LC_OBJ_TYP = JIBS_NumStr.charAt(0);
                    if (WS_I == 1) {
                        WS_LC_OBJ = CICREGLC.CI_NO;
                    }
                    if (WS_I == 2 
                        || WS_I == 5 
                        || WS_I == 6) {
                        WS_LC_OBJ = CICREGLC.CUS_AC;
                    }
                    if (WS_I == 3) {
                        WS_LC_OBJ = CICREGLC.ACO_AC;
                    }
                    WS_GOPP_USE_FLG = ' ';
                    if (CIRTYP.SPE_FLG != '1') {
                        R000_CHK_IF_PDTL();
                        if (pgmRtn) return;
                    }
                    if (WS_GOPP_USE_FLG == 'Y') {
                        CEP.TRC(SCCGWA, CIRGOPP.LC_NO);
                        if (CIRTYP.SPE_ACT_FLG == '0' 
                            || CIRTYP.SPE_ACT_FLG == '4') {
                            WS_LC_NO = CIRGOPP.LC_NO;
                            WS_TYPE = CIRGOPP.KEY.TYPE;
                            WS_TX_TYPE = CIRGOPP.KEY.TX_TYPE;
                        }
                        if (CIRTYP.SPE_ACT_FLG == '2') {
                            WS_LC_NO = CIRGOPP.LC_NO;
                            WS_TYPE = CIRGOPP.KEY.TYPE;
                            WS_TX_TYPE = CIRGOPP.KEY.TX_TYPE;
                            if (WS_LC_NO != 0) {
                                R000_CHK_DTL_PROC();
                                if (pgmRtn) return;
                            }
                            WS_LC_NO = CIRCMPR.LC_NO;
                            WS_TYPE = CIRCMPR.KEY.TYPE;
                            WS_TX_TYPE = CIRCMPR.KEY.TX_TYPE;
                        }
                    } else {
                        WS_LC_NO = CIRCMPR.LC_NO;
                        WS_TYPE = CIRCMPR.KEY.TYPE;
                        WS_TX_TYPE = CIRCMPR.KEY.TX_TYPE;
                    }
                    if (WS_LC_NO != 0) {
                        R000_CHK_DTL_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_LK_NUM1);
            CEP.TRC(SCCGWA, WS_LK_NUM2);
            CEP.TRC(SCCGWA, WS_LK_NUM3);
            CEP.TRC(SCCGWA, WS_LK_NUM4);
            T000_READNEXT_CITCMPR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCMPR();
        if (pgmRtn) return;
    }
    public void B220_CANCEL_LC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRUSHIS);
        CIRUSHIS.KEY.AC_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        WS_HOJ.WS_HOJ_INFO = "" + GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        JIBS_tmp_int = WS_HOJ.WS_HOJ_INFO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_HOJ.WS_HOJ_INFO = "0" + WS_HOJ.WS_HOJ_INFO;
        if (CICREGLC.HLD_NO.trim().length() > 0) {
            WS_HOJ.WS_HOJ_FLG = '1';
        } else {
            WS_HOJ.WS_HOJ_FLG = '0';
        }
        CIRUSHIS.KEY.JRN_NO = IBS.CLS2CPY(SCCGWA, WS_HOJ);
        T000_STARTBR_CITUSHIS();
        if (pgmRtn) return;
        T000_READNEXT_CITUSHIS();
        if (pgmRtn) return;
        while (WS_USHIS_FLG != 'N') {
            CIRUSHIS.EC_FLG = 'Y';
            R000_GEN_HSTOT_INFO_EC();
            if (pgmRtn) return;
            T000_REWRITE_CITUSHIS();
            if (pgmRtn) return;
            T000_READNEXT_CITUSHIS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITUSHIS();
        if (pgmRtn) return;
    }
    public void B230_CANCEL_PRE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRUSHIS);
        CIRUSHIS.KEY.AC_DATE = CICREGLC.CAN_DATE;
        WS_HOJ.WS_HOJ_INFO = CICREGLC.HLD_NO;
        WS_HOJ.WS_HOJ_FLG = '1';
        CIRUSHIS.KEY.JRN_NO = IBS.CLS2CPY(SCCGWA, WS_HOJ);
        T000_STARTBR_CITUSHIS_PRE();
        if (pgmRtn) return;
        T000_READNEXT_CITUSHIS();
        if (pgmRtn) return;
        while (WS_USHIS_FLG != 'N') {
            CIRUSHIS.EC_FLG = 'Y';
            R000_GEN_HSTOT_INFO_EC();
            if (pgmRtn) return;
            T000_REWRITE_CITUSHIS();
            if (pgmRtn) return;
            T000_READNEXT_CITUSHIS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITUSHIS();
        if (pgmRtn) return;
    }
    public void R000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CICREGLC.FUNC == '2') {
            CICREGLC.TXN_AMT = WK_BAL;
            CICREGLC.TXN_LMT_AMT = WK_BAL;
            CICREGLC.DLY_LMT_AMT = WK_BAL;
            CICREGLC.MLY_LMT_AMT = WK_BAL;
            CICREGLC.SYY_LMT_AMT = WK_BAL;
            CICREGLC.YLY_LMT_AMT = WK_BAL;
            CICREGLC.TM_LMT_AMT = WK_BAL;
            CICREGLC.SE_LMT_AMT = WK_BAL;
            CICREGLC.LMT_AMT_1 = WK_BAL;
            CICREGLC.LMT_AMT_2 = WK_BAL;
            CICREGLC.LMT_AMT_3 = WK_BAL;
            CICREGLC.LMT_AMT_4 = WK_BAL;
            CICREGLC.BAL_LMT_AMT = WK_BAL;
            CICREGLC.DLY_LMT_VOL = 9999;
            CICREGLC.MLY_LMT_VOL = 9999;
        }
    }
    public void R000_CHK_TYP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRTPINF);
        WS_QUIT_TYP_FLG = ' ';
        WS_TPINF_FLG = ' ';
        IBS.init(SCCGWA, WS_TPINF_INFO);
        WS_SAME_SEQ_FLG = ' ';
        CIRTPINF.KEY.TYPE = CIRTYP.KEY.TYPE;
        WS_LAST_PF_CON_SEQ = 9999;
        T000_STARTBR_CITTPINF();
        if (pgmRtn) return;
        T000_READNEXT_CITTPINF();
        if (pgmRtn) return;
        WS_CHK_TPINF_FLG = ' ';
        CEP.TRC(SCCGWA, WS_QUIT_TYP_FLG);
        CEP.TRC(SCCGWA, WS_CHK_TPINF_FLG);
        while (WS_TPINF_FLG != 'E' 
            && (WS_SAME_SEQ_FLG != 'N' 
            || WS_CHK_TPINF_FLG != 'Y')) {
            WS_SAME_SEQ_FLG = ' ';
            if (CIRTPINF.KEY.SEQ != WS_LAST_PF_CON_SEQ) {
                WS_N = 0;
                WS_TPINF_CHK_FLG = ' ';
                IBS.init(SCCGWA, WS_TPINF_INFO);
                WS_LAST_PF_CON_TYP = CIRTPINF.KEY.CON_TYP;
                WS_LAST_PF_VAL_COND = CIRTPINF.VAL_COND;
                WS_CHK_TPINF_FLG = ' ';
                WS_LAST_PF_CON_SEQ = (short) CIRTPINF.KEY.SEQ;
            }
            WS_N += 1;
            WS_TPINF_INFO.WS_TPINF_CON_TYP[WS_N-1] = CIRTPINF.KEY.CON_TYP;
            WS_TPINF_INFO.WS_TPINF_VAL_IN[WS_N-1] = CIRTPINF.KEY.VAL;
            WS_TPINF_INFO.WS_TPINF_VAL_COND[WS_N-1] = CIRTPINF.VAL_COND;
            R000_CHK_TPINF_RES_EQUAL();
            if (pgmRtn) return;
            R000_CHK_TPINF_VALUE();
            if (pgmRtn) return;
            T000_READNEXT_CITTPINF();
            if (pgmRtn) return;
            if (CIRTPINF.KEY.SEQ != WS_LAST_PF_CON_SEQ) {
                WS_SAME_SEQ_FLG = 'N';
                R000_CHK_TPINF_RES_EQUAL();
                if (pgmRtn) return;
            }
        }
        if (WS_N > 0) {
            R000_CHK_TPINF_RES_EQUAL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITTPINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CHK_TPINF_FLG);
        if (WS_CHK_TPINF_FLG == 'Y') {
            WS_QUIT_TYP_FLG = 'Y';
        } else {
            WS_QUIT_TYP_FLG = 'N';
        }
        if (CIRTYP.OBJ_TYP == null) CIRTYP.OBJ_TYP = "";
        JIBS_tmp_int = CIRTYP.OBJ_TYP.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.OBJ_TYP += " ";
        for (WS_I = 1; (CIRTYP.OBJ_TYP.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0 
            && WS_I <= 10) 
            && WS_QUIT_TYP_FLG != 'Y'; WS_I += 1) {
            if (CIRTYP.OBJ_TYP == null) CIRTYP.OBJ_TYP = "";
            JIBS_tmp_int = CIRTYP.OBJ_TYP.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.OBJ_TYP += " ";
            if (CIRTYP.OBJ_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                if ((WS_I == 5 
                    && !CICREGLC.ACO_FRM_APP.equalsIgnoreCase("DD")) 
                    || (WS_I == 6 
                    && !CICREGLC.ACO_FRM_APP.equalsIgnoreCase("TD"))) {
                    WS_QUIT_TYP_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_QUIT_TYP_FLG);
        CEP.TRC(SCCGWA, WS_SAME_SEQ_FLG);
        CEP.TRC(SCCGWA, WS_CHK_TPINF_FLG);
    }
    public void R000_CHK_TPINF_RES_EQUAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_QUIT_TYP_FLG);
        CEP.TRC(SCCGWA, WS_TPINF_INFO.WS_TPINF_CON_TYP[WS_N-1]);
        CEP.TRC(SCCGWA, WS_LAST_PF_CON_TYP);
        CEP.TRC(SCCGWA, CIRTPINF.KEY.SEQ);
        CEP.TRC(SCCGWA, WS_LAST_PF_CON_SEQ);
        CEP.TRC(SCCGWA, WS_TPINF_CHK_FLG);
        CEP.TRC(SCCGWA, WS_SAME_SEQ_FLG);
        if ((!WS_TPINF_INFO.WS_TPINF_CON_TYP[WS_N-1].equalsIgnoreCase(WS_LAST_PF_CON_TYP) 
            || WS_SAME_SEQ_FLG == 'N' 
            || WS_TPINF_CHK_FLG == 'E' 
            || WS_TPINF_FLG == 'E') 
            && WS_CHK_TPINF_FLG != 'N') {
            if ((WS_LAST_PF_VAL_COND == '!' 
                || WS_LAST_PF_VAL_COND == 'Y')) {
                if (WS_TPINF_CHK_FLG == 'E') {
                    WS_CHK_TPINF_FLG = 'N';
                } else {
                    WS_CHK_TPINF_FLG = 'Y';
                }
            } else {
                if (WS_TPINF_CHK_FLG == 'E') {
                    WS_CHK_TPINF_FLG = 'Y';
                } else {
                    WS_CHK_TPINF_FLG = 'N';
                }
            }
            if (!WS_TPINF_INFO.WS_TPINF_CON_TYP[WS_N-1].equalsIgnoreCase(WS_LAST_PF_CON_TYP)) {
                WS_TPINF_CHK_FLG = ' ';
                WS_LAST_PF_CON_TYP = WS_TPINF_INFO.WS_TPINF_CON_TYP[WS_N-1];
                WS_LAST_PF_VAL_COND = WS_TPINF_INFO.WS_TPINF_VAL_COND[WS_N-1];
            }
        }
        CEP.TRC(SCCGWA, WS_QUIT_TYP_FLG);
        CEP.TRC(SCCGWA, WS_CHK_TPINF_FLG);
    }
    public void R000_CHK_TYP_MTX() throws IOException,SQLException,Exception {
        if (WS_TRS_FLG != ' ') {
            CICREGLC.TRS_FLG = WS_TRS_FLG;
        }
        WS_TRS_FLG = ' ';
        if (CIRTYP.MTX_CON_TYP1 == null) CIRTYP.MTX_CON_TYP1 = "";
        JIBS_tmp_int = CIRTYP.MTX_CON_TYP1.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.MTX_CON_TYP1 += " ";
        if (CIRTYP.MTX_CON_TYP1 == null) CIRTYP.MTX_CON_TYP1 = "";
        JIBS_tmp_int = CIRTYP.MTX_CON_TYP1.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.MTX_CON_TYP1 += " ";
        for (WS_Z = 1; CIRTYP.MTX_CON_TYP1.substring(WS_Z - 1, WS_Z + 2 - !1).equalsIgnoreCase("00") 
            && CIRTYP.MTX_CON_TYP1.substring(WS_Z - 1, WS_Z + 2 - 1).trim().length() != 0 
            && WS_Z < 10; WS_Z += 2) {
            if (CIRTYP.MTX_CON_TYP1 == null) CIRTYP.MTX_CON_TYP1 = "";
            JIBS_tmp_int = CIRTYP.MTX_CON_TYP1.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.MTX_CON_TYP1 += " ";
            if (WS_Z != 1 
                && CIRTYP.MTX_CON_TYP1.substring(WS_Z - 1, WS_Z + 2 - 1).equalsIgnoreCase("17")) {
                WS_TRS_FLG = CICREGLC.TRS_FLG;
                CICREGLC.TRS_FLG = ' ';
            }
        }
    }
    public void R000_CHK_TPINF_VALUE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRTPINF.KEY.CON_TYP);
        if (CIRTPINF.KEY.CON_TYP.equalsIgnoreCase("16")) {
            WS_CON_TYP_FLG = 'A';
            if (CICREGLC.CI_NO.trim().length() == 0) {
                WS_CUS_AC = CICREGLC.CUS_AC;
                WS_OPP_FLG = 'N';
                R000_GET_CI_NO();
                if (pgmRtn) return;
                CICREGLC.CI_NO = WS_CI_NO;
            }
            if (CICREGLC.OPP_CI_NO.trim().length() == 0 
                && CICREGLC.OPP_CUS_AC.trim().length() > 0) {
                WS_CUS_AC = CICREGLC.OPP_CUS_AC;
                WS_OPP_FLG = 'Y';
                R000_GET_CI_NO();
                if (pgmRtn) return;
                CICREGLC.OPP_CI_NO = WS_CI_NO;
            }
            if (CICREGLC.CI_NO.equalsIgnoreCase(CICREGLC.OPP_CI_NO)) {
                WS_TPINF_VAL = "Y";
            } else {
                WS_TPINF_VAL = "N";
            }
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                WS_TPINF_VAL = "N";
            }
        } else if (CIRTPINF.KEY.CON_TYP.equalsIgnoreCase("25")) {
            WS_CON_TYP_FLG = 'A';
            if (CICREGLC.CUS_AC.equalsIgnoreCase(CICREGLC.OPP_CUS_AC)) {
                WS_TPINF_VAL = "Y";
            } else {
                WS_TPINF_VAL = "N";
            }
        } else if (CIRTPINF.KEY.CON_TYP.equalsIgnoreCase("23")) {
            WS_CON_TYP_FLG = 'F';
            WS_TPINF_VAL = CICREGLC.MMO;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
        if (WS_CON_TYP_FLG == 'F') {
            if (CIRTPINF.KEY.VAL == null) CIRTPINF.KEY.VAL = "";
            JIBS_tmp_int = CIRTPINF.KEY.VAL.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) CIRTPINF.KEY.VAL += " ";
            for (WS_J = 1; CIRTPINF.KEY.VAL.substring(WS_J - 1, WS_J + 1 - 1).trim().length() != 0; WS_J += 1) {
            }
            WS_J = (short) (WS_J - 1);
            if (WS_TPINF_VAL == null) WS_TPINF_VAL = "";
            JIBS_tmp_int = WS_TPINF_VAL.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_TPINF_VAL += " ";
            if (WS_TPINF_INFO.WS_TPINF_VAL_IN[WS_N-1] == null) WS_TPINF_INFO.WS_TPINF_VAL_IN[WS_N-1] = "";
            JIBS_tmp_int = WS_TPINF_INFO.WS_TPINF_VAL_IN[WS_N-1].length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_TPINF_INFO.WS_TPINF_VAL_IN[WS_N-1] += " ";
            if (WS_TPINF_VAL.substring(0, WS_J).equalsIgnoreCase(WS_TPINF_INFO.WS_TPINF_VAL_IN[WS_N-1].substring(0, WS_J))) {
                WS_TPINF_CHK_FLG = 'E';
            }
        } else {
            if (WS_TPINF_VAL.equalsIgnoreCase(WS_TPINF_INFO.WS_TPINF_VAL_IN[WS_N-1])) {
                WS_TPINF_CHK_FLG = 'E';
            }
        }
        CEP.TRC(SCCGWA, WS_TPINF_VAL);
        CEP.TRC(SCCGWA, WS_TPINF_INFO.WS_TPINF_VAL_IN[WS_N-1]);
        CEP.TRC(SCCGWA, WS_TPINF_CHK_FLG);
    }
    public void R000_CHK_IF_PDTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGOPP);
        CIRGOPP.KEY.LC_OBJ_TYP = "" + WS_LC_OBJ_TYP;
        JIBS_tmp_int = CIRGOPP.KEY.LC_OBJ_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRGOPP.KEY.LC_OBJ_TYP = "0" + CIRGOPP.KEY.LC_OBJ_TYP;
        CIRGOPP.KEY.LC_OBJ = WS_LC_OBJ;
        CIRGOPP.KEY.TYPE = CIRTYP.KEY.TYPE;
        CIRGOPP.KEY.TX_TYPE = CIRCMPR.KEY.TX_TYPE;
        T000_READ_CITGOPP();
        if (pgmRtn) return;
        if (WS_GOPP_FLG == 'Y') {
            WS_GOPP_USE_FLG = 'Y';
        } else {
            WS_GOPP_USE_FLG = 'N';
        }
        if (WS_GOPP_USE_FLG == 'N' 
            && CICREGLC.CARD_LNK_TYP == '2') {
            if (CICREGLC.MAIN_CARD_NO.trim().length() == 0) {
                R000_GET_MAIN_CARD_NO();
                if (pgmRtn) return;
            }
            CIRGOPP.KEY.LC_OBJ = CICREGLC.MAIN_CARD_NO;
            T000_READ_CITGOPP();
            if (pgmRtn) return;
            if (WS_GOPP_FLG == 'Y') {
                WS_GOPP_USE_FLG = 'Y';
                WS_LC_OBJ = CICREGLC.MAIN_CARD_NO;
            }
        }
    }
    public void R000_CHK_DTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRDTL);
        CIRDTL.KEY.LC_NO = WS_LC_NO;
        CIRDTL.DAY_STA_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_DTL_QUIT_FLG = 'N';
        WS_DINF_QUIT_FLG = 'N';
        WS_V = 0;
        T000_STARTBR_CITDTL();
        if (pgmRtn) return;
        T000_READNEXT_CITDTL();
        if (pgmRtn) return;
        while (WS_DTL_FLG != 'Y' 
            && WS_DTL_QUIT_FLG != 'Y') {
            WS_LENTH = 104;
            if (CIRDTL.SX_VAL == null) CIRDTL.SX_VAL = "";
            JIBS_tmp_int = CIRDTL.SX_VAL.length();
            for (int i=0;i<104-JIBS_tmp_int;i++) CIRDTL.SX_VAL += " ";
            IBS.CPY2CLS(SCCGWA, CIRDTL.SX_VAL.substring(0, WS_LENTH), WS_B_SX_VAL_INF);
            if (!(CIRDTL.CON_TYP.equalsIgnoreCase(WS_LAST_DTL_CON_TYP) 
                && CIRDTL.LVL == WS_LAST_LVL 
                && WS_EXIT_FLG == 'Y')) {
                WS_EXIT_FLG = ' ';
                if (WS_DINF_QUIT_FLG != 'Y') {
                    R000_CHK_DINF_EQUAL();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "EQUALFLG");
                    CEP.TRC(SCCGWA, WS_CHECK_FLG);
                    CEP.TRC(SCCGWA, CIRDTL.KEY.LC_NO);
                    CEP.TRC(SCCGWA, CIRDTL.KEY.LC_SEQ);
                    if (WS_CHECK_FLG == 'E') {
                        R000_CHK_LVL_PROC();
                        if (pgmRtn) return;
                        if (WS_LVL_CHK_FLG == 'Y') {
                            R000_CHK_LC_PROC();
                            if (pgmRtn) return;
                        }
                        if (CIRDTL.LVL != 33) {
                            R000_GRT_LVL33_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
                CEP.TRC(SCCGWA, WS_CHECK_FLG);
                WS_DINF_QUIT_FLG = 'N';
            }
            T000_READNEXT_CITDTL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITDTL();
        if (pgmRtn) return;
    }
    public void R000_CHK_DINF_EQUAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRDINF);
        WS_RAG_FLG = ' ';
        IBS.init(SCCGWA, WS_DINF_INFO);
        WS_N = 0;
        WS_DINF_CHK_FLG = ' ';
        WS_LAST_CON_SEQ = 9999;
        if (!CIRDTL.CON_TYP.equalsIgnoreCase(WS_LAST_DTL_CON_TYP)) {
            WS_LAST_DTL_CON_TYP = CIRDTL.CON_TYP;
        }
        if (CIRDTL.LVL != WS_LAST_LVL) {
            WS_LAST_LVL = CIRDTL.LVL;
        }
        CIRDINF.KEY.LC_NO = CIRDTL.KEY.LC_NO;
        CIRDINF.KEY.LC_SEQ = CIRDTL.KEY.LC_SEQ;
        WS_HSTOT_PROC_FLG = 'N';
        T000_STARTBR_CITDINF();
        if (pgmRtn) return;
        T000_READNEXT_CITDINF();
        if (pgmRtn) return;
        WS_CHECK_FLG = 'E';
        WS_DINF_PROC_FLG = 'Y';
        while (WS_DINF_FLG != 'Y' 
            && WS_CHECK_FLG != 'N') {
            if (CIRDINF.KEY.CON_SEQ != WS_LAST_CON_SEQ) {
                WS_RAG_FLG = ' ';
                IBS.init(SCCGWA, WS_DINF_INFO);
                WS_N = 0;
                WS_DINF_CHK_FLG = ' ';
                WS_LAST_CON_TYP = CIRDINF.KEY.CON_TYP;
                WS_LAST_VAL_COND = CIRDINF.VAL_COND;
                WS_CHECK_FLG = 'E';
                WS_LAST_CON_SEQ = CIRDINF.KEY.CON_SEQ;
            }
            WS_N += 1;
            WS_DINF_INFO.WS_DINF_CON_TYP[WS_N-1] = CIRDINF.KEY.CON_TYP;
            WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] = CIRDINF.VAL;
            if (CIRDINF.KEY.CON_TYP.equalsIgnoreCase("12")) {
                WS_DINF_VAL_12 = CIRDINF.VAL;
            }
            if (CIRDINF.KEY.CON_TYP.equalsIgnoreCase("13")) {
                WS_DINF_VAL_13 = CIRDINF.VAL;
            }
            WS_DINF_INFO.WS_DINF_VAL_COND[WS_N-1] = CIRDINF.VAL_COND;
            R000_CHK_RES_EQUAL();
            if (pgmRtn) return;
            R000_CHK_VALUE_EQUAL();
            if (pgmRtn) return;
            T000_READNEXT_CITDINF();
            if (pgmRtn) return;
        }
        if (WS_N > 0) {
            R000_CHK_RES_EQUAL();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.PARM_IND != 'E' 
            || (SCCGWA.COMM_AREA.PARM_IND == 'E' 
            && CIRDINF.KEY.LC_NO > 600000000)) {
            T000_ENDBR_CITDINF();
            if (pgmRtn) return;
        }
    }
    public void R000_CHK_RES_EQUAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_N);
        CEP.TRC(SCCGWA, WS_CHECK_FLG);
        if ((!WS_DINF_INFO.WS_DINF_CON_TYP[WS_N-1].equalsIgnoreCase(WS_LAST_CON_TYP) 
            || WS_DINF_CHK_FLG == 'E' 
            || WS_DINF_FLG == 'Y') 
            && WS_CHECK_FLG != 'N') {
            CEP.TRC(SCCGWA, WS_DINF_CHK_FLG);
            CEP.TRC(SCCGWA, WS_DINF_FLG);
            CEP.TRC(SCCGWA, WS_DINF_INFO.WS_DINF_CON_TYP[WS_N-1]);
            CEP.TRC(SCCGWA, WS_LAST_CON_TYP);
            if ((WS_LAST_VAL_COND == '!' 
                || WS_LAST_VAL_COND == 'Y')) {
                if (WS_DINF_CHK_FLG == 'E') {
                    WS_CHECK_FLG = 'N';
                } else {
                    WS_CHECK_FLG = 'E';
                }
            } else {
                if (WS_DINF_CHK_FLG == 'E') {
                    WS_CHECK_FLG = 'E';
                } else {
                    WS_CHECK_FLG = 'N';
                }
            }
            if (!WS_DINF_INFO.WS_DINF_CON_TYP[WS_N-1].equalsIgnoreCase(WS_LAST_CON_TYP)) {
                WS_DINF_CHK_FLG = ' ';
                WS_LAST_CON_TYP = WS_DINF_INFO.WS_DINF_CON_TYP[WS_N-1];
                WS_LAST_VAL_COND = WS_DINF_INFO.WS_DINF_VAL_COND[WS_N-1];
            }
        }
        CEP.TRC(SCCGWA, "EQUALFLG");
        CEP.TRC(SCCGWA, WS_CHECK_FLG);
    }
    public void R000_CHK_VALUE_EQUAL() throws IOException,SQLException,Exception {
        WS_CON_TYP = WS_DINF_INFO.WS_DINF_CON_TYP[WS_N-1];
        if (WS_CON_TYP.trim().length() == 0) WS_D = 0;
        else WS_D = Short.parseShort(WS_CON_TYP);
        if (WS_CON_TYP_LW == null) WS_CON_TYP_LW = "";
        JIBS_tmp_int = WS_CON_TYP_LW.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CON_TYP_LW += " ";
        if (WS_CON_TYP_LW.substring(WS_D - 1, WS_D + 1 - 1).equalsIgnoreCase("1") 
            || WS_HSTOT_PROC_FLG == 'N') {
            R000_GEN_DINF_VAL();
            if (pgmRtn) return;
        } else {
            R000_GET_DINF_VAL_FROM_HSTOT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_DINF_VAL);
        CEP.TRC(SCCGWA, WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1]);
        CEP.TRC(SCCGWA, WS_CON_TYP_FLG);
        R000_CHECK_DINF_VAL();
        if (pgmRtn) return;
        if ((WS_DINF_CHK_FLG != 'E') 
            && (WS_DINF_VAL1.trim().length() > 0 
            && !WS_DINF_VAL1.equalsIgnoreCase("0"))) {
            WS_DINF_VAL = WS_DINF_VAL1;
            R000_CHECK_DINF_VAL();
            if (pgmRtn) return;
            WS_DINF_VAL1 = " ";
        }
        CEP.TRC(SCCGWA, WS_DINF_CHK_FLG);
    }
    public void R000_CHECK_DINF_VAL() throws IOException,SQLException,Exception {
        if (WS_CON_TYP_FLG == 'A') {
            if (WS_DINF_VAL.equalsIgnoreCase(WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1])) {
                WS_DINF_CHK_FLG = 'E';
            }
        }
        if (WS_CON_TYP_FLG == 'B') {
            if (WS_HSTOT_PROC_FLG == 'N') {
                if (WS_DINF_VAL.compareTo(WS_DINF_VAL_1) >= 0 
                    && WS_DINF_VAL.compareTo(WS_DINF_VAL_2) <= 0) {
                    WS_DINF_CHK_FLG = 'E';
                }
            } else {
                if (WS_HSTOT_PROC_FLG == 'Y') {
                    if (WS_DINF_VAL == null) WS_DINF_VAL = "";
                    JIBS_tmp_int = WS_DINF_VAL.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) WS_DINF_VAL += " ";
                    if (WS_DINF_VAL == null) WS_DINF_VAL = "";
                    JIBS_tmp_int = WS_DINF_VAL.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) WS_DINF_VAL += " ";
                    if (WS_DINF_VAL.substring(0, 8).compareTo(WS_DINF_VAL_1) >= 0 
                        && WS_DINF_VAL.substring(9 - 1, 9 + 8 - 1).compareTo(WS_DINF_VAL_2) <= 0) {
                        WS_DINF_CHK_FLG = 'E';
                    }
                }
            }
        }
        if (WS_CON_TYP_FLG == 'F') {
            if (WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] == null) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] = "";
            JIBS_tmp_int = WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] += " ";
            for (WS_J = 1; WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].substring(WS_J - 1, WS_J + 1 - 1).trim().length() != 0; WS_J += 1) {
            }
            WS_J = (short) (WS_J - 1);
            if (WS_DINF_VAL == null) WS_DINF_VAL = "";
            JIBS_tmp_int = WS_DINF_VAL.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_DINF_VAL += " ";
            if (WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] == null) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] = "";
            JIBS_tmp_int = WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] += " ";
            if (WS_DINF_VAL.substring(0, WS_J).equalsIgnoreCase(WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].substring(0, WS_J))) {
                WS_DINF_CHK_FLG = 'E';
            }
        }
    }
    public void R000_GEN_DINF_VAL() throws IOException,SQLException,Exception {
        WS_DINF_VAL = " ";
        WS_CON_TYP_FLG = ' ';
        if (WS_CON_TYP.equalsIgnoreCase("01")) {
            if (CICREGLC.PROD_CD.trim().length() == 0) {
                R000_GET_PROD_CD();
                if (pgmRtn) return;
            }
            WS_DINF_VAL = CICREGLC.PROD_CD;
            WS_DINF_VAL1 = CICREGLC.ACO_PROD_CD;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("02")) {
            WS_DINF_VAL = "" + CICREGLC.CITY_FLG;
            JIBS_tmp_int = WS_DINF_VAL.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("03")) {
            if (CICREGLC.TX_CHNL_NO.trim().length() == 0) {
                CICREGLC.TX_CHNL_NO = SCCGWA.COMM_AREA.CHNL;
            }
            WS_DINF_VAL = CICREGLC.TX_CHNL_NO;
            WS_CON_TYP_FLG = 'F';
        } else if (WS_CON_TYP.equalsIgnoreCase("04")) {
            WS_DINF_VAL = "" + CICREGLC.AC_TYP;
            JIBS_tmp_int = WS_DINF_VAL.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("05")) {
            WS_DINF_VAL = CICREGLC.CCY;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("06")) {
            WS_DINF_VAL = "" + CICREGLC.AC_BR;
            JIBS_tmp_int = WS_DINF_VAL.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("07")) {
            if (WS_DINF_VAL == null) WS_DINF_VAL = "";
            JIBS_tmp_int = WS_DINF_VAL.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_DINF_VAL += " ";
            if (CICREGLC.CCY == null) CICREGLC.CCY = "";
            JIBS_tmp_int = CICREGLC.CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) CICREGLC.CCY += " ";
            WS_DINF_VAL = CICREGLC.CCY + WS_DINF_VAL.substring(3);
            if (WS_DINF_VAL == null) WS_DINF_VAL = "";
            JIBS_tmp_int = WS_DINF_VAL.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_DINF_VAL += " ";
            JIBS_tmp_str[0] = "" + CICREGLC.CCY_TYP;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_DINF_VAL = WS_DINF_VAL.substring(0, 4 - 1) + JIBS_tmp_str[0] + WS_DINF_VAL.substring(4 + 1 - 1);
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("08")) {
        } else if (WS_CON_TYP.equalsIgnoreCase("09")) {
            if (CICREGLC.TX_BR == 0) {
                WS_DINF_VAL = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                JIBS_tmp_int = WS_DINF_VAL.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            } else {
                WS_DINF_VAL = "" + CICREGLC.TX_BR;
                JIBS_tmp_int = WS_DINF_VAL.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            }
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("10")) {
            WS_DINF_VAL = CICREGLC.OPP_CUS_AC;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("11")) {
            WS_DINF_VAL = CICREGLC.TX_COUNTRY;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("13")) {
            WS_DINF_VAL = "" + SCCGWA.COMM_AREA.TR_TIME;
            JIBS_tmp_int = WS_DINF_VAL.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            WS_CON_TYP_FLG = 'B';
            if (WS_DINF_PROC_FLG == 'Y') {
                WS_RAG_FLG = 'Y';
                if (WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] == null) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] = "";
                JIBS_tmp_int = WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] += " ";
                WS_DINF_VAL_1 = WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].substring(0, 6);
                if (WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] == null) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] = "";
                JIBS_tmp_int = WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] += " ";
                WS_DINF_VAL_2 = WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].substring(7 - 1, 7 + 6 - 1);
            }
        } else if (WS_CON_TYP.equalsIgnoreCase("12")) {
            if (CICREGLC.TX_DATE == 0) {
                WS_DINF_VAL = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = WS_DINF_VAL.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            } else {
                WS_DINF_VAL = "" + CICREGLC.TX_DATE;
                JIBS_tmp_int = WS_DINF_VAL.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            }
            WS_CON_TYP_FLG = 'B';
            if (WS_DINF_PROC_FLG == 'Y') {
                WS_RAG_FLG = 'Y';
                if (WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] == null) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] = "";
                JIBS_tmp_int = WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] += " ";
                WS_DINF_VAL_1 = WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].substring(0, 8);
                if (WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] == null) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] = "";
                JIBS_tmp_int = WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1] += " ";
                WS_DINF_VAL_2 = WS_DINF_INFO.WS_DINF_VAL_IN[WS_N-1].substring(9 - 1, 9 + 8 - 1);
            }
        } else if (WS_CON_TYP.equalsIgnoreCase("14")) {
            if ((CICREGLC.AC_TYP == '2' 
                || CICREGLC.AC_TYP == '3')) {
                if (CICREGLC.HS_FLG == ' ') {
                    R000_GET_HS_FLG();
                    if (pgmRtn) return;
                } else {
                    WS_DINF_VAL = "" + CICREGLC.HS_FLG;
                    JIBS_tmp_int = WS_DINF_VAL.length();
                    for (int i=0;i<1-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
                }
            }
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("15")) {
            WS_DINF_VAL = "" + CICREGLC.BV_TYP;
            JIBS_tmp_int = WS_DINF_VAL.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("16")) {
            if (CICREGLC.CI_NO.trim().length() == 0) {
                if (CICREGLC.CUS_AC.trim().length() > 0) {
                    WS_CUS_AC = CICREGLC.CUS_AC;
                    WS_OPP_FLG = 'N';
                    R000_GET_CI_NO();
                    if (pgmRtn) return;
                    CICREGLC.CI_NO = WS_CI_NO;
                }
            }
            if (CICREGLC.OPP_CI_NO.trim().length() == 0) {
                if (CICREGLC.OPP_CUS_AC.trim().length() > 0) {
                    WS_CUS_AC = CICREGLC.OPP_CUS_AC;
                    WS_OPP_FLG = 'Y';
                    R000_GET_CI_NO();
                    if (pgmRtn) return;
                    CICREGLC.OPP_CI_NO = WS_CI_NO;
                }
            }
            if (CICREGLC.CI_NO.equalsIgnoreCase(CICREGLC.OPP_CI_NO)) {
                WS_DINF_VAL = "Y";
            } else {
                WS_DINF_VAL = "N";
            }
            WS_CON_TYP_FLG = 'A';
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                WS_DINF_VAL = "N";
            }
        } else if (WS_CON_TYP.equalsIgnoreCase("17")) {
            WS_DINF_VAL = "" + CICREGLC.TRS_FLG;
            JIBS_tmp_int = WS_DINF_VAL.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("18")) {
            WS_DINF_VAL = CICREGLC.TX_TYP;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("19")) {
            WS_DINF_VAL = CICREGLC.OTH_BK;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("20")) {
            if (CICREGLC.BD_FLG == ' ') {
                R000_GET_BD_FLG();
                if (pgmRtn) return;
            }
            WS_DINF_VAL = "" + CICREGLC.BD_FLG;
            JIBS_tmp_int = WS_DINF_VAL.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("21")) {
            if (CICREGLC.CI_TYP == ' ') {
                WS_CUS_AC = CICREGLC.CUS_AC;
                WS_OPP_FLG = 'N';
                R000_GET_CI_NO();
                if (pgmRtn) return;
            }
            WS_DINF_VAL = "" + CICREGLC.CI_TYP;
            JIBS_tmp_int = WS_DINF_VAL.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("23")) {
            WS_DINF_VAL = CICREGLC.MMO;
            WS_CON_TYP_FLG = 'F';
        } else if (WS_CON_TYP.equalsIgnoreCase("22")) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.TX_TYPE = 'I';
            DDCIMMST.DATA.KEY.AC_NO = CICREGLC.CUS_AC;
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            if (DDCIMMST.RC.RC_CODE != 0) {
                WS_DINF_VAL = " ";
            }
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD.substring(23 - 1, 23 + 1 - 1));
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            if (DDCIMMST.DATA.AC_STS_WORD.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                WS_DINF_VAL = "23";
            }
            WS_CON_TYP_FLG = 'A';
        } else if (WS_CON_TYP.equalsIgnoreCase("24")) {
            if (CICREGLC.OPP_CUS_AC.trim().length() > 0) {
                IBS.init(SCCGWA, DDCIMMST);
                DDCIMMST.TX_TYPE = 'I';
                DDCIMMST.DATA.KEY.AC_NO = CICREGLC.OPP_CUS_AC;
                S000_CALL_DDZIMMST();
                if (pgmRtn) return;
                if (DDCIMMST.RC.RC_CODE != 0) {
                    WS_DINF_VAL = " ";
                }
                CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_TYPE);
                WS_DINF_VAL = "" + DDCIMMST.DATA.AC_TYPE;
                JIBS_tmp_int = WS_DINF_VAL.length();
                for (int i=0;i<1-JIBS_tmp_int;i++) WS_DINF_VAL = "0" + WS_DINF_VAL;
            }
            WS_CON_TYP_FLG = 'A';
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void R000_GRT_LVL33_PROC() throws IOException,SQLException,Exception {
        if (CIRDTL.LVL == 99) {
            WS_DTL_QUIT_FLG = 'Y';
        }
        if (CIRDTL.LVL == 66) {
            WS_K += 1;
            WS_CON_TYP_50[WS_K-1] = CIRDTL.CON_TYP;
            for (WS_L = 1; WS_L <= 50; WS_L += 1) {
                if (CIRDTL.LVL_CON_TYP == null) CIRDTL.LVL_CON_TYP = "";
                JIBS_tmp_int = CIRDTL.LVL_CON_TYP.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) CIRDTL.LVL_CON_TYP += " ";
                if (CIRDTL.LVL_CON_TYP.substring(WS_L - 1, WS_L + 1 - 1).equalsIgnoreCase("1")) {
                    WS_CON_TYP_50[WS_K-1] = "0";
                    if (WS_CON_TYP_50[WS_K-1] == null) WS_CON_TYP_50[WS_K-1] = "";
                    JIBS_tmp_int = WS_CON_TYP_50[WS_K-1].length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) WS_CON_TYP_50[WS_K-1] += " ";
                    WS_CON_TYP_50[WS_K-1] = WS_CON_TYP_50[WS_K-1].substring(0, WS_L - 1) + "0" + WS_CON_TYP_50[WS_K-1].substring(WS_L + 1 - 1);
                }
            }
        }
    }
    public void R000_CHK_LVL_PROC() throws IOException,SQLException,Exception {
        WS_LVL_CHK_FLG = 'Y';
        if (CIRDTL.LVL == 33) {
            for (WS_L = 1; WS_L <= WS_K; WS_L += 1) {
                if (WS_CON_TYP_50[WS_L-1].equalsIgnoreCase(CIRDTL.CON_TYP)) {
                    WS_LVL_CHK_FLG = 'N';
                }
            }
        }
    }
    public void R000_CHK_LC_PROC() throws IOException,SQLException,Exception {
        R000_GEN_HSTOT_INFO();
        if (pgmRtn) return;
        if (CICREGLC.FUNC == '0') {
            R000_GEN_USHIS_INFO();
            if (pgmRtn) return;
        }
    }
    public void R000_GEN_HSTOT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRHSTOT);
        WS_MAX_NUM = 0;
        IBS.init(SCCGWA, WS_AMT_INFO);
        WS_IDX = 0;
        IBS.init(SCCGWA, WS_HS_VAL_INF);
        if (WS_RAG_FLG == 'Y') {
            WS_TYP_CON_TYP = CIRDTL.CON_TYP;
            CIRHSTOT.KEY.RAG_FLG = '1';
            WS_IN_VAL_INF.WS_IN_RAG_FLG = '1';
            WS_HS_VAL_INF.WS_HS_RAG_FLG = '1';
        } else {
            if (CIRTYP.KEY.TYPE.equalsIgnoreCase("01")) {
                WS_TYP_CON_TYP = WS_TYP_CON_TYP_01;
            } else {
                WS_TYP_CON_TYP = CIRDTL.CON_TYP;
            }
        }
        if (WS_CON_VAL_MTH == null) WS_CON_VAL_MTH = "";
        JIBS_tmp_int = WS_CON_VAL_MTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CON_VAL_MTH += " ";
        WS_CON_VAL_MTH = WS_CON_VAL_MTH.substring(0, 12 - 1) + "3" + WS_CON_VAL_MTH.substring(12 + 1 - 1);
        if (WS_CON_VAL_MTH == null) WS_CON_VAL_MTH = "";
        JIBS_tmp_int = WS_CON_VAL_MTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CON_VAL_MTH += " ";
        WS_CON_VAL_MTH = WS_CON_VAL_MTH.substring(0, 13 - 1) + "3" + WS_CON_VAL_MTH.substring(13 + 1 - 1);
        WS_CON_VAL_MTH = CIRTYP.CON_VAL_MTH;
        CEP.TRC(SCCGWA, WS_CON_VAL_MTH);
        CEP.TRC(SCCGWA, WS_TYP_CON_TYP);
        CEP.TRC(SCCGWA, WS_CON_TYP_LW);
        for (WS_M = 1; WS_M <= 50; WS_M += 1) {
            if (WS_TYP_CON_TYP == null) WS_TYP_CON_TYP = "";
            JIBS_tmp_int = WS_TYP_CON_TYP.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_TYP_CON_TYP += " ";
            if (WS_CON_TYP_LW == null) WS_CON_TYP_LW = "";
            JIBS_tmp_int = WS_CON_TYP_LW.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CON_TYP_LW += " ";
            if (CIRDTL.CON_TYP == null) CIRDTL.CON_TYP = "";
            JIBS_tmp_int = CIRDTL.CON_TYP.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) CIRDTL.CON_TYP += " ";
            if (WS_CON_VAL_MTH == null) WS_CON_VAL_MTH = "";
            JIBS_tmp_int = WS_CON_VAL_MTH.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CON_VAL_MTH += " ";
            if (WS_TYP_CON_TYP.substring(WS_M - 1, WS_M + 1 - 1).equalsIgnoreCase("1") 
                && !WS_CON_TYP_LW.substring(WS_M - 1, WS_M + 1 - 1).equalsIgnoreCase("1") 
                && !(CIRDTL.CON_TYP.substring(WS_M - 1, WS_M + 1 - 1).equalsIgnoreCase("0") 
                && WS_CON_VAL_MTH.substring(WS_M - 1, WS_M + 1 - 1).equalsIgnoreCase("3"))) {
                WS_D = WS_M;
                WS_CON_TYP = "" + WS_D;
                JIBS_tmp_int = WS_CON_TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) WS_CON_TYP = "0" + WS_CON_TYP;
                WS_IDX += 1;
                R000_GEN_HSTOT_INDEX();
                if (pgmRtn) return;
            }
        }
        CIRHSTOT.KEY.LC_OBJ_TYP = WS_LC_OBJ_TYP;
        CIRHSTOT.KEY.LC_OBJ = WS_LC_OBJ;
        CIRHSTOT.LC_NO = CIRDTL.KEY.LC_NO;
        CIRHSTOT.LC_SEQ = CIRDTL.KEY.LC_SEQ;
        CIRHSTOT.KEY.TYPE = WS_TYPE;
        CIRHSTOT.KEY.TX_TYPE = WS_TX_TYPE;
        WS_HSTOT_INS_FLG = 'Y';
        WS_HSTOT_PROC_FLG = 'Y';
        R000_CHECK_IF_EC_END();
        if (pgmRtn) return;
        if (WS_RAG_FLG == 'Y') {
            T000_STARTBR_CITHSTOT_LC_NO();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_CITHSTOT_TYPE();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITHSTOT();
        if (pgmRtn) return;
        while (WS_HSTOT_FLG != 'N') {
            WS_LTH = 469;
            if (CIRHSTOT.BLOB_LC_VAL == null) CIRHSTOT.BLOB_LC_VAL = "";
            JIBS_tmp_int = CIRHSTOT.BLOB_LC_VAL.length();
            for (int i=0;i<2000-JIBS_tmp_int;i++) CIRHSTOT.BLOB_LC_VAL += " ";
            IBS.CPY2CLS(SCCGWA, CIRHSTOT.BLOB_LC_VAL.substring(0, WS_LTH), WS_HS_VAL_INF);
            R000_CHK_HSTOT_EQUAL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_EQUAL_FLG);
            if (WS_EQUAL_FLG == 'A') {
                WS_HSTOT_INS_FLG = 'N';
                if (WS_EC_FLG == 'N') {
                    R000_REGEN_HSTOT();
                    if (pgmRtn) return;
                    if (CICREGLC.FUNC == '0') {
                        T000_REWRITE_CITHSTOT();
                        if (pgmRtn) return;
                    }
                }
                R000_GEN_AMT_PROC();
                if (pgmRtn) return;
            } else {
                if (WS_EQUAL_FLG == 'Y') {
                    R000_GEN_AMT_PROC();
                    if (pgmRtn) return;
                }
            }
            if (WS_MAX_NUM < CIRHSTOT.KEY.SEQ) {
                WS_MAX_NUM = CIRHSTOT.KEY.SEQ;
            }
            T000_READNEXT_CITHSTOT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_HSTOT_INS_FLG);
        if (WS_HSTOT_INS_FLG == 'Y' 
            && WS_EC_FLG == 'N') {
            IBS.init(SCCGWA, CIRHSTOT);
            if (WS_RAG_FLG == 'Y') {
                CIRHSTOT.KEY.RAG_FLG = '1';
            }
            R000_REGEN_HSTOT();
            if (pgmRtn) return;
            WS_MAX_NUM += 1;
            CIRHSTOT.KEY.SEQ = WS_MAX_NUM;
            CEP.TRC(SCCGWA, WS_MAX_NUM);
            if (CICREGLC.FUNC == '0') {
                T000_INSERT_CITHSTOT();
                if (pgmRtn) return;
            }
            R000_GEN_AMT_PROC();
            if (pgmRtn) return;
        }
        R000_CHK_IF_EXCEED();
        if (pgmRtn) return;
        T000_ENDBR_CITHSTOT();
        if (pgmRtn) return;
    }
    public void R000_GEN_HSTOT_INDEX() throws IOException,SQLException,Exception {
        WS_DINF_PROC_FLG = 'N';
        R000_GEN_DINF_VAL();
        if (pgmRtn) return;
        if (WS_CON_TYP_FLG == 'B') {
            if (WS_M == 12) {
                WS_DINF_VAL = WS_DINF_VAL_12;
            }
            if (WS_M == 13) {
                WS_DINF_VAL = WS_DINF_VAL_13;
            }
        }
        if (WS_CON_TYP.trim().length() == 0) WS_IN_VAL_INF.WS_IN_VAL_INFO[WS_IDX-1].WS_IN_CON_TYP = 0;
        else WS_IN_VAL_INF.WS_IN_VAL_INFO[WS_IDX-1].WS_IN_CON_TYP = Short.parseShort(WS_CON_TYP);
        WS_IN_VAL_INF.WS_IN_VAL_INFO[WS_IDX-1].WS_IN_VAL = WS_DINF_VAL;
        CEP.TRC(SCCGWA, WS_IDX);
        CEP.TRC(SCCGWA, WS_IN_VAL_INF.WS_IN_VAL_INFO[WS_IDX-1].WS_IN_CON_TYP);
        CEP.TRC(SCCGWA, WS_IN_VAL_INF.WS_IN_VAL_INFO[WS_IDX-1].WS_IN_VAL);
    }
    public void R000_GET_DINF_VAL_FROM_HSTOT() throws IOException,SQLException,Exception {
        WS_CON_TYP_FLG = ' ';
        for (WS_Q = 1; WS_Q <= 9; WS_Q += 1) {
            if (WS_HS_VAL_INF.WS_HS_VAL_INFO[WS_Q-1].WS_HS_CON_TYP == WS_CON_TYP) {
                WS_DINF_VAL = WS_HS_VAL_INF.WS_HS_VAL_INFO[WS_Q-1].WS_HS_VAL;
            }
        }
        if (WS_CON_TYP.trim().length() == 0) WS_D = 0;
        else WS_D = Short.parseShort(WS_CON_TYP);
        if (WS_CON_VAL_MTH == null) WS_CON_VAL_MTH = "";
        JIBS_tmp_int = WS_CON_VAL_MTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CON_VAL_MTH += " ";
        if (WS_CON_VAL_MTH.substring(WS_D - 1, WS_D + 1 - 1).equalsIgnoreCase("1")) {
            WS_CON_TYP_FLG = 'A';
        }
        if (WS_CON_VAL_MTH == null) WS_CON_VAL_MTH = "";
        JIBS_tmp_int = WS_CON_VAL_MTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CON_VAL_MTH += " ";
        if (WS_CON_VAL_MTH.substring(WS_D - 1, WS_D + 1 - 1).equalsIgnoreCase("2")) {
            WS_CON_TYP_FLG = 'F';
        }
        if (WS_CON_VAL_MTH == null) WS_CON_VAL_MTH = "";
        JIBS_tmp_int = WS_CON_VAL_MTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CON_VAL_MTH += " ";
        if (WS_CON_VAL_MTH.substring(WS_D - 1, WS_D + 1 - 1).equalsIgnoreCase("3")) {
            WS_CON_TYP_FLG = 'B';
        }
    }
    public void R000_CHK_HSTOT_EQUAL() throws IOException,SQLException,Exception {
        WS_DINF_CHK_FLG = ' ';
        WS_DINF_FLG = ' ';
        WS_LAST_CON_TYP = WS_DINF_INFO.WS_DINF_CON_TYP[1-1];
        WS_LAST_VAL_COND = WS_DINF_INFO.WS_DINF_VAL_COND[1-1];
        WS_CHECK_FLG = 'E';
        WS_EQUAL_FLG = 'N';
        CEP.TRC(SCCGWA, WS_DINF_INFO.WS_DINF_CON_TYP[1-1]);
        CEP.TRC(SCCGWA, WS_DINF_INFO.WS_DINF_CON_TYP[2-1]);
        for (WS_N = 1; WS_N <= 50 
            && WS_CHECK_FLG != 'N' 
            && WS_DINF_INFO.WS_DINF_CON_TYP[WS_N-1].trim().length() != 0; WS_N += 1) {
            R000_CHK_RES_EQUAL();
            if (pgmRtn) return;
            R000_CHK_VALUE_EQUAL();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_N);
        CEP.TRC(SCCGWA, WS_CHECK_FLG);
        R000_CHK_RES_EQUAL();
        if (pgmRtn) return;
        if (WS_CHECK_FLG == 'E') {
            R000_CHECK_IF_EQUAL_A();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_IF_EQUAL_A() throws IOException,SQLException,Exception {
        WS_EQUAL_FLG = 'A';
        for (WS_Q = 1; WS_Q <= 9; WS_Q += 1) {
            CEP.TRC(SCCGWA, WS_IN_VAL_INF.WS_IN_VAL_INFO[WS_Q-1].WS_IN_VAL);
            CEP.TRC(SCCGWA, WS_HS_VAL_INF.WS_HS_VAL_INFO[WS_Q-1].WS_HS_VAL);
            if (!WS_IN_VAL_INF.WS_IN_VAL_INFO[WS_Q-1].WS_IN_VAL.equalsIgnoreCase(WS_HS_VAL_INF.WS_HS_VAL_INFO[WS_Q-1].WS_HS_VAL)) {
                WS_EQUAL_FLG = 'Y';
            }
        }
    }
    public void R000_REGEN_HSTOT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICREGLC.CCY);
        CEP.TRC(SCCGWA, CIRDTL.LC_CCY);
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_NO);
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_SEQ);
        WS_TX_AMT = CICREGLC.AMT;
        WS_BAL = CICREGLC.BAL;
        if (!CICREGLC.CCY.equalsIgnoreCase(CIRDTL.LC_CCY)) {
            WS_AMT = WS_TX_AMT;
            R000_GET_EX_AMT();
            if (pgmRtn) return;
            WS_TX_AMT = WS_AMT;
            if (WS_BAL != 0) {
                WS_AMT = WS_BAL;
                R000_GET_EX_AMT();
                if (pgmRtn) return;
                WS_BAL = WS_AMT;
            }
        }
        CIRHSTOT.KEY.LC_OBJ = WS_LC_OBJ;
        CIRHSTOT.KEY.LC_OBJ_TYP = WS_LC_OBJ_TYP;
        CIRHSTOT.LC_NO = CIRDTL.KEY.LC_NO;
        CIRHSTOT.LC_SEQ = CIRDTL.KEY.LC_SEQ;
        CIRHSTOT.KEY.TYPE = WS_TYPE;
        CIRHSTOT.KEY.TX_TYPE = WS_TX_TYPE;
        CIRHSTOT.CCY = CIRDTL.LC_CCY;
        CIRHSTOT.CUS_AC = CICREGLC.CUS_AC;
        if (CIRHSTOT.LAST_UPD_DT != SCCGWA.COMM_AREA.AC_DATE) {
            CIRHSTOT.LAST_DLY_TOT_AMT = CIRHSTOT.DLY_TOT_AMT;
            CIRHSTOT.DLY_TOT_AMT = WS_TX_AMT;
            CIRHSTOT.LAST_DLY_TOT_VOL = CIRHSTOT.DLY_TOT_VOL;
            CIRHSTOT.DLY_TOT_VOL = 1;
        } else {
            CIRHSTOT.DLY_TOT_AMT += WS_TX_AMT;
            CIRHSTOT.DLY_TOT_VOL += 1;
        }
        WS_DAY1 = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DAY1+"", REDEFINES68);
        WS_DAY2 = CIRHSTOT.LAST_UPD_DT;
        IBS.CPY2CLS(SCCGWA, WS_DAY2+"", REDEFINES73);
        if ((REDEFINES68.WS_MM1 != REDEFINES73.WS_MM2) 
            || (REDEFINES68.WS_YY1 != REDEFINES73.WS_YY2)) {
            CIRHSTOT.LAST_MLY_TOT_AMT = CIRHSTOT.MLY_TOT_AMT;
            CIRHSTOT.MLY_TOT_AMT = WS_TX_AMT;
            CIRHSTOT.LAST_MLY_TOT_VOL = CIRHSTOT.MLY_TOT_VOL;
            CIRHSTOT.MLY_TOT_VOL = 1;
        } else {
            CIRHSTOT.MLY_TOT_AMT += WS_TX_AMT;
            CIRHSTOT.MLY_TOT_VOL += 1;
        }
        WS_MM_COMP1 = (short) (REDEFINES68.WS_MM1 - 1);
        WS_MM_COMP2 = (short) (REDEFINES73.WS_MM2 - 1);
        WS_MM1_RES = (short) ((WS_MM_COMP1 - WS_MM_COMP1 % 6) / 6);
        WS_MM2_RES = (short) ((WS_MM_COMP2 - WS_MM_COMP2 % 6) / 6);
        if (REDEFINES68.WS_MM1 > 6) {
            CIRHSTOT.LAST_SYY_TOT_AMT = CIRHSTOT.SYY_TOT_AMT;
            CIRHSTOT.SYY_TOT_AMT = WS_TX_AMT;
        } else {
            CIRHSTOT.SYY_TOT_AMT += WS_TX_AMT;
        }
        if (REDEFINES68.WS_YY1 != REDEFINES73.WS_YY2) {
            CIRHSTOT.YLY_TOT_AMT = WS_TX_AMT;
        } else {
            CIRHSTOT.YLY_TOT_AMT += WS_TX_AMT;
        }
        if (CIRDTL.CON_TYP == null) CIRDTL.CON_TYP = "";
        JIBS_tmp_int = CIRDTL.CON_TYP.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) CIRDTL.CON_TYP += " ";
        if (CIRDTL.CON_TYP.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            CIRHSTOT.SE_DAY_LMT_AMT += WS_TX_AMT;
        }
        if (CIRDTL.CON_TYP == null) CIRDTL.CON_TYP = "";
        JIBS_tmp_int = CIRDTL.CON_TYP.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) CIRDTL.CON_TYP += " ";
        if (CIRDTL.CON_TYP.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            if (CIRHSTOT.LAST_UPD_DT != SCCGWA.COMM_AREA.AC_DATE) {
                CIRHSTOT.SE_TM_LMT_AMT = WS_TX_AMT;
            } else {
                CIRHSTOT.SE_TM_LMT_AMT += WS_TX_AMT;
            }
        }
        CIRHSTOT.LMT_AMT_1 += WS_TX_AMT;
        if (CIRHSTOT.CRT_DATE == 0) {
            CIRHSTOT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (CIRHSTOT.CRT_TLR.trim().length() == 0) {
            CIRHSTOT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        CIRHSTOT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRHSTOT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (CIRHSTOT.LAST_UPD_DT != SCCGWA.COMM_AREA.AC_DATE) {
            CIRHSTOT.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_IN_VAL_INF);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_HS_VAL_INF);
    }
    public void R000_GEN_AMT_PROC() throws IOException,SQLException,Exception {
        if (CIRHSTOT.LAST_UPD_DT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_AMT_INFO.WS_DLY_TOT_AMT += CIRHSTOT.DLY_TOT_AMT;
            WS_AMT_INFO.WS_DLY_TOT_VOL += CIRHSTOT.DLY_TOT_VOL;
        }
        WS_DAY1 = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DAY1+"", REDEFINES68);
        WS_DAY2 = CIRHSTOT.LAST_UPD_DT;
        IBS.CPY2CLS(SCCGWA, WS_DAY2+"", REDEFINES73);
        if ((REDEFINES68.WS_MM1 == REDEFINES73.WS_MM2) 
            && (REDEFINES68.WS_YY1 == REDEFINES73.WS_YY2)) {
            WS_AMT_INFO.WS_MLY_TOT_AMT += CIRHSTOT.MLY_TOT_AMT;
            WS_AMT_INFO.WS_MLY_TOT_VOL += CIRHSTOT.MLY_TOT_VOL;
        }
        WS_MM_COMP1 = (short) (REDEFINES68.WS_MM1 - 1);
        WS_MM_COMP2 = (short) (REDEFINES73.WS_MM2 - 1);
        WS_MM1_RES = (short) ((WS_MM_COMP1 - WS_MM_COMP1 % 6) / 6);
        WS_MM2_RES = (short) ((WS_MM_COMP2 - WS_MM_COMP2 % 6) / 6);
        if ((WS_MM1_RES == WS_MM2_RES) 
            && (REDEFINES68.WS_YY1 == REDEFINES73.WS_YY2)) {
            WS_AMT_INFO.WS_SYY_TOT_AMT += CIRHSTOT.SYY_TOT_AMT;
        }
        if (REDEFINES68.WS_YY1 == REDEFINES73.WS_YY2) {
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
        WS_AMT_INFO.WS_LMT_AMT_1 += WS_TX_AMT;
    }
    public void R000_GEN_USHIS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRUSHIS);
        CIRUSHIS.KEY.LC_OBJ = WS_LC_OBJ;
        CIRUSHIS.KEY.LC_OBJ_TYP = "" + WS_LC_OBJ_TYP;
        JIBS_tmp_int = CIRUSHIS.KEY.LC_OBJ_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRUSHIS.KEY.LC_OBJ_TYP = "0" + CIRUSHIS.KEY.LC_OBJ_TYP;
        CIRUSHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (CICREGLC.HLD_NO.trim().length() > 0) {
            WS_HOJ.WS_HOJ_FLG = '1';
            WS_HOJ.WS_HOJ_INFO = CICREGLC.HLD_NO;
        } else {
            WS_HOJ.WS_HOJ_FLG = '0';
            WS_HOJ.WS_HOJ_INFO = "" + SCCGWA.COMM_AREA.JRN_NO;
            JIBS_tmp_int = WS_HOJ.WS_HOJ_INFO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) WS_HOJ.WS_HOJ_INFO = "0" + WS_HOJ.WS_HOJ_INFO;
        }
        CIRUSHIS.KEY.JRN_NO = IBS.CLS2CPY(SCCGWA, WS_HOJ);
        R000_GET_SEQ();
        if (pgmRtn) return;
        CIRUSHIS.KEY.SEQ = WS_SEQ;
        CIRUSHIS.KEY.LC_NO = CIRDTL.KEY.LC_NO;
        CIRUSHIS.KEY.LC_SEQ = CIRDTL.KEY.LC_SEQ;
        CIRUSHIS.EC_FLG = ' ';
        CIRUSHIS.ACO_AC = CICREGLC.ACO_AC;
        CIRUSHIS.TYPE = WS_TYPE;
        CIRUSHIS.TX_TYPE = WS_TX_TYPE;
        CIRUSHIS.CUS_AC = CICREGLC.CUS_AC;
        CIRUSHIS.CI_NO = CICREGLC.CI_NO;
        CIRUSHIS.CCY = CICREGLC.CCY;
        CIRUSHIS.LC_CCY = CIRDTL.LC_CCY;
        CIRUSHIS.AMT = CICREGLC.AMT;
        CIRUSHIS.LC_AMT = WS_TX_AMT;
        CIRUSHIS.DLY_TOT_AMT = WS_AMT_INFO.WS_DLY_TOT_AMT;
        CIRUSHIS.LC_DLY_TOT_AMT = CIRDTL.DLY_LMT_AMT;
        CIRUSHIS.DLY_TOT_VOL = WS_AMT_INFO.WS_DLY_TOT_VOL;
        CIRUSHIS.LC_DLY_TOT_VOL = CIRDTL.DLY_LMT_VOL;
        CIRUSHIS.MLY_TOT_AMT = WS_AMT_INFO.WS_MLY_TOT_AMT;
        CIRUSHIS.LC_MLY_TOT_AMT = CIRDTL.MLY_LMT_AMT;
        CIRUSHIS.MLY_TOT_VOL = WS_AMT_INFO.WS_MLY_TOT_VOL;
        CIRUSHIS.LC_MLY_TOT_VOL = CIRDTL.MLY_LMT_VOL;
        CIRUSHIS.SYY_TOT_AMT = WS_AMT_INFO.WS_SYY_TOT_AMT;
        CIRUSHIS.LC_SYY_TOT_AMT = CIRDTL.SYY_LMT_AMT;
        CIRUSHIS.YLY_TOT_AMT = WS_AMT_INFO.WS_YLY_TOT_AMT;
        CIRUSHIS.LC_YLY_TOT_AMT = CIRDTL.YLY_LMT_AMT;
        CIRUSHIS.SE_LMT_AMT = WS_AMT_INFO.WS_SE_DAY_LMT_AMT;
        CIRUSHIS.LC_SE_LMT_AMT = CIRDTL.SE_LMT_AMT;
        CIRUSHIS.SE_TM_LMT_AMT = WS_AMT_INFO.WS_SE_TM_LMT_AMT;
        CIRUSHIS.LC_SE_TM_LMT_AMT = CIRDTL.SE_LMT_AMT;
        CIRUSHIS.BAL = WS_BAL;
        CIRUSHIS.LC_BAL = CIRDTL.BAL_LMT_AMT;
        CIRUSHIS.LMT_AMT_1 = WS_AMT_INFO.WS_LMT_AMT_1;
        CIRUSHIS.LC_LMT_AMT_1 = CIRDTL.LMT_AMT_1;
        CIRUSHIS.LMT_AMT_2 = WS_AMT_INFO.WS_LMT_AMT_2;
        CIRUSHIS.LC_LMT_AMT_2 = CIRDTL.LMT_AMT_2;
        CIRUSHIS.LMT_AMT_3 = WS_AMT_INFO.WS_LMT_AMT_3;
        CIRUSHIS.LC_LMT_AMT_3 = CIRDTL.LMT_AMT_3;
        CIRUSHIS.LMT_AMT_4 = WS_AMT_INFO.WS_LMT_AMT_4;
        CIRUSHIS.LC_LMT_AMT_4 = CIRDTL.LMT_AMT_4;
        WS_LTH = 469;
        if (CIRUSHIS.LC_VAL == null) CIRUSHIS.LC_VAL = "";
        JIBS_tmp_int = CIRUSHIS.LC_VAL.length();
        for (int i=0;i<500-JIBS_tmp_int;i++) CIRUSHIS.LC_VAL += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_HS_VAL_INF);
        CIRUSHIS.LC_VAL = JIBS_tmp_str[0] + CIRUSHIS.LC_VAL.substring(WS_LTH);
        CIRUSHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRUSHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRUSHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRUSHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_INSERT_CITUSHIS();
        if (pgmRtn) return;
    }
    public void R000_GET_SEQ() throws IOException,SQLException,Exception {
        T000_READ_CITUSHIS_ORDER();
        if (pgmRtn) return;
        if (WS_USHIS_FLG == 'F') {
            WS_SEQ = CIRUSHIS.KEY.SEQ;
            WS_SEQ += 1;
        } else {
            WS_SEQ = 1;
        }
    }
    public void R000_MOVE_TO_TXTOT_PROC() throws IOException,SQLException,Exception {
        WS_TX_AMT = CICREGLC.AMT;
        WS_BAL = CICREGLC.BAL;
        CEP.TRC(SCCGWA, CICREGLC.CCY);
        CEP.TRC(SCCGWA, CIRDTL.LC_CCY);
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_NO);
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_SEQ);
        if (!CICREGLC.CCY.equalsIgnoreCase(CIRDTL.LC_CCY)) {
            WS_AMT = WS_TX_AMT;
            R000_GET_EX_AMT();
            if (pgmRtn) return;
            WS_TX_AMT = WS_AMT;
            if (WS_BAL != 0) {
                WS_AMT = WS_BAL;
                R000_GET_EX_AMT();
                if (pgmRtn) return;
                WS_BAL = WS_AMT;
            }
        }
        CIRTXTOT.KEY.LC_OBJ = WS_LC_OBJ;
        CIRTXTOT.KEY.LC_OBJ_TYP = "" + WS_LC_OBJ_TYP;
        JIBS_tmp_int = CIRTXTOT.KEY.LC_OBJ_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRTXTOT.KEY.LC_OBJ_TYP = "0" + CIRTXTOT.KEY.LC_OBJ_TYP;
        CIRTXTOT.KEY.LC_NO = CIRDTL.KEY.LC_NO;
        CIRTXTOT.KEY.LC_SEQ = CIRDTL.KEY.LC_SEQ;
        CIRTXTOT.TYPE = WS_TYPE;
        CIRTXTOT.TX_TYPE = WS_TX_TYPE;
        CIRTXTOT.CCY = CIRDTL.LC_CCY;
        CIRTXTOT.CI_NO = CICREGLC.CI_NO;
        CIRTXTOT.CUS_AC = CICREGLC.CUS_AC;
        if (CIRTXTOT.LAST_UPD_DT != SCCGWA.COMM_AREA.AC_DATE) {
            CIRTXTOT.LAST_DLY_TOT_AMT = CIRTXTOT.DLY_TOT_AMT;
            CIRTXTOT.DLY_TOT_AMT = WS_TX_AMT;
            CIRTXTOT.LAST_DLY_TOT_VOL = CIRTXTOT.DLY_TOT_VOL;
            CIRTXTOT.DLY_TOT_VOL = 1;
        } else {
            CIRTXTOT.DLY_TOT_AMT += WS_TX_AMT;
            CIRTXTOT.DLY_TOT_VOL += 1;
        }
        WS_DAY1 = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DAY1+"", REDEFINES68);
        WS_DAY2 = CIRTXTOT.LAST_UPD_DT;
        IBS.CPY2CLS(SCCGWA, WS_DAY2+"", REDEFINES73);
        if ((REDEFINES68.WS_MM1 != REDEFINES73.WS_MM2) 
            || (REDEFINES68.WS_YY1 != REDEFINES73.WS_YY2)) {
            CIRTXTOT.LAST_MLY_TOT_AMT = CIRTXTOT.MLY_TOT_AMT;
            CIRTXTOT.MLY_TOT_AMT = WS_TX_AMT;
            CIRTXTOT.LAST_MLY_TOT_VOL = CIRTXTOT.MLY_TOT_VOL;
            CIRTXTOT.MLY_TOT_VOL = 1;
        } else {
            CIRTXTOT.MLY_TOT_AMT += WS_TX_AMT;
            CIRTXTOT.MLY_TOT_VOL += 1;
        }
        WS_MM_COMP1 = (short) (REDEFINES68.WS_MM1 - 1);
        WS_MM_COMP2 = (short) (REDEFINES73.WS_MM2 - 1);
        WS_MM1_RES = (short) ((WS_MM_COMP1 - WS_MM_COMP1 % 6) / 6);
        WS_MM2_RES = (short) ((WS_MM_COMP2 - WS_MM_COMP2 % 6) / 6);
        if ((WS_MM1_RES != WS_MM2_RES) 
            || (REDEFINES68.WS_YY1 != REDEFINES73.WS_YY2)) {
            CIRTXTOT.LAST_SYY_TOT_AMT = CIRTXTOT.SYY_TOT_AMT;
            CIRTXTOT.SYY_TOT_AMT = WS_TX_AMT;
        } else {
            CIRTXTOT.SYY_TOT_AMT += WS_TX_AMT;
        }
        if (REDEFINES68.WS_YY1 != REDEFINES73.WS_YY2) {
            CIRTXTOT.YLY_TOT_AMT = WS_TX_AMT;
        } else {
            CIRTXTOT.YLY_TOT_AMT += WS_TX_AMT;
        }
        if (CIRDTL.CON_TYP == null) CIRDTL.CON_TYP = "";
        JIBS_tmp_int = CIRDTL.CON_TYP.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) CIRDTL.CON_TYP += " ";
        if (CIRDTL.CON_TYP.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            CIRTXTOT.SE_DAY_LMT_AMT += WS_TX_AMT;
        }
        if (CIRDTL.CON_TYP == null) CIRDTL.CON_TYP = "";
        JIBS_tmp_int = CIRDTL.CON_TYP.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) CIRDTL.CON_TYP += " ";
        if (CIRDTL.CON_TYP.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            if (CIRTXTOT.LAST_UPD_DT != SCCGWA.COMM_AREA.AC_DATE) {
                CIRTXTOT.SE_TM_LMT_AMT = WS_TX_AMT;
            } else {
                CIRTXTOT.SE_TM_LMT_AMT += WS_TX_AMT;
            }
        }
        CIRTXTOT.LMT_AMT_1 += WS_TX_AMT;
        if (CIRTXTOT.CRT_DATE == 0) {
            CIRTXTOT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (CIRTXTOT.CRT_TLR.trim().length() == 0) {
            CIRTXTOT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        CIRTXTOT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRTXTOT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (CIRTXTOT.LAST_UPD_DT != SCCGWA.COMM_AREA.AC_DATE) {
            CIRTXTOT.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void R000_MOVE_TO_HSTOT_PROC_EC() throws IOException,SQLException,Exception {
        WS_TX_AMT = CIRUSHIS.LC_AMT;
        if (CIRHSTOT.LAST_UPD_DT == SCCGWA.COMM_AREA.AC_DATE) {
            CIRHSTOT.DLY_TOT_AMT -= WS_TX_AMT;
            CIRHSTOT.DLY_TOT_VOL -= 1;
        }
        WS_DAY1 = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DAY1+"", REDEFINES68);
        WS_DAY2 = CIRHSTOT.LAST_UPD_DT;
        IBS.CPY2CLS(SCCGWA, WS_DAY2+"", REDEFINES73);
        if ((REDEFINES68.WS_MM1 == REDEFINES73.WS_MM2) 
            && (REDEFINES68.WS_YY1 == REDEFINES73.WS_YY2)) {
            CIRHSTOT.MLY_TOT_AMT -= WS_TX_AMT;
            CIRHSTOT.MLY_TOT_VOL -= 1;
        }
        WS_MM_COMP1 = (short) (REDEFINES68.WS_MM1 - 1);
        WS_MM_COMP2 = (short) (REDEFINES73.WS_MM2 - 1);
        WS_MM1_RES = (short) ((WS_MM_COMP1 - WS_MM_COMP1 % 6) / 6);
        WS_MM2_RES = (short) ((WS_MM_COMP2 - WS_MM_COMP2 % 6) / 6);
        if ((WS_MM1_RES != WS_MM2_RES) 
            || (REDEFINES68.WS_YY1 != REDEFINES73.WS_YY2)) {
            CIRHSTOT.LAST_SYY_TOT_AMT = CIRHSTOT.SYY_TOT_AMT;
            CIRHSTOT.SYY_TOT_AMT = WS_TX_AMT;
        } else {
            CIRHSTOT.SYY_TOT_AMT -= WS_TX_AMT;
        }
        if (REDEFINES68.WS_YY1 == REDEFINES73.WS_YY2) {
            CIRHSTOT.YLY_TOT_AMT -= WS_TX_AMT;
        }
        if (CIRUSHIS.LC_SE_TM_LMT_AMT != WK_BAL) {
            CIRHSTOT.SE_TM_LMT_AMT -= WS_TX_AMT;
        }
        if (CIRUSHIS.LC_SE_LMT_AMT != WK_BAL) {
            CIRHSTOT.SE_DAY_LMT_AMT -= WS_TX_AMT;
        }
        CIRHSTOT.LMT_AMT_1 -= WS_TX_AMT;
        CIRHSTOT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRHSTOT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_CHK_IF_EXCEED() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_NO);
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_SEQ);
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, CIRDTL.TXN_LMT_AMT);
        CEP.TRC(SCCGWA, WS_AMT_INFO.WS_DLY_TOT_AMT);
        WS_EXIT_FLG = 'Y';
        if (CICREGLC.FUNC == '2') {
            WS_AMT_NUM = 14;
            R000_GEN_AVAIL_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if ((CIRDTL.BAL_LMT_AMT < WS_BAL 
                && CIRDTL.BAL_TYP == '0') 
                || (CIRDTL.BAL_LMT_AMT > WS_BAL 
                && CIRDTL.BAL_TYP == '1')) {
                WS_AMT_NUM = 14;
                if (CIRDTL.KEY.LC_NO > 600000000) {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                } else {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                }
                R000_EXCEED_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICREGLC.FUNC == '2') {
            WS_AMT_NUM = 10;
            R000_GEN_AVAIL_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if (CIRDTL.LMT_AMT_1 < WS_AMT_INFO.WS_LMT_AMT_1) {
                CEP.TRC(SCCGWA, CIRDTL.LMT_AMT_1);
                CEP.TRC(SCCGWA, WS_AMT_INFO.WS_LMT_AMT_1);
                WS_AMT_NUM = 10;
                if (CIRDTL.KEY.LC_NO > 600000000) {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                } else {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                }
                R000_EXCEED_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICREGLC.FUNC == '2') {
            WS_AMT_NUM = 9;
            R000_GEN_AVAIL_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if (CIRDTL.SE_LMT_AMT < WS_AMT_INFO.WS_SE_DAY_LMT_AMT) {
                WS_AMT_NUM = 9;
                if (CIRDTL.KEY.LC_NO > 600000000) {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                } else {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                }
                R000_EXCEED_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICREGLC.FUNC == '2') {
            WS_AMT_NUM = 8;
            R000_GEN_AVAIL_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if (CIRDTL.TM_LMT_AMT < WS_AMT_INFO.WS_SE_TM_LMT_AMT) {
                WS_AMT_NUM = 8;
                if (CIRDTL.KEY.LC_NO > 600000000) {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                } else {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                }
                R000_EXCEED_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICREGLC.FUNC == '2') {
            WS_AMT_NUM = 7;
            R000_GEN_AVAIL_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if (CIRDTL.YLY_LMT_AMT < WS_AMT_INFO.WS_YLY_TOT_AMT) {
                WS_AMT_NUM = 7;
                if (CIRDTL.KEY.LC_NO > 600000000) {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                } else {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                }
                R000_EXCEED_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICREGLC.FUNC == '2') {
            WS_AMT_NUM = 6;
            R000_GEN_AVAIL_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if (CIRDTL.SYY_LMT_AMT < WS_AMT_INFO.WS_SYY_TOT_AMT) {
                WS_AMT_NUM = 6;
                if (CIRDTL.KEY.LC_NO > 600000000) {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                } else {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                }
                R000_EXCEED_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICREGLC.FUNC == '2') {
            WS_AMT_NUM = 5;
            R000_GEN_AVAIL_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if (CIRDTL.MLY_LMT_VOL < WS_AMT_INFO.WS_MLY_TOT_VOL) {
                WS_AMT_NUM = 5;
                if (CIRDTL.KEY.LC_NO > 600000000) {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                } else {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                }
                R000_EXCEED_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICREGLC.FUNC == '2') {
            WS_AMT_NUM = 4;
            R000_GEN_AVAIL_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if (CIRDTL.MLY_LMT_AMT < WS_AMT_INFO.WS_MLY_TOT_AMT) {
                WS_AMT_NUM = 4;
                if (CIRDTL.KEY.LC_NO > 600000000) {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                } else {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                }
                R000_EXCEED_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICREGLC.FUNC == '2') {
            WS_AMT_NUM = 3;
            R000_GEN_AVAIL_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if (CIRDTL.DLY_LMT_VOL < WS_AMT_INFO.WS_DLY_TOT_VOL) {
                WS_AMT_NUM = 3;
                if (CIRDTL.KEY.LC_NO > 600000000) {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                } else {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                }
                R000_EXCEED_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICREGLC.FUNC == '2') {
            WS_AMT_NUM = 2;
            R000_GEN_AVAIL_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if (CIRDTL.DLY_LMT_AMT < WS_AMT_INFO.WS_DLY_TOT_AMT) {
                WS_AMT_NUM = 2;
                if (CIRDTL.KEY.LC_NO > 600000000) {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                } else {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                }
                R000_EXCEED_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICREGLC.FUNC == '2') {
            WS_AMT_NUM = 1;
            R000_GEN_AVAIL_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if (CIRDTL.TXN_LMT_AMT < WS_TX_AMT) {
                WS_AMT_NUM = 1;
                if (CIRDTL.KEY.LC_NO > 600000000) {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                } else {
                    WS_ERR_INFO = CICREGLC.CUS_AC;
                }
                R000_EXCEED_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_EXCEED_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AMT_NUM);
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase(" ")) {
            if (CICREGLC.FUNC == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E, WS_ERR_INFO);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("0")) {
            if (CICREGLC.FUNC == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_0, WS_ERR_INFO);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_0, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("1")) {
            if (CICREGLC.FUNC == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_1, WS_ERR_INFO);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_1, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("2")) {
            if (CICREGLC.FUNC == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_2, WS_ERR_INFO);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_2, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("3")) {
            if (CICREGLC.FUNC == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_3, WS_ERR_INFO);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_3, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("4")) {
            if (CICREGLC.FUNC == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_4, WS_ERR_INFO);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_4, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("5")) {
            if (CICREGLC.FUNC == '0') {
                if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101")) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_5, WS_ERR_INFO);
                }
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_5, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("6")) {
            if (CICREGLC.FUNC == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_6, WS_ERR_INFO);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_6, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("7")) {
            if (CICREGLC.FUNC == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_7, WS_ERR_INFO);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_7, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("8")) {
            if (CICREGLC.FUNC == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_8, WS_ERR_INFO);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_8, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("9")) {
            if (CICREGLC.FUNC == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_9, WS_ERR_INFO);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_9, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("E")) {
            if (CICREGLC.FUNC == '0') {
                if (WS_AMT_NUM == 1) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E_1, WS_ERR_INFO);
                } else if (WS_AMT_NUM == 2) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E_2, WS_ERR_INFO);
                } else if (WS_AMT_NUM == 3) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E_3, WS_ERR_INFO);
                } else if (WS_AMT_NUM == 4) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E_4, WS_ERR_INFO);
                } else if (WS_AMT_NUM == 5) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E_5, WS_ERR_INFO);
                } else if (WS_AMT_NUM == 6) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E_6, WS_ERR_INFO);
                } else if (WS_AMT_NUM == 7) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E_7, WS_ERR_INFO);
                } else if (WS_AMT_NUM == 8) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E_8, WS_ERR_INFO);
                } else if (WS_AMT_NUM == 9) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E_9, WS_ERR_INFO);
                } else if (WS_AMT_NUM == 10) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E_10, WS_ERR_INFO);
                } else if (WS_AMT_NUM == 14) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_E_14, WS_ERR_INFO);
                }
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_0, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("N")) {
            if (CICREGLC.FUNC == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_0, WS_ERR_INFO);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AMT_GRT_LC_0, CICREGLC.RC);
            }
            if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
            JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
            for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        } else if (CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("T")) {
            if (CICREGLC.FUNC == '0') {
                R000_CALL_OTHER_PRG();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GEN_AVAIL_INFO_PROC() throws IOException,SQLException,Exception {
        if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
        JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
        JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        if (CIRDTL.LMT_CTL_STSW == null) CIRDTL.LMT_CTL_STSW = "";
        JIBS_tmp_int = CIRDTL.LMT_CTL_STSW.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) CIRDTL.LMT_CTL_STSW += " ";
        if ((CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).equalsIgnoreCase("E") 
            || CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).trim().length() == 0) 
            || !(CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).compareTo("0") >= 0 
            && CIRDTL.LMT_CTL_STSW.substring(WS_AMT_NUM - 1, WS_AMT_NUM + 1 - 1).compareTo("9") <= 0 
            && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101"))) {
            if (WS_AMT_NUM == 1) {
                WS_AMT1 = CIRDTL.TXN_LMT_AMT;
                if (WS_AMT1 < CICREGLC.TXN_LMT_AMT) {
                    CICREGLC.TXN_LMT_AMT = WS_AMT1;
                }
                if (CICREGLC.TXN_LMT_AMT < CICREGLC.TXN_AMT) {
                    CICREGLC.TXN_AMT = CICREGLC.TXN_LMT_AMT;
                }
            } else if (WS_AMT_NUM == 2) {
                WS_AMT1 = CIRDTL.DLY_LMT_AMT - WS_AMT_INFO.WS_DLY_TOT_AMT;
                if (WS_AMT1 < CICREGLC.DLY_LMT_AMT) {
                    CICREGLC.DLY_LMT_AMT = WS_AMT1;
                }
                if (CICREGLC.DLY_LMT_AMT < CICREGLC.TXN_AMT) {
                    CICREGLC.TXN_AMT = CICREGLC.DLY_LMT_AMT;
                }
            } else if (WS_AMT_NUM == 3) {
                WS_AMT1 = CIRDTL.DLY_LMT_VOL - WS_AMT_INFO.WS_DLY_TOT_VOL;
                if (WS_AMT1 < CIRDTL.DLY_LMT_VOL) {
                    CICREGLC.DLY_LMT_VOL = (int) WS_AMT1;
                }
            } else if (WS_AMT_NUM == 4) {
                WS_AMT1 = CIRDTL.MLY_LMT_AMT - WS_AMT_INFO.WS_MLY_TOT_AMT;
                if (WS_AMT1 < CICREGLC.MLY_LMT_AMT) {
                    CICREGLC.MLY_LMT_AMT = WS_AMT1;
                }
                if (CICREGLC.MLY_LMT_AMT < CICREGLC.TXN_AMT) {
                    CICREGLC.TXN_AMT = CICREGLC.MLY_LMT_AMT;
                }
            } else if (WS_AMT_NUM == 5) {
                WS_AMT1 = CIRDTL.MLY_LMT_VOL - WS_AMT_INFO.WS_MLY_TOT_VOL;
                if (WS_AMT1 < CIRDTL.MLY_LMT_VOL) {
                    CICREGLC.MLY_LMT_VOL = (int) WS_AMT1;
                }
            } else if (WS_AMT_NUM == 6) {
                WS_AMT1 = CIRDTL.SYY_LMT_AMT - WS_AMT_INFO.WS_SYY_TOT_AMT;
                if (WS_AMT1 < CICREGLC.SYY_LMT_AMT) {
                    CICREGLC.SYY_LMT_AMT = WS_AMT1;
                }
                if (CICREGLC.SYY_LMT_AMT < CICREGLC.TXN_AMT) {
                    CICREGLC.TXN_AMT = CICREGLC.SYY_LMT_AMT;
                }
            } else if (WS_AMT_NUM == 7) {
                WS_AMT1 = CIRDTL.YLY_LMT_AMT - WS_AMT_INFO.WS_YLY_TOT_AMT;
                if (WS_AMT1 < CICREGLC.YLY_LMT_AMT) {
                    CICREGLC.YLY_LMT_AMT = WS_AMT1;
                }
                if (CICREGLC.YLY_LMT_AMT < CICREGLC.TXN_AMT) {
                    CICREGLC.TXN_AMT = CICREGLC.YLY_LMT_AMT;
                }
            } else if (WS_AMT_NUM == 8) {
                WS_AMT1 = CIRDTL.TM_LMT_AMT - WS_AMT_INFO.WS_SE_TM_LMT_AMT;
                if (WS_AMT1 < CICREGLC.TM_LMT_AMT) {
                    CICREGLC.TM_LMT_AMT = WS_AMT1;
                }
                if (CICREGLC.TM_LMT_AMT < CICREGLC.TXN_AMT) {
                    CICREGLC.TXN_AMT = CICREGLC.TM_LMT_AMT;
                }
            } else if (WS_AMT_NUM == 9) {
                WS_AMT1 = CIRDTL.SE_LMT_AMT - WS_AMT_INFO.WS_SE_TM_LMT_AMT;
                if (WS_AMT1 < CICREGLC.SE_LMT_AMT) {
                    CICREGLC.SE_LMT_AMT = WS_AMT1;
                }
                if (CICREGLC.SE_LMT_AMT < CICREGLC.TXN_AMT) {
                    CICREGLC.TXN_AMT = CICREGLC.SE_LMT_AMT;
                }
            } else if (WS_AMT_NUM == 10) {
                WS_AMT1 = CIRDTL.LMT_AMT_1 - WS_AMT_INFO.WS_LMT_AMT_1;
                if (WS_AMT1 < CICREGLC.LMT_AMT_1) {
                    CICREGLC.LMT_AMT_1 = WS_AMT1;
                }
                if (CICREGLC.LMT_AMT_1 < CICREGLC.TXN_AMT) {
                    CICREGLC.TXN_AMT = CICREGLC.LMT_AMT_1;
                }
            } else if (WS_AMT_NUM == 14) {
                CEP.TRC(SCCGWA, WS_BAL);
                CEP.TRC(SCCGWA, CIRDTL.BAL_LMT_AMT);
                CEP.TRC(SCCGWA, CICREGLC.BAL_LMT_AMT);
                if (CIRDTL.BAL_TYP == '0') {
                    WS_AMT1 = WS_BAL - CIRDTL.BAL_LMT_AMT;
                } else {
                    WS_AMT1 = CIRDTL.BAL_LMT_AMT - WS_BAL;
                }
                if (WS_BAL == 0) {
                    WS_AMT1 = CIRDTL.BAL_LMT_AMT;
                }
                if (WS_AMT1 < CICREGLC.BAL_LMT_AMT) {
                    CICREGLC.BAL_LMT_AMT = WS_AMT1;
                }
                CEP.TRC(SCCGWA, CICREGLC.BAL_LMT_AMT);
            }
        }
    }
    public void R000_CALL_OTHER_PRG() throws IOException,SQLException,Exception {
        if (WS_AMT_NUM == 10 
            && (CICREGLC.AC_TYP == '2' 
            || CICREGLC.AC_TYP == '3')) {
            R000_CALL_CI_INFO();
            if (pgmRtn) return;
        }
    }
    public void R000_GEN_HSTOT_INFO_EC() throws IOException,SQLException,Exception {
        WS_LTH = 469;
        if (CIRUSHIS.LC_VAL == null) CIRUSHIS.LC_VAL = "";
        JIBS_tmp_int = CIRUSHIS.LC_VAL.length();
        for (int i=0;i<500-JIBS_tmp_int;i++) CIRUSHIS.LC_VAL += " ";
        IBS.CPY2CLS(SCCGWA, CIRUSHIS.LC_VAL.substring(0, WS_LTH), WS_HS_VAL_INF);
        IBS.init(SCCGWA, CIRHSTOT);
        CIRHSTOT.KEY.LC_OBJ = CIRUSHIS.KEY.LC_OBJ;
        CIRHSTOT.KEY.LC_OBJ_TYP = CIRUSHIS.KEY.LC_OBJ_TYP.charAt(0);
        CIRHSTOT.LC_NO = CIRUSHIS.KEY.LC_NO;
        CIRHSTOT.LC_SEQ = CIRUSHIS.KEY.LC_SEQ;
