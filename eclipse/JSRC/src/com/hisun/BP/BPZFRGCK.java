package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFRGCK {
    String JIBS_tmp_str[] = new String[10];
    String CPN_PARM_CHECK = "BP-PARM-READ        ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_R_ADW_BVRG = "BP-R-ADW-BVRG";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBVRG BPRBVRG = new BPRBVRG();
    BPRBVRG BPRORIS = new BPRBVRG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCTBVRG BPCTBVRG = new BPCTBVRG();
    SCCGWA SCCGWA;
    BPCFRGCK BPCFRGCK;
    public void MP(SCCGWA SCCGWA, BPCFRGCK BPCFRGCK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFRGCK = BPCFRGCK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFRGCK return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_RANGE_CHECK();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCFRGCK.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCFRGCK.BV_CODE.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_RANGE_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCFRGCK.BR;
        S000_CALL_BPZPQORG();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCFRGCK.BR);
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        BPRBVRG.KEY.BV_CODE = BPCFRGCK.BV_CODE;
        BPRBVRG.KEY.BR = BPCPQORG.BRANCH_BR;
        BPCTBVRG.POINTER = BPRBVRG;
        BPCTBVRG.LEN = 300;
        BPCTBVRG.INFO.FUNC = 'Q';
        S000_CALL_BPZRBVRG();
        if (BPCTBVRG.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOT_RANGE;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBVRG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_BVRG, BPCTBVRG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFRGCK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFRGCK = ");
            CEP.TRC(SCCGWA, BPCFRGCK);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
