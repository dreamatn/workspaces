package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCURI {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_C_INTR_INQ = "BP-C-INTR-INQ       ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String CPN_R_BK_MT = "BP-R-IRPD-MAINT";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_JJ = 0;
    short WS_TENOR_CNT = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    int WS_BASETYP_CNT = 0;
    BPZSCURI_WS_BASETYP_ALL[] WS_BASETYP_ALL = new BPZSCURI_WS_BASETYP_ALL[20];
    int WS_CCY_CNT = 0;
    BPZSCURI_WS_CCY_TWENTY[] WS_CCY_TWENTY = new BPZSCURI_WS_CCY_TWENTY[20];
    BPZSCURI_WS_TAB_CNT[] WS_TAB_CNT = new BPZSCURI_WS_TAB_CNT[20];
    BPZSCURI_WS_RATE_CNT[] WS_RATE_CNT = new BPZSCURI_WS_RATE_CNT[400];
    String WS_RATE_ARR = " ";
    BPZSCURI_WS_QUEUE_PROC WS_QUEUE_PROC = new BPZSCURI_WS_QUEUE_PROC();
    char WS_RECORD_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_INT_TENOR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    BPCQ10 BPCQ10 = new BPCQ10();
    SCCGWA SCCGWA;
    BPCSCURI BPCSCURI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSCURI() {
        for (int i=0;i<20;i++) WS_BASETYP_ALL[i] = new BPZSCURI_WS_BASETYP_ALL();
        for (int i=0;i<20;i++) WS_CCY_TWENTY[i] = new BPZSCURI_WS_CCY_TWENTY();
        for (int i=0;i<20;i++) WS_TAB_CNT[i] = new BPZSCURI_WS_TAB_CNT();
        for (int i=0;i<400;i++) WS_RATE_CNT[i] = new BPZSCURI_WS_RATE_CNT();
    }
    public void MP(SCCGWA SCCGWA, BPCSCURI BPCSCURI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCURI = BPCSCURI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCURI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCQ10);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_QUERY_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSCURI.BR == ' ' 
            || BPCSCURI.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_BR = BPCSCURI.BR;
            R000_CHECK_BRANCH();
            if (pgmRtn) return;
        }
        if (BPCSCURI.CCY.trim().length() > 0) {
            WS_CCY = BPCSCURI.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        if (BPCSCURI.BASE_TYP.trim().length() > 0) {
            WS_BASE_TYP = BPCSCURI.BASE_TYP;
            R000_CHECK_BASE_TYP();
            if (pgmRtn) return;
        }
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        B210_MPAG_HEAD_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = BPCSCURI.CCY;
        BPRIRPD.KEY.BASE_TYP = BPCSCURI.BASE_TYP;
        R000_STARTBR_BPTIRPD();
        if (pgmRtn) return;
        WS_STOP_FLG = 'N';
        R000_READNEXT_BPTIRPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRIRPD);
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, BPRIRPD);
            CEP.TRC(SCCGWA, BPRIRPD.KEY.BASE_TYP);
            CEP.TRC(SCCGWA, BPRIRPD.CONTRL);
            if (BPRIRPD.CONTRL == 'Y') {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.BR = BPCSCURI.BR;
                BPCCINTI.BASE_INFO.BASE_TYP = BPRIRPD.KEY.BASE_TYP;
                BPCCINTI.BASE_INFO.CCY = BPRIRPD.KEY.CCY;
                BPCCINTI.BASE_INFO.TENOR = BPRIRPD.KEY.TENOR;
                WS_INT_TENOR_FLG = 'Y';
                S000_CALL_BPZCINTI();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
                CEP.TRC(SCCGWA, "PPPPPPPP");
                if (WS_INT_TENOR_FLG == 'Y') {
                    IBS.init(SCCGWA, BPCQ10.DETAIL_D);
                    BPCQ10.DETAIL_D.TS_BR = BPCCINTI.BASE_INFO.BR;
                    BPCQ10.DETAIL_D.TS_BASETYP = BPCCINTI.BASE_INFO.BASE_TYP;
                    BPCQ10.DETAIL_D.TS_CCY = BPCCINTI.BASE_INFO.CCY;
                    BPCQ10.DETAIL_D.TS_TENOR = BPCCINTI.BASE_INFO.TENOR;
                    BPCQ10.DETAIL_D.TS_RATE = BPCCINTI.BASE_INFO.RATE;
                    B260_SHOW_RATE_MESS_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, "LOOKHEREYYY");
                CEP.TRC(SCCGWA, BPCQ10.DETAIL_D.TS_BASETYP);
            }
            R000_READNEXT_BPTIRPD();
            if (pgmRtn) return;
        }
        R000_ENDBR_BPTIRPD();
        if (pgmRtn) return;
    }
    public void B210_MPAG_HEAD_PROC() throws IOException,SQLException,Exception {
        WS_OUTPUT_FLG = 'T';
        R000_FMT_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B260_SHOW_RATE_MESS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "HOPEHERE");
        CEP.TRC(SCCGWA, BPCQ10.DETAIL_D);
        WS_OUTPUT_FLG = 'D';
        WS_QUEUE_PROC.WS_RES_MESS = " ";
        WS_QUEUE_PROC.WS_RES_MESS = IBS.CLS2CPY(SCCGWA, BPCQ10.DETAIL_D);
        R000_FMT_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
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
        WS_CCY_TWENTY[1-1].WS_CCY_DETAIL = WS_CCY;
        WS_CCY_CNT = 1;
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
        WS_BASETYP_ALL[1-1].WS_BASETYP_D = WS_BASE_TYP;
        WS_BASETYP_ALL[1-1].WS_BDESC_D = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        WS_BASETYP_CNT = 1;
    }
    public void R000_STARTBR_BPTIRPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'B';
        BPCRIPDM.INFO.OPT_1 = 'S';
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_BPTIRPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'B';
        BPCRIPDM.INFO.OPT_1 = 'N';
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
        if (BPCRIPDM.RETURN_INFO == 'N') {
            WS_STOP_FLG = 'Y';
        }
    }
    public void R000_ENDBR_BPTIRPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'B';
        BPCRIPDM.INFO.OPT_1 = 'E';
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (WS_OUTPUT_FLG == 'T') {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 435;
            SCCMPAG.SCR_ROW_CNT = 20;
            SCCMPAG.SCR_COL_CNT = 200;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            WS_QUEUE_PROC.WS_OUTPUT_DATA = " ";
            WS_QUEUE_PROC.WS_OUTPUT_DATA = WS_QUEUE_PROC.WS_RES_MESS;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = WS_QUEUE_PROC.WS_OUTPUT_DATA;
            SCCMPAG.DATA_LEN = 435;
            B_MPAG();
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
                CEP.TRC(SCCGWA, BPCCINTI.RC);
                WS_INT_TENOR_FLG = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
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
