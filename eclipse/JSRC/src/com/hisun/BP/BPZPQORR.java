package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQORR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String R_MAINT_ORG_REL = "BP-R-MAINT-ORG-REL  ";
    BPZPQORR_WS_REMARK WS_REMARK = new BPZPQORR_WS_REMARK();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRMORR BPCRMORR = new BPCRMORR();
    BPRORGR BPRORGR = new BPRORGR();
    SCCGWA SCCGWA;
    BPCPQORR BPCPQORR;
    public void MP(SCCGWA SCCGWA, BPCPQORR BPCPQORR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQORR = BPCPQORR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQORR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORR.RC);
        CEP.TRC(SCCGWA, BPCPQORR.BNK);
        if (BPCPQORR.BNK.equalsIgnoreCase("0") 
            || BPCPQORR.BNK.trim().length() == 0) {
            BPCPQORR.BNK = SCCGWA.COMM_AREA.TR_BANK;
            CEP.TRC(SCCGWA, BPCPQORR.BNK);
        }
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B20_GET_ORGR_INFO();
        if (pgmRtn) return;
        B30_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B10_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPQORR.BR == 0 
            || BPCPQORR.TYP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_INPUT_ERROR, BPCPQORR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B20_GET_ORGR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGR);
        IBS.init(SCCGWA, BPCRMORR);
        BPRORGR.KEY.BNK = BPCPQORR.BNK;
        BPRORGR.KEY.TYP = BPCPQORR.TYP;
        CEP.TRC(SCCGWA, BPCPQORR.CHECK_IND);
        if (BPCPQORR.CHECK_IND == '1') {
            BPRORGR.REL_BR = BPCPQORR.BR;
        } else {
            BPRORGR.KEY.BR = BPCPQORR.BR;
        }
        CEP.TRC(SCCGWA, "NCB0531002");
        CEP.TRC(SCCGWA, BPRORGR.REL_BR);
        CEP.TRC(SCCGWA, BPRORGR.KEY.BNK);
        CEP.TRC(SCCGWA, BPRORGR.KEY.TYP);
        CEP.TRC(SCCGWA, BPRORGR.KEY.BR);
        BPCRMORR.INFO.POINTER = BPRORGR;
        BPCRMORR.INFO.REC_LEN = 169;
        BPCRMORR.INFO.FUNC = 'Q';
        BPCRMORR.INFO.CHECK_IND = BPCPQORR.CHECK_IND;
        S00_CALL_BPZRMORR();
        if (pgmRtn) return;
        if (BPCRMORR.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "DEV");
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_ORGREL_NOTFND, BPCPQORR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B30_OUTPUT_INFO() throws IOException,SQLException,Exception {
        BPCPQORR.TYP = BPRORGR.KEY.TYP;
        if (BPCPQORR.CHECK_IND == '1') {
            BPCPQORR.REL_BR = BPRORGR.KEY.BR;
            BPCPQORR.BR = BPRORGR.REL_BR;
        } else {
            BPCPQORR.BR = BPRORGR.KEY.BR;
            BPCPQORR.REL_BR = BPRORGR.REL_BR;
        }
    }
    CEP.TRC(SCCGWA, BPCPQORR.BR);
    CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
    CEP.TRC(SCCGWA, BPCPQORR.BNK);
    BPCPQORR.EFF_DT = BPRORGR.EFF_DT;
    BPCPQORR.EXP_DT = BPRORGR.EXP_DT;
    BPCPQORR.UPT_DT = BPRORGR.UPT_DT;
    BPCPQORR.UPT_TLR = BPRORGR.UPT_TLR;
    IBS.CPY2CLS(SCCGWA, BPRORGR.REMARK, WS_REMARK);
    BPCPQORR.ONWAY_DAY = WS_REMARK.ONWAY_DAY;
    public void S00_CALL_BPZRMORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, R_MAINT_ORG_REL, BPCRMORR);
        if (BPCRMORR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMORR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQORR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQORR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQORR = ");
            CEP.TRC(SCCGWA, BPCPQORR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
