package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSQPC {
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITPDM_RD;
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    DBParm CITRISK_RD;
    brParm CITCRS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_I = 0;
    String WS_NM_TP = " ";
    String WS_CI_NM = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRPDM CIRPDM = new CIRPDM();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRRISK CIRRISK = new CIRRISK();
    CIRCRS CIRCRS = new CIRCRS();
    CICOSQPC CICOSQPC = new CICOSQPC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSQPC CICSQPC;
    public void MP(SCCGWA SCCGWA, CICSQPC CICSQPC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSQPC = CICSQPC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSQPC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQPC.RC);
        IBS.init(SCCGWA, CICOSQPC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_PER_BAS_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_PER_BAS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSQPC.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            || CIRBAS.CI_ATTR != '1') {
            CEP.TRC(SCCGWA, "BAS INF NOT FND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP != '1') {
            CEP.TRC(SCCGWA, "CI TYP ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TYP_INPUT_ERR);
        }
        R000_TRANS_BAS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRPDM);
        CIRPDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITPDM();
        if (pgmRtn) return;
        R000_TRANS_PDM_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRID.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        CICOSQPC.DATA.ID_RMK = CIRID.REMARK;
        for (WS_I = 1; WS_I <= 6; WS_I += 1) {
            IBS.init(SCCGWA, CIRNAM);
            WS_CI_NM = " ";
            CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
            if (WS_I == 1) {
                CIRNAM.KEY.NM_TYPE = "03";
                B021_INQ_NAM_INF();
                if (pgmRtn) return;
                CICOSQPC.DATA.CI_ENM = WS_CI_NM;
            } else if (WS_I == 2) {
                CIRNAM.KEY.NM_TYPE = "05";
                B021_INQ_NAM_INF();
                if (pgmRtn) return;
                CICOSQPC.DATA.CI_FNM = WS_CI_NM;
            } else if (WS_I == 3) {
                CIRNAM.KEY.NM_TYPE = "06";
                B021_INQ_NAM_INF();
                if (pgmRtn) return;
                CICOSQPC.DATA.CI_SNM = WS_CI_NM;
            } else if (WS_I == 4) {
                CIRNAM.KEY.NM_TYPE = "07";
                B021_INQ_NAM_INF();
                if (pgmRtn) return;
                CICOSQPC.DATA.CI_OFNM = WS_CI_NM;
            } else if (WS_I == 5) {
                CIRNAM.KEY.NM_TYPE = "08";
                B021_INQ_NAM_INF();
                if (pgmRtn) return;
                CICOSQPC.DATA.CI_OSNM = WS_CI_NM;
            } else {
                CIRNAM.KEY.NM_TYPE = "09";
                B021_INQ_NAM_INF();
                if (pgmRtn) return;
                CICOSQPC.DATA.CI_ONM = WS_CI_NM;
            }
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
    public void B021_INQ_NAM_INF() throws IOException,SQLException,Exception {
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CI_NM = CIRNAM.CI_NM;
        }
    }
    public void R000_TRANS_BAS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQPC.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICOSQPC.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICOSQPC.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICOSQPC.DATA.STSW = CIRBAS.STSW;
        CICOSQPC.DATA.IDE_STSW = CIRBAS.IDE_STSW;
        CICOSQPC.DATA.CI_NM = CIRBAS.CI_NM;
        CICOSQPC.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOSQPC.DATA.ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
        JIBS_tmp_int = CIRBAS.VER_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
        if (CIRBAS.VER_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICOSQPC.DATA.VER_FLG = 'T';
        } else {
            CICOSQPC.DATA.VER_FLG = 'F';
        }
        CICOSQPC.DATA.OIC_NO = CIRBAS.OIC_NO;
        CICOSQPC.DATA.NRA_TAX = CIRBAS.NRA_TAX_TYP;
        CICOSQPC.DATA.ORGIN_TP = CIRBAS.ORGIN_TP;
        CICOSQPC.DATA.ORGIN1 = CIRBAS.ORIGIN;
        CICOSQPC.DATA.ORGIN2 = CIRBAS.ORIGIN2;
        CICOSQPC.DATA.RESP_CD = CIRBAS.RESP_CD;
        CICOSQPC.DATA.SUB_DP = CIRBAS.SUB_DP;
        CICOSQPC.DATA.TAX_BANK = CIRBAS.TAX_BANK;
        CICOSQPC.DATA.TAX_ACNO = CIRBAS.TAX_AC_NO;
        CICOSQPC.DATA.TAX_TYPE = CIRBAS.TAX_TYPE;
        CICOSQPC.DATA.TAX_DSNO = CIRBAS.TAX_DIST_NO;
    }
    public void R000_TRANS_PDM_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQPC.DATA.SUB_TYP = CIRPDM.CI_SUB_TYP;
        CICOSQPC.DATA.RESIDENT = CIRPDM.RESIDENT;
        CICOSQPC.DATA.SEX = CIRPDM.SEX;
        CICOSQPC.DATA.BIRTH_DT = CIRPDM.BIRTH_DT;
        CICOSQPC.DATA.REG_CNTY = CIRPDM.REG_CNTY;
        CICOSQPC.DATA.NATION = CIRPDM.NATION;
        CICOSQPC.DATA.OCCUP = CIRPDM.OCCUP;
        CICOSQPC.DATA.MARI = CIRPDM.MARI;
        CICOSQPC.DATA.SID_FLG = CIRPDM.SID_FLG;
        CICOSQPC.DATA.BIRTH_RG = CIRPDM.BIRTH_RGN;
        CICOSQPC.DATA.EDU_LVL = CIRPDM.EDU_LVL;
        CICOSQPC.DATA.PER_INC = CIRPDM.PER_INC;
        CICOSQPC.DATA.FAM_INC = CIRPDM.FAM_INC;
        CICOSQPC.DATA.CORP_NM = CIRPDM.CORP_NM;
        CICOSQPC.DATA.CORP_TYP = CIRPDM.CORP_TYP;
        CICOSQPC.DATA.CORP_IND = CIRPDM.CORP_INDUS;
        CICOSQPC.DATA.WK_TITL = CIRPDM.WK_TITL;
        CICOSQPC.DATA.REMARK = CIRPDM.REMARK;
        CICOSQPC.DATA.HKC_TYP = CIRPDM.HK_CI_TYP;
    }
    public void R000_TRANS_RISK_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQPC.DATA.FATCA_TP = CIRRISK.FATCA_TP;
        CICOSQPC.DATA.W8W9_DT = CIRRISK.W8W9_DT;
        CICOSQPC.DATA.TIN_NO = CIRRISK.TIN_NO;
        CICOSQPC.DATA.GIIN_CD = CIRRISK.GIIN_CODE;
        CICOSQPC.DATA.CRS_TYPE = CIRRISK.CRS_TYPE;
        CICOSQPC.DATA.CRS_DESC = CIRRISK.CRS_DESC;
        CICOSQPC.DATA.PROOF_DT = CIRRISK.PROOF_DT;
        CICOSQPC.DATA.PROOF_CH = CIRRISK.PROOF_CHNL;
    }
    public void R000_TRANS_CRS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQPC.DATA.CRS_AREA[WS_I-1].CRS_ADR = CIRCRS.KEY.TAX_ADR;
        CICOSQPC.DATA.CRS_AREA[WS_I-1].CRS_DSNO = CIRCRS.TAX_DIST_NO;
        CICOSQPC.DATA.CRS_AREA[WS_I-1].CRS_NDCD = CIRCRS.NO_DIST_CODE;
        CICOSQPC.DATA.CRS_AREA[WS_I-1].CRS_NDRE = CIRCRS.NO_DIST_REASON;
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIO03";
        SCCFMT.DATA_PTR = CICOSQPC;
        SCCFMT.DATA_LEN = 6008;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITPDM() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        IBS.READ(SCCGWA, CIRPDM, CITPDM_RD);
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
