package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSUHLD {
    DBParm DDTHLD_RD;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    short WS_R = 0;
    String WS_ACO_NO = " ";
    String WS_DDAC_STSW = " ";
    char WS_DDTHLD_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRHLD DDRHLD = new DDRHLD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSUHLD DDCSUHLD;
    public void MP(SCCGWA SCCGWA, DDCSUHLD DDCSUHLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSUHLD = DDCSUHLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSUHLD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DDRHLD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK_PROC();
        B200_INQ_MAIN_PROC();
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSUHLD.KEY.HLD_NO);
        if (DDCSUHLD.KEY.HLD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT);
        }
    }
    public void B200_INQ_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = DDCSUHLD.KEY.HLD_NO;
        T000_READ_DDTHLD();
        if (WS_DDTHLD_FLG == 'Y') {
            DDCSUHLD.CRT_DATE = DDRHLD.CRT_DATE;
            DDCSUHLD.EFF_DATE = DDRHLD.EFF_DATE;
            DDCSUHLD.EXP_DATE = DDRHLD.EXP_DATE;
            DDCSUHLD.KEY.HLD_NO = DDRHLD.KEY.HLD_NO;
        }
        CEP.TRC(SCCGWA, DDCSUHLD.CRT_DATE);
        CEP.TRC(SCCGWA, DDCSUHLD.EFF_DATE);
        CEP.TRC(SCCGWA, DDCSUHLD.EXP_DATE);
        CEP.TRC(SCCGWA, DDCSUHLD.KEY.HLD_NO);
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTHLD_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
