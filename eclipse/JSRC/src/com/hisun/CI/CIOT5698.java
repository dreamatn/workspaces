package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5698 {
    DBParm CITBAS_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    char K_STE_TYP_AC = '1';
    char K_STE_TYP_CIF = '2';
    String K_OUTPUT_FMT_Q = "CIS98";
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    char WS_OUT_ENTY_TYP = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5690_AWA_5690 CIB5690_AWA_5690;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5698 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5690_AWA_5690>");
        CIB5690_AWA_5690 = (CIB5690_AWA_5690) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIB5690_AWA_5690.CI_NO;
        T000_READ_CITBAS_ONLY();
        if (CIRBAS.CI_TYP == '2') {
            T000_READ_CITCDM();
            WS_OUT_ENTY_TYP = CIRCDM.CAS_FLG;
        } else if (CIRBAS.CI_TYP == '3') {
            T000_READ_CITFDM();
            WS_OUT_ENTY_TYP = CIRFDM.CAS_FLG;
        } else {
            WS_MSGID = CICMSG_ERROR_MSG.CI_TYP_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
        if (WS_OUT_ENTY_TYP == ' ') {
            WS_MSGID = CICMSG_ERROR_MSG.CI_STCE_INF_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            R000_DATA_OUTPUT_FMT();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5690_AWA_5690.CI_NO.trim().length() == 0) {
            WS_MSGID = CICMSG_ERROR_MSG.CI_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_Q;
        SCCFMT.DATA_PTR = WS_OUT_ENTY_TYP;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS_ONLY() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.col = "CI_TYP";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_CITCDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCDM);
        CIRCDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        CITCDM_RD.col = "CAS_FLG";
        IBS.READ(SCCGWA, CIRCDM, CITCDM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = CICMSG_ERROR_MSG.CI_CDM_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_CITFDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFDM);
        CIRFDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        CITFDM_RD.col = "CAS_FLG";
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = CICMSG_ERROR_MSG.CI_FDM_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
