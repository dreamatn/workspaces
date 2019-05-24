package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTNRU {
    String CPN_U_INTR_UPD = "BP-U-INTR-UPD     ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_OUTPUT_FMT = "BP303";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_BASE_NAME = " ";
    char WS_TBL_BANK_FLAG = ' ';
    char WS_RECORD_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUINTU BPCUINTU = new BPCUINTU();
    BPCOTNRU BPCOTNRU = new BPCOTNRU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPCSTNRU BPCSTNRU;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTNRU BPCSTNRU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTNRU = BPCSTNRU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSTNRU return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOTNRU);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_QUERY_PROCESS();
        B300_TRANS_DATA_OUTPUT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSTNRU.BR == ' ' 
            || BPCSTNRU.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            S000_ERR_MSG_PROC();
        } else {
            WS_BR = BPCSTNRU.BR;
            R000_CHECK_BRANCH();
        }
        if (BPCSTNRU.CCY.trim().length() > 0) {
            WS_CCY = BPCSTNRU.CCY;
            R000_CHECK_CCY();
        }
        if (BPCSTNRU.BASE_TYP.trim().length() > 0) {
            WS_BASE_TYP = BPCSTNRU.BASE_TYP;
            R000_CHECK_BASE_TYP();
        }
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        WS_RECORD_FLG = 'Y';
        BPCOTNRU.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOTNRU.BR = BPCSTNRU.BR;
        BPCOTNRU.CCY = BPCSTNRU.CCY;
        BPCOTNRU.BASE_TYP = BPCSTNRU.BASE_TYP;
        BPCOTNRU.BASE_NAME = WS_BASE_NAME;
        for (WS_I = 1; WS_I <= 50 
            && WS_RECORD_FLG != 'N'; WS_I += 1) {
            if (BPCSTNRU.INTR_TBL[WS_I-1].TENOR.equalsIgnoreCase("0")) {
                WS_RECORD_FLG = 'N';
            } else {
                IBS.init(SCCGWA, BPCUINTU);
                BPCUINTU.BR = BPCSTNRU.BR;
                BPCUINTU.CCY = BPCSTNRU.CCY;
                BPCUINTU.BASE_TYP = BPCSTNRU.BASE_TYP;
                BPCUINTU.TENOR = BPCSTNRU.INTR_TBL[WS_I-1].TENOR;
                BPCUINTU.REF_BR = BPCSTNRU.INTR_TBL[WS_I-1].REF_BR;
                BPCUINTU.REF_CCY = BPCSTNRU.INTR_TBL[WS_I-1].REF_CCY;
                BPCUINTU.REF_BASE_TYP = BPCSTNRU.INTR_TBL[WS_I-1].REF_BASE_TYP;
                BPCUINTU.REF_TENOR = BPCSTNRU.INTR_TBL[WS_I-1].REF_TENOR;
                BPCUINTU.FORMAT = BPCSTNRU.INTR_TBL[WS_I-1].REF_FORMAT;
                BPCUINTU.DIFF = BPCSTNRU.INTR_TBL[WS_I-1].REF_DIFF;
                BPCUINTU.RATE = BPCSTNRU.INTR_TBL[WS_I-1].INT_RATE;
                S000_CALL_BPZUINTU();
                B210_TRANS_DATA_OUPUT();
            }
        }
    }
    public void B300_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTNRU;
        SCCFMT.DATA_LEN = 1786;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B210_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOTNRU.TENOR_TBL[WS_I-1].TENOR = BPCUINTU.TENOR;
        BPCOTNRU.TENOR_TBL[WS_I-1].REF_BR = BPCUINTU.REF_BR;
        BPCOTNRU.TENOR_TBL[WS_I-1].REF_CCY = BPCUINTU.REF_CCY;
        BPCOTNRU.TENOR_TBL[WS_I-1].REF_BASE_TYP = BPCUINTU.REF_BASE_TYP;
        BPCOTNRU.TENOR_TBL[WS_I-1].REF_TENOR = BPCUINTU.REF_TENOR;
        BPCOTNRU.TENOR_TBL[WS_I-1].FORMAT = BPCUINTU.FORMAT;
        if (BPCUINTU.REF_CCY.trim().length() == 0) {
            BPCOTNRU.TENOR_TBL[WS_I-1].RATE = BPCUINTU.RATE;
        } else {
            BPCOTNRU.TENOR_TBL[WS_I-1].RATE = BPCUINTU.DIFF;
        }
        BPCOTNRU.TENOR_TBL[WS_I-1].FILLER = 0X01;
        BPCOTNRU.CNT = WS_I;
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
        } else {
            WS_BASE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        }
    }
    public void S000_CALL_BPZUINTU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_INTR_UPD, BPCUINTU);
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
