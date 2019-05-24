package com.hisun.PS;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZWMBKP {
    DBParm PSTEINF_RD;
    String K_OUTPUT_FMT = "PS001";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PSZWMBKP_WS_FMT WS_FMT = new PSZWMBKP_WS_FMT();
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_WRITE_FLG = ' ';
    char WS_FUNC_FLG = ' ';
    PSREINF PSREINF = new PSREINF();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    String WS_BK_NO = " ";
    String WS_AREA_NO = " ";
    String WS_CCY = " ";
    int WS_TX_BR = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCWMBKP PSCWMBKP;
    public void MP(SCCGWA SCCGWA, PSCWMBKP PSCWMBKP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCWMBKP = PSCWMBKP;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSZWMBKP return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        CEP.TRC(SCCGWA, PSCWMBKP.FUNC_CD);
        if (PSCWMBKP.FUNC_CD == 'A') {
            B02_FUNC_WRITE();
        } else if (PSCWMBKP.FUNC_CD == 'D') {
            B03_FUNC_DELETE();
        } else if (PSCWMBKP.FUNC_CD == 'M') {
            B04_FUNC_REWRITE();
        } else if (PSCWMBKP.FUNC_CD == 'I') {
            B05_FUNC_READ();
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_FUNC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        if (PSCWMBKP.FUNC_CD == ' ') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_FUNC_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCWMBKP.EXG_CCY.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CCY_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCWMBKP.EXG_TX_BR == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TX_BR_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCWMBKP.FUNC_CD == 'A') {
            if (PSCWMBKP.EXG_INSNM.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BR_NAME_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (PSCWMBKP.EXG_NO.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (PSCWMBKP.EXG_DT == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BTMS_DT_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (PSCWMBKP.EXG_TMS == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BTMS_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (PSCWMBKP.EXG_PRE_DT == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_PRE_DT_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (PSCWMBKP.EXG_PRE_TMS == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_PRE_TMS_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (PSCWMBKP.EXG_BOOK_BR == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BOOK_BR_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (PSCWMBKP.EXG_CLR_BR == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CLR_BR_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (PSCWMBKP.EXG_JOIN_TMS == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_JOIN_TMS_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (PSCWMBKP.EXG_CLR_AC.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CLR_AC_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (PSCWMBKP.EXG_YEND_TMS == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_YEND_TMS_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (PSCWMBKP.RMK.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_RMK_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        WS_WRITE_FLG = 'Y';
        B10_READ();
        if (WS_FOUND_FLG == 'Y') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_EINF_EXIST;
            S000_ERR_MSG_PROC();
        }
        R00_COMA_RECA();
        T00_WRITE_PSTEINF();
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        B10_READ();
        if (WS_FOUND_FLG == 'Y') {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_EINF_EXIST;
            S000_ERR_MSG_PROC();
        }
        WS_BK_NO = PSCWMBKP.BK_NO;
        WS_AREA_NO = PSCWMBKP.EXG_AREA_NO;
        WS_CCY = PSCWMBKP.EXG_CCY;
        WS_TX_BR = PSCWMBKP.EXG_TX_BR;
        T00_DELETE_PSTEINF();
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = 'Y';
        B10_READ();
        if (WS_FOUND_FLG == 'Y') {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_EINF_EXIST;
            S000_ERR_MSG_PROC();
        }
        R00_COMA_RECA();
        T00_REWRITE_PSTEINF();
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (WS_FOUND_FLG != 'Y') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_EINF_EXIST;
            S000_ERR_MSG_PROC();
        }
        B20_OUTPUT_PROC();
    }
    public void B10_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCWMBKP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCWMBKP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCWMBKP.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = PSCWMBKP.EXG_TX_BR;
        CEP.TRC(SCCGWA, PSCWMBKP.BK_NO);
        CEP.TRC(SCCGWA, PSCWMBKP.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSCWMBKP.EXG_CCY);
        CEP.TRC(SCCGWA, PSCWMBKP.EXG_TX_BR);
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_PSTEINF();
        } else {
            T00_READ_PSTEINF();
        }
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCWMBKP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCWMBKP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCWMBKP.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = PSCWMBKP.EXG_TX_BR;
        PSREINF.EXG_INSNM = PSCWMBKP.EXG_INSNM;
        PSREINF.EXG_SYS_STS = PSCWMBKP.EXG_SYS_STS;
        PSREINF.EXG_NO = PSCWMBKP.EXG_NO;
        PSREINF.EXG_DT = PSCWMBKP.EXG_DT;
        PSREINF.EXG_TMS = PSCWMBKP.EXG_TMS;
        PSREINF.EXG_PRE_DT = PSCWMBKP.EXG_PRE_DT;
        PSREINF.EXG_PRE_TMS = PSCWMBKP.EXG_PRE_TMS;
        PSREINF.EXG_BOOK_BR = PSCWMBKP.EXG_BOOK_BR;
        PSREINF.EXG_CLR_BR = PSCWMBKP.EXG_CLR_BR;
        PSREINF.EXG_JOIN_TMS = PSCWMBKP.EXG_JOIN_TMS;
        PSREINF.EXG_CLR_AC = PSCWMBKP.EXG_CLR_AC;
        PSREINF.EXG_YEND_TMS = PSCWMBKP.EXG_YEND_TMS;
        PSREINF.RMK = PSCWMBKP.RMK;
        PSREINF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSREINF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        PSREINF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSREINF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B20_OUTPUT_PROC() throws IOException,SQLException,Exception {
        WS_FMT.WS_FUNC_CD = PSCWMBKP.FUNC_CD;
        WS_FMT.WS_EXG_AREA_NO = PSREINF.KEY.EXG_AREA_NO;
        WS_FMT.WS_EXG_CCY = PSREINF.KEY.EXG_CCY;
        WS_FMT.WS_EXG_TX_BR = PSREINF.KEY.EXG_TX_BR;
        WS_FMT.WS_EXG_INSNM = PSREINF.EXG_INSNM;
        WS_FMT.WS_EXG_SYS_STS = PSREINF.EXG_SYS_STS;
        CEP.TRC(SCCGWA, PSREINF.EXG_SYS_STS);
        WS_FMT.WS_EXG_NO = PSREINF.EXG_NO;
        WS_FMT.WS_EXG_DT = PSREINF.EXG_DT;
        WS_FMT.WS_EXG_TMS = PSREINF.EXG_TMS;
        WS_FMT.WS_EXG_PRE_DT = PSREINF.EXG_PRE_DT;
        WS_FMT.WS_EXG_PRE_TMS = PSREINF.EXG_PRE_TMS;
        WS_FMT.WS_EXG_BOOK_BR = PSREINF.EXG_BOOK_BR;
        WS_FMT.WS_EXG_CLR_BR = PSREINF.EXG_CLR_BR;
        WS_FMT.WS_EXG_JOIN_TMS = PSREINF.EXG_JOIN_TMS;
        WS_FMT.WS_EXG_CLR_AC = PSREINF.EXG_CLR_AC;
        WS_FMT.WS_EXG_YEND_TMS = PSREINF.EXG_YEND_TMS;
        WS_FMT.WS_RMK = PSREINF.RMK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 363;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T00_WRITE_PSTEINF() throws IOException,SQLException,Exception {
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.WRITE(SCCGWA, PSREINF, PSTEINF_RD);
    }
    public void T00_DELETE_PSTEINF() throws IOException,SQLException,Exception {
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        PSTEINF_RD.where = "EXG_BK_NO = :WS_BK_NO "
            + "AND EXG_AREA_NO = :WS_AREA_NO "
            + "AND EXG_CCY = :WS_CCY "
            + "AND EXG_TX_BR = :WS_TX_BR";
        IBS.DELETE(SCCGWA, PSREINF, this, PSTEINF_RD);
    }
    public void T00_READ_PSTEINF() throws IOException,SQLException,Exception {
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
        WS_FOUND_FLG = ' ';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "DU DAO");
            if (WS_WRITE_FLG == 'Y') {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_EINF_EXIST;
                S000_ERR_MSG_PROC();
            } else {
                WS_FOUND_FLG = 'Y';
            }
        } else {
            CEP.TRC(SCCGWA, "MEI DU DAO");
            if (PSCWMBKP.FUNC_CD == 'A') {
            } else {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_EINF_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void T00_READUPDATE_PSTEINF() throws IOException,SQLException,Exception {
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        PSTEINF_RD.upd = true;
        IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_PSTEINF() throws IOException,SQLException,Exception {
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.REWRITE(SCCGWA, PSREINF, PSTEINF_RD);
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
