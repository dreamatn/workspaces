package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUGRHD {
    DBParm DDTGPRS_RD;
    boolean pgmRtn = false;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDRGPRS DDRGPRS = new DDRGPRS();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCUGRHD DDCUGRHD;
    public void MP(SCCGWA SCCGWA, DDCUGRHD DDCUGRHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUGRHD = DDCUGRHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUGRHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_DDTGPRS_REC();
        if (pgmRtn) return;
        B030_UPDATE_DDTGPRS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCUGRHD.INPUT.FUNC != 'F' 
            && DDCUGRHD.INPUT.FUNC != 'U') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_HLD_TYPE_INVALID, DDCUGRHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUGRHD.INPUT.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN, DDCUGRHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUGRHD.INPUT.UP_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN, DDCUGRHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_DDTGPRS_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRGPRS);
        DDRGPRS.KEY.AC_NO = DDCUGRHD.INPUT.AC;
        T000_READUPD_DDTGPRS();
        if (pgmRtn) return;
        if (DDRGPRS.REL_STS == 'D') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GROUP_REC_EXPIRE, DDCUGRHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_DDTGPRS() throws IOException,SQLException,Exception {
        if (DDCUGRHD.INPUT.FUNC == 'F') {
            if (DDRGPRS.REL_STS == 'F') {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_IS_FREEZE, DDCUGRHD.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                DDRGPRS.REL_STS = 'F';
            }
        } else {
            if (DDRGPRS.REL_STS == 'N') {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_IS_NOT_FREEZE, DDCUGRHD.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                DDRGPRS.REL_STS = 'N';
            }
        }
        DDRGPRS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRGPRS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTGPRS();
        if (pgmRtn) return;
    }
    public void T000_READUPD_DDTGPRS() throws IOException,SQLException,Exception {
        DDTGPRS_RD = new DBParm();
        DDTGPRS_RD.TableName = "DDTGPRS";
        DDTGPRS_RD.upd = true;
        IBS.READ(SCCGWA, DDRGPRS, DDTGPRS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_AC_NOT_FND, DDCUGRHD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DDTGPRS ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPRS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTGPRS() throws IOException,SQLException,Exception {
        DDTGPRS_RD = new DBParm();
        DDTGPRS_RD.TableName = "DDTGPRS";
        IBS.REWRITE(SCCGWA, DDRGPRS, DDTGPRS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "UPDATE TABLE DDTGPRS ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPRS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCUGRHD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCUGRHD");
            CEP.TRC(SCCGWA, DDCUGRHD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
