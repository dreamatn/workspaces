package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5543 {
    DBParm CITBAS_RD;
    DBParm CITRELN_RD;
    DBParm CITCDM_RD;
    short WS_I = 0;
    char WS_CHK_REL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSMREL CICSMREL = new CICSMREL();
    CICCMCK CICCMCK = new CICCMCK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRRELN CIRRELN = new CIRRELN();
    CIRCDM CIRCDM = new CIRCDM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5543_AWA_5543 CIB5543_AWA_5543;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5543 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5543_AWA_5543>");
        CIB5543_AWA_5543 = (CIB5543_AWA_5543) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMREL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_MOD_REL_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5543_AWA_5543.CI_NO);
        if (CIB5543_AWA_5543.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
    }
    public void B020_MOD_REL_INF() throws IOException,SQLException,Exception {
        WS_CHK_REL_FLG = 'Y';
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (CIB5543_AWA_5543.ADR_AREA[WS_I-1].FUNC != ' ') {
                if (CIB5543_AWA_5543.ADR_AREA[WS_I-1].FUNC == 'D') {
                    WS_CHK_REL_FLG = 'Y';
                }
                IBS.init(SCCGWA, CICSMREL);
                CICSMREL.DATA.CI_NO = CIB5543_AWA_5543.CI_NO;
                CICSMREL.FUNC = CIB5543_AWA_5543.ADR_AREA[WS_I-1].FUNC;
                CICSMREL.DATA.REL_RECD = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_RECD;
                CICSMREL.DATA.REL_NAME = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_NAME;
                CICSMREL.DATA.REL_IDTP = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_IDTP;
                CICSMREL.DATA.REL_IDNO = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_IDNO;
                CICSMREL.DATA.REL_IDEX = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_IDEX;
                CICSMREL.DATA.REL_CNTY = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_CNTY;
                CICSMREL.DATA.REL_MOB = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_MOB;
                CICSMREL.DATA.REL_TEL = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_TEL;
                CICSMREL.DATA.REL_SEX = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_SEX;
                CICSMREL.DATA.REL_ADCN = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_ADCN;
                CICSMREL.DATA.REL_ADDR = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_ADDR;
                CICSMREL.DATA.REL_OCNM = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_OCNM;
                CICSMREL.DATA.REL_BIRT = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_BIRT;
                CICSMREL.DATA.REL_OCCU = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_OCCU;
                CICSMREL.DATA.REL_HOLD = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_HOLD;
                CICSMREL.DATA.REL_SHDT = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_SHDT;
                CICSMREL.DATA.REL_NM1 = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_NM1;
                CICSMREL.DATA.REL_NM2 = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_NM2;
                CICSMREL.DATA.REL_RSDT = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_RSDT;
                CICSMREL.DATA.REL_BRCT = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_BRCT;
                CICSMREL.DATA.REL_FMCT = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_FMCT;
                CICSMREL.DATA.REL_FMAD = CIB5543_AWA_5543.ADR_AREA[WS_I-1].REL_FMAD;
                S000_CALL_CIZSMREL();
            }
        }
        if (WS_CHK_REL_FLG == 'Y') {
            R000_CHK_REL();
        }
        IBS.init(SCCGWA, CICCMCK);
        CICCMCK.DATA.CI_NO = CIB5543_AWA_5543.CI_NO;
        CICCMCK.DATA.TABLE_FLG = 'W';
        S000_CALL_CIZCMCK();
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CIB5543_AWA_5543.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRRELN";
        S000_CALL_BPZPNHIS();
    }
    public void R000_CHK_REL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIB5543_AWA_5543.CI_NO;
        T000_READ_CITBAS();
        if (CIRBAS.CI_TYP == '2') {
            IBS.init(SCCGWA, CIRCDM);
            CIRCDM.KEY.CI_NO = CIB5543_AWA_5543.CI_NO;
            T000_READ_CITCDM();
            if (CIRCDM.SID_FLG == '1') {
                IBS.init(SCCGWA, CIRRELN);
                CIRRELN.KEY.CI_NO = CIB5543_AWA_5543.CI_NO;
                CIRRELN.KEY.CIREL_CD = "20101";
                T000_READ_CITRELN_CHK();
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    IBS.init(SCCGWA, CIRRELN);
                    CIRRELN.KEY.CI_NO = CIB5543_AWA_5543.CI_NO;
                    CIRRELN.KEY.CIREL_CD = "20201";
                    T000_READ_CITRELN_CHK();
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                        CEP.TRC(SCCGWA, "DELETE 20101 20201");
                        CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "公司法人负责人不能全删除");
                    }
                }
            } else {
                IBS.init(SCCGWA, CIRRELN);
                CIRRELN.KEY.CI_NO = CIB5543_AWA_5543.CI_NO;
                CIRRELN.KEY.CIREL_CD = "20102";
                T000_READ_CITRELN_CHK();
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    CEP.TRC(SCCGWA, "DELETE 20102");
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "公司不能删除董事");
                }
            }
            IBS.init(SCCGWA, CIRRELN);
            CIRRELN.KEY.CI_NO = CIB5543_AWA_5543.CI_NO;
            CIRRELN.KEY.CIREL_CD = "20204";
            T000_READ_CITRELN_CHK();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "公司不能删除财务经理");
            }
            IBS.init(SCCGWA, CIRRELN);
            CIRRELN.KEY.CI_NO = CIB5543_AWA_5543.CI_NO;
            CIRRELN.KEY.CIREL_CD = "29999";
            T000_READ_CITRELN_CHK();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "公司不能删除受益�?有人");
            }
        }
        if (CIRBAS.CI_TYP == '3') {
            IBS.init(SCCGWA, CIRRELN);
            CIRRELN.KEY.CI_NO = CIB5543_AWA_5543.CI_NO;
            CIRRELN.KEY.CIREL_CD = "20201";
            T000_READ_CITRELN_CHK();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "公司不能删除单位负责�?");
            }
        }
    }
    public void EXTI() throws IOException,SQLException,Exception {
    public void S000_CALL_CIZSMREL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-M-REL-INF", CICSMREL);
        if (CICSMREL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSMREL.RC);
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
    public void T000_READ_CITRELN_CHK() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        CITRELN_RD.eqWhere = "CI_NO,CIREL_CD";
        CITRELN_RD.fst = true;
        IBS.READ(SCCGWA, CIRRELN, CITRELN_RD);
    }
    public void T000_READ_CITCDM() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        CITCDM_RD.eqWhere = "CI_NO";
        CITCDM_RD.fst = true;
        IBS.READ(SCCGWA, CIRCDM, CITCDM_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
