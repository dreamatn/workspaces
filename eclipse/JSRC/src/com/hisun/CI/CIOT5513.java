package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5513 {
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSMID CICSMID = new CICSMID();
    CICCMCK CICCMCK = new CICCMCK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5513_AWA_5513 CIB5513_AWA_5513;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5513 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5513_AWA_5513>");
        CIB5513_AWA_5513 = (CIB5513_AWA_5513) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMID);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_MOD_ID_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5513_AWA_5513.CI_NO);
        if (CIB5513_AWA_5513.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
    }
    public void B020_MOD_ID_INF() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (CIB5513_AWA_5513.ID_AREA[WS_I-1].FUNC != ' ') {
                IBS.init(SCCGWA, CICSMID);
                CICSMID.DATA.CI_NO = CIB5513_AWA_5513.CI_NO;
                CICSMID.FUNC = CIB5513_AWA_5513.ID_AREA[WS_I-1].FUNC;
                CICSMID.DATA.ID_TYPE = CIB5513_AWA_5513.ID_AREA[WS_I-1].ID_TYPE;
                CICSMID.DATA.ID_NO = CIB5513_AWA_5513.ID_AREA[WS_I-1].ID_NO;
                CICSMID.DATA.ID_REMARK = CIB5513_AWA_5513.ID_AREA[WS_I-1].ID_RMK;
                CICSMID.DATA.ID_RGN = CIB5513_AWA_5513.ID_AREA[WS_I-1].ID_RGN;
                CICSMID.DATA.EFF_DT = CIB5513_AWA_5513.ID_AREA[WS_I-1].EFF_DT;
                CICSMID.DATA.EXP_DT = CIB5513_AWA_5513.ID_AREA[WS_I-1].EXP_DT;
                CICSMID.DATA.OPEN_FLG = CIB5513_AWA_5513.ID_AREA[WS_I-1].OPEN_FLG;
                S000_CALL_CIZSMID();
            }
        }
        IBS.init(SCCGWA, CICCMCK);
        CICCMCK.DATA.CI_NO = CIB5513_AWA_5513.CI_NO;
        CICCMCK.DATA.TABLE_FLG = 'W';
        S000_CALL_CIZCMCK();
    }
    IBS.init(SCCGWA, BPCPNHIS);
    BPCPNHIS.INFO.TX_TYP = 'M';
    BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
    BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
    BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
    BPCPNHIS.INFO.CI_NO = CIB5513_AWA_5513.CI_NO;
    BPCPNHIS.INFO.FMT_ID = "CIRID";
    S000_CALL_BPZPNHIS();
    public void S000_CALL_CIZSMID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-M-ID-INF", CICSMID);
        if (CICSMID.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSMID.RC);
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
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
