package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCVALI {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String CPN_R_BK_MT = "BP-R-IRPD-MAINT";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    short WS_J = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    char WS_TBL_BANK_FLAG = ' ';
    char WS_REF_RECORD_FLG = ' ';
    char WS_RECORD_FLG = ' ';
    char WS_TENOR_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_DUPLICATE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    SCCGWA SCCGWA;
    BPCCVALI BPCCVALI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCCVALI BPCCVALI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCVALI = BPCCVALI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZCVALI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCCVALI.RC);
        BPCCVALI.TNR_CNT = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_GET_TENOR_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCCVALI.BR == ' ' 
            || BPCCVALI.BR == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID, BPCCVALI.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            WS_BR = BPCCVALI.BR;
            R000_CHECK_BRANCH();
            if (pgmRtn) return;
        }
        if (BPCCVALI.CCY.trim().length() > 0) {
            WS_CCY = BPCCVALI.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        if (BPCCVALI.BASE_TYP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_BASETYP_NO_VALID, BPCCVALI.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            WS_BASE_TYP = BPCCVALI.BASE_TYP;
            R000_CHECK_BASE_TYP();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_TENOR_PROC() throws IOException,SQLException,Exception {
        if (BPCCVALI.CCY.trim().length() > 0) {
            B210_GET_TENOR_ONLY_CCY();
            if (pgmRtn) return;
        } else {
            B220_GET_TENOR_ALL_CCY();
            if (pgmRtn) return;
        }
    }
    public void B210_GET_TENOR_ONLY_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = BPCCVALI.CCY;
        BPRIRPD.KEY.BASE_TYP = BPCCVALI.BASE_TYP;
        R000_STARTBR_BPTIRPD();
        if (pgmRtn) return;
        R000_READNEXT_BPTIRPD();
        if (pgmRtn) return;
        if (BPCRIPDM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_TENOR_COMPAGES_U, BPCCVALI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y' 
            && WS_III < 50) {
            if (BPRIRPD.CONTRL == 'Y') {
                WS_III += 1;
                BPCCVALI.TNR_TBL[WS_III-1].TENOR = BPRIRPD.KEY.TENOR;
            }
            R000_READNEXT_BPTIRPD();
            if (pgmRtn) return;
            if (BPCRIPDM.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            }
        }
        BPCCVALI.TNR_CNT = WS_III;
        R000_ENDBR_BPTIRPD();
        if (pgmRtn) return;
    }
    public void B220_GET_TENOR_ALL_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.BASE_TYP = BPCCVALI.BASE_TYP;
        R000_STARTBR_BPTIRPD();
        if (pgmRtn) return;
        R000_READNEXT_BPTIRPD();
        if (pgmRtn) return;
        if (BPCRIPDM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_TENOR_COMPAGES_U, BPCCVALI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y' 
            && WS_III < 50) {
            if (BPRIRPD.CONTRL == 'Y') {
                WS_DUPLICATE_FLG = 'N';
                for (WS_I = 1; WS_I <= WS_III 
                    && WS_DUPLICATE_FLG != 'Y'; WS_I += 1) {
                    if (BPRIRPD.KEY.TENOR.equalsIgnoreCase(BPCCVALI.TNR_TBL[WS_I-1].TENOR)) {
                        WS_DUPLICATE_FLG = 'Y';
                    }
                }
                if (WS_DUPLICATE_FLG == 'N') {
                    WS_III += 1;
                    BPCCVALI.TNR_TBL[WS_III-1].TENOR = BPRIRPD.KEY.TENOR;
                }
            }
            R000_READNEXT_BPTIRPD();
            if (pgmRtn) return;
            if (BPCRIPDM.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            }
        }
        BPCCVALI.TNR_CNT = WS_III;
        R000_ENDBR_BPTIRPD();
        if (pgmRtn) return;
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCVALI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCVALI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RBASE;
        BPCOQPCD.INPUT_DATA.CODE = WS_BASE_TYP;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCVALI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
    }
    public void R000_ENDBR_BPTIRPD() throws IOException,SQLException,Exception {
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
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRIPDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCVALI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCCVALI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCCVALI = ");
            CEP.TRC(SCCGWA, BPCCVALI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
