package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSRCI {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_C_VALID_TNRS_INQ = "BP-C-VALID-TNRS-INQ";
    String CPN_C_INTR_INQ = "BP-C-INTR-INQ       ";
    String CPN_BROWSE_CCY = "BP-BROWSE-CCY     ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String CPN_R_BK_MT = "BP-R-IRPD-MAINT";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String K_OUTPUT_FMT = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_II = 0;
    int WS_TENOR_CNT = 0;
    short WS_J = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_BR = 0;
    String WS_LAST_CCY = " ";
    String WS_TENOR = " ";
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    int WS_CCY_CNT = 0;
    BPZSSRCI_WS_CCY_TWENTY[] WS_CCY_TWENTY = new BPZSSRCI_WS_CCY_TWENTY[200];
    BPZSSRCI_WS_TAB_CNT[] WS_TAB_CNT = new BPZSSRCI_WS_TAB_CNT[20];
    BPZSSRCI_WS_RATE_ARR WS_RATE_ARR = new BPZSSRCI_WS_RATE_ARR();
    String WS_BRO_CCY_TMP = " ";
    BPZSSRCI_WS_BROSE_OUTPUT WS_BROSE_OUTPUT = new BPZSSRCI_WS_BROSE_OUTPUT();
    char WS_RATE_FLG = ' ';
    char WS_CCY_FLG = ' ';
    char WS_RECORD_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCCVALI BPCCVALI = new BPCCVALI();
    BPCOSRCI BPCOSRCI = new BPCOSRCI();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCSCCY BPCSCCY = new BPCSCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    SCCGWA SCCGWA;
    BPCSSRCI BPCSSRCI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSSRCI() {
        for (int i=0;i<200;i++) WS_CCY_TWENTY[i] = new BPZSSRCI_WS_CCY_TWENTY();
        for (int i=0;i<20;i++) WS_TAB_CNT[i] = new BPZSSRCI_WS_TAB_CNT();
    }
    public void MP(SCCGWA SCCGWA, BPCSSRCI BPCSSRCI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSRCI = BPCSSRCI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSSRCI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOSRCI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSSRCI.DT);
        CEP.TRC(SCCGWA, "CGBYWSSSRCI");
        if (BPCSSRCI.DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_RATE_FLG = 'C';
        } else {
            WS_RATE_FLG = 'F';
        }
        if (BPCSSRCI.FUNC == 'Q') {
            B200_QUERY_PROCESS();
            if (pgmRtn) return;
            B300_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "BPZSSRCI END");
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSSRCI.CCY.trim().length() == 0 
            || BPCSSRCI.CCY.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_SPACE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSSRCI.FUNC);
        if (BPCSSRCI.FUNC != 'Q' 
            && BPCSSRCI.FUNC != 'B') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSSRCI.BR);
        if (BPCSSRCI.BR == ' ' 
            || BPCSSRCI.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "NCB040905");
            CEP.TRC(SCCGWA, BPCSSRCI.BR);
            if (BPCSSRCI.BR != 999999) {
                WS_BR = BPCSSRCI.BR;
                CEP.TRC(SCCGWA, WS_BR);
                R000_CHECK_BRANCH();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSSRCI.CCY);
        if (BPCSSRCI.CCY.trim().length() > 0) {
            WS_CCY = BPCSSRCI.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        } else {
            WS_CCY = " ";
        }
        CEP.TRC(SCCGWA, BPCSSRCI.BASE_TYP);
        if (BPCSSRCI.BASE_TYP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BASETYP_NO_VALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_BASE_TYP = BPCSSRCI.BASE_TYP;
            R000_CHECK_BASE_TYP();
            if (pgmRtn) return;
        }
        R000_GET_CCY_AND_TENOR();
        if (pgmRtn) return;
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CGBYWSCNT");
        CEP.TRC(SCCGWA, WS_TENOR_CNT);
        if (WS_TENOR_CNT > 20) {
            WS_TENOR_CNT = 20;
        }
        if (WS_TENOR_CNT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            for (WS_I = 1; WS_I <= WS_TENOR_CNT; WS_I += 1) {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.BR = BPCSSRCI.BR;
                BPCCINTI.BASE_INFO.BASE_TYP = BPCSSRCI.BASE_TYP;
                BPCCINTI.BASE_INFO.CCY = BPCOSRCI.CCY;
                BPCCINTI.BASE_INFO.TENOR = BPCOSRCI.TBL_TENOR[WS_I-1].TENOR;
                WS_RECORD_FLG = 'N';
                BPCCINTI.BASE_INFO.DT = BPCSSRCI.DT;
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.DT);
                S000_CALL_BPZCINTI();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BR);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BASE_TYP);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.CCY);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
                CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_BR);
                CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_CCY);
                CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_BASE_TYP);
                CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_TENOR);
                if (WS_RECORD_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "CCCCCCC");
                    BPCOSRCI.TBL.RATE_TBL[WS_I-1].RATE = 0;
                } else {
                    if (BPCCINTI.BASE_INFO.RATE == 0) {
                        CEP.TRC(SCCGWA, "AAAAAA");
                        B200_01_QUERY_BANK_PROCESS();
                        if (pgmRtn) return;
                    } else {
                        CEP.TRC(SCCGWA, "BBBBBB");
                        if (BPCSSRCI.BR == BPCCINTI.BASE_INFO.BR) {
                            BPCOSRCI.TBL.RATE_TBL[WS_I-1].RATE = BPCCINTI.BASE_INFO.RATE;
                            BPCOSRCI.TBL_DT[WS_I-1].DT = BPCCINTI.BASE_INFO.DT;
                        } else {
                            BPCOSRCI.TBL.RATE_TBL[WS_I-1].RATE = BPCCINTI.BASE_INFO.RATE;
                            BPCOSRCI.TBL_DT[WS_I-1].DT = BPCCINTI.BASE_INFO.DT;
                        }
                    }
                }
                if (BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_BR != 0 
                    && BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_CCY.trim().length() > 0 
                    && BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_BASE_TYP.trim().length() > 0 
                    && !BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_TENOR.equalsIgnoreCase("0")) {
                    CEP.TRC(SCCGWA, "11111");
                    BPCOSRCI.REF_A[WS_I-1].REF_BR = BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_BR;
                    BPCOSRCI.REF_A[WS_I-1].REF_CCY = BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_CCY;
                    BPCOSRCI.REF_A[WS_I-1].REF_TYP = BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_BASE_TYP;
                    BPCOSRCI.REF_A[WS_I-1].REF_TERM = BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_TENOR;
                    BPCOSRCI.REF_A[WS_I-1].REF_OPT = BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_FORMAT;
                    BPCOSRCI.REF_A[WS_I-1].REF_DIFF = BPCCINTI.EXT_INFO.REF_TBL[1-1].REF_DIFF;
                }
            }
        }
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
        B210_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSSRCI.BR);
        CEP.TRC(SCCGWA, BPCSSRCI.BASE_TYP);
        CEP.TRC(SCCGWA, BPCSSRCI.NAME);
        CEP.TRC(SCCGWA, BPCOSRCI.TBL_DT[1-1].DT);
        CEP.TRC(SCCGWA, "CGBYWSHELP");
    }
    public void B400_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 1620;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.BASE_TYP = BPCSSRCI.BASE_TYP;
        R000_STARTBR_BPZRIPDM();
        if (pgmRtn) return;
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            R000_READNEXT_BPZRIPDM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRIRPD);
            CEP.TRC(SCCGWA, BPCRIPDM.RETURN_INFO);
            if (BPCRIPDM.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                if (BPRIRPD.CONTRL == 'Y') {
                    WS_BRO_CCY_TMP = BPRIRPD.KEY.CCY;
                    CEP.TRC(SCCGWA, WS_BRO_CCY_TMP);
                    CEP.TRC(SCCGWA, WS_BROSE_OUTPUT.WS_BRO_CCY);
                    if (!WS_BRO_CCY_TMP.equalsIgnoreCase(WS_BROSE_OUTPUT.WS_BRO_CCY)) {
                        WS_BROSE_OUTPUT.WS_BRO_TYPE = BPCSSRCI.BASE_TYP;
                        WS_BROSE_OUTPUT.WS_BRO_TYPE_NAME = BPCSSRCI.NAME;
                        WS_BROSE_OUTPUT.WS_BRO_BR = BPCSSRCI.BR;
                        WS_BROSE_OUTPUT.WS_BRO_DT = BPCSSRCI.DT;
                        WS_BROSE_OUTPUT.WS_BRO_CCY = WS_BRO_CCY_TMP;
                        IBS.init(SCCGWA, SCCMPAG);
                        SCCMPAG.FUNC = 'D';
                        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROSE_OUTPUT);
                        SCCMPAG.DATA_LEN = 1620;
                        B_MPAG();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        R000_ENDBR_BPZRIPDM();
        if (pgmRtn) return;
    }
    public void B200_01_QUERY_BANK_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.HQT_BANK;
        BPCCINTI.BASE_INFO.BASE_TYP = BPCSSRCI.BASE_TYP;
        BPCCINTI.BASE_INFO.CCY = BPCOSRCI.CCY;
        BPCCINTI.BASE_INFO.TENOR = BPCOSRCI.TBL_TENOR[WS_I-1].TENOR;
        BPCCINTI.BASE_INFO.DT = BPCSSRCI.DT;
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        if (WS_RECORD_FLG == 'Y') {
            BPCOSRCI.TBL.RATE_TBL[WS_I-1].RATE = 0;
        } else {
            if (BPCCINTI.BASE_INFO.RATE == 0) {
                if (!BPCCINTI.BASE_INFO.BASE_TYP.equalsIgnoreCase("0") 
                    && !BPCCINTI.BASE_INFO.TENOR.equalsIgnoreCase("0")) {
                    BPCOSRCI.TBL.RATE_TBL[WS_I-1].RATE = 0;
                }
            } else {
                BPCOSRCI.TBL.RATE_TBL[WS_I-1].RATE = BPCCINTI.BASE_INFO.RATE;
            }
        }
    }
    public void B300_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOSRCI;
        SCCFMT.DATA_LEN = 6113;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "CGBYWSOUT");
        CEP.TRC(SCCGWA, BPCOSRCI.TBL_DT[1-1].DT);
        CEP.TRC(SCCGWA, BPCOSRCI.BR);
        CEP.TRC(SCCGWA, BPCOSRCI.CCY);
        CEP.TRC(SCCGWA, BPCOSRCI.BASE_TYP);
        CEP.TRC(SCCGWA, BPCOSRCI.NAME);
        CEP.TRC(SCCGWA, BPCOSRCI.TBL_TENOR[1-1].TENOR);
        CEP.TRC(SCCGWA, BPCOSRCI.TBL.RATE_TBL[2-1].RATE);
        CEP.TRC(SCCGWA, BPCOSRCI.TBL.RATE_TBL[1-1].RATE);
        CEP.TRC(SCCGWA, BPCOSRCI.REF_A[1-1].REF_BR);
        CEP.TRC(SCCGWA, BPCOSRCI.REF_A[1-1].REF_CCY);
        CEP.TRC(SCCGWA, BPCOSRCI.REF_A[1-1].REF_TYP);
        CEP.TRC(SCCGWA, BPCOSRCI.REF_A[1-1].REF_TERM);
        BPCOSRCI.REF_A[1-1].REF_TERM = "" + 43;
        JIBS_tmp_int = BPCOSRCI.REF_A[1-1].REF_TERM.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCOSRCI.REF_A[1-1].REF_TERM = "0" + BPCOSRCI.REF_A[1-1].REF_TERM;
        CEP.TRC(SCCGWA, BPCOSRCI.REF_A[1-1].REF_OPT);
        CEP.TRC(SCCGWA, BPCOSRCI.REF_A[1-1].REF_DIFF);
    }
    public void B210_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOSRCI.BR = BPCSSRCI.BR;
        BPCOSRCI.BASE_TYP = BPCSSRCI.BASE_TYP;
        BPCOSRCI.NAME = BPCSSRCI.NAME;
        BPCOSRCI.NAME_FILLER = 0X02;
        for (WS_J = 1; WS_J <= 20; WS_J += 1) {
            BPCOSRCI.TBL.RATE_TBL[WS_J-1].FILLER = 0X01;
        }
        CEP.TRC(SCCGWA, BPCOSRCI);
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RBASE;
        BPCOQPCD.INPUT_DATA.CODE = WS_BASE_TYP;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_STARTBR_BPZSCAN() throws IOException,SQLException,Exception {
        BPCSCCY.OP_FUNC = 'S';
        S000_CALL_BPZSCAN();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_BPZSCAN() throws IOException,SQLException,Exception {
        BPCSCCY.OP_FUNC = 'R';
        S000_CALL_BPZSCAN();
        if (pgmRtn) return;
    }
    public void R000_ENDBR_BPZSCAN() throws IOException,SQLException,Exception {
        BPCSCCY.OP_FUNC = 'E';
        S000_CALL_BPZSCAN();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSCAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BROWSE_CCY, BPCSCCY);
        WS_CCY_FLG = 'N';
        if (BPCSCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCCY.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND)) {
                WS_CCY_FLG = 'Y';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSCCY.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GET_CCY_AND_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = WS_CCY;
        BPRIRPD.KEY.BASE_TYP = BPCSSRCI.BASE_TYP;
        CEP.TRC(SCCGWA, "R000GETCCY");
        R000_STARTBR_BPZRIPDM();
        if (pgmRtn) return;
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y') {
            R000_READNEXT_BPZRIPDM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRIRPD);
            if (BPCRIPDM.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                if (BPRIRPD.CONTRL == 'Y') {
                    R000_SET_CCY_TENOR();
                    if (pgmRtn) return;
                }
            }
        }
        R000_ENDBR_BPZRIPDM();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            BPCOSRCI.CCY = BPCSSRCI.CCY;
            BPCOSRCI.TBL_TENOR[WS_I-1].TENOR = WS_TAB_CNT[WS_I-1].WS_TMP_TENOR;
        }
    }
    public void R000_SET_CCY_TENOR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CGBYWSTENOR");
        CEP.TRC(SCCGWA, WS_TENOR_CNT);
        if (WS_TENOR_CNT == 0) {
            WS_TENOR_CNT = 1;
            WS_TAB_CNT[WS_TENOR_CNT-1].WS_TMP_TENOR = BPRIRPD.KEY.TENOR;
        } else {
            WS_FOUND_FLG = 'Y';
            for (WS_I = 1; WS_I < 20 
                && WS_FOUND_FLG != 'N'; WS_I += 1) {
                if (BPRIRPD.KEY.TENOR.equalsIgnoreCase(WS_TAB_CNT[WS_I-1].WS_TMP_TENOR)) {
                    WS_FOUND_FLG = 'N';
                }
                if (WS_TAB_CNT[WS_I-1].WS_TMP_TENOR.equalsIgnoreCase("0")) {
                    WS_I = 99;
                }
            }
            if (WS_FOUND_FLG == 'Y' 
                && WS_TENOR_CNT < 20) {
                WS_TENOR_CNT += 1;
                WS_TAB_CNT[WS_TENOR_CNT-1].WS_TMP_TENOR = BPRIRPD.KEY.TENOR;
            }
        }
    }
    public void R000_STARTBR_BPZRIPDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'B';
        BPCRIPDM.INFO.OPT_1 = 'S';
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_BPZRIPDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'B';
        BPCRIPDM.INFO.OPT_1 = 'N';
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
    }
    public void R000_ENDBR_BPZRIPDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'B';
        BPCRIPDM.INFO.OPT_1 = 'E';
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRIPDM() throws IOException,SQLException,Exception {
        BPCRIPDM.INFO.POINTER = BPRIRPD;
        IBS.CALLCPN(SCCGWA, CPN_R_BK_MT, BPCRIPDM);
        if (BPCRIPDM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRIPDM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCVALI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_C_VALID_TNRS_INQ, BPCCVALI);
        if (BPCCVALI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCVALI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_C_INTR_INQ, BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_VALUE_NOT_INPUT) 
                || JIBS_tmp_str[2].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_TENOR_COMPAGES_U)) {
                WS_RECORD_FLG = 'Y';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
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
