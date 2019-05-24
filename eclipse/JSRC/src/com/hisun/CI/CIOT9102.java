package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9102 {
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
    CIB9102_AWA_9102 CIB9102_AWA_9102;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9102 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9102_AWA_9102>");
        CIB9102_AWA_9102 = (CIB9102_AWA_9102) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMCC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_OPEN_COM_CUST();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9102_AWA_9102.ID_TYPE);
        CEP.TRC(SCCGWA, CIB9102_AWA_9102.ID_NO);
        CEP.TRC(SCCGWA, CIB9102_AWA_9102.CI_NM);
    }
    public void B020_OPEN_COM_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSMCC);
        CICSMCC.DATA.CI_NO = CIB9102_AWA_9102.CI_NO;
        CICSMCC.DATA.CI_TYP = '2';
        CICSMCC.DATA.CI_ATTR = '1';
        CICSMCC.DATA.CI_NM = CIB9102_AWA_9102.CI_NM;
        CICSMCC.DATA.CI_ENM = CIB9102_AWA_9102.CI_ENM;
        CICSMCC.DATA.CI_ONM = CIB9102_AWA_9102.CI_ONM;
        CICSMCC.DATA.ID_TYPE = CIB9102_AWA_9102.ID_TYPE;
        CICSMCC.DATA.ID_NO = CIB9102_AWA_9102.ID_NO;
        CICSMCC.DATA.ID_RMK = CIB9102_AWA_9102.ID_RMK;
        CICSMCC.DATA.SUB_TYP = CIB9102_AWA_9102.SUB_TYP;
        CICSMCC.DATA.RESIDENT = CIB9102_AWA_9102.RESIDENT;
        CICSMCC.DATA.ORG_TYPE = CIB9102_AWA_9102.ORG_TYPE;
        CICSMCC.DATA.ECO = CIB9102_AWA_9102.ECO;
        CICSMCC.DATA.HECO = CIB9102_AWA_9102.HECO;
        CICSMCC.DATA.INDUS1 = CIB9102_AWA_9102.INDUS1;
        CICSMCC.DATA.INDUS2 = CIB9102_AWA_9102.INDUS2;
        CICSMCC.DATA.ENC_CD = CIB9102_AWA_9102.ENC_CD;
        CICSMCC.DATA.SID_FLG = CIB9102_AWA_9102.SID_FLG;
        CICSMCC.DATA.SIZE = CIB9102_AWA_9102.SIZE;
        CICSMCC.DATA.BUSN_SCP = CIB9102_AWA_9102.BUSN_SCP;
        CICSMCC.DATA.REG_DT = CIB9102_AWA_9102.REG_DT;
        CICSMCC.DATA.REG_CCY = CIB9102_AWA_9102.REG_CCY;
        CICSMCC.DATA.REG_AMT = CIB9102_AWA_9102.REG_AMT;
        CICSMCC.DATA.HCNTY = CIB9102_AWA_9102.HCNTY;
        CICSMCC.DATA.SVR_LVL = CIB9102_AWA_9102.SVR_LVL;
        CICSMCC.DATA.NED_TYP = CIB9102_AWA_9102.NED_TYP;
        CICSMCC.DATA.DEP_TYPE = CIB9102_AWA_9102.DEP_TYPE;
        CICSMCC.DATA.PB_AP_NO = CIB9102_AWA_9102.PB_AP_NO;
        CICSMCC.DATA.PB_BANK = CIB9102_AWA_9102.PB_BANK;
        CICSMCC.DATA.PB_AC_NO = CIB9102_AWA_9102.PB_AC_NO;
        CICSMCC.DATA.OIC_NO = CIB9102_AWA_9102.OIC_NO;
        CICSMCC.DATA.PROD_TYP = CIB9102_AWA_9102.PROD_TYP;
        CICSMCC.DATA.STK_NO = CIB9102_AWA_9102.STK_NO;
        CICSMCC.DATA.LST_CITY = CIB9102_AWA_9102.LST_CITY;
        CICSMCC.DATA.REMARK = CIB9102_AWA_9102.REMARK;
        CICSMCC.DATA.OPER_INC = CIB9102_AWA_9102.OPER_INC;
        CICSMCC.DATA.REVENUE = CIB9102_AWA_9102.REVENUE;
        CICSMCC.DATA.EMP_NUM = CIB9102_AWA_9102.EMP_NUM;
        CICSMCC.DATA.TOTAL_ASS = CIB9102_AWA_9102.TOTL_ASS;
        CICSMCC.DATA.NET_ASS = CIB9102_AWA_9102.NET_ASS;
        CICSMCC.DATA.CAP_AMT = CIB9102_AWA_9102.CAP_AMT;
        CICSMCC.DATA.CAP_CCY = CIB9102_AWA_9102.CAP_CCY;
        CICSMCC.DATA.PL_FLG = CIB9102_AWA_9102.PL_FLG;
        CICSMCC.DATA.PEA_FLG = CIB9102_AWA_9102.PEA_FLG;
        CICSMCC.DATA.GRE_FLG = CIB9102_AWA_9102.GRE_FLG;
        CICSMCC.DATA.NRA_TAX = CIB9102_AWA_9102.NRA_TAX;
        CICSMCC.DATA.ORGIN_TP = CIB9102_AWA_9102.ORGIN_TP;
        CICSMCC.DATA.ORGIN1 = CIB9102_AWA_9102.ORGIN1;
        CICSMCC.DATA.ORGIN2 = CIB9102_AWA_9102.ORGIN2;
        CICSMCC.DATA.RESP_CD = CIB9102_AWA_9102.RESP_CD;
        CICSMCC.DATA.SUB_DP = CIB9102_AWA_9102.SUB_DP;
        CICSMCC.DATA.SUP_NM = CIB9102_AWA_9102.SUP_NM;
        CICSMCC.DATA.SUP_ORNO = CIB9102_AWA_9102.SUP_ORTP;
        CICSMCC.DATA.SUP_APNO = CIB9102_AWA_9102.SUP_APNO;
        CICSMCC.DATA.CNT1_NM = CIB9102_AWA_9102.CNT1_NM;
        CICSMCC.DATA.CNT1_MOB = CIB9102_AWA_9102.CNT1_MOB;
        CICSMCC.DATA.CNT1_TEL = CIB9102_AWA_9102.CNT1_TEL;
        CICSMCC.DATA.CNT2_NM = CIB9102_AWA_9102.CNT2_NM;
        CICSMCC.DATA.CNT2_MOB = CIB9102_AWA_9102.CNT2_MOB;
        CICSMCC.DATA.CNT2_TEL = CIB9102_AWA_9102.CNT2_TEL;
        CICSMCC.DATA.DIR_NM = CIB9102_AWA_9102.DIR_NM;
        CICSMCC.DATA.DIR_MOB = CIB9102_AWA_9102.DIR_MOB;
        CICSMCC.DATA.DIR_TEL = CIB9102_AWA_9102.DIR_TEL;
        CICSMCC.DATA.TAX_BANK = CIB9102_AWA_9102.TAX_BANK;
        CICSMCC.DATA.TAX_ACNO = CIB9102_AWA_9102.TAX_ACNO;
        CICSMCC.DATA.TAX_TYPE = CIB9102_AWA_9102.TAX_TYPE;
        CICSMCC.DATA.TAX_DSNO = CIB9102_AWA_9102.TAX_DSNO;
        CICSMCC.DATA.FATCA_TP = CIB9102_AWA_9102.FATCA_TP;
        CICSMCC.DATA.W8W9_DT = CIB9102_AWA_9102.W8W9_DT;
        CICSMCC.DATA.TIN_NO = CIB9102_AWA_9102.TIN_NO;
        CICSMCC.DATA.GIIN_CD = CIB9102_AWA_9102.GIIN_CD;
        CICSMCC.DATA.CRS_TYPE = CIB9102_AWA_9102.CRS_TYPE;
        CICSMCC.DATA.CRS_DESC = CIB9102_AWA_9102.CRS_DESC;
        CICSMCC.DATA.PROOF_DT = CIB9102_AWA_9102.PROOF_DT;
        CICSMCC.DATA.PROOF_CH = CIB9102_AWA_9102.PROOF_CH;
        CICSMCC.DATA.CDG_EMP = CIB9102_AWA_9102.CDG_EMP;
        CICSMCC.DATA.CSTS_TYP = CIB9102_AWA_9102.CSTS_TYP;
        CICSMCC.DATA.FLTS_NO = CIB9102_AWA_9102.FLTS_NO;
        CICSMCC.DATA.SCH_DT = CIB9102_AWA_9102.SCH_DT;
        CICSMCC.DATA.HKC_TYP = CIB9102_AWA_9102.HKC_TYP;
        for (WS_I = 1; WS_I <= 25; WS_I += 1) {
            CICSMCC.DATA.CRS_AREA[WS_I-1].CRS_ADR = CIB9102_AWA_9102.CRS_AREA[WS_I-1].CRS_ADR;
            CICSMCC.DATA.CRS_AREA[WS_I-1].CRS_DSNO = CIB9102_AWA_9102.CRS_AREA[WS_I-1].CRS_DSNO;
            CICSMCC.DATA.CRS_AREA[WS_I-1].CRS_NDCD = CIB9102_AWA_9102.CRS_AREA[WS_I-1].CRS_NDCD;
            CICSMCC.DATA.CRS_AREA[WS_I-1].CRS_NDRE = CIB9102_AWA_9102.CRS_AREA[WS_I-1].CRS_NDRE;
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