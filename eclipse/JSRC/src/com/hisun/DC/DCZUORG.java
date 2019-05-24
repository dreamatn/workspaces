package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUORG {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICSACR CICSACR = new CICSACR();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUORG DCCUORG;
    public void MP(SCCGWA SCCGWA, DCCUORG DCCUORG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUORG = DCCUORG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUORG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CARD_INFO();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            B030_MIG_CARD();
            if (pgmRtn) return;
            B040_CI_UPD_CARD();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUORG.CARD_NO);
        CEP.TRC(SCCGWA, DCCUORG.BR_OLD);
        CEP.TRC(SCCGWA, DCCUORG.BR_NEW);
        if (DCCUORG.CARD_NO.trim().length() == 0 
            || DCCUORG.BR_OLD == ' ' 
            || DCCUORG.BR_NEW == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT);
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT, DCCUORG.RC);
        }
    }
    public void B020_GET_CARD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUORG.CARD_NO;
        DCRCDDAT.ISSU_BR = DCCUORG.BR_OLD;
        T000_READ_DCTCDDAT_UPD();
        if (pgmRtn) return;
    }
    public void B030_MIG_CARD() throws IOException,SQLException,Exception {
        DCRCDDAT.ISSU_BR = DCCUORG.BR_NEW;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B040_CI_UPD_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'M';
        CICSACR.DATA.AGR_NO = DCCUORG.CARD_NO;
        CICSACR.DATA.ENTY_TYP = '2';
        CICSACR.DATA.OPN_BR = DCCUORG.BR_NEW;
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT_UPD() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.eqWhere = "CARD_NO, ISSU_BR";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
