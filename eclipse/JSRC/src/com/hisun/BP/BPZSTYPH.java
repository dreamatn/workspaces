package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTYPH {
    SCZEXIT_WS_STAR_COMM WS_STAR_COMM;
    String K_PARM_TYPH = "TYPH ";
    String K_OUTPUT_FMT = "BP084";
    String WS_ERR_MSG = " ";
    char WS_CHECK_RUSULT = ' ';
    BPZSTYPH_WS_OUT WS_OUT = new BPZSTYPH_WS_OUT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTDAYE BPCTDAYE = new BPCTDAYE();
    BPRDAYE BPRDAYE = new BPRDAYE();
    BPCSBSP BPCSBSP = new BPCSBSP();
    SCCSTAR SCCSTAR = new SCCSTAR();
    BPCIFLTN BPCIFLTN = new BPCIFLTN();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCSTYPH BPCSTYPH;
    public void MP(SCCGWA SCCGWA, BPCSTYPH BPCSTYPH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTYPH = BPCSTYPH;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, BPCSTYPH.FUNC);
        CEP.TRC(SCCGWA, BPCSTYPH.REST_CODE);
        CEP.TRC(SCCGWA, BPCSTYPH.UNSCH_TYP);
        CEP.TRC(SCCGWA, BPCSTYPH.EXCH_FLG);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSTYPH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBSP);
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CEP.TRC(SCCGWA, BPCSTYPH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSTYPH.FUNC == 'I') {
            B020_INQUIRE_RECORD();
        } else if (BPCSTYPH.FUNC == 'S') {
            B030_SET_TYPH_STS();
        } else if (BPCSTYPH.FUNC == 'C') {
            B040_CANCEL_TYPH_STS();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC();
        }
        B050_TRANS_DATA_OUT();
    }
    public void B020_INQUIRE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTDAYE);
        IBS.init(SCCGWA, BPRDAYE);
        IBS.init(SCCGWA, BPCPQBCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        BPCPQBCH.ACCT = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQBCH();
        BPRDAYE.KEY.RGN = BPCSTYPH.CITY_CD;
        BPRDAYE.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRDAYE.KEY.TYPE = 'C';
        BPCTDAYE.INFO.FUNC = 'Q';
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        S000_CALL_BPZTDAYE();
    }
    public void B030_SET_TYPH_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTDAYE);
        IBS.init(SCCGWA, BPRDAYE);
        IBS.init(SCCGWA, BPCPQBCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        BPCPQBCH.ACCT = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQBCH();
        CEP.TRC(SCCGWA, BPCPQBCH.BNK);
        CEP.TRC(SCCGWA, BPCPQBCH.BCH);
        BPRDAYE.KEY.RGN = BPCSTYPH.CITY_CD;
        BPRDAYE.KEY.TYPE = 'C';
        BPRDAYE.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCSTYPH.REST_CODE);
        CEP.TRC(SCCGWA, BPCSTYPH.UNSCH_TYP);
        CEP.TRC(SCCGWA, BPCSTYPH.EXCH_FLG);
        BPRDAYE.CHARACTER = BPCSTYPH.REST_CODE;
        BPRDAYE.DATE_TYPE = BPCSTYPH.UNSCH_TYP;
        BPRDAYE.EXCH_FLG = BPCSTYPH.EXCH_FLG;
        BPRDAYE.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRDAYE.LAST_TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPCTDAYE.INFO.FUNC = 'C';
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        S000_CALL_BPZTDAYE();
        if (BPCTDAYE.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ADD_DB_DATA_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPCTDAYE.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ALREADY_TYPH_STATUS;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCIFLTN);
        BPCIFLTN.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCIFLTN.FUNC = 'S';
        S000_CALL_BPZIFLTN();
    }
    public void B040_CANCEL_TYPH_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTDAYE);
        IBS.init(SCCGWA, BPRDAYE);
        IBS.init(SCCGWA, BPCPQBCH);
        BPCPQBCH.ACCT = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQBCH();
        BPRDAYE.KEY.RGN = BPCSTYPH.CITY_CD;
        BPRDAYE.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCPQBCH.BCH);
        CEP.TRC(SCCGWA, BPRDAYE.KEY.DATE);
        BPRDAYE.KEY.TYPE = 'C';
        BPCTDAYE.INFO.FUNC = 'R';
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        S000_CALL_BPZTDAYE();
        if (BPCTDAYE.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_UNSCH_HOL;
            S000_ERR_MSG_PROC();
        }
        BPCTDAYE.INFO.FUNC = 'D';
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        S000_CALL_BPZTDAYE();
        if (BPCTDAYE.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DEL_DB_DATA_ERR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCIFLTN);
        BPCIFLTN.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCIFLTN.FUNC = 'S';
        S000_CALL_BPZIFLTN();
    }
    public void B050_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_OUT.WS_FUNC = BPCSTYPH.FUNC;
        WS_OUT.WS_CHARACTER = BPRDAYE.CHARACTER;
        WS_OUT.WS_UNSCH_TYP = BPRDAYE.DATE_TYPE;
        WS_OUT.WS_EXCH_FLG = BPRDAYE.EXCH_FLG;
        WS_OUT.WS_CITY_CD = BPRDAYE.KEY.RGN;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT;
        SCCFMT.DATA_LEN = 37;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_01_DEL_TYPHFLG() throws IOException,SQLException,Exception {
        BPCSBSP.AP_PROC = "SPSTYPH";
        S000_CALL_BPZSBSP();
    }
    public void S000_CALL_BPZSBSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BPZSBSP");
        WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
        WS_STAR_COMM.STAR_PGM = "BPZSBSP";
        WS_STAR_COMM.STAR_DATA = BPCSBSP;
        IBS.START(SCCGWA, WS_STAR_COMM, true);
    }
    public void S000_CALL_BPZTDAYE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-DAYE", BPCTDAYE);
    }
    public void S000_CALL_BPZPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
    }
    public void S000_CALL_BPZIFLTN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INIT-FLT-NRULE", BPCIFLTN);
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
