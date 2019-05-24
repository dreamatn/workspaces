package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCIXSS {
    boolean pgmRtn = false;
    String K_INQUIRE_CCY_NAME = "BP-INQUIRE-CCY      ";
    String K_IRPD_MAINT_NAME = "BP-R-IRPD-MAINT     ";
    String K_OUTPUT_FMT = "BP903";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    BPRPCCY BPRPCCY = new BPRPCCY();
    BPRIRPD BPRIRPD = new BPRIRPD();
    SCCGWA SCCGWA;
    BPCCIXSS BPCCIXSS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCCIXSS BPCCIXSS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCIXSS = BPCCIXSS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZCIXSS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPRIRPD);
        IBS.init(SCCGWA, BPCCIXSS);
        IBS.init(SCCGWA, BPCRIPDM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_STS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCCIXSS.TENOR.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCCIXSS.CCY_TBL[1-1].CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        if (BPCCIXSS.BASE_TYP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_STS() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 100 
            && BPCCIXSS.CCY_TBL[WS_I-1].CCY.trim().length() != 0; WS_I += 1) {
            BPRIRPD.KEY.CCY = BPCCIXSS.CCY_TBL[WS_I-1].CCY;
            BPRIRPD.KEY.BASE_TYP = BPCCIXSS.BASE_TYP;
            BPRIRPD.KEY.TENOR = BPCCIXSS.TENOR;
            BPCRIPDM.INFO.FUNC = 'Q';
            BPCRIPDM.INFO.POINTER = BPRIRPD;
            S000_CALL_BPZRIPDM();
            if (pgmRtn) return;
            BPCCIXSS.CCY_TBL[WS_I-1].STS = BPRIRPD.CONTRL;
        }
        if (WS_I >= 100) {
            BPCCIXSS.CCY_CNT = WS_I;
        } else {
            BPCCIXSS.CCY_CNT = WS_I - 1;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 100 
            && BPCCIXSS.CCY_TBL[WS_I-1].CCY.trim().length() != 0; WS_I += 1) {
            BPCQCCY.DATA.CCY = BPCCIXSS.CCY_TBL[WS_I-1].CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
    }
    public void R000_CHECK_TENOR() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZRIPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_IRPD_MAINT_NAME, BPCRIPDM);
        if (BPCRIPDM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_INQUIRE_CCY_NAME, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            BPCCIXSS.RTN_CODE = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (!BPCCIXSS.RTN_CODE.equalsIgnoreCase("0")) {
            CEP.TRC(SCCGWA, " BPCCIXSS = ");
            CEP.TRC(SCCGWA, BPCCIXSS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
