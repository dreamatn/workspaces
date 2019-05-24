package com.hisun.BP;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMMRT {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_RATEID = "RRTID";
    String K_BASE_TYPE = "RBASE";
    String K_TENOR = "RTENO";
    String K_RTBK_AP_TYPE = "UPBKRT";
    String K_DD_SERV_CODE = "DDPEOD8";
    String K_TD_SERV_CODE = "BSPTD04";
    String K_FMT_CD_X01 = "BPX01";
    String K_FMT_CD_303 = "BP303";
    String K_FMT_CD_306 = "BP306";
    String K_FMT_CD_307 = "BP307";
    String K_FMT_CD_308 = "BP308";
    String K_HIS_COPYBOOK_RTID = "BPRRTID";
    String K_HIS_COPYBOOK_INTR = "BPRINTR";
    String K_HIS_COPYBOOK_RTFW = "BPRRTFW";
    String K_HIS_REMARKS = "BASE RATE MAINTENANCE";
    String K_HIS_REMARKS_RTID = "RATE ID   MAINTENANCE";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_PROC_DD = "DDPEOD80";
    String K_PROC_TD = "TDPEOD51";
    String K_SPEC_TENOR = "301";
    int K_BATCH_CNT = 40;
    int K_BKVAL_CNT = 1;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    int WS_CNT = 0;
    int WS_BKVAL_CNT = 0;
    int WS_DAYS = 0;
    String WS_CAL_CD = " ";
    int WS_ORI_DATE = 0;
    int WS_PREV_DATE = 0;
    int WS_OEFF_DATE = 0;
    int WS_DT = 0;
    String WS_TS = " ";
    double WS_OEFF_RATE = 0;
    int WS_FST_RT_DT = 0;
    long WS_JRNNO = 0;
    String WS_RTID_CCY = " ";
    String WS_RTID_TENOR = " ";
    BPZSMMRT_WS_HIS_REF_NO WS_HIS_REF_NO = new BPZSMMRT_WS_HIS_REF_NO();
    BPZSMMRT_WS_HIS_REF_RTID WS_HIS_REF_RTID = new BPZSMMRT_WS_HIS_REF_RTID();
    String WS_TMP_CCY = " ";
    String WS_TMP_TENOR = " ";
    int WS_TMP_EFF_DT = 0;
    int WS_TMP_OEFF_DT = 0;
    double WS_TMP_RATE = 0;
    String WS_TMP_RTID = " ";
    int WS_RTFW_NEW_DT = 0;
    int WS_RTFW_OLD_DT = 0;
    double WS_PREV_RATE = 0;
    BPZSMMRT_WS_IHIT_RECORDS[] WS_IHIT_RECORDS = new BPZSMMRT_WS_IHIT_RECORDS[50];
    BPZSMMRT_WS_RTBK_INFO WS_RTBK_INFO = new BPZSMMRT_WS_RTBK_INFO();
    BPZSMMRT_WS_BASE_RT_HD WS_BASE_RT_HD = new BPZSMMRT_WS_BASE_RT_HD();
    BPZSMMRT_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSMMRT_WS_OUTPUT_DATA();
    char WS_OUTPUT_FLG = ' ';
    char WS_FILE_NAME = ' ';
    char WS_HIS_FLG = ' ';
    char WS_IHIT_FLG = ' ';
    char WS_BSP_FLG = ' ';
    char WS_RTBK_SEQ_FLG = ' ';
    char WS_INTR_REC_FOUND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRINTR BPRINTR = new BPRINTR();
    BPRRTID BPRRTID = new BPRRTID();
    BPRRTFW BPRRTFW = new BPRRTFW();
    BPRRTBK BPRRTBK = new BPRRTBK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRINTR BPRBINTR = new BPRINTR();
    BPRRTFW BPRBRTFW = new BPRRTFW();
    BPCRINTM BPCRINTM = new BPCRINTM();
    BPCRINTB BPCRINTB = new BPCRINTB();
    BPCRHITM BPCRHITM = new BPCRHITM();
    BPCRHITB BPCRHITB = new BPCRHITB();
    BPCRMRTD BPCRMRTD = new BPCRMRTD();
    BPCO306 BPCO306 = new BPCO306();
    BPCOQRTD BPCOQRTD = new BPCOQRTD();
    BPCRRTFW BPCRRTFW = new BPCRRTFW();
    BPCRRTBK BPCRRTBK = new BPCRRTBK();
    BPCRHISM BPCRHISM = new BPCRHISM();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCO307 BPCO307 = new BPCO307();
    BPCO308 BPCO308 = new BPCO308();
    BPCO303 BPCO303 = new BPCO303();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCBSP SCCBSP = new SCCBSP();
    SCCBSPS SCCBSPS = new SCCBSPS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSMMRT BPCSMMRT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSMMRT() {
        for (int i=0;i<50;i++) WS_IHIT_RECORDS[i] = new BPZSMMRT_WS_IHIT_RECORDS();
    }
    public void MP(SCCGWA SCCGWA, BPCSMMRT BPCSMMRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMMRT = BPCSMMRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMMRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCSMMRT.BR = 999999;
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSMMRT.FUNC == 'I') {
            B010_QUERY_INTR_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMMRT.FUNC == 'A') {
            B020_ADD_INTR_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMMRT.FUNC == 'U') {
            B030_UPDATE_INTR_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMMRT.FUNC == 'D') {
            B040_DELETE_INTR_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMMRT.FUNC == 'B') {
            B050_BROWSE_INTR_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMMRT.FUNC == 'N') {
            B060_BRW_BTH_INTR_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMMRT.FUNC == 'H') {
            if (BPCSMMRT.BTH_OPT == 'M') {
                B070_BTH_INTR_MARKET_PROC();
                if (pgmRtn) return;
            } else {
                B070_BTH_INTR_BASE_PROC();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSMMRT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMMRT.FUNC);
        if (BPCSMMRT.FUNC == 'B'
            || BPCSMMRT.FUNC == 'I'
            || BPCSMMRT.FUNC == 'U'
            || BPCSMMRT.FUNC == 'D'
            || BPCSMMRT.FUNC == 'A'
            || BPCSMMRT.FUNC == 'H'
            || BPCSMMRT.FUNC == 'N') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSMMRT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, BPCSMMRT.BASE_TYP);
        CEP.TRC(SCCGWA, BPCSMMRT.BR);
        if (BPCSMMRT.BR == 0 
            || BPCSMMRT.BASE_TYP.trim().length() == 0) {
            CEP.TRC(SCCGWA, "1111");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMMRT.FUNC == 'I' 
            || BPCSMMRT.FUNC == 'D') {
            CEP.TRC(SCCGWA, "CHECK-INQ--------------------");
            CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].TENOR);
            CEP.TRC(SCCGWA, BPCSMMRT.CCY);
            CEP.TRC(SCCGWA, );
            CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].OEFF_DT);
            if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                || BPCSMMRT.CCY.trim().length() == 0 
                || BPCSMMRT.UPD_DATA[1-1].OEFF_DT == 0) {
                CEP.TRC(SCCGWA, "2222");
                CEP.TRC(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSMMRT.FUNC == 'A') {
            if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                || BPCSMMRT.CCY.trim().length() == 0 
                || BPCSMMRT.UPD_DATA[1-1].OEFF_DT == 0 
                || BPCSMMRT.UPD_DATA[1-1].ORATE == 0 
                || BPCSMMRT.UPD_DATA[1-1].RATE_ID.trim().length() == 0) {
                CEP.TRC(SCCGWA, "3333");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
            WS_TMP_RTID = BPCSMMRT.UPD_DATA[1-1].RATE_ID;
            WS_RTID_CCY = BPCSMMRT.CCY;
            B100_01_CHECK_RATEID_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMMRT.FUNC == 'D') {
            if (BPCSMMRT.UPD_DATA[1-1].OEFF_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_DEL_EFF_RT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSMMRT.FUNC == 'U') {
            if (BPCSMMRT.UPD_DATA[1-1].TENOR.equalsIgnoreCase("0") 
                || BPCSMMRT.CCY.trim().length() == 0 
                || BPCSMMRT.UPD_DATA[1-1].OEFF_DT == 0 
                || BPCSMMRT.UPD_DATA[1-1].ORATE == 0 
                || BPCSMMRT.UPD_DATA[1-1].NEFF_DT == 0 
                || BPCSMMRT.UPD_DATA[1-1].NRATE == 0) {
                CEP.TRC(SCCGWA, "4444");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMMRT.UPD_DATA[1-1].NEFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_TMP_OEFF_DT = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
                WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[1-1].NEFF_DT;
                B100_02_CHK_DAYS_BTW_ONDT();
                if (pgmRtn) return;
            }
        }
        if (BPCSMMRT.FUNC == 'H') {
            WS_BKVAL_CNT = 0;
            for (WS_I = 1; WS_I <= K_BATCH_CNT; WS_I += 1) {
                if (BPCSMMRT.UPD_DATA[WS_I-1].OEFF_DT > 0) {
                    if (BPCSMMRT.UPD_DATA[WS_I-1].NEFF_DT > 0 
                        && BPCSMMRT.UPD_DATA[WS_I-1].NEFF_DT != SCCGWA.COMM_AREA.AC_DATE) {
                        WS_BKVAL_CNT = WS_BKVAL_CNT + 1;
                    }
                }
            }
            if (WS_BKVAL_CNT > K_BKVAL_CNT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BKVAL_CNT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMMRT.BTH_OPT == 'M') {
                if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                    || BPCSMMRT.UPD_DATA[1-1].NEFF_DT == 0 
                    || BPCSMMRT.UPD_DATA[1-1].NRATE == 0 
                    || BPCSMMRT.UPD_DATA[1-1].RATE_ID.trim().length() == 0) {
                    CEP.TRC(SCCGWA, "5555");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (BPCSMMRT.UPD_DATA[1-1].CCY_TBL.trim().length() == 0 
                    || BPCSMMRT.UPD_DATA[1-1].NEFF_DT == 0 
                    || BPCSMMRT.UPD_DATA[1-1].NRATE == 0 
                    || BPCSMMRT.UPD_DATA[1-1].RATE_ID.trim().length() == 0) {
                    CEP.TRC(SCCGWA, "6666");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B100_01_CHECK_RATEID_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RATEID;
        BPCOQPCD.INPUT_DATA.CODE = WS_TMP_RTID;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        WS_RTID_TENOR = WS_TMP_TENOR;
        B300_01_GET_RATEID();
        if (pgmRtn) return;
        if (BPCOQRTD.DATA.RATE_ID.trim().length() > 0) {
            if (!WS_TMP_RTID.equalsIgnoreCase(BPCOQRTD.DATA.RATE_ID)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_ID_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_ID_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_02_CHK_DAYS_BTW_ONDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '1';
        BPCOQCAL.CCY = "HKD";
        S000_CALL_BPZPQCAL();
        if (pgmRtn) return;
        WS_CAL_CD = BPCOQCAL.CAL_CODE;
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = WS_CAL_CD;
        BPCOCLWD.DATE1 = WS_TMP_OEFF_DT;
        BPCOCLWD.DATE2 = WS_TMP_EFF_DT;
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        WS_DAYS = BPCOCLWD.DAYS;
        CEP.TRC(SCCGWA, WS_DAYS);
        if (WS_DAYS > 366 
            && WS_TMP_OEFF_DT > WS_TMP_EFF_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MORE_ONE_MONTH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_INTR_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSMMRT.UPD_DATA[1-1].OEFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, BPCRRTFW);
            IBS.init(SCCGWA, BPRRTFW);
            BPRRTFW.KEY.BR = BPCSMMRT.BR;
            BPRRTFW.KEY.BASE_TYP = BPCSMMRT.BASE_TYP;
            BPRRTFW.KEY.TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
            BPRRTFW.KEY.CCY = BPCSMMRT.CCY;
            BPRRTFW.KEY.VAL_DT = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
            BPCRRTFW.INFO.FUNC = 'Q';
            BPCRRTFW.INFO.POINTER = BPRRTFW;
            BPCRRTFW.INFO.LEN = 128;
            S000_CALL_BPZRRTFW();
            if (pgmRtn) return;
            if (BPCRRTFW.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_FILE_NAME = 'R';
        } else {
            IBS.init(SCCGWA, BPCRINTM);
            BPCRINTM.BR = BPCSMMRT.BR;
            BPCRINTM.BASE_TYP = BPCSMMRT.BASE_TYP;
            BPCRINTM.TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
            BPCRINTM.CCY = BPCSMMRT.CCY;
            CEP.TRC(SCCGWA, BPCSMMRT.BR);
            CEP.TRC(SCCGWA, BPCSMMRT.BASE_TYP);
            CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].TENOR);
            CEP.TRC(SCCGWA, BPCSMMRT.CCY);
            BPCRINTM.FUNC = 'I';
            S000_CALL_BPZRINTM();
            if (pgmRtn) return;
            if (BPCRINTM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_FILE_NAME = 'I';
        }
        B200_OUTPUT_BASIC_PROC();
        if (pgmRtn) return;
    }
    public void B020_ADD_INTR_PROCESS() throws IOException,SQLException,Exception {
        WS_TMP_CCY = BPCSMMRT.CCY;
        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
        WS_TMP_RATE = BPCSMMRT.UPD_DATA[1-1].ORATE;
        WS_TMP_RTID = BPCSMMRT.UPD_DATA[1-1].RATE_ID;
        B000_01_WRITE_BASE_RT_PROC();
        if (pgmRtn) return;
        B200_OUTPUT_BASIC_PROC();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_INTR_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMMRT.BASE_TYP);
        CEP.TRC(SCCGWA, BPCSMMRT.CCY);
        CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].TENOR);
        CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].RATE_ID);
        CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].OEFF_DT);
        CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].ORATE);
        CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].NEFF_DT);
        CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].NRATE);
        if (BPCSMMRT.UPD_DATA[1-1].NEFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            if (BPCSMMRT.UPD_DATA[1-1].OEFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
                WS_RTFW_NEW_DT = BPCSMMRT.UPD_DATA[1-1].NEFF_DT;
                WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[1-1].NEFF_DT;
                WS_RTFW_OLD_DT = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
                WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
                WS_TMP_RATE = BPCSMMRT.UPD_DATA[1-1].NRATE;
                B030_07_UPDATE_RTFW_PROC();
                if (pgmRtn) return;
            } else {
                WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[1-1].NEFF_DT;
                WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
                WS_TMP_RATE = BPCSMMRT.UPD_DATA[1-1].NRATE;
                WS_TMP_CCY = BPCSMMRT.CCY;
                B000_03_WRITE_RTFW_PROC();
                if (pgmRtn) return;
                WS_HIS_FLG = 'A';
                WS_FILE_NAME = 'R';
                S000_CALL_HIS_PROC();
                if (pgmRtn) return;
                WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
                WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
                WS_TMP_CCY = BPCSMMRT.CCY;
                B030_05_05_GET_PREV_RT();
                if (pgmRtn) return;
                if (WS_FST_RT_DT == 0 
                    && WS_PREV_RATE == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.FIRST_RATE_RECORD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].TENOR);
                CEP.TRC(SCCGWA, WS_TMP_TENOR);
                WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
                WS_TMP_CCY = BPCSMMRT.CCY;
                B000_04_READUPD_INTR_PROC();
                if (pgmRtn) return;
                B000_08_MOVE_TO_BPRINTR();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, BPRINTR, BPRBINTR);
                WS_TMP_EFF_DT = WS_FST_RT_DT;
                WS_TMP_RATE = WS_PREV_RATE;
                B000_05_REWRITE_INTR_PROC();
                if (pgmRtn) return;
                B000_08_MOVE_TO_BPRINTR_FW();
                if (pgmRtn) return;
                WS_FILE_NAME = 'I';
                WS_HIS_FLG = 'M';
                WS_TMP_CCY = BPCSMMRT.CCY;
                S000_CALL_HIS_PROC();
                if (pgmRtn) return;
                if (BPCSMMRT.UPD_DATA[1-1].OEFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
                    WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
                    WS_TMP_RTID = BPCSMMRT.UPD_DATA[1-1].RATE_ID;
                    WS_RTBK_INFO.WS_RTBK_SEQ = 1;
                    WS_RTBK_INFO.WS_RTBK_FR_DT = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
                    WS_RTBK_INFO.WS_RTBK_TO_DT = SCCGWA.COMM_AREA.AC_DATE;
                    WS_RTBK_INFO.WS_RTBK_OLD_RT = BPCSMMRT.UPD_DATA[1-1].ORATE;
                    WS_RTBK_INFO.WS_RTBK_NEW_RT = WS_PREV_RATE;
                    WS_TMP_CCY = BPCSMMRT.CCY;
                    B030_05_WRITE_RTBK_PROC();
                    if (pgmRtn) return;
                    WS_TMP_EFF_DT = WS_FST_RT_DT;
                    WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
                    WS_TMP_CCY = BPCSMMRT.CCY;
                    B030_06_DELETE_IHIT_PROC();
                    if (pgmRtn) return;
                }
            }
        } else if (BPCSMMRT.UPD_DATA[1-1].NEFF_DT == SCCGWA.COMM_AREA.AC_DATE) {
            if (BPCSMMRT.UPD_DATA[1-1].OEFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
                WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
                B000_04_READONLY_INTR_PROC();
                if (pgmRtn) return;
                if (WS_INTR_REC_FOUND == 'Y') {
                    B030_UPD_INTR_HIIT_PROC();
                    if (pgmRtn) return;
                } else {
                    B030_ADD_INTR_HIIT_PROC();
                    if (pgmRtn) return;
                }
                B030_DEL_RTFW_REC_PROC();
                if (pgmRtn) return;
            } else {
                B030_UPD_INTR_HIIT_PROC();
                if (pgmRtn) return;
            }
        } else if (BPCSMMRT.UPD_DATA[1-1].NEFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            if (BPCSMMRT.UPD_DATA[1-1].OEFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
                WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
                B000_04_READONLY_INTR_PROC();
                if (pgmRtn) return;
                if (WS_INTR_REC_FOUND == 'Y') {
                    WS_OEFF_DATE = BPCRINTM.DT;
                    WS_OEFF_RATE = BPCRINTM.RATE;
                    WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
                    WS_TMP_CCY = BPCSMMRT.CCY;
                    WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[1-1].NEFF_DT;
                    WS_TMP_OEFF_DT = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
                    WS_TMP_RATE = BPCSMMRT.UPD_DATA[1-1].NRATE;
                    WS_TMP_RTID = BPCSMMRT.UPD_DATA[1-1].RATE_ID;
                    B030_BKVALUE_UPD_RATE_PROC();
                    if (pgmRtn) return;
                } else {
                    B030_ADD_INTR_HIIT_PROC();
                    if (pgmRtn) return;
                }
                B030_DEL_RTFW_REC_PROC();
                if (pgmRtn) return;
            } else {
                WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
                WS_OEFF_DATE = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
                WS_OEFF_RATE = BPCSMMRT.UPD_DATA[1-1].ORATE;
                WS_TMP_CCY = BPCSMMRT.CCY;
                WS_TMP_OEFF_DT = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
                WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[1-1].NEFF_DT;
                WS_TMP_RATE = BPCSMMRT.UPD_DATA[1-1].NRATE;
                WS_TMP_RTID = BPCSMMRT.UPD_DATA[1-1].RATE_ID;
                CEP.TRC(SCCGWA, WS_OEFF_RATE);
                CEP.TRC(SCCGWA, WS_TMP_RATE);
                B030_BKVALUE_UPD_RATE_PROC();
                if (pgmRtn) return;
            }
        } else {
        }
        B200_OUTPUT_MOD_PROC();
        if (pgmRtn) return;
    }
    public void B030_UPD_INTR_HIIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].TENOR);
        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
        WS_TMP_CCY = BPCSMMRT.CCY;
        B000_04_READUPD_INTR_PROC();
        if (pgmRtn) return;
        B000_08_MOVE_TO_BPRINTR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPRINTR, BPRBINTR);
        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[1-1].NEFF_DT;
        WS_TMP_RATE = BPCSMMRT.UPD_DATA[1-1].NRATE;
        B000_05_REWRITE_INTR_PROC();
        if (pgmRtn) return;
        B000_08_MOVE_TO_BPRINTR();
        if (pgmRtn) return;
        WS_IHIT_FLG = 'M';
        WS_TMP_CCY = BPCSMMRT.CCY;
        B030_01_UPDATE_IHIT_PROC();
        if (pgmRtn) return;
        B030_02_WRITE_IHIS_PROC();
        if (pgmRtn) return;
        WS_FILE_NAME = 'I';
        WS_HIS_FLG = 'M';
        WS_TMP_CCY = BPCSMMRT.CCY;
        S000_CALL_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B030_ADD_INTR_HIIT_PROC() throws IOException,SQLException,Exception {
        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[1-1].NEFF_DT;
        WS_TMP_RATE = BPCSMMRT.UPD_DATA[1-1].NRATE;
        WS_TMP_CCY = BPCSMMRT.CCY;
        B000_07_WRITE_INTR_PROC();
        if (pgmRtn) return;
        WS_IHIT_FLG = 'A';
        B030_01_UPDATE_IHIT_PROC();
        if (pgmRtn) return;
        B030_02_WRITE_IHIS_PROC();
        if (pgmRtn) return;
        WS_FILE_NAME = 'I';
        WS_HIS_FLG = 'A';
        WS_TMP_CCY = BPCSMMRT.CCY;
        S000_CALL_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B030_BKVALUE_UPD_RATE_PROC() throws IOException,SQLException,Exception {
        WS_BSP_FLG = 'Y';
        B030_09_CHECK_EFF_DATE();
        if (pgmRtn) return;
        B000_04_READUPD_INTR_PROC();
        if (pgmRtn) return;
        B000_08_MOVE_TO_BPRINTR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPRINTR, BPRBINTR);
        B000_05_REWRITE_INTR_PROC();
        if (pgmRtn) return;
        B000_08_MOVE_TO_BPRINTR();
        if (pgmRtn) return;
        if (WS_BSP_FLG == 'Y') {
            B030_05_WRITE_BPTRTBK();
            if (pgmRtn) return;
        }
        WS_IHIT_FLG = 'M';
        B030_01_UPDATE_IHIT_PROC();
        if (pgmRtn) return;
        B030_02_WRITE_IHIS_PROC();
        if (pgmRtn) return;
        if (WS_TMP_EFF_DT < WS_TMP_OEFF_DT) {
            B030_06_DELETE_IHIT_PROC();
            if (pgmRtn) return;
        }
        WS_HIS_FLG = 'M';
        WS_FILE_NAME = 'I';
        S000_CALL_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B030_DEL_RTFW_REC_PROC() throws IOException,SQLException,Exception {
        WS_RTFW_OLD_DT = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
        B000_02_READUPD_RTFW_PROC();
        if (pgmRtn) return;
        BPCRRTFW.INFO.FUNC = 'D';
        BPCRRTFW.INFO.POINTER = BPRRTFW;
        BPCRRTFW.INFO.LEN = 128;
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        WS_FILE_NAME = 'R';
        WS_HIS_FLG = 'D';
        WS_TMP_CCY = BPCSMMRT.CCY;
        S000_CALL_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B030_01_UPDATE_IHIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITM);
        BPCRHITM.FUNC = 'R';
        BPCRHITM.BR = BPCSMMRT.BR;
        BPCRHITM.CCY = WS_TMP_CCY;
        BPCRHITM.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRHITM.TENOR = WS_TMP_TENOR;
        BPCRHITM.DT = WS_TMP_EFF_DT;
        S000_CALL_BPZRHITM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHITM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST)) {
            IBS.init(SCCGWA, BPCRHITM);
            BPCRHITM.FUNC = 'A';
            BPCRHITM.BR = BPCSMMRT.BR;
            BPCRHITM.CCY = WS_TMP_CCY;
            BPCRHITM.BASE_TYP = BPCSMMRT.BASE_TYP;
            BPCRHITM.TENOR = WS_TMP_TENOR;
            BPCRHITM.DT = WS_TMP_EFF_DT;
            BPCRHITM.TM = SCCGWA.COMM_AREA.TR_TIME;
            BPCRHITM.RATE = WS_TMP_RATE;
            BPCRHITM.TELLER = SCCGWA.COMM_AREA.TL_ID;
            BPCRHITM.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPCRHITM.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
            S000_CALL_BPZRHITM();
            if (pgmRtn) return;
            if (BPCRHITM.RC.RC_CODE > 0) {
                CEP.TRC(SCCGWA, "AAAA");
                CEP.TRC(SCCGWA, BPCRHITM.RC);
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRHITM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (WS_IHIT_FLG == 'M') {
                BPCRHITM.FUNC = 'U';
                BPCRHITM.TM = SCCGWA.COMM_AREA.TR_TIME;
                BPCRHITM.RATE = WS_TMP_RATE;
                BPCRHITM.TELLER = SCCGWA.COMM_AREA.TL_ID;
                BPCRHITM.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
                BPCRHITM.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
                S000_CALL_BPZRHITM();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_02_WRITE_IHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHISM);
        BPCRHISM.FUNC = 'A';
        BPCRHISM.BR = BPCSMMRT.BR;
        BPCRHISM.CCY = WS_TMP_CCY;
        BPCRHISM.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRHISM.TENOR = WS_TMP_TENOR;
        BPCRHISM.DT = WS_TMP_EFF_DT;
        BPCRHISM.TM = SCCGWA.COMM_AREA.TR_TIME;
        BPCRHISM.RATE = WS_TMP_RATE;
        BPCRHISM.TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPCRHISM.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCRHISM.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
        S000_CALL_BPZRHISM();
        if (pgmRtn) return;
        if (BPCRHISM.RC.RC_CODE > 0) {
            CEP.TRC(SCCGWA, "BBB");
            CEP.TRC(SCCGWA, BPCRHISM.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRHISM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_03_GET_BK_JRNNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRTBK);
        IBS.init(SCCGWA, BPCRRTBK);
        BPRRTBK.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCRRTBK.INFO.FUNC = 'B';
        BPCRRTBK.INFO.OPT = 'S';
        BPCRRTBK.INFO.POINTER = BPRRTBK;
        BPCRRTBK.LEN = 111;
        S000_CALL_BPZRRTBK();
        if (pgmRtn) return;
        BPCRRTBK.INFO.OPT = 'N';
        S000_CALL_BPZRRTBK();
        if (pgmRtn) return;
        if (BPCRRTBK.RETURN_INFO == 'F') {
            WS_JRNNO = BPRRTBK.KEY.JRNNO;
        } else {
            WS_JRNNO = 0;
        }
        IBS.init(SCCGWA, BPCRRTBK);
        BPCRRTBK.INFO.FUNC = 'B';
        BPCRRTBK.INFO.OPT = 'E';
        S000_CALL_BPZRRTBK();
        if (pgmRtn) return;
    }
    public void B030_04_CHK_BSP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCBSPS);
        SCCBSPS.FUN = 'C';
        SCCBSPS.AP_TYPE = K_RTBK_AP_TYPE;
        S000_CALL_SCZBSPS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCBSPS.CHK_FLG);
        if (SCCBSPS.CHK_FLG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BKVALUE_NOT_END;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_05_WRITE_BPTRTBK() throws IOException,SQLException,Exception {
        if (WS_TMP_EFF_DT < WS_OEFF_DATE) {
            B030_05_03_WR_MORE_REC_RTBK();
            if (pgmRtn) return;
        } else {
            B030_05_01_WR_ONE_REC_RTBK();
            if (pgmRtn) return;
        }
    }
    public void B030_05_01_WR_ONE_REC_RTBK() throws IOException,SQLException,Exception {
        WS_ORI_DATE = SCCGWA.COMM_AREA.AC_DATE;
        B030_05_02_GET_PRE_DT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_OEFF_RATE);
        CEP.TRC(SCCGWA, WS_TMP_RATE);
        WS_RTBK_INFO.WS_RTBK_SEQ = 1;
        WS_RTBK_INFO.WS_RTBK_FR_DT = WS_TMP_EFF_DT;
        WS_RTBK_INFO.WS_RTBK_TO_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_RTBK_INFO.WS_RTBK_OLD_RT = WS_OEFF_RATE;
        WS_RTBK_INFO.WS_RTBK_NEW_RT = WS_TMP_RATE;
        B030_05_WRITE_RTBK_PROC();
        if (pgmRtn) return;
    }
    public void B030_05_02_GET_PRE_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_ORI_DATE;
        SCCCLDT.DAYS = -1;
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC == 0) {
            WS_PREV_DATE = SCCCLDT.DATE2;
        }
    }
    public void B030_05_03_WR_MORE_REC_RTBK() throws IOException,SQLException,Exception {
        B030_05_04_BRW_IHIT();
        if (pgmRtn) return;
        if (WS_PREV_RATE == 0) {
            B030_05_05_GET_PREV_RT();
            if (pgmRtn) return;
        }
        WS_RTBK_INFO.WS_RTBK_SEQ = 0;
        WS_RTBK_SEQ_FLG = '0';
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, WS_FST_RT_DT);
        CEP.TRC(SCCGWA, WS_PREV_RATE);
        if (WS_CNT > 0) {
            if (WS_TMP_EFF_DT >= WS_FST_RT_DT) {
                WS_ORI_DATE = WS_IHIT_RECORDS[1-1].WS_IHIT_DT;
                B030_05_02_GET_PRE_DT();
                if (pgmRtn) return;
                WS_RTBK_INFO.WS_RTBK_SEQ = 1;
                WS_RTBK_SEQ_FLG = '1';
                WS_RTBK_INFO.WS_RTBK_FR_DT = WS_TMP_EFF_DT;
                WS_RTBK_INFO.WS_RTBK_TO_DT = WS_PREV_DATE;
                WS_RTBK_INFO.WS_RTBK_OLD_RT = WS_PREV_RATE;
                WS_RTBK_INFO.WS_RTBK_NEW_RT = WS_TMP_RATE;
                CEP.TRC(SCCGWA, "FIRST");
                CEP.TRC(SCCGWA, WS_RTBK_INFO.WS_RTBK_FR_DT);
                CEP.TRC(SCCGWA, WS_RTBK_INFO.WS_RTBK_TO_DT);
                B030_05_WRITE_RTBK_PROC();
                if (pgmRtn) return;
            }
            for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
                if (WS_I < WS_CNT) {
                    WS_ORI_DATE = WS_IHIT_RECORDS[WS_I + 1-1].WS_IHIT_DT;
                } else {
                    WS_ORI_DATE = SCCGWA.COMM_AREA.AC_DATE;
                }
                B030_05_02_GET_PRE_DT();
                if (pgmRtn) return;
                if (WS_RTBK_SEQ_FLG == '1') {
                    WS_RTBK_INFO.WS_RTBK_SEQ = WS_I + 1;
                } else {
                    WS_RTBK_INFO.WS_RTBK_SEQ = WS_RTBK_INFO.WS_RTBK_SEQ + 1;
                }
                CEP.TRC(SCCGWA, WS_RTBK_INFO.WS_RTBK_SEQ);
                if (WS_IHIT_RECORDS[WS_I-1].WS_IHIT_DT == SCCGWA.COMM_AREA.AC_DATE) {
                    WS_PREV_DATE = SCCGWA.COMM_AREA.AC_DATE;
                }
                CEP.TRC(SCCGWA, WS_IHIT_RECORDS[WS_I-1].WS_IHIT_DT);
                WS_RTBK_INFO.WS_RTBK_FR_DT = WS_IHIT_RECORDS[WS_I-1].WS_IHIT_DT;
                if (WS_I == WS_CNT) {
                    WS_RTBK_INFO.WS_RTBK_TO_DT = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    WS_RTBK_INFO.WS_RTBK_TO_DT = WS_PREV_DATE;
                }
                CEP.TRC(SCCGWA, WS_RTBK_INFO.WS_RTBK_FR_DT);
                CEP.TRC(SCCGWA, WS_RTBK_INFO.WS_RTBK_TO_DT);
                WS_RTBK_INFO.WS_RTBK_OLD_RT = WS_IHIT_RECORDS[WS_I-1].WS_IHIT_RT;
                WS_RTBK_INFO.WS_RTBK_NEW_RT = WS_TMP_RATE;
                B030_05_WRITE_RTBK_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_05_04_BRW_IHIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITB);
        BPCRHITB.FUNC = 'S';
        BPCRHITB.COND = '8';
        BPCRHITB.STARTD = WS_TMP_EFF_DT;
        BPCRHITB.ENDD = SCCGWA.COMM_AREA.AC_DATE;
        BPCRHITB.BR = BPCSMMRT.BR;
        BPCRHITB.CCY = WS_TMP_CCY;
        BPCRHITB.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRHITB.TENOR = WS_TMP_TENOR;
        CEP.TRC(SCCGWA, BPCRHITB);
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
        WS_CNT = 0;
        if (BPCRHITB.RC.RC_CODE == 0) {
            BPCRHITB.FUNC = 'N';
            S000_CALL_BPZRHITB();
            if (pgmRtn) return;
            if (BPCRHITB.RC.RC_CODE == 0) {
                if (BPCRHITB.DT == WS_TMP_EFF_DT) {
                    WS_PREV_RATE = BPCRHITB.RATE;
                } else {
                    WS_CNT = WS_CNT + 1;
                    WS_IHIT_RECORDS[WS_CNT-1].WS_IHIT_DT = BPCRHITB.DT;
                    WS_IHIT_RECORDS[WS_CNT-1].WS_IHIT_RT = BPCRHITB.RATE;
                    CEP.TRC(SCCGWA, WS_IHIT_RECORDS[WS_CNT-1].WS_IHIT_DT);
                    CEP.TRC(SCCGWA, WS_IHIT_RECORDS[WS_CNT-1].WS_IHIT_RT);
                }
            }
        }
        while (BPCRHITB.RC.RC_CODE == 0) {
            BPCRHITB.FUNC = 'N';
            S000_CALL_BPZRHITB();
            if (pgmRtn) return;
            if (BPCRHITB.RC.RC_CODE == 0) {
                WS_CNT = WS_CNT + 1;
                WS_IHIT_RECORDS[WS_CNT-1].WS_IHIT_DT = BPCRHITB.DT;
                WS_IHIT_RECORDS[WS_CNT-1].WS_IHIT_RT = BPCRHITB.RATE;
            }
        }
        BPCRHITB.FUNC = 'E';
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
    }
    public void B030_05_05_GET_PREV_RT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITB);
        BPCRHITB.FUNC = 'S';
        BPCRHITB.COND = '9';
        BPCRHITB.STARTD = WS_TMP_EFF_DT;
        BPCRHITB.BR = BPCSMMRT.BR;
        BPCRHITB.CCY = WS_TMP_CCY;
        BPCRHITB.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRHITB.TENOR = WS_TMP_TENOR;
        CEP.TRC(SCCGWA, BPCRHITB);
        CEP.TRC(SCCGWA, WS_TMP_EFF_DT);
        CEP.TRC(SCCGWA, BPCSMMRT.BR);
        CEP.TRC(SCCGWA, WS_TMP_CCY);
        CEP.TRC(SCCGWA, BPCSMMRT.BASE_TYP);
        CEP.TRC(SCCGWA, WS_TMP_TENOR);
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRHITB.RC);
        CEP.TRC(SCCGWA, BPCRHITB.RATE);
        if (BPCRHITB.RC.RC_CODE == 0) {
            WS_FST_RT_DT = BPCRHITB.DT;
            WS_PREV_RATE = BPCRHITB.RATE;
        }
    }
    public void B030_05_WRITE_RTBK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RTBK_INFO.WS_RTBK_OLD_RT);
        CEP.TRC(SCCGWA, WS_RTBK_INFO.WS_RTBK_NEW_RT);
        IBS.init(SCCGWA, BPRRTBK);
        IBS.init(SCCGWA, BPCRRTBK);
        BPRRTBK.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRRTBK.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPRRTBK.KEY.BATCH_SEQ = WS_RTBK_INFO.WS_RTBK_SEQ;
        BPRRTBK.BR = BPCSMMRT.BR;
        BPRRTBK.CCY = WS_TMP_CCY;
        BPRRTBK.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPRRTBK.TENOR = WS_TMP_TENOR;
        BPRRTBK.RATE_ID = WS_TMP_RTID;
        BPRRTBK.FR_DT = WS_RTBK_INFO.WS_RTBK_FR_DT;
        BPRRTBK.TO_DT = WS_RTBK_INFO.WS_RTBK_TO_DT;
        BPRRTBK.OLD_RATE = WS_RTBK_INFO.WS_RTBK_OLD_RT;
        BPRRTBK.NEW_RATE = WS_RTBK_INFO.WS_RTBK_NEW_RT;
        BPRRTBK.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRRTBK.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRRTBK.INFO.FUNC = 'A';
        BPCRRTBK.INFO.POINTER = BPRRTBK;
        BPCRRTBK.LEN = 111;
        S000_CALL_BPZRRTBK();
        if (pgmRtn) return;
        if (BPCRRTBK.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_06_DELETE_IHIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITB);
        BPCRHITB.FUNC = 'S';
        BPCRHITB.COND = 'A';
        BPCRHITB.STARTD = WS_TMP_EFF_DT;
        BPCRHITB.BR = BPCSMMRT.BR;
        BPCRHITB.CCY = WS_TMP_CCY;
        BPCRHITB.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRHITB.TENOR = WS_TMP_TENOR;
        CEP.TRC(SCCGWA, BPCRHITB);
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
        while (BPCRHITB.RC.RC_CODE == 0) {
            BPCRHITB.FUNC = 'N';
            S000_CALL_BPZRHITB();
            if (pgmRtn) return;
            if (BPCRHITB.RC.RC_CODE == 0) {
                IBS.init(SCCGWA, BPCRHITM);
                BPCRHITM.BR = BPCRHITB.BR;
                BPCRHITM.CCY = BPCRHITB.CCY;
                BPCRHITM.BASE_TYP = BPCRHITB.BASE_TYP;
                BPCRHITM.TENOR = BPCRHITB.TENOR;
                BPCRHITM.DT = BPCRHITB.DT;
                CEP.TRC(SCCGWA, BPCRHITM.BR);
                CEP.TRC(SCCGWA, BPCRHITM.CCY);
                CEP.TRC(SCCGWA, BPCRHITM.BASE_TYP);
                CEP.TRC(SCCGWA, BPCRHITM.TENOR);
                CEP.TRC(SCCGWA, BPCRHITM.DT);
                BPCRHITM.FUNC = 'D';
                S000_CALL_BPZRHITM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCRHITM.RC);
            }
        }
        BPCRHITB.FUNC = 'E';
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
    }
    public void B030_07_UPDATE_RTFW_PROC() throws IOException,SQLException,Exception {
        B000_02_READUPD_RTFW_PROC();
        if (pgmRtn) return;
        if (WS_RTFW_NEW_DT == WS_RTFW_OLD_DT) {
            IBS.CLONE(SCCGWA, BPRRTFW, BPRBRTFW);
            B000_06_REWRITE_RTFW_PROC();
            if (pgmRtn) return;
            WS_HIS_FLG = 'M';
        } else {
            BPCRRTFW.INFO.FUNC = 'D';
            BPCRRTFW.INFO.POINTER = BPRRTFW;
            BPCRRTFW.INFO.LEN = 128;
            S000_CALL_BPZRRTFW();
            if (pgmRtn) return;
            WS_FILE_NAME = 'R';
            WS_HIS_FLG = 'D';
            WS_TMP_CCY = BPCSMMRT.CCY;
            S000_CALL_HIS_PROC();
            if (pgmRtn) return;
            B000_03_WRITE_RTFW_PROC();
            if (pgmRtn) return;
            WS_HIS_FLG = 'A';
        }
        WS_FILE_NAME = 'R';
        WS_TMP_CCY = BPCSMMRT.CCY;
        S000_CALL_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B030_08_START_BSP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCBSPS);
        SCCBSPS.FUN = 'A';
        SCCBSPS.SERV_CODE = K_DD_SERV_CODE;
        SCCBSPS.AP_TYPE = K_RTBK_AP_TYPE;
        SCCBSPS.AP_BATNO = SCCGWA.COMM_AREA.JRN_NO;
        IBS.init(SCCGWA, SCCBSP);
        SCCBSP.BH_SEQ.BH_BATNO = SCCGWA.COMM_AREA.JRN_NO;
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = "RDATE=" + SCCBSP.PARM_DA1.substring(6);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 7 - 1) + JIBS_tmp_str[0] + SCCBSP.PARM_DA1.substring(7 + 8 - 1);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 15 - 1) + "," + SCCBSP.PARM_DA1.substring(15 + 1 - 1);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 16 - 1) + "RJRNNO=" + SCCBSP.PARM_DA1.substring(16 + 7 - 1);
        if (SCCBSP.PARM_DA1 == null) SCCBSP.PARM_DA1 = "";
        JIBS_tmp_int = SCCBSP.PARM_DA1.length();
        for (int i=0;i<62-JIBS_tmp_int;i++) SCCBSP.PARM_DA1 += " ";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        SCCBSP.PARM_DA1 = SCCBSP.PARM_DA1.substring(0, 23 - 1) + JIBS_tmp_str[0] + SCCBSP.PARM_DA1.substring(23 + 9 - 1);
        SCCBSP.AP_PROC = K_PROC_DD;
        CEP.TRC(SCCGWA, SCCBSP.SERV_CODE);
        CEP.TRC(SCCGWA, SCCBSP.BH_SEQ.BH_TYPE);
        CEP.TRC(SCCGWA, SCCBSP.AP_PROC);
        S000_CALL_SCZOBSP();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCBSPS);
        SCCBSPS.FUN = 'A';
        SCCBSPS.SERV_CODE = K_TD_SERV_CODE;
        SCCBSPS.AP_TYPE = K_RTBK_AP_TYPE;
        SCCBSPS.AP_BATNO = SCCGWA.COMM_AREA.JRN_NO;
        S000_CALL_SCZBSPS();
        if (pgmRtn) return;
        SCCBSP.SERV_CODE = K_TD_SERV_CODE;
        SCCBSP.BH_SEQ.BH_TYPE = K_RTBK_AP_TYPE;
        SCCBSP.AP_PROC = " ";
        CEP.TRC(SCCGWA, SCCBSP.SERV_CODE);
        CEP.TRC(SCCGWA, SCCBSP.BH_SEQ.BH_TYPE);
        CEP.TRC(SCCGWA, SCCBSP.AP_PROC);
        S000_CALL_SCZOBSP();
        if (pgmRtn) return;
    }
    public void B030_09_CHECK_EFF_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITB);
        BPCRHITB.FUNC = 'S';
        BPCRHITB.COND = '9';
        BPCRHITB.BR = BPCSMMRT.BR;
        BPCRHITB.CCY = WS_TMP_CCY;
        BPCRHITB.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRHITB.TENOR = WS_TMP_TENOR;
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRHITB.RC);
        CEP.TRC(SCCGWA, BPCRHITB.DT);
        if (BPCRHITB.RC.RC_CODE == 0) {
            if (BPCRHITB.DT > 0) {
                WS_FST_RT_DT = BPCRHITB.DT;
                if (WS_TMP_EFF_DT < BPCRHITB.DT) {
                    if (BPCRHITB.DT == SCCGWA.COMM_AREA.AC_DATE) {
                        WS_BSP_FLG = 'N';
                    }
                }
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_DELETE_INTR_PROCESS() throws IOException,SQLException,Exception {
        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
        WS_RTFW_OLD_DT = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
        B000_02_READUPD_RTFW_PROC();
        if (pgmRtn) return;
        BPCRRTFW.INFO.FUNC = 'D';
        BPCRRTFW.INFO.POINTER = BPRRTFW;
        BPCRRTFW.INFO.LEN = 128;
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        WS_FILE_NAME = 'R';
        WS_HIS_FLG = 'D';
        WS_TMP_TENOR = BPCSMMRT.CCY;
        S000_CALL_HIS_PROC();
        if (pgmRtn) return;
        B200_OUTPUT_BASIC_PROC();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_INTR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, BPCO306);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 196;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        WS_BASE_RT_HD.WS_RATE_TYPE = BPCSMMRT.BASE_TYP;
        WS_BASE_RT_HD.WS_RATE_CCY = BPCSMMRT.CCY;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BASE_RT_HD);
        SCCMPAG.DATA_LEN = 350;
        CEP.TRC(SCCGWA, WS_BASE_RT_HD);
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRINTB);
        BPCRINTB.BR = BPCSMMRT.BR;
        BPCRINTB.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRINTB.TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
        BPCRINTB.CCY = BPCSMMRT.CCY;
        CEP.TRC(SCCGWA, BPCSMMRT.BR);
        CEP.TRC(SCCGWA, BPCSMMRT.BASE_TYP);
        CEP.TRC(SCCGWA, BPCSMMRT.CCY);
        CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].TENOR);
        WS_FILE_NAME = 'I';
        BPCRINTB.FUNC = 'S';
        if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
            || BPCSMMRT.CCY.trim().length() == 0) {
            if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-CCY-BASETYP");
                BPCRINTB.COND = '5';
            }
            if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() > 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-BASETYP-TENOR");
                BPCRINTB.COND = '7';
            }
            if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-BASETYP");
                BPCRINTB.COND = '3';
            }
        } else {
            BPCRINTB.COND = '9';
        }
        CEP.TRC(SCCGWA, BPCSMMRT.CCY);
        CEP.TRC(SCCGWA, BPCRINTB.COND);
        CEP.TRC(SCCGWA, BPCRINTB.BR);
        CEP.TRC(SCCGWA, BPCRINTB.CCY);
        CEP.TRC(SCCGWA, BPCRINTB.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRINTB.TENOR);
        S000_CALL_BPZRINTB();
        if (pgmRtn) return;
        BPCRINTB.FUNC = 'N';
        S000_CALL_BPZRINTB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READNEXT");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRINTB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST) 
            && SCCMPAG.FUNC != 'E') {
            B300_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
            BPCRINTB.FUNC = 'N';
            S000_CALL_BPZRINTB();
            if (pgmRtn) return;
        }
        BPCRINTB.FUNC = 'E';
        S000_CALL_BPZRINTB();
        if (pgmRtn) return;
        WS_FILE_NAME = 'R';
        IBS.init(SCCGWA, BPRRTFW);
        IBS.init(SCCGWA, BPCRRTFW);
        BPRRTFW.KEY.BR = BPCSMMRT.BR;
        BPRRTFW.KEY.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPRRTFW.KEY.TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
        BPRRTFW.KEY.CCY = BPCSMMRT.CCY;
        CEP.TRC(SCCGWA, BPCSMMRT.BASE_TYP);
        CEP.TRC(SCCGWA, BPCSMMRT.CCY);
        CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].TENOR);
        BPCRRTFW.INFO.FUNC = 'B';
        if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
            || BPCSMMRT.CCY.trim().length() == 0) {
            if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "RRTFW-START-4");
                BPCRRTFW.INFO.OPT = '4';
            }
            if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() > 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RRTFW-START-2 ");
                BPCRRTFW.INFO.OPT = '2';
            }
            if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RRTFW-START-5");
                BPCRRTFW.INFO.OPT = '5';
            }
        } else {
            BPCRRTFW.INFO.OPT = '3';
        }
        BPCRRTFW.INFO.POINTER = BPRRTFW;
        BPCRRTFW.INFO.LEN = 128;
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRRTFW.RC);
        CEP.TRC(SCCGWA, BPCRRTFW.RETURN_INFO);
        BPCRRTFW.INFO.OPT = 'N';
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRRTFW.RC);
        CEP.TRC(SCCGWA, BPCRRTFW.RETURN_INFO);
        while (BPCRRTFW.RETURN_INFO != 'N') {
            B300_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
            BPCRRTFW.INFO.OPT = 'N';
            S000_CALL_BPZRRTFW();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCRRTFW);
        BPCRRTFW.INFO.OPT = 'E';
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
    }
    public void B060_BRW_BTH_INTR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTB);
        BPCRINTB.BR = BPCSMMRT.BR;
        BPCRINTB.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRINTB.CCY = BPCSMMRT.CCY;
        BPCRINTB.TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
        WS_FILE_NAME = 'I';
        BPCRINTB.FUNC = 'S';
        if (BPCSMMRT.BRW_BTH_OPT == 'M') {
            if (BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0) {
                BPCRINTB.COND = '5';
            } else {
                BPCRINTB.COND = '9';
            }
        } else {
            BPCRINTB.TENOR = K_SPEC_TENOR;
            if (BPCSMMRT.CCY.trim().length() == 0) {
                BPCRINTB.COND = '7';
            } else {
                BPCRINTB.COND = '9';
            }
        }
        S000_CALL_BPZRINTB();
        if (pgmRtn) return;
        BPCRINTB.FUNC = 'N';
        S000_CALL_BPZRINTB();
        if (pgmRtn) return;
        WS_I = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRINTB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST) 
            && WS_I <= K_BATCH_CNT) {
            if (BPCSMMRT.BRW_BTH_OPT == 'M') {
                if (!BPCRINTB.TENOR.equalsIgnoreCase(K_SPEC_TENOR)) {
                    WS_I = WS_I + 1;
                    BPCSMMRT.UPD_DATA[WS_I-1].TENOR = BPCRINTB.TENOR;
                    BPCSMMRT.UPD_DATA[WS_I-1].OEFF_DT = BPCRINTB.DT;
                    BPCSMMRT.UPD_DATA[WS_I-1].ORATE = BPCRINTB.RATE;
                    WS_RTID_TENOR = BPCRINTB.TENOR;
                    WS_RTID_CCY = BPCRINTB.CCY;
                    B300_01_GET_RATEID();
                    if (pgmRtn) return;
                    BPCSMMRT.UPD_DATA[WS_I-1].RATE_ID = BPCOQRTD.DATA.RATE_ID;
                }
            } else {
                WS_I = WS_I + 1;
                BPCSMMRT.UPD_DATA[WS_I-1].CCY_TBL = BPCRINTB.CCY;
                BPCSMMRT.UPD_DATA[WS_I-1].OEFF_DT = BPCRINTB.DT;
                BPCSMMRT.UPD_DATA[WS_I-1].ORATE = BPCRINTB.RATE;
                WS_RTID_TENOR = BPCRINTB.TENOR;
                WS_RTID_CCY = BPCRINTB.CCY;
                B300_01_GET_RATEID();
                if (pgmRtn) return;
                BPCSMMRT.UPD_DATA[WS_I-1].RATE_ID = BPCOQRTD.DATA.RATE_ID;
            }
            BPCRINTB.FUNC = 'N';
            S000_CALL_BPZRINTB();
            if (pgmRtn) return;
        }
        BPCRINTB.FUNC = 'E';
        S000_CALL_BPZRINTB();
        if (pgmRtn) return;
        if (BPCSMMRT.BRW_BTH_OPT == 'M') {
            B200_OUTPUT_BTH_MARKET_PROC();
            if (pgmRtn) return;
        } else {
            B200_OUTPUT_BTH_BASE_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_BTH_INTR_MARKET_PROC() throws IOException,SQLException,Exception {
        for (WS_K = 1; WS_K <= K_BATCH_CNT; WS_K += 1) {
            if (BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT > 0) {
                B070_01_CHECK_INPUT_DATA();
                if (pgmRtn) return;
                if (WS_J == 4 
                    && (BPCSMMRT.UPD_DATA[WS_K-1].ORATE != BPCSMMRT.UPD_DATA[WS_K-1].NRATE 
                    || BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT != BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT)) {
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].TENOR);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].ORATE);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].NRATE);
                    if (BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
                        WS_OEFF_DATE = BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT;
                        WS_OEFF_RATE = BPCSMMRT.UPD_DATA[WS_K-1].ORATE;
                        WS_TMP_CCY = BPCSMMRT.CCY;
                        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT;
                        WS_TMP_OEFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT;
                        WS_TMP_RATE = BPCSMMRT.UPD_DATA[WS_K-1].NRATE;
                        WS_TMP_RTID = BPCSMMRT.UPD_DATA[WS_K-1].RATE_ID;
                        WS_TMP_OEFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT;
                        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT;
                        B100_02_CHK_DAYS_BTW_ONDT();
                        if (pgmRtn) return;
                        B030_BKVALUE_UPD_RATE_PROC();
                        if (pgmRtn) return;
                    } else if (BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT == SCCGWA.COMM_AREA.AC_DATE) {
                        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                        WS_TMP_CCY = BPCSMMRT.CCY;
                        B000_04_READUPD_INTR_PROC();
                        if (pgmRtn) return;
                        B000_08_MOVE_TO_BPRINTR();
                        if (pgmRtn) return;
                        IBS.CLONE(SCCGWA, BPRINTR, BPRBINTR);
                        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT;
                        WS_TMP_RATE = BPCSMMRT.UPD_DATA[WS_K-1].NRATE;
                        B000_05_REWRITE_INTR_PROC();
                        if (pgmRtn) return;
                        B000_08_MOVE_TO_BPRINTR();
                        if (pgmRtn) return;
                        WS_IHIT_FLG = 'M';
                        WS_TMP_CCY = BPCSMMRT.CCY;
                        B030_01_UPDATE_IHIT_PROC();
                        if (pgmRtn) return;
                        B030_02_WRITE_IHIS_PROC();
                        if (pgmRtn) return;
                        WS_FILE_NAME = 'I';
                        WS_HIS_FLG = 'M';
                        S000_CALL_HIS_PROC();
                        if (pgmRtn) return;
                    } else if (BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
                        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT;
                        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                        WS_TMP_RATE = BPCSMMRT.UPD_DATA[WS_K-1].NRATE;
                        WS_TMP_CCY = BPCSMMRT.CCY;
                        B000_03_WRITE_RTFW_PROC();
                        if (pgmRtn) return;
                        WS_HIS_FLG = 'A';
                        WS_FILE_NAME = 'R';
                        S000_CALL_HIS_PROC();
                        if (pgmRtn) return;
                        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT;
                        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                        WS_TMP_CCY = BPCSMMRT.CCY;
                        B030_05_05_GET_PREV_RT();
                        if (pgmRtn) return;
                        if (WS_FST_RT_DT == 0 
                            && WS_PREV_RATE == 0) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.FIRST_RATE_RECORD;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                        WS_TMP_CCY = BPCSMMRT.CCY;
                        B000_04_READUPD_INTR_PROC();
                        if (pgmRtn) return;
                        B000_08_MOVE_TO_BPRINTR();
                        if (pgmRtn) return;
                        IBS.CLONE(SCCGWA, BPRINTR, BPRBINTR);
                        WS_TMP_EFF_DT = WS_FST_RT_DT;
                        WS_TMP_RATE = WS_PREV_RATE;
                        B000_05_REWRITE_INTR_PROC();
                        if (pgmRtn) return;
                        B000_08_MOVE_TO_BPRINTR_FW();
                        if (pgmRtn) return;
                        WS_FILE_NAME = 'I';
                        WS_HIS_FLG = 'M';
                        S000_CALL_HIS_PROC();
                        if (pgmRtn) return;
                        if (BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
                            WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                            WS_TMP_RTID = BPCSMMRT.UPD_DATA[WS_K-1].RATE_ID;
                            WS_RTBK_INFO.WS_RTBK_SEQ = 1;
                            WS_RTBK_INFO.WS_RTBK_FR_DT = BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT;
                            WS_RTBK_INFO.WS_RTBK_TO_DT = SCCGWA.COMM_AREA.AC_DATE;
                            WS_RTBK_INFO.WS_RTBK_OLD_RT = BPCSMMRT.UPD_DATA[WS_K-1].ORATE;
                            WS_RTBK_INFO.WS_RTBK_NEW_RT = WS_PREV_RATE;
                            WS_TMP_CCY = BPCSMMRT.CCY;
                            B030_05_WRITE_RTBK_PROC();
                            if (pgmRtn) return;
                            WS_TMP_EFF_DT = WS_FST_RT_DT;
                            WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                            WS_TMP_CCY = BPCSMMRT.CCY;
                            B030_06_DELETE_IHIT_PROC();
                            if (pgmRtn) return;
                        }
                    } else {
                    }
                }
            } else {
                B070_01_CHECK_INPUT_DATA();
                if (pgmRtn) return;
                if (WS_J == 4) {
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].TENOR);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].ORATE);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].NRATE);
                    WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                    WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT;
                    WS_TMP_RATE = BPCSMMRT.UPD_DATA[WS_K-1].NRATE;
                    WS_TMP_RTID = BPCSMMRT.UPD_DATA[WS_K-1].RATE_ID;
                    WS_RTID_CCY = BPCSMMRT.CCY;
                    WS_TMP_CCY = BPCSMMRT.CCY;
                    B100_01_CHECK_RATEID_PROC();
                    if (pgmRtn) return;
                    B000_01_WRITE_BASE_RT_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        B200_OUTPUT_BTH_MARKET_PROC();
        if (pgmRtn) return;
    }
    public void B070_BTH_INTR_BASE_PROC() throws IOException,SQLException,Exception {
        for (WS_K = 1; WS_K <= K_BATCH_CNT; WS_K += 1) {
            if (BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT > 0) {
                B070_01_CHECK_INPUT_DATA();
                if (pgmRtn) return;
                if (WS_J == 4 
                    && (BPCSMMRT.UPD_DATA[WS_K-1].ORATE != BPCSMMRT.UPD_DATA[WS_K-1].NRATE 
                    || BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT != BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT)) {
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].ORATE);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].NRATE);
                    if (BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
                        WS_OEFF_DATE = BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT;
                        WS_OEFF_RATE = BPCSMMRT.UPD_DATA[WS_K-1].ORATE;
                        WS_TMP_CCY = BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL;
                        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT;
                        WS_TMP_OEFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT;
                        WS_TMP_RATE = BPCSMMRT.UPD_DATA[WS_K-1].NRATE;
                        WS_TMP_RTID = BPCSMMRT.UPD_DATA[WS_K-1].RATE_ID;
                        WS_TMP_OEFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT;
                        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT;
                        B100_02_CHK_DAYS_BTW_ONDT();
                        if (pgmRtn) return;
                        B030_BKVALUE_UPD_RATE_PROC();
                        if (pgmRtn) return;
                    } else if (BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT == SCCGWA.COMM_AREA.AC_DATE) {
                        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                        WS_TMP_CCY = BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL;
                        B000_04_READUPD_INTR_PROC();
                        if (pgmRtn) return;
                        B000_08_MOVE_TO_BPRINTR();
                        if (pgmRtn) return;
                        IBS.CLONE(SCCGWA, BPRINTR, BPRBINTR);
                        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT;
                        WS_TMP_RATE = BPCSMMRT.UPD_DATA[WS_K-1].NRATE;
                        B000_05_REWRITE_INTR_PROC();
                        if (pgmRtn) return;
                        B000_08_MOVE_TO_BPRINTR();
                        if (pgmRtn) return;
                        WS_IHIT_FLG = 'M';
                        WS_TMP_CCY = BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL;
                        B030_01_UPDATE_IHIT_PROC();
                        if (pgmRtn) return;
                        B030_02_WRITE_IHIS_PROC();
                        if (pgmRtn) return;
                        WS_FILE_NAME = 'I';
                        WS_HIS_FLG = 'M';
                        S000_CALL_HIS_PROC();
                        if (pgmRtn) return;
                    } else if (BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
                        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT;
                        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                        WS_TMP_RATE = BPCSMMRT.UPD_DATA[WS_K-1].NRATE;
                        WS_TMP_CCY = BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL;
                        B000_03_WRITE_RTFW_PROC();
                        if (pgmRtn) return;
                        WS_HIS_FLG = 'A';
                        WS_FILE_NAME = 'R';
                        S000_CALL_HIS_PROC();
                        if (pgmRtn) return;
                        WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT;
                        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                        WS_TMP_CCY = BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL;
                        B030_05_05_GET_PREV_RT();
                        if (pgmRtn) return;
                        if (WS_FST_RT_DT == 0 
                            && WS_PREV_RATE == 0) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.FIRST_RATE_RECORD;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                        WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                        WS_TMP_CCY = BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL;
                        B000_04_READUPD_INTR_PROC();
                        if (pgmRtn) return;
                        B000_08_MOVE_TO_BPRINTR();
                        if (pgmRtn) return;
                        IBS.CLONE(SCCGWA, BPRINTR, BPRBINTR);
                        WS_TMP_EFF_DT = WS_FST_RT_DT;
                        WS_TMP_RATE = WS_PREV_RATE;
                        B000_05_REWRITE_INTR_PROC();
                        if (pgmRtn) return;
                        B000_08_MOVE_TO_BPRINTR_FW();
                        if (pgmRtn) return;
                        WS_FILE_NAME = 'I';
                        WS_HIS_FLG = 'M';
                        S000_CALL_HIS_PROC();
                        if (pgmRtn) return;
                        if (BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
                            WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                            WS_TMP_RTID = BPCSMMRT.UPD_DATA[WS_K-1].RATE_ID;
                            WS_RTBK_INFO.WS_RTBK_SEQ = 1;
                            WS_RTBK_INFO.WS_RTBK_FR_DT = BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT;
                            WS_RTBK_INFO.WS_RTBK_TO_DT = SCCGWA.COMM_AREA.AC_DATE;
                            WS_RTBK_INFO.WS_RTBK_OLD_RT = BPCSMMRT.UPD_DATA[WS_K-1].ORATE;
                            WS_RTBK_INFO.WS_RTBK_NEW_RT = WS_PREV_RATE;
                            WS_TMP_CCY = BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL;
                            B030_05_WRITE_RTBK_PROC();
                            if (pgmRtn) return;
                            WS_TMP_EFF_DT = WS_FST_RT_DT;
                            WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                            WS_TMP_CCY = BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL;
                            B030_06_DELETE_IHIT_PROC();
                            if (pgmRtn) return;
                        }
                    } else {
                    }
                }
            } else {
                B070_01_CHECK_INPUT_DATA();
                if (pgmRtn) return;
                if (WS_J == 4) {
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].TENOR);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].OEFF_DT);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].ORATE);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT);
                    CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[WS_K-1].NRATE);
                    WS_TMP_TENOR = BPCSMMRT.UPD_DATA[WS_K-1].TENOR;
                    WS_TMP_EFF_DT = BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT;
                    WS_TMP_RATE = BPCSMMRT.UPD_DATA[WS_K-1].NRATE;
                    WS_TMP_RTID = BPCSMMRT.UPD_DATA[WS_K-1].RATE_ID;
                    WS_TMP_CCY = BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL;
                    WS_RTID_CCY = BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL;
                    B100_01_CHECK_RATEID_PROC();
                    if (pgmRtn) return;
                    B000_01_WRITE_BASE_RT_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        B200_OUTPUT_BTH_BASE_PROC();
        if (pgmRtn) return;
    }
    public void B070_01_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_K);
        WS_J = 0;
        if (BPCSMMRT.BTH_OPT == 'M') {
            if (BPCSMMRT.UPD_DATA[WS_K-1].TENOR.trim().length() > 0) {
                WS_J = WS_J + 1;
            }
        } else {
            if (BPCSMMRT.UPD_DATA[WS_K-1].CCY_TBL.trim().length() > 0) {
                WS_J = WS_J + 1;
            }
        }
        if (BPCSMMRT.UPD_DATA[WS_K-1].RATE_ID.trim().length() > 0) {
            WS_J = WS_J + 1;
        }
        if (BPCSMMRT.UPD_DATA[WS_K-1].NEFF_DT != 0) {
            WS_J = WS_J + 1;
        }
        if (BPCSMMRT.UPD_DATA[WS_K-1].NRATE != 0) {
            WS_J = WS_J + 1;
        }
        CEP.TRC(SCCGWA, WS_J);
    }
    public void B000_01_WRITE_BASE_RT_PROC() throws IOException,SQLException,Exception {
        if (WS_TMP_EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            B000_03_WRITE_RTFW_PROC();
            if (pgmRtn) return;
            WS_FILE_NAME = 'R';
        } else {
            B000_07_WRITE_INTR_PROC();
            if (pgmRtn) return;
            WS_IHIT_FLG = 'A';
            B030_01_UPDATE_IHIT_PROC();
            if (pgmRtn) return;
            B030_02_WRITE_IHIS_PROC();
            if (pgmRtn) return;
            WS_FILE_NAME = 'I';
        }
        WS_HIS_FLG = 'A';
        S000_CALL_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B000_02_READUPD_RTFW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRRTFW);
        IBS.init(SCCGWA, BPRRTFW);
        BPRRTFW.KEY.BR = BPCSMMRT.BR;
        BPRRTFW.KEY.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPRRTFW.KEY.TENOR = WS_TMP_TENOR;
        BPRRTFW.KEY.CCY = BPCSMMRT.CCY;
        BPRRTFW.KEY.VAL_DT = WS_RTFW_OLD_DT;
        BPCRRTFW.INFO.FUNC = 'R';
        BPCRRTFW.INFO.POINTER = BPRRTFW;
        BPCRRTFW.INFO.LEN = 128;
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        if (BPCRRTFW.RC.RC_CODE > 0) {
            CEP.TRC(SCCGWA, "CCC");
            CEP.TRC(SCCGWA, BPCRRTFW.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRRTFW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_03_WRITE_RTFW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRRTFW);
        IBS.init(SCCGWA, BPRRTFW);
        BPRRTFW.KEY.BR = BPCSMMRT.BR;
        BPRRTFW.KEY.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPRRTFW.KEY.TENOR = WS_TMP_TENOR;
        BPRRTFW.KEY.CCY = WS_TMP_CCY;
        BPRRTFW.KEY.VAL_DT = WS_TMP_EFF_DT;
        BPRRTFW.RATE = WS_TMP_RATE;
        BPRRTFW.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRRTFW.TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPRRTFW.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRRTFW.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRRTFW.INFO.FUNC = 'C';
        BPCRRTFW.INFO.POINTER = BPRRTFW;
        BPCRRTFW.INFO.LEN = 128;
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        if (BPCRRTFW.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_04_READONLY_INTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.BR = BPCSMMRT.BR;
        BPCRINTM.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRINTM.TENOR = WS_TMP_TENOR;
        BPCRINTM.CCY = BPCSMMRT.CCY;
        BPCRINTM.FUNC = 'I';
        S000_CALL_BPZRINTM();
        if (pgmRtn) return;
        if (BPCRINTM.RETURN_INFO == 'F') {
            WS_INTR_REC_FOUND = 'Y';
        } else {
            WS_INTR_REC_FOUND = 'N';
        }
    }
    public void B000_04_READUPD_INTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.BR = BPCSMMRT.BR;
        BPCRINTM.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRINTM.TENOR = WS_TMP_TENOR;
        BPCRINTM.CCY = WS_TMP_CCY;
        CEP.TRC(SCCGWA, BPCRINTM.BR);
        CEP.TRC(SCCGWA, BPCRINTM.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRINTM.TENOR);
        CEP.TRC(SCCGWA, BPCRINTM.CCY);
        BPCRINTM.FUNC = 'R';
        S000_CALL_BPZRINTM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRINTM.RC);
        CEP.TRC(SCCGWA, BPCRINTM.RETURN_INFO);
        if (BPCRINTM.RETURN_INFO != 'F') {
            CEP.TRC(SCCGWA, "DDD");
            CEP.TRC(SCCGWA, BPCRINTM.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRINTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_05_REWRITE_INTR_PROC() throws IOException,SQLException,Exception {
        BPCRINTM.FUNC = 'U';
        BPCRINTM.DT = WS_TMP_EFF_DT;
        BPCRINTM.TM = SCCGWA.COMM_AREA.TR_TIME;
        BPCRINTM.RATE = WS_TMP_RATE;
        BPCRINTM.TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPCRINTM.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCRINTM.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
        S000_CALL_BPZRINTM();
        if (pgmRtn) return;
        if (BPCRINTM.RETURN_INFO != 'F') {
            CEP.TRC(SCCGWA, "EEE");
            CEP.TRC(SCCGWA, BPCRINTM.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRINTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_06_REWRITE_RTFW_PROC() throws IOException,SQLException,Exception {
        BPCRRTFW.INFO.FUNC = 'U';
        BPRRTFW.RATE = WS_TMP_RATE;
        BPRRTFW.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRRTFW.TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPRRTFW.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRRTFW.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRRTFW.INFO.POINTER = BPRRTFW;
        BPCRRTFW.INFO.LEN = 128;
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
    }
    public void B000_07_WRITE_INTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.BR = BPCSMMRT.BR;
        BPCRINTM.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRINTM.TENOR = WS_TMP_TENOR;
        BPCRINTM.CCY = WS_TMP_CCY;
        BPCRINTM.DT = WS_TMP_EFF_DT;
        BPCRINTM.TM = SCCGWA.COMM_AREA.TR_TIME;
        BPCRINTM.RATE = WS_TMP_RATE;
        BPCRINTM.TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPCRINTM.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCRINTM.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRINTM.FUNC = 'A';
        S000_CALL_BPZRINTM();
        if (pgmRtn) return;
        if (BPCRINTM.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_08_MOVE_TO_BPRINTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTM.BR;
        BPRINTR.KEY.BASE_TYP = BPCRINTM.BASE_TYP;
        BPRINTR.KEY.TENOR = BPCRINTM.TENOR;
        BPRINTR.KEY.CCY = BPCRINTM.CCY;
        BPRINTR.KEY.DT = BPCRINTM.DT;
        BPRINTR.TM = BPCRINTM.TM;
        BPRINTR.RATE = BPCRINTM.RATE;
        BPRINTR.TELLER = BPCRINTM.TELLER;
        BPRINTR.OVR1 = BPCRINTM.OVR1;
        BPRINTR.OVR2 = BPCRINTM.OVR2;
    }
    public void B000_08_MOVE_TO_BPRINTR_FW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTM.BR;
        BPRINTR.KEY.BASE_TYP = BPCRINTM.BASE_TYP;
        BPRINTR.KEY.TENOR = BPCRINTM.TENOR;
        BPRINTR.KEY.CCY = BPCRINTM.CCY;
        BPRINTR.KEY.DT = WS_TMP_EFF_DT;
        BPRINTR.TM = BPCRINTM.TM;
        BPRINTR.RATE = WS_TMP_RATE;
        BPRINTR.TELLER = BPCRINTM.TELLER;
        BPRINTR.OVR1 = BPCRINTM.OVR1;
        BPRINTR.OVR2 = BPCRINTM.OVR2;
    }
    public void B200_OUTPUT_BASIC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO306);
        BPCO306.ACCT_CENTER = BPCSMMRT.BR;
        if (WS_FILE_NAME == 'I') {
            BPCO306.RATE_TYPE = BPCRINTM.BASE_TYP;
            BPCO306.CCY = BPCRINTM.CCY;
            BPCO306.TENOR = BPCRINTM.TENOR;
            BPCO306.EFF_DT = BPCRINTM.DT;
            BPCO306.RATE = BPCRINTM.RATE;
            BPCO306.LAST_TLR = BPCRINTM.TELLER;
        } else {
            BPCO306.RATE_TYPE = BPRRTFW.KEY.BASE_TYP;
            BPCO306.CCY = BPRRTFW.KEY.CCY;
            BPCO306.TENOR = BPRRTFW.KEY.TENOR;
            BPCO306.EFF_DT = BPRRTFW.KEY.VAL_DT;
            BPCO306.RATE = BPRRTFW.RATE;
            BPCO306.LAST_TLR = BPRRTFW.TELLER;
        }
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_BASE_TYPE;
        BPCOQPCD.INPUT_DATA.CODE = BPCO306.RATE_TYPE;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCO306.BASE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
        BPCOQPCD.INPUT_DATA.CODE = BPCO306.TENOR;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCO306.TENOR_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        if (BPCSMMRT.FUNC != 'A') {
            WS_RTID_TENOR = BPCO306.TENOR;
            WS_RTID_CCY = BPCO306.CCY;
            B300_01_GET_RATEID();
            if (pgmRtn) return;
            BPCO306.RATE_ID = BPCOQRTD.DATA.RATE_ID;
        } else {
            BPCO306.RATE_ID = WS_TMP_RTID;
        }
        BPCO306.NEFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCO306);
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSMMRT.FUNC == 'I') {
            SCCFMT.FMTID = K_FMT_CD_X01;
        } else {
            SCCFMT.FMTID = K_FMT_CD_306;
        }
        SCCFMT.DATA_PTR = BPCO306;
        SCCFMT.DATA_LEN = 196;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B200_OUTPUT_MOD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO307);
        BPCO307.ACCT_CENTER = BPCSMMRT.BR;
        BPCO307.RATE_TYPE = BPCSMMRT.BASE_TYP;
        BPCO307.CCY = BPCSMMRT.CCY;
        BPCO307.TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
        BPCO307.RATE_ID = BPCSMMRT.UPD_DATA[1-1].RATE_ID;
        BPCO307.OEFF_DT = BPCSMMRT.UPD_DATA[1-1].OEFF_DT;
        BPCO307.ORATE = BPCSMMRT.UPD_DATA[1-1].ORATE;
        BPCO307.NEFF_DT = BPCSMMRT.UPD_DATA[1-1].NEFF_DT;
        BPCO307.NRATE = BPCSMMRT.UPD_DATA[1-1].NRATE;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_BASE_TYPE;
        BPCOQPCD.INPUT_DATA.CODE = BPCO307.RATE_TYPE;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCO307.BASE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
        BPCOQPCD.INPUT_DATA.CODE = BPCO307.TENOR;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCO307.TENOR_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD_307;
        SCCFMT.DATA_PTR = BPCO307;
        SCCFMT.DATA_LEN = 188;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B200_OUTPUT_BTH_MARKET_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO308);
        BPCO308.ACCT_CENTER = BPCSMMRT.BR;
        BPCO308.RATE_TYPE = BPCSMMRT.BASE_TYP;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_BASE_TYPE;
        BPCOQPCD.INPUT_DATA.CODE = BPCO308.RATE_TYPE;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCO308.BASE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        BPCO308.CCY = BPCSMMRT.CCY;
        BPCO308.CNT = 0;
        for (WS_I = 1; WS_I <= K_BATCH_CNT; WS_I += 1) {
            BPCO308.UPD_DATA[WS_I-1].TENOR = BPCSMMRT.UPD_DATA[WS_I-1].TENOR;
            BPCO308.UPD_DATA[WS_I-1].RATE_ID = BPCSMMRT.UPD_DATA[WS_I-1].RATE_ID;
            BPCO308.UPD_DATA[WS_I-1].OEFF_DT = BPCSMMRT.UPD_DATA[WS_I-1].OEFF_DT;
            BPCO308.UPD_DATA[WS_I-1].ORATE = BPCSMMRT.UPD_DATA[WS_I-1].ORATE;
            BPCO308.UPD_DATA[WS_I-1].NEFF_DT = BPCSMMRT.UPD_DATA[WS_I-1].NEFF_DT;
            BPCO308.UPD_DATA[WS_I-1].NRATE = BPCSMMRT.UPD_DATA[WS_I-1].NRATE;
            if (BPCO308.UPD_DATA[WS_I-1].TENOR.trim().length() > 0) {
                BPCO308.CNT = BPCO308.CNT + 1;
                if (BPCSMMRT.FUNC == 'N') {
                    BPCO308.UPD_DATA[WS_I-1].NEFF_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPCO308.UPD_DATA[WS_I-1].NRATE = BPCSMMRT.UPD_DATA[WS_I-1].ORATE;
                }
            }
        }
        CEP.TRC(SCCGWA, BPCO308.CNT);
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSMMRT.FUNC == 'N') {
            SCCFMT.FMTID = K_FMT_CD_X01;
        } else {
            SCCFMT.FMTID = K_FMT_CD_308;
        }
        SCCFMT.DATA_PTR = BPCO308;
        SCCFMT.DATA_LEN = 2238;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B200_OUTPUT_BTH_BASE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO303);
        BPCO303.ACCT_CENTER = BPCSMMRT.BR;
        BPCO303.RATE_TYPE = BPCSMMRT.BASE_TYP;
        BPCO303.CNT = 0;
        for (WS_I = 1; WS_I <= K_BATCH_CNT; WS_I += 1) {
            BPCO303.UPD_DATA[WS_I-1].CCY = BPCSMMRT.UPD_DATA[WS_I-1].CCY_TBL;
            BPCO303.UPD_DATA[WS_I-1].RATE_ID = BPCSMMRT.UPD_DATA[WS_I-1].RATE_ID;
            BPCO303.UPD_DATA[WS_I-1].OEFF_DT = BPCSMMRT.UPD_DATA[WS_I-1].OEFF_DT;
            BPCO303.UPD_DATA[WS_I-1].ORATE = BPCSMMRT.UPD_DATA[WS_I-1].ORATE;
            BPCO303.UPD_DATA[WS_I-1].NEFF_DT = BPCSMMRT.UPD_DATA[WS_I-1].NEFF_DT;
            BPCO303.UPD_DATA[WS_I-1].NRATE = BPCSMMRT.UPD_DATA[WS_I-1].NRATE;
            if (BPCO303.UPD_DATA[WS_I-1].CCY.trim().length() > 0) {
                BPCO303.CNT = BPCO303.CNT + 1;
                if (BPCSMMRT.FUNC == 'N') {
                    BPCO303.UPD_DATA[WS_I-1].NEFF_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPCO303.UPD_DATA[WS_I-1].NRATE = BPCSMMRT.UPD_DATA[WS_I-1].ORATE;
                }
            }
        }
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSMMRT.FUNC == 'N') {
            SCCFMT.FMTID = K_FMT_CD_X01;
        } else {
            SCCFMT.FMTID = K_FMT_CD_303;
        }
        SCCFMT.DATA_PTR = BPCO303;
        SCCFMT.DATA_LEN = 2195;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B300_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_O_RATE_TYPE = BPCSMMRT.BASE_TYP;
        if (WS_FILE_NAME == 'I') {
            CEP.TRC(SCCGWA, "BRW INTR");
            WS_OUTPUT_DATA.WS_O_TENOR = BPCRINTB.TENOR;
            WS_OUTPUT_DATA.WS_O_CCY = BPCRINTB.CCY;
            WS_OUTPUT_DATA.WS_O_EFF_DT = BPCRINTB.DT;
            WS_OUTPUT_DATA.WS_O_RATE = BPCRINTB.RATE;
        } else {
            CEP.TRC(SCCGWA, "BRW RTFW");
            WS_OUTPUT_DATA.WS_O_TENOR = BPRRTFW.KEY.TENOR;
            WS_OUTPUT_DATA.WS_O_CCY = BPRRTFW.KEY.CCY;
            WS_OUTPUT_DATA.WS_O_EFF_DT = BPRRTFW.KEY.VAL_DT;
            WS_OUTPUT_DATA.WS_O_RATE = BPRRTFW.RATE;
        }
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
        BPCOQPCD.INPUT_DATA.CODE = WS_OUTPUT_DATA.WS_O_TENOR;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        WS_OUTPUT_DATA.WS_O_TENOR_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        WS_RTID_TENOR = WS_OUTPUT_DATA.WS_O_TENOR;
        WS_RTID_CCY = WS_OUTPUT_DATA.WS_O_CCY;
        B300_01_GET_RATEID();
        if (pgmRtn) return;
        WS_OUTPUT_DATA.WS_O_RATE_ID = BPCOQRTD.DATA.RATE_ID;
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.RATE_ID);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 5050;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B300_01_GET_RATEID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQRTD);
        BPCOQRTD.DATA.CCY = WS_RTID_CCY;
        BPCOQRTD.DATA.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCOQRTD.DATA.TENOR = WS_RTID_TENOR;
        CEP.TRC(SCCGWA, BPCSMMRT.CCY);
        CEP.TRC(SCCGWA, BPCSMMRT.BASE_TYP);
        CEP.TRC(SCCGWA, WS_RTID_TENOR);
        BPCOQRTD.INQ_FLG = 'C';
        S000_CALL_BPZPQRTD();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTID-MAINT", BPCRMRTD);
    }
    public void S000_CALL_BPZPQRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-RTID", BPCOQRTD);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        CEP.TRC(SCCGWA, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "FFF");
            CEP.TRC(SCCGWA, BPCOQPCD.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRRTFW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTFW-MAINT", BPCRRTFW);
    }
    public void S000_CALL_BPZRRTBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTBK-MAINT", BPCRRTBK);
    }
    public void S000_CALL_BPZRINTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INTR-MAINT", BPCRINTM);
    }
    public void S000_CALL_BPZRHITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-IHIT-MAINT", BPCRHITM);
    }
    public void S000_CALL_BPZRHISM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-IHIS-MAINT", BPCRHISM);
    }
    public void S000_CALL_BPZRINTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INTR-BRW", BPCRINTB);
    }
    public void S000_CALL_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (WS_HIS_FLG == 'A' 
            || WS_HIS_FLG == 'D') {
            S000_01_CALL_HIS_PROC();
            if (pgmRtn) return;
        }
        if (WS_HIS_FLG == 'M') {
            S000_02_CALL_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_01_CALL_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRTFW);
        IBS.init(SCCGWA, BPRBINTR);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        if (WS_HIS_FLG == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            if (WS_FILE_NAME == 'I') {
                B000_08_MOVE_TO_BPRINTR();
                if (pgmRtn) return;
                BPCPNHIS.INFO.FMT_ID_LEN = 129;
                BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_INTR;
                BPCPNHIS.INFO.TX_TYP_CD = "BPINTR";
                BPCPNHIS.INFO.OLD_DAT_PT = BPRBINTR;
                BPCPNHIS.INFO.NEW_DAT_PT = BPRINTR;
            } else {
                BPCPNHIS.INFO.FMT_ID_LEN = 128;
                BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_RTFW;
                BPCPNHIS.INFO.TX_TYP_CD = "BPRTFW";
                BPCPNHIS.INFO.OLD_DAT_PT = BPRBRTFW;
                BPCPNHIS.INFO.NEW_DAT_PT = BPRRTFW;
            }
        }
        if (WS_HIS_FLG == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.FMT_ID_LEN = 128;
            BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_RTFW;
            BPCPNHIS.INFO.TX_TYP_CD = "BPRTFW";
            BPCPNHIS.INFO.OLD_DAT_PT = BPRRTFW;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRBRTFW;
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REF_NO.WS_HIS_BR = BPCSMMRT.BR;
        WS_HIS_REF_NO.WS_HIS_BASE_TYP = BPCSMMRT.BASE_TYP;
        WS_HIS_REF_NO.WS_HIS_TENOR = WS_TMP_TENOR;
        WS_HIS_REF_NO.WS_HIS_CCY = WS_TMP_CCY;
        WS_HIS_REF_NO.WS_HIS_VAL_DT = WS_TMP_EFF_DT;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_HIS_REF_NO);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_02_CALL_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        if (WS_FILE_NAME == 'I') {
            BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_INTR;
            BPCPNHIS.INFO.TX_TYP_CD = "BPINTR";
        } else {
            BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_RTFW;
            BPCPNHIS.INFO.TX_TYP_CD = "BPRTFW";
        }
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REF_NO.WS_HIS_BR = BPCSMMRT.BR;
        WS_HIS_REF_NO.WS_HIS_BASE_TYP = BPCSMMRT.BASE_TYP;
        WS_HIS_REF_NO.WS_HIS_TENOR = WS_TMP_TENOR;
        WS_HIS_REF_NO.WS_HIS_CCY = WS_TMP_CCY;
        WS_HIS_REF_NO.WS_HIS_VAL_DT = WS_TMP_EFF_DT;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_HIS_REF_NO);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        if (WS_FILE_NAME == 'I') {
            BPCPNHIS.INFO.OLD_DAT_PT = BPRBINTR;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRINTR;
        } else {
            BPCPNHIS.INFO.OLD_DAT_PT = BPRBRTFW;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRRTFW;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_HIS_RTID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS_RTID;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_RTID;
        BPCPNHIS.INFO.TX_TYP_CD = "BPRTID";
        WS_HIS_REF_RTID.WS_HIS_ID_RT = BPCSMMRT.UPD_DATA[1-1].RATE_ID;
        WS_HIS_REF_RTID.WS_HIS_CCY_RT = BPCSMMRT.CCY;
        WS_HIS_REF_RTID.WS_HIS_B_TYPE_RT = BPCSMMRT.BASE_TYP;
        WS_HIS_REF_RTID.WS_HIS_TENOR_RT = BPCSMMRT.UPD_DATA[1-1].TENOR;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_HIS_REF_RTID);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "HHH");
            CEP.TRC(SCCGWA, BPCPNHIS.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZBSPS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-S-GET-BSP-INF", SCCBSPS);
    }
    public void S000_CALL_BPZRHITB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-IHIT-BRW", BPCRHITB);
    }
    public void S000_CALL_BPZPQCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CAL-CODE", BPCOQCAL);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "GGG");
            CEP.TRC(SCCGWA, BPCOCLWD.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZOBSP() throws IOException,SQLException,Exception {
        SCZOBSP SCZOBSP = new SCZOBSP();
        SCZOBSP.MP(SCCGWA, SCCBSP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
