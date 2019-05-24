package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSWLST {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMLS2 CICMLS2 = new CICMLS2();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSWLST CICSWLST;
    public void MP(SCCGWA SCCGWA, CICSWLST CICSWLST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSWLST = CICSWLST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZSWLST return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICMLS2);
        CICMLS2.DATA.LST_CD = "WHT";
        CICMLS2.DATA.LST_SEQ = 1;
        CICMLS2.DATA.ID_TYPE = CICSWLST.ID_TYPE;
        CICMLS2.DATA.ID_NO = CICSWLST.ID_NO;
        CICMLS2.DATA.CI_CNM = CICSWLST.CI_NM;
        CICMLS2.FUNC = 'A';
        S000_LINK_CIZMLS2();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSWLST.ID_NO);
        if (CICSWLST.ID_NO.trim().length() == 0 
            || CICSWLST.ID_TYPE.trim().length() == 0 
            || CICSWLST.CI_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void S000_LINK_CIZMLS2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-LST2", CICMLS2);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
