package com.hisun.FS;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class FSZUVSAT {
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    char K_DD_AC_STS_CLO = 'C';
    String WS_MSG_ID = "      ";
    double WS_VAL_BAL = 0;
    char WS_TBL_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICQACRI CICQACRI = new CICQACRI();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    FSCUVSAT FSCUVSAT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, FSCUVSAT FSCUVSAT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCUVSAT = FSCUVSAT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FSZUVSAT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_TBL_FLAG = 'Y';
        CEP.TRC(SCCGWA, FSCUVSAT.VS_AC);
        CEP.TRC(SCCGWA, FSCUVSAT.CCY);
        CEP.TRC(SCCGWA, FSCUVSAT.CCY_TYP);
        CEP.TRC(SCCGWA, FSCUVSAT.VALUE_DT);
        CEP.TRC(SCCGWA, FSCUVSAT.TNJNL);
        CEP.TRC(SCCGWA, FSCUVSAT.DR_CR_F);
        CEP.TRC(SCCGWA, FSCUVSAT.TRAN_AMT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_VS_INFO();
        B020_CHECK_INFO();
        B030_MOD_DDTCCY();
    }
    public void B010_CHECK_VS_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FSCUVSAT.DR_CR_F);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = FSCUVSAT.VS_AC;
        DDRCCY.CCY = FSCUVSAT.CCY;
        DDRCCY.CCY_TYPE = FSCUVSAT.CCY_TYP;
        T000_READ_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
    }
    public void B020_CHECK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        if (FSCUVSAT.OTH_AC.trim().length() > 0) {
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = FSCUVSAT.OTH_AC;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = FSCUVSAT.VS_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = FSCUVSAT.VS_AC;
        T000_READ_DDTMST();
        if (DDRMST.AC_STS == K_DD_AC_STS_CLO) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (FSCUVSAT.DR_CR_F == 'D') {
            WS_VAL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
        }
    }
    public void B030_MOD_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = FSCUVSAT.VS_AC;
        DDRCCY.CCY = FSCUVSAT.CCY;
        DDRCCY.CCY_TYPE = FSCUVSAT.CCY_TYP;
        T000_READ_DDTCCY_UPDATE();
        if (WS_TBL_FLAG == 'Y') {
            CEP.TRC(SCCGWA, "HAVE DATA, USING UPDATE");
            if (FSCUVSAT.DR_CR_F == 'D') {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    DDRCCY.CURR_BAL -= FSCUVSAT.TRAN_AMT;
                } else {
                    DDRCCY.CURR_BAL += FSCUVSAT.TRAN_AMT;
                }
            } else {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    DDRCCY.CURR_BAL += FSCUVSAT.TRAN_AMT;
                } else {
                    DDRCCY.CURR_BAL -= FSCUVSAT.TRAN_AMT;
                }
            }
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            T000_REWRITE_DDTCCY();
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NOT_FOUND;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NOT_FOUND;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTCCY_UPDATE() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
