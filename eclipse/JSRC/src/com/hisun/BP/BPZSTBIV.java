package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTBIV {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTVHPB_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPA65";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD ";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    int K_MAX_ROW = 99;
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_J = 0;
    int WS_K = 0;
    String WS_TEMP_PLBOX_NO = " ";
    char WS_POLL_BOX_IND = ' ';
    int WS_COPY_NUM = 0;
    BPZSTBIV_WS_TBVB_HEAD WS_TBVB_HEAD = new BPZSTBIV_WS_TBVB_HEAD();
    BPZSTBIV_WS_TEMP_DETAIL WS_TEMP_DETAIL = new BPZSTBIV_WS_TEMP_DETAIL();
    BPZSTBIV_WS_BPRD_KEY WS_BPRD_KEY = new BPZSTBIV_WS_BPRD_KEY();
    char WS_TBL_TBVD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPRPBPRD BPRPBPRD = new BPRPBPRD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCO173 BPCO173 = new BPCO173();
    SCCGWA SCCGWA;
    BPCSTBDB BPCSTBDB;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTBDB BPCSTBDB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTBDB = BPCSTBDB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTBIV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, BPCRTBDB);
        IBS.init(SCCGWA, BPRTBVD);
        IBS.init(SCCGWA, BPCSTBDB.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTBDB.FUNC);
        CEP.TRC(SCCGWA, BPCSTBDB.BR);
        CEP.TRC(SCCGWA, BPCSTBDB.TLR);
        CEP.TRC(SCCGWA, BPCSTBDB.VB_FLG);
        CEP.TRC(SCCGWA, BPCSTBDB.STS);
        CEP.TRC(SCCGWA, BPCSTBDB.BV_CODE);
        CEP.TRC(SCCGWA, BPCSTBDB.TYPE);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_MAIN_PLBOX_BROWSE_CN();
            if (pgmRtn) return;
        } else {
            B010_MAIN_PLBOX_BROWSE();
            if (pgmRtn) return;
        }
        B040_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_MAIN_PLBOX_BROWSE_CN() throws IOException,SQLException,Exception {
        if (BPCSTBDB.TLR.trim().length() > 0) {
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.BR = BPCSTBDB.BR;
            BPRVHPB.CUR_TLR = BPCSTBDB.TLR;
            BPRVHPB.POLL_BOX_IND = BPCSTBDB.VB_FLG;
            BPCRVHPB.INFO.FUNC = 'B';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
            BPCRVHPB.INFO.FUNC = 'N';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            for (WS_CNT = 1; BPCRVHPB.RETURN_INFO != 'N' 
                && WS_CNT <= 500; WS_CNT += 1) {
                WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                if (WS_TEMP_PLBOX_NO.trim().length() > 0) {
                    B010_BROWSE_PROCESS_CH();
                    if (pgmRtn) return;
                }
                BPCRVHPB.INFO.FUNC = 'N';
                S000_CALL_BPZRVHPB();
                if (pgmRtn) return;
            }
        } else {
            B010_BROWSE_PROCESS_CH();
            if (pgmRtn) return;
        }
    }
    public void B010_MAIN_PLBOX_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = BPCSTBDB.BR;
        BPRVHPB.CUR_TLR = BPCSTBDB.TLR;
        BPRVHPB.POLL_BOX_IND = BPCSTBDB.VB_FLG;
        BPCRVHPB.INFO.FUNC = 'B';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        BPCRVHPB.INFO.FUNC = 'N';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        for (WS_CNT = 1; BPCRVHPB.RETURN_INFO != 'N' 
            && WS_CNT <= 500; WS_CNT += 1) {
            WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            B010_BROWSE_PROCESS();
            if (pgmRtn) return;
            BPCRVHPB.INFO.FUNC = 'N';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTBDB.BR);
        BPRTBVD.KEY.BR = BPCSTBDB.BR;
        CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
        if (WS_TEMP_PLBOX_NO.trim().length() == 0) {
            BPRTBVD.KEY.PL_BOX_NO = "%%%%%%";
        } else {
            BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        }
        if (BPCSTBDB.BV_CODE.trim().length() == 0) {
            BPRTBVD.KEY.BV_CODE = "%%%%%%";
        } else {
            BPRTBVD.KEY.BV_CODE = BPCSTBDB.BV_CODE;
        }
        if (BPCSTBDB.TYPE == ' ') {
            BPRTBVD.TYPE = ALL.charAt(0);
        } else {
            BPRTBVD.TYPE = BPCSTBDB.TYPE;
        }
        if (BPCSTBDB.STS == ' ') {
            BPCRTBDB.INFO.FUNC = 'S';
        } else {
            BPRTBVD.KEY.STS = BPCSTBDB.STS;
            BPCRTBDB.INFO.FUNC = 'G';
        }
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
        BPCRTBDB.INFO.FUNC = 'N';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
        if (BPCSTBDB.FUNC == '0') {
            CEP.TRC(SCCGWA, "BROWSE DETAIL");
            for (WS_CNT = 1; BPCRTBDB.RETURN_INFO != 'N' 
                && WS_CNT <= 500 
                && SCCMPAG.FUNC != 'E' 
                && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
                if (!BPRTBVD.KEY.BV_CODE.equalsIgnoreCase(WS_TEMP_DETAIL.WS_TEMP_CODE)) {
                    B011_SEARCH_BVNM();
                    if (pgmRtn) return;
                }
                WS_TEMP_DETAIL.WS_TEMP_CODE = BPRTBVD.KEY.BV_CODE;
                CEP.TRC(SCCGWA, BPRPBPRD.DATA_TXT.BV_CNM);
                B010_03_OUTPUT_DETAIL();
                if (pgmRtn) return;
                BPCRTBDB.INFO.FUNC = 'N';
                S000_CALL_BPZRTBDB();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "BROWSE TOTAL");
            WS_TEMP_DETAIL.WS_TEMP_TLR = BPCSTBDB.TLR;
            if (BPRTBVD.KEY.PL_BOX_NO == null) BPRTBVD.KEY.PL_BOX_NO = "";
            JIBS_tmp_int = BPRTBVD.KEY.PL_BOX_NO.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPRTBVD.KEY.PL_BOX_NO += " ";
            WS_TEMP_DETAIL.WS_TEMP_VB_FLG = BPRTBVD.KEY.PL_BOX_NO.substring(0, 1).charAt(0);
            WS_TEMP_DETAIL.WS_TEMP_CODE = BPRTBVD.KEY.BV_CODE;
            WS_TEMP_DETAIL.WS_TEMP_STS = BPRTBVD.KEY.STS;
            WS_TEMP_DETAIL.WS_TEMP_HEAD_NO = BPRTBVD.KEY.HEAD_NO;
            WS_TEMP_DETAIL.WS_NUM = 0;
            WS_CNT = 0;
            for (WS_CNT = 1; BPCRTBDB.RETURN_INFO != 'N' 
                && WS_CNT <= 500 
                && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
                B011_SEARCH_BVNM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPRPBPRD.DATA_TXT.BV_CNM);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
                if (BPRTBVD.KEY.PL_BOX_NO == null) BPRTBVD.KEY.PL_BOX_NO = "";
                JIBS_tmp_int = BPRTBVD.KEY.PL_BOX_NO.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPRTBVD.KEY.PL_BOX_NO += " ";
                CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO.substring(0, 1));
                CEP.TRC(SCCGWA, WS_TEMP_DETAIL.WS_TEMP_VB_FLG);
                CEP.TRC(SCCGWA, "121212");
                CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
                CEP.TRC(SCCGWA, WS_TEMP_DETAIL.WS_TEMP_CODE);
                if (BPRTBVD.KEY.PL_BOX_NO == null) BPRTBVD.KEY.PL_BOX_NO = "";
                JIBS_tmp_int = BPRTBVD.KEY.PL_BOX_NO.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPRTBVD.KEY.PL_BOX_NO += " ";
                if (!BPCSTBDB.TLR.equalsIgnoreCase(WS_TEMP_DETAIL.WS_TEMP_TLR) 
                    || !BPRTBVD.KEY.PL_BOX_NO.substring(0, 1).equalsIgnoreCase(WS_TEMP_DETAIL.WS_TEMP_VB_FLG+"") 
                    || !BPRTBVD.KEY.BV_CODE.equalsIgnoreCase(WS_TEMP_DETAIL.WS_TEMP_CODE) 
                    || BPRTBVD.KEY.STS != WS_TEMP_DETAIL.WS_TEMP_STS 
                    || !BPRTBVD.KEY.HEAD_NO.equalsIgnoreCase(WS_TEMP_DETAIL.WS_TEMP_HEAD_NO)) {
                    B010_04_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                    WS_TEMP_DETAIL.WS_TEMP_TLR = BPCSTBDB.TLR;
                    if (BPRTBVD.KEY.PL_BOX_NO == null) BPRTBVD.KEY.PL_BOX_NO = "";
                    JIBS_tmp_int = BPRTBVD.KEY.PL_BOX_NO.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPRTBVD.KEY.PL_BOX_NO += " ";
                    WS_TEMP_DETAIL.WS_TEMP_VB_FLG = BPRTBVD.KEY.PL_BOX_NO.substring(0, 1).charAt(0);
                    WS_TEMP_DETAIL.WS_TEMP_CODE = BPRTBVD.KEY.BV_CODE;
                    WS_TEMP_DETAIL.WS_TEMP_STS = BPRTBVD.KEY.STS;
                    WS_TEMP_DETAIL.WS_TEMP_HEAD_NO = BPRTBVD.KEY.HEAD_NO;
                    WS_TEMP_DETAIL.WS_NUM = BPRTBVD.NUM;
                } else {
                    CEP.TRC(SCCGWA, "101010");
                    WS_TEMP_DETAIL.WS_NUM = WS_TEMP_DETAIL.WS_NUM + BPRTBVD.NUM;
                }
                WS_TEMP_DETAIL.WS_TEMP_BVNM = BPCFBVQU.TX_DATA.BV_CNM;
                BPCRTBDB.INFO.FUNC = 'N';
                S000_CALL_BPZRTBDB();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_CNT);
            if (WS_CNT != 1) {
                CEP.TRC(SCCGWA, "AAAAAAAAA");
                B011_SEARCH_BVNM();
                if (pgmRtn) return;
                B010_04_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
        }
        BPCRTBDB.INFO.FUNC = 'E';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
    }
    public void B010_BROWSE_PROCESS_CH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTBDB.BR);
        BPRTBVD.KEY.BR = BPCSTBDB.BR;
        CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
        BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = BPCSTBDB.BV_CODE;
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
        BPRTBVD.TYPE = BPCSTBDB.TYPE;
        CEP.TRC(SCCGWA, BPRTBVD.TYPE);
        BPRTBVD.KEY.STS = BPCSTBDB.STS;
        CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
        BPCRTBDB.INFO.FUNC = 'K';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
        BPCRTBDB.INFO.FUNC = 'N';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
        if (BPCRTBDB.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "NOT FOUND RTBDB AAAAAAAAAAAAAAAAAAA");
        }
        if (BPCSTBDB.FUNC == '0') {
            CEP.TRC(SCCGWA, "BROWSE DETAIL");
            for (WS_CNT = 1; BPCRTBDB.RETURN_INFO != 'N' 
                && WS_CNT <= 500; WS_CNT += 1) {
                if (!BPRTBVD.KEY.BV_CODE.equalsIgnoreCase(WS_TEMP_DETAIL.WS_TEMP_CODE)) {
                    B011_SEARCH_BVNM();
                    if (pgmRtn) return;
                }
                WS_TEMP_DETAIL.WS_TEMP_CODE = BPRTBVD.KEY.BV_CODE;
                CEP.TRC(SCCGWA, BPRPBPRD.DATA_TXT.BV_CNM);
                B010_03_OUTPUT_DETAIL();
                if (pgmRtn) return;
                BPCRTBDB.INFO.FUNC = 'N';
                S000_CALL_BPZRTBDB();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "BROWSE TOTAL");
            WS_TEMP_DETAIL.WS_TEMP_TLR = BPCSTBDB.TLR;
            if (BPRTBVD.KEY.PL_BOX_NO == null) BPRTBVD.KEY.PL_BOX_NO = "";
            JIBS_tmp_int = BPRTBVD.KEY.PL_BOX_NO.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPRTBVD.KEY.PL_BOX_NO += " ";
            WS_TEMP_DETAIL.WS_TEMP_VB_FLG = BPRTBVD.KEY.PL_BOX_NO.substring(0, 1).charAt(0);
            WS_TEMP_DETAIL.WS_TEMP_CODE = BPRTBVD.KEY.BV_CODE;
            WS_TEMP_DETAIL.WS_TEMP_STS = BPRTBVD.KEY.STS;
            WS_TEMP_DETAIL.WS_TEMP_HEAD_NO = BPRTBVD.KEY.HEAD_NO;
            WS_TEMP_DETAIL.WS_NUM = 0;
            WS_TEMP_DETAIL.WS_PL_BOX_NO = BPRTBVD.KEY.PL_BOX_NO;
            WS_CNT = 0;
            for (WS_CNT = 1; BPCRTBDB.RETURN_INFO != 'N'; WS_CNT += 1) {
                CEP.TRC(SCCGWA, BPRPBPRD.DATA_TXT.BV_CNM);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
                if (BPRTBVD.KEY.PL_BOX_NO == null) BPRTBVD.KEY.PL_BOX_NO = "";
                JIBS_tmp_int = BPRTBVD.KEY.PL_BOX_NO.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPRTBVD.KEY.PL_BOX_NO += " ";
                CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO.substring(0, 1));
                CEP.TRC(SCCGWA, WS_TEMP_DETAIL.WS_TEMP_VB_FLG);
                CEP.TRC(SCCGWA, "121212");
                CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
                CEP.TRC(SCCGWA, WS_TEMP_DETAIL.WS_TEMP_CODE);
                if (!BPRTBVD.KEY.PL_BOX_NO.equalsIgnoreCase(WS_TEMP_DETAIL.WS_PL_BOX_NO) 
                    || !BPRTBVD.KEY.BV_CODE.equalsIgnoreCase(WS_TEMP_DETAIL.WS_TEMP_CODE) 
                    || BPRTBVD.KEY.STS != WS_TEMP_DETAIL.WS_TEMP_STS 
                    || !BPRTBVD.KEY.HEAD_NO.equalsIgnoreCase(WS_TEMP_DETAIL.WS_TEMP_HEAD_NO)) {
                    B010_04_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                    WS_TEMP_DETAIL.WS_TEMP_TLR = BPCSTBDB.TLR;
                    if (BPRTBVD.KEY.PL_BOX_NO == null) BPRTBVD.KEY.PL_BOX_NO = "";
                    JIBS_tmp_int = BPRTBVD.KEY.PL_BOX_NO.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPRTBVD.KEY.PL_BOX_NO += " ";
                    WS_TEMP_DETAIL.WS_TEMP_VB_FLG = BPRTBVD.KEY.PL_BOX_NO.substring(0, 1).charAt(0);
                    WS_TEMP_DETAIL.WS_TEMP_CODE = BPRTBVD.KEY.BV_CODE;
                    WS_TEMP_DETAIL.WS_TEMP_STS = BPRTBVD.KEY.STS;
                    WS_TEMP_DETAIL.WS_TEMP_HEAD_NO = BPRTBVD.KEY.HEAD_NO;
                    WS_TEMP_DETAIL.WS_NUM = BPRTBVD.NUM;
                    WS_TEMP_DETAIL.WS_PL_BOX_NO = BPRTBVD.KEY.PL_BOX_NO;
                } else {
                    CEP.TRC(SCCGWA, "101010");
                    WS_TEMP_DETAIL.WS_NUM = WS_TEMP_DETAIL.WS_NUM + BPRTBVD.NUM;
                }
                WS_TEMP_DETAIL.WS_TEMP_BVNM = BPCFBVQU.TX_DATA.BV_CNM;
                BPCRTBDB.INFO.FUNC = 'N';
                S000_CALL_BPZRTBDB();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_CNT);
            if (WS_CNT != 1) {
                CEP.TRC(SCCGWA, "AAAAAAAAA");
                B010_04_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
        }
        BPCRTBDB.INFO.FUNC = 'E';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, SCCFMT);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 4741;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCO173);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO173;
        SCCFMT.DATA_LEN = 4741;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B010_04_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        B011_SEARCH_BVNM();
        if (pgmRtn) return;
        BPCO173.BR = BPRTBVD.KEY.BR;
        if (BPCSTBDB.TLR.trim().length() > 0) {
            BPCO173.PB_NO = WS_TEMP_PLBOX_NO;
            BPCO173.TLR = BPRVHPB.CUR_TLR;
            BPCO173.VB_FLG = BPRVHPB.POLL_BOX_IND;
        } else {
            BPCO173.PB_NO = WS_TEMP_DETAIL.WS_PL_BOX_NO;
            BPRVHPB.KEY.POOL_BOX_NO = WS_TEMP_DETAIL.WS_PL_BOX_NO;
            T000_READ_BPTVHPB();
            if (pgmRtn) return;
            BPCO173.TLR = BPRVHPB.CUR_TLR;
            BPCO173.VB_FLG = BPRVHPB.POLL_BOX_IND;
        }
        BPCO173.VB_INFO[WS_CNT-1].STS = WS_TEMP_DETAIL.WS_TEMP_STS;
        BPCO173.VB_INFO[WS_CNT-1].BV_CODE = WS_TEMP_DETAIL.WS_TEMP_CODE;
        BPCO173.VB_INFO[WS_CNT-1].BLL_CNM = BPCFBVQU.TX_DATA.BV_CNM;
        BPCO173.VB_INFO[WS_CNT-1].HEAD_NO = WS_TEMP_DETAIL.WS_TEMP_HEAD_NO;
        CEP.TRC(SCCGWA, "11");
        CEP.TRC(SCCGWA, WS_TEMP_DETAIL.WS_TEMP_CODE);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CNT_UT);
        CEP.TRC(SCCGWA, WS_TEMP_DETAIL.WS_NUM);
        WS_COPY_NUM = 0;
        if (BPCFBVQU.TX_DATA.CNT_UT != 0) {
            WS_COPY_NUM = (int) ((WS_TEMP_DETAIL.WS_NUM - WS_TEMP_DETAIL.WS_NUM % BPCFBVQU.TX_DATA.CNT_UT) / BPCFBVQU.TX_DATA.CNT_UT);
            BPCO173.VB_INFO[WS_CNT-1].NUM = WS_TEMP_DETAIL.WS_NUM - BPCFBVQU.TX_DATA.CNT_UT * WS_COPY_NUM;
            BPCO173.VB_INFO[WS_CNT-1].COPY_NUM = WS_COPY_NUM;
        } else {
            BPCO173.VB_INFO[WS_CNT-1].COPY_NUM = 0;
            BPCO173.VB_INFO[WS_CNT-1].NUM = WS_TEMP_DETAIL.WS_NUM;
        }
        CEP.TRC(SCCGWA, "22");
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CNT_UT);
        CEP.TRC(SCCGWA, BPCO173.VB_INFO[WS_CNT-1].COPY_NUM);
        CEP.TRC(SCCGWA, BPCO173.VB_INFO[WS_CNT-1].NUM);
        CEP.TRC(SCCGWA, BPCO173);
    }
    public void B010_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO173);
        BPCO173.BR = BPRTBVD.KEY.BR;
        if (BPCSTBDB.TLR.trim().length() > 0) {
            BPCO173.PB_NO = WS_TEMP_PLBOX_NO;
            BPCO173.TLR = BPRVHPB.CUR_TLR;
            BPCO173.VB_FLG = BPRVHPB.POLL_BOX_IND;
        } else {
            BPCO173.PB_NO = BPRTBVD.KEY.PL_BOX_NO;
            BPRVHPB.KEY.POOL_BOX_NO = BPRTBVD.KEY.PL_BOX_NO;
            T000_READ_BPTVHPB();
            if (pgmRtn) return;
            BPCO173.TLR = BPRVHPB.CUR_TLR;
            BPCO173.VB_FLG = BPRVHPB.POLL_BOX_IND;
        }
        BPCO173.TLR = BPRVHPB.CUR_TLR;
        BPCO173.VB_FLG = BPRVHPB.POLL_BOX_IND;
        BPCO173.VB_INFO[WS_CNT-1].STS = BPRTBVD.KEY.STS;
        BPCO173.VB_INFO[WS_CNT-1].BV_CODE = BPRTBVD.KEY.BV_CODE;
        BPCO173.VB_INFO[WS_CNT-1].BLL_CNM = BPCFBVQU.TX_DATA.BV_CNM;
        BPCO173.VB_INFO[WS_CNT-1].HEAD_NO = BPRTBVD.KEY.HEAD_NO;
        BPCO173.VB_INFO[WS_CNT-1].BEG_NO = BPRTBVD.BEG_NO;
        BPCO173.VB_INFO[WS_CNT-1].END_NO = BPRTBVD.KEY.END_NO;
        WS_COPY_NUM = 0;
        CEP.TRC(SCCGWA, "33");
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CNT_UT);
        CEP.TRC(SCCGWA, BPRTBVD.NUM);
        CEP.TRC(SCCGWA, WS_COPY_NUM);
        if (BPCFBVQU.TX_DATA.CNT_UT == 0) {
            BPCO173.VB_INFO[WS_CNT-1].COPY_NUM = 0;
            BPCO173.VB_INFO[WS_CNT-1].NUM = BPRTBVD.NUM;
        } else {
            WS_COPY_NUM = (int) ((BPRTBVD.NUM - BPRTBVD.NUM % BPCFBVQU.TX_DATA.CNT_UT) / BPCFBVQU.TX_DATA.CNT_UT);
            BPCO173.VB_INFO[WS_CNT-1].NUM = BPRTBVD.NUM - BPCFBVQU.TX_DATA.CNT_UT * WS_COPY_NUM;
            BPCO173.VB_INFO[WS_CNT-1].COPY_NUM = WS_COPY_NUM;
            CEP.TRC(SCCGWA, "44");
            CEP.TRC(SCCGWA, BPCO173.VB_INFO[WS_CNT-1].NUM);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CNT_UT);
            CEP.TRC(SCCGWA, BPRTBVD.NUM);
            CEP.TRC(SCCGWA, WS_COPY_NUM);
            CEP.TRC(SCCGWA, BPCO173);
        }
    }
    public void B011_SEARCH_BVNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        CEP.TRC(SCCGWA, "000");
        CEP.TRC(SCCGWA, WS_TEMP_DETAIL.WS_TEMP_CODE);
        if (BPCSTBDB.FUNC == '0') {
            BPCFBVQU.TX_DATA.KEY.CODE = BPRTBVD.KEY.BV_CODE;
        } else {
            BPCFBVQU.TX_DATA.KEY.CODE = WS_TEMP_DETAIL.WS_TEMP_CODE;
        }
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TEMP_DETAIL.WS_TEMP_CODE);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CNT_UT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_TBVD, BPCRTBDB);
        if (BPCRTBDB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBDB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSTBDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_RD = new DBParm();
        BPTVHPB_RD.TableName = "BPTVHPB";
        IBS.READ(SCCGWA, BPRVHPB, BPTVHPB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRVHPB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRVHPB.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_REC_NOTFND, BPCRVHPB.RC);
        } else {
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
