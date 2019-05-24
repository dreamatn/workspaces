package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9143 {
    DBParm CITCNT_RD;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRCNT CIRCNT = new CIRCNT();
    CICSMCNT CICSMCNT = new CICSMCNT();
    CICCMCK CICCMCK = new CICCMCK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9143_AWA_9143 CIB9143_AWA_9143;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9143 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9143_AWA_9143>");
        CIB9143_AWA_9143 = (CIB9143_AWA_9143) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMCNT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_MOD_CNT_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9143_AWA_9143.CI_NO);
        if (CIB9143_AWA_9143.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
    }
    public void B020_MOD_CNT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSMCNT);
        CICSMCNT.DATA.CI_NO = CIB9143_AWA_9143.CI_NO;
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CIB9143_AWA_9143.CI_NO;
        CIRCNT.KEY.CNT_TYPE = "21";
        T000_READ_CITCNT();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICSMCNT.FUNC = 'M';
        } else {
            CICSMCNT.FUNC = 'A';
        }
        CICSMCNT.DATA.CNT_TYPE = "21";
        CICSMCNT.DATA.CNT_TEL = CIB9143_AWA_9143.TEL_NO;
        S000_CALL_CIZSMCNT();
        IBS.init(SCCGWA, CICCMCK);
        CICCMCK.DATA.CI_NO = CIB9143_AWA_9143.CI_NO;
        CICCMCK.DATA.TABLE_FLG = 'W';
        S000_CALL_CIZCMCK();
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CIB9143_AWA_9143.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRCNT";
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_CIZSMCNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-M-CNT-INF", CICSMCNT);
        if (CICSMCNT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSMCNT.RC);
        }
    }
    public void S000_CALL_CIZCMCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-COM-CHK", CICCMCK);
        if (CICCMCK.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCMCK.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    } //FROM #ENDIF
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void T000_READ_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
