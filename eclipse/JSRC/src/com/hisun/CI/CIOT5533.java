package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5533 {
    DBParm CITBAS_RD;
    DBParm CITCNT_RD;
    short WS_I = 0;
    char WS_CHK_CNT_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSMCNT CICSMCNT = new CICSMCNT();
    CICCMCK CICCMCK = new CICCMCK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCNT CIRCNT = new CIRCNT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5533_AWA_5533 CIB5533_AWA_5533;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5533 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5533_AWA_5533>");
        CIB5533_AWA_5533 = (CIB5533_AWA_5533) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMCNT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_MOD_CNT_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5533_AWA_5533.CI_NO);
        if (CIB5533_AWA_5533.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
    }
    public void B020_MOD_CNT_INF() throws IOException,SQLException,Exception {
        WS_CHK_CNT_FLG = 'N';
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (CIB5533_AWA_5533.ADR_AREA[WS_I-1].FUNC != ' ') {
                if (CIB5533_AWA_5533.ADR_AREA[WS_I-1].FUNC == 'D') {
                    WS_CHK_CNT_FLG = 'Y';
                }
                IBS.init(SCCGWA, CICSMCNT);
                CICSMCNT.DATA.CI_NO = CIB5533_AWA_5533.CI_NO;
                CICSMCNT.FUNC = CIB5533_AWA_5533.ADR_AREA[WS_I-1].FUNC;
                CICSMCNT.DATA.CNT_TYPE = CIB5533_AWA_5533.ADR_AREA[WS_I-1].CNT_TYPE;
                CICSMCNT.DATA.CNT_CNTY = CIB5533_AWA_5533.ADR_AREA[WS_I-1].CNT_CNTY;
                CICSMCNT.DATA.CNT_TECN = CIB5533_AWA_5533.ADR_AREA[WS_I-1].CNT_TECN;
                CICSMCNT.DATA.CNT_ZONE = CIB5533_AWA_5533.ADR_AREA[WS_I-1].CNT_ZONE;
                CICSMCNT.DATA.CNT_TEL = CIB5533_AWA_5533.ADR_AREA[WS_I-1].CNT_TEL;
                CICSMCNT.DATA.CNT_TEL2 = CIB5533_AWA_5533.ADR_AREA[WS_I-1].CNT_TEL2;
                CICSMCNT.DATA.CNT_INFO = CIB5533_AWA_5533.ADR_AREA[WS_I-1].CNT_INFO;
                S000_CALL_CIZSMCNT();
            }
        }
        if (WS_CHK_CNT_FLG == 'Y') {
            R000_CHK_CNT();
        }
        IBS.init(SCCGWA, CICCMCK);
        CICCMCK.DATA.CI_NO = CIB5533_AWA_5533.CI_NO;
        CICCMCK.DATA.TABLE_FLG = 'W';
        S000_CALL_CIZCMCK();
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CIB5533_AWA_5533.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRCNT";
        S000_CALL_BPZPNHIS();
    }
    public void R000_CHK_CNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIB5533_AWA_5533.CI_NO;
        T000_READ_CITBAS();
        if (CIRBAS.CI_TYP == '1') {
            IBS.init(SCCGWA, CIRCNT);
            CIRCNT.KEY.CI_NO = CIB5533_AWA_5533.CI_NO;
            T000_READ_CITCNT_CHK();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "个人不能删除�?有联络电�?");
            }
        }
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
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITCNT_CHK() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.eqWhere = "CI_NO";
        CITCNT_RD.where = "CNT_TYPE IN ( '11' , '12' , '21' )";
        CITCNT_RD.fst = true;
        IBS.READ(SCCGWA, CIRCNT, this, CITCNT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
