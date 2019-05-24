package com.hisun.AI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPQGRP {
    DBParm AITGRP_RD;
    String WS_ERR_MSG = " ";
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRGRP AIRGRP = new AIRGRP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    AICPQGRP AICPQGRP;
    public void MP(SCCGWA SCCGWA, AICPQGRP AICPQGRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICPQGRP = AICPQGRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIZPQGRP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQ_GRP_INFO_PROCESS();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (AICPQGRP.INPUT_DATA.BR == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_INQ_GRP_INFO_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRGRP);
        AIRGRP.ISSUING_BRANCH = AICPQGRP.INPUT_DATA.BR;
        AIRGRP.KEY.ISSUING_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_AITGRP_FIRST();
    }
    public void T000_READ_AITGRP_FIRST() throws IOException,SQLException,Exception {
        AITGRP_RD = new DBParm();
        AITGRP_RD.TableName = "AITGRP";
        AITGRP_RD.where = "ISSUING_BRANCH = :AIRGRP.ISSUING_BRANCH "
            + "AND ISSUING_DATE = :AIRGRP.KEY.ISSUING_DATE "
            + "AND ( STS = 'O' "
            + "OR STS = 'C' )";
        AITGRP_RD.fst = true;
        IBS.READ(SCCGWA, AIRGRP, this, AITGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICPQGRP.OUTPUT_DATA.CLOSE = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICPQGRP.OUTPUT_DATA.CLOSE = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGRP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICPQGRP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICPQGRP = ");
            CEP.TRC(SCCGWA, AICPQGRP);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
