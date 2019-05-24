package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSQPC2 {
    int BAS_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITPDM_RD;
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    DBParm CITACR_RD;
    brParm CITCNT_BR = new brParm();
    brParm CITADR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_I = 0;
    short WS_II = 0;
    String WS_CI_NO = " ";
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
    CIRCNT CIRCNT = new CIRCNT();
    CIRADR CIRADR = new CIRADR();
    CIRACR CIRACR = new CIRACR();
    CICFB11 CICFB11 = new CICFB11();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSQPC2 CICSQPC2;
    public void MP(SCCGWA SCCGWA, CICSQPC2 CICSQPC2) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSQPC2 = CICSQPC2;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSQPC2 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQPC2.RC);
        IBS.init(SCCGWA, CICFB11);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CI_NO();
        if (pgmRtn) return;
        B030_INQ_PER_BAS_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_GET_CI_NO() throws IOException,SQLException,Exception {
        if (CICSQPC2.DATA.CI_NO.trim().length() > 0) {
            WS_CI_NO = CICSQPC2.DATA.CI_NO;
        } else if (CICSQPC2.DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICSQPC2.DATA.AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND);
            }
            WS_CI_NO = CIRACR.CI_NO;
        } else if (CICSQPC2.DATA.ID_TYPE.trim().length() > 0 
                && CICSQPC2.DATA.ID_NO.trim().length() > 0 
                && CICSQPC2.DATA.CI_NM.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.ID_TYPE = CICSQPC2.DATA.ID_TYPE;
            CIRBAS.ID_NO = CICSQPC2.DATA.ID_NO;
            CIRBAS.CI_NM = CICSQPC2.DATA.CI_NM;
            BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
            T000_READ_CITBAS_BY_IDNM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "NOT FOUND");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
            }
            WS_CI_NO = CIRBAS.KEY.CI_NO;
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B030_INQ_PER_BAS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
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
        CICFB11.DATA.ID_REMARK = CIRID.REMARK;
        CICFB11.DATA.ID_EXPDT = CIRID.EXP_DT;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB11.DATA.CI_ENM = CIRNAM.CI_NM;
        }
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_STARTBR_CITCNT();
        if (pgmRtn) return;
        T000_READNEXT_CITCNT();
        if (pgmRtn) return;
        for (WS_I = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_I += 1) {
            R000_TRANS_CNT_DATA_TO_OUTPUT();
            if (pgmRtn) return;
            T000_READNEXT_CITCNT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCNT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRADR);
        CIRADR.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_STARTBR_CITADR();
        if (pgmRtn) return;
        T000_READNEXT_CITADR();
        if (pgmRtn) return;
        for (WS_II = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_II += 1) {
            R000_TRANS_ADR_DATA_TO_OUTPUT();
            if (pgmRtn) return;
            T000_READNEXT_CITADR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITADR();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_TRANS_BAS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICFB11.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICFB11.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICFB11.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICFB11.DATA.CI_STSW = CIRBAS.STSW;
        CICFB11.DATA.IDE_STSW = CIRBAS.IDE_STSW;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICFB11.DATA.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICFB11.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICFB11.DATA.ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
        JIBS_tmp_int = CIRBAS.VER_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
        if (CIRBAS.VER_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICFB11.DATA.VER_STS = 'T';
        } else {
            CICFB11.DATA.VER_STS = 'F';
        }
        CICFB11.DATA.SVR_LVL = CIRBAS.SVR_LVL;
        CICFB11.DATA.OIC_NO = CIRBAS.OIC_NO;
    }
    public void R000_TRANS_PDM_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICFB11.DATA.CI_SUB_TYP = CIRPDM.CI_SUB_TYP;
        CICFB11.DATA.RESIDENT = CIRPDM.RESIDENT;
        CICFB11.DATA.SEX = CIRPDM.SEX;
        CICFB11.DATA.BIRTH_DT = CIRPDM.BIRTH_DT;
        CICFB11.DATA.REG_CNTY = CIRPDM.REG_CNTY;
        CICFB11.DATA.NATION = CIRPDM.NATION;
        CICFB11.DATA.OCCUP = CIRPDM.OCCUP;
        CICFB11.DATA.MARI = CIRPDM.MARI;
        CICFB11.DATA.SID_FLG = CIRPDM.SID_FLG;
        CICFB11.DATA.REMARK = CIRPDM.REMARK;
        CICFB11.DATA.EDU_LVL = CIRPDM.EDU_LVL;
        CICFB11.DATA.PER_INC = CIRPDM.PER_INC;
        CICFB11.DATA.FAM_INC = CIRPDM.FAM_INC;
        CICFB11.DATA.CORP_NM = CIRPDM.CORP_NM;
        CICFB11.DATA.CORP_INDUS = CIRPDM.CORP_INDUS;
    }
    public void R000_TRANS_CNT_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        if (CIRCNT.KEY.CNT_TYPE.equalsIgnoreCase("21")) {
            CICFB11.DATA.TEL_CNT_CNTY = CIRCNT.CNT_CNTY;
            CICFB11.DATA.TEL_NO = CIRCNT.TEL_NO;
        } else if (CIRCNT.KEY.CNT_TYPE.equalsIgnoreCase("11")) {
            CICFB11.DATA.FARM_CNT_CNTY = CIRCNT.CNT_CNTY;
            CICFB11.DATA.FARM_TEL_NO = CIRCNT.TEL_NO;
        } else if (CIRCNT.KEY.CNT_TYPE.equalsIgnoreCase("12")) {
            CICFB11.DATA.COMP_CNT_CNTY = CIRCNT.CNT_CNTY;
            CICFB11.DATA.COMP_TEL_NO = CIRCNT.TEL_NO;
        } else if (CIRCNT.KEY.CNT_TYPE.equalsIgnoreCase("30")) {
            CICFB11.DATA.EMAIL = CIRCNT.CNT_INFO;
        } else {
        }
    }
    public void R000_TRANS_ADR_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        if (CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("111")) {
            CICFB11.DATA.CONT_CNTY_CD = CIRADR.CNTY_CD;
            CICFB11.DATA.CONT_ADR_NM = CIRADR.ADR_NM;
            CICFB11.DATA.CONT_L1 = CIRADR.LADDR_L1;
            CICFB11.DATA.CONT_L2 = CIRADR.LADDR_L2;
            CICFB11.DATA.CONT_L3 = CIRADR.LADDR_L3;
            CICFB11.DATA.CONT_L4 = CIRADR.LADDR_L4;
            CICFB11.DATA.CONT_L5 = CIRADR.LADDR_L5;
            CICFB11.DATA.CONT_L6 = CIRADR.LADDR_L6;
            CICFB11.DATA.CONT_L7 = CIRADR.LADDR_L7;
            CICFB11.DATA.CONT_E2 = CIRADR.LADDR_E2;
            CICFB11.DATA.CONT_POST_CD = CIRADR.POST_CD;
        } else if (CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("115")) {
            CICFB11.DATA.COMP_CNTY_CD = CIRADR.CNTY_CD;
            CICFB11.DATA.COMP_ADR_NM = CIRADR.ADR_NM;
            CICFB11.DATA.COMP_L1 = CIRADR.LADDR_L1;
            CICFB11.DATA.COMP_L2 = CIRADR.LADDR_L2;
            CICFB11.DATA.COMP_L3 = CIRADR.LADDR_L3;
            CICFB11.DATA.COMP_L4 = CIRADR.LADDR_L4;
            CICFB11.DATA.COMP_L5 = CIRADR.LADDR_L5;
            CICFB11.DATA.COMP_L6 = CIRADR.LADDR_L6;
            CICFB11.DATA.COMP_L7 = CIRADR.LADDR_L7;
            CICFB11.DATA.COMP_E2 = CIRADR.LADDR_E2;
            CICFB11.DATA.COMP_POST_CD = CIRADR.POST_CD;
        } else if (CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("114")) {
            CICFB11.DATA.FARM_CNTY_CD = CIRADR.CNTY_CD;
            CICFB11.DATA.FARM_ADR_NM = CIRADR.ADR_NM;
            CICFB11.DATA.FARM_L1 = CIRADR.LADDR_L1;
            CICFB11.DATA.FARM_L2 = CIRADR.LADDR_L2;
            CICFB11.DATA.FARM_L3 = CIRADR.LADDR_L3;
            CICFB11.DATA.FARM_L4 = CIRADR.LADDR_L4;
            CICFB11.DATA.FARM_L5 = CIRADR.LADDR_L5;
            CICFB11.DATA.FARM_L6 = CIRADR.LADDR_L6;
            CICFB11.DATA.FARM_L7 = CIRADR.LADDR_L7;
            CICFB11.DATA.COMP_E2 = CIRADR.LADDR_E2;
            CICFB11.DATA.FARM_POST_CD = CIRADR.POST_CD;
        } else if (CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("130")) {
            CICFB11.DATA.ID_CNTY_CD = CIRADR.CNTY_CD;
            CICFB11.DATA.ID_ADR_NM = CIRADR.ADR_NM;
            CICFB11.DATA.ID_L1 = CIRADR.LADDR_L1;
            CICFB11.DATA.ID_L2 = CIRADR.LADDR_L2;
            CICFB11.DATA.ID_L3 = CIRADR.LADDR_L3;
            CICFB11.DATA.ID_L4 = CIRADR.LADDR_L4;
            CICFB11.DATA.ID_L5 = CIRADR.LADDR_L5;
            CICFB11.DATA.ID_L6 = CIRADR.LADDR_L6;
            CICFB11.DATA.ID_L7 = CIRADR.LADDR_L7;
            CICFB11.DATA.ID_E2 = CIRADR.LADDR_E2;
            CICFB11.DATA.ID_POST_CD = CIRADR.POST_CD;
        } else {
        }
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIB11";
        SCCFMT.DATA_PTR = CICFB11;
        SCCFMT.DATA_LEN = 3928;
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
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_STARTBR_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_BR.rp = new DBParm();
        CITCNT_BR.rp.TableName = "CITCNT";
        CITCNT_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRCNT, CITCNT_BR);
    }
    public void T000_READNEXT_CITCNT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCNT, this, CITCNT_BR);
    }
    public void T000_ENDBR_CITCNT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCNT_BR);
    }
    public void T000_STARTBR_CITADR() throws IOException,SQLException,Exception {
        CITADR_BR.rp = new DBParm();
        CITADR_BR.rp.TableName = "CITADR";
        CITADR_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRADR, CITADR_BR);
    }
    public void T000_READNEXT_CITADR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRADR, this, CITADR_BR);
    }
    public void T000_ENDBR_CITADR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITADR_BR);
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
