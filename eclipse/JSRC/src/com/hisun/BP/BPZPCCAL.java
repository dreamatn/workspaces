package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCCAL {
    boolean pgmRtn = false;
    String CPN_CALN_READ = "BP-R-BRW-CALN       ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRCALN BPCRCALN = new BPCRCALN();
    BPRCALN BPRCALN = new BPRCALN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCOCCAL BPCOCCAL;
    public void MP(SCCGWA SCCGWA, BPCOCCAL BPCOCCAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOCCAL = BPCOCCAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCCAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOCCAL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_PUBLIC_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCOCCAL.CAL_CODE.compareTo(SPACE) <= 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOCCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_PUBLIC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCALN);
        IBS.init(SCCGWA, BPCRCALN);
        BPCRCALN.INFO.FUNC = 'R';
        BPRCALN.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRCALN.KEY.CODE = BPCOCCAL.CAL_CODE;
        CEP.TRC(SCCGWA, BPCOCCAL.CAL_CODE);
        BPCRCALN.INFO.POINTER = BPRCALN;
        BPCRCALN.INFO.LEN = 173;
        S000_CALL_BPZRCALN();
        if (pgmRtn) return;
        BPCOCCAL.CAL_NAME = BPRCALN.CDESC;
    }
    public void S000_CALL_BPZRCALN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALN_READ, BPCRCALN);
        if (BPCRCALN.RETURN_INFO == 'N') {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOCCAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOCCAL = ");
            CEP.TRC(SCCGWA, BPCOCCAL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
