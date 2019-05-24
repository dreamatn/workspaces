package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIOT7002 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    char WS_TBL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICBACR CICBACR = new CICBACR();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CIRACR CIRACR = new CIRACR();
    CICOCUST CICOCUST = new CICOCUST();
    CIRBAS CIRBAS = new CIRBAS();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    CICCASHE SCCPFMCE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB7002_AWA_7002 CIB7002_AWA_7002;
    char WS_BAS_FLG = ' ';
    char WS_CNT_FLG = ' ';
    char WS_ACR_FLG = ' ';
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        if (CIB7002_AWA_7002.OP_TYPE == 'I') {
            CEP.TRC(SCCGWA, "中文PERFORM C000-MAIN-PROC调试�?始！");
            C000_MAIN_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "中文PERFORM C000-MAIN-PROC调试结束�?");
        } else if (CIB7002_AWA_7002.OP_TYPE == 'A') {
            CEP.TRC(SCCGWA, "OP-TYPE 内容�?");
            CEP.TRC(SCCGWA, CIB7002_AWA_7002.OP_TYPE);
            CEP.TRC(SCCGWA, "中文T100-INSERT-CITBAS调试�?始！");
            T100_INSERT_CITBAS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "中文T100-INSERT-CITBAS调试结束�?");
        } else if (CIB7002_AWA_7002.OP_TYPE == 'U') {
            CEP.TRC(SCCGWA, "OP-TYPE 内容�?");
            CEP.TRC(SCCGWA, CIB7002_AWA_7002.OP_TYPE);
            CEP.TRC(SCCGWA, "中文T200-UPDATE-CITBAS调试�?始！");
            T200_UPDATE_CITBAS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "中文T200-UPDATE-CITBAS调试结束�?");
        } else if (CIB7002_AWA_7002.OP_TYPE == 'D') {
            CEP.TRC(SCCGWA, "OP-TYPE 内容�?");
            CEP.TRC(SCCGWA, CIB7002_AWA_7002.OP_TYPE);
            CEP.TRC(SCCGWA, "中文T300-DELETE-CITBAS调试�?始！");
            T300_DELETE_CITBAS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "中文T300-DELETE-CITBAS调试结束�?");
        }
        CEP.TRC(SCCGWA, "CIOT7002 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB7002_AWA_7002>");
        CIB7002_AWA_7002 = (CIB7002_AWA_7002) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCUST);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICBACR);
        CICBACR.DATA.CI_NO = CIB7002_AWA_7002.CI_NO;
        CICBACR.DATA.ID_NO = CIB7002_AWA_7002.CI_NAME;
        CICBACR.DATA.CI_NM = CIB7002_AWA_7002.ID_NO;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB7002_AWA_7002.CI_NO);
        CEP.TRC(SCCGWA, CIB7002_AWA_7002.CI_NAME);
        CEP.TRC(SCCGWA, CIB7002_AWA_7002.ID_NO);
        CEP.TRC(SCCGWA, "中文日志输出测试验证for IBS MSG --BEGIN");
        CEP.TRC(SCCGWA, "中文日志输出测试验证for IBS MSG --END");
        if (CIB7002_AWA_7002.CI_NO.trim().length() > 0) {
            CICCUST.FUNC = 'C';
        } else if (CIB7002_AWA_7002.CI_NAME.trim().length() > 0) {
            CICCUST.FUNC = 'A';
        } else if (CIB7002_AWA_7002.ID_NO.trim().length() > 0) {
            CICCUST.FUNC = 'B';
        } else {
            CEP.TRC(SCCGWA, "INPUT ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
        }
    }
    public void B020_INQUIRE_CUST() throws IOException,SQLException,Exception {
        CICCUST.DATA.CI_NO = CIB7002_AWA_7002.CI_NO;
        CICCUST.DATA.ID_NO = CIB7002_AWA_7002.ID_NO;
        CICCUST.DATA.CI_NM = CIB7002_AWA_7002.CI_NAME;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void C000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIB7002_AWA_7002.CI_NO;
        CEP.TRC(SCCGWA, "BAS-CI-NO为：");
        CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
        CEP.TRC(SCCGWA, "BAS-ID-NO为：");
        CEP.TRC(SCCGWA, CIRBAS.ID_NO);
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "查询表中记录后输出CI-NM为：");
