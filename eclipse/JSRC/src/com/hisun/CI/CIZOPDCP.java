package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.util.StringTokenizer;

import java.io.IOException;
import java.sql.SQLException;

public class CIZOPDCP {
    StringTokenizer JIBS_stb;
    DBParm CITBAS_RD;
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    DBParm CITCNT_RD;
    DBParm CITADR_RD;
    boolean pgmRtn = false;
    char K_PERSONAL_TYP = '1';
    String K_STS_NORMAL = "00";
    String K_STS_BSP = "24";
    String K_STS_INC = "23";
    String K_OUTPUT_FMT = "CI004";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "CI OPEN PERSONAL INFO  ";
    String K_HIS_CPY = "CICOPDCP";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    char WS_BAS_FLG = ' ';
    char WS_ID_FLAG = ' ';
    char WS_NAM_FLAG = ' ';
    char WS_CNT_FLAG = ' ';
    char WS_ADR_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICMPDM CICMPDM = new CICMPDM();
    CICMCNT CICMCNT = new CICMCNT();
    CICMADR CICMADR = new CICMADR();
    CICMSTS CICMSTS = new CICMSTS();
    CICSVER CICSVER = new CICSVER();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CICMID CICMID = new CICMID();
    CICMNAM CICMNAM = new CICMNAM();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRADR CIRADR = new CIRADR();
    CIRCNT CIRCNT = new CIRCNT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICOPDCP CICOPDCP;
    public void MP(SCCGWA SCCGWA, CICOPDCP CICOPDCP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICOPDCP = CICOPDCP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZOPDCP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_ADD_PDM_INF();
        if (pgmRtn) return;
        B040_ADD_ID_INF();
        if (pgmRtn) return;
        B050_ADD_NAME_INF();
        if (pgmRtn) return;
        B060_ADD_ADDR_INF();
        if (pgmRtn) return;
        B070_ADD_CNT_INF();
        if (pgmRtn) return;
        B080_ADD_STS_INF();
        if (pgmRtn) return;
        B100_ADD_VER_INF();
        if (pgmRtn) return;
        B120_CHECK_CI_STS();
        if (pgmRtn) return;
        B110_ADD_HIS_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICOPDCP.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (WS_BAS_FLG == 'Y') {
            CICOPDCP.CTLW.CTLW_2 = 'E';
        } else {
            CICOPDCP.CTLW.CTLW_2 = 'N';
        }
        if (CICOPDCP.CTLW.CTLW_1 == 'N' 
            || CICOPDCP.CTLW.CTLW_1 == 'O') {
            if (CICOPDCP.DATA.ID_TYP.trim().length() == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.ID_TYP);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_TYP_MUST_INPUT);
            }
            if (CICOPDCP.DATA.ID_NO.trim().length() == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.ID_NO);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_NO_MUST_INPUT);
            }
            if (CICOPDCP.DATA.NM_TYP1.trim().length() == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.NM_TYP1);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NMTYP_IS_EMPTY);
            }
            if (CICOPDCP.DATA.CI_NM1.trim().length() == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.CI_NM1);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NAM_MUST_INPUT);
            }
            if (CICOPDCP.DATA.BIRTH == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.BIRTH);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BIR_MUST_INPUT);
            }
            if (CICOPDCP.DATA.REG_CNTY.trim().length() == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.REG_CNTY);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CNTY_INPUT_ERR);
            }
            if (CICOPDCP.DATA.ID_EXPDT < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.ID_EXPDT);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_EXP_ERR);
            }
            if ((CICOPDCP.DATA.TEL_SRC.trim().length() == 0 
                || CICOPDCP.DATA.TEL.trim().length() == 0) 
                && (CICOPDCP.DATA.MOB_SRC.trim().length() == 0 
                || CICOPDCP.DATA.PHONE.trim().length() == 0) 
                && CICOPDCP.CTLW.CTLW_1 == 'N') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CNT_INFO_IS_EMPTY);
            }
            if (CICOPDCP.DATA.OCCP.trim().length() == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.OCCP);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_OCCP_MUST_INPUT);
            }
            if (CICOPDCP.DATA.ADR_SRC.trim().length() == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.ADR_SRC);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ADR_MUST_INPUT);
            }
            if (CICOPDCP.DATA.ADR.trim().length() == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.ADR);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ADR_MUST_INPUT);
            }
        }
        if (CICOPDCP.CTLW.CTLW_1 == 'E') {
            if (CICOPDCP.DATA.ID_TYP.trim().length() == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.ID_TYP);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_TYP_MUST_INPUT);
            }
            if (CICOPDCP.DATA.ID_NO.trim().length() == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.ID_NO);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_NO_MUST_INPUT);
            }
            if (CICOPDCP.DATA.CI_NM1.trim().length() == 0) {
                CEP.TRC(SCCGWA, CICOPDCP.DATA.CI_NM1);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NAM_MUST_INPUT);
            }
        }
        if (CICOPDCP.DATA.PAY_FLG == '1' 
            && CICOPDCP.DATA.OCCP.equalsIgnoreCase("99990")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INOCC_NOT_PAY);
        }
        if (CICOPDCP.DATA.RES_FLG == ' ') {
            if (CICOPDCP.DATA.ID_TYP.equalsIgnoreCase("10100") 
                || CICOPDCP.DATA.ID_TYP.equalsIgnoreCase("10200") 
                || CICOPDCP.DATA.ID_TYP.equalsIgnoreCase("10300") 
                || CICOPDCP.DATA.ID_TYP.equalsIgnoreCase("10501") 
                || CICOPDCP.DATA.ID_TYP.equalsIgnoreCase("10502") 
                || CICOPDCP.DATA.ID_TYP.equalsIgnoreCase("10602")) {
                CICOPDCP.DATA.RES_FLG = '1';
            } else {
                CICOPDCP.DATA.RES_FLG = '2';
            }
        }
    }
    public void B030_ADD_PDM_INF() throws IOException,SQLException,Exception {
        if (CICOPDCP.CTLW.CTLW_2 == 'N') {
            IBS.init(SCCGWA, CICMPDM);
            CICMPDM.FUNC = 'A';
            CICMPDM.INP_DATA.CI_NO = CICOPDCP.DATA.CI_NO;
            CICMPDM.INP_DATA.BAS_CI_TYP = K_PERSONAL_TYP;
            CICMPDM.INP_DATA.SEX = CICOPDCP.DATA.SEX;
            CICMPDM.INP_DATA.BIRTH_DT = CICOPDCP.DATA.BIRTH;
            CICMPDM.INP_DATA.REG_CNTY = CICOPDCP.DATA.REG_CNTY;
            CICMPDM.INP_DATA.OCCUP = CICOPDCP.DATA.OCCP;
            CICMPDM.INP_DATA.BAS_SVR_LVL = "N";
            CICMPDM.INP_DATA.CI_SUB_TYP = "02";
            CICMPDM.INP_DATA.EP_FLG = '0';
            CICMPDM.INP_DATA.PAY_GLY = '2';
            CICMPDM.INP_DATA.RESIDENT = CICOPDCP.DATA.RES_FLG;
            CICMPDM.INP_DATA.BAS_CI_ATTR = '1';
            CICMPDM.INP_DATA.MARI = "99";
            CICMPDM.INP_DATA.NATION = CICOPDCP.DATA.NATION;
            CICMPDM.INP_DATA.FCATYP = CICOPDCP.DATA.FCATYP;
            S000_LINK_CIZMPDM();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CICMPDM);
            CICMPDM.INP_DATA.CI_NO = CICOPDCP.DATA.CI_NO;
            CICMPDM.FUNC = 'I';
            S000_LINK_CIZMPDM();
            if (pgmRtn) return;
            if (CICOPDCP.DATA.SEX != ' ') {
                CICMPDM.INP_DATA.SEX = CICOPDCP.DATA.SEX;
            }
            if (CICOPDCP.DATA.BIRTH != 0) {
                CICMPDM.INP_DATA.BIRTH_DT = CICOPDCP.DATA.BIRTH;
            }
            if (CICOPDCP.DATA.REG_CNTY.trim().length() > 0) {
                CICMPDM.INP_DATA.REG_CNTY = CICOPDCP.DATA.REG_CNTY;
            }
            if (CICOPDCP.DATA.OCCP.trim().length() > 0) {
                CICMPDM.INP_DATA.OCCUP = CICOPDCP.DATA.OCCP;
            }
            if (CICMPDM.INP_DATA.BAS_SVR_LVL.trim().length() == 0) {
                CICMPDM.INP_DATA.BAS_SVR_LVL = "N";
            }
            if (CICMPDM.INP_DATA.CI_SUB_TYP.trim().length() == 0) {
                CICMPDM.INP_DATA.CI_SUB_TYP = "02";
            }
            if (CICMPDM.INP_DATA.EP_FLG == ' ') {
                CICMPDM.INP_DATA.EP_FLG = '0';
            }
            if (CICMPDM.INP_DATA.PAY_GLY == ' ') {
                CICMPDM.INP_DATA.PAY_GLY = '2';
            }
            if (CICOPDCP.DATA.RES_FLG != ' ') {
                CICMPDM.INP_DATA.RESIDENT = CICOPDCP.DATA.RES_FLG;
            }
            if (CICMPDM.INP_DATA.BAS_CI_ATTR == ' ') {
                CICMPDM.INP_DATA.BAS_CI_ATTR = '1';
            }
            if (CICMPDM.INP_DATA.MARI.trim().length() == 0) {
                CICMPDM.INP_DATA.MARI = "99";
            }
            if (CICOPDCP.DATA.NATION.trim().length() > 0) {
                CICMPDM.INP_DATA.NATION = CICOPDCP.DATA.NATION;
            }
            if (CICOPDCP.DATA.FCATYP.trim().length() > 0) {
                CICMPDM.INP_DATA.FCATYP = CICOPDCP.DATA.FCATYP;
            }
            CICMPDM.FUNC = 'M';
            S000_LINK_CIZMPDM();
            if (pgmRtn) return;
        }
    }
    public void B040_ADD_ID_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMID);
        CICMID.DATA.CI_NO = CICOPDCP.DATA.CI_NO;
        CICMID.DATA.SINGLE_DATA.S_ID_TYPE = CICOPDCP.DATA.ID_TYP;
        CICMID.DATA.SINGLE_DATA.S_ID_NO = CICOPDCP.DATA.ID_NO;
        CICMID.DATA.SINGLE_DATA.S_DESC = CICOPDCP.DATA.REMARK;
        CICMID.DATA.SINGLE_DATA.S_OPEN = 'Y';
        CICMID.DATA.SINGLE_DATA.S_ID_RGN = CICOPDCP.DATA.ID_RGN;
        CICMID.DATA.SINGLE_DATA.S_EFF_DT = CICOPDCP.DATA.EFF_DT;
        CICMID.DATA.SINGLE_DATA.S_EXP_DT = CICOPDCP.DATA.ID_EXPDT;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CICOPDCP.DATA.CI_NO;
        CIRID.KEY.ID_TYPE = CICOPDCP.DATA.ID_TYP;
        T000_READ_CITID();
        if (pgmRtn) return;
        if (WS_ID_FLAG == 'Y') {
            CICMID.FUNC = 'M';
        } else {
            CICMID.FUNC = 'A';
            CICMID.CTLW_1 = 'O';
            if (CICOPDCP.CTLW.CTLW_2 == 'N') {
                CICMID.CTLW_2 = 'O';
            }
        }
        S000_CALL_CIZMID();
        if (pgmRtn) return;
    }
    public void B050_ADD_NAME_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICOPDCP.DATA.NM_TYP1);
        CEP.TRC(SCCGWA, CICOPDCP.DATA.CI_NM1);
        IBS.init(SCCGWA, CICMNAM);
        CICMNAM.DATA.CI_NO = CICOPDCP.DATA.CI_NO;
        CICMNAM.DATA.SINGLE_DATA.S_NM_TYPE = CICOPDCP.DATA.NM_TYP1;
        CICMNAM.DATA.SINGLE_DATA.S_CI_NM = CICOPDCP.DATA.CI_NM1;
        CICMNAM.DATA.SINGLE_DATA.S_OPEN = 'Y';
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CICOPDCP.DATA.CI_NO;
        CIRNAM.KEY.NM_TYPE = CICOPDCP.DATA.NM_TYP1;
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (WS_NAM_FLAG == 'Y') {
            CICMNAM.FUNC = 'M';
        } else {
            CICMNAM.FUNC = 'A';
            CICMNAM.CTLW_1 = 'O';
            if (CICOPDCP.CTLW.CTLW_2 == 'N') {
                CICMNAM.CTLW_2 = 'O';
            }
        }
        S000_CALL_CIZMNAM();
        if (pgmRtn) return;
    }
    public void B060_ADD_ADDR_INF() throws IOException,SQLException,Exception {
        if (CICOPDCP.DATA.ADR_SRC.trim().length() > 0 
            && CICOPDCP.DATA.ADR.trim().length() > 0) {
            IBS.init(SCCGWA, CICMADR);
            CICMADR.CI_NO = CICOPDCP.DATA.CI_NO;
            CICMADR.SRC_NO = CICOPDCP.DATA.ADR_SRC;
            CICMADR.ADR_TYPE = "114";
            CICMADR.CNTY_CD = CICOPDCP.DATA.ADRCNTY;
            CICMADR.LADDR_L1 = CICOPDCP.DATA.ADRPROV;
            CICMADR.LADDR_L2 = CICOPDCP.DATA.ADRCITY;
            CICMADR.LADDR_L3 = CICOPDCP.DATA.ADRCOTY;
            CICMADR.LADDR_L4 = CICOPDCP.DATA.ADRDTL;
            CICMADR.EADDR_L1 = CICOPDCP.DATA.EADRBCY;
            CICMADR.EADDR_L2 = CICOPDCP.DATA.EADRCOY;
            CICMADR.EADDR_L3 = CICOPDCP.DATA.EADRCIY;
            CICMADR.EADDR_L4 = CICOPDCP.DATA.EADRPRE;
            CICMADR.ADR_NM = CICOPDCP.DATA.ADR;
            CICMADR.POST_CD = CICOPDCP.DATA.POST;
            CICMADR.STD_FLG = 'N';
            CICMADR.STD_CD = "88";
            IBS.init(SCCGWA, CIRADR);
            CIRADR.KEY.CI_NO = CICOPDCP.DATA.CI_NO;
            CIRADR.SRC_NO = CICOPDCP.DATA.ADR_SRC;
            T000_READ_CITADR();
            if (pgmRtn) return;
            if (WS_ADR_FLAG == 'Y') {
                CICMADR.FUNC = 'M';
            } else {
                CICMADR.FUNC = 'A';
                CICMADR.CTLW.CTLW_2 = 'N';
            }
            S000_LINK_CIZMADR();
            if (pgmRtn) return;
        }
    }
    public void B070_ADD_CNT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICOPDCP.DATA.MOB_SRC);
        CEP.TRC(SCCGWA, CICOPDCP.DATA.PHONE);
        if (CICOPDCP.DATA.MOB_SRC.trim().length() > 0 
            && CICOPDCP.DATA.PHONE.trim().length() > 0) {
            IBS.init(SCCGWA, CICMCNT);
            CICMCNT.CI_NO = CICOPDCP.DATA.CI_NO;
            CICMCNT.DATA.SINGLE_DATA.S_SRC_NO = CICOPDCP.DATA.MOB_SRC;
            CICMCNT.DATA.SINGLE_DATA.S_CNT_TYPE = "13";
            CICMCNT.DATA.SINGLE_DATA.S_CNT_INFO = CICOPDCP.DATA.PHONE;
            IBS.init(SCCGWA, CIRCNT);
            CIRCNT.KEY.CI_NO = CICOPDCP.DATA.CI_NO;
            CIRCNT.SRC_NO = CICOPDCP.DATA.MOB_SRC;
            T000_READ_CITCNT();
            if (pgmRtn) return;
            if (WS_CNT_FLAG == 'Y') {
                CICMCNT.FUNC = 'M';
            } else {
                CICMCNT.FUNC = 'A';
                CICMCNT.CTLW.CTLW_1 = 'O';
            }
            S000_LINK_CIZMCNT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICOPDCP.DATA.TEL_SRC);
        CEP.TRC(SCCGWA, CICOPDCP.DATA.TEL);
        if (CICOPDCP.DATA.TEL_SRC.trim().length() > 0 
            && CICOPDCP.DATA.TEL.trim().length() > 0) {
            IBS.init(SCCGWA, CICMCNT);
            CICMCNT.FUNC = 'A';
            CICMCNT.CTLW.CTLW_1 = 'O';
            CICMCNT.CI_NO = CICOPDCP.DATA.CI_NO;
            CICMCNT.DATA.SINGLE_DATA.S_SRC_NO = CICOPDCP.DATA.TEL_SRC;
            CICMCNT.DATA.SINGLE_DATA.S_CNT_TYPE = "16";
            CICMCNT.DATA.SINGLE_DATA.S_CNT_INFO = CICOPDCP.DATA.TEL;
            JIBS_stb = new StringTokenizer(CICOPDCP.DATA.TEL, "-");
            if (JIBS_stb.hasMoreTokens()) CICMCNT.DATA.SINGLE_DATA.S_CNT_ZONE = JIBS_stb.nextToken();
            if (JIBS_stb.hasMoreTokens()) CICMCNT.DATA.SINGLE_DATA.S_TEL_NO = JIBS_stb.nextToken();
            CEP.TRC(SCCGWA, CICMCNT.DATA.SINGLE_DATA.S_CNT_ZONE);
            CEP.TRC(SCCGWA, CICMCNT.DATA.SINGLE_DATA.S_TEL_NO);
            IBS.init(SCCGWA, CIRCNT);
            CIRCNT.KEY.CI_NO = CICOPDCP.DATA.CI_NO;
            CIRCNT.SRC_NO = CICOPDCP.DATA.TEL_SRC;
            T000_READ_CITCNT();
            if (pgmRtn) return;
            if (WS_CNT_FLAG == 'Y') {
                CICMCNT.FUNC = 'M';
            } else {
                CICMCNT.FUNC = 'A';
                CICMCNT.CTLW.CTLW_1 = 'O';
            }
            S000_LINK_CIZMCNT();
            if (pgmRtn) return;
        }
    }
    public void B080_ADD_STS_INF() throws IOException,SQLException,Exception {
        if (CICOPDCP.CTLW.CTLW_2 == 'N') {
            IBS.init(SCCGWA, CICMSTS);
            CEP.TRC(SCCGWA, "STS00");
            CICMSTS.DATA.CI_NO = CICOPDCP.DATA.CI_NO;
            CICMSTS.DATA.STS_CODE = K_STS_NORMAL;
            CICMSTS.FUNC = 'A';
            S000_LINK_CIZMSTS();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICMSTS);
            CEP.TRC(SCCGWA, "STS24");
            CICMSTS.DATA.CI_NO = CICOPDCP.DATA.CI_NO;
            CICMSTS.DATA.STS_CODE = K_STS_BSP;
            CICMSTS.FUNC = 'A';
            S000_LINK_CIZMSTS();
            if (pgmRtn) return;
        }
    }
    public void B090_ADD_SEC_STS_INF() throws IOException,SQLException,Exception {
        if (CICMPDM.INP_DATA.BIRTH_DT == 0 
            || CICMPDM.INP_DATA.REG_CNTY.trim().length() == 0 
            || CICOPDCP.DATA.ID_EXPDT == 0 
            || CICMPDM.INP_DATA.OCCUP.trim().length() == 0 
            || CICOPDCP.DATA.ADR_SRC.trim().length() == 0 
            || CICOPDCP.DATA.ADR.trim().length() == 0) {
            IBS.init(SCCGWA, CICMSTS);
            CEP.TRC(SCCGWA, "STS23");
            CICMSTS.DATA.CI_NO = CICOPDCP.DATA.CI_NO;
            CICMSTS.DATA.STS_CODE = K_STS_INC;
            CICMSTS.FUNC = 'A';
            S000_LINK_CIZMSTS();
            if (pgmRtn) return;
        }
    }
    public void B100_ADD_VER_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSVER);
        CICSVER.CI_DATE.CI_NO = CICOPDCP.DATA.CI_NO;
        CICSVER.CI_DATE.REAL_CHK = '1';
        CICSVER.CI_DATE.IDT_CHK = "02";
        IBS.CPY2CLS(SCCGWA, ALL, CICSVER.CI_DATE.DEAL_WAY);
        CICSVER.FUNC = 'M';
        S000_LINK_CIZSVER();
        if (pgmRtn) return;
    }
    public void B120_CHECK_CI_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMSTS);
        CICMSTS.FUNC = 'C';
        CICMSTS.DATA.CI_NO = CICOPDCP.DATA.CI_NO;
        S000_LINK_CIZMSTS();
        if (pgmRtn) return;
    }
    public void B110_ADD_HIS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        S000_CALL_SCZGJRN();
        if (pgmRtn) return;
        GWA_BP_AREA.NFHIS_CUR_SEQ = 0;
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.CI_NO = CICOPDCP.DATA.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY;
        BPCPNHIS.INFO.NEW_DAT_PT = CICOPDCP;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZMNAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-NAM", CICMNAM, true);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICMNAM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMNAM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    } //FROM #ENDIF
    }
    public void S000_CALL_CIZMID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ID       ", CICMID, true);
        if (CICMID.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMID.RC);
        }
    }
    public void S000_LINK_CIZMPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-PDM   ", CICMPDM, true);
    }
    public void S000_LINK_CIZMCNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CNT   ", CICMCNT, true);
        if (CICMCNT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMCNT.RC);
        }
    }
    public void S000_LINK_CIZMADR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ADR   ", CICMADR, true);
        if (CICMADR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMADR.RC);
        }
    }
    public void S000_LINK_CIZMSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-REC-CIZMSTS", CICMSTS, true);
        if (CICMSTS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMSTS.RC);
        }
    }
    public void S000_LINK_CIZSVER() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-VER-CIZSVER", CICSVER, true);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_SCZGJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
        SCCCALL.COMMAREA_PTR = null;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BAS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BAS_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.READ(SCCGWA, CIRID, CITID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ID_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ID_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITID";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        CITNAM_RD.col = "CI_NO";
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_NAM_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_NAM_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITNAM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.eqWhere = "CI_NO,SRC_NO";
        CITCNT_RD.col = "CI_NO";
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CNT_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CNT_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCNT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITADR() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        CITADR_RD.eqWhere = "CI_NO,SRC_NO";
        CITADR_RD.col = "CI_NO";
        IBS.READ(SCCGWA, CIRADR, CITADR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADR_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ADR_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITADR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICOPDCP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICOPDCP=");
            CEP.TRC(SCCGWA, CICOPDCP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
