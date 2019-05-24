package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSQFC {
    DBParm CITBAS_RD;
    DBParm CITFDM_RD;
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    DBParm CITRISK_RD;
    brParm CITCRS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRFDM CIRFDM = new CIRFDM();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRRISK CIRRISK = new CIRRISK();
    CIRCRS CIRCRS = new CIRCRS();
    CICOSQFC CICOSQFC = new CICOSQFC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSQFC CICSQFC;
    public void MP(SCCGWA SCCGWA, CICSQFC CICSQFC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSQFC = CICSQFC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSQFC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQFC.RC);
        IBS.init(SCCGWA, CICOSQFC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_FIN_BAS_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_FIN_BAS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSQFC.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            || CIRBAS.CI_ATTR != '1') {
            CEP.TRC(SCCGWA, "BAS INF NOT FND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP != '3') {
            CEP.TRC(SCCGWA, "CI TYP ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TYP_INPUT_ERR);
        }
        R000_TRANS_BAS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRFDM);
        CIRFDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITFDM();
        if (pgmRtn) return;
        R000_TRANS_FDM_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRID.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        CICOSQFC.DATA.ID_RMK = CIRID.REMARK;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICOSQFC.DATA.CI_ENM = CIRNAM.CI_NM;
        }
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "09";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICOSQFC.DATA.CI_ONM = CIRNAM.CI_NM;
        }
        IBS.init(SCCGWA, CIRRISK);
        CIRRISK.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITRISK();
        if (pgmRtn) return;
        R000_TRANS_RISK_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRCRS);
        CIRCRS.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRCRS.KEY.INFO_TYP = '1';
        T000_STARTBR_CITCRS();
        if (pgmRtn) return;
        T000_READNEXT_CITCRS();
        if (pgmRtn) return;
        for (WS_I = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_I <= 25; WS_I += 1) {
            R000_TRANS_CRS_DATA_TO_OUTPUT();
            if (pgmRtn) return;
            T000_READNEXT_CITCRS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCRS();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_TRANS_BAS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQFC.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICOSQFC.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICOSQFC.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICOSQFC.DATA.STSW = CIRBAS.STSW;
        CICOSQFC.DATA.IDE_STSW = CIRBAS.IDE_STSW;
        CICOSQFC.DATA.CI_NM = CIRBAS.CI_NM;
        CICOSQFC.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOSQFC.DATA.ID_NO = CIRBAS.ID_NO;
        CICOSQFC.DATA.OIC_NO = CIRBAS.OIC_NO;
        CICOSQFC.DATA.NRA_TAX = CIRBAS.NRA_TAX_TYP;
        CICOSQFC.DATA.ORGIN_TP = CIRBAS.ORGIN_TP;
        CICOSQFC.DATA.ORGIN1 = CIRBAS.ORIGIN;
        CICOSQFC.DATA.ORGIN2 = CIRBAS.ORIGIN2;
        CICOSQFC.DATA.RESP_CD = CIRBAS.RESP_CD;
        CICOSQFC.DATA.SUB_DP = CIRBAS.SUB_DP;
        CICOSQFC.DATA.TAX_BANK = CIRBAS.TAX_BANK;
        CICOSQFC.DATA.TAX_ACNO = CIRBAS.TAX_AC_NO;
        CICOSQFC.DATA.TAX_TYPE = CIRBAS.TAX_TYPE;
        CICOSQFC.DATA.TAX_DSNO = CIRBAS.TAX_DIST_NO;
        CICOSQFC.DATA.SCH_DT = CIRBAS.SEARCH_DT;
        CEP.TRC(SCCGWA, CIRBAS.SEARCH_DT);
    }
    public void R000_TRANS_FDM_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRFDM.CI_SUB_TYP);
        CICOSQFC.DATA.SUB_TYP = CIRFDM.CI_SUB_TYP;
        CEP.TRC(SCCGWA, CICOSQFC.DATA.SUB_TYP);
        CICOSQFC.DATA.RESIDENT = CIRFDM.RESIDENT;
        CICOSQFC.DATA.ORG_TYPE = CIRFDM.ORG_TYPE;
        CICOSQFC.DATA.FIN_TYPE = CIRFDM.FIN_TYPE;
        CICOSQFC.DATA.ECO = CIRFDM.ECO;
        CICOSQFC.DATA.HECO = CIRFDM.HECO;
        CICOSQFC.DATA.INDUS1 = CIRFDM.INDUS1;
        CICOSQFC.DATA.ENC_CD = CIRFDM.ENC_CD;
        CICOSQFC.DATA.SID_FLG = CIRFDM.SID_FLG;
        CICOSQFC.DATA.SIZE = CIRFDM.COMP_SIZE;
        CICOSQFC.DATA.BUSN_SCP = CIRFDM.BUSN_SCP;
        CICOSQFC.DATA.REG_DT = CIRFDM.REG_DT;
        CICOSQFC.DATA.REG_CCY = CIRFDM.REG_CCY;
        CICOSQFC.DATA.REG_AMT = CIRFDM.REG_AMT;
        CICOSQFC.DATA.HCNTY = CIRFDM.HCNTY;
        CICOSQFC.DATA.NED_TYP = CIRFDM.NED_TYP;
        CICOSQFC.DATA.DEP_TYPE = CIRFDM.DEP_TYPE;
        CICOSQFC.DATA.PB_AP_NO = CIRFDM.PB_AP_NO;
        CICOSQFC.DATA.PB_BANK = CIRFDM.PB_BANK;
        CICOSQFC.DATA.PB_AC_NO = CIRFDM.PB_AC_NO;
        CICOSQFC.DATA.PROD_TYP = CIRFDM.PROD_TYP;
        CICOSQFC.DATA.STK_NO = CIRFDM.STK_NO;
        CICOSQFC.DATA.LST_CITY = CIRFDM.LST_CITY;
        CICOSQFC.DATA.REMARK = CIRFDM.REMARK;
        CICOSQFC.DATA.OPER_INC = CIRFDM.OPER_INC;
        CICOSQFC.DATA.REVENUE = CIRFDM.REVENUE;
        CICOSQFC.DATA.EMP_NUM = CIRFDM.EMP_NUM;
        CICOSQFC.DATA.TOTAL_ASS = CIRFDM.TOTAL_ASS;
        CICOSQFC.DATA.NET_ASS = CIRFDM.NET_ASS;
        CICOSQFC.DATA.CAP_AMT = CIRFDM.CAP_AMT;
        CICOSQFC.DATA.CAP_CCY = CIRFDM.CAP_CCY;
        CICOSQFC.DATA.COR_NO = CIRFDM.COR_NO;
        CICOSQFC.DATA.M_COR_NO = CIRFDM.MAIN_COR_NO;
        CICOSQFC.DATA.SUP_NM = CIRFDM.SUP_NM;
        CICOSQFC.DATA.SUP_ORNO = CIRFDM.SUP_ORG_NO;
        CICOSQFC.DATA.SUP_APNO = CIRFDM.SUP_AP_NO;
        CICOSQFC.DATA.CNT1_NM = CIRFDM.CONT1_NM;
        CICOSQFC.DATA.CNT1_MOB = CIRFDM.CONT1_MOB_NO;
        CICOSQFC.DATA.CNT1_TEL = CIRFDM.CONT1_TEL_NO;
        CICOSQFC.DATA.CNT2_NM = CIRFDM.CONT2_NM;
        CICOSQFC.DATA.CNT2_MOB = CIRFDM.CONT2_MOB_NO;
        CICOSQFC.DATA.CNT2_TEL = CIRFDM.CONT2_TEL_NO;
        CICOSQFC.DATA.DIR_NM = CIRFDM.FIN_DIR_NM;
        CICOSQFC.DATA.DIR_MOB = CIRFDM.FIN_DIR_MOB_NO;
        CICOSQFC.DATA.DIR_TEL = CIRFDM.FIN_DIR_TEL_NO;
        CICOSQFC.DATA.INDUS2 = CIRFDM.INDUS2;
        CICOSQFC.DATA.HKC_TYP = CIRFDM.HK_CI_TYP;
        CICOSQFC.DATA.CSTS_TYP = CIRFDM.CUST_STS_TYPE;
        CICOSQFC.DATA.FLTS_NO = CIRFDM.FLOATERS_NO;
        CICOSQFC.DATA.TICKER = CIRFDM.TICKER;
        CEP.TRC(SCCGWA, CIRFDM.INDUS2);
        CEP.TRC(SCCGWA, CIRFDM.HK_CI_TYP);
        CEP.TRC(SCCGWA, CIRFDM.CUST_STS_TYPE);
        CEP.TRC(SCCGWA, CIRFDM.FLOATERS_NO);
    }
    public void R000_TRANS_RISK_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQFC.DATA.FATCA_TP = CIRRISK.FATCA_TP;
        CICOSQFC.DATA.W8W9_DT = CIRRISK.W8W9_DT;
        CICOSQFC.DATA.TIN_NO = CIRRISK.TIN_NO;
        CICOSQFC.DATA.GIIN_CD = CIRRISK.GIIN_CODE;
        CICOSQFC.DATA.CRS_TYPE = CIRRISK.CRS_TYPE;
        CICOSQFC.DATA.CRS_DESC = CIRRISK.CRS_DESC;
        CICOSQFC.DATA.PROOF_DT = CIRRISK.PROOF_DT;
        CICOSQFC.DATA.PROOF_CH = CIRRISK.PROOF_CHNL;
    }
    public void R000_TRANS_CRS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQFC.DATA.CRS_AREA[WS_I-1].CRS_ADR = CIRCRS.KEY.TAX_ADR;
        CICOSQFC.DATA.CRS_AREA[WS_I-1].CRS_DSNO = CIRCRS.TAX_DIST_NO;
        CICOSQFC.DATA.CRS_AREA[WS_I-1].CRS_NDCD = CIRCRS.NO_DIST_CODE;
        CICOSQFC.DATA.CRS_AREA[WS_I-1].CRS_NDRE = CIRCRS.NO_DIST_REASON;
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIO05";
        SCCFMT.DATA_PTR = CICOSQFC;
        SCCFMT.DATA_LEN = 6728;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITFDM() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
    }
    public void T000_READ_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.READ(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_READ_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_READ_CITRISK() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        IBS.READ(SCCGWA, CIRRISK, CITRISK_RD);
    }
    public void T000_STARTBR_CITCRS() throws IOException,SQLException,Exception {
        CITCRS_BR.rp = new DBParm();
        CITCRS_BR.rp.TableName = "CITCRS";
        CITCRS_BR.rp.eqWhere = "CI_NO,INFO_TYP";
        IBS.STARTBR(SCCGWA, CIRCRS, CITCRS_BR);
    }
    public void T000_READNEXT_CITCRS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCRS, this, CITCRS_BR);
    }
    public void T000_ENDBR_CITCRS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCRS_BR);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
