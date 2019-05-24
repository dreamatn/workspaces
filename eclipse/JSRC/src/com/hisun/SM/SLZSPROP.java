package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.AI.*;
import com.hisun.BP.*;

import java.util.StringTokenizer;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SLZSPROP {
    StringTokenizer JIBS_stb;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm SLTPROP_RD;
    brParm SLTPROP_BR = new brParm();
    brParm SLTAC_BR = new brParm();
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "SL PROP INFO        ";
    String K_HIS_CPY = "SLRPROP";
    String K_OUTPUT_FMT_9 = "SL002";
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    int K_OLD_CODE_CNT = 100000;
    String K_SEQ_TYPE = "SLSEQ";
    String K_SEQ_CD = "PROPCD";
    String K_ITM_BOOK_FLG = "BK002";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_J = 0;
    String WS_TEMP_NAME = " ";
    int WS_TEMP_NAME_LEN = 0;
    char WS_AC_FLG = ' ';
    char WS_PROP_FLG = ' ';
    char WS_AC_EXIST_FLG = ' ';
    SLCMSG_ERROR_MSG SLCMSG_ERROR_MSG = new SLCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SLRPROP SLRPROP = new SLRPROP();
    SLRPROP SLRPRPO = new SLRPROP();
    SLRPROP SLRPRPN = new SLRPROP();
    SLCOPRP SLCOPRP = new SLCOPRP();
    SLCOPRPB SLCOPRPB = new SLCOPRPB();
    SLRAC SLRAC = new SLRAC();
    AICPQITM AICPQITM = new AICPQITM();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SLCMPRP SLCMPRP;
    public void MP(SCCGWA SCCGWA, SLCMPRP SLCMPRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SLCMPRP = SLCMPRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SLZSPROP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SLRPROP);
        IBS.init(SCCGWA, SLRPRPO);
        IBS.init(SCCGWA, SLRPRPN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (SLCMPRP.FUNC == 'I') {
            B020_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (SLCMPRP.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if (SLCMPRP.FUNC == 'M') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (SLCMPRP.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else if (SLCMPRP.FUNC == 'B') {
            B060_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (SLCMPRP.FUNC == 'W') {
            B065_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INVALID_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        SLRPROP.KEY.PROP_TYP = SLCMPRP.DATA.PROP_TYP;
        SLRPROP.KEY.PROP_CD = SLCMPRP.DATA.PROP_CD;
        if (SLCMPRP.DATA.PROP_TYP == 1) {
            if (SLCMPRP.DATA.SWIFT_CO.trim().length() == 0) {
                SLRPROP.IB_NAME = SLCMPRP.DATA.IB_NAME;
            } else {
                SLRPROP.SWIFT_CO = SLCMPRP.DATA.SWIFT_CO;
            }
            if (SLCMPRP.DATA.BND_CD.trim().length() > 0 
                || SLCMPRP.DATA.BND_TYPE.trim().length() > 0 
                || SLCMPRP.DATA.MET_FINE.trim().length() > 0) {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INPUT_DATA_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (SLCMPRP.DATA.PROP_TYP == 2) {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_AVOID_PTY2;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else if (SLCMPRP.DATA.PROP_TYP == 3) {
            SLRPROP.BND_CD = SLCMPRP.DATA.BND_CD;
            if (SLCMPRP.DATA.SWIFT_CO.trim().length() > 0 
                || (SLCMPRP.FUNC != 'W' 
                && SLCMPRP.DATA.IB_NAME.trim().length() > 0) 
                || SLCMPRP.DATA.MET_FINE.trim().length() > 0) {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INPUT_DATA_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            R000_CONVERT_PROP_NAME();
            if (pgmRtn) return;
        } else if (SLCMPRP.DATA.PROP_TYP == 4) {
            SLRPROP.MET_FINE = SLCMPRP.DATA.MET_FINE;
            if (SLCMPRP.DATA.SWIFT_CO.trim().length() > 0 
                || (SLCMPRP.FUNC != 'W' 
                && SLCMPRP.DATA.IB_NAME.trim().length() > 0) 
                || SLCMPRP.DATA.BND_CD.trim().length() > 0 
                || SLCMPRP.DATA.BND_TYPE.trim().length() > 0) {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INPUT_DATA_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            R000_CONVERT_PROP_NAME();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INVALID_PTY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SLCMPRP.FUNC);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.PROP_TYP);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.PROP_CD);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.PROP_NAME);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.SWIFT_CO);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.IB_NAME);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.BND_CD);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.BND_TYPE);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.MET_FINE);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.CNTY_CD);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.RSK_VAL);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.IB_TYP);
        CEP.TRC(SCCGWA, SLCMPRP.DATA.REMARK1);
    }
    public void B020_INQUIRE_PROC() throws IOException,SQLException,Exception {
        T000_READ_PROP_FIRST();
        if (pgmRtn) return;
        if (WS_PROP_FLG == 'Y') {
            R000_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            R000_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, SLCMSG_ERROR_MSG.SL_PROP_NOTFND);
        }
    }
    public void B030_ADD_PROC() throws IOException,SQLException,Exception {
        T000_READ_PROP_FIRST();
        if (pgmRtn) return;
        if (WS_PROP_FLG == 'Y') {
            CEP.ERR(SCCGWA, SLCMSG_ERROR_MSG.SL_PROP_EXSIT);
        }
        R000_GEN_PROP_CD();
        if (pgmRtn) return;
        R000_DATA_ADD_TO_TBL();
        if (pgmRtn) return;
        T000_WRITE_SLTPROP();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, SLRPROP, SLRPRPN);
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
        T000_READ_SLTPROP_UPD();
        if (pgmRtn) return;
        if ((SLCMPRP.DATA.PROP_CD == 0 
            || SLCMPRP.DATA.PROP_CD == SLRPROP.KEY.PROP_CD) 
            && (SLCMPRP.DATA.SWIFT_CO.trim().length() == 0 
            || SLCMPRP.DATA.SWIFT_CO.equalsIgnoreCase(SLRPROP.SWIFT_CO)) 
            && (SLCMPRP.DATA.IB_NAME.trim().length() == 0 
            || SLCMPRP.DATA.IB_NAME.equalsIgnoreCase(SLRPROP.IB_NAME)) 
            && (SLCMPRP.DATA.BND_CD.trim().length() == 0 
            || SLCMPRP.DATA.BND_CD.equalsIgnoreCase(SLRPROP.BND_CD)) 
            && (SLCMPRP.DATA.BND_TYPE.trim().length() == 0 
            || SLCMPRP.DATA.BND_TYPE.equalsIgnoreCase(SLRPROP.BND_TYPE)) 
            && (SLCMPRP.DATA.MET_FINE.trim().length() == 0 
            || SLCMPRP.DATA.MET_FINE.equalsIgnoreCase(SLRPROP.MET_FINE)) 
            && (SLCMPRP.DATA.CNTY_CD.trim().length() == 0 
            || SLCMPRP.DATA.CNTY_CD.equalsIgnoreCase(SLRPROP.CNTY_CD)) 
            && (SLCMPRP.DATA.IB_TYP == ' ' 
            || SLCMPRP.DATA.IB_TYP == SLRPROP.IB_TYP) 
            && (SLCMPRP.DATA.REMARK1.trim().length() == 0 
            || SLCMPRP.DATA.REMARK1.equalsIgnoreCase(SLRPROP.REMARK1))) {
        } else {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_MOD_NM_ONLY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, SLRPROP, SLRPRPO);
        R000_DATA_MOD_TO_TBL();
        if (pgmRtn) return;
        T000_REWRITE_SLTPROP();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, SLRPROP, SLRPRPN);
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
        T000_READ_SLTPROP_UPD();
        if (pgmRtn) return;
        SLRAC.KEY.PROP_CD = SLRPROP.KEY.PROP_CD;
        WS_AC_EXIST_FLG = 'N';
        T000_STARTBR_SLTAC();
        if (pgmRtn) return;
        T000_READNEXT_SLTAC();
        if (pgmRtn) return;
        while (WS_AC_FLG != 'N' 
            && WS_AC_EXIST_FLG != 'Y') {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.BOOK_FLG = K_ITM_BOOK_FLG;
            AICPQITM.INPUT_DATA.NO = SLRAC.KEY.ITM;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICPQITM.OUTPUT_DATA.SL_FLG == SLRPROP.KEY.PROP_TYP) {
                WS_AC_EXIST_FLG = 'Y';
            }
            T000_READNEXT_SLTAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_SLTAC();
        if (pgmRtn) return;
        if (WS_AC_EXIST_FLG == 'Y') {
            CEP.ERR(SCCGWA, SLCMSG_ERROR_MSG.SL_NOTDEL_PROP);
        }
        IBS.CLONE(SCCGWA, SLRPROP, SLRPRPO);
        T000_DELETE_SLTPROP();
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
    public void B060_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_READ_PROP_FIRST();
        if (pgmRtn) return;
        if (WS_PROP_FLG == 'Y') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
            B060_02_OUT_BRW_DATA();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, SLCMSG_ERROR_MSG.SL_PROP_NOTFND);
        }
    }
    public void B065_BROWSE_PROC() throws IOException,SQLException,Exception {
        if (SLCMPRP.DATA.IB_NAME.trim().length() > 0 
            && (SLCMPRP.DATA.PROP_TYP == '3' 
            || SLCMPRP.DATA.PROP_TYP == '4')) {
            T000_STARTBR_BY_NAME();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_SLTPROP();
            if (pgmRtn) return;
        }
        T000_READNEXT_SLTPROP();
        if (pgmRtn) return;
        if (WS_PROP_FLG == 'Y') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_PROP_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_SLTPROP();
            if (pgmRtn) return;
        }
        T000_ENDBR_SLTPROP();
        if (pgmRtn) return;
    }
    public void B060_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SLCOPRPB);
        SLCOPRPB.DATA.PROP_TYP = SLRPROP.KEY.PROP_TYP;
        SLCOPRPB.DATA.PROP_CD = SLRPROP.KEY.PROP_CD;
        SLCOPRPB.DATA.SWIFT_CO = SLRPROP.SWIFT_CO;
        SLCOPRPB.DATA.IB_NAME = SLRPROP.IB_NAME;
        SLCOPRPB.DATA.BND_CD = SLRPROP.BND_CD;
        SLCOPRPB.DATA.MET_FINE = SLRPROP.MET_FINE;
        SLCOPRPB.DATA.PROP_NAME = SLRPROP.PROP_NAME;
        SLCOPRPB.DATA.CNTY_CD = SLRPROP.CNTY_CD;
        SLCOPRPB.DATA.RSK_VAL = SLRPROP.RSK_VAL;
        SLCOPRPB.DATA.IB_TYP = SLRPROP.IB_TYP;
        SLCOPRPB.DATA.REMARK1 = SLRPROP.REMARK1;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, SLCOPRPB);
        SCCMPAG.DATA_LEN = 584;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_CONVERT_PROP_NAME() throws IOException,SQLException,Exception {
        if (SLCMPRP.DATA.IB_NAME.trim().length() > 0) {
            JIBS_stb = new StringTokenizer(SLCMPRP.DATA.IB_NAME, " ");
            if (JIBS_stb.hasMoreTokens()) WS_TEMP_NAME = JIBS_stb.nextToken();
            WS_TEMP_NAME_LEN = WS_TEMP_NAME.length();
            if (SLCMPRP.DATA.IB_NAME == null) SLCMPRP.DATA.IB_NAME = "";
            JIBS_tmp_int = SLCMPRP.DATA.IB_NAME.length();
            for (int i=0;i<140-JIBS_tmp_int;i++) SLCMPRP.DATA.IB_NAME += " ";
            if (SLCMPRP.DATA.IB_NAME.charAt(0) == 0X0E) {
                if (SLCMPRP.DATA.IB_NAME == null) SLCMPRP.DATA.IB_NAME = "";
                JIBS_tmp_int = SLCMPRP.DATA.IB_NAME.length();
                for (int i=0;i<140-JIBS_tmp_int;i++) SLCMPRP.DATA.IB_NAME += " ";
                SLCMPRP.DATA.IB_NAME = "%" + SLCMPRP.DATA.IB_NAME.substring(1);
            }
            if (SLCMPRP.DATA.IB_NAME == null) SLCMPRP.DATA.IB_NAME = "";
            JIBS_tmp_int = SLCMPRP.DATA.IB_NAME.length();
            for (int i=0;i<140-JIBS_tmp_int;i++) SLCMPRP.DATA.IB_NAME += " ";
            if (SLCMPRP.DATA.IB_NAME.charAt(WS_TEMP_NAME_LEN - 1) == 0X0F) {
                if (SLCMPRP.DATA.IB_NAME == null) SLCMPRP.DATA.IB_NAME = "";
                JIBS_tmp_int = SLCMPRP.DATA.IB_NAME.length();
                for (int i=0;i<140-JIBS_tmp_int;i++) SLCMPRP.DATA.IB_NAME += " ";
                SLCMPRP.DATA.IB_NAME = SLCMPRP.DATA.IB_NAME.substring(0, WS_TEMP_NAME_LEN - 1) + " " + SLCMPRP.DATA.IB_NAME.substring(WS_TEMP_NAME_LEN + 1 - 1);
            }
            WS_TEMP_NAME = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
            WS_J = 3;
            if (SLCMPRP.DATA.IB_NAME == null) SLCMPRP.DATA.IB_NAME = "";
            JIBS_tmp_int = SLCMPRP.DATA.IB_NAME.length();
            for (int i=0;i<140-JIBS_tmp_int;i++) SLCMPRP.DATA.IB_NAME += " ";
            for (WS_I = 1; WS_I <= 140 
                && SLCMPRP.DATA.IB_NAME.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0; WS_I += 1) {
                if (SLCMPRP.DATA.IB_NAME == null) SLCMPRP.DATA.IB_NAME = "";
                JIBS_tmp_int = SLCMPRP.DATA.IB_NAME.length();
                for (int i=0;i<140-JIBS_tmp_int;i++) SLCMPRP.DATA.IB_NAME += " ";
                if (WS_TEMP_NAME == null) WS_TEMP_NAME = "";
                JIBS_tmp_int = WS_TEMP_NAME.length();
                for (int i=0;i<252-JIBS_tmp_int;i++) WS_TEMP_NAME += " ";
                WS_TEMP_NAME = WS_TEMP_NAME.substring(0, WS_J - 1) + SLCMPRP.DATA.IB_NAME.substring(WS_I - 1, WS_I + 1 - 1) + WS_TEMP_NAME.substring(WS_J + 1 - 1);
                WS_J += 1;
            }
            CEP.TRC(SCCGWA, WS_TEMP_NAME);
            SLRPROP.PROP_NAME = WS_TEMP_NAME;
        }
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        if (SLCMPRP.DATA.PROP_TYP == 1) {
            BPCPNHIS.INFO.REF_NO = SLCMPRP.DATA.SWIFT_CO;
        } else if (SLCMPRP.DATA.PROP_TYP == 3) {
            BPCPNHIS.INFO.REF_NO = SLCMPRP.DATA.BND_CD;
        } else if (SLCMPRP.DATA.PROP_TYP == 4) {
            BPCPNHIS.INFO.REF_NO = SLCMPRP.DATA.MET_FINE;
        } else {
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY;
        BPCPNHIS.INFO.FMT_ID_LEN = 699;
        BPCPNHIS.INFO.OLD_DAT_PT = SLRPRPO;
        BPCPNHIS.INFO.NEW_DAT_PT = SLRPRPN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_GEN_PROP_CD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        SLCMPRP.DATA.PROP_CD = 0;
        BPCSGSEQ.TYPE = K_SEQ_TYPE;
        BPCSGSEQ.CODE = K_SEQ_CD;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        SLCMPRP.DATA.PROP_CD = (int) (BPCSGSEQ.SEQ + K_OLD_CODE_CNT);
    }
    public void R000_DATA_ADD_TO_TBL() throws IOException,SQLException,Exception {
        SLRPROP.KEY.PROP_TYP = SLCMPRP.DATA.PROP_TYP;
        SLRPROP.KEY.PROP_CD = SLCMPRP.DATA.PROP_CD;
        SLRPROP.PROP_NAME = SLCMPRP.DATA.PROP_NAME;
        SLRPROP.SWIFT_CO = SLCMPRP.DATA.SWIFT_CO;
        SLRPROP.IB_NAME = SLCMPRP.DATA.IB_NAME;
        SLRPROP.BND_CD = SLCMPRP.DATA.BND_CD;
        SLRPROP.BND_TYPE = SLCMPRP.DATA.BND_TYPE;
        SLRPROP.MET_FINE = SLCMPRP.DATA.MET_FINE;
        SLRPROP.CNTY_CD = SLCMPRP.DATA.CNTY_CD;
        SLRPROP.RSK_VAL = SLCMPRP.DATA.RSK_VAL;
        SLRPROP.IB_TYP = SLCMPRP.DATA.IB_TYP;
        SLRPROP.REMARK1 = SLCMPRP.DATA.REMARK1;
        SLRPROP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        SLRPROP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        SLRPROP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        SLRPROP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_DATA_MOD_TO_TBL() throws IOException,SQLException,Exception {
        SLRPROP.PROP_NAME = SLCMPRP.DATA.PROP_NAME;
        SLRPROP.RSK_VAL = SLCMPRP.DATA.RSK_VAL;
        SLRPROP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        SLRPROP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SLCOPRP);
        SLCOPRP.DATA.PROP_TYP = SLRPROP.KEY.PROP_TYP;
        SLCOPRP.DATA.PROP_CD = SLRPROP.KEY.PROP_CD;
        SLCOPRP.DATA.PROP_NAME = SLRPROP.PROP_NAME;
        SLCOPRP.DATA.SWIFT_CO = SLRPROP.SWIFT_CO;
        SLCOPRP.DATA.IB_NAME = SLRPROP.IB_NAME;
        SLCOPRP.DATA.BND_CD = SLRPROP.BND_CD;
        SLCOPRP.DATA.BND_TYPE = SLRPROP.BND_TYPE;
        SLCOPRP.DATA.MET_FINE = SLRPROP.MET_FINE;
        SLCOPRP.DATA.CNTY_CD = SLRPROP.CNTY_CD;
        SLCOPRP.DATA.RSK_VAL = SLRPROP.RSK_VAL;
        SLCOPRP.DATA.IB_TYP = SLRPROP.IB_TYP;
        SLCOPRP.DATA.REMARK1 = SLRPROP.REMARK1;
        CEP.TRC(SCCGWA, SLCOPRP.DATA.PROP_TYP);
        CEP.TRC(SCCGWA, SLCOPRP.DATA.PROP_CD);
        CEP.TRC(SCCGWA, SLCOPRP.DATA.SWIFT_CO);
        CEP.TRC(SCCGWA, SLCOPRP.DATA.IB_NAME);
        CEP.TRC(SCCGWA, SLCOPRP.DATA.BND_CD);
        CEP.TRC(SCCGWA, SLCOPRP.DATA.MET_FINE);
        CEP.TRC(SCCGWA, SLCOPRP.DATA.CNTY_CD);
        CEP.TRC(SCCGWA, SLCOPRP.DATA.RSK_VAL);
        CEP.TRC(SCCGWA, SLCOPRP.DATA.IB_TYP);
        CEP.TRC(SCCGWA, SLCOPRP.DATA.PROP_NAME);
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = SLCOPRP;
        SCCFMT.DATA_LEN = 645;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSGSEQ.RC);
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_PROP_FIRST() throws IOException,SQLException,Exception {
        if (SLCMPRP.DATA.PROP_TYP == 1) {
            T000_READ_SLTPROP_TYP1();
            if (pgmRtn) return;
        } else if (SLCMPRP.DATA.PROP_TYP == 3) {
            T000_READ_SLTPROP_TYP3();
            if (pgmRtn) return;
        } else if (SLCMPRP.DATA.PROP_TYP == 4) {
            T000_READ_SLTPROP_TYP4();
            if (pgmRtn) return;
        } else {
            T000_READ_SLTPROP();
            if (pgmRtn) return;
        }
        if (WS_PROP_FLG == 'N' 
            && SLCMPRP.DATA.PROP_CD > 0) {
            IBS.init(SCCGWA, SLRPROP);
            SLRPROP.KEY.PROP_TYP = SLCMPRP.DATA.PROP_TYP;
            SLRPROP.KEY.PROP_CD = SLCMPRP.DATA.PROP_CD;
            T000_READ_SLTPROP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_SLTPROP() throws IOException,SQLException,Exception {
        SLTPROP_RD = new DBParm();
        SLTPROP_RD.TableName = "SLTPROP";
        IBS.READ(SCCGWA, SLRPROP, SLTPROP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PROP_FLG = 'Y';
        } else {
            WS_PROP_FLG = 'N';
        }
    }
    public void T000_READ_SLTPROP_TYP1() throws IOException,SQLException,Exception {
        if (SLCMPRP.DATA.SWIFT_CO.trim().length() == 0) {
            if (SLRPROP.IB_NAME.trim().length() > 0) {
                SLTPROP_RD = new DBParm();
                SLTPROP_RD.TableName = "SLTPROP";
                SLTPROP_RD.where = "IB_NAME = :SLRPROP.IB_NAME";
                SLTPROP_RD.fst = true;
                IBS.READ(SCCGWA, SLRPROP, this, SLTPROP_RD);
            } else {
                SCCGWA.COMM_AREA.DBIO_FLG = '1';
            }
        } else {
            SLTPROP_RD = new DBParm();
            SLTPROP_RD.TableName = "SLTPROP";
            SLTPROP_RD.where = "SWIFT_CO = :SLRPROP.SWIFT_CO";
            SLTPROP_RD.errhdl = true;
            IBS.READ(SCCGWA, SLRPROP, this, SLTPROP_RD);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PROP_FLG = 'Y';
        } else {
            WS_PROP_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_PROP_FLG);
    }
    public void T000_READ_SLTPROP_TYP3() throws IOException,SQLException,Exception {
        if (SLRPROP.BND_CD.trim().length() > 0) {
            SLTPROP_RD = new DBParm();
            SLTPROP_RD.TableName = "SLTPROP";
            SLTPROP_RD.where = "BND_CD = :SLRPROP.BND_CD";
            SLTPROP_RD.errhdl = true;
            IBS.READ(SCCGWA, SLRPROP, this, SLTPROP_RD);
        } else {
            SCCGWA.COMM_AREA.DBIO_FLG = '1';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PROP_FLG = 'Y';
        } else {
            WS_PROP_FLG = 'N';
        }
    }
    public void T000_READ_SLTPROP_TYP4() throws IOException,SQLException,Exception {
        if (SLRPROP.MET_FINE.trim().length() == 0) {
            SCCGWA.COMM_AREA.DBIO_FLG = '1';
        } else {
            SLTPROP_RD = new DBParm();
            SLTPROP_RD.TableName = "SLTPROP";
            SLTPROP_RD.where = "MET_FINE = :SLRPROP.MET_FINE";
            SLTPROP_RD.errhdl = true;
            IBS.READ(SCCGWA, SLRPROP, this, SLTPROP_RD);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PROP_FLG = 'Y';
        } else {
            WS_PROP_FLG = 'N';
        }
    }
    public void T000_STARTBR_SLTPROP() throws IOException,SQLException,Exception {
        SLTPROP_BR.rp = new DBParm();
        SLTPROP_BR.rp.TableName = "SLTPROP";
        SLTPROP_BR.rp.where = "PROP_TYP = :SLRPROP.KEY.PROP_TYP";
        SLTPROP_BR.rp.order = "PROP_CD";
        IBS.STARTBR(SCCGWA, SLRPROP, this, SLTPROP_BR);
    }
    public void T000_STARTBR_BY_NAME() throws IOException,SQLException,Exception {
        SLTPROP_BR.rp = new DBParm();
        SLTPROP_BR.rp.TableName = "SLTPROP";
        SLTPROP_BR.rp.where = "PROP_TYP = :SLRPROP.KEY.PROP_TYP "
            + "AND PROP_NAME LIKE :SLRPROP.PROP_NAME";
        SLTPROP_BR.rp.order = "PROP_CD";
        IBS.STARTBR(SCCGWA, SLRPROP, this, SLTPROP_BR);
    }
    public void T000_READNEXT_SLTPROP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, SLRPROP, this, SLTPROP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PROP_FLG = 'Y';
        } else {
            WS_PROP_FLG = 'N';
        }
    }
    public void T000_ENDBR_SLTPROP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SLTPROP_BR);
    }
    public void T000_WRITE_SLTPROP() throws IOException,SQLException,Exception {
        SLTPROP_RD = new DBParm();
        SLTPROP_RD.TableName = "SLTPROP";
        IBS.WRITE(SCCGWA, SLRPROP, SLTPROP_RD);
    }
    public void T000_READ_SLTPROP_UPD() throws IOException,SQLException,Exception {
        SLTPROP_RD = new DBParm();
        SLTPROP_RD.TableName = "SLTPROP";
        SLTPROP_RD.upd = true;
        IBS.READ(SCCGWA, SLRPROP, SLTPROP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_PROP_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_SLTPROP() throws IOException,SQLException,Exception {
        SLTPROP_RD = new DBParm();
        SLTPROP_RD.TableName = "SLTPROP";
        IBS.REWRITE(SCCGWA, SLRPROP, SLTPROP_RD);
    }
    public void T000_DELETE_SLTPROP() throws IOException,SQLException,Exception {
        SLTPROP_RD = new DBParm();
        SLTPROP_RD.TableName = "SLTPROP";
        IBS.DELETE(SCCGWA, SLRPROP, SLTPROP_RD);
    }
    public void T000_STARTBR_SLTAC() throws IOException,SQLException,Exception {
        SLTAC_BR.rp = new DBParm();
        SLTAC_BR.rp.TableName = "SLTAC";
        SLTAC_BR.rp.eqWhere = "PROP_CD";
        IBS.STARTBR(SCCGWA, SLRAC, SLTAC_BR);
    }
    public void T000_READNEXT_SLTAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, SLRAC, this, SLTAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AC_FLG = 'Y';
        } else {
            WS_AC_FLG = 'N';
        }
    }
    public void T000_ENDBR_SLTAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SLTAC_BR);
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
        if (SCCMSG.MSGID.trim().length() > 0) {
            CEP.TRC(SCCGWA, "SLCMPRP=");
            CEP.TRC(SCCGWA, SLCMPRP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
