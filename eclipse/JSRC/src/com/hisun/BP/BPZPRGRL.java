package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPRGRL {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MAINT_ORG_REL = "BP-R-MAINT-ORG-REL  ";
    int K_MAX_CNT = 50;
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRMORR BPCRMORR = new BPCRMORR();
    BPRORGR BPRORGR = new BPRORGR();
    SCCGWA SCCGWA;
    BPCPRGRL BPCPRGRL;
    public void MP(SCCGWA SCCGWA, BPCPRGRL BPCPRGRL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPRGRL = BPCPRGRL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPRGRL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRGRL.RC);
        if (BPCPRGRL.BNK.equalsIgnoreCase("0")) {
            BPCPRGRL.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B20_GET_RELORG_INFO();
        if (pgmRtn) return;
    }
    public void B10_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NCB040902");
        CEP.TRC(SCCGWA, BPCPRGRL.BR);
        CEP.TRC(SCCGWA, BPCPRGRL.R_TYPE);
        if (BPCPRGRL.BR == 0 
            || BPCPRGRL.R_TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPRGRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B20_GET_RELORG_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGR);
        IBS.init(SCCGWA, BPCRMORR);
        BPRORGR.KEY.BNK = BPCPRGRL.BNK;
        BPRORGR.KEY.BR = BPCPRGRL.BR;
        BPRORGR.KEY.TYP = BPCPRGRL.R_TYPE;
        BPCRMORR.INFO.REC_LEN = 169;
        BPCRMORR.INFO.POINTER = BPRORGR;
        BPCRMORR.INFO.FUNC = 'Q';
        S00_CALL_BPZRMORR();
        if (pgmRtn) return;
        if (BPCRMORR.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORGREL_NOTFND, BPCPRGRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CNT = 0;
        do {
            WS_CNT = WS_CNT + 1;
            BPCPRGRL.R_BR_INFO.R_BR[WS_CNT-1] = BPRORGR.REL_BR;
            IBS.init(SCCGWA, BPRORGR);
            IBS.init(SCCGWA, BPCRMORR);
            BPRORGR.KEY.BNK = BPCPRGRL.BNK;
            BPRORGR.KEY.BR = BPCPRGRL.R_BR_INFO.R_BR[WS_CNT-1];
            BPRORGR.KEY.TYP = BPCPRGRL.R_TYPE;
            BPCRMORR.INFO.FUNC = 'Q';
            BPCRMORR.INFO.POINTER = BPRORGR;
            BPCRMORR.INFO.REC_LEN = 169;
            S00_CALL_BPZRMORR();
            if (pgmRtn) return;
        } while (BPCRMORR.RETURN_INFO == 'F' 
            && WS_CNT < K_MAX_CNT);
    }
    public void S00_CALL_BPZRMORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_ORG_REL, BPCRMORR);
        if (BPCRMORR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMORR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPRGRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPRGRL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPRGRL = ");
            CEP.TRC(SCCGWA, BPCPRGRL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
