package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1270 {
    String CPN_S_BPZFFCLT = "BP-F-S-FEE-COLLECT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_CLT_TYPE = ' ';
    char WS_SGN_TYPE = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFCLT BPCSFCLT = new BPCSFCLT();
    CICACCU CICACCU = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1270_AWA_1270 BPB1270_AWA_1270;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1270 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1270_AWA_1270>");
        BPB1270_AWA_1270 = (BPB1270_AWA_1270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSFCLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_CLT_TYPE = BPB1270_AWA_1270.CLT_TYPE;
        if ((WS_CLT_TYPE != '0' 
            && WS_CLT_TYPE != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_CLT_TYPE_ERR;
            WS_FLD_NO = BPB1270_AWA_1270.CLT_TYPE_NO;
            S000_ERR_MSG_PROC();
        }
        WS_SGN_TYPE = BPB1270_AWA_1270.SGN_TYPE;
        if ((WS_SGN_TYPE != '0' 
            && WS_SGN_TYPE != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_SGN_TYPE_ERR;
            WS_FLD_NO = BPB1270_AWA_1270.SGN_TYPE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1270_AWA_1270.ST_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1270_AWA_1270.ST_DT;
            S000_CALL_SCSSCKDT();
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                WS_FLD_NO = BPB1270_AWA_1270.ST_DT_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            BPB1270_AWA_1270.ST_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB1270_AWA_1270.ED_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1270_AWA_1270.ED_DT;
            S000_CALL_SCSSCKDT();
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                WS_FLD_NO = BPB1270_AWA_1270.ED_DT_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            BPB1270_AWA_1270.ED_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB1270_AWA_1270.ST_DT > BPB1270_AWA_1270.ED_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            WS_FLD_NO = BPB1270_AWA_1270.ST_DT_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1270_AWA_1270.CI_AC.trim().length() > 0 
            && BPB1270_AWA_1270.SGN_TYPE == '0') {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPB1270_AWA_1270.CI_AC;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
        }
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFCLT);
        BPCSFCLT.CLT_TYPE = BPB1270_AWA_1270.CLT_TYPE;
        BPCSFCLT.SGN_TYPE = BPB1270_AWA_1270.SGN_TYPE;
        if (BPB1270_AWA_1270.SGN_TYPE == '0') {
            BPCSFCLT.SGN_CINO = BPB1270_AWA_1270.CI_AC;
        } else {
            BPCSFCLT.SGN_AC = BPB1270_AWA_1270.CI_AC;
        }
        BPCSFCLT.ST_DATE = BPB1270_AWA_1270.ST_DT;
        BPCSFCLT.ED_DATE = BPB1270_AWA_1270.ED_DT;
        BPCSFCLT.PAGE_NUM = BPB1270_AWA_1270.PAGE_NUM;
        if (BPB1270_AWA_1270.PAGE_ROW != 0) {
            CEP.TRC(SCCGWA, BPB1270_AWA_1270.PAGE_ROW);
            BPCSFCLT.PAGE_ROW = BPB1270_AWA_1270.PAGE_ROW;
        } else {
            BPCSFCLT.PAGE_ROW = 10;
        }
        CEP.TRC(SCCGWA, BPB1270_AWA_1270.PAGE_NUM);
        CEP.TRC(SCCGWA, BPB1270_AWA_1270.PAGE_ROW);
        CEP.TRC(SCCGWA, BPB1270_AWA_1270.ST_DT);
        CEP.TRC(SCCGWA, BPB1270_AWA_1270.ED_DT);
        BPCSFCLT.FUNC = 'B';
        S00_CALL_BPZFFCLT();
    }
    public void S00_CALL_BPZFFCLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZFFCLT, BPCSFCLT);
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
