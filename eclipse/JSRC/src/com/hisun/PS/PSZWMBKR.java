package com.hisun.PS;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZWMBKR {
    DBParm PSTPBIN_RD;
    String K_OUTPUT_FMT = "PS001";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PSZWMBKR_WS_FMT WS_FMT = new PSZWMBKR_WS_FMT();
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_WRITE_FLG = ' ';
    char WS_FUNC_FLG = ' ';
    PSRPBIN PSRPBIN = new PSRPBIN();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    String WS_BK_NO = " ";
    String WS_AREA_NO = " ";
    String WS_CCY = " ";
    int WS_TX_BR = 0;
    String WS_EXG_NO = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCWMBKR PSCWMBKR;
    public void MP(SCCGWA SCCGWA, PSCWMBKR PSCWMBKR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCWMBKR = PSCWMBKR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSZWMBKR return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        B10_FUNC_READ();
        if (WS_FOUND_FLG == 'Y') {
            T00_DELETE_PSTPBIN();
            B12_WRITE_PSTPBIN();
        } else {
            B12_WRITE_PSTPBIN();
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        if (PSCWMBKR.EXG_CCY.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CCY_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCWMBKR.EXG_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B10_FUNC_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRPBIN);
        PSRPBIN.KEY.EXG_BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        PSRPBIN.KEY.EXG_AREA_NO = PSCWMBKR.EXG_AREA_NO;
        PSRPBIN.KEY.EXG_CCY = PSCWMBKR.EXG_CCY;
        PSRPBIN.KEY.EXG_NO = PSCWMBKR.EXG_NO;
        T00_READUPDATE_PSTPBIN();
    }
    public void B12_WRITE_PSTPBIN() throws IOException,SQLException,Exception {
        PSRPBIN.KEY.EXG_BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        PSRPBIN.KEY.EXG_AREA_NO = PSCWMBKR.EXG_AREA_NO;
        PSRPBIN.KEY.EXG_CCY = PSCWMBKR.EXG_CCY;
        PSRPBIN.KEY.EXG_NO = PSCWMBKR.EXG_NO;
        PSRPBIN.EXG_EFF_DT = PSCWMBKR.EFF_DT;
        PSRPBIN.EXG_NB = PSCWMBKR.EXG_NB;
        PSRPBIN.PBKA_EX_INSNM = PSCWMBKR.EX_INSNM;
        PSRPBIN.PBKA_CLR_EXN = PSCWMBKR.CLR_EXN;
        PSRPBIN.PBKA_CLR_EXNM = PSCWMBKR.CLR_EXNM;
        PSRPBIN.RMK = PSCWMBKR.RMK;
        PSRPBIN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSRPBIN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        PSRPBIN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSRPBIN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T00_WRITE_PSTPBIN();
    }
    public void T00_WRITE_PSTPBIN() throws IOException,SQLException,Exception {
        PSTPBIN_RD = new DBParm();
        PSTPBIN_RD.TableName = "PSTPBIN";
        IBS.WRITE(SCCGWA, PSRPBIN, PSTPBIN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T00_DELETE_PSTPBIN() throws IOException,SQLException,Exception {
        WS_BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        WS_AREA_NO = PSCWMBKR.EXG_AREA_NO;
        WS_CCY = PSCWMBKR.EXG_CCY;
        WS_EXG_NO = PSCWMBKR.EXG_NO;
        PSTPBIN_RD = new DBParm();
        PSTPBIN_RD.TableName = "PSTPBIN";
        PSTPBIN_RD.where = "EXG_BK_NO = :WS_BK_NO "
            + "AND EXG_AREA_NO = :WS_AREA_NO "
            + "AND EXG_CCY = :WS_CCY "
            + "AND EXG_NO = :WS_EXG_NO";
        IBS.DELETE(SCCGWA, PSRPBIN, this, PSTPBIN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T00_READUPDATE_PSTPBIN() throws IOException,SQLException,Exception {
        PSTPBIN_RD = new DBParm();
        PSTPBIN_RD.TableName = "PSTPBIN";
        PSTPBIN_RD.upd = true;
        IBS.READ(SCCGWA, PSRPBIN, PSTPBIN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            WS_FOUND_FLG = 'N';
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
