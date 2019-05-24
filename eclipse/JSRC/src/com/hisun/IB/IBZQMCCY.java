package com.hisun.IB;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZQMCCY {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCQINFT IBCQINFT = new IBCQINFT();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCQMCCY IBCQMCCY;
    public void MP(SCCGWA SCCGWA, IBCQMCCY IBCQMCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCQMCCY = IBCQMCCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZQMCCY return!");
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
        B020_GET_AC_INFO();
        if (pgmRtn) return;
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQMCCY.AC_NO);
        CEP.TRC(SCCGWA, IBCQMCCY.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCQMCCY.CCY);
        if ((IBCQMCCY.AC_NO.trim().length() == 0) 
            && (IBCQMCCY.NOSTR_CD.trim().length() == 0 
            || IBCQMCCY.CCY.trim().length() == 0)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT, IBCQMCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFT);
        if (IBCQMCCY.AC_NO.trim().length() > 0) {
            IBCQINFT.AC_NO = IBCQMCCY.AC_NO;
        } else {
            IBCQINFT.NOSTR_CD = IBCQMCCY.NOSTR_CD;
            IBCQINFT.CCY = IBCQMCCY.CCY;
        }
        S000_CALL_IBZQINFT();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBCQMCCY.AC_NO = IBCQINFT.AC_NO;
        IBCQMCCY.NOSTR_CD = IBCQINFT.NOSTR_CD;
        IBCQMCCY.CCY = IBCQINFT.CCY;
    }
    public void S000_CALL_IBZQINFT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINFT", IBCQINFT);
        if (IBCQINFT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINFT.RC);
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
