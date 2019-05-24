package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZQIDNM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBG30   ";
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    IBCOQDNM IBCOQDNM = new IBCOQDNM();
    IBRINTH IBRINTH = new IBRINTH();
    IBRTMST IBRTMST = new IBRTMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCQIDNM IBCQIDNM;
    public void MP(SCCGWA SCCGWA, IBCQIDNM IBCQIDNM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCQIDNM = IBCQIDNM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZQIDNM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_INQ_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQIDNM.BR);
        if (IBCQIDNM.BR == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.BR_MUST_INPUT, IBCQIDNM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQIDNM.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111");
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
            || !BPCPQORG.VIL_TYP.equalsIgnoreCase("043")) {
            CEP.TRC(SCCGWA, "333");
            IBCQIDNM.CORP_ID = BPCPQORG.VIL_TYP;
            IBCQIDNM.CORP_TRI_ID = BPCPQORG.TRA_TYP;
            if (BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
                IBCQIDNM.CORP_ATTR = '1';
            } else {
                IBCQIDNM.CORP_ATTR = '2';
            }
        } else {
            CEP.TRC(SCCGWA, "444");
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NON_VIL_TRA, IBCQIDNM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBCQIDNM.CORP_NAME = BPCPQORG.CHN_NM;
        CEP.TRC(SCCGWA, IBCQIDNM.CORP_NAME);
        CEP.TRC(SCCGWA, IBCQIDNM.CORP_ID);
        CEP.TRC(SCCGWA, IBCQIDNM.CORP_ATTR);
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOQDNM);
        IBCOQDNM.CORP_ID = IBCQIDNM.CORP_ID;
        IBCOQDNM.CORP_ATTR = IBCQIDNM.CORP_ATTR;
        IBCOQDNM.CORP_NAME = IBCQIDNM.CORP_NAME;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOQDNM;
        SCCFMT.DATA_LEN = 144;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
