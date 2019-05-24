package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFIAMO {
    char K_ERROR = 'E';
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_F_T_FEE_INFO = "BP-F-T-FEE-INFO";
    String CPN_Z_T_MAINTAIN_FAMO = "BP-Z-T-MAINTAIN-FAMO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    double WS_AMT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPVFAMO BPVFAMO = new BPVFAMO();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFAMO BPCTFAMO = new BPCTFAMO();
    BPCFAMO BPCTAMO = new BPCFAMO();
    SCCGWA SCCGWA;
    BPCFIAMO BPCFIAMO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFIAMO BPCFIAMO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFIAMO = BPCFIAMO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFIAMO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCTFAMO);
        IBS.init(SCCGWA, BPCTAMO);
        IBS.init(SCCGWA, BPCTFBAS);
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCFPARM);
        IBS.init(SCCGWA, BPVFAMO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROCESS();
        B020_BROWSE_AMO_PROCESS();
    }
    public void B010_CHECK_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFIAMO);
        if (BPCFIAMO.INPUT_DATA.REF_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REF_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BROWSE_AMO_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTFAMO);
        IBS.init(SCCGWA, BPCTAMO);
        WS_CNT = 0;
        CEP.TRC(SCCGWA, BPCFIAMO.INPUT_DATA.AC_NO);
        CEP.TRC(SCCGWA, BPCFIAMO.INPUT_DATA.REF_NO);
        BPCTAMO.AC_NO = BPCFIAMO.INPUT_DATA.AC_NO;
        BPCTAMO.REF_NO = BPCFIAMO.INPUT_DATA.REF_NO;
        BPCTFAMO.INFO.REC_LEN = 391;
        BPCTFAMO.INFO.FUNC = 'B';
        BPCTFAMO.INFO.OPT = 'S';
        CEP.TRC(SCCGWA, "START BROWSE");
        BPCTFAMO.INFO.POINTER = BPCTAMO;
        S010_CALL_BPZTFAMO();
        while (BPCTFAMO.RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, "READ NEXT");
            BPCTFAMO.INFO.OPT = 'N';
            S010_CALL_BPZTFAMO();
            CEP.TRC(SCCGWA, BPCTAMO.AMO_CODE);
            if (BPCTAMO.AMO_CODE.equalsIgnoreCase("77777")) {
                B030_INQUIRE_FBAS_PROCESS();
                B040_BACK_FLAG_PROCESS();
            }
        }
        BPCTFAMO.INFO.OPT = 'E';
        S010_CALL_BPZTFAMO();
        CEP.TRC(SCCGWA, "END BROWSE");
        CEP.TRC(SCCGWA, BPCFIAMO);
    }
    public void B030_INQUIRE_FBAS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTFBAS);
        IBS.init(SCCGWA, BPRFBAS);
        BPRFBAS.KEY.FEE_CODE = "DDCRF";
        CEP.TRC(SCCGWA, BPRFBAS.KEY.FEE_CODE);
        BPCTFBAS.INFO.REC_LEN = 312;
        BPCTFBAS.INFO.FUNC = 'Q';
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        S020_CALL_BPZTFBAS();
        CEP.TRC(SCCGWA, BPRFBAS);
    }
    public void B040_BACK_FLAG_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPARM);
        IBS.init(SCCGWA, BPVFAMO);
        BPVFAMO.KEY.AMORT_CODE = BPRFBAS.AMO_CODE;
        CEP.TRC(SCCGWA, BPVFAMO.KEY.AMORT_CODE);
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FAMO ";
        BPCFPARM.INFO.POINTER = BPVFAMO;
        S030_CALL_BPZFPARM();
        if (WS_CNT != 20) {
            WS_AMT = BPCTAMO.FEE_AMT - BPCTAMO.AMO_AMT;
            WS_CNT = (short) (WS_CNT + 1);
            BPCFIAMO.AMO_INFO[WS_CNT-1].FEE_CODE = "DDCRF";
            BPCFIAMO.AMO_INFO[WS_CNT-1].AMO_AMT = WS_AMT;
            BPCFIAMO.AMO_INFO[WS_CNT-1].AMOED_AMT = BPCTAMO.AMO_AMT;
            BPCFIAMO.AMO_INFO[WS_CNT-1].DIS_METHOD = '1';
            BPCFIAMO.AMO_INFO[WS_CNT-1].FEE_ACCT = BPCTAMO.SUS_AC;
            BPCFIAMO.AMO_INFO[WS_CNT-1].SUS_ACCT = BPCTAMO.FEE_AC;
        }
    }
    public void S010_CALL_BPZTFAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_Z_T_MAINTAIN_FAMO, BPCTFAMO);
        CEP.TRC(SCCGWA, BPCTFAMO.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCTAMO);
    }
    public void S020_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_T_FEE_INFO, BPCTFBAS);
        CEP.TRC(SCCGWA, BPCTFBAS.RETURN_INFO);
        if (BPCTFBAS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FBAS_KEY_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S030_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_MAINTAIN_PARM, BPCFPARM);
        CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
        if (BPCFPARM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_CODE_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFIAMO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFIAMO = ");
            CEP.TRC(SCCGWA, BPCFIAMO);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
