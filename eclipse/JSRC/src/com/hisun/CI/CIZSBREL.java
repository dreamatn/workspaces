package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSBREL {
    String JIBS_tmp_str[] = new String[10];
    String CPN_REC_MCRE = "CI-MAIN-MCRE        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICMCRE CICMCRE = new CICMCRE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSBREL CICSBREL;
    public void MP(SCCGWA SCCGWA, CICSBREL CICSBREL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSBREL = CICSBREL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZSBREL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMCRE);
        CEP.TRC(SCCGWA, CICSBREL);
        CEP.TRC(SCCGWA, CICSBREL.FUNC);
        CEP.TRC(SCCGWA, CICSBREL.REL_OPER);
        CEP.TRC(SCCGWA, "GFX TEST");
        CICMCRE.FUNC = CICSBREL.FUNC;
        CICMCRE.REL_OPER = CICSBREL.REL_OPER;
        CICMCRE.CI_NO = CICSBREL.CI_NO;
        CICMCRE.AGT_NO = CICSBREL.AGT_NO;
        CICMCRE.CARD_NO = CICSBREL.CARD_NO;
        CICMCRE.CI_CNM = CICSBREL.CI_CNM;
        CICMCRE.ID_CTYP = CICSBREL.ID_CTYP;
        CICMCRE.ID_CNO = CICSBREL.ID_CNO;
        CICMCRE.CI_PNM = CICSBREL.CI_PNM;
        CICMCRE.ID_PTYP = CICSBREL.ID_PTYP;
        CICMCRE.ID_PNO = CICSBREL.ID_PNO;
        CICMCRE.TEL_NO = CICSBREL.TEL_NO;
        CICMCRE.FLDK = CICSBREL.FLDK;
        S000_CALL_CIZMCRE();
    }
    public void S000_CALL_CIZMCRE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-MCRE", CICMCRE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICMCRE.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICSBREL.RC);
        CEP.TRC(SCCGWA, CICSBREL.RC);
        if (CICSBREL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSBREL.RC);
            CEP.TRC(SCCGWA, "XXX");
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
