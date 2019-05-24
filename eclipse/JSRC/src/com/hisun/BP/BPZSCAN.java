package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCAN {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CALL_NAME = "BP-RESOURCE-CCY";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRCCY BPCRCCY = new BPCRCCY();
    SCCGWA SCCGWA;
    BPCSCCY BPCSCCY;
    public void MP(SCCGWA SCCGWA, BPCSCCY BPCSCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCCY = BPCSCCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCAN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCCY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSCCY.OP_FUNC == 'S') {
            B010_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCSCCY.OP_FUNC == 'R') {
            B020_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCSCCY.OP_FUNC == 'E') {
            B030_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERR, BPCSCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        BPCRCCY.DATA.CCY = BPCSCCY.DATA.CCY;
        BPCRCCY.DATA.CCY_CD = BPCSCCY.DATA.CCY_CD;
        BPCRCCY.OP_FUNC = 'S';
        S000_CALL_BPCRCCY();
        if (pgmRtn) return;
    }
    public void B020_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        BPCRCCY.OP_FUNC = 'R';
        S000_CALL_BPCRCCY();
        if (pgmRtn) return;
        if ((BPCRCCY.RC.RTNCODE == 0)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSCCY.DATA);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSCCY.RC);
    }
    public void B030_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        BPCRCCY.OP_FUNC = 'E';
        S000_CALL_BPCRCCY();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPCRCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_NAME, BPCRCCY);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
