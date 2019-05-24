package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

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

public class CIZSDTL {
    boolean pgmRtn = false;
    String K_PRDP_TYP = "PRDPR";
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
    short WS_IDX = 0;
    short WS_L = 0;
    short WS_O = 0;
    short WS_P = 0;
    short WS_Q = 0;
    short WS_OCNT = 0;
    short WS_LTH = 0;
    short WS_X = 0;
    short WS_C = 0;
    short WS_D = 0;
    int WS_LC_SEQ = 0;
    int WS_SUB_SEQ = 0;
    int WS_SEQ = 0;
    int WS_LAST_LC_NO = 0;
    int WS_LAST_SEQ = 0;
    int WS_LAST_SUB_SEQ = 0;
    char WS_LAST_VAL_COND = ' ';
    String WS_LAST_CON_TYP = " ";
    short WS_CON_TYP_N = 0;
    int WS_A_LC_NO = 0;
    int WS_A_LC_SEQ = 0;
    String WS_A_CON_TYP = " ";
    String WS_A_CON_TYP_D = " ";
    String WS_A_VAL = " ";
    char WS_A_VAL_COND = ' ';
    int WS_A_STA_DT = 0;
    int WS_A_END_DT = 0;
    int WS_B_LC_NO = 0;
    int WS_B_LC_SEQ = 0;
    String WS_B_CON_TYP = " ";
    String WS_B_CON_TYP_D = " ";
    String WS_B_VAL = " ";
    char WS_B_VAL_COND = ' ';
    int WS_B_STA_DT = 0;
    int WS_B_END_DT = 0;
    String WS_A_VAL_1 = " ";
    String WS_A_VAL_2 = " ";
    String WS_B_VAL_1 = " ";
    String WS_B_VAL_2 = " ";
    CIZSDTL_WS_TEXT_INFO WS_TEXT_INFO = new CIZSDTL_WS_TEXT_INFO();
    String WS_LMT_TYP = " ";
    CIZSDTL_WS_SX_INF WS_SX_INF = new CIZSDTL_WS_SX_INF();
    CIZSDTL_WS_SX_INF_1 WS_SX_INF_1 = new CIZSDTL_WS_SX_INF_1();
    String WS_CON_TYP_1 = " ";
    char WS_MSREL_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_CMPR_FLG = ' ';
    char WS_DTL_FLG_1 = ' ';
    char WS_DTL_FLG_2 = ' ';
    char WS_DINF_FLG_A = ' ';
    char WS_DINF_FLG_B = ' ';
    char WS_DUP_FLG = ' ';
    char WS_TEMP_FLG = ' ';
    char WS_DINF_CHK_FLG = ' ';
    char WS_DEL_DINF_FLG = ' ';
    char WS_CON_TYP_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    CIRTYP CIRTYP = new CIRTYP();
    CIRDTL CIRDTL = new CIRDTL();
    CIRCMPR CIRCMPR = new CIRCMPR();
    CIRDINF CIRDINF = new CIRDINF();
    CIRTEMP CIRTEMP = new CIRTEMP();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICMDTL CICMDTL;
    public void MP(SCCGWA SCCGWA, CICMDTL CICMDTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMDTL = CICMDTL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSDTL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, CIRTYP);
        IBS.init(SCCGWA, CIRCMPR);
        IBS.init(SCCGWA, CIRDTL);
        IBS.init(SCCGWA, CIRDINF);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, WS_CMPR_FLG);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICMDTL.FUNC);
        if (CICMDTL.FUNC == '0') {
            B210_ADD_DTL_INFO();
            if (pgmRtn) return;
        } else if (CICMDTL.FUNC == '1') {
            B220_UPD_DTL_INFO();
            if (pgmRtn) return;
        } else if (CICMDTL.FUNC == '2') {
            B230_DEL_DTL_INFO();
            if (pgmRtn) return;
        } else if (CICMDTL.FUNC == '3') {
            B230_DEL_DTL_INFO();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERR);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        R000_CHECK_TYPE_INFO();
        if (pgmRtn) return;
    }
    public void B210_ADD_DTL_INFO() throws IOException,SQLException,Exception {
        R000_INSERT_INTO_CITDTL();
        if (pgmRtn) return;
    }
    public void B220_UPD_DTL_INFO() throws IOException,SQLException,Exception {
        R000_CHECK_IF_TEMP_FOUND();
        if (pgmRtn) return;
        if (WS_DEL_DINF_FLG == 'Y') {
            R000_CHANGE_CITDTL_INFO();
            if (pgmRtn) return;
        } else {
            R000_DEL_CITDTL_INFO();
            if (pgmRtn) return;
            R000_INSERT_INTO_CITDTL();
            if (pgmRtn) return;
        }
    }
    public void B230_DEL_DTL_INFO() throws IOException,SQLException,Exception {
        R000_DEL_CITDTL_INFO();
        if (pgmRtn) return;
    }
    public void B240_QRY_DTL_INFO() throws IOException,SQLException,Exception {
    }
    public void R000_CHECK_TYPE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRTYP);
        CIRTYP.KEY.TYPE = CICMDTL.TYPE;
        T000_READ_CITTYP();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (CICMDTL.OBJ_TYP == null) CICMDTL.OBJ_TYP = "";
            JIBS_tmp_int = CICMDTL.OBJ_TYP.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CICMDTL.OBJ_TYP += " ";
            if (CIRTYP.OBJ_TYP == null) CIRTYP.OBJ_TYP = "";
            JIBS_tmp_int = CIRTYP.OBJ_TYP.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CIRTYP.OBJ_TYP += " ";
            if (CICMDTL.OBJ_TYP == null) CICMDTL.OBJ_TYP = "";
            JIBS_tmp_int = CICMDTL.OBJ_TYP.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CICMDTL.OBJ_TYP += " ";
            if (!CICMDTL.OBJ_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase(CIRTYP.OBJ_TYP.substring(WS_I - 1, WS_I + 1 - 1)) 
                && CICMDTL.OBJ_TYP.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TYP_NOT_MARCH_OBJ);
            }
        }
        WS_LMT_TYP = CIRTYP.LMT_TYP;
    }
    public void R000_INSERT_CITCMPR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCMPR);
        CIRCMPR.KEY.TYPE = CICMDTL.TYPE;
        CIRCMPR.KEY.TX_TYPE = CICMDTL.TX_TYPE;
        T000_READUPD_CITCMPR();
        if (pgmRtn) return;
        CIRCMPR.LC_NO = CICMDTL.LC_NO;
        if (CIRCMPR.LC_NO == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_LC_NO_ZERO);
        }
        CIRCMPR.AVL_FLG = '1';
        CIRCMPR.STA_DT = CICMDTL.DAY_STA;
        CIRCMPR.END_DT = CICMDTL.DAY_END;
        CIRCMPR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRCMPR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (WS_CMPR_FLG == 'N') {
            T000_REWRITE_CITCMPR();
            if (pgmRtn) return;
        } else {
            CIRCMPR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CIRCMPR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_INSERT_CITCMPR();
            if (pgmRtn) return;
        }
    }
    public void R000_CHANGE_CITDTL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRDTL);
        CIRDTL.KEY.LC_NO = CICMDTL.LC_NO;
        CIRDTL.KEY.LC_SEQ = CICMDTL.SEQ;
        T000_READUPD_CITDTL();
        if (pgmRtn) return;
        R000_INSERT_CITDTL();
        if (pgmRtn) return;
        T000_REWRITE_CITDTL();
        if (pgmRtn) return;
    }
    public void R000_INSERT_CITDTL() throws IOException,SQLException,Exception {
        if (WS_DEL_DINF_FLG == 'Y') {
            WS_CON_TYP_1 = CIRDTL.CON_TYP;
            WS_LTH = 104;
            if (CIRDTL.SX_VAL == null) CIRDTL.SX_VAL = "";
            JIBS_tmp_int = CIRDTL.SX_VAL.length();
            for (int i=0;i<104-JIBS_tmp_int;i++) CIRDTL.SX_VAL += " ";
            IBS.CPY2CLS(SCCGWA, CIRDTL.SX_VAL.substring(0, WS_LTH), WS_SX_INF_1);
