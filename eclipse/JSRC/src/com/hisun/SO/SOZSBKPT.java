package com.hisun.SO;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRBKPT;

import java.io.IOException;
import java.sql.SQLException;

public class SOZSBKPT {
    DBParm SCTBKPT_RD;
    SOZSBKPT_WS_VARIABLES WS_VARIABLES = new SOZSBKPT_WS_VARIABLES();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCRBKPT SCRBKPT = new SCRBKPT();
    SCRBKPT_WS_DB_VARS WS_DB_VARS = new SCRBKPT_WS_DB_VARS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SOZSBKPT_WL2 WL2;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "SOZSBKPT return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WL2 = SC_AREA.INP_OUTP_AREA.INP_AREA_PTR;
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        C00_READUPD_BKPT();
        C00_REWRITE_BKPT();
    }
    public void C00_READUPD_BKPT() throws IOException,SQLException,Exception {
        if (WL2.BAT_DATE.trim().length() == 0) SCRBKPT.KEY.AC_DATE = 0;
        else SCRBKPT.KEY.AC_DATE = Integer.parseInt(WL2.BAT_DATE);
        SCRBKPT.KEY.PROC_NAME = WL2.JOB_NAME;
        SCRBKPT.KEY.STEP_NAME = WL2.PGM_NAME;
        SCTBKPT_RD = new DBParm();
        SCTBKPT_RD.TableName = "SCTBKPT";
        SCTBKPT_RD.upd = true;
        SCTBKPT_RD.errhdl = true;
        IBS.READ(SCCGWA, SCRBKPT, SCTBKPT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.MSGID = SOCMSG.SO_BKPT_REC_NO_EXIT;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SCTBKPT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    public void C00_REWRITE_BKPT() throws IOException,SQLException,Exception {
        SCRBKPT.JOB_STS = ' ';
        SCRBKPT.POINT = " ";
        SCTBKPT_RD = new DBParm();
        SCTBKPT_RD.TableName = "SCTBKPT";
        IBS.REWRITE(SCCGWA, SCRBKPT, SCTBKPT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SCTBKPT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
