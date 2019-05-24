package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQIBA {
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_CNT = 0;
    BPZPQIBA_WS_IBAC_KEY WS_IBAC_KEY = new BPZPQIBA_WS_IBAC_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPIBAC BPRPIBAC = new BPRPIBAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQIBA BPCPQIBA;
    public void MP(SCCGWA SCCGWA, BPCPQIBA BPCPQIBA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQIBA = BPCPQIBA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPQIBA return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPQIBA.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        B020_QUERY_IBAC();
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_IBAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPIBAC);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPIBAC.KEY.TYP = "IBAC";
        WS_IBAC_KEY.WS_IBAC_CCY = BPCPQIBA.DATA_INFO.CCY;
        WS_IBAC_KEY.WS_IBAC_SWIFT = BPCPQIBA.DATA_INFO.SWIFT;
        BPRPIBAC.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_IBAC_KEY);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPIBAC;
        S000_CALL_BPZPRMM();
        BPCPQIBA.DATA_INFO.DESC = BPRPIBAC.DESC;
        BPCPQIBA.DATA_INFO.CDSC = BPRPIBAC.CDSC;
        BPCPQIBA.DATA_INFO.IB_AC = BPRPIBAC.DATA_TXT.IB_AC;
        BPCPQIBA.DATA_INFO.AC_NAME = BPRPIBAC.DATA_TXT.AC_NAME;
        BPCPQIBA.DATA_INFO.UPD_DT = BPRPIBAC.DATA_TXT.UPD_DT;
        BPCPQIBA.DATA_INFO.UPD_TLR = BPRPIBAC.DATA_TXT.UPD_TLR;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQIBA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQIBA = ");
            CEP.TRC(SCCGWA, BPCPQIBA);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
