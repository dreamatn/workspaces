package com.hisun.CI;

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

public class CIOT5523 {
    boolean pgmRtn = false;
    short WS_I = 0;
    String WS_MSG_INFO = " ";
    char WS_CHK_ADR_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRPDM CIRPDM = new CIRPDM();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CICSMADR CICSMADR = new CICSMADR();
    CICCMCK CICCMCK = new CICCMCK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRADR CIRADR = new CIRADR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5523_AWA_5523 CIB5523_AWA_5523;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT5523 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5523_AWA_5523>");
        CIB5523_AWA_5523 = (CIB5523_AWA_5523) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMADR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MOD_ADR_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5523_AWA_5523.CI_NO);
        if (CIB5523_AWA_5523.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
    }
    public void B020_MOD_ADR_INF() throws IOException,SQLException,Exception {
        WS_CHK_ADR_FLG = 'N';
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (CIB5523_AWA_5523.ADR_AREA[WS_I-1].FUNC != ' ') {
                if (CIB5523_AWA_5523.ADR_AREA[WS_I-1].FUNC == 'D') {
                    WS_CHK_ADR_FLG = 'Y';
                }
                IBS.init(SCCGWA, CICSMADR);
                CICSMADR.DATA.CI_NO = CIB5523_AWA_5523.CI_NO;
                CICSMADR.FUNC = CIB5523_AWA_5523.ADR_AREA[WS_I-1].FUNC;
                CICSMADR.DATA.ADR_TYPE = CIB5523_AWA_5523.ADR_AREA[WS_I-1].ADR_TYPE;
