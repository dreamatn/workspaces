package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZQBAL {
    DBParm DDTCCY_RD;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    DDCQBAL DDCQBAL;
    public void MP(SCCGWA SCCGWA, DDCQBAL DDCQBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCQBAL = DDCQBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZQBAL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDCQBAL.RC.RC_MMO = "DD";
        DDCQBAL.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_GET_DATA();
    }
    public void B200_GET_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCQBAL.DATA.CUS_AC;
        DDRCCY.CCY = DDCQBAL.DATA.CCY;
        DDRCCY.CCY_TYPE = DDCQBAL.DATA.CCY_TYPE;
        T000_READ_DDTCCY();
        DDCQBAL.DATA.AVL_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.HOLD_BAL;
        DDCQBAL.DATA.STS_WORD = DDRCCY.STS_WORD;
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            DDCQBAL.RC.RC_CODE = 1;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCQBAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DDCQBAL.RC.RC_CODE);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
