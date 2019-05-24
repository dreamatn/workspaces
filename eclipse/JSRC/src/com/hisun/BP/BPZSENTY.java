package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSENTY {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_BP215 = "BP215";
    String K_AMACM_TYPE = "AMACM";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    short WS_IDX2 = 0;
    short WS_IDX3 = 0;
    short WS_K = 0;
    short WS_NEXT = 0;
    short WS_LEN = 0;
    int WS_CNT1 = 0;
    short WS_TMP = 0;
    String WS_ACMOD_NAME = " ";
    String WS_MOD_NO = " ";
    String WS_CNTR_TYPE1 = " ";
    String WS_PROD_TYPE1 = " ";
    int WS_BR1 = 0;
    BPZSENTY_WS_ENTY_KEY WS_ENTY_KEY = new BPZSENTY_WS_ENTY_KEY();
    BPZSENTY_WS_KEY WS_KEY = new BPZSENTY_WS_KEY();
    BPZSENTY_WS_VAL WS_VAL = new BPZSENTY_WS_VAL();
    char WS_OUTPUT_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_MOD_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRACM BPRACM = new BPRACM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCOENTY BPCOENTY = new BPCOENTY();
    BPCQENTY BPCQENTY = new BPCQENTY();
    BPCUENTY BPCUENTY = new BPCUENTY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQENT BPCPQENT = new BPCPQENT();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    BPRENTY BPRENTY = new BPRENTY();
    BPRENTB BPRENTB = new BPRENTB();
    SCCGWA SCCGWA;
    BPCSENTY BPCSENTY;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSENTY BPCSENTY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSENTY = BPCSENTY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSENTY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSENTY.INPUT_DATA.FUNC != 'B') {
            B010_CHECK_PROC();
            if (pgmRtn) return;
        }
        if (BPCSENTY.INPUT_DATA.FUNC == 'A'
            || BPCSENTY.INPUT_DATA.FUNC == 'C'
            || BPCSENTY.INPUT_DATA.FUNC == 'M'
            || BPCSENTY.INPUT_DATA.FUNC == 'N'
            || BPCSENTY.INPUT_DATA.FUNC == 'D'
            || BPCSENTY.INPUT_DATA.FUNC == 'U'
            || BPCSENTY.INPUT_DATA.FUNC == 'I'
            || BPCSENTY.INPUT_DATA.FUNC == 'E') {
            B020_CALL_BPZUENTY_PROC();
            if (pgmRtn) return;
            if (BPCSENTY.INPUT_DATA.FUNC == 'U' 
                || BPCSENTY.INPUT_DATA.FUNC == 'I') {
                B035_OUTPUT_REC();
                if (pgmRtn) return;
            } else {
                if (BPCSENTY.INPUT_DATA.FUNC == 'E') {
                    B032_OUTPUT_ONE_PROC();
                    if (pgmRtn) return;
                } else {
                    B033_OUTPUT_ONE_PROC();
                    if (pgmRtn) return;
                }
            }
        } else if (BPCSENTY.INPUT_DATA.FUNC == 'B') {
            B040_CALL_BPZPQENT_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.FUNC);
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.BR_AC);
        if (BPCSENTY.INPUT_DATA.FUNC == 'A' 
            && (BPCSENTY.INPUT_DATA.MODNO.trim().length() == 0 
            || BPCSENTY.INPUT_DATA.MODNO.charAt(0) == 0X00)) {
            R000_GET_ACMOD_NO();
            if (pgmRtn) return;
        }
        if ((BPCSENTY.INPUT_DATA.MODNO.trim().length() == 0 
            || BPCSENTY.INPUT_DATA.MODNO.charAt(0) == 0X00) 
            && BPCSENTY.INPUT_DATA.FUNC != 'I') {
            if (BPCSENTY.INPUT_DATA.CNTR_TYPE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTR_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACCU_MOD_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.MODNO);
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.EVENT_TYPE);
        if (BPCSENTY.INPUT_DATA.EVENT_TYPE.trim().length() == 0 
            || BPCSENTY.INPUT_DATA.EVENT_TYPE.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EVENT_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQAMO);
            BPCPQAMO.DATA_INFO.MOD_NO = BPCSENTY.INPUT_DATA.MODNO;
            CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.CNTR_TYPE);
            CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.PROD_TYPE);
            CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.BR);
            CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.MOD_NO);
            BPCPQAMO.FUNC = 'Q';
            S000_CALL_BPZPQAMO();
            if (pgmRtn) return;
            WS_TMP = 0;
            WS_MOD_FLG = 'N';
            for (WS_TMP = 1; WS_TMP <= 60 
                && WS_MOD_FLG != 'Y'; WS_TMP += 1) {
                if (BPCSENTY.INPUT_DATA.EVENT_TYPE.equalsIgnoreCase(BPCPQAMO.DATA_INFO.EVENT[WS_TMP-1].EVENT_TYPE)) {
                    WS_MOD_FLG = 'Y';
                }
            }
            if (WS_MOD_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EVENT_CODE_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.GL_BOOK);
        if (BPCSENTY.INPUT_DATA.GL_BOOK.trim().length() == 0 
            || BPCSENTY.INPUT_DATA.GL_BOOK.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GL_BOOK_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSENTY.INPUT_DATA.FUNC == 'C' 
            || BPCSENTY.INPUT_DATA.FUNC == 'M') {
            if ((!BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("I01") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("I02") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("S") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("N*") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G01") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G02") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G03") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G04") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G05") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G06") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G07") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G08") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G09") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G10") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G11") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G12") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G13") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G14") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G15") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G16") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G17") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G18") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G19") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G20") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G21") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G22") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G23") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G24") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G25") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G26") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G27") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G28") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G29") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G30") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G31") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G32") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G33") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G34") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G35") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G36") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G37") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G38") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G39") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G40") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G41") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G42") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G43") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G44") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G45") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G46") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G47") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G48") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G49") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G50") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G51") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G52") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G53") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G54") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G55") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G56") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G57") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G58") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G59") 
                && !BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("G60"))) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ITEM_PNT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.ITM_PNT);
            if (BPCSENTY.INPUT_DATA.ITM_PNT.equalsIgnoreCase("S")) {
                if (BPCSENTY.INPUT_DATA.ITM_CODE.equalsIgnoreCase("0") 
                    || BPCSENTY.INPUT_DATA.ITM_CODE.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ITM_NO_MUST_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (BPCSENTY.INPUT_DATA.ITM_CODE.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ITEM_CANNOT_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSENTY.INPUT_DATA.BR_FLG == 'S') {
                if (BPCSENTY.INPUT_DATA.BR == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_MUST_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (BPCSENTY.INPUT_DATA.BR != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_CANNOT_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            for (WS_IDX = 1; WS_IDX <= 60; WS_IDX += 1) {
                if (BPCSENTY.INPUT_DATA.AMT_METHOD[WS_IDX-1].AMT_PNT != 0) {
                    WS_NEXT = (short) (WS_IDX + 1);
                    for (WS_IDX2 = WS_NEXT; WS_IDX2 <= 60; WS_IDX2 += 1) {
                        if (BPCSENTY.INPUT_DATA.AMT_METHOD[WS_IDX-1].AMT_PNT == BPCSENTY.INPUT_DATA.AMT_METHOD[WS_IDX2-1].AMT_PNT) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_PNT_BE_SAME;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                    if (BPCSENTY.INPUT_DATA.AMT_METHOD[WS_IDX-1].AMT_FLG == ' ' 
                        || BPCSENTY.INPUT_DATA.AMT_METHOD[WS_IDX-1].AMT_FLG == 0X00) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_PNT_FLG_INPUT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B020_CALL_BPZUENTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUENTY);
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.FUNC);
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.SORT_SEQ);
        BPCUENTY.INPUT_DATA.FUNC = BPCSENTY.INPUT_DATA.FUNC;
        BPCUENTY.INPUT_DATA.MODNO = BPCSENTY.INPUT_DATA.MODNO;
        BPCUENTY.INPUT_DATA.EVENT_TYPE = BPCSENTY.INPUT_DATA.EVENT_TYPE;
        BPCUENTY.INPUT_DATA.GL_BOOK = BPCSENTY.INPUT_DATA.GL_BOOK;
        BPCUENTY.INPUT_DATA.SORT_SEQ = BPCSENTY.INPUT_DATA.SORT_SEQ;
        BPCUENTY.INPUT_DATA.TR_GROUP = BPCSENTY.INPUT_DATA.TR_GROUP;
        BPCUENTY.INPUT_DATA.GLMST_PNT = BPCSENTY.INPUT_DATA.GLMST_PNT;
        BPCUENTY.INPUT_DATA.DR_CR = BPCSENTY.INPUT_DATA.DR_CR;
        BPCUENTY.INPUT_DATA.ITM_CODE = BPCSENTY.INPUT_DATA.ITM_CODE;
        BPCUENTY.INPUT_DATA.ITM_PNT = BPCSENTY.INPUT_DATA.ITM_PNT;
        BPCUENTY.INPUT_DATA.BR_FLG = BPCSENTY.INPUT_DATA.BR_FLG;
        BPCUENTY.INPUT_DATA.BR = BPCSENTY.INPUT_DATA.BR;
        BPCUENTY.INPUT_DATA.CCY_PNT = BPCSENTY.INPUT_DATA.CCY_PNT;
        CEP.TRC(SCCGWA, "SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS:");
        CEP.TRC(SCCGWA, BPCUENTY.INPUT_DATA.CCY_PNT);
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.POST_AUTH);
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.RM_FLG);
        BPCUENTY.INPUT_DATA.POST_AUTH = BPCSENTY.INPUT_DATA.POST_AUTH;
        BPCUENTY.INPUT_DATA.RM_FLG = BPCSENTY.INPUT_DATA.RM_FLG;
        BPCUENTY.INPUT_DATA.EFF_DAYS = BPCSENTY.INPUT_DATA.EFF_DAYS;
        for (WS_IDX = 1; WS_IDX <= 60; WS_IDX += 1) {
            if (BPCSENTY.INPUT_DATA.AMT_METHOD[WS_IDX-1].AMT_PNT != 0) {
                CEP.TRC(SCCGWA, WS_IDX);
                WS_IDX2 = BPCSENTY.INPUT_DATA.AMT_METHOD[WS_IDX-1].AMT_PNT;
                BPCUENTY.INPUT_DATA.AMT_METHOD.AMT_FLG[WS_IDX2-1] = BPCSENTY.INPUT_DATA.AMT_METHOD[WS_IDX-1].AMT_FLG;
                CEP.TRC(SCCGWA, "TTTTTTTTTTTTTTTTT");
                CEP.TRC(SCCGWA, WS_IDX2);
                CEP.TRC(SCCGWA, BPCUENTY.INPUT_DATA.AMT_METHOD.AMT_FLG[WS_IDX2-1]);
            }
        }
        BPCUENTY.INPUT_DATA.REMARK = BPCSENTY.INPUT_DATA.REMARK;
        BPCUENTY.INPUT_DATA.HIDE_INFO.LST_UPD_DATE = BPCSENTY.INPUT_DATA.HIDE_INFO.LST_UPD_DATE;
        BPCUENTY.INPUT_DATA.HIDE_INFO.LST_UPD_TM = BPCSENTY.INPUT_DATA.HIDE_INFO.LST_UPD_TM;
        BPCUENTY.INPUT_DATA.HIDE_INFO.EVENT_POS = BPCSENTY.INPUT_DATA.HIDE_INFO.EVENT_POS;
        S000_CALL_BPZUENTY();
        if (pgmRtn) return;
    }
    public void B032_OUTPUT_ONE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQENTY);
        BPCQENTY.BASIC_DATA.BASIC_DATA2.CNTR_TYPE = BPCSENTY.INPUT_DATA.CNTR_TYPE;
        if (BPCSENTY.INPUT_DATA.PROD_TYPE.trim().length() == 0) {
            BPCQENTY.BASIC_DATA.BASIC_DATA2.PROD_TYPE = "*";
        } else {
            BPCQENTY.BASIC_DATA.BASIC_DATA2.PROD_TYPE = BPCSENTY.INPUT_DATA.PROD_TYPE;
        }
        if (BPCSENTY.INPUT_DATA.BR_AC == 0) {
            BPCQENTY.BASIC_DATA.BASIC_DATA2.BR_AC = 999999;
        } else {
            BPCQENTY.BASIC_DATA.BASIC_DATA2.BR_AC = BPCSENTY.INPUT_DATA.BR_AC;
        }
        BPCQENTY.BASIC_DATA.BASIC_DATA2.MODNO = BPCUENTY.INPUT_DATA.MODNO;
        WS_MOD_NO = BPCUENTY.INPUT_DATA.MODNO;
        BPCQENTY.BASIC_DATA.BASIC_DATA2.MOD_NAME = WS_ACMOD_NAME;
        BPCQENTY.BASIC_DATA.BASIC_DATA2.EVENT_TYPE = BPCUENTY.INPUT_DATA.EVENT_TYPE;
        BPCQENTY.BASIC_DATA.BASIC_DATA2.GL_BOOK = BPCUENTY.INPUT_DATA.GL_BOOK;
        BPCQENTY.LIST_DATA.SORT_SEQ = BPCUENTY.INPUT_DATA.SORT_SEQ;
        BPCQENTY.LIST_DATA.TR_GROUP = BPCUENTY.INPUT_DATA.TR_GROUP;
        BPCQENTY.LIST_DATA.GLMST_PNT = BPCUENTY.INPUT_DATA.GLMST_PNT;
        BPCQENTY.LIST_DATA.DR_CR = BPCUENTY.INPUT_DATA.DR_CR;
        BPCQENTY.LIST_DATA.ITM_CODE = BPCUENTY.INPUT_DATA.ITM_CODE;
        BPCQENTY.LIST_DATA.ITM_PNT = BPCUENTY.INPUT_DATA.ITM_PNT;
        BPCQENTY.LIST_DATA.BR_FLG = BPCUENTY.INPUT_DATA.BR_FLG;
        BPCQENTY.LIST_DATA.BR = BPCUENTY.INPUT_DATA.BR;
        BPCQENTY.LIST_DATA.CCY_PNT = BPCUENTY.INPUT_DATA.CCY_PNT;
        BPCQENTY.LIST_DATA.POST_AUTH = BPCUENTY.INPUT_DATA.POST_AUTH;
        BPCQENTY.LIST_DATA.RM_FLG = BPCUENTY.INPUT_DATA.RM_FLG;
        BPCQENTY.LIST_DATA.EFF_DAYS = BPCUENTY.INPUT_DATA.EFF_DAYS;
        for (WS_IDX = 1; WS_IDX <= 76; WS_IDX += 1) {
            if (BPCUENTY.INPUT_DATA.AMT_METHOD.AMT_FLG[WS_IDX-1] != ' ') {
                WS_K = (short) (WS_K + 1);
                BPCQENTY.LIST_DATA.AMT_METHOD[WS_K-1].AMT_PNT.AMT_PNT_CNT = WS_IDX;
                BPCQENTY.LIST_DATA.AMT_METHOD[WS_K-1].AMT_FLG = BPCUENTY.INPUT_DATA.AMT_METHOD.AMT_FLG[WS_IDX-1];
            }
        }
        BPCQENTY.LIST_DATA.REMARK = BPCUENTY.INPUT_DATA.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_BP215;
        SCCFMT.DATA_PTR = BPCQENTY;
        SCCFMT.DATA_LEN = 498;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B033_OUTPUT_ONE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOENTY);
        BPCOENTY.BASIC_DATA.BASIC_DATA2.CNTR_TYPE = BPCSENTY.INPUT_DATA.CNTR_TYPE;
        if (BPCSENTY.INPUT_DATA.PROD_TYPE.trim().length() == 0) {
            BPCOENTY.BASIC_DATA.BASIC_DATA2.PROD_TYPE = "*";
        } else {
            BPCOENTY.BASIC_DATA.BASIC_DATA2.PROD_TYPE = BPCSENTY.INPUT_DATA.PROD_TYPE;
        }
        if (BPCSENTY.INPUT_DATA.BR_AC == 0) {
            BPCOENTY.BASIC_DATA.BASIC_DATA2.BR_AC = 999999;
        } else {
            BPCOENTY.BASIC_DATA.BASIC_DATA2.BR_AC = BPCSENTY.INPUT_DATA.BR_AC;
        }
        BPCOENTY.BASIC_DATA.BASIC_DATA2.MODNO = BPCSENTY.INPUT_DATA.MODNO;
        WS_MOD_NO = BPCSENTY.INPUT_DATA.MODNO;
        BPCOENTY.BASIC_DATA.BASIC_DATA2.MOD_NAME = WS_ACMOD_NAME;
        BPCOENTY.BASIC_DATA.BASIC_DATA2.EVENT_TYPE = BPCSENTY.INPUT_DATA.EVENT_TYPE;
        BPCOENTY.BASIC_DATA.BASIC_DATA2.GL_BOOK = BPCSENTY.INPUT_DATA.GL_BOOK;
        BPCOENTY.LIST_DATA.SORT_SEQ = BPCSENTY.INPUT_DATA.SORT_SEQ;
        BPCOENTY.LIST_DATA.TR_GROUP = BPCSENTY.INPUT_DATA.TR_GROUP;
        BPCOENTY.LIST_DATA.GLMST_PNT = BPCSENTY.INPUT_DATA.GLMST_PNT;
        BPCOENTY.LIST_DATA.DR_CR = BPCSENTY.INPUT_DATA.DR_CR;
        BPCOENTY.LIST_DATA.ITM_CODE = BPCSENTY.INPUT_DATA.ITM_CODE;
        BPCOENTY.LIST_DATA.ITM_PNT = BPCSENTY.INPUT_DATA.ITM_PNT;
        BPCOENTY.LIST_DATA.BR_FLG = BPCSENTY.INPUT_DATA.BR_FLG;
        BPCOENTY.LIST_DATA.BR = BPCSENTY.INPUT_DATA.BR;
        BPCOENTY.LIST_DATA.CCY_PNT = BPCSENTY.INPUT_DATA.CCY_PNT;
        BPCOENTY.LIST_DATA.POST_AUTH = BPCSENTY.INPUT_DATA.POST_AUTH;
        BPCOENTY.LIST_DATA.RM_FLG = BPCSENTY.INPUT_DATA.RM_FLG;
        BPCOENTY.LIST_DATA.EFF_DAYS = BPCSENTY.INPUT_DATA.EFF_DAYS;
        for (WS_IDX = 1; WS_IDX <= 60; WS_IDX += 1) {
            BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX-1].AMT_PNT.AMT_PNT_FIXED = 'A';
            BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX-1].AMT_PNT.AMT_PNT_CNT = "" + BPCSENTY.INPUT_DATA.AMT_METHOD[WS_IDX-1].AMT_PNT;
            JIBS_tmp_int = BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX-1].AMT_PNT.AMT_PNT_CNT.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX-1].AMT_PNT.AMT_PNT_CNT = "0" + BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX-1].AMT_PNT.AMT_PNT_CNT;
            BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX-1].AMT_FLG = BPCSENTY.INPUT_DATA.AMT_METHOD[WS_IDX-1].AMT_FLG;
        }
        BPCOENTY.LIST_DATA.REMARK = BPCSENTY.INPUT_DATA.REMARK;
        CEP.TRC(SCCGWA, BPCOENTY.LIST_DATA);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_BP215;
        SCCFMT.DATA_PTR = BPCOENTY;
        SCCFMT.DATA_LEN = 647;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B035_OUTPUT_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 4086;
        S000_CALL_SCZSUBS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 1;
        SCCMPAG.MAX_COL_NO = 461;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCOENTY);
        IBS.init(SCCGWA, BPCPQENT);
        BPCPQENT.FUNC = 'Q';
        BPCPQENT.DATA_INFO.MODNO = BPCSENTY.INPUT_DATA.MODNO;
        BPCPQENT.DATA_INFO.EVENT_TYPE = BPCSENTY.INPUT_DATA.EVENT_TYPE;
        BPCPQENT.DATA_INFO.GL_BOOK = BPCSENTY.INPUT_DATA.GL_BOOK;
        CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.MODNO);
        CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.EVENT_TYPE);
        CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.GL_BOOK);
        S000_CALL_BPZPQENT();
        if (pgmRtn) return;
        BPCOENTY.BASIC_DATA.BASIC_DATA2.MODNO = BPCSENTY.INPUT_DATA.MODNO;
        WS_MOD_NO = BPCSENTY.INPUT_DATA.MODNO;
        BPCOENTY.BASIC_DATA.BASIC_DATA2.MOD_NAME = WS_ACMOD_NAME;
        BPCOENTY.BASIC_DATA.BASIC_DATA2.CNTR_TYPE = BPCSENTY.INPUT_DATA.CNTR_TYPE;
        if (WS_PROD_TYPE1.trim().length() == 0) {
            BPCOENTY.BASIC_DATA.BASIC_DATA2.PROD_TYPE = "*";
        } else {
            BPCOENTY.BASIC_DATA.BASIC_DATA2.PROD_TYPE = WS_PROD_TYPE1;
        }
        if (WS_BR1 == 0) {
            BPCOENTY.BASIC_DATA.BASIC_DATA2.BR_AC = 999999;
        } else {
            BPCOENTY.BASIC_DATA.BASIC_DATA2.BR_AC = WS_BR1;
        }
        BPCOENTY.BASIC_DATA.BASIC_DATA2.EVENT_TYPE = BPCSENTY.INPUT_DATA.EVENT_TYPE;
        BPCOENTY.BASIC_DATA.BASIC_DATA2.GL_BOOK = BPCSENTY.INPUT_DATA.GL_BOOK;
        BPCOENTY.BASIC_DATA.BASIC_DATA2.EVENT_CNT = BPCPQENT.DATA_INFO.CNT;
        BPCOENTY.BASIC_DATA.BASIC_DATA2.LST_UPD_DATE = BPCPQENT.DATA_INFO.UPD_DATE;
        BPCOENTY.BASIC_DATA.BASIC_DATA2.LST_UPD_TM = BPCPQENT.DATA_INFO.UPD_TIME;
        BPCOENTY.BASIC_DATA.BASIC_DATA2.LST_UPD_TLR = BPCPQENT.DATA_INFO.UPD_TEL;
        WS_LEN = 186;
        WS_OUTPUT_FLG = '1';
        S000_WRITE_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.CNT);
        for (WS_IDX = 1; WS_IDX <= BPCPQENT.DATA_INFO.CNT; WS_IDX += 1) {
            IBS.init(SCCGWA, BPCOENTY.LIST_DATA);
            BPCOENTY.LIST_DATA.EVENT_POS = WS_IDX;
            CEP.TRC(SCCGWA, BPCOENTY.LIST_DATA.EVENT_POS);
            BPCOENTY.LIST_DATA.SORT_SEQ = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].ENTRY_NUMBER;
            BPCOENTY.LIST_DATA.TR_GROUP = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].TR_GROUP;
            BPCOENTY.LIST_DATA.GLMST_PNT = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].GL_MST;
            BPCOENTY.LIST_DATA.DR_CR = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].DR_CR;
            BPCOENTY.LIST_DATA.ITM_CODE = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].GL_CODE;
            BPCOENTY.LIST_DATA.ITM_PNT = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].AC_KEY;
            BPCOENTY.LIST_DATA.BR_FLG = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].BR_FLG;
            BPCOENTY.LIST_DATA.BR = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].BR;
            BPCOENTY.LIST_DATA.CCY_PNT = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].CCY_PNT;
            BPCOENTY.LIST_DATA.POST_AUTH = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].POST_AUTH;
            CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].POST_AUTH);
            CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].RM_FLG);
            BPCOENTY.LIST_DATA.RM_FLG = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].RM_FLG;
            BPCOENTY.LIST_DATA.EFF_DAYS = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].EFF_DAYS;
            WS_IDX3 = 1;
            for (WS_IDX2 = 1; WS_IDX2 <= 76 
                && WS_IDX3 <= 60; WS_IDX2 += 1) {
                if (BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].AMT_METHOD == null) BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].AMT_METHOD = "";
                JIBS_tmp_int = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].AMT_METHOD.length();
                for (int i=0;i<76-JIBS_tmp_int;i++) BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].AMT_METHOD += " ";
                if (BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].AMT_METHOD.substring(WS_IDX2 - 1, WS_IDX2 + 1 - 1).trim().length() > 0) {
                    BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX3-1].AMT_PNT.AMT_PNT_FIXED = 'A';
                    CEP.TRC(SCCGWA, WS_IDX2);
                    BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX3-1].AMT_PNT.AMT_PNT_CNT = "" + WS_IDX2;
                    JIBS_tmp_int = BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX3-1].AMT_PNT.AMT_PNT_CNT.length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX3-1].AMT_PNT.AMT_PNT_CNT = "0" + BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX3-1].AMT_PNT.AMT_PNT_CNT;
                    if (BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].AMT_METHOD == null) BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].AMT_METHOD = "";
                    JIBS_tmp_int = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].AMT_METHOD.length();
                    for (int i=0;i<76-JIBS_tmp_int;i++) BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].AMT_METHOD += " ";
                    BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX3-1].AMT_FLG = BPCPQENT.DATA_INFO.EVENT_DATA[WS_IDX-1].AMT_METHOD.substring(WS_IDX2 - 1, WS_IDX2 + 1 - 1).charAt(0);
                    CEP.TRC(SCCGWA, BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX3-1].AMT_PNT.AMT_PNT_CNT);
                    CEP.TRC(SCCGWA, BPCOENTY.LIST_DATA.AMT_METHOD[WS_IDX3-1].AMT_FLG);
                    WS_IDX3 += 1;
                }
            }
            BPCOENTY.LIST_DATA.REMARK = BPRENTB.DATA_TXT.EVENT_DATA.get(WS_IDX-1).REMARK;
            WS_LEN = 461;
            S000_WRITE_TS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOENTY.LIST_DATA);
        }
    }
    public void B040_CALL_BPZPQENT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 4085;
        S000_CALL_SCZSUBS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 185;
        SCCMPAG.SCR_ROW_CNT = 10;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.PROD_TYPE);
        CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.BR_AC);
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPRPARM.KEY.TYPE = K_AMACM_TYPE;
        BPCRMBPM.FUNC = 'S';
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        while (BPCRMBPM.RETURN_INFO != 'N' 
            && BPCRMBPM.RETURN_INFO != 'L') {
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, WS_VAL);
            IBS.CPY2CLS(SCCGWA, BPRPARM.KEY.CODE, WS_KEY);
            CEP.TRC(SCCGWA, WS_VAL.WS_MOD_NO_AC);
            CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.CNTR_TYPE);
            CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.PROD_TYPE);
            CEP.TRC(SCCGWA, BPCSENTY.INPUT_DATA.BR_AC);
            if ((BPCSENTY.INPUT_DATA.CNTR_TYPE.trim().length() == 0 
                || WS_KEY.WS_CNTR_TYPE.equalsIgnoreCase(BPCSENTY.INPUT_DATA.CNTR_TYPE)) 
                && (BPCSENTY.INPUT_DATA.PROD_TYPE.trim().length() == 0 
                || WS_KEY.WS_PROD_TYPE.equalsIgnoreCase(BPCSENTY.INPUT_DATA.PROD_TYPE)) 
                && (BPCSENTY.INPUT_DATA.BR_AC == 0 
                || WS_KEY.WS_BR_AC == BPCSENTY.INPUT_DATA.BR_AC)) {
                BPCSENTY.INPUT_DATA.MODNO = WS_VAL.WS_MOD_NO_AC;
                S000_CALL_BPRENTY();
                if (pgmRtn) return;
            }
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
        }
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        if (WS_CNT1 == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPRENTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQENT);
        BPCPQENT.FUNC = 'B';
        BPCPQENT.DATA_INFO.MODNO = BPCSENTY.INPUT_DATA.MODNO;
        BPCPQENT.DATA_INFO.EVENT_TYPE = BPCSENTY.INPUT_DATA.EVENT_TYPE;
        BPCPQENT.DATA_INFO.GL_BOOK = BPCSENTY.INPUT_DATA.GL_BOOK;
        S000_CALL_BPZPQENT();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQENT.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_9380) 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCPQENT);
            BPCPQENT.FUNC = 'N';
            BPCPQENT.DATA_INFO.MODNO = BPCSENTY.INPUT_DATA.MODNO;
            BPCPQENT.DATA_INFO.EVENT_TYPE = BPCSENTY.INPUT_DATA.EVENT_TYPE;
            BPCPQENT.DATA_INFO.GL_BOOK = BPCSENTY.INPUT_DATA.GL_BOOK;
            S000_CALL_BPZPQENT();
            if (pgmRtn) return;
            if (BPCPQENT.RC.RC_CODE == 0) {
                IBS.init(SCCGWA, BPCOENTY);
                BPCOENTY.BASIC_DATA.BASIC_DATA2.CNTR_TYPE = WS_KEY.WS_CNTR_TYPE;
                if (WS_KEY.WS_PROD_TYPE.trim().length() == 0) {
                    BPCOENTY.BASIC_DATA.BASIC_DATA2.PROD_TYPE = "*";
                } else {
                    BPCOENTY.BASIC_DATA.BASIC_DATA2.PROD_TYPE = WS_KEY.WS_PROD_TYPE;
                }
                if (WS_KEY.WS_BR_AC == 0) {
                    BPCOENTY.BASIC_DATA.BASIC_DATA2.BR_AC = 999999;
                } else {
                    BPCOENTY.BASIC_DATA.BASIC_DATA2.BR_AC = WS_KEY.WS_BR_AC;
                }
                BPCOENTY.BASIC_DATA.BASIC_DATA2.MODNO = BPCPQENT.DATA_INFO.MODNO;
                BPCOENTY.BASIC_DATA.BASIC_DATA2.EVENT_TYPE = BPCPQENT.DATA_INFO.EVENT_TYPE;
                BPCOENTY.BASIC_DATA.BASIC_DATA2.GL_BOOK = BPCPQENT.DATA_INFO.GL_BOOK;
                BPCOENTY.BASIC_DATA.BASIC_DATA2.EVENT_CNT = BPCPQENT.DATA_INFO.CNT;
                BPCOENTY.BASIC_DATA.BASIC_DATA2.LST_UPD_DATE = BPCPQENT.DATA_INFO.UPD_DATE;
                BPCOENTY.BASIC_DATA.BASIC_DATA2.LST_UPD_TM = BPCPQENT.DATA_INFO.UPD_TIME;
                BPCOENTY.BASIC_DATA.BASIC_DATA2.LST_UPD_TLR = BPCPQENT.DATA_INFO.UPD_TEL;
                WS_MOD_NO = BPCPQENT.DATA_INFO.MODNO;
                BPCOENTY.BASIC_DATA.BASIC_DATA2.MOD_NAME = WS_ACMOD_NAME;
                WS_LEN = 185;
                WS_OUTPUT_FLG = '2';
                CEP.TRC(SCCGWA, BPCPQENT);
                CEP.TRC(SCCGWA, BPCOENTY.BASIC_DATA.BASIC_DATA2);
                S000_WRITE_TS();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPCPQENT);
        BPCPQENT.FUNC = 'E';
        BPCPQENT.DATA_INFO.MODNO = BPCSENTY.INPUT_DATA.MODNO;
        BPCPQENT.DATA_INFO.EVENT_TYPE = BPCSENTY.INPUT_DATA.EVENT_TYPE;
        BPCPQENT.DATA_INFO.GL_BOOK = BPCSENTY.INPUT_DATA.GL_BOOK;
        S000_CALL_BPZPQENT();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZUENTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-ENTY", BPCUENTY);
        if (BPCUENTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUENTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_ACMOD_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQAMO);
        BPCPQAMO.DATA_INFO.CNTR_TYPE = BPCSENTY.INPUT_DATA.CNTR_TYPE;
        BPCPQAMO.DATA_INFO.PROD_TYPE = BPCSENTY.INPUT_DATA.PROD_TYPE;
        BPCPQAMO.DATA_INFO.BR = BPCSENTY.INPUT_DATA.BR_AC;
        BPCPQAMO.FUNC = 'Q';
        S000_CALL_BPZPQAMO();
        if (pgmRtn) return;
        BPCSENTY.INPUT_DATA.MODNO = BPCPQAMO.DATA_INFO.MOD_NO;
    }
    public void R000_GET_ACMOD_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQAMO);
        WS_ACMOD_NAME = " ";
        BPCPQAMO.FUNC = 'B';
        S000_CALL_BPZPQAMO();
        if (pgmRtn) return;
        WS_END_FLG = 'N';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQAMO.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_9383) 
            && WS_END_FLG != 'Y') {
            IBS.init(SCCGWA, BPCPQAMO);
            BPCPQAMO.FUNC = 'N';
            S000_CALL_BPZPQAMO();
            if (pgmRtn) return;
            if (BPCPQAMO.RC.RC_CODE == 0 
                && WS_MOD_NO.equalsIgnoreCase(BPCPQAMO.DATA_INFO.MOD_NO)) {
                WS_END_FLG = 'Y';
                WS_ACMOD_NAME = BPCPQAMO.DATA_INFO.MOD_NAME;
                WS_CNTR_TYPE1 = BPCPQAMO.DATA_INFO.CNTR_TYPE;
                WS_PROD_TYPE1 = BPCPQAMO.DATA_INFO.PROD_TYPE;
                WS_BR1 = BPCPQAMO.DATA_INFO.BR;
            }
        }
        IBS.init(SCCGWA, BPCPQAMO);
        BPCPQAMO.FUNC = 'E';
        S000_CALL_BPZPQAMO();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        WS_CNT1 = WS_CNT1 + 1;
        CEP.TRC(SCCGWA, WS_CNT1);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        if (WS_OUTPUT_FLG == '1') {
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOENTY.BASIC_DATA);
        } else if (WS_OUTPUT_FLG == ' ') {
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOENTY.LIST_DATA);
        } else if (WS_OUTPUT_FLG == '2') {
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOENTY.BASIC_DATA.BASIC_DATA2);
        } else {
        }
        SCCMPAG.DATA_LEN = WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
        WS_OUTPUT_FLG = ' ';
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZSUBS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS   ", SCCSUBS);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC ", BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_9380;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQENT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-EVENT", BPCPQENT);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQENT.RC);
        if (BPCPQENT.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_9380)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQENT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-AMOD  ", BPCPQAMO);
        CEP.TRC(SCCGWA, BPCPQAMO.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQAMO.RC);
        if (BPCPQAMO.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_9383)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQAMO.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
