package com.hisun.DC;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUUORD {
    DBParm DCTCDDAT_RD;
    DBParm DCTCDORD_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC063";
    String WS_ERR_MSG = " ";
    String WS_CARD_NO = " ";
    char WS_CARD_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUUORD DCCUUORD;
    public void MP(SCCGWA SCCGWA, DCCUUORD DCCUUORD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUUORD = DCCUUORD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUUORD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_CARD_FLG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CARD_PROCESS();
        if (pgmRtn) return;
        B030_UPDATE_DDTDORD();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUUORD.CARD_NO);
        if (DCCUUORD.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCFCDGG);
        DCCFCDGG.VAL.FUNC = 'G';
        DCCFCDGG.VAL.CARD_NO = DCCUUORD.CARD_NO;
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
        S000_CALL_DCZFCDGG();
        if (pgmRtn) return;
        WS_CARD_NO = DCCFCDGG.VAL.CARD_NO_GEN;
    }
    public void B020_CARD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = WS_CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            CEP.TRC(SCCGWA, "找到�?");
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (DCRCDDAT.CARD_STS != '1') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_STS_MUST_MADE;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
            } else {
                if (DCRCDDAT.CARD_STS != '3') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_STS_MUST_UNTAKE;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
            }
        }
        if (WS_TBL_FLAG == 'N') {
            CEP.TRC(SCCGWA, DCCUUORD.CARD_NO);
            CEP.TRC(SCCGWA, "未找�?");
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DCRCDDAT.CARD_STS = '3';
        } else {
            DCRCDDAT.CARD_STS = '1';
        }
        DCRCDDAT.LAST_TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.ISSU_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.CLT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_DDTDORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = WS_CARD_NO;
        DCRCDORD.KEY.EXC_CARD_TMS = DCRCDDAT.EXC_CARD_TMS;
        DCRCDORD.CRT_STS = '2';
        T000_READUPD_DCTCDORD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (DCRCDORD.CRT_STS != '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_STS_MUST_MADE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DCRCDORD.CRT_STS != '2') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_STS_MUST_UNTAKE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DCRCDORD.CRT_STS = '2';
        } else {
            DCRCDORD.CRT_STS = '1';
        }
        DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DCRCDORD.UPDTBL_DATE);
        T000_UPDATE_DCTCDORD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AAA");
        CEP.TRC(SCCGWA, DCRCDORD.UPDTBL_DATE);
    }
    public void S000_CALL_DCZFCDGG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-F-CHK-DIGIT-GEN", DCCFCDGG);
        if (DCCFCDGG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCFCDGG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.fst = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READUPD_DCTCDORD() throws IOException,SQLException,Exception {
        null.col = "CARD_NO, CARD_PROD, EMBS_TYP, APP_BAT_NO, CRT_STS, CRT_DT, TRAN_PIN_DAT, QURY_PIN_DAT, APP_TELLER, APP_DT, APP_BR, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.col = "CARD_PROD, EMBS_TYP, APP_BAT_NO, CRT_STS, CRT_DT, TRAN_PIN_DAT, QURY_PIN_DAT, APP_TELLER, APP_DT, APP_BR, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCDORD_RD.where = "CARD_NO = :DCRCDORD.KEY.CARD_NO "
            + "AND EXC_CARD_TMS = :DCRCDORD.KEY.EXC_CARD_TMS";
        IBS.REWRITE(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
