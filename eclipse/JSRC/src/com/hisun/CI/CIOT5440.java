package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5440 {
    String K_STS_TABLE_APP = "CI";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    CIOT5440_WS_T_CTL WS_T_CTL = new CIOT5440_WS_T_CTL();
    int WS_T_ID = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICMSTS CICMSTS = new CICMSTS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5440_AWA_5440 CIB5440_AWA_5440;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5440 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5440_AWA_5440>");
        CIB5440_AWA_5440 = (CIB5440_AWA_5440) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICMSTS);
        CICMSTS.DATA.CI_NO = CIB5440_AWA_5440.CI_NO;
        CICMSTS.FUNC = 'B';
        S000_LINK_CIZMSTS();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5440_AWA_5440.CI_NO);
        if (CIB5440_AWA_5440.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
    }
    public void S000_LINK_CIZMSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-REC-CIZMSTS", CICMSTS);
        if (CICMSTS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMSTS.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
