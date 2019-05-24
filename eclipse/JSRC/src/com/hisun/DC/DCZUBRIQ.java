package com.hisun.DC;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUBRIQ {
    DBParm DCTBRARC_RD;
    String WS_ERR_MSG = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRBRARC DCRBRARC = new DCRBRARC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUBRIQ DCCUBRIQ;
    public void MP(SCCGWA SCCGWA, DCCUBRIQ DCCUBRIQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUBRIQ = DCCUBRIQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCZUBRIQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_BR_INFO();
    }
    public void B010_GET_BR_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUBRIQ.BR);
        if (DCCUBRIQ.BR == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT);
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT, DCCUBRIQ.RC);
        }
        IBS.init(SCCGWA, DCRBRARC);
        DCRBRARC.KEY.BR_NO = DCCUBRIQ.BR;
        T000_READ_DCTBRARC();
        if (WS_TBL_FLAG == 'Y') {
            DCCUBRIQ.AREA_NO = DCRBRARC.AREA_NO;
        }
    }
    public void T000_READ_DCTBRARC() throws IOException,SQLException,Exception {
        DCTBRARC_RD = new DBParm();
        DCTBRARC_RD.TableName = "DCTBRARC";
        IBS.READ(SCCGWA, DCRBRARC, DCTBRARC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
