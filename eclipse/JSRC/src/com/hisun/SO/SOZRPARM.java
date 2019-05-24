package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

import java.io.IOException;
import java.sql.SQLException;

public class SOZRPARM {
    DBParm SCTCWA_RD;
    char SOZRPARM_FILLER1 = ' ';
    SCRCWA SCRCWA = new SCRCWA();
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWAT;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "SOZRPARM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCRCWAT = (SCRCWAT) SCRCWA;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B300_DIS_PARM();
    }
    public void B300_DIS_PARM() throws IOException,SQLException,Exception {
        SCRCWA.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SCTCWA_RD = new DBParm();
        SCTCWA_RD.TableName = "SCTCWA";
        SCTCWA_RD.upd = true;
        IBS.READ(SCCGWA, SCRCWA, SCTCWA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SCTCWA";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
        SCRCWAT.PARM_IND = 'D';
        SCTCWA_RD = new DBParm();
        SCTCWA_RD.TableName = "SCTCWA";
        IBS.REWRITE(SCCGWA, SCRCWA, SCTCWA_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
