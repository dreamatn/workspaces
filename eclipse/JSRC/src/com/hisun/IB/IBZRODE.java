package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class IBZRODE {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    long WS_INT_TMP0 = 0;
    double WS_INT_TMP1 = 0;
    double WS_INT_TMP2 = 0;
    double WS_INT_TMP3 = 0;
    double WS_INT_PUBLIC = 0;
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCRODE IBCRODE;
    public void MP(SCCGWA SCCGWA, IBCRODE IBCRODE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCRODE = IBCRODE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZRODE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (IBCRODE.INT_AMT != 0) {
            B020_ROUN_PROC();
            if (pgmRtn) return;
        }
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCRODE.CCY);
        CEP.TRC(SCCGWA, IBCRODE.INT_AMT);
        if (IBCRODE.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CCY_M, IBCRODE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_ROUN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = IBCRODE.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
        if (BPCQCCY.DATA.DEC_MTH == 0) {
            WS_INT_TMP0 = IBCRODE.INT_AMT;
            bigD = new BigDecimal(WS_INT_TMP0);
            WS_INT_TMP0 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            IBCRODE.INT_AMT = WS_INT_TMP0;
        } else if (BPCQCCY.DATA.DEC_MTH == 1) {
            WS_INT_TMP1 = IBCRODE.INT_AMT;
            bigD = new BigDecimal(WS_INT_TMP1);
            WS_INT_TMP1 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            IBCRODE.INT_AMT = WS_INT_TMP1;
        } else if (BPCQCCY.DATA.DEC_MTH == 2) {
            WS_INT_TMP2 = IBCRODE.INT_AMT;
            bigD = new BigDecimal(WS_INT_TMP2);
            WS_INT_TMP2 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            IBCRODE.INT_AMT = WS_INT_TMP2;
        } else if (BPCQCCY.DATA.DEC_MTH == 3) {
            WS_INT_TMP3 = IBCRODE.INT_AMT;
            bigD = new BigDecimal(WS_INT_TMP3);
            WS_INT_TMP3 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            IBCRODE.INT_AMT = WS_INT_TMP3;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID MTH(" + BPCQCCY.DATA.DEC_MTH + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, IBCRODE.INT_AMT);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCRODE.RC);
            Z_RET();
            if (pgmRtn) return;
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
