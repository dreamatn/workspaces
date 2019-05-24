package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8590 {
    DBParm CITACR_RD;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "CI030";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICOIAG CICOIAG = new CICOIAG();
    CIRACR CIRACR = new CIRACR();
    TDRCMST TDRCMST = new TDRCMST();
    DDRVCH DDRVCH = new DDRVCH();
    CICQCHDC CICQCHDC = new CICQCHDC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8590_AWA_8590 CIB8590_AWA_8590;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8590 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8590_AWA_8590>");
        CIB8590_AWA_8590 = (CIB8590_AWA_8590) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICOIAG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQUIRE_AGR_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB8590_AWA_8590.AGR_NO);
        if (CIB8590_AWA_8590.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AGR-NO INPUT IS NULL");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "客户账号�?");
        } else {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIB8590_AWA_8590.AGR_NO;
            T000_TABLE_AGR_NO_EXIST();
        }
    }
    public void B020_INQUIRE_AGR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCHDC);
        CICQCHDC.FUNC = 'W';
        CICQCHDC.DATA.O_AGR_NO = CIB8590_AWA_8590.AGR_NO;
        CICQCHDC.DATA.O_ENTY_TYP = CIRACR.ENTY_TYP;
        S000_CALL_CIZQCHDC_PROC();
        CICOIAG.AGR_NO = CICQCHDC.DATA.N_AGR_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICOIAG;
        SCCFMT.DATA_LEN = 32;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_TABLE_AGR_NO_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AGR-NO INPUT NOT EXIST");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户账号不存�?");
        }
    }
    public void S000_CALL_CIZQCHDC_PROC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
        if (CICQCHDC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CALL CIZQCHDC PROC ERR");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCHDC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0], "CIZQCHDC组件内报�?");
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
