package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSZMQC {
    brParm DDTZMQT_BR = new brParm();
    String WS_ERR_MSG = " ";
    char WS_ZMQT_FLG = ' ';
    int WS_FR_BR = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRZMQT DDRZMQT = new DDRZMQT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSZMQC DDCSZMQC;
    public void MP(SCCGWA SCCGWA, DDCSZMQC DDCSZMQC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSZMQC = DDCSZMQC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSZMQC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT_DATA();
        B200_STARTBR_DDTZMQT();
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSZMQC.FR_BR);
        if (DDCSZMQC.FR_BR == 0) {
            WS_FR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            WS_FR_BR = DDCSZMQC.FR_BR;
        }
    }
    public void B200_STARTBR_DDTZMQT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRZMQT);
        R000_STARTBR_DDTZMQT();
        R000_READNEXT_DDTZMQT();
        while (WS_ZMQT_FLG != 'Y') {
            CEP.TRC(SCCGWA, DDRZMQT.STS);
            if (DDRZMQT.STS == '0') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STS_AFTER_TRANSFERIN);
            }
            if (DDRZMQT.STS == '4') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STS_IN_ERRORBACK);
            }
            CEP.TRC(SCCGWA, DDRZMQT.FRM_AC);
            CEP.TRC(SCCGWA, DDRZMQT.FRM_AC_NM);
            R000_READNEXT_DDTZMQT();
        }
        R000_ENDBR_DDTZMQT();
    }
    public void R000_STARTBR_DDTZMQT() throws IOException,SQLException,Exception {
        DDTZMQT_BR.rp = new DBParm();
        DDTZMQT_BR.rp.TableName = "DDTZMQT";
        DDTZMQT_BR.rp.where = "FRM_BR = :WS_FR_BR";
        IBS.STARTBR(SCCGWA, DDRZMQT, this, DDTZMQT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMQT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void R000_READNEXT_DDTZMQT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRZMQT, this, DDTZMQT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ZMQT_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ZMQT_FLG = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMQT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void R000_ENDBR_DDTZMQT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTZMQT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMQT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "ENDBR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
