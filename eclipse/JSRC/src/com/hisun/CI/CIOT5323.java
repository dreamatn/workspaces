package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5323 {
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSMCC CICSMCC = new CICSMCC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5323_AWA_5323 CIB5323_AWA_5323;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5323 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5323_AWA_5323>");
        CIB5323_AWA_5323 = (CIB5323_AWA_5323) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMCC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_OPEN_COM_CUST();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5323_AWA_5323.ID_TYPE);
        CEP.TRC(SCCGWA, CIB5323_AWA_5323.ID_NO);
        CEP.TRC(SCCGWA, CIB5323_AWA_5323.CI_NM);
    }
    public void B020_OPEN_COM_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSMCC);
        CICSMCC.DATA.CI_NO = CIB5323_AWA_5323.CI_NO;
        CICSMCC.DATA.CI_TYP = CIB5323_AWA_5323.CI_TYP;
        CICSMCC.DATA.CI_ATTR = CIB5323_AWA_5323.CI_ATTR;
        CICSMCC.DATA.CI_NM = CIB5323_AWA_5323.CI_NM;
        CICSMCC.DATA.CI_ENM = CIB5323_AWA_5323.CI_ENM;
        CICSMCC.DATA.CI_ONM = CIB5323_AWA_5323.CI_ONM;
        CICSMCC.DATA.ID_TYPE = CIB5323_AWA_5323.ID_TYPE;
        CICSMCC.DATA.ID_NO = CIB5323_AWA_5323.ID_NO;
        CICSMCC.DATA.ID_RMK = CIB5323_AWA_5323.ID_RMK;
        CICSMCC.DATA.SUB_TYP = CIB5323_AWA_5323.SUB_TYP;
        CICSMCC.DATA.RESIDENT = CIB5323_AWA_5323.RESIDENT;
        CICSMCC.DATA.ORG_TYPE = CIB5323_AWA_5323.ORG_TYPE;
        CICSMCC.DATA.ECO = CIB5323_AWA_5323.ECO;
        CICSMCC.DATA.HECO = CIB5323_AWA_5323.HECO;
        CICSMCC.DATA.INDUS1 = CIB5323_AWA_5323.INDUS1;
        CICSMCC.DATA.ENC_CD = CIB5323_AWA_5323.ENC_CD;
        CICSMCC.DATA.SID_FLG = CIB5323_AWA_5323.SID_FLG;
        CICSMCC.DATA.SIZE = CIB5323_AWA_5323.SIZE;
        CICSMCC.DATA.BUSN_SCP = CIB5323_AWA_5323.BUSN_SCP;
        CICSMCC.DATA.REG_DT = CIB5323_AWA_5323.REG_DT;
        CICSMCC.DATA.REG_CCY = CIB5323_AWA_5323.REG_CCY;
        CICSMCC.DATA.REG_AMT = CIB5323_AWA_5323.REG_AMT;
        CICSMCC.DATA.HCNTY = CIB5323_AWA_5323.HCNTY;
        CICSMCC.DATA.SVR_LVL = CIB5323_AWA_5323.SVR_LVL;
        CICSMCC.DATA.NED_TYP = CIB5323_AWA_5323.NED_TYP;
        CICSMCC.DATA.DEP_TYPE = CIB5323_AWA_5323.DEP_TYPE;
        CICSMCC.DATA.PB_AP_NO = CIB5323_AWA_5323.PB_AP_NO;
        CICSMCC.DATA.PB_BANK = CIB5323_AWA_5323.PB_BANK;
        CICSMCC.DATA.PB_AC_NO = CIB5323_AWA_5323.PB_AC_NO;
        CICSMCC.DATA.OIC_NO = CIB5323_AWA_5323.OIC_NO;
        CICSMCC.DATA.PROD_TYP = CIB5323_AWA_5323.PROD_TYP;
        CICSMCC.DATA.STK_NO = CIB5323_AWA_5323.STK_NO;
        CICSMCC.DATA.LST_CITY = CIB5323_AWA_5323.LST_CITY;
        CICSMCC.DATA.REMARK = CIB5323_AWA_5323.REMARK;
        CICSMCC.DATA.OPER_INC = CIB5323_AWA_5323.OPER_INC;
        CICSMCC.DATA.REVENUE = CIB5323_AWA_5323.REVENUE;
        CICSMCC.DATA.EMP_NUM = CIB5323_AWA_5323.EMP_NUM;
        CICSMCC.DATA.TOTAL_ASS = CIB5323_AWA_5323.TOTL_ASS;
        CICSMCC.DATA.NET_ASS = CIB5323_AWA_5323.NET_ASS;
        CICSMCC.DATA.CAP_AMT = CIB5323_AWA_5323.CAP_AMT;
        CICSMCC.DATA.CAP_CCY = CIB5323_AWA_5323.CAP_CCY;
        CICSMCC.DATA.PL_FLG = CIB5323_AWA_5323.PL_FLG;
        CICSMCC.DATA.PEA_FLG = CIB5323_AWA_5323.PEA_FLG;
        CICSMCC.DATA.GRE_FLG = CIB5323_AWA_5323.GRE_FLG;
        CICSMCC.DATA.NRA_TAX = CIB5323_AWA_5323.NRA_TAX;
        CICSMCC.DATA.ORGIN_TP = CIB5323_AWA_5323.ORGIN_TP;
        CICSMCC.DATA.ORGIN1 = CIB5323_AWA_5323.ORGIN1;
        CICSMCC.DATA.ORGIN2 = CIB5323_AWA_5323.ORGIN2;
        CICSMCC.DATA.RESP_CD = CIB5323_AWA_5323.RESP_CD;
        CICSMCC.DATA.SUB_DP = CIB5323_AWA_5323.SUB_DP;
        CICSMCC.DATA.SUP_NM = CIB5323_AWA_5323.SUP_NM;
        CICSMCC.DATA.SUP_ORNO = CIB5323_AWA_5323.SUP_ORNO;
        CICSMCC.DATA.SUP_APNO = CIB5323_AWA_5323.SUP_APNO;
        CICSMCC.DATA.CNT1_NM = CIB5323_AWA_5323.CNT1_NM;
        CICSMCC.DATA.CNT1_MOB = CIB5323_AWA_5323.CNT1_MOB;
        CICSMCC.DATA.CNT1_TEL = CIB5323_AWA_5323.CNT1_TEL;
        CICSMCC.DATA.CNT2_NM = CIB5323_AWA_5323.CNT2_NM;
        CICSMCC.DATA.CNT2_MOB = CIB5323_AWA_5323.CNT2_MOB;
        CICSMCC.DATA.CNT2_TEL = CIB5323_AWA_5323.CNT2_TEL;
        CICSMCC.DATA.DIR_NM = CIB5323_AWA_5323.DIR_NM;
        CICSMCC.DATA.DIR_MOB = CIB5323_AWA_5323.DIR_MOB;
        CICSMCC.DATA.DIR_TEL = CIB5323_AWA_5323.DIR_TEL;
        CICSMCC.DATA.TAX_BANK = CIB5323_AWA_5323.TAX_BANK;
        CICSMCC.DATA.TAX_ACNO = CIB5323_AWA_5323.TAX_ACNO;
        CICSMCC.DATA.TAX_TYPE = CIB5323_AWA_5323.TAX_TYPE;
        CICSMCC.DATA.TAX_DSNO = CIB5323_AWA_5323.TAX_DSNO;
        CICSMCC.DATA.FATCA_TP = CIB5323_AWA_5323.FATCA_TP;
        CICSMCC.DATA.W8W9_DT = CIB5323_AWA_5323.W8W9_DT;
        CICSMCC.DATA.TIN_NO = CIB5323_AWA_5323.TIN_NO;
        CICSMCC.DATA.GIIN_CD = CIB5323_AWA_5323.GIIN_CD;
        CICSMCC.DATA.CRS_TYPE = CIB5323_AWA_5323.CRS_TYPE;
        CICSMCC.DATA.CRS_DESC = CIB5323_AWA_5323.CRS_DESC;
        CICSMCC.DATA.PROOF_DT = CIB5323_AWA_5323.PROOF_DT;
        CICSMCC.DATA.PROOF_CH = CIB5323_AWA_5323.PROOF_CH;
        CICSMCC.DATA.CDG_EMP = CIB5323_AWA_5323.CDG_EMP;
        CICSMCC.DATA.INDUS2 = CIB5323_AWA_5323.INDUS2;
        CICSMCC.DATA.CSTS_TYP = CIB5323_AWA_5323.CSTS_TYP;
        CICSMCC.DATA.FLTS_NO = CIB5323_AWA_5323.FLTS_NO;
        CICSMCC.DATA.SCH_DT = CIB5323_AWA_5323.SCH_DT;
        CICSMCC.DATA.HKC_TYP = CIB5323_AWA_5323.HKC_TYP;
        for (WS_I = 1; WS_I <= 25; WS_I += 1) {
            CICSMCC.DATA.CRS_AREA[WS_I-1].CRS_ADR = CIB5323_AWA_5323.CRS_AREA[WS_I-1].CRS_ADR;
            CICSMCC.DATA.CRS_AREA[WS_I-1].CRS_DSNO = CIB5323_AWA_5323.CRS_AREA[WS_I-1].CRS_DSNO;
            CICSMCC.DATA.CRS_AREA[WS_I-1].CRS_NDCD = CIB5323_AWA_5323.CRS_AREA[WS_I-1].CRS_NDCD;
            CICSMCC.DATA.CRS_AREA[WS_I-1].CRS_NDRE = CIB5323_AWA_5323.CRS_AREA[WS_I-1].CRS_NDRE;
        }
        S000_CALL_CIZSMCC();
    }
    public void S000_CALL_CIZSMCC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-M-COM-INF", CICSMCC);
        if (CICSMCC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSMCC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}