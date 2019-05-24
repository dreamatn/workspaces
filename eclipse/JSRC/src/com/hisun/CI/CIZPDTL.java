package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFX;
import com.hisun.BP.BPCSGSEQ;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIZPDTL {
    boolean pgmRtn = false;
    String K_PRDP_TYP = "PRDPR";
    String K_IRUL_TYP = "TIRUL";
    String K_AP_MMO = "TD";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    double K_MAX_AMT = 99999999999999.99;
    String WS_MSGID = " ";
    String WS_MMO = " ";
    char WS_EC = ' ';
    char WS_PROC_TYP = ' ';
    short WS_I = 0;
    short WS_J = 0;
    short WS_Z = 0;
    short WS_M = 0;
    short WS_IDX = 0;
    short WS_X = 0;
    short WS_C = 0;
    short WS_D = 0;
    short WS_A = 0;
    short WS_B = 0;
    String WS_MC = " ";
    short WS_MCT = 0;
    int WS_LC_SEQ = 0;
    int WS_SUB_SEQ = 0;
    int WS_SEQ = 0;
    int WS_LAST_LC_NO = 0;
    int WS_LAST_SEQ = 0;
    int WS_LAST_SUB_SEQ = 0;
    int[] WS_LC_NO_O = new int[20];
    int[] WS_LC_NO_I = new int[20];
    int WS_LC_NO = 0;
    int WS_LAST_LC_NO2 = 0;
    char WS_LAST_VAL_COND = ' ';
    String WS_LAST_CON_TYP = " ";
    short WS_CON_TYP_N = 0;
    char WS_LC_OBJ_TYP = ' ';
    String WS_CNTRCT_TYP = " ";
    char WS_ONE_FLG = ' ';
    int WS_A_LC_NO = 0;
    int WS_A_LC_SEQ = 0;
    String WS_A_CON_TYP = " ";
    String WS_A_CON_TYP_D = " ";
    String WS_A_VAL = " ";
    char WS_A_VAL_COND = ' ';
    int WS_A_STA_DT = 0;
    int WS_A_END_DT = 0;
    String WS_A_CCY = " ";
    double WS_A_TXN_LMT_AMT = 0;
    double WS_A_DLY_LMT_AMT = 0;
    int WS_A_DLY_LMT_VOL = 0;
    double WS_A_MLY_LMT_AMT = 0;
    int WS_A_MLY_LMT_VOL = 0;
    double WS_A_SYY_LMT_AMT = 0;
    double WS_A_YLY_LMT_AMT = 0;
    double WS_A_TM_LMT_AMT = 0;
    double WS_A_SE_LMT_AMT = 0;
    double WS_A_LMT_AMT_1 = 0;
    double WS_A_LMT_AMT_2 = 0;
    double WS_A_LMT_AMT_3 = 0;
    double WS_A_LMT_AMT_4 = 0;
    double WS_A_BAL_LMT_AMT = 0;
    int WS_B_LC_NO = 0;
    int WS_B_LC_SEQ = 0;
    String WS_B_CON_TYP = " ";
    String WS_B_CON_TYP_D = " ";
    String WS_B_VAL = " ";
    char WS_B_VAL_COND = ' ';
    int WS_B_STA_DT = 0;
    int WS_B_END_DT = 0;
    String WS_B_ACT_SCENE = " ";
    String WS_B_LMT_CTL_STSW = " ";
    String WS_B_LC_CCY = " ";
    CIZPDTL_WS_B_SX_VAL_INF WS_B_SX_VAL_INF = new CIZPDTL_WS_B_SX_VAL_INF();
    String WS_A_VAL_1 = " ";
    String WS_A_VAL_2 = " ";
    String WS_B_VAL_1 = " ";
    String WS_B_VAL_2 = " ";
    String[] WS_A_VAL_LW = new String[50];
    String WS_CON_TYP = " ";
    String WS_CON_TYP_1 = " ";
    String WS_CON_TYP_LW = " ";
    String WS_ERR_INFO = " ";
    int WS_CNT = 0;
    CIZPDTL_WS_AC_DATA[] WS_AC_DATA = new CIZPDTL_WS_AC_DATA[10];
    char WS_ANUL_FLG = ' ';
    int WS_BEGIN_DT = 0;
    int WS_END_DT = 0;
    char WS_SPE_FLG = ' ';
    double WS_TX_AMT = 0;
    CIZPDTL_WS_A_TXN_A[] WS_A_TXN_A = new CIZPDTL_WS_A_TXN_A[8];
    CIZPDTL_WS_B_TXN_B[] WS_B_TXN_B = new CIZPDTL_WS_B_TXN_B[8];
    CIZPDTL_WS_MTX_VAL[] WS_MTX_VAL = new CIZPDTL_WS_MTX_VAL[10];
    char WS_MSREL_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_GOPP_FLG = ' ';
    char WS_CMPR_FLG = ' ';
    char WS_DTL_FLG_1 = ' ';
    char WS_DTL_FLG_2 = ' ';
    char WS_DTL_FLG = ' ';
    char WS_DINF_FLG_A = ' ';
    char WS_DINF_FLG_B = ' ';
    char WS_DINF_FLG = ' ';
    char WS_DUP_FLG = ' ';
    char WS_LC_NO_FLG = ' ';
    char WS_STYP_FLG = ' ';
    char WS_DINF_CHK_FLG = ' ';
    char WS_B_A_EQ_FLG = ' ';
    char WS_B_A_EXIT_FLG = ' ';
    char WS_CON_TYP_FLG = ' ';
    char WS_LC_INFO_FLG = ' ';
    char WS_QUIT_DINF_FLG = ' ';
    char WS_CHECK_MAIN_FLG = ' ';
    char WS_GRT_FLG = ' ';
    char WS_COMP_DINF_FLG = ' ';
    char WS_COMP_GC_FLG = ' ';
    char WS_MAIN_GRT_FLG = ' ';
    char WS_ACRL_CHK_FLG = ' ';
    char WS_CON_VAL_FLG = ' ';
    char WS_MAIN_SUB_CMP_FLG = ' ';
    char WS_GET_A_INF_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    CICO370 CICO370 = new CICO370();
    CIRACRL CIRACRL = new CIRACRL();
    BPCFX BPCFX = new BPCFX();
    CIRTYP CIRTYP = new CIRTYP();
    CIRDTL CIRDTL = new CIRDTL();
    CIRGOPP CIRGOPP = new CIRGOPP();
    CIRDINF CIRDINF = new CIRDINF();
    CIRSTYP CIRSTYP = new CIRSTYP();
    CIRCMPR CIRCMPR = new CIRCMPR();
    CIRDTL CIRDDL = new CIRDTL();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICPDTL CICPDTL;
    public CIZPDTL() {
        for (int i=0;i<10;i++) WS_AC_DATA[i] = new CIZPDTL_WS_AC_DATA();
        for (int i=0;i<8;i++) WS_A_TXN_A[i] = new CIZPDTL_WS_A_TXN_A();
        for (int i=0;i<8;i++) WS_B_TXN_B[i] = new CIZPDTL_WS_B_TXN_B();
        for (int i=0;i<10;i++) WS_MTX_VAL[i] = new CIZPDTL_WS_MTX_VAL();
    }
    public void MP(SCCGWA SCCGWA, CICPDTL CICPDTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICPDTL = CICPDTL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZPDTL return!");
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
        WS_CON_TYP_LW = "00000000000000000000000000000000000000000000000000";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICPDTL.FUNC);
        CEP.TRC(SCCGWA, CICPDTL.LC_NO);
        CEP.TRC(SCCGWA, CICPDTL.SEQ);
        CEP.TRC(SCCGWA, CICPDTL.TYPE);
        if (CICPDTL.FUNC == '0') {
            B210_ADD_DTL_INFO();
            if (pgmRtn) return;
            B300_OUTPUT_PROC();
            if (pgmRtn) return;
        } else if (CICPDTL.FUNC == '1') {
            B220_UPD_DTL_INFO();
            if (pgmRtn) return;
            B300_OUTPUT_PROC();
            if (pgmRtn) return;
        } else if (CICPDTL.FUNC == '2') {
            B230_DEL_DTL_INFO();
            if (pgmRtn) return;
            B300_OUTPUT_PROC();
            if (pgmRtn) return;
        } else if (CICPDTL.FUNC == '3') {
            B240_QRY_DTL_INFO();
            if (pgmRtn) return;
        } else if (CICPDTL.FUNC == '5') {
            B250_ADD_DTL_AMT_INFO();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERR);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        R000_CHECK_TYPE_INFO();
        if (pgmRtn) return;
        if (CICPDTL.FUNC == ' ') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_M_INP);
        }
        if (CICPDTL.TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TYPE_M_INPT);
        }
        if (CICPDTL.LC_OBJ.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_LC_M_INP);
        }
        if (CICPDTL.TX_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TX_M_INP);
        }
    }
    public void B210_ADD_DTL_INFO() throws IOException,SQLException,Exception {
        R000_INSERT_INTO_CITDTL();
        if (pgmRtn) return;
    }
    public void B220_UPD_DTL_INFO() throws IOException,SQLException,Exception {
        if (WS_ONE_FLG == 'Y') {
            R000_GET_LC_NO_SEQ();
            if (pgmRtn) return;
        }
        R000_DEL_CITDTL_INFO();
        if (pgmRtn) return;
        R000_INSERT_INTO_CITDTL();
        if (pgmRtn) return;
    }
    public void B230_DEL_DTL_INFO() throws IOException,SQLException,Exception {
        if (WS_ONE_FLG == 'Y') {
            R000_GET_LC_NO_SEQ();
            if (pgmRtn) return;
        }
        R000_DEL_CITDTL_INFO();
        if (pgmRtn) return;
    }
    public void B240_QRY_DTL_INFO() throws IOException,SQLException,Exception {
        if (WS_ONE_FLG == 'Y' 
            || WS_SPE_FLG == '4') {
            R000_GET_LC_NO_SEQ();
            if (pgmRtn) return;
        }
        if (CICPDTL.LC_NO != 0 
            && CICPDTL.LC_NO != ' ') {
            R000_GET_DTL_INFO();
            if (pgmRtn) return;
        }
        R000_MOVE_TO_PDTL();
        if (pgmRtn) return;
    }
    public void B250_ADD_DTL_AMT_INFO() throws IOException,SQLException,Exception {
        R000_ADD_CITDTL_AMT();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        R000_GEN_O370_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CI370";
        SCCFMT.DATA_PTR = CICO370;
        SCCFMT.DATA_LEN = 3225;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_TYPE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRTYP);
        CIRTYP.KEY.TYPE = CICPDTL.TYPE;
        T000_READ_CITTYP();
        if (pgmRtn) return;
        WS_SPE_FLG = CIRTYP.SPE_FLG;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (CICPDTL.OBJ_TYP == null) CICPDTL.OBJ_TYP = "";
            JIBS_tmp_int = CICPDTL.OBJ_TYP.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CICPDTL.OBJ_TYP += " ";
            if (CIRTYP.OBJ_TYP == null) CIRTYP.OBJ_TYP = "";
            JIBS_tmp_int = CIRTYP.OBJ_TYP.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.OBJ_TYP += " ";
            if (CICPDTL.OBJ_TYP == null) CICPDTL.OBJ_TYP = "";
            JIBS_tmp_int = CICPDTL.OBJ_TYP.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CICPDTL.OBJ_TYP += " ";
            if (!CICPDTL.OBJ_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase(CIRTYP.OBJ_TYP.substring(WS_I - 1, WS_I + 1 - 1)) 
                && CICPDTL.OBJ_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
            }
            if (CIRTYP.OBJ_TYP == null) CIRTYP.OBJ_TYP = "";
            JIBS_tmp_int = CIRTYP.OBJ_TYP.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.OBJ_TYP += " ";
            if (CIRTYP.OBJ_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                WS_C = WS_I;
                JIBS_NumStr = "" + WS_C;
                WS_LC_OBJ_TYP = JIBS_NumStr.charAt(0);
            }
        }
        for (WS_A = 1; WS_A < 10; WS_A += 2) {
            if (CIRTYP.MTX_CON_TYP1 == null) CIRTYP.MTX_CON_TYP1 = "";
            JIBS_tmp_int = CIRTYP.MTX_CON_TYP1.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.MTX_CON_TYP1 += " ";
            if (CIRTYP.MTX_CON_TYP1.substring(WS_A - 1, WS_A + 2 - 1).trim().length() > 0) {
                WS_B += 1;
                if (CIRTYP.MTX_CON_TYP1 == null) CIRTYP.MTX_CON_TYP1 = "";
                JIBS_tmp_int = CIRTYP.MTX_CON_TYP1.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.MTX_CON_TYP1 += " ";
                WS_MTX_VAL[WS_B-1].WS_MTX = CIRTYP.MTX_CON_TYP1.substring(WS_A - 1, WS_A + 2 - 1);
            }
        }
        if (CICPDTL.DAY_STA == 0) {
            CICPDTL.DAY_STA = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (CICPDTL.DAY_END == 0) {
            CICPDTL.DAY_END = 20991231;
        }
        CEP.TRC(SCCGWA, CICPDTL.DAY_STA);
        CEP.TRC(SCCGWA, CIRTYP.STA_TM);
        if (CICPDTL.FUNC != '2') {
            if (CICPDTL.DAY_STA < CIRTYP.STA_TM) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TYPDT_LT_DT);
            }
        }
        if (CICPDTL.DAY_END > CIRTYP.END_TM) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TYPDT_GT_DD);
        }
        if (CIRTYP.ONE_FLG == 'Y') {
            WS_ONE_FLG = 'Y';
        }
    }
    public void R000_GET_LC_NO_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGOPP);
        CIRGOPP.KEY.TYPE = CICPDTL.TYPE;
        CIRGOPP.KEY.LC_OBJ_TYP = "" + WS_LC_OBJ_TYP;
        JIBS_tmp_int = CIRGOPP.KEY.LC_OBJ_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRGOPP.KEY.LC_OBJ_TYP = "0" + CIRGOPP.KEY.LC_OBJ_TYP;
        CIRGOPP.KEY.LC_OBJ = CICPDTL.LC_OBJ;
        CIRGOPP.KEY.TX_TYPE = CICPDTL.TX_TYPE;
        T000_READ_CITGOPP();
        if (pgmRtn) return;
        if (CICPDTL.FUNC == '1') {
            if (WS_GOPP_FLG == 'Y') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DELETE_NOTFND);
            }
        }
        CICPDTL.LC_NO = CIRGOPP.LC_NO;
        CICPDTL.SEQ = 1;
    }
    public void R000_INSERT_CITGOPP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGOPP);
        CIRGOPP.KEY.TYPE = CICPDTL.TYPE;
        CIRGOPP.KEY.LC_OBJ_TYP = "" + WS_LC_OBJ_TYP;
        JIBS_tmp_int = CIRGOPP.KEY.LC_OBJ_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRGOPP.KEY.LC_OBJ_TYP = "0" + CIRGOPP.KEY.LC_OBJ_TYP;
        CIRGOPP.KEY.LC_OBJ = CICPDTL.LC_OBJ;
        CIRGOPP.KEY.TX_TYPE = CICPDTL.TX_TYPE;
        T000_READ_CITGOPP();
        if (pgmRtn) return;
        if (WS_GOPP_FLG == 'Y') {
            if (CICPDTL.FUNC == '1') {
                CIRGOPP.LC_NO = CICPDTL.LC_NO;
            } else {
                R000_GET_LC_NO();
                if (pgmRtn) return;
                CIRGOPP.LC_NO = WS_LC_NO;
            }
            CIRGOPP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CIRGOPP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRGOPP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CIRGOPP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_INSERT_CITGOPP();
            if (pgmRtn) return;
        }
        CICPDTL.LC_NO = CIRGOPP.LC_NO;
    }
    public void R000_INSERT_CITSTYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSTYP);
        CIRSTYP.KEY.LC_OBJ = CICPDTL.LC_OBJ;
        CIRSTYP.KEY.LC_OBJ_TYP = "" + WS_LC_OBJ_TYP;
        JIBS_tmp_int = CIRSTYP.KEY.LC_OBJ_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRSTYP.KEY.LC_OBJ_TYP = "0" + CIRSTYP.KEY.LC_OBJ_TYP;
        CIRSTYP.KEY.TYPE = CICPDTL.TYPE;
        R000_CHECK_AC_INFO();
        if (pgmRtn) return;
        T000_READUPD_CITSTYP();
        if (pgmRtn) return;
        CIRSTYP.CUS_AC = CICPDTL.CUS_AC;
        if (WS_STYP_FLG == 'Y') {
            if (CICPDTL.DAY_END > CIRSTYP.END_DT) {
                CIRSTYP.END_DT = CICPDTL.DAY_END;
            }
            if (CICPDTL.DAY_STA < CIRSTYP.STA_DT) {
                CIRSTYP.STA_DT = CICPDTL.DAY_STA;
            }
        } else {
            CIRSTYP.STA_DT = CICPDTL.DAY_STA;
            CIRSTYP.END_DT = CICPDTL.DAY_END;
            CIRSTYP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CIRSTYP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        CIRSTYP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRSTYP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (WS_STYP_FLG == 'N') {
            T000_INSERT_CITSTYP();
            if (pgmRtn) return;
        } else {
            T000_REWRITE_CITSTYP();
            if (pgmRtn) return;
        }
    }
    public void R000_INSERT_CITDTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRDTL);
        CIRDTL.KEY.LC_NO = CIRGOPP.LC_NO;
        if (CICPDTL.FUNC == '1') {
            CIRDTL.KEY.LC_SEQ = CICPDTL.SEQ;
        } else {
            T000_READ_CITDTL_ORDER();
            if (pgmRtn) return;
            if (WS_DTL_FLG == 'Y') {
                CIRDTL.KEY.LC_SEQ += 1;
            } else {
                CIRDTL.KEY.LC_SEQ = 1;
            }
            CICPDTL.SEQ = CIRDTL.KEY.LC_SEQ;
        }
        CIRDTL.CON_TYP = CICPDTL.CON_TYP;
        CIRDTL.LVL_CON_TYP = CICPDTL.LVL_TYP;
        CIRDTL.LVL = CICPDTL.LVL;
        CIRDTL.LC_CCY = CICPDTL.LC_CCY;
        CIRDTL.DAY_STA_DT = CICPDTL.DAY_STA;
        CIRDTL.DAY_END_DT = CICPDTL.DAY_END;
        if (CIRDTL.DAY_END_DT < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ENDDT_LT_ACDT);
        }
        CIRDTL.TXN_LMT_AMT = CICPDTL.TXN_AMT;
        CIRDTL.DLY_LMT_AMT = CICPDTL.DLY_AMT;
        if (CIRDTL.TXN_LMT_AMT > CIRDTL.DLY_LMT_AMT) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TXN_GRT_DAY);
        }
        CIRDTL.DLY_LMT_VOL = CICPDTL.DLY_VOL;
        CIRDTL.MLY_LMT_AMT = CICPDTL.MLY_AMT;
        if (CIRDTL.DLY_LMT_AMT > CIRDTL.MLY_LMT_AMT) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DAY_GRT_MTH);
        }
        CIRDTL.MLY_LMT_VOL = CICPDTL.MLY_VOL;
        CIRDTL.SYY_LMT_AMT = CICPDTL.SYY_AMT;
        if (CIRDTL.MLY_LMT_AMT > CIRDTL.SYY_LMT_AMT) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_M_GRT_BN);
        }
        if (CIRDTL.DLY_LMT_VOL > CIRDTL.MLY_LMT_VOL) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_VOL_GRT_MTH);
        }
        CIRDTL.YLY_LMT_AMT = CICPDTL.YLY_AMT;
        if (CIRDTL.SYY_LMT_AMT > CIRDTL.YLY_LMT_AMT) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BN_GRT_N);
        }
        CIRDTL.TM_LMT_AMT = CICPDTL.TM_AMT;
        CIRDTL.SE_LMT_AMT = CICPDTL.SE_AMT;
        if (CIRDTL.TM_LMT_AMT != K_MAX_AMT) {
            if (CIRDTL.CON_TYP == null) CIRDTL.CON_TYP = "";
            JIBS_tmp_int = CIRDTL.CON_TYP.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) CIRDTL.CON_TYP += " ";
            if (!CIRDTL.CON_TYP.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TM_NOT_INPUT);
            }
        }
        if (CIRDTL.SE_LMT_AMT != K_MAX_AMT) {
            if (CIRDTL.CON_TYP == null) CIRDTL.CON_TYP = "";
            JIBS_tmp_int = CIRDTL.CON_TYP.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) CIRDTL.CON_TYP += " ";
            if (!CIRDTL.CON_TYP.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DT_NOT_INPUT);
            }
        }
        CIRDTL.LMT_AMT_1 = CICPDTL.LMT_AMT1;
        CIRDTL.LMT_AMT_2 = CICPDTL.LMT_AMT2;
        CIRDTL.LMT_AMT_3 = CICPDTL.LMT_AMT3;
        CIRDTL.LMT_AMT_4 = CICPDTL.LMT_AMT4;
        CIRDTL.REL_TYP = CICPDTL.REL_TYP;
        CIRDTL.BAL_LMT_AMT = CICPDTL.BAL_AMT;
        CIRDTL.BAL_TYP = CICPDTL.BAL_TYP;
        CIRDTL.LMT_CTL_STSW = CICPDTL.LMT_STSW;
        CIRDTL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRDTL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRDTL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRDTL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRDTL.CON_TYP = "00000000000000000000000000000000000000000000000000";
        if (CICPDTL.TYPE.equalsIgnoreCase("16")) {
            CIRDTL.ACT_SCENE = "II/III类账户原进原出限�?";
            DTL_ACT_SCENE_LEN = CIRDTL.ACT_SCENE.length();
        }
    }
    public void R000_CHANGE_CITDTL() throws IOException,SQLException,Exception {
        CIRDTL.TXN_LMT_AMT = CICPDTL.TXN_AMT;
        CIRDTL.DLY_LMT_AMT = CICPDTL.DLY_AMT;
        CIRDTL.DLY_LMT_VOL = CICPDTL.DLY_VOL;
        CIRDTL.MLY_LMT_AMT = CICPDTL.MLY_AMT;
        CIRDTL.MLY_LMT_VOL = CICPDTL.MLY_VOL;
        CIRDTL.SYY_LMT_AMT = CICPDTL.SYY_AMT;
        CIRDTL.YLY_LMT_AMT = CICPDTL.YLY_AMT;
        CIRDTL.TM_LMT_AMT = CICPDTL.TM_AMT;
        CIRDTL.SE_LMT_AMT = CICPDTL.SE_AMT;
        CIRDTL.LMT_AMT_1 += CICPDTL.LMT_AMT1;
        CIRDTL.LMT_AMT_2 = CICPDTL.LMT_AMT2;
        CIRDTL.LMT_AMT_3 = CICPDTL.LMT_AMT3;
        CIRDTL.LMT_AMT_4 = CICPDTL.LMT_AMT4;
        CIRDTL.BAL_LMT_AMT = CICPDTL.BAL_AMT;
        CIRDTL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRDTL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_CITDTL();
        if (pgmRtn) return;
    }
    public void R000_INSERT_CITDINF() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 50; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, CICPDTL.TIMES50[WS_I-1].CON_TYP1);
            if (CICPDTL.TIMES50[WS_I-1].VAL == null) CICPDTL.TIMES50[WS_I-1].VAL = "";
            JIBS_tmp_int = CICPDTL.TIMES50[WS_I-1].VAL.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) CICPDTL.TIMES50[WS_I-1].VAL += " ";
            if (CICPDTL.TIMES50[WS_I-1].VAL == null) CICPDTL.TIMES50[WS_I-1].VAL = "";
            JIBS_tmp_int = CICPDTL.TIMES50[WS_I-1].VAL.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) CICPDTL.TIMES50[WS_I-1].VAL += " ";
            if ((CICPDTL.TIMES50[WS_I-1].CON_TYP1 != ' ' 
                && CICPDTL.TIMES50[WS_I-1].CON_TYP1 != 0) 
                && ((CICPDTL.TIMES50[WS_I-1].VAL.trim().length() > 0 
                && (CICPDTL.TIMES50[WS_I-1].CON_TYP1 != 12 
                && CICPDTL.TIMES50[WS_I-1].CON_TYP1 != 13)) 
                || (CICPDTL.TIMES50[WS_I-1].CON_TYP1 == 12 
                && !CICPDTL.TIMES50[WS_I-1].VAL.substring(0, 16).equalsIgnoreCase("0000000000000000")) 
                || (CICPDTL.TIMES50[WS_I-1].CON_TYP1 == 13 
                && !CICPDTL.TIMES50[WS_I-1].VAL.substring(0, 12).equalsIgnoreCase("000000000000")))) {
                if (CICPDTL.TIMES50[WS_I-1].CON_TYP1 == 12) {
                    if (CICPDTL.TIMES50[WS_I-1].VAL == null) CICPDTL.TIMES50[WS_I-1].VAL = "";
                    JIBS_tmp_int = CICPDTL.TIMES50[WS_I-1].VAL.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) CICPDTL.TIMES50[WS_I-1].VAL += " ";
                    if (CICPDTL.TIMES50[WS_I-1].VAL.substring(0, 8).trim().length() == 0) WS_BEGIN_DT = 0;
                    else WS_BEGIN_DT = Integer.parseInt(CICPDTL.TIMES50[WS_I-1].VAL.substring(0, 8));
                    if (CICPDTL.TIMES50[WS_I-1].VAL == null) CICPDTL.TIMES50[WS_I-1].VAL = "";
                    JIBS_tmp_int = CICPDTL.TIMES50[WS_I-1].VAL.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) CICPDTL.TIMES50[WS_I-1].VAL += " ";
                    if (CICPDTL.TIMES50[WS_I-1].VAL.substring(9 - 1, 9 + 16 - 1).trim().length() == 0) WS_END_DT = 0;
                    else WS_END_DT = Integer.parseInt(CICPDTL.TIMES50[WS_I-1].VAL.substring(9 - 1, 9 + 16 - 1));
                    if (WS_BEGIN_DT < CICPDTL.DAY_STA 
                        || WS_END_DT > CICPDTL.DAY_END) {
                        CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MORE_DT_ERROR);
                    }
                }
                WS_ANUL_FLG = 'N';
                WS_SUB_SEQ += 1;
                CIRDINF.KEY.LC_NO = CIRGOPP.LC_NO;
                CIRDINF.KEY.LC_SEQ = CIRDTL.KEY.LC_SEQ;
                CIRDINF.KEY.CON_TYP = "" + CICPDTL.TIMES50[WS_I-1].CON_TYP1;
                JIBS_tmp_int = CIRDINF.KEY.CON_TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) CIRDINF.KEY.CON_TYP = "0" + CIRDINF.KEY.CON_TYP;
                CIRDINF.SUB_SEQ = WS_SUB_SEQ;
                CIRDINF.VAL = CICPDTL.TIMES50[WS_I-1].VAL;
                CIRDINF.VAL_COND = CICPDTL.TIMES50[WS_I-1].VAL_COND;
                if (CIRDINF.KEY.CON_TYP.trim().length() == 0) WS_CON_TYP_N = 0;
                else WS_CON_TYP_N = Short.parseShort(CIRDINF.KEY.CON_TYP);
                if (CIRDTL.CON_TYP == null) CIRDTL.CON_TYP = "";
                JIBS_tmp_int = CIRDTL.CON_TYP.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) CIRDTL.CON_TYP += " ";
                CIRDTL.CON_TYP = CIRDTL.CON_TYP.substring(0, WS_CON_TYP_N - 1) + "1" + CIRDTL.CON_TYP.substring(WS_CON_TYP_N + 1 - 1);
                if (!CIRDINF.KEY.CON_TYP.equalsIgnoreCase(WS_LAST_CON_TYP)) {
                    WS_LAST_VAL_COND = CIRDINF.VAL_COND;
                }
                if (CIRDINF.VAL_COND == '!' 
                    && WS_LAST_VAL_COND != '!') {
                    CEP.TRC(SCCGWA, CIRDINF.VAL_COND);
                    CEP.TRC(SCCGWA, WS_LAST_VAL_COND);
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_VAL_COND_DIFF);
                }
                CIRDINF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                CIRDINF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRDINF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                CIRDINF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_INSERT_CITDINF();
                if (pgmRtn) return;
            }
        }
        if (WS_ANUL_FLG != 'N') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CON_NONE);
        }
        R000_CHK_TYP_MTX();
        if (pgmRtn) return;
        T000_INSERT_CITDTL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRDDL);
        IBS.CLONE(SCCGWA, CIRDTL, CIRDDL);
    }
    public void R000_DEL_CITDTL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRDTL);
        IBS.init(SCCGWA, CIRGOPP);
        IBS.init(SCCGWA, CIRDINF);
        if (CICPDTL.TYPE.equalsIgnoreCase("05") 
            && CICPDTL.LC_NO == 0) {
            CIRGOPP.KEY.LC_OBJ = CICPDTL.LC_OBJ;
            CIRGOPP.KEY.LC_OBJ_TYP = "2";
            CIRGOPP.KEY.TYPE = CICPDTL.TYPE;
            CIRGOPP.KEY.TX_TYPE = CICPDTL.TX_TYPE;
            T000_READ_CITGOPP();
            if (pgmRtn) return;
            if (WS_GOPP_FLG == 'Y') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DELETE_NOTFND);
            }
            CICPDTL.LC_NO = CIRGOPP.LC_NO;
            CICPDTL.SEQ = 1;
            IBS.init(SCCGWA, CIRGOPP);
        }
        CIRDTL.KEY.LC_NO = CICPDTL.LC_NO;
        CIRDTL.KEY.LC_SEQ = CICPDTL.SEQ;
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_NO);
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_SEQ);
        T000_DELETE_CITDTL();
        if (pgmRtn) return;
        CIRDINF.KEY.LC_NO = CICPDTL.LC_NO;
        CIRDINF.KEY.LC_SEQ = CICPDTL.SEQ;
        T000_DELETE_CITDINF();
        if (pgmRtn) return;
        T000_STARTBR_CITDTL_GOPP();
        if (pgmRtn) return;
        T000_READNEXT_CITDTL_GOPP();
        if (pgmRtn) return;
        if (WS_DTL_FLG_1 == 'Y') {
            CIRGOPP.KEY.TYPE = CICPDTL.TYPE;
            CIRGOPP.KEY.LC_OBJ = CICPDTL.LC_OBJ;
            CIRGOPP.KEY.LC_OBJ_TYP = "" + WS_LC_OBJ_TYP;
            JIBS_tmp_int = CIRGOPP.KEY.LC_OBJ_TYP.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) CIRGOPP.KEY.LC_OBJ_TYP = "0" + CIRGOPP.KEY.LC_OBJ_TYP;
            CIRGOPP.LC_NO = CICPDTL.LC_NO;
            T000_DELETE_CITGOPP();
            if (pgmRtn) return;
            T000_STARTBR_CITGOPP();
            if (pgmRtn) return;
            T000_READNEXT_CITGOPP();
            if (pgmRtn) return;
            if (WS_GOPP_FLG == 'Y') {
                CIRSTYP.KEY.LC_OBJ = CICPDTL.LC_OBJ;
                CIRSTYP.KEY.LC_OBJ_TYP = "" + WS_LC_OBJ_TYP;
                JIBS_tmp_int = CIRSTYP.KEY.LC_OBJ_TYP.length();
                for (int i=0;i<1-JIBS_tmp_int;i++) CIRSTYP.KEY.LC_OBJ_TYP = "0" + CIRSTYP.KEY.LC_OBJ_TYP;
                CIRSTYP.KEY.TYPE = CICPDTL.TYPE;
                T000_DELETE_CITSTYP();
                if (pgmRtn) return;
            }
            T000_ENDBR_CITGOPP();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITDTL_GOPP();
        if (pgmRtn) return;
    }
    public void R000_INSERT_INTO_CITDTL() throws IOException,SQLException,Exception {
        if (CICPDTL.FUNC == '0') {
            if ((CICPDTL.DAY_STA < SCCGWA.COMM_AREA.AC_DATE) 
                || (CICPDTL.DAY_END < CICPDTL.DAY_STA)) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
            }
        }
        WS_SEQ = 0;
        WS_SUB_SEQ = 0;
        WS_LAST_LC_NO = 0;
        WS_LAST_SEQ = 0;
        WS_LAST_SUB_SEQ = 0;
        R000_INSERT_CITSTYP();
        if (pgmRtn) return;
        R000_INSERT_CITGOPP();
        if (pgmRtn) return;
        R000_INSERT_CITDTL();
        if (pgmRtn) return;
        R000_INSERT_CITDINF();
        if (pgmRtn) return;
        R000_CHECK_IF_DUP_DTL();
        if (pgmRtn) return;
        if (WS_CHECK_MAIN_FLG == 'Y') {
            R000_CHECK_IF_GRT_MAIN();
            if (pgmRtn) return;
        }
        if (WS_SPE_FLG != '4') {
            R000_CHECK_IF_GRT_NORMAL();
            if (pgmRtn) return;
        }
    }
    public void R000_ADD_CITDTL_AMT() throws IOException,SQLException,Exception {
        WS_SEQ = 0;
        WS_SUB_SEQ = 0;
        WS_LAST_LC_NO = 0;
        WS_LAST_SEQ = 0;
        WS_LAST_SUB_SEQ = 0;
        R000_INSERT_CITSTYP();
        if (pgmRtn) return;
        R000_INSERT_CITGOPP();
        if (pgmRtn) return;
        WS_LC_INFO_FLG = 'N';
        if (WS_STYP_FLG == 'Y' 
            && WS_GOPP_FLG == 'N') {
            R000_GET_LC_INFO();
            if (pgmRtn) return;
        }
        if (WS_LC_INFO_FLG == 'N') {
            R000_INSERT_CITDTL();
            if (pgmRtn) return;
            R000_INSERT_CITDINF();
            if (pgmRtn) return;
        } else {
            R000_CHANGE_CITDTL();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_IF_DUP_DTL() throws IOException,SQLException,Exception {
        WS_A_LC_NO = CIRDTL.KEY.LC_NO;
        WS_A_LC_SEQ = CIRDTL.KEY.LC_SEQ;
        WS_A_CON_TYP = CIRDTL.CON_TYP;
        WS_A_STA_DT = CIRDTL.DAY_STA_DT;
        WS_A_END_DT = CIRDTL.DAY_END_DT;
        CEP.TRC(SCCGWA, "1");
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_NO);
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_SEQ);
        T000_STARTBR_CITDTL_NE_SEQ();
        if (pgmRtn) return;
        T000_READNEXT_CITDTL_NE_SEQ();
        if (pgmRtn) return;
        while (WS_DTL_FLG_2 != 'Y') {
            CEP.TRC(SCCGWA, "2");
            CEP.TRC(SCCGWA, CIRDTL.KEY.LC_NO);
            CEP.TRC(SCCGWA, CIRDTL.KEY.LC_SEQ);
            WS_B_LC_NO = CIRDTL.KEY.LC_NO;
            WS_B_LC_SEQ = CIRDTL.KEY.LC_SEQ;
            WS_B_CON_TYP = CIRDTL.CON_TYP;
            WS_B_STA_DT = CIRDTL.DAY_STA_DT;
            WS_B_END_DT = CIRDTL.DAY_END_DT;
            if (WS_A_END_DT <= WS_B_STA_DT 
                || WS_A_STA_DT >= WS_B_END_DT) {
            } else {
                WS_GRT_FLG = 'N';
                R000_CHECK_DUP_DINF();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITDTL_NE_SEQ();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITDTL_NE_SEQ();
        if (pgmRtn) return;
    }
    public void R000_CHECK_DUP_DINF() throws IOException,SQLException,Exception {
        WS_COMP_DINF_FLG = 'Y';
        for (WS_I = 1; WS_I <= 50 
            && WS_COMP_DINF_FLG != 'N'; WS_I += 1) {
            if (WS_A_CON_TYP == null) WS_A_CON_TYP = "";
            JIBS_tmp_int = WS_A_CON_TYP.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_A_CON_TYP += " ";
            if (WS_B_CON_TYP == null) WS_B_CON_TYP = "";
            JIBS_tmp_int = WS_B_CON_TYP.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_B_CON_TYP += " ";
            if (!WS_A_CON_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase(WS_B_CON_TYP.substring(WS_I - 1, WS_I + 1 - 1))) {
                if (WS_B_CON_TYP == null) WS_B_CON_TYP = "";
                JIBS_tmp_int = WS_B_CON_TYP.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_B_CON_TYP += " ";
                if (WS_CON_TYP_LW == null) WS_CON_TYP_LW = "";
                JIBS_tmp_int = WS_CON_TYP_LW.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_CON_TYP_LW += " ";
                if (WS_B_CON_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1") 
                    && WS_CON_TYP_LW.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                } else {
                    if (WS_A_CON_TYP == null) WS_A_CON_TYP = "";
                    JIBS_tmp_int = WS_A_CON_TYP.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) WS_A_CON_TYP += " ";
                    if (WS_A_CON_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                    } else {
                        WS_COMP_DINF_FLG = 'N';
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_A_CON_TYP);
        CEP.TRC(SCCGWA, WS_B_CON_TYP);
        CEP.TRC(SCCGWA, WS_CON_TYP_LW);
        CEP.TRC(SCCGWA, WS_COMP_DINF_FLG);
        if (WS_COMP_DINF_FLG == 'Y') {
            WS_DUP_FLG = 'Y';
            for (WS_I = 1; WS_DUP_FLG != 'N' 
                && WS_I <= 50; WS_I += 1) {
                WS_GET_A_INF_FLG = ' ';
                if (WS_B_CON_TYP == null) WS_B_CON_TYP = "";
                JIBS_tmp_int = WS_B_CON_TYP.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_B_CON_TYP += " ";
                if (WS_B_CON_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                    WS_CON_TYP_N = WS_I;
                    WS_A_CON_TYP_D = "" + WS_CON_TYP_N;
                    JIBS_tmp_int = WS_A_CON_TYP_D.length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) WS_A_CON_TYP_D = "0" + WS_A_CON_TYP_D;
                    WS_B_CON_TYP_D = "" + WS_CON_TYP_N;
                    JIBS_tmp_int = WS_B_CON_TYP_D.length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) WS_B_CON_TYP_D = "0" + WS_B_CON_TYP_D;
                    if (WS_A_CON_TYP == null) WS_A_CON_TYP = "";
                    JIBS_tmp_int = WS_A_CON_TYP.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) WS_A_CON_TYP += " ";
                    if (!WS_A_CON_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                        WS_GET_A_INF_FLG = 'Y';
                    }
                    R000_GET_CHECK_DINF_INFO();
                    if (pgmRtn) return;
                }
            }
            if (WS_DUP_FLG == 'Y') {
                CEP.TRC(SCCGWA, WS_B_LC_NO);
                CEP.TRC(SCCGWA, WS_B_LC_SEQ);
                CEP.TRC(SCCGWA, WS_B_CON_TYP);
                CEP.TRC(SCCGWA, WS_A_LC_NO);
                CEP.TRC(SCCGWA, WS_A_LC_SEQ);
                CEP.TRC(SCCGWA, WS_A_CON_TYP);
                CEP.TRC(SCCGWA, WS_CON_VAL_FLG);
                WS_CON_VAL_FLG = 'Y';
                if (WS_GRT_FLG == 'Y') {
                    IBS.init(SCCGWA, CIRDDL);
                    IBS.CLONE(SCCGWA, CIRDTL, CIRDDL);
                    IBS.init(SCCGWA, CIRDTL);
                    CIRDTL.KEY.LC_NO = WS_A_LC_NO;
                    CIRDTL.KEY.LC_SEQ = WS_A_LC_SEQ;
                    T000_READUP_CITDTL();
                    if (pgmRtn) return;
                    CIRDTL.ACT_SCENE = WS_B_ACT_SCENE;
                    DTL_ACT_SCENE_LEN = CIRDTL.ACT_SCENE.length();
                    CIRDTL.LMT_CTL_STSW = WS_B_LMT_CTL_STSW;
                    CIRDTL.LC_CCY = WS_B_LC_CCY;
                    WS_A_CCY = CIRDTL.LC_CCY;
                    if (CIRDTL.LC_CCY.trim().length() == 0) {
                        CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CCY_INPUT_ERROR);
                    }
