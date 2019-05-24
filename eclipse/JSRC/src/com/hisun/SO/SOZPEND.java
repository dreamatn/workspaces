package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class SOZPEND {
    DBParm SOTWAIT_RD;
    boolean pgmRtn = false;
    short WK_REC_LEN = 0;
    int WK_RESP = 0;
    short WK_I = 0;
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SORWAIT SORWAIT = new SORWAIT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCBATH SCCBATH;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_CHK_PROC();
        if (pgmRtn) return;
        C00_MAIN_PROC();
        if (pgmRtn) return;
        D00_OUTP_PROC();
        if (pgmRtn) return;
        E00_GO_BACK();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
    }
    public void B00_CHK_PROC() throws IOException,SQLException,Exception {
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        C01_UPD_END_DATE();
        if (pgmRtn) return;
    }
    public void C01_UPD_END_DATE() throws IOException,SQLException,Exception {
        SORWAIT.KEY.ID = SCCBATH.PARM;
        T01_READ_UPD_WAIT();
        if (pgmRtn) return;
        SORWAIT.END_DATE = SORWAIT.PROC_DATE;
        T05_REWRITE_WAIT();
        if (pgmRtn) return;
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
    }
    public void E00_GO_BACK() throws IOException,SQLException,Exception {
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void T01_READ_UPD_WAIT() throws IOException,SQLException,Exception {
        SORWAIT.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SOTWAIT_RD = new DBParm();
        SOTWAIT_RD.TableName = "SOTWAIT";
        SOTWAIT_RD.upd = true;
        IBS.READ(SCCGWA, SORWAIT, SOTWAIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            pgmRtn = true;
            return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTWAIT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T05_REWRITE_WAIT() throws IOException,SQLException,Exception {
        SOTWAIT_RD = new DBParm();
        SOTWAIT_RD.TableName = "SOTWAIT";
        IBS.REWRITE(SCCGWA, SORWAIT, SOTWAIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTWAIT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
