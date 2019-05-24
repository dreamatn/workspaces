package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZUGBAL {
    boolean pgmRtn = false;
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRGPMST DDROLD = new DDRGPMST();
    DDRGPMST DDRGPMST = new DDRGPMST();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCUGBAL DDCUGBAL;
    public void MP(SCCGWA SCCGWA, DDCUGBAL DDCUGBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUGBAL = DDCUGBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUGBAL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_UPDATE_CNTL_BAL();
        if (pgmRtn) return;
        B030_REC_HIS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCUGBAL.INPUT.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN, DDCUGBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUGBAL.INPUT.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT, DDCUGBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUGBAL.INPUT.INT_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_CBAL_MST_IN, DDCUGBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_UPDATE_CNTL_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRGPMST);
        DDRGPMST.KEY.AC_NO = DDCUGBAL.INPUT.AC;
        DDRGPMST.KEY.CCY = DDCUGBAL.INPUT.CCY;
        T000_READUPD_DDTGPMST();
        if (pgmRtn) return;
        if (DDRGPMST.STS == 'D') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GROUP_REC_EXPIRE, DDCUGBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUGBAL);
