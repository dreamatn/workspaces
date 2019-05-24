package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTNRI {
    String CPN_C_TNRS_ATIR_INQ = "BP-C-TNRS-ATIR-INQ";
    String CPN_R_INTR_MAINT = "BP-R-INTR-MAINT   ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String CPN_R_IRAT_MAINT = "BP-R-IRAT-MAINT   ";
    String CPN_R_IRPD_MAINT = "BP-R-IRPD-MAINT   ";
    String CPN_R_IDEV_MAINT = "BP-R-IDEV-MAINT   ";
    String K_RBASE = "RBASE";
    String K_OUTPUT_FMT = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRINTM BPCRINTM = new BPCRINTM();
    BPCCTNRI BPCCTNRI = new BPCCTNRI();
    BPCOTNRI BPCOTNRI = new BPCOTNRI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    BPRIRAT BPRIRAT = new BPRIRAT();
    BPCRIATM BPCRIATM = new BPCRIATM();
    BPRIDEV BPRIDEV = new BPRIDEV();
    BPCRDEVM BPCRDEVM = new BPCRDEVM();
    SCCGWA SCCGWA;
    BPCSTNRI BPCSTNRI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTNRI BPCSTNRI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTNRI = BPCSTNRI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSTNRI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOTNRI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_QUERY_PROCESS();
        B300_TRANS_DATA_OUTPUT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSTNRI.BR == ' ' 
            || BPCSTNRI.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            S000_ERR_MSG_PROC();
        } else {
            WS_BR = BPCSTNRI.BR;
            R000_CHECK_BRANCH();
        }
        if (BPCSTNRI.CCY.trim().length() > 0) {
            WS_CCY = BPCSTNRI.CCY;
            R000_CHECK_CCY();
        }
        if (BPCSTNRI.BASE_TYP.trim().length() > 0) {
            WS_BASE_TYP = BPCSTNRI.BASE_TYP;
            R000_CHECK_BASE_TYP();
        }
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCTNRI);
        BPCCTNRI.BR = BPCSTNRI.BR;
        BPCCTNRI.CCY = BPCSTNRI.CCY;
        BPCCTNRI.BASE_TYP = BPCSTNRI.BASE_TYP;
        S000_CALL_BPZCTNRI();
        B210_GET_DETAIL_RATE_PROC();
    }
    public void B210_GET_DETAIL_RATE_PROC() throws IOException,SQLException,Exception {
        BPCOTNRI.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOTNRI.BR = BPCCTNRI.BR;
        BPCOTNRI.CCY = BPCCTNRI.CCY;
        BPCOTNRI.BASE_TYP = BPCCTNRI.BASE_TYP;
        for (WS_I = 1; WS_I <= BPCCTNRI.TENOR_CNT 
            && WS_I <= 50; WS_I += 1) {
            if (BPCCTNRI.BR != SCCGWA.COMM_AREA.HQT_BANK) {
                R000_CHECK_IRAT();
                R000_CHECK_IDEV();
            }
            IBS.init(SCCGWA, BPCRINTM);
            BPCRINTM.FUNC = 'I';
            BPCRINTM.BR = BPCCTNRI.BR;
            BPCRINTM.CCY = BPCCTNRI.CCY;
            BPCRINTM.BASE_TYP = BPCCTNRI.BASE_TYP;
            BPCRINTM.TENOR = BPCCTNRI.TENOR_TBL[WS_I-1].TENOR;
            S000_CALL_BPZRINTM();
            B210_TRANS_DATA_OUPUT();
        }
        for (WS_I = 1; WS_I <= 50; WS_I += 1) {
            BPCOTNRI.TENOR_TBL[WS_I-1].FILLER = 0X01;
            BPCOTNRI.TENOR_TBL[WS_I-1].FILLER2 = 0X01;
        }
    }
    public void B300_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTNRI;
        SCCFMT.DATA_LEN = 3220;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B210_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        WS_J += 1;
        BPCOTNRI.TENOR_TBL[WS_J-1].TENOR = BPCRINTM.TENOR;
        BPCOTNRI.TENOR_TBL[WS_J-1].REF_BR = BPCRINTM.REF_BR;
        BPCOTNRI.TENOR_TBL[WS_J-1].REF_CCY = BPCRINTM.REF_CCY;
        BPCOTNRI.TENOR_TBL[WS_J-1].REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
        BPCOTNRI.TENOR_TBL[WS_J-1].REF_TENOR = BPCRINTM.REF_TENOR;
        BPCOTNRI.TENOR_TBL[WS_J-1].FORMAT = BPCRINTM.FORMAT;
        if (BPCSTNRI.BR != SCCGWA.COMM_AREA.HQT_BANK 
            && BPCOTNRI.TENOR_TBL[WS_J-1].REF_CCY.trim().length() == 0) {
            BPCOTNRI.TENOR_TBL[WS_J-1].FILLER_BR = SCCGWA.COMM_AREA.HQT_BANK;
            BPCOTNRI.TENOR_TBL[WS_J-1].FILLER_CCY = BPCOTNRI.CCY;
            BPCOTNRI.TENOR_TBL[WS_J-1].FILLER_BASETYP = BPCOTNRI.BASE_TYP;
            BPCOTNRI.TENOR_TBL[WS_J-1].FILLER_TENOR = BPCOTNRI.TENOR_TBL[WS_J-1].TENOR;
        }
        if (BPCRINTM.REF_CCY.trim().length() == 0) {
            BPCOTNRI.TENOR_TBL[WS_J-1].RATE = BPCRINTM.RATE;
        } else {
            BPCOTNRI.TENOR_TBL[WS_J-1].RATE = BPCRINTM.DIFF;
        }
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
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
        }
    }
    public void R000_CHECK_IRAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRAT);
        BPRIRAT.KEY.BR = BPCCTNRI.BR;
        BPRIRAT.KEY.CCY = BPCCTNRI.CCY;
        BPRIRAT.KEY.BASE_TYP = BPCCTNRI.BASE_TYP;
        BPRIRAT.KEY.TENOR = BPCCTNRI.TENOR_TBL[WS_I-1].TENOR;
        IBS.init(SCCGWA, BPCRIATM);
        BPCRIATM.INFO.FUNC = 'Q';
        BPCRIATM.INFO.OPT_2 = 'O';
        S000_CALL_BPZRIATM();
        if (BPCRIATM.RETURN_INFO == 'N') {
            BPRIRAT.KEY.BR = SCCGWA.COMM_AREA.HQT_BANK;
            IBS.init(SCCGWA, BPCRIATM);
            BPCRIATM.INFO.FUNC = 'Q';
            BPCRIATM.INFO.OPT_2 = 'O';
            S000_CALL_BPZRIATM();
            if (BPCRIATM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_NO_EXIST_BR_SCOP;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZRIATM() throws IOException,SQLException,Exception {
        BPCRIATM.INFO.POINTER = BPRIRAT;
        IBS.CALLCPN(SCCGWA, CPN_R_IRAT_MAINT, BPCRIATM);
        if (BPCRIATM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRIATM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_IDEV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIDEV);
        BPRIDEV.KEY.TYPE = 'M';
        BPRIDEV.KEY.BR = BPCCTNRI.BR;
        BPRIDEV.KEY.CCY = BPCCTNRI.CCY;
        BPRIDEV.KEY.BASE_TYP = BPCCTNRI.BASE_TYP;
        BPRIDEV.KEY.TENOR = BPCCTNRI.TENOR_TBL[WS_I-1].TENOR;
        IBS.init(SCCGWA, BPCRDEVM);
        BPCRDEVM.INFO.FUNC = 'I';
        S000_CALL_BPZRDEVM();
        if (BPCRDEVM.RETURN_INFO == 'N') {
            BPRIDEV.KEY.BR = 0;
            IBS.init(SCCGWA, BPCRDEVM);
            BPCRDEVM.INFO.FUNC = 'I';
            S000_CALL_BPZRDEVM();
            if (BPCRDEVM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_NO_EXIST_DEV_TAB;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZRDEVM() throws IOException,SQLException,Exception {
        BPCRDEVM.INFO.POINTER = BPRIDEV;
        IBS.CALLCPN(SCCGWA, CPN_R_IDEV_MAINT, BPCRDEVM);
        if (BPCRDEVM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRDEVM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZCTNRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_C_TNRS_ATIR_INQ, BPCCTNRI);
        if (BPCCTNRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCTNRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRINTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_INTR_MAINT, BPCRINTM);
        if (BPCRINTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRINTM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
