package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCTNRI {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_C_VALID_TNRS_INQ = "BP-C-VALID-TNRS-INQ";
    String CPN_C_INTR_INQ = "BP-C-INTR-INQ      ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    char WS_RECORD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCCVALI BPCCVALI = new BPCCVALI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPCCTNRI BPCCTNRI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCCTNRI BPCCTNRI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCTNRI = BPCCTNRI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZCTNRI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCCTNRI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_GET_TENOR();
        if (pgmRtn) return;
        B300_GET_RATE();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCCTNRI.BR == ' ' 
            || BPCCTNRI.BR == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID, BPCCTNRI.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            WS_BR = BPCCTNRI.BR;
            R000_CHECK_BRANCH();
            if (pgmRtn) return;
        }
        WS_CCY = BPCCTNRI.CCY;
        R000_CHECK_CCY();
        if (pgmRtn) return;
        WS_BASE_TYP = BPCCTNRI.BASE_TYP;
        R000_CHECK_BASE_TYP();
        if (pgmRtn) return;
    }
    public void B200_GET_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCVALI);
        BPCCVALI.BR = BPCCTNRI.BR;
        BPCCVALI.CCY = BPCCTNRI.CCY;
        BPCCVALI.BASE_TYP = BPCCTNRI.BASE_TYP;
        R000_CALL_BPZCVALI();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 50 
            && WS_I <= BPCCVALI.TNR_CNT; WS_I += 1) {
            BPCCTNRI.TENOR_TBL[WS_I-1].TENOR = BPCCVALI.TNR_TBL[WS_I-1].TENOR;
        }
        BPCCTNRI.TENOR_CNT = WS_I;
        BPCCTNRI.TENOR_CNT -= 1;
    }
    public void B300_GET_RATE() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 50 
            && WS_I <= BPCCVALI.TNR_CNT; WS_I += 1) {
            IBS.init(SCCGWA, BPCCINTI);
            BPCCINTI.FUNC = 'I';
            BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCCINTI.BASE_INFO.BR = BPCCTNRI.BR;
            BPCCINTI.BASE_INFO.CCY = BPCCTNRI.CCY;
            BPCCINTI.BASE_INFO.BASE_TYP = BPCCTNRI.BASE_TYP;
            BPCCINTI.BASE_INFO.TENOR = BPCCTNRI.TENOR_TBL[WS_I-1].TENOR;
            WS_RECORD_FLAG = 'N';
            R000_CALL_BPZCINTI();
            if (pgmRtn) return;
            if (WS_RECORD_FLAG == 'N') {
                BPCCTNRI.TENOR_TBL[WS_I-1].RATE = BPCCINTI.BASE_INFO.RATE;
            }
        }
    }
    public void R000_CALL_BPZCVALI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_C_VALID_TNRS_INQ, BPCCVALI);
        if (BPCCVALI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCVALI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCTNRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_C_INTR_INQ, BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_TENOR_COMPAGES_U)) {
                WS_RECORD_FLAG = 'D';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCTNRI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCTNRI.RC);
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
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCTNRI.RC);
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
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCTNRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCCTNRI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCCTNRI = ");
            CEP.TRC(SCCGWA, BPCCTNRI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
