package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSRCU {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_U_INTR_UPD = "BP-U-INTR-UPD     ";
    String CPN_R_IRPD_MAINT = "BP-R-IRPD-MAINT   ";
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
    short WS_III = 0;
    short WS_J = 0;
    short WS_CNT1 = 0;
    short WS_CNT2 = 0;
    short WS_LEN = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_BASE_NAME = " ";
    String WS_TENOR = " ";
    int WS_TENOR_CNT = 0;
    int WS_CCY_CNT = 0;
    BPZSSRCU_WS_RATE_ARR WS_RATE_ARR = new BPZSSRCU_WS_RATE_ARR();
    BPZSSRCU_WS_DATE WS_DATE = new BPZSSRCU_WS_DATE();
    char WS_TBL_BANK_FLAG = ' ';
    char WS_RECORD_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCUINTU BPCUINTU = new BPCUINTU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    BPCOSRCU BPCOSRCU = new BPCOSRCU();
    SCCGWA SCCGWA;
    BPCSSRCU BPCSSRCU;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSSRCU BPCSSRCU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSRCU = BPCSSRCU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSSRCU return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOSRCU);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_QUERY_PROCESS();
        if (pgmRtn) return;
        B300_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSSRCU.BR == ' ' 
            || BPCSSRCU.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCSSRCU.BR != 999999) {
                WS_BR = BPCSSRCU.BR;
                R000_CHECK_BRANCH();
                if (pgmRtn) return;
            }
        }
        WS_RECORD_FLG = 'N';
        for (WS_I = 1; WS_I <= 20 
            && WS_RECORD_FLG != 'Y'; WS_I += 1) {
            if (BPCSSRCU.TBL_A[WS_I-1].CCY.trim().length() > 0) {
                WS_CCY = BPCSSRCU.TBL_A[WS_I-1].CCY;
                R000_CHECK_CCY();
                if (pgmRtn) return;
                WS_CCY_CNT = WS_I;
            } else {
                WS_RECORD_FLG = 'Y';
            }
        }
        WS_RECORD_FLG = 'N';
        for (WS_I = 1; WS_I <= 20 
            && WS_RECORD_FLG != 'Y'; WS_I += 1) {
            if (BPCSSRCU.TENOR_A[WS_I-1].TENOR.trim().length() > 0) {
                WS_TENOR = BPCSSRCU.TENOR_A[WS_I-1].TENOR;
                R000_CHECK_TENOR();
                if (pgmRtn) return;
                WS_TENOR_CNT = WS_I;
            } else {
                WS_RECORD_FLG = 'Y';
            }
        }
        if (BPCSSRCU.BASE_TYP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_BASE_TYP = BPCSSRCU.BASE_TYP;
            R000_CHECK_BASE_TYP();
            if (pgmRtn) return;
        }
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "2222");
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        for (WS_I = 1; WS_I <= WS_CCY_CNT; WS_I += 1) {
            CEP.TRC(SCCGWA, "3333");
            CEP.TRC(SCCGWA, WS_TENOR_CNT);
            CEP.TRC(SCCGWA, BPCSSRCU.TBL_A[WS_I-1].RATE_A);
            CEP.TRC(SCCGWA, "YWSHELP");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSSRCU.TBL_A[WS_I-1].RATE_A);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_RATE_ARR);
            CEP.TRC(SCCGWA, WS_RATE_ARR);
            for (WS_J = 1; WS_J <= WS_TENOR_CNT; WS_J += 1) {
                IBS.init(SCCGWA, BPCUINTU);
                WS_CCY = BPCSSRCU.TBL_A[WS_I-1].CCY;
                WS_BASE_TYP = BPCSSRCU.BASE_TYP;
                WS_TENOR = BPCSSRCU.TENOR_A[WS_J-1].TENOR;
                R000_READ_BPTIRPD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "4444");
                CEP.TRC(SCCGWA, WS_RECORD_FLG);
                if (BPRIRPD.CONTRL == 'Y' 
                    && WS_RECORD_FLG == 'F') {
                    CEP.TRC(SCCGWA, "5555");
                    BPCUINTU.BR = BPCSSRCU.BR;
                    BPCUINTU.BASE_TYP = BPCSSRCU.BASE_TYP;
                    BPCUINTU.CCY = BPCSSRCU.TBL_A[WS_I-1].CCY;
                    BPCUINTU.TENOR = BPCSSRCU.TENOR_A[WS_J-1].TENOR;
                    BPCUINTU.RATE = WS_RATE_ARR.WS_RATE_AR[WS_J-1].WS_RATE;
                    CEP.TRC(SCCGWA, WS_RATE_ARR.WS_RATE_AR[WS_J-1].WS_RATE);
                    CEP.TRC(SCCGWA, BPCUINTU.RATE);
                    CEP.TRC(SCCGWA, "YWSRATE");
                    BPCUINTU.DT = BPCSSRCU.DT;
                    BPCUINTU.REF_BR = BPCSSRCU.REF_A[WS_J-1].REF_BR;
                    BPCUINTU.REF_CCY = BPCSSRCU.REF_A[WS_J-1].REF_CCY;
                    BPCUINTU.REF_BASE_TYP = BPCSSRCU.REF_A[WS_J-1].REF_TYP;
                    BPCUINTU.REF_TENOR = BPCSSRCU.REF_A[WS_J-1].REF_TERM;
                    BPCUINTU.FORMAT = BPCSSRCU.REF_A[WS_J-1].REF_OPT;
                    BPCUINTU.DIFF = BPCSSRCU.REF_A[WS_J-1].REF_DIFF;
                    CEP.TRC(SCCGWA, BPCUINTU.REF_BR);
                    CEP.TRC(SCCGWA, BPCUINTU.REF_CCY);
                    CEP.TRC(SCCGWA, BPCUINTU.REF_BASE_TYP);
                    CEP.TRC(SCCGWA, BPCUINTU.REF_TENOR);
                    CEP.TRC(SCCGWA, BPCUINTU.FORMAT);
                    CEP.TRC(SCCGWA, BPCUINTU.DIFF);
                    S000_CALL_BPZUINTU();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, WS_RATE_ARR);
        B210_OUTPUT_TS_BASIC();
        if (pgmRtn) return;
    }
    public void B210_OUTPUT_TS_BASIC() throws IOException,SQLException,Exception {
        BPCOSRCU.DT = BPCSSRCU.DT;
        BPCOSRCU.BR = BPCSSRCU.BR;
        BPCOSRCU.BASE_TYP = BPCSSRCU.BASE_TYP;
        BPCOSRCU.NAME = WS_BASE_NAME;
        BPCOSRCU.NAME_FILLER = 0X02;
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            BPCOSRCU.TBL_TENOR[WS_I-1].TENOR = BPCSSRCU.TENOR_A[WS_I-1].TENOR;
        }
        for (WS_I = 1; WS_I <= 10 
            && !BPCSSRCU.TENOR_A[WS_I-1].TENOR.equalsIgnoreCase("0") 
            && BPCSSRCU.TENOR_A[WS_I-1].TENOR.trim().length() != 0; WS_I += 1) {
            BPCOSRCU.TBL_LINE[WS_I-1].LINE = "---";
        }
        BPCOSRCU.TBL.CCY = BPCSSRCU.TBL_A[1-1].CCY;
        for (WS_J = 1; WS_J <= 20; WS_J += 1) {
            BPCOSRCU.TBL.RATE_TBL[WS_J-1].RATE = BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[WS_J-1].RATE;
            BPCOSRCU.TBL.RATE_TBL[WS_J-1].FILLER = 0X01;
        }
        BPCOSRCU.CNT = 1;
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            CEP.TRC(SCCGWA, "1111111");
            BPCOSRCU.REF_A[WS_I-1].REF_BR = BPCSSRCU.REF_A[WS_I-1].REF_BR;
            BPCOSRCU.REF_A[WS_I-1].REF_CCY = BPCSSRCU.REF_A[WS_I-1].REF_CCY;
            BPCOSRCU.REF_A[WS_I-1].REF_TYP = BPCSSRCU.REF_A[WS_I-1].REF_TYP;
            BPCOSRCU.REF_A[WS_I-1].REF_TERM = BPCSSRCU.REF_A[WS_I-1].REF_TERM;
            BPCOSRCU.REF_A[WS_I-1].REF_OPT = BPCSSRCU.REF_A[WS_I-1].REF_OPT;
            BPCOSRCU.REF_A[WS_I-1].REF_DIFF = BPCSSRCU.REF_A[WS_I-1].REF_DIFF;
        }
    }
    public void B300_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOSRCU;
        SCCFMT.DATA_LEN = 3403;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "READBPTIRPD1");
    }
    public void R000_READ_BPTIRPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = WS_CCY;
        BPRIRPD.KEY.BASE_TYP = WS_BASE_TYP;
        BPRIRPD.KEY.TENOR = WS_TENOR;
        CEP.TRC(SCCGWA, WS_BASE_TYP);
        CEP.TRC(SCCGWA, WS_TENOR);
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'Q';
        BPCRIPDM.INFO.OPT_2 = 'O';
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
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
        } else {
            WS_BASE_NAME = " ";
            WS_BASE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        }
    }
    public void R000_CHECK_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RTENO;
        BPCOQPCD.INPUT_DATA.CODE = WS_TENOR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRIPDM() throws IOException,SQLException,Exception {
        BPCRIPDM.INFO.POINTER = BPRIRPD;
        WS_RECORD_FLG = 'F';
        IBS.CALLCPN(SCCGWA, CPN_R_BK_MT, BPCRIPDM);
        if (BPCRIPDM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRIPDM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCRIPDM.RETURN_INFO == 'N') {
            WS_RECORD_FLG = 'T';
        }
    }
    public void S000_CALL_BPZUINTU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_INTR_UPD, BPCUINTU);
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
