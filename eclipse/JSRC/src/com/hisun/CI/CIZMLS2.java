package com.hisun.CI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMLS2 {
    String JIBS_tmp_str[] = new String[10];
    DBParm CITBAS_RD;
    DBParm CITLS2_RD;
    brParm CITLS2_BR = new brParm();
    boolean pgmRtn = false;
    String K_SEQ_TYPE = "CILST";
    String K_SEQ_CODE = "LST";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "LS2     INFO        ";
    String K_HIS_CPY = "CIRLS2";
    String K_OUTPUT_FMT_X = "CIX01";
    String K_OUTPUT_FMT_9 = "CI009";
    int K_MAX_ROW = 50;
    int K_MAX_COL = 99;
    int K_COL_LST = 30;
    int K_MAX_BRW_NUM = 1000;
    String K_ID_TYPE_SF = "10100";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_LST_ORDNO = 0;
    int WS_REC_LEN = 0;
    CIZMLS2_WS_OUTPUT_DATA WS_OUTPUT_DATA = new CIZMLS2_WS_OUTPUT_DATA();
    char WS_LST_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRLS2 CIRLS2 = new CIRLS2();
    CIRLS2 CIRLS2O = new CIRLS2();
    CIRLS2 CIRLS2N = new CIRLS2();
    CICOLS2R CICOLS2R = new CICOLS2R();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CICCKID CICCKID = new CICCKID();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    CICPLST CICPLST = new CICPLST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMLS2 CICMLS2;
    public void MP(SCCGWA SCCGWA, CICMLS2 CICMLS2) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMLS2 = CICMLS2;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMLS2 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRLS2);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_LSTUT_DATA();
        if (pgmRtn) return;
        if (CICMLS2.FUNC == 'I') {
            B020_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (CICMLS2.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if (CICMLS2.FUNC == 'M') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (CICMLS2.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else if (CICMLS2.FUNC == 'B') {
            B080_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + CICMLS2.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_LSTUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLS2.FUNC);
        CEP.TRC(SCCGWA, CICMLS2.OPT);
        CEP.TRC(SCCGWA, CICMLS2.DATA.LST_CD);
        CEP.TRC(SCCGWA, CICMLS2.DATA.LST_SEQ);
        CEP.TRC(SCCGWA, CICMLS2.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICMLS2.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICMLS2.DATA.CI_CNM);
    }
    public void R000_CHECK_LST_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICPLST);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        BPRPRMT.KEY.TYP = "CILST";
        BPRPRMT.KEY.CD = CICMLS2.DATA.LST_CD;
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPLST);
    }
    public void B020_INQUIRE_PROC() throws IOException,SQLException,Exception {
        if (CICMLS2.DATA.ID_NO.trim().length() == 0 
            || CICMLS2.DATA.ID_TYPE.trim().length() == 0 
            || CICMLS2.DATA.CI_CNM.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICMLS2.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CIRLS2.KEY.LST_CD = CICMLS2.DATA.LST_CD;
        CIRLS2.KEY.LST_SEQ = CICMLS2.DATA.LST_SEQ;
        CIRLS2.KEY.ID_TYPE = CICMLS2.DATA.ID_TYPE;
        CIRLS2.KEY.ID_NO = CICMLS2.DATA.ID_NO;
        CIRLS2.KEY.CI_CNM = CICMLS2.DATA.CI_CNM;
        T000_READ_CITLS2();
        if (pgmRtn) return;
    }
    public void B030_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS2);
        IBS.init(SCCGWA, CIRLS2O);
        IBS.init(SCCGWA, CIRLS2N);
        CIRLS2.KEY.LST_CD = CICMLS2.DATA.LST_CD;
        CIRLS2.KEY.LST_SEQ = CICMLS2.DATA.LST_SEQ;
        CIRLS2.KEY.ID_TYPE = CICMLS2.DATA.ID_TYPE;
        CIRLS2.KEY.ID_NO = CICMLS2.DATA.ID_NO;
        CIRLS2.KEY.CI_CNM = CICMLS2.DATA.CI_CNM;
        T000_READ_CITLS2_LSTIDNM();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_TBL();
        if (pgmRtn) return;
        T000_WRITE_CITLS2();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLS2, CIRLS2N);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_WRITE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS2);
        IBS.init(SCCGWA, CIRLS2O);
        IBS.init(SCCGWA, CIRLS2N);
        R000_CHECK_LST_TYP();
        if (pgmRtn) return;
        CIRLS2.KEY.LST_CD = CICMLS2.DATA.LST_CD;
        CIRLS2.KEY.LST_SEQ = CICMLS2.DATA.LST_SEQ;
        T000_READ_CITLS2_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLS2, CIRLS2O);
        R000_TRANS_DATA_TO_TBL();
        if (pgmRtn) return;
        T000_REWRITE_CITLS2();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLS2, CIRLS2N);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRITE_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS2);
        IBS.init(SCCGWA, CIRLS2O);
        IBS.init(SCCGWA, CIRLS2N);
        CIRLS2.KEY.LST_CD = CICMLS2.DATA.LST_CD;
        CIRLS2.KEY.LST_SEQ = CICMLS2.DATA.LST_SEQ;
        CIRLS2.KEY.ID_TYPE = CICMLS2.DATA.ID_TYPE;
        CIRLS2.KEY.ID_NO = CICMLS2.DATA.ID_NO;
        CIRLS2.KEY.CI_CNM = CICMLS2.DATA.CI_CNM;
        T000_READ_CITLS2_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLS2, CIRLS2O);
        T000_DELETE_CITLS2();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        R000_WRITE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B080_BROWSE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLS2.OPT);
        if (CICMLS2.OPT == 'L') {
            B080_10_LST_CD_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (CICMLS2.OPT == 'I') {
            B080_20_ID_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (CICMLS2.OPT == 'N') {
            B080_30_NM_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
        }
        B080_01_OUT_TITLE();
        if (pgmRtn) return;
        T000_READNEXT_CITLS2();
        if (pgmRtn) return;
        if (WS_LST_FLG == 'Y') {
            while (WS_LST_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                WS_I = WS_I + 1;
                B080_02_OUT_BRW_DATA();
                if (pgmRtn) return;
                T000_READNEXT_CITLS2();
                if (pgmRtn) return;
            }
        }
        T000_ENDBR_CITLS2();
        if (pgmRtn) return;
    }
    public void B080_10_LST_CD_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS2);
        WS_I = 0;
        CIRLS2.KEY.LST_CD = CICMLS2.DATA.LST_CD;
        CEP.TRC(SCCGWA, CICMLS2.DATA.LST_CD);
        T000_STARTBR_CITLS2_LST_CD();
        if (pgmRtn) return;
    }
    public void B080_20_ID_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS2);
        WS_I = 0;
        CIRLS2.KEY.LST_CD = CICMLS2.DATA.LST_CD;
        CIRLS2.KEY.LST_SEQ = CICMLS2.DATA.LST_SEQ;
        CIRLS2.KEY.ID_TYPE = CICMLS2.DATA.ID_TYPE;
        CIRLS2.KEY.ID_NO = CICMLS2.DATA.ID_NO;
        CEP.TRC(SCCGWA, CICMLS2.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICMLS2.DATA.ID_NO);
        T000_STARTBR_CITLS2_ID();
        if (pgmRtn) return;
    }
    public void B080_30_NM_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS2);
        WS_I = 0;
        CIRLS2.KEY.LST_CD = CICMLS2.DATA.LST_CD;
        CIRLS2.KEY.LST_SEQ = CICMLS2.DATA.LST_SEQ;
        CIRLS2.KEY.ID_TYPE = CICMLS2.DATA.ID_TYPE;
        CIRLS2.KEY.ID_NO = CICMLS2.DATA.ID_NO;
        CIRLS2.KEY.CI_CNM = CICMLS2.DATA.CI_CNM;
        CIRLS2.CI_ENM = CICMLS2.DATA.CI_ENM;
        T000_STARTBR_CITLS2_CNM();
        if (pgmRtn) return;
    }
    public void B080_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        WS_REC_LEN = 594;
        CEP.TRC(SCCGWA, WS_REC_LEN);
        SCCMPAG.MAX_COL_NO = (short) WS_REC_LEN;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_O_LST_CD = CIRLS2.KEY.LST_CD;
        WS_OUTPUT_DATA.WS_O_LST_SEQ = CIRLS2.KEY.LST_SEQ;
        WS_OUTPUT_DATA.WS_O_ID_TYPE = CIRLS2.KEY.ID_TYPE;
        WS_OUTPUT_DATA.WS_O_ID_NO = CIRLS2.KEY.ID_NO;
        WS_OUTPUT_DATA.WS_O_CI_CNM = CIRLS2.KEY.CI_CNM;
        WS_OUTPUT_DATA.WS_O_CI_ENM = CIRLS2.CI_ENM;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        WS_REC_LEN = 594;
        CEP.TRC(SCCGWA, WS_REC_LEN);
        SCCMPAG.DATA_LEN = (short) WS_REC_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_GET_ORDNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = K_SEQ_TYPE;
        BPCSGSEQ.CODE = K_SEQ_CODE;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        WS_LST_ORDNO = (int) BPCSGSEQ.SEQ;
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSGSEQ.RC);
        }
    }
    public void R000_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY;
        BPCPNHIS.INFO.FMT_ID_LEN = 1540;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRLS2O;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRLS2N;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_TBL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLS2.FUNC);
        CIRLS2.KEY.LST_CD = CICMLS2.DATA.LST_CD;
        CIRLS2.KEY.LST_SEQ = CICMLS2.DATA.LST_SEQ;
        CIRLS2.KEY.ID_TYPE = CICMLS2.DATA.ID_TYPE;
        CIRLS2.KEY.ID_NO = CICMLS2.DATA.ID_NO;
        CIRLS2.CI_ENM = CICMLS2.DATA.CI_ENM;
        CIRLS2.KEY.CI_CNM = CICMLS2.DATA.CI_CNM;
        CIRLS2.SRC_SEQ = CICMLS2.DATA.SRC_SEQ;
        CIRLS2.LST_DT = CICMLS2.DATA.LST_DT;
        CIRLS2.CI_BBR = CICMLS2.DATA.CI_BBR;
        CEP.TRC(SCCGWA, CICMLS2.DATA.CI_BBR);
        CIRLS2.LEG_NAME = CICMLS2.DATA.LEG_NAME;
        CIRLS2.LEG_IDTY = CICMLS2.DATA.LEG_IDTY;
        CIRLS2.LEG_IDNO = CICMLS2.DATA.LEG_IDNO;
        CIRLS2.DFA_CNT = CICMLS2.DATA.DFA_CNT;
        CIRLS2.DFA_AMT = CICMLS2.DATA.DFA_AMT;
        CIRLS2.RSN = CICMLS2.DATA.RSN;
        CIRLS2.DESC = CICMLS2.DATA.DESC;
        if (CICMLS2.FUNC == 'A') {
            CIRLS2.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRLS2.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRLS2.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CIRLS2.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRLS2.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRLS2.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void R000_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOLS2R);
        CICOLS2R.LST_CD = CIRLS2.KEY.LST_CD;
        CICOLS2R.LST_SEQ = CIRLS2.KEY.LST_SEQ;
        CICOLS2R.ID_TYPE = CIRLS2.KEY.ID_TYPE;
        CICOLS2R.ID_NO = CIRLS2.KEY.ID_NO;
        CICOLS2R.CI_ENM = CIRLS2.CI_ENM;
        CICOLS2R.CI_CNM = CIRLS2.KEY.CI_CNM;
        CICOLS2R.CI_BBR = CIRLS2.CI_BBR;
        CICOLS2R.LEG_NAME = CIRLS2.LEG_NAME;
        CICOLS2R.LEG_IDTY = CIRLS2.LEG_IDTY;
        CICOLS2R.LEG_IDNO = CIRLS2.LEG_IDNO;
        CICOLS2R.DFA_CNT = CIRLS2.DFA_CNT;
        CICOLS2R.DFA_AMT = CIRLS2.DFA_AMT;
        CICOLS2R.SRC_SEQ = CIRLS2.SRC_SEQ;
        CICOLS2R.LST_DT = CIRLS2.LST_DT;
        CICOLS2R.RSN = CIRLS2.RSN;
        CICOLS2R.DESC = CIRLS2.DESC;
        CICOLS2R.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CICOLS2R.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICOLS2R.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICOLS2R.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CICOLS2R.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICOLS2R.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLS2.FUNC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (CICMLS2.FUNC == 'A' 
            || CICMLS2.FUNC == 'M' 
            || CICMLS2.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        SCCFMT.DATA_PTR = CICOLS2R;
        SCCFMT.DATA_LEN = 1522;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.col = "CI_NO";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND);
        }
    }
    public void T000_READ_CITLS2() throws IOException,SQLException,Exception {
        CITLS2_RD = new DBParm();
        CITLS2_RD.TableName = "CITLS2";
        IBS.READ(SCCGWA, CIRLS2, CITLS2_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LST_NOTFND, CICMLS2.RC);
            CICMLS2.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITLS2_LSTIDNM() throws IOException,SQLException,Exception {
        CITLS2_RD = new DBParm();
        CITLS2_RD.TableName = "CITLS2";
        CITLS2_RD.where = "LST_CD = :CIRLS2.KEY.LST_CD "
            + "AND LST_SEQ = :CIRLS2.KEY.LST_SEQ "
            + "AND ID_TYPE = :CIRLS2.KEY.ID_TYPE "
            + "AND ID_NO = :CIRLS2.KEY.ID_NO "
            + "AND CI_CNM = :CIRLS2.KEY.CI_CNM";
        IBS.READ(SCCGWA, CIRLS2, this, CITLS2_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_LST_INF_EXIST);
        }
    }
    public void T000_WRITE_CITLS2() throws IOException,SQLException,Exception {
        CITLS2_RD = new DBParm();
        CITLS2_RD.TableName = "CITLS2";
        IBS.WRITE(SCCGWA, CIRLS2, CITLS2_RD);
    }
    public void T000_READ_CITLS2_UPD() throws IOException,SQLException,Exception {
        CITLS2_RD = new DBParm();
        CITLS2_RD.TableName = "CITLS2";
        CITLS2_RD.upd = true;
        IBS.READ(SCCGWA, CIRLS2, CITLS2_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_LST_NOTFND);
        }
    }
    public void T000_REWRITE_CITLS2() throws IOException,SQLException,Exception {
        CITLS2_RD = new DBParm();
        CITLS2_RD.TableName = "CITLS2";
        IBS.REWRITE(SCCGWA, CIRLS2, CITLS2_RD);
    }
    public void T000_DELETE_CITLS2() throws IOException,SQLException,Exception {
        CITLS2_RD = new DBParm();
        CITLS2_RD.TableName = "CITLS2";
        IBS.DELETE(SCCGWA, CIRLS2, CITLS2_RD);
    }
    public void T000_STARTBR_CITLS2_ALL() throws IOException,SQLException,Exception {
        CITLS2_BR.rp = new DBParm();
        CITLS2_BR.rp.TableName = "CITLS2";
        CITLS2_BR.rp.where = "LST_CD = :CIRLS2.KEY.LST_CD "
            + "AND ID_TYPE = :CIRLS2.KEY.ID_TYPE "
            + "AND ID_NO = :CIRLS2.KEY.ID_NO "
            + "AND CI_CNM = :CIRLS2.KEY.CI_CNM "
            + "AND CI_ENM = :CIRLS2.CI_ENM";
        CITLS2_BR.rp.order = "LST_SEQ";
        IBS.STARTBR(SCCGWA, CIRLS2, this, CITLS2_BR);
    }
    public void T000_STARTBR_CITLS2_NM() throws IOException,SQLException,Exception {
        CITLS2_BR.rp = new DBParm();
        CITLS2_BR.rp.TableName = "CITLS2";
        CITLS2_BR.rp.where = "CI_CNM = :CIRLS2.KEY.CI_CNM "
            + "AND CI_ENM = :CIRLS2.CI_ENM";
        CITLS2_BR.rp.order = "LST_SEQ";
        IBS.STARTBR(SCCGWA, CIRLS2, this, CITLS2_BR);
    }
    public void T000_STARTBR_CITLS2_CNM() throws IOException,SQLException,Exception {
        CITLS2_BR.rp = new DBParm();
        CITLS2_BR.rp.TableName = "CITLS2";
        CITLS2_BR.rp.where = "LST_CD = :CIRLS2.KEY.LST_CD "
            + "AND LST_SEQ = :CIRLS2.KEY.LST_SEQ "
            + "AND ID_TYPE = :CIRLS2.KEY.ID_TYPE "
            + "AND ID_NO = :CIRLS2.KEY.ID_NO "
            + "AND ( :CIRLS2.KEY.CI_CNM = ' ' "
            + "OR CI_CNM = :CIRLS2.KEY.CI_CNM )";
        CITLS2_BR.rp.order = "LST_SEQ";
        IBS.STARTBR(SCCGWA, CIRLS2, this, CITLS2_BR);
    }
    public void T000_STARTBR_CITLS2_ENM() throws IOException,SQLException,Exception {
        CITLS2_BR.rp = new DBParm();
        CITLS2_BR.rp.TableName = "CITLS2";
        CITLS2_BR.rp.where = "CI_ENM = :CIRLS2.CI_ENM";
        CITLS2_BR.rp.order = "LST_SEQ";
        IBS.STARTBR(SCCGWA, CIRLS2, this, CITLS2_BR);
    }
    public void T000_STARTBR_CITLS2_LST_CD() throws IOException,SQLException,Exception {
        CITLS2_BR.rp = new DBParm();
        CITLS2_BR.rp.TableName = "CITLS2";
        CITLS2_BR.rp.where = "LST_CD = :CIRLS2.KEY.LST_CD";
        CITLS2_BR.rp.order = "LST_CD,LST_SEQ,ID_TYPE,ID_NO,CI_CNM";
        IBS.STARTBR(SCCGWA, CIRLS2, this, CITLS2_BR);
    }
    public void T000_STARTBR_CITLS2_ID() throws IOException,SQLException,Exception {
        CITLS2_BR.rp = new DBParm();
        CITLS2_BR.rp.TableName = "CITLS2";
        CITLS2_BR.rp.where = "LST_CD = :CIRLS2.KEY.LST_CD "
            + "AND LST_SEQ = :CIRLS2.KEY.LST_SEQ "
            + "AND ID_TYPE = :CIRLS2.KEY.ID_TYPE "
            + "AND ID_NO = :CIRLS2.KEY.ID_NO";
        CITLS2_BR.rp.order = "LST_CD,LST_SEQ,ID_TYPE,ID_NO,CI_CNM";
        IBS.STARTBR(SCCGWA, CIRLS2, this, CITLS2_BR);
    }
    public void T000_READNEXT_CITLS2() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRLS2, this, CITLS2_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LST_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_LST_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITLS2";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_CITLS2() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITLS2_BR);
    }
    public void S000_LINK_CIZCKID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-ID-CHECK ", CICCKID);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_LST_CD_NOT_DEF);
            } else {
                CEP.ERR(SCCGWA, BPCPRMR.RC);
            }
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
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
        if (CICMLS2.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICMLS2=");
            CEP.TRC(SCCGWA, CICMLS2);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
