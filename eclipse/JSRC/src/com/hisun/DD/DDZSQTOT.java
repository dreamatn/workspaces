package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQTOT {
    DBParm DDTCCY_RD;
    DBParm DDTINTB_RD;
    brParm DDTACCU_BR = new brParm();
    DBParm DDTMST_RD;
    String K_NEST_INQ = "DD502";
    String WS_ERR_MSG = " ";
    double WS_TOT1 = 0;
    double WS_TOT2 = 0;
    double WS_TOT3 = 0;
    double WS_TOT4 = 0;
    double WS_TOT5 = 0;
    double WS_ACCU_TOT = 0;
    DDZSQTOT_WS_OUT_PUT WS_OUT_PUT = new DDZSQTOT_WS_OUT_PUT();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    CICQACAC CICQACAC = new CICQACAC();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRINTB DDRINTB = new DDRINTB();
    DDRACCU DDRACCU = new DDRACCU();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSQTOT DDCSQTOT;
    public void MP(SCCGWA SCCGWA, DDCSQTOT DDCSQTOT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQTOT = DDCSQTOT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSQTOT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_GET_AC_INF_PROC();
        B030_CHK_AC_STS();
        B040_CHK_KH_DATE();
        B050_INQ_TOT_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSQTOT.AC_NO;
        T000_READ_DDTMST();
    }
    public void B030_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B040_CHK_KH_DATE() throws IOException,SQLException,Exception {
        if (DDRMST.OPEN_DATE > DDCSQTOT.DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VAL_DT_INVALID;
            S000_ERR_MSG_PROC();
        }
    }
    public void B050_INQ_TOT_PROC() throws IOException,SQLException,Exception {
        B050_READ_DDTCCY();
        B051_READ_DDTINTB();
        if (DDCSQTOT.DATE >= DDRINTB.LST_DPOST_DATE) {
            B050_STARTBR_DDTACCU();
            B053_READNEXT_DDTACCU();
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                WS_ACCU_TOT = DDRACCU.TOT + WS_ACCU_TOT;
                B053_READNEXT_DDTACCU();
            }
            B054_ENDBR_DDTACCU();
            WS_TOT1 = DDRINTB.DEP_TOT1;
            WS_TOT2 = DDRINTB.DEP_TOT2;
            WS_TOT3 = DDRINTB.DEP_TOT3;
            WS_TOT4 = DDRINTB.DEP_TOT4;
            WS_TOT5 = DDRINTB.DEP_TOT5;
            WS_OUT_PUT.WS_TOT = WS_TOT1 + WS_TOT2 + WS_TOT3 + WS_TOT4 + WS_TOT5 + WS_ACCU_TOT;
            CEP.TRC(SCCGWA, WS_OUT_PUT.WS_TOT);
        } else {
            B052_STARTBR_DDTACCU();
            B053_READNEXT_DDTACCU();
            while ((DDCSQTOT.DATE <= DDRACCU.KEY.STR_DATE 
                || DDCSQTOT.DATE >= DDRACCU.KEY.END_DATE) 
                && (SCCGWA.COMM_AREA.DBIO_FLG != '1')) {
                WS_OUT_PUT.WS_TOT = DDRACCU.TOT;
                CEP.TRC(SCCGWA, WS_OUT_PUT.WS_TOT);
                B053_READNEXT_DDTACCU();
            }
            B054_ENDBR_DDTACCU();
        }
        B060_INQ_FMT_OUT();
    }
    public void B050_READ_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSQTOT.AC_NO;
        DDRCCY.CCY = DDCSQTOT.CCY;
        DDRCCY.CCY_TYPE = DDCSQTOT.CCY_TYP;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B051_READ_DDTINTB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        DDTINTB_RD.where = "AC = :DDRINTB.KEY.AC "
            + "AND TYPE = 'D'";
        IBS.READ(SCCGWA, DDRINTB, this, DDTINTB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INTB_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B050_STARTBR_DDTACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRACCU.INT_STS = 'N';
        DDTACCU_BR.rp = new DBParm();
        DDTACCU_BR.rp.TableName = "DDTACCU";
        DDTACCU_BR.rp.where = "AC = :DDRACCU.KEY.AC "
            + "AND INT_STS = :DDRACCU.INT_STS";
        IBS.STARTBR(SCCGWA, DDRACCU, this, DDTACCU_BR);
    }
    public void B052_STARTBR_DDTACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRACCU.KEY.CLS = 'P';
        DDTACCU_BR.rp = new DBParm();
        DDTACCU_BR.rp.TableName = "DDTACCU";
        DDTACCU_BR.rp.where = "AC = :DDRACCU.KEY.AC "
            + "AND CLS = :DDRACCU.KEY.CLS";
        IBS.STARTBR(SCCGWA, DDRACCU, this, DDTACCU_BR);
    }
    public void B053_READNEXT_DDTACCU() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRACCU, this, DDTACCU_BR);
    }
    public void B054_ENDBR_DDTACCU() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTACCU_BR);
    }
    public void B060_INQ_FMT_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_NEST_INQ;
        SCCFMT.DATA_PTR = WS_OUT_PUT;
        SCCFMT.DATA_LEN = 19;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
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
