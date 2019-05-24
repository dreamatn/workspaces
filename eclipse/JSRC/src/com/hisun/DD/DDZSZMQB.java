package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSZMQB {
    DBParm DDTZMQT_RD;
    String K_OUTPUT_FMT = "DD140";
    String K_HIS_REMARKS = "FT TRANSFER IN FAILUE BACK";
    String K_HIS_CPB_NAME = "DDCSZMQB";
    String WS_ERR_MSG = " ";
    int WS_TR_DATE = 0;
    long WS_TR_JRN = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCOZMQB DDCOZMQB = new DDCOZMQB();
    DDRZMQT DDRZMQT = new DDRZMQT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSZMQB DDCSZMQB;
    public void MP(SCCGWA SCCGWA, DDCSZMQB DDCSZMQB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSZMQB = DDCSZMQB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSZMQB return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT_DATA();
        B200_READUPDATE_DDTZMQT();
        B300_NON_FIN_HIS_PROC();
        B400_OUTPUT_DATA();
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSZMQB.TR_DATE);
        CEP.TRC(SCCGWA, DDCSZMQB.TR_JRN);
        CEP.TRC(SCCGWA, DDCSZMQB.TR_RMK);
        if (DDCSZMQB.TR_DATE == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_DATE_M_INPUT);
        }
        if (DDCSZMQB.TR_JRN == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_JRN_M_INPUT);
        }
        if (DDCSZMQB.TR_RMK.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_RMK_M_INPUT);
        }
    }
    public void B200_READUPDATE_DDTZMQT() throws IOException,SQLException,Exception {
        WS_TR_DATE = DDCSZMQB.TR_DATE;
        WS_TR_JRN = DDCSZMQB.TR_JRN;
        IBS.init(SCCGWA, DDRZMQT);
        R000_READUPDATE_DDTZMQT();
        CEP.TRC(SCCGWA, DDRZMQT.STS);
        if (DDRZMQT.STS > '0') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STS_NOTAFTERIN);
        } else {
            DDRZMQT.STS = '4';
            DDRZMQT.TR_RMK = DDCSZMQB.TR_RMK;
            DDRZMQT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRZMQT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            R000_REWRITE_DDTZMQT();
        }
        CEP.TRC(SCCGWA, DDRZMQT.FRM_AC);
        CEP.TRC(SCCGWA, DDRZMQT.FRM_AC_NM);
        CEP.TRC(SCCGWA, DDRZMQT.TO_AC);
        CEP.TRC(SCCGWA, DDRZMQT.TO_AC_NM);
        CEP.TRC(SCCGWA, DDRZMQT.TR_RMK);
    }
    public void B300_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.AC = DDRZMQT.FRM_AC;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NAME;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
    }
    public void B400_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOZMQB);
        DDCOZMQB.TR_DATE = DDRZMQT.KEY.OUT_DATE;
        DDCOZMQB.TR_JRN = DDRZMQT.KEY.OUT_JRN;
        DDCOZMQB.TO_AC = DDRZMQT.TO_AC;
        DDCOZMQB.TO_ACNM = DDRZMQT.TO_AC_NM;
        DDCOZMQB.FRM_AC = DDRZMQT.FRM_AC;
        DDCOZMQB.FRM_ACNM = DDRZMQT.FRM_AC_NM;
        DDCOZMQB.TR_RMK = DDRZMQT.TR_RMK;
        CEP.TRC(SCCGWA, DDCOZMQB.TR_DATE);
        CEP.TRC(SCCGWA, DDCOZMQB.TR_JRN);
        CEP.TRC(SCCGWA, DDCOZMQB.TO_AC);
        CEP.TRC(SCCGWA, DDCOZMQB.TO_ACNM);
        CEP.TRC(SCCGWA, DDCOZMQB.FRM_AC);
        CEP.TRC(SCCGWA, DDCOZMQB.FRM_ACNM);
        CEP.TRC(SCCGWA, DDCOZMQB.TR_RMK);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOZMQB;
        SCCFMT.DATA_LEN = 710;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_READUPDATE_DDTZMQT() throws IOException,SQLException,Exception {
        DDTZMQT_RD = new DBParm();
        DDTZMQT_RD.TableName = "DDTZMQT";
        DDTZMQT_RD.where = "OUT_JRN = :WS_TR_JRN "
            + "AND OUT_DATE = :WS_TR_DATE";
        DDTZMQT_RD.upd = true;
        IBS.READ(SCCGWA, DDRZMQT, this, DDTZMQT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OUT_TRANS_NOTFND);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMQT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void R000_REWRITE_DDTZMQT() throws IOException,SQLException,Exception {
        DDTZMQT_RD = new DBParm();
        DDTZMQT_RD.TableName = "DDTZMQT";
        IBS.REWRITE(SCCGWA, DDRZMQT, DDTZMQT_RD);
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
