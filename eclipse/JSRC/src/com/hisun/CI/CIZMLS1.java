package com.hisun.CI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMLS1 {
    String JIBS_tmp_str[] = new String[10];
    DBParm CITLS1_RD;
    DBParm CITBAS_RD;
    brParm CITLS1_BR = new brParm();
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "CI LS1 INFO        ";
    String K_HIS_CPY = "CIRLS1";
    String K_OUTPUT_FMT_X = "CIX01";
    String K_OUTPUT_FMT_9 = "CI008";
    int K_MAX_ROW = 10;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    int K_MAX_BRW_NUM = 1000;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    String WS_ENTY_NO = " ";
    int WS_ENTY_NO_LEN = 0;
    char WS_LS1_FLG = ' ';
    char WS_BAS_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRLS1 CIRLS1 = new CIRLS1();
    CIRBAS CIRBAS = new CIRBAS();
    CICOLS1L CICOLS1L = new CICOLS1L();
    CICPLST CICPLST = new CICPLST();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CICOLS1 CICOLS1 = new CICOLS1();
    CIRLS1 CIRLS1O = new CIRLS1();
    CIRLS1 CIRLS1N = new CIRLS1();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMLS1 CICMLS1;
    public void MP(SCCGWA SCCGWA, CICMLS1 CICMLS1) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMLS1 = CICMLS1;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMLS1 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRLS1);
        IBS.init(SCCGWA, CIRBAS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICMLS1.FUNC);
        if (CICMLS1.FUNC == 'I') {
            B020_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (CICMLS1.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if (CICMLS1.FUNC == 'M') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (CICMLS1.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else if (CICMLS1.FUNC == 'B') {
            B080_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, 10);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void R000_CHECK_LST_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICPLST);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        BPRPRMT.KEY.TYP = "CILST";
        BPRPRMT.KEY.CD = CICMLS1.DATA.INP_OUT_DATA.LST_CD;
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPLST);
    }
    public void B020_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS1);
        IBS.init(SCCGWA, CIRBAS);
        CIRLS1.KEY.LST_CD = CICMLS1.DATA.INP_OUT_DATA.LST_CD;
        CIRLS1.KEY.ENTY_TYP = CICMLS1.DATA.INP_OUT_DATA.ENTY_TYP;
        CIRLS1.KEY.ENTY_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        T000_READ_CITLS1();
        if (pgmRtn) return;
        CIRBAS.KEY.CI_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        T000_READ_CI_NAME_CITBAS();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B030_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS1);
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRLS1O);
        IBS.init(SCCGWA, CIRLS1N);
        CIRBAS.KEY.CI_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        T000_READ_CI_NAME_CITBAS();
        if (pgmRtn) return;
        if (WS_BAS_FLG == 'N') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        R000_CHECK_LST_TYP();
        if (pgmRtn) return;
        CIRLS1.KEY.LST_CD = CICMLS1.DATA.INP_OUT_DATA.LST_CD;
        CIRLS1.KEY.ENTY_TYP = CICMLS1.DATA.INP_OUT_DATA.ENTY_TYP;
        CIRLS1.KEY.ENTY_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        T000_READ_CITLS1_BY_CDNOTYP();
        if (pgmRtn) return;
        R000_TRANS_DATA_ADD_TO_TBL();
        if (pgmRtn) return;
        T000_WRITE_CITLS1();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLS1, CIRLS1N);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B040_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS1);
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRLS1O);
        IBS.init(SCCGWA, CIRLS1N);
        CIRBAS.KEY.CI_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        T000_READ_CI_NAME_CITBAS();
        if (pgmRtn) return;
        if (WS_BAS_FLG == 'N') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        R000_CHECK_LST_TYP();
        if (pgmRtn) return;
        CIRLS1.KEY.LST_CD = CICMLS1.DATA.INP_OUT_DATA.LST_CD;
        CIRLS1.KEY.ENTY_TYP = CICMLS1.DATA.INP_OUT_DATA.ENTY_TYP;
        CIRLS1.KEY.ENTY_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        CEP.TRC(SCCGWA, CIRLS1.KEY.LST_CD);
        CEP.TRC(SCCGWA, CIRLS1.KEY.ENTY_TYP);
        CEP.TRC(SCCGWA, CIRLS1.KEY.ENTY_NO);
        T000_READ_CITLS1_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLS1, CIRLS1O);
        R000_TRANS_DATA_MD_TO_TBL();
        if (pgmRtn) return;
        T000_REWRITE_CITLS1();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLS1, CIRLS1N);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS1);
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRLS1O);
        IBS.init(SCCGWA, CIRLS1N);
        CIRBAS.KEY.CI_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        T000_READ_CI_NAME_CITBAS();
        if (pgmRtn) return;
        if (WS_BAS_FLG == 'N') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CIRLS1.KEY.LST_CD = CICMLS1.DATA.INP_OUT_DATA.LST_CD;
        CIRLS1.KEY.ENTY_TYP = CICMLS1.DATA.INP_OUT_DATA.ENTY_TYP;
        CIRLS1.KEY.ENTY_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        T000_READ_CITLS1_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLS1, CIRLS1O);
        T000_DELETE_CITLS1();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B080_BROWSE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLS1.OPT);
        if (CICMLS1.OPT == 'E') {
            B080_20_ENTY_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (CICMLS1.OPT == 'A') {
            B080_30_ALL_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + CICMLS1.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B080_20_ENTY_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS1);
        IBS.init(SCCGWA, CIRBAS);
        WS_I = 0;
        IBS.CPY2CLS(SCCGWA, CICMLS1.DATA.INP_OUT_DATA.ENTY_NO, CIRBAS);
        T000_READ_CI_NAME_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CUST INF NOT FOUND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CIRLS1.KEY.ENTY_TYP = CICMLS1.DATA.INP_OUT_DATA.ENTY_TYP;
        CIRLS1.KEY.ENTY_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        T000_STARTBR_CITLS1_ENTY();
        if (pgmRtn) return;
        T000_READNEXT_CITLS1();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            WS_I = WS_I + 1;
            CEP.TRC(SCCGWA, WS_I);
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITLS1();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITLS1();
        if (pgmRtn) return;
    }
    public void B080_30_ALL_BROWSE_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_LST_TYP();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRLS1);
        IBS.init(SCCGWA, CIRBAS);
        WS_I = 0;
        CIRBAS.KEY.CI_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        T000_READ_CI_NAME_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CUST INF NOT FOUND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CIRLS1.KEY.LST_CD = CICMLS1.DATA.INP_OUT_DATA.LST_CD;
        CIRLS1.KEY.ENTY_TYP = CICMLS1.DATA.INP_OUT_DATA.ENTY_TYP;
        CIRLS1.KEY.ENTY_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        CEP.TRC(SCCGWA, CIRLS1.KEY.LST_CD);
        CEP.TRC(SCCGWA, CIRLS1.KEY.ENTY_TYP);
        CEP.TRC(SCCGWA, CIRLS1.KEY.ENTY_NO);
        T000_READ_CITLS1();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
        }
    }
    public void B080_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOLS1L);
        CICOLS1L.LST_CD = CIRLS1.KEY.LST_CD;
        CICOLS1L.ENTY_TYP = CIRLS1.KEY.ENTY_TYP;
        CICOLS1L.ENTY_NO = CIRLS1.KEY.ENTY_NO;
        CICOLS1L.REL_CI = CIRLS1.REL_CI;
        CICOLS1L.REL_NM = CIRBAS.CI_NM;
        CICOLS1L.RSN = CIRLS1.RSN;
        CICOLS1L.DESC = CIRLS1.DESC;
        CICOLS1L.CRT_TLR = CIRLS1.CRT_TLR;
        CICOLS1L.CRT_DT = CIRLS1.CRT_DT;
        CICOLS1L.CRT_BR = CIRLS1.CRT_BR;
        CICOLS1L.UPD_TLR = CIRLS1.UPD_TLR;
        CICOLS1L.UPD_DT = CIRLS1.UPD_DT;
        CICOLS1L.UPD_BR = CIRLS1.UPD_BR;
        CEP.TRC(SCCGWA, CICOLS1L.REL_NM);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOLS1L);
        SCCMPAG.DATA_LEN = 587;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.CI_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY;
        BPCPNHIS.INFO.FMT_ID_LEN = 358;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRLS1O;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRLS1N;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_ADD_TO_TBL() throws IOException,SQLException,Exception {
        CIRLS1.KEY.LST_CD = CICMLS1.DATA.INP_OUT_DATA.LST_CD;
        CIRLS1.KEY.ENTY_TYP = CICMLS1.DATA.INP_OUT_DATA.ENTY_TYP;
        CIRLS1.KEY.ENTY_NO = CICMLS1.DATA.INP_OUT_DATA.ENTY_NO;
        CIRLS1.REL_CI = CICMLS1.DATA.INP_OUT_DATA.REL_CI;
        CIRLS1.RSN = CICMLS1.DATA.INP_OUT_DATA.RSN;
        CIRLS1.DESC = CICMLS1.DATA.INP_OUT_DATA.DESC;
        CIRLS1.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRLS1.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRLS1.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRLS1.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRLS1.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRLS1.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void R000_TRANS_DATA_MD_TO_TBL() throws IOException,SQLException,Exception {
        CIRLS1.RSN = CICMLS1.DATA.INP_OUT_DATA.RSN;
        CIRLS1.DESC = CICMLS1.DATA.INP_OUT_DATA.DESC;
        CIRLS1.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRLS1.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRLS1.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void R000_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOLS1);
        CICOLS1.LST_CD = CIRLS1.KEY.LST_CD;
        CICOLS1.ENTY_TYP = CIRLS1.KEY.ENTY_TYP;
        CICOLS1.ENTY_NO = CIRLS1.KEY.ENTY_NO;
        CICOLS1.REL_CI = CIRLS1.REL_CI;
        CICOLS1.REL_NM = CIRBAS.CI_NM;
        CICOLS1.RSN = CIRLS1.RSN;
        CICOLS1.DESC = CIRLS1.DESC;
        CICOLS1.CRT_TLR = CIRLS1.CRT_TLR;
        CICOLS1.CRT_DT = CIRLS1.CRT_DT;
        CICOLS1.CRT_BR = CIRLS1.CRT_BR;
        CICOLS1.UPD_TLR = CIRLS1.UPD_TLR;
        CICOLS1.UPD_DT = CIRLS1.UPD_DT;
        CICOLS1.UPD_BR = CIRLS1.UPD_BR;
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        if (CICMLS1.FUNC == 'A' 
            || CICMLS1.FUNC == 'M' 
            || CICMLS1.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        } else {
            SCCFMT.FMTID = K_OUTPUT_FMT_X;
        }
        SCCFMT.DATA_PTR = CICOLS1;
        SCCFMT.DATA_LEN = 587;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITLS1() throws IOException,SQLException,Exception {
        CITLS1_RD = new DBParm();
        CITLS1_RD.TableName = "CITLS1";
        IBS.READ(SCCGWA, CIRLS1, CITLS1_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_LS1_INF_NOTFND);
        }
    }
    public void T000_READ_CITLS1_BY_CDNOTYP() throws IOException,SQLException,Exception {
        CITLS1_RD = new DBParm();
        CITLS1_RD.TableName = "CITLS1";
        IBS.READ(SCCGWA, CIRLS1, CITLS1_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_LS1_INF_EXIST);
        }
    }
    public void T000_READ_CI_NAME_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.col = "CI_NM";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BAS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BAS_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_CITLS1() throws IOException,SQLException,Exception {
        CITLS1_RD = new DBParm();
        CITLS1_RD.TableName = "CITLS1";
        IBS.WRITE(SCCGWA, CIRLS1, CITLS1_RD);
    }
    public void T000_READ_CITLS1_UPD() throws IOException,SQLException,Exception {
        CITLS1_RD = new DBParm();
        CITLS1_RD.TableName = "CITLS1";
        CITLS1_RD.upd = true;
        IBS.READ(SCCGWA, CIRLS1, CITLS1_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_LS1_INF_NOTFND);
        }
    }
    public void T000_REWRITE_CITLS1() throws IOException,SQLException,Exception {
        CITLS1_RD = new DBParm();
        CITLS1_RD.TableName = "CITLS1";
        IBS.REWRITE(SCCGWA, CIRLS1, CITLS1_RD);
    }
    public void T000_DELETE_CITLS1() throws IOException,SQLException,Exception {
        CITLS1_RD = new DBParm();
        CITLS1_RD.TableName = "CITLS1";
        IBS.DELETE(SCCGWA, CIRLS1, CITLS1_RD);
    }
    public void T000_STARTBR_CITLS1_ALL() throws IOException,SQLException,Exception {
        CITLS1_BR.rp = new DBParm();
        CITLS1_BR.rp.TableName = "CITLS1";
        IBS.STARTBR(SCCGWA, CIRLS1, CITLS1_BR);
    }
    public void T000_STARTBR_CITLS1_ENTY() throws IOException,SQLException,Exception {
        CITLS1_BR.rp = new DBParm();
        CITLS1_BR.rp.TableName = "CITLS1";
        CITLS1_BR.rp.eqWhere = "ENTY_TYP,ENTY_NO";
        IBS.STARTBR(SCCGWA, CIRLS1, CITLS1_BR);
    }
    public void T000_READNEXT_CITLS1() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRLS1, this, CITLS1_BR);
    }
    public void T000_ENDBR_CITLS1() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITLS1_BR);
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
        if (CICMLS1.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICMLS1=");
            CEP.TRC(SCCGWA, CICMLS1);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
